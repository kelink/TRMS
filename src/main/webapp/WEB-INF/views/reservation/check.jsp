<%@page import="com.dummy.domain.Room"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/trms/resources/images/hsbcicon.ico" type="image/x-icon"/>
</head>
<body>
	<h1>Reservation check</h1>
	
	<div>
	<form action="<%=request.getContextPath()%>/reservation/checkReservations" method="post">
		Reservation Number:
		<input type="text" name="reservation_Num"/><br/>
		Booked Time:
		<input type="date" name="order_Time"/><br/>
		Ticket Status:
		<select name="status">
			<option></option>
			<option value="1">accept</option>
			<option value="-1">refuse</option>
			<option value="0">unhandle</option>
		</select>
		Room：
		<select name="room_ID">
		<option></option>
			<c:forEach var="room" items="${rooms}">	
				<option value='${room.room_ID}'>${room.item}</option>
			</c:forEach>		
		</select><br/>
		Planned Use Start Time：
		<input type="date" name="Applied_Start_Date"/><br/>
		Planned Use End Time：
		<input type="date" name="Applied_End_Date"/><br/>
		User LN:
		<input type="text" name="email"/><br/>
		User Team:
		<select name="team_ID">
			<option></option>
			<c:forEach var="team" items="${teams}">	
				<option value='${team.team_ID}'>${team.teamName}</option>
			</c:forEach>
		</select><br/>
		User TelLine:
		<input type="text" name="tele"/><br/>
		Use Resaon:
		<input type="text" name="purpose"/>
		<input type="submit"name="submit" value="submit"/>
		</form>
	</div>
</body>
</body>
</html>
