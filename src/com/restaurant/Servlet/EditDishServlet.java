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

import com.my.Dao.FoodDAO;
import com.my.Entity.Food;
import com.my.Entity.Restaurant;

public class EditDishServlet extends HttpServlet {

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


		int foodid = object.getInt("foodid");
		String dishname = object.getString("dishname");
		double price = object.getDouble("price");
		String description = object.getString("description");
		int categoryid = object.getInt("categoryid");
		System.out.println(foodid+" need to be edited"+dishname+price+description+categoryid);
		FoodDAO FoodDAO = new FoodDAO();
		Food food = new Food();
		food = FoodDAO.findById(foodid);
		Restaurant restaurant = new Restaurant();
		restaurant = food.getRestaurant();
		ArrayList<Food>foods = (ArrayList<Food>) FoodDAO.findByRestaurant(restaurant);
		for(Food thefood:foods){
			if(thefood.getName().equals(dishname) && (thefood.getFoodid()!=foodid)){
				out.print("-1");
				out.flush();
				out.close();
				return;
			}
		}
		
		
		food.setName(dishname);
		food.setPrice(price);
		food.setDescription(description);
		food.setCategoryid(categoryid);
		FoodDAO.save(food);
		out.print(food.getFoodid());
		
		
		
		
		
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
