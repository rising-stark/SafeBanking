package com.bank.model;

public class PicPreferenceDetails {
	private String uname;
	private int effectid;
	private int language;
	private int scaleX;
	private int scaleY;
	private int rotation;
	private int opacity;
	private int skewX;
	private int skewY;
	public int getEffectid() {
		return effectid;
	}
	public void setCurve(int curve) {
		this.effectid = curve;
	}
	public int getOpacity() {
		return opacity;
	}
	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public int getSkewX() {
		return skewX;
	}
	public void setSkewX(int skewX) {
		this.skewX = skewX;
	}
	public int getSkewY() {
		return skewY;
	}
	public void setSkewY(int skewY) {
		this.skewY = skewY;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getScaleY() {
		return scaleY;
	}
	public void setScaleY(int scaleY) {
		this.scaleY = scaleY;
	}
	public int getScaleX() {
		return scaleX;
	}
	public void setScaleX(int scaleX) {
		this.scaleX = scaleX;
	}
	public int getLanguage() {
		return language;
	}
	public void setLanguage(int language) {
		this.language = language;
	}
	
}
