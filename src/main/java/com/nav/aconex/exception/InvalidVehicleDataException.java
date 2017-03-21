package com.nav.aconex.exception;

public class InvalidVehicleDataException extends Exception{
    public InvalidVehicleDataException(String message) {
        super(message);
    }

    public InvalidVehicleDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
