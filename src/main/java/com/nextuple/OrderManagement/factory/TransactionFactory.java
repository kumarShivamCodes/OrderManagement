package com.nextuple.OrderManagement.factory;

import com.nextuple.OrderManagement.entity.OrderDetails;
import com.nextuple.OrderManagement.entity.Transaction;

import java.util.List;

public interface TransactionFactory {
    Transaction createTransaction(String transactionId, List<OrderDetails> orderDetailsList, Double totalOrderAmount);

}
