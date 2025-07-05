package com.pragma.plazoleta.domain.exception;

public class UserNotRestaurantOwnerException extends RuntimeException{
    public UserNotRestaurantOwnerException(String message) {
        super(message);
    }
}
