package com.bank.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.model.LoggingInLogDetails;

public class AddLoggingInLogList {
	public static void add(String loginid, String uname, String action, String msg, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		GetTime obj = new GetTime();
		ArrayList<LoggingInLogDetails> loggingInLogList=(ArrayList<LoggingInLogDetails>)session.getAttribute("loggingInLogList");
		
		LoggingInLogDetails loggingInLog=new LoggingInLogDetails();
		loggingInLog.setLoginid(loginid);
		loggingInLog.setUname(uname);
		loggingInLog.setDate(obj.now());
		loggingInLog.setAction(action);
		loggingInLog.setMessage(msg);
		
		loggingInLogList.add(loggingInLog);
	}
	public static void addTime(String loginid, String uname, String time, String action, String msg, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		ArrayList<LoggingInLogDetails> loggingInLogList=(ArrayList<LoggingInLogDetails>)session.getAttribute("loggingInLogList");
		
		LoggingInLogDetails loggingInLog=new LoggingInLogDetails();
		loggingInLog.setLoginid(loginid);
		loggingInLog.setUname(uname);
		loggingInLog.setDate(time);
		loggingInLog.setAction(action);
		loggingInLog.setMessage(msg);
		
		loggingInLogList.add(loggingInLog);
	}
	public static void clear(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		ArrayList<LoggingInLogDetails> loggingInLogList=(ArrayList<LoggingInLogDetails>)session.getAttribute("loggingInLogList");
		loggingInLogList.clear();
	}
}