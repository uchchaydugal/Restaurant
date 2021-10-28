package com.restaurant.order_manager.repositories;
import java.util.List;

import com.restaurant.order_manager.models.Order;
import com.restaurant.order_manager.models.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    
    //@Query("SELECT * FROM orders o WHERE o.state LIKE %?1%")
    List<Order> findByState(State state);
}
