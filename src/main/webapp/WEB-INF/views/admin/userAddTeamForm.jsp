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
			$("#users").empty();
			$("#teams").empty(); 
			return false;
		}
	    getDepartmentAllUser(department_ID);
	    getDepartmentAllteam(department_ID);
	  });
	});

function getDepartmentAllUser(department_ID){
 	$.ajax({
			url : "<%=request.getContextPath()%>/team/getDepartmentAllCommontUser",
			type : "Get",
			data : "department_ID=" + department_ID,
			dataType : "json",
			success : function(json) {
				if(json=="")
					$("#users").empty();
				for (position in json) {				
						var user_ID=json[position].user_ID;
						var department_ID=json[position].department_ID;
						var account=json[position].account;
						//添加元素，使得option可以选择
						$("#users").append("<option value='"+user_ID+"'>"+account+"</option>");
				}			
             },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error");
			}
		});
}
function getDepartmentAllteam(department_ID){
 	$.ajax({
		url : "<%=request.getContextPath()%>/team/getDepartmentAllteam",
		type : "Get",
		data : "department_ID=" + department_ID,
		dataType : "json",
		success : function(json) {
			for (position in json) {				
					var team_ID=json[position].team_ID;
					var department_ID=json[position].department_ID;
					var teamName=json[position].teamName;
					//添加元素，使得option可以选择
					$("#teams").append("<option value='"+team_ID+"'>"+teamName+"</option>");
			}			
         },
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
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
		     <h1 class="roomList">User List</h1>
			 <div class="roomListBody">
			 	<h1>LC用户添加管理界面</h1>
				<h3>传入的是部门信息，当点击部门的时候,显示出选择team的框，会ajax调用获取team信息，当点击选中team后会ajax 查新当前的黑名单情况，接口已经定义</h3>				
				<form action="" method="post">
					
					departments
					<select name="departments" id="departments">
						<option value=""></option>
						<c:forEach items="${departments}" var="department">
							<option value="${department.department_ID}">${department.departmentName}</option>
						</c:forEach>
					</select>
					<br/>
					users
					<select name="users" id="users">
						<option value=""></option>
					</select>
					<br/>
					teams
					<select name="teams" id="teams">
						<option value=""></option>
					</select>
					<br/>
					<input type="submit" name="submit" value="submit"/>
				</form>
				<div id="display">
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
