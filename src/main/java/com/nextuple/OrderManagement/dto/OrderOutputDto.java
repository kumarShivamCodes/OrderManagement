package com.nextuple.OrderManagement.dto;

import lombok.Data;

@Data
public class OrderOutputDto {
    private String id;
    private String productName;
    private Integer quantity;
    private String type;
    private Double productPrice;
    private Double orderPrice;
}
