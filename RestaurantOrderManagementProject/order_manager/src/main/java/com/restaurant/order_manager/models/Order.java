package com.restaurant.order_manager.models;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "State")
	public State state;

	@Column(name = "order_details")
	private String orderDetails;

	@Column(name = "Time")
	private LocalTime timestamp;

	@Column(name = "Price")
	private double totalPrice;

	public long getId() {
		return id;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalTime localTime) {
		this.timestamp = localTime;
	}

	public void setId(long id) {
		this.id = id;
	}
}
