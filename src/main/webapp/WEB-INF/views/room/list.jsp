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
 <script src="<%=request.getContextPath()%>/resources/js/lcIndex.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
 <script type="text/javascript" >
 var pageIndex = 0;
 var pageSize = 5;
 $(function() {
 	pageIndex = 1;
 	AjaxGetData(pageIndex, pageSize);
 });

 function AjaxGetData(index, size) {
 	$
 			.ajax({
 				url : "<%=request.getContextPath()%>/room/listPageRoom",
 				type : "Get",
 				data : "pageNum=" + index + "&pageSize=" + size,
 				dataType : "json",
 				success : function(json) {
 					var html="";
 					for (position in json) {				
 						var room_ID=json[position].room_ID;
 						var item=json[position].item;
 						var room_Status=json[position].room_Status;
 						var last_Used_Date=json[position].last_Used_Date;
 						
 						html+="<li id='list"+room_ID+"'class='roomListContent'>";
 						html+="<span class='roomListNum'>"+room_ID+"</span>";
 						html+="<span class='roomicon'><img src='/trms/resources/images/roomicon.png' width='43px' height='43px'/></span>";
 						html+="<span class='roomNum'><span>"+item+"</span></span>";
 											
 						html+="<button id='cal"+room_ID+"' class='btn btnCalendar' onclick='displayCal("+room_ID+")'>";
 						html+="<span class='calIcon'>";
 						html+="<img class='calImg' src='/trms/resources/images/calendaricon.png'"+"width='20px' height='20px'/>";
 						html+=" </span>";
 						html+="<span class='calText'>Calendar</span>";
 						html+=" </button>";
	
 						html+=" <div id='calArea"+room_ID+"' class='calArea'>";
 						html+= "<iframe class='iframe' id='iframe"+room_ID+"' name='calendar' width='100%' height='100%' frameborder='0' style='overflow:auto;'scrolling='no'></iframe>";
 						html+="</div>";
 						html+=" </li>";         
					
 					}
 					$('#roomInfo').html("");
 	                $('#roomInfo').html(html); 
 	                $('#pageIndex').html("");
 	               $('#pageIndex').html(pageIndex);
 	                },
 				error : function(XMLHttpRequest, textStatus, errorThrown) {
 					alert(XMLHttpRequest);
 					alert(textStatus);
 					alert(errorThrown);
 				}
 			});
 }

 function GoToFirstPage() {
 	pageIndex = 1;
 	AjaxGetData(pageIndex, pageSize);
 }

 function GoToPrePage() {
 	pageIndex -= 1;
 	pageIndex = pageIndex >= 1 ? pageIndex : 1;
 	AjaxGetData(pageIndex, pageSize);
 }

 function GoToNextPage() {
 	if (pageIndex < parseInt($("#count").text())) {
 		pageIndex += 1;
 		AjaxGetData(pageIndex, pageSize);
 	}	
 }

 function GoToEndPage() {
 	pageIndex = parseInt($("#count").text());
 	AjaxGetData(pageIndex, pageSize);
 }

 function GoToAppointPage(e) {
 	var page = $(e).prev().val();
 	if (isNaN(page)) {
 		alert("Page should be a valid number");
 	} else {
 		var tempPageIndex = pageIndex;
 		pageIndex = parseInt($(e).prev().val());
 		if (pageIndex <= 0 || pageIndex > parseInt($("#count").text())) {
 			pageIndex = tempPageIndex;
 			alert("Please input valid page scope!");
 		} else {
 			AjaxGetData(pageIndex, pageSize);
 		}
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
			  	 <div class="roomListHeader">
				     <div class="roomListTitle">
					     Room
					 </div>					 
				 </div>
 
					<!-- 显示room的信息 -->
					
				 <ul>			 
				    <div id="roomInfo">
				    </div>				
				 </ul>
				 <div id="listInfoWrapper">
				     <div id="listInfo">
					    Total Page:<span id='count'>${pageCount}</span>
					    Total Records:<span id='recordCount'>${recordCount}</span>
				    </div>
				    
				    <div id="jump">
					     <a href='javascript:void' onclick='GoToFirstPage()' id='FirstPage' ><img class="pageIcon"alt="Go to first page" src="<%=request.getContextPath()%>/resources/images/first.png"></a>
					     <a href='javascript:void' onclick='GoToPrePage()' id='PrePage' ><img class="pageIcon"alt="Go to previous page" src="<%=request.getContextPath()%>/resources/images/prepage.png"></a>
					     <span id="pageIndex"></span>
					     <a href='javascript:void' onclick='GoToNextPage()' id='NextPage' ><img class="pageIcon"alt="Go to next page" src="<%=request.getContextPath()%>/resources/images/nextpage.png"></a>
					     <a href='javascript:void' onclick='GoToEndPage()' id='EndPage' ><img class="pageIcon" alt="Go to last page"src="<%=request.getContextPath()%>/resources/images/end.png"></a>
					     <input type='text' size='4' name='page' />
					     <input class="btnJump" type='button' value='Jump' onclick='GoToAppointPage(this)' />
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
 <%@ include file="footer.jsp"%>
 
 
 

<!--  <div id="footerContainer"> -->
<!--       <div id="footer"> -->
<!--           <span id="copyright"> &copy; Copyright 2014 by <a href="#">Lin Jiajian</a>&<a href="#">Luo Kelin</a></span> -->
<%--           <span><img id="hsbcIconFooter" width="30px"height="15px" src="<%=request.getContextPath() %>/resources/images/hsbcFooter.png"/></span> --%>
<!--           <span id="footerWord"><a href="#">Team</a> <a href="#">Telephone</a><a href="#">Email</a><a href="#">about</a></span> -->
<!--       </div> -->
  
 </div>

</body>
</html>