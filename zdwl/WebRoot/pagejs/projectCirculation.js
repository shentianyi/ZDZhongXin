//执行保存操作
function doSave() {

	var reg = /^(-?\d+)(\.\d+)?$/;

	if (!$("#dealerName").val()) {
		alert("经销商名称不能为空");
		return false;
	}
	if ($("#two").val() <= 0) {
		alert("请选择金融机构");
		return false;
	}

	if (!$("#pinpai").combobox("getValue")) {
		alert("请选择品牌");
		return false;
	}

	if ($("#province").val() <= 0) {
		alert("请选择省");
		return false;
	}
	if ($("#city").val() <= 0) {
		alert("请选择市");
		return false;
	}
	if (!$("#superviseAddress").val()) {
		alert("监管详细地址不能为空");
		return false;
	}
	if ($("[name=mpc\\.isBindShop]:checked").val() == 1) {
		if ($("[name='mpc\\.bindShop']").val() <= 0
				&& $("[name='mpc\\.bindShop2']").val() <= 0) {
			alert("请至少选择一家绑定店");
			return false;
		}
	} else {
		$("[name='mpc\\.bindShop']").val("-1");
		$("[name='mpc\\.bindShop2']").val("-1");
		$("#bindShop1BankName").val("");
		$("#bindShop2BankName").val("");
		$("#distance").val("");
		$("#distance2").val("");
	}
	if (!$("#inStoreDate").val()) {
		alert("预进店时间不能为空");
		return false;
	}

	if (!$("#superviseMoney").val()) {
		alert("监管费不能为空");
		return false;
	}
	if (!$("#invoiceMode").val()) {
		alert("发票开具方式不能为空");
		return false;
	}

	if (!reg.test($("#superviseMoney").val())) {
		alert("监管费格式不正确");
		return false;
	}

	if ($("[name='mpc\\.isNeedHandover']:checked").val() == 2) {
		getElement("mpc.handoverDate").value = "";
		getElement("handoverCompany").value = "";
	}

	document.forms[0].submit();
}

function init(isUpdate) {
	$("#pinpai").combobox({
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
			}

		}
		
	});
	$("#bs1").combobox({
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
				$(this).combobox('setValue',-1);
				bindShop1change(-1);
			}else{
				bindShop1change(newValue);
			}

		}
		
	});
	$("#bs2").combobox({
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
				$(this).combobox('setValue',-1);
				bindShop2change(-1);
			}else{
				bindShop2change(newValue);
			}

		}
		
	});
}

