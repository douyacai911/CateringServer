package com.restaurant.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.my.Dao.FoodDAO;
import com.my.Dao.RestaurantDAO;
import com.my.Entity.Food;
import com.my.Entity.Restaurant;

public class AddDishServlet extends HttpServlet {

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


		int restid = object.getInt("restid");
		String dishname = object.getString("dishname");
		double price = object.getDouble("price");
		String description = object.getString("description");
		int categoryid = object.getInt("categoryid");
		System.out.println(restid+" want to add dish"+dishname+price+description+categoryid);
		RestaurantDAO RestaurantDAO = new RestaurantDAO();
		FoodDAO FoodDAO = new FoodDAO();
		Restaurant restaurant = RestaurantDAO.findById(restid);
		Food food = new Food();
		food.setName(dishname);
		food.setPrice(price);
		food.setDescription(description);
		food.setCategoryid(categoryid);
		food.setRestaurant(restaurant);
		restaurant.setVersion(restaurant.getVersion()+1);
		RestaurantDAO.save(restaurant);
		FoodDAO.save(food);
		
		
		String theRestid = String.valueOf(restid);
		out.print(theRestid); //菜品添加完成
		
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
