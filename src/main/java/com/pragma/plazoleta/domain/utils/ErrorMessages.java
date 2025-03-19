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

    private ErrorMessages() {
        throw new UnsupportedOperationException("Utility class");
    }
}