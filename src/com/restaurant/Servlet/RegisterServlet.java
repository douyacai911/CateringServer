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

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6359547327756778592L;

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


		String username = object.getString("username");
		String password = object.getString("password");
		String email = object.getString("email");
		
		System.out.println(username+" want to register");
		RestaurantDAO RestaurantDAO = new RestaurantDAO();
		Restaurant restaurant = new Restaurant();
		restaurant.setUsername(username);
		restaurant.setPassword(password);
		restaurant.setEmail(email);
		restaurant.setVersion(0); //初始化版本号
		ArrayList<Restaurant>restaurants = (ArrayList<Restaurant>) RestaurantDAO.findByUsername(username);
		if(restaurants.size()==0){
			RestaurantDAO.save(restaurant);
			String restid = String.valueOf(restaurant.getRestid());
			out.print(restid); //注册成功
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