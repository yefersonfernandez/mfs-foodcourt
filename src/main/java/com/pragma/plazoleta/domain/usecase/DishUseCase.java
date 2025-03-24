package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.exception.*;
import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.spi.ICategoryPersistencePort;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.utils.ErrorMessages;

import java.util.Optional;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveDish(DishModel dishModel) {
        validateDish(dishModel);

        dishModel.setActive(true);
        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel getDishById(Long dishId) {
        return dishPersistencePort.getDishById(dishId);
    }

    @Override
    public void updateDish(Long dishId, DishModel dishModel) {
        DishModel updateDish = dishPersistencePort.getDishById(dishId);

        if (updateDish == null)
            throw new DishNotFoundException(ErrorMessages.DISH_NOT_FOUND);

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
        if (restaurantPersistencePort.getRestaurantById(restaurantId) == null) {
            throw new RestaurantNotFoundException(ErrorMessages.RESTAURANT_NOT_FOUND);
        }
    }

    private void validateCategory(Long categoryId) {
        if (categoryPersistencePort.getCategoryById(categoryId) == null) {
            throw new CategoryNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND);
        }
    }
}
