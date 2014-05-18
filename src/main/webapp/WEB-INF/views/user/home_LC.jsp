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

  <title> LC Page </title>
 <link href="/trms/resources/css/lcIndex.css" rel="stylesheet" >
 <script src="/trms/resources/js/lcIndex.js" type="text/javascript"></script>
 <script src="/trms/resources/js/jquery.js" type="text/javascript"></script>

</head>
<body>
	<security:authentication property="principal.username"></security:authentication> 
	<c:url value="/j_spring_security_logout" var="logoutUrl"/>  
<a href="${logoutUrl}">Log Out</a> 
<br/>



<div class="cover">
 </div>
 <div class="wrapper">
     <div class="header">
	     <div class="headerContainer">
		 <span id="logo"><img src="/trms/resources/images/hsbcLogo.png"/></span>
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
				     <a href="lcBook.html" class="navItem selected">Book Room</a>
				 </li>
				 <li>
				     <a href="lcRoomCheck.html"class="navItem">Check My Reservation</a>
				 </li>
				 <li>
				     <a href="lcRoomBook.html" class="navItem">Delete My Reservation</a>
				 </li>
				 <li>
				     <a href="profileAdmin.html"class="navItem">Manage My Profile</a>
				 </li>
			 </ul>
		 </div>
	 </div>
	 
	 <div class="middle">
	     <div class="middleContainer">
		     <h1 class="roomList">Room List</h1>
			 <div class="roomListBody">
			  	 <div class="roomListHeader">
				     <div class="roomListTitle">
					     Room
					 </div>
					 
				 </div>

					<!-- 显示room的信息 -->
				 <ul>
				 <c:forEach items="${rooms}" var="room"> 
				     <li id="list1"class="roomListContent">
					     <span class="roomListNum">1</span>
						 <span class="roomicon"><img src="/trms/resources/images/roomicon.png"width="43px"height="43px"/></span>
						 <span class="roomNum"><span>${room.item }</span></span>

						 <button id="cal1" class="btn btnCalendar">
						 <span class="calIcon">
						 <img class="calImg" src="/trms/resources/images/calendaricon.png"width="20px"height="20px"/>
						 </span>
						 <span class="calText">Calendar</span>
						 </button>

						 <div id="calArea1"class="calArea">						 
							  <iframe id="iframe1" name="calendar"width="100%"height="100%"frameborder="0"style="overflow:auto;"scrolling="no"></iframe>
						 </div>
					 </li>
				</c:forEach> 
				 </ul>
				
					 
				 
			 </div>
		 </div>
	 </div>
	 <div class="footer">
	     <div class="footerContainer">
		 </div>
	 </div>
 </div>
 
</body>
</html>