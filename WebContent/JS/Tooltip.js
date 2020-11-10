$(document).ready(function() {
	$("img[id$=img]").click(function(){
		var alt = $(this).attr('alt');
		var item=null;
		if(alt=="wrong"){
			item = $(this).parent().next().children("span")[0];
		}else if(alt=="tick"){
			item = $(this).parent().next().children("span")[1];
		}
		$(item).slideToggle(250);
	});
	$("img[id$=img]").on('mouseover', function(){
		var alt = $(this).attr('alt');
		var item=null;
		if(alt=="wrong"){
			item = $(this).parent().next().children("span")[0];
		}else if(alt=="tick"){
			item = $(this).parent().next().children("span")[1];
		}
		$(item).show(250);
	});
	$(".wrong, .tick").click(function(){
		$(this).hide(250);
	});
});