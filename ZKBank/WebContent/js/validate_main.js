//<meta http-equiv="Content-Type" content="text/html; charset=GBK">

//validate1.0 描述
//valdataFlag：校验标识{true：校验、false：不校验}
//notEmpty：不可为空标识{true：不可为空、false：可为空、number：不可为空且不可为0、char：不可为空且不可只存在空格}
//cName：中文名
//dataType：数据类型{string：字符串、number：整型数字、nmber(N,N)：小数型(小数前有效位数,小数后有效位数)、date：日期类型、timestamp：日期加时间类型}
//bigThan：大于某项{可以是对象ID,也可以是一个具体的值，只dateType为number、number(N,N)、date、timestamp有效}
//lessThan：小于某项{可以是对象ID,也可以是一个具体的值，只dateType为number、number(N,N)、date、timestamp有效}
//maxLength：最大输入长度
//minLength：最小输入长度
//regStr：自定义格式（正则表达式）
//regMsg：自定义格式中文描述

//validate2.0 描述
//pattern：描述dataType为timestamp时的日期精度{yyyy-MM-dd hh:mm:ss}
//timePeriod:时间类型的时段（起始或截止，决定拼写小时分秒后缀时拼写方式）
//dataType：数据类型{positive_number：正整数}


	
//定义存放全部timestamp型元素对象的数组
var timestamp_validates = new Array();

/** 公共校验主函数
 *  formId form对象的ID或NAME
 *  建议调用方法：在onSubmit="return validateMain()"
 */
function validateMain(formId){
	errorELm=null;
	var formObj=null;
	
	//获得form对象
	if(formId != "undefined"){
		formObj = document.getElementById(formId);
	}
	if(!formObj || formObj.nodeName != "FORM"){
		formObj = event.srcElement;
	}
	if(!formObj || formObj.nodeName != "FORM"){
		alert("未找到form对象！");
		return false;
	}
	
	//获得form中全部表单对象
	for(var i = 0; i < formObj.length; i++){
		
		var elm = formObj[i];

		var valiObj=null;
		if(!elm.disabled){
			var valiObjName = getValiObjName(elm.name);
			valiObj = document.getElementById(valiObjName);
		}
		
		//当校验为真时进入校验
		if(valiObj){

			if(elm.name.lastIndexOf(".")!=-1){
				valiObj.parentId = elm.name.substring(0,elm.name.lastIndexOf("."));
			}
			
			//进行为空校验
			if(!valiEmpty(valiObj,elm)){
				eFocus(elm);
				return false;
			}
			
			//进行输入类型校验
			//---yutao2009-01-04注释去掉纯空格输入内容不校验问题--- replaceAll(elm.value," ","") != "" && 
			if(elm.value != "" && valiObj.dataType){
				
				//整数类校验
				if(valiObj.dataType == "number" && !valiNumber(valiObj,elm)){
					eFocus(elm);
					return false;
				}
				
				//正整数类校验
				else if(valiObj.dataType == "positive_number" && !valiPositiveNumber(valiObj,elm)){
					eFocus(elm);
					return false;
				}
				
				//小数类校验
				else if(valiObj.dataType.indexOf("number(") == 0 && !valiDecimal(valiObj,elm)){
					eFocus(elm);
					return false;
				}
				
				//日期类校验
				else if( (valiObj.dataType == "date" 
				       || valiObj.dataType == "timestamp") 
					  && !valiDate(valiObj,elm)){
					eFocus(elm);
					return false;
				}
				
				if(valiObj.dataType == "timestamp"
			    && !hasThisValue(timestamp_validates,elm)){
					elm.pattern = valiObj.pattern;
					elm.timePeriod = valiObj.timePeriod;
					timestamp_validates.push(elm);
				}
			}
			
			//进行输入最大长度校验
//			if(valiObj.maxlength && valiObj.maxlength != "" && valiObj.maxlength > 0 && !valiMaxLength(valiObj,elm)){
//				eFocus(elm);
//				return false;
//			}
			
			//进行输入最小长度校验
			if(valiObj.minlength && valiObj.minlength != "" && valiObj.minlength > 0 && !valiMinLength(valiObj,elm)){
				eFocus(elm);
				return false;
			}

			if(valiObj.regStr && valiObj.regStr.length > 0 && !valiRegStr(valiObj,elm)){
				eFocus(elm);
				return false;
			}
			
		}
	}
	return true;
	
}

function hasThisValue(array,value){
	for(var i=0;i<array.length;i++){
		if(array[i]==value){
			return true;
		}
	}
	return false;
}

