package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.DishModel;

public interface IDishPersistencePort {

    void saveDish(DishModel dishModel);

    DishModel getDishById(Long dishId);

}
