function emailVerificationComplete(){
	$('.three').slideUp(750);
	$('.four').delay(750).fadeIn(100);
	$('#fourp4').delay(750).fadeIn(100);
	$('.tooltiptext').slideUp(0);
	$('#twoc3p2').fadeOut(0);
	$('#twoc3p3').fadeIn(0);
	$.ajax({
		type: 'POST',
		url: 'EmailVerify',
		data: {
			action: 'emailVerificationComplete'
		},
		cache: false,
		success: function(result){
			if(result==1){
				console.log("Here time after sending email");
				$('.four').fadeOut(500);
				redirect();
			}else{
				alert("Some error occured. While sending email.")
			}
		}
	});
}
function emailForgotInitiated(){
	$.ajax({
		type: 'POST',
		url: 'ForgotPassword',
		data: {
			action: 'emailForgotInitiated',
			forgotpagetime: $('[name="forgotpagetime"]').val()
		},
		cache: false,
		success: function(result){
			if(result==1){
				console.log("Email sent successfully for emailForgotInitiated");
			}else{
				var msg="Error in sending email for emailForgotInitiated";
				alert(msg)
				console.log(msg);
			}
		}
	});
}

function redirect(){
	window.location.href="Welcome.jsp";
}

var newPassType=0,oldPassType=0,securityAnswerType=0,cnfNewPassType=0;
var oldpass,newpass,cnfnewpass,verify,securityquestion,securityanswer;
var tooltipSecurityQuestion, tooltipNewPass, tooltipOldPass, tooltipSecurityAnswer, tooltipCnfNewPass;
oldpass=0;
newpass=0;
cnfnewpass=0;
verify=0;
securityquestion=0;
securityanswer=0;

