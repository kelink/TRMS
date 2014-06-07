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
<link rel="icon"
	href="<%=request.getContextPath()%>/resources/images/hsbcicon.ico"
	type="image/x-icon" />
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/resources/css/blacklistlist.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/resources/js/jquery.js"
	type="text/javascript"></script>
<script type="text/javascript">
var pageIndex = 0;
var pageSize =7;
$(function() {
	pageIndex = 1;
	AjaxGetData(pageIndex, pageSize);
});

function AjaxGetData(index, size) {
		
	$.ajax({
				url : "<%=request.getContextPath()%>/reservation/unhandleListPageReservation",
				type : "get",
				data : "pageNum=" + index + "&pageSize=" + size,	
				dataType : "json",
				success : function(json) {
	                },
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
				}
			});
}

function GoToFirstPage() {
	pageIndex = 1;
	AjaxGetData(pageIndex, pageSize);
}

function GoToPrePage() {
	pageIndex -= 1;
	pageIndex = pageIndex >= 1 ? pageIndex : 1;
	AjaxGetData(pageIndex, pageSize);
}

function GoToNextPage() {
	if (pageIndex < parseInt($("#count").text())) {
		pageIndex += 1;
		AjaxGetData(pageIndex, pageSize);
	}	
}

function GoToEndPage() {
	pageIndex = parseInt($("#count").text());
	AjaxGetData(pageIndex, pageSize);
}

function GoToAppointPage(e) {
	var page = $(e).prev().val();
	if (isNaN(page)) {
		alert("Page should be a valid number");
	} else {
		var tempPageIndex = pageIndex;
		pageIndex = parseInt($(e).prev().val());
		if (pageIndex <= 0 || pageIndex > parseInt($("#count").text())) {
			pageIndex = tempPageIndex;
			alert("Please input valid page scope!");
		} else {
			AjaxGetData(pageIndex, pageSize);
		}
	}
}
</script>
</head>

<body id="listBody">
	<!-- 进来时默认显示全部的黑名单 -->
	<div class="ticketTitle">BlackList Information</div>
	<div id="blacklistArea">
			<div id="reservationTableWrapper">
				<table>
					<tbody>				
					<c:forEach items="${blacklists}" var="blacklist">
					
						<tr>
							<td>department Name</td>
							<td><input class="readonlyInfo" type="text"
								name="departmentName" readonly="true"
								value="${departments.get(blacklist.bl_ID).departmentName}"></td>
						</tr>	
						<tr>
							<td>Team Name</td>
							<td><input class="readonlyInfo" type="text"
								name="reservation_Num" readonly="true"
								value="${teams.get(blacklist.bl_ID).teamName}"></td>
						</tr>
						<tr>
							<td>blackList Reason</td>
							<td><input class="readonlyInfo" type="text"
								name="reservation_Num" readonly="true"
								value="${blacklist.reason}"></td>
						</tr>					
						<tr>					
							<td class="btnWrapper"><a class="btnDelete reservationLcBtn"
								href="<%=request.getContextPath()%>/blacklist/delete?bl_ID=${blacklist.bl_ID}">delete</a>
							</td>
						</tr>				 
					</c:forEach>
					</tbody>
				</table>
			</div>
	</div>
	<div id="totalWrapper">
		<div id="listInfoWrapper1">
			<div id="listInfo">
				Total Page:<span id='count'>${pageCount}</span> Total Records:<span
					id='recordCount'>${recordCount}</span>
			</div>

			<div id="jump">
				<a href='javascript:void' onclick='GoToFirstPage()' id='FirstPage'><img
					class="pageIcon" alt="Go to first page"
					src="<%=request.getContextPath()%>/resources/images/first.png"></a>
				<a href='javascript:void' onclick='GoToPrePage()' id='PrePage'><img
					class="pageIcon" alt="Go to previous page"
					src="<%=request.getContextPath()%>/resources/images/prepage.png"></a>
				<span id="pageIndex"></span> <a href='javascript:void'
					onclick='GoToNextPage()' id='NextPage'><img class="pageIcon"
					alt="Go to next page"
					src="<%=request.getContextPath()%>/resources/images/nextpage.png"></a>
				<a href='javascript:void' onclick='GoToEndPage()' id='EndPage'><img
					class="pageIcon" alt="Go to last page"
					src="<%=request.getContextPath()%>/resources/images/end.png"></a>
				<input type='text' size='4' name='page' /> <input class="btnJump"
					type='button' value='Jump' onclick='GoToAppointPage(this)' />
			</div>
		</div>
	</div>

</body>

</html>

