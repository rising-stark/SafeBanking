function setSecuritypagetime(){
	$('[name="securitypagetime"]').val(getTimeStamp());
	$.ajax({
		type: 'POST',
		url: 'Login',
		data:{
			action: 'setSecuritypagetime',
			securitypagetime: $('[name="securitypagetime"]').val()
		},
		cache:false
	});
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
$(document).ready(function(){
	setLoginpagetime();
	
	$('[name="uname"],[name="code"],[name="securityanswer"]').on("focus",function(){
		$('[name="temp"]').val(getTimeInMilliseconds());
	});
	$('[name="uname"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="utime"]').val(parseInt($('[name="utime"]').val())+time-$('[name="temp"]').val());
	});
	$('[name="code"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="rctime"]').val(parseInt($('[name="rctime"]').val())+time-$('[name="temp"]').val());
	});
	
	$('[name="securityquestion"]').click( function() {
		var t=parseInt($('[name="temp1"]').val());
		var time=getTimeInMilliseconds();
		if(t==0){
			$('[name="temp1"]').val(time);
		}else{
			$('[name="securityquestime"]').val(parseInt($('[name="securityquestime"]').val())+time-$('[name="temp1"]').val());
			$('[name="temp1"]').val(0);
		}
	});
	
	$('[name="securityanswer"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="securityanstime"]').val(parseInt($('[name="securityanstime"]').val())+time-$('[name="temp"]').val());
	});
	
	function setLoginpagetime(){
		$('[name="loginpagetime"]').val(getTimeStamp());
		$.ajax({
			type: 'POST',
			url: 'Login',
			data:{
				action: 'setLoginpagetime',
				loginpagetime: $('[name="loginpagetime"]').val()
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