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
<link rel="icon" href="..resources/images/hsbcicon.ico" type="image/x-icon"/>

  <title> LC Page </title>
 <link href="..resources/css/lcIndex.css" rel="stylesheet" >
 <script src="..resources/js/lcIndex.js" type="text/javascript"></script>
 <script src="..resources/js/jquery.js" type="text/javascript"></script>

</head>
<body>
	<security:authentication property="principal.username"></security:authentication> 
	<c:url value="/j_spring_security_logout" var="logoutUrl"/>  
<li><a href="${logoutUrl}">Log Out</a></li> 


<div class="cover">
 </div>
 <div class="wrapper">
     <div class="header">
	     <div class="headerContainer">
		 <span id="logo"><img src="..resources/images/hsbclogo.png"/></span>
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

				 <ul>
				     <li id="list1"class="roomListContent">
					     <span class="roomListNum">1</span>
						 <span class="roomicon"><img src="..resources/images/roomicon.png"width="43px"height="43px"/></span>
						 <span class="roomNum"><span>TKH 0T2 /</span><strong>22.5</strong></span>

						 <button id="cal1" class="btn btnCalendar">
						 <span class="calIcon">
						 <img class="calImg" src="..resources/images/calendaricon.png"width="20px"height="20px"/>
						 </span>
						 <span class="calText">Calendar</span>
						 </button>

						 <div id="calArea1"class="calArea">
						 
							  <iframe id="iframe1" name="calendar"width="100%"height="100%"frameborder="0"style="overflow:auto;"scrolling="no"></iframe>
						 </div>


					 </li>
					 <li id="list2" class="roomListContent">
					 <span class="roomListNum">2</span>
						 <span class="roomicon"><img src="..resources/images/roomicon.png"width="43px"height="43px"/></span>
						 <span class="roomNum"><span>TKH 0T2 /</span><strong>24.5</strong></span>

						 <button id="cal2"class="btn btnCalendar">
						 <span class="calIcon">
						 <img class="calImg" src="..resources/images/calendaricon.png"width="20px"height="20px"/>
						 </span>
						 <span class="calText">Calendar</span>
						 </button>

						 <div id="calArea2"class="calArea">
						       <iframe id="iframe2" name="calendar"width="100%"height="100%"frameborder="0"style="overflow:auto;"scrolling="no"></iframe>
						 </div>
					 </li>
					 <li id="list3" class="roomListContent">
					 <span class="roomListNum">3</span>
						 <span class="roomicon"><img src="..resources/images/roomicon.png"width="43px"height="43px"/></span>
						 <span class="roomNum"><span>TKH 0T2 /</span><strong>25.5</strong></span>

						 <button id="cal3"class="btn btnCalendar">
						 <span class="calIcon">
						 <img class="calImg" src="..resources/images/calendaricon.png"width="20px"height="20px"/>
						 </span>
						 <span class="calText">Calendar</span>
						 </button>

						 <div id="calArea3"class="calArea">
						      <iframe id="iframe3" name="calendar"width="100%"height="100%"frameborder="0"style="overflow:auto;"scrolling="no"></iframe>
						 </div>
					 </li>
					 <li id="list4" class="roomListContent">
					 <span class="roomListNum">4</span>
						 <span class="roomicon"><img src="..resources/images/roomicon.png"width="43px"height="43px"/></span>
						 <span class="roomNum"><span>TKH 0T2 /</span><strong>26.5</strong></span>

						 <button id="cal4"class="btn btnCalendar">
						 <span class="calIcon">
						 <img class="calImg" src="..resources/images/calendaricon.png"width="20px"height="20px"/>
						 </span>
						 <span class="calText">Calendar</span>
						 </button>

						 <div id="calArea4"class="calArea">
						      <iframe id="iframe4" name="calendar"width="100%"height="100%"frameborder="0"style="overflow:auto;"scrolling="no"></iframe>
						 </div>
					 </li>
					 <li id="list5" class="roomListContent">
					 <span class="roomListNum">5</span>
						 <span class="roomicon"><img src="..resources/images/roomicon.png"width="43px"height="43px"/></span>
						 <span class="roomNum"><span>TKH 0T2 /</span><strong>27.5</strong></span>

						 <button id="cal5"class="btn btnCalendar">
						 <span class="calIcon">
						 <img class="calImg" src="..resources/images/calendaricon.png"width="20px"height="20px"/>
						 </span>
						 <span class="calText">Calendar</span>
						 </button>

						 <div id="calArea5"class="calArea">
						      <iframe id="iframe5" name="calendar"width="100%"height="100%"frameborder="0"style="overflow:auto;"scrolling="no"></iframe>
						 </div>
					 </li>
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