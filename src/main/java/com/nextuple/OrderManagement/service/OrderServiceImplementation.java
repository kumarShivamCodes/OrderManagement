package com.nextuple.OrderManagement.service;

import com.nextuple.OrderManagement.dao.OrderDao;
import com.nextuple.OrderManagement.dao.ProductDao;
import com.nextuple.OrderManagement.entity.Order;
import com.nextuple.OrderManagement.entity.Product;
import com.nextuple.OrderManagement.enumerations.OrderType;
import com.nextuple.OrderManagement.exception.InvalidQuantityException;
import com.nextuple.OrderManagement.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public Order getOrderById(String id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Order> createOrderPurchase(List<Order> orders) {
        List<Order> newOrders= new ArrayList<>();
        List<Product> productsWithQtyUpdated=new ArrayList<>();

        for(Order order:orders)
        {
            String prodName=order.getProductName();
            Product product=productDao.getItemByName(prodName);
            if(product==null)
                throw new ProductNotFoundException("Product does not exist in inventory: "+ order.getProductName());

            if(order.getQuantity()<0)
                throw  new InvalidQuantityException("Product Quantity can't be negative: "+ order.getQuantity());

            //update product quantity
            product.setQuantity(product.getQuantity() + order.getQuantity());

            //update Order Type to Sale
            order.setType(OrderType.PURCHASE_ORDER);

            // update order's product price
            order.setProductPrice(product.getPrice());

            //update order price
            order.setOrderPrice(order.getQuantity()*product.getPrice());

            productsWithQtyUpdated.add(product);
            newOrders.add(order);
        }
        productDao.addItem(productsWithQtyUpdated);
        return orderDao.createOrder(newOrders);
    }

    @Override
    public List<Order> createOrderSell(List<Order> orders) {

        List<Order> newOrders= new ArrayList<>();
        List<Product> productsWithQtyUpdated=new ArrayList<>();

        for(Order order:orders)
        {
            String prodName=order.getProductName();
            Product product=productDao.getItemByName(prodName);
            if(product==null)
                throw new ProductNotFoundException("Product does not exist in inventory: "+ order.getProductName());

            if(order.getQuantity()<0)
                throw  new InvalidQuantityException("Product Quantity can't be negative: "+ order.getQuantity());


                int updatedQuantity = product.getQuantity() - order.getQuantity();
                if (updatedQuantity < 0) {
                    throw new InvalidQuantityException("Insufficient quantity for product: " + prodName + " Available Qty: " +product.getQuantity());
                }
                product.setQuantity(updatedQuantity);

                //update Order Type to Sale
                order.setType(OrderType.SALE_ORDER);

                // update order's product price
                order.setProductPrice(product.getPrice());

                //update order price
                order.setOrderPrice(order.getQuantity()*product.getPrice());

            productsWithQtyUpdated.add(product);
            newOrders.add(order);
        }
        productDao.addItem(productsWithQtyUpdated);
        return orderDao.createOrder(newOrders);


    }
}
