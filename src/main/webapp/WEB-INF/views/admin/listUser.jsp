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
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/roomManagement.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">

function getDepartmentInfo(dept_ID){
	$.ajax({
			url : "<%=request.getContextPath()%>/admin/getUserDepartment",
			type : "Get",
			data : "department_ID=" + dept_ID,
			dataType : "html",
			success : function(json) {
				alert(json);
             },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error");
			}
		});
}
function getDetial(dept_ID){
	$("#display").attr("src","<%=request.getContextPath()%>/team/getDetail?department_ID="+dept_ID);
}
</script>

<title> User Page </title>
</head>
<body>
 <div class="roomListBody"> 
		<div id="commonUser">
			<h2>commonUser Information</h2>
			<c:forEach items="${commonUsers}" var="commonUser">
				commonUser :${commonUser}<br/>
				<br/>
				<button onclick='getDetial(${commonUser.dept_ID})'>getDetial</button>
			</c:forEach>
		</div>
		<hr/>	
			<iframe id ="display" src="<%=request.getContextPath()%>/team/getDetail?department_ID=1"></iframe>
	</div>
</body>
</html>