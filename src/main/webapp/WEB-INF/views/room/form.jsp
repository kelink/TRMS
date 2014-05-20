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
<title>Insert title here</title>
</head>
<body>
<h1>订房界面</h1>
<h1><a href="<%=request.getContextPath()%>/user/index">BACK</a></h1>
<form action="<%=request.getContextPath()%>/room/bookRoom" method="post" name="bookForm">
Free Room:
<select name="room"> 
		<option value="${room.room_ID }">${room.item }</option>
</select>
<br/>
Start_Time<input type="date" name="begin_time" value="${select_date}"/><br/>
End_Time:<input type="date" name="end_time" value="${select_date}""/><br/>
Email<input type="text" name="email"/><br/>
Team
<select name="team">
	<c:forEach items="${teams}" var="team"> 
		<option value="${team.team_ID}">${team.teamName }</option>
	</c:forEach>
</select>
<br/>
TelLine<input type="text" name="userTelLine"><br/>
Purpose:<input type="text" name="purpose"><br/>
<input type="submit" name="submit" value="Submit"/>
</form>
 
</body>
</html>