package com.bank.model;

import java.util.ArrayList;

public class PhotoCountDetails {
	private ArrayList<String> list;
	private String type; // type=1 for reg, type=2 for login
	private String dateaccessed;
	private String id;
	
	public ArrayList<String> getList() {
		return list;
	}
	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDateaccessed() {
		return dateaccessed;
	}
	public void setDateaccessed(String dateaccessed) {
		this.dateaccessed = dateaccessed;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
