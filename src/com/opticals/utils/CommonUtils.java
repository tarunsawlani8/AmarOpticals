package com.opticals.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.opticals.model.Orders;

public class CommonUtils {
	private static DataSource dataSource = null;

	public static void getDataSource() {
			
		if (dataSource == null) {
		try {
			Context initCtx = new InitialContext();

			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			dataSource = (DataSource) envCtx.lookup("jdbc/RealDB");
			System.out.println("DS Initialised");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}

	
	public static Orders mapOrders (HttpServletRequest request) {
		
		Orders orders = new Orders();
		orders.setOrderId(Integer.parseInt(request.getParameter("orderId")));
		orders.setCustomerName(request.getParameter("customerName"));
		orders.setCustomerContact(request.getParameter("customerContact"));
		orders.setDeliveryStatus(request.getParameter("deliveryStatus"));
		orders.setDeliveryDate(request.getParameter("deliveryDate"));
		if (request.getParameter("pendingAmount") != null) {
		orders.setPendingAmount(Integer.parseInt(request.getParameter("pendingAmount")));
		}
		return orders;
		
	
	} 
	
	public static Orders executeQuery (String sql, Orders orders, String pageAction) {
		
		if (dataSource == null) {
			getDataSource();
		}
		
		Connection con = null;
		
		PreparedStatement ps;
		try {
			
			con = dataSource.getConnection();
			 ps = con.prepareStatement(sql);
			
			if ("insertOrders".equalsIgnoreCase(pageAction)) {
			ps.setInt(1, orders.getOrderId());
			ps.setString(2, orders.getCustomerName());
			ps.setString(3, orders.getCustomerContact());
			ps.setString(4, orders.getDeliveryStatus());
			ps.setString(5, orders.getDeliveryDate());
			ps.setInt(6, orders.getPendingAmount());
			int count =	ps.executeUpdate();
			if (count > 0)
			{
				return orders;
			}
			}else {
				ps.setInt(1, orders.getOrderId());
				ResultSet result =	ps.executeQuery();
				if (result.next()) {
					System.out.println("got Record"+result.getInt(1));
				Orders ordersresult = new Orders();
				ordersresult.setOrderId(result.getInt(1));
				ordersresult.setCustomerName(result.getString(2));
				ordersresult.setCustomerContact(result.getString(3));
				ordersresult.setDeliveryStatus(result.getString(4));
				ordersresult.setDeliveryDate(result.getString(5));
				ordersresult.setPendingAmount(result.getInt(6));
				return ordersresult;
				}
				}
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
				
					if (con !=null)
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

	}
		return null;
		}
	
	public static void CacheControl(HttpServletResponse response) {
		 response.setHeader("Cache-Control", "no-cache");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Expires", "-1");
	}

}
