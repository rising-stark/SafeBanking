<%@page import="com.bank.model.BankDetails"%>
<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
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
		<link rel="stylesheet" type="text/css" href="CSS/Profile.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/Profile.js"></script>
		<script type="text/javascript" src="JS/ProfileLog.js"></script>
		<script type="text/javascript" src="JS/LoginTemplate.js"></script>
	</head>
	<body style="background-image: url('img/2.jpg');">
		<%@ include file="Header.html" %>
		<%@ include file="ClearCache.jsp" %>
		<%!
			GetTime obj = new GetTime();
			String logout_time,uname;
			ShowAll show = new ShowAll();
		%>
		<%
			System.out.println("\nFinally in FetchProfile.jsp\n");
			/*if(session.getAttribute("loggedInLogList")==null){
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}*/
			
			BankDetails bank=(BankDetails)request.getAttribute("bank");
			uname=(String)session.getAttribute("uname");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
		%>
		<div class="two">
			<div class="twoc1">
				<a class="twoc1c1 active" href="Profile?action=AccountProfile"><i class="fa fa-home"></i>
					My Account &amp; Profile
				</a>
				<a class="twoc1c1" href="Profile?action=PaymentTransfer"><i class="fa fa-usd"></i><i class="fa fa-usd"></i>
					Payments / Transfers
				</a>
				<a class="twoc1c1" href="Profile?action=ManageBeneficiaries"><i class="fa fa-list-ul"></i>
					Manage Beneficiaries
				</a>
				<a class="twoc1c1" href="Profile?action=Transaction"><i class=" fa fa-exchange"></i>
					Transactions
				</a>
			</div>
			<div class="twoc2">
				<%-- <div id="twoc2p1" class="twoc2c1">
					<%=logout_time%>				
				</div> --%>
				<div id="twoc2p2" class="twoc2c1"><i class=" fa fa-university"></i>  Welcome, <%=uname%>
					<ul class="dropdown">
						<li><a href="Profile?action=Profile"><i class="fa fa-user-circle-o"></i>Profile</a></li>
						<li><a href="Profile?action=Settings"><i class="fa fa-cog"></i>Settings</a></li>
						<li><a href="Profile?action=Logout"><i class="fa fa-sign-out"></i>Log out</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="three">
			<ul class="threec1" id="threep1">
				<li><a>Quick Links >></a></li>
				<li class="threep1c1"><a href="Profile?action=AddBeneficiary">Add Beneficiary</a></li>
				<li class="threep1c1"><a href="Profile?action=ApproveBeneficiary">Approve Beneficiary</a></li>
				<li class="threep1c1"><a href="Profile?action=DeleteBeneficiary">Delete Beneficiary</a></li>
				<li class="threep1c1"><a href="Profile?action=FundBeneficiary">Pay to Existing Beneficiaries</a></li>
				<li class="threep1c1"><a href="Profile?action=MiniStatement">Mini Statement</a></li>
			</ul>
		</div>
		<div class="four">
			<div class="fourc1">
				<h2>User Profile</h2>
			</div>
			<div class="fourc2">
				<table>
				    <tr>
					    <td width="40%">Account Holder Name: </td>
					    <td width="60%"><input type="text" name="fullname" size="50" value="<%= bank.getFname()+" "+bank.getLname() %>"readonly/></td>
				    </tr>
				    <tr>
					    <td>Account Number: </td>
					    <td><input type="text" name="accno" size="50" value="<%= bank.getAcc()%>" readonly/></td>
				    </tr>
				    <tr>
					    <td>Currency: </td>
					    <td><input type="text" name="currency" size="50" value="INR" readonly/></td>
				    </tr>
				    <tr>
					    <td>Phone Number: </td>
					    <td><input type="text" name="phone" size="50" value="<%= bank.getPhone()%>" readonly/></td>
				    </tr>
				    <tr>
					    <td>Email Address: </td>
					    <td><input type="text" name="email" size="50" value="<%= bank.getEmail()%>" readonly/></td>
				    </tr>
				    <tr>
					    <td>Residential Address: </td>
					    <td><textarea rows="3" cols="93" readonly><%=bank.getAddress() %></textarea></td> 
				    </tr>
				</table>
			</div>
		</div>
		<div class="five">
			<img alt="loading" src="gifs/processing.gif">
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>
