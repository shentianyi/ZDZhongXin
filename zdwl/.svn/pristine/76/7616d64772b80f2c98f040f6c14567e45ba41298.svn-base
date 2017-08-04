$(function() {
   var selectObj = $("#hideDiv").find('select');
	selectObj.each(function(i) {
		if ($(this).combobox('getValue') == 1) {
			$(this).parent().parent().find('td:last').find('input').prop(
					"readonly", false);

		}

	});

	selectObj.combobox({
		onChange : function(newValue, oldValue) {
			var remarkObj = $(this).parent().parent().find('td:last').find(
					'input');
			if (newValue == 1) {
				remarkObj.prop("readonly", false);
			} else {
				remarkObj.val('');
				remarkObj.prop("readonly", true);
			}

		}

	});

	var obj = $("input[type=checkbox]:first").get(0);
	if (obj.checked) {
		$("#reasonWrite").prop("readonly", false);
		$("#hideDiv").hide();
	}
})

function skipWrite(obj) {
	if (obj.checked) {
		$("#reasonWrite").prop("readonly", false);
		$("#hideDiv").hide();
	} else {
		 $("#reasonWrite").val('');
		$("#hideDiv").show(); 
		$("#reasonWrite").prop("readonly", true);
		
	}
	
} 

function doSave() {
	commonAjax();
}

function doSubmit() {
	if(window.confirm('你确定要提交吗？提交后就不能修改了')){
		$("input[name=method]").val("submitReport");
		commonAjax();
     }else{
        return false;
    }
	
}
	
function commonAjax(){
	var obj = $("input[type=checkbox]:first").get(0);
	if (obj.checked) {
		if ($("#reasonWrite").val() == '') {
			alert("请输入跳过原因");
			return false;
		}
		document.forms[0].submit();

	} else {
		 if ($("#checkMinute").val() == '') {
			alert("请输入实际检查用时");
			return false;
		}
		var fag = true;
		var requireObj = $(".requiredWrite");
		 requireObj.each(function(i) {
			if ($(this).val() == "") {
				alert("检查项目不能为空");
				fag = false;
				return false;
			}

		}); 
		 
		var selectcheck = $(".ly-bor-none");
		selectcheck.each(function(i) {
		if ($(this).val() == -1) {
			alert("请选择下拉框");
			fag = false;
			return false;
		}
		}); 
		  if (!fag) {
			return false;
		}
		/* var selectObj = $("#hideDiv").find('select');
		 
		selectObj.each(function(i) {
			alert();
			if ($(this).combobox('getValue') == 1) {
				if ($(this).parent().parent().find('td:last').find('input')
						.val() == '') {
					alert("情况不符合或不规范时,备注不能为空");
					fag = false;
					return false;
				}

			}

		}); 

		 
		if (!fag) {
			return false;
		}  */
 
		document.forms[0].submit();
	}
}

//进入列表页面
function goList() {
	location.href = "/videoReport.do?method=findProgressList";
}