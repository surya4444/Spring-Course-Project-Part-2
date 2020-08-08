package com.upgrad.HireWheels.exception;

import org.springframework.http.HttpStatus;

public class changeAvailabilityException extends Exception{

    public changeAvailabilityException() {
    }

    public changeAvailabilityException(String message) {
        super(message);
    }

    public changeAvailabilityException(String message, Throwable cause) {
        super(message, cause);
    }

    public changeAvailabilityException(Throwable cause) {
        super(cause);
    }

    public changeAvailabilityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}