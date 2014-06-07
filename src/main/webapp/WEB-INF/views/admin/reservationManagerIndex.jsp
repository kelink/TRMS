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
<title>Reservation Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="<%=request.getContextPath()%>/resources/images/hsbcicon.ico" type="image/x-icon"/>
<link href="<%=request.getContextPath()%>/resources/css/lcIndex.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/checkReservation.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/reservationList.css" rel="stylesheet" >
<script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js/adminReservation.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/resources/css/footer.css" rel="stylesheet" >
<link href="<%=request.getContextPath()%>/resources/css/reservationList.css" rel="stylesheet" >
</head>
<body>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>  

<div id="cover">
 </div>
 <div class="wrapper">
     <div class="header">
	     <div class="headerContainer">
		 <span id="logo"><img src="<%=request.getContextPath()%>/resources/images/hsbcLogo.png"/></span>
		 </div>
	 </div>
	 
	 <div class="status">

	     <div class="statusContainer">
	         <div id="userInfo">
	         <img id="userCard" width="35px" height="35px" src="<%=request.getContextPath()%>/resources/images/card.png"/>
	         <div id="userName">
	         <span>Welcome! <security:authentication property="principal.username"/></span>
	         </div>
	         </div>
	         <button id="logoutBtn" class="btnLogout" onclick="logout('${logoutUrl}');">
	         <span id="logoutImage"><img id="logoutIcon" width="20px"height="20px" src="<%=request.getContextPath()%>/resources/images/logout.png"/>
	         </span>
	         <span>LogOut</span>
	         </button>
	      
	     </div>
	 </div>
	 
	 <div>
	     <div class="seperateLine">
		 </div>
	 </div>
	
	 <div class="navigator">
	     <div class="navContainer">
		     <ul class="navi">
			     <li>
				     <a href="<%=request.getContextPath()%>/admin/index" class="navItem">Book Room</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/reservation/reservationManagerIndex" class="navItem selected">Reservation Management</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/blacklist/index" class="navItem">BlackList Management</a>
				 </li>
				 <li>
				     <a href="<%=request.getContextPath()%>/department/index"class="navItem">Department Management </a>
				 </li>			
				  <li>
				     <a href="<%=request.getContextPath()%>/profile/modify"class="navItem">Manage My Profile</a>
				 </li>
			 </ul>
		 </div>
	 </div>
	 
	 <div class="middle">
	     <div class="middleContainer">
		     <h1 class="roomList">Reservation List</h1>
			 <div class="roomListBody" style="overflow:hidden;">
			 
			     <div id="checkLeftPanel">
			      <!-- handle  begin -->
					 <div id="search0" class="checkMethod selected">
			             ${recordCount} reservations unhandle
			         </div>
			         <div id="search1" class="checkMethod">
			             All Reservation
			         </div>
			         <div class="seperateLineSearch">
			         </div>
			         <div id="search2" class="checkMethod">
			             Search by ID
			         </div>
			         <!-- handle  end -->
			         <div id="search3" class="checkMethod">
			             Fuzzy search
			         </div>
			         <div id="search4" class="checkMethod">
			             Precise search
			         </div>
			         
			         <form action="<%=request.getContextPath()%>/reservation/list" method="post"target="checkRightInner">
			         <div id="searchDivWrapper">
			             <div class="searchDiv">
			             <label class="searchLabel">Reservation Num...</label>
			             <input type="text" class="searchInput" id="searchInput1"name="reservation_Num"></input>
			             </div>
<!-- --------------------------------------------------------------------- -->
			            
			         
			         
			         
			         
			         
			             <!--模糊查询额外部分-->
			             <div class="searchDiv vague" >
			             <label class="searchLabel">User LN...</label>
			             <input type="text" class="searchInput" id="searchInput2"name="email"></input>
			             </div>
			             <div class="searchDiv vague">
			             <label class="searchLabel">User Tele...</label>
			             <input type="text" class="searchInput" id="searchInput3"name="tele"></input>
			             </div>
			             <div class="searchDiv vague">
			             <label class="searchLabel">Purpose...</label>
			             <input type="text" class="searchInput" id="searchInput4"name="purpose"></input>
			             </div>
			         
			             <!--模糊查询额外部分-->
			             
			             
			             <!--精确查询额外部分-->
			             <div class="preciseSearch preciseLabel">Order Time</div>
			             <input type="date" name="order_Time" id="orderTime" class="preciseSearch preciseInput">
			             <div class="preciseSearch preciseLabel">Start Date</div>
			             <input type="date" name="Applied_Start_Date" id="startDate"class="preciseSearch preciseInput">
                         <div class="preciseSearch preciseLabel">End Date</div>
		                 <input type="date" name="Applied_End_Date" id="endDate"class="preciseSearch preciseInput">
			             <div class="preciseSearch preciseLabel">Ticket Status</div>
			             <select name="status"id="status" class="preciseSearch preciseInput">
			                  <option></option>
			                  <option value="1">accept</option>
			                  <option value="0">refuse</option>
		 	                  <option value="-1">unhandle</option>
		                 </select>
		                 <div class="preciseSearch preciseLabel">Order Room</div>
		                 <select name="room_ID" id="orderRoom"class="preciseSearch preciseInput">
		                      <option></option>
			                  <c:forEach var="room" items="${rooms}">	
				              <option value='${room.room_ID}'>${room.item}</option>
			                  </c:forEach>		
		                 </select>
		                 	
		                 
		                 <div class="preciseSearch preciseLabel">Order Team</div>
		                 <select name="team_ID" id="teamId"class="preciseSearch preciseInput">
			                  <option></option>
			                  <c:forEach var="team" items="${teams}">	
				              <option value='${team.team_ID}'>${team.teamName}</option>
			                  </c:forEach>
		                 </select>
			             <!--精确查询额外部分-->
			             
			             
			             
			         </div>
			         <input type="submit" name="submit"class="btnSearch"id="submitSearch"value="Search">
			             
			         </form>
			     </div>
			     <div id="checkRightPanel">
