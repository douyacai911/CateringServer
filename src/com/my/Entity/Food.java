package com.my.Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Food entity. @author MyEclipse Persistence Tools
 */

public class Food implements java.io.Serializable {

	// Fields

	private Integer foodid;
	private Restaurant restaurant;
	private String name;
	private String description;
	private Double price;
	private Double stars;
	private Integer categoryid;
	private String pictureurl;
	private Set orderdetails = new HashSet(0);
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Food() {
	}

	/** minimal constructor */
	public Food(Integer foodid, Restaurant restaurant, String name, Double price) {
		this.foodid = foodid;
		this.restaurant = restaurant;
		this.name = name;
		this.price = price;
	}

	/** full constructor */
	public Food(Integer foodid, Restaurant restaurant, String name,
			String description, Double price, Double stars, Integer categoryid,
			String pictureurl, Set orderdetails, Set comments) {
		this.foodid = foodid;
		this.restaurant = restaurant;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stars = stars;
		this.categoryid = categoryid;
		this.pictureurl = pictureurl;
		this.orderdetails = orderdetails;
		this.comments = comments;
	}

	// Property accessors

	public Integer getFoodid() {
		return this.foodid;
	}

	public void setFoodid(Integer foodid) {
		this.foodid = foodid;
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getStars() {
		return this.stars;
	}

	public void setStars(Double stars) {
		this.stars = stars;
	}

	public Integer getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public String getPictureurl() {
		return this.pictureurl;
	}

	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	public Set getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(Set orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

}