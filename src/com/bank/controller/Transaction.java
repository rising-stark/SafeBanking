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

@WebServlet("/Transaction")
public class Transaction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Transaction() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		bankDAO u=new bankDAO();
		
		String action=request.getParameter("action");
		String loginid=(String)session.getAttribute("loginid");
		String uname=(String)session.getAttribute("uname");
		String acc=(String)session.getAttribute("acc");
		
		if(action!=null &&  action.equals("setPageTime")) {
			String time = request.getParameter("pagetime").trim();
			
			AddLoggedInLogList.addTime(loginid, uname, time, "Transaction.jsp", "Successfully Loaded",request,response);
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			return;
		}else if(action!=null &&  action.equals("AccountProfile")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Account Profile Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="AccountProfile.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("PaymentTransfer")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Payment Transfer Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="PaymentTransfer.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("ManageBeneficiaries")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Manage Beneficiaries Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="ManageBeneficiaries.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("Transaction")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Transaction Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchTransaction";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("AddBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Add Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="AddBeneficiary.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("ApproveBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Approve Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchBeneficiary?type=approve";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("DeleteBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Delete Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchBeneficiary?type=delete";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("FundTransferBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Fund Transfer Existing Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchBeneficiary?type=transfer";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("MiniStatement")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Mini Statement Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchTransaction";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("Profile")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Profile Dropdown Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="Profile?action=FetchProfile";
			RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
			rd.forward(request, response);
			return;
		}else if(action!=null &&  action.equals("Settings")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Settings Dropdown Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="Settings";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("Logout")) {
			AddLoggedInLogList.add(loginid, uname, "Transaction.jsp", "Logout Dropdown Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="Logout";
			response.sendRedirect(nextviewpage);
			return;
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}