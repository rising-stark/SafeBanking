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
import com.bank.model.BeneficiaryDetails;
import com.bank.model.LoggedInLogDetails;
import com.bank.model.TransactionBenLogDetails;
import com.bank.model.TransactionDetails;

@WebServlet("/FundTransferBen")
public class FundTransferBen extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FundTransferBen() {
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
			AddLoggedInLogList.addTime(loginid, uname, time, "FundTransferBen.jsp", "Successfully Loaded",request,response);
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			return;
		}else if(action!=null &&  action.equals("AccountProfile")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Account Profile Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="AccountProfile.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("PaymentTransfer")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Payment Transfer Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="PaymentTransfer.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("ManageBeneficiaries")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Manage Beneficiaries Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="ManageBeneficiaries.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("Transaction")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Transaction Menu Bar Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchTransaction";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("AddBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Add Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="AddBeneficiary.jsp";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("ApproveBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Approve Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchBeneficiary?type=approve";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("DeleteBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Delete Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchBeneficiary?type=delete";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("FundTransferBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Fund Transfer Existing Beneficiary Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchBeneficiary?type=transfer";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("MiniStatement")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Mini Statement Quick Link Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="FetchTransaction";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("Profile")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Profile Dropdown Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="Profile?action=FetchProfile";
			RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
			rd.forward(request, response);
			return;
		}else if(action!=null &&  action.equals("Settings")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Settings Dropdown Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="Settings";
			response.sendRedirect(nextviewpage);
			return;
		}else if(action!=null &&  action.equals("Logout")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransferBen.jsp", "Logout Dropdown Option Clicked",request,response);
			
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			String nextviewpage="Logout";
			response.sendRedirect(nextviewpage);
			return;
		}
		
		System.out.println("\n\nFund Transfer Beneficiary starts in submit");
		ShowAll show = new ShowAll();
    	BeneficiaryDetails ben=new BeneficiaryDetails();
    	TransactionDetails tr=new TransactionDetails();
    	TransactionBenLogDetails trbenLog = new TransactionBenLogDetails();
		
		show.ReqParam(request, response);
		show.SessionParam(request, response);
		
		int index=Integer.parseInt(request.getParameter("index"));
		ben=((ArrayList<BeneficiaryDetails>)session.getAttribute("benList")).get(index);
		
		String trid=u.generateTrid(loginid);
		String payeracc=acc;
		String payeeacc=ben.getBenacc();
		String payername=u.retrieveName(acc);
		String payeename=ben.getBenfname()+" "+ben.getBenlname();
		String fundtransferbenpagetime = request.getParameter("fundtransferbenpagetime");
		String transactiondate=request.getParameter("subtime1");
		String subtime1 = request.getParameter("subtime1");
		int success=1;
		String message="Clicked on transfer button. Moving to FundTransfer2.jsp";
		String nextviewpage = "FundTransfer2.jsp";
		
		AddLoggedInLogList.addTime(loginid, uname, subtime1, "FundTransferBen.java", "Processing Information", request, response);
		
		tr.setTrid(trid);
		tr.setLoginid(loginid);
		tr.setPayeracc(payeracc);
		tr.setPayeeacc(payeeacc);
		tr.setPayername(payername);
		tr.setPayeename(payeename);
		tr.setTransactiondate(transactiondate);
		tr.setType("0");
		tr.setSuccess(success);
		tr.setMessage(message);
		/*
		 * These 3 are not inserted in database and are only for
		 * displaying the confirm information on emailfundtransfer page.
		 * */
		tr.setBenifsc(ben.getBenifsc());
		tr.setBenbank(ben.getBenbankname());
		tr.setBenbranch(ben.getBenbranchname());
		
		trbenLog.setTrid(trid);
		trbenLog.setFundtransferbenpagetime(fundtransferbenpagetime);
		trbenLog.setSubtime1(subtime1);
		trbenLog.setSuccess(success);
		trbenLog.setMessage(message);
		
		session.setAttribute("tr", tr);
		session.setAttribute("amtLimit", ben.getBenlimit());
		
		session.removeAttribute("benList");
		
		AddLoggedInLogList.add(loginid, uname, "FundTransferBen.java", message, request, response);
		u.insertTransactionDetails(tr);
		u.insertTransactionBenLogDetails(trbenLog);
		if(tr.getType().equals("0")) {
			u.updateBenLastTransaction(tr);
		}
		u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
		AddLoggedInLogList.clear(request, response);
		
		response.sendRedirect(nextviewpage);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}