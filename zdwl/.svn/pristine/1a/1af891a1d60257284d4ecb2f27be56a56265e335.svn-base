//为了避免jquery中的'$'与其它的'$'冲突,在此将jquery中的'$'重命名为'jQuery'
var jQuery = $;

//初始化数据
//jQuery(document).ready(function(){
	//getProvince();
//});

//取所有省份
function getProvince(){
	//&callback=?"注意这个是为了解决跨域访问的问题.
	var url = "../json/area.do?callback=?";
	jQuery.getJSON(url,{areaId : '1'},function(result){
		setProvince(result);
	});
}

//设置省份
function setProvince(result){
	
	var province = document.getElementById("province");
	jQuery.each(result.data, function(i, area){
		if(i>6){
			$("#province").css({height:"132px",overflow:"hidden",overflowY:"scroll"});
		}
		var value = area.id;
		var text = area.name;
		$("#province").append("<li id='"+value+"' class='' onclick='provinceChange("+value+")'>"+text+"</li>");
	});
}

function provinceChange(id){
	getArea("city",id);
}
function cityChange(id){
	getArea("county",id);
}


//按上级ID取子地区
function getArea(name,id){
	if(name == 'city'){
		var cityMsg = document.getElementById("show_city"); //清空城市展示
		cityMsg.innerHTML="请选择公司所在市";
		var countyMsg = document.getElementById("show_county");//清空城区展示
		countyMsg.innerHTML="请选择公司所在区县";
		var tocity = document.getElementById("toCity"); //清空城市隐藏信息
		tocity.value="";
		var toCounty = document.getElementById("toCounty");//清空城区隐藏信息
		toCounty.value="";
		var city = document.getElementById("city");
		city.innerHTML="";
		var county = document.getElementById("county");
		county.innerHTML="";
		
		var url ="../json/area.do?callback=?";
		
		jQuery.getJSON(url,{areaId : id},function(result){
			setCity(result);
		});
	}else if(name == 'county'){
		var countyMsg = document.getElementById("show_county");//清空城区展示
		countyMsg.innerHTML="请选择公司所在区县";
		var toCounty = document.getElementById("toCounty");//清空城区隐藏信息
		toCounty.value="";
		var county = document.getElementById("county");
		county.innerHTML="";
		var url ="../json/area.do?callback=?";
		jQuery.getJSON(url,{areaId : id},function(result){
			setCounty(result);
		});
	}
	
}
 
//当改变省份时设置城市
function setCity(result){
	var city = document.getElementById("city");
	jQuery.each(result.data, function(i, area){
		if(i>6){
			$("#city").css({height:"132px",overflow:"hidden",overflowY:"scroll"});
		}
		var value = area.id;
		var text = area.name;
		$("#city").append("<li id='"+value+"' class='' onclick='cityChange("+value+")'>"+text+"</li>");
	});
	
}
  
//当改变城市时设置市区
function setCounty(result){
	var county = document.getElementById("county");
	jQuery.each(result.data, function(i, area){
		if(i>6){
			$("#county").css({height:"132px",overflow:"hidden",overflowY:"scroll"});
		}
		var value = area.id;
		var text = area.name;
		$("#county").append("<li id='"+value+"' class=''>"+text+"</li>");
	});
	
}

// 清空下拉列表
function clearSel(oSelect){
	
	while(oSelect.childNodes.length>0){
		oSelect.removeChild(oSelect.childNodes[0]);
	}
	
}

function initArea(){
	
}