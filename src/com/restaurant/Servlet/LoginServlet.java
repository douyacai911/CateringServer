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

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4045158512935813729L;
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
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8"); 

		String json = URLDecoder.decode(request.getParameter("param"),"utf-8");
        
        JSONObject object =new JSONObject(json);


		String username = object.getString("username");
		String password = object.getString("password");
		
		System.out.println(username+"is coming");
		RestaurantDAO RestaurantDAO = new RestaurantDAO();
		ArrayList<Restaurant>restaurants = (ArrayList<Restaurant>) RestaurantDAO.findByUsername(username);
		if(restaurants!=null && restaurants.size()!=0){
			for(Restaurant restaurant:restaurants){
				if(restaurant.getPassword().equals(password)){
					out.print(build(restaurant));
					out.flush();
					out.close();
				}
			}
				out.print("1"); //√‹¬Î¥ÌŒÛ
		}
		else{
			out.print("0"); //Œﬁ¥À’Àªß
		}
		out.flush();
		out.close();
	}
	private String build(Restaurant u){
		String userMsg = "";
		userMsg+="restid="+u.getRestid();
		userMsg+=";";
		userMsg+="name="+u.getUsername();
		return userMsg;
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
