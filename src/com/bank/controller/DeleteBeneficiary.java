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
import com.bank.model.BeneficiaryDetails;
import com.bank.model.LoggedInLogDetails;

@WebServlet("/DeleteBeneficiary")
public class DeleteBeneficiary extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DeleteBeneficiary() {
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
			
			AddLoggedInLogList.addTime(loginid, uname, time, "DeleteBeneficiary.jsp", "Successfully Loaded",request,response);
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			return;
		}else if(action!=null &&  action.equals("AccountProfile")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Account Profile Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="AccountProfile.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("PaymentTransfer")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Payment Transfer Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="PaymentTransfer.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("ManageBeneficiaries")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Manage Beneficiaries Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="ManageBeneficiaries.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("Transaction")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Transaction Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchTransaction";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("AddBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Add Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="AddBeneficiary.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("ApproveBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Approve Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchBeneficiary?type=approve";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("DeleteBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Delete Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchBeneficiary?type=delete";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("FundTransferBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Fund Transfer Existing Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchBeneficiary?type=transfer";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("MiniStatement")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Mini Statement Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchTransaction";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("Profile")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Profile Dropdown Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="Profile?action=FetchProfile";
			RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
			rd.forward(request, response);
			return;
		}else if(action!=null &&  action.equals("Settings")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Settings Dropdown Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="Settings";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("Logout")) {
			AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.jsp", "Logout Dropdown Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="Logout";
			response.sendRedirect(nextviewpage);
			return;
		}
		
		System.out.println("\n\nDelete Beneficiary starts in submit");
		ShowAll show = new ShowAll();
    	BeneficiaryDetails ben=new BeneficiaryDetails();
    	BeneficiaryDeleteLogDetails benLog=new BeneficiaryDeleteLogDetails();
		
		show.ReqParam(request, response);
		show.SessionParam(request, response);
		
		int index=Integer.parseInt(request.getParameter("index"));
		ben=((ArrayList<BeneficiaryDetails>)session.getAttribute("benList")).get(index);
		
		String payeracc=acc;
		String benacc=ben.getBenacc();
		String benname=ben.getBenfname()+" "+ben.getBenlname();
		String bendeletepagetime = request.getParameter("bendeletepagetime");
		String subtime1 = request.getParameter("subtime1");
		int success=1;
		String message="Clicked on delete button. Moving to EmailDeleteBeneficiary.jsp";
		String nextviewpage = "EmailDeleteBeneficiary.jsp";
		
		AddLoggedInLogList.addTime(loginid, uname, subtime1, "DeleteBeneficiary.java", "Processing Information", request, response);
		
		benLog.setLoginid(loginid);
		benLog.setPayeracc(payeracc);
		benLog.setBenacc(benacc);
		benLog.setBendeletepagetime(bendeletepagetime);
		benLog.setSubtime1(subtime1);
		benLog.setSuccess(success);
		benLog.setMessage(message);
		
		session.setAttribute("benLog", benLog);
		session.setAttribute("benname", benname);
		session.setAttribute("sendDeleteEmail", "sendDeleteEmail");
		session.setAttribute("approved", ben.getApproved());
		
		session.removeAttribute("benList");
		
		AddLoggedInLogList.add(loginid, uname, "DeleteBeneficiary.java", message, request, response);
		u.insertBeneficiaryDeleteLogDetails(benLog, 0);
		u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
		AddLoggedInLogList.clear(request, response);
		
		response.sendRedirect(nextviewpage);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}