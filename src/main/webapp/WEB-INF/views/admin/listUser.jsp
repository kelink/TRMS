<%@page import="com.dummy.domain.Room"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="/trms/resources/images/hsbcicon.ico"
	type="image/x-icon" />
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/listUser.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/footer.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/js/listUser.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.js"
	type="text/javascript"></script>
<script type="text/javascript">

function getDepartmentInfo(dept_ID){
	$.ajax({
			url : "<%=request.getContextPath()%>/admin/getUserDepartment",
			type : "Get",
			data : "department_ID=" + dept_ID,
			dataType : "html",
			success : function(json) {
				alert(json);
             },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
	
			}
		});
}
function getTeamByUser(dept_ID,user_ID){
	$("#display").attr("src","<%=request.getContextPath()%>/team/getTeamByUser?department_ID="+dept_ID+"&user_ID="+user_ID);
	var department=document.getElementById("departments");
	
	for(var i=0;i<department.options.length;i++)
	{
		     if(department.options[i].value==dept_ID)
		     {
		    	 department.options[i].selected=true;
		     }
		     
		     
	}
	$("#departments").trigger("change");

    setTimeout("chooseUser("+user_ID+")",200);

	
}
function chooseUser(user_ID){


 	var user=document.getElementById("users");
	for(var i=0;i<user.options.length;i++){
		if(user.options[i].value==user_ID)
	     {
			
	    	 user.options[i].selected=true;
	     }
	}
}
function deleteCommonUser(args){
	if(confirm("Are you sure to delete it?All information about this user will lost"))
	{
	    $.ajax({
		    url : "<%=request.getContextPath()%>/admin/deleteCommonUser",
		    type : "get",
		    data : "user_ID=" +args,	
		    dataType : "html",
		    success : function(json) {
			    alert(json);
			    location.reload();
		    },
		    error : function(XMLHttpRequest, textStatus, errorThrown) {
		
	        }
	

        });
	}else{
		return false;
	}
	
}
function sortByDepartment(department_ID){
	//修改父窗口
	$("#userDisplay",parent.document).attr("src","<%=request.getContextPath()%>/admin/listUser?department_ID="+department_ID);
}
function edit(account,access,gender,tele,password,dept_ID,user_ID)
{ 
	$("#account").attr("value",account);
	$("#access").attr("value",access);
	$("#gender").attr("value",gender);
	$("#tele").attr("value",tele);
	$("#password").attr("value",password);
	$("#dept_ID").attr("value",dept_ID);	
	$("#user_ID").attr("value",user_ID);
	
	if($("#showPanel6").css("display")=="none")
		$("#showPanel6").css("display","block");
	else
		$("#showPanel6").css("display","none");
	$("#sortPanel6").css("display","none");	
}

</script>

