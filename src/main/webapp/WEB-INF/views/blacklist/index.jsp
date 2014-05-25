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
<link rel="icon" href="<%=request.getContextPath()%>/resources/images/hsbcicon.ico" type="image/x-icon"/>
</head>
<body>
	<h1>黑名单管理界面</h1>
	<h3>传入的是部门信息，当点击部门的时候,显示出选择team的框，会ajax调用获取team信息，当点击选中team后会ajax 查新当前的黑名单情况，接口已经定义</h3>
	
	<div>
<form action="<%=request.getContextPath()%>/blacklist/getTeamsByDepartment" method="get">
	department:
	<select name="department_ID">
		<option value=""></option>
		<c:forEach var="department" items="${departments}">	
				<option value='${department.department_ID}'>${department.departmentName}</option>
		</c:forEach>
	</select>
	</div>
	<input type="submit" name="submit" value="check"> 
	</form>
	<hr/>

	<form action="<%=request.getContextPath()%>/blacklist/getReasonByTeam" method="get">
	team:
	<select name="team_ID">
		<option value="1">1</option>
		<c:forEach var="team" items="${teams}">	
				<option value='${team.team_ID}'>${team.teamName}</option>
		</c:forEach>
	</select>
	<input type="submit" name="submit" value="check"> 
	</form>
	
	<div id="display">
	</div>
</body>
</body>
</html>
