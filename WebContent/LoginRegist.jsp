<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="jsp/include.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Game Login</title>
</head>
<body>
	<script>
        $(function() {
            $('input[name=dob]').datepicker();
        });
    </script>
    
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
			<div id="login_div">
				<h2>Please Login</h2>
				<form method="POST" action="Login">
					<table frame="above">
						<tr>
						<td>Email : </td>
						<td><input type="text" name="log_email" /></td>			
						</tr>
						<tr>
						<td>Password : </td>
						<td><input type="password" name="log_password" /></td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td><input type="submit" value="submit" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="register_div">
				<h2>Please Registe</h2>
				<form method="POST" action="Register">
				<table frame="above">
					<tr>
						<td>First Name :</td>
						<td><input type="text" name="firstname"/></td>
					</tr>
					<tr>
						<td>Last Name :</td>
						<td><input type="text" name="lastname"/></td>
					</tr>
					<tr>
						<td>Date :</td>
						<td><input type="text" name="dob" value="<fmt:formatDate pattern= "MM/dd/yyyy" value="${user.dob}"/>"/></td>
					</tr>		
					<tr>
						<td>Email :</td>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td>Password :</td>
						<td><input type="text" name="password" /></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td><input type="submit" value="submit" /></td>
					</tr>
				</table>	
				</form>
			</div>
		</div>
		
	</div>

</body>
</html>