<title>User Page</title>
</head>
<body>

	<div id="sortByDepartmentBtnWrapper">
	    <div id="lcManagementTitle">LC Management</div>
		<div class="sortByDepartmentBtn" id="sortByDepartmentBtn">Sort:Department</div>
		<div id="showPanel">
			<div class="panelHeader">
				<span class="headerText">Department</span> <span class="close"
					id="closeShow"><img class="closeIcon"
					src="<%=request.getContextPath()%>/resources/images/closeShallow.png"
					width="16px" height="16px" /></span>

			</div>
			<div id="panelContent">
				
				<c:forEach items="${departments}" var="department">
				    <a class="sortPanelItem" target="checkRightInner" onClick="sortByDepartment(${department.department_ID})">${department.departmentName}</a>
			    </c:forEach>
			    <a class="sortPanelItem" target="checkRightInner" onClick="sortByDepartment(0)"style="font-weight:bold;">&ltBack</a>
			</div>
		</div>
	</div>
	<div id="allInfoWrapper">
		<div id="userListWrapper">
			<div class="listTopMenu">
				<span class="userTopMenuTitle userTopMenuTitle1">User LN</span> <span
					class="userTopMenuTitle userTopMenuTitle2">ROLE</span> <span
					class="userTopMenuTitle userTopMenuTitle3">Gender</span> <span
					class="userTopMenuTitle userTopMenuTitle4">Telephone</span> <span
					class="userTopMenuTitle userTopMenuTitle5">Operation</span>
			</div>
			<div id="userListinner">
			<c:forEach items="${commonUsers}" var="commonUser">
					<div class="userListItem"
						onclick='getTeamByUser(${commonUser.dept_ID},${commonUser.user_ID})'>
						<img class="lc"
							src="<%=request.getContextPath()%>/resources/images/lc.png" />
						<div class="userInfoWrapper">

							<span class="userInfo userInfo1">${commonUser.account}</span> <span
								class="userInfo userInfo2"> <c:if
									test="${commonUser.access==1}">LC </c:if> <c:if
									test="${commonUser.access==2}">TA</c:if>
							</span> <span class="userInfo userInfo3"> <c:if
									test="${commonUser.gender==1}">male</c:if> <c:if
									test="${commonUser.gender==0}">female</c:if>
							</span> <span class="userInfo userInfo4">${commonUser.tele}</span> <span class="userInfo userInfo5"> 
								
								<a class="operation"onclick="edit('${commonUser.account}',${commonUser.access},${commonUser.gender},${commonUser.tele},${commonUser.password},${commonUser.dept_ID},${commonUser.user_ID})" >Edit&nbsp&nbsp&nbsp</a>
								<a class="operation" onclick="deleteCommonUser(${commonUser.user_ID})">Remove</a>
							</span>
						</div>

					</div>

				</c:forEach>
			</div>
			<div id="showPanel6">
					<div class="panelHeader">
						<span class="headerText">Edit user</span> <span class="close"
							id="closeShow6"><img class="closeIcon"
							src="<%=request.getContextPath()%>/resources/images/closeShallow.png"
							width="16px" height="16px" /></span>
					</div>
					<div id="panelContent6">
						<form action="<%=request.getContextPath()%>/admin/updateUser" method="post" >
						    <div class="addUserLine">
							    <div class="addUserLabel">User LN:</div>
							    <input class="addUserInput" name="account" id="account"  required >
							</div>

						    <div class="addUserLine">
							    <div class="addUserLabel">Role:</div>
							    <select class="addUserInput" name="access" id="access"  required >
							    <option value=""></option>
							    <option value="1">LC</option>
							    <option value="2">TA</option>
							    </select>
							</div>							
							
							<div class="addUserLine">
							    <div class="addUserLabel">Gender:</div>
							    <select class="addUserInput" name="gender" id="gender" required >
							    <option value=""></option>
							    <option value="1">male</option>
							    <option value="0">female</option>
							    </select>
							</div>	
							
							<div class="addUserLine">
							    <div class="addUserLabel">Telephone:</div>
							    <input class="addUserInput" name="tele" id="tele" required >
							</div>	
							
							<div class="addUserLine">
							    <div class="addUserLabel">Password:</div>
							    <input class="addUserInput" name="password" id="password" required >
							</div>	
							
							<div class="addUserLine">
							    <div class="addUserLabel">Department:</div>
							    <select class="addUserInput" name="dept_ID" id="dept_ID" required >
							    <option value=""></option>
							    <c:forEach items="${departments}" var="department">
										<option value="${department.department_ID }">${department.departmentName }</option>
								</c:forEach>
							    
							    </select>
							</div>	
							<input type="hidden" name="user_ID" id="user_ID"/>
							<div><input class="addUserSubmit btnAdd1" type="submit" name="submit" id="submit" value="Update"/></div>
							
							
						</form>
					
					</div>
				</div>
			
			
			<div id="userListBtnWrapper">
				<a class="addUserBtn" id="addUserBtn">Add</a>
				<div id="showPanel1">
					<div class="panelHeader">
						<span class="headerText">Add user</span> <span class="close"
							id="closeShow1"><img class="closeIcon"
							src="<%=request.getContextPath()%>/resources/images/closeShallow.png"
							width="16px" height="16px" /></span>
					</div>
					<div id="panelContent">
						<form action="<%=request.getContextPath()%>/admin/addUser" method="post">
						    <div class="addUserLine">
							<div class="addUserLabel">ACCOUNT:</div>
							<input class="addUserInput" type="text" name="account" id="account" required />
							</div>
							<div class="addUserLine">
							<div class="addUserLabel">PASSWORD:</div>
							<input class="addUserInput" type="text" name="password" id="password" required/>
							</div>
							<div class="addUserLine">
							<div class="addUserLabel">ROLE:</div>
							<select class="addUserInput" name="access" id="access" required>
								<option></option>
								<option value="1">LC</option>
								<option value="2">TA</option>	
							</select>
							</div>
							<div class="addUserLine">
							<div class="addUserLabel">GENDER:</div>
							<select class="addUserInput" name="gender" id="gender" required>
								<option></option>
								<option value="1">male</option>
								<option value="0">female</option>
							</select>
							</div>
							<div class="addUserLine">
							<div class="addUserLabel">TELE:</div>
							<input class="addUserInput" type="text" name="tele" id="tele" required />
							</div>
							<div class="addUserLine">
							<div class="addUserLabel">DEPAETMENT:</div>	
							<select class="addUserInput" name="dept_ID" id="dept_ID" required>
								<option></option>
								<c:forEach items="${departments}" var="department">
										<option value="${department.department_ID }">${department.departmentName }</option>
								</c:forEach>
							</select>
							</div>
							<div><input class="addUserSubmit btnAdd" type="submit" name="submit" id="submit" value="add"/></div>
							
							
						</form>
					
					</div>
				</div>
			</div>
		</div>
		<div id="linkPot"></div>
		<div id="userTeamListWrapper">
			<div class="listTopMenu"></div>
			<div id="userTeamListInner">
				<iframe id="display" name="display"
					src="<%=request.getContextPath()%>/team/getTeamByUser?department_ID=${commonUsers[0].dept_ID}&user_ID=${commonUsers[0].user_ID}"></iframe>
			</div>
			<div id="userTeamListBtnWrapper">
