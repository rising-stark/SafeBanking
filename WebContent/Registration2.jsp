<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
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
		<link rel="stylesheet" type="text/css" href="CSS/Registration2.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/Registration2.js"></script>
		<script type="text/javascript" src="JS/Registration2Log.js"></script>
		<script type="text/javascript">
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
	<body style="background-image: url('img/3.jpg')">
		<%@ include file="ClearCache.jsp" %>
		<%@ include file="Header.html" %>
		<%
			System.out.println("\nRegistration2.jsp starts here\n");
			if(session.getAttribute("bank")==null){
				System.out.print("\n\nworking\n\n");
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			session.setAttribute("ReachedReg2", "ReachedReg2");
			ShowAll show = new ShowAll();
			show.ReqParam(request, response);
			show.SessionParam(request, response);
		%>
		<div class="icon-bars">
			<h5 style="float:left;margin-top:4px 10px 0 50px;font-size:15px;color:white;background: brown">Login to Safe Banking</h5>
			<marquee width="75%" direction="left" height="100%" style="color:white;overflow: hidden;">
				NEVER respond to any pop-up, email, SMS or phone call, no matter how appealing or official looking,
				seeking your personal information such as username, password(s), mobile number, ATM Card details, etc.
			</marquee>
		</div>
		<div id="two">
			<form method="post" action="Registration2">
				<i class="fa fa-arrow-circle-left"></i>
				<div id="twop1">
					<h2>SELECT A PICTURE(KEY):</h2>
				</div>
				<div id="twop2">
					<%
						for(int i=0;i<2;i++){
					%>
						<div class="twop2c1">
							<%
								for(int j=0;j<4;j++){
							%>
							<div class="twop2c1c1">
								<input id="radiop<%=4*i+j%>" type="radio" name="picids" value="<%=4*i+j%>">
								<img id="imgp<%=4*i+j%>" src="Registration2?picid=<%=4*i+j%>" alt="Image<%=4*i+j%>">
							</div>
							<%}%>
						</div>
					<%}%>
				</div>
				<i class="fa fa-arrow-circle-right"></i>
				<div id="twop3">
					<input id="back" class="grow" type="button" name="btnBACK" value="BACK" onclick="history.back()">
					<input class="register" type="button" name="btnREGISTER" value="REGISTER" disabled>
					
					<input type="hidden" name="pictime" value="0"/>
					<input type="hidden" name="effecttime" value="0"/>
					<input type="hidden" name="subtime2" value="0"/>
					<input type="hidden" name="regpage2time" value="0"/>
					<input type="hidden" name="backtime" value="0"/>
				</div>
			</form>
		</div>
		<!-- <div id="three">
			<h1>Select language/category options</h1>
			<div class="threec1">
				<input id="radioe1" type="radio" name="piceffect" value="1">
				<img id="imge1" alt="ImageEffect1">
			</div>
		</div> -->
		<div id="four">
			<h1>Select an effect of the image selected</h1>
			<h2 id="cancel">x</h2>
			<div class="fourc1">
				<input id="radioe1" type="radio" name="piceffect" value="1">
				<img id="imge1" alt="ImageEffect1">
			</div>
			<div class="fourc1">
				<input id="radioe2" type="radio" name="piceffect" value="2">
				<img id="imge2" alt="ImageEffect2">
			</div>
			<div class="fourc1">
				<input id="radioe3" type="radio" name="piceffect" value="3">
				<img id="imge3" alt="ImageEffect3">
			</div>
			<div class="fourc1">
				<input id="radioe4" type="radio" name="piceffect" value="4">
				<img id="imge4" alt="ImageEffect4">
			</div>
			<div class="fourc1">
				<input id="radioe5" type="radio" name="piceffect" value="5">
				<img id="imge5" alt="ImageEffect5">
			</div>
			<!-- <div class="fourc1">
				<input id="radioe0" type="radio" name="piceffect" value="0">
				<img id="imge0" alt="RandomImageEffect">
				<h2>Random curve<br> each time you login</h2>
			</div> -->
	 </div>
	 <div class="five">
	 	<img alt="loading" src="gifs/loading.gif">
	 	<h1>PROCESSING</h1>
	 </div>
	<%@ include file="Footer.html" %>
	</body>
</html>