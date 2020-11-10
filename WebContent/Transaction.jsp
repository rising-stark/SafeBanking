<%@page import="com.bank.model.TransactionDetails"%>
<%@page import="com.bank.controller.ShowAll"%>
<%@page import="com.bank.controller.GetTime"%>
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
		<link type="text/css" rel="stylesheet" href="CSS/Transaction.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/Transaction.js"></script>
		<script type="text/javascript" src="JS/TransactionLog.js"></script>
		<script type="text/javascript" src="JS/LoginTemplate.js"></script>
	</head>
	<body  style="background-image: url('img/2.jpg');">
		<%@ include file="Header.html" %>
		<%@ include file="ClearCache.jsp" %>
		<%!
			GetTime obj = new GetTime();
			ShowAll show = new ShowAll();
			String acc, uname;
			int i;
		%>
		<%
			System.out.println("\nFinally in Transaction.jsp\n");
			if(session.getAttribute("loggedInLogList")==null){
				session.invalidate();
				response.sendRedirect("Welcome.jsp");
				return;
			}
			ArrayList<TransactionDetails> trList=(ArrayList<TransactionDetails>)session.getAttribute("trList");
			acc=(String)session.getAttribute("acc");
			uname=(String)session.getAttribute("uname");
			show.ReqParam(request, response);
			show.SessionParam(request, response);
		%>
		<div class="two">
			<div class="twoc1">
				<a class="twoc1c1" href="Transaction?action=AccountProfile"><i class="fa fa-home"></i>
					My Account &amp; Profile
				</a>
				<a class="twoc1c1" href="Transaction?action=PaymentTransfer"><i class="fa fa-exchange"></i>
					Payments / Transfers
				</a>
				<a class="twoc1c1" href="Transaction?action=ManageBeneficiaries"><i class=" fa fa-id-card-o"></i>
					Manage Beneficiaries
				</a>
				<a class="twoc1c1 active" href="Transaction?action=Transaction"><i class="fa fa-globe"></i>
					Transactions
				</a>
			</div>
			<div class="twoc2">
				<%-- <div id="twoc2p1" class="twoc2c1">
					<%=logout_time%>				
				</div> --%>
				<div id="twoc2p2" class="twoc2c1">Welcome, <%=uname%>
					<ul class="dropdown">
						<li><a href="Transaction?action=Profile"><i class="fa fa-user-circle-o"></i>Profile</a></li>
						<li><a href="Transaction?action=Settings"><i class="fa fa-cog"></i>Settings</a></li>
						<li><a href="Transaction?action=Logout"><i class="fa fa-sign-out"></i>Log out</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="three">
			<ul class="threec1" id="threep1">
				<li title="Toggle the Dropdown" id="codefold"><a>Quick Links</a> <span id="codefoldp1"><i class="fa fa-angle-double-left"></i> <span>...</span> <i class="fa fa-angle-double-right"></i></span>  <i id="codefoldp2" class="fa fa-angle-double-right"></i></li>
				<li class="threep1c1"><a href="Transaction?action=AddBeneficiary.jsp">Add Beneficiary</a></li>
				<li class="threep1c1"><a href="Transaction?action=ApproveBeneficiary">Approve Beneficiary</a></li>
				<li class="threep1c1"><a href="Transaction?action=DeleteBeneficiary">Delete Beneficiary</a></li>
				<li class="threep1c1"><a href="Transaction?action=FundTransferBeneficiary">Pay to Existing Beneficiaries</a></li>
				<li class="threep1c1"><a href="Transaction?action=MiniStatement">Mini Statement</a></li>
			</ul>
		</div>
		<div class="four">
			<div class="fourc1">
				<h2>All Transactions</h2>
			</div>
			<div class="fourc2">
				<%
   				if(trList.size()>0){
   					i=0;
				%><table id="fourc2p1">
						<tr>
							<th width="2%">S. No.</th>
							<th>Transaction Id</th>
							<th>Payee Name</th>
							<th>Payee<br>Account Number</th>
							<th>Payer Name</th>
							<th>Payer<br/>Account Number</th>
							<th id="trdate">Transaction<br/>Date <i class="fa fa-arrow-down"></i><i class="fa fa-arrow-up"></i></th>
							<th id="tramt">Amount<i class="fa fa-sort-amount-desc"></i><i class="fa fa-sort-amount-asc"></i><i class="fa fa-arrow-up"></i><i class="fa fa-arrow-down"></i></th>
							<th>Remaining<br>Balance</th>
						</tr>
						<%for(TransactionDetails tr:trList){
							if(tr.getPayeeacc().equals(acc)){%>
								<tr class="credit">
									<td width="2%"><%=++i %>.</td>
									<td><%=tr.getTrid()%></td>
									<td><%=tr.getPayeename()%></td>
									<td><%=tr.getPayeeacc()%></td>
									<td><%=tr.getPayername()%></td>
									<td><%=tr.getPayeracc()%></td>
									<td><%=tr.getTransactiondate()%></td>
									<td><%=tr.getAmount()%> credited</td>
									<td style="background: rgba(0, 250, 0, 0.6)"><%=tr.getRemBalance()%></td>
								</tr>
							<%} 
							else{%>
								<tr class="debit">
									<td width="2%"><%=++i %>.</td>
									<td><%=tr.getTrid()%></td>
									<td><%=tr.getPayeename()%></td>
									<td><%=tr.getPayeeacc()%></td>
									<td><%=tr.getPayername()%></td>
									<td><%=tr.getPayeracc()%></td>
									<td><%=tr.getTransactiondate()%></td>
									<td><%=tr.getAmount()%> debited</td>
									<td style="background: rgba(250, 0, 0, 0.6)"><%=tr.getRemBalance()%></td>
								</tr>
							<%}
		       			  }%>
					</table>
				<%}else{%>
      				<h2>No Transactions to display!!</h2>
      			<%}%>
			</div>
		</div>
		<%@ include file="Footer.html" %>
	</body>
</html>