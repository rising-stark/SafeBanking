package com.bank.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.bankDAO;
import com.bank.model.LoggedInLogDetails;
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Logout() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		System.out.println("\nFinally in Logout.java\n");
		
		ShowAll show = new ShowAll();
		GetTime obj = new GetTime();
		bankDAO u=new bankDAO();
		
		show.ReqParam(request, response);
		show.SessionParam(request, response);
		
		String loginid = (String) session.getAttribute("loginid");
		String uname = (String) session.getAttribute("uname");
		String acc = (String) session.getAttribute("acc");
		String logouttime=obj.now();
		String nextviewpage = "Welcome.jsp";
		String message = "";
		
		u.updateLogout(loginid, uname, logouttime, message);
		AddLoggedInLogList.addTime(loginid, uname, logouttime, "Logout.java", "Successfully Loggedout. Redirected to Welcome.jsp", request,
				response);
		u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
		AddLoggedInLogList.clear(request, response);
		
		session.invalidate();
		session = request.getSession(true);
		session.setAttribute("Logout", "Logout");
		response.sendRedirect(nextviewpage);
		System.out.println("\nlogout.java ended\n");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
