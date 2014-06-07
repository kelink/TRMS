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
				alert("error");
			}
		});
}
function getDetial(dept_ID){
	$("#display").attr("src","<%=request.getContextPath()%>/team/getDetail?department_ID="+dept_ID);
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
			    alert("error");
	        }
	

        });
	}else{
		return false;
	}
	
}
</script>

<title>User Page</title>
</head>
<body>

	<div id="sortByDepartmentBtnWrapper">
		<div class="sortByDepartmentBtn" id="sortByDepartmentBtn">Sort:Department</div>
		<div id="showPanel">
			<div class="panelHeader">
				<span class="headerText">Department</span> <span class="close"
					id="closeShow"><img class="closeIcon"
					src="<%=request.getContextPath()%>/resources/images/closeShallow.png"
					width="16px" height="16px" /></span>

			</div>
			<div id="panelContent">
				<a class="sortPanelItem" target="checkRightInner"
					href="<%=request.getContextPath()%>/reservation/list?status=-1">Unhandled</a>
				<a class="sortPanelItem" target="checkRightInner"
					href="<%=request.getContextPath()%>/reservation/list?status=1">Approve</a>
				<a class="sortPanelItem" target="checkRightInner"
					href="<%=request.getContextPath()%>/reservation/list?status=0">Reject</a>
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
						onclick='getDetial(${commonUser.dept_ID})'>
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
								
								<a class="operation" >Edit&nbsp&nbsp&nbsp</a>
								<a class="operation" onclick="deleteCommonUser(${commonUser.user_ID})">Remove</a>
							</span>
						</div>

					</div>

				</c:forEach>
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
					<div id="panelContent"></div>
				</div>
			</div>
		</div>
		<div id="linkPot"></div>
		<div id="userTeamListWrapper">
			<div class="listTopMenu"></div>
			<div id="userTeamListInner">
				<iframe id="display" name="display"
					src="<%=request.getContextPath()%>/team/getDetail?department_ID=1"></iframe>
			</div>
			<div id="userTeamListBtnWrapper">
				<a class="addUserTeamBtn">Add</a> <a class="removeUserBtn">Remove</a>
			</div>
		</div>
	</div>
</body>
</html>









