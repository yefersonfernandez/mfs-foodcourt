package com.pragma.plazoleta.domain.utils;

public class ErrorMessages {

    public static final String RESTAURANT_NAME_REQUIRED = "Restaurant name is required.";
    public static final String NIT_REQUIRED = "NIT is required.";
    public static final String ADDRESS_REQUIRED = "Address is required.";
    public static final String PHONE_REQUIRED = "Phone number is required.";
    public static final String LOGO_URL_REQUIRED = "Logo URL is required.";
    public static final String OWNER_ID_REQUIRED = "Owner ID is required.";

    public static final String INVALID_NIT = "NIT must contain only numbers.";
    public static final String INVALID_PHONE = "Invalid phone number format";
    public static final String INVALID_RESTAURANT_NAME = "Restaurant name cannot consist only of numbers.";

    public static final String USER_NOT_FOUND = "The user does not exist.";
    public static final String INVALID_USER_ROLE = "The user must have the OWNER role to create a restaurant.";

    public static final String DISH_NAME_REQUIRED = "The dish name is required.";
    public static final String DISH_PRICE_REQUIRED = "The dish price is required.";
    public static final String DISH_DESCRIPTION_REQUIRED = "The dish description is required.";
    public static final String DISH_IMAGE_URL_REQUIRED = "The dish image URL is required.";
    public static final String DISH_RESTAURANT_REQUIRED = "The dish restaurant is required.";
    public static final String DISH_CATEGORY_REQUIRED = "The dish category is required.";
    public static final String DISH_PRICE_MUST_BE_POSITIVE = "The dish price must be a positive integer greater than zero.";

    public static final String RESTAURANT_NOT_FOUND = "The restaurant does not exist.";
    public static final String CATEGORY_NOT_FOUND = "The category does not exist.";
    public static final String DISH_NOT_FOUND = "The dish does not exist.";

    private ErrorMessages() {
        throw new UnsupportedOperationException("Utility class");
    }
}