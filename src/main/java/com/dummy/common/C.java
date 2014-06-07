package com.dummy.common;

public class C {
	public static final class base {
		public static final String APPPATH_STRING = "";
	}
	// DB constant
	public static final class DB {
		// DBUser
		public static final int DEFAULT_ROLE_LC = 1;
		public static final int DEFAULT_ROLE_TA = 2;
		// Role
		public static final String DEFAULT_ROLE_ADMIN = "ROLE_TA";
		public static final String DEFAULT_ROLE_USER = "ROLE_LC";
		// Room ״̬
		public static final int DEFAULT_FREE_ROOM = 0;// 代表可以申请
		public static final int DEFAULT_UNFREE_ROOM = 1;// 代表不可以申请

		// Reservation״̬
		public static final int DEFAULT_RESERVATION_ACCEPT = 1;
		public static final int DEFAULT_RESERVATION_REFUSE = 0;
		public static final int DEFAULT_RESERVATION_UNHANDLE = -1;
		public static final int DEFAULT_APPROVE_BY = 0;

		// Team
		public static final int DEFAULT_TEAM_NOUSER = 0;
	}

}
