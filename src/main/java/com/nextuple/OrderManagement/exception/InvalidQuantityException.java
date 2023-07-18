package com.nextuple.OrderManagement.exception;

public class InvalidQuantityException extends RuntimeException{

    private String message;
    public InvalidQuantityException(){}

    public InvalidQuantityException(String msg)
    {
        super();
        this.message=msg;
    }

    public String getMessage() {
        return message;
    }

}
