<%@page import="com.bank.model.LoginLogDetails"%>
<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
<%@page import="java.util.ArrayList"%>
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
		<link rel="stylesheet" type="text/css" href="CSS/Login2.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/Login2.js"></script>
		<script type="text/javascript" src="JS/Login2Log.js"></script>
	</head>
	<body style="background-image: url('img/3.jpg')">
		<%@ include file="ClearCache.jsp" %>
		<%@ include file="Header.html" %>
		<%!
			GetTime obj = new GetTime();
			ShowAll show = new ShowAll();
		%>
		<%
			System.out.println("\nLogin2.jsp starts here\n");
			String now = obj.now();
		
			show.ReqParam(request, response);
			show.SessionParam(request, response);
			if(session.getAttribute("loginLog")==null){
				//System.out.print("\n\nworking\n\n");
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			LoginLogDetails loginLog=(LoginLogDetails)session.getAttribute("loginLog");
			ArrayList<String> list=(ArrayList<String>)session.getAttribute("list");
		%>
		<div class="icon-bars">
			<h5 style="float:left;margin-top:4px 10px 0 50px;font-size:15px;color:white;background: brown">Login to Safe Banking</h5>
		  	<marquee width="75%" direction="left" height="100%" style="color:white;overflow: hidden;">
				NEVER respond to any pop-up, email, SMS or phone call, no matter how appealing or official looking, seeking your personal information such as username, password(s), mobile number, ATM Card details, etc. </marquee>
		  	<h5 style="float:right;margin-left:10px;font-size:15px;color:white;margin-right:10px;margin-top: 4px;background: brown"><%=now %></h5>
		</div>
		<form method="post" action="PassAuthentication">
			<div class="two">
				<div class="twop1">
					<h2>Login</h2>
				</div>
				<div class="twop2">
					<h4>THE USERNAME PROVIDED MATCHES AN ACCOUNT HOLDER.<br/>
					PLEASE PROVIDE THE PASSWORD AND KEY TO LOGIN.</h4>
					<table>
						<tr>
						    <td width="45%">User Name: </td>
						    <td width="45%"><input type="text" size="20" name="uname" value="<%=loginLog.getUname()%>" readonly/></td>
						    <td width="10%"></td>
					    </tr>
					    <tr>
						    <td>Random Code: </td>
						    <td><input type="text" name="code" size="20" value="<%=loginLog.getCode()%>" readonly/></td>
					    </tr>
					    <tr class="afterPic">
						    <td>Enter Your Password: </td>
						    <td><input type="password" name="pass" size="20" autofocus placeholder="Enter Your Password"/></td>
						    <!-- <td><img id="passimg" src="img/wrong.png" width="25" height="25" alt="wrong" hidden="true"/></td> -->
					    </tr>
					    <tr  id="chooseRow">
						    <td>Choose Image: </td>
						    <td>Choose Image<input id="chooseradio" type="radio" name="choose"><img id="chooseimg"/></td>
					    </tr>
					    <tr id="link">
						    <td><a href="PassAuthentication?forgotPassword">Forgot Password?</a></td>
						    <td><a href="#">Forgot Picture?</a></td>
					    </tr>
					    <tr>
					    	<td><input class="grow" type="button" name="btnBACK" value="BACK" onclick="history.back()"></td>
						    <td><input type="submit" value="LOGIN" name="btnLOGIN" disabled></td>
					    </tr>
					</table>
					<input type="hidden" name="loginpage2time" value="0"/>
					<input type="hidden" name="passtime" value="0"/>
					<input type="hidden" name="pictime" value="0"/>
					<input type="hidden" name="subtime2" value="0"/>
					<input type="hidden" name="temp" value="0"/>
				</div>
			</div>
			<%-- 
			This should be used to make this application more secure because this avoids 
			displaying all the images and restricts to only 3 images at first and then 
			load more images if required by the user(indicated by the pressing of refresh button).
			
			But this makes the application slow because if the refresh button is pressed 
			multiple times then it will call the servlet and load the images again and again
			as it does not store the images. This can be made faster by loading all the 12 images
			at once in separate divs and just display:none for all other and on the press of 
			refresh button that respective div will be shown and the current one set to hidden.
			This is kind of data abstraction i.e. loading all data but showing only what is 
			essential at this point of time.
			
			
			<div id="three">
				<h2>SELECT THE PICTURE(KEY) YOU CHOSE DURING REGISTRATION:</h2>
				<h2 id="cancel">x</h2>
				<h2 id="code">The code you entered is <span><%=loginLog.getCode()%></span></h2>
				<div id="threep1">
					<div class="threep1c1">
						<input id="radioe0" type="radio" name="picEffect">
						<img id="imge0" alt="ImageEffect0">
					</div>
					<div class="threep1c1">
						<input id="radioe1" type="radio" name="picEffect">
						<img id="imge1" alt="ImageEffect1">
					</div>
					<div class="threep1c1">
						<input id="radioe2" type="radio" name="picEffect">
						<img id="imge2" alt="ImageEffect2">
					</div>
				</div>
				<div id="threep2">
		    		<img src="img/refresh.png" alt="refresh image">
		    		<div id="threep2p1">REFRESH</div>
			    </div>
			</div> --%>
			
			
			<div id="three">
				<h2>SELECT THE PICTURE(KEY) YOU CHOSE DURING REGISTRATION:</h2>
				<h2 id="cancel">x</h2>
				<h2 id="code">The code you entered is <span><%=loginLog.getCode()%></span></h2>
				<%
					for(int i=1;i<=4;i++){
				%>
				<div id="threep<%=i%>">
					<%
						for(int j=3;j>0;j--){
					%>
					<div class="threep<%=i%>c1">
						<input id="radioe<%=3*i-j%>" type="radio" name="picids" value="<%=list.get(3*i-j)%>">
						<img id="imge<%=3*i-j%>" src="LoginImages?action=GetPic&picids=<%= list.get(3*i-j) %>" alt="ImageEffect<%=3*i-j%>">
					</div>
					<%}%>
				</div>
				<%}%>
				<div id="threep5">
		    		<img src="img/refresh.png" alt="refresh image">
		    		<div id="threep5p1">REFRESH</div>
			    </div>
			</div>
		</form>
		<div class="four">
			<img alt="loading" src="gifs/processing.gif">
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>