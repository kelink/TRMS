package com.dummy.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Department")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private int department_ID;

	@Column(length = 255)
	private String departmentName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "department_ID")
	private Set<Room> roomSet;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "department_ID")
	private Set<Team> teamSet;

	public Set<Team> getTeamSet() {
		return teamSet;
	}

	public void setTeamSet(Set<Team> teamSet) {
		this.teamSet = teamSet;
	}

	public int getDepartment_ID() {
		return department_ID;
	}

	public void setDepartment_ID(int department_ID) {
		this.department_ID = department_ID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<Room> getRoomSet() {
		return roomSet;
	}

	public void setRoomSet(Set<Room> roomSet) {
		this.roomSet = roomSet;
	}

}
