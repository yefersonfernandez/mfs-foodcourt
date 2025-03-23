package com.pragma.plazoleta.infrastructure.utils;

public class ApiDescriptions {
    public static final String RESTAURANT_TAG = "Restaurant Operations";

    public static final String CREATE_RESTAURANT_SUMMARY = "Create a new restaurant";
    public static final String CREATE_RESTAURANT_DESCRIPTION = "Register a new restaurant with the provided details";
    public static final String CREATE_RESTAURANT_PARAM = "Restaurant data for registration";
    public static final String CREATE_RESTAURANT_SUCCESS = "Restaurant successfully created";
    public static final String CREATE_RESTAURANT_CONFLICT = "Restaurant already exists";

    public static final String GET_RESTAURANT_BY_ID_SUMMARY = "Get restaurant details by ID";
    public static final String GET_RESTAURANT_BY_ID_DESCRIPTION = "Retrieve information about a specific restaurant using its ID";
    public static final String GET_RESTAURANT_BY_ID_PARAM = "ID of the restaurant to retrieve";
    public static final String GET_RESTAURANT_BY_ID_SUCCESS = "Restaurant retrieved successfully";
    public static final String GET_RESTAURANT_BY_ID_NOT_FOUND = "Restaurant not found";

    public static final String DISH_TAG = "Dish Operations";

    public static final String CREATE_DISH_SUMMARY = "Create a new dish";
    public static final String CREATE_DISH_DESCRIPTION = "Register a new dish with the provided details";
    public static final String CREATE_DISH_PARAM = "Dish data for registration";
    public static final String CREATE_DISH_SUCCESS = "Dish successfully created";
    public static final String CREATE_DISH_BAD_REQUEST = "Invalid dish data";

    public static final String GET_DISH_BY_ID_SUMMARY = "Get dish details by ID";
    public static final String GET_DISH_BY_ID_DESCRIPTION = "Retrieve information about a specific dish using its ID";
    public static final String GET_DISH_BY_ID_PARAM = "ID of the dish to retrieve";
    public static final String GET_DISH_BY_ID_SUCCESS = "Dish retrieved successfully";
    public static final String GET_DISH_BY_ID_NOT_FOUND = "Dish not found";

    public static final String CATEGORY_TAG = "Category Operations";

    public static final String CREATE_CATEGORY_SUMMARY = "Create a new category";
    public static final String CREATE_CATEGORY_DESCRIPTION = "Register a new category with the provided details";
    public static final String CREATE_CATEGORY_PARAM = "Category data for registration";
    public static final String CREATE_CATEGORY_SUCCESS = "Category successfully created";
    public static final String CREATE_CATEGORY_CONFLICT = "Category already exists";

    public static final String GET_CATEGORY_BY_ID_SUMMARY = "Get category details by ID";
    public static final String GET_CATEGORY_BY_ID_DESCRIPTION = "Retrieve information about a specific category using its ID";
    public static final String GET_CATEGORY_BY_ID_PARAM = "ID of the category to retrieve";
    public static final String GET_CATEGORY_BY_ID_SUCCESS = "Category retrieved successfully";
    public static final String GET_CATEGORY_BY_ID_NOT_FOUND = "Category not found";

    private ApiDescriptions() {
        throw new UnsupportedOperationException("Utility class");
    }
}
