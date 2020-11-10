package com.bank.model;

public class BeneficiaryDeleteLogDetails {
	private String loginid;
	private String payeracc;
	private String benacc;
	private String bendeletepagetime;
	private String subtime1;
	private String benemaildeletepagetime;
	private String otptime;
	private String OTPUser;
	private String OTPEmail;
	private String confirmtime;
	private String subtime2;
	private String bendeletetime;
	private int attempt;
	private int success;
	private String message;
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getPayeracc() {
		return payeracc;
	}
	public void setPayeracc(String payeracc) {
		this.payeracc = payeracc;
	}
	public String getBenacc() {
		return benacc;
	}
	public void setBenacc(String benacc) {
		this.benacc = benacc;
	}
	public String getSubtime1() {
		return subtime1;
	}
	public void setSubtime1(String subtime1) {
		this.subtime1 = subtime1;
	}
	public String getOtptime() {
		return otptime;
	}
	public void setOtptime(String otptime) {
		this.otptime = otptime;
	}
	public String getSubtime2() {
		return subtime2;
	}
	public void setSubtime2(String subtime2) {
		this.subtime2 = subtime2;
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
	public String getOTPUser() {
		return OTPUser;
	}
	public void setOTPUser(String oTPUser) {
		OTPUser = oTPUser;
	}
	public String getOTPEmail() {
		return OTPEmail;
	}
	public void setOTPEmail(String oTPEmail) {
		OTPEmail = oTPEmail;
	}
	public String getBendeletepagetime() {
		return bendeletepagetime;
	}
	public void setBendeletepagetime(String bendeletepagetime) {
		this.bendeletepagetime = bendeletepagetime;
	}
	public String getBenemaildeletepagetime() {
		return benemaildeletepagetime;
	}
	public void setBenemaildeletepagetime(String benemaildeletepagetime) {
		this.benemaildeletepagetime = benemaildeletepagetime;
	}
	public String getConfirmtime() {
		return confirmtime;
	}
	public void setConfirmtime(String confirmtime) {
		this.confirmtime = confirmtime;
	}
	public String getBendeletetime() {
		return bendeletetime;
	}
	public void setBendeletetime(String bendeletetime) {
		this.bendeletetime = bendeletetime;
	}
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
}
