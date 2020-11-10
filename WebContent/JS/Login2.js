$(document).ready(function(){
	var pass,checked;
	pass=0;
	checked=0;
	
	$("#chooseRow td:nth-child(2)").click(function(){
		$("input[type='radio']").attr('checked', false);// jQuery version higher than 1.6
		$("input[type='radio']").prop("checked", false);// jQuery version 1.6 and lower
		$("#chooseimg").attr('src',"");
	  	checked=0;
	  	$('#three').slideDown(500);
	});
	$('[name="pass"]').on('keyup keydown keypress',function(){
		var tooltipPass;
		var password=$('[name="pass"]').val();
		if(password.length>=8){
			pass=1;
			tooltipPass = "Accepted";
			$('#passimg').attr("src","img/tick.png");
			$('#passimg').prop("alt", "tick");
			$('#passimg').attr("hidden",false);
		}
		else if(password.length>0){
			pass=0;
			tooltipPass = "It must be between 8 to 15 characters long. It should atleast contain:\n1 lowercase character, 1 uppercase character,\n1 number/digit character, and\n1 a special case character out of ?!@$&%_.";
			$('#passimg').attr("src","img/wrong.png");
			$('#passimg').prop("alt", "wrong");
			$('#passimg').attr("hidden",false);
		}
		else{
			pass=0;
			tooltipPass = "Please fill out this field";
			$('#passimg').attr("src","img/wrong.png");
			$('#passimg').prop("alt", "wrong");
			$('#passimg').attr("hidden",true);
		}
		$('[name="pass"]').attr('title', tooltipPass);
		$('#passimg').attr('title', tooltipPass);
	});
	/*$('[name="pass"]').on('focus blur mouseleave',function(){
		var tooltipPass;
		var password=$('[name="pass"]').val();
		if(password.length>0){
			$.ajax({
				type: 'POST',
				data:{
					passWord: password,
					action: 'validPass'
				},
				url: 'PassAuthentication',
				cache:false,
				success: function(result){
					if(result==1){
						pass=1;
						tooltipPass = "Accepted";
						$('#passimg').attr("src","img/tick.png");
						$('#passimg').prop("alt", "tick");
						$('#passimg').attr("hidden",false);
					}
					else if(result==0){
						pass=0;
						tooltipPass = "It must be between 8 to 15 characters long. It should atleast contain:\n1 lowercase character, 1 uppercase character,\n1 number/digit character, and\n1 a special case character out of ?!@$&%_.";
						$('#passimg').attr("src","img/wrong.png");
						$('#passimg').prop("alt", "wrong");
						$('#passimg').attr("hidden",false);
					}
					$('[name="pass"]').attr('title', tooltipPass);
					$('#passimg').attr('title', tooltipPass);
				}
			});
		}
		else{
			pass=0;
			tooltipPass = "Please fill out this field";
			$('#passimg').attr("src","img/wrong.png");
			$('#passimg').prop("alt", "wrong");
			$('#passimg').attr("hidden",true);
			$('[name="pass"]').attr('title', tooltipPass);
			$('#passimg').attr('title', tooltipPass);
		}
	});*/
	$('#three').on('click','[id^=imge]', function() {
		console.log("Working. Image selected");
        var effect = this.id.split("imge")[1];
        console.log("effect "+effect);
        $("#radioe"+effect).attr('checked', 'checked');
	  	$("#radioe"+effect).prop("checked", true);
	  	$('#three').delay(500).fadeOut(500);
	  	checked=1;
	  	$('#chooseradio').attr('checked', 'checked');
	  	$('#chooseradio').prop("checked", true);
	  	$('#chooseradio').val($('input[name=picEffect]:checked').val()+","+effect);
	  	/*var res=$('#chooseradio').val();
	  	$("input[name=picEffect][value="+res+"]").attr('checked', true);
	    var hell=$("input[name=picEffect][value="+res+"]").attr('id');
	    console.log(hell);*/
	  	var source=$("#imge"+effect).attr('src');
	  	$('#chooseimg').attr('src',source);
	});
	
	$("#cancel").click(function(){
		$('#three').fadeOut(500);
	});
	
	$('#threep5').on('click', function() {
		$('.four').fadeIn(200).delay(200).fadeOut(200);
		$.ajax({
			type: 'POST',
			data: {
				action: "Refresh",
				uname: $('[name="uname"]').val()
			},
			url: 'LoginImages',
			cache:false,
			success: function (refresh){
				console.log("refresh "+refresh);
				refresh=parseInt(refresh);
				$("input[type='radio']").attr('checked', 'checked');// jQuery version higher than 1.6
				$("input[type='radio']").prop("checked", false);// jQuery version 1.6 and lower
			  	checked=0;
			  	
			  	/*Here for now, hide and show but it can be made scrolling effect
			  	 * which reverts back quickly to original position when no refresh left
			  	 * 
			  	 * */
			  	if(refresh==0){
			  		$('#threep4').slideUp(500);
				  	$('#threep1').slideDown(500);
			  	}else{
			  		$('#threep'+refresh).slideUp(500);
			  		$('#threep'+(refresh+1)).slideDown(500);
			  	}
			  	/*for(j=1;j<4;j++){
			  		if(refresh==0){
			  			console.log("Here if");
			  			$('#imge'+(3*refresh-j+12)).slideUp(500);
			  		}else{
			  			console.log("Here else");
			  			$('#imge'+(3*refresh-j)).slideUp(500);
			  		}
			  	}
			  	for(j=0;j<3;j++){
			  		console.log("Here down "+j);
			  		$('#imge'+(3*refresh+j)).delay(500).show(500);
			  	}*/
			}
		});
	});
	
	setInterval(Login, 100);
	setInterval(DisplayPassword, 100);
	function Login(){
		if(pass==1 && checked==1){
			$('[name="btnLOGIN"]').addClass("grow");
			$('[name="btnLOGIN"]')[0].disabled=false;
		}
		else{
			$('[name="btnLOGIN"]').removeClass("grow");
			$('[name="btnLOGIN"]')[0].disabled=true;
		}
	}
	function DisplayPassword(){
		if(checked==1){
			$('.afterPic').slideDown(500);
		}
		else{
			$('.afterPic').slideUp(500);
		}
	}
	
	$("#link a:nth-child(1)").click(function(){
		$.ajax({
			type: 'POST',
			data: {
				uname:$('[name="uname"]').val(),
				action: "forgotPassword"
			},
			url: 'ForgotPassword',
			cache:false
		});
	});
	$("#link a:nth-child(2)").click(function(){
		$.ajax({
			type: 'POST',
			data: {
				uname:$('[name="uname"]').val(),
				action: "forgotPicture"
			},
			url: 'ForgotPicture',
			cache:false
		});
	});
	
	$('[name="btnLOGIN"]').click(function(){
		$('[name="subtime2"]').val(getTimeStamp());
		$('.four').fadeIn(100);
  	});
	
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