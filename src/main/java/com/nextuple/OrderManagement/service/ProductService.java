package com.nextuple.OrderManagement.service;

import com.nextuple.OrderManagement.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllItems();

    Product getItemByName(String name);

    List<Product> addItem(List<Product> item); //purchase order

    String deleteItem(String name);

    List<Product> updateItem(List<Product> product); // patch request for qty, price,etc


}
