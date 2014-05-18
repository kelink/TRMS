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
<title>Insert title here</title>
</head>
<body>
<h1>订房界面</h1>
<script type="text/javascript" src="../resources/js/jquery-1.7.2.js"></script>  
 

<button id="button" name="button" value="test">test</button>

<%=request.getContextPath()%>

<form action="<%=request.getContextPath()%>/book/" method="post" name="bookForm">
Free Room:
<select>
	<c:forEach items="${rooms}" var="room"> 
		<option>${room.item }</option>
	</c:forEach>
</select>
User Start Time:<input type="text" name="begin_time"/>
User End  Time:<input type="text" name="room"/>
User LN:<input type="text" name="room"/>
User Team<input type="text" name="room"/>
User TelLine<input type="text" name="userTelLine">
Use Resaon:<input type="text" name="purpose">
</form>
 
</body>
</html>