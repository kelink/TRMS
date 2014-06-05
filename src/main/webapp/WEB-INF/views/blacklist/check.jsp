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
			alert("error");
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
			$("#display").empty();
			$("#display").append("<form action='#' method='post' name='updateForm' id='updateForm'>");
			$("#updateForm").append("<p>departmentName:"+departmentName+"</p>");
			$("#updateForm").append("<p>teamName"+teamName+"</p>");
			$("#updateForm").append("reason:<textarea id='reason_area' readonly='readonly' name='reason'>"+json.reason+"</textarea>");
			$("#updateForm").append("<button name='updateBlackListBtn' id='updateBlackListBtn' onClick='updateBlackList("+json.bl_ID+")'>edit</button>");
			$("#updateForm").append("<button name='deleteBlackListBtn' id='deleteBlackListBtn' onClick='deleteBlackList("+json.bl_ID+")'>delete</button>");			
			$("#updateForm").append("</form>");
         },
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#display").empty();
		}
	});	
}

function updateBlackList(bl_ID){
	$("#reason_area").attr('readonly','');
	$("#updateBlackListBtn").remove();
	$("#updateForm").attr('action','<%=request.getContextPath()%>/blacklist/update');
	$("#reason_area").after("<input id='hidden' type='hidden' name='bl_ID' value='"+bl_ID+"'/>");
	$("#hidden").after("<input type='submit' value='update' /></form>");
	window.location.reload();//刷新当前界面
}

function deleteBlackList(bl_ID){
	$.ajax({
		url : "<%=request.getContextPath()%>/blacklist/delete",
		type : "Get",
		data : "bl_ID=" + bl_ID,
		success : function(json) {
			alert("test"+json);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#display").empty();
			alert("error");
		}
	});	
	window.location.reload();//刷新当前界面
	
}



</script>
</head>
</head>
<body>	
					<h1>黑名单管理界面</h1>							
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
					<!-- 显示信息区域 -->	
					<div id="display"></div>
	
</body>
</body>
</html>
