package com.dummy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalanderUtil {
	public static void main(String[] args) {
		System.out.println(getFirstDay());
		System.out.println(getLastDay());
	}

	// 获取本月的第一天getFirstDay
	public static String getFirstDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-" + "01";
	}

	// 获取本月最后一天
	public static String getLastDay() {
		Calendar calendar = Calendar.getInstance();
		int countDay = calDayByYearAndMonth(
				String.valueOf(calendar.get(Calendar.YEAR)),
				String.valueOf(calendar.get(Calendar.MONTH) + 1));
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-" + countDay;
	}

	// 获取本月有多少天
	public static int calDayByYearAndMonth(String dyear, String dmouth) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM");
		Calendar rightNow = Calendar.getInstance();
		try {
			rightNow.setTime(simpleDate.parse(dyear + "/" + dmouth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);// 根据年月 获取月份天数
	}
}
