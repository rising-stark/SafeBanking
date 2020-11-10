$(document).ready(function(){
	setLoginpage2time();
	
	$('[name="pass"]').on("focus",function(){
		$('[name="temp"]').val(getTimeInMilliseconds());
	});
	$('[name="pass"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="passtime"]').val(parseInt($('[name="passtime"]').val())+time-$('[name="temp"]').val());
	});
	$('#three').on('click','[id^=imge]', function() {
		$('[name="pictime"]').val(getTimeStamp());
	});
	
	$("input[type='radio']").click(function(){
		$('[name="pictime"]').val(getTimeStamp());
	});
	
	function setLoginpage2time(){
		$('[name="loginpage2time"]').val(getTimeStamp());
		$.ajax({
			type: 'POST',
			url: 'PassAuthentication',
			data:{
				action: 'setLoginpage2time',
				loginpage2time: $('[name="loginpage2time"]').val()
			},
			cache:false
		});
	}
	
	function getTimeInMilliseconds() {
		var now = new Date();
	    return (now.getTime());
	}
	
	function getTimeStamp() {
		var now = new Date();
	    var year,month,day,hours,minutes,sec,msec;
	    year=now.getFullYear();
	    month=now.getMonth()+1;
	    day=now.getDate();
	    hours=now.getHours();
	    minutes=now.getMinutes();
	    sec=now.getSeconds();
	    msec=now.getMilliseconds();
			return (((day<10)?("0"+day):day)+"-"
	    +((month<10)?("0"+month):month)+"-"
	    +year+" "
	    +((hours<10)?("0"+hours):hours)+":"
	    +((minutes<10)?("0"+minutes):minutes)+":"
	    +((sec<10)?("0"+sec):sec)+":"
	    +((msec<100)?((msec<10)?("00"+msec):("0"+msec)):msec));
	}
});