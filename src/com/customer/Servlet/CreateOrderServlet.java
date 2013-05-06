package com.customer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.my.Dao.CustomerDAO;
import com.my.Dao.FoodDAO;
import com.my.Dao.OrderDAO;
import com.my.Dao.OrderdetailDAO;
import com.my.Dao.RestaurantDAO;
import com.my.Entity.Customer;
import com.my.Entity.Order;
import com.my.Entity.Orderdetail;
import com.my.Entity.Restaurant;

public class CreateOrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9066935450943007218L;

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		String requestJson = URLDecoder.decode(request.getParameter("param"),
				"utf-8");

		JSONObject json = new JSONObject(requestJson);
		JSONArray orderjsonarray = json.getJSONArray("orderjsonarray");

		String remark = "";
		String cusaddress = "";
		String numofpeo = "";
		boolean remarkFlag = false;
		boolean cusaddressFlag = false;
		boolean numofpeoFlag = false;
		double total = json.getDouble("total");
		int customerid = json.getInt("customerid");
		int restid = json.getInt("restid");
		boolean delivery = json.getBoolean("delivery");
		String maketime = json.getString("maketime");
		String eattime = json.getString("eattime");
		System.out.println("#########"+maketime+"#########"+eattime);
		if (json.has("remark")) {
			remark = json.getString("remark");
			remarkFlag = true;
		}
		if(json.has("cusaddress")){
			cusaddress = json.getString("cusaddress");
			cusaddressFlag = true;
		}
		if(json.has("numofpeo")){
			numofpeo = json.getString("numofpeo");
			numofpeoFlag = true;
		}
		try{
			
		
		System.out.println(customerid + " create the order of " + restid);
		OrderDAO orderDAO = new OrderDAO();
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.findById(customerid);
		RestaurantDAO restaurantDAO = new RestaurantDAO();
		Restaurant restaurant = restaurantDAO.findById(restid);
		Order order = new Order();
		order.setTotal(total);
		order.setCustomer(customer);
		order.setRestaurant(restaurant);
		order.setDelivery(delivery);
		order.setCompleteflag(false);
		Timestamp make = Timestamp.valueOf(maketime);
		order.setMaketime(make);
		Timestamp eat = Timestamp.valueOf(eattime);
		order.setEattime(eat);
		if (remarkFlag) {
			order.setRemarks(remark);
		}
		if(cusaddressFlag){
			order.setAddress(cusaddress);
		}
		if(numofpeoFlag){
			order.setNumofpeople(numofpeo);
		}
		orderDAO.save(order); //创建订单

		OrderdetailDAO orderdetailDAO = new OrderdetailDAO();
		FoodDAO foodDAO = new FoodDAO();
		for (int i = 0; i < orderjsonarray.length(); i++) {
			Orderdetail orderdetail = new Orderdetail();
			JSONObject dish = (JSONObject) orderjsonarray.get(i);
			int foodid = dish.getInt("foodid");
			double subtotal = dish.getDouble("subtotal");
			int quantity = dish.getInt("quantity");
			orderdetail.setFood(foodDAO.findById(foodid));
			orderdetail.setQuantity(quantity);
			orderdetail.setSubtotal(subtotal);
			orderdetail.setOrder(order);
			orderdetailDAO.save(orderdetail);//保存订单详细

		}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("0");

			out.flush();
			out.close();
			return;
		}
		out.print("1");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
