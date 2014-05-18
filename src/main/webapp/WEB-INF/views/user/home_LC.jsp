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
	welcome  LC <security:authentication property="principal.username"></security:authentication> 
	<c:url value="/j_spring_security_logout" var="logoutUrl"/>  
<a href="${logoutUrl}">Log Out</a> 

<br/>
<a href="/trms/book/index">Book Room</a>
<a href="#">Check My Reservation</a>
<a href="#">Delete My Reservation</a>
<a href="#">Manage My Profile</a>
<br/>
<!-- 显示room的信息 -->
Room:
<ul>
	<c:forEach items="${rooms}" var="room"> 
			<li>
			${room.item}-----------
			日期显示:
			<select>
				<%for(int i=1;i<=31;i++){
					out.write("<option value="+i+">"+i+"</option>");
				} %>
			</select>
			</li>
	</c:forEach> 	
</ul>

 
</body>
</html>