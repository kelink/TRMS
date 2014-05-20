<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>  
<html>
 <head>

   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title> Login </title>
  <link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet" type="text/css">
  <script src="<%=request.getContextPath()%>/resources/js/login.js" type="text/javascript"></script>
   <script src="<%=request.getContextPath()%>/resources/js/jquery.js" type="text/javascript"></script>
 


 </head>

 <body id="lcBody">

<div class="wrapper">
 

 <div id="viewport">
 <div id="viewportContainer">
 <div id="space">
 <div id="header">
     <div id="headerContainer">
	     <div id="logo">
	     </div>
     </div>
 </div>
 <div id="leftPanel">
 </div>
     <div id="loginPanel">
    
	    <form method="post" action="/trms/j_spring_security_check">
		 <h2>Login For System</h2>
		 <div id="inputWrapper">
		 <div class="inputLine">
		     <label id="label1" for="account">
			     <span id="icon1">
				 </span>
			 </label> 
		     <input type="text" class="textLine" id="account" name="j_username" maxlength="50">
	     </div>
		 <div class="inputLine">
		     <label id="label2" for="password">
			     <span id="icon2">
				 </span>
			 </label>
		     <input type="password" class="textLine"id="password" name="j_password" maxlength="30">
		 </div>

		 </div>

		 <div id="reset-btn">
		     <input type="reset" id="reset" value="reset">
		 </div>
		 <div id="loginInfo">	${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message} </div>

		 <div id="login-btn">
			 <input type="submit" value="Login" id=btn-submit"" class="btn-style" >
		 </div>
		 </form>
	 </div>
	 
	   <div class="Finfo">
          <div class="copyright"> &copy; Copyright 2014 by <a href="#">Lin Jiajian & Luo Kelin</a></div>
       </div>
       </div>
 </div>    
 </div>



  


  </div>

 </body>
</html>
