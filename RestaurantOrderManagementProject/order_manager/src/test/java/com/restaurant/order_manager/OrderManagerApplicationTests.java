package com.restaurant.order_manager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;

import com.restaurant.order_manager.models.Order;
import com.restaurant.order_manager.models.State;
import com.restaurant.order_manager.repositories.IOrderRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderManagerApplicationTests {

	@Autowired
	private IOrderRepository orderRepository;

	@Test
	public void addOrder() {
		Order order = new Order();
		order.setOrderDetails("some order");
		order.setTimestamp(LocalTime.now());
		order.setTotalPrice(1200d);
		order.state = State.open;

		assertNotNull(orderRepository.findById(order.getId()).get());
	}

}
