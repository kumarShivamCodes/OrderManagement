package com.nextuple.OrderManagement.repository;

import com.nextuple.OrderManagement.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends MongoRepository<Order,String> {

}
