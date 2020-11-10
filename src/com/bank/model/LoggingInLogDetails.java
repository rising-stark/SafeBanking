package com.bank.model;

public class LoggingInLogDetails {
	private String uname;
	private String date;
	private String loginid;
	private String action;
	private String message;
	
	/*public LoggedInLogDetails(String uname,String date,String eventid,String action) {
		this.setAction(action);
		this.setUname(uname);
		this.setEventid(eventid);
		this.setDate(date);
	}*/
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}