package com.bank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.bankDAO;
import com.bank.model.LoggedInLogDetails;

@WebServlet("/FetchBeneficiary")
public class FetchBeneficiary extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FetchBeneficiary() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			// gets values of text fields
			HttpSession session = request.getSession(false);
			
			System.out.println("\n\nFetchBeneficiary.java starts");
			ShowAll show = new ShowAll();
			
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			String loginid=(String)session.getAttribute("loginid");
			String uname = (String) session.getAttribute("uname");
			String acc = (String) session.getAttribute("acc");
			String type= request.getParameter("type");
			String nextviewpage = null;
			
			bankDAO u = new bankDAO();
			List<Object> benList=null;
			
			/*All the records that are fetched from beneficiary table must only be 
			 * legitimate records i.e. those with success=1 because now the tables
			 * such as bank, beneficiary and transactions are designed to keep 
			 * useful as well failed attempts data in the same format and the success
			 * column is added to distinguish between the two and the message column
			 * indicates the problem happened with records having success=0 and
			 * is NONE for records with success=1  
			 * 
			 * */
			AddLoggedInLogList.add(loginid, uname, "FetchBeneficiary.java", "type="+type, request, response);
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			/*benList=u.fetchBeneficiary(uname,type);
			nextviewpage = type+".jsp";*/
			
			if(type.equals("show")) {
				benList=u.fetchBeneficiary(acc,"show");
				nextviewpage = "ShowBeneficiary.jsp";
			}
			else if(type.equals("delete")) {
				benList=u.fetchBeneficiary(acc,"delete");
				nextviewpage = "DeleteBeneficiary.jsp";
			}
			else if(type.equals("approve")) {
				benList=u.fetchBeneficiary(acc,"approve");
				nextviewpage = "ApproveBeneficiary.jsp";
			}
			else if(type.equals("transfer")) {
				benList=u.fetchBeneficiary(acc,"transfer");
				nextviewpage = "FundTransferBen.jsp";
			}
			
			session.setAttribute("benList", benList.get(0));
			session.setAttribute("ex", benList.get(1));
			response.sendRedirect(nextviewpage);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}