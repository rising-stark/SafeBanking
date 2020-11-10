package com.bank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.service.WorkingEmail;
import com.bank.dao.bankDAO;

@WebServlet(asyncSupported = true, urlPatterns = { "/ChangePassword" })
public class ChangePassword extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ChangePassword() {
		super();
	}
	private int validPass(String password) {
		int upper, lower, special, digit, i, len, valid;
		upper = lower = digit = special = valid = 0;
		len = password.length();
		if (len < 8)
			valid = 0;
		else {
			for (i = 0; i < len; i++) {
				char ch = password.charAt(i);
				if (ch <= 90 && ch >= 65)
					upper = 1;
				else if (ch <= 122 && ch >= 97)
					lower = 1;
				else if (ch <= 57 && ch >= 48)
					digit = 1;
				else if (ch <= 57 && ch >= 48)
					digit = 1;
				// ?!@$&%_
				else if (ch == 33 || ch == 63 || ch == 64 || ch == 36 || ch == 37 || ch == 38 || ch == 95)
					special = 1;
			}
			if (upper == 1 && lower == 1 && digit == 1 && special == 1)
				valid = 1;
		}
		return valid;
	}

	private int samePass(String newPassword, String cnfPassword) {
		int match = 0;
		if (newPassword.equals(cnfPassword))
			match = 1;
		return match;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(true);
			System.out.println("\n\nChangePassword.java ajax starts");
			
			String action = request.getParameter("action");
			String loginid = (String) session.getAttribute("loginid");
			String uname = (String) session.getAttribute("uname");
			String acc = (String) session.getAttribute("acc");
			
			if (action != null && action.equals("FirstTime")) {
				AddLoggedInLogList.add(loginid, uname, "ChangePassword.java", "ChangePassword.java through AccountProfile.jsp",request,response);
				bankDAO u=new bankDAO();
				String email=u.retrieveEmail(acc, "acc");
				if(email!=null) {
					String OTP=u.generateOTP();
					session.setAttribute("OTPEmail", OTP);
					String subject="Change Password";
					String message="This email is valid only for the next 3 minutes."
							+ "\nThis email is sent for changing password."
							+ "Please confirm this email id by entering the below mentioned OTP in the Change Password Window"
							+ "\nOTP: "+OTP+".\n\n";
					WorkingEmail.sendEmail(email,subject,message);
					response.getWriter().write("1");
				}
				else {
					response.getWriter().write("0");
					System.out.println("Email could not be retrieved");
				}
				return;
			}
			else if (action != null && action.equals("Resend")) {
				AddLoggedInLogList.add(loginid, uname, "ChangePassword.java", "ChangePassword.java Resend",request,response);
				bankDAO u=new bankDAO();
				String email=u.retrieveEmail(acc, "acc");
				if(email!=null) {
					String OTP=u.generateOTP();
					session.setAttribute("OTPEmail", OTP);
					String subject="Change Password";
					String message="This email is valid only for the next 3 minutes."
							+ "\nThis email is sent for changing password."
							+ "Please confirm this email id by entering the below mentioned OTP in the Change Password Window"
							+ "\nOTP: "+OTP+".\n\n";
					WorkingEmail.sendEmail(email,subject,message);
					response.getWriter().write("1");
				}
				else {
					response.getWriter().write("0");
					System.out.println("Email could not be retrieved");
				}
				return;
			}
			else if (action != null && action.equals("validNewPass")) {
				String newPassword = request.getParameter("newPassword").trim();
				System.out.println("\n\naction " + action);
				if (validPass(newPassword) == 0) {
					response.getWriter().write("0");
				}else {
					response.getWriter().write("1");
				}
				return;
			} else if (action != null && action.equals("validSameCnfNewPass")) {
				String cnfNewPassword = request.getParameter("cnfNewPassword").trim();
				String newPassword = request.getParameter("newPassword").trim();
				System.out.println("\n\naction " + action);
				if (samePass(cnfNewPassword, newPassword) == 0) {
					response.getWriter().write("0");
				} else {
					response.getWriter().write("1");
				}
				return;
			}
			else if (action != null && action.equals("VerifyEmail")) {
				String OTPUser= request.getParameter("OTPUser");
				String OTPEmail= (String) session.getAttribute("OTPEmail");
				if(OTPEmail.equals(OTPUser)) {
					response.getWriter().write("1");
				}
				else{
					response.getWriter().write("0");
				}
				AddLoggedInLogList.add(loginid, uname, "ChangePassword.java", "ChangePassword.java for VerifyEmail",request,response);
				return;
			}
			/*else if (action != null && action.equals("CheckOldPassword")) {
				bankDAO u = new bankDAO();
				String oldPassword= request.getParameter("oldPassword");
				if(u.checkPassword(oldPassword,uname)==1) {
					response.getWriter().write("1");
				}
				else{
					response.getWriter().write("0");
				}
				AddLoggedInLogList.add(loginid, uname, "ChangePassword.java", "ChangePassword.java for CheckOldPassword",request,response);
				return;
			}*/
			else if (action != null && action.equals("Change")) {
				bankDAO u = new bankDAO();
				GetTime obj=new GetTime();
				String now=obj.now();
				String newpass= request.getParameter("newPassword");
				System.out.println("\n\nuname " + uname);
				System.out.println("\nnewpass " + newpass);
				/*String email = (String) session.getAttribute("email");
				String uname = (String) session.getAttribute("uname");
				String newpass = request.getParameter("newpass").trim();
				String subtime3 = request.getParameter("subtime3").trim();
				String verifytime = request.getParameter("verifytime").trim();
				String cnfpasstime = request.getParameter("cnfpasstime").trim();
				String newpasstime = request.getParameter("newpasstime").trim();
				// String proceedtime = request.getParameter("proceedtime").trim();
				String oldpasstime = request.getParameter("oldpasstime").trim();*/
				/*if(u.insertChangePassword(uname, newpass,now)==1) {
					response.getWriter().write("1");
				}
				else{
					response.getWriter().write("0");
				}*/
				AddLoggedInLogList.add(loginid, uname, "ChangePassword.java", "ChangePassword.java for Change",request,response);
				// ChangePasswordLog change=new ChangePasswordLog();
				//u.insertChangePasswordLog(change);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}