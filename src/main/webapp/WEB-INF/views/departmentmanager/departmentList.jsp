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

<title> BlackList Page </title>
<link href="<%=request.getContextPath()%>/resources/css/departmentList.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
<script src="<%=request.getContextPath()%>/resources/js/departmentList.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
</head>
<body>
<div class="roomListBody"> 
 <h2>department information</h2>
		      <c:forEach var="department" items="${departments}">	 
							
 							department Name:${department.departmentName }<br/> 
 							<!-- 
 							department ID:${department.department_ID }<br/> 
 							<c:forEach var="team" items="${department.getTeamSet()}"> 
 									${team } 
 							</c:forEach> 
 							department room: <br/> 
							<c:forEach var="room" items="${department.getRoomSet()}"> 
 								${room } 
 								</c:forEach>									
 							<hr/>
 							-->
						</c:forEach> 
						<!-- 
		      <p id="defaultInfo">No user information to show</p> 
		       -->
		      <div id="displayArea"></div>
</div>
</body>
</html>