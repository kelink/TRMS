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
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/resources/js/lcIndex.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>

</head>
<body>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>  

<div id="cover">
 </div>
 <div class="wrapper">
     <div class="header">
	     <div class="headerContainer">
		 <span id="logo"><img src="<%=request.getContextPath()%>/resources/images/hsbcLogo.png"/></span>
		 </div>
	 </div>
	 
	 <div class="status">

	     <div class="statusContainer">
	         <div id="userInfo">
	         <img id="userCard" width="35px" height="35px" src="<%=request.getContextPath()%>/resources/images/card.png"/>
	         <div id="userName">
	         <span>Welcome! <security:authentication property="principal.username"/></span>
	         </div>
	         </div>
	         <button id="logoutBtn" class="btnLogout" onclick="logout('${logoutUrl}');">
	         <span id="logoutImage"><img id="logoutIcon" width="20px"height="20px" src="<%=request.getContextPath()%>/resources/images/logout.png"/>
	         </span>
	         <span>LogOut</span>
	         </button>
	      
	     </div>
	 </div>
	 
	 <div>
	     <div class="seperateLine">
		 </div>
	 </div>
	
	 <div class="navigator">
	     <div class="navContainer">
		     <ul class="navi">
			     <li>
				     <a href="<%=request.getContextPath()%>/admin/index" class="navItem selected">Book Room</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/reservation/reservationManagerIndex" class="navItem">Reservation Manager</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/blacklist/index" class="navItem">BlackList Management</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/team/index"class="navItem">LC Management </a>
				 </li>
				  <li>
				     <a href="<%=request.getContextPath()%>/room/roommanager"class="navItem">Room Management</a>
				 </li>
				  <li>
				     <a href="<%=request.getContextPath()%>/profile/modify"class="navItem">Manage My Profile</a>
				 </li>
			 </ul>
		 </div>
	 </div>
	 
	 <div class="middle">
	     <div class="middleContainer">
		     <h1 class="roomList">BlackList List</h1>
			 <div class="roomListBody">
			 	<h1>admin 管理 界面普通用户user 的界面</h1>
						管理员：<security:authentication property="principal.username"></security:authentication> 
						<c:url value="/j_spring_security_logout" var="logoutUrl"/>  
						<li><a href="${logoutUrl}">Log Out</a></li> 
						<hr/>
						department:
						<form action="<%=request.getContextPath()%>/team/getDetial" method="post">
						<select name="department_ID">
							<option value=""></option>
							<c:forEach var="department" items="${departments}">	
									<option value='${department.department_ID}'>${department.departmentName}</option>
							</c:forEach>
						</select>
						<input type="submit" value="check" name="submit" />					
						<hr/>
						</form>
						<hr/>
					<!-- 显示信息区域 -->	
					
						
			 </div>
		 </div>
	 </div>
	 <div class="footer">
	     <div class="footerContainer">
		 </div>
	 </div>
 </div>

	
</body>
</body>
</html>
