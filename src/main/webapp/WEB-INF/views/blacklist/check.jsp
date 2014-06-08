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
<link href="<%=request.getContextPath()%>/resources/css/checkBlacklist.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/blacklistform.css" rel="stylesheet">
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
	//var departmentName=$("#departments option:selected").text();
	//var teamName=$("#teams option:selected").text();
	if(team_ID==""){
		$("#display").css("display","none");
		$("#noInfo").css("display","block");
		return false;
	}
	$.ajax({
		url : "<%=request.getContextPath()%>/blacklist/getReasonByTeam",
		type : "Get",
		data : "team_ID=" + team_ID,
		dataType : "json",
		success : function(json) {
			if(json!=""){
				$("#display").css("display","block");
				$("#noInfo").css("display","none");
				$("textArea").attr("readonly","readonly");
				$("textArea").attr("value",json.reason);	
				$("#updateBlackListBtn").attr("onClick","return updateBlackList("+json.bl_ID+")");
				$("#deleteBlackListBtn").attr("onClick","deleteBlackList("+json.bl_ID+")");
				
			}else{
				$("#noInfo").css("display","block");
				$("#display").css("display","none");
			}			
         },
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#noInfo").css("display","block");
			$("#display").css("display","none");
		}
	});	
}

function updateBlackList(bl_ID){
	var arg=$("#updateBlackListBtn").val();
	if(arg=="edit"){
		$("#textArea").attr('readonly','');
		$("#updateBlackListBtn").attr("value","update");
		$("#updateForm").attr('action','<%=request.getContextPath()%>/blacklist/update');
		$("#textArea").after("<input id='hidden' type='hidden' name='bl_ID' value='"+bl_ID+"'/>");
		return false;
	}

}

function deleteBlackList(bl_ID){
	$.ajax({
		url : "<%=request.getContextPath()%>/blacklist/delete",
		type : "Get",
		data : "bl_ID=" + bl_ID,
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
<div class="listBody">

</div>
					<h1>Check blacklist</h1>							
					<div class="checkItem">departments</div>
					<select name="departments" id="departments">
						<option value=""></option>
						<c:forEach items="${departments}" var="department">
							<option value="${department.department_ID}">${department.departmentName}</option>
						</c:forEach>
					</select>
					<div class="checkItem">teams</div>
					<select name="team_ID" id="teams">
						<option value=""></option>
					</select>
					<button name="checkBtn" id="checkBtn" onClick="displayBlackList()">check</button>				
					<hr/>
					<!-- 显示信息区域 -->	
					<div id="noInfo">
					    <p>No information to show.</p>
					</div>
					<div id="display" style="display:none">
						<form action="/trms/blacklist/add" method="post" id='updateForm'>
							<div id="tableWrapper">
								<table>
								<tbody>							
									<tr>
										<td class="leftTd">Reason</td>
										<td class="rightTd">
											<textarea id="textArea" class="rightInput" name="reason"></textarea>
										</td>
									</tr>
									<tr>
										<td class="btnWrapper">
											<input class="btnUpdate reservationLcBtn" type="submit" name="submit" id="updateBlackListBtn" value="edit">
										</td>
										<td class="btnWrapper">
											<a class="btnDelete reservationLcBtn" id="deleteBlackListBtn">Delete</a>
										</td>
									</tr>
								</tbody>
								</table>
							</div>
						</form>				
					</div>
	
</body>
</body>
</html>
