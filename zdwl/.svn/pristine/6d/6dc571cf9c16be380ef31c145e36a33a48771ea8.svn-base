//执行保存操作
function doSave() {
	if (!$("#jxs").combobox("getValue")) {
		alert("请选择经销商");
		return false;
	}
	if (!getElement("mc.changeDate").value) {
		alert("请选择变更时间");
		return false;
	}
	if (!getElement("mc.createDate").value) {
		alert("请选择制单时间");
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
	//showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
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
