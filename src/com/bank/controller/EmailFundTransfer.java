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
import com.bank.model.TransactionConfirmLogDetails;
import com.bank.model.TransactionDetails;
import com.bank.service.WorkingEmail;

@WebServlet("/EmailFundTransfer")
public class EmailFundTransfer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EmailFundTransfer() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(true);
			bankDAO u=new bankDAO();
			GetTime obj = new GetTime();
			
			String action=request.getParameter("action");
			String loginid=(String)session.getAttribute("loginid");
			String uname=(String)session.getAttribute("uname");
			String acc=(String)session.getAttribute("acc");
			
			if(action!=null &&  action.equals("setPageTime")) {
				String time = request.getParameter("pagetime").trim();
				System.out.println("\nHello "+time);
				AddLoggedInLogList.addTime(loginid, uname, time, "EmailFundTransfer.jsp", "Successfully Loaded",request,response);
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				return;
			}
			if(action!=null &&  action.equals("emailTransferInitiated")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
		    	
				TransactionConfirmLogDetails trconfirmLog = (TransactionConfirmLogDetails)session.getAttribute("trconfirmLog");
				String email=(String)session.getAttribute("ex");
				String OTPEmail=u.generateOTP();
				String subject="Transaction OTP";
				String body="This email is valid only for the next 180 seconds."
						+ "\nPlease enter the OTP in transaction window"
						+ "\n\nOTP: "+OTPEmail
						+ "\n\n";
				
				System.out.println("Time before sending email "+obj.now());
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.java", "Sending Email to "+email+" with OTP= "+OTPEmail, request, response);
				WorkingEmail.sendEmail(email, subject, body);
				System.out.println("Time after sending email "+obj.now());
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.java", "Email Sent to "+email+" with OTP= "+OTPEmail, request, response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				trconfirmLog.setOTPEmail(OTPEmail);
				session.setAttribute("trconfirmLog", trconfirmLog);
				response.getWriter().write("1");
				return;
			}
			if(action!=null &&  action.equals("AccountProfile")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Account Profile Menu Bar Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="AccountProfile.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("PaymentTransfer")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Payment Transfer Menu Bar Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="PaymentTransfer.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("ManageBeneficiaries")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Manage Beneficiaries Menu Bar Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="ManageBeneficiaries.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("Transaction")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Transaction Menu Bar Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchTransaction";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("AddBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Add Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="AddBeneficiary.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("ApproveBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Approve Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=approve";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("DeleteBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Delete Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=delete";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("FundTransferBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Fund Transfer Existing Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=transfer";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("MiniStatement")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Mini Statement Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchTransaction";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("Profile")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Profile Dropdown Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="Profile?action=FetchProfile";
				RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
				rd.forward(request, response);
				return;
			}
			if(action!=null &&  action.equals("Settings")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Settings Dropdown Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="Settings";
				response.sendRedirect(nextviewpage);
				return;
			}
			if(action!=null &&  action.equals("Logout")) {
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.jsp", "Logout Dropdown Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="Logout";
				response.sendRedirect(nextviewpage);
				return;
			}
			if (action != null && action.equals("verifyOTP")) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				
				System.out.println("\nEmailFundTransfer.java for verifyOTP starts");
				ShowAll show = new ShowAll();
				TransactionDetails tr=(TransactionDetails) session.getAttribute("tr");
				TransactionConfirmLogDetails trconfirmLog=(TransactionConfirmLogDetails) session.getAttribute("trconfirmLog");
				
				show.ReqParam(request, response);
				show.SessionParam(request, response);
				
				String confirmpagetime = request.getParameter("confirmpagetime");
				String otptime = request.getParameter("otptime");
				String OTPEmail = trconfirmLog.getOTPEmail();
				String OTPUser = request.getParameter("OTPUser");
				String subtime3 = request.getParameter("subtime3");
				int success=3;
				String message = "None";
				String nextviewpage = null; /*Been taken care of in EmailFundTransfer.js using window.location.href*/
				
				AddLoggedInLogList.addTime(loginid, uname, subtime3, "EmailFundTransfer.java", "Processing Information", request, response);
	
				trconfirmLog.setConfirmpagetime(confirmpagetime);
				trconfirmLog.setOTPTime(otptime);
				trconfirmLog.setOTPUser(OTPUser);
				
				if (OTPEmail.equals(OTPUser)) {
					success=3;
					message="Transaction Successful. Move to Payment and Transfers page.";
	
					session.removeAttribute("tr");
					session.removeAttribute("trconfirmLog");
				} else {
					success=2;
					message="Incorrect OTP!!. Transaction Unsuccessful.";
				}
				response.getWriter().write((success-2)+"");
				tr.setTransactiondate(subtime3);
				tr.setSuccess(success);
				tr.setMessage(message);
				
				trconfirmLog.setSuccess(success);
				trconfirmLog.setMessage(message);
				
				u.insertTransactionDetails(tr);
				u.insertTransactionConfirmLogDetails(trconfirmLog, 1);
				u.updateBenLastTransaction(tr);
				u.updateAccountBalance(acc, tr.getRemBalance());
				u.updateBankBalance(uname, tr.getRemBalance());
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.java", message, request, response);
				AddLoggedInLogList.clear(request, response);
				
				System.out.println("EmailFundTransfer.java ends after showing all\n");
				show.ReqParam(request, response);
				show.SessionParam(request, response);
				return;
			}
			if (action != null && action.equals("cancelTransaction")) {
				System.out.println("\nEmailFundTransfer.java for cancelTransaction starts");
				ShowAll show = new ShowAll();
				TransactionDetails tr=(TransactionDetails) session.getAttribute("tr");
				TransactionConfirmLogDetails trconfirmLog=(TransactionConfirmLogDetails) session.getAttribute("trconfirmLog");
				
				show.ReqParam(request, response);
				show.SessionParam(request, response);
				
				String confirmpagetime = request.getParameter("confirmpagetime");
				String otptime = request.getParameter("otptime");
				String OTPEmail = trconfirmLog.getOTPEmail();
				String OTPUser = request.getParameter("OTPUser");
				String subtime3 = request.getParameter("subtime3");
				int success=2;
				String message = "Transaction Cancelled. Redirecting to Payment and Transfers page in 5s.";
				String nextviewpage = null; /*Been taken care of in EmailFundTransfer.js using window.location.href*/
				
				AddLoggedInLogList.addTime(loginid, uname, subtime3, "EmailFundTransfer.java", "Processing Information", request, response);
	
				trconfirmLog.setConfirmpagetime(confirmpagetime);
				trconfirmLog.setOTPTime(otptime);
				trconfirmLog.setOTPUser(OTPUser);
				trconfirmLog.setSuccess(success);
				trconfirmLog.setMessage(message);
				
				tr.setSuccess(success);
				tr.setMessage(message);
				
				u.insertTransactionDetails(tr);
				u.insertTransactionConfirmLogDetails(trconfirmLog, 1);
				if(tr.getType().equals("0")) {
					u.updateBenLastTransaction(tr);
				}
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.add(loginid, uname, "EmailFundTransfer.java", message, request, response);
				AddLoggedInLogList.clear(request, response);
				
				System.out.println("EmailFundTransfer.java ends after showing all\n");
				show.ReqParam(request, response);
				show.SessionParam(request, response);
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