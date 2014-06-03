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
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
<script src="<%=request.getContextPath()%>/resources/js/lcIndex.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	  $("#departments").change(function(){
		var department_ID=$("#departments").val();
		if(department_ID==""){
			$("#display").empty(); 
			return false;
		}	
		getRoomInfo(department_ID);
	  });
	});

function getRoomInfo(department_ID){
 	$.ajax({
		url : "<%=request.getContextPath()%>/room/getRoomsBydepartment",
		type : "Get",
		data : "department_ID=" + department_ID,
		dataType : "json",
		success : function(json) {
			$("#display").empty();
			for (position in json) {			
					var room_ID=json[position].room_ID;
					var item=json[position].item;
					var last_Used_Date=json[position].last_Used_Date;
					var department_ID=json[position].department_ID;
					var room_Status=json[position].room_Status;
					
					$("#display").append("room_ID:"+room_ID);
					$("#display").append("item:"+item);
					$("#display").append("last_Used_Date:"+last_Used_Date);
					$("#display").append("department_ID:"+department_ID);
					$("#display").append("room_Status:"+room_Status);
					$("#display").append("<a href='#'>delete</a> ");
					$("#display").append("<a href='#'>edit</a><br/>");
			}			
         },
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("exception");
		}
	});
}


</script>
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
				     <a href="<%=request.getContextPath()%>/team/index"class="navItem">LC Management </a>
				 </li>
				  <li>
				     <a href="<%=request.getContextPath()%>/room/roommanager"class="navItem selected">Room Management</a>
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
			 	<h1>Room 房间管理界面</h1>
				<h3>传入的是部门信息，当点击部门的时候,会ajax显示当前选择部门的 room,支持批量处理删除room</h3>				
					departments
					<select name="departments" id="departments">
						<option value=""></option>
						<c:forEach items="${departments}" var="department">
							<option value="${department.department_ID}">${department.departmentName}</option>
						</c:forEach>
					</select>
					<button name="checkBtn" id="checkBtn" onClick="displayBlackList()">check</button>					
					<button name="deleteBtn" id="deleteBtn" onClick="deleteBlackList()">delete</button>
					<hr/>
					<!-- 显示信息区域 -->	
					Room:<div id="display"></div>
						
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
