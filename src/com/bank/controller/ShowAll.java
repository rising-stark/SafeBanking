package com.bank.controller;

import java.io.IOException;
import com.bank.model.*;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowAll extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void ReqParam(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("Here are all the parameter values accepted from user");
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			System.out.print("\t"+paramName + ": ");
			String[] paramValues = request.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++)
				System.out.println(paramValues[i]);
		}

		System.out.println("Here are all the request attributes set");
		Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String attrname = (String) attributeNames.nextElement();
			System.out.println("\t"+attrname);
			//System.out.println(request.getValue(attrname));
		}
	}

	public void SessionParam(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		System.out.println("Here are all the session attributes set\n");
		HttpSession session = request.getSession(true);
		Enumeration<String> keys = session.getAttributeNames();
		int i=0;
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			System.out.println(++i+". "+key + ": " + session.getAttribute(key));
			
			if(key.equals("bank") || key.equals("up")) {
				printBankDetails((BankDetails)session.getAttribute(key));
			}
			else if(key.equals("regLog")) {
				printRegistrationLogDetails((RegistrationAddLogDetails)session.getAttribute(key));
			}
			else if(key.equals("loginLog")) {
				printLoginLogDetails((LoginLogDetails)session.getAttribute(key));
			}
			else if(key.equals("loggedInLogList")) {
				printLoggedInLogDetails((ArrayList<LoggedInLogDetails>)session.getAttribute(key));
			}
			else if(key.equals("loggingInLogList")) {
				printLoggingInLogDetails((ArrayList<LoggingInLogDetails>)session.getAttribute(key));
			}
			else if(key.equals("registeringLogList")) {
				printRegisteringLogDetails((ArrayList<RegisteringLogDetails>)session.getAttribute(key));
			}
			else if(key.equals("loginAttempt")) {
				printLoginAttemptDetails((LoginAttemptDetails)session.getAttribute(key));
			}
			else if(key.equals("emailVerifyAttempt")) {
				printEmailVerifyAttemptDetails((EmailVerifyAttemptDetails)session.getAttribute(key));
			}
			else if(key.equals("pc")) {
				printPhotoCountDetails((PhotoCountDetails)session.getAttribute(key));
			}
			else if(key.equals("imglog")) {
				printImageLogDetails((ImageLogDetails)session.getAttribute(key));
			}/*
			else if(key.equals("ben")) {
				printBeneficiaryDetails((ArrayList<BeneficiaryDetails>)session.getAttribute(key));
			}
			else if(key.equals("benLog")) {
				printBeneficiaryLogDetails((BeneficiaryLogDetails)session.getAttribute(key));
			}*/
			else if(key.equals("tr")) {
				printTransactionDetails((TransactionDetails)session.getAttribute(key));
			}
			else if(key.equals("trbenLog")) {
				printTransactionBenLogDetails((TransactionBenLogDetails)session.getAttribute(key));
			}/*
			else if(key.equals("trnewlog")) {
				printTransactionLogNewDetails((TransactionLogNewDetails)session.getAttribute(key));
			}*/
			else if(key.equals("trconfirmLog")) {
				printTransactionConfirmLogDetails((TransactionConfirmLogDetails)session.getAttribute(key));
			}
		}
	}
	
	public void printLoginLogDetails(LoginLogDetails loginLog)
			throws IOException, ServletException {

		System.out.println("LoginLogDetails: ");
		System.out.println("\tLogin id "+loginLog.getLoginid());
		System.out.println("\tCode  "+loginLog.getCode());
		System.out.println("\tUname "+loginLog.getUname());
		System.out.println("\tLogin Page time "+loginLog.getLoginpagetime());
		System.out.println("\tUtime "+loginLog.getUtime());
		System.out.println("\trctime "+loginLog.getRctime());
		System.out.println("\tSubmit time "+loginLog.getSubtime1());
		System.out.println("\tLogin Page2 time "+loginLog.getLoginpage2time());
		System.out.println("\tPictime "+loginLog.getPictime());
		System.out.println("\tPassstime "+loginLog.getPasstime());
		System.out.println("\tAttemptpage1 no. "+loginLog.getAttemptpage1());
		System.out.println("\tAttemptpage2 no. "+loginLog.getAttemptpage2());
		System.out.println("\tPicchosen "+loginLog.getPicchosen());
		System.out.println("\tEffectchosen "+loginLog.getEffectchosen());
		System.out.println("\tLanguagechosen "+loginLog.getLanguagechosen());
		System.out.println("\tSubmit time2 "+loginLog.getSubtime2());
		System.out.println("\tLogin time "+loginLog.getLogintime());
		System.out.println("\tLogout time "+loginLog.getLogouttime());
	}
	
	public void printBankDetails(BankDetails bank)
			throws IOException, ServletException {

		System.out.println("BankDetails: ");
		System.out.println("\tRegid "+bank.getRegid());
		System.out.println("\tUname "+bank.getUname());
		System.out.println("\tAccount no. "+bank.getAcc());
		System.out.println("\tFirst Name "+bank.getFname());
		System.out.println("\tLast Name  "+bank.getLname());
		System.out.println("\tPic id "+bank.getPicid());
		System.out.println("\tPassword "+bank.getPassword());
		System.out.println("\tEmail "+bank.getEmail());
		System.out.println("\tPhone "+bank.getPhone());
		System.out.println("\tAddress "+bank.getAddress());
		System.out.println("\tSecurity Ques "+bank.getSecurityquestion());
		System.out.println("\tSecurity ANS "+bank.getSecurityanswer());
		System.out.println("\tBalance "+bank.getBalance());
		System.out.println("\tFlag "+bank.getFlag());
		System.out.println("\tVerified "+bank.getVerified());
		System.out.println("\tDate created "+bank.getDatecreated());
		System.out.println("\tBank modified "+bank.getBankmodified());
		System.out.println("\tBank Verified "+bank.getBankverified());
		System.out.println("\tLast Login "+bank.getLastlogin());
		System.out.println("\tSuccess "+bank.getSuccess());
		System.out.println("\tMessage "+bank.getMessage());
	}
	
	public void printLoginAttemptDetails(LoginAttemptDetails loginAttempt)
			throws IOException, ServletException {

		System.out.println("LoginAttemptDetails: ");
		System.out.println("\tLoginid. "+loginAttempt.getLoginid());
		System.out.println("\tUname "+loginAttempt.getUname());
		System.out.println("\tDatepage1 "+loginAttempt.getDatepage1());
		System.out.println("\tAttemptpage1 "+loginAttempt.getAttemptpage1());
		System.out.println("\tDatepage2 "+loginAttempt.getDatepage2());
		System.out.println("\tAttemptpage2 "+loginAttempt.getAttemptpage2());
		System.out.println("\tSuccess "+loginAttempt.getSuccess());
		System.out.println("\tMessage "+loginAttempt.getMessage());
	}
	
	public void printEmailVerifyAttemptDetails(EmailVerifyAttemptDetails emailVerifyAttempt)
			throws IOException, ServletException {

		System.out.println("EmailVerifyAttemptDetails: ");
		System.out.println("\tRegid. "+emailVerifyAttempt.getRegid());
		System.out.println("\tUname "+emailVerifyAttempt.getUname());
		System.out.println("\tDate "+emailVerifyAttempt.getDate());
		System.out.println("\tAttempt "+emailVerifyAttempt.getAttempt());
	}
	
	public void printLoggedInLogDetails(ArrayList<LoggedInLogDetails> loggedInLogList)
			throws IOException, ServletException {
		System.out.println("LoggedInLogDetails: ");
		for(int i=1;i<=loggedInLogList.size();i++) {
			LoggedInLogDetails loggedInLog=(LoggedInLogDetails)loggedInLogList.get(i-1);
			System.out.print("\t"+i+". Loginid.="+loggedInLog.getLoginid());
			System.out.print("  Uname="+loggedInLog.getUname());
			System.out.print("  Date="+loggedInLog.getDate());
			System.out.println("  Action="+loggedInLog.getAction());
			System.out.println("  ; Message="+loggedInLog.getMessage());
		}
	}
	
	public void printLoggingInLogDetails(ArrayList<LoggingInLogDetails> loggingInLogList)
			throws IOException, ServletException {
		System.out.println("loggingInLogList: ");
		for(int i=1;i<=loggingInLogList.size();i++) {
			LoggingInLogDetails loggingInLog=(LoggingInLogDetails)loggingInLogList.get(i-1);
			System.out.print("\t"+i+". Loginid.="+loggingInLog.getLoginid());
			System.out.print("  Uname="+loggingInLog.getUname());
			System.out.print("    Date="+loggingInLog.getDate());
			System.out.println("    Action="+loggingInLog.getAction());
			System.out.println("  ; Message="+loggingInLog.getMessage());
		}
	}
	
	public void printRegisteringLogDetails(ArrayList<RegisteringLogDetails> regList)
			throws IOException, ServletException {
		System.out.println("registeringLogDetails: ");
		for(int i=0;i<regList.size();i++) {
			RegisteringLogDetails registeringLog=(RegisteringLogDetails)regList.get(i);
			System.out.print("\t"+i+". Regid="+registeringLog.getRegid());
			System.out.print("  ; Uname="+registeringLog.getUname());
			System.out.print("  ; Date="+registeringLog.getDate());
			System.out.println("  ; Action="+registeringLog.getAction());
			System.out.println("  ; Message="+registeringLog.getMessage());
		}
	}
	
	public void printRegistrationLogDetails(RegistrationAddLogDetails regLog)
			throws IOException, ServletException {

		System.out.println("RegistrationAddLogDetails: ");
		System.out.println("\tReg id "+regLog.getRegid());
		System.out.println("\tAccount no. "+regLog.getAcc());
		System.out.println("\tUname "+regLog.getUname());
		System.out.println("\tRegpage1time "+regLog.getRegpage1time());
		System.out.println("\tftime "+regLog.getFtime());
		System.out.println("\tltime "+regLog.getLtime());
		System.out.println("\tutime "+regLog.getUtime());
		System.out.println("\tAcctime "+regLog.getAcctime());
		System.out.println("\tphone time "+regLog.getPhonetime());
		System.out.println("\temail time "+regLog.getEmailtime());
		System.out.println("\taddress time "+regLog.getAddresstime());
		System.out.println("\tchk time "+regLog.getChktime());
		System.out.println("\treset time "+regLog.getResettime());
		System.out.println("\tsubmit time1 "+regLog.getSubtime1());
		System.out.println("\tRegpage2time "+regLog.getRegpage2time());
		System.out.println("\tpictime "+regLog.getPictime());
		System.out.println("\teffect time "+regLog.getEffecttime());
		System.out.println("\tPic id "+regLog.getPicid());
		System.out.println("\tEffect id "+regLog.getEffectid());
		System.out.println("\tsubmit time2 "+regLog.getSubtime2());
		System.out.println("\tAttempt "+regLog.getAttempt());
		System.out.println("\tSuccess "+regLog.getSuccess());
		System.out.println("\tMessage "+regLog.getMessage());
	}
	
	public void printTransactionDetails(TransactionDetails tr)
			throws IOException, ServletException {

		System.out.println("TransactionDetails: ");
		System.out.println("\tTrid : "+tr.getTrid());
		System.out.println("\tLoginid: "+tr.getLoginid());
		System.out.println("\tPayeracc. "+tr.getPayeracc());
		System.out.println("\tPayeeacc. "+tr.getPayeeacc());
		System.out.println("\tTransactiondate "+tr.getTransactiondate());
		System.out.println("\tType: "+tr.getType());
		System.out.println("\tAmount "+tr.getAmount());
		System.out.println("\tSuccess: "+tr.getSuccess());
		System.out.println("\tMessage: "+tr.getMessage());
	}
	
	public void printPhotoCountDetails(PhotoCountDetails pc)
			throws IOException, ServletException {

		System.out.println("PhotoCountDetails: ");
		System.out.println("\tList "+pc.getList());
		System.out.println("\tType "+pc.getType());
		System.out.println("\tDateaccessed  "+pc.getDateaccessed());
		System.out.println("\tId "+pc.getId());
	}
	
	public void printImageLogDetails(ImageLogDetails imglog)
			throws IOException, ServletException {

		System.out.println("ImageLogDetails: ");
		System.out.println("\tAcc "+imglog.getAcc());
		System.out.println("\tImage String "+imglog.getImagestring());
		System.out.println("\tDateaccessed  "+imglog.getDateaccessed());
		System.out.println("\tActionpage "+imglog.getActionpage());
		System.out.println("\tId "+imglog.getId());
	}
	
	public void printTransactionBenLogDetails(TransactionBenLogDetails trbenLog)
			throws IOException, ServletException {

		System.out.println("TransactionBenLogDetails: ");
		System.out.println("\tTrid: "+trbenLog.getTrid());
		System.out.println("\tFundtransferbenpagetime: "+trbenLog.getFundtransferbenpagetime());
		System.out.println("\tSubtime1: "+trbenLog.getSubtime1());
		System.out.println("\tSuccess: "+trbenLog.getSuccess());
		System.out.println("\tMessage: "+trbenLog.getMessage());
	}
	
	public void printTransactionConfirmLogDetails(TransactionConfirmLogDetails trconfirmLog)
			throws IOException, ServletException {

		System.out.println("TransactionConfirmLogDetails: ");
		System.out.println("\tTrid: "+trconfirmLog.getTrid());
		System.out.println("\tFundtransferpage2time: "+trconfirmLog.getFundtransferpage2time());
		System.out.println("\tAmounttime: "+trconfirmLog.getAmounttime());
		System.out.println("\tSubtime2: "+trconfirmLog.getSubtime2());
		System.out.println("\tConfirmpagetime: "+trconfirmLog.getConfirmpagetime());
		System.out.println("\tOTPTime: "+trconfirmLog.getOTPTime());
		System.out.println("\tOTPEmail: "+trconfirmLog.getOTPEmail());
		System.out.println("\tOTPUser: "+trconfirmLog.getOTPUser());
		System.out.println("\tSubtime3: "+trconfirmLog.getSubtime3());
		System.out.println("\tSuccess: "+trconfirmLog.getSuccess());
		System.out.println("\tMessage: "+trconfirmLog.getMessage());
	}
}