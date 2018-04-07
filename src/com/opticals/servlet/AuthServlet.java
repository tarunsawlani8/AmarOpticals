package com.opticals.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.opticals.utils.CommonUtils;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/loginApp")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthServlet() {
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
		System.out.println("in post");

		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println("in ull" + username + " " + password);

		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			System.out.println("in ull");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("loginApp.jsp");
			request.setAttribute("message",
					"Username/Password cannot be empty.");

			dispatcher.forward(request, response);
			CommonUtils.CacheControl(response);
			return;
		}

		if ("ankit".equals(username) && "opticals123".equals(password)) {

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/dashboard.jsp");
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", username);
			session.setAttribute("page", "addRecord");
			
			dispatcher.forward(request, response);
			CommonUtils.CacheControl(response);

		} else {

			System.out.println("in out");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("loginApp.jsp");
			request.setAttribute("message", "Invalid Credentials.");

			dispatcher.forward(request, response);
			CommonUtils.CacheControl(response);

		}

	}
	
	
}
