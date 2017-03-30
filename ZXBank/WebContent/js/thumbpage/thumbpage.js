// JavaScript Document
//翻页跳转
function to_page(tableName,num){
	var oCurrentPageNum = getCurrentPageNum(tableName);
	var oForm = oCurrentPageNum.form;
	try{
		var formName = oForm.name;
		if(!validateMain(formName)){
			return;
		}
	}catch(e){}
	if(num){
		oCurrentPageNum.value = num;
	}
	oForm.action = getAction(tableName).value;
	try{
		setTimeSuffix();
	}catch(e){}
	getParamQuery(tableName).value="";
	oForm.submit();
}
//跳转至第一页
function to_firstPage(tableName){
	to_page(tableName,1);
}
//跳转至上一页
function to_prevPage(tableName){
	var page_num = getCurrentPageNum(tableName).value;
	page_num = parseInt(page_num) - 1;
	if(isNaN(page_num)){
		alert("当前页为非数字项，请联系管理员");
		return;
	}
	to_page(tableName,page_num);
}
//跳转至下一页
function to_nextPage(tableName){
	var page_num = getCurrentPageNum(tableName).value;
	page_num = parseInt(page_num) + 1;
	if(isNaN(page_num)){
		alert("当前页为非数字项，请联系管理员");
		return;
	}
	to_page(tableName,page_num);
}
//跳转至最后页
function to_lastPage(tableName){
	var page_num = getTotalPagesNum(tableName).value;
	page_num = parseInt(page_num);
	if(isNaN(page_num)){
		alert("当总页数为非数字项，请联系管理员");
		return;
	}
	to_page(tableName,page_num);
}

//跳转至指定页
function to_appointPage(tableName){
	
	var evt = window.event || arguments.callee.caller.arguments[0];
	var charCode = (evt.which)? evt.which : evt.keyCode;
	if(charCode==13){
		//var page_num = getAppointPageNum(tableName).value;
//		var page_num = event.srcElement.value;
		var oElem = evt.srcElement?evt.srcElement:evt.target
		var page_num = oElem.value;
		
		page_num = parseInt(page_num);
		if(isNaN(page_num)){
			alert("跳转页数不能为非数字项，请修改后重新操作");
			return;
		}
		var totalPagesNum = getTotalPagesNum(tableName).value;
		if(page_num>parseInt(totalPagesNum)){
			alert("跳转页数不能大于总页数，请修改后重新操作");
			return;
		}
		if(page_num<1){
			alert("跳转页数不能小于1，请修改后重新操作");
			return;
		}
		to_page(tableName,page_num);
	}
}

//选择每页显示记录数
function to_selectPageSize(tableName){
	//var page_size = getSelectPageSize(tableName).value;
	var page_size = event.srcElement.value;
	getPageSize(tableName).value = page_size;
	to_page(tableName,1);
}

//排序
function to_order(tableName){
	//获得触发事件对象
	var elm = event.srcElement;
	//获得对象中描述的字段名
	var filedName = elm.filedName;
	if(filedName==""){
		return;
	}
	
	//获得排序字段对象
	var oOrderField = getOrderField(tableName);
	//获得原排序字段
	var orderField = oOrderField.value;
	
	var orderType = "";
	//如原排序字段中包含本次排序字段(且不为本次排序字段的倒排序)，则将本次排序字段设置为倒排序
	if(orderField.indexOf(filedName)!=-1 && orderField.indexOf(filedName+ " desc")==-1){
		orderType = " desc";
	}
	
	//将原排序字段拆分成数组
	var orderFieldArray = orderField.split(",");
	//设置第一排序字段为本次排序字段
	orderField = filedName + orderType;
	//设置运行次数为0
	var runNum = 0;
	//循环原排序字段数组
	for(var i=0;i<orderFieldArray.length;i++){
		//当原排序字段不为空，且原排序字段不为本次排序字段及本次排序字段的倒排序，且排序字段不超过3个时，将原排序字段拼入
		if(orderFieldArray[i]!="" 
		&& orderFieldArray[i]!=filedName
		&& orderFieldArray[i]!=filedName + " desc"
		&& runNum<3){
			orderField += "," + orderFieldArray[i];
			runNum++;
		}
	}
	//重新设置排序字段
	oOrderField.value = orderField;
	//执行翻页查询
	to_page(tableName);
	return false;
}

//获得总页数(对象)
function getTotalPagesNum(tableName){
	return document.all[tableName+"_param.totalPagesNum"];
}
//获得当前页数(对象)
function getCurrentPageNum(tableName){
	return document.all[tableName+"_param.currentPageNum"];
}
//获得每页显示记录数(对象)
function getPageSize(tableName){
	return document.all[tableName+"_param.pageSize"];
}
//获得总记录数(对象)
function getTotalItemsNum(tableName){
	return document.all[tableName+"_param.totalItemsNum"];
}
//获得排序字段(对象)
function getOrderField(tableName){
	return document.all[tableName+"_param.orderField"];
}
//获得请求处理地址(对象)
function getAction(tableName){
	return document.all[tableName+"_param.action"];
}
//获得要跳转至第几页(对象)
/*
function getAppointPageNum(tableName){
	//return document.all[tableName+"_param.appointPageNum"];
	return event.srcElement;
}
*/
//获得选择每页显示记录数(对象)
function getSelectPageSize(tableName){
	return document.all[tableName+"_param.selectPageSize"];
}
//获得是否记录当前查询条件值(对象)
function getParamRemember(tableName){
	return document.all[tableName+"_param.param_remember"];
}
//获得是否记录当前查询条件值(对象)
function getParamQuery(tableName){
	return document.all[tableName+"_param.param_query"];
}