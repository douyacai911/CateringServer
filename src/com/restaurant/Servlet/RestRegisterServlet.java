package com.restaurant.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.my.Dao.RestaurantDAO;
import com.my.Entity.Restaurant;

public class RestRegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1011517945230548814L;


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
		
		String json = URLDecoder.decode(request.getParameter("param"),"utf-8");
        
        JSONObject object =new JSONObject(json);
        boolean isdelivery = false;

		int restid = object.getInt("restid");
		String restname = object.getString("restname");
		String tel = object.getString("tel");
		String address = object.getString("address");
		String location = object.getString("location");
		int delivery = object.getInt("delivery");
		System.out.println(restid+" want to register restaurant"+restname+tel+address+location+delivery);
		if(delivery>0){
			isdelivery = true;
		}
		RestaurantDAO RestaurantDAO = new RestaurantDAO();
		ArrayList<Restaurant>restaurants = (ArrayList<Restaurant>) RestaurantDAO.findByUsername(restname);
		if(restaurants.size()==0 ){
			Restaurant restaurant = RestaurantDAO.findById(restid);
			restaurant.setRestname(restname);
			restaurant.setTel(tel);
			restaurant.setAddress(address);
			restaurant.setLocation(location);
			restaurant.setDelivery(isdelivery);
			restaurant.setVersion(restaurant.getVersion()+1);
			RestaurantDAO.save(restaurant);
			String theRestid = String.valueOf(restid);
			out.print(theRestid); //餐厅注册成功
		}
		else{
			out.print("-1"); //此用户名已注册过
		}
		
			

		out.flush();
		out.close();
		
		
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
