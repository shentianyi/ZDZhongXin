function initBank() {
	/*var id = document.getElementById("clientType").value;
	if(id == null || id == "undefind"){
		id = -1;
	}*/
	var id = -1;
	loadSelect(id, $("#one"));
	
	$("#one").change(function() {
		$("#two option:gt(0)").remove();
		$("#three option:gt(0)").remove();
		var id = this.value;
		if (id>0) {
			loadSelect(id, $("#two"));
			setBank(id);
		}else{
			setBank("");
			
			//清空下级银行内容
			$("#two").val(-1);
			$("#three").val(-1);
		}
	});
	$("#two").change(function() {
		$("#three option:gt(0)").remove();
		var id = this.value;
		if (id>0) {
			loadSelect(id, $("#three"));
			setBank(id);
		}else{
			setBank($("#one").val());
			$("#three").val(-1);
		}
	});
	$("#three").change(function(){
		var id = this.value;
		if (id>0) {
			setBank(id);
		}else{
			setBank($("#two").val());
		}
	});
	
}
function setBank(id,name){
	if(id!=-1){
		$("#financeId").val(id);
	}else{
		$("#financeId").val("");
	}
}

function loadSelect(id, nextSelect) {
	var url = "../json/getBankChildById.do?method=getBank&callback=?&bankQuery.id="
			+ id;
	$.ajax({
		url:url,
		async:false,
		dataType:"jsonp",
		success:function(result){
			var data = result.data;
			$.each(data, function(i, item) {
				var option = "<option value="+item.id+">" + item.bankName
						+ "</option>";
				nextSelect.append(option);
			});
		}
	});
}
function initProvince(){
	var url = "/json/findRegionByParentId.do?callback=?&parentId=0";
	$.ajax({
		url:url,
		async:false,
		dataType:"jsonp",
		success:function(result){
			var data = result.data;
			setProvince(data);
		}
	});
}
function setProvince(province){
	var provinces = $("#provinceId");
	for(var i=0;i<province.length;i++){
		var option = "<option value="+province[i].id+">" + province[i].name
		+ "</option>";
		provinces.append(option);
	}
}
function changeProvince(id,nextSelect){
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


//县
function changeCity(id,nextSelect){
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
			setCounty(data,nextSelect);
		}
	});
}

function setCounty(county,nextSelect){
	nextSelect.html("");
	var option = "<option value='-1' >请选择</option>";
	nextSelect.append(option);
	for(var i=0;i<county.length;i++){
		var option = "<option value="+county[i].id+">" + county[i].name
		+ "</option>";
		nextSelect.append(option);
	}
}
