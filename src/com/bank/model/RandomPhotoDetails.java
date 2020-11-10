package com.bank.model;

import java.io.InputStream;

public class RandomPhotoDetails {
	private int picid;
	private InputStream photo;
	
	public int getPicid() {
		return picid;
	}
	public void setPicid(int picid) {
		this.picid = picid;
	}
	public InputStream getPhoto() {
		return photo;
	}
	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}
}
