package com.bank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.dao.bankDAO;
import com.bank.model.TransactionDetails;

@WebServlet(asyncSupported=true, urlPatterns = { "/test" })
public class Test extends HttpServlet {
     
	private static final long serialVersionUID = 1L;
    
    public Test() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
    	try {
    		HttpSession session = request.getSession(true);
    		System.out.println("loginimagestring ");
    		
    		/*int subject=8;
    		session.setAttribute("email", subject);
    		
    		System.out.println("loginimagestring "+(int)session.getAttribute("email"));
    		subject=6;
    		
    		System.out.println("loginimagestring "+(int)session.getAttribute("email"));*/
    		String atime = request.getParameter("atime").trim();
    		System.out.println("loginimagestring "+atime);
    		
    		/*session.setAttribute("email", email);
    		PrintWriter out = response.getWriter();
    		out.println ("<p>Some error in allocating account no.</p> "+atime);*/
    		
    		bankDAO u=new bankDAO();
    		List<TransactionDetails> tr = new ArrayList<TransactionDetails>();
    		tr=u.fetchTransactionDetailsList("99468853241201");

    		for (TransactionDetails x : tr) { 
    	        System.out.println(x.getAmount() + "   "+x.getTransactiondate()); 
    	    }
    		
    		Collections.sort(tr, new Sortbydate());
    		System.out.println("loginimagestring \n\n\n\n\n");
    		for (TransactionDetails x : tr) { 
    	        System.out.println(x.getAmount() + "   "+x.getTransactiondate()); 
    	    }
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	public static void main(String args[]) {
		/* second test to resolve updateAccountImageCount issue
		String s="4I5F3E4E0J2J3A5J";
		int i, len=s.length();
		int a=0,b=0;
		for(i=0;i<len;i++) {
			if(Character.isLowerCase(s.charAt(i))) {
				a++;
			}
			else if(Character.isUpperCase(s.charAt(i))){
				b++;
			}
		}
		System.out.println("A= "+a+" b= "+b);
		*/
		
		// Third test to resolve another issue of keeping effect of 3/6 and other 3/6 images same
		bankDAO u=new bankDAO();
		/*String regimagestring=u.encodeReg(122+"", 60, 5);
		ArrayList<String> list=u.decode(regimagestring, -1);
		String loginimagestring=u.encodeLogin("87705712881201", Integer.parseInt(list.get(0).substring(4)), list, 2, 5);
		/*u.decode(loginimagestring);
		String logineffects;
		logineffects=loginimagestring.substring(loginimagestring.lastIndexOf(',')+1);
    	loginimagestring=loginimagestring.substring(0, loginimagestring.lastIndexOf(','));
    	System.out.println("loginimagestring "+loginimagestring);
    	System.out.println("logineffects "+logineffects);
    	System.out.println("loginimagestring "+u.fixedHidden(loginimagestring));
    	String key[]= {"a","b","c","d","e","f","g","h","i","j"};
    	loginimagestring=u.getDistinctRandom(loginimagestring,logineffects, 6, 106, 5);
    	System.out.println("loginimagestring "+loginimagestring);
    	System.out.println("loginimagestring "+u.fixedHidden(loginimagestring));
    	loginimagestring=u.getDistinctRandom(u.fixedHidden(loginimagestring),logineffects, 6, 106, 5);
    	System.out.println("loginimagestring "+loginimagestring);
    	
		String loginimagestring=u.getDistinctRandom("5H2J0a0b0c0d0e0f9i5f1b2a,252255444325","252255444325", 10, 106, 5);
		System.out.println("loginimagestring "+loginimagestring);
		*/
		/*ArrayList<String> list=u.decode("5H0J0a0b0c0d0e0f9i5f1b2a,252255444325", 1);
		System.out.println("loginimagestring "+list.size());
		System.out.println("loginimagestring "+u.encode(list));
		Collections.shuffle(list);
		System.out.println("loginimagestring "+list);
		System.out.println("loginimagestring "+u.encode(list));*/
		
    	/*Fourth test of recording effect order as well
		bankDAO u=new bankDAO();
		String regimagestring=u.encodeReg(122+"", 100, 5);
		System.out.println("Encoded string "+regimagestring.substring(0,regimagestring.lastIndexOf(',')));
		System.out.println("Encoded string "+regimagestring.substring(regimagestring.lastIndexOf(',')+1));
		*/
		List<TransactionDetails> tr = new ArrayList<TransactionDetails>();
		tr=u.fetchTransactionDetailsList("99468853241201");

		for (TransactionDetails x : tr) { 
	        System.out.println(x.getAmount() + "   "+x.getTransactiondate()); 
	    }
		
		Collections.sort(tr, new Sortbydate());
		System.out.println("loginimagestring \n\n\n\n\n");
		for (TransactionDetails x : tr) { 
	        System.out.println(x.getAmount() + "   "+x.getTransactiondate()); 
	    }
	}
}