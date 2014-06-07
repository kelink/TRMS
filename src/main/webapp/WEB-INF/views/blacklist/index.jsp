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
<title> Blacklist Management </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/resources/images/hsbcicon.ico" type="image/x-icon"/>
<link href="<%=request.getContextPath()%>/resources/css/blacklist.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
<script src="<%=request.getContextPath()%>/resources/js/lcIndex.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
function changeFrame(args){
	if(args==0){
		$("#search0").attr("class","checkMethod selected");
		$("#search1").attr("class","checkMethod");	
		$("#search2").attr("class","checkMethod");
		$("#checkRightInner").attr("src","<%=request.getContextPath()%>/blacklist/list");
	}
	if(args==1){
		$("#search0").attr("class","checkMethod");
		$("#search1").attr("class","checkMethod selected");	
		$("#search2").attr("class","checkMethod");
		$("#checkRightInner").attr("src","<%=request.getContextPath()%>/blacklist/getAddForm");
	}
	if(args==2){
		$("#search0").attr("class","checkMethod");
		$("#search1").attr("class","checkMethod");	
		$("#search2").attr("class","checkMethod selected");
		$("#checkRightInner").attr("src","<%=request.getContextPath()%>/blacklist/getCheckPage");
	}
}	
	
	
	
</script>

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
				     <a href="<%=request.getContextPath()%>/blacklist/index" class="navItem selected">BlackList Management</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/department/index"class="navItem">Department Management </a>
				 </li>			
				  <li>
				     <a href="<%=request.getContextPath()%>/profile/modify"class="navItem">Manage My Profile</a>
				 </li>
			 </ul>
		 </div>
	 </div>
	 
	 <div class="middle">
	     <div class="middleContainer">
		     <h1 class="roomList">Blacklist Management</h1>
			 <div class="roomListBody" style="overflow:hidden;">
			 
			     <div id="checkLeftPanel">
			      <!-- handle  begin -->
					 <div id="search0" class="checkMethod selected" onClick="changeFrame(0)">
			           	All Blacklists
			         </div>
			         <div id="search1" class="checkMethod" onClick="changeFrame(1)">
			           Add BlackLists
			         </div>
			         <div class="seperateLineSearch">
			         </div>
			         <div id="search2" class="checkMethod" onClick="changeFrame(2)">		             
			           Check BlackLists
			         </div>
			      
			     </div>
			     <div id="checkRightPanel">
			         <iframe id="checkRightInner" name="checkRightInner" src="<%=request.getContextPath()%>/blacklist/list" >
			         </iframe>			     
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

</html>