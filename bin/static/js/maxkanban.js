function allowDrop(ev) {
  ev.preventDefault();
}

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  ev.target.appendChild(document.getElementById(data));
}


function postPosition() {
	
	document.getElementById("drag1").onmouseout= function(){
	
	var laPosition = document.getElementById("drag1").parentNode.id;
	
	var envois = {}
	envois["place"] = laPosition;
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/postPosition",
		data : JSON.stringify(envois),
		dataType : 'json'
							
	});
	}
}

postPosition()




