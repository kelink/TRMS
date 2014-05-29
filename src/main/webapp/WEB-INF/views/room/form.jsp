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
<title>book room form</title>

<link href="<%=request.getContextPath()%>/resources/css/bookForm.css" rel="stylesheet" type="text/css" >
 <script src="<%=request.getContextPath()%>/resources/js/bookForm.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	//判断日期超过本月31日，保证start day 会小于end day ，同时不跨月
function isEmpty(){
	var department_ID=$("#departments").val();
	var team_ID=$("#teams").val();
	var begin_time=$("#begin_time").val();
	var end_time=$("#end_time").val();
	var email=$("#email").val();
	
	if(department_ID==""||team_ID==""||begin_time==""||end_time==""||email==""){
		alert(" Empty item ,please fill it");	
		return false;
	}
	
}
	
</script>
</head>
<body>
<div class="panelHeader">
<span id="headerText">Fill in the form</span>
<span id="close"onclick="back(${room.room_ID});"><img id="closeIcon" src="<%=request.getContextPath()%>/resources/images/closeShallow.png"width="16px"height="16px"/></span>

</div>

<form class="bookForm" action="<%=request.getContextPath()%>/room/bookRoom" method="post" name="bookForm" onsubmit="return isEmpty()">

    <div class="bookFormLabel">Free Room</div>
    <select class="bookFormInput" name="room_ID"> 
		<option value="${room.room_ID }">${room.item }</option>
    </select>
     <div class="bookFormLabel">Team</div>
    <select class="bookFormInput" name="team_ID" id="teams">
    	<option value=""></option>
	    <c:forEach items="${teams}" var="team"> 	    	
		    <option value="${team.team_ID}">${team.teamName }</option>
	    </c:forEach>
    </select><span>*</span>
    
    <div class="bookFormLabel">Start_Time</div>
    <input class="bookFormInput" type="date" name="begin_time" value="${select_date}" id="begin_time"/><span>*</span>
    <div class="bookFormLabel">End_Time</div>
    <input class="bookFormInput" type="date" name="end_time" value="${select_date}"" id="end_time"/><span>*</span>
    <div class="bookFormLabel">Email</div>
    <input class="bookFormInput" type="text" name="email" id="email"/><span>*</span>
   
    
    <div class="bookFormLabel">TelLine</div>
    <input class="bookFormInput" type="text" name="tele" id="tele">
    <div class="bookFormLabel">Purpose</div>
    <textarea class="bookFormInput" type="text" name="purpose" id="purpose"></textarea>
    <br />
    <input class="btnBook" type="submit" name="submit" value="Submit"/>
</form>
 
</body>
</html>