//图片放大和缩小（兼容IE和火狐，谷歌）
var num;
function ImageChange(args,num,id) {
	var oImg,imgw,imgh;
	if(num == 1){
		oImg = document.getElementById("pic_"+id);
		imgw = 780;
		imgh = 420;
	}
	if(num == 2){
		oImg = document.getElementById("picsmall");
		imgw = 200;
		imgh = 167;		
	}
	if (args) {
		oImg.width = oImg.width * 1.2;
		oImg.height = oImg.width / 1.2;
		//缩小时初始化图片位置
		if(oImg.width<=imgw && oImg.height<=imgh){
			oImg.style.top = (imgh- oImg.height)/2 + "px";  
			oImg.style.left = (imgw- oImg.width)/2 + "px"; 	
		}else {
			if(oImg.offsetLeft>0 && oImg.offsetTop>0){
				oImg.style.top = 0;   
				oImg.style.left = 0;
			}
		}
	}else {
		oImg.width = oImg.width / 1.2;
		oImg.height = oImg.height / 1.2;
		//缩小时初始化图片位置
		if(oImg.width<=imgw && oImg.height<=imgh){
			oImg.style.top = (imgh- oImg.height)/2 + "px";  
			oImg.style.left = (imgw- oImg.width)/2 + "px"; 	
		}else {
			oImg.style.top = 0;   
			oImg.style.left = 0;	
		}
	}
}

//还原图片大小
function ImageDefault(num,id){
	var dImg,imgw,imgh;
	if(num == 1){
		dImg = document.getElementById("pic_"+id);
		imgw = 780;
		imgh = 420;
	}
	if(num == 2){
		dImg = document.getElementById("picsmall");
		imgw = 200;
		imgh = 167;		
	}
	dImg.width = imgw;
	dImg.height = imgh;
	dImg.style.top = 0;
	dImg.style.left = 0;
}

//获取大图的四个顶点坐标
function getDivPosition(id){
	var odiv = document.getElementById(id);
    var xLeft,xRigh,yTop,yBottom;
    return {
		xLeft:odiv.getBoundingClientRect().left,
		xRigh:odiv.getBoundingClientRect().left+600, 
		yTop:odiv.getBoundingClientRect().top,
		yBottom:odiv.getBoundingClientRect().top+410
	 };
}

//获取小图的四个顶点坐标
function getDivPositionSmall(){
	var odiv = document.getElementById("picsmall");
    var xLeft,xRigh,yTop,yBottom;
    return {
		xLeft:odiv.getBoundingClientRect().left,
		xRigh:odiv.getBoundingClientRect().left+200, 
		yTop:odiv.getBoundingClientRect().top,
		yBottom:odiv.getBoundingClientRect().top+167
	 };
}

//获取鼠标坐标
function mousePos(e){
	var x,y;
	var e = e||window.event;
	return {
		x:e.clientX+document.body.scrollLeft+document.documentElement.scrollLeft,
		y:e.clientY+document.body.scrollTop+document.documentElement.scrollTop
	};
};

//在固定div层拖动图片
var ie = document.all;
var nn6 = document.getElementById && !document.all;
var isdrag = false;
var y, x;
var oDragObj;

//大图鼠标移动，拖拽图片
function moveMouse(e) {
	//鼠标的坐标
	mousePos(e).x;
	mousePos(e).y;
	
	var id = e.target.getAttribute('id');
	
	//div的四个顶点坐标
	getDivPosition(id).xLeft
	getDivPosition(id).xRigh
	getDivPosition(id).yTop
	getDivPosition(id).yBottom
	
	//获取放大或缩小后的图片尺寸
	var oImgaftermove = document.getElementById(id);
	
	if(isdrag){
		//图片在区域内可以拖动的判断
		if(nn6){
			if((nTX + e.clientX - x)<=0 && (nTX + e.clientX - x)>(600-oImgaftermove.width)){
				oDragObj.style.left = (nTX + e.clientX - x) + "px";
			}
			if((nTY + e.clientY - y)<=0 && (nTY + e.clientY - y)>(410-oImgaftermove.height)){
				oDragObj.style.top = (nTY + e.clientY - y) + "px";
			}	
		}else {
			if((nTX + event.clientX - x)<=0 && (nTX + event.clientX - x)>(600-oImgaftermove.width)){
				oDragObj.style.left = (nTX + event.clientX - x) + "px";
			}
			if((nTY + event.clientY - y)<=0 && (nTY + event.clientY - y)>(410-oImgaftermove.height)){
				oDragObj.style.top = (nTY + event.clientY - y) + "px";
			}		
		}
		return false;
	}
}

//小图鼠标移动，拖拽图片
function moveMouseSmall(e) {	
	//鼠标的坐标
	mousePos(e).x;
	mousePos(e).y;
	
	//div的四个顶点坐标
	getDivPositionSmall().xLeft
	getDivPositionSmall().xRigh
	getDivPositionSmall().yTop
	getDivPositionSmall().yBottom
	
	//获取放大或缩小后的图片尺寸
	var oImgaftermove = document.getElementById("picsmall");
	if(isdrag){
		//图片在区域内可以拖动的判断
		if(nn6){
			if((nTX + e.clientX - x)<=0 && (nTX + e.clientX - x)>(200-oImgaftermove.width)){
				oDragObj.style.left = (nTX + e.clientX - x) + "px";
			}
			if((nTY + e.clientY - y)<=0 && (nTY + e.clientY - y)>(167-oImgaftermove.height)){
				oDragObj.style.top = (nTY + e.clientY - y) + "px";
			}	
		}else {
			if((nTX + event.clientX - x)<=0 && (nTX + event.clientX - x)>(200-oImgaftermove.width)){
				oDragObj.style.left = (nTX + event.clientX - x) + "px";
			}
			if((nTY + event.clientY - y)<=0 && (nTY + event.clientY - y)>(167-oImgaftermove.height)){
				oDragObj.style.top = (nTY + event.clientY - y) + "px";
			}		
		}
		return false;
	}
}

//鼠标按下才初始化
function initDrag(e) {
	var oDragHandle = nn6 ? e.target : event.srcElement;
	var topElement = "HTML";
	while (oDragHandle.tagName != topElement && oDragHandle.className != "boximg") {
		oDragHandle = nn6 ? oDragHandle.parentNode : oDragHandle.parentElement;
	}
	if (oDragHandle.className == "boximg") {
		isdrag = true;
		oDragObj = oDragHandle;
		nTY = parseInt(oDragObj.style.top + 0);
		y = nn6 ? e.clientY : event.clientY;
		nTX = parseInt(oDragObj.style.left + 0);
		x = nn6 ? e.clientX : event.clientX;
		var classN = oDragHandle.parentNode.className;
		if(classN=="box_img"){
			document.onmousemove = moveMouse;
		}
		if(classN=="box_right"){
			document.onmousemove = moveMouseSmall;
		}
		return false;
	}
}

document.onmousedown = initDrag;
document.onmouseup = new Function("isdrag=false");