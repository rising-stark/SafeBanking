package com.bank.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
 
@WebServlet("/ForgotPicture")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class ForgotPicture extends HttpServlet {
     
	private static final long serialVersionUID = 1L;
    
    public ForgotPicture() {
        super();
    }
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
    	HttpSession session= request.getSession(true);
        String nextviewpage=null;
        BankDetails up=new BankDetails();
       
        bankDAO u=new bankDAO();
        List<BankDetails> breaklist=u.fetchCustomer();
        if(breaklist.size() > 0)
	    {
	    	request.setAttribute("servermsg", breaklist);
	    	nextviewpage ="ForgotPicture.jsp";
	    }
	    else
	    {
	    	request.setAttribute("errormsg", "No Customer Details Found!");
	    	nextviewpage ="ForgotPicture.jsp";
	    }
	          
      
        RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
	     	rd.forward(request, response);
        
    }
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    	{
    		doPost(request,response);
    	}

    
    
    
}