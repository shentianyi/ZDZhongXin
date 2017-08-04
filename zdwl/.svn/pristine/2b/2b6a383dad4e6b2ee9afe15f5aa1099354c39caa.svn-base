//页面初始化函数
function doLoadArea() {
	initProvince();
	var provincehiId = $("#provincehiId").val();
	var cityhiId = $("#cityhiId").val();
	var areaiId = $("#areaiId").val();

	if (provincehiId && provincehiId != "" && provincehiId > 0) {
		$("#provinceId").val(provincehiId);
	}
	if (cityhiId && cityhiId != "" && cityhiId > 0) {
		changeProvince(provincehiId, $("#cityId"));
		$("#cityId").val(cityhiId);
	}
	if (areaiId && areaiId != "" && areaiId > 0) {
		changeCity(cityhiId, $("#areaiId"));
		$("#cityId").val(areaiId);
	}
}

function changeCityE(id,nextSelect){
	if(id==-1){
		return;
	}
	var url = "/json/findRegionByParentId.do?callback=?&parentId="+id;
	$.ajax({
		url:url,
		async:false,
		dataType:"jsonp",
		success:function(result){
			var data = result.data;
			setCity(data,nextSelect);
		}
	});
}

function setCity(city,nextSelect){
	nextSelect.html("");
	var option = "<option value='-1' >请选择</option>";
	nextSelect.append(option);
	for(var i=0;i<city.length;i++){
		var option = "<option value="+city[i].id+">" + city[i].name
		+ "</option>";
		nextSelect.append(option);
	}
}
	