package com.nextuple.OrderManagement.exception;

public class InvalidPriceException extends RuntimeException{
    private String message;
    public InvalidPriceException(){}

    public InvalidPriceException(String msg)
    {
        super();
        this.message=msg;
    }

    public String getMessage() {
        return message;
    }
}
