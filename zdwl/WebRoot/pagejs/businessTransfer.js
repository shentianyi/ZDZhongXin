//执行保存操作
function doSave() {
	
	if (!$("#jxs").combobox("getValue")) {
		alert("请选择经销商");
		return false;
	}
	
	if (!$("#yjjd")[0].value) {
		alert("进店时间不能为空");
		return false;
	}
	// 提交表单
	document.forms[0].submit();

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
	if (jsxValue) {
		changeDealer(jsxValue);
	}
}