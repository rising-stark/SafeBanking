$(document).ready(function(){
	$('#trdate .fa-arrow-down').click(function(){
		$('#tramt .fa-sort-amount-desc').hide();
		$('#tramt .fa-sort-amount-asc').hide();
		$('#tramt .fa-arrow-up').show();
		$('#tramt .fa-arrow-down').show();
		
		$('#trdate .fa-arrow-down').hide();
		$('#trdate .fa-arrow-up').show();
		sortTable(6, 0);
	});
	$('#trdate .fa-arrow-up').click(function(){
		$('#tramt .fa-sort-amount-desc').hide();
		$('#tramt .fa-sort-amount-asc').hide();
		$('#tramt .fa-arrow-up').show();
		$('#tramt .fa-arrow-down').show();
		
		$('#trdate .fa-arrow-up').hide();
		$('#trdate .fa-arrow-down').show();
		sortTable(6, 1);
	});
	$('#tramt .fa-arrow-up, #tramt .fa-arrow-down').click(function(){
		$('#trdate .fa-arrow-down').show();
		$('#trdate .fa-arrow-up').show();
		
		$('#tramt .fa-arrow-up').hide();
		$('#tramt .fa-arrow-down').hide();
		$('#tramt .fa-sort-amount-desc').show();
		sortTable(7, 1);
	});
	$('#tramt .fa-sort-amount-desc').click(function(){
		$('#tramt .fa-sort-amount-desc').hide();
		$('#tramt .fa-sort-amount-asc').show();
		sortTable(7, 0);
	});
	$('#tramt .fa-sort-amount-asc').click(function(){
		$('#tramt .fa-sort-amount-asc').hide();
		$('#tramt .fa-sort-amount-desc').show();
		sortTable(7, 1);
	});
	function sortTable(n, dir) {
		  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
		  table = document.getElementById("fourc2p1");
		  switching = true;
		  /*Make a loop that will continue until
		  no switching has been done:*/
		  while (switching) {
		    //start by saying: no switching is done:
		    switching = false;
		    rows = table.rows;
		    /*Loop through all table rows (except the
		    first, which contains table headers):*/
		    for (i = 1; i < (rows.length - 1); i++) {
		      //start by saying there should be no switching:
		      shouldSwitch = false;
		      /*Get the two elements you want to compare,
		      one from current row and one from the next:*/
		      x = rows[i].getElementsByTagName("TD")[n];
		      y = rows[i + 1].getElementsByTagName("TD")[n];
		      /*check if the two rows should switch place,
		      based on the direction, asc or desc:*/
		      if(n==7){
	    		  var amt1=parseInt(x.innerHTML.split(" ")[0]);
	    		  var amt2=parseInt(y.innerHTML.split(" ")[0]);
		    	  if (dir == 0) {
				        if (amt1 > amt2) {
				          //if so, mark as a switch and break the loop:
				          shouldSwitch= true;
				          break;
				        }
				      } else {
				        if (amt1 < amt2) {
				          //if so, mark as a switch and break the loop:
				          shouldSwitch = true;
				          break;
				        }
				      }
		      }else if(n==6){
		    	  var yr1, yr2, mth1, mth2, day1, day2, hr1, hr2, min1, min2, sec1, sec2, msec1, msec2;
		    	  yr1=parseInt(x.innerHTML.substring(6, 10));
		    	  yr2=parseInt(y.innerHTML.substring(6, 10));
		    	  mth1=parseInt(x.innerHTML.substring(3, 5));
		    	  mth2=parseInt(y.innerHTML.substring(3, 5));
		    	  day1=parseInt(x.innerHTML.substring(0, 2));
		    	  day2=parseInt(y.innerHTML.substring(0, 2));
		    	  hr1=parseInt(x.innerHTML.substring(11, 13));
		    	  hr2=parseInt(y.innerHTML.substring(11, 13));
		    	  min1=parseInt(x.innerHTML.substring(14, 16));
		    	  min2=parseInt(y.innerHTML.substring(14, 16));
		    	  sec1=parseInt(x.innerHTML.substring(17, 19));
		    	  sec2=parseInt(y.innerHTML.substring(17, 19));
		    	  msec1=parseInt(x.innerHTML.substring(20, 23));
		    	  msec2=parseInt(y.innerHTML.substring(20, 23));
		    	  if (dir == 0) {
				        if (yr1 > yr2) {
				          //if so, mark as a switch and break the loop:
				          shouldSwitch= true;
				          break;
				        }else if(yr1 == yr2 && mth1>mth2){
				        	shouldSwitch= true;
					          break;
				        }else if(yr1 == yr2 && mth1==mth2 && day1>day2){
				        	shouldSwitch= true;
					          break;
				        }else if(yr1 == yr2 && mth1==mth2 && day1==day2 && hr1>hr2){
				        	shouldSwitch= true;
					          break;
				        }else if(yr1 == yr2 && mth1==mth2 && day1==day2 && hr1==hr2 && sec1>sec2){
				        	shouldSwitch= true;
					          break;
				        }else if(yr1 == yr2 && mth1==mth2 && day1==day2 && hr1==hr2 && sec1==sec2 && msec1>msec2){
				        	shouldSwitch= true;
					          break;
				        }
				      } else if(dir==1){
				    	  if (yr1 < yr2) {
					          //if so, mark as a switch and break the loop:
					          shouldSwitch= true;
					          break;
					        }else if(yr1 == yr2 && mth1<mth2){
					        	shouldSwitch= true;
						          break;
					        }else if(yr1 == yr2 && mth1==mth2 && day1<day2){
					        	shouldSwitch= true;
						          break;
					        }else if(yr1 == yr2 && mth1==mth2 && day1==day2 && hr1<hr2){
					        	shouldSwitch= true;
						          break;
					        }else if(yr1 == yr2 && mth1==mth2 && day1==day2 && hr1==hr2 && sec1<sec2){
					        	shouldSwitch= true;
						          break;
					        }else if(yr1 == yr2 && mth1==mth2 && day1==day2 && hr1==hr2 && sec1==sec2 && msec1<msec2){
					        	shouldSwitch= true;
						          break;
					        }
				      }
		      	 }
		    }
		    if (shouldSwitch) {
		      /*If a switch has been marked, make the switch
		      and mark that a switch has been done:*/
		      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
		      x = rows[i].getElementsByTagName("TD")[0].innerHTML;
		      y = rows[i + 1].getElementsByTagName("TD")[0].innerHTML;
		      rows[i].getElementsByTagName("TD")[0].innerHTML = y;
		      rows[i+1].getElementsByTagName("TD")[0].innerHTML = x;
		      switching = true;
		      //Each time a switch is done, increase this count by 1:
		      switchcount ++;      
		    } else {
		      /*If no switching has been done AND the direction is "asc",
		      set the direction to "desc" and run the while loop again.*/
		      if (switchcount == 0 && dir == 0) {
		        dir = 1;
		        switching = true;
		      }
		    }
		  }
		}
});