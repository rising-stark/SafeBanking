$(document).ready(function() {
	function check() {
		return ($('[name="password"]').val().length);
	}

	$('[name="password"]').keyup(function() {
		var len = check();
		if (len < 8)
			$('[name="btnLogin"]')[0].disabled = true;
		else
			$('[name="btnLogin"]')[0].disabled = false;
		if ($('[name="btnLogin"]').prop("disabled")) {
			console.log("hello");
			$('[name="btnLogin"]').removeClass("btnLogin");
		} else {
			console.log("this");
			$('[name="btnLogin"]').addClass("btnLogin");
		}
	});
});