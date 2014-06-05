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
</head>
	
<body>
	<!-- 进来时默认显示全部的黑名单 -->
					<div id="blacklistArea">
					<h1>黑名单information</h1>
					<c:forEach items="${blackLists}" var="blacklist">
						blackList ID:${blacklist.value[0]}<br/>
						blackList Reason:${blacklist.value[1]}<br/>
						blackList team_ID:${blacklist.value[2]}	<br/>
						blackList TeamName:${blacklist.value[3]}<br/>	
						blackList LCName:${blacklist.value[4]}	<br/>						
						<a href="">edit</a>
						<button onClick="deleteBlackList(${blacklist.value[0]})">delete</button>
						<hr/>
					</c:forEach>
					</div>
</body>
</html>
