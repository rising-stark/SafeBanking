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
		<link type="text/css" rel="stylesheet" href="CSS/FundTransferNewUser.css">
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/FundTransferNewUser.js"></script>
		<script type="text/javascript" src="JS/LoginTemplate.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<script type="text/javascript">
			var msg="<%=(String)session.getAttribute("servermsg")%>";
			if(msg!="null"){
				alert(msg);
				<%session.removeAttribute("servermsg");
				System.out.println("msg attr "+(String)session.getAttribute("servermsg"));
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
			/*System.out.println("\nFinally in FundTransferNewUser.jsp\n");
			if(session.getAttribute("loggedInLogList")==null){
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			AddLoggedInLogList.add("FundTransferNewUser.jsp",request,response);
			logout_time = obj.now();
			uname=(String)session.getAttribute("uname");
			show.ReqParam(request, response);
			show.SessionParam(request, response);*/
		%>
		<div class="two">
			<div class="twoc1">
				<a class="twoc1c1" href="AccountProfile.jsp"><i class="fa fa-home"></i>
					My Account &amp; Profile
				</a>
				<a class="twoc1c1 active" href="PaymentTransfer.jsp"><i class="fa fa-exchange"></i>
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
				<li class="threep1c1"><a href="FetchBeneficiary?type=transfer">Fund Transfer</a></li>
				<li class="threep1c1"><a href="FetchTransaction">Mini Statement</a></li>
			</ul>
		</div>
		<div class="four">
			<div class="fourc1">
				<h1>Fund Transfer to a New User</h1>
			</div>
			<div class="fourc2">
				<form action="FundTransferNewUser" method="post">
					<table>
						<tr>
						    <td width="35%">Enter Payee's Name*:</td>
						    <td width="30%"><input id="fname" type="text" name="fname" size="40" placeholder="Enter Payee's First Name" required/></td>
						    <td width="30%"><input id="lname" type="text" name="lname" size="40" placeholder="Enter Payee's Last Name" required/></td>
						    <!-- <td width="5%"><img id="fnameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td> -->
						    <!-- <td width="6%"><img id="lnameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td> -->
					    </tr>
					    <tr>
						    <td>Enter Payee's Account No*: </td>
						    <td colspan="2"><input id="accno" type="text" name="accno" size="40" placeholder="Enter Payee's Account No" required/></td>
					    	<!-- <td><img id="accnoimg" src="img/wrong.png" alt="wrong" hidden="true"/></td> -->
					    </tr>
					    <tr>
						    <td>Confirm Payee's Account No*: </td>
						    <td colspan="2"><input id="cnfaccno" type="text" name="cnfaccno" size="40" placeholder="Confirm Payee's Account No" disabled required/></td>
						    <td><img id="cnfaccnoimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr>
						    <td>Select Bank &amp; Branch Name*: </td>
						    <td>
						    	<select id="banknamelist" name="banknamelist" required>
						    		<option value='null'>---- Choose Payee's Bank Name ----</option>
						    		<option value="SBI">SBI</option>
								  	<option value="HDFC">HDFC</option>
								  	<option value="PNB">PNB</option>
								  	<option value="ICICI">ICICI</option>
								</select>
							</td>
							<!-- <td><img id="banknameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td> -->
							<td>
						    	<select id="branchnamelist" name="branchnamelist" required>
						    		<option value='null'>---- Choose Payee's Branch Name ----</option>
						    		<option value="DELHI">DELHI</option>
								  	<option value="KOLKATA">KOLKATA</option>
								  	<option value="MADRAS">MADRAS</option>
								  	<option value="MUMBAI">MUMBAI</option>
								</select>
							</td>
							<!-- <td><img id="branchnameimg" src="img/wrong.png" alt="wrong" hidden="true"/></td> -->
					    </tr>
					    <tr>
						    <td>Enter Amount to Pay*: </td>
						    <td colspan="2"><input type="number" name="amount" min="100" max="500000" placeholder="Enter The Amount" required></td>
						    <td><img id="amountimg" src="img/wrong.png" alt="wrong" hidden="true"/></td>
					    </tr>
					    <tr>
						    <td>Enter Payee's IFSC code*: </td>
						    <td colspan="2"><input id="ifsc" type="text" name="ifsc" size="40" placeholder="Enter Payee's IFSC code"  required/></td>
						    <!-- <td><img id="ifscimg" src="img/wrong.png" alt="wrong" hidden="true"/></td> -->
					    </tr>
					    <tr  id="terms">
					    	<td>
					    		<input id="chkbox" type="checkbox" name="chkbox" value="accept"/>&nbsp;&nbsp;<a href="Terms.html">I ACCEPT THE TERMS AND LICENSE AGGREMENT</a>
					    	</td>
					    </tr>
					    <tr id="buttons">
					    	<td><input class="grow" type="button" name="btnRESET" value="RESET"></td>
						    <td colspan="3"><input type="submit" name="btnNEXT" value="NEXT" disabled></td>
					    </tr>
					</table>
					<input type="hidden" name="fundtransfernewuserpagetime" value=""/>
					<input type="hidden" name="ftime" value=""/>
					<input type="hidden" name="ltime" value=""/>
					<input type="hidden" name="accnotime" value=""/>
					<input type="hidden" name="cnfaccnotime" value=""/>
					<input type="hidden" name="banknametime" value=""/>
					<input type="hidden" name="branchnametime" value=""/>
					<input type="hidden" name="ifsctime" value=""/>
					<input type="hidden" name="amounttime" value=""/>
					<input type="hidden" name="chktime" value=""/>
					<input type="hidden" name="resettime" value=""/>
					<input type="hidden" name="subtime1" value=""/>
				</form>
			</div>
		</div>
		<h3 id="note">Note: The fields marked with * are compulsory</h3>
		<%@ include file="Footer.html" %>
	</body>
</html>
