//执行保存操作
function doSave() {

	if (!$("#jxs").combobox("getValue")) {
		alert("请选择经销商");
		return false;
	}
	if (!$("#refundMoney").val()) {
		alert("退还监管费不能为空");
		return false;
	}
	if (!$("#actualPayment").val()) {
		alert("实收监管费不能为空");
		return false;
	}
	if (!getElement("refund.actualPaymentDate").value) {
		alert("缴费时间不能为空");
		return false;
	}
	

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
			} else {
				changeDealer(newValue);
			}

		}
	});
	var jsxValue = $("#jxs").combobox('getValue');
	if (jsxValue) {
		changeDealer(jsxValue);
	}

}
