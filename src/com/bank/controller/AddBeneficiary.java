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
import com.bank.model.BeneficiaryAddLogDetails;
import com.bank.model.LoggedInLogDetails;

@WebServlet("/AddBeneficiary")
public class AddBeneficiary extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AddBeneficiary() {
		super();
	}
	
	private int validacc(String acc) {
    	if(acc.length()!=14) 
			return 0;
    	return 1;
    }
	
	private int sameacc(String acc,String cnfacc) {
    	int match=0;
    	if(acc.equals(cnfacc))
    		match=1;
    	return match;
    }
	
	private int validIfsc(String ifsc) {
    	if(ifsc.length()!=11 || (!ifsc.equals("SBIN0002425"))) {
    		System.out.println("\n\nIFSC code "+ifsc);
			return 0;
		}
    	return 1;
    }
	
	private int validBankLimit(int banklimit) {
    	if(banklimit<101 || banklimit>500000)
			return 0;
    	return 1;
    }

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try{
			HttpSession session = request.getSession(true);
			bankDAO u=new bankDAO();
			
	    	String action=request.getParameter("action");
	    	String loginid=(String)session.getAttribute("loginid");
			String uname=(String)session.getAttribute("uname");
			String acc=(String)session.getAttribute("acc");
			
			if(action!=null &&  action.equals("setPageTime")) {
				String time = request.getParameter("pagetime").trim();
				
				AddLoggedInLogList.addTime(loginid, uname, time, "AddBeneficiary.jsp", "Successfully Loaded", request, response);
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				return;
			}else if(action!=null && action.equals("validacc")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
		    	
				String acc1 = request.getParameter("acc").trim();
				if(validacc(acc1)==0) {
					response.getWriter().write("0");
				}
				else {
					response.getWriter().write("1");
				}
				return;
			}else if(action!=null && action.equals("validSameCnfacc")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
				
				String acc1 = request.getParameter("acc").trim();
				String cnfacc = request.getParameter("cnfacc").trim();
				//System.out.println("\n\naction "+action);
				if(sameacc(acc1, cnfacc)==1) {
					response.getWriter().write("1");
				}
				else {
					response.getWriter().write("0");
				}
				return;
			}
			else if(action!=null && action.equals("validIfsccode")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
		    	
				String ifsccode = request.getParameter("ifsccode").trim();
				if(validIfsc(ifsccode)==0) {
					System.out.println("\n\nIFSC true code "+ifsccode);
					response.getWriter().write("0");
				}
				else {
					response.getWriter().write("1");
				}
				return;
			}
			else if(action!=null && action.equals("validBanktransferlimit")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
				
				int limit = Integer.parseInt(request.getParameter("limit").trim());
				if(validBankLimit(limit)==0) {
					response.getWriter().write("0");
				}
				else {
					response.getWriter().write("1");
				}
				return;
			}else if(action!=null && action.equals("resetButton")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
				
				BeneficiaryDetails ben = new BeneficiaryDetails();
				BeneficiaryAddLogDetails benLog =new BeneficiaryAddLogDetails();
				
				String payeracc = acc;
				String payername = u.retrieveName(payeracc);
				String benfname = request.getParameter("benfname").trim();
				if(benfname.length()>0) {
					benfname = benfname.substring(0, 1).toUpperCase() + benfname.substring(1);
				}
				String benlname = request.getParameter("benlname").trim();
				if(benlname.length()>0) {
					benlname = benlname.substring(0, 1).toUpperCase() + benlname.substring(1);
				}
				String benacc = request.getParameter("benacc").trim();
				String benlimit = request.getParameter("benlimit").trim();
				String benbankname = request.getParameter("benbankname").trim();
				String benbranchname = request.getParameter("benbranchname").trim();
				String benifsc = request.getParameter("benifsc").trim();
				int success=0;
				String message="Reset Button Clicked";
				
				String benaddpagetime = request.getParameter("benaddpagetime").trim();
				String ftime = request.getParameter("ftime").trim();
				String ltime = request.getParameter("ltime").trim();
				String acctime = request.getParameter("acctime").trim();
				String cnfacctime = request.getParameter("cnfacctime").trim();
				String limittime = request.getParameter("limittime").trim();
				String banknametime = request.getParameter("banknametime").trim();
				String branchnametime = request.getParameter("branchnametime").trim();
				String ifsctime = request.getParameter("ifsctime").trim();
				String chktime = request.getParameter("chktime").trim();
				String resettime = request.getParameter("resettime").trim();
				
				ben.setLoginid(loginid);
				ben.setPayeracc(payeracc);
				ben.setPayername(payername);
				ben.setBenfname(benfname);
				ben.setBenlname(benlname);
				ben.setBenacc(benacc);
				ben.setBenlimit(benlimit);
				ben.setBenbankname(benbankname);
				ben.setBenbranchname(benbranchname);
				ben.setBenifsc(benifsc);
				ben.setBendateadded(resettime);
				ben.setBenmodified(loginid);
				ben.setSuccess(success);
				ben.setMessage(message);
				
				benLog.setLoginid(loginid);
				benLog.setPayeracc(payeracc);
				benLog.setBenacc(benacc);
				benLog.setBenaddpagetime(benaddpagetime);
				benLog.setFtime(ftime);
				benLog.setLtime(ltime);
				benLog.setAcctime(acctime);
				benLog.setCnfacctime(cnfacctime);
				benLog.setLimittime(limittime);
				benLog.setBanknametime(banknametime);
				benLog.setBranchnametime(branchnametime);
				benLog.setIfsctime(ifsctime);
				benLog.setChktime(chktime);
				benLog.setResettime(resettime);
				benLog.setSubtime("0");
				benLog.setSuccess(success);
				benLog.setMessage(message);
				
				//System.out.println("\n\nAJAX reset time "+request.getParameter("resettime").trim());
				AddLoggedInLogList.addTime(loginid, uname, resettime, "AddBeneficiary.java", message, request, response);
				u.insertBeneficiaryDetails(uname, ben);
				u.insertBeneficiaryAddLogDetails(benLog);
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				return;
			}else if(action!=null &&  action.equals("AccountProfile")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Account Profile Menu Bar Option Clicked",request,response);
    			
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="AccountProfile.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}else if(action!=null &&  action.equals("PaymentTransfer")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Payment Transfer Menu Bar Option Clicked",request,response);
    			
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="PaymentTransfer.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}else if(action!=null &&  action.equals("ManageBeneficiaries")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Manage Beneficiaries Menu Bar Option Clicked",request,response);
    			
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="ManageBeneficiaries.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}else if(action!=null &&  action.equals("Transaction")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Transaction Menu Bar Option Clicked",request,response);
    			
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchTransaction";
				response.sendRedirect(nextviewpage);
				return;
			}else if(action!=null &&  action.equals("AddBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Add Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="AddBeneficiary.jsp";
				response.sendRedirect(nextviewpage);
				return;
			}else if(action!=null &&  action.equals("ApproveBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Approve Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=approve";
				response.sendRedirect(nextviewpage);
				return;
			}else if(action!=null &&  action.equals("DeleteBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Delete Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=delete";
				response.sendRedirect(nextviewpage);
				return;
			}else if(action!=null &&  action.equals("FundTransferBeneficiary")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Fund Transfer Existing Beneficiary Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchBeneficiary?type=transfer";
				response.sendRedirect(nextviewpage);
				return;
			}else if(action!=null &&  action.equals("MiniStatement")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Mini Statement Quick Link Option Clicked",request,response);
				
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="FetchTransaction";
				response.sendRedirect(nextviewpage);
				return;
			}else if(action!=null &&  action.equals("Profile")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Profile Option Clicked",request,response);
    			
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="Profile?action=FetchProfile";
				RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
				rd.forward(request, response);
				return;
			}else if(action!=null &&  action.equals("Settings")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Settings Option Clicked",request,response);
    			
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="Settings";
				response.sendRedirect(nextviewpage);
				return;
			}else if(action!=null &&  action.equals("Logout")) {
				AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.jsp", "Logout Option Clicked",request,response);
    			
				u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
				AddLoggedInLogList.clear(request, response);
				
				String nextviewpage="Logout";
				response.sendRedirect(nextviewpage);
				return;
			}
	    	System.out.println("\n\nAddBeneficiary.java starts in Submit");
	    	ShowAll show = new ShowAll();
	    	BeneficiaryDetails ben=new BeneficiaryDetails();
			BeneficiaryAddLogDetails benLog=new BeneficiaryAddLogDetails();
			
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			String payeracc=acc;
			String fname = request.getParameter("fname").trim();
			fname = fname.substring(0, 1).toUpperCase() + fname.substring(1);
			String lname = request.getParameter("lname").trim();
			lname = lname.substring(0, 1).toUpperCase() + lname.substring(1);
			String benacc = request.getParameter("acc").trim();
			String benlimit = request.getParameter("banktransferlimit").trim();
			String benbranchname = request.getParameter("branchnamelist").trim();
			String benbankname = request.getParameter("banknamelist").trim();
			String benifsc = request.getParameter("ifsc").trim();
			
			String benaddpagetime = request.getParameter("benaddpagetime");
			String ftime = request.getParameter("ftime");
			String ltime = request.getParameter("ltime");
			String acctime = request.getParameter("acctime");
			String cnfacctime = request.getParameter("cnfacctime");
			String limittime = request.getParameter("limittime");
			String banknametime = request.getParameter("banknametime");
			String branchnametime = request.getParameter("branchnametime");
			String ifsctime = request.getParameter("ifsctime");
			String chktime = request.getParameter("chktime");
			String resettime = request.getParameter("resettime");
			String subtime = request.getParameter("subtime");
			String message="NONE";
			int success=0;
			int approved=0;
			String nextviewpage = null;
					
			AddLoggedInLogList.addTime(loginid, uname, subtime, "AddBeneficiary.java", "Processing Information", request, response);
			
			ben.setLoginid(loginid);
			ben.setPayeracc(payeracc);
			ben.setPayername(u.retrieveName(payeracc));
			ben.setBenfname(fname);
			ben.setBenlname(lname);
			ben.setBenacc(benacc);
			ben.setBenlimit(benlimit);
			ben.setBenbankname(benbankname);
			ben.setBenbranchname(benbranchname);
			ben.setBenifsc(benifsc);
			ben.setBendateadded(subtime);
			ben.setBenapproved("0"); // Benapproved and benmodified contain loginids
			ben.setBenmodified("0");
			ben.setBenlasttransaction("0");
			ben.setBenlasttransactiondate("NA");
			ben.setApproved(approved);
			
			benLog.setLoginid(loginid);
			benLog.setPayeracc(payeracc);
			benLog.setBenacc(benacc);
			benLog.setBenaddpagetime(benaddpagetime);
			benLog.setFtime(ftime);
			benLog.setLtime(ltime);
			benLog.setAcctime(acctime);
			benLog.setCnfacctime(cnfacctime);
			benLog.setLimittime(limittime);
			benLog.setBanknametime(banknametime);
			benLog.setBranchnametime(branchnametime);
			benLog.setIfsctime(ifsctime);
			benLog.setChktime(chktime);
			benLog.setResettime(resettime);
			benLog.setSubtime(subtime);
			
			if (payeracc.equals(benacc)) {
				message="Cannot add yourself as a beneficiary";
				nextviewpage = "AddBeneficiary.jsp";
				session.setAttribute("notFound", message);
			} else {
				boolean exist = u.registeredAcc(benacc);
				//exist=true;
				if (exist==false) {
					message="No such account holder is registered in the bank!!!";
					nextviewpage = "AddBeneficiary.jsp";
					session.setAttribute("notFound", message);
				} else {
					int already = u.alreadyApprovedBeneficiary(payeracc, benacc);
					if (already==1) {
						message="Beneficiary already exists and is approved as well";
						nextviewpage = "AddBeneficiary.jsp";
						session.setAttribute("notFound", message);
					} else if (already==0){
						message="Beneficiary already exists but not approved. Redirecting you to Approve Beneficiary in 5s";
						//nextviewpage = "FetchBeneficiary?type=approve";
						nextviewpage = "AddBeneficiary.jsp";
						session.setAttribute("already", message);
					} else {
						String flag = u.checkBlockedAccount(benacc);
						if (flag.equals("false")) {
							message="THE BENEFICIARY YOU ARE TRYING TO ADD IS BLOCKED.";
							nextviewpage = "AddBeneficiary.jsp";
							session.setAttribute("notFound", message);
						} else {
							message="Beneficiary name added! Please approve the beneficiary.  Redirecting you to Approve Beneficiary in 5s";
							success=1;
							//nextviewpage = "FetchBeneficiary?type=approve";
							nextviewpage = "AddBeneficiary.jsp";
							session.setAttribute("already", message);
						}
					}
				}
			}
			ben.setSuccess(success);
			ben.setMessage(message);
			benLog.setSuccess(success);
			benLog.setMessage(message);
			AddLoggedInLogList.add(loginid, uname, "AddBeneficiary.java", message, request, response);
			
			u.insertBeneficiaryDetails(uname, ben);
			u.insertBeneficiaryAddLogDetails(benLog);
			u.insertLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList"));
			AddLoggedInLogList.clear(request, response);
			
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