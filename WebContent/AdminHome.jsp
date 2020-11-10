<%@page import="com.bank.controller.GetTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="https://fonts.googleapis.com/css?family=Merriweather"  rel="stylesheet">
		<link type="text/css" rel="stylesheet" href="CSS/Header.css">
		<link type="text/css" rel="stylesheet" href="CSS/Footer.css">
		<link rel="stylesheet" href="CSS/AdminHome.css">
		<script type="text/javascript" src="JS/Jquery.js"></script>
	</head>
	<body>
		<%@ include file="ClearCache.jsp" %>
		<%@ include file="Header.html" %>
		<%
		GetTime obj = new GetTime();
		String now = obj.now();
		
		System.out.println("Admin_home.jsp showing " + now);
		String uname=(String)session.getAttribute("uname");
		%>
		<div class="two">
			<div class="twop1">
				<a class="twop1p1" href="registration1.jsp"><i class="fa fa-home"></i>
					Net Banking Registration
				</a>
				<a class="twop1p1" href="transfer_money.jsp"><i class="fa fa-exchange"></i>
					Update Passbook
				</a>
				<a class="twop1p1" href="unblock_customer_list"><i class=" fa fa-id-card-o"></i>
					Unblock Customer
				</a>
				<a class="twop1p1" href="forgot_picture"><i class="fa fa-globe"></i>
					Forgot Picture
				</a>
			</div>
			<div class="twop2">
				<h5 href="logout" style="float: left; padding-left: 50px; font-size: 15px; color: white;"><%=now%></h5>
				<h4 style="float: right; padding-right: 50px">
					Welcome, <%=uname%>
				</h4>
			</div>
		</div>

		<div class="four">
			<div class="fourp1">
				WELCOME ADMINISTRATOR
			</div>
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>