$(document).ready(function(){
	$('input:not(#securityanswer)').keypress(function(e){
		if(e.which==32)
			return false;
	});
	$('[name="securityanswer"]').on('keypress', function(e) {
		if($('[name="securityanswer"]').val().length >39){
			e.preventDefault();
		}
	});
	$('input').on('keyup keydown keypress', function() {
		  var name = $(this).attr("name");
		  if(name=="newpass"){
			  newPassType=1;
			  newpass=0;
		  }
		  else if(name=="oldpass"){
			  oldPassType=1;
			  oldpass=0;
		  }
		  else if(name=="cnfnewpass"){
			  cnfNewPassType=1;
			  cnfnewpass=0;
		  }
		  else if(name=="securityanswer"){
			  securityAnswerType=1;
			  securityanswer=0;
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
	$('#securityquestion').click(function(){
		  var a=$('select[name="securityquestion"] option:selected').text();
		  //console.log(a);
		  if(a=='---- Choose Your Security Question ----' || a==null){
			  securityquestion=0;
			  tooltipSecurityQuestion = "Please Choose An Option";
			  $('#securityquestionimg').attr("src","img/wrong.png");
			  $('#securityquestionimg').prop("alt", "wrong");
			  $('#securityquestionimg').attr("hidden",true);
		  }
		  else if(a!=null){
			  securityquestion=1;
			  //console.log(a);
			  tooltipSecurityQuestion = "Selected";
			  $('#securityquestionimg').attr("src","img/tick.png");
			  $('#securityquestionimg').prop("alt", "tick");
			  $('#securityquestionimg').attr("hidden",false);
		  }
		  $('[name="securityquestion"]').attr('title', tooltipSecurityQuestion);
		  $('#securityquestion').attr('title', tooltipSecurityQuestion);
	  });
	$('[name="newpass"]').on('keyup keydown keypress',function(){
		var tooltipNewPass;
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
	$('[name="oldpass"],.twoc3').on('mousemove',function(){
		//console.log("a");
		var tooltipOldPass;
		var oldpassword=$('[name="oldpass"]').val();
		if(oldpassword.length>=8){
			oldpass=1;
			tooltipOldPass = "Accepted";
		}
		else if(oldpassword.length>0){
			oldpass=0;
			tooltipOldPass = "It must be between 8 to 15 characters long. It should atleast contain:\n1 lowercase character, 1 uppercase character,\n1 number/digit character, and\n1 a special case character out of ?!@$&%_.";
		}
		else{
			oldpass=0;
			tooltipOldPass = "Please fill out this field";
		}
		$('[name="oldpass"]').attr('title', tooltipOldPass);
	});
	/*$('[name="oldpass"]').on('focus blur mouseleave',function(){
		var oldpassword=$('[name="oldpass"]').val();
		if(oldpassword.length>0){
			$.ajax({
				type: 'POST',
				data:{
					oldPassword: oldpassword,
					action: 'validOldPass'
				},
				url: 'EmailVerify',
				cache:false,
				success: function(result){
					if(result==2){
						oldpass=1;
						tooltipOldPass = "Accepted";
						$('#oldpassimg').attr("src","img/tick.png");
						$('#oldpassimg').prop("alt", "tick");
						$('#oldpassimg').attr("hidden",false);
					}
					else if(result==1){
						oldpass=0;
						tooltipOldPass ="It must be between 8 to 15 characters long. It should atleast contain:\n1 lowercase character, 1 uppercase character,\n1 number/digit character, and\n1 a special case character out of ?!@$&%_.";
						$('#oldpassimg').attr("src","img/wrong.png");
						$('#oldpassimg').prop("alt", "wrong");
						$('#oldpassimg').attr("hidden",false);
					}
					else{
						oldpass=0;
						//tooltipOldPass ="Attempts left: "+result/10;
						$('#oldpassimg').attr("src","img/wrong.png");
						$('#oldpassimg').prop("alt", "wrong");
						$('#oldpassimg').attr("hidden",false);
					}
					$('[name="oldpass"]').attr('title', tooltipOldPass);
					$('#oldpassimg').attr('title', tooltipOldPass);
				}
			});
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
	});*/
	$('[name="newpass"]').on('focus blur mouseleave',function(){
		if(newPassType==0){
			return false;
		}
		newPassType=0;
		var newPassword=$('[name="newpass"]').val();
		if(newPassword.length>0){
			$.ajax({
				type: 'POST',
				url: 'EmailVerify',
				data:{
					newPassword: newPassword,
					action: 'validSameNewPass'
				},
				cache:false,
				success: function(result){
					if(result==2){
						newpass=1;
						tooltipNewPass = "Accepted";
						$('#newpassimg').attr("src","img/tick.png");
						$('#newpassimg').prop("alt", "tick");
						$('#newpassimg').attr("hidden",false);
					}
					else if(result==1){
						newpass=0;
						tooltipNewPass = "It must be between 8 to 15 characters long. It should atleast contain:\n1 lowercase character, 1 uppercase character,\n1 number/digit character, and\n1 a special case character out of ?!@$&%_.";
						$('#newpassimg').attr("src","img/wrong.png");
						$('#newpassimg').prop("alt", "wrong");
						$('#newpassimg').attr("hidden",false);
					}
					else{
						newpass=0;
						tooltipNewPass = "The password in the email cannot be set as your account login password";
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
		if(cnfNewPassType==0){
			return false;
		}
		cnfNewPassType=0;
		var cnfnewpassword=$('[name="cnfnewpass"]').val();
		if(cnfnewpassword.length>0){
			$.ajax({
				type: 'POST',
				url: 'EmailVerify',
				data:{
					newPassword: $('[name="newpass"]').val(),
					cnfNewPassword: cnfnewpassword,
					action: 'validSameCnfNewPass'
				},
				cache:false,
				success: function(result){
					if(result==2){
						cnfnewpass=1;
						tooltipCnfNewPass = "Matching";
						$('#cnfnewpassimg').attr("src","img/tick.png");
						$('#cnfnewpassimg').prop("alt", "tick");
						$('#cnfnewpassimg').attr("hidden",false);
					}
					else if(result==1){
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
	$('[name="securityanswer"]').on('focus blur mouseleave',function(){
		  if(securityAnswerType==0){
			  return false;
		  }
		  securityAnswerType=0;//console.log("a= "+a+"securityanswer field");
		  if($('[name="securityanswer"]').val().length > 1){
			  securityanswer=1;
			  tooltipSecurityAnswer="Accepted";
			  $('#securityanswerimg').attr("src","img/tick.png");
			  $('#securityanswerimg').prop("alt", "tick");
			  $('#securityanswerimg').attr("hidden",false);
		  }
		else if($('[name="securityanswer"]').val().length > 0){
			securityanswer=0;
			tooltipSecurityAnswer="Atleast 2 characters";
			$('#securityanswerimg').attr("src","img/wrong.png");
			$('#securityanswerimg').prop("alt", "wrong");
			$('#securityanswerimg').attr("hidden",false);
		}
		  else{
				securityanswer=0;
				tooltipSecurityAnswer="Please fill out this field";
				$('#securityanswerimg').attr("src","img/wrong.png");
				$('#securityanswerimg').prop("alt", "wrong");
				$('#securityanswerimg').attr("hidden",true);
			} 
		  $('[name="securityanswer"]').attr('title', tooltipSecurityAnswer);
		  $('#securityanswerimg').attr('title', tooltipSecurityAnswer);
	  });
	$('#fourp2p1').click(function(){
		$('.four').fadeOut(500);
		$('#fourp2').fadeOut(500);
		$('.tooltiptext').slideUp(0);
		$('#twoc3p2').fadeOut(500);
		$('#twoc3p3').fadeIn(1000);
		$('.tooltiptext').delay(1000).slideDown(500);
	});
	
	$('#fourp3p1').click(function(){
		$('.four').fadeOut(500);
		$('#fourp3').fadeOut(500);
	});
	$("#fourp4p1").click(function(){
		$('.four').fadeOut(500);
		$('#fourp4').fadeOut(500);
		redirect();
	});
	
	$('#fourp1p1').click(function(){
		$('.four').fadeOut(500);
		$('#fourp1').fadeOut(500);
	});
	$('[name="btnVERIFY"]').click(function(){
		$('[name="verifytime"]').val(getTimeStamp());
	 	$('.three').fadeIn(500);
		$.ajax({
			type: 'POST',
			url: 'EmailVerify',
			data: {
				emailverifypagetime: $('[name="emailverifypagetime"]').val(),
				oldpass: $('[name="oldpass"]').val(),
				oldpasstime: $('[name="oldpasstime"]').val(),
				verifytime: $('[name="verifytime"]').val(),
				action: 'verifyEmail'
			},
			cache: false,
			success: function(result){
				$('.three').slideUp(500);
				if(result==1){
					verify=1;
					oldpass=1;
					$('.four').delay(750).fadeIn(200);
					$('#fourp2').fadeIn(100);
				}
				else if(result==0){
					verify=0;
					oldpass=0;
					$('.four').delay(750).fadeIn(200);
					$('#fourp3').fadeIn(100);
					tooltipOldPass="Incorrect Password";
					//tooltipOldPass ="Attempts left: "+result/10;
					//$('#oldpassimg').attr("src","img/wrong.png");
					//$('#oldpassimg').prop("alt", "wrong");
					//$('#oldpassimg').attr("hidden",true);
					$('[name="oldpass"]').val("");
					$('[name="oldpasstime"]').val("0");
					$('[name="verifytime"]').val("0");
					$('[name="oldpass"]').attr('title', tooltipOldPass);
					$('#oldpassimg').attr('title', tooltipOldPass);
				}
			}
		});
	});
	$('[name="btnCHANGE"]').click(function(){
		$('[name="subtime"]').val(getTimeStamp());
		$('.three').fadeIn(500);
		$('form').delay(500).submit();
	});
	
	setInterval(Verify, 500);
	setInterval(Change, 500);
	setInterval(Enable, 500);
	function Verify(){
		if(oldpass==1){
			$('[name="btnVERIFY"]').addClass("grow");
			$('[name="btnVERIFY"]')[0].disabled=false;
		}
		else{
			$('[name="btnVERIFY"]').removeClass("grow");
			$('[name="btnVERIFY"]')[0].disabled=true;
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
		if(verify==1 && newpass==1 && cnfnewpass==1 && securityquestion==1 && securityanswer==1){
			$('[name="btnCHANGE"]').addClass("grow");
			$('[name="btnCHANGE"]')[0].disabled=false;
		}
		else{
			$('[name="btnCHANGE"]').removeClass("grow");
			$('[name="btnCHANGE"]')[0].disabled=true;
		}
	}
	
	function getTimeStamp() {
		var now = new Date();
		var year, month, day, hours, minutes, sec, msec;
		year = now.getFullYear();
		month = now.getMonth() + 1;
		day = now.getDate();
		hours = now.getHours();
		minutes = now.getMinutes();
		sec = now.getSeconds();
		msec = now.getMilliseconds();
		return (((day < 10) ? ("0" + day) : day) + "-" +
			((month < 10) ? ("0" + month) : month) + "-" +
			year + " " +
			((hours < 10) ? ("0" + hours) : hours) + ":" +
			((minutes < 10) ? ("0" + minutes) : minutes) + ":" +
			((sec < 10) ? ("0" + sec) : sec) + ":" +
			((msec < 100) ? ((msec < 10) ? ("00" + msec) : ("0" + msec)) : msec));
	}
});