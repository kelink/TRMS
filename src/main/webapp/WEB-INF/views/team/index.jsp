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
<link href="<%=request.getContextPath()%>/resources/css/teamEdit.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/teamEdit.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	  $("#departments").change(function(){
		var department_ID=$("#departments").val();	
		var departmentName=$("#departments  option:selected").text();
		if(department_ID==""){
			$("#teams").empty(); 
			return false;
		}	
	    getDepartmentAllteam(department_ID,departmentName);
	    $("#noInfo").css("display","none");
	  });
	  if(window.parent.name!="")
	  {
	
		  choose();
	  }
	
	  
	});

function getDepartmentAllteam(department_ID,departmentName){
	$.ajax({
		url : "<%=request.getContextPath()%>/team/getDepartmentAllteam",
		type : "Get",
		data : "department_ID=" + department_ID,
		dataType : "json",
		success : function(json) {
			$("#teams").empty();
			$("#teams").append("<option value=''></option>");
			var html="";
			html+="<table><tr><th>Team</th><th>Manager User</th><th>Operation</th></tr>";
			for (position in json) {					
					var team_ID=json[position].team_ID;
					var department_ID=json[position].department_ID;
					var teamName=json[position].teamName;
					var account=json[position].user_Account;
					//添加元素，使得option可以选择
					
					html+="<tr>";
					
					html+="<td>"+teamName+"</td>";
					html+="<td>"+account+"</td>";
					html+='<td><a class="operation" onclick="showEdit('+team_ID+',\''+teamName+'\''+','+department_ID+',\''+departmentName+'\')">'+"edit"+'</a></td>';			
				
					
					html+="</tr>";
			}	
			html+="</table>";
			$("#display").empty();
			$("#display").html(html);
       },
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
		}
	});
}
function showEdit(team_ID, teamName,department_ID,departmentName) {
	$("#tipsWrapper").css("display", "block");
	$("#departmentName").attr("value", departmentName);
	$("#teamName").attr("value", teamName);
	$("#team_ID").attr("value", team_ID);
	$("#department_ID").attr("value", team_ID);
	$("#deleteBtn").attr("onClick", "deleteTeam("+team_ID+")");
}
function back() {
	$("#tipsWrapper").css("display", "none");
}

function deleteTeam(team_ID){
	$.ajax({
		url : "<%=request.getContextPath()%>/team/deleteTeam",
		type : "Get",
		data : "team_ID=" + team_ID,
		dataType : "html",
		success : function(json) {
			alert(json);
			window.location.reload();//刷新当前界面
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
			window.location.reload();//刷新当前界面
		}
	});	
	
}
</script>
</head>

<body>

 <div class="roomListBody roomListBody1"> 
 			 <h1>Team Management</h1>	
 			 <div id="searchWrapper">						
					<div id="roomDeptment">departments</div> 
					<select name="departments" id="departments">
						<option value=""></option>
						<c:forEach items="${departments}" var="department">
							<option value="${department.department_ID}">${department.departmentName}</option>
						</c:forEach>
					</select>
						<button id="addTeamBtn">add Team</button>
					
						
						
						
			</div>
			<div id="showPanel2">
					<div class="panelHeader">
						<span class="headerText">Add team</span> <span class="close"
							id="closeShow2"><img class="closeIcon"
							src="<%=request.getContextPath()%>/resources/images/closeShallow.png"
							width="16px" height="16px" /></span>
					</div>
					<div id="panelContent2">
						<form action="<%=request.getContextPath()%>/team/addTeamForDepartment" method="post" >
						    <div class="addUserLine">
							<div class="addUserLabel">Team name:</div>
							<input class="addUserInput" name="teamName"id="team"  required >
							
							</div>
							
							<div class="addUserLine">
							<div class="addUserLabel">Department:</div>
							<select class="addUserInput" name="department_ID"id="department"  required >
							<option value=""></option>
							<c:forEach items="${departments}" var="department">
								<option value="${department.department_ID}">${department.departmentName}</option>
							</c:forEach>
							</select>
							</div>
							
							<br/>
						
							
							
							<div><input class="addUserSubmit btnAdd" type="submit" name="submit" id="submit" value="add"/></div>
							
							
						</form>
					
					</div>
				</div>
					<hr/>
					<!-- 显示信息区域 -->
				
					<!-- 				显示空信息 -->
				<div id="noInfo">
					    <p>No information to show.</p>
				</div>
				<div id="displayWrapper">
				    <div id="display"></div> 
				</div>
					
</div>
<!-- top -->
<div id="tipsWrapper">
<div class="panelHeader">
<span id="headerText">Modify Team</span>
<span id="close"onclick="back();"><img id="closeIcon" src="<%=request.getContextPath()%>/resources/images/closeShallow.png"width="16px"height="16px"/></span>
</div>
<!-- content -->
        <div id="modifyWrapper">
	    
		<form action="<%=request.getContextPath()%>/team/updateTeam" method="post">
		
		<div class="editTeamTitle">Department Name:</div>
		<div><input type="text" id="departmentName" class="teamEditContent"name="departmentName" readOnly="true"/></div>
		
		<div class="editTeamTitle">Team Item：</div>
		<div><input type="text" id="teamName" name="teamName"class="teamEditContent"/></div>
		<input type="hidden" id="department_ID" name="department_ID"/>
		<input type="hidden" id="team_ID" name="team_ID"/>
		<div id="btnWrapper">

		<input class="btnUpdate" type="submit"name="submit" id="btnUpdate"  value="update"/>
		<a class="btnDelete" id="deleteBtn">delete</a>
		</div>
		</form>
        </div>
	<br/>

</div>
 			

</body>
</html>
