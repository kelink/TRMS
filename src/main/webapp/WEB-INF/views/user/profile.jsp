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

  <title> LC Page </title>
 <link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
 <link href="<%=request.getContextPath()%>/resources/css/modifyProfile.css" rel="stylesheet" >
 <script src="<%=request.getContextPath()%>/resources/js/lcIndex.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/resources/js/modifyProfile.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
 
</head>

<body>
<%-- <security:authentication property="principal.username"/>  --%>
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
				     <a href="<%=request.getContextPath()%>/room/list" class="navItem selected">Book Room</a>
				 </li>
				 <li>
				     <a href="lcRoomCheck.html"class="navItem">Check My Reservation</a>
				 </li>
				 <li>
				     <a href="lcRoomBook.html" class="navItem">Delete My Reservation</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/profile/modify"class="navItem">Manage My Profile</a>
				 </li>
			 </ul>
		 </div>
	 </div>
	 
	 <div class="middle">
	     <div class="middleContainer">
		     <h1 class="roomList">Room List</h1>
			 <div class="roomListBody">
			  	 <div id="profileHeader">
			  	     <div id="profileTopic">
			  	         <span id="profileTopicText">Personal Information</span>
			  	     </div>
			  	     
			  	     <div id="profileLine">
			  	         
			  	     </div>
			  	         
			  	     <div id="profileBtn">
			  	         <span id="alterBtn">alter</span>
			  	     </div>
			  	     
			  	 </div>
			  	 <div id="profileContent">
			  	     <div id="profileContentLeft">
			  	         <div id="picture">
			  	             <img height="150px" width="150px" src="<%=request.getContextPath()%>/resources/images/person.jpg"/>
			  	         </div>
			  	         
			  	     </div>
			  	     
			  	     <div id="profileContentRight">
			  	     
			  	     <form method="post" action="">
			  	     <div class="infoWrapper">
			  	     
			  	     <div class="infoRow">
			  	     <div class="label">Login Name:</div>
			  	     <div class="userInfo"><security:authentication property="principal.username"/></div>
			  	     <div><a id="chPassword" href="#" >Change Password</a></div>
			  	     </div>
			  	     
			  	     <div class="infoRow">
			  	     <div class="label">Role:</div>
			  	     <div class="userInfo"></div>
			  	     </div>
			  	     
			  	     <div class="infoRow">
			  	     <div class="label">Gender:</div>
			  	     <div class="userInfo"></div>
			  	     </div>
			  	     
			  	     <div class="infoRow">
			  	     <div class="label">Team:</div>
			  	     <div class="userInfo"></div>
			  	     </div>
			  	     
			  	     <div class="infoRow">
			  	     <div class="label">Email:</div>
			  	     <div class="userInfo"></div>
			  	     </div>
			  	     
			  	     <div class="infoRow">
			  	     <div class="label">Telephone:</div>
			  	     <div class="userInfo"></div>
			  	     </div>
			  	     
			  	     </div>
			  	     </form>
			  	     
			  	     </div>
			  	 
			  	 </div>
			  	 
			  	 
			  	 
		     </div>
		 </div>
	 </div>
	 <div class="footer">
	     <div class="footerContainer">
		 </div>
	 </div>
 </div>
 
 
 
 

<!-- <div id="footer"> -->
<!--   <div id="footer-inner"> -->
<!--     <div class="client-say"> -->
<!--       <h3>Training Room Service</h3> -->
<%--       <img src="<%=request.getContextPath()%>/resources/images/client.jpg" alt="" class="flleft grayborR" /> --%>
<!--       <font size="2" color=""> -->
<!-- 	  <br> -->
<!-- 	  <p>敬请期待更多翻译系统服务</p> -->
<!-- 	  <p>我们的服务，你值得拥有！</p> -->
<!-- 	  </font> -->
<!--     </div> -->
<!--     <div class="footer-details"> -->
<!--       <h3>Contact Us</h3> -->
<!-- 	  <br> -->
<%--       <div class="address"><img alt="" src="<%=request.getContextPath()%>/resources/images/address.png"> <strong>Address:</strong> GDUFS</div> --%>
<%--       <div class="address"><img height="18" width="14" alt="" src="<%=request.getContextPath()%>/resources/images/phone.png"> <strong>Phone:</strong> 110</div> --%>
<%--       <div class="address"><img height="12" width="15" alt="" src="<%=request.getContextPath()%>/resources/images/mail.png"> <strong>Email:</strong> <a href="#">gdufs@163.com</a></div> --%>
<!--     </div> -->
<!--     <div class="footer-details no-margin"> -->
<!--       <h3>Link</h3> -->
	 
<!--       <p> <a href="http://www.baidu.com"><font size="2" color="white">Baidu</font></a></p> -->
<!-- 	  <br> -->
<!-- 	  <p> <a href="http://www.google.com"><font size="2" color="white">Google</font></a></p> -->
<!-- 	  <br> -->
<!-- 	  <p> <a href="#"><font size="2" color="white">HSBC</font></a></p> -->
<!--     </div> -->
<!--   </div> -->

<!--   <div class="Finfo"> -->
<!--     <div class="copyright"> &copy; Copyright 2013 by <a href="#">gdufs.com</a></div> -->
<!--   </div> -->
<!-- </div> -->
 
</body>
</html>