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
	<h1>admin 管理 界面普通用户user 的界面</h1>
	
	管理员：<security:authentication property="principal.username"></security:authentication> 
	<c:url value="/j_spring_security_logout" var="logoutUrl"/>  
	<li><a href="${logoutUrl}">Log Out</a></li> 
	<hr/>
	department:
	<form action="" method="post">
	<select name="department_ID">
		<option value=""></option>
		<c:forEach var="department" items="${departments}">	
				<option value='${department.department_ID}'>${department.departmentName}</option>
		</c:forEach>
	</select>
	<hr/>
	</form>
	<!-- 一开始不显示，当提交选择部门时候 ，ajax显示-->
	<div>
		
	</div>
	<hr/>
</body>
</body>
</html>