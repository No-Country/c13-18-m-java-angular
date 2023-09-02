package com.grupo18.nocountry.greenpoint.exceptions;

public class InsufficientPointsException extends RuntimeException{
    public InsufficientPointsException(String message) {
        super(message);
    }
}
