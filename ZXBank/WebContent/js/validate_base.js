//<meta http-equiv="Content-Type" content="text/html; charset=GBK">

/** 校验是否为空
 *  elm 校验元素对象
 *  return boolean
 */
function valiEmpty(valiObj,elm){
	if(valiObj.getAttribute("notEmpty") == "false"){
		return true;
	}
	
	if(elm.type == "checkbox" || elm.type == "radio"){
		
		var elmArray = document.getElementsByName(elm.name);
		
		for(var i = 0; i < elmArray.length; i++){
			if(elmArray[i].checked)
				return true;
		}
		showMessage(getMessage(valiObj) + "不能为空");
		return false;
		
	}
	if(valiObj.getAttribute("notEmpty") == "true" && elm.value == ""){
		showMessage(getMessage(valiObj) + "不能为空");
		return false;
	}
	else if(valiObj.getAttribute("notEmpty") == "spacing" && replaceAll(elm.value," ","") == ""){
		showMessage(getMessage(valiObj) + "不能为空");
		return false;
	}
	else if(valiObj.getAttribute("notEmpty") == "number" && (elm.value == "" || elm.value <= 0)){
		showMessage(getMessage(valiObj) + "应大于0");
		return false;
	}
	return true;
}

/** 校验是否为整型数字
 *  elm 校验元素对象
 *  return boolean
 */
function valiNumber(valiObj,elm){
	if(isNaN(elm.value) || elm.value.toString().indexOf(".") != -1 || elm.value.toString().indexOf(" ") != -1){
		showMessage(getMessage(valiObj) + "存在非法字符");
		return false;
	}
	
	if(valiObj.getAttribute("bigThan") && valiObj.getAttribute("bigThan") != ""){
		if(isNaN(valiObj.getAttribute("bigThan"))){
			var oMinValue = validataGetThanObject(elm.name,valiObj.getAttribute("bigThan"));
			if(isNaN(oMinValue.value)){
				return true;
			}
			else if(parseInt(elm.value) < parseInt(oMinValue.value)){
				showMessage(getMessage(valiObj) + "应大于" + getMessage(getValiObjByElmName(oMinValue.name)));
				return false;
			}
		}
		else if(parseInt(elm.value) < parseInt(valiObj.getAttribute("bigThan"))){
			showMessage(getMessage(valiObj) + "应大于" + parseInt(valiObj.getAttribute("bigThan")));
			return false;
		}
	}
	
	if(valiObj.getAttribute("lessThan") && valiObj.getAttribute("lessThan") != ""){
		if(isNaN(valiObj.getAttribute("lessThan"))){
			var oMaxValue = validataGetThanObject(elm.name,valiObj.getAttribute("lessThan"));
			if(isNaN(oMaxValue.value)){
				return true;
			}
			else if(parseInt(elm.value) > parseInt(oMaxValue.value)){
				showMessage(getMessage(valiObj) + "应小于" + getMessage(getValiObjByElmName(oMaxValue.name)));
				return false;
			}
		}
		else if(parseInt(elm.value) > parseInt(valiObj.getAttribute("lessThan"))){
			showMessage(getMessage(valiObj) + "应小于" + parseInt(valiObj.getAttribute("lessThan")));
			return false;
		}
	}
	
	return true;
}

/** 校验是否为正整型数字
 *  elm 校验元素对象
 *  return boolean
 */
