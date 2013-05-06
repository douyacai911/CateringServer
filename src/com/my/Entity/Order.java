package com.my.Entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer orderid;
	private Customer customer;
	private Restaurant restaurant;
	private Double total;
	private Boolean delivery;
	private String address;
	private Boolean completeflag;
	private Timestamp maketime;
	private Timestamp eattime;
	private String numofpeople;
	private String remarks;
	private Set orderdetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(Customer customer, Restaurant restaurant, Boolean delivery,
			Boolean completeflag, Timestamp maketime) {
		this.customer = customer;
		this.restaurant = restaurant;
		this.delivery = delivery;
		this.completeflag = completeflag;
		this.maketime = maketime;
	}

	/** full constructor */
	public Order(Customer customer, Restaurant restaurant, Double total,
			Boolean delivery, String address, Boolean completeflag,
			Timestamp maketime, Timestamp eattime, String numofpeople,
			String remarks, Set orderdetails) {
		this.customer = customer;
		this.restaurant = restaurant;
		this.total = total;
		this.delivery = delivery;
		this.address = address;
		this.completeflag = completeflag;
		this.maketime = maketime;
		this.eattime = eattime;
		this.numofpeople = numofpeople;
		this.remarks = remarks;
		this.orderdetails = orderdetails;
	}

	// Property accessors

	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Boolean getDelivery() {
		return this.delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getCompleteflag() {
		return this.completeflag;
	}

	public void setCompleteflag(Boolean completeflag) {
		this.completeflag = completeflag;
	}

	public Timestamp getMaketime() {
		return this.maketime;
	}

	public void setMaketime(Timestamp maketime) {
		this.maketime = maketime;
	}

	public Timestamp getEattime() {
		return this.eattime;
	}

	public void setEattime(Timestamp eattime) {
		this.eattime = eattime;
	}

	public String getNumofpeople() {
		return this.numofpeople;
	}

	public void setNumofpeople(String numofpeople) {
		this.numofpeople = numofpeople;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(Set orderdetails) {
		this.orderdetails = orderdetails;
	}

}