package com.nextuple.OrderManagement.exception;

public class OrderNotFoundException extends RuntimeException{
    private String message;
    public OrderNotFoundException(){}

    public OrderNotFoundException(String msg)
    {
        super();
        this.message=msg;
    }

    public String getMessage() {
        return message;
    }
}
