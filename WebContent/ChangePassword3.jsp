<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>Safe Banking Registration</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style type="text/css">

.centered_text {
    position: absolute;
    top: 40%;
    left: 50%;
    transform: translate(-50%, -50%);
}
.left {
    left: 0;
}

.right {
    right: 0;
}
.centered {
    position: absolute;
    top: 10%;
    left: 50%;
    text-align: center;
}

h1{
font-family: 'Merriweather', serif;
font-size: 72px;
color:white;
}
h4{
font-family: 'Merriweather', serif;
font-size: 15px;
color:white;
}

ul {
    list-style-type: none;
     height:100%;
      position: static;
      left: 0;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: black;
}


li a {
    display: block;
    color: white;
    padding: 10px;
    text-decoration: none;
}

li:hover {
    background: brown;
}

li .active{
    background: brown;

}

*{
margin:0px;
padding:0px;
}

.container {
    position: relative;
    text-align: center;
    color: white;
}


#sidebar{
position:absolute;
width:230px;
height:100%;
background:black;
left:0px;
}

#sidebar ul li{
color:rgba(230,230,230,0.9);
list-style:none;
padding: 5px 30px;
border-bottom: 1px solid rgba(100,100,100);
}


#sidebar .toggle-btn span{
display:block;
width:30px;
height:5px;
background:black;
margin: 5px 0px;
}

#sidebar.active{
left:-200px;}
.container_form {
    border-radius: 5px;
    background-color: rgba(0,0,0,0.5);
    margin-left: 270px;
    margin-right: 100px;
    margin-top: 10px;
    padding-bottom:50px;
}
.container_header {
    border-radius: 5px;
    background-color: rgba(0,0,0,1);
    padding-top: 0px;
    margin-top:0px;
}
.col-75 {
    float: left;
    width: 75%;
    margin-top: 6px;
    padding-left: 20px;
}

/* Clear floats after the columns */
.row:after {
    content: "";
    display: table;
    clear: both;
}

.col-25 {
    float: left;
    width: 25%;
    margin-top: 6px;
        padding-left: 20px;
    
}
* {
    box-sizing: border-box;
}

input[type=text], select, textarea {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
}
input[type=number], select, textarea {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
}

label {
    padding: 12px 12px 12px 0;
    display: inline-block;
}

input[type=submit] {
    background-color: brown;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: right;
}

input[type=submit]:hover {
    background-color: #45a049;
}
.btn{
 background-color: brown;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: right;
}
.btn a{
color:white;
text-decoration: none;
}
#header_navigation {
    float: right;
    margin: 0 10px 0 0;
    overflow: hidden;
}
.icon-bar {
    width: 100%;
    background-color: #555;
    overflow: auto;
}
.icon-bars {
    width: 100%;
    background-color: rgba(0,0,0,0.7);
    overflow: auto;
    height: 40px;
    padding-top: 5px;
}
.icon-bar a {
    float: left;
    width: 25%;
    text-align: center;
    padding: 12px 0;
    transition: all 0.3s ease;
    color: white;
    font-size: 36px;
}

.icon-bar a:hover {
    background-color: #000;
}

.active {
    background-color: brown !important;
}
#wrapper {
      display: block;

    width: 1000px;
    height:250px;
}

#first {
      display: block;

    float:left;
    width: 50%;
    height: 100%;
        padding-left: 100px;
        padding-top: 20px;    
}
#first a{
       text-decoration: none;
}
#second a{
text-decoration: none;
}
#second {
      display: block;
        padding-top: 20px;    

    float:left;
    width: 50%;
    height: 100%;
    padding-left: 200px
}
.footer {
   position:fixed;
   height:50px;
   left: 0;
   bottom: 0;
   width: 100%;
   border-top:7px solid brown;
   background-color: rgba(0,0,0,0.7);
   color: white;
   text-align: center;
}
table {
font-family: 'Merriweather', serif;
    border-collapse: collapse;
    width: 100%;
}
th {
	background-color: black;
}
td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
    color:white;
}
tr:nth-child(even) {
    background-color: rgba(0,0,0,0.3);
}


