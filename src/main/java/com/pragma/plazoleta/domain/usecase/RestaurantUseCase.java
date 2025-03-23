package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.exception.*;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.IUserFeignClientPort;
import com.pragma.plazoleta.domain.utils.ErrorMessages;
import com.pragma.plazoleta.domain.utils.ValidationConstants;

import java.util.Optional;
import java.util.regex.Pattern;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserFeignClientPort userFeignClientPort;

    private static final Pattern NIT_PATTERN = Pattern.compile(ValidationConstants.NIT_REGEX);
    private static final Pattern PHONE_PATTERN = Pattern.compile(ValidationConstants.PHONE_REGEX);
    private static final Pattern NAME_PATTERN = Pattern.compile(ValidationConstants.NAME_REGEX);

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserFeignClientPort userFeignClientPort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userFeignClientPort = userFeignClientPort;
    }

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        validateRestaurant(restaurantModel);
        restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public RestaurantModel getRestaurantById(Long restaurantId) {
        return restaurantPersistencePort.getRestaurantById(restaurantId);
    }

    private void validateRestaurant(RestaurantModel restaurantModel) {
        validateRequiredFields(restaurantModel);
        validateFormats(restaurantModel);
        validateUser(restaurantModel.getOwnerId());
    }

    private void validateRequiredFields(RestaurantModel restaurantModel) {
        Optional.ofNullable(restaurantModel.getName())
                .filter(name -> !name.isEmpty())
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.RESTAURANT_NAME_REQUIRED));

        Optional.ofNullable(restaurantModel.getNit())
                .filter(nit -> !nit.isEmpty())
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.NIT_REQUIRED));

        Optional.ofNullable(restaurantModel.getAddress())
                .filter(address -> !address.isEmpty())
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.ADDRESS_REQUIRED));

        Optional.ofNullable(restaurantModel.getPhone())
                .filter(phone -> !phone.isEmpty())
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.PHONE_REQUIRED));

        Optional.ofNullable(restaurantModel.getUrlLogo())
                .filter(logo -> !logo.isEmpty())
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.LOGO_URL_REQUIRED));

        Optional.ofNullable(restaurantModel.getOwnerId())
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.OWNER_ID_REQUIRED));
    }

    private void validateFormats(RestaurantModel restaurant) {
        validateNit(restaurant.getNit());
        validatePhoneNumber(restaurant.getPhone());
        validateRestaurantName(restaurant.getName());
    }

    private void validateNit(String nit) {
        if (!NIT_PATTERN.matcher(nit).matches()) {
            throw new InvalidNitException(ErrorMessages.INVALID_NIT);
        }
    }

    private void validatePhoneNumber(String phone) {
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new InvalidPhoneNumberException(ErrorMessages.INVALID_PHONE);
        }
    }

    private void validateRestaurantName(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new InvalidRestaurantNameException(ErrorMessages.INVALID_RESTAURANT_NAME);
        }
    }

    private void validateUser(Long userId) {
        UserModel userModel = Optional.ofNullable(userFeignClientPort.getUserById(userId))
                .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND));

        if (!ValidationConstants.ROLE_ADMIN.equalsIgnoreCase(userModel.getRoleModel().getName())) {
            throw new InvalidUserRoleException(ErrorMessages.INVALID_USER_ROLE);
        }
    }
}
