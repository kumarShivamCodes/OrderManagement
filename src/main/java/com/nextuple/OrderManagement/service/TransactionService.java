package com.nextuple.OrderManagement.service;

import com.nextuple.OrderManagement.entity.Order;
import com.nextuple.OrderManagement.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    Transaction getTransactionById(String id);

    void createTransaction(List<Order> orders);
}
