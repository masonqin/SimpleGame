<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="jsp/include.jsp" %> 
<%@ page import="game.model.User" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Info</title>
</head>
<body>
<%
	if(session.getAttribute("userid")!=null) {
		String userid = (String)session.getAttribute("userid").toString();
	}
	User user = (User)request.getAttribute("userinfo");
%>

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
		</div>

		<div id="main_div">
			<table frame="border">
				<tr>
					<td>First Name</td>
					<td> <%= user.getFirstName() %> </td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td> <%= user.getLastName() %> </td>
				</tr>
				<tr>
					<td>Email</td>
					<td> <%= user.getEmail() %> </td>
				</tr>
				<tr>
					<td>Date</td>
					<td> <%= user.getDob() %> </td>
				</tr>
				<tr>
					<td>Password</td>
					<td> 
						change
					</td>
				</tr>
			</table>
		</div>
	</div>


</body>
</html>