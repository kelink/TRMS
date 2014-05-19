package com.dummy.common;

public class C {
	public static final class base {
		public static final String APPPATH_STRING = "";
	}

	// 下面是数据库默认值的常量值
	public static final class DB {
		// Room 状态
		public static final int DEFAULT_FREE_ROOM = 0;
		public static final int DEFAULT_UNFREE_ROOM = 1;

		// Reservation 处理状态
		public static final int DEFAULT_RESERVATION_ACCEPT = 1;
		public static final int DEFAULT_RESERVATION_REFUSE = 0;
		public static final int DEFAULT_RESERVATION_UNHANDLE = -1;
	}

}
