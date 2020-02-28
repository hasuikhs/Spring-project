package testtest;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest{
	public static void main(String[] args) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("hello : " + new Date());
			}
		};
		
		
		Date firstTime = new Date();
		long period = 1000;
		Timer timer = new Timer();
		
		Date setTime = new Date();
		setTime.setYear(120);
		setTime.setMonth(1);
		setTime.setHours(9);
		
		setTime.setMinutes(35);
		setTime.setSeconds(0);
		System.out.println(setTime);
		
		timer.schedule(task, setTime);
		
	}
}
