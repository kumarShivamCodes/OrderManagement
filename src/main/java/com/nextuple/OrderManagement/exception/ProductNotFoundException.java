package com.nextuple.OrderManagement.exception;

public class ProductNotFoundException extends RuntimeException{

    private String message;
    public ProductNotFoundException(){}
    public ProductNotFoundException(String msg)
    {
        super();
        this.message=msg;
    }
    public String getMessage() {
        return message;
    }
}
