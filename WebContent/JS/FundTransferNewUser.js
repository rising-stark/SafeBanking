$(document).ready(function(){
	var fname,lname,accno,cnfaccno,amount,branchname,bankname,chkbox,ifsc;
	fname=0;
	lname=0;
	accno=0;
	cnfaccno=0;
	amount=0;
	branchname=0;
	bankname=0;
	ifsc=0;
	chkbox=0;
	$('[name="btnRESET"]').click();
	$('[name="btnRESET"]').click(function(){console.log("btnRESET clicked");
		fname=0;lname=0;accno=0;cnfaccno=0;amount=0;branchname=0;bankname=0;ifsc=0;chkbox=0;
		$('select[name="banknamelist"] option:selected').attr("selected",null);
		$('select[name="branchnamelist"] option:selected').attr("selected",null);
		$('input[type=text]').val("");
		var idarray=['#fname','#lname','#accno','#amount','#cnfaccno','#ifsc'];
		for(i=0;i<idarray.length;i++){
			var tooltip = "Please fill out this field";
			$(idarray[i]+'img').attr("src","img/wrong.png");
			$(idarray[i]+'img').prop("alt", "wrong");
			$(idarray[i]+'img').attr("hidden",true);
			$(idarray[i]).attr('title', tooltip);
			$(idarray[i]+'img').attr('title', tooltip);
		}
		$('#banknameimg').attr("src","img/wrong.png");
		$('#banknameimg').prop("alt", "wrong");
		$('#banknameimg').attr("hidden",true);
		$('[name="banknamelist"]').attr('title', "Please Choose An Option");
		$('#banknameimg').attr('title', "Please Choose An Option");
		
		$('#branchnameimg').attr("src","img/wrong.png");
		$('#branchnameimg').prop("alt", "wrong");
		$('#branchnameimg').attr("hidden",true);
		$('[name="branchnamelist"]').attr('title', "Please Choose An Option");
		$('#branchnameimg').attr('title', "Please Choose An Option");
		$('[name="chkbox"]').prop("checked", false);
	});
	
	$('input').keypress(function(e){
		if(e.which==32)
			return false;
	});
	
	var regExpNUM = /[0-9]/;
	  $('[name="accno"],[name="cnfaccno"],[name="amount"]').on('keydown keyup blur focus', function(e) {

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
	  var regExpIfsc = /^[A-Z0-9]+$/;
	  $('[name="ifsc"]').on('keydown keyup blur focus', function(e) {

	    var value =e.key;
	    /*var ascii=value.charCodeAt(0);
	    $('textarea').append(ascii);
	    $('textarea').append(value);
	    console.log(e);*/
	    // Only numbers
	    if (!regExpIfsc.test(value)
	      && e.which != 8   // backspace
	      && e.which != 46  // delete
	      && (e.which < 37  // arrow keys
	        || e.which > 40)) {
	          e.preventDefault();
	          return false;
	    }
	  });
	  $('[name="accno"]').on('keypress', function(e) {
		  if($('[name="accno"]').val().length >9){
		    	e.preventDefault();
		  }
	  });
	  $('[name="cnfaccno"]').on('keypress', function(e) {
		  if($('[name="cnfaccno"]').val().length >9){
		    	e.preventDefault();
		  }
	  });
	  $('[name="ifsc"]').on('keypress', function(e) {
		  if($('[name="ifsc"]').val().length >10){
		    	e.preventDefault();
		    }
	  });
	  $('[name="amount"]').on('keypress', function(e) {
		  if($('[name="amount"]').val().length >5){
		    	e.preventDefault();
		  }
	  });
	
	  var regExpFname = /[a-zA-Z]/;
	  $('[name="fname"]').on('keydown keyup blur focus', function(e) {

	    var value =e.key;
	    /*var ascii=value.charCodeAt(0);
	    $('textarea').append(ascii);
	    $('textarea').append(value);
	    console.log(e);*/
	    // Only numbers
	    if (!regExpFname.test(value)
	      && e.which != 8   // backspace
	      && e.which != 46  // delete
	      && (e.which < 37  // arrow keys
	        || e.which > 40)) {
	          e.preventDefault();
	          return false;
	    }
	  });
	  
	  $('[name="fname"]').on('keypress', function(e) {
		  if($('[name="fname"]').val().length >39){
		    	e.preventDefault();
		    }
	  });
	  
	  var regExpLname = /[a-zA-Z]/;
	  $('[name="lname"]').on('keydown keyup blur focus', function(e) {

	    var value =e.key;
	    /*var ascii=value.charCodeAt(0);
	    $('textarea').append(ascii);
	    $('textarea').append(value);
	    console.log(e);*/
	    // Only numbers
	    if (!regExpLname.test(value)
	      && e.which != 8   // backspace
	      && e.which != 46  // delete
	      && (e.which < 37  // arrow keys
	        || e.which > 40)) {
	          e.preventDefault();
	          return false;
	      }
	  });
	  
	  $('[name="lname"]').on('keypress', function(e) {
		  if($('[name="lname"]').val().length >39){
		    	e.preventDefault();
		    }
	  });
	
	  /*$('input').on('focus blur click hover mouseenter', function() {
		  if($(this).val().length==0){
			  var displayTooltip = "Please fill out this field";
			  $(this).attr('title', displayTooltip);
		  }
	  });*/
	  setInterval(checkbox,0);
	  function checkbox(){
		  if($('[name="chkbox"]').is(":checked")){
			  chkbox=1;
		  }
		  else{
			  chkbox=0;
		  }
	  }
	  $('#banknamelist').click(function(){
		  var tooltipBankName;
		  var a=$('select[name="banknamelist"] option:selected').text();
		  console.log(a);
		  if(a=='---- Choose Your Bank Name ----' || a==null){
			  bankname=0;
			  tooltipBankName = "Please Choose An Option";
			  $('#banknameimg').attr("src","img/wrong.png");
			  $('#banknameimg').prop("alt", "wrong");
			  $('#banknameimg').attr("hidden",true);
		  }
		  else if(a!=null){
			  bankname=1;
			  console.log(a);
			  tooltipBankName = "Selected";
			  $('#banknameimg').attr("src","img/tick.png");
			  $('#banknameimg').prop("alt", "tick");
			  $('#banknameimg').attr("hidden",false);
		  }
		  $('[name="banknamelist"]').attr('title', tooltipBankName);
		  $('#banknameimg').attr('title', tooltipBankName);
	  });
	  $('#branchnamelist').click(function(){
		  var tooltipBranchName;
		  var a=$('select[name="branchnamelist"] option:selected').text();
		  if(a=='---- Choose Your Branch Name ----' || a==null){
			  branchname=0;
			  tooltipBranchName = "Please Choose An Option";
			  $('#branchnameimg').attr("src","img/wrong.png");
			  $('#branchnameimg').prop("alt", "wrong");
			  $('#branchnameimg').attr("hidden",true);
		  }
		  else if(a!=null){
			  branchname=1;
			  console.log(a);
			  tooltipBranchName = "Selected";
			  $('#branchnameimg').attr("src","img/tick.png");
			  $('#branchnameimg').prop("alt", "tick");
			  $('#branchnameimg').attr("hidden",false);
		  }
		  $('[name="branchnamelist"]').attr('title', tooltipBranchName);
		  $('#branchnameimg').attr('title', tooltipBranchName);
	  });
	  $('[name="accno"]').on('keyup keydown keypress',function(){
		  	var tooltipAccno;
			var accountno=$('[name="accno"]').val();
			if(accountno.length==10){
				accno=1;
				tooltipAccno = "Accepted";
				$('#accnoimg').attr("src","img/tick.png");
				$('#accnoimg').prop("alt", "tick");
				$('#accnoimg').attr("hidden",false);
			}
			$('[name="accno"]').attr('title', tooltipAccno);
			$('#accnoimg').attr('title', tooltipAccno);
		});
	  $('[name="cnfaccno"]').on('keyup keydown keypress',function(){
		  	var tooltipCnfAccno;
			var cnfaccountno=$('[name="cnfaccno"]').val();
			var accountno=$('[name="accno"]').val();
			if(accountno==cnfaccountno){
				cnfaccno=1;
				tooltipCnfAccno = "Matching";
				$('#cnfaccnoimg').attr("src","img/tick.png");
				$('#cnfaccnoimg').prop("alt", "tick");
				$('#cnfaccnoimg').attr("hidden",false);
			}
			$('[name="cnfaccno"]').attr('title', tooltipCnfAccno);
			$('#cnfaccnoimg').attr('title', tooltipCnfAccno);
		});
	  $('input').on('focus blur',function(){
		  var tooltipFname,tooltipLname,tooltipAccno,tooltipCnfAccno,tooltipAmount,tooltipBranchName,tooltipBankName,tooltipIfsc;
		  if($('[name="fname"]').val().length > 1){//$(this).blockUI({ message: '<h1><img src="img/wrong.png" /> Redirecting to email verification window in 3 seconds</h1>' });
			  fname=1;
			  tooltipFname="Accepted";
			  $('#fnameimg').attr("src","img/tick.png");
			  $('#fnameimg').prop("alt", "tick");
			  $('#fnameimg').attr("hidden",false);
		  }
		else{
			fname=0;
			tooltipFname="Please fill out this field";
			$('#fnameimg').attr("src","img/wrong.png");
			$('#fnameimg').prop("alt", "wrong");
			$('#fnameimg').attr("hidden",true);
		}
		if($('[name="lname"]').val().length > 1){
			lname=1;
			tooltipLname="Accepted";
			$('#lnameimg').attr("src","img/tick.png");
			$('#lnameimg').prop("alt", "tick");
			$('#lnameimg').attr("hidden",false);
		}
		else{
			lname=0;
			tooltipLname="Please fill out this field";
			$('#lnameimg').attr("src","img/wrong.png");
			$('#lnameimg').prop("alt", "wrong");
			$('#lnameimg').attr("hidden",true);
		}
		var accountno=$('[name="accno"]').val();
		if(accountno.length>0){
			$.ajax({
				type: 'POST',
				data:{
					accNo: accountno,
					action: 'validAccno'
				},
				url: 'FundTransferNewUser',
				cache:false,
				success: function(result){
					if(result==1){
						accno=1;
						tooltipAccno = "Accepted";
						$('#accnoimg').attr("src","img/tick.png");
						$('#accnoimg').prop("alt", "tick");
						$('#accnoimg').attr("hidden",false);
					}
					else{
						accno=0;
						tooltipAccno = "The Account Number should be 10 digits long";
						$('#accnoimg').attr("src","img/wrong.png");
						$('#accnoimg').prop("alt", "wrong");
						$('#accnoimg').attr("hidden",false);
					}
					$('[name="accno"]').attr('title', tooltipAccno);
					$('#accnoimg').attr('title', tooltipAccno);
				}
			});
		}
		else{
			accno=0;
			tooltipAccno = "Please fill out this field";
			$('#accnoimg').attr("src","img/wrong.png");
			$('#accnoimg').prop("alt", "wrong");
			$('#accnoimg').attr("hidden",true);
			$('[name="accno"]').attr('title', tooltipAccno);
			$('#accnoimg').attr('title', tooltipAccno);
		}
		var cnfaccountno=$('[name="cnfaccno"]').val();
		if(cnfaccountno.length>0){
			$.ajax({
				type: 'POST',
				data:{
					accNo: $('[name="accno"]').val(),
					cnfAccNo: cnfaccountno,
					action: 'validSameCnfAccno'
				},
				url: 'FundTransferNewUser',
				cache:false,
				success: function(result){
					if(result==1){
						cnfaccno=1;
						tooltipCnfAccno = "Matching";
						$('#cnfaccnoimg').attr("src","img/tick.png");
						$('#cnfaccnoimg').prop("alt", "tick");
						$('#cnfaccnoimg').attr("hidden",false);
					}
					else if(result==0){
						cnfaccno=0;
						tooltipCnfAccno = "Account Number does not match";
						$('#cnfaccnoimg').attr("src","img/wrong.png");
						$('#cnfaccnoimg').prop("alt", "wrong");
						$('#cnfaccnoimg').attr("hidden",false);
					}
					$('[name="cnfaccno"]').attr('title', tooltipCnfAccno);
					$('#cnfaccnoimg').attr('title', tooltipCnfAccno);
				}
			});
		}
		else{
			cnfaccno=0;
			tooltipCnfAccno = "Please fill out this field";
			$('#cnfaccnoimg').attr("src","img/wrong.png");
			$('#cnfaccnoimg').prop("alt", "wrong");
			$('#cnfaccnoimg').attr("hidden",true);
			$('[name="cnfaccno"]').attr('title', tooltipCnfAccno);
			$('#cnfaccnoimg').attr('title', tooltipCnfAccno);
		}
		var ifsccode=$('[name="ifsc"]').val();
		if(ifsccode.length>0){
			$.ajax({
				type: 'POST',
				data:{
					ifscCode: ifsccode,
					action: 'validIfscCode'
				},
				url: 'FundTransferNewUser',
				cache:false,
				success: function(result){
					if(result==1){
						console.log("yes");
						ifsc=1;
						tooltipIfsc = "Accepted";
						$('#ifscimg').attr("src","img/tick.png");
						$('#ifscimg').prop("alt", "tick");
						$('#ifscimg').attr("hidden",false);
					}
					else if(result==0){
						ifsc=0;
						console.log("No");
						tooltipIfsc = "The ifsc number "+ifsccode+" is not a valid ifsc number";
						$('#ifscimg').attr("src","img/wrong.png");
						$('#ifscimg').prop("alt", "wrong");
						$('#ifscimg').attr("hidden",false);
					}
					$('[name="ifsc"]').attr('title', tooltipIfsc);
					$('#ifscimg').attr('title', tooltipIfsc);
				}
			});
		}
		else{
			ifsc=0;
			tooltipIfsc = "Please fill out this field";
			$('#ifscimg').attr("src","img/wrong.png");
			$('#ifscimg').prop("alt", "wrong");
			$('#ifscimg').attr("hidden",true);
			$('[name="ifsc"]').attr('title', tooltipIfsc);
			$('#ifscimg').attr('title', tooltipIfsc);
		}
		var amt=$('[name="amount"]').val();
		if(amt.length>0){
			$.ajax({
				type: 'POST',
				data:{
					Amt: amt,
					action: 'validAmount'
				},
				url: 'FundTransferNewUser',
				cache:false,
				success: function(result){
					if(result==1){
						amount=1;
						tooltipAmount = "Accepted";
						$('#amountimg').attr("src","img/tick.png");
						$('#amountimg').prop("alt", "tick");
						$('#amountimg').attr("hidden",false);
					}
					else if(result==0){
						amount=0;
						tooltipAmount = "The Amount "+amt+" must be less than 500000 and greater than 100";
						$('#amountimg').attr("src","img/wrong.png");
						$('#amountimg').prop("alt", "wrong");
						$('#amountimg').attr("hidden",false);
					}
					$('[name="amount"]').attr('title', tooltipAmount);
					$('#amountimg').attr('title', tooltipAmount);
				}
			});
		}
		else{
			amount=0;
			tooltipAmount = "Please fill out this field";
			$('#amountimg').attr("src","img/wrong.png");
			$('#amountimg').prop("alt", "wrong");
			$('#amountimg').attr("hidden",true);
			$('[name="amount"]').attr('title', tooltipAmount);
			$('#amountimg').attr('title', tooltipAmount);
		}
		$('[name="fname"]').attr('title', tooltipFname);
		$('[name="lname"]').attr('title', tooltipLname);
		$('#fnameimg').attr('title', tooltipFname);
		$('#lnameimg').attr('title', tooltipLname);
  	});
	setInterval(Check, 0);
	setInterval(Enable, 0);
	function Enable(){
		if(accno==1){
			$('[name="cnfaccno"]')[0].disabled=false;
		}
		else{
			$('[name="cnfaccno"]')[0].disabled=true;
			$('[name="cnfaccno"]').val("");
			$('#cnfaccnoimg').attr("src","img/wrong.png");
			$('#cnfaccnoimg').prop("alt", "wrong");
			$('#cnfaccnoimg').attr("hidden",true);
			$('[name="cnfaccno"]').attr('title', "Please fill out this field");
			$('#cnfaccnoimg').attr('title', "Please fill out this field");
		}
	}
	function Check(){
		if(fname==1 && lname==1 && accno==1 && cnfaccno==1 && amount==1 && bankname==1 && branchname==1 && ifsc==1 && chkbox==1){
			$('[name="btnNEXT"]').addClass("grow");
			$('[name="btnNEXT"]')[0].disabled=false;
		}
		else if(fname==0 || lname==0 || accno==0 || cnfaccno==0 || amount==0 || bankname==0 || branchname==0 || ifsc==0 || chkbox==0){
			$('[name="btnNEXT"]').removeClass("grow");
			$('[name="btnNEXT"]')[0].disabled=true;
		}
	}
});