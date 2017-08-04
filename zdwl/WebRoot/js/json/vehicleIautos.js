//为了避免jquery中的'$'与其它的'$'冲突,在此将jquery中的'$'重命名为'jQuery'
var jQuery = $;

//初始化数据
//jQuery(document).ready(function(){
	//getBrand();
//});

//取所有品牌
function getBrand(){
	//&callback=?"注意这个是为了解决跨域访问的问题.
	var url = "../json/brands.do?callback=?";
	jQuery.getJSON(url,{brandId : '1'},function(result){
		setBrand(result);
	});
}

//设置省份
function setBrand(result){
	var brand = document.getElementById("toBrand");
	clearSel(brand);
	var defaultSeries = new Option("请选择品牌...",-1);
	brand.options.add(defaultSeries);
	var year="";
	jQuery.each(result.data, function(i, vehicle){
		var text = vehicle.fld_serial;
		var typeid = vehicle.fld_makeid;
		if(year!=vehicle.fld_serialchar){
			year=vehicle.fld_serialchar;
			var group=document.createElement('OPTGROUP');  
			group.label = year;
			brand.appendChild(group);
		}
		var option = new Option(text,typeid);
		
		brand.options.add(option);
	});
	
}

//按上级ID取子地区
function getVehicle(name){
	
	if(name == 'series'){
		clearSel(document.getElementById("toSeries")); //清空车系
		clearSel(document.getElementById("toType"));//清空车型
		var brand = document.getElementById("toBrand");
		if(brand.options[brand.selectedIndex].value == "")
			return;
		var brandId = brand.options[brand.selectedIndex].value;
		if(brandId < 0){
			setSeries(null);
			return;
		}
		var url ="../json/seriess.do?callback=?";
		
		jQuery.getJSON(url,{brandId : brandId},function(result){
			setSeries(result);
		});
	}else if(name == 'type'){
		clearSel(document.getElementById("toType")); //清空车型
		var series = document.getElementById("toSeries");
		if(series.options[series.selectedIndex].value == "")
			return;
		var seriesName = series.options[series.selectedIndex].value;
		if(seriesName < 0){
			setType(null);
			return;
		}
		
		var url ="../json/types.do?callback=?";
		jQuery.getJSON(url,{seriesName : encodeURI(seriesName,"utf-8")},function(result){
			setType(result);
		});
	}
	
}
 
//当改变省份时设置城市
function setSeries(result){
	var series = document.getElementById("toSeries");
	clearSel(series);
	var defaultSeries = new Option("请选择车系...",-1);
	series.options.add(defaultSeries);

	var type = document.getElementById("toType");
	var defaultType = new Option("请选择车型...",-1);
	type.options.add(defaultType);
	var year="";
	jQuery.each(result.data, function(i, vehicle){
		var ss = vehicle.split("||");
		var text = ss[1];
		var typeid = ss[2];
		if(year!=ss[0]){
			year=ss[0];
			var group=document.createElement('OPTGROUP');  
			group.label = year;
			series.appendChild(group);
		}
		var option = new Option(text,typeid);
		series.options.add(option);
	});
	
}
  
//当改变城市时设置市区
function setType(result){
	
	var type = document.getElementById("toType");
	clearSel(type);
	var defaultType = new Option("请选择车型...",-1);
	type.options.add(defaultType);
	var year="";
	jQuery.each(result.data, function(i, vehicle){
		var ss = vehicle.split("||");
		var value = ss[1];
		var text = ss[1];
		var typeid = ss[2];
		if(year!=ss[0]){
			year=ss[0];
			value=value+"-"+year;
			var group=document.createElement('OPTGROUP');  
			group.label = year;
			type.appendChild(group);
		}
		var option = new Option(text,typeid);
		type.options.add(option);
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