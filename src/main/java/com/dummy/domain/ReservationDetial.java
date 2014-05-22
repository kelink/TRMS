package com.dummy.domain;

public class ReservationDetial {
	private Reservation reservation;
	private Room room;
	private DBUser user;
	private Team team;

	public ReservationDetial(Reservation reservation, Room room, DBUser user,
			Team team) {
		super();
		this.reservation = reservation;
		this.room = room;
		this.user = user;
		this.team = team;
	}

	public ReservationDetial() {
		super();
	}

	@Override
	public String toString() {
		return "ReservationDetial [reservation=" + reservation + ", room="
				+ room + ", user=" + user + ", team=" + team + "]";
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public DBUser getUser() {
		return user;
	}

	public void setUser(DBUser user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
