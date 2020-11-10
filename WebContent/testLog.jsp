<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Invite</title>
		
		<link type="text/css" rel="stylesheet" href="CSS/Header.css">
		<link type="text/css" rel="stylesheet" href="CSS/Footer.css">
		<link type="text/css" rel="stylesheet" href="CSS/test.css">
		<link type="text/css" rel="stylesheet" href="https://unpkg.com/microtip/microtip.css">
		
		<script type="text/javascript" src="JS/Jquery.js"></script>
		<script type="text/javascript" src="JS/testLog.js"></script>
	</head>
	<body>
		<table>
			<tr>
			    <td width="40%">Enter Beneficiary's Account No*: </td>
			    <td width="40%"><input id="acc" type="text" name="acc" size="40" placeholder="Enter Beneficiary's Account No" required/></td>
		    	<td width="5%"><img id="accimg" src="img/wrong.png" alt="wrong" hidden="true"/><span id="tooltipAcc">HelloHelloHelloHelloHelloHello</span></td>
		    </tr>
		</table>
	</body>
</html>