package com.bank.model;

public class AccountDetails {
	private String acc;
	private int savings;
	private String regimagestring;
	private String loginimagestring;
	private int countshow;
	private int counthide;
	private String datecreated;
	private String datemodified;
	
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public int getSavings() {
		return savings;
	}
	public void setSavings(int savings) {
		this.savings = savings;
	}
	public String getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}
	public String getDatemodified() {
		return datemodified;
	}
	public void setDatemodified(String datemodified) {
		this.datemodified = datemodified;
	}
	public String getRegimagestring() {
		return regimagestring;
	}
	public void setRegimagestring(String regimagestring) {
		this.regimagestring = regimagestring;
	}
	public String getLoginimagestring() {
		return loginimagestring;
	}
	public void setLoginimagestring(String loginimagestring) {
		this.loginimagestring = loginimagestring;
	}
	public int getCountshow() {
		return countshow;
	}
	public void setCountshow(int countshow) {
		this.countshow = countshow;
	}
	public int getCounthide() {
		return counthide;
	}
	public void setCounthide(int counthide) {
		this.counthide = counthide;
	}
	
}
