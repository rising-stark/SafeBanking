<%@page import="com.bank.model.BeneficiaryDetails"%>
<%@page import="com.bank.controller.GetTime"%>
<%@page import="com.bank.controller.ShowAll"%>
<%@page import="java.util.ArrayList"%>
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
		<link rel="stylesheet" type="text/css" href="CSS/DeleteBeneficiary.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/DeleteBeneficiary.js"></script>
		<script type="text/javascript" src="JS/DeleteBeneficiaryLog.js"></script>
		<script type="text/javascript" src="JS/LoginTemplate.js"></script>
	</head>
	<body style="background-image: url('img/2.jpg');">
		<%@ include file="Header.html" %>
		<%@ include file="ClearCache.jsp" %>
		<%!
			GetTime obj = new GetTime();
			ShowAll show = new ShowAll();
			String uname, ex;
			int i;
		%>
		<%
			System.out.println("\nFinally in DeleteBeneficiary.jsp\n");
			if(session.getAttribute("loggedInLogList")==null){
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			ArrayList<BeneficiaryDetails> benList=(ArrayList<BeneficiaryDetails>)session.getAttribute("benList");
			uname=(String)session.getAttribute("uname");
			ex=(String)session.getAttribute("ex");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
		%>
		<div class="two">
			<div class="twoc1">
				<a class="twoc1c1" href="DeleteBeneficiary?action=AccountProfile"><i class="fa fa-home"></i>
					My Account &amp; Profile
				</a>
				<a class="twoc1c1" href="DeleteBeneficiary?action=PaymentTransfer"><i class="fa fa-usd"></i><i class="fa fa-usd"></i>
					Payments / Transfers
				</a>
				<a class="twoc1c1 active" href="DeleteBeneficiary?action=ManageBeneficiaries"><i class="fa fa-list-ul"></i>
					Manage Beneficiaries
				</a>
				<a class="twoc1c1" href="DeleteBeneficiary?action=Transaction"><i class=" fa fa-exchange"></i>
					Transactions
				</a>
			</div>
			<div class="twoc2">
				<%-- <div id="twoc2p1" class="twoc2c1">
					<%=logout_time%>				
				</div> --%>
				<div id="twoc2p2" class="twoc2c1"><i class=" fa fa-university"></i>  Welcome, <%=uname%>
					<ul class="dropdown">
						<li><a href="DeleteBeneficiary?action=Profile"><i class="fa fa-user-circle-o"></i>Profile</a></li>
						<li><a href="DeleteBeneficiary?action=Settings"><i class="fa fa-cog"></i>Settings</a></li>
						<li><a href="DeleteBeneficiary?action=Logout"><i class="fa fa-sign-out"></i>Log out</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="three">
			<ul class="threec1" id="threep1">
				<li title="Toggle the Dropdown" id="codefold"><a>Quick Links</a> <span id="codefoldp1"><i class="fa fa-angle-double-left"></i> <span>...</span> <i class="fa fa-angle-double-right"></i></span>  <i id="codefoldp2" class="fa fa-angle-double-right"></i></li>
				<li class="threep1c1"><a href="DeleteBeneficiary?action=AddBeneficiary">Add Beneficiary</a></li>
				<li class="threep1c1"><a href="DeleteBeneficiary?action=ApproveBeneficiary">Approve Beneficiary</a></li>
				<li class="threep1c1"><a href="DeleteBeneficiary?action=DeleteBeneficiary">Delete Beneficiary</a></li>
				<li class="threep1c1"><a href="DeleteBeneficiary?action=FundTransferBeneficiary">Pay to Existing Beneficiaries</a></li>
				<li class="threep1c1"><a href="DeleteBeneficiary?action=MiniStatement">Mini Statement</a></li>
			</ul>
		</div>
		<div class="four">
			<div class="fourc1">
				<h2>List of Beneficiaries Ordered by approved first</h2>
			</div>
			<div class="fourc2">
				<h3>An email to &nbsp;&nbsp;<span id="email">Delete</span>&nbsp;&nbsp; the beneficiaries will be sent to &nbsp;&nbsp;<span id="email"><em><%=ex %></em></span></h3>
			</div>
			<div class="fourc3">
				<form method="POST" action="DeleteBeneficiary">
					<%
					if(benList.size()>0){
						/* System.out.println("BENList "+benList); */
					%>
						<table>
							<tr>
								<th>Name</th>
								<th>Account Number</th>
								<th>Transfer Limit</th>
								<th>Bank Name</th>
								<th>Date Added</th>
								<th>Last Transaction Date</th>
								<th>IFSC Code</th>
								<th>Approved Status</th>
								<th>Delete Button</th>
							</tr>
						<%
							for(i=0;i<benList.size();i++){
								BeneficiaryDetails ben=benList.get(i);
						%>
								<tr>
									<td><%=ben.getBenfname()+" "+ben.getBenlname()%></td>
									<td><%=ben.getBenacc() %></td>
									<td><%=ben.getBenlimit() %></td>
									<td><%=ben.getBenbankname() %></td>
									<td><%=ben.getBendateadded() %></td>
									<td><%=ben.getBenlasttransactiondate() %></td>
									<td><%=ben.getBenifsc() %></td>
									<%if(ben.getApproved()==0) {%>
										<td style="color: darkred;font-size: 20px;weight: 300;">NO</td>
									<%}else{%>
										<td style="color: yellow;font-size: 20px;weight: 300;">YES</td>
									<%}%>
									<td><input class="delete" type="button" name="btnDELETE" value="DELETE"></td>
									<td ><input type="hidden" name="index" value="<%=i%>"/></td>
			       				</tr>
			       			<%}%>
						</table>
					<%}else{%>
	      				<h2>No beneficiaries found!!</h2>
	      			<%}%>
	      			<input type="hidden" name="bendeletepagetime" value="0"/>
					<input type="hidden" name="subtime1" value="0"/>
				</form>
			</div>
		</div>
		<div class="five">
			<img alt="loading" src="gifs/loading.gif">
			<h1>PROCESSING</h1>
		</div>
		<%-- <%@ include file="Footer.html" %> --%>
	</body>
</html>
