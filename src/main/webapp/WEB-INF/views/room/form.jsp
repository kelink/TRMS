<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags'%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/resources/css/bookForm.css" rel="stylesheet" type="text/css" >
 <script src="<%=request.getContextPath()%>/resources/js/bookForm.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	//判断日期超过本月31日，保证start day 会小于end day ，同时不跨月
</script>
</head>
<body>
<div class="panelHeader">
<span id="headerText">Fill in the form</span>
<span id="close"onclick="back(${room.room_ID});"><img id="closeIcon" src="<%=request.getContextPath()%>/resources/images/closeShallow.png"width="16px"height="16px"/></span>

</div>

<form action="<%=request.getContextPath()%>/room/bookRoom" method="post" name="bookForm">
Free Room:
<select name="room_ID"> 
		<option value="${room.room_ID }">${room.item }</option>
</select>
<br/>
Start_Time<input type="date" name="begin_time" value="${select_date}"/><br/>
End_Time:<input type="date" name="end_time" value="${select_date}""/><br/>
Email<input type="text" name="email"/><br/>
Team
<select name="team_ID">
	<c:forEach items="${teams}" var="team"> 
		<option value="${team.team_ID}">${team.teamName }</option>
	</c:forEach>
</select>
<br/>
TelLine<input type="text" name="tele"><br/>
Purpose:<input type="text" name="purpose"><br/>
<input type="submit" name="submit" value="Submit"/>
</form>
 
</body>
</html>