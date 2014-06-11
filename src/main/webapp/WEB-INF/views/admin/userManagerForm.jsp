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
<style>
a
{
color:black;
text-decoration:none;
}
span
{
width: 156px;
display: none;
position: absolute;
z-index: 1000;
top: 11px;
left: 23px;
white-space: pre;
word-wrap: break-word;
word-break: normal;
background: rgb(252, 251, 221);
border: 1px solid #cccccc;
overflow: hidden;
border-radius: 3px;
box-shadow: 0 1px 5px rgb(180, 180, 180);
}

a:hover span
{
    display:block;
}

</style>
</head>
<body>
	<div class="teamListTitle">Department</div>
	<div id="departmentContent">${department.departmentName }</div>
	<div class="teamListTitle">Team List</div>
	<div id="teamListContent">
	<form action="<%=request.getContextPath()%>/team/batchRemoveTeam" method="post">
	<c:forEach items="${teams}" var="team">
		<div class="teamListItem" style="position:relative;">
		<input type="checkbox" name="checkbox" value="${team.team_ID }"/>
		<a href="#">${team.teamName }<span>${team.teamName }</span></a>
		</div>
	</c:forEach>
	</form>
	</div>	
	
</body>
</body>
</html>