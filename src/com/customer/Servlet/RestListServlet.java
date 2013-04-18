package com.customer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

import util.Distance;
import util.DistanceComparator;
import util.HibernateSessionFactory;

import com.my.Dao.FoodDAO;
import com.my.Dao.RestaurantDAO;
import com.my.Entity.Food;
import com.my.Entity.Restaurant;

public class RestListServlet extends HttpServlet {
	double cuslat = 0;
	double cuslon = 0;
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
		
		int customerid = Integer.parseInt(request.getParameter("customerid"));
		cuslon = Double.parseDouble(request.getParameter("lon"));
		cuslat = Double.parseDouble(request.getParameter("lat"));
		System.out.println(customerid+" want to search his menu");
		
		JSONArray jsonArray = new JSONArray();
		
		RestaurantDAO RestaurantDAO = new RestaurantDAO();
		
		
		ArrayList<Restaurant>restaurants = (ArrayList<Restaurant>) RestaurantDAO.findAll();
		ArrayList<HashMap<String, Object>> wanttosort = new ArrayList<HashMap<String, Object>>();
		if(restaurants==null){
			out.print("-1");
			out.flush();
			out.close();
			return;
			}
			for(Restaurant restaurant:restaurants){
				String restLocation = restaurant.getLocation();
				if(restLocation==null){
					restLocation = "0,0";
				}
				String[] restCoord = restLocation.split(",");
				double restlon = Double.parseDouble(restCoord[0]);
				double restlat = Double.parseDouble(restCoord[1]);
				double distance = Distance.GetDistance(cuslon, cuslat, restlon, restlat);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("distance", distance);
				map.put("restname", restaurant.getRestname());
				map.put("restid", restaurant.getRestid());
				wanttosort.add(map);
				
				
			}
			//∞¥æ‡¿Î≈≈–Ú
	        Collections.sort(wanttosort, new Comparator<HashMap<String,Object>>(){ 
                @Override 
                public int compare(HashMap<String, Object> r0,HashMap<String, Object> r1) { 
                    return ((Double) r0.get("distance")).compareTo((Double) r1.get("distance")); 
                } 
            });
	        
	        for(int i = 0;i < wanttosort.size(); i ++){
	        	
	        	JSONObject jsonObj  = new JSONObject();
	        	
	        	jsonObj.put("distance",wanttosort.get(i).get("distance"));
				jsonObj.put("restname",wanttosort.get(i).get("restname"));
				jsonObj.put("restid",wanttosort.get(i).get("restid"));
				
				
				jsonArray.put(jsonObj);
	        	
	        }
	        JSONObject json = new JSONObject();
			json.put("restlist", jsonArray);
			System.out.println(json.toString());
			out.print(json.toString());
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
