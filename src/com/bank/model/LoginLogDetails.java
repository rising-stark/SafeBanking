package com.bank.model;

public class LoginLogDetails {
	private String loginid;
	private String loginpagetime;
	private String uname;
	private String code;
	private String utime;
	private String rctime;
	private int attemptpage1;
	private String subtime1;
	private String securitypagetime;
	private String securityquestime;
	private String securityanstime;
	private String securityquestion;
	private String securityanswer;
	private String verifytime;
	private String loginpage2time;
	private String pictime;
	private String passtime;
	private int attemptpage2;
	private String picchosen;
	private String effectchosen;
	private String languagechosen;
	private String subtime2;
	private String logintime;
	private String logouttime;
	private int success;
	private String message;
	
	public LoginLogDetails(){
		this.attemptpage1=-1;
		this.securitypagetime="0";
		this.securityquestime="0";
		this.securityanstime="0";
		this.securityquestion="0";
		this.securityanswer="0";
		this.verifytime="0";
		this.loginpage2time="0";
		this.pictime="0";
		this.passtime="0";
		this.attemptpage2=-1;
		this.picchosen="0";
		this.effectchosen="-1";
		this.languagechosen="-1";
		this.subtime2="0";
		this.logintime="0";
		this.logouttime="0";
		this.success=0;
		this.message="NA";
		
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	public String getRctime() {
		return rctime;
	}
	public void setRctime(String rctime) {
		this.rctime = rctime;
	}
	public String getPasstime() {
		return passtime;
	}
	public void setPasstime(String passtime) {
		this.passtime = passtime;
	}
	public String getPictime() {
		return pictime;
	}
	public void setPictime(String pictime) {
		this.pictime = pictime;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getLogouttime() {
		return logouttime;
	}
	public void setLogouttime(String logouttime) {
		this.logouttime = logouttime;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getLoginpagetime() {
		return loginpagetime;
	}
	public void setLoginpagetime(String loginpagetime) {
		this.loginpagetime = loginpagetime;
	}
	public String getLoginpage2time() {
		return loginpage2time;
	}
	public void setLoginpage2time(String loginpage2time) {
		this.loginpage2time = loginpage2time;
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
	public String getPicchosen() {
		return picchosen;
	}
	public void setPicchosen(String picchosen) {
		this.picchosen = picchosen;
	}
	public String getEffectchosen() {
		return effectchosen;
	}
	public void setEffectchosen(String effectchosen) {
		this.effectchosen = effectchosen;
	}
	public String getLanguagechosen() {
		return languagechosen;
	}
	public void setLanguagechosen(String languagechosen) {
		this.languagechosen = languagechosen;
	}
	public String getSubtime2() {
		return subtime2;
	}
	public void setSubtime2(String subtime2) {
		this.subtime2 = subtime2;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSecuritypagetime() {
		return securitypagetime;
	}
	public void setSecuritypagetime(String securitypagetime) {
		this.securitypagetime = securitypagetime;
	}
	public String getSecurityanswer() {
		return securityanswer;
	}
	public void setSecurityanswer(String securityanswer) {
		this.securityanswer = securityanswer;
	}
	public String getVerifytime() {
		return verifytime;
	}
	public void setVerifytime(String verifytime) {
		this.verifytime = verifytime;
	}
	public String getSecurityquestime() {
		return securityquestime;
	}
	public void setSecurityquestime(String securityquestime) {
		this.securityquestime = securityquestime;
	}
	public String getSecurityquestion() {
		return securityquestion;
	}
	public void setSecurityquestion(String securityquestion) {
		this.securityquestion = securityquestion;
	}
	public String getSecurityanstime() {
		return securityanstime;
	}
	public void setSecurityanstime(String securityanstime) {
		this.securityanstime = securityanstime;
	}
}