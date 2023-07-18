package com.nextuple.OrderManagement.dto;

import lombok.Data;

@Data
public class ProductOutputDto {
    private String name;
    private Double price;
    private String category;
    private Integer quantity;
}
