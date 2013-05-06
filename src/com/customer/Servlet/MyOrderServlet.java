package com.customer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

import util.HibernateSessionFactory;

import com.my.Dao.CustomerDAO;
import com.my.Entity.Customer;
import com.my.Entity.Order;

public class MyOrderServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();

        JSONArray jsonArray = new JSONArray();
        
		int customerid = Integer.parseInt(request.getParameter("customerid"));
		
		System.out.println(customerid+" want to search his orderlist");
		
		
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = customerDAO.findById(customerid);
		Criteria cr = HibernateSessionFactory.getSession().createCriteria(Order.class);
		cr.add(Restrictions.eq("customer", customer));//TODO .like改成.eq，有时间测试一下
		cr.addOrder(org.hibernate.criterion.Order.asc("maketime"));
		ArrayList<Order>orders =(ArrayList<Order>)cr.list();

		if(orders!=null && orders.size()!=0){
			for(int i = 0;i < orders.size(); i ++){
				
				JSONObject jsonObj  = new JSONObject();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //去掉.0 格式化
				
				jsonObj.put("orderid", orders.get(i).getOrderid());
				jsonObj.put("restname", orders.get(i).getRestaurant().getRestname());
				jsonObj.put("delivery", orders.get(i).getDelivery());
				jsonObj.put("eattime", df.format(orders.get(i).getEattime()).toString());
				jsonObj.put("maketime", df.format(orders.get(i).getMaketime()).toString());
				jsonObj.put("completeflag", orders.get(i).getCompleteflag());
				jsonObj.put("total", orders.get(i).getTotal());
				jsonArray.put(jsonObj);
				
				}
		JSONObject json = new JSONObject();
		json.put("order", jsonArray);
		out.print(json.toString());
		out.flush();
		out.close();
	} else { //此用户没有订单
		out.print("-1");
		out.flush();
		out.close();
	}}


	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
