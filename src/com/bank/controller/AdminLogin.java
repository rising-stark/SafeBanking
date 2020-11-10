package com.bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.bankDAO;
import com.bank.model.Admin;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AdminLogin() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// gets values of text fields
		HttpSession session1 = request.getSession(true);
		session1.invalidate();
		
		HttpSession session = request.getSession(true);
		
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		String nextviewpage = null;

		System.out.println("Uname " + uname);
		System.out.println("Password " + password);

		bankDAO u = new bankDAO();

		Admin ad = new Admin();
		ad.setUname(uname);
		ad.setPassword(password);

		/*int confirmation = u.checkAdmin(ad);

		if (confirmation==1) {
			nextviewpage = "admin_home.jsp";
			session.setAttribute("uname", uname);
		} else {
			session.setAttribute("notFound", "You are not an admin");
			nextviewpage = "AdminLogin.jsp";
		}*/

		response.sendRedirect(nextviewpage);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}