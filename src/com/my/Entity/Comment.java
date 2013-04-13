package com.my.Entity;

import java.sql.Timestamp;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer commentid;
	private Food food;
	private Customer customer;
	private Restaurant restaurant;
	private Timestamp time;
	private String detail;
	private Double stars;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(Integer commentid, Food food, Customer customer,
			Timestamp time) {
		this.commentid = commentid;
		this.food = food;
		this.customer = customer;
		this.time = time;
	}

	/** full constructor */
	public Comment(Integer commentid, Food food, Customer customer,
			Restaurant restaurant, Timestamp time, String detail, Double stars) {
		this.commentid = commentid;
		this.food = food;
		this.customer = customer;
		this.restaurant = restaurant;
		this.time = time;
		this.detail = detail;
		this.stars = stars;
	}

	// Property accessors

	public Integer getCommentid() {
		return this.commentid;
	}

	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}

	public Food getFood() {
		return this.food;
	}

	public void setFood(Food food) {
		this.food = food;
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

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Double getStars() {
		return this.stars;
	}

	public void setStars(Double stars) {
		this.stars = stars;
	}

}