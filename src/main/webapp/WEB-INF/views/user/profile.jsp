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
  <link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
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
			  	         <div id="chPwd"><a id="chPassword"class="btnChPwd" href="#" >Change Password</a></div>
			  	        
			  	         <div id="chPwdPanel">
			  	             <div class="panelHeader">
                                 <span id="headerText">Change Password</span>
                                 <span id="close"><img id="closeIcon" src="<%=request.getContextPath()%>/resources/images/closeShallow.png"width="16px"height="16px"/></span>
                             </div>
                             
                             <div>
                                 <form action="" method="post">
                                     <div>
                                         Old password
                                     </div>
                                     <input type="password" >
                                     <div>
                                         New password
                                     </div>
                                     <input type="password">
                                     <div>
                                         Confirm new password
                                     </div type="password">
                                     <input type="password">
                                     
                                     <div>
                                         <input type="submit" value="Update">
                                     </div>
                                     
                                 </form>
                             </div>
                         </div>
                         
			  	     </div>
			  	     
			  	     <div id="profileContentRight">
			  	     
			  	     <form method="post" action="">
			  	     <div class="infoWrapper">
			  	     
			  	     <div class="infoRow">
			  	     <div class="label">Login Name:</div>
			  	     <div class="userInfo"><security:authentication property="principal.username"/></div>
			  	     
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
 
 
 
 
 <div id="footerContainer">
      <div id="footer">
          <span id="copyright"> &copy; Copyright 2014 by <a href="#">Lin Jiajian</a>&<a href="#">Luo Kelin</a></span>
          <span><img id="hsbcIconFooter" width="30px"height="15px" src="<%=request.getContextPath() %>/resources/images/hsbcFooter.png"/></span>
          <span id="footerWord"><a href="#">Team</a> <a href="#">Telephone</a><a href="#">Email</a><a href="#">about</a></span>
      </div>
 
</body>
</html>