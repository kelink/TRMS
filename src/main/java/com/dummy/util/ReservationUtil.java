package com.dummy.util;

import java.util.UUID;

public class ReservationUtil {
	// ʹ��JDK����UUID����Ψһ������
	public static String getUniqueSequence() {
		return UUID.randomUUID().toString();
	}
}
