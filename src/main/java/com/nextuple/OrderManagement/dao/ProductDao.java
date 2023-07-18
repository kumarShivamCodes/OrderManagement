package com.nextuple.OrderManagement.dao;

import com.nextuple.OrderManagement.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> getAllItems();

    Product getItemByName(String name);

    List<Product> addItem(List<Product> items); //purchase order

    String deleteItem(String name);

    List<Product> updateItem(List<Product> product);

}
