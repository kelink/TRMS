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

<title> Database Manager Page </title>
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/userManagement.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/userManagement.js" type="text/javascript"></script>

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
				     <a href="<%=request.getContextPath()%>/reservation/reservationManagerIndex" class="navItem">Reservation Management</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/blacklist/index" class="navItem">BlackList Management</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/department/index"class="navItem selected">Database Management </a>
				 </li>			
				  <li>
				     <a href="<%=request.getContextPath()%>/profile/modify"class="navItem">Manage My Profile</a>
				 </li>
			 </ul>
		 </div>
	 </div>
	 
	 <div class="middle">
	     <div class="middleContainer">

		 

		     <h1 class="roomList">Database Management</h1>
			 <div class="roomListBody roomListBodyUserManagement">


             
			 <div id="userSearchDiv">
			
			     <div id="userSearchLabel"><img id="searchImg" src="<%=request.getContextPath()%>/resources/images/search.png"/><span id="userSearchSpan">Search by user LN...</span></div>
			     
			     <input id="userSearchInput">
			    
			 </div>
			 <div id="userDisplayWrapper">
			      <iframe id="userDisplay"name="userDisplay" src="<%=request.getContextPath()%>/admin/listUser"></iframe>
			  </div>
			  <div id="rightNav">
			      <div id="rightNav1"class="rightNavItem"><div class="floatLabel">LC Management</div><a target="userDisplay" href="<%=request.getContextPath()%>/admin/listUser"><img class="rightNavIcon" width="38px" src="<%=request.getContextPath()%>/resources/images/person.png"/></a></div>
			      <div id="rightNav2"class="rightNavItem"><div class="floatLabel">Team Management</div><a target="userDisplay" href="<%=request.getContextPath()%>/team/index"><img class="rightNavIcon" width="38px" src="<%=request.getContextPath()%>/resources/images/team.png"/></a></div>
			      <div id="rightNav3"class="rightNavItem"><div class="floatLabel">Room Management</div><a target="userDisplay" href="<%=request.getContextPath()%>/room/roommanager"><img class="rightNavIcon" width="38px" src="<%=request.getContextPath()%>/resources/images/room.png"/></a></div>
			      <div id="rightNav4"class="rightNavItem"><div class="floatLabel">Department Management</div><a target="userDisplay" href="<%=request.getContextPath()%>/department/departmentList"><img class="rightNavIcon" width="38px" src="<%=request.getContextPath()%>/resources/images/department.png"/></a></div>
			  </div>				
			 </div>
		 </div>
	 </div>	
 </div>
  <div id="footerContainer">
      <div id="footer">
          <span id="copyright"> &copy; Copyright 2014 by <a href="#">Lin Jiajian</a>&<a href="#">Luo Kelin</a></span>
          <span><img id="hsbcIconFooter" width="30px"height="15px" src="<%=request.getContextPath() %>/resources/images/hsbcFooter.png"/></span>
          <span id="footerWord"><a href="#">Team</a> <a href="#">Telephone</a><a href="#">Email</a><a href="#">about</a></span>
      </div>
 </div>

	
</body>
</body>
</html>
