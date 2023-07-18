package com.nextuple.OrderManagement.entity;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id = UUID.randomUUID().toString();
    private String productName;
    private Integer quantity;
    private String type; //purchase or sell

}
