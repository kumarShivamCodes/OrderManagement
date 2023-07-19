package com.nextuple.OrderManagement.dao;

import com.nextuple.OrderManagement.entity.Transaction;
import com.nextuple.OrderManagement.exception.OrderNotFoundException;
import com.nextuple.OrderManagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionDaoImplementation implements TransactionDao{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id).orElseThrow(
                ()-> new OrderNotFoundException("Transaction does not exist with id :"+id));
    }

    @Override
    public void createTransactions(Transaction transaction) {

            transactionRepository.save(transaction);

    }
}
