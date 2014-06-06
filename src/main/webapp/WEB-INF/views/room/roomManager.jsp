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
<script src="<%=request.getContextPath()%>/resources/js/roomManagement.js" type="text/javascript"></script>
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
function teamOperate(args){
	//delete team
	if(args==0){
		$.ajax({
			url : "<%=request.getContextPath()%>/room/getRoomsBydepartment",
			type : "Get",
			data : "department_ID=" + department_ID,
			dataType : "json",
			success : function(json) {
						
	         },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("exception");
			}
		});
	}
	if(args==1){
		$.ajax({
			url : "<%=request.getContextPath()%>/room/getRoomsBydepartment",
			type : "Get",
			data : "department_ID=" + department_ID,
			dataType : "json",
			success : function(json) {
				
					
	         },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("exception");
			}
		});
	}
	if(args==2){
		$.ajax({
			url : "<%=request.getContextPath()%>/room/getRoomsBydepartment",
			type : "Get",
			data : "department_ID=" + department_ID,
			dataType : "json",
			success : function(json) {
				
					
	         },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("exception");
			}
		});
	}

}
</script>
</head>
</head>
<body>
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
					<button name="checkBtn" id="checkBtn" onClick="displayRoom()">check</button>				
 					<button name="deleteBtn" id="deleteBtn" onClick="deleteRoom()">delete</button>
 					<button name="deleteBtn" id="deleteBtn" onClick="addRoom()">add</button> 
					<hr/> 
 					显示信息区域	 
				Room:<div id="display"></div> 
						
			 </div>

</body>
</html>
