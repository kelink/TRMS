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
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/resources/css/userManagementForm.css" rel="stylesheet">
<script type="text/javascript">
function checkCheckbox(args){
	//判断是否已经选择
	var  box = new Array(); 
	if($("input[name='checkbox']:checkbox:checked").length>0){	   
	    $("input[name='checkbox']:checkbox:checked").each(function(){
	    	box.push($(this).val()); 
	    }); 
	 }else{
	    alert('You do not choose any items');
	    return false;
	}
	//multi delete
	if(args=="0"){
		$.ajax({
				url : "<%=request.getContextPath()%>/team/batchRemoveTeam",
				type : "get",
				data:'checkbox='+box,  		
				dataType : "html",
				success : function(json) {
					alert(json);
					window.parent.location.reload();
		        },
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					history.back();
				}
			});
	}
	return false;
}


</script>
</head>
<body>
	<div class="teamListTitle">Department</div>
	<div id="departmentContent">${department.departmentName }</div>
	<div class="teamListTitle">Team List</div>
	<div id="teamListContent">
	<form action="<%=request.getContextPath()%>/team/batchRemoveTeam" method="post">
	<c:forEach items="${teams}" var="team">
		<div class="teamListItem"><input type="checkbox" name="checkbox" value="${team.team_ID }"/>${team.teamName }</div>
	</c:forEach>
	</form>
	</div>	
	
</body>
</body>
</html>