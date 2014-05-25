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
<script type="text/javascript">
function isEdit(){
	
}

</script>
</head>
</body>
	<h1>订单具体信息显示，修改和删除界面</h1>
	订单在未处理状态下可以修改和编辑，在approve 和 refuse 状态下不可更改<br/>
	
		<form action="<%=request.getContextPath()%>/reservation/update" method="post">
		Reservation Number:
		<input type="text" name="reservation_Num" readOnly="true" value="${reservation.reservation_Num}"/><br/>

		Room：<input type="text" name="room_item" readOnly="true" value="${room.item}"/><br/>
	
		Planned Use Start Time：
		<input type="datetime" name="Applied_Start_Date" value="${reservation.applied_Start_Date}"/><br/>
		Planned Use End Time：
		<input type="datetime" name="Applied_End_Date"value="${reservation.applied_End_Date}"/><br/>
		User LN:
		<input type="text" name="email" value="${reservation.email}"/><br/>
		User TelLine:
		<input type="text" name="tele" value="${reservation.tele}"/><br/>
		Use Resaon:
		<input type="text" name="purpose"value="${reservation.purpose}"/>
		
		<input type="hidden" name="reservation_ID" value="${reservation.reservation_ID}">
		
		<input type="submit"name="submit" value="update"/>
		</form>

	
	<a href="<%=request.getContextPath()%>/reservation/delete?reservation_ID=${reservation.reservation_ID}">delete</a>
	<br/>	
</body>
</html>
