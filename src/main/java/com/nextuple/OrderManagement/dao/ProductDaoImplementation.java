package com.nextuple.OrderManagement.dao;

import com.nextuple.OrderManagement.entity.Product;
import com.nextuple.OrderManagement.exception.InvalidPriceException;
import com.nextuple.OrderManagement.exception.ProductNotFoundException;
import com.nextuple.OrderManagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoImplementation implements ProductDao {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllItems() {
        return productRepository.findAll();
    }


    @Override
    public Product getItemByName(String name) {

            return productRepository.findByName(name);
    }

    @Override
    public List<Product> addItem(List<Product> items) {
        //illegal argument Exception Check to be added
        List<Product> newItems=new ArrayList<>();
        for(Product item:items)
        {   newItems.add(item);
            productRepository.save(item);
        }
        return newItems;
    }

    @Override
    public String deleteItem(String name) {
        Product existingProduct= productRepository.findByName(name);
        productRepository.deleteById(existingProduct.getId());
        return "Item deleted";

    }

    @Override
    public List<Product> updateItem(List<Product> products) {

        List<Product> updatedProducts=new ArrayList<>();

        for(Product product:products )
        {
            Product existingProduct=productRepository.findByName(product.getName());
            if(existingProduct==null)
                throw new ProductNotFoundException("Product not found Exception: "+product.getName());
            if(product.getPrice()>0)
            {
                existingProduct.setPrice(product.getPrice());
                existingProduct.setName(product.getName());
                existingProduct.setCategory(product.getCategory());
                // use DTO classes to prevent updating quantity by any method except Order functions
                existingProduct.setQuantity(product.getQuantity());
                updatedProducts.add(existingProduct);
                productRepository.save(existingProduct);
            }
            else
                throw new InvalidPriceException("Price is equal to or less than 0 " );
        }
        return updatedProducts;

    }
}
