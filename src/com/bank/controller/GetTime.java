package com.bank.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class GetTime {
	public String now() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
		ZonedDateTime nowInIndia = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		String time = dtf.format(nowInIndia);
		//System.out.println("using local date/time "+time);
		return time;
	}
}