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
 <link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
  <link href="<%=request.getContextPath()%>/resources/css/headInfo.css" rel="stylesheet" >
  <script src="<%=request.getContextPath()%>/resources/js/headInfo.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
</head>
<body>
<div id="headInfoWrapper">

<div id="unhandleReservation"class="headInfoItem">
    <div id="unhandleWrapper">
        <img id="unhandleIcon" src="<%=request.getContextPath()%>/resources/images/unhandle1.png"/>
    </div>
    <span id="unhandleWord">${count}</span>reservations unhandled
</div>
<div id="nextMonth"class="headInfoItem">
    <div id="nextMonthWrapper">
        <img id="nextMonthOpenIcon" src="<%=request.getContextPath()%>/resources/images/nextMonthOpen.png"/>
        
    </div>
    <span id="nextMonthWord"></span>
    
</div>

</div>
<div id="headInfoBottom"></div>
</body>
</html>