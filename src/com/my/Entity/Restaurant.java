package com.my.Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Restaurant entity. @author MyEclipse Persistence Tools
 */

public class Restaurant implements java.io.Serializable {

	// Fields

	private Integer restid;
	private String username;
	private String password;
	private String restname;
	private String address;
	private String location;
	private String email;
	private String tel;
	private Boolean delivery;
	private Integer version;
	private Set orders = new HashSet(0);
	private Set comments = new HashSet(0);
	private Set foods = new HashSet(0);

	// Constructors

	/** default constructor */
	public Restaurant() {
	}

	/** minimal constructor */
	public Restaurant(Integer restid, String username, String password) {
		this.restid = restid;
		this.username = username;
		this.password = password;
	}

	/** full constructor */
	public Restaurant(Integer restid, String username, String password,
			String restname, String address, String location, String email,
			String tel, Boolean delivery, Integer version, Set orders,
			Set comments, Set foods) {
		this.restid = restid;
		this.username = username;
		this.password = password;
		this.restname = restname;
		this.address = address;
		this.location = location;
		this.email = email;
		this.tel = tel;
		this.delivery = delivery;
		this.version = version;
		this.orders = orders;
		this.comments = comments;
		this.foods = foods;
	}

	// Property accessors

	public Integer getRestid() {
		return this.restid;
	}

	public void setRestid(Integer restid) {
		this.restid = restid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRestname() {
		return this.restname;
	}

	public void setRestname(String restname) {
		this.restname = restname;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Boolean getDelivery() {
		return this.delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getFoods() {
		return this.foods;
	}

	public void setFoods(Set foods) {
		this.foods = foods;
	}

}