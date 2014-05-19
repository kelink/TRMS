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
<title>Insert title here</title>
</head>
<body>
<h1>订房界面</h1>
<script type="text/javascript" src="../resources/js/jquery-1.7.2.js"></script>  
<script type="text/javascript">  
$(document).ready(function(){
    $("button").click(function(){ 
    	 var saveDataAry=[];  
         var data1={"userName":"test","address":"gz"};  
         var data2={"userName":"ququ","address":"gr"};  
         saveDataAry.push(data1);  
         saveDataAry.push(data2);         
        $.ajax({ 
            type:"POST", 
            url:"<%=request.getContextPath()%>/book/ajaxtest", 
            dataType:"json",      
            contentType:"application/json",               
            data:JSON.stringify(saveData), 
            success:function(data,status){ 
				    alert(data);                                    
            } 
         });
    });  
});  
</script> 


<button id="button" name="button" value="test">test</button>

<%=request.getContextPath()%> 

<form action="<%=request.getContextPath()%>/book/" method="post" name="bookForm">

Room
Planned Use End Time：

Planned Use Start Time：
User LN 
User Team:

User TelLine:

Use Resaon:
</form>
 
</body>
</html>