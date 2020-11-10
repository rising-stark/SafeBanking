package com.bank.model;

public class BeneficiaryDetails {
	private String loginid;
	private String payeracc;
	private String payername;
	private String benfname;
	private String benlname;
	private String benacc;
	private String benlimit;
	private String benbankname;
	private String benbranchname;
	private String benifsc;
	private String bendateadded;
	private String benapproved;
	private String benmodified;
	private String benlasttransaction;
	private String benlasttransactiondate;
	private int approved;
	private int success;
	private String message;
	
	public BeneficiaryDetails() {
		this.bendateadded="0";
		this.benapproved="0";
		this.benmodified="0";
		this.benlasttransaction="0";
		this.approved=0;
	}
	
	public String getPayeracc() {
		return payeracc;
	}
	public void setPayeracc(String payeracc) {
		this.payeracc = payeracc;
	}
	public String getPayername() {
		return payername;
	}
	public void setPayername(String payername) {
		this.payername = payername;
	}
	public String getBenacc() {
		return benacc;
	}
	public void setBenacc(String benacc) {
		this.benacc = benacc;
	}
	public String getBenlimit() {
		return benlimit;
	}
	public void setBenlimit(String benlimit) {
		this.benlimit = benlimit;
	}
	public String getBenbankname() {
		return benbankname;
	}
	public void setBenbankname(String benbankname) {
		this.benbankname = benbankname;
	}
	public String getBenbranchname() {
		return benbranchname;
	}
	public void setBenbranchname(String benbranchname) {
		this.benbranchname = benbranchname;
	}
	public String getBenifsc() {
		return benifsc;
	}
	public void setBenifsc(String benifsc) {
		this.benifsc = benifsc;
	}
	public String getBendateadded() {
		return bendateadded;
	}
	public void setBendateadded(String bendateadded) {
		this.bendateadded = bendateadded;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public String getBenfname() {
		return benfname;
	}
	public void setBenfname(String benfname) {
		this.benfname = benfname;
	}
	public String getBenlname() {
		return benlname;
	}
	public void setBenlname(String benlname) {
		this.benlname = benlname;
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
	public String getBenmodified() {
		return benmodified;
	}
	public void setBenmodified(String benmodified) {
		this.benmodified = benmodified;
	}
	public String getBenlasttransaction() {
		return benlasttransaction;
	}
	public void setBenlasttransaction(String benlasttransaction) {
		this.benlasttransaction = benlasttransaction;
	}
	public String getBenapproved() {
		return benapproved;
	}
	public void setBenapproved(String benapproved) {
		this.benapproved = benapproved;
	}

	public String getBenlasttransactiondate() {
		return benlasttransactiondate;
	}

	public void setBenlasttransactiondate(String benlasttransactiondate) {
		this.benlasttransactiondate = benlasttransactiondate;
	}
	
}
