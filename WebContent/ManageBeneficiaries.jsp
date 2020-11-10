<%@page import="com.bank.controller.ShowAll"%>
<%@page import="com.bank.controller.GetTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Safe Banking Services</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="CSS/Header.css">
		<link rel="stylesheet" type="text/css" href="CSS/Footer.css">
		<link rel="stylesheet" type="text/css" href="CSS/LoginTemplate.css">
		<link rel="stylesheet" type="text/css" href="CSS/LoginTemplate2.css">
		<link rel="stylesheet" type="text/css" href="CSS/ManageBeneficiaries.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/ManageBeneficiaries.js"></script>
		<script type="text/javascript" src="JS/ManageBeneficiariesLog.js"></script>
		<script type="text/javascript" src="JS/LoginTemplate.js"></script>
	</head>
	<body  style="background-image: url('img/2.jpg');">
		<%@ include file="Header.html" %>
		<%@ include file="ClearCache.jsp" %>
		<%!
			GetTime obj = new GetTime();
			String logout_time,uname;
			ShowAll show = new ShowAll();
		%>
		<%
			System.out.println("\nFinally in ManageBeneficiaries.jsp\n");
			if(session.getAttribute("loggedInLogList")==null){
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			/* 
			added in ManageBeneficiaries.java
			AddLoggedInLogList.add("ManageBeneficiaries.jsp",request,response);
			
			logout_time = obj.now();
			*/
			uname=(String)session.getAttribute("uname");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
		%>
		<div class="two">
			<div class="twoc1">
				<a class="twoc1c1" href="ManageBeneficiaries?action=AccountProfile"><i class="fa fa-home"></i>
					My Account &amp; Profile
				</a>
				<a class="twoc1c1" href="ManageBeneficiaries?action=PaymentTransfer"><i class="fa fa-usd"></i><i class="fa fa-usd"></i>
					Payments / Transfers
				</a>
				<a class="twoc1c1 active" href="ManageBeneficiaries?action=ManageBeneficiaries"><i class="fa fa-list-ul"></i>
					Manage Beneficiaries
				</a>
				<a class="twoc1c1" href="ManageBeneficiaries?action=Transaction"><i class=" fa fa-exchange"></i>
					Transactions
				</a>
			</div>
			<div class="twoc2">
				<%--
				<div id="twoc2p1" class="twoc2c1">
					<%=logout_time%>				
				</div> 
				--%>
				<div id="twoc2p2" class="twoc2c1"><i class=" fa fa-university"></i>  Welcome, <%=uname%>
					<ul class="dropdown">
						<li><a href="ManageBeneficiaries?action=Profile"><i class="fa fa-user-circle-o"></i>Profile</a></li>
						<li><a href="ManageBeneficiaries?action=Settings"><i class="fa fa-cog"></i>Settings</a></li>
						<li><a href="ManageBeneficiaries?action=Logout"><i class="fa fa-sign-out"></i>Log out</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="three">
			<ul class="threec1" id="threep1">
				<li title="Toggle the Dropdown" id="codefold"><a>Quick Links</a> <span id="codefoldp1"><i class="fa fa-angle-double-left"></i> <span>...</span> <i class="fa fa-angle-double-right"></i></span>  <i id="codefoldp2" class="fa fa-angle-double-right"></i></li>
				<li class="threep1c1"><a href="ManageBeneficiaries?action=AddBeneficiary">Add Beneficiary</a></li>
				<li class="threep1c1"><a href="ManageBeneficiaries?action=ApproveBeneficiary">Approve Beneficiary</a></li>
				<li class="threep1c1"><a href="ManageBeneficiaries?action=DeleteBeneficiary">Delete Beneficiary</a></li>
				<li class="threep1c1"><a href="ManageBeneficiaries?action=FundBeneficiary">Pay to Existing Beneficiaries</a></li>
				<li class="threep1c1"><a href="ManageBeneficiaries?action=MiniStatement">Mini Statement</a></li>
			</ul>
		</div>
		<div class="four">
			<div class="fourc1" id="fourp1">
				<h2>Manage Beneficiaries</h2>
			</div>
			<div class="fourc2"  id="fourp2">
				<div class="fourc2c1" id="fourp2p1">
					<a href="ManageBeneficiaries?action=ShowBeneficiary">
						<i class="fa fa-list-ul"></i>
						Existing Beneficiaries
					</a>
				</div>
				<div class="fourc2c1" id="fourp2p2">
					<a href="ManageBeneficiaries?action=AddBeneficiary">
						<i class="fa fa-address-book"></i>
						Add Beneficiary
					</a>
				</div>
				<div class="fourc2c1" id="fourp2p3">
					<a href="ManageBeneficiaries?action=ApproveBeneficiary">
						<i class="fa fa-check"></i>
						Approve Beneficiary
					</a>
				</div>
				<div class="fourc2c1" id="fourp2p4">
					<a href="ManageBeneficiaries?action=DeleteBeneficiary">
						<i class="fa fa-close"></i>
						Delete Beneficiary
					</a>
				</div>
			</div>
		</div>
		<div class="five">
			<img alt="loading" src="gifs/processing.gif">
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>