package com.restaurant.Servlet;

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

import com.my.Dao.OrderDAO;
import com.my.Entity.Order;
import com.my.Entity.Orderdetail;

public class OrderDetailServlet extends HttpServlet {

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

		int orderid = Integer.parseInt(request.getParameter("orderid"));
		System.out.println(orderid+" need to be searched");
		
		OrderDAO orderDAO = new OrderDAO();
		Order order = orderDAO.findById(orderid);
		Criteria cr = HibernateSessionFactory.getSession().createCriteria(Orderdetail.class);
		cr.add(Restrictions.eq("order", order));//TODO .like改成.eq，有时间测试一下
		ArrayList<Orderdetail>orderdetails =(ArrayList<Orderdetail>)cr.list();
		
		if(orderdetails!=null && orderdetails.size()!=0){
			for(int i = 0;i < orderdetails.size(); i ++){
				
				JSONObject jsonObj  = new JSONObject();
				
//				jsonObj.put("index", i);
				jsonObj.put("dishname", orderdetails.get(i).getFood().getName());
				jsonObj.put("quantity", orderdetails.get(i).getQuantity().toString());
				jsonObj.put("subtotal", orderdetails.get(i).getSubtotal().toString());
				
				jsonArray.put(jsonObj);
				
				}
		JSONObject json = new JSONObject();
		boolean delivery = order.getDelivery();
		if(delivery){
			json.put("address",order.getAddress());
		}else{
			json.put("numofpeo", order.getNumofpeople());
		}
		json.put("orderdetailarray", jsonArray);
		json.put("delivery", delivery);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //去掉.0 格式化
		
		json.put("eattime", df.format(order.getEattime()).toString());
		json.put("maketime", df.format(order.getMaketime()).toString());
		json.put("total", order.getTotal().toString());
		if((order.getRemarks()!=null) && (!order.getRemarks().equals(""))){
			json.put("remark", order.getRemarks());
		}

		json.put("customername", order.getCustomer().getUsername());
		json.put("customertel", order.getCustomer().getTel());
		out.print(json.toString());
		out.flush();
		out.close();
	} else { //此店没有订单
		out.print("-1");
		out.flush();
		out.close();
	}
	}

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
