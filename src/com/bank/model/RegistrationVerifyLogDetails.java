package com.bank.model;

public class RegistrationVerifyLogDetails {
	private String regid;
	private String acc;
	private String uname;
	private String emailverifypagetime;
	private String passemail;
	private String passuser;
	private String verifytime;
	private String oldpasstime;
	private String proceedtime;
	private String newpasstime;//password
	private String cnfpasstime;//confirm password
	private String securityquestime;
	private String securityanstime;
	private String subtime;
	private int attempt;
	private int success;
	private String message;
	
	public RegistrationVerifyLogDetails() {
		this.oldpasstime="0";
		this.proceedtime="0";
		this.newpasstime="0";
		this.cnfpasstime="0";
		this.securityquestime="0";
		this.securityanstime="0";
		this.subtime="0";
	}
	
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public String getNewpasstime() {
		return newpasstime;
	}
	public void setNewpasstime(String newpasstime) {
		this.newpasstime = newpasstime;
	}
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getCnfpasstime() {
		return cnfpasstime;
	}
	public void setCnfpasstime(String cnfpasstime) {
		this.cnfpasstime = cnfpasstime;
	}
	public String getOldpasstime() {
		return oldpasstime;
	}
	public void setOldpasstime(String oldpasstime) {
		this.oldpasstime = oldpasstime;
	}
	public String getVerifytime() {
		return verifytime;
	}
	public void setVerifytime(String verifytime) {
		this.verifytime = verifytime;
	}
	public String getProceedtime() {
		return proceedtime;
	}
	public void setProceedtime(String proceedtime) {
		this.proceedtime = proceedtime;
	}
	public String getEmailverifypagetime() {
		return emailverifypagetime;
	}
	public void setEmailverifypagetime(String emailverifypagetime) {
		this.emailverifypagetime = emailverifypagetime;
	}
	public String getSecurityquestime() {
		return securityquestime;
	}
	public void setSecurityquestime(String securityquestime) {
		this.securityquestime = securityquestime;
	}
	public String getSecurityanstime() {
		return securityanstime;
	}
	public void setSecurityanstime(String securityanstime) {
		this.securityanstime = securityanstime;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
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
	public String getSubtime() {
		return subtime;
	}
	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}
	public String getPassemail() {
		return passemail;
	}
	public void setPassemail(String passemail) {
		this.passemail = passemail;
	}
	public String getPassuser() {
		return passuser;
	}
	public void setPassuser(String passuser) {
		this.passuser = passuser;
	}
}