input[type=text], select, textarea {
    width: 75%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
}
input[type=number], select, textarea {
    width: 75%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
}

input[type=submit] {
    background-color: brown;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: right;
}

input[type=submit]:hover {
    background-color: #45a049;
}



</style>
<script type="text/javascript">
function goFurther(){
if (document.getElementById("chkAgree").checked == true)
document.getElementById("btnSubmit").disabled = false;
else
document.getElementById("btnSubmit").disabled = true;
}
</script>
<script type="text/javascript">
function toggleSidebar(){
	document.getElementById("sidebar").classList.toggle('active');
}
</script>

</head>
<body style="background-image: url('img/2.jpg');">
<div>
<img src="img/log.png">
<div style="float:right;padding-right:100px;">
<a style="color: white">What's New</a> |
<a style="color: white">About Safe Banking</a> |
<a style="color: white">24X7 Service</a> |
<a href="logout" style="color: white"><img src="img/logout.png" style="height:30px;width: 30px;padding-top: 5px"></a>

</div>
</div>
<div class="icon-bar">
  <a  href="account_profile.jsp"><i class="fa fa-home"></i><h4>My Account &amp; Profile</h4></a> 
  <a  class="active" href="payment_transfer.jsp"><i class="fa fa-exchange"></i><h4>Payment / Transfer</h4></a> 
  <a href="transaction.jsp"><i class=" fa fa-id-card-o"></i><h4>Transaction</h4></a> 
  <a href="#"><i class="fa fa-globe"></i><h4>Useful Link</h4></a>
</div>
<%!HttpSession session;String name;DateTimeFormatter dtf;LocalDateTime now ; String logout_time;%>
<%name=(String)session.getAttribute("name");
 dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
	     now = LocalDateTime.now();  
	    System.out.print(dtf.format(now));
	     logout_time=dtf.format(now);%>
<div class="icon-bars">
  <h5 href="logout" style="float:left;padding-left:50px;font-size:15px;color:white;"><%=logout_time %></h5> 
  <h4 style="float:right;padding-right:50px">Welcome, <%=name %></h4> 
  
</div>

<div id="sidebar">
<div class="toggle-btn" onclick="toggleSidebar()">
</div>
<ul>
  <li style="background-color: brown;"><a>Quick Links >></a></li>

  <li><a href="add_beneficiary.jsp">Add Beneficiary</a></li>
    <li><a href="fetch_beneficiary3">Approve Beneficiary</a></li>
        <li><a href="fetch_beneficiary2">Delete Beneficiary</a></li>
  <li><a href="fetch_beneficiary">Fund Transfer</a></li>
  <li><a href="fetch_statement">Mini Statement</a></li>

</ul>
</div>
<div class="container_form">
<div style="background-color: black;padding-top: 10px;padding-bottom: 10px">
<h4 style="font-size: 20px;padding-left: 20px;">New Password</h4>
</div> <div style="color: red;background-color: rgba(0,0,255,0.1);margin-bottom: 20px;padding-left: 60px;margin-right: 30px"><b>
    				<%
        if(request.getAttribute("servermsg")!=null)
        	out.print(request.getAttribute("servermsg"));%>
        	</div>
  <form action="newPass" method="post">
    <div class="row">
      <div class="col-25">
        <label for="fname"><h4>New Password:</h4></label>
      </div>
      <div class="col-75">
        <input type="password" id="fname" name="newpass" placeholder="Enter the new password">
      </div>
    </div>
     <div class="row">
      <div class="col-25">
        <label for="fname"><h4>Verify Password:</h4></label>
      </div>
      <div class="col-75">
        <input type="password" id="fname" name="" placeholder="Enter the confirm password">
      </div>
    </div>
        
      <input type="submit" id="btnSubmit" value="Submit">
    </div>
  </form>
</div>
<div class="footer">
  <p style="float:left;margin-left:10px">© Safe Banking</p>
  <p style="float:right;margin-right: 10px">Site best viewed at 1024 x 768 resolution in I.E 10 +, Mozilla 35 +, Google Chrome 35 +</p>
</div>
</body>
</html>
