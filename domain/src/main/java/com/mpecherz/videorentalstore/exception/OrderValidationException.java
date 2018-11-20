package com.mpecherz.videorentalstore.exception;


import lombok.Getter;

@Getter
public class OrderValidationException extends RuntimeException {
    private final String errorCode;

    public OrderValidationException(String errorCode) {
        this.errorCode = errorCode;
    }

    public OrderValidationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
