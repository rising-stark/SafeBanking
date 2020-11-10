package com.bank.additional;

import java.util.Timer;
import java.util.TimerTask;

public class Timebomb {
static Timer timer;

	public static void main(String args[]) {
	    int delay = 3000;
	    int period = 1000;
	    timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	        	timer.cancel();System.out.println("\nCountdown ends\n\n");
	        }
	    }, delay, period);
	}
}