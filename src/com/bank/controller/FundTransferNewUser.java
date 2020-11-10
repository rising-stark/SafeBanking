package com.bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.bankDAO;
import com.bank.model.TransactionDetails;
import com.bank.model.TransactionLogNewUserDetails;

@WebServlet("/FundTransferNewUser")
public class FundTransferNewUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FundTransferNewUser() {
		super();
	}
	
	private int validAccno(String accno) {
    	if(accno.length()!=10) 
			return 0;
    	return 1;
    }
	
	private int sameAccno(String accno,String cnfAccno) {
    	int match=0;
    	if(accno.equals(cnfAccno))
    		match=1;
    	return match;
    }
	
	private int validIfsc(String ifsc) {
    	if(ifsc.length()!=11 || (!ifsc.equals("SBIN0002425"))) {
    		System.out.println("\n\nIFSC code "+ifsc);
			return 0;}
    	return 1;
    }
	
	private int validAmount(int amount) {
    	if(amount>100 && amount<=500000)
    		return 1;
    	return 0;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
			response.setContentType("text/plain");
	    	response.setCharacterEncoding("UTF-8");
	    	String action=request.getParameter("action");
	    	if(action!=null && action.equals("validAccno")) {//System.out.println("\n\naction "+action);
	    		//System.out.println("\n\nRegistration1.java starts in Uname ajax");
				String accno = request.getParameter("accNo").trim();
				if(validAccno(accno)==0) {
					response.getWriter().write("0");
				}
				else {
					response.getWriter().write("1");
				}
				return;
			}else if(action!=null && action.equals("validSameCnfAccno")) {
				String accno = request.getParameter("accNo").trim();
				String cnfaccno = request.getParameter("cnfAccNo").trim();
				//System.out.println("\n\naction "+action);
				if(sameAccno(accno,cnfaccno)==1) {
					response.getWriter().write("1");
				}
				else {
					response.getWriter().write("0");
				}
				return;
			}
			else if(action!=null && action.equals("validIfscCode")) {//System.out.println("\n\naction "+action);
				String ifsccode = request.getParameter("ifscCode").trim();
				if(validIfsc(ifsccode)==0) {
					System.out.println("\n\nIFSC true code "+ifsccode);
					response.getWriter().write("0");
				}
				else {
					response.getWriter().write("1");
				}
				return;
			}
			else if(action!=null && action.equals("validAmount")) {//System.out.println("\n\naction "+action);
	    		//System.out.println("\n\nRegistration1.java starts in Uname ajax");
				int amount = Integer.parseInt(request.getParameter("Amt").trim());
				if(validAmount(amount)==0) {
					response.getWriter().write("0");
				}
				else {
					response.getWriter().write("1");
				}
				return;
			}
	    	System.out.println("\n\nFundTransferNewUser.java starts in Submit");
	    	HttpSession session = request.getSession(true);
	    	AddLoggedInLogList.add("FundTransferNewUser.java",request,response);
	    	ShowAll show = new ShowAll();
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String payeeaccno = request.getParameter("accno");
			String payeeifsc = request.getParameter("ifsc");
			String payeebranchname = request.getParameter("branchnamelist");
			String payeebankname = request.getParameter("banknamelist");
			int amount = Integer.parseInt(request.getParameter("amount"));
			
			String fundtransfernewuserpagetime = request.getParameter("fundtransfernewuserpagetime");
			String ftime = request.getParameter("ftime");
			String ltime = request.getParameter("ltime");
			String accnotime = request.getParameter("accnotime");
			String cnfaccnotime = request.getParameter("cnfaccnotime");
			String banknametime = request.getParameter("banknametime");
			String branchnametime = request.getParameter("branchnametime");
			String amounttime = request.getParameter("amounttime");
			String ifsctime = request.getParameter("ifsctime");
			String chktime = request.getParameter("chktime");
			String resettime = request.getParameter("resettime");
			String subtime1 = request.getParameter("subtime1");
		
			bankDAO u = new bankDAO();
			GetTime obj=new GetTime();
			String now = obj.now();
			TransactionDetails tr=new TransactionDetails();
			TransactionLogNewUserDetails trLogNewUser=new TransactionLogNewUserDetails();
			
			String uname=(String)session.getAttribute("uname");
			String nextviewpage=null;
			//String event_id=(String)session.getAttribute("event_id");
			String error="NA";
			int success=0;
			
			tr.setPayeraccno(u.retrieveAccno(uname));
			tr.setPayeeaccno(payeeaccno);
			tr.setPayeename(fname+" "+lname);
			tr.setPayername(u.retrieveName(uname));
			tr.setTransactiondate(now);
			tr.setTransactionid(u.generateEventId());
			tr.setPayeeifsc(payeeifsc);
			tr.setPayeebankname(payeebankname);
			tr.setPayeebranchname(payeebranchname);
			tr.setPayeetransferlimit(500000+"");
			tr.setAmount(amount);
			
			trLogNewUser.setPayeraccno(tr.getPayeraccno());
			trLogNewUser.setPayeeaccno(payeeaccno);
			trLogNewUser.setPayeename(tr.getPayeename());
			trLogNewUser.setTransactiondate(tr.getTransactiondate());
			trLogNewUser.setTransactionid(tr.getTransactionid());
			trLogNewUser.setFundtransfernewuserpagetime(fundtransfernewuserpagetime);
			trLogNewUser.setFtime(ftime);
			trLogNewUser.setLtime(ltime);
			trLogNewUser.setAccnotime(accnotime);
			trLogNewUser.setCnfaccnotime(cnfaccnotime);
			trLogNewUser.setBanknametime(banknametime);
			trLogNewUser.setBranchnametime(branchnametime);
			trLogNewUser.setIfsctime(ifsctime);
			trLogNewUser.setAmounttime(amounttime);
			trLogNewUser.setChktime(chktime);
			trLogNewUser.setResettime(resettime);
			trLogNewUser.setSubtime1(subtime1);			
			
			if (payeeaccno.equals(tr.getPayeraccno())) {
				error="Cannot Transfer to same account no.";
				nextviewpage = "FundTransferNewUser.jsp";
			} else {
				boolean exist = u.existAccount(payeeaccno);
				//exist=true;
				if (!exist) {
					error="No such account holder exists in the bank!!!";
					session.setAttribute("servermsg", error);
					nextviewpage = "FundTransferNewUser.jsp";
				} else {
					boolean already = u.alreadyBeneficiary(uname, payeeaccno);
					if (already) {
						error="This account holder is already your beneficiary. Redirecting to Fund Transfer to existing beneficiary.";
						session.setAttribute("servermsg",error );
						nextviewpage = "FetchBeneficiary?type=transfer";
					} else {
						String flag = u.blockAccount(payeeaccno);
						if (flag.equals("false")) {
							error="This Account no. is blocked";
							nextviewpage = "FundTransferNewUser.jsp";
						} else {
							int balance=u.retrieveBalance(uname);
							if(amount<=balance) {
								error="Successful";
								session.setAttribute("tr", tr);
								nextviewpage="ConfirmFetchTransaction";
							}
							else{
								error="Insufficient Balance";
								nextviewpage="FundTransferNewUser.jsp";
							}
						}
					}
				}
			}
			trLogNewUser.setSuccess(success);
			trLogNewUser.setError(error);
			session.setAttribute("servermsg", error);
			
			u.insertTransactionLogNewUserDetails(trLogNewUser);
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