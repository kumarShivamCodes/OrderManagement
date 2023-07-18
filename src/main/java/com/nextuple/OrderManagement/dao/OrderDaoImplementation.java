package com.nextuple.OrderManagement.dao;

import com.nextuple.OrderManagement.entity.Order;
import com.nextuple.OrderManagement.exception.OrderNotFoundException;
import com.nextuple.OrderManagement.exception.ProductNotFoundException;
import com.nextuple.OrderManagement.repository.OrdersRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDaoImplementation implements OrderDao{

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Order getOrderById(String id) {
        return ordersRepository.findById(id).orElseThrow(
                ()-> new OrderNotFoundException("Order Not found :" + id));
    }

    @Override
    public List<Order> createOrder(List<Order> orders) {
        List<Order> newOrders=new ArrayList<>();
        for(Order order: orders)
        {
            newOrders.add(order);
            ordersRepository.save(order);
        }
        return newOrders;
    }
}
