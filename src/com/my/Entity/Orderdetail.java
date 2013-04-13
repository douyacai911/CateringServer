package com.my.Entity;

/**
 * Orderdetail entity. @author MyEclipse Persistence Tools
 */

public class Orderdetail implements java.io.Serializable {

	// Fields

	private Integer orderdetailid;
	private Food food;
	private Order order;
	private Integer quantity;
	private Double subtotal;

	// Constructors

	/** default constructor */
	public Orderdetail() {
	}

	/** full constructor */
	public Orderdetail(Integer orderdetailid, Food food, Order order,
			Integer quantity, Double subtotal) {
		this.orderdetailid = orderdetailid;
		this.food = food;
		this.order = order;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	// Property accessors

	public Integer getOrderdetailid() {
		return this.orderdetailid;
	}

	public void setOrderdetailid(Integer orderdetailid) {
		this.orderdetailid = orderdetailid;
	}

	public Food getFood() {
		return this.food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

}