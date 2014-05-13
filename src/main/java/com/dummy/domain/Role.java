package com.dummy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Role Domain
 *
 * CREATE TABLE `role` ( `Role_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT
 * '用户类型的id', `Authority` varchar(50) NOT NULL COMMENT '用户类型的描述', PRIMARY KEY
 * (`Role_ID`) ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
 *
 */
@Entity
@Table(name = "Role")
public class Role {
	/**
	 * 角色表
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private int Role_ID;

	@Column(length = 11)
	private String authority;

	public int getRole_ID() {
		return Role_ID;
	}

	public void setRole_ID(int role_ID) {
		Role_ID = role_ID;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}