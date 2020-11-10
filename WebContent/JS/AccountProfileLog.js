$(document).ready(function(){
	
	setPageTime();
	
	/*$('.twoc1 a').click(function(){
		$.ajax({
			type: 'POST',
			url: 'AccountProfile',
			data:{
				action: 'Clicked Menu',
				option: this.getAttribute("href"),
				time: getTimeStamp()
			},
			cache:false
		});
	});
	
	$('.three a').click(function(){
		$.ajax({
			type: 'POST',
			url: 'AccountProfile',
			data:{
				action: 'Clicked Quick Link',
				option: this.getAttribute("href"),
				time: getTimeStamp()
			},
			cache:false
		});
	});*/
	
	function setPageTime(){
		/*$('[name="emailverifypagetime"]').val(getTimeStamp());
		console.log("Email verify pagetime textfield "+$('[name="emailverifypagetime"]').val());
		console.log("Current time "+getTimeStamp());*/
		$.ajax({
			type: 'POST',
			url: 'AccountProfile',
			data:{
				action: 'setPageTime',
				pagetime: getTimeStamp()
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