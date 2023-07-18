package com.nextuple.OrderManagement.controller;

import com.nextuple.OrderManagement.dao.OrderDao;
import com.nextuple.OrderManagement.dto.OrderDto;
import com.nextuple.OrderManagement.entity.Order;
import com.nextuple.OrderManagement.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders()
    {
        List<OrderDto> orders=orderService.getAllOrders().stream().map(
                order -> modelMapper.map(order,OrderDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String id)
    {
        Order existingOrder= orderService.getOrderById(id);

        OrderDto orderDto=modelMapper.map(existingOrder,OrderDto.class);
        return new ResponseEntity<>(orderDto,HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<OrderDto>> createOrderForPurchase(@RequestBody List<OrderDto> orderDtoList)
    {
        // convert Dto to entity
        List<Order> orders=orderDtoList.stream().map( orderDto ->
                modelMapper.map(orderDto,Order.class)).collect(Collectors.toList());

        //convert entity to Dto and use OrderDao
        List<OrderDto> addedOrders=orderService.createOrder(orders).stream().map( order->
                modelMapper.map(order,OrderDto.class)).collect(Collectors.toList());

        return new ResponseEntity<>(addedOrders,HttpStatus.CREATED);
    }
}
