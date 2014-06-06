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
<title>万年历</title>
<script>
var bookedDate=eval(${calendarData})
var roomId=${room_ID}
var path="<%=request.getContextPath()%>"
</script>
<link href="<%=request.getContextPath()%>/resources/css/calendar.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
<script src="<%=request.getContextPath()%>/resources/js/lcIndex.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/calendarTA.js" type="text/javascript"charset=”utf-8″></script>

</head>
<body class="calBody">
<div class="day">
    <div class="DaySelect">
		<i class="lr" onclick="Month('l')" title="Last Month"><</i>
        <div class="select">
            <div class="stop" id="cy"></div>
        
        </div>
        <div class="select" id="sm">
            <div class="stop" id="cm"></div>
            <div class="sbox" id="mm">
<!--                 <ul id="DateAll"> -->
<!--                     <li>01</li> -->
<!--                 </ul> -->
            </div>
        </div>
        <i class="lr" onclick="Month('r')" title="Next Month">></i>
        <i onclick="now()">Today</i>
    </div>
    <div id="DayAll"></div>
</div>
<div id="bottomWrapper">
    <div id="bookedWord1">
    Booked.
    </div>
    <div id="bookedDiv1">
    </div>
</div>
</body>
</html>

























