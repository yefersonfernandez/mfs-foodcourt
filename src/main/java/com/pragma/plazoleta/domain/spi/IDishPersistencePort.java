package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.DishModel;

import java.util.Optional;

public interface IDishPersistencePort {

    void saveDish(DishModel dishModel);

    Optional<DishModel> getDishById(Long dishId);

}
