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

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4465702463606233659L;

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
		String tel = object.getString("tel");
		
		System.out.println(username+" want to register");
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = new Customer();
		customer.setUsername(username);
		customer.setPassword(password);
		customer.setEmail(email);
		customer.setTel(tel);
		ArrayList<Customer>customers = (ArrayList<Customer>) customerDAO.findByUsername(username);
		if(customers.size()==0){
			customerDAO.save(customer);
			String customerid = String.valueOf(customer.getCustomerid());
			out.print(customerid); //注册成功
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
