var fnameType = 0,
	lnameType = 0,
	accType = 0,
	cnfaccType = 0,
	banktransferlimitType = 0,
	branchType = 0,
	bankType = 0,
	ifscType = 0;
var fname, lname, acc, cnfacc, banktransferlimit, branchname, bankname, chkbox, ifsc;
var tooltipFname, tooltipLname, tooltipAcc, tooltipCnfacc, tooltipBankTransferLimit, tooltipBank, tooltipBranch, tooltipIfsc;

$(document).ready(function() {
	fname = 0;
	lname = 0;
	acc = 0;
	cnfacc = 0;
	banktransferlimit = 0;
	branchname = 0;
	bankname = 0;
	ifsc = 0;
	chkbox = 0;
	$('[name="btnRESET"]').click(function() {
		$('[name="resettime"]').val(getTimeStamp());
		fname = 0;
		lname = 0;
		acc = 0;
		cnfacc = 0;
		banktransferlimit = 0;
		branchname = 0;
		bankname = 0;
		ifsc = 0;
		chkbox = 0;
		$.ajax({
			type: 'POST',
			url: 'AddBeneficiary',
			data: {
				action: 'resetButton',
				benacc: $('[name="acc"]').val(),
				benfname: $('[name="fname"]').val(),
				benlname: $('[name="lname"]').val(),
				benlimit: $('[name="banktransferlimit"]').val(),
				benbankname: $('[name="banknamelist"]').val(),
				benbranchname: $('[name="branchnamelist"]').val(),
				benifsc: $('[name="ifsc"]').val(),
				
				benaddpagetime: $('[name="benaddpagetime"]').val(),
				ftime: $('[name="ftime"]').val(),
				ltime: $('[name="ltime"]').val(),
				acctime: $('[name="acctime"]').val(),
				cnfacctime: $('[name="cnfacctime"]').val(),
				limittime: $('[name="limittime"]').val(),
				banknametime: $('[name="banknametime"]').val(),
				branchnametime: $('[name="branchnametime"]').val(),
				ifsctime: $('[name="ifsctime"]').val(),
				chktime: $('[name="chktime"]').val(),
				resettime: $('[name="resettime"]').val()
			},
			cache: false
		});
		
		var a = $('[name="benaddpagetime"]').val();
		$('input[type=text], textarea').val("");
		$('input[type=hidden]').val(0);
		$('[name="benaddpagetime"]').val(a);
		
		$('select[name="banknamelist"] option:selected').attr("selected", null);
		$('select[name="branchnamelist"] option:selected').attr("selected", null);
		
		var idarray = ['#fname', '#lname', '#acc', '#banktransferlimit', '#cnfacc', '#ifsc'];
		for (i = 0; i < idarray.length; i++) {
			var tooltip = "Please fill out this field";
			$(idarray[i] + 'img').attr("src", "img/wrong.png");
			$(idarray[i] + 'img').prop("alt", "wrong");
			$(idarray[i] + 'img').attr("hidden", true);
			$(idarray[i]).attr('title', tooltip);
			$(idarray[i] + 'img').attr('title', tooltip);
		}
		$('[name="chkbox"]').prop("checked", false);
		
		$('#banknameimg').attr("src", "img/wrong.png");
		$('#banknameimg').prop("alt", "wrong");
		$('#banknameimg').attr("hidden", true);
		$('[name="banknamelist"]').attr('title', "Please Choose An Option");
		$('#banknameimg').attr('title', "Please Choose An Option");
		
		$('#branchnameimg').attr("src", "img/wrong.png");
		$('#branchnameimg').prop("alt", "wrong");
		$('#branchnameimg').attr("hidden", true);
		$('[name="branchnamelist"]').attr('title', "Please Choose An Option");
		$('#branchnameimg').attr('title', "Please Choose An Option");
	});
	$('input').keypress(function(e) {
		if (e.which == 32)
			return false;
	});
	var regExpNUM = /[0-9]/;
	$('[name="acc"],[name="cnfacc"],[name="banktransferlimit"]').on('keydown keyup blur focus', function(e) {
		var value = e.key;
		/*var ascii=value.charCodeAt(0);
		$('textarea').append(ascii);
		$('textarea').append(value);
		console.log(e);*/
		// Only numbers
		if (!regExpNUM.test(value) &&
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
	var regExpIfsc = /^[A-Z0-9]+$/;
	$('[name="ifsc"]').on('keydown keyup blur focus', function(e) {
		var value = e.key;
		/*var ascii=value.charCodeAt(0);
		$('textarea').append(ascii);
		$('textarea').append(value);
		console.log(e);*/
		// Only numbers
		if (!regExpIfsc.test(value) &&
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
	
	$('[name="acc"],[name="cnfacc"]').on('keypress', function(e) {
		if ($(this).val().length > 13) {
			e.preventDefault();
		}
	});
	
	$('[name="ifsc"]').on('keypress', function(e) {
		if ($('[name="ifsc"]').val().length > 10) {
			e.preventDefault();
		}
	});
	
	$('[name="banktransferlimit"]').on('keypress', function(e) {
		if ($('[name="banktransferlimit"]').val().length > 5) {
			e.preventDefault();
		}
	});
	
	var regExpFname = /[a-zA-Z]/;
	$('[name="fname"],[name="lname"]').on('keydown keyup blur focus', function(e) {
		var value = e.key;
		/*var ascii=value.charCodeAt(0);
		$('textarea').append(ascii);
		$('textarea').append(value);
		console.log(e);*/
		// Only numbers
		if (!regExpFname.test(value) &&
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
	
	$('input').on('keyup keydown keypress', function() {
		var name = $(this).attr("name");
		if (name == "fname") {
			fnameType = 1;
			fname = 0;
		} else if (name == "lname") {
			lnameType = 1;
			lname = 0;
		} else if (name == "acc") {
			accType = 1;
			acc = 0;
		} else if (name == "cnfacc") {
			cnfaccType = 1;
			cnfacc = 0;
		} else if (name == "banktransferlimit") {
			banktransferlimitType = 1;
			banktransferlimit = 0;
		} else if (name == "ifsc") {
			ifscType = 1;
			ifsc = 0;
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
	
	$('[name="fname"]').on('focus blur mouseleave', function() {
		if (fnameType == 0) {
			return false;
		}
		fnameType = 0;
		fnamelen=$('[name="fname"]').val().length;
		if (fnamelen > 1 && fnamelen<=40) {
			fname = 1;
			tooltipFname = "Accepted";
			$('#fnameimg').attr("src", "img/tick.png");
			$('#fnameimg').prop("alt", "tick");
			$('#fnameimg').attr("hidden", false);
		}else if (fnamelen > 40){
			fname = 0;
			tooltipFname = "Please keep the length of first name within 40 characters.";
			$('#fnameimg').attr("src", "img/wrong.png");
			$('#fnameimg').prop("alt", "wrong");
			$('#fnameimg').attr("hidden", false);
		} else {
			fname = 0;
			tooltipFname = "Please fill out this field";
			$('#fnameimg').attr("src", "img/wrong.png");
			$('#fnameimg').prop("alt", "wrong");
			$('#fnameimg').attr("hidden", true);
		}
		$('[name="fname"]').attr('title', tooltipFname);
		$('#fnameimg').attr('title', tooltipFname);
	});
	
	$('[name="lname"]').on('focus blur mouseleave', function() {
		if (lnameType == 0) {
			return false;
		}
		lnameType = 0;
		lnamelen=$('[name="lname"]').val().length;
		if (lnamelen > 1 && lnamelen<=40) {
			lname = 1;
			tooltipLname = "Accepted";
			$('#lnameimg').attr("src", "img/tick.png");
			$('#lnameimg').prop("alt", "tick");
			$('#lnameimg').attr("hidden", false);
		}else if (lnamelen > 40){
			lname = 0;
			tooltipLname = "Please keep the length of last name within 40 characters.";
			$('#lnameimg').attr("src", "img/wrong.png");
			$('#lnameimg').prop("alt", "wrong");
			$('#lnameimg').attr("hidden", false);
		} else {
			lname = 0;
			tooltipLname = "Please fill out this field";
			$('#lnameimg').attr("src", "img/wrong.png");
			$('#lnameimg').prop("alt", "wrong");
			$('#lnameimg').attr("hidden", true);
		}
		$('[name="lname"]').attr('title', tooltipLname);
		$('#lnameimg').attr('title', tooltipLname);
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
	
	$('[name="cnfacc"]').on('focus blur mouseleave', function() {
		if (cnfaccType == 0) {
			return false;
		}
		cnfaccType = 0;
		var cnfaccountno = $('[name="cnfacc"]').val();
		var accountno = $('[name="acc"]').val();
		if (cnfaccountno.length >0) {
			if (accountno == cnfaccountno) {
				cnfacc = 1;
				tooltipCnfacc = "Matching";
				$('#cnfaccimg').attr("src", "img/tick.png");
				$('#cnfaccimg').prop("alt", "tick");
				$('#cnfaccimg').attr("hidden", false);
			} else {
				cnfacc = 0;
				tooltipCnfacc = "Not Matching";
				$('#cnfaccimg').attr("src", "img/wrong.png");
				$('#cnfaccimg').prop("alt", "wrong");
				$('#cnfaccimg').attr("hidden", false);
			}
		} else {
			cnfacc = 0;
			tooltipCnfacc = "Please fill out this field";
			$('#cnfaccimg').attr("src", "img/wrong.png");
			$('#cnfaccimg').prop("alt", "wrong");
			$('#cnfaccimg').attr("hidden", true);
		}
		$('[name="cnfacc"]').attr('title', tooltipCnfacc);
		$('#cnfaccimg').attr('title', tooltipCnfacc);
	});
	
	$('[name="banktransferlimit"]').on('focus blur mouseleave', function() {
		if (banktransferlimitType == 0) {
			return false;
		}
		banktransferlimitType = 0;
		var limit = $('[name="banktransferlimit"]').val();
		if (limit.length > 0) {
			if(limit >101 && limit <=500000) {
				banktransferlimit = 1;
				tooltipBankTransferLimit = "Accepted";
				$('#banktransferlimitimg').attr("src", "img/tick.png");
				$('#banktransferlimitimg').prop("alt", "tick");
				$('#banktransferlimitimg').attr("hidden", false);
			} else{
				banktransferlimit = 0;
				tooltipBankTransferLimit = "The Bank Transfer Limit " + limit + " must be less than 500000 and greater than 100";
				$('#banktransferlimitimg').attr("src", "img/wrong.png");
				$('#banktransferlimitimg').prop("alt", "wrong");
				$('#banktransferlimitimg').attr("hidden", false);
			}
		} else {
			banktransferlimit = 0;
			tooltipBankTransferLimit = "Please fill out this field";
			$('#banktransferlimitimg').attr("src", "img/wrong.png");
			$('#banktransferlimitimg').prop("alt", "wrong");
			$('#banktransferlimitimg').attr("hidden", true);
		}
		$('[name="banktransferlimit"]').attr('title', tooltipBankTransferLimit);
		$('#banktransferlimitimg').attr('title', tooltipBankTransferLimit);
	});
	
	$('[name="ifsc"]').on('focus blur mouseleave', function() {
		if (ifscType == 0) {
			return false;
		}
		ifscType = 0;
		var ifsccode = $('[name="ifsc"]').val();
		if (ifsccode.length > 0) {
			$.ajax({
				type: 'POST',
				url: 'AddBeneficiary',
				data: {
					ifsccode: ifsccode,
					action: 'validIfsccode'
				},
				cache: false,
				success: function(result) {
					if (result == 1) {
						console.log("yes");
						ifsc = 1;
						tooltipIfsc = "Accepted";
						$('#ifscimg').attr("src", "img/tick.png");
						$('#ifscimg').prop("alt", "tick");
						$('#ifscimg').attr("hidden", false);
					} else if (result == 0) {
						ifsc = 0;
						console.log("No");
						tooltipIfsc = "The ifsc number " + ifsccode + " is not a valid ifsc number";
						$('#ifscimg').attr("src", "img/wrong.png");
						$('#ifscimg').prop("alt", "wrong");
						$('#ifscimg').attr("hidden", false);
					}
					$('[name="ifsc"]').attr('title', tooltipIfsc);
					$('#ifscimg').attr('title', tooltipIfsc);
				}
			});
		} else {
			ifsc = 0;
			tooltipIfsc = "Please fill out this field";
			$('#ifscimg').attr("src", "img/wrong.png");
			$('#ifscimg').prop("alt", "wrong");
			$('#ifscimg').attr("hidden", true);
			$('[name="ifsc"]').attr('title', tooltipIfsc);
			$('#ifscimg').attr('title', tooltipIfsc);
		}
	});
	
	$('#banknamelist').click(function() {
		//var a = $('select[name="banknamelist"] option:selected').text();
		var a = $('select[name="banknamelist"] option:selected').val();
		console.log(a);
		if (a == "0") {
			bankname = 0;
			tooltipBankName = "Please Choose An Option";
			$('#banknameimg').attr("src", "img/wrong.png");
			$('#banknameimg').prop("alt", "wrong");
			$('#banknameimg').attr("hidden", true);
		} else if (a != null) {
			bankname = 1;
			tooltipBankName = "Selected";
			$('#banknameimg').attr("src", "img/tick.png");
			$('#banknameimg').prop("alt", "tick");
			$('#banknameimg').attr("hidden", false);
		}
		$('[name="banknamelist"]').attr('title', tooltipBankName);
		$('#banknameimg').attr('title', tooltipBankName);
	});
	
	$('#branchnamelist').click(function() {
		var a = $('select[name="branchnamelist"] option:selected').val();
		if (a === 0) {
			branchname = 0;
			tooltipBranchName = "Please Choose An Option";
			$('#branchnameimg').attr("src", "img/wrong.png");
			$('#branchnameimg').prop("alt", "wrong");
			$('#branchnameimg').attr("hidden", true);
		} else if (a != null) {
			branchname = 1;
			console.log(a);
			tooltipBranchName = "Selected";
			$('#branchnameimg').attr("src", "img/tick.png");
			$('#branchnameimg').prop("alt", "tick");
			$('#branchnameimg').attr("hidden", false);
		}
		$('[name="branchnamelist"]').attr('title', tooltipBranchName);
		$('#branchnameimg').attr('title', tooltipBranchName);
	});
	
	$('[name="chkbox"]').click(function() {
		if ($('[name="chkbox"]').is(":checked")) {
			chkbox = 1;
		} else {
			console.log("Not Checked");
			chkbox = 0;
		}
	});
	
	/*$('[name="banktransferlimit"]').on('focus blur mouseleave', function() {
		if (banktransferlimitType == 0) {
			return false;
		}
		banktransferlimitType = 0;
		var limit = $('[name="banktransferlimit"]').val();
		if (limit.length > 0) {
			$.ajax({
				type: 'POST',
				url: 'AddBeneficiary',
				data: {
					limit: limit,
					action: 'validBanktransferlimit'
				},
				cache: false,
				success: function(result) {
					if (result == 1) {
						banktransferlimit = 1;
						tooltipBankTransferLimit = "Accepted";
						$('#banktransferlimitimg').attr("src", "img/tick.png");
						$('#banktransferlimitimg').prop("alt", "tick");
						$('#banktransferlimitimg').attr("hidden", false);
					} else if (result == 0) {
						banktransferlimit = 0;
						tooltipBankTransferLimit = "The Bank Transfer Limit " + limit + " must be less than 500000 and greater than 100";
						$('#banktransferlimitimg').attr("src", "img/wrong.png");
						$('#banktransferlimitimg').prop("alt", "wrong");
						$('#banktransferlimitimg').attr("hidden", false);
					}
					$('[name="banktransferlimit"]').attr('title', tooltipBankTransferLimit);
					$('#banktransferlimitimg').attr('title', tooltipBankTransferLimit);
				}
			});
		} else {
			banktransferlimit = 0;
			tooltipBankTransferLimit = "Please fill out this field";
			$('#banktransferlimitimg').attr("src", "img/wrong.png");
			$('#banktransferlimitimg').prop("alt", "wrong");
			$('#banktransferlimitimg').attr("hidden", true);
			$('[name="banktransferlimit"]').attr('title', tooltipBankTransferLimit);
			$('#banktransferlimitimg').attr('title', tooltipBankTransferLimit);
		}
	});
	var accountno=$('[name="acc"]').val();
	if(accountno.length>0){
		$.ajax({
			type: 'POST',
			data:{
				acc: accountno,
				action: 'validacc'
			},
			url: 'AddBeneficiary',
			cache:false,
			success: function(result){
				if(result==1){
					acc=1;
					tooltipacc = "Accepted";
					$('#accimg').attr("src","img/tick.png");
					$('#accimg').prop("alt", "tick");
					$('#accimg').attr("hidden",false);
				}
				else{
					acc=0;
					tooltipacc = "The Account Number should be 10 digits long";
					$('#accimg').attr("src","img/wrong.png");
					$('#accimg').prop("alt", "wrong");
					$('#accimg').attr("hidden",false);
				}
				$('[name="acc"]').attr('title', tooltipacc);
				$('#accimg').attr('title', tooltipacc);
			}
		});
	}
	else{
		acc=0;
		tooltipacc = "Please fill out this field";
		$('#accimg').attr("src","img/wrong.png");
		$('#accimg').prop("alt", "wrong");
		$('#accimg').attr("hidden",true);
		$('[name="acc"]').attr('title', tooltipacc);
		$('#accimg').attr('title', tooltipacc);
	}
	var cnfaccountno=$('[name="cnfacc"]').val();
	if(cnfaccountno.length>0){
		$.ajax({
			type: 'POST',
			data:{
				acc: $('[name="acc"]').val(),
				cnfacc: cnfaccountno,
				action: 'validSameCnfacc'
			},
			url: 'AddBeneficiary',
			cache:false,
			success: function(result){
				if(result==1){
					cnfacc=1;
					tooltipCnfacc = "Matching";
					$('#cnfaccimg').attr("src","img/tick.png");
					$('#cnfaccimg').prop("alt", "tick");
					$('#cnfaccimg').attr("hidden",false);
				}
				else if(result==0){
					cnfacc=0;
					tooltipCnfacc = "Account Number does not match";
					$('#cnfaccimg').attr("src","img/wrong.png");
					$('#cnfaccimg').prop("alt", "wrong");
					$('#cnfaccimg').attr("hidden",false);
				}
				$('[name="cnfacc"]').attr('title', tooltipCnfacc);
				$('#cnfaccimg').attr('title', tooltipCnfacc);
			}
		});
	}
	else{
		cnfacc=0;
		tooltipCnfacc = "Please fill out this field";
		$('#cnfaccimg').attr("src","img/wrong.png");
		$('#cnfaccimg').prop("alt", "wrong");
		$('#cnfaccimg').attr("hidden",true);
		$('[name="cnfacc"]').attr('title', tooltipCnfacc);
		$('#cnfaccimg').attr('title', tooltipCnfacc);
	}*/
	
	$('[name="btnADD"]').click(function() {
		$('[name="subtime"]').val(getTimeStamp());
		$('.five').fadeIn(500);
		$('form').delay(500).submit();
	});	
	
	setInterval(Check, 250);
	setInterval(Enable, 250);

	function Enable() {
		if (acc == 1) {
			$('[name="cnfacc"]')[0].disabled = false;
		} else {
			$('[name="cnfacc"]')[0].disabled = true;
			$('[name="cnfacc"]').val("");
			$('#cnfaccimg').attr("src", "img/wrong.png");
			$('#cnfaccimg').prop("alt", "wrong");
			$('#cnfaccimg').attr("hidden", true);
			$('[name="cnfacc"]').attr('title', "Please fill out this field");
			$('#cnfaccimg').attr('title', "Please fill out this field");
		}
	}

	function Check() {
		if (fname == 1 && lname == 1 && acc == 1 && cnfacc == 1 && banktransferlimit == 1 && bankname == 1 && branchname == 1 && ifsc == 1 && chkbox == 1) {
			$('[name="btnADD"]').addClass("grow");
			$('[name="btnADD"]')[0].disabled = false;
		} else if (fname == 0 || lname == 0 || acc == 0 || cnfacc == 0 || banktransferlimit == 0 || bankname == 0 || branchname == 0 || ifsc == 0 || chkbox == 0) {
			$('[name="btnADD"]').removeClass("grow");
			$('[name="btnADD"]')[0].disabled = true;
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
