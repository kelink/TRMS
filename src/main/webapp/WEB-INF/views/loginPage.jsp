<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
 <head>

   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title> Login </title>
  <link href="../resources/css/style.css" rel="stylesheet" type="text/css">

 </head>

 <body>

 <div id="header">
     <div id="headerContainer">
	     <div id="logo">
	     </div>
     </div>
 </div>
 
 <div id="viewport">
 <div id="leftPanel">
 </div>
     <div id="loginPanel">
	     <form method="post" action="">
		 <h2>Login For System</h2>
		 <div id="inputWrapper">
		 <div class="inputLine">
		     <label id="label1" for="account">
			     <span id="icon1">
				 </span>
			 </label>
		     <input type="text" class="textLine" id="account" name="account">
	     </div>
		 <div class="inputLine">
		     <label id="label2" for="password">
			     <span id="icon2">
				 </span>
			 </label>
		     <input type="password" class="textLine"id="password" name="password">
		 </div>

		 </div>

		 <div id="reset-btn">
		     <input type="reset" id="reset" value="reset">
		 </div>

		 <div id="login-btn">
			 <input type="submit" value="Login" class="btn-style" >
		 </div>
		 </form>
	 </div>

 </div>


 <div id="footer">
 <div id="footerContainer">
 <div id="footerContent">
      <h3>Design by Ljj and Link in 2014.</h3>

 </div>
 </div>
 </div>
  



 </body>
</html>

