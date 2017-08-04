//为了避免jquery中的'$'与其它的'$'冲突,在此将jquery中的'$'重命名为'jQuery'
var jQuery = $;

//初始化数据
jQuery(document).ready(function(){
	getBrand();
});

//取品牌
function getBrand(){
	//&callback=?"注意这个是为了解决跨域访问的问题.
	var url = "../json/brand.do?callback=?";
	jQuery.getJSON(url,null,function(result){
		setBrand(result);
	});
}

//设置品牌
function setBrand(result){
	
	var province = document.getElementById("toBrand");
	jQuery.each(result.data, function(i, brand){
		var value = brand.id;
		var text = brand.brandName;
		var option = new Option(text,value);
		province.options.add(option);
	});
	
}

//按上级ID取子信息
function getArea(name){
	
	if(name == 'series'){
		clearSel(document.getElementById("toSeries")); //清空车系
		var brand = document.getElementById("toBrand");
		if(brand.options[brand.selectedIndex].value == "")
			return;
		var brandId = brand.options[brand.selectedIndex].value;
		var url ="../json/series.do?callback=?";
		
		jQuery.getJSON(url,{brandId : brandId},function(result){
			setSeries(result);
		});
	}else if(name == 'vehicletype'){
		clearSel(document.getElementById("toVehicletype")); //清空城区
		var series = document.getElementById("toSeries");
		if(series.options[series.selectedIndex].value == "")
			return;
		var seriesId = series.options[series.selectedIndex].value;
		var url ="../json/vehicletype.do?callback=?";
		jQuery.getJSON(url,{seriesId : seriesId},function(result){
			setVehicletype(result);
		});
	}
	
}
 
//当改变品牌时设置车系
function setSeries(result){
	
	var series = document.getElementById("toSeries");
	jQuery.each(result.data, function(i, series){
		var value = series.id;
		var text = series.seriesName;
		var option = new Option(text,value);
		series.options.add(option);
	});
	
}
  
//当改变车系时设置车型
function setVehicletype(result){
	
	var county = document.getElementById("toVehicletype");
	jQuery.each(result.data, function(i, vehicletype){
		var value = vehicletype.id;
		var text = vehicletype.typeName;
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
