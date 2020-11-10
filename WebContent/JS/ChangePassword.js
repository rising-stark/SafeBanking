$(document).ready(function(){
	var OTP,oldpass,newpass,cnfnewpass,verify,check;
	var tooltipOldPass,tooltipNewPass,tooltipCnfNewPass,tooltipOTPUser;
	OTP=0;
	oldpass=0;
	newpass=0;
	cnfnewpass=0;
	verify=0;
	check=0;
	
	function startTimer(duration, display) {
		  var timer = duration, minutes, seconds;
		  setInterval(function () {
		      minutes = parseInt(timer / 60, 10)
		      seconds = parseInt(timer % 60, 10);

		      minutes = minutes < 10 ? "0" + minutes : minutes;
		      seconds = seconds < 10 ? "0" + seconds : seconds;

		      display.textContent = minutes + " minutes and " + seconds+" seconds";

		      if (--timer == 0){
		          alert("Session timeout. Redirecting to Account Profile.jsp.");
		          window.localStorage.removeItem("minutes");
		          window.localStorage.removeItem("seconds");
		          window.location.replace("AccountProfile.jsp");
		      }
		    window.localStorage.setItem("seconds",seconds);
		    window.localStorage.setItem("minutes",minutes);
		  }, 1000);
		  }

	sec  = parseInt(window.localStorage.getItem("seconds"));
	  min = parseInt(window.localStorage.getItem("minutes"));
	 
	  if(parseInt(min*sec)){
	    var fiveMinutes = (parseInt(min*60)+sec);
	  }else{
	    var fiveMinutes = 60 * 3;
	  }
	    // var fiveMinutes = 60 * 5;
	  display = document.querySelector('#fourc2p1 span');
	  startTimer(fiveMinutes, display);
	
  $('input').keypress(function(e){
		if(e.which==32)
			return false;
	});
	$('input').on('keyup keydown keypress', function() {
		  if($(this).val().length==0){
			  var displayTooltip = "Please fill out this field";
			  var name = $(this).attr("name");
			  $(this).attr('title', displayTooltip);
			  if(name=="oldpass")
				  oldpass=0;
			  else if(name=="newpass")
				  newpass=0;
			  else if(name=="cnfnewpass")
				  cnfnewpass=0;
			  else if(name=="OTPUser")
				  OTP=0;
			  $('#'+name+'img').attr("src","img/wrong.png");
			  $('#'+name+'img').prop("alt", "wrong");
			  $('#'+name+'img').attr("hidden",true);
			  $('#'+name+'img').attr('title', displayTooltip);
		  }
	});
	var regExpOTP = /[0-9]/;
	$('[name="OTPUser"]').on('keydown keyup keypress blur focus mouseenter mouseleave hover', function(e) {
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
			$('#OTPUserimg').attr("src","img/tick.png");
			$('#OTPUserimg').prop("alt", "tick");
			$('#OTPUserimg').attr("hidden",false);
			$('[name="OTPUser"]').attr('title', tooltipOTPUser);
			$('#OTPUserimg').attr('title', tooltipOTPUser);
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
	$('[name="oldpass"]').on('keypress', function(e) {
		  if($('[name="oldpass"]').val().length >14){
		    	e.preventDefault();
		    }
	});
	$('[name="newpass"]').on('keypress', function(e) {
		  if($('[name="newpass"]').val().length >14){
		    	e.preventDefault();
		    }
	});
	$('[name="cnfnewpass"]').on('keypress', function(e) {
		  if($('[name="cnfnewpass"]').val().length >14){
		    	e.preventDefault();
		    }
	});
	$('[name="oldpass"]').on('keyup keydown keypress',function(){
		var oldpassword=$('[name="oldpass"]').val();
		if(oldpassword.length>=8){
			oldpass=1;
		}
		else{
			oldpass=0;
			tooltipOldPass = "Please fill out this field";
			$('#oldpassimg').attr("src","img/wrong.png");
			$('#oldpassimg').prop("alt", "wrong");
			$('#oldpassimg').attr("hidden",true);
			$('[name="oldpass"]').attr('title', tooltipOldPass);
			$('#oldpassimg').attr('title', tooltipOldPass);
		}
	});
	$('[name="newpass"]').on('keyup keydown keypress',function(){
		var newpassword=$('[name="newpass"]').val();
		if(newpassword.length>0){
			$('.tooltiptext').slideUp(100);
		}
		else{
			$('.tooltiptext').slideDown(100);
			newpass=0;
			tooltipNewPass = "Please fill out this field";
			$('#newpassimg').attr("src","img/wrong.png");
			$('#newpassimg').prop("alt", "wrong");
			$('#newpassimg').attr("hidden",true);
			$('[name="newpass"]').attr('title', tooltipNewPass);
			$('#newpassimg').attr('title', tooltipNewPass);
		}
	});
	$('[name="OTPUser"]').on('focus blur mouseleave',function(){
		var OTPUser=$('[name="OTPUser"]').val();
		if(OTPUser.length==6){
			OTP=1;
			tooltipOTPUser = "Accepted";
			$('#OTPUserimg').attr("src","img/tick.png");
			$('#OTPUserimg').prop("alt", "tick");
			$('#OTPUserimg').attr("hidden",false);
			$('[name="OTPUser"]').attr('title', tooltipOTPUser);
			$('#OTPUserimg').attr('title', tooltipOTPUser);
		}
		else if(OTPUser.length<6 && OTPUser.length>0){
			OTP=0;
			tooltipOTPUser = "The OTP should be 6 digits long";
			$('#OTPUserimg').attr("src","img/wrong.png");
			$('#OTPUserimg').prop("alt", "wrong");
			$('#OTPUserimg').attr("hidden",false);
			$('[name="OTPUser"]').attr('title', tooltipOTPUser);
			$('#OTPUserimg').attr('title', tooltipOTPUser);
		}
		else{
			OTP=0;
			tooltipOTPUser = "Please fill out this field";
			$('#OTPUserimg').attr("src","img/wrong.png");
			$('#OTPUserimg').prop("alt", "wrong");
			$('#OTPUserimg').attr("hidden",true);
			$('[name="OTPUser"]').attr('title', tooltipOTPUser);
			$('#OTPUserimg').attr('title', tooltipOTPUser);
		}
	});
	$('[name="newpass"]').on('focus blur mouseleave',function(){
		var newpassword=$('[name="newpass"]').val();
		if(newpassword.length>0){console.log(newpassword);
			$.ajax({
				type: 'POST',
				data:{
					newPassword: newpassword,
					action: 'validNewPass'
				},
				url: 'ChangePassword',
				cache:false,
				success: function(result){
					if(result==1){
						newpass=1;
						tooltipNewPass = "Accepted";
						$('#newpassimg').attr("src","img/tick.png");
						$('#newpassimg').prop("alt", "tick");
						$('#newpassimg').attr("hidden",false);
					}
					else if(result==0){
						newpass=0;
						tooltipNewPass = "It must be between 8 to 15 characters long. It should atleast contain:\n1 lowercase character, 1 uppercase character,\n1 number/digit character, and\n1 a special case character out of ?!@$&%_.";
						$('#newpassimg').attr("src","img/wrong.png");
						$('#newpassimg').prop("alt", "wrong");
						$('#newpassimg').attr("hidden",false);
					}
					$('[name="newpass"]').attr('title', tooltipNewPass);
					$('#newpassimg').attr('title', tooltipNewPass);
				}
			});
		}
		else{
			newpass=0;
			tooltipNewPass = "Please fill out this field";
			$('#newpassimg').attr("src","img/wrong.png");
			$('#newpassimg').prop("alt", "wrong");
			$('#newpassimg').attr("hidden",true);
			$('[name="newpass"]').attr('title', tooltipNewPass);
			$('#newpassimg').attr('title', tooltipNewPass);
		}
	});
	$('[name="cnfnewpass"]').on('focus blur mouseleave',function(){
		var cnfnewpassword=$('[name="cnfnewpass"]').val();
		if(cnfnewpassword.length>0){
			$.ajax({
				type: 'POST',
				data:{
					newPassword: $('[name="newpass"]').val(),
					cnfNewPassword: cnfnewpassword,
					action: 'validSameCnfNewPass'
				},
				url: 'ChangePassword',
				cache:false,
				success: function(result){
					if(result==1){
						cnfnewpass=1;
						tooltipCnfNewPass = "Matching";
						$('#cnfnewpassimg').attr("src","img/tick.png");
						$('#cnfnewpassimg').prop("alt", "tick");
						$('#cnfnewpassimg').attr("hidden",false);
					}
					else if(result==0){
						cnfnewpass=0;
						tooltipCnfNewPass = "Passwords do not match";
						$('#cnfnewpassimg').attr("src","img/wrong.png");
						$('#cnfnewpassimg').prop("alt", "wrong");
						$('#cnfnewpassimg').attr("hidden",false);
					}
					$('[name="cnfnewpass"]').attr('title', tooltipCnfNewPass);
					$('#cnfnewpassimg').attr('title', tooltipCnfNewPass);
				}
			});
		}
		else{
			cnfnewpass=0;
			tooltipCnfNewPass = "Please fill out this field";
			$('#cnfnewpassimg').attr("src","img/wrong.png");
			$('#cnfnewpassimg').prop("alt", "wrong");
			$('#cnfnewpassimg').attr("hidden",true);
			$('[name="cnfnewpass"]').attr('title', tooltipCnfNewPass);
			$('#cnfnewpassimg').attr('title', tooltipCnfNewPass);
		}
	});
	$('#link span').click(function(){
		$('.five').fadeIn(100).delay(5500).fadeOut(500);
		$.ajax({
			type: "POST",
			data:{
				action: "Resend"
			},
			url: 'ChangePassword',
			cache: false,
			success: function(result){
				if(result==1){
					window.location.href="ChangePassword.jsp";
				}
				else
					alert("OOPS!! Something Went Wrong");
			}
		});
	});
	setInterval(Verify, 0);
	setInterval(Check, 0);
	setInterval(Change, 0);
	setInterval(Enable, 0);
	function Verify(){
		if(OTP==1){
			$('[name="btnVERIFY"]').addClass("grow");
			$('[name="btnVERIFY"]')[0].disabled=false;
		}
		else{
			$('[name="btnVERIFY"]').removeClass("grow");
			$('[name="btnVERIFY"]')[0].disabled=true;
		}
	}
	function Check(){
		if(oldpass==1){
			$('[name="btnCHECK"]').addClass("grow");
			$('[name="btnCHECK"]')[0].disabled=false;
		}
		else{
			$('[name="btnCHECK"]').removeClass("grow");
			$('[name="btnCHECK"]')[0].disabled=true;
		}
	}
	function Enable(){
		if(newpass==1){
			$('[name="cnfnewpass"]')[0].disabled=false;
		}
		else{
			$('[name="cnfnewpass"]')[0].disabled=true;
			$('[name="cnfnewpass"]').val("");
			$('#cnfnewpassimg').attr("src","img/wrong.png");
			$('#cnfnewpassimg').prop("alt", "wrong");
			$('#cnfnewpassimg').attr("hidden",true);
			$('[name="cnfnewpass"]').attr('title', "Please fill out this field");
			$('#cnfnewpassimg').attr('title', "Please fill out this field");
		}
	}
	function Change(){
		if(oldpass==1 && newpass==1){
			$('[name="btnCHANGE"]').addClass("grow");
			$('[name="btnCHANGE"]')[0].disabled=false;
		}
		else{
			$('[name="btnCHANGE"]').removeClass("grow");
			$('[name="btnCHANGE"]')[0].disabled=true;
		}
	}
	$('[name="btnVERIFY"]').click(function(){
		$('.five').fadeIn(100).delay(500).fadeOut(500);
		$.ajax({
			type: 'POST',
			data:{
				OTPUser: $('[name="OTPUser"]').val(),
				action: 'VerifyEmail'
			},
			url: 'ChangePassword',
			cache:false,
			success: function(result){
				setTimeout(function() {
					if(result==1){
						OTP=1;
						alert("Email Verified. Please Set a new Password");
						$('.tooltiptext').slideUp(0);
						$('.fourc2').fadeOut(200);
						$('#fourc5p1').fadeOut(200);
						$('.fourc3').fadeIn(200);
						$('#fourc5p2').delay(200).fadeIn(500);
						$('.tooltiptext').delay(1000).slideDown(500);
					}
					else{
						OTP=0;
						$('[name="OTPUser"]').val("");
						alert("Incorrect OTP!!. Email Not Verified.");
						tooltipOTPUser="Please Fill Out This Field";
						//tooltipOTPUser ="Attempts left: "+result/10;
						$('#OTPUserimg').attr("src","img/wrong.png");
						$('#OTPUserimg').prop("alt", "wrong");
						$('#OTPUserimg').attr("hidden",true);
						$('[name="OTPUser"]').val("");
						$('[name="OTPUser"]').attr('title', tooltipOTPUser);
						$('#OTPUserimg').attr('title', tooltipOTPUser);
					}
				}, 1000);
			}
		});
	});
	$('[name="btnCHECK"]').click(function(){
		$('.five').fadeIn(100).delay(500).fadeOut(500);
		$.ajax({
			type: 'POST',
			data:{
				oldPassword: $('[name="oldpass"]').val(),
				action: 'CheckOldPassword'
			},
			url: 'ChangePassword',
			cache:false,
			success: function(result){
				setTimeout(function() {
					if(result==1){
						check=1;
						alert("Password Matched. Please set a new password");
						$('.tooltiptext').slideUp(0);
						$('.fourc3').fadeOut(200);
						$('#fourc5p2').fadeOut(200);
						$('.fourc4').fadeIn(200);
						$('#fourc5p3').delay(200).fadeIn(500);
						$('.tooltiptext').delay(1000).slideDown(500);
					}
					else{
						check=0;
						oldpass=0;
						alert("Incorrect Password!!");
						tooltipOldPass="Please Fill Out This Field";
						//tooltipOldPass ="Attempts left: "+result/10;
						$('#oldpassimg').attr("src","img/wrong.png");
						$('#oldpassimg').prop("alt", "wrong");
						$('#oldpassimg').attr("hidden",true);
						$('[name="oldpass"]').val("");
						$('[name="oldpass"]').attr('title', tooltipOldPass);
						$('#oldpassimg').attr('title', tooltipOldPass);
					}
				}, 1000);
			}
		});
	});
	$('[name="btnCHANGE"]').click(function(){
		$('.five').fadeIn(100).delay(3500).fadeOut(500);
		var newpassword=$('[name="newpass"]').val();
		$.ajax({
			type: 'POST',
			data: {
				newPassword: newpassword,
				action: 'Change'
			},
			cache: false,
			url: 'ChangePassword',
			success: function(result){
				setTimeout(function() {
					if(result==1){
						alert("Password Set Successfully. Taking you now to Account Profile Window.");
						window.location.replace("AccountProfile.jsp");
					}
					else if(result==0){
						newpass=0;
						cnfnewpass=0;
						alert("OOPS!! Something Went Wrong. Could Not Change Password");
						tooltipNewPass="Please Fill Out This Field";
						tooltipCnfNewPass="Please Fill Out This Field";
						//tooltipOldPass ="Attempts left: "+result/10;
						$('#newpassimg').attr("src","img/wrong.png");
						$('#newpassimg').prop("alt", "wrong");
						$('#newpassimg').attr("hidden",true);
						$('[name="newpass"]').val("");
						$('[name="newpass"]').attr('title', tooltipNewPass);
						$('#newpassimg').attr('title', tooltipNewPass);
						$('#cnfnewpassimg').attr("src","img/wrong.png");
						$('#cnfnewpassimg').prop("alt", "wrong");
						$('#cnfnewpassimg').attr("hidden",true);
						$('[name="cnfnewpass"]').val("");
						$('[name="cnfnewpass"]').attr('title', tooltipCnfNewPass);
						$('#cnfnewpassimg').attr('title', tooltipCnfNewPass);
					}
				}, 2500);
			}
		});
	});
});