//执行保存操作
function doSave(){
	if (!$("#jxs").combobox("getValue")) {
		alert("请选择经销商");
		return false;
	}
//	var agreement_num = document.getElementById("agreementBack.agreement_num").value;
//	if(agreement_num == ""){
//		alert("请填写协议编号");
//		return false;
//	}
	var agreement_date = document.getElementById("agreementBack.agreement_date").value;
	if(agreement_date == ""){
		alert("请填写协议邮寄时间");
		return false;
	}
	var send_address = document.getElementById("agreementBack.send_address").value;
	if(send_address == ""){
		alert("请填写邮寄地址");
		return false;
	}
	
	document.forms[0].submit();
}

// 执行表单重置操作
function doReset() {
	document.forms[0].reset();
}

// 执行返回列表操作
function doReturn() {
	history.go(-1);
}

// 页面初始化函数
function doLoad() {
	// 显示提示信息
	// showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
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

