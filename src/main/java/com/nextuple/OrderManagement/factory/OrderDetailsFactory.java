package com.nextuple.OrderManagement.factory;

import com.nextuple.OrderManagement.entity.OrderDetails;

public interface OrderDetailsFactory {
    OrderDetails createOrderDetails(String orderId, Double orderAmount);
}
