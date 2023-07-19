package com.nextuple.OrderManagement.factory;

import com.nextuple.OrderManagement.entity.OrderDetails;
import org.springframework.stereotype.Component;

@Component
public class DefaultOrderDetailsFactory implements OrderDetailsFactory{
    @Override
    public OrderDetails createOrderDetails(String orderId, Double orderAmount) {
        return  new OrderDetails(orderId,orderAmount);
    }
}
