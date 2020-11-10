$(document).ready(function(){
	setRegpage1time();
	
	$('[name="chkbox"]').click(function(){
		$('[name="chktime"]').val(getTimeStamp());
	});
	$('[name="fname"],[name="lname"],[name="uname"],[name="acc"],[name="email"],[name="phone"],[name="address"]').on("focus",function(){
		$('[name="temp"]').val(getTimeInMilliseconds());
	});
	$('[name="fname"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="ftime"]').val(parseInt($('[name="ftime"]').val())+time-$('[name="temp"]').val());
		//console.log("blur time "+time);
	});
	$('[name="lname"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="ltime"]').val(parseInt($('[name="ltime"]').val())+time-$('[name="temp"]').val());
	});
	$('[name="uname"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="utime"]').val(parseInt($('[name="utime"]').val())+time-$('[name="temp"]').val());
	});
	$('[name="acc"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="acctime"]').val(parseInt($('[name="acctime"]').val())+time-$('[name="temp"]').val());
	});
	$('[name="phone"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="phonetime"]').val(parseInt($('[name="phonetime"]').val())+time-$('[name="temp"]').val());
	});
	$('[name="email"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="emailtime"]').val(parseInt($('[name="emailtime"]').val())+time-$('[name="temp"]').val());
	});
	$('[name="address"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="addresstime"]').val(parseInt($('[name="addresstime"]').val())+time-$('[name="temp"]').val());
	});
	function getTimeInMilliseconds() {
		var now = new Date();
	    return (now.getTime());
	}
	
	function setRegpage1time(){
		$('[name="regpage1time"]').val(getTimeStamp());
		$.ajax({
			type: 'POST',
			url: 'Registration1',
			data:{
				action: 'setRegpage1time',
				regpage1time: $('[name="regpage1time"]').val()
			},
			cache:false
		});
	}
	
	function getTimeStamp() {
		var now = new Date();
		var year, month, day, hours, minutes, sec, msec;
		year = now.getFullYear();
		month = now.getMonth() + 1;
		day = now.getDate();
		hours = now.getHours();
		minutes = now.getMinutes();
		sec = now.getSeconds();
		msec = now.getMilliseconds();
		return (((day < 10) ? ("0" + day) : day) + "-" +
			((month < 10) ? ("0" + month) : month) + "-" +
			year + " " +
			((hours < 10) ? ("0" + hours) : hours) + ":" +
			((minutes < 10) ? ("0" + minutes) : minutes) + ":" +
			((sec < 10) ? ("0" + sec) : sec) + ":" +
			((msec < 100) ? ((msec < 10) ? ("00" + msec) : ("0" + msec)) : msec));
	}
});