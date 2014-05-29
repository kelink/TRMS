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
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >

<link href="<%=request.getContextPath()%>/resources/css/reservationList.css" rel="stylesheet" >

 <script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>

</head>
<body>





					
					  <c:forEach var="reservationDetial" items="${reservationDetials}">	
				              ${reservationDetial.reservation }<br/>
				              <hr/>
				               ${reservationDetial.team }<br/>
				               
				               </hr>
				               ${reservationDetial.user }
			          </c:forEach>



 
 
 




</body>

</html>
