package com.bank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.bankDAO;
import com.bank.model.BankDetails;
import com.bank.model.ImageLogDetails;
import com.bank.model.LoggingInLogDetails;
import com.bank.model.LoginAttemptDetails;
import com.bank.model.LoginLogDetails;
import com.bank.model.PhotoCountDetails;
import com.bank.model.PicPreferenceDetails;

@WebServlet(asyncSupported=true, urlPatterns = { "/Login" })
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int hide = 106;
	private static final int effects=5;

	public Login() {
		super();
	}
	
	/*private int validUname(String uname) {
    	int upper,lower,i,len,valid;
		upper=lower=valid=0;
		len=uname.length();
		if(len<8) 
			valid=0;
		else {
			for(i=0;i<len;i++) {
				char ch=uname.charAt(i);
				if(ch<=90 && ch>=65)
					upper=1;
				else if(ch<=122 && ch>=97)
					lower=1;
			}
			if(upper==1 && lower==1)
				valid=1;
		}
		return valid;
    }*/
    
	/*private int existUname(String uname) {
		bankDAO u=new bankDAO();
		return (u.checkUname(uname));
	}
	
    private int existVerifiedUname(String uname) {
		bankDAO u=new bankDAO();
		return (u.checkVerified(uname));
	}
    
    private int validCode(String code) {
    	if(code.length()!=6) 
			return 0;
    	return 1;
    }*/
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession(true);
	    	
	    	String action=request.getParameter("action");
	    	
	    	if(action!=null && action.equals("verifySecurity")) {
	    		/*
	    		 * This section is activated when a user after attempting Q&A, clicks on submit button.
	    		 * For now, I have infinite attempts at security answer(no matter the answer and the question chosen are correct or wrong).
	    		 * Because security answers can be many and one user can try many answers get it right after 2-3 attempts.
	    		 * If we want to have a wrong security Q&A to be counted as a wrong attempt,
	    		 * we just need to set attemptpage1 before inserting into database and check for blocking when 2nd incorrect answer.
	    		 * */
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
		    	bankDAO u=new bankDAO();
		    	LoginLogDetails loginLog=(LoginLogDetails)session.getAttribute("loginLog");
				LoginAttemptDetails loginAttempt=(LoginAttemptDetails)session.getAttribute("loginAttempt");

				String securitypagetime = request.getParameter("securitypagetime").trim();
				String securityquestion = request.getParameter("securityquestion").trim();
				String securityanswer = request.getParameter("securityanswer").trim();
				String securityquestime = request.getParameter("securityquestime").trim();
				String securityanstime = request.getParameter("securityanstime").trim();
				String verifytime = request.getParameter("verifytime").trim();
				String ucode=(String)session.getAttribute("ucode");
				String uname=ucode.substring(0, ucode.length()-6);
				String loginid=(String)session.getAttribute("loginid");
				int success=1;
				String message="Processing Security Wizard";
				/*
				 * Must be uncommented if we start counting incorrect security Q&A in attemptpage1. 
				 * int attemptpage1= loginAttempt.getAttemptpage1();
				attemptpage1--;*/
				System.out.println("Result: "+uname);
				System.out.println("Result: "+securityquestion);
				System.out.println("Result: "+securityanswer);
				loginLog.setSecuritypagetime(securitypagetime);
				loginLog.setSecurityquestion(securityquestion);
				loginLog.setSecurityanswer(securityanswer);
				loginLog.setSecurityquestime(securityquestime);
				loginLog.setSecurityanstime(securityanstime);
				loginLog.setVerifytime(verifytime);
				
		  		if(u.verifySecurity(uname, securityquestion, securityanswer)==1) {
		  			response.getWriter().write("1");
	    			message+=" Correct Answer";
	    			
	    			String loginimagestring;
					int language;
					ImageLogDetails imgLog=new ImageLogDetails();
					PhotoCountDetails pc=new PhotoCountDetails();
					PicPreferenceDetails pp= new PicPreferenceDetails();
					ArrayList<String> list = new ArrayList<String>();
					
					String acc=u.retrieveAcc(uname);
					loginimagestring=u.encodeRandomLogin(acc, hide, verifytime, effects);
					/* Here the parameter hide denotes the current no. of hidden 
					images in hide folder out of which 4 exclusive images will be shown during login.*/
					
					pp=u.retrievePicPreferenceDetails(uname);
					language=pp.getLanguage();
					list=u.decode(loginimagestring, language);
					Collections.shuffle(list); 
					AddLoggingInLogList.addTime(loginid, uname, verifytime, "Login.java", "List Shuffled to "+u.encode(list)+" lang= "+language,request,response);
					/*Now here, there could be all sorts of customised shuffling based on any suspicion factor
					 * or based on which position to show which image and after what.
					 * For now, I have kept it completely randomized by using method shuffle.
					 * 
					 * */
					
					imgLog.setId(loginid);
					imgLog.setAcc(acc);
					imgLog.setActionpage("Login.java Security Wizard. Ready to show 12 login images");
					imgLog.setDateaccessed(verifytime);
					imgLog.setImagestring(loginimagestring);
					
					pc.setList(list);
					pc.setType("login");
					pc.setDateaccessed(verifytime);
					pc.setId(loginid);
					
					/*Here, the updateAccountImageCount function does not take care of first login,
					 * that's why, first login updation of imagecount is incorporated in 
					 * encodeRandomLogin in bankDAO function
					 * 
					 * */
					
					u.insertImageLog(imgLog);
					u.insertPhotoCountDetails(pc);
					u.updateAccountLoginImageString(acc, loginimagestring, verifytime);
					u.updateAccountImageCount(acc, loginimagestring, verifytime, "login");
					
					session.setAttribute("list", list);
					session.setAttribute("refresh", 0);
				}
				else {
					response.getWriter().write("0");
					message+=" Incorrect Answer";
					/*The blocking a user code will be here, if we count a wrong security Q&A as attemptpage1.
					 * For now, we are giving infinite attempts at security wizard.
					 * if(attemptpage1==-1) {
						u.insertBlockUser(uname);
					}*/
				}
		  		
		  		loginLog.setSuccess(success);
		  		loginLog.setMessage(message);

				loginAttempt.setSuccess(success);
				loginAttempt.setMessage(message);
				
		  		AddLoggingInLogList.add(loginid, uname, "Login.java", message, request,response);
		  		
		  		u.updateBankLastLogin(loginid, uname, message);
		  		u.insertLoginAttemptDetails(loginAttempt, success);
		  		u.insertLoginLogDetails(loginLog, 1);
				u.insertLoggingInLogDetails((ArrayList<LoggingInLogDetails>)session.getAttribute("loggingInLogList"));
				AddLoggingInLogList.clear(request,response);
				return;
	    	}
	    	/*else if(action!=null && action.equals("verifiedUname")) {
	    		String uname = request.getParameter("username").trim();
	    		if(existUname(uname)==1) {
					if(existVerifiedUname(uname)==0) {
						response.getWriter().write("0");
					}
					else
						response.getWriter().write("1");
				}
				else {
					response.getWriter().write("1");
				}
				return;
	    	}*/
	    	else if(action!=null && action.equals("setSecuritypagetime")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
		    	System.out.println("\n\nLogin.java here setting the securitypagetime\n");
				String time = request.getParameter("securitypagetime").trim();
				String loginid=(String)session.getAttribute("loginid");
				String uname=((LoginLogDetails)session.getAttribute("loginLog")).getUname();
				
				AddLoggingInLogList.addTime(loginid, uname, time, "Login.jsp", "Security Wizard Successfully Loaded",request,response);
				AddLoggingInLogList.clear(request,response);
				
				return;
			}
	    	else if(action!=null && action.equals("setLoginpagetime")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
		    	System.out.println("\n\nLogin.java here setting the loginpagetime\n");
				String time = request.getParameter("loginpagetime").trim();
				String loginid=(String)session.getAttribute("loginid");
				
				AddLoggingInLogList.addTime(loginid, "NA", time, "Login.jsp", "Successfully Loaded",request,response);
				AddLoggingInLogList.clear(request,response);
				
				return;
			}
	
			System.out.println("\n\nLogin.java starts in NEXT\n");
			ShowAll show = new ShowAll();
			bankDAO u = new bankDAO();
			
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			String loginid=(String)session.getAttribute("loginid");
			String uname = request.getParameter("uname");
			String nextviewpage="Login.jsp";
			
			if(uname!=null) {
				String code = request.getParameter("code");
				String loginpagetime = request.getParameter("loginpagetime");
				String utime = request.getParameter("utime");
				String rctime = request.getParameter("rctime");
				int attemptpage1=-1;
				String subtime1 = request.getParameter("subtime1");
				int successLoginAttempt=-1; 
				/*
				 *  In loginattempt table, success column's meaning and significance is different from all other tables.
					It denotes real or fake user logins or unverified users trying to login before email verification.
						a) successLoginAttempt = -1 ==> fake user
						b) successLoginAttempt = 0 ==> registered unverified user
						c) successLoginAttempt = 1 ==> real registered verified user
					case b) means a user tried login without verifying his email id no matter he verifies email after wards and 
					then logins.
				*/
				int success=0; //This success is for loginlog table and behaves like other tables.
				String message="none";
				
				AddLoggingInLogList.addTime(loginid, uname, subtime1, "Login.java", "Processing Information",request,response);
				LoginLogDetails loginLog=new LoginLogDetails();
				LoginAttemptDetails loginAttempt=new LoginAttemptDetails();
				
				loginLog.setLoginid(loginid);
				loginLog.setLoginpagetime(loginpagetime);
				loginLog.setUname(uname);
				loginLog.setCode(code);
				loginLog.setUtime(utime);
				loginLog.setRctime(rctime);
				loginLog.setAttemptpage1(attemptpage1);
				loginLog.setSubtime1(subtime1);
				
				loginAttempt.setUname(uname);
				loginAttempt.setLoginid(loginid);
				loginAttempt.setDatepage1(subtime1);
				loginAttempt.setAttemptpage1(attemptpage1);
				
				int verified=u.alreadyExistUname(uname);
				if(verified==-1) {
					message="NO ACCOUNT DETAILS FOUND FOR THE USERNAME "+uname;
					session.setAttribute("notFound", message);
				}else {
					success=1;
					if(verified==0) {
						successLoginAttempt=0;
						message="The username "+uname+" is not verified. Redirecting to Email Verify Page";
						
						String pass=u.generatePassword();
						String acc=u.retrieveAcc(uname);
						BankDetails up=new BankDetails();

						up.setAcc(acc);
						up.setPassword(pass);
						up.setBankmodified(loginid);
						up.setMessage(message);
						up.setRegid(loginid);
						
						u.updatePassword(up);
						u.retrieveBankDetails(up);
						
						session.setAttribute("verify", up);
						nextviewpage ="EmailVerify.jsp";
					}else {
						successLoginAttempt=1;
						String acc=u.retrieveAcc(uname);
						String flag = u.checkBlockedAccount(acc);
						if(flag.equals("false")) {
							message="ACCOUNT FOR THE USERNAME "+uname+" IS BLOCKED BECAUSE YOU EXCEEDED THE LOGIN ATTEMPTS.";
							session.setAttribute("notFound", message);
						}else {
							attemptpage1=u.retrieveViewAttempt(uname);
							System.out.println("# of attempts of page1 retrieved -->>" + attemptpage1 + "\n");
							//Here 5 is chosen to be the number of attempts while entering uname in login page1.
							attemptpage1--;
							/*
							for table loginattempt.
							It indicates no. of attemts left. if attemptpage1=5 then it means the user logged in first time. similarly, if attemptpage1=-1 the user could not login within 5 chances;
							datepage1 & 2 here represent when the loginpage1 & 2 gets submitted(no matter wrong or right).
							To get complete loginlog detatils this table can be joined with loginlog using uname, loginid and datepage1 and loginpagetime as FOREIGNKEY.
							Now, this table was initially designed to contain the loginattempts of only the verified users but now due
							to bank table becoming the one to contain the information of even all wrong user attempts as well,
							the uname column in bank table could not be primary key and thus no foreign key column in loginattempt table. So, the question now is that whether this table should contain the data of those users which are not verified/registered by the bank. If yes, then real users will be the records with success=1 and others with success=0; This might help to even show information of a phisher during a dictionary attack.
							There will only be one unique record with a given username and success=1 but can be many with unregistered unames and success=0.
							That's why success=1 even without checking attempts no matter attemptpage1>0 or not.
							*/
							if(attemptpage1>1) {
								message="able to pass Login page1 with attemptpage1left= "+attemptpage1;
								
								String loginimagestring;
								int language;
								ImageLogDetails imgLog=new ImageLogDetails();
								PhotoCountDetails pc=new PhotoCountDetails();
								PicPreferenceDetails pp= new PicPreferenceDetails();
								ArrayList<String> list = new ArrayList<String>();
								
								loginimagestring=u.encodeRandomLogin(acc, hide, subtime1, effects);
								/* Here the parameter hide denotes the current no. of hidden 
								images in hide folder out of which 4 exclusive images will be shown during login.*/
								
								pp=u.retrievePicPreferenceDetails(uname);
								language=pp.getLanguage();
								list=u.decode(loginimagestring, language);
								Collections.shuffle(list);
								AddLoggingInLogList.addTime(loginid, uname, subtime1, "Login.java", "List Shuffled to "+u.encode(list)+" lang= "+language,request,response);
								/*Now here, there could be all sorts of customised shuffling based on any suspicion factor
								 * or based on which position to show which image and after what.
								 * For now, I have kept it completely randomized by using method shuffle.
								 * 
								 * */
								
								imgLog.setId(loginid);
								imgLog.setAcc(acc);
								imgLog.setActionpage("Login.java after submitting. Ready to show 12 login images");
								imgLog.setDateaccessed(subtime1);
								imgLog.setImagestring(loginimagestring);
								
								pc.setList(list);
								pc.setType("login");
								pc.setDateaccessed(subtime1);
								pc.setId(loginid);
								
								/*Here, the updateAccountImageCount function does not take care of first login,
								 * that's why, first login updation of imagecount is incorporated in 
								 * encodeRandomLogin in bankDAO function
								 * 
								 * */
								
								u.insertImageLog(imgLog);
								u.insertPhotoCountDetails(pc);
								u.updateAccountLoginImageString(acc, loginimagestring, subtime1);
								u.updateAccountImageCount(acc, loginimagestring, subtime1, "login");
								
								session.setAttribute("list", list);
								session.setAttribute("refresh", 0);
								nextviewpage ="Login2.jsp";
							}
							else if(attemptpage1>=0) {
								message="only "+attemptpage1+" attempts left. Security Wizard shown";
								session.setAttribute("ucode", uname+code);
								session.setAttribute("securityWizard", "security wizard");
							}else if(attemptpage1==-1) {
								u.insertBlockUser(uname);
								message="ACCOUNT FOR THE USERNAME "+uname+" IS BLOCKED BECAUSE YOU EXCEEDED THE LOGIN ATTEMPTS.";
								session.setAttribute("notFound", message);
							}
							loginAttempt.setAttemptpage1(attemptpage1);
							loginLog.setAttemptpage1(attemptpage1);
							session.setAttribute("loginAttempt", loginAttempt);
							session.setAttribute("loginLog", loginLog);
						}
					}
					u.updateBankLastLogin(loginid, uname, message);
				}
				
				loginAttempt.setAttemptpage1(attemptpage1);
				loginAttempt.setSuccess(successLoginAttempt);
				loginAttempt.setMessage(message);

				loginLog.setAttemptpage1(attemptpage1);
				loginLog.setSuccess(success);
				loginLog.setMessage(message);
				AddLoggingInLogList.add(loginid, uname, "Login.java", message, request, response);
				
				u.insertLoginAttemptDetails(loginAttempt, success);// Here success is taken a substitute for type
				u.insertLoginLogDetails(loginLog, 0);
				u.insertLoggingInLogDetails((ArrayList<LoggingInLogDetails>)session.getAttribute("loggingInLogList"));
				AddLoggingInLogList.clear(request,response);
			}
			else{
				System.out.println("Login URL entered in browser by loginid "+loginid);
				AddLoggingInLogList.add(loginid, "NA", "Login.java", "Login URL entered in browser",request,response);
				u.insertLoggingInLogDetails((ArrayList<LoggingInLogDetails>)session.getAttribute("loggingInLogList"));
				AddLoggingInLogList.clear(request,response);
				
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			System.out.println("Login.java ends after showing all\n");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			System.out.println("This is Login.java ends\n");
			
			response.sendRedirect(nextviewpage);
			}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}