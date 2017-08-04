//根据省市区ID初始化
function initVehicle(brandId,sn,typeId){
	initBrand(brandId,sn);
	if(sn != ""){
		initSeries(brandId,sn);
	}
	if(sn != "" && typeId != ""){
		initType(sn,typeId);
	}
	
}
function initBrand(brandId,sn){
	
	var url1 = "../json/scarmodel.do?callback=?";
	var series;
	jQuery.getJSON(url1,{series : sn},function(result){
		jQuery.each(result.data, function(i, sc){
				series=sc;
			});
	});
	
	
	var url = "../json/brands.do?callback=?";
	jQuery.getJSON(url,{brandId : brandId},function(result){
		
		var brand = document.getElementById("toBrand");
		clearSel(brand);
		var defaultType = new Option("请选择品牌...",-1);
		brand.options.add(defaultType);
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
			if(text == series){
				option.selected = true;
			}
			brand.options.add(option);
		});
	});
}
function initSeries(brandId,sn){
	var url ="../json/seriess.do?callback=?";
		jQuery.getJSON(url,{brandId : brandId},function(result){
			var series = document.getElementById("toSeries");
			clearSel(series);
			var defaultType = new Option("请选择车系...",-1);
			series.options.add(defaultType);
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
				if(typeid == sn){
					option.selected = true;
				}
				series.options.add(option);
			});
		});
}
function initType(sn,typeId){
	var url ="../json/types.do?callback=?";
		jQuery.getJSON(url,{seriesName : encodeURI(sn,"utf-8")},function(result){
			var type = document.getElementById("toType");
			clearSel(type);
			var year="";
			jQuery.each(result.data, function(i, vehicle){
				var ss = vehicle.split("||");
				var value = ss[1];
				var text = ss[1];
				var typeidc = ss[2];
				if(year!=ss[0]){
					year=ss[0];
					value=value+"-"+year;
					var group=document.createElement('OPTGROUP');  
					group.label = year;
					type.appendChild(group);
				}
				var option = new Option(text,typeidc);
				if(typeidc == typeId){
					option.selected = true;
				}
				type.options.add(option);
			});
		});
}