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
<link rel="icon" href="<%=request.getContextPath()%>/resources/images/hsbcicon.ico" type="image/x-icon"/>
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
  <link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
 <script src="<%=request.getContextPath()%>/resources/js/lcIndex.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
var pageIndex = 0;
var pageSize = 5;
$(function() {
	pageIndex = 1;
	AjaxGetData(pageIndex, pageSize);
});

function AjaxGetData(index, size) {

	var params="{pageNum:index,pageSize:size,optionStr:'${optionStr}'}";
		
	$.ajax({
				url : "<%=request.getContextPath()%>/reservation/listPageReservation",
				//type : "get",
				//data : "pageNum=" + index + "&pageSize=" + size+"&optionStr="+"${optionStr}",	
				type : "post",
				data: "{pageNum:"+index+",pageSize:"+size+",optionStr:${optionStr}}", 
				dataType : "json",
				success : function(json) {
					var html="";
					for (position in json) {	
									
						var reservation_ID=json[position].reservation.reservation_ID;
						var reservation_start_Daty=json[position].reservation.applied_Start_Date;
						var reservation_end_Daty=json[position].reservation.applied_End_Date;
						var reservation_Num=json[position].reservation.reservation_Num;
						var purpose=json[position].reservation.purpose;
						var email=json[position].reservation.email;
						var tele=json[position].reservation.tele;
						var Order_Date=json[position].reservation.order_Time;
						var handler_by=json[position].reservation.handle_by;
						
						var team_ID=json[position].team.team_ID;
						var order_Team=json[position].team.teamName;
						
						var room_ID=json[position].room.room_ID;
						var room_item=json[position].room.item;
						
						var applicant_ID=json[position].user.user_ID;
						var applicant_account=json[position].user.account;
																	
						html+=("reservation_ID:"+reservation_ID+"<br/>");
						html+=("reservation_start_Daty:"+reservation_start_Daty+"<br/>");
						html+=("reservation_end_Daty:"+reservation_end_Daty+"<br/>");
						html+=("reservation_Num:"+reservation_Num+"<br/>");
						html+=("purpose:"+purpose+"<br/>");
						html+=("email:"+email+"<br/>");
						html+=("tele:"+tele+"<br/>");
						html+=("Order_Date:"+Order_Date+"<br/>");
				
						html+=("team_ID:"+team_ID+"<br/>");	
						html+=("order_Team:"+order_Team+"<br/>");	
						
						html+=("room_ID:"+room_ID+"<br/>");	
						html+=("room_item:"+room_item+"<br/>");
				
						html+=("applicant_ID:"+applicant_ID+"<br/>");	
						html+=("applicant_account:"+applicant_account+"<br/>");
						
						html+=("handler_by:"+handler_by+"<br/>");
						html+=("Click <a href='<%=request.getContextPath()%>/reservation/edit?"
								+"reservation_ID="+reservation_ID
								+"&room_ID="+room_ID
								+"&team_ID="+team_ID
								+"&applicant_ID="+applicant_ID
								+"&handler_by="+handler_by
								+"'> Here </a> to get more Information<br/>");
						
					html+=("<hr/>");
					}					
					
					$('#reservationInfo').html("");
	                $('#reservationInfo').html(html); 
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
				     <a href="<%=request.getContextPath()%>/room/list" class="navItem">Book Room</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/reservation/list" class="navItem selected">Check My Reservation</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/reservation/delete" class="navItem">Delete My Reservation</a>
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

<!-- --------------------------------------- -->

                                               	<h1>高级查询</h1>		
	<form action="<%=request.getContextPath()%>/reservation/list" method="get">
		Reservation Number:
		<input type="text" name="reservation_Num"/><br/>
		Booked Time:
		<input type="date" name="order_Time"/><br/>
		Ticket Status:
		<select name="status">
			<option></option>
			<option value="1">accept</option>
			<option value="-1">refuse</option>
			<option value="0">unhandle</option>
		</select>
		Room：
		<select name="room_ID">
		<option></option>
			<c:forEach var="room" items="${rooms}">	
				<option value='${room.room_ID}'>${room.item}</option>
			</c:forEach>		
		</select><br/>
		Planned Use Start Time：
		<input type="date" name="Applied_Start_Date"/><br/>
		Planned Use End Time：
		<input type="date" name="Applied_End_Date"/><br/>
		User LN:
		<input type="text" name="email"/><br/>
		User Team:
		<select name="team_ID">
			<option></option>
			<c:forEach var="team" items="${teams}">	
				<option value='${team.team_ID}'>${team.teamName}</option>
			</c:forEach>
		</select><br/>
		User TelLine:
		<input type="text" name="tele"/><br/>
		Use Resaon:
		<input type="text" name="purpose"/>
		<input type="submit"name="submit" value="submit"/>
		<hr/>
		</form>
			<h1> 显示查询结果</h1> 
		 	     <ul>			 
				     <div id="reservationInfo">
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

<!-- --------------------------------------- -->
			 
			 
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
