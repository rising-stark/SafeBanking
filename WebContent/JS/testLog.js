var accType = 0;
var acc=0;
var toooltipAcc;
$(document).ready(function(){
	/*$(window).unload(function(){
		var a="HH";
		console.log(a);
		return a;
	});
	
	$(window).on('popstate', function(event) {
		 alert("pop");
		});*/
	
	$('.twoc1 a').click(function(){
		var a=this.getAttribute("href");
		console.log(a);
	});
	
	function showTooltip(){
		$("body").find('img').each(function(){
			console.log($(this).attr("alt"));
		   if($(this).is(':visible')){
		     console.log($(this).attr("alt"));
		    }
		});
	}
	
	$('input').keypress(function(e) {
		if (e.which == 32)
			return false;
	});
	var regExpNUM = /[0-9]/;
	$('[name="acc"]').on('keydown keyup', function(e) {
		var value = e.key;
		/*var ascii=value.charCodeAt(0);
		$('textarea').append(ascii);
		$('textarea').append(value);
		console.log(e);*/
		// Only numbers
		if (!regExpNUM.test(value) &&
			e.which != 8 // backspace
			&&
			e.which != 46 // delete
			&&
			(e.which < 37 // arrow keys
				||
				e.which > 40)) {
			e.preventDefault();
			return false;
		}
	});
	
	$('[name="acc"]').on('keypress', function(e) {
		if ($(this).val().length > 13) {
			e.preventDefault();
		}
	});
	$('input').on('keyup keydown keypress', function() {
		var name = $(this).attr("name");
		if (name == "acc") {
			accType = 1;
			acc = 0;
		}
		if ($(this).val().length == 0) {
			var displayTooltip = "Please fill out this field";
			$(this).attr('title', displayTooltip);
			$('#' + name + 'img').attr("src", "img/wrong.png");
			$('#' + name + 'img').prop("alt", "wrong");
			$('#' + name + 'img').attr('title', displayTooltip);
		}
		$('#' + name + 'img').attr("hidden", true);
	});
	$('[name="acc"]').on('focus blur mouseleave', function() {
		if (accType == 0) {
			return false;
		}
		accType = 0;
		var accnumber = $('[name="acc"]').val();
		if (accnumber.length > 0) {
			if (accnumber.length == 14) {
				acc = 1;
				tooltipAcc = "Accepted";
				$('#accimg').attr("src", "img/tick.png");
				$('#accimg').prop("alt", "tick");
				$('#accimg').attr("hidden", false);
			} else {
				acc = 0;
				tooltipAcc = "Account Number must be a 14-digit Number";
				$('#accimg').attr("src", "img/wrong.png");
				$('#accimg').prop("alt", "wrong");
				$('#accimg').attr("hidden", false);
				showTooltip();
			}
		} else {
			acc = 0;
			tooltipAcc = "Please fill out this field";
			$('#accimg').attr("src", "img/wrong.png");
			$('#accimg').prop("alt", "wrong");
			$('#accimg').attr("hidden", true);
		}
		$('[name="acc"]').attr('title', tooltipAcc);
		$('#accimg').attr('title', tooltipAcc);
	});
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