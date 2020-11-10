package com.bank.additional;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dao.bankDAO;

import processing.data.JSONObject;

@WebServlet("/Run")
public class Run extends HttpServlet{
	/*public static void main(String[] args){
		bankDAO u=new bankDAO();
		//u.encode();
		//u.encodeRandomLogin("", hide, now)
		//u.encodeReg("54", 60);
		//ArrayList<String> list1=u.decode("7A5B0e9i9I");
		//u.encodeLogin("XXXXXXXXXX1201", Integer.parseInt(list1.get(0).substring(4)), list1);
		//u.decode("5h2C0J0J4C4B3C0E");
		//new File("C:\\Users\\ujjwa\\Desktop\\risingStark").mkdirs();
		//u.compareDates( "22-07-2019 12:01:07:679","22-07-2019 12:40:58:816");
		//u.getDistinctRandom("7A4C2b7c3i1a8d9e2h2j4b4c", 10, 105);
	}*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        JSONObject obj = new JSONObject();

        obj.put("name", "foo");
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));
        response.getWriter().write(obj.toString());
        System.out.print(obj);
	}
}