<%@page import="com.bank.controller.ShowAll"%>
<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.AddLoggedInLogList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="https://fonts.googleapis.com/css?family=Merriweather"  rel="stylesheet">
		<title>Safe Banking Services</title>
		<link type="text/css" rel="stylesheet" href="CSS/Header.css">
		<link type="text/css" rel="stylesheet" href="CSS/Footer.css">
		<link type="text/css" rel="stylesheet" href="CSS/LoginTemplate.css">
		<link type="text/css" rel="stylesheet" href="CSS/ChangePassword.css">
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/ChangePassword.js"></script>
		<script type="text/javascript" src="JS/LoginTemplate.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
	<body  style="background-image: url('img/2.jpg');">
		<%@ include file="Header.html" %>
		<%@ include file="ClearCache.jsp" %>
		<%!
			GetTime obj = new GetTime();
			String logout_time,uname;
			ShowAll show = new ShowAll();
		%>
		<%-- <%
			System.out.println("\nFinally in ChangePassword.jsp\n");
			if(session.getAttribute("loggedInLogList")==null){
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			String uname=(String)session.getAttribute("uname");
			AddLoggedInLogList.add("ChangePassword.jsp",request,response);
			logout_time = obj.now();
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			uname=(String)session.getAttribute("uname");
		%> --%>
		<div class="two">
			<div class="twoc1">
				<a class="twoc1c1 active" href="AccountProfile.jsp"><i class="fa fa-home"></i>
					My Account &amp; Profile
				</a>
				<a class="twoc1c1" href="PaymentTransfer.jsp"><i class="fa fa-exchange"></i>
					Payment / Transfer
				</a>
				<a class="twoc1c1" href="Transaction.jsp"><i class=" fa fa-id-card-o"></i>
					Transaction
				</a>
				<a class="twoc1c1" href="#"><i class="fa fa-globe"></i>
					Useful Link
				</a>
			</div>
			<div class="twoc2">
				<div id="twoc2p1" class="twoc2c1">
					<%=logout_time%>				
				</div>
				<div id="twoc2p2" class="twoc2c1">Welcome, <%=uname%>
					<ul class="dropdown">
						<li><a href="FetchProfile"><i class="fa fa-user-circle-o"></i>Profile</a></li>
						<li><a href="Settings"><i class="fa fa-cog"></i>Settings</a></li>
						<li><a href="Logout"><i class="fa fa-sign-out"></i>Log out</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="three">
			<ul class="threec1" id="threep1">
				<li><a>Quick Links >></a></li>
				<li class="threep1c1" ><a href="AddBeneficiary.jsp">Add Beneficiary</a></li>
				<li class="threep1c1"><a href="FetchBeneficiary?type=approve">Approve Beneficiary</a></li>
				<li class="threep1c1"><a href="FetchBeneficiary?type=delete">Delete Beneficiary</a></li>
				<li class="threep1c1"><a href="FetchBeneficiary?type=transfer">Fund Transfer Existing Beneficiaries</a></li>
				<li class="threep1c1"><a href="FetchTransaction">Mini Statement</a></li>
			</ul>
		</div>
		<div class="four">
			<div class="fourc1">
				<h2 class="fourc1c1" id="fourc1p1">Change Password</h2>
			</div>
			<div class="fourc2">
				<h4 class="fourc2c1" id="fourc2p1">An email containing an OTP has been sent to your registered email id. Please enter the OTP below. This OTP is valid only for the next <span></span></h4>
			</div>
			<div class="fourc3">
				<h4 class="fourc3c1" id="fourc3p1">Password Matched. Please enter your old password</h4>
			</div>
			<div class="fourc4">
				<ul><h4>Password Matched. Set a new password.</h4>
					<li>Please avoid choosing a password that is generic in nature, guessable or inferable.</li>
					<li>Avoid password that is related to your personal data such as name, date of birth, address, telephone number and car or bike registration number.</li>
					<li>It is a good practice to memorize your password rather than write it down somewhere.</li>
					<li>For security reasons, keep changing your password at regular intervals.</li>
				</ul>
			</div>
			<div class="fourc5">
					<table id="fourc5p1">
					    <tr>
						    <td width="50%">Enter the OTP in the Email:</td>
						    <td width="40%"><input type="text" name="OTPUser" size="30" placeholder="Enter OTP in the Email" autofocus required/></td>
						    <td width="10%"><img id="OTPUserimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr id="link">
						    <td colspan="3">Didn't receive email.  <span>Resend</span></td>
					    </tr>
					    <tr>
						    <td colspan="2"><input type="button" name="btnVERIFY" value="VERIFY" disabled></td>
						</tr>
					</table>
					<table id="fourc5p2">
					    <tr>
						    <td width="50%">Enter your Old Password:</td>
						    <td width="40%"><input type="password" name="oldpass" size="30" placeholder="Enter your Old Password" autofocus required/></td>
					    </tr>
					    <tr>
						    <td colspan="2"><input type="button" id="btnCHECK" name="btnCHECK" value="CHECK" disabled></td>
						</tr>
					</table>
					<table id="fourc5p3">
						<tr>
						    <td width="30%">Enter New Password : </td>
						    <td width="40%"><input type="password" name="newpass" size="15" placeholder="Enter New Password" autofocus required/></td>
						    <td width="30%">
						    	<img id="newpassimg" src="img/wrong.png" alt="wrong" hidden="true"/>
						    	<span class="tooltiptext">
						    		<ul>It must be between 8 to 15 characters long. It should atleast contain:
						    			<li>1 lowercase and 1 uppercase character,</li>
						    			<li>1 number / digit character, and</li>
						    			<li>1 special case character out of ?!@$&amp;%_.</li>
						    		</ul>
						    	</span>
						    </td>
					    </tr>
					    <tr>
						    <td>Confirm Password : </td>
						    <td><input type="password" name="cnfnewpass" size="15" placeholder="Confirm New Password" required disabled/></td>
					    	<td><img id="cnfnewpassimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr>
						    <td colspan="2"><input type="button" name="btnCHANGE" value="CHANGE PASSWORD" disabled></td>
						</tr>
					</table>
					<input type="hidden" name="changepagetime" value=""/>
					<input type="hidden" name="otppagetime" value=""/>
					<input type="hidden" name="otptime" value=""/>
					<input type="hidden" name="verifytime" value=""/>
					<input type="hidden" name="oldpasspagetime" value=""/>
					<input type="hidden" name="oldpasstime" value=""/>
					<input type="hidden" name="checktime" value=""/>
					<input type="hidden" name="newpasspagetime" value=""/>
					<input type="hidden" name="newpasstime" value=""/>
					<input type="hidden" name="cnfnewpasstime" value=""/>
					<input type="hidden" name="changetime" value=""/>
					<input type="hidden" name="redirecttime" value=""/>
			</div>
		</div>
		<div class="five">
			<img alt="loading" src="gifs/processing.gif">
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>