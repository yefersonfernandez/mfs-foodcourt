package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.application.mapper.request.IRestaurantRequestMapper;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandlerImpl implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantModel restaurantModel = restaurantRequestMapper.toRestaurantModel(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurantModel);
    }
}
