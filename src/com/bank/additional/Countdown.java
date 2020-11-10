package com.bank.additional;

import java.util.Timer;
import java.util.TimerTask;

public class Countdown {
static int interval;
static Timer timer;

public static void emailVerifyCountdown(String seconds) {
    int delay = 1000;
    int period = 1000;
    timer = new Timer();
    interval = Integer.parseInt(seconds);
    System.out.println(seconds);
    timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
            setInterval();
        }
    }, delay, period);
}

private static final int setInterval() {
    if (interval == 1) {
        timer.cancel();System.out.println("\nCountdown ends\n\n");}
    return --interval;
}
}