<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bank.model.BankDetails"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
<%@page import="java.awt.Graphics"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
<title>Safe Banking Registration</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style type="text/css">


.frame{
height:150px;
width:200px;
-webkit-box-shadow:5px 5px 5px #111;
box-shaddow: 5px 5px 5px #111;
float:left;
margin:7px
}
.picture:hover {
  box-shadow: 0 0 11px rgba(33,33,33,.2); 
}
.picture img{
height: 150px;
width:200px;
webkit-transition:all 1s ease;
}
.container_image{
height:400px;
width: 750px;
}
#wrapper {
	display: block;
	width: 1200px;
	height: 250px;
}

#first {
	display: block;
	float: left;
	width: 50%;
	height: 100%;
	padding-top: 20px;
		padding-left: 70px;
	
}

#second {
		padding-left: 100px;
	padding-top: 30px;

	display: block;
	float: left;
	width: 50%;
	height: 100%;
}

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
}
* {
    box-sizing: border-box;
}
.footer {
   position: fixed;
   height:50px;
   left: 0;
   bottom: 0;
   width: 100%;
   border-top:7px solid brown;
   background-color: rgba(0,0,0,0.7);
   color: white;
   text-align: center;
}
input[type=text], select, textarea {
    width: 50%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
}
input[type=password], select, textarea {
    width: 50%;
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
    padding: 12px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    float: left;
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
    float:left;
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
</style><script type="text/javascript">
window.onload=setTime2();
function goFurther(){
if (document.getElementById("chkAgree").checked == true)
document.getElementById("btnSubmit").disabled = false;
else
document.getElementById("btnSubmit").disabled = true;
setTime();
}

function getTimeStamp() {
       var now = new Date();
       return ((now.getDate() + 1) + '-' + (now.getMonth()) + '-' + now.getFullYear() + " " +(now.getHours()) + ':'+ ((now.getMinutes() < 10) ? ("0" + now.getMinutes()) : (now.getMinutes())) + ':' + ((now.getSeconds() < 10) ? ("0" + now.getSeconds()) : (now.getSeconds()))+':' + ((now.getMilliseconds() < 10) ? ("0" + now.getMilliseconds()) : (now.getMilliseconds())));
}

function setTime() {		
    document.getElementById('field').value = getTimeStamp();
}
function setTime2() {		
    document.getElementById('field1').value = getTimeStamp();
}

</script>

</head>
<body style="background-image: url('img/2.jpg')">
<img src="img/log.png">
<div style="float:right;padding-right:100px;">
<a style="color: white">What's New</a> |
<a style="color: white">About Safe Banking</a> |
<a style="color: white">24X7 Service</a> |
<a href="admin_logout" style="color: white"><img src="img/logout.png" style="height:30px;width: 30px;padding-top: 5px"></a>


</div>
<%!HttpSession session;DateTimeFormatter dtf;LocalDateTime now ; String logout_time;%>
<% dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
	     now = LocalDateTime.now();  
	    System.out.print(dtf.format(now));
	     logout_time=dtf.format(now);%>

<div class="icon-bar">
  <a  href="registration1.jsp"><i class="fa fa-home"></i><h4>Net Banking Registration</h4></a> 
  <a  href="transfer_money"><i class="fa fa-exchange"></i><h4>Update Passbook</h4></a> 
  <a href="unblock_customer_list"><i class=" fa fa-id-card-o"></i><h4>Unblock Customer</h4></a> 
  <a  class="active"  href="forgot_picture"><i class="fa fa-globe"></i><h4>Forgot Picture</h4></a>
</div>
<div class="icon-bars">

  <h5 style="float:left;margin-left:50px;font-size:15px;color:white;margin-right:10px;margin-top: 4px;background: brown">Login to Safe Banking</h5>
  <marquee width="75%" direction="left" height="100%" style="color:white;overflow: hidden;">
NEVER respond to any popup,email, SMS or phone call, no matter how appealing or official looking, seeking your personal information such as username, password(s), mobile number, ATM Card details, etc. </marquee>
  <h5 href="logout" style="float:right;margin-left:10px;font-size:15px;color:white;margin-right:10px;margin-top: 4px;background: brown"><%=logout_time %></h5>

</div>
<div id="wrapper">
<div id="first">
  <form method="post" action="forgot_picture2">
  <div style="color: blue;padding-left: 500px;background-color: rgba(0,0,255,0.1);margin-bottom: 20px">
  
<%
    			
        if(request.getAttribute("errormsg")!=null)
        	out.print(request.getAttribute("errormsg"));%>
        	</div>

 <h4>CHANGE PICTURE:
 </h4><br>
   <h4 style="float: left;">Account Number:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4> <input type="text" id="acc_no" name="acc_no" placeholder="<%=(String)request.getParameter("acc_no")%>" readonly/>  <br>  <br>       
<%session.setAttribute("acc_no",request.getParameter("acc_no"));%>
  
	
		   <br><input type="submit" value="Change Picture" class="button button4">
		   
  </div>
<div id="second">
 <h4>SELECT THE PICTURE CHOSE DURING REGISTRATION:</h4><br>

 <div class="container_image">
<%! ArrayList<Integer> hlist=null; %>  
    
  <%
  hlist=(ArrayList<Integer>)request.getAttribute("listdetails");%>   
<%!  Random r;int rand_int1,rand_int2,rand_int3,rand_int4,rand_int5,rand_int6;int i;String rand1,rand2,rand3,rand4,rand5,rand6;%>
<%	rand_int1=hlist.get(0);
rand1=Integer.toString(rand_int1);
System.out.println("Here"+rand1);

rand_int2=hlist.get(1);
rand2=Integer.toString(rand_int2);
System.out.println("Here"+rand2);


rand_int3=hlist.get(2);
rand3=Integer.toString(rand_int3);
System.out.println("Here"+rand3);


rand_int4=hlist.get(3);
rand4=Integer.toString(rand_int4);
System.out.println("Here"+rand4);


rand_int5=hlist.get(4);
rand5=Integer.toString(rand_int5);
System.out.println("Here"+rand5);


rand_int6=hlist.get(5);
rand6=Integer.toString(rand_int6);
System.out.println("Here"+rand6);

%>
    <div class="picture frame"><input type="radio" name="site_key" value="<%=rand1 %>" style="position: absolute"><img src="ImageRetrieve?pic_id=<%=rand1 %>" alt="Avatar man"></div>
  
       <div class="picture frame"><input type="radio" name="site_key" value="<%=rand2 %>" style="position: absolute"><img src="ImageRetrieve?pic_id=<%=rand2 %>" alt="Avatar man"></a></div>
       

       <div class="picture frame"><input type="radio" name="site_key" value="<%=rand3 %>" style="position: absolute"><img src="ImageRetrieve?pic_id=<%=rand3 %>" alt="Avatar man"></a></div>

       <div class="picture frame"><input type="radio" name="site_key" value="<%=rand4 %>" style="position: absolute"><img src="ImageRetrieve?pic_id=<%=rand4 %>" alt="Avatar man"></a></div>
              
       <div class="picture frame"><input type="radio" name="site_key" value="<%=rand5 %>" style="position: absolute"><img src="ImageRetrieve?pic_id=<%=rand5 %>" alt="Avatar man"></a></div>

       <div class="picture frame"><input type="radio" name="site_key" value="<%=rand6 %>" style="position: absolute"><img src="ImageRetrieve?pic_id=<%=rand6 %>" alt="Avatar man"></a></div>
   
    
    
    </div>
    <a href="generateSitekeyLogin2?"><img src="img/refresh.png" alt="refresh image" style="height:50px;width: 50px"></a></div>
		   </form>

</div>
<div class="footer">
  <p style="float:left;margin-left:10px">© Safe Banking</p>
  <p style="float:right;margin-right: 10px">Site best viewed at 1024 x 768 resolution in I.E 10 +, Mozilla 35 +, Google Chrome 35 +</p>
</div>
 </body>
</html>