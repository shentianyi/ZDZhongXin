//页面初始化函数
function doLoad() {
		initProvince();
		var provincehiId = $("#provincehiId").val();
		var cityhiId = $("#cityhiId").val();

		if (provincehiId && provincehiId != "" && provincehiId > 0) {
			$("#provinceId").val(provincehiId);
		}
		if (cityhiId && cityhiId != "" && cityhiId > 0) {
			changeProvince(provincehiId, $("#cityId"));
			$("#cityId").val(cityhiId);
		}
	}

	//执行查询操作
	function doQuery() {
        document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		$("#provinceId").val(-1);
		$("#cityId").val(-1);
		$("[name='query.dealerName']").val("");
		$("[name='query.planCode']").val("");
	}

	//进入修改页面
	function goUpd(id) {
		location = "/windcontrol/inspection.do?method=inspectionEntry&inspectionId=" + id;
	}
	function goUpd(id,name) {
		location = "/windcontrol/inspection.do?method=inspectionEntry&inspectionId=" + id
		+"&inspectionName="+name;
	}
	
	//进入详情页面
	function detail(id) {
		location = "/windcontrol/inspection.do?method=detailsEntry&id=" + id;
	}
	

	//已完成列表
	function completed() {
		location.href = "/windcontrol/inspection.do?method=findCompletedList";
	}
	
	
	//未完成列表
	function progress() {
		location.href = "/windcontrol/inspection.do?method=findProgressList";
	}
	
	