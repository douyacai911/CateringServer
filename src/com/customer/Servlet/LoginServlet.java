package com.customer.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.my.Dao.CustomerDAO;
import com.my.Entity.Customer;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersioncustomerid = -6762656748491440566L;
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
		
        
		System.out.println(username+" is coming");
		CustomerDAO customerDAO = new CustomerDAO();
		ArrayList<Customer>customers = (ArrayList<Customer>) customerDAO.findByUsername(username);
		if(customers!=null && customers.size()!=0){
			for(Customer customer:customers){
				if(customer.getPassword().equals(password)){
					out.print(build(customer));
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
	private String build(Customer u){
		String userMsg = "";
		userMsg+="customerid="+u.getCustomerid();
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
