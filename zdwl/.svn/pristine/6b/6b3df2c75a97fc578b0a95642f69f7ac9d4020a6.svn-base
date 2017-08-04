function checkAll(name) {
	var el = document.getElementsByTagName('input');
	var len = el.length;
	for (var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].name == name) && el[i].getAttribute("checked") != "checked") {
			el[i].checked = true;
		}
	}
	$("#fullRead").prop("checked",true)
}

function clearAll(name) {
	var el = document.getElementsByTagName('input');
	var len = el.length;
	for (var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].name == name) && el[i].getAttribute("checked") != "checked") {
			el[i].checked = false;
		}
	}
	$("#fullRead").prop("checked",false)
}
//判断字符串是否为空
function isNull(str){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
function doLoad(str) {
	initBank();
	var onehiId = $("#onehiId").val();
	var twohiId = $("#twohiId").val();
	var threehiId = $("#threehiId").val();
	var strs= new Array(); 
	if("" != str){
		strs = str.split(",");
	}
	if (onehiId && onehiId != "" && onehiId > 0) {
		$("#one").val(onehiId);
	}
	if (twohiId && twohiId != "" && twohiId > 0) {
		loadSelect(onehiId, $("#two"));
		$("#two").val(twohiId);
	}
	if (threehiId && threehiId !="" && threehiId > 0) {
		loadSelect(twohiId, $("#three"));
		$("#three").val(threehiId);
	}
	if(!isNull(strs) && strs.length !=0){
		$.each(strs, function(i, item) {
			$('#'+item).datebox({    
				editable:false   
			});
		});
	}
}