function valiPositiveNumber(valiObj,elm){
	if(isNaN(elm.value) || elm.value.toString().indexOf(".") != -1 || elm.value.toString().indexOf(" ") != -1 || elm.value.toString().indexOf("-") != -1){
		showMessage(getMessage(valiObj) + "存在非法字符");
		return false;
	}
	
	if(valiObj.getAttribute("bigThan") && valiObj.getAttribute("bigThan") != ""){
		if(isNaN(valiObj.getAttribute("bigThan"))){
			var oMinValue = validataGetThanObject(elm.name,valiObj.getAttribute("bigThan"));
			if(isNaN(oMinValue.value)){
				return true;
			}
			else if(parseInt(elm.value) < parseInt(oMinValue.value)){
				showMessage(getMessage(valiObj) + "应大于" + getMessage(getValiObjByElmName(oMinValue.name)));
				return false;
			}
		}
		else if(parseInt(elm.value) < parseInt(valiObj.getAttribute("bigThan"))){
			showMessage(getMessage(valiObj) + "应大于" + parseInt(valiObj.getAttribute("bigThan")));
			return false;
		}
	}
	
	if(valiObj.getAttribute("lessThan") && valiObj.getAttribute("lessThan") != ""){
		if(isNaN(valiObj.getAttribute("lessThan"))){
			var oMaxValue = validataGetThanObject(elm.name,valiObj.getAttribute("lessThan"));
			if(isNaN(oMaxValue.value)){
				return true;
			}
			else if(parseInt(elm.value) > parseInt(oMaxValue.value)){
				showMessage(getMessage(valiObj) + "应小于" + getMessage(getValiObjByElmName(oMaxValue.name)));
				return false;
			}
		}
		else if(parseInt(elm.value) > parseInt(valiObj.getAttribute("lessThan"))){
			showMessage(getMessage(valiObj) + "应小于" + parseInt(valiObj.getAttribute("lessThan")));
			return false;
		}
	}
	
	return true;
}

/** 校验是否为小数
 *  elm 校验元素对象
 *  return boolean
 */
function valiDecimal(valiObj,elm){
	if(isNaN(elm.value) || elm.value.toString().indexOf(" ") != -1){
		showMessage(getMessage(valiObj) + "存在非法字符");
		return false;
	}
	else if(valiObj.getAttribute("dataType") !=  "number" && valiObj.getAttribute("dataType") !=  "number(0,0)"){
		var numberLength = parseInt(valiObj.getAttribute("dataType").substring(valiObj.getAttribute("dataType").indexOf("(")+1,valiObj.getAttribute("dataType").indexOf(",")));
		var decimalLength = parseInt(valiObj.getAttribute("dataType").substring(valiObj.getAttribute("dataType").indexOf(",")+1,valiObj.getAttribute("dataType").indexOf(")")));
		var number,decimal;
		if(elm.value.indexOf(".") > 0){
			number = elm.value.substring(0,elm.value.indexOf("."));
			decimal = elm.value.substring(elm.value.indexOf(".")+1);
		}
		else{
			number = elm.value;
			decimal = "";
		}
		
		if(decimal=="" && elm.value.indexOf(".")!=-1){
			showMessage(getMessage(valiObj) + "小数点后不能为空");
			return false;
		}

		if(number.length > numberLength){
			showMessage(getMessage(valiObj) + "小数点前输入不能超过" + numberLength + "位");
			return false;
		}
		if(decimal.length > decimalLength){
			showMessage(getMessage(valiObj) + "小数点后输入不能超过" + decimalLength + "位");
			return false;
		}
	}
	
	if(valiObj.getAttribute("bigThan") && valiObj.getAttribute("bigThan") != ""){
		if(isNaN(valiObj.getAttribute("bigThan"))){
			var oMinValue = validataGetThanObject(elm.name,valiObj.getAttribute("bigThan"));
			if(isNaN(oMinValue.value)){
				return true;
			}
			else if(parseFloat(elm.value) < parseFloat(oMinValue.value)){
				showMessage(getMessage(valiObj) + "应大于" + getMessage(getValiObjByElmName(oMinValue.name)));
				return false;
			}
		}
		else if(parseFloat(elm.value) < parseFloat(valiObj.getAttribute("bigThan"))){
			showMessage(getMessage(valiObj) + "应大于" + valiObj.getAttribute("bigThan"));
			return false;
		}
	}
	
	if(valiObj.getAttribute("lessThan") && valiObj.getAttribute("lessThan") != ""){
		if(isNaN(valiObj.getAttribute("lessThan"))){
			var oMaxValue = validataGetThanObject(elm.name,valiObj.getAttribute("lessThan"));
			if(isNaN(oMaxValue.value)){
				return true;
			}
			else if(parseFloat(elm.value) > parseFloat(oMaxValue.value)){
				showMessage(getMessage(valiObj) + "应小于" + getMessage(getValiObjByElmName(oMaxValue.name)));
				return false;
			}
		}
		else if(parseFloat(elm.value) > parseFloat(valiObj.getAttribute("lessThan"))){
			showMessage(getMessage(valiObj) + "应小于" + valiObj.getAttribute("lessThan"));
			return false;
		}
	}
	
	return true;
}

