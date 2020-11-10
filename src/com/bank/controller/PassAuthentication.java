package com.bank.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.bankDAO;
import com.bank.model.LoggedInLogDetails;
import com.bank.model.LoggingInLogDetails;
import com.bank.model.LoginAttemptDetails;
import com.bank.model.LoginLogDetails;
import com.bank.service.WorkingEmail;

@WebServlet(asyncSupported=true, urlPatterns = { "/PassAuthentication" })
public class PassAuthentication extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int loginMaxAttempt=3;

	public PassAuthentication() {
		super();
	}

	private int validPass(String password) {
    	int upper,lower,special,digit,i,len,valid;
		upper=lower=digit=special=valid=0;
		len=password.length();
		if(len<8) 
			valid=0;
		else {
			for(i=0;i<len;i++) {
				char ch=password.charAt(i);
				if(ch<=90 && ch>=65)
					upper=1;
				else if(ch<=122 && ch>=97)
					lower=1;
				else if(ch<=57 && ch>=48)
					digit=1;
				else if(ch<=57 && ch>=48)
					digit=1;
				//     ?!@$&%_
				else if(ch==33 || ch==63 || ch==64 || ch==36 || ch==37 || ch==38 || ch==95)
					special=1;
			}
			if(upper==1 && lower==1 && digit==1 && special==1)
				valid=1;
		}
		return valid;
    }
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		
    	String loginid=(String)session.getAttribute("loginid");
    	String action=request.getParameter("action");
    	
    	if(action!=null && action.equals("validPass")) {
    		response.setContentType("text/plain");
        	response.setCharacterEncoding("UTF-8");
        	
			String Password = request.getParameter("passWord").trim();
			/*if(validPass(Password)==0) {
				response.getWriter().write("0");
			}
			else {
				response.getWriter().write("1");
			}*/
			response.getWriter().write("1");
			return;
		}
    	else if(action!=null && action.equals("setLoginpage2time")) {
    		response.setContentType("text/plain");
        	response.setCharacterEncoding("UTF-8");
        	    		
			String time = request.getParameter("loginpage2time").trim();
			String uname=((LoginLogDetails) session.getAttribute("loginLog")).getUname();
			
			AddLoggingInLogList.addTime(loginid, uname, time, "Login2.jsp", "Successfully Loaded",request,response);
			return;
		}else if(action!=null && action.equals("forgotPassword")) {
    		
			
			String uname=((LoginLogDetails) session.getAttribute("loginLog")).getUname();
			
			AddLoggingInLogList.add(loginid, uname, "Login2.jsp", "Successfully Loaded",request,response);
			return;
		}
    	System.out.println("Finally in PassAuth.java in submit");
		bankDAO u = new bankDAO();
		LoginLogDetails loginLog = (LoginLogDetails) session.getAttribute("loginLog");
		LoginAttemptDetails loginAttempt = (LoginAttemptDetails) session.getAttribute("loginAttempt");
		ShowAll show = new ShowAll();
		GetTime obj = new GetTime();
		
		show.ReqParam(request, response);
		show.SessionParam(request, response);

		String uname=loginLog.getUname();
		String password = request.getParameter("pass").trim();
		String picids = request.getParameter("picids").trim();
		String loginpage2time = request.getParameter("loginpage2time").trim();
		String passtime = request.getParameter("passtime").trim();
		String pictime = request.getParameter("pictime").trim();
		String subtime2 = request.getParameter("subtime2").trim();
		
		AddLoggingInLogList.addTime(loginid, uname, subtime2, "PassAuthentication.java", "Processing Information",request,response);
		
		System.out.println("\nSelected picids: "+picids);
		String picid = picids.substring(0, picids.length()-2);
		int effectid = (int)(picids.charAt(picids.length()-2) - '0');
		int language=(int)(picids.charAt(picids.length()-1)  - '0');
		String nextviewpage = "Login.jsp";
		int success=1;
		String message="None";

		loginLog.setLoginpage2time(loginpage2time);
		loginLog.setPasstime(passtime);
		loginLog.setPictime(pictime);
		loginLog.setAttemptpage2(-1);
		loginLog.setPicchosen(picid);
		loginLog.setEffectchosen(effectid+"");
		loginLog.setLanguagechosen(language+"");
		loginLog.setSubtime2(subtime2);
		loginLog.setLogintime("0");

		System.out.println("Parameter being send to the function u.passAuth\n");
		System.out.println("password: "+password);
		System.out.println("picid: "+picid);
		System.out.println("effectid: "+effectid);
		System.out.println("language: "+language);
		System.out.println("uname: "+uname);
		
		int confirm= u.passAuth(password, picid, effectid, language, uname);
		int loginAttemptLeftPage1=u.retrieveLoginAttemptPage1(uname);
		int loginAttemptLeftPage2=u.retrieveLoginAttemptPage2(uname);
		
		System.out.println("Confirm after running u.passAuth: "+confirm);
		System.out.println("loginAttemptLeftPage1: "+loginAttemptLeftPage1);
		System.out.println("loginAttemptLeftPage2: "+loginAttemptLeftPage2);
		
		String now=obj.now();
		if (confirm==1) {System.out.println("\nAuthenticated\n");
			success=2;
			message="Logged in Successfully";
			loginAttemptLeftPage1=5;
			loginAttemptLeftPage2=3;
			
			loginLog.setLogintime(now);
			loginLog.setAttemptpage2(loginAttemptLeftPage2);
			loginLog.setSuccess(success);
			loginLog.setMessage(message);
			
			loginAttempt.setAttemptpage1(loginAttemptLeftPage1);
			loginAttempt.setDatepage1(now);
			loginAttempt.setAttemptpage2(loginAttemptLeftPage2);
			loginAttempt.setDatepage2(now);
			loginAttempt.setSuccess(success-1);
			loginAttempt.setMessage(message);
			
			AddLoggingInLogList.addTime(loginid, uname, now, "PassAuthentication.java", message,request,response);
			
			//WorkingEmail.sendEmail(u.retrieveEmail(uname),"Logged in","User "+uname+" logged in at "+now);
			u.insertLoginAttemptDetails(loginAttempt, 2);
			u.insertLoginLogDetails(loginLog, 2);
			u.updateBankLastLogin(loginid, uname, message);
			u.insertLoggingInLogDetails((ArrayList<LoggingInLogDetails>)session.getAttribute("loggingInLogList"));
			AddLoggingInLogList.clear(request,response);
			
			session.invalidate();
			session = request.getSession(true);
			
			ArrayList<LoggedInLogDetails> loggedInLogList = new ArrayList<LoggedInLogDetails>();
			
			session.setAttribute("uname", uname);
			session.setAttribute("acc", u.retrieveAcc(uname));
			session.setAttribute("loginid", loginid);
			session.setAttribute("loggedInLogList", loggedInLogList);
			
			response.sendRedirect("AccountProfile.jsp");
			return;
		} else{
			//session.setAttribute("login","LOGIN");
			--loginAttemptLeftPage2;
			if(loginAttemptLeftPage2>1) {
				message="Incorrect Credentials. You have " + loginAttemptLeftPage2 + " attempts left";
			}
			else if(loginAttemptLeftPage2==1) {
				message="Incorrect Credentials. You have 1 attempt left";
			}else {
				message="You have entered incorrect credentials for "+ loginMaxAttempt +" times consecutively. "
						+ "Your account is blocked now.";
				u.insertBlockUser(uname);
			}
		}
		loginLog.setAttemptpage2(loginAttemptLeftPage2);
		loginAttempt.setSuccess(success);
		loginAttempt.setMessage(message);
		
		loginAttempt.setAttemptpage2(loginAttemptLeftPage2);
		loginAttempt.setDatepage2(now);
		loginLog.setSuccess(success);
		loginLog.setMessage(message);
		
		AddLoggingInLogList.addTime(loginid, uname, now, "PassAuthentication.java", message, request,response);
		
		session.setAttribute("notFound", message);
		u.updateBankLastLogin(loginid, uname, message);
		u.insertLoginAttemptDetails(loginAttempt, 2);
		u.insertLoginLogDetails(loginLog, 2);
		u.insertLoggingInLogDetails((ArrayList<LoggingInLogDetails>)session.getAttribute("loggingInLogList"));
		AddLoggingInLogList.clear(request,response);
		
		show.ReqParam(request, response);
		show.SessionParam(request, response);
		response.sendRedirect(nextviewpage);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}