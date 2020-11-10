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
import com.bank.model.BeneficiaryApproveLogDetails;
import com.bank.model.LoggedInLogDetails;
import com.bank.service.WorkingEmail;

@WebServlet("/EmailApproveBeneficiary")
public class EmailApproveBeneficiary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmailApproveBeneficiary() {
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

				AddLoggedInLogList.addTime(loginid, uname, time, "EmailApproveBeneficiary.jsp", "Successfully Loaded", request, response);
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				return;
			}
			if(action != null && action.equals("emailApprovalInitiated")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
				
		    	BeneficiaryApproveLogDetails benLog =(BeneficiaryApproveLogDetails)session.getAttribute("benLog");
		    	String benacc=benLog.getBenacc();
		    	String benname=(String)session.getAttribute("benname");
				String email=(String)session.getAttribute("ex");
				String OTPEmail=u.generateOTP();
				String subject="Approve Beneficiary for SafeBanking";
				String body="This email is valid only for the next 10 minutes."
						+ "\nPlease verify the beneficiary you selected by entering the below mentioned OTP in the form opened"
						+ "\nThe details of the beneficiary selected are: \nAccount Number "+benacc+"."
						+ "\nBeneficiary Name: "+benname
						+ "\n\nOTP: "+OTPEmail
						+ "\n\n";

				System.out.println("Time before sending email "+obj.now());
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.java", "Beneficiary Email Approval Initiated. Sending Email to "+email+" with OTP= "+OTPEmail, request, response);
				WorkingEmail.sendEmail(email, subject, body);
				System.out.println("Time after sending email "+obj.now());
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.java", "Beneficiary Email Approval Initiated. Email Sent to "+email, request, response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				benLog.setOTPEmail(OTPEmail);
				session.setAttribute("benLog", benLog);
				response.getWriter().write("1");
				return;
			}
			if(action != null && action.equals("AccountProfile")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp",
						"Account Profile Menu Bar Option Clicked", request, response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "AccountProfile.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("PaymentTransfer")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp",
						"Payment Transfer Menu Bar Option Clicked", request, response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "PaymentTransfer.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("ManageBeneficiaries")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp",
						"Manage Beneficiaries Menu Bar Option Clicked", request, response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "ManageBeneficiaries.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("Transaction")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp",
						"Transaction Menu Bar Option Clicked", request, response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "FetchTransaction";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("AddBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp", "Add Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="AddBeneficiary.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("ApproveBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp", "Approve Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=approve";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("DeleteBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp", "Delete Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=delete";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("Transactioneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp", "Fund Transfer Existing Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=transfer";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("MiniStatement")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp", "Mini Statement Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchTransaction";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("Profile")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp", "Profile Option Clicked", request,
						response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "Profile?action=FetchProfile";
				RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
				rd.forward(request, response);
				return;
			}
			if (action != null && action.equals("Settings")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp", "Settings Option Clicked",
						request, response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "Settings";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("Logout")) {
				AddLoggedInLogList.add(loginid, uname, "EmailApproveBeneficiary.jsp", "Logout Option Clicked", request,
						response);

				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);

				String nextviewpage = "Logout";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("verifyOTP")) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				
				System.out.println("\nEmailApproveBeneficiary.java for verifyOTP starts");
				ShowAll show = new ShowAll();
				BeneficiaryApproveLogDetails benLog =(BeneficiaryApproveLogDetails)session.getAttribute("benLog");

				show.ReqParam(request, response);
				show.SessionParam(request, response);

				String benemailapprovepagetime = request.getParameter("benemailapprovepagetime");
				String otptime = request.getParameter("otptime");
				String OTPEmail = benLog.getOTPEmail();
				String OTPUser = request.getParameter("OTPUser");
				String subtime2 = request.getParameter("subtime2");
				String benapprovetime=obj.now();
				int attempt=3;
				int success = 1;
				String message = "None";
				String nextviewpage = null; /*Been taken care of in EmailApproveBeneficiary.js using window.location.href*/
				
				AddLoggedInLogList.addTime(loginid, uname, subtime2, "EmailApproveBeneficiary.java", "Processing Information", request, response);
				
				benLog.setBenemailapprovepagetime(benemailapprovepagetime);
				benLog.setOtptime(otptime);
				benLog.setOTPUser(OTPUser);
				benLog.setOTPEmail(OTPEmail);
				benLog.setSubtime2(subtime2);
				benLog.setBenapprovetime(benapprovetime);
				benLog.setAttempt(attempt);
				
				if (OTPEmail.equals(OTPUser)) {
					success=2;
					message="Beneficiary Approved. Redirecting you to Manage Beneficiaries page.";
					
					session.removeAttribute("benLog");
					session.removeAttribute("ex");
					session.removeAttribute("benname");
				} else {
					success=1;
					message="Incorrect OTP!!. Beneficiary Not Approved.";
				}
				response.getWriter().write((success-1)+"");
				benLog.setSuccess(success);
				benLog.setMessage(message);
				
				AddLoggedInLogList.addTime(loginid, uname, benapprovetime, "EmailApproveBeneficiary.java", message, request, response);
				
				u.updateBeneficiaryApproved(benLog);
				u.insertBeneficiaryApproveLogDetails(benLog, 1);
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