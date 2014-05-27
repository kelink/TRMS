package com.dummy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalanderUtil {
	public static void main(String[] args) {
		System.out.println(getFirstDay());
		System.out.println(getLastDay());
	}

	// ��ȡ���µĵ�һ��getFirstDay
	public static String getFirstDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-" + "01";
	}

	// ��ȡ�������һ��
	public static String getLastDay() {
		Calendar calendar = Calendar.getInstance();
		int countDay = calDayByYearAndMonth(
				String.valueOf(calendar.get(Calendar.YEAR)),
				String.valueOf(calendar.get(Calendar.MONTH) + 1));
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-" + countDay;
	}

	// ��ȡ�����ж�����
	public static int calDayByYearAndMonth(String dyear, String dmouth) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM");
		Calendar rightNow = Calendar.getInstance();
		try {
			rightNow.setTime(simpleDate.parse(dyear + "/" + dmouth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);// �������
																// ��ȡ�·�����
	}
}
