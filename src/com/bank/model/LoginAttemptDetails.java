package com.bank.model;

public class LoginAttemptDetails {
	private String loginid;
	private String uname;
	private String datepage1;
	private String datepage2;
	private int attemptpage1;
	private int attemptpage2;
	private int success;
	private String message;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getDatepage1() {
		return datepage1;
	}
	public void setDatepage1(String datepage1) {
		this.datepage1 = datepage1;
	}
	public String getDatepage2() {
		return datepage2;
	}
	public void setDatepage2(String datepage2) {
		this.datepage2 = datepage2;
	}
	public int getAttemptpage1() {
		return attemptpage1;
	}
	public void setAttemptpage1(int attemptpage1) {
		this.attemptpage1 = attemptpage1;
	}
	public int getAttemptpage2() {
		return attemptpage2;
	}
	public void setAttemptpage2(int attemptpage2) {
		this.attemptpage2 = attemptpage2;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
