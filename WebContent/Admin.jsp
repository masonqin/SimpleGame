<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="jsp/include.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/ajax.js"> </script>


<title>Admin</title>
</head>
<body>

	<div id="header">
	<h1>Welcome to game Platform</h1>
	</div>
	<div id="container">
		<div id="nav">
			<br><br>
			<a href="/SimpleGame/LoginRegist.jsp">Login</a><br>
			<a href="/SimpleGame/UserInfo.jsp">Info</a><br>
			<a href="/SimpleGame/Plantform.jsp">Plantform</a><br>
			<a href="/SimpleGame/Room.jsp">Game Room</a><br>
			<a href="/SimpleGame/OnlineUsers.jsp">Online List</a><br>
		</div>
		
		<div id="main_div">
			<input id="ajax_get" type="button" value="Get User List" />
			
			<table id="usertable" >
			
			</table>
		</div>
	</div>

	<!--  div id="container">
		<input type="button" value="Get User List" onClick='ajaxAsyncRequest("GetUsers")' />
	</div>
	-->
	 
	 
	 

</body>
</html>