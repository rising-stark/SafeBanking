package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bank.dao.bankDAO;
import com.bank.model.AccountDetails;
 
@WebServlet(asyncSupported=true, urlPatterns = { "/Invite" })
public class Invite extends HttpServlet {
     
	private static final long serialVersionUID = 1L;
	private static final int show = 60;
	private static final int hide = 106;
	private static final int effects=5;
	static Timer timer;
    
    public Invite() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
    	try {
    		bankDAO u=new bankDAO();
    		AccountDetails account=new AccountDetails();
    		GetTime obj=new GetTime();
    		String now=obj.now();
    		
    		String acc=u.generateAccountNo();
    		String imageString=u.encodeReg(acc,show, effects);
    		int count=u.retrieveAccountCount();
    		
    		account.setRegimagestring(imageString);
    		account.setLoginimagestring("NA");
    		account.setAcc(acc+count); //String concatenation
    		account.setSavings(1000);
    		account.setDatecreated(now);
    		account.setDatemodified("NA "+now);
    		
    		PrintWriter out = response.getWriter();
    		if(u.insertAccountDetails(account)==1) {
    			out.println ("<p>The account no. allocated to you is "+acc+count+"</p>");
    		}else {
    			out.println ("<p>Some error in allocating account no.</p>");
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
		/*Countdown.emailVerifyCountdown(10+"");
		System.out.println("\nCountdown\n\n");
		String nextviewpage="registration1.jsp";
		response.sendRedirect(nextviewpage);*/
	}
}