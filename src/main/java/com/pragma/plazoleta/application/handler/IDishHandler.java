package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;


public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto);

    DishResponseDto getDishById(Long dishId);

    void updateDish(Long dishId, DishRequestDto dishRequestDto);

}
