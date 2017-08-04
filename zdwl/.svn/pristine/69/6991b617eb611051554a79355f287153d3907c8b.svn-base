//执行保存操作
function doSave() {

	if (!$("#jxs").combobox("getValue")) {
		alert("请选择经销商");
		return false;
	}
	var value = document.getElementById("invoice.paymentAmount").value;
	if (value == "") {
		alert("请填写缴费金额");
		return false;
	}
	var value = document.getElementById("invoice.office").value;
	if (value == "") {
		alert("请填写开票单位");
		return false;
	}
	var value = document.getElementById("invoice.applicant").value;
	if (value == "") {
		alert("请填写申请人");
		return false;
	}
	var value = document.getElementById("invoice.companyname").value;
	if (value == "") {
		alert("请填写公司名称");
		return false;
	}
	var value = document.getElementById("invoice.bankaccount").value;
	if (value == "") {
		alert("请填写开户行");
		return false;
	}
	var value = document.getElementById("invoice.accountnum").value;
	if (value == "") {
		alert("请填写账号");
		return false;
	}
	var value = document.getElementById("invoice.taxpayernum").value;
	if (value == "") {
		alert("请填写纳税人识别号");
		return false;
	}
	var value = document.getElementById("invoice.registeraddress").value;
	if (value == "") {
		alert("请填写纳税人注册地址");
		return false;
	}
	// 提交表单
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