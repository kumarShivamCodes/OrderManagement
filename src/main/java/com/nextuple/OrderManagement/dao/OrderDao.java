package com.nextuple.OrderManagement.dao;

import com.nextuple.OrderManagement.entity.Order;

import java.util.List;

public interface OrderDao {

    List<Order> getAllOrders();

    Order getOrderById(String id);

    List<Order> createOrder(List<Order> orders);
}
