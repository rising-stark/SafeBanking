<html>
	<head>
		<script type="text/javascript" src="JS/Jquery.js"></script>		
		<script type="text/javascript">
			if (performance.navigation.type == 1) {
				confirm( "This page is reloaded" );
			} else {
				console.log( "This page is not reloaded");
			}
		</script>
	</head>
	<body>
		<input id="hi" type="button" value="Hi">		
	</body>
</html>