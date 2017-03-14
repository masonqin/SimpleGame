<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="jsp/include.jsp" %>   
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/websocket.js"> </script>
<script type="text/javascript" src="js/plantform.js"> </script>
<link rel="stylesheet" type="text/css" href="css/plantform.css">

<% 
	String userid=null;	
	if(session.getAttribute("userid")!=null) {
		userid = (String)session.getAttribute("userid").toString();
	}
%>
<title>Plantform</title>
</head>
<body>
	
	<div id="header">
	<h1>Welcome to game Platform</h1>
	</div>
	
	<div id="container">
		<div id="nav">
			<br>
			<p hidden="hidden" id="userid"><%=userid%></p>
			<a href="/SimpleGame/LoginRegist.jsp">Login</a><br>
			<a href="/SimpleGame/UserInfo.jsp">Info</a><br>
			<a href="/SimpleGame/Plantform.jsp">Plantform</a><br>
			<a href="/SimpleGame/Room.jsp">Game Room</a><br><br>
			
			<button id="getplantbtn" type="button">GetIn Plant</button> <br>
			<button id="addroom" type="button">Add Room</button> <br>
			<button id="getroombtn" type="button">Room List</button> <br>
		</div>
		<div id="main_div">
			<div id="roomboard">
		
			</div>
		
		</div>
		

	</div>
	
	
	


</body>
</html>