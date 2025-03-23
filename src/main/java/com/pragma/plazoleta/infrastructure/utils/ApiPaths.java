package com.pragma.plazoleta.infrastructure.utils;

public class ApiPaths {

    public static final String BASE_RESTAURANT = "/api/v1/restaurants";
    public static final String BASE_DISH = "/api/v1/dishes";
    public static final String BASE_CATEGORY = "/api/v1/categories";

    public static final String CREATE_RESTAURANT = "/";
    public static final String GET_RESTAURANT_BY_ID = "/{id}";

    public static final String CREATE_DISH = "/";
    public static final String GET_DISH_BY_ID = "/{id}";

    public static final String CREATE_CATEGORY = "/";
    public static final String GET_CATEGORY_BY_ID = "/{id}";

    private ApiPaths() {
        throw new UnsupportedOperationException("Utility class");
    }
}