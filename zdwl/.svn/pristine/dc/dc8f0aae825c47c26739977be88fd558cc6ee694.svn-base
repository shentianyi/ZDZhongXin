function changeMoney(id, money,mode,changeDate) {
	this.id = id;
	this.money = money;
	this.mode = mode;
	this.changeDate = changeDate;
	return this;
}

//执行保存操作
function doSave() {
	if (selectDealer.dealerIds.length < 2) {
		alert("请选择至少两个经销商");
		return false;
	}
	var cm = $("input[dealerid]");
	var cc = [];
	if (cm) {
		for (var i = 0; i < cm.length; i++) {
			var dealerId = cm[i].getAttribute("dealerid");
			if (!$("#changeMoney" + dealerId)
					.numberbox('getValue')) {
				alert("变更监管费不能为空");
				return false;
			}
			if(!$("#changeMoneyDate" + dealerId).datebox(
			'getValue')){
				alert("变更时间不能为空");
				return false;
			}
					
			cc.push(new changeMoney(dealerId, $("#changeMoney" + dealerId)
					.numberbox('getValue'), $("#changeMode" + dealerId)
					.val(), $("#changeMoneyDate" + dealerId).datebox(
					'getValue')));
		}

	}
	$("#changeMoneyJson").val(JSON.stringify(cc));
	document.forms[0].submit();
}
// 执行表单重置操作
function doReset() {
	document.forms[0].reset();
}

function showDealer(ids) {
	$(".newDealer").remove();
	if (ids) {
		var tab = $(".jxsInfo");
		var html = "<tr class='newDealer'><td colspan='4' style='text-align: center;'><h3>经销商信息</h3></td></tr>";
		for (var i = 0; i < ids.length; i++) {
			var newDealer = getDealer(ids[i]);
			if (newDealer != null) {
				html += "<tr class='newDealer'><td colspan='4'><hr/></td></tr>"
						+ "<tr class='newDealer'>"
						+ "<td class='nameCol2'>经销商名称：</td>"
						+ "<td class='codeCol2'>"
						+ newDealer.dealerName
						+ "</td>"
						+ "<td class='nameCol2'>金融机构：</td>"
						+ "<td class='codeCol2'>"
						+ newDealer.bankName
						+ "</td>"
						+ "</tr>"
						+ "<tr class='newDealer'>"
						+ "<td class='nameCol2'>品牌：</td>"
						+ "<td class='codeCol2'>"
						+ newDealer.brand
						+ "</td>"
						+ "<td class='nameCol2'>付费方式1：</td>"
						+ "<td class='codeCol2'>"
						+ newDealer.payMode
						+ "</td>"
						+ "</tr>"
						+ "<tr class='newDealer'>"
						+ "<td class='nameCol2'>监管费用(/年)：</td>"
						+ "<td class='codeCol2'>"
						+ newDealer.payMoney
						+ "</td>"
						+ "<td class='nameCol2'>进店时间：</td>"
						+ "<td class='codeCol2'>"
						+ newDealer.inDate
						+ "</td>"
						+ "</tr>"
						+ "<tr class='newDealer'>"
						+ "<td class='nameCol2'><font color='#FF0000'>*</font>变更监管费用(/年)：</td>"
						+ "<td class='codeCol2'><input dealerid='"
						+ newDealer.id
						+ "' type='text' id ='changeMoney"
						+ newDealer.id
						+ "' name='changeMoney'/></td>"
						+ "<td class='nameCol2'><font color='#FF0000'>*</font>变更付费方式：</td>"
						+ "<td class='codeCol2'>"
						+ "<select id='changeMode"
						+ (newDealer.id + "'")
						+ " name='changeMode'>"
						+ "<option "
						+ (newDealer.payMode == '月' ? "selected='selected'"
								: "")
						+ " value='1'>月</option>"
						+ "<option "
						+ (newDealer.payMode == '季度' ? "selected='selected'"
								: "")
						+ " value='2'>季度</option>"
						+ "<option "
						+ (newDealer.payMode == '半年' ? "selected='selected'"
								: "")
						+ " value='3'>半年</option>"
						+ "<option "
						+ (newDealer.payMode == '年' ? "selected='selected'"
								: "")
						+ "  value='4'>年</option>"
						+ "</select>"
						+ "</td>"
						+ "</tr>"
						+ "<tr class='newDealer'>"
						+ "<td class='nameCol2'><font color='#FF0000'>*</font>变更时间：</td>"
						+ "<td class='codeCol2'>"
						+ "<input id='changeMoneyDate"
						+ (newDealer.id + "'")
						+ " name='changeMoneyDate' type='text'> </input>"
						+ "</td>" + "</tr>";
			}
		}
		tab.after(html);
		$('[name=changeMoneyDate]').datebox({
		    required:false,
		    editable:false,
		    formatter:function(date){
		    	var y = date.getFullYear();
		    	var m = date.getMonth()+1;
		    	var d = date.getDate();
		    	return y+'-'+m+'-'+d;
		    }

		});
		$('[name=changeMoney]').numberbox({
			 min:0,
			 precision:2
		});
	}
}

function initDealer() {
	var cmJson = $("#changeMoneyJson").val();
	cmJson = $.parseJSON(cmJson);
	$.each(cmJson, function() {
		$("#changeMoney" + this.id).numberbox('setValue', this.money);
		$("#changeMode" + this.id).val(this.mode);
		$("#changeMoneyDate" + this.id).datebox('setValue', this.changeDate);
	});

}