<!-- 			         ------------------------------------------------------ -->
			         <div id="totalTitle">
                          Reservation List
                     </div>
    
                     <div id="listTitle">
                         <div id="sortPanel">
                             <div class="panelHeader">
                             <span class="headerText">Sort Option</span>
                             <span class="close"id="closeSort"onclick="close()"><img class="closeIcon" src="<%=request.getContextPath()%>/resources/images/closeShallow.png"width="16px"height="16px"/></span>

                             </div>
                             
          					<a class="sortPanelItem"onclick="closeSort();"  target="checkRightInner" href="<%=request.getContextPath()%>/reservation/list?orderby=desc">Time DESC</a>
                            <a class="sortPanelItem"onclick="closeSort();" target="checkRightInner" href="<%=request.getContextPath()%>/reservation/list?orderby=asc">Time ASC</a>
                       
            
            
                           </div>
                           <div id="showPanel">
                               <div class="panelHeader">
                                   <span class="headerText">Show Option</span>
                                   <span class="close"id="closeShow"><img class="closeIcon" src="<%=request.getContextPath()%>/resources/images/closeShallow.png"width="16px"height="16px"/></span>

                               </div>
                             <a class="sortPanelItem"onclick="closeShow();" target="checkRightInner" href="<%=request.getContextPath()%>/reservation/list?status=-1">Unhandled</a>
                               <a class="sortPanelItem"onclick="closeShow();" target="checkRightInner" href="<%=request.getContextPath()%>/reservation/list?status=1">Approve</a>
                               <a class="sortPanelItem"onclick="closeShow();" target="checkRightInner" href="<%=request.getContextPath()%>/reservation/list?status=0">Reject</a>
            
                               
            
                          </div>
                          <div id="btnFunctionWrapper">
                              <a href="<%=request.getContextPath()%>/reservation/list"target="checkRightInner" class="btnFunction">All</a>
                              <div id="verticalLine"></div>

                              <div class="btnFunction" id="approveBtn" onClick="window.frames['checkRightInner'].checkCheckbox(0)">Approve</div>
                              <div class="btnFunction" id="rejectBtn" onClick="window.frames['checkRightInner'].checkCheckbox(1)">Reject</div>
                             <!-- 
                              <div class="btnFunction" id="conflictBtn">Conflict</div>
                               -->
                              <div class="btnFunction" id="showBtn">Show<img class="downImg" src="<%=request.getContextPath()%>/resources/images/down.png"/></div>
                              <div class="btnFunction" id="sortBtn">Sort<img class="downImg" src="<%=request.getContextPath()%>/resources/images/down.png"/></div>
                          </div>   
                   </div>
			        
			         
<!-- 			         ------------------------------------------------------ -->			         
			         <iframe id="checkRightInner" name="checkRightInner" src="<%=request.getContextPath()%>/reservation/reservationManagerList" >
			         </iframe>			     
			     </div>		 
			 </div>
		 </div>
	 </div>

 </div>

 
 
 

 <div id="footerContainer">
      <div id="footer">
          <span id="copyright"> &copy; Copyright 2014 by <a href="#">Lin Jiajian</a>&<a href="#">Luo Kelin</a></span>
          <span><img id="hsbcIconFooter" width="30px"height="15px" src="<%=request.getContextPath() %>/resources/images/hsbcFooter.png"/></span>
          <span id="footerWord"><a href="#">Team</a> <a href="#">Telephone</a><a href="#">Email</a><a href="#">about</a></span>
      </div>
 </div>


</body>

</html>
