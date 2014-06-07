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
    <script src="<%=request.getContextPath()%>/resources/js/reservationList.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
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
																	



               
                        html+=("<div class=\"listItem\"id=\"listItem"+reservation_ID+"\"onclick=\"expand("+reservation_ID+");\">");
                        html+=("<div class=\"listFoldBtn\"id=\"listFoldBtn"+reservation_ID+"\"onclick=\"fold("+reservation_ID+",event)\">");
                        html+=("<img class=\"listFoldImg\"src=\"/trms/resources/images/listFold.png\"/>");
                        html+=("</div>");
                        html+=("<div class=\"reservationInfo\"id=\"reservationInfo"+reservation_ID+"\">");
                        /////////////////////////////

                        html+=("<div class=\"ticketTitle\">Ticket Information</div>");
                        html+=("<div class=\"ticketContent\">");
						html+=("reservation_ID:"+reservation_ID+"<br/>");
						html+=("reservation_start_Daty:"+reservation_start_Daty+"<br/>");
						html+=("reservation_end_Daty:"+reservation_end_Daty+"<br/>");
						html+=("reservation_Num:"+reservation_Num+"<br/>");
						html+=("purpose:"+purpose+"<br/>");
						html+=("email:"+email+"<br/>");
						html+=("tele:"+tele+"<br/>");
						html+=("Order_Date:"+Order_Date+"<br/>");
				
						html+=("team_ID:"+team_ID+"<br/>");	
						html+=("order_Team:"+order_Team+"<br/>");	
						
						html+=("room_ID:"+room_ID+"<br/>");	
						html+=("room_item:"+room_item+"<br/>");
				
						html+=("applicant_ID:"+applicant_ID+"<br/>");	
						html+=("applicant_account:"+applicant_account+"<br/>");
						
						html+=("handler_by:"+handler_by+"<br/>");
						
						html+=("<div class='btnUpdate' name='approveBtn' id='approveBtn' onclick=' checkApproveRejectBtn(0,"+reservation_ID+")'>approve</div>");
						html+=("<div class='btnDelete' name='rejectBtn' id='rejectBtn' onclick=' checkApproveRejectBtn(1,"+reservation_ID+")'>reject</div>");
						
						html+=("</div>");

                         ///////////////////////////////
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
// 					alert(XMLHttpRequest);
// 					alert(textStatus);
// 					alert(errorThrown);
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

function checkCheckbox(args){
	//判断是否已经选择
	var  box = new Array(); 
	if($("input[name='checkbox']:checkbox:checked").length>0){	   
	    $("input[name='checkbox']:checkbox:checked").each(function(){
	    	box.push($(this).val());  
	    }); 
	 }else{
	    alert('You do not choose an item');
	    return false;
	}
	//ajax to approve or reject seleted reservation
	
	if(args=="0")
		//approve 
		$.ajax({
				url : "<%=request.getContextPath()%>/reservation/approveReservations",
				type : "get",
				data:'checkbox='+box,  		
				dataType : "html",
				success : function(json) {
					alert(json);
		        },
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
				}
			});
	//reject
	if(args=='1')
		$.ajax({
			url : "<%=request.getContextPath()%>/reservation/rejectReservations",
			type : "get",
			data:'checkbox='+box, 
			dataType : "html",
			success : function(json) {
				alert(json);
	        },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error");
			}
		});
	window.parent.location.reload();
	return false;
	
}

function checkApproveRejectBtn(args,reservation_ID){
	if(args=="0")
		//approve 
		$.ajax({
				url : "<%=request.getContextPath()%>/reservation/approveAReservation",
				type : "get",
				data : "reservation_ID=" + reservation_ID,	
				dataType : "html",
				success : function(json) {
					alert(json);
		        },
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
				}
			});
	//reject
	if(args=='1')
		$.ajax({
			url : "<%=request.getContextPath()%>/reservation/rejectAReservation",
			type : "get",
			data : "reservation_ID=" + reservation_ID,	
			dataType : "html",
			success : function(json) {
				alert(json);
	        },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error");
			}
		});
	window.parent.location.reload();
	return false;
}

</script>
</head>

<body id="listBody">
<div id="totalWrapper">


    <form action="<%=request.getContextPath()%>/reservation/approveReservations" method="post">
    <div id="topBar">
 
    <input type="checkbox" class="checkbox"onclick="check_all(this,'checkbox')">
    <span class="topBarItem topBarItem1">Reservation No.</span>
    <span class="topBarItem topBarItem2">Usage</span>
    <span class="topBarItem topBarItem3">Order Time</span>
    </div>
    <div id="listWrapper">   
    </div>
    
    
    
 
    </form>


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




					
<%-- 					  <c:forEach var="reservationDetial" items="${reservationDetials}">	 --%>
<%-- 				              ${reservationDetial.reservation }<br/> --%>
<!-- 				              <hr/> -->
<%-- 				               ${reservationDetial.team }<br/> --%>
				               
<!-- 				               </hr> -->
<%-- 				               ${reservationDetial.user } --%>
<%-- 			          </c:forEach> --%>




</html>
