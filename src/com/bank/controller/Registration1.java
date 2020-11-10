package com.bank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.model.RegistrationAddLogDetails;
import com.bank.dao.bankDAO;
import com.bank.model.BankDetails;
import com.bank.model.ImageLogDetails;
import com.bank.model.PhotoCountDetails;
import com.bank.model.RegisteringLogDetails;
 
@WebServlet(asyncSupported=true, urlPatterns = { "/Registration1" })
public class Registration1 extends HttpServlet {
     
	private static final long serialVersionUID = 1L;
	static Timer timer;
    
    public Registration1() {
        super();
    }

    private int validUname(String uname) {
    	/*This functions checks if the username entered follows constraints of 
    	 * 1 lowercase character
    	 * 1 uppercase character
    	 * and length between 8-15 characters.
    	 * 
    	 * */
    	
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
    }
    
    /*private int existVerifiedUname(String uname) {
		bankDAO u=new bankDAO();
		return (u.checkVerified(uname));
	}
    
    private int existUname(String uname) {
		bankDAO u=new bankDAO();
		return (u.checkUname(uname));
	}*/
    
	private int workingPhone(String phone) {
		return 1;
	}

	private int existPhone(String phone) {
		bankDAO u=new bankDAO();
		return u.alreadyExistPhone(phone);
	}

	private boolean validPhone(String phone) {
		{ 
		     // The given argument to compile() method  
		     // is regular expression. With the help of  
		     // regular expression we can validate mobile 
		     // number.  
		     // 1) Begins with 0 or 91 
		     // 2) Then contains 7 or 8 or 9. 
		     // 3) Then contains 9 digits 
		     Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 

		     // Pattern class contains matcher() method 
		     // to find matching between given number  
		     // and regular expression 
		     Matcher m = p.matcher(phone); 
		     return (m.find() && m.group().equals(phone)); 
		 }
	}

	private int existEmail(String email) {
		bankDAO u=new bankDAO();
		return u.alreadyExistEmail(email);
	}

