package com.bank.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.model.RegistrationVerifyLogDetails;
import com.bank.service.WorkingEmail;
import com.bank.dao.bankDAO;
import com.bank.model.BankDetails;
import com.bank.model.EmailVerifyAttemptDetails;
import com.bank.model.LoginLogDetails;

@WebServlet(asyncSupported = true, urlPatterns = { "/ForgotPassword" })
public class ForgotPassword extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int loginMaxAttempt = 3;
	private static final int loginMaxView = 5;
	private static final int emailMaxAttempt=5;

	public ForgotPassword() {
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
				// ?!@$&%_
				else if (ch == 33 || ch == 63 || ch == 64 || ch == 36 || ch == 37 || ch == 38 || ch == 95)
					special = 1;
			}
			if (upper == 1 && lower == 1 && digit == 1 && special == 1)
				valid = 1;
		}
		return valid;
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(true);
			bankDAO u = new bankDAO();
			GetTime obj = new GetTime();
			String action=request.getParameter("action");
			String loginid=(String)session.getAttribute("loginid");
			String uname=((LoginLogDetails)session.getAttribute("loginLog")).getUname();
			
			if(session.getAttribute("emailVerificationComplete")!=null && action != null && action.equals("emailVerificationComplete")){
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
				
		    	uname=(String)session.getAttribute("uname");
		    	acc=(String)session.getAttribute("acc");
		    	String email=u.retrieveEmail(acc, "acc");
				String subject="Password Changed";
				String body = "Welcome to SafeBanking.\n"
						+ "Your email is verified and your password is changed successfully.\n"
						+ "Your account is activated and ready for first login.\n\n";
				System.out.println("Time before sending email "+obj.now());
				WorkingEmail.sendEmail(email, subject, body);
				AddLoggingInLogList.add(loginid, uname, "EmailVerify.java", "Email Verify Completed. Password Changed Successfully. Email Sent to "+email, request, response);
				System.out.println("Time after sending email "+obj.now());
				u.insertRegisteringLogDetails((ArrayList<RegisteringLogDetails>)session.getAttribute("registeringLogList"));
				AddLoggingInLogList.clear(request,response);
				response.getWriter().write("1");
				session.invalidate();
				return;
			}
			if(action != null && action.equals("emailForgotInitiated")){
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
				
				String pass=bank.getPassword();
				String email=bank.getEmail();
				String time = request.getParameter("forgotpagetime").trim();
				String subject="Verify Your Email id";
				String body="This email is valid only for the next 10 minutes and is sent for Net Banking Registration"
						+ "\n\nYour Acccount Number "+acc.substring(0, 2)+"XXXXXXXXXX"+acc.substring(12)+"."
						+ "\nPlease verify your email account by entering the below mentioned password in the registration wizard and then changing your password"
						+ "\n\nPassword: "+pass
						+ "\n\nPlease change this password.\n\n\n";
				
				System.out.println("Time before sending email "+obj.now());
				AddLoggingInLogList.addTime(loginid, uname, time, "EmailVerify.java", "Email Verify Initiated. Sending Email to "+email, request, response);
				WorkingEmail.sendEmail(email, subject, body);
				System.out.println("Time after sending email "+obj.now());
				AddLoggingInLogList.add(loginid, uname, "EmailVerify.java", "Email Verify Initiated. Email Sent to "+email, request, response);
				
				u.insertRegisteringLogDetails((ArrayList<RegisteringLogDetails>)session.getAttribute("registeringLogList"));
				AddLoggingInLogList.clear(request,response);
				
				bank.setEmail(email);
				session.setAttribute("verify", bank);
				response.getWriter().write("1");
				return;
			}
			if (action != null && action.equals("validOldPass")) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				String oldPassword = request.getParameter("oldPassword").trim();
				if (validPass(oldPassword) == 0) {
					response.getWriter().write("1");
				} else {
					response.getWriter().write("2");
				}
				return;
			}
			if (action != null && action.equals("validSameNewPass")) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				String newPassword = request.getParameter("newPassword").trim();
				System.out.println("action " + action);
				if (validPass(newPassword) == 0) {
					response.getWriter().write("1");
				} else if (newPassword.equals(bank.getPassword())) {
					response.getWriter().write("0");
				} else {
					response.getWriter().write("2");
				}
				return;
			}
			if (action != null && action.equals("validSameCnfNewPass")) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				String cnfNewPassword = request.getParameter("cnfNewPassword").trim();
				String newPassword = request.getParameter("newPassword").trim();
				if (!cnfNewPassword.equals(newPassword)) {
					response.getWriter().write("1");
				} else {
					response.getWriter().write("2");
				}
				return;
			}
			if (action != null && action.equals("verifyEmail")) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				
				String emailverifypagetime = request.getParameter("emailverifypagetime").trim();
				String oldpass = request.getParameter("oldpass").trim();
				String oldpasstime = request.getParameter("oldpasstime").trim();
				String verifytime = request.getParameter("verifytime").trim();
				int success=0;
				String message="Incorrect Password";
				int attempt=emailMaxAttempt;//u.retrieveEmailverifyattempt(acc);
				
				if (oldpass.equals(bank.getPassword())) {
					success=1;
					message="Email Password is correct";
					response.getWriter().write("1");
				} else {
					response.getWriter().write("0");
				}
				
				RegistrationVerifyLogDetails regLog = new RegistrationVerifyLogDetails();
				
				regLog.setRegid(loginid);
				regLog.setAcc(acc);
				regLog.setUname(uname);
				regLog.setEmailverifypagetime(emailverifypagetime);
				regLog.setPassemail(bank.getPassword());
				regLog.setPassuser(oldpass);
				regLog.setOldpasstime(oldpasstime);
				regLog.setVerifytime(verifytime);
				regLog.setAttempt(attempt);
				regLog.setSuccess(success);
				regLog.setMessage(message);
				AddLoggingInLogList.addTime(loginid, uname, verifytime, "EmailVerify.jsp", message+" "+oldpass,request,response);
				
				u.insertRegistrationVerifyLogDetails(regLog, 0);
				u.insertRegisteringLogDetails((ArrayList<RegisteringLogDetails>)session.getAttribute("registeringLogList"));
				AddLoggingInLogList.clear(request,response);
				
				session.setAttribute("regLog", regLog);
				return;
			}
			if(action!=null && action.equals("setEmailverifypagetime")) {
				
				String time = request.getParameter("emailverifypagetime").trim();
				
				AddLoggingInLogList.addTime(loginid, uname, time, "EmailVerify.jsp", "Successfully Loaded",request,response);
				return;
			}
			
			ShowAll show = new ShowAll();
			RegistrationVerifyLogDetails regLog = (RegistrationVerifyLogDetails)session.getAttribute("regLog");
			LoginAttemptDetails loginAttempt = new LoginAttemptDetails();
			EmailVerifyAttemptDetails emailAttempt=new EmailVerifyAttemptDetails();
			
			System.out.println("\n\nEmailVerify.java starts in change button");
			
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			String newpass = request.getParameter("newpass").trim();
			String securityquestion = request.getParameter("securityquestion").trim();
			String securityanswer = request.getParameter("securityanswer").trim();
			
			String proceedtime = request.getParameter("proceedtime").trim();
			String newpasstime = request.getParameter("newpasstime").trim();
			String cnfpasstime = request.getParameter("cnfpasstime").trim();
			String securityquestime = request.getParameter("securityquestime").trim();
			String securityanstime = request.getParameter("securityanstime").trim();
			String subtime = request.getParameter("subtime").trim();
			int attempt=emailMaxAttempt;
			int success=2;
			String message="Registration Complete. Email Verified. Redirecing to Welcome.jsp";
			
			AddLoggingInLogList.addTime(loginid, uname, subtime, "EmailVerify.java", "Processing Information",request,response);
			
			regLog.setProceedtime(proceedtime);
			regLog.setNewpasstime(newpasstime);
			regLog.setCnfpasstime(cnfpasstime);
			regLog.setSecurityquestime(securityquestime);
			regLog.setSecurityanstime(securityanstime);
			regLog.setSubtime(subtime);
			regLog.setAttempt(attempt);
			regLog.setSuccess(success);
			regLog.setMessage(message);
			
			loginAttempt.setUname(uname);
			loginAttempt.setLoginid(loginid);
			loginAttempt.setAttemptpage1(loginMaxView);
			loginAttempt.setDatepage1(subtime);
			loginAttempt.setSuccess(success-1);
			loginAttempt.setMessage(message);
			
			bank.setPassword(newpass);
			bank.setSecurityquestion(securityquestion);
			bank.setSecurityanswer(securityanswer);
			bank.setVerified((success-1)+"");
			bank.setBankverified(loginid);
			bank.setBankmodified(loginid);
			bank.setMessage(message);
			
			emailAttempt.setUname(uname);
			emailAttempt.setRegid(loginid);
			emailAttempt.setDate(subtime);
			emailAttempt.setAttempt(emailMaxAttempt);
			
			System.out.println("\nEmailVerify.java showing all before modifying session\n");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			AddLoggingInLogList.add(loginid, uname, "EmailVerify.java", message, request, response);
			
			u.updateVerifyEmail(acc, loginid, message);
			u.insertLoginAttemptDetails(loginAttempt, 0);
			u.updatePassword(bank);
			u.insertRegistrationVerifyLogDetails(regLog, 1);
			u.insertRegisteringLogDetails((ArrayList<RegisteringLogDetails>)session.getAttribute("registeringLogList"));
			//u.insertEmailVerifyAttempt(emailAttempt);
			AddLoggingInLogList.clear(request,response);
			
			session.removeAttribute("verify");
			session.setAttribute("emailVerificationComplete", "complete");
			session.setAttribute("acc", acc);

			System.out.println("\nEmailVerify.java ends and checking session attributes as session is invalidated here\n");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			response.sendRedirect("EmailVerify.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}