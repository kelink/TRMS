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
<title>万年历</title>
</head>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
        var pageIndex = 0;
        var pageSize = 5;
        $(function () {          
                pageIndex = 1;
                AjaxGetData(pageIndex, pageSize);      
        });

        function AjaxGetData( index, size) {
            $.ajax({
                url: "${pageContext.request.contextPath}/book/listPageRoom",
                type: "Get",
                data: "pageNum=" + index + "&pageSize=" + size,
                dataType: "json",
                success: function (json) {
                     var html = "";
                     html += "<table>";
                     html += "<thead>";
                     html += "<tr><th colspan=7 >Position List</th></tr>";
                     html += "<tr><th>ID</th><th>Name</th><th>Location</th><th>Nature</th><th>Number</th><th>End Date</th><th>Operation</th></tr>";
                
                     html += "</thead>";
                     html += "<tbody>";  
                     	//获取构造输出查询内容
                   for(position in json){
                   html += "<tr>";
                   html += "<td>"+json[position].room_ID+"</td>";
                       html += "<td>"+json[position].item+"</td>";
                       html += "<td>"+json[position].room_Status+"</td>";
                       html += "<td>"+json[position].last_Used_Date+"</td>";
                       html += "<td><a href='editposition?id="+json[position].room_ID+"'>Edit&nbsp;<a href='position?id="+json[position].room_ID+"'>View</td>"; 
                       html += "</tr>";
                
                   }
                     //构造输出上下页和首页尾页的标签
                   html += "</tbody>";
                  
                  html += "<tfoot>";
                  html += "<tr>";
                  html += "<td colspan='7'>";
                  html += "<span>Total Records:" + ${recordCount} + "; Total Page:<span id='count'>" +${pageCount} + "" + "<br/>";
                  html += "<a href='javascript:void' onclick='GoToFirstPage()' id='FirstPage' >First&nbsp;&nbsp; ";
                  html += "<a href='javascript:void' onclick='GoToPrePage()' id='PrePage' >Pre&nbsp;&nbsp; ";
                  html += "<a href='javascript:void' onclick='GoToNextPage()' id='NextPage'>Next&nbsp;&nbsp; ";
                  html += "<a href='javascript:void' onclick='GoToEndPage()' id='EndPage' >Last&nbsp;&nbsp; ";
                  html += "<input type='text' size='4' name='page' /><input type='button' value='Jump' onclick='GoToAppointPage(this)' /> ";
                  html += "</td>";
                  html += "</tr>";
                  html += "</tfoot>";
                  html += "</table>";
                  //写入文件
                  $('#divResult').html("");
                  $('#divResult').html(html);               
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest);
                    alert(textStatus);
                    alert(errorThrown);
                }
            });
        }
        
        function GoToFirstPage() {
            pageIndex = 1;
            AjaxGetData(pageIndex, pageSize);
            alert("GoToFirstPage");
        }
        
        function GoToPrePage() {
            pageIndex -= 1;
            pageIndex = pageIndex >= 1 ? pageIndex : 1;
            AjaxGetData( pageIndex, pageSize);
            alert("GoToPrePage");
        }
       
        function GoToNextPage() {
            if (pageIndex < parseInt($("#count").text())) {
                pageIndex += 1;
            }
            AjaxGetData( pageIndex, pageSize);
            alert("GoToNextPage");
        }
       
        function GoToEndPage() {
            pageIndex = parseInt($("#count").text()) ;
            AjaxGetData( pageIndex, pageSize);
            alert("GoToEndPage");
        }
       
        function GoToAppointPage(e) {
            var page = $(e).prev().val();
            if (isNaN(page)) {
                alert("Page should be a valid number");
            }
            else {
                var tempPageIndex = pageIndex;
                pageIndex = parseInt($(e).prev().val());
                if (pageIndex <= 0 || pageIndex > parseInt($("#count").text())) {
                    pageIndex = tempPageIndex;
                    alert("Please input valid page scope!");
                }
                else {
                	AjaxGetData(pageIndex, pageSize);
                }
            }
            alert("GoToAppointPage");
        }
    </script>
    <body>
   <H1>Welcom To  Here</H1>
    <div id="divResult" > TEST page</div>
    </body>