package com.nextuple.OrderManagement.factory;

import com.nextuple.OrderManagement.entity.OrderDetails;
import com.nextuple.OrderManagement.entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DefaultTransactionFactory implements TransactionFactory{
    @Override
    public Transaction createTransaction(String transactionId, List<OrderDetails> orderDetailsList) {
        return new Transaction(transactionId, orderDetailsList);
    }
}
