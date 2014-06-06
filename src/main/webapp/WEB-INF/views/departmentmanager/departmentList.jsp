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
<link href="<%=request.getContextPath()%>/resources/css/departmentList.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
<script src="<%=request.getContextPath()%>/resources/js/departmentList.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
function changeFrame(args){
	if(args==0){
		window.location.href='<%=request.getContextPath()%>/department/index';
		window.parent.document.getElementById("userDisplay").src="<%=request.getContextPath()%>/team/index"
	}
}

</script>
</head>
<body>
<div class="roomListBody"> 
 <h2>department information</h2>
 	<button>add Department</button>
 	<button>delete Department</button>
 	<button onclick="display()">check Department</button><br/>
 	<ul>		
 	
 		<c:forEach var="department" items="${departments}">
 		
		<li>
		<!-- 当点击选择部门时候才显示部门的信息 -->
			<input type='radio' name='department' value=${department.department_ID }>${department.departmentName }<br>			
 			<a href="#" onClick="changeFrame(0)">User numbers:(${userList[department.department_ID].size()})</a><br/>
			<a href="#"  onClick="changeFrame(1)">Team numbers(${department.getTeamSet().size() })</a><br/>
			<a href="#"  onClick="changeFrame(2)">Room numbers(${department.getRoomSet().size() })</a><br/>
 			 <!--  
			<c:if test="${department.departmentName}">
				<p id="defaultInfo">No user information to show</p> 
			</c:if>	
			<c:forEach var="team" items="${department.getTeamSet()}"> 
 						${team } 
 			</c:forEach> 
 			department room: <br/> 
			<c:forEach var="room" items="${department.getRoomSet()}"> 
 					${room } 
 			</c:forEach>
 			 -->
 			<hr/>	
 		</li>								
 								
		</c:forEach> 
 		
 	</ul>
	 
			
		      <div id="displayArea"></div>
</div>
</body>
</html>