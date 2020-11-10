package com.bank.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.model.LoggedInLogDetails;

public class AddLoggedInLogList {
	public static void add(String loginid, String uname, String action, String msg, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		GetTime obj = new GetTime();
		ArrayList<LoggedInLogDetails> loggedInLogList=(ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList");
		
		LoggedInLogDetails loggedInLog=new LoggedInLogDetails();
		
		loggedInLog.setLoginid(loginid);
		loggedInLog.setUname(uname);
		loggedInLog.setDate(obj.now());
		loggedInLog.setAction(action);
		loggedInLog.setMessage(msg);
		
		loggedInLogList.add(loggedInLog);
	}
	
	public static void addTime(String loginid, String uname, String time, String action, String msg, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		ArrayList<LoggedInLogDetails> loggedInLogList=(ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList");
		
		LoggedInLogDetails loggedInLog=new LoggedInLogDetails();
		
		loggedInLog.setLoginid(loginid);
		loggedInLog.setUname(uname);
		loggedInLog.setDate(time);
		loggedInLog.setAction(action);
		loggedInLog.setMessage(msg);
		
		loggedInLogList.add(loggedInLog);
	}
	public static void clear(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		ArrayList<LoggedInLogDetails> loggedInLogList=(ArrayList<LoggedInLogDetails>)session.getAttribute("loggedInLogList");
		loggedInLogList.clear();
	}
}