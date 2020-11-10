package com.bank.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.model.RegisteringLogDetails;

public class AddRegisteringLogList {
	public static void add(String regid, String uname, String action, String msg, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		GetTime obj = new GetTime();
		ArrayList<RegisteringLogDetails> registeringLogList=(ArrayList<RegisteringLogDetails>)session.getAttribute("registeringLogList");
		
		RegisteringLogDetails regLog=new RegisteringLogDetails();
		regLog.setRegid(regid);
		regLog.setUname(uname);
		regLog.setDate(obj.now());
		regLog.setAction(action);
		regLog.setMessage(msg);
		registeringLogList.add(regLog);
	}
	public static void clear(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		ArrayList<RegisteringLogDetails> registeringLogList=(ArrayList<RegisteringLogDetails>)session.getAttribute("registeringLogList");
		registeringLogList.clear();
	}
	public static void addTime(String regid, String uname, String time, String action, String msg, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		GetTime obj = new GetTime();
		ArrayList<RegisteringLogDetails> registeringLogList=(ArrayList<RegisteringLogDetails>)session.getAttribute("registeringLogList");
		
		RegisteringLogDetails regLog=new RegisteringLogDetails();
		regLog.setRegid(regid);
		regLog.setUname(uname);
		regLog.setDate(time);
		regLog.setAction(action);
		regLog.setMessage(msg);
		registeringLogList.add(regLog);
	}
}
