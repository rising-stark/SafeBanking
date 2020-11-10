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

@WebServlet("/FundTransfer2")
public class FundTransfer2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FundTransfer2() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		bankDAO u = new bankDAO();

		String action = request.getParameter("action");
		String loginid = (String) session.getAttribute("loginid");
		String uname = (String) session.getAttribute("uname");
		String acc = (String) session.getAttribute("acc");

		if (action != null && action.equals("setPageTime")) {
			String time = request.getParameter("pagetime").trim();

			AddLoggedInLogList.addTime(loginid, uname, time, "FundTransfer2.jsp", "Successfully Loaded", request,
					response);
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			return;
		} else if (action != null && action.equals("AccountProfile")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp", "Account Profile Menu Bar Option Clicked",
					request, response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "AccountProfile.jsp";
			response.sendRedirect(nextviewpage);
			return;
		} else if (action != null && action.equals("PaymentTransfer")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp", "Payment Transfer Menu Bar Option Clicked",
					request, response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "PaymentTransfer.jsp";
			response.sendRedirect(nextviewpage);
			return;
		} else if (action != null && action.equals("ManageBeneficiaries")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp",
					"Manage Beneficiaries Menu Bar Option Clicked", request, response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "ManageBeneficiaries.jsp";
			response.sendRedirect(nextviewpage);
			return;
		} else if (action != null && action.equals("Transaction")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp", "Transaction Menu Bar Option Clicked",
					request, response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "FetchTransaction";
			response.sendRedirect(nextviewpage);
			return;
		} else if (action != null && action.equals("AddBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp", "Add Beneficiary Quick Link Option Clicked",
					request, response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "AddBeneficiary.jsp";
			response.sendRedirect(nextviewpage);
			return;
		} else if (action != null && action.equals("ApproveBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp",
					"Approve Beneficiary Quick Link Option Clicked", request, response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "FetchBeneficiary?type=approve";
			response.sendRedirect(nextviewpage);
			return;
		} else if (action != null && action.equals("DeleteBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp",
					"Delete Beneficiary Quick Link Option Clicked", request, response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "FetchBeneficiary?type=delete";
			response.sendRedirect(nextviewpage);
			return;
		} else if (action != null && action.equals("FundTransferBeneficiary")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp",
					"Fund Transfer Existing Beneficiary Quick Link Option Clicked", request, response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "FetchBeneficiary?type=transfer";
			response.sendRedirect(nextviewpage);
			return;
		} else if (action != null && action.equals("MiniStatement")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp", "Mini Statement Quick Link Option Clicked",
					request, response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "FetchTransaction";
			response.sendRedirect(nextviewpage);
			return;
		} else if (action != null && action.equals("Profile")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp", "Profile Dropdown Option Clicked", request,
					response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "Profile?action=FetchProfile";
			RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
			rd.forward(request, response);
			return;
		} else if (action != null && action.equals("Settings")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp", "Settings Dropdown Option Clicked", request,
					response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "Settings";
			response.sendRedirect(nextviewpage);
			return;
		} else if (action != null && action.equals("Logout")) {
			AddLoggedInLogList.add(loginid, uname, "FundTransfer2.jsp", "Logout Dropdown Option Clicked", request,
					response);

			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);

			String nextviewpage = "Logout";
			response.sendRedirect(nextviewpage);
			return;
		}

		System.out.println("\n\nFund Transfer 2 starts in submit");
		ShowAll show = new ShowAll();
		TransactionDetails tr = (TransactionDetails) session.getAttribute("tr");
		TransactionConfirmLogDetails trconfirmLog = new TransactionConfirmLogDetails();

		show.ReqParam(request, response);
		show.SessionParam(request, response);

		String fundtransferpage2time = request.getParameter("fundtransferpage2time").trim();
		String amt=request.getParameter("amount").trim();
		int amount = Integer.parseInt(request.getParameter("amount"));
		String amounttime = request.getParameter("amounttime").trim();
		String subtime2 = request.getParameter("subtime2").trim();
		int success = 2;
		String message = "Clicked on next button. Moving to EmailFundTransfer.jsp";
		String nextviewpage = "EmailFundTransfer.jsp";
		int limit = Integer.parseInt((String) session.getAttribute("amtLimit"));
		int balance = u.retrieveBalance(acc);
		System.out.println("Amount iahhfu hh "+amount);
		System.out.println("Amount iahhfu hh "+amt);

		AddLoggedInLogList.addTime(loginid, uname, subtime2, "FundTransfer2.java", "Processing Information", request,
				response);
		
		tr.setTransactiondate(subtime2);
		tr.setAmount(amount);
		tr.setRemBalance(balance-amount);

		trconfirmLog.setTrid(tr.getTrid());
		trconfirmLog.setFundtransferpage2time(fundtransferpage2time);
		trconfirmLog.setAmounttime(amounttime);
		trconfirmLog.setSubtime2(subtime2);

		if (balance < amount) {
			success = 1;
			message = "Insufficient Balance";
			nextviewpage = "FundTransfer2.jsp";
			session.setAttribute("notFound", message);
		} else if (amount > limit) {
			success = 1;
			message = "Amount Entered is greater than Transfer Limit";
			nextviewpage = "FundTransfer2.jsp";
			session.setAttribute("notFound", message);
		}else {
			session.removeAttribute("amtLimit");
		}

		tr.setSuccess(success);
		tr.setMessage(message);

		trconfirmLog.setSuccess(success);
		trconfirmLog.setMessage(message);
		
		session.setAttribute("tr", tr);
		session.setAttribute("trconfirmLog", trconfirmLog);
		session.setAttribute("sendTransferEmail", "sendTransferEmail");

		AddLoggedInLogList.add(loginid, uname, "FundTransfer2.java", message, request, response);
		u.insertTransactionDetails(tr);
		u.insertTransactionConfirmLogDetails(trconfirmLog, 0);
		if(tr.getType().equals("0")) {
			u.updateBenLastTransaction(tr);
		}
		u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
		AddLoggedInLogList.clear(request, response);

		response.sendRedirect(nextviewpage);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}