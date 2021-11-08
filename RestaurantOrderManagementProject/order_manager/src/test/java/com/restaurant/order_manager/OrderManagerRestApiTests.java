package com.restaurant.order_manager;

import com.restaurant.order_manager.repositories.IOrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderManagerRestApiTests {
	
	@Autowired
	private IOrderRepository orderRepository;

	private MockMvc mvc;

	@Test
	public void getAllOrdersFromDatabase() throws Exception {

		final var orders = orderRepository.findAll();
		when(orderRepository.findAll()).thenReturn(orders);

		mvc.perform(get("/orders")).andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(4)));
	}

}
