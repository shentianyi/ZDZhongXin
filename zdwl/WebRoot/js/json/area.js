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
	
	var province = document.getElementById("toProvince");
	jQuery.each(result.data, function(i, area){
		var value = area.id;
		var text = area.name;
		var option = new Option(text,value);
		province.options.add(option);
	});
	
}

//按上级ID取子地区
function getArea(name){
	
	if(name == 'city'){
		clearSel(document.getElementById("toCity")); //清空城市
		clearSel(document.getElementById("toCounty"));//清空城区
		var province = document.getElementById("toProvince");
		if(province.options[province.selectedIndex].value == "")
			return;
		var areaId = province.options[province.selectedIndex].value;
		if(areaId < 0){
			setCity(null);
			return;
		}
		var url ="../json/area.do?callback=?";
		
		jQuery.getJSON(url,{areaId : areaId},function(result){
			setCity(result);
		});
	}else if(name == 'county'){
		clearSel(document.getElementById("toCounty")); //清空城区
		var city = document.getElementById("toCity");
		if(city.options[city.selectedIndex].value == "")
			return;
		var areaId = city.options[city.selectedIndex].value;
		if(areaId < 0){
			setCounty(null);
			return;
		}
		var url ="../json/area.do?callback=?";
		jQuery.getJSON(url,{areaId : areaId},function(result){
			setCounty(result);
		});
	}
	
}
 
//当改变省份时设置城市
function setCity(result){
	
	var city = document.getElementById("toCity");
	var defaultCity = new Option("请选择城市...",-1);
	city.options.add(defaultCity);
	var county = document.getElementById("toCounty");
	var defaultCounty = new Option("请选择城区...",-1);
	county.options.add(defaultCounty);
	jQuery.each(result.data, function(i, area){
		var value = area.id;
		var text = area.name;
		var option = new Option(text,value);
		city.options.add(option);
	});
	
}
  
//当改变城市时设置市区
function setCounty(result){
	
	var county = document.getElementById("toCounty");
	var defaultCounty = new Option("请选择城区...",-1);
	county.options.add(defaultCounty);
	jQuery.each(result.data, function(i, area){
		var value = area.id;
		var text = area.name;
		var option = new Option(text,value);
		county.options.add(option);
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