<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Safe Banking Login</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="CSS/Header.css">
		<link rel="stylesheet" type="text/css" href="CSS/Footer.css">
		<link rel="stylesheet" type="text/css" href="CSS/Login.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/Login.js"></script>
		<script type="text/javascript" src="JS/Login1Log.js"></script>
		<script type="text/javascript">
		
			var found="<%=(String)session.getAttribute("notFound")%>";
			if(found!="null"){
				$(document).ready(function() {
					$("#msgp2").text(found);
					$(".four").css("display", "block");
					$("#fourp2").css("display", "block");
				});
				<%session.removeAttribute("notFound");%>
			}
			
			var already="<%=(String)session.getAttribute("already")%>";
			if(already!="null"){
				$(document).ready(function() {
					$("#msgp2").text(found);
					$(".four").css("display", "block");
					$("#fourp1").css("display", "block");
					setTimeout(function(){
						$(".four").css("display", "none");
						window.location.href="Login.jsp";
					}, 5000);
				});
				<%session.removeAttribute("already");%>
			}
			
			var ucode="<%=(String)session.getAttribute("securityWizard")%>";
			if(ucode!="null"){
				$(document).ready(function() {
					console.log("setting ");
					setSecuritypagetime();
					$('.three').slideUp(500);
					$(".four").delay(750).css("display", "block");
					$("#fourp3").delay(750).css("display", "block");
				});
				<% session.removeAttribute("securityWizard");%>
			}
		</script>
	</head>
	<body style="background-image: url('img/3.jpg');">
		<%@ include file="Header.html" %>
		<%@ include file="ClearCache.jsp" %>
		<%!
			GetTime obj = new GetTime();
			ShowAll show = new ShowAll();
		%>
		<%
			System.out.println("\nFinally in Login.jsp\n");
			
			if(session.getAttribute("LOGIN")==null){
				System.out.println("\nRedirecting because of null LOGIN attribute in Login.jsp\n");
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			System.out.println("\nGoing to show the attr in Login.jsp\n");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
		%>
		<div class="icon-bars">
			<h5	style="float: left; margin-left: 50px; font-size: 15px; color: white; margin-right: 10px; margin-top: 4px; background: brown">
				Login to Safe Banking</h5>
			<marquee width="75%" direction="left" height="100%" style="color: white; overflow: hidden;"> 
				NEVER respond to any popup,email, SMS or phone call, no matter how appealing or
				official looking, seeking your personal information such as username,
				password(s), mobile number, ATM Card details, etc. </marquee>
		</div>
		<div class="two">
			<div class="twop1">
				<h2>Login</h2>
			</div>
			<div class="twop2">
				<h4>Care: Username and Password are case sensitive</h4>
				<form action="Login" method="post">
					<table>
						<tr>
						    <td width="45%">Enter Your User Name* : </td>
						    <td width="45%"><input id="uname" type="text" name="uname" size="20" autofocus placeholder="Enter Your User Name" required/></td>
					    	<!-- <td width="10%"><img id="unameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td> -->
					    </tr>
					    <tr>
						    <td>Enter a 6-Digit Random Code* : </td>
						    <td><input type="text" name="code" size="20" placeholder="Enter a 6-Digit Random Code"  required/></td>
						    <!-- <td><img id="codeimg" src="img/wrong.png" alt="wrong" hidden="true"/></td> -->
					    </tr>
					    <tr id="link">
					    	<td colspan="2">New User?<a href="Registration1.jsp">Register</a> Here</td>
					    </tr>
					    <tr><!-- Here there is no need of reset button on login.jsp page because this is not of that much use   -->
					    	<!-- <td><input class="grow" type="button" name="btnRESET" value="RESET"/></td> -->
						    <td colspan="2"><input type="button" name="btnNEXT" value="NEXT" disabled /></td>
					    </tr>
					</table>
					<input type="hidden" name="loginpagetime" value="0"/>
					<input type="hidden" name="utime" value="0"/>
					<input type="hidden" name="rctime" value="0"/>
					<input type="hidden" name="subtime1" value="0"/>
					<input type="hidden" name="securitypagetime" value="0"/>
					<input type="hidden" name="securityquestime" value="0"/>
					<input type="hidden" name="securityanstime" value="0"/>
					<input type="hidden" name="verifytime" value="0"/>
					<input type="hidden" name="temp" value="0"/>
				</form>
				<h3>Note: The fields marked with * are compulsory</h3>
			</div>
		</div>
		<div class="three">
			<img alt="loading" src="gifs/processing.gif">
		</div>
		<div class="four">
			<div id="fourp1">
				<h2 id="msgp1">Redirect to Email Verify Page</h2>
				<img id="fourp1p1" alt="next" src="gifs/next.gif">
			</div>
			<div id="fourp2">
				<h2 id="msgp2">Incorrect Answer</h2>
				<img id="fourp2p1" alt="wrong" src="gifs/wrong2.png">
				<img id="fourp2p2" alt="wrong" src="gifs/wrong2.png">
			</div>
			<div id="fourp3">
				<div id="fourp3p1">
					<h2 class="msgp3">There have been multiple unsuccessful login attempts through your account. <br>
					To login into your account, you would have to choose your security question and answer. </h2>
				</div>
				<table>
					<tr>
					    <td width="40%">Security Question: </td>
					    <td width="60%">
					    	<select id="securityquestion" name="securityquestion" required>
					    		<option value='null'>---- Choose Your Security Question ----</option>
					    		<option value="Actor">Who is Your Favourite Actor?</option>
					    		<option value="School">Which is Your Favourite School?</option>
							  	<option value="City">Which is Your Favourite City?</option>
							  	<option value="Sport">Which is Your Favourite Sport?</option>
							  	<option value="Teacher">Who is Your Favourite Teacher?</option>
							</select>
						</td>
						<!-- <td><img id="securityquestionimg" src="img/wrong.png" alt="wrong" hidden="true"/></td> -->
				    </tr>
				    <tr>
					    <td width="40%">Security Answer: </td>
					    <td width="60%"><input id="securityanswer" type="text" name="securityanswer" size="40" placeholder="Write Your Answer between 3 to 40 characters" required/></td>
						<!-- <td><img id="securityanswerimg" src="img/wrong.png" alt="wrong" hidden="true"/></td> -->
				    </tr>
				    <tr>
					    <td colspan="2"><input class="verify" type="button" name="btnVERIFY" value="SUBMIT" disabled></td>
					</tr>
				</table>
			</div>
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>