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
	  });
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
			for (position in json) {					
					var team_ID=json[position].team_ID;
					var department_ID=json[position].department_ID;
					var teamName=json[position].teamName;
					var account=json[position].user_Account;
					//添加元素，使得option可以选择
					html+="team_ID:"+teamName+"<br/>";
					html+="account:"+account+"<br/>";
					html+='<button onclick="showEdit('+team_ID+',\''+teamName+'\''+',\''+departmentName+'\')">'+"edit"+'</button>';			
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
function showEdit(team_ID, teamName,departmentName) {
	$("#tipsWrapper").css("display", "block");
	$("#departmentName").attr("value", departmentName);
	$("#team_item").attr("value", teamName);
	$("#team_ID").attr("value", team_ID);
}
function back() {
	$("#tipsWrapper").css("display", "none");

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
<!-- top -->
<div id="tipsWrapper">
<div class="panelHeader">
<span id="headerText">Modify Team</span>
<span id="close"onclick="back();"><img id="closeIcon" src="<%=request.getContextPath()%>/resources/images/closeShallow.png"width="16px"height="16px"/></span>
</div>
<!-- content -->
        <div id="modifyWrapper">
	    
		<form action="<%=request.getContextPath()%>/reservation/update" method="post">
		<br/>
		<h2>Department Name:<input type="text" id="departmentName" name="departmentName" readOnly="true"/></h2>
		<br/>
		<h2>Team Item：<input type="text" id="team_item" name="team_item"/><br/></h2>
	
		<input type="hidden" id="team_ID" name="team_ID"/>
		<div id="btnWrapper">

		<input class="btnUpdate" type="submit"name="submit" id="btnUpdate"  value="update"/>
		<a class="btnDelete" href="#" onclick="deleteReservation(${reservation.reservation_ID})">delete</a>
		</div>
		</form>
        </div>
	<br/>

</div>
 			

</body>
</html>
