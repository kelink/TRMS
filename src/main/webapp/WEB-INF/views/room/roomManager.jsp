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
	var departmentName=$("#departments").find("option:selected").text();
 	$.ajax({
		url : "<%=request.getContextPath()%>/room/getRoomsBydepartment",
		type : "Get",
		data : "department_ID=" + department_ID,
		dataType : "json",
		success : function(json) {
			var html="";
			$("#display").empty();
			html+="<form action='#' method='get'>";
			html+="<table><tr><th>";
			html+="<input type='checkbox' id='checkAll' class='check' onclick='check_all(this,\"checkbox\");'>";
			html+="Room</th><th>Last used date</th><th>Own by</th><th>Status</th><th>Operation</th></tr>";
			for (position in json) {
					var room_ID=json[position].room_ID;
					var item=json[position].item;
					var last_Used_Date=json[position].last_Used_Date;
					var department_ID=json[position].department_ID;
					var room_Status=json[position].room_Status;
					
					
					
					
					html+="<tr><td><input type='checkbox'name='checkbox' id='check"+room_ID+"' class='check' value="+room_ID+">"+item+"</td>";
					html+="<td>"+last_Used_Date+"</td>";
					html+="<td>"+departmentName+"</td>";
					if(room_Status==0)
					{
						 html+="<td>free</td>";
					}
					if(room_Status==1)
					{
						 html+="<td>unfree</td>";
					}
 	               
 	               html+="<td>"+"<a class='operation' href='#'>delete</a> ";
 	              html+="<a class='operation' href='#'>edit</a>"+"</td></tr>";
					
					
			}			
			html+="</table>";
			html+="</form>";
			$("#display").html(html);
         },
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});
}
function roomOperate(args){
	//get the select item
	//delete room
	if(args==0){
		$.ajax({
			url : "<%=request.getContextPath()%>/room/getRoomsBydepartment",
			type : "Get",
			data : "department_ID=" + department_ID,
			dataType : "json",
			success : function(json) {
						
	         },
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});
	}
	//add room
	if(args==1){
		$.ajax({
			url : "<%=request.getContextPath()%>/room/getRoomsBydepartment",
			type : "Get",
			data : "department_ID=" + department_ID,
			dataType : "json",
			success : function(json) {
				
					
	         },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			
			}
		});
	}
	//change status
	if(args==2){
		$.ajax({
			url : "<%=request.getContextPath()%>/room/getRoomsBydepartment",
			type : "Get",
			data : "department_ID=" + department_ID,
			dataType : "json",
			success : function(json) {
				
					
	         },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
		
			}
		});
	}

}
function checkCheckbox(args){
	//判断是否已经选择
	var  box = new Array(); 
	if($("input[name='checkbox']:checkbox:checked").length>0){	   
	    $("input[name='checkbox']:checkbox:checked").each(function(){
	    	box.push($(this).val()); 
	    }); 
	 }else{
	    alert('You do not choose any items');
	    return false;
	}
	//multi delete
	if(args=="0"){
		$.ajax({
				url : "<%=request.getContextPath()%>/room/multiDelete",
				type : "get",
				data:'checkbox='+box,  		
				dataType : "html",
				success : function(json) {
					alert(json);
					location.reload();
		        },
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
					location.reload();
				}
			});
	}
	//multi change status
	if(args=="1"){
		$.ajax({
				url : "<%=request.getContextPath()%>/room/multiUpdateRoomStatus",
				type : "get",
				data:'checkbox='+box,  		
				dataType : "html",
				success : function(json) {
					alert(json);
					location.reload();
		        },
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
					location.reload();
				}
			});
	}
	return false;
}
</script>
</head>
</head>
<body>
 			 <div class="roomListBody roomListBody1"> 
 			 			 	<h1>Room Management</h1> 
			     <div id="searchWrapper">
					<div id="roomDeptment">departments</div> 
					<select name="departments" id="departments"> 
						<option value=""></option>
						<c:forEach items="${departments}" var="department"> 
							<option value="${department.department_ID}">${department.departmentName}</option> 
 						</c:forEach> 
					</select> 
					<button name="addBtn" id="addBtn" onClick="addRoom()">add room</button> 
 					<button name="deleteBtn" id="deleteBtn" onClick="checkCheckbox(0)">delete room</button>
 					<button name="checkBtn" id="checkBtn" onClick="checkCheckbox(1)">change Status</button>			
 				</div>
					<hr/> 
 					 
				<div id="roomTitleWrapper">Room:</div>
				<div id="displayWrapper">
				    <div id="display"></div> 
				</div>
						
			 </div>

</body>
</html>
