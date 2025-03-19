package com.pragma.plazoleta.infrastructure.utils;

public class ApiDescriptions {
    public static final String RESTAURANTS_TAG = "Operations related to restaurants";
    public static final String CREATE_RESTAURANT_SUMMARY = "Create a new restarurant";
    public static final String CREATE_RESTAURANT_PARAM = "Restaurant data for registration";
    public static final String CREATE_RESTAURANT_201 = "Restaurant created";
    public static final String CREATE_RESTAURANT_409 = "Restaurant already exists";

    private ApiDescriptions() {
        throw new UnsupportedOperationException("Utility class");
    }
}
