<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
<%@page import="com.bank.model.BankDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Safe Banking Registration</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Merriweather">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="CSS/Header.css">
		<link rel="stylesheet" type="text/css" href="CSS/Footer.css">
		<link rel="stylesheet" type="text/css" href="CSS/Tooltip.css">
		<link rel="stylesheet" type="text/css" href="CSS/Registration1.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/Tooltip.js"></script>
		<script type="text/javascript" src="JS/Registration1.js"></script>
		<script type="text/javascript" src="JS/Registration1Log.js"></script>
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
			
			var already="<%=(String)session.getAttribute("already")%>";
			if(already!="null"){
				$(document).ready(function() {
					$("#msgp2").text(found);
					$(".six").css("display", "block");
					$("#sixp1").css("display", "block");
					$("#sixp1p1").css("display", "none");
					setTimeout(function(){
						$(".six").css("display", "none");
						window.location.href="Login.jsp";
					}, 5000);
				});
				<%
					session.removeAttribute("already");
					System.out.println("already attr "+(String)session.getAttribute("already"));
				%>
			}
			
			var check="<%=(String)session.getAttribute("ReachedReg2")%>";
			if(check!="null"){
				$(document).ready(function() {
				});
				<%
					session.removeAttribute("ReachedReg2");
					System.out.println("checking in Registration1.jsp check attr "+(String)session.getAttribute("ReachedReg2"));
				%>
			}
		
			//document.addEventListener('contextmenu', event => event.preventDefault());
			/*
			This code will block the right click of mouse, Ctrl + Shift + I, Ctrl+ Shift + J, Ctrl + S, Ctrl + U, and F12 key.
			The F12 key uses for looking the source code of page, so it also need be disabled.
			https://mycyberuniverse.com/developing/disabling-right-clicking-by-using-javascript.html
			*/
			window.onload = function() {
			    document.addEventListener("contextmenu", function(e){
			      e.preventDefault();
			    }, false);
			    document.addEventListener("keydown", function(e) {
			    //document.onkeydown = function(e) {
			      // "I" key
			      if (e.ctrlKey && e.shiftKey && e.keyCode == 73) {
			        disabledEvent(e);
			      }
			      // "J" key
			      if (e.ctrlKey && e.shiftKey && e.keyCode == 74) {
			        disabledEvent(e);
			      }
			      // "S" key + macOS
			      if (e.keyCode == 83 && (navigator.platform.match("Mac") ? e.metaKey : e.ctrlKey)) {
			        disabledEvent(e);
			      }
			      // "U" key
			      if (e.ctrlKey && e.keyCode == 85) {
			        disabledEvent(e);
			      }
			      // "F12" key
			      if (event.keyCode == 123) {
			        disabledEvent(e);
			      }
			    }, false);
			    function disabledEvent(e){
			      if (e.stopPropagation){
			        e.stopPropagation();
			      } else if (window.event){
			        window.event.cancelBubble = true;
			      }
			      e.preventDefault();
			      return false;
			    }
			  };
		</script>
	</head>
	<body oncontextmenu="return false;" style="background-image: url('img/2.jpg');">
		<%@ include file="ClearCache.jsp" %>
		<%@ include file="Header.html" %>
		<%
			System.out.println("\nFinally in Registration1.jsp\n");
				
			ShowAll show = new ShowAll();
			GetTime obj = new GetTime();
			
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			
			/* if(session.getAttribute("REGISTER")==null){
				response.sendRedirect("Welcome.jsp");
				return;
			} */
		%>
		<div class="two">
			<div class="twoc1">
				<h1><span>Registration of a new user</span></h1>
			</div>
			<div class="twoc2">
				<h2 class="heading">Track Progress</h2>
				<div class="twoc2c1">
					<img id="twoc2c1p1" src="img/page1.png" alt="page1"/>
					<span class="twoc2c1c1">Enter Details</span>
					<img class="done" id="twoc2c1p2" src="gifs/giphy.gif" alt="done" hidden="true"/>
				</div>
				<div class="twoc2c1">
					<img id="twoc2c1p3" src="img/page2.png" alt="page3"/>
					<span class="twoc2c1c1">Choose Image</span>
					<img class="done" id="twoc2c1p4" src="gifs/giphy.gif" alt="done" hidden="true"/>
				</div>
				<div class="twoc2c1">
					<img id="twoc2c1p5" src="img/page3.png" alt="page3"/>
					<span class="twoc2c1c1">Verify Email</span>
					<img class="done" id="twoc2c1p6" src="gifs/giphy.gif" alt="done" hidden="true"/>
				</div>
			</div>
			<div class="twoc3">
				<h2 class="heading">Please enter your details here</h2>
				<form action="Registration1" method="post">
					<table>
						<tr>
						    <td width="30%">Enter Your First Name* :</td>
						    <td width="60%"><input id="fname" type="text" name="fname" size="40" placeholder="Enter First Name" required/></td>
						    <td width="5%"><img id="fnameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
						    <td width="1%"><span class="wrong"></span><span class="tick">Accepted</span></td>
					    </tr>
					    <tr>
						    <td>Enter Your Last Name* : </td>
						    <td><input id="lname" type="text" name="lname" size="40" placeholder="Enter Last Name" required/></td>
						    <td><img id="lnameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
						    <td><span class="wrong"></span><span class="tick">Accepted</span></td>
					    </tr>
					    <tr>
						    <td>Choose a user name* : </td>
						    <td><input id="uname" type="text" name="uname" size="40" placeholder="Enter A User Name that has not been taken" required/></td>
					    	<td><img id="unameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    	<td><span class="wrong"></span><span class="tick">Accepted</span></td>
					    </tr>
					    <tr>
						    <td>Enter Your Bank Account No.* : </td>
						    <td><input id="acc" type="text" name="acc" size="40" placeholder="Enter Your Account Number" required/></td>
						    <td><img id="accimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
						    <td><span class="wrong"></span><span class="tick">Accepted</span></td>
					    </tr>
					    <tr>
						    <td>Enter Your Email-id* : </td>
						    <td><input id="email" type="text" name="email" size="40" placeholder="Enter Your Email-id" required/></td>
						    <td><img id="emailimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
						    <td><span class="wrong"></span><span class="tick">Accepted</span></td>
					    </tr>
					    <tr>
						    <td>Enter Your Phone No.* : </td>
						    <td><input id="phone" type="text" name="phone" size="40" placeholder="Enter Your Phone Number" required/></td>
						    <td><img id="phoneimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
						    <td><span class="wrong"></span><span class="tick">Accepted</span></td>
					    </tr>
					    <tr>
						    <td>Enter Your Address : </td>
						    <td><textarea id="address" name="address" rows="3" cols="93" placeholder="Enter Your Address"></textarea></td>
						    <td><img id="addressimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr id="termsrow">
					    	<td id="terms" colspan="2">
					    		<input id="chkbox" type="checkbox" name="chkbox" value="accept"/>&nbsp;&nbsp;I ACCEPT THE <a href="Terms.html" target="_blank">TERMS AND LICENSE AGREEMENT</a>
					    	</td>
					    </tr>
					    <tr>
					    	<td><input class="grow reset" type="button" name="btnRESET" value="RESET"></td>
						    <td colspan="2"><input class="next" type="button" name="btnNEXT" value="NEXT" disabled></td>
					    </tr>
					</table>
					
					<input type="hidden" name="regpage1time" value="0"/>
					<input type="hidden" name="ftime" value="0"/>
					<input type="hidden" name="ltime" value="0"/>
					<input type="hidden" name="utime" value="0"/>
					<input type="hidden" name="acctime" value="0"/>
					<input type="hidden" name="phonetime" value="0"/>
					<input type="hidden" name="addresstime" value="0"/>
					<input type="hidden" name="emailtime" value="0"/>
					<input type="hidden" name="chktime" value="0"/>
					<input type="hidden" name="subtime1" value="0"/>
					<input type="hidden" name="resettime" value="0"/>
					<input type="hidden" name="temp" value="0"/>
				</form>
				<h2 id="note">Note: The fields marked with * are compulsory</h2>
			</div>
		</div>
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
		<%-- <%@ include file="Footer.html" %> --%>
	</body>
</html>