package com.bank.model;

public class TransactionDetails {
	private String trid;
	private String loginid;
	private String payeracc;
	private String payeeacc;
	private String payername;
	private String payeename;
	private String transactiondate;
	private String type;
	private int amount;
	private int remBalance;
	private int success;
	private String message;
	/*
	 * These 3 are not inserted in database and are only for
	 * displaying the confirm information on emailfundtransfer page.
	 * */
	private String benifsc;
	private String benbank;
	private String benbranch;
	
	public TransactionDetails(){
		this.amount=0;
		this.remBalance=0;
	}
	
	public String getPayeracc() {
		return payeracc;
	}
	public void setPayeracc(String payeracc) {
		this.payeracc = payeracc;
	}
	public String getPayeeacc() {
		return payeeacc;
	}
	public void setPayeeacc(String payeeacc) {
		this.payeeacc = payeeacc;
	}
	public String getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(String transactiondate) {
		this.transactiondate = transactiondate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int i) {
		this.amount = i;
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
	public String getTrid() {
		return trid;
	}
	public void setTrid(String trid) {
		this.trid = trid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPayername() {
		return payername;
	}

	public void setPayername(String payername) {
		this.payername = payername;
	}

	public String getPayeename() {
		return payeename;
	}

	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}

	public int getRemBalance() {
		return remBalance;
	}

	public void setRemBalance(int remBalance) {
		this.remBalance = remBalance;
	}

	public String getBenifsc() {
		return benifsc;
	}

	public void setBenifsc(String benifsc) {
		this.benifsc = benifsc;
	}

	public String getBenbranch() {
		return benbranch;
	}

	public void setBenbranch(String benbranch) {
		this.benbranch = benbranch;
	}

	public String getBenbank() {
		return benbank;
	}

	public void setBenbank(String benbank) {
		this.benbank = benbank;
	}	
}