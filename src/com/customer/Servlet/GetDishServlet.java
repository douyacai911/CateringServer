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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

import util.HibernateSessionFactory;

import com.my.Dao.CommentDAO;
import com.my.Dao.CustomerDAO;
import com.my.Dao.FoodDAO;
import com.my.Entity.Comment;
import com.my.Entity.Food;

public class GetDishServlet extends HttpServlet {

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
		
		int foodid = Integer.parseInt(request.getParameter("foodid"));

		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		CustomerDAO CustomerDAO = new CustomerDAO();
		//获得food数据
		FoodDAO FoodDAO = new FoodDAO();
		Food food = FoodDAO.findById(foodid);
		String dishname = food.getName();
		double price = food.getPrice();
		int category = food.getCategoryid();
		String description = food.getDescription();
		
		json.put("dishname", food.getName());
		json.put("price", food.getPrice());
		json.put("category", food.getCategoryid());
		json.put("description", food.getDescription());
		
		
		//获得该food的comment
		CommentDAO CommentDAO = new CommentDAO();
		Criteria cr = HibernateSessionFactory.getSession().createCriteria(Comment.class);
		cr.add(Restrictions.eq("food", food));//TODO .like改成.eq，有时间测试一下
		cr.addOrder(Order.asc("time"));
		ArrayList<Comment>comments =(ArrayList<Comment>)cr.list();
		double stars = 0;
		if(comments!=null && comments.size()!=0){
			json.put("havecomment", true);
			for(int i = 0;i < comments.size(); i ++){
				
				JSONObject jsonObj  = new JSONObject();
				
				jsonObj.put("customername",comments.get(i).getCustomer().getUsername()); //评论人
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //去掉.0 格式化
				jsonObj.put("time",df.format(comments.get(i).getTime()).toString());
				jsonObj.put("detail",comments.get(i).getDetail());
				double thisStar = comments.get(i).getStars();
				jsonObj.put("stars",thisStar);
				stars+=thisStar;
				
				jsonArray.put(jsonObj);
				
			}
			int star =(int) (stars/comments.size());
			json.put("star", star);
			json.put("comment", jsonArray);
		}
		else {
			json.put("havecomment", false);
		}
		
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
