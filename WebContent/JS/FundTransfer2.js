var amountType = 0;
var amount;
var tooltipAmount;
$(document).ready(function(){
	amount=0;
	$('.five').fadeOut(0);
	$('input').keypress(function(e){
		if(e.which==32)
			return false;
	});
	
	var regExpNUM = /[0-9]/;
	  $('[name="amount"]').on('keydown keyup blur focus', function(e) {

	    var value =e.key;
	    /*var ascii=value.charCodeAt(0);
	    $('textarea').append(ascii);
	    $('textarea').append(value);
	    console.log(e);*/
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
	  $('[name="amount"]').on('keypress', function(e) {
		  if($('[name="amount"]').val().length >5){
		    	e.preventDefault();
		  }
	  });
	  $('input').on('keyup keydown keypress', function() {
			var name = $(this).attr("name");
			if (name == "amount") {
				amountType = 1;
				amount = 0;
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
	  $('[name="amount"]').on('keyup focus blur mouseleave', function() {
		  if (amountType == 0) {
			  return false;
		  }
		  amountType=0;
		  var amt=$('[name="amount"]').val(); 
		  if(amt.length >0){
			  var amt1=parseInt(amt);
			  if(amt1>=100 && amt1<=500000){
				  amount=1;
				  tooltipAmount = "Accepted";
					$('#amountimg').attr("src", "img/tick.png");
					$('#amountimg').prop("alt", "tick");
					$('#amountimg').attr("hidden", false);
			  }else{
				  amount=0;
				  tooltipAmount = "Amount must be greater than 100 and less than transfer limit";
					$('#amountimg').attr("src", "img/wrong.png");
					$('#amountimg').prop("alt", "wrong");
					$('#amountimg').attr("hidden", false);
			  }
			  $('[name="amount"]').attr('title', tooltipAmount);
			  $('#amountimg').attr('title', tooltipAmount);
		  }
		  else{
			  amount=0;
			  tooltipAmount = "Please fill out this field";
				$('#amountimg').attr("src", "img/wrong.png");
				$('#amountimg').prop("alt", "wrong");
				$('#amountimg').attr("hidden", true);
				$('[name="amount"]').attr('title', tooltipUname);
				$('#amountimg').attr('title', tooltipUname);
		  }
	  });
	  
	  $('[name="btnNEXT"]').click(function() {
			$('[name="subtime2"]').val(getTimeStamp());
			$('.five').fadeIn(500);
			$('form').delay(500).submit();
	  });
	  
	setInterval(Check, 250);
	function Check(){
		if(amount==1){
			$('[name="btnNEXT"]').addClass("grow");
			$('[name="btnNEXT"]')[0].disabled=false;
		}
		else if(amount==0){
			$('[name="btnNEXT"]').removeClass("grow");
			$('[name="btnNEXT"]')[0].disabled=true;
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