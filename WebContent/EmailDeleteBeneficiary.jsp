<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
<%@page import="com.bank.model.BeneficiaryDeleteLogDetails"%>
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
		<link rel="stylesheet" type="text/css" href="CSS/EmailDeleteBeneficiary.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/EmailDeleteBeneficiary.js"></script>
		<script type="text/javascript" src="JS/EmailDeleteBeneficiaryLog.js"></script>
		<script type="text/javascript" src="JS/LoginTemplate.js"></script>
		<script type="text/javascript">
			var msg="<%=(String)session.getAttribute("sendDeleteEmail")%>";
			if(msg!="null"){
				emailDeletionInitiated();
			}
		</script>
	</head>
	<body style="background-image: url('img/2.jpg');">
		<%@ include file="ClearCache.jsp" %>
		<%@ include file="Header.html" %>
		<%!
			GetTime obj = new GetTime();
			ShowAll show = new ShowAll();
			BeneficiaryDeleteLogDetails benLog;
			String uname, ex, benname, benacc;
			int i, approved=0;
		%>
		<%
			System.out.println("\nFinally in EmailDeleteBeneficiary.jsp\n");	
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			if(session.getAttribute("loggedInLogList")==null){
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			
			benLog=(BeneficiaryDeleteLogDetails)session.getAttribute("benLog");
			
			approved=(int)session.getAttribute("approved");
			uname=(String)session.getAttribute("uname");
			ex=(String)session.getAttribute("ex");
			benname=(String)session.getAttribute("benname");
			benacc=benLog.getBenacc();
			//benacc=benacc.substring(0,2)+"XXXXXXXX"+benacc.substring(10);
		%>
		<div class="two">
			<div class="twoc1">
				<a class="twoc1c1" href="EmailDeleteBeneficiary?action=AccountProfile"><i class="fa fa-home"></i>
					My Account &amp; Profile
				</a>
				<a class="twoc1c1" href="EmailDeleteBeneficiary?action=PaymentTransfer"><i class="fa fa-usd"></i><i class="fa fa-usd"></i>
					Payments / Transfers
				</a>
				<a class="twoc1c1 active" href="EmailDeleteBeneficiary?action=ManageBeneficiaries"><i class="fa fa-list-ul"></i>
					Manage Beneficiaries
				</a>
				<a class="twoc1c1" href="EmailDeleteBeneficiary?action=Transaction"><i class=" fa fa-exchange"></i>
					Transactions
				</a>
			</div>
			<div class="twoc2">
				<%-- <div id="twoc2p1" class="twoc2c1">
					<%=logout_time%>				
				</div> --%>
				<div id="twoc2p2" class="twoc2c1"><i class=" fa fa-university"></i>  Welcome, <%=uname%>
					<ul class="dropdown">
						<li><a href="EmailDeleteBeneficiary?action=Profile"><i class="fa fa-user-circle-o"></i>Profile</a></li>
						<li><a href="EmailDeleteBeneficiary?action=Settings"><i class="fa fa-cog"></i>Settings</a></li>
						<li><a href="EmailDeleteBeneficiary?action=Logout"><i class="fa fa-sign-out"></i>Log out</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="three">
			<ul class="threec1" id="threep1">
				<li title="Toggle the Dropdown" id="codefold"><a>Quick Links</a> <span id="codefoldp1"><i class="fa fa-angle-double-left"></i> <span>...</span> <i class="fa fa-angle-double-right"></i></span>  <i id="codefoldp2" class="fa fa-angle-double-right"></i></li>
				<li class="threep1c1"><a href="EmailDeleteBeneficiary?action=AddBeneficiary">Add Beneficiary</a></li>
				<li class="threep1c1"><a href="EmailDeleteBeneficiary?action=ApproveBeneficiary">Approve Beneficiary</a></li>
				<li class="threep1c1"><a href="EmailDeleteBeneficiary?action=DeleteBeneficiary">Delete Beneficiary</a></li>
				<li class="threep1c1"><a href="EmailDeleteBeneficiary?action=FundTransferBeneficiary">Pay to Existing Beneficiaries</a></li>
				<li class="threep1c1"><a href="EmailDeleteBeneficiary?action=MiniStatement">Mini Statement</a></li>
			</ul>
		</div>
		<div class="four">
			<div class="fourc1">
				<h4>Enter the OTP sent to your registered email&nbsp;&nbsp;<span id="email"><em><%=ex %></em></span>&nbsp;&nbsp;to delete the beneficiary</h4>
			</div>
			<div class="fourc2">
			<form method="POST" action="EmailDeleteBeneficiary">
					<table id="fourc2p1">
					    <tr>
						    <td>Beneficiary Name: </td>
						    <td><input type="text" name="benname" size="40" value="<%=benname  %>"readonly/></td>
					    </tr>
					    <tr>
						    <td>Beneficiary Account Number: </td>
						    <td><input type="text" name="benacc" size="10" value="<%=benacc %>" readonly/></td>
					    </tr>
					    <tr>
						    <td>Beneficiary Approved Status: </td>
						    <%if(approved==0) {%>
								<td><input type="text" name="status" size="10" value="NOT APPROVED" readonly/></td>
							<%}else{%>
								<td><input type="text" name="status" size="10" value="APPROVED" readonly/></td>
							<%}%>
					    </tr>
					</table>
					<table id="fourc2p2">
					    <tr>
						    <td>Enter OTP: </td>
						    <td><input type="text" name="OTPUser" size="6" placeholder="Enter 6-Digit OTP"/></td>
					    </tr>
					    <tr>
						    <td colspan="2"><input type="button" name="btnDELETE" value="DELETE" disabled></td>
						</tr>
					</table>
					<input type="hidden" name="benemaildeletepagetime" value="0"/>
					<input type="hidden" name="otptime" value="0"/>
					<input type="hidden" name="confirmtime" value="0"/>
					<input type="hidden" name="subtime2" value="0"/>
					<input type="hidden" name="temp" value="0"/>
				</form>
			</div>
		</div>
		<div class="five">
			<img alt="loading" src="gifs/loading.gif">
			<h1>PROCESSING</h1>
		</div>
		<div class="six">
			<div id="sixp1">
				<h2 id="msgp1">Beneficiary Deleted. Redirecting you to Manage Beneficiaries page.</h2>
				<img id="sixp1p1" alt="next" src="gifs/next.gif">
			</div>
			<div id="sixp2">
				<h2 id="msgp2">Incorrect OTP!!  Beneficiary Not Deleted.</h2>
				<img id="sixp2p1" alt="wrong" src="gifs/wrong2.png">
			</div>
			<div id="sixp3">
				<h2 id="msgp3">Beneficiary Deleted. Move to Manage Beneficiaries page.</h2>
				<img id="sixp3p1" alt="approved" src="gifs/giphy.gif">
			</div>
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>