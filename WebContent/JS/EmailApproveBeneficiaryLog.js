$(document).ready(function(){
	
	setPageTime();
	$('[name="otptime"]').on("focus",function(){
		$('[name="temp"]').val(getTimeInMilliseconds());
	});
	$('[name="otptime"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="otptime"]').val(parseInt($('[name="otptime"]').val())+time-$('[name="temp"]').val());
		console.log("time elapsed "+$('[name="otptime"]').val());
	});
	
	function setPageTime(){
		$('[name="benemailapprovepagetime"]').val(getTimeStamp());
		$.ajax({
			type: 'POST',
			url: 'EmailApproveBeneficiary',
			data:{
				action: 'setPageTime',
				pagetime: $('[name="benemailapprovepagetime"]').val()
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