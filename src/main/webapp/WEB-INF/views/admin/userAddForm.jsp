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
						$("#users").empty();
						$("#users").append("<option value='"+user_ID+"'>"+account+"</option>");
				}			
             },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest);
				alert(textStatus);
				alert(errorThrown);
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
					$("#teams").empty(); 
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

</script>
</head>
<body>


 
</head>
<body>
<h1>添加LC 普通用户角色的界面，用户必须是某个部门里面的人才有机会成为LC</h1>
<form action="" method="post">
departments
<select name="departments" id="departments">
	<option value=""></option>
	<c:forEach items="${departments}" var="department">
		<option value="${department.department_ID}">${department.departmentName}</option>
	</c:forEach>
</select>
users
<select name="users" id="users">
	<option value=""></option>
</select>
teams
<select name="teams" id="teams">
	<option value=""></option>
</select>

<input type="submit" name="submit" value="submit"/>
</form>	
</body>
</html>