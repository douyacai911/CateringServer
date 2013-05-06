package com.customer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

import util.HibernateSessionFactory;

import com.my.Dao.CommentDAO;
import com.my.Dao.CustomerDAO;
import com.my.Dao.FoodDAO;
import com.my.Entity.Comment;

public class AddReviewServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3726438282769854462L;

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
        
        CommentDAO commentDAO = new CommentDAO();
        Comment comment = new Comment();
        CustomerDAO customerDAO = new CustomerDAO();
        FoodDAO foodDAO = new FoodDAO();
        Criteria cr = HibernateSessionFactory.getSession().createCriteria(Comment.class);
		cr.add(Restrictions.eq("customer", customerDAO.findById(object.getInt("customerid"))));
		ArrayList<Comment>comments =(ArrayList<Comment>)cr.list();
		if(comments!=null && comments.size()!=0){
			for(int i = 0;i < comments.size(); i ++){
				if(comments.get(i).getFood().equals(foodDAO.findById(object.getInt("foodid")))){
					out.print("2");
					out.flush();
					out.close();
					return;
				}
			}
		}
        try{
        if(object.has("star")){
        	comment.setStars(object.getDouble("star"));
        	
        }
        comment.setCustomer(customerDAO.findById(object.getInt("customerid")));
        comment.setRestaurant(foodDAO.findById(object.getInt("foodid")).getRestaurant());
        Timestamp make = Timestamp.valueOf(object.getString("time"));
        comment.setTime(make);
        comment.setDetail(object.getString("review"));
		comment.setFood(foodDAO.findById(object.getInt("foodid")));
		commentDAO.save(comment);
		out.print("1");
        }catch(Exception e){
        	out.print("0");
        	e.printStackTrace();
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
