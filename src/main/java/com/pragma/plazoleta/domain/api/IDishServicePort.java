package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.DishModel;

public interface IDishServicePort {

    void saveDish(DishModel dishModel);

    DishModel getDishById(Long dishId);

    void updateDish(Long dishId, DishModel dishModel);
}
