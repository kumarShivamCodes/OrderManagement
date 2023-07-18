package com.nextuple.OrderManagement.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@Data
@Document(collection = "product")
public class Product {
    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private Double price;
    private String category;
    private Integer quantity = 0;


}
