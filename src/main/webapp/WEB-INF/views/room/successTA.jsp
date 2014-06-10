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

 <link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
  <link href="<%=request.getContextPath()%>/resources/css/bookRoomSuccess.css" rel="stylesheet" >
   <script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bookRoomSuccessTA.js" type="text/javascript"></script>
</head>
<body>
<div id="bookRoomIcon">
<img width="95px;" src="<%=request.getContextPath()%>/resources/images/success.png"/>
</div>
<div id="bookRoomFeedback">
<span id="successTitle">Book room success!</span>
<br/>
${message }
<br/>

</div>
<a class="btnReturn"id="btnReturn" href="<%=request.getContextPath()%>/room/headInfo">Return</a>
</body>
</html>