function setTimeSuffix(){
	if(timestamp_validates.length > 0){
		var timeSuffix = getTimeSuffix();
		var timeSuffix_end = getTimeSuffix_end();
		for(var i = 0; i < timestamp_validates.length; i++){
			if(timestamp_validates[i].value==""){
				continue;
			}
			if(timestamp_validates[i].pattern=="yyyy-MM-dd hh:mm:ss"){
				continue;
			}
			if(timestamp_validates[i].pattern=="yyyy-MM-dd"){
				if(timestamp_validates[i].timePeriod=="end"){
					timestamp_validates[i].value += timeSuffix_end;
				}
				else{
					timestamp_validates[i].value += timeSuffix;
				}
			}
			else if(timestamp_validates[i].pattern=="yyyy-MM-dd hh"){
				if(timestamp_validates[i].timePeriod=="end"){
					timestamp_validates[i].value += timeSuffix_end.substring(3);
				}
				else{
					timestamp_validates[i].value += timeSuffix.substring(3);
				}
			}
			else if(timestamp_validates[i].pattern=="yyyy-MM-dd hh:mm"){
				if(timestamp_validates[i].timePeriod=="end"){
					timestamp_validates[i].value += timeSuffix_end.substring(6);
				}
				else{
					timestamp_validates[i].value += timeSuffix.substring(6);
				}
			}
		}
	}
}

//以下为校验中基础方法
/** 设置提示信息
 *  elm 元素对象
 *  return String
 */
function getMessage(valiObj){
	var message = "该项";
	
	if(valiObj.getAttribute("msg") && valiObj.getAttribute("msg") != ""){
		message = valiObj.getAttribute("msg");
	}
	else if(valiObj.getAttribute("cName") && valiObj.getAttribute("cName") != ""){
		message = valiObj.getAttribute("cName");
	}
	else if(valiObj.getAttribute("alt") && valiObj.getAttribute("alt") != ""){
		message = valiObj.getAttribute("alt");
	}
	
	return message;
}

/** 设置时间后缀
 *  return String
 */
function getTimeSuffix(){
	var sign,hours,minutes,seconds;
	
	sign = ":";
	hours="00";
	minutes="00";
	seconds="00";
	
	return " " + hours + sign + minutes + sign + seconds;
}

/** 设置时间后缀
 *  return String
 */
function getTimeSuffix_end(){
	var sign,hours,minutes,seconds;
	
	sign = ":";
	hours="23";
	minutes="59";
	seconds="59";
	
	return " " + hours + sign + minutes + sign + seconds;
}
var errorELm=null;
/** 将光标置在对象上
 *  elm 元素对象
 *  当对象状态为不可见时则自动向上追寻对象的父接点,直至可将光标置于对象中(为避免陷入死循环,将最大查找范围设置为100次)
 */
function eFocus(elm){
	errorELm=elm;
	var pElm = elm;
	var runflag = true;
	var i = 0;
	while(runflag && i < 100){
		try{
			//pElm.style.display = "";
		}
		catch(e){
			break;
		}
		try{
			elm.focus();
			runflag = false;
			return true;
		}
		catch(e){
			pElm = pElm.parentNode;
			if(!pElm){
				runflag = false;
			}
		}
		i++;
	}
	return false;
}

/** 全部替换
 *  value 被替换的字符串
 *  oldValue 替换掉的字符串
 *  newValue 替换为的字符串
 *  return String
 */
function replaceAll(value,oldValue,newValue){	
	if(oldValue == newValue){
		return value;
	}
	while(value.indexOf(oldValue)!=-1){
		value = value.replace(oldValue,newValue);
	}
	return value;
}

function validataGetElementById(id){
	var obj = document.getElementById(id);
	if(!obj){
		alert("通过"+id+"未获得对象");
	}
	else{
		return obj;
	}
}

function validataGetThanObject(thisName,thanName){
	if(thisName.indexOf(".")!=-1){
		thanName = thisName.substring(0,thisName.lastIndexOf(".")+1) + thanName;
	}
	var obj = document.getElementById(thanName);
	if(!obj){
		alert("通过"+thanName+"未获得对象");
	}
	else{
		return obj;
	}
}

function getValiObjByElmName(elmName){
	if(elmName.indexOf("[") < elmName.indexOf("]")){
		return validataGetElementById("validata."+elmName.substring(0,elmName.indexOf("["))+elmName.substring(elmName.indexOf("]")+1));
	}
	else{
		return validataGetElementById("validata."+elmName);
	}
}

function getValiObjName(elmName){
	if(elmName.indexOf("[") < elmName.indexOf("]")){
		return "validata."+elmName.substring(0,elmName.indexOf("["))+elmName.substring(elmName.indexOf("]")+1);
	}
	else{
		return "validata."+elmName;
	}
}


