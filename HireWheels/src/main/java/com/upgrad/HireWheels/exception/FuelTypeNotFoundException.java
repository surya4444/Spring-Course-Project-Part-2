package com.upgrad.HireWheels.exception;

public class FuelTypeNotFoundException extends Exception{

    public FuelTypeNotFoundException() {
    }

    public FuelTypeNotFoundException(String message) {
        super(message);
    }

    public FuelTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FuelTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public FuelTypeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
