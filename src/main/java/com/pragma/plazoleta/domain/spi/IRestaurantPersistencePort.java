package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.RestaurantModel;

import java.util.Optional;

public interface IRestaurantPersistencePort {

    void saveRestaurant(RestaurantModel model);

    Optional<RestaurantModel> getRestaurantById(Long restaurantId);
}
