package com.dummy.common;

public class C {
	public static final class base {
		public static final String APPPATH_STRING = "";
	}

	public static final class Util {
		public static final String CONFIG_EMAIL_PATH = "E:\\spring too suit\\workplace\\Dummy\\TRMS\\src\\main\\resources\\email.properties";
	}

	// 下面是数据库默认值的常量值
	public static final class DB {
		// DBUser
		public static final int DEFAULT_ROLE_LC = 1;
		public static final int DEFAULT_ROLE_TA = 2;
		// Room 状态
		public static final int DEFAULT_FREE_ROOM = 0;
		public static final int DEFAULT_UNFREE_ROOM = 1;

		// Reservation 处理状态
		public static final int DEFAULT_RESERVATION_ACCEPT = 1;
		public static final int DEFAULT_RESERVATION_REFUSE = 0;
		public static final int DEFAULT_RESERVATION_UNHANDLE = -1;
		public static final int DEFAULT_APPROVE_BY = 0;
	}

}
