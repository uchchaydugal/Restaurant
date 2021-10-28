package com.restaurant.order_manager.controllers;

import java.util.ArrayList;
import java.util.List;
import com.restaurant.order_manager.models.Order;
import com.restaurant.order_manager.models.State;
import com.restaurant.order_manager.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	@Autowired
	private final IOrderRepository orderRepository;

	OrderController(IOrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@GetMapping("/orders/id/{id}")
	Order order(@PathVariable Long id) throws NotFoundException {
		return orderRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}

	@GetMapping("/orders/status/{state}")
	List<Order> orders(@PathVariable String state) throws NotFoundException {
		List<Order> list = new ArrayList<>();
		State st;
		if (state.toLowerCase().compareTo("open") == 0) {
			st = State.open;
		}
		else if (state.toLowerCase().compareTo("inprogress") == 0) {
			st = State.inProgress;
		}
		else if (state.toLowerCase().compareTo("completed") == 0) {
			st = State.completed;
		}
		else {
			throw new NotFoundException();
		}
		for (Order order : orderRepository.findAll()) {
			if (order.state == st) {
				list.add(order);
			}
		}
		return list;
	}
	
	@GetMapping("/orders")
	List<Order> orders() throws NotFoundException {
		return orderRepository.findAll();
	}

	@PostMapping("/new_order")
	Order addOrder(@RequestBody Order order) {
		return orderRepository.save(order);
	}

	@PutMapping("/edit_order/{id}")
	Order changeOrder(@RequestBody Order newOrder, @PathVariable Long id) {
		return orderRepository.findById(id).map(order -> {
			order.setOrderDetails(newOrder.getOrderDetails());
			order.setTotalPrice(newOrder.getTotalPrice());
			order.setTimestamp(newOrder.getTimestamp());
			return orderRepository.save(order);
		}).
		orElseGet(() -> {
			newOrder.setId(id);
			return orderRepository.save(newOrder);
		});
	}

	@DeleteMapping("/delete_order/{id}")
	void deleteOrder(@PathVariable Long id) throws NotFoundException {
		orderRepository.deleteById(id);
	}
	
}