/** 校验是否为日期(只含yyyy-MM-dd类型)
 *  elm 校验元素对象
 *  return boolean
 */
function valiDate(valiObj,elm){
	
	var elmValue = elm.value;
	
	if(isNaN(getFormatDate(elmValue,valiObj.pattern))){
		showMessage(getMessage(valiObj) + "格式错误或不合法,正确的日期格式为" + valiObj.pattern + " 如:" + getTrueDatePatternMsg(valiObj.pattern));
		return false;
	}
	
	if(valiObj.getAttribute("bigThan") && valiObj.getAttribute("bigThan") != ""){
		if(isNaN(getFormatDate(valiObj.getAttribute("bigThan"),valiObj.pattern))){
			var oMinValue = validataGetThanObject(elm.name,valiObj.getAttribute("bigThan"));
			if(isNaN(getFormatDate(oMinValue.value))){
				return true;
			}
			else if(getFormatDate(elm.value,valiObj.pattern) < getFormatDate(oMinValue.value)){
				showMessage(getMessage(valiObj) + "应大于" + getMessage(getValiObjByElmName(oMinValue.name)));
				return false;
			}
		}
		else if(getFormatDate(elm.value,valiObj.pattern) < getFormatDate(valiObj.getAttribute("bigThan"),valiObj.pattern)){
			showMessage(getMessage(valiObj) + "应大于" + valiObj.getAttribute("bigThan"));
			return false;
		}
	}
	
	if(valiObj.getAttribute("lessThan") && valiObj.getAttribute("lessThan") != ""){
		if(isNaN(getFormatDate(valiObj.getAttribute("lessThan"),valiObj.pattern))){
			var oMaxValue = validataGetThanObject(elm.name,valiObj.getAttribute("lessThan"));
			if(isNaN(getFormatDate(oMaxValue.value))){
				return true;
			}
			else if(getFormatDate(elm.value,valiObj.pattern) > getFormatDate(oMaxValue.value)){
				showMessage(getMessage(valiObj) + "应小于" + getMessage(getValiObjByElmName(oMaxValue.name)));
				return false;
			}
		}
		else if(getFormatDate(elm.value,valiObj.pattern) > getFormatDate(valiObj.getAttribute("lessThan"),valiObj.pattern)){
			showMessage(getMessage(valiObj) + "应小于" + valiObj.getAttribute("lessThan"));
			return false;
		}
	}
	
	return true;
}

/** 根据日期格式返回日期示范
 *  pattern 日期格式
 * 	return String
 */
function getTrueDatePatternMsg(pattern){
	if(!pattern || pattern=="yyyy-MM-dd"){
		return "2009-01-01";
	}
	else if(pattern=="yyyy-MM-dd hh"){
		return "2009-01-01 09";
	}
	else if(pattern=="yyyy-MM-dd hh:mm"){
		return "2009-01-01 09:00";
	}
	else if(pattern=="yyyy-MM-dd hh:mm:ss"){
		return "2009-01-01 09:00:00";
	}
}

/** 得到格式化的日期
 *  date 要格式化的日期
 *  pattern 日期格式
 *  return String(当date非日期时返回NaN)
 */
