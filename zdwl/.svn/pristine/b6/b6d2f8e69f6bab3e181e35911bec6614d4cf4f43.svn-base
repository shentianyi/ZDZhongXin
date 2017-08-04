$(function() {
		$("#submitTimeBegin").datebox({
			editable : false
		});
		$("#submitTimeEnd").datebox({
			editable : false
		});
 });

	//页面初始化函数
	function doLoad() {
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
		
		
	}

	//执行查询操作
	function doQuery() {
		if ($("#one").val() > -1) {
			if ($("#two").val() == -1) {
				alert("请选择分行！");
				return;
			}
		}
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		$("#one").val(-1);
		$("#two").val(-1);
		$("#three").val(-1);
		$("#provinceId").val(-1);
		$("#cityId").val(-1);
		$("[name='query.dealerName']").val("");
		$("[name='query.planCode']").val("");
		$('#submitTimeBegin').datebox('clear');
		$('#submitTimeEnd').datebox('clear');
	}

	//进入修改页面
	function goUpd(id) {
		location = "/videoReport.do?method=reportEntry&videoReport.id=" + id;
	}
	
	//进入详情页面
	function detail(id) {
		location = "/videoReport.do?method=getDetail&id="+ id;
    }
	
	//已完成列表
	function completed() {
		location.href = "/videoReport.do?method=findCompletedList";
	}
	
	
	//已完成列表
	function progress() {
		location.href = "/videoReport.do?method=findProgressList";
	}