package com.bank.model;

public class TransactionConfirmLogDetails {
	private String trid;
	private String fundtransferpage2time;
	private String amounttime;
	private String subtime2;
	private String confirmpagetime;
	private String OTPTime;
	private String OTPEmail;
	private String OTPUser;
	private String subtime3;
	private int success;
	private String message;
	
	public String getTrid() {
		return trid;
	}
	public void setTrid(String trid) {
		this.trid = trid;
	}
	public String getConfirmpagetime() {
		return confirmpagetime;
	}
	public void setConfirmpagetime(String confirmpagetime) {
		this.confirmpagetime = confirmpagetime;
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
	public String getSubtime3() {
		return subtime3;
	}
	public void setSubtime3(String subtime3) {
		this.subtime3 = subtime3;
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
	public String getFundtransferpage2time() {
		return fundtransferpage2time;
	}
	public void setFundtransferpage2time(String fundtransferpage2time) {
		this.fundtransferpage2time = fundtransferpage2time;
	}
	public String getSubtime2() {
		return subtime2;
	}
	public void setSubtime2(String subtime2) {
		this.subtime2 = subtime2;
	}
	public String getAmounttime() {
		return amounttime;
	}
	public void setAmounttime(String amounttime) {
		this.amounttime = amounttime;
	}
	public String getOTPTime() {
		return OTPTime;
	}
	public void setOTPTime(String oTPTime) {
		OTPTime = oTPTime;
	}
}