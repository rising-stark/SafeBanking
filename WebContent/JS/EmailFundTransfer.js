function emailTransferInitiated(){
	localStorage.setItem("start","y")
	$.ajax({
		type: 'POST',
		url: 'EmailFundTransfer',
		data: {
			action: 'emailTransferInitiated'
		},
		cache: false,
		success: function(result){
			if(result=="1"){
				console.log("Email sent successfully for emailTransferInitiated");
			}else{
				var msg="Error in sending email for emailTransferInitiated";
				alert(msg)
				console.log(msg);
			}
		}
	});
}
$(document).ready(function(){
	var OTP;
	OTP=0;
	console.log(localStorage);
	function startTimer(duration, display) {
		var timer = duration, minutes, seconds;
		var timerInter = setInterval(function () {
			minutes = parseInt(timer / 60, 10)
			seconds = parseInt(timer % 60, 10);
			minutes = minutes < 10 ? "0" + minutes : minutes;
			seconds = seconds < 10 ? "0" + seconds : seconds;
			
			if(minutes=="01"){
				display.textContent = minutes + " minute and " + seconds+" seconds.";
			}else{
				display.textContent = minutes + " minutes and " + seconds+" seconds.";
			}
			
			if (timer<=0 || timer-- == 0){
				$('.six').slideDown(500);
				$('#sixp1').fadeIn(500);
				localStorage.clear();
				console.log("removing local storage");
				console.log(localStorage);
				display.textContent = "00 minutes and 00 seconds.";
				clearInterval(timerInter);
				setTimeout(function(){
					$('.six').slideUp(500);
					window.location.href="PaymentTransfer.jsp";
				}, 3000);
			}
			localStorage.setItem("seconds",seconds)
			localStorage.setItem("minutes",minutes)
		}, 1000);
	}
	var twoMinutes, sec, min, start;
	sec  = localStorage.getItem("seconds");
	min = localStorage.getItem("minutes");
	start = localStorage.getItem("start");
	if(min==null || min=="NaN" || start=="y"){
		localStorage.setItem("start", "n");
		twoMinutes = 60 * 2;
	}else{
		twoMinutes = (parseInt(min)*60+parseInt(sec));
    }
    display = document.querySelector('.fourc1 #timer');
    startTimer(twoMinutes, display);
	
	$('input').keypress(function(e){
		if(e.which==32)
			return false;
	});
	var regExpNUM = /[0-9]/;
	$('[name="OTPUser"]').on('keydown keyup blur focus', function(e) {
		var value =e.key;
		// Only numbers
		if (!regExpNUM.test(value)
	      && e.which != 8   // backspace
	      && e.which != 46  // delete
	      && (e.which < 37  // arrow keys
	      || e.which > 40)) {
	          e.preventDefault();
	          return false;
	    }
	});
	$('[name="OTPUser"]').on('keypress', function(e) {
		if($('[name="OTPUser"]').val().length >5){
			e.preventDefault();
		}
	});
	$('[name="OTPUser"],.four').on('keyup keydown', function() {
		var OTPUser=$('[name="OTPUser"]').val();
		if(OTPUser.length ==6){
			OTP=1;
		}
		else
			OTP=0;
	});
	setInterval(Check, 250);
	function Check(){
		if(OTP==1){
			$('[name="btnPAY"]').addClass("grow");
			$('[name="btnPAY"]')[0].disabled=false;
		}
		else if(OTP==0){
			$('[name="btnPAY"]').removeClass("grow");
			$('[name="btnPAY"]')[0].disabled=true;
		}
	}
	$('#sixp1p1, #sixp3p1, #sixp4p1').click(function(){
		$('.six').slideUp(500);
		window.location.href="PaymentTransfer.jsp";
	});
	$('[name="btnPAY"]').click(function(){
		$('[name="subtime3"]').val(getTimeStamp());
		$('.five').slideDown(500);
		$.ajax({
			type: 'POST',
			url: 'EmailFundTransfer',
			data:{
				action: "verifyOTP",
				OTPUser: $('[name="OTPUser"]').val(),
				confirmpagetime: $('[name="confirmpagetime"]').val(),
				otptime: $('[name="otptime"]').val(),
				subtime3: $('[name="subtime3"]').val()
			},
			cache:false,
			success: function(result){
				console.log(typeof(result));
				console.log("Result is "+result);
				setTimeout(function() {
					$('.five').slideUp(500);
					if(result=="1"){
						$('.six').delay(750).slideDown(500);
						$('#sixp3').delay(750).slideDown(500);
						setTimeout(function(){
							$('.six').slideUp(500);
							window.location.href="PaymentTransfer.jsp";
						}, 3000);
					}
					else{
						OTP=0;
						tooltipOTPUser = "Please fill out this field";
						$('.six').delay(750).slideDown(500);
						$('#sixp2').delay(750).slideDown(500);
						$('[name="OTPUser"]').val("");
						$('[name="otptime"]').val("0");
						$('[name="subtime3"]').val("0");
						$('[name="confirmpagetime"]').val(getTimeStamp());
						$('[name="OTPUser"]').attr('title', tooltipOTPUser);
						$('[name="btnPAY"]').removeClass("grow");
						$('[name="btnPAY"]')[0].disabled=true;
					}
				}, 500);
			}
		});
	});
	$('[name="btnCANCEL"]').click(function(){
		$('[name="subtime3"]').val(getTimeStamp());
		$('.six').slideDown(500);
		$('#sixp4').slideDown(500);
		$.ajax({
			type: 'POST',
			url: 'EmailFundTransfer',
			data:{
				action: "cancelTransaction",
				OTPUser: $('[name="OTPUser"]').val(),
				confirmpagetime: $('[name="confirmpagetime"]').val(),
				otptime: $('[name="otptime"]').val(),
				subtime3: $('[name="subtime3"]').val()
			},
			cache:false
		});
		setTimeout(function(){
			$('.six').slideUp(500);
			window.location.href="PaymentTransfer.jsp";
		}, 3000);
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