//根据省市区ID初始化
function initArea(provinceId,cityId,countyId){
	//初始化省
	var url = "../json/area.do?callback=?";
	jQuery.getJSON(url,{areaId : '1'},function(result){
		
		var province = document.getElementById("toProvince");
		jQuery.each(result.data, function(i, area){
			var value = area.id;
			var text = area.name;
			var option = new Option(text,value);
			if(value == provinceId){
				option.selected = true;
			}
			province.options.add(option);
		});
		
	});
	
	//初始化市
	if(provinceId > 0){
		var url ="../json/area.do?callback=?";
		jQuery.getJSON(url,{areaId : provinceId},function(result){
			var city = document.getElementById("toCity");
			jQuery.each(result.data, function(i, area){
				var value = area.id;
				var text = area.name;
				var option = new Option(text,value);
				if(value == cityId){
					option.selected = true;
				}
				city.options.add(option);
			});
		});
	}
	
	
	//初始化区
	if(cityId > 0){
		var url ="../json/area.do?callback=?";
		jQuery.getJSON(url,{areaId : cityId},function(result){
			var county = document.getElementById("toCounty");
			jQuery.each(result.data, function(i, area){
				var value = area.id;
				var text = area.name;
				var option = new Option(text,value);
				if(value == countyId){
					option.selected = true;
				}
				county.options.add(option);
			});
		});
	}
	
}