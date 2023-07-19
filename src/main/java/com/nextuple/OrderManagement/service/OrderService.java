package com.nextuple.OrderManagement.service;


import com.nextuple.OrderManagement.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(String id);

    List<Order> createOrderPurchase(List<Order> orders);

    List<Order> createOrderSell(List<Order> orders);
}
