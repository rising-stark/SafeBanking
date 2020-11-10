package com.bank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.bankDAO;
import com.bank.model.LoggedInLogDetails;
import com.bank.model.TransactionDetails;

class Sortbydate implements Comparator<TransactionDetails>{
	public int compare(TransactionDetails a, TransactionDetails b) {
		int yr1, yr2, mth1, mth2, day1, day2, hr1, hr2, min1, min2, sec1, sec2, msec1, msec2;
		yr1=Integer.parseInt(a.getTransactiondate().substring(6, 10));
		yr2=Integer.parseInt(b.getTransactiondate().substring(6, 10));
		if(yr1==yr2) {
			mth1=Integer.parseInt(a.getTransactiondate().substring(3, 5));
			mth2=Integer.parseInt(b.getTransactiondate().substring(3, 5));
			if(mth1==mth2) {
				day1=Integer.parseInt(a.getTransactiondate().substring(0, 2));
				day2=Integer.parseInt(b.getTransactiondate().substring(0, 2));
				if(day1==day2) {
					hr1=Integer.parseInt(a.getTransactiondate().substring(11, 13));
					hr2=Integer.parseInt(b.getTransactiondate().substring(11, 13));
					if(hr1==hr2) {
						min1=Integer.parseInt(a.getTransactiondate().substring(14, 16));
						min2=Integer.parseInt(b.getTransactiondate().substring(14, 16));
						if(min1==min2) {
							sec1=Integer.parseInt(a.getTransactiondate().substring(17, 19));
							sec2=Integer.parseInt(b.getTransactiondate().substring(17, 19));
							if(sec1==sec2) {
								msec1=Integer.parseInt(a.getTransactiondate().substring(20, 23));
								msec2=Integer.parseInt(b.getTransactiondate().substring(20, 23));
								return msec2 - msec1;
							}else {
								return sec2 - sec1;
							}
						}else {
							return min2 - min1;
						}
					}else {
						return hr2 - hr1;
					}
				}else {
					return day2 - day1;
				}
			}else {
				return mth2 - mth1;
			}
		}else {
			return yr2 - yr1;
		}
	}
}

@WebServlet("/FetchTransaction")
public class FetchTransaction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FetchTransaction() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			// gets values of text fields
			HttpSession session = request.getSession(false);
			
			System.out.println("\n\nFetchTransaction.java starts");
			ShowAll show = new ShowAll();
			
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			String loginid=(String)session.getAttribute("loginid");
			String uname = (String) session.getAttribute("uname");
			String acc = (String) session.getAttribute("acc");
			String nextviewpage = "Transaction.jsp";
			
			bankDAO u = new bankDAO();
			List<TransactionDetails> trList = new ArrayList<TransactionDetails>();
			
			/*All the records that are fetched from transaction table must only be 
			 * legitimate records i.e. those with success=3 because now the tables
			 * such as bank, beneficiary and transactions are designed to keep 
			 * useful as well failed attempts data in the same format and the success
			 * column is added to distinguish between the two and the message column
			 * indicates the problem happened  
			 * 
			 * */
			AddLoggedInLogList.add(loginid, uname, "FetchTransaction.java", "Processing information", request, response);
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>) session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
			/*benList=u.fetchBeneficiary(uname,type);
			nextviewpage = type+".jsp";*/
			trList=u.fetchTransactionDetailsList(acc);
			Collections.sort(trList, new Sortbydate());
			
			session.setAttribute("trList", trList);
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