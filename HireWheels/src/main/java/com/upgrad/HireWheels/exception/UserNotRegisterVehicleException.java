package com.upgrad.HireWheels.exception;

public class UserNotRegisterVehicleException extends Exception{

    public UserNotRegisterVehicleException() {
    }

    public UserNotRegisterVehicleException(String message) {
        super(message);
    }

    public UserNotRegisterVehicleException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotRegisterVehicleException(Throwable cause) {
        super(cause);
    }

    public UserNotRegisterVehicleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
