<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
		<title>Safe Banking Login</title>
		<link type="text/css" rel="stylesheet" href="CSS/Header.css">
		<link type="text/css" rel="stylesheet" href="CSS/Footer.css">
		<link type="text/css" rel="stylesheet" href="CSS/AdminLogin.css">
		<script type="text/javascript" src="JS/jquery.js"></script>
		<script type="text/javascript" src="JS/AdminLogin.js"></script>
		<script type="text/javascript">
			var found="<%=(String)session.getAttribute("notFound")%>";
			console.log(typeof found);
			console.log("found worked");
			console.log(found);
			<%System.out.println("above working");%>
			if(found!="null"){
				alert(found);
				<%session.setAttribute("notFound",null);
				System.out.println("found attr "+(String)session.getAttribute("notFound")+"");
				%>
			}
		</script>
	</head>
	<body style="background-image: url('img/3.jpg');">
		<%@ include file="ClearCache.jsp" %>
		<%@ include file="Header.html" %>
		<%!HttpSession session;
		String name;
		ShowAll show= new ShowAll();
		GetTime obj=new GetTime();
		String logout_time;%>
		<%
		String now = obj.now();
		System.out.println(" reloading admin_login.jsp "+now);
		%>
		<div id="wrapper">
			<div id="first">
				<h4>(CARE: Username and password are case sensitive.)</h4>
				<form method="post" action="admin_login" class="centered_form">
					<table style="width:100%">
						<tr>
						    <td>Username*</td>
						    <td><input type="text" name="uname" size="50" placeholder="Enter Username" autocomplete="off" required/></td>
					    </tr>
					    <tr>
						    <td>Password*</td>
						    <td><input type="password" name="password" placeholder="Enter password" autocomplete="off"  required/></td>
					    </tr>
					    <tr>
						    <td colspan="2"><input type="submit" name="btnLogin" value="Submit" disabled></td>
					    </tr>
					</table>
				</form>
				<div>Fields marked with * are compulsory</div>
			</div>
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>