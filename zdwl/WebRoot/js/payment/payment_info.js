$(function(){
	var patten = /^-?\d+\.?\d{0,2}$/;   //验证小数,保留两位小数点  
	var payState = $("#payState").val();//每月薪酬信息提交状态
	
	if(payState == 0 || payState == ""){//状态为0时  为未提交
		$(".appstate").hide();//隐藏审批状态查询条件
	}
	
	$("[name='editremark']").attr("disabled","disabled");
	
	$("[name='paymentData']").attr("disabled","disabled");
	$("[name='paymentData']").blur(function(){  
    	var payflag = $(this).attr("id");
    	var payRowId = $(this).data("data-payrowid");
		
    	if(payflag){
    		var objid = payflag.split('-')[1];
    		var payrowid = payflag.split('-')[2];
    		if(!patten.test($(this).val())){
    			alert("请输入数字,且小数点后最多输入两位");
    			//$("#"+payflag).val("")
    			return;
    		}
    		if(payrowid && payrowid > 0){
				var url = "/json/savePaymentInfoData.do?callback=?&id=" + payrowid + 
						"&data=" + $(this).val() + "&objid=" + objid;
				$.getJSON(url, function(result) {
					var data = result.data[0];
					console.info(data);
				});
    		}
	    }
	}); 
});
	
function goEdit(rowId){
	if(rowId && rowId > 0){
		$("#editInfo"+rowId).attr("disabled","disabled");
		$("#editInfo"+rowId).css("color","gray");
		var replaceCost = $("#replaceCost-2-"+rowId);
		var mealSubsidy = $("#mealSubsidy-3-"+rowId);
		var phoneSubsidy = $("#phoneSubsidy-4-"+rowId);
		var trafficSubsidy = $("#trafficSubsidy-5-"+rowId);
		var houseSubsidy = $("#houseSubsidy-6-"+rowId);
		var manySubsidy = $("#manySubsidy-7-"+rowId);
		var farSubsidy = $("#farSubsidy-8-"+rowId);
		var fullTimeSubsidy = $("#fullTimeSubsidy-9-"+rowId);
		var settleCost = $("#settleCost-10-"+rowId);
		var elseSubsidy = $("#elseSubsidy-11-"+rowId);
		var subsidyOne = $("#subsidyOne-12-"+rowId);
		var subsidyTwo = $("#subsidyTwo-13-"+rowId);
		var deductOne = $("#deductOne-14-"+rowId);
		var deductTwo = $("#deductTwo-15-"+rowId);
		var subsidyOneTotal = $("#subsidyOneTotal-16-"+rowId);
		var subsidyTwoTotal = $("#subsidyTwoTotal-17-"+rowId);
		var deductOneTotal = $("#deductOneTotal-18-"+rowId);
		var deductTwoTotal = $("#deductTwoTotal-19-"+rowId);
		var remark = $("#remark-20-"+rowId);
		remark.removeAttr("disabled");
		remark.css('background-color','yellow');
		
		replaceCost.removeAttr("disabled");
		replaceCost.css('background-color','yellow');
		mealSubsidy.removeAttr("disabled");
		mealSubsidy.css('background-color','yellow');
		phoneSubsidy.removeAttr("disabled");
		phoneSubsidy.css('background-color','yellow');
		trafficSubsidy.removeAttr("disabled");
		trafficSubsidy.css('background-color','yellow');
		
		houseSubsidy.removeAttr("disabled");
		houseSubsidy.css('background-color','yellow');
		manySubsidy.removeAttr("disabled");
		manySubsidy.css('background-color','yellow');
		farSubsidy.removeAttr("disabled");
		farSubsidy.css('background-color','yellow');
		fullTimeSubsidy.removeAttr("disabled");
		fullTimeSubsidy.css('background-color','yellow');
		settleCost.removeAttr("disabled");
		settleCost.css('background-color','yellow');
		    
		elseSubsidy.removeAttr("disabled");
		elseSubsidy.css('background-color','yellow');
		subsidyOne.removeAttr("disabled");
		subsidyOne.css('background-color','yellow');
		subsidyTwo.removeAttr("disabled");
		subsidyTwo.css('background-color','yellow');
		deductOne.removeAttr("disabled");
		deductOne.css('background-color','yellow');
		deductTwo.removeAttr("disabled");
		deductTwo.css('background-color','yellow');
		
		subsidyOneTotal.removeAttr("disabled");
		subsidyOneTotal.css('background-color','yellow');
		subsidyTwoTotal.removeAttr("disabled");
		subsidyTwoTotal.css('background-color','yellow');
		deductOneTotal.removeAttr("disabled");
		deductOneTotal.css('background-color','yellow');
		deductTwoTotal.removeAttr("disabled");
		deductTwoTotal.css('background-color','yellow');
		
	}
}

function initDate(){
	$("#year").combobox({
		width : "98.7778px",
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
	$("#month").combobox({
		width : "98.7778px",
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
/*	$("#cityId").combobox({ 
		width : "98.7778px",
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
	});*/
/*	$("# ").combobox({
		width : "98.7778px",
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
	});*/
}

//异步更新备注
function saveRemark(obj,payRowId){
	var remark = obj.value;
	if(payRowId > 0){
		var url = "/json/savePaymentInfoData.do?callback=?&id=" + payRowId + "&data=" + remark + "&objid=" + 20;
		$.getJSON(url, function(result) {
			var data = result.data[0];
		});
	}
}