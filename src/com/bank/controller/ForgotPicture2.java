package com.bank.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bank.dao.bankDAO;
import com.bank.model.BankDetails;
import com.bank.model.BeneficiaryDetails;
import com.bank.model.RandomPhotoDetails;
import com.bank.model.TransactionDetails;
 
@WebServlet("/ForgotPicture2")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class ForgotPicture2 extends HttpServlet {
     
	private static final long serialVersionUID = 1L;
    
    public ForgotPicture2() {
        super();
    }
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
    	HttpSession session= request.getSession(true);
    	String acc_no=(String)session.getAttribute("acc_no");
    	String site_key=request.getParameter("site_key");
	    String nextviewpage=null;
	    if(site_key!=null) {
	    	bankDAO up=new bankDAO();
		    boolean reply=up.change_picture(acc_no,site_key);
		    if(reply) {
		    	request.setAttribute("errormsg", "Picture successfully changed for account no: "+acc_no);
	    		nextviewpage="admin_home.jsp";

		    }
		    
		    	else {
			    	request.setAttribute("errormsg", "Something went wrong");
		    		nextviewpage="admin_home.jsp";

		    	}
	    }
	    else {
	    	request.setAttribute("errormsg", "Please select the picture!");
    		nextviewpage="ForgotPicture.jsp";
	    }
	    
	   
	    	
	    
	    
        RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
	     	rd.forward(request, response);
        
    }
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    	{
    		doPost(request,response);
    	}

    
    
    
}