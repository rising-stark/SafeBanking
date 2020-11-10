package com.bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.bankDAO;
@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Welcome() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		System.out.println("\nFinally in Welcome.java\n");
		
		ShowAll show = new ShowAll();
		GetTime obj = new GetTime();
		bankDAO u=new bankDAO();
		String now=obj.now();
		
		show.ReqParam(request, response);
		show.SessionParam(request, response);
		String value=(String) request.getParameter("proceed");
		String nextviewpage=null;
		
		String id=u.generateId();
		
		if(value.equals("LOGIN")) {
			int count=u.retrieveLoginCount();
			
			AddLoggingInLogList.addTime(id+count, "NA", now, "Welcome.java", "Login button clicked", request,response);
			session.removeAttribute("registeringLogList");
			session.setAttribute("LOGIN", value);
			session.setAttribute("loginid", id+count);
			nextviewpage="Login.jsp";
		}
		else if(value.equals("REGISTER")) {
			int count=u.retrieveRegCount();
			
			AddRegisteringLogList.addTime(id+count, "NA", now, "Welcome.java", "Register button clicked", request,response);
			session.removeAttribute("loggingInLogList");
			session.setAttribute("REGISTER", value);
			session.setAttribute("regid", id+count);
			nextviewpage="Registration1.jsp";
		}
		System.out.println("\nWelcome.java will end after showing all attributes\n");
		show.ReqParam(request, response);
		show.SessionParam(request, response);
		response.sendRedirect(nextviewpage);
		System.out.println("\nWelcome.java ended\n");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
