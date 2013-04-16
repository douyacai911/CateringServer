package com.restaurant.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.my.Dao.FoodDAO;
import com.my.Dao.RestaurantDAO;
import com.my.Entity.Food;
import com.my.Entity.Restaurant;

public class MyMenuServlet extends HttpServlet {

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

		int restid = Integer.parseInt(request.getParameter("restid"));
		System.out.println(restid+" want to search his menu");
		
		FoodDAO FoodDAO = new FoodDAO();
		RestaurantDAO RestaurantDAO = new RestaurantDAO();
		Restaurant restaurant = RestaurantDAO.findById(restid);
		ArrayList<Food>foods = (ArrayList<Food>) FoodDAO.findByRestaurant(restaurant);
		if(foods!=null && foods.size()!=0){
			for(int i = 0;i < foods.size(); i ++){
				
				JSONObject jsonObj  = new JSONObject();
				
				jsonObj.put("index", i);
				jsonObj.put("dishname", foods.get(i).getName());
				jsonObj.put("price", foods.get(i).getPrice());
				jsonObj.put("categoryid", foods.get(i).getCategoryid());
				
				jsonArray.put(jsonObj);
				
				}
		JSONObject json = new JSONObject();
		json.put("menu", jsonArray);
		out.print(json.toString());
		out.flush();
		out.close();
	} else { //此店没有发布过菜品
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
