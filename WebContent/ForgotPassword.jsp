<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
<%@page import="com.bank.dao.bankDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Safe Banking Forgot Password</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="CSS/Header.css">
		<link rel="stylesheet" type="text/css" href="CSS/Footer.css">
		<link rel="stylesheet" type="text/css" href="CSS/Tooltip.css">
		<link type="text/css" rel="stylesheet" href="CSS/ForgotPassword.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/Tooltip.js"></script>
		<script type="text/javascript" src="JS/ForgotPassword.js"></script>
		<script type="text/javascript" src="JS/ForgotPasswordLog.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				console.log("reached in ForgotPassword.jsp. Now going to call emailForgotInitiated function from script tag");
				emailForgotInitiated();
			});
		</script>
	</head>
	<body style="background-image: url('img/2.jpg');">
		<%@ include file="ClearCache.jsp" %>
		<%@ include file="Header.html" %>
		<%!
			ShowAll show = new ShowAll();
			bankDAO u = new bankDAO(); 
			String uname = "null";
			String email="null";
			String emailx = "null";
		%>
		<%-- <%
			System.out.println("\nFinally in ForgotPassword.jsp\n");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			if(session.getAttribute("emailForgotCompleted")!=null){
			%>
				<script type="text/javascript">
					$(document).ready(function(){
						console.log("reached in ForgotPassword.jsp. Now going to call emailForgotCompleted function from script tag");
						emailForgotCompleted();
					});
				</script>
			<%
			}
			else if(session.getAttribute("forgotPassword")==null){
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			uname = (String)session.getAttribute("uname");
			email = (String)session.getAttribute("ex");
			emailx = u.encodeEmail(email);
		%> --%>
		<div class="two">
			<div class="twoc1">
				<h2 class="twoc1c1" id="twoc1p1">Forgot Password</h2>
				<br/>
				<h4 class="twoc1c1" id="twoc1p2">An Email has been sent to the email-id&nbsp;&nbsp;&nbsp;<span id="email"><%=emailx %></span>. The email contains a temporary password.</h4>
			</div>
			<div class="twoc2">
				<h3 class="twoc1c1" id="twoc2p1">Password Matched</h3>
				<h4 class="twoc1c1" id="twoc2p2">Your password will be changed only after you set a new password and choose a security QnA.</h4>
				<h4 class="twoc1c1" id="twoc2p3">Please avoid choosing a password that is generic in nature or easy to guess.</h4>
				<h4 class="twoc1c1" id="twoc2p4">Avoid password that is related to your personal data such as name, date of birth, address, telephone number and car or bike registration number.</h4>
				<h4 class="twoc1c1" id="twoc2p5">It is a good practice to memorize your password rather than to write it down somewhere.</h4>
			</div>
			<div class="twoc3">
				<form action="ForgotPassword" method="post">
					<table id="twoc3p1">
					    <tr>
						    <td width="45%">Your User Name:</td>
						    <td width="50%"><input type="text" name="uname" size="40" value="<%=uname%>" readonly/></td>
						</tr>
						<tr class="afterEmail">
						    <td>Email-id: </td>
						    <td><input type="text" name="email" size="40" value="<%= email %>" readonly/></td>
					    </tr>
					</table>
					<!-- <table id="twoc3p2">
					    <tr>
						    <td width="50%">Enter Registered Email-id:</td>
						    <td width="45%"><input type="email" name="email" size="30" placeholder="Enter Your Registered Email-id" autofocus required/></td>
						    <td width="5%"><img id="emailimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr>
						    <td colspan="2"><input type="button" name="btnSEND" value="SEND" disabled></td>
						</tr>
					</table> -->
					<table id="twoc3p2">
					    <tr>
						    <td width="40%">Enter the password in the email:*</td>
						    <td width="40%"><input type="password" name="oldpass" size="15" placeholder="Enter Password in the Email" autofocus required/></td>
						    <td width="10%"><img id="oldpassimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr>
						    <td colspan="2"><input class="verify" type="button" name="btnVERIFY" value="VERIFY" disabled></td>
						</tr>
					</table>
					<table id="twoc3p3">
						<tr>
						    <td width="30%">Enter New Password*: </td>
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
						    <td width="1%"><span class="wrong"></span><span class="tick">Accepted</span></td>
					    </tr>
					    <tr>
						    <td>Confirm Password*: </td>
						    <td><input type="password" name="cnfnewpass" size="15" placeholder="Confirm New Password" required disabled/></td>
					    	<td><img id="cnfnewpassimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    	<td><span class="wrong"></span><span class="tick">Accepted</span></td>
					    </tr>
					    <tr>
						    <td>Security Question*: </td>
						    <td>
						    	<select id="securityquestion" name="securityquestion" required>
						    		<option value='null'>---- Choose Your Security Question ----</option>
						    		<option value="Actor">Who is Your Favourite Actor?</option>
								  	<option value="School">Which is Your Favourite School?</option>
								  	<option value="City">Which is Your Favourite City?</option>
								  	<option value="Sport">Which is Your Favourite Sport?</option>
								  	<option value="Teacher">Who is Your Favourite Teacher?</option>
								</select>
							</td>
							<td><img id="securityquestionimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
							<td><span class="wrong"></span><span class="tick">Accepted</span></td>
					    </tr>
					    <tr>
						    <td>Security Answer*: </td>
						    <td><input id="securityanswer" type="text" name="securityanswer" size="40" placeholder="Write Your Answer in 3 to 40 characters" required/></td>
							<td><img id="securityanswerimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
							<td><span class="wrong"></span><span class="tick">Accepted</span></td>
					    </tr>
					    <tr>
						    <td colspan="2"><input class="change" type="button" name="btnCHANGE" value="CHANGE PASSWORD" disabled></td>
						</tr>
					</table>
					<input type="hidden" name="forgotpagetime" value="0"/>
					<input type="hidden" name="oldpasstime" value="0"/>
					<input type="hidden" name="verifytime" value="0"/>
					<input type="hidden" name="proceedtime" value="0"/>
					<input type="hidden" name="newpasstime" value="0"/>
					<input type="hidden" name="cnfpasstime" value="0"/>
					<input type="hidden" name="securityquestime" value="0"/>
					<input type="hidden" name="securityanstime" value="0"/>
					<input type="hidden" name="subtime" value="0"/>
					<input type="hidden" name="temp" value="0"/>
					<input type="hidden" name="temp1" value="0"/>
				</form>
				<h3 id="note">Note: The fields marked with * are compulsory</h3>
			</div>
		</div>
		<div class="three">
			<img alt="loading" src="gifs/loading.gif">
			<h1>PROCESSING</h1>
		</div>
		<div class="four">
			<div id="fourp1">
				<h2>Password Matched.<br> Please change your password in the next section.</h2>
				<img id="fourp1p1" alt="next" src="gifs/next.gif">
			</div>
			<div id="fourp2">
				<img id="fourp2p1" alt="incorrect" src="gifs/wrong.gif">
				<h2>Incorrect Password. Please Try again.</h2>
			</div>
			<div id="fourp3">
				<img id="fourp3p1" alt="complete" src="gifs/source1.gif">
				<h2>Your Email is verified and the password is changed successfully.<br> <br>Redirecting to Login Page in 5s.</h2>
			</div>
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>