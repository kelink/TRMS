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
				     <a href="<%=request.getContextPath()%>/admin/index" class="navItem">Book Room</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/reservation/reservationManagerIndex" class="navItem">Reservation Manager</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/blacklist/index" class="navItem">BlackList Management</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/department/index"class="navItem selected">Department Management </a>
				 </li>			
				  <li>
				     <a href="<%=request.getContextPath()%>/profile/modify"class="navItem">Manage My Profile</a>
				 </li>
			 </ul>
		 </div>
	 </div>
	 
	 <div class="middle">
	     <div class="middleContainer">
		     <h1 class="roomList">Department Information Manager Page</h1>		   
<!-- 			 <div class="roomListBody">				 -->
<!-- 						<br/> -->
<!-- 						<h1>Department Information Detial</h1>	 -->
<!-- 						<br/> -->
<%-- 									<h2><a href="<%=request.getContextPath()%>/team/index">Department Manager</a></h2> --%>
<%-- 									<h2><a href="<%=request.getContextPath()%>/team/index">Team Manager</a></h2> --%>
<%-- 									<h2><a href="<%=request.getContextPath()%>/room/roommanager">Room Manager</a></h2> --%>
<%-- 									<h2><a href="<%=request.getContextPath()%>/department/userList">User Manager </a></h2>	 --%>
<!-- 						<br/> -->
<%-- 						<c:forEach var="department" items="${departments}">	 --%>
<%-- 									department ID:${department.department_ID }<br/> --%>
<%-- 									department ID:${department.departmentName }<br/> --%>
<!-- 									department team:<br/> -->
<%-- 									<c:forEach var="team" items="${department.getTeamSet()}"> --%>
<%-- 										${team } --%>
<%-- 									</c:forEach> --%>
<!-- 									department room: <br/> -->
<%-- 									<c:forEach var="room" items="${department.getRoomSet()}"> --%>
<%-- 									${room } --%>
<%-- 									</c:forEach>									 --%>
<!-- 									<hr/> -->
<%-- 						</c:forEach> --%>
<!-- 			 </div> -->
<div class="roomListBody roomListBodyUserManagement">


             
			 <div id="userSearchDiv">
			
			     <div id="userSearchLabel"><img id="searchImg" src="<%=request.getContextPath()%>/resources/images/search.png"/><span id="userSearchSpan">Search by user LN...</span></div>
			     
			     <input id="userSearchInput">
			    
			 </div>
			  <div id="checkLeftPanel">
			      <!-- handle  begin -->
					 <div id="allDepartment" class="checkMethod selected">
			             All Department
			         </div>
			         <div id="seperateLine">
			         </div>
			         <div id="departmentWrapper"></div>
			  </div>
			  <div id="rightNav">
			      <div id="rightNav1"class="rightNavItem"><a href="<%=request.getContextPath()%>/team/index"><img class="rightNavIcon" width="38px" src="<%=request.getContextPath()%>/resources/images/person.png"/></a></div>
			      <div id="rightNav2"class="rightNavItem"><a href="<%=request.getContextPath()%>/team/index"><img class="rightNavIcon" width="38px" src="<%=request.getContextPath()%>/resources/images/team.png"/></a></div>
			      <div id="rightNav3"class="rightNavItem"><a href="<%=request.getContextPath()%>/room/roommanager"><img class="rightNavIcon" width="38px" src="<%=request.getContextPath()%>/resources/images/room.png"/></a></div>
			      <div id="rightNav4"class="rightNavItem"><a href="<%=request.getContextPath()%>/department/departmentList"><img class="rightNavIcon" width="38px" src="<%=request.getContextPath()%>/resources/images/department.png"/></a></div>
			  </div>
		      <div id="userDisplay">
<!-- 		      <p id="defaultInfo">No user information to show</p> -->
		      <div id="displayArea"></div>
		      </div>			
		      


					
						
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
