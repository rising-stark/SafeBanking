$(document).ready(function(){
	
	setPageTime();
	$('[name="fname"],[name="lname"],[name="acc"],[name="cnfacc"],[name="banktransferlimit"],[name="ifsc"]').on("focus",function(){
		$('[name="temp"]').val(getTimeInMilliseconds());
	});
	$('[name="chkbox"]').click(function(){
		$('[name="chktime"]').val(getTimeStamp());
	});
	
	$('[name="fname"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="ftime"]').val(parseInt($('[name="ftime"]').val())+time-$('[name="temp"]').val());
		//console.log("blur time "+time);
	});
	$('[name="lname"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="ltime"]').val(parseInt($('[name="ltime"]').val())+time-$('[name="temp"]').val());
	});
	
	$('[name="acc"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="acctime"]').val(parseInt($('[name="acctime"]').val())+time-$('[name="temp"]').val());
	});
	$('[name="cnfacc"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="cnfacctime"]').val(parseInt($('[name="cnfacctime"]').val())+time-$('[name="temp"]').val());
	});
	$('[name="banktransferlimit"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="limittime"]').val(parseInt($('[name="limittime"]').val())+time-$('[name="temp"]').val());
	});
	$('[name="ifsc"]').on("blur", function() {
		var time=getTimeInMilliseconds();
		$('[name="ifsctime"]').val(parseInt($('[name="ifsctime"]').val())+time-$('[name="temp"]').val());
	});
	
	$('[name="branchnamelist"]').click( function() {
		var t=parseInt($('[name="temp1"]').val());
		var time=getTimeInMilliseconds();
		if(t==0){
			$('[name="temp1"]').val(time);
		}else{
			$('[name="branchnametime"]').val(parseInt($('[name="branchnametime"]').val())+time-$('[name="temp1"]').val());
			$('[name="temp1"]').val(0);
		}
	});
	
	$('[name="banknamelist"]').click( function() {
		var t=parseInt($('[name="temp2"]').val());
		var time=getTimeInMilliseconds();
		if(t==0){
			$('[name="temp2"]').val(time);
		}else{
			$('[name="banknametime"]').val(parseInt($('[name="banknametime"]').val())+time-$('[name="temp2"]').val());
			$('[name="temp2"]').val(0);
		}
	});
	
	function setPageTime(){
		$('[name="benaddpagetime"]').val(getTimeStamp());
		$.ajax({
			type: 'POST',
			url: 'AddBeneficiary',
			data:{
				action: 'setPageTime',
				pagetime: $('[name="benaddpagetime"]').val()
			},
			cache:false
		});
	}
	
	function getTimeInMilliseconds() {
		var now = new Date();
	    return (now.getTime());
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