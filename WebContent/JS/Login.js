function sendEmail(){
	$('.four').fadeIn(1000);
	$('#fourp1').fadeIn(100);
	$.ajax({
		type: 'POST',
		url: 'Login',
		data: {
			action: 'sendEmail'
		},
		cache: false,
		success: function(result){
			if(result==1){
				setTimeout(function (){
					$('.four').fadeOut(500);
					redirect();
				}, 5000);
			}
		}
	});
}

function redirect(){
	window.location.href="EmailVerify.jsp";
}

var unameType=0, codeType=0, securityAnswerType=0;
var uname, code, securityquestion, securityanswer;
var tooltipUname, tooltipCode, tooltipSecurityQuestion, tooltipSecurityAnswer;
uname=0
code=0;
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
	
	$('[name="uname"]').keypress(function(e){
		if($('[name="uname"]').val().length >14){
			e.preventDefault();
			return false;
		}
	});
	
	var regExpCode = /[0-9]/;
	$('[name="code"]').on('keydown keyup blur focus', function(e) {
		var value =e.key;
		/*var ascii=value.charCodeAt(0);
		$('textarea').append(ascii);
		$('textarea').append(value);
		console.log(e);*/
		// Only numbers
		if (!regExpCode.test(value)
		  && e.which != 8   // backspace
		  && e.which != 46  // delete
		  && (e.which < 37  // arrow keys
			|| e.which > 40)) {
			  e.preventDefault();
			  return false;
		}
	});
	$('[name="code"]').on('keypress', function(e) {
		  if($('[name="code"]').val().length >5){
				e.preventDefault();
		  }
	});
	
	$('input').on('keyup keydown keypress', function() {
		var name = $(this).attr("name");
		  if(name=="uname"){
			  unameType=1;
			  uname=0;
		  }
		  else if(name=="code"){
			  codeType=1;
			  code=0;
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
	
	$('[name="code"]').on('keyup focus blur mouseleave',function(){
		if(codeType==0){
			  return false;
		  }
		codeType=0;
		var rancode=$('[name="code"]').val();
		if(rancode.length==6){
			code=1;
			tooltipCode = "Accepted";
			$('#codeimg').attr("src","img/tick.png");
			$('#codeimg').prop("alt", "tick");
			$('#codeimg').attr("hidden",false);
		}
		else if(rancode.length>0){
			code=0;
			tooltipCode = "The random code should be 6 digits long";
			$('#codeimg').attr("src","img/wrong.png");
			$('#codeimg').prop("alt", "wrong");
			$('#codeimg').attr("hidden",false);
		}
		else{
			code=0;
			tooltipCode = "Please fill out this field";
			$('#codeimg').attr("src","img/wrong.png");
			$('#codeimg').prop("alt", "wrong");
			$('#codeimg').attr("hidden",true);
		}
		$('[name="code"]').attr('title', tooltipCode);
		$('#codeimg').attr('title', tooltipCode);
	});
	$('[name="uname"]').on('focus blur mouseleave',function(){
		if(unameType==0){
			  return false;
		  }
		  unameType=0;//console.log("c= "+c+"Uname field");
		var username=$('[name="uname"]').val();
		if(username.length>=8){
			uname=1;
			tooltipUname = "Accepted";
			$('#unameimg').attr("src","img/tick.png");
			$('#unameimg').prop("alt", "tick");
			$('#unameimg').attr("hidden",true);
		}
		else if(username.length>0){
			uname=0;
			tooltipUname = "The username should be 8-15 characters long";
			$('#unameimg').attr("src","img/wrong.png");
			$('#unameimg').prop("alt", "wrong");
			$('#unameimg').attr("hidden",true);
		}
		else{
			uname=0;
			tooltipUname = "Please fill out this field";
			$('#unameimg').attr("src","img/wrong.png");
			$('#unameimg').prop("alt", "wrong");
			$('#unameimg').attr("hidden",true);
		}
		$('[name="uname"]').attr('title', tooltipUname);
		$('#unameimg').attr('title', tooltipUname);
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
	
	setInterval(Check, 250);
	setInterval(Verify, 250);
	function Check(){
		/*console.log("uname "+uname);
		console.log("code "+code);*/
		if(uname==1 && code==1){
			$('[name="btnNEXT"]').addClass("grow");
			$('[name="btnNEXT"]')[0].disabled=false;
		}
		else if(uname==0 || code==0){
			$('[name="btnNEXT"]').removeClass("grow");
			$('[name="btnNEXT"]')[0].disabled=true;
		}
	}
	function Verify(){
		if(securityquestion==1 && securityanswer==1){
			$('[name="btnVERIFY"]').addClass("grow");
			$('[name="btnVERIFY"]')[0].disabled=false;
		}
		else{
			$('[name="btnVERIFY"]').removeClass("grow");
			$('[name="btnVERIFY"]')[0].disabled=true;
		}
	}
	/*$('[name="btnNEXT"]').click(function(){
		$('.three').fadeIn(100).delay(2500).fadeOut(500);
		$.ajax({
			type: 'POST',
			data:{
				username: $('[name="uname"]').val(),
				action: 'verifiedUname'
			},
			url: 'Login',
			cache:false,
			success: function(result){console.log(result);
				if(result==0){
					$('.three').fadeIn(100).delay(5500).fadeOut(500);
					setTimeout(alert("The username "+username+" has not verified its email. Redirecting to verify the email id"),5000);
					window.location.replace("EmailVerify.jsp");
				}
			}
		});
	});*/
	
	$('[name="btnNEXT"]').click(function(){
		$('[name="subtime1"]').val(getTimeStamp());
		$('.three').fadeIn(500);
		$('form').delay(500).submit();
	});
	$('[name="btnVERIFY"]').click(function(){
		$('[name="verifytime"]').val(getTimeStamp());
		$('.three').fadeIn(100);
		$.ajax({
			type: 'POST',
			url: 'Login',
			data: {
				securitypagetime: $('[name="securitypagetime"]').val(),
				securityquestion: $('[name="securityquestion"]').val(),
				securityanswer: $('[name="securityanswer"]').val(),
				securityquestime: $('[name="securityquestime"]').val(),
				securityanstime: $('[name="securityanstime"]').val(),
				verifytime: $('[name="verifytime"]').val(),
				action: 'verifySecurity'
			},
			cache: false,
			success: function(result){
				setTimeout(function(){
					$('.three').fadeOut(500);
					if(result==1){
						$('.four').fadeOut(100);
						window.location.href="Login2.jsp";
					}
					else if(result==0){
						securityquestion=0;
						securityanswer=0;
						$('#fourp2').fadeIn(100).delay(3000).fadeOut(500);
						$('#fourp2p1').fadeOut(100).delay(3000).fadeIn(500);
						$('#fourp2p2').fadeIn(100).delay(3000).fadeOut(500);
						tooltipSecurityQuestion = "Please Choose An Option";
						tooltipSecurityAnswer="Please fill out this field";
						$('[name="securityanswer"]').val("");
						$('select[name="securityquestion"] option:selected').attr("selected", null);
						$('[name="securityquestime"]').val("0");
						$('[name="securityanstime"]').val("0");
						$('[name="verifytime"]').val("0");
						$('[name="securityquestion"]').attr('title', tooltipSecurityQuestion);
						$('[name="securityanswer"]').attr('title', tooltipSecurityAnswer);
						
						//tooltipOldPass ="Attempts left: "+result/10;
						//$('#oldpassimg').attr("src","img/wrong.png");
						//$('#oldpassimg').prop("alt", "wrong");
						//$('#oldpassimg').attr("hidden",true);
						/*$('[name="oldpasstime"]').val("");
						$('[name="verifytime"]').val("");*/
						//$('#oldpassimg').attr('title', tooltipOldPass);
					}
				}, 1000);
			}
		});
	});
	
	$("#fourp2p1").click(function(){
		console.log("Working here");
		$('.four').fadeOut(500);
	});
	$("#fourp2p2").click(function(){
		console.log("Working here");
		$('#fourp2').fadeOut(500);
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