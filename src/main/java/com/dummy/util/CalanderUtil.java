package com.dummy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalanderUtil {
	public static void main(String[] args) {
		System.out.println(getFirstDay());
		System.out.println(getLastDay());
	}

	// get First Date of month
	public static String getFirstDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-" + "01";
	}

	// get last Date of month
	public static String getLastDay() {
		Calendar calendar = Calendar.getInstance();
		int countDay = calDayByYearAndMonth(
				String.valueOf(calendar.get(Calendar.YEAR)),
				String.valueOf(calendar.get(Calendar.MONTH) + 1));
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-" + countDay;
	}

	// get the number of current month
	public static int calDayByYearAndMonth(String dyear, String dmouth) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM");
		Calendar rightNow = Calendar.getInstance();
		try {
			rightNow.setTime(simpleDate.parse(dyear + "/" + dmouth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);

	}
	// judge the last day
	public static boolean isOverLastDay(String dateStr) {
		String lastDateStr = CalanderUtil.getLastDay();
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
		Date date;
		try {
			date = format.parse(dateStr);
			Date lastDate = format.parse(lastDateStr);
			if (date.getTime() > lastDate.getTime()) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

	}
	// judge the first day
	public static boolean isEarlyFirstDay(String dateStr) {
		String lastDateStr = CalanderUtil.getFirstDay();
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
		Date date;
		try {
			date = format.parse(dateStr);
			Date lastDate = format.parse(lastDateStr);
			if (date.getTime() < lastDate.getTime()) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;

		}

	}
}
