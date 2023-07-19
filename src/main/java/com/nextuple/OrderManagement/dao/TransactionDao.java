package com.nextuple.OrderManagement.dao;

import com.nextuple.OrderManagement.entity.Transaction;

import java.util.List;

public interface TransactionDao {

    List<Transaction> getAllTransactions();

    Transaction getTransactionById(String id);

    void createTransactions(Transaction transaction);
}
