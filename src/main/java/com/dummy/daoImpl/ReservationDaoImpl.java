package com.dummy.daoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.dummy.common.C;
import com.dummy.dao.ReservationDao;
import com.dummy.domain.CalanderDataDomain;
import com.dummy.domain.DBUser;
import com.dummy.domain.Reservation;
import com.dummy.domain.ReservationDetial;
import com.dummy.domain.Room;
import com.dummy.domain.Team;
import com.dummy.util.CalanderUtil;

@Repository(value = "reservationDao")
public class ReservationDaoImpl implements ReservationDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Reservation getReservation(int id) {
		String hql = "from Reservation r where r.reservation_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Reservation) query.uniqueResult();
	}

	@Override
	public List<Reservation> getAllReservation() {
		String hql = "from Reservation";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void addReservation(Reservation reservation) {
		sessionFactory.getCurrentSession().save(reservation);
	}

	@Override
	public boolean delReservation(int id) {
		String hql = "delete Reservation r where r.reservation_ID = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (query.executeUpdate() > 0);
	}

	// Update a reservation
	@Override
	public boolean updateReservation(Reservation reservation) {
		String hql = "update Reservation "
				+ "set reservation.reservation_ID=?,"
				+ "reservation.reservation_Num=?,"
				+ "reservation.Applied_Start_Date=?,"
				+ "reservation.Applied_End_Date=?," + "reservation.email=?,"
				+ "reservation.tele=?," + "reservation.order_Time=?,"
				+ "reservation.purpose=?," + "reservation.room_ID=?,"
				+ "reservation.status=?," + "reservation.team_ID=?,"
				+ "reservation.handle_by=?," + "reservation.user_ID=?"
				+ " where reservation=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, reservation.getReservation_ID());
		query.setString(1, reservation.getReservation_Num());
		query.setDate(2, reservation.getApplied_Start_Date());
		query.setDate(3, reservation.getApplied_End_Date());
		query.setString(4, reservation.getEmail());
		query.setString(5, reservation.getTele());
		query.setDate(6, reservation.getOrder_Time());
		query.setString(7, reservation.getPurpose());
		query.setInteger(8, reservation.getRoom_ID());
		query.setInteger(9, reservation.getStatus());
		query.setInteger(10, reservation.getTeam_ID());
		query.setInteger(11, reservation.getHandle_by());
		query.setInteger(12, reservation.getUser_ID());

		query.setInteger(13, reservation.getUser_ID());
		int n = query.executeUpdate();
		if (n == 1) {
			return true;
		} else {
			return false;
		}
	}

	// get all reservation Info
	@SuppressWarnings("unchecked")
	@Override
	public List<CalanderDataDomain> getAllReservationInfo(int room_ID) {
		String hql = "select " + "Applied_END_Date," + "Applied_Start_Date,"
				+ "email," + "order_Time," + "purpose," + "Team.teamName,"
				+ "DBUser.user_ID," + "DBUser.account,"
				+ "Reservation.room_ID," + "Reservation.tele "
				+ "from Reservation "
				+ "left join Room on Reservation.room_ID=Room.room_ID "
				+ "left join DBUser on Reservation.user_ID=DBUser.user_ID "
				+ "left join Team on  Reservation.team_ID=Team.team_ID "
				+ "where " + "Reservation.status=? "
				+ "and applied_start_date>=? " + "and applied_end_date<=? "
				+ "and reservation.room_ID=? "
				+ "and Dbuser.user_ID=reservation.user_ID;";

		SQLQuery query = sessionFactory.openSession().createSQLQuery(hql);
		query.setInteger(0, C.DB.DEFAULT_RESERVATION_ACCEPT);
		query.setString(1, CalanderUtil.getFirstDay());
		query.setString(2, CalanderUtil.getLastDay());
		query.setInteger(3, room_ID);
		List<Object[]> stus = query
				.addScalar("Applied_END_Date", StandardBasicTypes.DATE)
				.addScalar("Applied_Start_Date", StandardBasicTypes.DATE)
				.addScalar("email", StandardBasicTypes.STRING)
				.addScalar("order_Time", StandardBasicTypes.DATE)
				.addScalar("purpose", StandardBasicTypes.STRING)
				.addScalar("teamName", StandardBasicTypes.STRING)
				.addScalar("user_ID", StandardBasicTypes.INTEGER)
				.addScalar("account", StandardBasicTypes.STRING)
				.addScalar("room_ID", StandardBasicTypes.INTEGER)
				.addScalar("tele", StandardBasicTypes.STRING).list();
		List<CalanderDataDomain> list = new ArrayList<CalanderDataDomain>();
		for (Iterator<Object[]> iterator = stus.iterator(); iterator.hasNext();) {
			CalanderDataDomain data = new CalanderDataDomain();
			Object[] rows = (Object[]) iterator.next();
			data.setApplied_END_Date((Date) rows[0]);
			data.setApplied_Start_Date((Date) rows[1]);
			data.setEmail((String) rows[2]);
			data.setOrder_Time((Date) rows[3]);
			data.setPurpose((String) rows[4]);
			data.setTeamName((String) rows[5]);
			data.setUser_ID((Integer) rows[6]);
			data.setAccount((String) rows[7]);
			data.setRoom_ID((Integer) rows[8]);
			data.setTele((String) rows[9]);
			list.add(data);
		}
		return list;
	}

	// get Reservation detial
	public List<ReservationDetial> getReservationByOption(String optionSql) {
		List<ReservationDetial> result = new ArrayList<ReservationDetial>();
		String hql = "select " + "reservation.reservation_ID,"
				+ "reservation.reservation_Num,"
				+ "reservation.Applied_Start_Date,"
				+ "reservation.Applied_End_Date," + "reservation.email,"
				+ "reservation.tele," + "reservation.order_Time,"
				+ "reservation.purpose," + "reservation.room_ID,"
				+ "reservation.status," + "reservation.team_ID,"
				+ "reservation.handle_by," + "reservation.user_ID,"
				+ "room.room_ID," + "room.item," + "room.last_Used_Date,"
				+ "room.room_Status," + "room.department_ID,"
				+ "dbuser.user_ID," + "dbuser.Tele," + "dbuser.access,"
				+ "dbuser.account," + "dbuser.gender," + "dbuser.password,"
				+ "dbuser.dept_ID," + "team.team_ID," + "team.department_ID,"
				+ "team.teamName," + "team.user_ID " + "from Reservation "
				+ "left join Room on Reservation.room_ID=Room.room_ID "
				+ "left join DBUser on Reservation.user_ID=DBUser.user_ID "
				+ "left join Team on  Reservation.team_ID=Team.team_ID "
				+ optionSql + " order by reservation.order_Time desc";

		System.out.println(hql);
		SQLQuery query = sessionFactory.openSession().createSQLQuery(hql);
		@SuppressWarnings("unchecked")
		List<Object[]> stus = query

				.addScalar("reservation.reservation_ID",
						StandardBasicTypes.INTEGER)
				.addScalar("reservation.reservation_Num",
						StandardBasicTypes.STRING)
				.addScalar("reservation.Applied_Start_Date",
						StandardBasicTypes.DATE)
				.addScalar("reservation.Applied_End_Date",
						StandardBasicTypes.DATE)
				.addScalar("reservation.email", StandardBasicTypes.STRING)
				.addScalar("reservation.tele", StandardBasicTypes.STRING)
				.addScalar("reservation.order_Time", StandardBasicTypes.DATE)
				.addScalar("reservation.purpose", StandardBasicTypes.STRING)
				.addScalar("reservation.room_ID", StandardBasicTypes.INTEGER)
				.addScalar("reservation.status", StandardBasicTypes.INTEGER)
				.addScalar("reservation.team_ID", StandardBasicTypes.INTEGER)
				.addScalar("reservation.handle_by", StandardBasicTypes.INTEGER)
				.addScalar("reservation.user_ID", StandardBasicTypes.INTEGER)

				.addScalar("room.room_ID", StandardBasicTypes.INTEGER)
				.addScalar("room.item", StandardBasicTypes.STRING)
				.addScalar("room.last_Used_Date", StandardBasicTypes.DATE)
				.addScalar("room.room_Status", StandardBasicTypes.INTEGER)
				.addScalar("room.department_ID", StandardBasicTypes.INTEGER)

				.addScalar("dbuser.user_ID", StandardBasicTypes.INTEGER)
				.addScalar("dbuser.Tele", StandardBasicTypes.STRING)
				.addScalar("dbuser.access", StandardBasicTypes.INTEGER)
				.addScalar("dbuser.account", StandardBasicTypes.STRING)
				.addScalar("dbuser.gender", StandardBasicTypes.INTEGER)
				.addScalar("dbuser.password", StandardBasicTypes.STRING)
				.addScalar("dbuser.dept_ID", StandardBasicTypes.INTEGER)

				.addScalar("team.team_ID", StandardBasicTypes.INTEGER)
				.addScalar("team.department_ID", StandardBasicTypes.INTEGER)
				.addScalar("team.teamName", StandardBasicTypes.STRING)
				.addScalar("team.user_ID", StandardBasicTypes.INTEGER)

				.list();

		for (Iterator<Object[]> iterator = stus.iterator(); iterator.hasNext();) {

			Reservation reservation = new Reservation();

			Object[] rows = (Object[]) iterator.next();
			reservation.setReservation_ID((Integer) rows[0]);
			reservation.setReservation_Num((String) rows[1]);
			reservation.setApplied_Start_Date((Date) rows[2]);
			reservation.setApplied_End_Date((Date) rows[3]);
			reservation.setEmail((String) rows[4]);
			reservation.setTele((String) rows[5]);
			reservation.setOrder_Time((Date) rows[6]);
			reservation.setPurpose((String) rows[7]);
			reservation.setRoom_ID((Integer) rows[8]);
			reservation.setStatus((Integer) rows[9]);
			reservation.setTeam_ID((Integer) rows[10]);
			reservation.setHandle_by((Integer) rows[11]);
			reservation.setUser_ID((Integer) rows[12]);

			Room room = new Room();
			room.setRoom_ID((Integer) rows[13]);
			room.setItem((String) rows[14]);
			room.setLast_Used_Date((Date) rows[15]);
			room.setRoom_Status((Integer) rows[16]);
			room.setDepartment_ID((Integer) rows[17]);

			DBUser dbUser = new DBUser();
			dbUser.setUser_ID((Integer) rows[18]);
			dbUser.setTele((String) rows[19]);
			dbUser.setAccess((Integer) rows[20]);
			dbUser.setAccount((String) rows[21]);
			dbUser.setGender((Integer) rows[22]);
			dbUser.setPassword((String) rows[23]);
			dbUser.setDept_ID((Integer) rows[24]);

			Team team = new Team();
			team.setTeam_ID((Integer) rows[25]);
			team.setDepartment_ID((Integer) rows[26]);
			team.setTeamName((String) rows[27]);
			team.setUser_ID((Integer) rows[28]);

			ReservationDetial data = new ReservationDetial();
			data.setReservation(reservation);
			data.setRoom(room);
			data.setTeam(team);
			data.setUser(dbUser);
			result.add(data);
		}
		System.out.println(result);
		return result;
	}

	public List<ReservationDetial> getReservationDetialOnPage(int pageNum,
			int pageSize, String optionSql) {
		List<ReservationDetial> result = new ArrayList<ReservationDetial>();
		String hql = "select " + "reservation.reservation_ID,"
				+ "reservation.reservation_Num,"
				+ "reservation.Applied_Start_Date,"
				+ "reservation.Applied_End_Date," + "reservation.email,"
				+ "reservation.tele," + "reservation.order_Time,"
				+ "reservation.purpose," + "reservation.room_ID,"
				+ "reservation.status," + "reservation.team_ID,"
				+ "reservation.handle_by," + "reservation.user_ID,"
				+ "room.room_ID," + "room.item," + "room.last_Used_Date,"
				+ "room.room_Status," + "room.department_ID,"
				+ "dbuser.user_ID," + "dbuser.Tele," + "dbuser.access,"
				+ "dbuser.account," + "dbuser.gender," + "dbuser.password,"
				+ "dbuser.dept_ID," + "team.team_ID," + "team.department_ID,"
				+ "team.teamName," + "team.user_ID " + "from Reservation "
				+ "left join Room on Reservation.room_ID=Room.room_ID "
				+ "left join DBUser on Reservation.user_ID=DBUser.user_ID "
				+ "left join Team on  Reservation.team_ID=Team.team_ID "
				+ optionSql + " order by reservation.order_Time desc";

		System.out.println("hql-------------------------->>>>>" + hql);
		SQLQuery query = sessionFactory.openSession().createSQLQuery(hql);
		int offSet = (pageNum - 1) * pageSize >= 0
				? (pageNum - 1) * pageSize
				: 0;
		query.setFirstResult(offSet);// begin index
		query.setMaxResults(pageSize);// size of recorder

		@SuppressWarnings("unchecked")
		List<Object[]> stus = query

				.addScalar("reservation.reservation_ID",
						StandardBasicTypes.INTEGER)
				.addScalar("reservation.reservation_Num",
						StandardBasicTypes.STRING)
				.addScalar("reservation.Applied_Start_Date",
						StandardBasicTypes.DATE)
				.addScalar("reservation.Applied_End_Date",
						StandardBasicTypes.DATE)
				.addScalar("reservation.email", StandardBasicTypes.STRING)
				.addScalar("reservation.tele", StandardBasicTypes.STRING)
				.addScalar("reservation.order_Time", StandardBasicTypes.DATE)
				.addScalar("reservation.purpose", StandardBasicTypes.STRING)
				.addScalar("reservation.room_ID", StandardBasicTypes.INTEGER)
				.addScalar("reservation.status", StandardBasicTypes.INTEGER)
				.addScalar("reservation.team_ID", StandardBasicTypes.INTEGER)
				.addScalar("reservation.handle_by", StandardBasicTypes.INTEGER)
				.addScalar("reservation.user_ID", StandardBasicTypes.INTEGER)

				.addScalar("room.room_ID", StandardBasicTypes.INTEGER)
				.addScalar("room.item", StandardBasicTypes.STRING)
				.addScalar("room.last_Used_Date", StandardBasicTypes.DATE)
				.addScalar("room.room_Status", StandardBasicTypes.INTEGER)
				.addScalar("room.department_ID", StandardBasicTypes.INTEGER)

				.addScalar("dbuser.user_ID", StandardBasicTypes.INTEGER)
				.addScalar("dbuser.Tele", StandardBasicTypes.STRING)
				.addScalar("dbuser.access", StandardBasicTypes.INTEGER)
				.addScalar("dbuser.account", StandardBasicTypes.STRING)
				.addScalar("dbuser.gender", StandardBasicTypes.INTEGER)
				.addScalar("dbuser.password", StandardBasicTypes.STRING)
				.addScalar("dbuser.dept_ID", StandardBasicTypes.INTEGER)

				.addScalar("team.team_ID", StandardBasicTypes.INTEGER)
				.addScalar("team.department_ID", StandardBasicTypes.INTEGER)
				.addScalar("team.teamName", StandardBasicTypes.STRING)
				.addScalar("team.user_ID", StandardBasicTypes.INTEGER)

				.list();

		for (Iterator<Object[]> iterator = stus.iterator(); iterator.hasNext();) {

			Reservation reservation = new Reservation();

			Object[] rows = (Object[]) iterator.next();
			reservation.setReservation_ID((Integer) rows[0]);
			reservation.setReservation_Num((String) rows[1]);
			reservation.setApplied_Start_Date((Date) rows[2]);
			reservation.setApplied_End_Date((Date) rows[3]);
			reservation.setEmail((String) rows[4]);
			reservation.setTele((String) rows[5]);
			reservation.setOrder_Time((Date) rows[6]);
			reservation.setPurpose((String) rows[7]);
			reservation.setRoom_ID((Integer) rows[8]);
			reservation.setStatus((Integer) rows[9]);
			reservation.setTeam_ID((Integer) rows[10]);
			reservation.setHandle_by((Integer) rows[11]);
			reservation.setUser_ID((Integer) rows[12]);

			Room room = new Room();
			room.setRoom_ID((Integer) rows[13]);
			room.setItem((String) rows[14]);
			room.setLast_Used_Date((Date) rows[15]);
			room.setRoom_Status((Integer) rows[16]);
			room.setDepartment_ID((Integer) rows[17]);

			DBUser dbUser = new DBUser();
			dbUser.setUser_ID((Integer) rows[18]);
			dbUser.setTele((String) rows[19]);
			dbUser.setAccess((Integer) rows[20]);
			dbUser.setAccount((String) rows[21]);
			dbUser.setGender((Integer) rows[22]);
			dbUser.setPassword((String) rows[23]);
			dbUser.setDept_ID((Integer) rows[24]);

			Team team = new Team();
			team.setTeam_ID((Integer) rows[25]);
			team.setDepartment_ID((Integer) rows[26]);
			team.setTeamName((String) rows[27]);
			team.setUser_ID((Integer) rows[28]);

			ReservationDetial data = new ReservationDetial();
			data.setReservation(reservation);
			data.setRoom(room);
			data.setTeam(team);
			data.setUser(dbUser);
			result.add(data);
		}
		System.out.println(result);
		return result;
	}

	@Override
	public boolean delReservationByNum(String num) {
		String hql = "delete Reservation r where r.reservation_Num = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, num);
		return (query.executeUpdate() > 0);
	}

	@Override
	public Reservation getReservationByNum(String num) {
		String hql = "from Reservation r where r.reservation_Num=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, num);
		return (Reservation) query.uniqueResult();
	}

}
