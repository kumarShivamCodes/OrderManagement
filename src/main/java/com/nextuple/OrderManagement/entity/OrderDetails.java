package com.nextuple.OrderManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class OrderDetails {
    private String orderId;
    private Double orderAmount;
}
