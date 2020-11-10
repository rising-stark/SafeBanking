package com.bank.model;

public class TransactionLogNewUserDetails {
	private String payeracc;
	private String payeeacc;
	private String payeename;
	private String transactiondate;
	private String transactionid;
	private String fundtransfernewuserpagetime;
	private String ftime;
	private String ltime;
	private String acctime;
	private String cnfacctime;
	private String banknametime;
	private String branchnametime;
	private String ifsctime;
	private String amounttime;
	private String chktime;
	private String resettime;
	private String subtime1;
	private int success;
	private String error;
	
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
	public String getPayeename() {
		return payeename;
	}
	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}
	public String getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(String transactiondate) {
		this.transactiondate = transactiondate;
	}
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public String getAmounttime() {
		return amounttime;
	}
	public void setAmounttime(String amounttime) {
		this.amounttime = amounttime;
	}
	public String getSubtime1() {
		return subtime1;
	}
	public void setSubtime1(String subtime1) {
		this.subtime1 = subtime1;
	}
	public String getFundtransfernewuserpagetime() {
		return fundtransfernewuserpagetime;
	}
	public void setFundtransfernewuserpagetime(String fundtransfernewuserpagetime) {
		this.fundtransfernewuserpagetime = fundtransfernewuserpagetime;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	public String getAcctime() {
		return acctime;
	}
	public void setAcctime(String acctime) {
		this.acctime = acctime;
	}
	public String getBanknametime() {
		return banknametime;
	}
	public void setBanknametime(String banknametime) {
		this.banknametime = banknametime;
	}
	public String getResettime() {
		return resettime;
	}
	public void setResettime(String resettime) {
		this.resettime = resettime;
	}
	public String getLtime() {
		return ltime;
	}
	public void setLtime(String ltime) {
		this.ltime = ltime;
	}
	public String getBranchnametime() {
		return branchnametime;
	}
	public void setBranchnametime(String branchnametime) {
		this.branchnametime = branchnametime;
	}
	public String getIfsctime() {
		return ifsctime;
	}
	public void setIfsctime(String ifsctime) {
		this.ifsctime = ifsctime;
	}
	public String getChktime() {
		return chktime;
	}
	public void setChktime(String chktime) {
		this.chktime = chktime;
	}
	public String getCnfacctime() {
		return cnfacctime;
	}
	public void setCnfacctime(String cnfacctime) {
		this.cnfacctime = cnfacctime;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
