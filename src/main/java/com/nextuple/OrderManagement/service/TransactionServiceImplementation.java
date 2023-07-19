package com.nextuple.OrderManagement.service;

import com.nextuple.OrderManagement.dao.TransactionDao;
import com.nextuple.OrderManagement.entity.Order;
import com.nextuple.OrderManagement.entity.OrderDetails;
import com.nextuple.OrderManagement.entity.Transaction;
import com.nextuple.OrderManagement.factory.OrderDetailsFactory;
import com.nextuple.OrderManagement.factory.TransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImplementation implements TransactionService{

    @Autowired
    private TransactionFactory transactionFactory;
    @Autowired
    private OrderDetailsFactory orderDetailsFactory;
    @Autowired
    private TransactionDao transactionDao;
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    @Override
    public Transaction getTransactionById(String id) {
        return transactionDao.getTransactionById(id);
    }

    @Override
    public void createTransaction(List<Order> orders) {

        List<OrderDetails> orderDetailsList = new ArrayList<>();

        Double totalOrderAmount=0.0;

         for(Order order:orders)
         {
            String orderId=order.getId();
            Double orderAmount=order.getOrderPrice();
            totalOrderAmount+=orderAmount;
            orderDetailsList.add(orderDetailsFactory.createOrderDetails(orderId,orderAmount));
         }

         // Generate a unique transaction ID (you can use your own logic to generate a unique ID)
        String transactionId = generateUniqueTransactionId();

        // Create the Transaction using the TransactionFactory
        Transaction transaction = transactionFactory.createTransaction(transactionId, orderDetailsList,totalOrderAmount);

        // save transaction

        transactionDao.createTransactions(transaction);

    }

    private String generateUniqueTransactionId() {
        // Implement your logic to generate a unique transaction ID
        // For simplicity, you can use UUID.randomUUID().toString() or any other suitable method
        return UUID.randomUUID().toString();
    }
}
