<%@page import="com.bank.controller.ShowAll"%>
<%@page import="com.bank.controller.GetTime"%>
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
		<link rel="stylesheet" type="text/css" href="CSS/AddBeneficiary.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/AddBeneficiary.js"></script>
		<script type="text/javascript" src="JS/AddBeneficiaryLog.js"></script>
		<script type="text/javascript" src="JS/LoginTemplate.js"></script>
		<script type="text/javascript">
			var found="<%=(String)session.getAttribute("notFound")%>";
			if(found!="null"){
				$(document).ready(function() {
					$("#msgp2").text(found);
					$(".six").css("display", "block");
					$('.five').slideUp(500);
					$('.six').slideUp(0);
					$('.six').slideDown(500);
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
					$("#msgp1").text(already);
					$(".six").css("display", "block");
					$('.five').slideUp(500);
					$('.six').slideUp(0);
					$('.six').slideDown(500);
					$("#sixp1").css("display", "block");
					$("#sixp1p1").css("display", "none");
					setTimeout(function(){
						$(".six").css("display", "none");
						window.location.href="FetchBeneficiary?type=approve";
					}, 5000);
				});
				<%
					session.removeAttribute("already");
					System.out.println("already attr "+(String)session.getAttribute("already"));
				%>
			}
		
		</script>
	</head>
	<body  style="background-image: url('img/2.jpg');">
		<%@ include file="Header.html" %>
		<%@ include file="ClearCache.jsp" %>
		<%!
			GetTime obj = new GetTime();
			String logout_time,uname;
			ShowAll show = new ShowAll();
		%>
		<%
			System.out.println("\nFinally in AddBeneficiary.jsp\n");
			if(session.getAttribute("loggedInLogList")==null){
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}			
			uname=(String)session.getAttribute("uname");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
		%>
		<div class="two">
			<div class="twoc1">
				<a class="twoc1c1" href="AddBeneficiary?action=AccountProfile"><i class="fa fa-home"></i>
					My Account &amp; Profile
				</a>
				<a class="twoc1c1" href="AddBeneficiary?action=PaymentTransfer"><i class="fa fa-usd"></i><i class="fa fa-usd"></i>
					Payments / Transfers
				</a>
				<a class="twoc1c1 active" href="AddBeneficiary?action=ManageBeneficiaries"><i class="fa fa-list-ul"></i>
					Manage Beneficiaries
				</a>
				<a class="twoc1c1" href="AddBeneficiary?action=Transaction"><i class=" fa fa-exchange"></i>
					Transactions
				</a>
			</div>
			<div class="twoc2">
				<%-- <div id="twoc2p1" class="twoc2c1">
					<%=logout_time%>				
				</div> --%>
				<div id="twoc2p2" class="twoc2c1"><i class=" fa fa-university"></i>  Welcome, <%=uname%>
					<ul class="dropdown">
						<li><a href="AddBeneficiary?action=Profile"><i class="fa fa-user-circle-o"></i>Profile</a></li>
						<li><a href="AddBeneficiary?action=Settings"><i class="fa fa-cog"></i>Settings</a></li>
						<li><a href="AddBeneficiary?action=Logout"><i class="fa fa-sign-out"></i>Log out</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="three">
			<ul class="threec1" id="threep1">
				<li title="Toggle the Dropdown" id="codefold"><a>Quick Links</a> <span id="codefoldp1"><i class="fa fa-angle-double-left"></i> <span>...</span> <i class="fa fa-angle-double-right"></i></span>  <i id="codefoldp2" class="fa fa-angle-double-right"></i></li>
				<li class="threep1c1"><a href="AddBeneficiary?action=AddBeneficiary">Add Beneficiary</a></li>
				<li class="threep1c1"><a href="AddBeneficiary?action=ApproveBeneficiary">Approve Beneficiary</a></li>
				<li class="threep1c1"><a href="AddBeneficiary?action=DeleteBeneficiary">Delete Beneficiary</a></li>
				<li class="threep1c1"><a href="AddBeneficiary?action=FundTransferBeneficiary">Fund Transfer Existing Beneficiaries</a></li>
				<li class="threep1c1"><a href="AddBeneficiary?action=MiniStatement">Mini Statement</a></li>
			</ul>
		</div>
		<div class="four">
			<div class="fourc1">
				<h1>Add Beneficiary</h1>
			</div>
			<div class="fourc2">
				<form action="AddBeneficiary" method="post">
					<table>
						<tr>
						    <td width="40%">Enter Beneficiary's Name*:</td>
						    <td width="25%"><input id="fname" type="text" name="fname" size="40" placeholder="Enter Beneficiary's First Name" required/></td>
						    <td width="5%"><img id="fnameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
						    <td width="25%"><input id="lname" type="text" name="lname" size="40" placeholder="Enter Beneficiary's Last Name" required/></td>
						    <td width="5%"><img id="lnameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr>
						    <td>Enter Beneficiary's Account No*: </td>
						    <td colspan="3"><input id="acc" type="text" name="acc" size="40" placeholder="Enter Beneficiary's Account No" required/></td>
					    	<td><img id="accimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr>
						    <td>Confirm Beneficiary's Account No*: </td>
						    <td colspan="3"><input id="cnfacc" type="text" name="cnfacc" size="40" placeholder="Confirm Beneficiary's Account No" required disabled/></td>
						    <td><img id="cnfaccimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr>
						    <td>Enter Bank Transfer Limit*: </td>
						    <td colspan="3"><input id="banktransferlimit" type="text" name="banktransferlimit" size="40" min="100" max="500000" placeholder="Enter Bank Transfer Limit. Max limit 500000" required/></td>
					    	<td><img id="banktransferlimitimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr>
						    <td>Select Bank Name*: </td>
						    <td>
						    	<select id="banknamelist" name="banknamelist" required>
						    		<option value="0">-- Choose Beneficiary's Bank Name --</option>
						    		<option value="SBI">SBI</option>
								  	<option value="HDFC">HDFC</option>
								  	<option value="PNB">PNB</option>
								  	<option value="ICICI">ICICI</option>
								</select>
							</td>
							<td><img id="banknameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
							<td>
						    	<select id="branchnamelist" name="branchnamelist" required>
						    		<option value='0'>-- Choose Beneficiary's Branch Name --</option>
						    		<option value="DELHI">DELHI</option>
								  	<option value="KOLKATA">KOLKATA</option>
								  	<option value="MADRAS">MADRAS</option>
								  	<option value="MUMBAI">MUMBAI</option>
								</select>
							</td>
							<td><img id="branchnameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr>
						    <td>Enter Beneficiary's IFSC code*: </td>
						    <td colspan="3"><input id="ifsc" type="text" name="ifsc" size="40" placeholder="Enter Beneficiary's Branch's IFSC code"  required/></td>
						    <td><img id="ifscimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr  id="terms">
					    	<td>
					    		<input id="chkbox" type="checkbox" name="chkbox" value="accept"/>&nbsp;&nbsp;<a href="Terms.html">I ACCEPT THE TERMS AND LICENSE AGGREMENT</a>
					    	</td>
					    </tr>
					    <tr id="buttons">
					    	<td><input class="grow reset" type="button" name="btnRESET" value="RESET"></td>
						    <td colspan="4"><input class="add" type="button" name="btnADD" value="ADD BENEFICIARY" disabled></td>
					    </tr>
					</table>
					<input type="hidden" name="benaddpagetime" value="0"/>
					<input type="hidden" name="ftime" value="0"/>
					<input type="hidden" name="ltime" value="0"/>
					<input type="hidden" name="acctime" value="0"/>
					<input type="hidden" name="cnfacctime" value="0"/>
					<input type="hidden" name="limittime" value="0"/>
					<input type="hidden" name="banknametime" value="0"/>
					<input type="hidden" name="branchnametime" value="0"/>
					<input type="hidden" name="ifsctime" value="0"/>
					<input type="hidden" name="chktime" value="0"/>
					<input type="hidden" name="resettime" value="0"/>
					<input type="hidden" name="subtime" value="0"/>
					<input type="hidden" name="temp" value="0"/>
					<input type="hidden" name="temp1" value="0"/>
					<input type="hidden" name="temp2" value="0"/>
				</form>
			</div>
		</div>
		<h3 id="note">Note: The fields marked with * are compulsory</h3>
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
		<%@ include file="Footer.html" %>
	</body>
</html>
