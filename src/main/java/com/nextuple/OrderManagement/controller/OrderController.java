package com.nextuple.OrderManagement.controller;

import com.nextuple.OrderManagement.dto.OrderInputDto;
import com.nextuple.OrderManagement.dto.OrderOutputDto;
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
    public ResponseEntity<List<OrderOutputDto>> getAllOrders()
    {
        List<OrderOutputDto> orders=orderService.getAllOrders().stream().map(
                order -> modelMapper.map(order, OrderOutputDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderOutputDto> getOrderById(@PathVariable String id)
    {
        Order existingOrder= orderService.getOrderById(id);

        OrderOutputDto orderOutputDto =modelMapper.map(existingOrder, OrderOutputDto.class);
        return new ResponseEntity<>(orderOutputDto,HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<OrderOutputDto>> createOrderForPurchase(@RequestBody List<OrderInputDto> orderInputDtoList)
    {
        // convert Dto to entity
        List<Order> orders= orderInputDtoList.stream().map(orderInputDto ->
                modelMapper.map(orderInputDto,Order.class)).collect(Collectors.toList());

        //convert entity to Dto and use OrderDao
        List<OrderOutputDto> addedOrders=orderService.createOrderPurchase(orders).stream().map(order->
                modelMapper.map(order, OrderOutputDto.class)).collect(Collectors.toList());

        return new ResponseEntity<>(addedOrders,HttpStatus.CREATED);
    }

    @PostMapping("/sale")
    public ResponseEntity<List<OrderOutputDto>> createOrderForSale(@RequestBody List<OrderInputDto> orderInputDtoList)
    {
        // convert Dto to entity
        List<Order> orders= orderInputDtoList.stream().map(orderInputDto ->
                modelMapper.map(orderInputDto,Order.class)).collect(Collectors.toList());

        //convert entity to Dto and use OrderDao
        List<OrderOutputDto> addedOrders=orderService.createOrderSell(orders).stream().map(order->
                modelMapper.map(order, OrderOutputDto.class)).collect(Collectors.toList());

        return new ResponseEntity<>(addedOrders,HttpStatus.CREATED);
    }


}