function getFormatDate(oldDate,pattern){
	
	if(!pattern){
		if(oldDate.length==13)
			pattern = "yyyy-MM-dd hh";
		if(oldDate.length==16)
			pattern = "yyyy-MM-dd hh:mm";
		if(oldDate.length==19)
			pattern = "yyyy-MM-dd hh:mm:ss";
	}
	
	var reg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/;
	var hh = null;
	var mm = null;
	var ss = null;
	
	if(pattern=="yyyy-MM-dd hh"){
		reg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2}) (\d{1,2})$/;
		if(oldDate.length!=13){
			return NaN;
		}
		hh = oldDate.substring(11);
	}
	else if(pattern=="yyyy-MM-dd hh:mm"){
		reg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2})$/;
		if(oldDate.length!=16){
			return NaN;
		}
		hh = oldDate.substring(11);
		mm = oldDate.substring(14);
	}
	else if(pattern=="yyyy-MM-dd hh:mm:ss"){
		reg = /^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
		if(oldDate.length!=19){
			return NaN;
		}
		hh = oldDate.substring(11);
		mm = oldDate.substring(14);
		ss = oldDate.substring(17);
	}
	
	if(hh!=null && (parseInt(hh)>23 || hh.length<2)){
		return NaN;
	}
	
	if(mm!=null && (parseInt(mm)>59 || mm.length<2)){
		return NaN;
	}
	
	if(ss!=null && (parseInt(ss)>59 || ss.length<2)){
		return NaN;
	}
	
	//if(oldDate.search(reg)==0){
		oldDate = oldDate.substring(0,10);
		oldDate = replaceAll(oldDate,"-","/");
		
		var newDate = new Date(oldDate);
		if(isNaN(newDate)){
			return NaN;
		}
		
		var year = newDate.getYear().toString();
		if(year.length==2){
			year = "19"+year;
		}
		var month = (parseInt(newDate.getMonth())+1).toString();
		if(month.length == 1){
			month = 0 + month;
		}
		var date = newDate.getDate().toString();
		if(date.length == 1){
			date = 0 + date;
		}
		
		var oldyear = oldDate.substring(0,4);
		var oldmonth = oldDate.substring(5,7);
		var olddate = oldDate.substring(8,10);
		
		if(oldyear!=year
		|| oldmonth!=month
		|| olddate!=date){
			return NaN;
		}
		
		return newDate;
		/*
	}
	else{
		alert(5+":"+oldDate.search(reg));
		return NaN;
	}	
	*/
}

/** 校验是否超长
 *  elm 校验元素对象
 *  return boolean
 */
function valiMaxLength(valiObj,elm){
	
	if(isNaN(valiObj.getAttribute("maxlength"))){
		return true;
	}
	
	var maxLength = parseInt(valiObj.getAttribute("maxlength"));
	
	if(elm.value.length > maxLength){
		showMessage(getMessage(valiObj) + "输入超长");
		return false;
	}
	
	var factLength = elm.value.length;
	for(i = 0; i < elm.value.length; i++){
		var tmpCode = elm.value.charCodeAt(i);
		if(tmpCode > 255){
			factLength++;
			if(factLength > maxLength){
				showMessage(getMessage(valiObj) + "输入超长");
				return false;
			}
		}
	}
	
	return true;
	
}

/** 校验是否长度不足
 *  elm 校验元素对象
 *  return boolean
 */
function valiMinLength(valiObj,elm){
	if(isNaN(valiObj.getAttribute("minlength"))){
		return true;
	}
	
	var minLength = parseInt(valiObj.getAttribute("minlength"));
	
	if(elm.value!= "" && elm.value.length < minLength){
		showMessage(getMessage(valiObj) + "至少应输入" + minLength + "位");
		return false;
	}
	
	return true;
	
}

function valiRegStr(valiObj,elm){
	if(valiObj.getAttribute("regStr") && valiObj.getAttribute("regStr") != "" && elm.value.search(valiObj.getAttribute("regStr"))!=0){
		showMessage(getMessage(valiObj) + "应按 " + valiObj.getAttribute("regMsg") + " 格式输入");
	}
}