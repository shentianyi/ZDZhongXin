//根据省市区ID初始化
function initVehicle(brandId,sn,typeId){
	
	//初始化省
	var url = "../json/brand.do?callback=?";
	jQuery.getJSON(url,{brandId : '1'},function(result){
		
		var brand = document.getElementById("toBrand");
		jQuery.each(result.data, function(i, vehicle){
			var value = vehicle.id;
			var text = vehicle.brandName;
			var option = new Option(text,value);
			
			if(value == brandId){
				option.selected = true;
			}
			brand.options.add(option);
		});
		getVehicle('series');
		
	});
	
	//初始化市
	if(sn != ""){
		var url ="../json/series.do?callback=?";
		jQuery.getJSON(url,{brandId : brandId},function(result){
			var series = document.getElementById("toSeries");
			var placeOrigin = "";
			jQuery.each(result.data, function(i, vehicle){
				var ss = vehicle.split("-");
				var value = ss[0];
				var text = ss[0];
				if(placeOrigin!=ss[1]){
					placeOrigin=ss[1];
					value=value+"-"+placeOrigin;
					var group=document.createElement('OPTGROUP');  
					group.label = placeOrigin;
					series.appendChild(group);
				}
				var option = new Option(text,value);
				if(value == sn){
					option.selected = true;
				}
				series.options.add(option);
			});
		});
		
	}
	
	//初始化区
	if(typeId > 0){
		var url ="../json/type.do?callback=?";
		jQuery.getJSON(url,{seriesName : encodeURI(sn,"utf-8")},function(result){
			var type = document.getElementById("toType");
			jQuery.each(result.data, function(i, vehicle){
				var value = vehicle.id;
				var text = vehicle.typeCode;
				var option = new Option(text,value);
				if(value == typeId){
					option.selected = true;
				}
				type.options.add(option);
			});
		});
	}
	
}