function setDistrib(){
	var url ="http://www.cheyintong.com/json/distribs.do?callback=?";
	jQuery.getJSON(url,function(result){
		var distributor = document.getElementById("distributorId");
		clearSels(distributor);
		var defaultDistributor = new Option("请选择",-1);
		distributor.options.add(defaultDistributor);
		jQuery.each(result.data, function(i, distrib){
			var ss = distrib.split("||");
			var typeid = ss[0];
			var text = ss[1];
			var option = new Option(text,typeid);
			distributor.options.add(option);
		});
	});
}

// 清空下拉列表
function clearSels(oSelect){
	
	while(oSelect.childNodes.length>0){
		oSelect.removeChild(oSelect.childNodes[0]);
	}
	
}