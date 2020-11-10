package com.bank.model;

public class TransactionBenLogDetails {
	private String trid;
	private String fundtransferbenpagetime;
	private String subtime1;
	private int success;
	private String message;
	
	public String getFundtransferbenpagetime() {
		return fundtransferbenpagetime;
	}
	public void setFundtransferbenpagetime(String fundtransferbenpagetime) {
		this.fundtransferbenpagetime = fundtransferbenpagetime;
	}
	
	public String getSubtime1() {
		return subtime1;
	}
	public void setSubtime1(String subtime1) {
		this.subtime1 = subtime1;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getTrid() {
		return trid;
	}
	public void setTrid(String trid) {
		this.trid = trid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
