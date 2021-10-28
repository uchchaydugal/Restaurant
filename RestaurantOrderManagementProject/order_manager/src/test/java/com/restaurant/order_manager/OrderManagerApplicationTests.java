package com.restaurant.order_manager;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import com.restaurant.order_manager.models.Order;
import com.restaurant.order_manager.models.State;
import com.restaurant.order_manager.repositories.IOrderRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
class OrderManagerApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IOrderRepository orderRepository;

	@Test
	public void testAddOrderFunction() {
		Order order = new Order();
		order.setOrderDetails("dwafes, fghjk, oijn");
		order.setTimestamp(LocalTime.now());
		order.setTotalPrice(680);
		order.state = State.completed;

		Order savedOrder = orderRepository.save(order);

		Order existingOrder = entityManager.find(Order.class, savedOrder.getId());

		assertThat(order.getOrderDetails()).isEqualTo(existingOrder.getOrderDetails());

	}

}
