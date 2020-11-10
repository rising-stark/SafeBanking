function emailDeletionInitiated(){
	$.ajax({
		type: 'POST',
		url: 'EmailDeleteBeneficiary',
		data: {
			action: 'emailDeletionInitiated'
		},
		cache: false,
		success: function(result){
			if(result=="1"){
				console.log("Email sent successfully for emailDeletionInitiated");
			}else{
				var msg="Error in sending email for emailDeletionInitiated";
				alert(msg)
				console.log(msg);
			}
		}
	});
}
$(document).ready(function(){
	var OTP=0;
	var OTPType;
	var tooltipOTP;
	
	$('input').keypress(function(e){
		if(e.which==32)
			return false;
	});
	
	$('input').on('keyup keydown keypress', function() {
		  var name = $(this).attr("name");
		  if(name=="OTPUser"){
			  OTPType=1;
			  OTP=0;
		  }
		  if($(this).val().length==0){
			  var displayTooltip = "Please fill out this field";
			  $(this).attr('title', displayTooltip);				  
			  $('#'+name+'img').attr("src","img/wrong.png");
			  $('#'+name+'img').prop("alt", "wrong");
			  $('#'+name+'img').attr('title', displayTooltip);
		  }
		  $('#'+name+'img').attr("hidden",true);
	  });
	
	var regExpOTP = /[0-9]/;
	$('[name="OTPUser"]').on('keydown keyup blur focus', function(e) {
		var value =e.key;
	    /*var ascii=value.charCodeAt(0);
	    $('textarea').append(ascii);
	    $('textarea').append(value);
	    console.log(e);*/
	    // Only numbers
	    if (!regExpOTP.test(value)
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
	$('[name="OTPUser"]').on('keyup keydown keypress',function(){
		var OTPUser=$('[name="OTPUser"]').val();
		if(OTPUser.length==6){
			OTP=1;
			tooltipOTPUser = "Accepted";
		}
		else if(OTPUser.length>0){
			OTP=0;
			tooltipOTPUser = "The OTP should be 6 digits long";
		}
		else{
			OTP=0;
			tooltipOTPUser = "Please fill out this field";
		}
		$('[name="OTPUser"]').attr('title', tooltipOTPUser);
	});
	$('#sixp3p1').click(function(){
		$('.six').slideUp(500);
		window.location.href="ManageBeneficiaries.jsp";
	});
	$('[name="btnDELETE"]').click(function(){
		$('[name="subtime2"]').val(getTimeStamp());
		if (confirm('Are you sure you want to delete this beneficiary? This action can not be reversed.')) {
			$('[name="confirmtime"]').val(getTimeStamp());
			$('.five').slideDown(500);
			$.ajax({
				type: 'POST',
				url: 'EmailDeleteBeneficiary',
				data:{
					action: "verifyOTP",
					OTPUser: $('[name="OTPUser"]').val(),
					benemaildeletepagetime: $('[name="benemaildeletepagetime"]').val(),
					otptime: $('[name="otptime"]').val(),
					confirmtime: $('[name="confirmtime"]').val(),
					subtime2: $('[name="subtime2"]').val()
				},
				cache:false,
				success: function(result){
					setTimeout(function() {
						$('.five').slideUp(500);
						if(result=="1"){
							$('.six').delay(750).slideDown(500);
							$('#sixp3').delay(750).slideDown(500);
							setTimeout(function(){
								$('.six').slideUp(500);
								window.location.href="ManageBeneficiaries.jsp";
							}, 3000);
						}
						else{
							OTP=0;
							tooltipOTPUser = "Please fill out this field";
							$('.six').delay(750).slideDown(500);
							$('#sixp2').delay(750).slideDown(500);
							$('[name="OTPUser"]').val("");
							$('[name="otptime"]').val("0");
							$('[name="confirmtime"]').val("0");
							$('[name="subtime2"]').val("0");
							$('[name="benemaildeletepagetime"]').val(getTimeStamp());
							$('[name="OTPUser"]').attr('title', tooltipOTPUser);
							$('[name="btnDELETE"]').removeClass("grow");
							$('[name="btnDELETE"]')[0].disabled=true;
						}
					}, 500);
				}
			});
		}else{
			OTP=0;
			tooltipOTPUser = "Please fill out this field";
			$('[name="OTPUser"]').val("");
			$('[name="OTPUser"]').attr('title', tooltipOTPUser);
			$('[name="btnDELETE"]').removeClass("grow");
			$('[name="btnDELETE"]')[0].disabled=true;
		}
	});
	setInterval(Verify, 250);
	function Verify(){
		if(OTP==1){
			$('[name="btnDELETE"]').addClass("grow");
			$('[name="btnDELETE"]')[0].disabled=false;
		}
		else{
			$('[name="btnDELETE"]').removeClass("grow");
			$('[name="btnDELETE"]')[0].disabled=true;
		}
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