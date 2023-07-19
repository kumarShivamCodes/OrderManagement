package com.nextuple.OrderManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "transaction")
public class Transaction {
    @Id
    private String transactionId;
    private List<OrderDetails> orderDetailsList;
    private Double totalOrderAmount;


}
