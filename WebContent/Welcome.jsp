<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
<%@page import="com.bank.controller.AddLoggingInLogList"%>
<%@page import="com.bank.model.LoggingInLogDetails"%>
<%@page import="com.bank.controller.AddRegisteringLogList"%>
<%@page import="com.bank.model.RegisteringLogDetails"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Safe Banking Welcome</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link type="text/css" rel="stylesheet" href="CSS/Header.css">
		<link type="text/css" rel="stylesheet" href="CSS/Footer.css">
		<link type="text/css" rel="stylesheet" href="CSS/Welcome.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/Welcome.js"></script>
		<script type="text/javascript">
			var found="<%=(String)session.getAttribute("Logout")%>";
			if(found!="null"){
				$(document).ready(function() {
					$(".three").css("display", "block");
					$('.three').slideUp(0);
					$('.three').slideDown(500);
					$("#threep1").css("display", "block");
				});
				<% session.removeAttribute("Logout");%>
			}
		</script>
	</head>
	<body style="background-image: url('img/2.jpg');">
		<%@ include file="Header.html" %>
		<%
			System.out.println("\nFinally in Welcome.jsp\n");
			ShowAll show = new ShowAll();
			GetTime obj = new GetTime();
			String now=obj.now();
			
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			session.invalidate();
			session=request.getSession(true);
			System.out.println("\nSession Invalidated in Welcome.jsp\n");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			ArrayList<LoggingInLogDetails> loggingInLogList = new ArrayList<LoggingInLogDetails>();
			ArrayList<RegisteringLogDetails> registeringLogList = new ArrayList<RegisteringLogDetails>();
			session.setAttribute("loggingInLogList", loggingInLogList);
			session.setAttribute("registeringLogList", registeringLogList);
			
			AddRegisteringLogList.addTime("NA", "NA", now, "Welcome.jsp", "Successfully Loaded",request,response);
			AddLoggingInLogList.addTime("NA", "NA", now, "Welcome.jsp", "Successfully Loaded",request,response);
			
			show.ReqParam(request, response);
			show.SessionParam(request, response);
		%>
		<div class="twoc1">
			<img id="twoc1p1" alt="image" src="img/1.jpg">
			<h1 id="twoc1p2">Welcome... to Safe Banking</h1>
		</div>
		<br>
		<div class="twoc2">
			<form method="POST" action="Welcome">
				<input id="twoc2p1" type="submit" name="proceed" value="LOGIN">
				<input id="twoc2p2" type="submit" name="proceed" value="REGISTER">
			</form>
		</div>
		<div class="three">
			<div id="threep1">
				<h1 id="msgp1">Logged out Successfully</h1>
				<img id="threep1p1" alt="next" src="gifs/giphy.gif">
			</div>
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>