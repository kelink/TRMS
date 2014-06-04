package com.dummy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BlackList")
public class BlackList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private int bl_ID;

	@Column(length = 11)
	private int team_ID;

	@Column(length = 255)
	private String reason;

	public int getBl_ID() {
		return bl_ID;
	}

	public void setBl_ID(int bl_ID) {
		this.bl_ID = bl_ID;
	}

	public int getTeam_ID() {
		return team_ID;
	}

	public void setTeam_ID(int team_ID) {
		this.team_ID = team_ID;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "BlackList [bl_ID=" + bl_ID + ", team_ID=" + team_ID
				+ ", reason=" + reason + "]";
	}
}