<%-- 				<a class="addUserTeamBtn" id="addTeamBtn" target="_blank"  href="<%=request.getContextPath()%>/team/getUserAddTeamForm" >Add</a> --%>
				<a class="addUserTeamBtn" id="addTeamBtn" >Add</a>
				<a class="removeUserBtn" onclick="window.frames['display'].checkCheckbox(0)" id="remove">Remove</a>
				<div id="showPanel2">
					<div class="panelHeader">
						<span class="headerText">Add team for LC</span> <span class="close"
							id="closeShow2"><img class="closeIcon"
							src="<%=request.getContextPath()%>/resources/images/closeShallow.png"
							width="16px" height="16px" /></span>
					</div>
					<div id="panelContent2">
						<form action="<%=request.getContextPath()%>/team/addTeamForUser" method="post" onSubmit="return checkSubmit()">
						    <div class="addUserLine">
							<div class="addUserLabel">Department:</div>
							<select class="addUserInput" name="departments"id="departments"  required >
							<option value=""></option>
							<c:forEach items="${departments}" var="department">
							<option value="${department.department_ID}">${department.departmentName}</option>
						    </c:forEach>
							</select>
							</div>
							<div class="addUserLine">
							<div class="addUserLabel">User:</div>
							<select class="addUserInput"  name="users"id="users"  required >
							<option value=""></option>
							</select>
							</div>
							<div class="addUserLine">
							<div class="addUserLabel">Team:</div>
							<select class="addUserInput"  name="teams"id="teams" required >
							<option value=""></option>
							</select>
							</div>
							
							<div><input class="addUserSubmit btnAdd" type="submit" name="submit" id="submit" value="add"/></div>
							
							
						</form>
					
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>









