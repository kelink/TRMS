package com.dummy.util;

import java.util.UUID;

public class ReservationUtil {
	// 使用JDK里面UUID产生唯一订单号
	public static String getUniqueSequence() {
		return UUID.randomUUID().toString();
	}
}
