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
import com.bank.model.BeneficiaryDeleteLogDetails;
import com.bank.model.LoggedInLogDetails;
import com.bank.service.WorkingEmail;

@WebServlet("/EmailDeleteBeneficiary")
public class EmailDeleteBeneficiary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmailDeleteBeneficiary() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			bankDAO u = new bankDAO();
			GetTime obj = new GetTime();

			String action = request.getParameter("action");
			String loginid = (String) session.getAttribute("loginid");
			String uname = (String) session.getAttribute("uname");
			String payeracc=(String) session.getAttribute("acc");

			if (action != null && action.equals("setPageTime")) {
				String time = request.getParameter("pagetime").trim();

				AddLoggedInLogList.addTime(loginid, uname, time, "EmailDeleteBeneficiary.jsp", "Successfully Loaded",
						request, response);
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				return;
			}
			if(action != null && action.equals("emailDeletionInitiated")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
				
		    	BeneficiaryDeleteLogDetails benLog =(BeneficiaryDeleteLogDetails)session.getAttribute("benLog");
		    	String benacc=benLog.getBenacc();
		    	String benname=(String)session.getAttribute("benname");
				String email=(String)session.getAttribute("ex");
				String OTPEmail=u.generateOTP();
				String subject="Delete Beneficiary for SafeBanking";
				String body="This email is valid only for the next 10 minutes."
						+ "\nPlease verify the beneficiary you selected by entering the below mentioned OTP in the form opened"
						+ "\nThe details of the beneficiary selected are: \nAccount Number "+benacc+"."
						+ "\nBeneficiary Name: "+benname
						+ "\n\nOTP: "+OTPEmail
						+ "\n\n";

				System.out.println("Time before sending email "+obj.now());
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.java", "Beneficiary Email Deletion Initiated. Sending Email to "+email, request, response);
				WorkingEmail.sendEmail(email, subject, body);
				System.out.println("Time after sending email "+obj.now());
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.java", "Beneficiary Email Deletion Initiated. Email Sent to "+email, request, response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				benLog.setOTPEmail(OTPEmail);
				session.setAttribute("benLog", benLog);
				response.getWriter().write("1");
				return;
			}
			if (action != null && action.equals("AccountProfile")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp",
						"Account Profile Menu Bar Option Clicked", request, response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "AccountProfile.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("PaymentTransfer")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp",
						"Payment Transfer Menu Bar Option Clicked", request, response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "PaymentTransfer.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("ManageBeneficiaries")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp",
						"Manage Beneficiaries Menu Bar Option Clicked", request, response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "ManageBeneficiaries.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("Transaction")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp",
						"Transaction Menu Bar Option Clicked", request, response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "FetchTransaction";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("AddBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp", "Add Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="AddBeneficiary.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("ApproveBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp", "Approve Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=approve";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("DeleteBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp", "Delete Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=delete";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("Transactioneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp", "Fund Transfer Existing Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=transfer";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("MiniStatement")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp", "Mini Statement Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchTransaction";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("Profile")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp", "Profile Option Clicked", request,
						response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "Profile?action=FetchProfile";
				RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
				rd.forward(request, response);
				return;
			}
			if (action != null && action.equals("Settings")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp", "Settings Option Clicked",
						request, response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "Settings";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("Logout")) {
				AddLoggedInLogList.add(loginid, uname, "EmailDeleteBeneficiary.jsp", "Logout Option Clicked", request,
						response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "Logout";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("verifyOTP")) {

				System.out.println("\nEmailDeleteBeneficiary.java for verifyOTP starts");
				ShowAll show = new ShowAll();
				BeneficiaryDeleteLogDetails benLog=(BeneficiaryDeleteLogDetails)session.getAttribute("benLog");
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");

				show.ReqParam(request, response);
				show.SessionParam(request, response);

				String benemaildeletepagetime = request.getParameter("benemaildeletepagetime");
				String otptime = request.getParameter("otptime");
				String OTPUser = request.getParameter("OTPUser");
				String OTPEmail = benLog.getOTPEmail();
				String confirmtime = request.getParameter("confirmtime");
				String subtime2 = request.getParameter("subtime2");
				String bendeletetime=obj.now();
				int attempt = 3;
				int success = 1;
				int deleted=0;
				String message = "None";
				String nextviewpage = null; /*Been taken care of in EmailDeleteBeneficiary.js using window.location.href*/
				
				AddLoggedInLogList.addTime(loginid, uname, subtime2, "EmailDeleteBeneficiary.java", "Processing Information", request, response);
				
				benLog.setBenemaildeletepagetime(benemaildeletepagetime);
				benLog.setOtptime(otptime);
				benLog.setOTPUser(OTPUser);
				benLog.setOTPEmail(OTPEmail);
				benLog.setConfirmtime(confirmtime);
				benLog.setSubtime2(subtime2);
				benLog.setBendeletetime(bendeletetime);
				benLog.setAttempt(attempt);
				
				if (OTPEmail.equals(OTPUser)) {					
					success=2;
					deleted=-1;
					message="Beneficiary Deleted. Redirecting you to Manage Beneficiaries page.";
					
					session.removeAttribute("benLog");
					session.removeAttribute("ex");
					session.removeAttribute("benname");
				} else {
					success=1;
					deleted=1;
					message="Incorrect OTP!!. Beneficiary Not Deleted.";
				}
				response.getWriter().write((success-1)+"");
				benLog.setSuccess(success);
				benLog.setMessage(message);
				
				AddLoggedInLogList.addTime(loginid, uname, bendeletetime, "EmailDeleteBeneficiary.java", message, request, response);
				
				u.updateBeneficiaryDeleted(benLog, deleted);
				u.insertBeneficiaryDeleteLogDetails(benLog, 1);
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
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