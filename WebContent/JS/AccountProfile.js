$(document).ready(function(){
	window.localStorage.removeItem("minutes");
    window.localStorage.removeItem("seconds");
    console.log("localStorage on AccountProfile.jsp ready"+window.localStorage);
    
    $('#fourp2p4 a').click(function(){
		$.ajax({
			type: 'POST',
			url: 'AccountProfile',
			data:{
				action: 'checkBalance'
			},
			cache:false,
			success: function(result){
				$('#fourp2p4 a').html("Your account balance is <em><u>"+result+"</u></em> INR");
			},
			fail: function(){
				alert("OOPS!! Server not responding");
			}
		});
	});
	/*$('#fourp2p2 a').click(function(){
		$('.five').fadeIn(100).delay(5500).fadeOut(500);
		$.ajax({
			type: "POST",
			url: 'ChangePassword',
			data:{
				action: "FirstTime"
			},
			cache: false,
			success: function(result){
				if(result==1){
					window.location.href="ChangePassword.jsp";
				}
				else
					alert("OOPS!! Something Went Wrong");
			}
		});
	});*/
});