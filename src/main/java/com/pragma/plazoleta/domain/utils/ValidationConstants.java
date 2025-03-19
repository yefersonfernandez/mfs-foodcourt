package com.pragma.plazoleta.domain.utils;

public class ValidationConstants {
    public static final String NIT_REGEX = "\\d+";
    public static final String PHONE_REGEX = "^\\+?\\d{1,13}$";
    public static final String NAME_REGEX = "\\D+.*";

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
