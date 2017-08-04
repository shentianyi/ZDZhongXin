$(function() {
		$("#submitTimeBegin").datebox({
			editable : false
		});
		$("#submitTimeEnd").datebox({
			editable : false
		});
 });

//页面初始化函数
function doLoad(fag) {
		initBank();
		initProvince();
		var onehiId = $("#onehiId").val();
		var twohiId = $("#twohiId").val();
		var threehiId = $("#threehiId").val();
		var provincehiId = $("#provincehiId").val();
		var cityhiId = $("#cityhiId").val();

		if (onehiId && onehiId != "" && onehiId > 0) {
			$("#one").val(onehiId);
		}
		if (twohiId && twohiId != "" && twohiId > 0) {
			loadSelect(onehiId, $("#two"));
			$("#two").val(twohiId);
		}
		if (threehiId && threehiId != "" && threehiId > 0) {
			loadSelect(twohiId, $("#three"));
			$("#three").val(threehiId);
		}
		if (provincehiId && provincehiId != "" && provincehiId > 0) {
			$("#provinceId").val(provincehiId);
		}
		if (cityhiId && cityhiId != "" && cityhiId > 0) {
			changeProvince(provincehiId, $("#cityId"));
			$("#cityId").val(cityhiId);
		}
      
		if(fag){
			var dangerhiId = $("#dangerhiId").val();
			var dealerhiId = $("#dealerhiId").val();
			var supervisorhiId = $("#supervisorhiId").val();
		 	if (dangerhiId && dangerhiId != "" && dangerhiId > 0) {
	            $("#danger_level").val(dangerhiId);
			}

			if (dealerhiId && dealerhiId != "" && dealerhiId > 0) {
				$("#dealer_level").val(dealerhiId);
			}

			if (supervisorhiId && supervisorhiId != "" && supervisorhiId > 0) {
				$("#supervisor_level").val(supervisorhiId);
			} 
        }
		
}

//执行查询操作	
function doQuery() {
		if ($("#one").val() > -1) {
			if ($("#two").val() == -1) {
				alert("请选择分行！");
				return;
			}
		}
		$("#method").val("reportLedger");
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear(fag) {
		$("#one").val(-1);
		$("#two").val(-1);
		$("#three").val(-1);
		$("#provinceId").val(-1);
		$("#cityId").val(-1);
		$('#submitTimeBegin').datebox('clear');
		$('#submitTimeEnd').datebox('clear');
		if(fag){
			$("#danger_level").val(-1);
			$("#dealer_level").val(-1);
			$("#supervisor_level").val(-1);
			$("[name='dealerName']").val("");
			$("[name='planCode']").val("");
			$("[name='brandName']").val("");
		}else{
			$("[name='query.dealerName']").val("");
			$("[name='query.planCode']").val("");
			$("[name='query.videoUserName']").val("");
			$("[name='query.brandName']").val("");	
		}
	}

	