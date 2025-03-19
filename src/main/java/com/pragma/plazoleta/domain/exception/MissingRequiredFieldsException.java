package com.pragma.plazoleta.domain.exception;

public class MissingRequiredFieldsException extends RuntimeException{

    public MissingRequiredFieldsException(String message) {
        super(message);
    }

}
