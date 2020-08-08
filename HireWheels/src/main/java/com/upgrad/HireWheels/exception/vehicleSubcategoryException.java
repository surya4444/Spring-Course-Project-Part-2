package com.upgrad.HireWheels.exception;

public class vehicleSubcategoryException extends Exception{

    public vehicleSubcategoryException() {
    }

    public vehicleSubcategoryException(String message) {
        super(message);
    }

    public vehicleSubcategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public vehicleSubcategoryException(Throwable cause) {
        super(cause);
    }

    public vehicleSubcategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
