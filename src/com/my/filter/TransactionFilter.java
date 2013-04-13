package com.my.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateSessionFactory;

/**
 * Servlet Filter implementation class TransactionFilter
 */
@WebFilter("/TransactionFilter")
public class TransactionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TransactionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 Session s = HibernateSessionFactory.getSession();
		  Transaction tx = s.beginTransaction();
		  try
		  {
		   chain.doFilter(request, response);
		   tx.commit();
		   s.close();
		  } catch (Exception e)
		  {
		   tx.rollback();
		  }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
