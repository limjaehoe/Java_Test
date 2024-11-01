package com.example.javat1application.t_future;

public class DeviceException extends Exception {
    public DeviceException(String message) {
        super(message);
    }

    public DeviceException(String message, Throwable cause) {
        super(message, cause);
    }
}