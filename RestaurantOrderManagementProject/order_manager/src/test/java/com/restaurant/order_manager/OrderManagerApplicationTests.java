package com.restaurant.order_manager;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import com.restaurant.order_manager.models.Order;
import com.restaurant.order_manager.models.State;
import com.restaurant.order_manager.repositories.IOrderRepository;
import org.hamcrest.MatcherAssert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderManagerApplicationTests {

	@Autowired
	private IOrderRepository orderRepository;

	@Test
	public void addOrder() {
		Order order = new Order();
		order.setOrderDetails("some order");
		order.setTimestamp(LocalTime.now());
		order.setTotalPrice(1200d);
		order.state = State.open;

		orderRepository.save(order);

		assertNotNull(orderRepository.findById(order.getId()).get());
	}

	@Test
	public void deleteOrder() {
		Order orderToDelete = orderRepository.findById(11l).get();
		orderRepository.deleteById(orderToDelete.getId());
		assertNotEquals(true, orderRepository.findById(orderToDelete.getId()).isPresent());
	}

	@Test
	public void editOrder() {
		Order orderToEdit = orderRepository.findById(1l).get();
		State state = orderToEdit.state;
		orderToEdit.state = State.completed;
		orderRepository.save(orderToEdit);

		assertNotEquals(state, orderRepository.findById(orderToEdit.getId()).get().state);
	}

}
