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
<link href="<%=request.getContextPath()%>/resources/css/roomManagement.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
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
			var html="";
			for (position in json) {					
					var team_ID=json[position].team_ID;
					var department_ID=json[position].department_ID;
					var teamName=json[position].teamName;
					//添加元素，使得option可以选择
					html+="team_ID:"+teamName;								
					html+="<a href='<%=request.getContextPath()%>/team?team_ID="+team_ID+"'>edit</a>";
					html+="<br/>";
			}	
			$("#display").empty();
			$("#display").html(html);
       },
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
		}
	});
}

</script>
</head>

<body>



 			 <div class="roomListBody"> 
 			 <h1>team管理界面</h1>							
					departments
					<select name="departments" id="departments">
						<option value=""></option>
						<c:forEach items="${departments}" var="department">
							<option value="${department.department_ID}">${department.departmentName}</option>
						</c:forEach>
					</select>
						<button>add Team</button>
					<hr/>
					<!-- 显示信息区域 -->
					teams Information	
					<div id="display"></div>
						
			 </div>

</body>
</html>




