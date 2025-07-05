package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.exception.*;
import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.spi.ICategoryPersistencePort;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.ITokenPort;
import com.pragma.plazoleta.domain.utils.ErrorMessages;

import java.util.Optional;

import static com.pragma.plazoleta.domain.utils.ErrorMessages.USER_NOT_OWNER_OF_RESTAURANT;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final ITokenPort tokenPort;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, ICategoryPersistencePort categoryPersistencePort, ITokenPort tokenPort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.tokenPort = tokenPort;
    }

    @Override
    public void saveDish(DishModel dishModel) {
        validateDish(dishModel);

        dishModel.setActive(true);
        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel getDishById(Long dishId) {
        return dishPersistencePort.getDishById(dishId)
                .orElseThrow(() -> new DishNotFoundException(ErrorMessages.dishNotFound(dishId)));
    }

    @Override
    public void updateDish(Long dishId, DishModel dishModel) {
        DishModel updateDish = dishPersistencePort.getDishById(dishId)
                .orElseThrow(() -> new DishNotFoundException(ErrorMessages.dishNotFound(dishId)));

        validateDishOwnership(updateDish.getRestaurantModel().getId());

        validateDishUpdate(dishModel);
        Optional.ofNullable(dishModel.getPrice()).ifPresent(updateDish::setPrice);
        Optional.ofNullable(dishModel.getDescription()).ifPresent(updateDish::setDescription);

        dishPersistencePort.saveDish(updateDish);
    }

    private void validateDish(DishModel dishModel) {
        validateRequiredFields(dishModel);
        validateFormats(dishModel);
        validateRestaurant(dishModel.getCategoryModel().getId());
        validateCategory(dishModel.getCategoryModel().getId());
        validateDishOwnership(dishModel.getRestaurantModel().getId());
    }

    private void validateDishUpdate(DishModel dishModel) {
        Optional.ofNullable(dishModel.getPrice()).ifPresent(this::validatePositivePrice);
    }

    private void validateRequiredFields(DishModel dishModel) {
        Optional.ofNullable(dishModel.getName())
                .filter(name -> !name.trim().isEmpty())
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.DISH_NAME_REQUIRED));

        Optional.ofNullable(dishModel.getPrice())
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.DISH_PRICE_REQUIRED));

        Optional.ofNullable(dishModel.getDescription())
                .filter(description -> !description.trim().isEmpty())
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.DISH_DESCRIPTION_REQUIRED));

        Optional.ofNullable(dishModel.getImageUrl())
                .filter(url -> !url.trim().isEmpty())
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.DISH_IMAGE_URL_REQUIRED));

        Optional.ofNullable(dishModel.getRestaurantModel())
                .filter(restaurant -> restaurant.getId() != null)
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.DISH_RESTAURANT_REQUIRED));

        Optional.ofNullable(dishModel.getCategoryModel())
                .filter(category -> category.getId() != null)
                .orElseThrow(() -> new MissingRequiredFieldsException(ErrorMessages.DISH_CATEGORY_REQUIRED));
    }

    private void validateFormats(DishModel dishModel) {
        validatePositivePrice(dishModel.getPrice());
    }

    private void validatePositivePrice(Integer price) {
        if (price <= 0) {
            throw new InvalidPriceException(ErrorMessages.DISH_PRICE_MUST_BE_POSITIVE);
        }
    }

    private void validateRestaurant(Long restaurantId) {
        restaurantPersistencePort.getRestaurantById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(ErrorMessages.restaurantNotFound(restaurantId)));
    }

    private void validateCategory(Long categoryId) {
        categoryPersistencePort.getCategoryById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(ErrorMessages.categoryNotFound(categoryId)));
    }

    private void validateDishOwnership(Long restaurantId) {
        Long userId = tokenPort.getAuthenticatedUserId(tokenPort.getBearerToken());

        RestaurantModel restaurant = restaurantPersistencePort.getRestaurantById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(ErrorMessages.restaurantNotFound(restaurantId)));

        if (!restaurant.getOwnerId().equals(userId)) {
            throw new UserNotRestaurantOwnerException(USER_NOT_OWNER_OF_RESTAURANT);
        }
    }
}
