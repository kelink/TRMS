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
<script src="<%=request.getContextPath()%>/resources/js/reservationList.js" type="text/javascript"></script>
<script type="text/javascript">
var pageIndex = 0;
var pageSize =7;
$(function() {
	pageIndex = 1;
	AjaxGetData(pageIndex, pageSize);
});

function AjaxGetData(index, size) {
		
	$.ajax({
				url : "<%=request.getContextPath()%>/reservation/listPageReservation",
				type : "get",
				data : "pageNum=" + index + "&pageSize=" + size+"&optionStr="+"${optionStr}",	
				dataType : "json",
				success : function(json) {
					var html="";
					for (position in json) {	
									
						var reservation_ID=json[position].reservation.reservation_ID;
						var reservation_start_Daty=json[position].reservation.applied_Start_Date;
						var reservation_end_Daty=json[position].reservation.applied_End_Date;
						var reservation_Num=json[position].reservation.reservation_Num;
						var purpose=json[position].reservation.purpose;
						var email=json[position].reservation.email;
						var tele=json[position].reservation.tele;
						var Order_Date=json[position].reservation.order_Time;
						var handler_by=json[position].reservation.handle_by;
						
						var team_ID=json[position].team.team_ID;
						var order_Team=json[position].team.teamName;
						
						var room_ID=json[position].room.room_ID;
						var room_item=json[position].room.item;
						
						var applicant_ID=json[position].user.user_ID;
						var applicant_account=json[position].user.account;
						var status=json[position].reservation.status;											



               
html+=("<div class=\"listItem\"id=\"listItem"+reservation_ID+"\"onclick=\"expand("+reservation_ID+");\">");
html+=("<div class=\"listFoldBtn\"id=\"listFoldBtn"+reservation_ID+"\"onclick=\"fold("+reservation_ID+",event)\">");
html+=("<img class=\"listFoldImg\"src=\"/trms/resources/images/listFold.png\"/>");
html+=("</div>");
html+=("<div class=\"reservationInfo\"id=\"reservationInfo"+reservation_ID+"\">");
/////////////////////////////

                        html+=("<div class=\"ticketTitle\">Ticket Information</div>");
                        html+=("<div class=\"ticketContent\">");

                        
                        
                        
                        
                        
                        
                        
                        
						
						
						
						
						
						
						
						
///////////////////////////////////////////////////////////////////////////////////

						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					// // 						<div id="modifyWrapper">
                         html+="<div id='modifyWrapper'>";
// // 				    	<div id="warning">The ticket can only be modify when it is at the status of untreated!</div>
 					    html+="<div id=\"warning\">The ticket can only be modify when it is at the status of untreated!</div>";
<%--					<form action="<%=request.getContextPath()%>/reservation/update" method="post" onsubmit="return updateCheck(${reservation.status})"> --%> 
// //////////////////////////////////////////////////////////////////////////////////有问题
                         html+="<form action=\"<%=request.getContextPath()%>/reservation/update\" method=\"post\" onsubmit=\"return updateCheck("+status+")\">"; 
// 						Reservation Number:
	                    html+="Reservation Number:";
// 						<input type="text" name="reservation_Num" readOnly="true" value="${reservation.reservation_Num}"/><br/>
                        html+="<input type=\"text\" name=\"reservation_Num\" readOnly=\"true\" value=\""+reservation_Num+"\"/><br/>";
// 						Room：<input type="text" name="room_item" readOnly="true" value="${room.item}"/><br/>
					    html+="Room：<input type=\"text\" name=\"room_item\" readOnly=\"true\" value=\""+room_item+"\"/><br/>";
// 						Planned Use Start Time：
                        html+="Planned Use Start Time：";
// 						<input type="date" name="Applied_Start_Date" value="${reservation.getApplied_Start_DateByString()}"/><br/>
                        html+="<input type=\"date\" name=\"Applied_Start_Date\" value=\""+reservation_start_Daty+"\"/><br/>";
// 						Planned Use End Time：
                        html+="Planned Use End Time：";
// 						<input type="date" name="Applied_End_Date"value="${reservation.getApplied_End_DateByString()}"/><br/>
                        html+="<input type=\"date\" name=\"Applied_End_Date\"value=\""+reservation_end_Daty+"\"/><br/>";
// 						User LN:
	                    html+="User LN:";
// 						<input type="text" name="email" value="${reservation.email}"/><br/>
                        html+="<input type=\"text\" name=\"email\" value=\""+email+"\"/><br/>";
// 						User TelLine:
	                    html+="User TelLine:";
// 						<input type="text" name="tele" value="${reservation.tele}"/><br/>
                        html+="<input type=\"text\" name=\"tele\" value=\""+tele+"\"/><br/>";
// 						Use Resaon:
	                    html+="Use Resaon:";
// 						<input type="text" name="purpose"value="${reservation.purpose}"/>
                        html+="<input type=\"text\" name=\"purpose\"value=\""+purpose+"\"/>";
						
// 						<input type="hidden" name="reservation_ID" value="${reservation.reservation_ID}">
                        html+="<input type=\"hidden\" name=\"reservation_ID\" value=\""+reservation_ID+"\">";
						
// 						<div id="btnWrapper">
                        html+="<div id=\"btnWrapper\">";

// 						<input class="btnUpdate" type="submit"name="submit" id="btnUpdate"  value="update"/>
                        html+="<input class=\"btnUpdate\" type=\"submit\"name=\"submit\" id=\"btnUpdate\"  value=\"update\"/>";
// // 						<a class="btnDelete" href="#" onclick="deleteReservation(${reservation.reservation_ID})">delete</a>
                        html+="<a class=\"btnDelete\" href=\"#\" onclick=\"deleteReservation"+reservation_ID+"\">delete</a>";
// // 						</div>
                        html+="</div>";
// // 						</form>
                         html+="</form>";
// // 				        </div>
                         html+="</div>";	
						
						
                         html+=("</div>");
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
/////////////////////////////////////////////////////////////////////////////////////
						html+=("</div>");
                        html+=("<input type='checkbox'name='checkbox' onclick='chose(event);'value='"+reservation_ID+"' class='checkbox1 listContentClass"+reservation_ID+"'>");
                        html+=("<span class=\"listContent1 listContent listContentClass"+reservation_ID+"\">"+reservation_Num+"</span>");
                        html+=("<span class=\"listContent2 listContentClass"+reservation_ID+"\">"+purpose+"</span>");
                        html+=("<span class=\"listContent3 listContentClass"+reservation_ID+"\">"+Order_Date+"</span>");
                        html+=("</div>");




					}					
				$('#listWrapper').html("");
		        $('#listWrapper').html("<iframe id=\"moreInfo\"name=\"moreInfo\" scrolling=\"no\" ></iframe>"+html); 

	                $('#pageIndex').html("");
	               $('#pageIndex').html(pageIndex);
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
<div id="totalWrapper">

    <div id="topBar">
 
    <input type="checkbox" class="checkbox"onclick="check_all(this,'checkbox')">
    <span class="topBarItem topBarItem1">Reservation No.</span>
    <span class="topBarItem topBarItem2">Usage</span>
    <span class="topBarItem topBarItem3">Order Time</span>
    </div>
    <div id="listWrapper">
    
    </div>


				 <div id="listInfoWrapper1" >
				     <div id="listInfo">
					    Total Page:<span id='count'>${pageCount}</span>
					    Total Records:<span id='recordCount'>${recordCount}</span>
				    </div>
				    
				    <div id="jump">
					     <a href='javascript:void' onclick='GoToFirstPage()' id='FirstPage' ><img class="pageIcon"alt="Go to first page" src="<%=request.getContextPath()%>/resources/images/first.png"></a>
					     <a href='javascript:void' onclick='GoToPrePage()' id='PrePage' ><img class="pageIcon"alt="Go to previous page" src="<%=request.getContextPath()%>/resources/images/prepage.png"></a>
					     <span id="pageIndex"></span>
					     <a href='javascript:void' onclick='GoToNextPage()' id='NextPage' ><img class="pageIcon"alt="Go to next page" src="<%=request.getContextPath()%>/resources/images/nextpage.png"></a>
					     <a href='javascript:void' onclick='GoToEndPage()' id='EndPage' ><img class="pageIcon" alt="Go to last page"src="<%=request.getContextPath()%>/resources/images/end.png"></a>
					     <input type='text' size='4' name='page' />
					     <input class="btnJump" type='button' value='Jump' onclick='GoToAppointPage(this)' />
				     </div>
				 </div>
</div>



</body>

</html>