	/*private boolean validEmail(String email) {
		return(EmailValidate.isAddressValid(email));
	}
	
	private void redirect(int countdown,String nextviewpage,HttpServletRequest request,
            HttpServletResponse response) {
		try {
		timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	        	timer.cancel();
	        	try {
	        		
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    }, countdown, 1000);}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
    
    @SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
    	HttpSession session = request.getSession(true);
    	try {
	    	String action=request.getParameter("action");
	    	
	    	if(action!=null && action.equals("validExistVerifiedUname")) {
	    		response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
		    	
				String uname = request.getParameter("username").trim();
				if(validUname(uname)==0) {
					response.getWriter().write("2");
				}else {
					response.getWriter().write("3");
				}
				/*else if(existUname(uname)==1) {
					if(existVerifiedUname(uname)==0) {
						response.getWriter().write("0");
						System.out.println("\n\nRedirecting");
						HttpSession session = request.getSession(true);
						bankDAO u=new bankDAO();
						BankDetails up=new BankDetails();
						String pass,email,acc;
						
						pass=u.generatePassword();
						email=u.retrieveEmail(uname);
						acc=u.retrieveAcc(uname);
						
						up.setAcc(acc);
						up.setPassword(pass);
						u.updatePassword(up);
						
						session.setAttribute("uname", uname);
						session.setAttribute("regLog", u.retrieveRegistrationLogDetails(uname));
						session.setAttribute("email", email);
						session.setAttribute("emailVerifyPage", "emailVerifyPage");
						session.setAttribute("fullname", u.retrieveName(uname));
						session.setAttribute("acc", acc);
						String subject="Verify Your Email id";
						String message="This email is valid only for the next 10 minutes."
								+ "\nPlease verify your email account by entering the below mentioned password in the registration wizard and then changing your password"
								+ "\nThe Account number allocated to you is "+acc+"."
								+ "\nPassword: "+pass+""
								+ "\nPlease change this password.\n\n";
						session.setAttribute("message", message);
						session.setAttribute("subject", subject);
						//redirect(2000,"emailVerify.jsp",request,response);
						return;
					}
					else
						response.getWriter().write("1");
				}
				else {
					response.getWriter().write("3");
				}*/
				return;
			}
			else if(action!=null && action.equals("validExistEmail")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
		    	
				//System.out.println("\n\nRegistration1.java starts in email ajax");
				String email = request.getParameter("emailId").trim();
				//System.out.println("Email is "+email);
				/*if(! validEmail(email)) {
					System.out.println("Working");
					response.getWriter().write("1");
				}
				else */if(existEmail(email)==1) {
					//System.out.println("Second also working");
					response.getWriter().write("0");
				}
				else {
					response.getWriter().write("2");
				}
				return;
			}
			else if(action!=null &&  action.equals("validExistWorkingPhone")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
		    	
				//System.out.println("\n\nRegistration1.java starts in Phone ajax");
				String phone = request.getParameter("phonenumber").trim();
				if(! validPhone(phone)) {
					response.getWriter().write("2");
				}
				else if(existPhone(phone)==1) {
					response.getWriter().write("0");
				}
				else if(workingPhone(phone)==0) {
					response.getWriter().write("1");
				}
				else {
					response.getWriter().write("3");
				}
				return;
			}
			else if(action!=null && action.equals("setRegpage1time")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
				System.out.println("Hello action");
				
				String regid=(String)session.getAttribute("regid");
				String time = request.getParameter("regpage1time").trim();
				
				AddRegisteringLogList.addTime(regid, "NA", time, "Registration1.jsp", "Successfully Loaded",request,response);
				return;
			}
			else if(action!=null && action.equals("resetButton")) {
				response.setContentType("text/plain");
		    	response.setCharacterEncoding("UTF-8");
				
				RegistrationAddLogDetails regLog=new RegistrationAddLogDetails();
				BankDetails bank =new BankDetails();
				bankDAO u=new bankDAO();
				
				String regid=(String)session.getAttribute("regid");
				String acc = request.getParameter("acc").trim();
				String uname = request.getParameter("uname").trim();
				String fname = request.getParameter("fname").trim();
				if(fname.length()>0) {
					fname = fname.substring(0, 1).toUpperCase()+fname.substring(1);
				}
				String lname = request.getParameter("lname").trim();
				if(lname.length()>0) {
					lname = lname.substring(0, 1).toUpperCase()+lname.substring(1);
				}
				String email = request.getParameter("email").trim();
				String phone = request.getParameter("phone").trim();
				String address = request.getParameter("address").trim();
				int success=0;
				String message="Reset Button Clicked";
				
				String regpage1time = request.getParameter("regpage1time").trim();
				String ftime = request.getParameter("ftime").trim();
				String ltime = request.getParameter("ltime").trim();
				String utime = request.getParameter("utime").trim();
				String acctime = request.getParameter("acctime").trim();
				String emailtime = request.getParameter("emailtime").trim();
				String phonetime = request.getParameter("phonetime").trim();
				String addresstime = request.getParameter("addresstime").trim();
				String chktime = request.getParameter("chktime").trim();
				String resettime = request.getParameter("resettime").trim();
				
				bank.setRegid(regid);
				bank.setUname(uname);
				bank.setAcc(acc);
				bank.setFname(fname);
				bank.setLname(lname);
				bank.setEmail(email);
				bank.setPhone(phone);
				bank.setAddress(address);
				bank.setDatecreated(resettime);
				bank.setSuccess(success);
				bank.setMessage(message);
				
				regLog.setRegid(regid);
				regLog.setAcc(acc);
				regLog.setUname(uname);
				regLog.setRegpage1time(regpage1time);
				regLog.setUtime(utime);
				regLog.setFtime(ftime);
				regLog.setLtime(ltime);
				regLog.setAcctime(acctime);
				regLog.setEmailtime(emailtime);
				regLog.setPhonetime(phonetime);
				regLog.setAddresstime(addresstime);
				regLog.setChktime(chktime);
				regLog.setResettime(resettime);
				regLog.setSubtime1("0");
				regLog.setSuccess(success);
				regLog.setMessage(message);
				
				//System.out.println("\n\nAJAX reset time "+request.getParameter("resettime").trim());
				AddRegisteringLogList.addTime(regid, uname, resettime, "Registration1.java", message,request,response);
				u.insertBankDetails(bank, 0);
				u.insertRegistrationAddLogDetails(regLog, 0);
				u.insertRegisteringLogDetails((ArrayList<RegisteringLogDetails>)session.getAttribute("registeringLogList"));
				AddRegisteringLogList.clear(request,response);
				return;
			}
	    	System.out.println("\n\nRegistration1.java starts in submit");
			
			RegistrationAddLogDetails regLog=new RegistrationAddLogDetails();
			BankDetails bank=new BankDetails();
			ShowAll show = new ShowAll();
			bankDAO u=new bankDAO();
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			String regid=(String)session.getAttribute("regid");
			String acc = request.getParameter("acc").trim();
			String uname = request.getParameter("uname").trim();
			String fname = request.getParameter("fname").trim();
			fname = fname.substring(0, 1).toUpperCase()+fname.substring(1);
			String lname = request.getParameter("lname").trim();
			lname = lname.substring(0, 1).toUpperCase()+lname.substring(1);
			String email = request.getParameter("email").trim();
			String phone = request.getParameter("phone").trim();
			String address = request.getParameter("address").trim();
			int success=0;
			String message="None";
			String nextviewpage="Registration1.jsp";
			
			String regpage1time = request.getParameter("regpage1time").trim();
			String ftime = request.getParameter("ftime").trim();
			String ltime = request.getParameter("ltime").trim();
			String utime = request.getParameter("utime").trim();
			String acctime = request.getParameter("acctime").trim();
			String emailtime = request.getParameter("emailtime").trim();
			String phonetime = request.getParameter("phonetime").trim();
			String addresstime = request.getParameter("addresstime").trim();
			String chktime = request.getParameter("chktime").trim();
			String subtime1 = request.getParameter("subtime1").trim();
			String resettime = request.getParameter("resettime").trim();
			
			AddRegisteringLogList.addTime(regid, uname, subtime1, "Registration1.java", "Processing Information",request,response);
			
			bank.setRegid(regid);
			bank.setUname(uname);
			bank.setAcc(acc);
			bank.setFname(fname);
			bank.setLname(lname);
			bank.setEmail(email);
			bank.setPhone(phone);
			bank.setAddress(address);
			bank.setDatecreated(subtime1);
			
			regLog.setRegid(regid);
			regLog.setAcc(acc);
			regLog.setUname(uname);
			regLog.setRegpage1time(regpage1time);
			regLog.setUtime(utime);
			regLog.setFtime(ftime);
			regLog.setLtime(ltime);
			regLog.setAcctime(acctime);
			regLog.setEmailtime(emailtime);
			regLog.setPhonetime(phonetime);
			regLog.setAddresstime(addresstime);
			regLog.setChktime(chktime);
			regLog.setResettime(resettime);
			regLog.setSubtime1(subtime1);
			
			/*Here, major attention is paid to accno instead of username to protect username
			 * from dictionary attacks or revealing of usernames.
			 * 
			 * */
			if(u.existAcc(acc)==false) {
				message="No user with Account no. "+acc+" exists in the bank.";
				
				session.setAttribute("notFound", message);
			} else {
				int verified=u.alreadyVerifiedAcc(acc);
				if(verified==1) { //This checks if the email is also verified.
					message="The Account no. "+acc+" is Already Registered for Net Banking. Redirecting to Login Page in 5s";
					
					session.setAttribute("already", message);
					session.setAttribute("LOGIN", "LOGIN");
					/*
					 * here add the logginginlog details object in session
					 * like the one is added using welcom.jsp
					 * also the login id and remove the regid, register and registeringlog list and reg log and all these objects
					 * print all session attributes here and make this after removal and addition of session
					 * attributes as if the login button is clicked from welcome.jsp page
					 * Proper information can be found by seeing the attributes set here, after login page.
					 * */
				}else if(verified==0) {
					message="The Account no. "+acc+" is not verified. Redirecting to Email Verify Page in 5s";
					String pass=u.generatePassword();
					
					BankDetails up=new BankDetails();
										
					up.setAcc(acc);
					up.setPassword(pass);
					up.setBankmodified(regid);
					up.setMessage(message);
					up.setRegid(regid);
					
					u.updatePassword(up);
					u.retrieveBankDetails(up);
					
					session.setAttribute("verify", up);
					/*
					 * here also set proper attributes and remove/retrieve information
					 * Proper information can be found by seeing the attributes set here, after emailverify page.
					 * */
				}else if(verified==-1) {
					verified=u.alreadyExistUname(uname);
					/*Here, both the cases of verified or unverified unames are considered as same 
					 * because major focus is on acc than revealing info about unames. 
					 * 
					 * */
					if(verified>-1) {
						message="The uname "+uname+" is already taken. Please choose a different one";
						
						session.setAttribute("notFound", message);
					}else {
						ImageLogDetails imgLog=new ImageLogDetails();
						PhotoCountDetails pc=new PhotoCountDetails();
						ArrayList<String> list = new ArrayList<String>();
						String regimagestring, effectorder;
						int last;
						
						success=1;
						message="Successful on first page";
						nextviewpage="Registration2.jsp";
				
						regimagestring=u.retrieveRegImageString(acc);
						last=regimagestring.lastIndexOf(',');
						list=u.decode(regimagestring.substring(0,last), -1);
						effectorder=regimagestring.substring(last+1);
						
						imgLog.setId(regid);
						imgLog.setAcc(acc);
						imgLog.setDateaccessed(subtime1);
						/* This Dateaccessed of imagelog table is set to be subtime1 instead of now
						 * because "now" is the time when this servlet runs but subtime1 is the time
						 * when the user clicks on the submit button on Registration1.jsp page.
						 * Also, this will help to maintain a track record of user activities. 
						 * As, the regLog table subtime1 and imagelog table dateaccessed will be same
						 * Also, the registeringLogList will contain an entry of this exact timestamp.
						 * So, it will be easy to backtrack which action was taken by which user and at what time.
						 * 
						 * Now, the thing that although the images are shown at registration2.jsp then 
						 * why is imagelog set here at registration1.java with subtime1=time-stamp because 
						 * if for some reason/intentionally the browser is stopped after this page's processing
						 * then it would seem that the user never saw the images but somehow the images were
						 * seen on the next page, so it is set here because for a normal person, next page 
						 * will be shown and is normal if no-one hinders the normal flow of this web-app. 
						 * */
						
						imgLog.setActionpage("Registration1.jsp submitted. Registration2.jsp ready to show base images");
						imgLog.setImagestring(regimagestring);
						
						pc.setId(regid);
						pc.setList(list);
						pc.setDateaccessed(subtime1);
						pc.setType("reg");
						
						session.setAttribute("list",list);
						session.setAttribute("effectorder",effectorder);
						session.setAttribute("bank", bank);
						session.setAttribute("regLog", regLog);
						
						u.updateAccountImageCount(acc, regimagestring, subtime1, "reg");
						u.insertImageLog(imgLog);
						u.insertPhotoCountDetails(pc);
					}
				}
			}
			regLog.setSuccess(success);
			regLog.setMessage(message);
			bank.setSuccess(success);
			bank.setMessage(message);
			AddRegisteringLogList.add(regid, uname, "Registration1.java", message,request,response);
			
			u.insertBankDetails(bank, 0);
			u.insertRegistrationAddLogDetails(regLog, 0);
			u.insertRegisteringLogDetails((ArrayList<RegisteringLogDetails>)session.getAttribute("registeringLogList"));
			AddRegisteringLogList.clear(request,response);
			
			System.out.println("\nSession attributes set and Registration1.java will end after showing all\n\n");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			response.sendRedirect(nextviewpage);
			/*RequestDispatcher rd=request.getRequestDispatcher(nextviewpage);
			rd.forward(request,response);*/
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
		/*Countdown.emailVerifyCountdown(10+"");
		System.out.println("\nCountdown\n\n");
		String nextviewpage="registration1.jsp";
		response.sendRedirect(nextviewpage);*/
	}
}