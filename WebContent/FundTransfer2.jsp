<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
<%@page import="com.bank.controller.AddLoggedInLogList"%>
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
		<link type="text/css" rel="stylesheet" href="CSS/FundTransfer2.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/FundTransfer2.js"></script>
		<script type="text/javascript" src="JS/FundTransfer2Log.js"></script>
		<script type="text/javascript" src="JS/LoginTemplate.js"></script>
		<script type="text/javascript">
			var found="<%=(String)session.getAttribute("notFound")%>";
			if(found!="null"){
				$(document).ready(function() {
					$("#msgp2").text(found);
					$(".six").css("display", "block");
					$("#sixp2").css("display", "block");
				});
				<%
					session.removeAttribute("notFound");
					System.out.println("checking in Registration1.jsp found attr "+(String)session.getAttribute("notFound"));
				%>
			}
		</script>
	</head>
	<body style="background-image: url('img/2.jpg');">
		<%@ include file="Header.html" %>
		<%@ include file="ClearCache.jsp" %>
		<%!
			GetTime obj = new GetTime();
			String uname, limit;
			ShowAll show = new ShowAll();
		%>
		<%
			System.out.println("\nFinally in FundTransfer2.jsp\n");
			if(session.getAttribute("loggedInLogList")==null){
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			uname=(String)session.getAttribute("uname");
			limit=(String)session.getAttribute("amtLimit");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
		%>
		<div class="two">
			<div class="twoc1">
				<a class="twoc1c1" href="FundTransfer2?action=AccountProfile"><i class="fa fa-home"></i>
					My Account &amp; Profile
				</a>
				<a class="twoc1c1 active" href="FundTransfer2?action=PaymentTransfer"><i class="fa fa-usd"></i><i class="fa fa-usd"></i>
					Payments / Transfers
				</a>
				<a class="twoc1c1" href="FundTransfer2?action=ManageBeneficiaries"><i class="fa fa-list-ul"></i>
					Manage Beneficiaries
				</a>
				<a class="twoc1c1" href="FundTransfer2?action=Transaction"><i class=" fa fa-exchange"></i>
					Transactions
				</a>
			</div>
			<div class="twoc2">
				<%-- <div id="twoc2p1" class="twoc2c1">
					<%=logout_time%>				
				</div> --%>
				<div id="twoc2p2" class="twoc2c1"><i class=" fa fa-university"></i>  Welcome, <%=uname%>
					<ul class="dropdown">
						<li><a href="FundTransfer2?action=Profile"><i class="fa fa-user-circle-o"></i>Profile</a></li>
						<li><a href="FundTransfer2?action=Settings"><i class="fa fa-cog"></i>Settings</a></li>
						<li><a href="FundTransfer2?action=Logout"><i class="fa fa-sign-out"></i>Log out</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="three">
			<ul class="threec1" id="threep1">
				<li><a>Quick Links >></a></li>
				<li class="threep1c1"><a href="FundTransfer2?action=AddBeneficiary">Add Beneficiary</a></li>
				<li class="threep1c1"><a href="FundTransfer2?action=ApproveBeneficiary">Approve Beneficiary</a></li>
				<li class="threep1c1"><a href="FundTransfer2?action=DeleteBeneficiary">Delete Beneficiary</a></li>
				<li class="threep1c1"><a href="FundTransfer2?action=FundTransferBeneficiary">Fund Transfer Existing Beneficiaries</a></li>
				<li class="threep1c1"><a href="FundTransfer2?action=MiniStatement">Mini Statement</a></li>
			</ul>
		</div>
		<div class="four">
			<div class="fourc1">
				<h2>Enter the amount you want to transfer</h2>
			</div>
			<div class="fourc2">
				<form method="POST" action="FundTransfer2">
					<table>
						<tr>
							<td width="40%">Enter the Amount:* </td>
							<td width="50%"><input type="number" name="amount" min="100" max="500000" placeholder="Enter The Amount less than <%=limit %>" required></td>
							<td width="10%"><img id="amountimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
						</tr>
						<tr>
							<td><input class="grow" type="button" name="btnBACK" value="BACK" onclick="history.back()"></td>
							<td><input type="submit" name="btnNEXT" value="NEXT" disabled></td>
	       				</tr>
					</table>
					<input type="hidden" name="fundtransferpage2time" value="0">
					<input type="hidden" name="amounttime" value="0">
					<input type="hidden" name="subtime2" value="0">
					<input type="hidden" name="temp" value="0"/>
				</form>
			</div>
		</div>
		<h3 id="note">Note: The fields marked with * are compulsory</h3>
		<div class="five">
			<img alt="loading" src="gifs/loading.gif">
			<h1>PROCESSING</h1>
		</div>
		<div class="six">
			<div id="sixp1">
				<h2 id="msgp1">Redirecting to Email Verify Page</h2>
				<img id="sixp1p1" alt="next" src="gifs/next.gif">
			</div>
			<div id="sixp2">
				<h2 id="msgp2">Error Messages</h2>
				<img id="sixp2p1" alt="wrong" src="gifs/wrong2.png">
			</div>
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>