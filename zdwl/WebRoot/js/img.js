
function show(id){
	var mydiv = document.getElementById('mydiv'+id);
	mydiv.style.display = "block";
	mydiv.style.left = event.x - 5;
	mydiv.style.top = event.y + 5;
	//mydiv.innerText = txt; 
}

function hide(id){
	var mydiv = document.getElementById('mydiv'+id);
	mydiv.style.display = "none";
}