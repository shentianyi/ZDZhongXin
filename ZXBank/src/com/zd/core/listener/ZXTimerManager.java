package com.zd.core.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import com.zd.core.SystemProperty;

public class ZXTimerManager {
	private static final long DAY;
	private static final int HOUR;
	private static final int MINUTE;
	private static final int SECOND;

	static {
		DAY = new Long(SystemProperty.getPropertyValue("timer.properties", "zx.day"));
		HOUR = Integer.parseInt(SystemProperty.getPropertyValue("timer.properties", "zx.hour"));
		MINUTE = Integer.parseInt(SystemProperty.getPropertyValue("timer.properties", "zx.minute"));
		SECOND = Integer.parseInt(SystemProperty.getPropertyValue("timer.properties", "zx.second"));
	}

	public ZXTimerManager() {
		Calendar calender = Calendar.getInstance();

		//设置定时时间
		calender.set(Calendar.HOUR_OF_DAY, HOUR);
		calender.set(Calendar.MINUTE, MINUTE);
		calender.set(Calendar.SECOND, SECOND);

		Date date = calender.getTime();

		if (date.before(new Date())) {
			date = this.addDay(date, 1);
			System.out.println("next Time:" + date);
		}

		Timer timer = new Timer();

		ZXBankTimer task = new ZXBankTimer();

		timer.schedule(task, date, DAY);
	}

	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
}
