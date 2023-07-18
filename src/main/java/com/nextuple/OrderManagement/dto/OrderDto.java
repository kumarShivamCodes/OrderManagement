package com.nextuple.OrderManagement.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String productName;
    private Integer quantity;
    private String type;
}
