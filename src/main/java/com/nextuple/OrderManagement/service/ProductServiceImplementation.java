package com.nextuple.OrderManagement.service;


import com.nextuple.OrderManagement.dao.ProductDao;
import com.nextuple.OrderManagement.entity.Product;
import com.nextuple.OrderManagement.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> getAllItems() {
        return productDao.getAllItems();
    }

    @Override
    public Product getItemByName(String name) {

        Product product= productDao.getItemByName(name);
        if (product==null)
            throw new ProductNotFoundException("Product does not exists in the inventory:" +name);
        return product;
    }

    @Override
    public List<Product> addItem(List<Product> items) {
        return productDao.addItem(items);
    }

    @Override
    public String deleteItem(String name) {
        Product product= productDao.getItemByName(name);
        if (product==null)
            throw new ProductNotFoundException("Product does not exists in the inventory:" +name);
        return productDao.deleteItem(name);
    }

    @Override
    public List<Product> updateItem( List<Product> products) {

        return productDao.updateItem(products);
    }
}
