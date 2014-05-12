package com.dummy.domain;
/**
 * 
 * Role Domain
 *
 *CREATE TABLE `role` (
 `Role_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户类型的id',
 `Authority` varchar(50) NOT NULL COMMENT '用户类型的描述',
 PRIMARY KEY (`Role_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
 *
 */
public class Role {	
	private int role_ID;
	private String authority;
	public int getRole_ID() {
		return role_ID;
	}
	public void setRole_ID(int role_ID) {
		this.role_ID = role_ID;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
