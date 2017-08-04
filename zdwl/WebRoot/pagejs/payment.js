//执行保存操作
function doSave() {
	var reg = /^(-?\d+)(\.\d+)?$/;

	if (!$("#jxs").combobox("getValue")) {
		alert("请选择经销商");
		return false;
	}

	if (!$("#paymentMoney").val()) {
		alert("应缴金额不能为空");
		return false;
	}
	if (!$("#actualPaymentMoney").val()) {
		alert("实际缴费金额不能为空");
		return false;
	}
	if(!getElement("payment.actualPaymentDate").value){
		alert("实际缴费时间不能为空");
		return false;
	}

	if (!reg.test($("#paymentMoney").val())) {
		alert("请输入正确的已缴费金额");
		return false;
	}
	if (!reg.test($("#actualPaymentMoney").val())) {
		alert("请输入正确的实际缴费金额");
		return false;
	}
	if($("[name=payment\\.isArrears]:checked").val()==2){
		$("#arrearsDate").val("");
		$("#arrearsMoney").val("");
	}
	
	// 提交表单
	document.forms[0].submit();
}
	
//执行返回列表操作
function doReturn() {
	history.go(-1);
}

//执行表单重置操作
function doReset() {
	document.forms[0].reset();
}


function init() {
	$("#jxs").combobox({
		onHidePanel : function() {// 验证输入的值，如果不存在下拉列表 则置空
			var newValue = $(this).combobox('getValue');
			var data = $(this).combobox('getData');
			var flag = false;
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (newValue == data[i].value) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				$(this).combobox('clear');
			}else{
				changeDealer(newValue);
			}

		}
	});
	var jsxValue = $("#jxs").combobox('getValue');
	if(jsxValue){
		changeDealer(jsxValue);
	}
	
}