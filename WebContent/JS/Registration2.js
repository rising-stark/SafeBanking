$(document).ready(function(){
	var left,right;
	left=0;right=1;
	
	setInterval(Check, 100);
	function Check(){
		if(left==0)
			$(".fa-arrow-circle-left").css("color", "grey");
		else
			$(".fa-arrow-circle-left").css("color", "green");
		if(right==0)
			$(".fa-arrow-circle-right").css("color", "grey");
		else
			$(".fa-arrow-circle-right").css("color", "green");
	}
	
	setInterval(Register, 500);
	
	function Register(){
		if(($('[name="picids"]').is(':checked')) && ($('[name="piceffect"]').is(':checked'))){
			$('[name="btnREGISTER"]').addClass("grow");
			$('[name="btnREGISTER"]')[0].disabled=false;
		}
		else{
			$('[name="btnREGISTER"]').removeClass("grow");
			$('[name="btnREGISTER"]')[0].disabled=true;
		}
	}

	$(".fa-arrow-circle-right").click(function(){
		if(right!=0){
			right=right-1;
			left=left+1;
			console.log("In Right ="+right+" Left="+left);
			$('#twop2').css({"left": "calc(-100% * "+left+")"});
		}
	});
	$(".fa-arrow-circle-left").click(function(){
		if(left!=0){
			left=left-1;
			right=right+1;
			console.log("Right ="+right+" In Left="+left);
			$('#twop2').css({"left": "calc(-100% * "+left+")"});
		}
	});
	$('#twop2').on('click','[id^=imgp]', function() {//^="starts with selector"
		//console.log("Working");
        var num = this.id.split("imgp")[1];
        $("#cancel").show();
        console.log("num "+num);// returns our number from the id!
        // from now on we can use our var "num" :
        $('input[type="radio"]').prop("checked", false);
        $("#radiop"+num).prop("checked", true);
	  	$('#imge1').attr('src','Registration2?picid='+num+'&effectid=1');
	  	$('#imge2').attr('src','Registration2?picid='+num+'&effectid=2');
	  	$('#imge3').attr('src','Registration2?picid='+num+'&effectid=3');
	  	$('#imge4').attr('src','Registration2?picid='+num+'&effectid=4');
	  	$('#imge5').attr('src','Registration2?picid='+num+'&effectid=5');
	  	$('#four').delay(500).slideDown(500);
    });
	$('#four').on('click','[id^=imge]', function() {
		//console.log("Working");
        var effect = this.id.split("imge")[1];
        console.log("effect "+effect);
        $("#radioe"+effect).prop("checked", true);
	  	$.ajax({
	  		type: 'POST',
	  		url: 'Registration2',
	  		data:{
	  			position: effect,
	  			action: 'setPosition'
	  		},
	  		cache: false
	  	});
	  	$('#four').delay(500).fadeOut(500);
	  	$("#cancel").hide();
	});
	$("#cancel").click(function(){
		console.log("Working");
		$('#four').fadeOut(500);
		$('input[type="radio"]').prop("checked", false);
	});
	$(".register").click(function(){
		$('[name="subtime2"]').val(getTimeStamp());
		$('.five').fadeIn(500);
		$('form').delay(500).submit();
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