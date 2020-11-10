$(document).ready(function() {
	var a=0;
	$(document).click(function(event) {
		if (!event.target.matches('#twoc2p2')){
			$('.dropdown').slideUp(500).removeClass('active');
		}
		else{
			if ($('.dropdown').hasClass("active") )
				$('.dropdown').slideUp(500).removeClass('active');
			else
				$('.dropdown').slideDown(500).addClass('active');
		}
	});
	$("#sixp1p1").click(function(){
		console.log("Working here");
		$('.six').fadeOut(500);
	});
	$("#sixp2p1").click(function(){
		console.log("Working here");
		$('.six').fadeOut(500);
	});
	$("#codefold").click(function(){
		if(a==0){
			a=1;
			$('.threep1c1').slideUp(500);
			$('#codefoldp1').fadeIn(0);
			$('#codefoldp2').fadeOut(0);
		}else{
			a=0;
			$('.threep1c1').slideDown(500);
			$('#codefoldp1').fadeOut(0);
			$('#codefoldp2').fadeIn(0);
		}
	});
});