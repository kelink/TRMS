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
	<h1>显示reservation</h1>
	<c:forEach var="reservationDetial" items="${reservationDetials}">	
		<hr/>
		${reservationDetial.reservation.reservation_ID }<br/>
		${reservationDetial.reservation.reservation_Num }<br/>
		${reservationDetial.reservation.team_ID }<br/>
		${reservationDetial.reservation.room_ID }<br/>
		${reservationDetial.reservation.user_ID }<br/>
		${reservationDetial.reservation.purpose }<br/>
		${reservationDetial.reservation.status }<br/>
		${reservationDetial.reservation.email }<br/>
		${reservationDetial.reservation.tele }<br/>
		${reservationDetial.reservation.approve_by}<br/>
		${reservationDetial.reservation.getApplied_Start_DateByString()}<br/>
		${reservationDetial.reservation.getApplied_End_DateByString() }<br/>
		${reservationDetial.reservation.getOrder_DateByString() }<br/>
		
		${reservationDetial.team.teamName}
		<hr/>
	</c:forEach>
</body>
</body>
</html>
