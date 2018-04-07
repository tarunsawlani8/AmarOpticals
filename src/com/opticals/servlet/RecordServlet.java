package com.opticals.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		System.out.println("Record Controller page Action"+pageAction);
		
		
			if ("insertOrders".equals(pageAction)) {
				
				if (!StringUtils.isNumeric(request.getParameter("orderId")) || !StringUtils.isNumeric(request.getParameter("pendingAmount"))){
					request.setAttribute("message", "Order Id/Pending Amount must be Number");
				dispatch(request, response, "dashboard.jsp");
				CommonUtils.CacheControl(response);
						return;
				
				}} else if ("getOrderDetails".equals(pageAction))  {
					
					if (!StringUtils.isNumeric(request.getParameter("orderId"))){
				request.setAttribute("message", "Order Id must be Number");
				dispatch(request, response, "login.jsp");
				CommonUtils.CacheControl(response);
				return;
					}
			}
			
		
		Orders orders =	CommonUtils.mapOrders(request);
		
		HttpSession session = request.getSession(false);
		if (("insertOrders").equals(pageAction)) {
			
		Orders	oldOrder = CommonUtils.executeQuery("SELECT * FROM opticals_orders  WHERE ORDER_ID=?", orders, "getOrderDetails", null);
				
			if (oldOrder == null) {
		orders = CommonUtils.executeQuery("INSERT INTO opticals_orders  VALUES(?, ?, ?,?,?, ?)", orders, pageAction, null);
			} else {
				orders = CommonUtils.executeQuery("UPDATE opticals_orders SET DELIVERY_STATUS=?, DELIVERY_DATE=? WHERE ORDER_ID=?",
						orders, pageAction, null);
			}
		
		if (orders != null) {
			request.setAttribute("actionStatus", "success");
		request.setAttribute("message", "Order has been successfully"
				+ " added to the Database with orderId "+ orders.getOrderId());
	
		}else {
			request.setAttribute("actionStatus", "failure");
			request.setAttribute("message", "Order cannot be added to Database due to some issues");
		
		}
		
		dispatch(request, response, "dashboard.jsp");
		CommonUtils.CacheControl(response);
		
			} else if ("getOrderDetails".equals(pageAction))  {
				
				orders = CommonUtils.executeQuery("SELECT * FROM opticals_orders  WHERE ORDER_ID= ?", orders, pageAction, null);
				if (orders != null) {
					request.setAttribute("actionStatus", "success");
					request.setAttribute("orderObj", orders);
					}else {
						request.setAttribute("actionStatus", "failure");
				
					}
				dispatch(request, response, "login.jsp");
				CommonUtils.CacheControl(response);
			} else if (("searchRecords").equals(pageAction))  {
				
				StringBuilder strBuilder = new StringBuilder();
				strBuilder.append("SELECT * FROM opticals_orders");
				
				boolean andReq = false;
				if (orders.getOrderId()!= null) {
					
					if (!andReq) {
						strBuilder.append(" WHERE ");
					}
					
					strBuilder.append("ORDER_ID="+orders.getOrderId());
					andReq= true;
				}
				
				if (orders.getCustomerName()!= null) {
					
					if (andReq) {
						strBuilder.append(" AND ");
					} else {
						strBuilder.append(" WHERE ");
					}
					
					strBuilder.append("CUSTOMER_NAME LIKE '"+orders.getCustomerName()+"%'");
					andReq = true;
				}
				
					if (orders.getDeliveryStatus()!= null) {
						if (andReq) {
							strBuilder.append(" AND ");
						} else {
							strBuilder.append(" WHERE ");
						}
					
					strBuilder.append("DELIVERY_STATUS LIKE '"+orders.getDeliveryStatus()+"'");
				}
					List<Orders> ordersList = new ArrayList<>();
				 CommonUtils.executeQuery(strBuilder.toString(), orders, pageAction, ordersList );
					request.setAttribute("message", "Number of records found :"+ordersList.size());
					request.setAttribute("orderList", ordersList);
				
				dispatch(request, response, "dashboard.jsp");
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
