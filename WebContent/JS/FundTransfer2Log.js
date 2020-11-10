$(document).ready(function(){
	
	setPageTime();
	function setPageTime(){
		$('[name="fundtransferpage2time"]').val(getTimeStamp());
		$.ajax({
			type: 'POST',
			url: 'FundTransfer2',
			data:{
				action: 'setPageTime',
				pagetime: $('[name="fundtransferpage2time"]').val()
			},
			cache:false
		});
	}
	
	$('[name="amount"]').on("focus",function(){
		$('[name="temp"]').val(getTimeInMilliseconds());
	});
	
	$('[name="amount"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="amounttime"]').val(parseInt($('[name="amounttime"]').val())+time-$('[name="temp"]').val());
	});
	
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