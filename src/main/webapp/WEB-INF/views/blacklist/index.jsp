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
			$("#teams").empty(); 
			return false;
		}	
	    getDepartmentAllteam(department_ID);
	  });
	});

function getDepartmentAllteam(department_ID){
 	$.ajax({
		url : "<%=request.getContextPath()%>/team/getDepartmentAllteam",
		type : "Get",
		data : "department_ID=" + department_ID,
		dataType : "json",
		success : function(json) {
			$("#teams").empty();
			$("#teams").append("<option value=''></option>");
			for (position in json) {				
					var team_ID=json[position].team_ID;
					var department_ID=json[position].department_ID;
					var teamName=json[position].teamName;
					//添加元素，使得option可以选择				
					$("#teams").append("<option value='"+team_ID+"'>"+teamName+"</option>");
			}			
         },
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest);
			alert(textStatus);
			alert(errorThrown);
		}
	});
}
function displayBlackList(){
	var team_ID=$("#teams").val();	
	var departmentName=$("#departments option:selected").text();
	var teamName=$("#teams option:selected").text();
	if(team_ID==""){
		$("#display").empty();
		return false;
	}
	$.ajax({
		url : "<%=request.getContextPath()%>/blacklist/getReasonByTeam",
		type : "Get",
		data : "team_ID=" + team_ID,
		dataType : "json",
		success : function(json) {
			$("#departmentName").empty();
			$("#teamName").empty();
			$("#reason").empty();	
			$("#departmentName").append("departmentName:"+departmentName);
			$("#teamName").append("teamName"+teamName);
			$("#reason").append("reason:"+json.reason);
			
         },
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(team_ID);
			alert(XMLHttpRequest);
			alert(textStatus);
			alert(errorThrown);
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
				     <a href="<%=request.getContextPath()%>/admin/index" class="navItem selected">Book Room</a>
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
				     <a href="<%=request.getContextPath()%>/room/roommanager"class="navItem">Room Management</a>
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
			 	<h1>黑名单管理界面</h1>
				<h3>传入的是部门信息，当点击部门的时候,显示出选择team的框，会ajax调用获取team信息，当点击选中team后会ajax 查新当前的黑名单情况，接口已经定义</h3>				
					departments
					<select name="departments" id="departments">
						<option value=""></option>
						<c:forEach items="${departments}" var="department">
							<option value="${department.department_ID}">${department.departmentName}</option>
						</c:forEach>
					</select>
					teams
					<select name="team_ID" id="teams">
						<option value=""></option>
					</select>
					<button name="checkBtn" id="checkBtn" onClick="displayBlackList()">check</button>
					<hr/>	
					<div id="display">
						<h1>BlackList Detial</h1>
						<h2 id="departmentName"></h2>
						<h2 id="teamName"></h2>
						<textarea id="reason"></textarea>
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
