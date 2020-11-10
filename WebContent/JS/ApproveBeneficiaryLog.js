$(document).ready(function(){
	
	setPageTime();
	function setPageTime(){
		$('[name="benapprovepagetime"]').val(getTimeStamp());
		$.ajax({
			type: 'POST',
			url: 'ApproveBeneficiary',
			data:{
				action: 'setPageTime',
				pagetime: $('[name="benapprovepagetime"]').val()
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
});