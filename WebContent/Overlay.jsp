<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
#overlay {
  position: fixed;
  display: none;
  width: 100%;
  height: 100%;
  top: 10%;
  left: 10%;
  right: 0;
  bottom: 0;
  background-color: rgba(0,0,0,0.5);
  z-index: 2;
  cursor: pointer;
}

#text{
  position: absolute;
  top: 50%;
  left: 50%;
  font-size: 50px;
  color: white;
  transform: translate(-50%,-50%);
  -ms-transform: translate(-50%,-50%);
}
</style>
</head>
<body>

<div id="overlay" onclick="off()">
  <div id="text">
  	<h2>Overlay text</h2>
  	<div class="twop2c1c1">
		<input id="radiop1" type="radio" name="site_key" style="position: absolute">
		<img id="imgp1" width="100px" height="100px" src="img/1.jpg" alt="Image1">
	</div>
	<div class="twop2c1c1">
		<input id="radiop1" type="radio" name="site_key">
		<img id="imgp2" width="100px" height="100px" src="img/2.jpg" alt="Image2">
	</div>
  </div>
</div>

<div style="padding:20px">
  <h2>Overlay with Text</h2>
  <button onclick="on()">Turn on overlay effect</button>
</div>

<script>
function on() {
  document.getElementById("overlay").style.display = "block";
}

function off() {
  document.getElementById("overlay").style.display = "none";
}
</script>
   
</body>
</html> 
