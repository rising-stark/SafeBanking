var fnameType = 0,
	lnameType = 0,
	unameType = 0,
	emailType = 0,
	accType = 0,
	phoneType = 0,
	addType = 0;
var fname, lname, uname, email, phone, address, chkbox, acc;
var tooltipFname, tooltipEmail, tooltipLname, tooltipUname, tooltipAcc, tooltipPhone, tooltipAddress;

function backbutton(){
	console.log("hello");
}

$(document).ready(function() {
	fname = 0;
	lname = 0;
	uname = 0;
	acc = 0;
	email = 0;
	phone = 0;
	chkbox = 0;
	address = 1;
	$('.five').fadeOut(0);
	
	$('[name="btnRESET"]').click(function() {
		$('[name="resettime"]').val(getTimeStamp());
		console.log($('[name="resettime"]').val());
		fname = 0;
		lname = 0;
		uname = 0;
		acc = 0;
		email = 0;
		phone = 0;
		chkbox = 0;
		address = 1;
		$.ajax({
			type: 'POST',
			url: 'Registration1',
			data: {
				action: 'resetButton',
				uname: $('[name="uname"]').val(),
				acc: $('[name="acc"]').val(),
				fname: $('[name="fname"]').val(),
				lname: $('[name="lname"]').val(),
				email: $('[name="email"]').val(),
				phone: $('[name="phone"]').val(),
				address: $('[name="address"]').val(),
				
				regpage1time: $('[name="regpage1time"]').val(),
				ftime: $('[name="ftime"]').val(),
				ltime: $('[name="ltime"]').val(),
				utime: $('[name="utime"]').val(),
				acctime: $('[name="acctime"]').val(),
				emailtime: $('[name="emailtime"]').val(),
				phonetime: $('[name="phonetime"]').val(),
				addresstime: $('[name="addresstime"]').val(),
				chktime: $('[name="chktime"]').val(),
				resettime: $('[name="resettime"]').val()
			},
			cache: false
		});
		
		var a = $('[name="regpage1time"]').val();
		$('input[type=text], textarea').val("");
		$('input[type=hidden]').val(0);
		$('[name="regpage1time"]').val(a);
		
		var idarray = ['#fname', '#lname', '#uname', '#acc', '#email', '#phone', '#address']
		for (i = 0; i < idarray.length; i++) {
			var tooltip = "Please fill out this field";
			$(idarray[i] + 'img').attr("src", "img/wrong.png");
			$(idarray[i] + 'img').prop("alt", "wrong");
			$(idarray[i] + 'img').attr("hidden", true);
			$(idarray[i]).attr('title', tooltip);
			var item = $(idarray[i] + 'img').parent().next().next().children("span")[0];
			$(item).hide(250);
			item = $(idarray[i] + 'img').parent().next().next().children("span")[1];
			$(item).hide(250);
			//$(idarray[i] + 'img').attr('title', tooltip);
		}
		$('[name="chkbox"]').prop("checked", false);
	});
	$('input').on('keypress', function(e) {
		if (e.which == 32)
			return false;
	});
	$('[name="uname"]').on('keypress', function(e) {
		if ($('[name="uname"]').val().length > 14) {
			e.preventDefault();
			return false;
		}
	});
	var regExpPhone = /[0-9]/;
	$('[name="acc"],[name="phone"]').on('keydown keyup blur focus', function(e) {
		var value = e.key;
		//var ascii=value.charCodeAt(0);
		//$('textarea').append(ascii);
		//$('textarea').append(value);
		//console.log(e);
		// Only numbers
		if ((!regExpPhone.test(value) &&
			e.which !=35 // end key
			&&
			e.which !=9 // tab key for keyboard focusing
			&&
			e.which != 8 // backspace
			&&
			e.which != 46 // delete
			&&
			(e.which < 37 // arrow keys
				||
				e.which > 40))) {
			e.preventDefault();
			return false;
		}
	});
	$('[name="phone"]').on('keypress', function(e) {
		if ($('[name="phone"]').val().length > 9) {
			e.preventDefault();
		}
	});
	$('[name="acc"]').on('keypress', function(e) {
		if ($('[name="acc"]').val().length > 13) {
			e.preventDefault();
		}
	});
	var regExpFname = /[a-zA-Z]/;
	$('[name="fname"],[name="lname"]').on('keydown keyup keypress', function(e) {
		var value = e.key;
		/*var ascii=value.charCodeAt(0);
		$('textarea').append(ascii);
		$('textarea').append(value);
		console.log(e);*/
		// Only numbers
		if (!regExpFname.test(value) &&
			e.which !=35 // end key
			&&
			e.which !=9 // tab key for keyboard focusing
			&&
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
	
	$('[name="fname"],[name="lname"]').on('keypress', function(e) {
		if ($(this).val().length > 39) {
			e.preventDefault();
		}
	});

	$('input').on('keydown keyup keypress', function() {
		var name = $(this).attr("name");
		var flag=0;
		var regExpAlpha = /[a-zA-Z]/;
		var regExpNum = /[0-9]/;
		if (name == "fname") {
			fnameType = 1;
			fname = 0;
		} else if (name == "lname") {
			lnameType = 1;
			lname = 0;
		} else if (name == "uname") {
			unameType = 1;
			uname = 0;
		} else if (name == "acc") {
			accType = 1;
			acc = 0;
		} else if (name == "phone") {
			phoneType = 1;
			phone = 0;
		} else if (name == "email") {
			emailType = 1;
			email = 0;
		} else if (name == "address") {
			addType = 1;
			address = 1;
		}
		if ($(this).val().length == 0) {
			flag=1;
			var displayTooltip = "Please fill out this field";
			$(this).attr('title', displayTooltip);
			$('#' + name + 'img').attr("src", "img/wrong.png");
			$('#' + name + 'img').prop("alt", "wrong");
			/*var item = $(this).parent().next().next().children("span")[0];
			item.innerHTML= displayTooltip;
			$(item).show();
			$(item).delay(1000).fadeOut(1000);*/
			$('#' + name + 'img').attr("hidden", true);
			//$('#' + name + 'img').attr('title', displayTooltip);
		}
		if(flag==0){
			$('#' + name + 'img').attr("hidden", true);
			var item = $(this).parent().next().next().children("span")[0];
			$(item).hide(250);
			item = $(this).parent().next().next().children("span")[1];
			$(item).hide(250);
		}
	});
	$('[name="chkbox"]').click(function() {
		if ($('[name="chkbox"]').is(":checked")) {
			console.log("Checked. Showing all other field values.");
			console.log("fname =" + fname);
			console.log("lname =" + lname);
			console.log("uname =" + uname);
			console.log("email =" + email);
			console.log("phone =" + phone);
			console.log("address =" + address);
			chkbox = 1;
		} else {
			console.log("Not Checked");
			chkbox = 0;
		}
	});
	
	$('[name="fname"]').on('focus blur mouseleave', function() {
		if (fnameType == 0) {
			return false;
		}
		fnameType = 0;
		fnamelen=$('[name="fname"]').val().length;
		if (fnamelen > 0) {
			var item;
			if (fnamelen<=40) {
				fname = 1;
				tooltipFname = "Accepted";
				$('#fnameimg').attr("src", "gifs/ver.gif");
				$('#fnameimg').prop("alt", "tick");
				$('#fnameimg').attr("hidden", false);
				item= $('#fnameimg').parent().next().children("span")[1];
			}else{
				fname = 0;
				tooltipFname = "Please keep the length of first name within 40 characters.";
				$('#fnameimg').attr("src", "img/wrong.png");
				$('#fnameimg').prop("alt", "wrong");
				$('#fnameimg').attr("hidden", false);
				item= $('#fnameimg').parent().next().children("span")[0];
			}
			item.innerHTML=tooltipFname;
			$('#fnameimg').click();
		} else {
			fname = 0;
			tooltipFname = "Please fill out this field";
			$('#fnameimg').attr("src", "img/wrong.png");
			$('#fnameimg').prop("alt", "wrong");
			$('#fnameimg').attr("hidden", true);
		}
		$('[name="fname"]').attr('title', tooltipFname);
		//$('#fnameimg').attr('title', tooltipFname);
	});
	
	$('[name="lname"]').on('focus blur mouseleave', function() {
		if (lnameType == 0) {
			return false;
		}
		lnameType = 0;
		lnamelen=$('[name="lname"]').val().length;
		if (lnamelen > 0) {
			var item;
			if (lnamelen<=40) {
				lname = 1;
				tooltipLname = "Accepted";
				$('#lnameimg').attr("src", "gifs/ver.gif");
				$('#lnameimg').prop("alt", "tick");
				$('#lnameimg').attr("hidden", false);
				item= $('#lnameimg').parent().next().children("span")[1];
			}else{
				lname = 0;
				tooltipLname = "Please keep the length of last name within 40 characters.";
				$('#lnameimg').attr("src", "img/wrong.png");
				$('#lnameimg').prop("alt", "wrong");
				$('#lnameimg').attr("hidden", false);
				item= $('#lnameimg').parent().next().children("span")[0];
			}
			item.innerHTML=tooltipLname;
			$('#lnameimg').click();
		} else {
			lname = 0;
			tooltipLname = "Please fill out this field";
			$('#lnameimg').attr("src", "img/wrong.png");
			$('#lnameimg').prop("alt", "wrong");
			$('#lnameimg').attr("hidden", true);
		}
		$('[name="lname"]').attr('title', tooltipLname);
		//$('#lnameimg').attr('title', tooltipLname);
	});
	
	/*
	 * Here, in AJAX of uname, if we start telling that this username has already been taken by just
	 * typing then it will be a source of correct usernames using a dictionary attack.
	 * That's why it is checked after the form is submitted.
	 * This way, the information of a wrong attempt will also be logged. 
	 * */
	
	$('[name="uname"]').on('focus blur mouseleave', function() {
		if (unameType == 0) {
			return false;
		}
		unameType = 0; //console.log("c= "+c+"Uname field");
		var username = $('[name="uname"]').val();
		if (username.length > 0) {
			$.ajax({
				type: 'POST',
				data: {
					username: username,
					action: 'validExistVerifiedUname'
				},
				url: 'Registration1',
				cache: false,
				success: function(result) {
					if (result == 3) {
						uname = 1;
						tooltipUname = "Accepted";
						$('#unameimg').attr("src", "gifs/ver.gif");
						$('#unameimg').prop("alt", "tick");
						$('#unameimg').attr("hidden", false);
					} else if (result == 2) {
						uname = 0;
						tooltipUname = "Username must be between 8 to 15 characters long. It should atleast contain:\n1 lowercase character and,\n 1 uppercase character.";
						$('#unameimg').attr("src", "img/wrong.png");
						$('#unameimg').prop("alt", "wrong");
						$('#unameimg').attr("hidden", false);
						//showTooltip();
					}/* else if (result == 1) {
						uname = 0;
						tooltipUname = "The username " + username + " is already taken";
						$('#unameimg').attr("src", "img/wrong.png");
						$('#unameimg').prop("alt", "wrong");
						$('#unameimg').attr("hidden", false);
					} else if (result == 0){
						$('.five').fadeIn(100).delay(1500).fadeOut(500);
						uname = 0;
						sendEmail();
						tooltipUname = "The username " + username + " is already registered but email is not verified. Please verify your email id. Redirecting in 3s";
						alert(tooltipUname);
						$('#unameimg').attr("src", "img/wrong.png");
						$('#unameimg').prop("alt", "wrong");
						$('#unameimg').attr("hidden", false);
						window.location.href = "EmailVerify.jsp";
					}*/
					$('[name="uname"]').attr('title', tooltipUname);
					var item;
					if(result==3){
						item= $('#unameimg').parent().next().children("span")[1];
					}else{
						item= $('#unameimg').parent().next().children("span")[0];
					}
					item.innerHTML=tooltipUname;
					$('#unameimg').click();
					//$('#unameimg').attr('title', tooltipUname);
				}
			});
		} else {
			uname = 0;
			tooltipUname = "Please fill out this field";
			$('#unameimg').attr("src", "img/wrong.png");
			$('#unameimg').prop("alt", "wrong");
			$('#unameimg').attr("hidden", true);
			$('[name="uname"]').attr('title', tooltipUname);
			//$('#unameimg').attr('title', tooltipUname);
		}
	});
	$('[name="acc"]').on('focus blur mouseleave', function() {
		if (accType == 0) {
			return false;
		}
		accType = 0; //console.log("d= "+d+"Phone field");
		var accnumber = $('[name="acc"]').val();
		if (accnumber.length > 0) {
			var item;
			if (accnumber.length == 14) {
				acc = 1;
				tooltipAcc = "Accepted";
				$('#accimg').attr("src", "gifs/ver.gif");
				$('#accimg').prop("alt", "tick");
				$('#accimg').attr("hidden", false);
				item= $('#accimg').parent().next().children("span")[1];
			} else {
				acc = 0;
				tooltipAcc = "Account Number must be a 14-digit Number";
				$('#accimg').attr("src", "img/wrong.png");
				$('#accimg').prop("alt", "wrong");
				$('#accimg').attr("hidden", false);
				item= $('#accimg').parent().next().children("span")[0];
			}
			item.innerHTML=tooltipAcc;
			$('#accimg').click();
		} else {
			acc = 0;
			tooltipAcc = "Please fill out this field";
			$('#accimg').attr("src", "img/wrong.png");
			$('#accimg').prop("alt", "wrong");
			$('#accimg').attr("hidden", true);
		}
		$('[name="acc"]').attr('title', tooltipAcc);
		//$('#accimg').attr('title', tooltipAcc);
	});

	$('[name="email"]').on('focus blur mouseleave', function() {
		if (emailType == 0) {
			return false;
		}
		emailType = 0; //console.log("e= "+e+"Email field");
		var emailid = $('[name="email"]').val();
		var reg = /^[A-Z0-9._%+-]+@([A-Z0-9-]+\.)+[A-Z]{2,4}$/i;
		if (emailid.length > 0) {
			if (!reg.test(emailid)) {
				email = 0;
				tooltipEmail = "Your input " + emailid + " is not even an email id";
				var item= $('#phoneimg').parent().next().children("span")[0];
				$('#emailimg').attr("src", "img/wrong.png");
				$('#emailimg').prop("alt", "wrong");
				$('#emailimg').attr("hidden", false);
				item.innerHTML=tooltipPhone;
				$('#emailimg').click();
			} else {
				//$('.five').fadeIn(100).delay(3000).fadeOut(500);
				$.ajax({
					type: 'POST',
					data: {
						emailId: emailid,
						action: 'validExistEmail'
					},
					url: 'Registration1',
					cache: false,
					success: function(result) {
						if (result == 2) {
							email = 1;
							tooltipEmail = "Accepted";
							$('#emailimg').attr("src", "gifs/ver.gif");
							$('#emailimg').prop("alt", "tick");
							$('#emailimg').attr("hidden", false);
						} else if (result == 1) {
							email = 0;
							tooltipEmail = "The email id " + emailid + " is not an active email id";
							$('#emailimg').attr("src", "img/wrong.png");
							$('#emailimg').prop("alt", "wrong");
							$('#emailimg').attr("hidden", false);
						} else {
							email = 0;
							tooltipEmail = "The email id " + emailid + " is already associated with another account";
							$('#emailimg').attr("src", "img/wrong.png");
							$('#emailimg').prop("alt", "wrong");
							$('#emailimg').attr("hidden", false);
						}
						$('[name="email"]').attr('title', tooltipEmail);
						var item;
						if(result==2){
							item= $('#emailimg').parent().next().children("span")[1];
						}else{
							item= $('#emailimg').parent().next().children("span")[0];
						}
						item.innerHTML=tooltipEmail;
						$('#emailimg').click();
						//$('#emailimg').attr('title', tooltipEmail);
					}
				});
			}
		} else {
			email = 0;
			tooltipEmail = "Please fill out this field";
			$('#emailimg').attr("src", "img/wrong.png");
			$('#emailimg').prop("alt", "wrong");
			$('#emailimg').attr("hidden", true);
			$('[name="email"]').attr('title', tooltipEmail);
			//$('#emailimg').attr('title', tooltipEmail);
		}
	});
	$('[name="phone"]').on('focus blur mouseleave', function() {
		if (phoneType == 0) {
			return;
		}
		phoneType = 0; //console.log("d= "+d+"Phone field");
		var phonenumber = $('[name="phone"]').val();
		if (phonenumber.length > 0) {
			$.ajax({
				type: 'POST',
				data: {
					phonenumber: phonenumber,
					action: 'validExistWorkingPhone'
				},
				url: 'Registration1',
				cache: false,
				success: function(result) {
					if (result == 3) {
						phone = 1;
						tooltipPhone = "Accepted";
						$('#phoneimg').attr("src", "gifs/ver.gif");
						$('#phoneimg').prop("alt", "tick");
						$('#phoneimg').attr("hidden", false);
					} else if (result == 2) {
						phone = 0;
						tooltipPhone = "The phone number " + phonenumber + " is not a valid phone number";
						$('#phoneimg').attr("src", "img/wrong.png");
						$('#phoneimg').prop("alt", "wrong");
						$('#phoneimg').attr("hidden", false);
					} else if (result == 1) {
						phone = 0;
						$('#phoneimg').attr("src", "img/wrong.png");
						$('#phoneimg').prop("alt", "wrong");
						$('#phoneimg').attr("hidden", false);
						alert("The phone number " + phonenumber + " is not working/active");
					} else {
						phone = 0;
						tooltipPhone = "The phone number " + phonenumber + " is already associated with another account";
						$('#phoneimg').attr("src", "img/wrong.png");
						$('#phoneimg').prop("alt", "wrong");
						$('#phoneimg').attr("hidden", false);
					}
					$('[name="phone"]').attr('title', tooltipPhone);
					var item;
					if(result==3){
						item= $('#phoneimg').parent().next().children("span")[1];
					}else{
						item= $('#phoneimg').parent().next().children("span")[0];
					}
					item.innerHTML=tooltipPhone;
					$('#phoneimg').click();
					//$('#phoneimg').attr('title', tooltipPhone);
				}
			});
		} else {
			phone = 0;
			tooltipPhone = "Please fill out this field";
			$('#phoneimg').attr("src", "img/wrong.png");
			$('#phoneimg').prop("alt", "wrong");
			$('#phoneimg').attr("hidden", true);
			$('[name="phone"]').attr('title', tooltipPhone);
			//$('#phoneimg').attr('title', tooltipPhone);
		}
	});
	/*$('[name="address"]').on('focus blur mouseleave',function(){
	  if(f==0){
		  return false;
	  }
	  f=0;console.log("f= "+f+"Address field");
		$('[name="address"]').attr('title', tooltipAddress);
		$('#addressimg').attr('title', tooltipAddress);
	});*/
	
	$('[name="btnNEXT"]').click(function() {
		$('[name="subtime1"]').val(getTimeStamp());
		$('.five').fadeIn(500);
		$('form').delay(500).submit();
	});
	$("#sixp1p1").click(function(){
		$('.six').fadeOut(500);
	});
	$("#sixp2p1").click(function(){
		$('.six').fadeOut(500);
	});
	
	setInterval(Check, 250);
	function Check() {
		if (fname == 1 && lname == 1 && uname == 1 && acc == 1 && email == 1 && phone == 1 && address == 1 && chkbox == 1) {
			$('[name="btnNEXT"]').addClass("grow");
			$('[name="btnNEXT"]')[0].disabled = false;
		} else if (fname == 0 || lname == 0 || uname == 0 || acc == 0 || email == 0 || phone == 0 || address == 0 || chkbox == 0) {
			$('[name="btnNEXT"]').removeClass("grow");
			$('[name="btnNEXT"]')[0].disabled = true;
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