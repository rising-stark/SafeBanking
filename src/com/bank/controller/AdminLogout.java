package com.bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet("/AdminLogout")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class AdminLogout extends HttpServlet {
     
	private static final long serialVersionUID = 1L;
    
    public AdminLogout() {
        super();
    }
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
		HttpSession session = request.getSession(true);

    	
       session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			
		} else {
			request.getRequestDispatcher("admin_login.jsp").include(request, response);

		}
		request.getRequestDispatcher("admin_login.jsp").include(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}    
}