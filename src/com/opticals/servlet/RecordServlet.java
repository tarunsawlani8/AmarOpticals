package com.opticals.servlet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.opticals.model.Orders;
import com.opticals.utils.CommonUtils;


/**
 * Servlet implementation class RecordServlet
 */
@WebServlet("/recordController")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageAction = request.getParameter("pageAction");
		HttpSession session = request.getSession(false);
		System.out.println("Record Controller");
		
		
			if (("insertOrders").equals(pageAction)) {
				
				if (!StringUtils.isNumeric(request.getParameter("orderId")) || !StringUtils.isNumeric(request.getParameter("pendingAmount"))){
				session.setAttribute("message", "Order Id/Pending Amount must be Number");
				dispatch(request, response, "dashboard.jsp");
				CommonUtils.CacheControl(response);
						return;
				
				}} else {
					
					if (!StringUtils.isNumeric(request.getParameter("orderId"))){
				request.setAttribute("message", "Order Id must be Number");
				dispatch(request, response, "login.jsp");
				CommonUtils.CacheControl(response);
				return;
					}
			}
			
		
		Orders orders =	CommonUtils.mapOrders(request);
		
	
		if (("insertOrders").equals(pageAction)) {
				
		orders = CommonUtils.executeQuery("INSERT INTO opticals_orders  VALUES(?, ?, ?,?,?, ?)", orders, pageAction);
		
		
		if (orders != null) {
		session.setAttribute("actionStatus", "success");
		session.setAttribute("orderId", orders.getOrderId());
		}else {
			session.setAttribute("actionStatus", "failure");
		}
		
		dispatch(request, response, "dashboard.jsp");
		CommonUtils.CacheControl(response);
		
			} else {
				
				orders = CommonUtils.executeQuery("SELECT * FROM opticals_orders  WHERE ORDER_ID= ?", orders, pageAction);
				if (orders != null) {
					request.setAttribute("actionStatus", "success");
					request.setAttribute("orderObj", orders);
					}else {
						request.setAttribute("actionStatus", "failure");
				
					}
				dispatch(request, response, "login.jsp");
				CommonUtils.CacheControl(response);
			}
		
	}	
		
	

	private void dispatch(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
