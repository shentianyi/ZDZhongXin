var baseKeyCodes = ",46,8,13,37,39,9,189,109,";
var NumKeyCodes = ",96,97,98,99,100,101,102,103,104,105,48,49,50,51,52,53,54,55,56,57,";
var pointKeyCodes = ",110,190,";
var baseFunctionKeyCodes = ",65,67,86,88,90,";
var inputToolsCodes = ",229,";

function setValiEvent(){
	var inputArray = new Array();
	var reportTable = document.getElementById("grid");
	
	if(reportTable && reportTable.nodeName == "TABLE"){
		var array = reportTable.getElementsByTagName("INPUT");
		inputArray.pushAll(array);
		array = reportTable.getElementsByTagName("SELECT");
		inputArray.pushAll(array);
		array = reportTable.getElementsByTagName("TEXTAREA");
		inputArray.pushAll(array);
	}

	for(i = 0; i < inputArray.length; i++){
		inputArray[i].attachEvent("onkeydown",valiInput);
	}
}

function getInputIndex(){
	var oRange = document.selection.createRange();
	var rangeLength = oRange.text.length;
	oRange.setEndPoint("startToStart",event.srcElement.createTextRange());
	return oRange.text.length - rangeLength;
}

function getRangeLength(){
	var oRange = document.selection.createRange();
	return oRange.text.length;
}

function valiInputTools(){
	var elm = event.srcElement;
	if(elm.fractionLength){
		if(inputToolsCodes.indexOf("," + event.keyCode.toString() + ",")!=-1){
			alert("当前输入类型为数值型，请关闭中文输入法后录入。");
			return false;
		}
	}
}

function valiInput(){

//	if(!valiMaxLength()){
//		return false;
//	}
	
	var elm = event.srcElement;

	if(elm.fractionLength){

		if(inputToolsCodes.indexOf("," + event.keyCode.toString() + ",")!=-1){
			return false;
		}

		//负数校验目前不完整
		if(event.keyCode == 189 || event.keyCode == 109){	
			var inputIndex = getInputIndex();
			if(elm.value.indexOf("-") != -1 && getRangeLength() <= 0 && inputIndex == 0){
				return false;
			}
			if(inputIndex != 0){
				return false;
			}
		}

		if(elm.fractionLength <= 0){
			return inputNumber();
		}
		else{
			var intLength = 99;
			if(!isNaN(elm.maxLength)){
				intLength = parseInt(elm.maxLength) - parseInt(elm.fractionLength) - 1;
			}
			return inputDecimal(intLength,parseInt(elm.fractionLength));
		}
	}
}

/** 输入数字控制
 *  只能输入纯数字，没有长度及范围校验
 */
function inputNumber(){
	
	//设置键位为基础键 + 数字键
	var thisKeyCodes = baseKeyCodes + NumKeyCodes;
	
	var elm = event.srcElement;
	
	var returnBoolen = true;
	
	if(thisKeyCodes.indexOf("," + event.keyCode.toString() + ",") == -1)
		returnBoolen = valiBaseFunctionKey();//校验输入键是否为功能键
		
	if(returnBoolen)
		returnBoolen = valiClipboardDataByNumber();//校验粘贴板内容是否符合数字
	
	return returnBoolen;
}

/** 输入数字控制
 *  intLength：整数位长
 *  decimalLength：小数位长
 *  能根据整数位长及小数位长进行精度控制
 */
function inputDecimal(intLength,decimalLength){
	
	//设置键位为基础键 + 数字键 + 小数点键
	var thisKeyCodes = baseKeyCodes + NumKeyCodes + pointKeyCodes;
	
	var elm = event.srcElement;
	
	var returnBoolen = true;
	
	if(thisKeyCodes.indexOf("," + event.keyCode.toString() + ",") == -1)
		returnBoolen = valiBaseFunctionKey();//校验输入键是否为功能键
	if(returnBoolen)
		returnBoolen = valiClipboardDataByDecimal(intLength,decimalLength);//校验粘贴板内容是否符合小数
	if(returnBoolen)
		returnBoolen = valiInputPoint();//校验输入小数点合法性
	if(returnBoolen)
		returnBoolen = valiInputDecimal(intLength,decimalLength);//校验输入小数合法性
	
	return returnBoolen;
}

/** 校验粘贴板内容是否符合小数
 *  intLength：整数位长
 *  decimalLength：小数位长
 *  能根据整数位长及小数位长进行精度校验
 */
function valiClipboardDataByDecimal(intLength,decimalLength){
		
	if(event.ctrlKey && event.keyCode == 86){
		
		var clipboardDataText = clipboardData.getData("text");//获得粘贴板的内容
		
		var elm = event.srcElement;

		var inputIndex = getInputIndex();
		var selectLength = getRangeLength();
		
		var elmValueBegin = elm.value.substring(0,inputIndex);//插入点前的已有值
		var elmValueEnd = elm.value.substring(inputIndex + selectLength);//插入点后的已有值
		var fullValue = elmValueBegin + clipboardDataText + elmValueEnd;//粘贴内容 + 已有值
		
		var valiResult = valiDecimalValue(fullValue,intLength,decimalLength);
		if(!valiResult){
			alert("粘贴板中存在非法字符或与已有值冲突");
		}
		else{
			//由于获取插入点的方法会破坏粘贴板与输入框的原有属性,因此粘贴成功时,需改变原值
			elm.value = elmValueBegin + elmValueEnd;
		}
		return valiResult;
	}
	else
		return true;
}

/** 校验粘贴板内容是否符合整数
 *  只能校验是否为纯数字，没有长度及范围校验
 */
function valiClipboardDataByNumber(){
		
	if(event.ctrlKey && event.keyCode == 86){
		var valiResult = valiNumberValue(clipboardData.getData("text"));
		if(!valiResult){
			alert("粘贴板中存在非法字符");
		}
		return valiResult;
	}
	else
		return true;
}

/** 校验输入键是否为功能键
 *  用于校验组合键
 */
function valiBaseFunctionKey(){
	if(event.ctrlKey && baseFunctionKeyCodes.indexOf("," + event.keyCode.toString() + ",") != -1)
		return true;
	else
		return false;
}

/** 校验输入键是否为功能键
 *	支持所有键盘功能
 */
function valiAllBaseFunctionKey(){
	if(baseKeyCodes.indexOf("," + event.keyCode.toString() + ",") != -1){
		return true;
	}
	return valiBaseFunctionKey();
}

/** 校验输入小数点的合法性
 *  已输入小数的返回false
 */
function valiInputPoint(){
	
	var elmValue = event.srcElement.value;
	
	if(pointKeyCodes.indexOf("," + event.keyCode.toString() + ",") != -1 && (elmValue.indexOf(".") != -1 || elmValue == "")){
		var selectText = document.selection.createRange().text;
		var inputIndex = getInputIndex();
		if(inputIndex != 0 && selectText.indexOf(".") != -1 && selectText.indexOf(".") + inputIndex == elmValue.indexOf(".")){
			return true;
		}
		return false;
	}
	else
		return true;
}

/** 校验输入项是否符合小数
 *  intLength：整数位长
 *  decimalLength：小数位长
 *  能根据整数位长及小数位长进行精度校验
 */
function valiInputDecimal(intLength,decimalLength){
	
	var elm = event.srcElement;
	var elmValue = elm.value;
	
	var int = "",decimal = "";
	
	var decimalIndex = elmValue.indexOf(".");
	
	if(decimalIndex != -1){
		int = elmValue.substring(0,decimalIndex);
		decimal = elmValue.substring(decimalIndex + 1);
	}
	else
		int = elmValue;
	
	var oRange =  document.selection.createRange();//创建选择域对象的文本对象
	
	if(NumKeyCodes.indexOf("," + event.keyCode.toString() + ",") != -1 && oRange.text == ""){
		
		//获得插入点序号
		oRange.setEndPoint("startToStart",elm.createTextRange());
		var inputIndex = oRange.text.length;
		
		if(decimalIndex == -1){
			if(int.length >= parseInt(intLength))
				return false;
		}
		else{
			if(inputIndex <= decimalIndex && int.length >= parseInt(intLength))
				return false;
			else if(inputIndex > decimalIndex && decimal.length >= parseInt(decimalLength))
				return false;
		}
	}
	
	return true;
}

/** 校验小数
 *  value：输入数字
 *  intLength：整数位长
 *  decimalLength：小数位长
 *  能根据整数位长及小数位长进行精度校验
 */
function valiDecimalValue(value,intLength,decimalLength){
	
	if(isNaN(value)){
		return false;
	}
	
	var int = "",decimal = "";
	var decimalIndex = value.indexOf(".");
	
	if(decimalIndex != -1){
		int = value.substring(0,decimalIndex);
		decimal = value.substring(decimalIndex + 1);
	}
	else
		int = value;
	
	if(int.length > parseInt(intLength) || decimal.length > parseInt(decimalLength))
		return false;
	else
		return true;
}

/** 校验整数
 *  value：输入数字
 *  只能校验是否为纯数字，没有长度及范围校验
 */
function valiNumberValue(value){
	
	if(isNaN(value) || value.indexOf(".") != -1)
		return false;
	else
		return true;
}


function isNumber(str){
	if(isNaN(str)){
		return false;
	}
	return true;
}

function valiTel(value){

	//电话长度在8位和11位两种
	if(value.length != 8 && value.length != 11){
		return false;
	}
	
	//开头不能为0
	if(value.charAt(i)=="0"){
		return false;
	}
	
	//var telHead = value.substring(0,2);

	//为13，15开头的为手机，长度应为11位。
	//if((telHead == "13" || telHead == "15")){
		//if(value.length != 11){
			//return false;
		//}
	//}
	//否则为固话。长度应为8位。
	//else{
		//if(value.length != 8){
			//return false;
		//}
	//}

	//必须全为数字。
	for(var i = 0; i < value.length; i++){
		if(!isNumber(value.charAt(i))){
			return false;
		}
	}
	return true;
}

function valiFjh(value){
	
	if(value.length != 0 && value.length > 6){
		return false;
	}

	//必须全为数字。
	for(var i = 0; i < value.length; i++){
		if(!isNumber(value.charAt(i))){
			return false;
		}
	}
	return true;
}

function valiChineseName(value){

	var unsideStr = "0123456789";
	unsideStr += "０１２３４５６７８９";
	unsideStr += "‘’'‘’\"“”＇＂";
	unsideStr += ",，?？·";

	var chineseCount = 0;
	
	for(var i = 0; i < value.length; i++){
		var str = value.charAt(i);
		if(unsideStr.indexOf(str)!=-1){
			return false;
		}

		var tmpCode = value.charCodeAt(i);
		if(tmpCode > 255){
			chineseCount++;
		}
	}

	if(chineseCount < 2){
		return false;
	}

	return true;
}

function isDate(dateValue){
	var newDate = new Date(dateValue);
	if(isNaN(newDate)){
		return false;
	}
	
	var year = newDate.getYear().toString();
	var month = (parseInt(newDate.getMonth())+1).toString();
	if(month.length == 1){
		month = 0 + month;
	}
	var date = newDate.getDate().toString();
	if(date.length == 1){
		date = 0 + date;
	}
	var formatDate = year + "/" + month + "/" + date;
	if(dateValue != formatDate){
		return false;
	}

	return true;
}

function valiSjx(value){
	/*
	if(value.length != 8 && value.length != 9 && value.length != 10 && value.length != 11){
		return false;
	}
	else{
		if(value.length == 8){
			if(value.charAt(4) != "年" || value.charAt(7) != "月"){
				return false;
			}
			else{
				var dateValue = value.substring(0,4) + "/" + value.substring(5,7) + "/" + "01";
				return isDate(dateValue);
			}
		}
		else if(value.length == 9){
			if(value.charAt(4) != "年" || value.charAt(6) != "月" || value.charAt(8) != "日"){
				return false;
			}
			else{
				var dateValue = value.substring(0,4) + "/0" + value.charAt(5) + "/0" + value.charAt(7);
				return isDate(dateValue);
			}
		}
		else if(value.length == 10){
			if(value.charAt(4) != "-" && value.charAt(4) != "－" && value.charAt(4) != "年"){
				return false;
			}
			else if(value.charAt(7) != "-" && value.charAt(7) != "－" && value.charAt(6) != "月" && value.charAt(7) != "月"){
				return false;
			}
			else{

				var dateValue = value.substring(0,4) + "/" + value.substring(5,7) + "/" + value.substring(8,10);
				
				if(value.charAt(4) == "年" && (value.charAt(6) != "月" && value.charAt(7) != "月" || value.charAt(9) != "日")){
					return false;
				}
				else if(value.charAt(4) == "年"){
					dateValue = value.substring(0,4) + "/" ;
					if(value.charAt(6) == "月"){
						dateValue += "0" + value.charAt(5) + "/" + value.substring(7,9);
					}
					else if(value.charAt(7) == "月"){
						dateValue += value.substring(5,7) + "/0" + value.charAt(8);
					}
				}
				return isDate(dateValue);
			}
		}
		else if(value.length == 11){
			if(value.charAt(4) != "年" || value.charAt(7) != "月" || value.charAt(10) != "日"){
				return false;
			}
			else{
				var dateValue = value.substring(0,4) + "/" + value.substring(5,7) + "/" + value.substring(8,10);
				return isDate(dateValue);
			}
		}
	}
	return false;
	*/
	if(value.length != 9 && value.length != 10 && value.length != 11){
		return false;
	}
	else{
		if(value.length == 9){
			if(value.charAt(4) != "年" || value.charAt(6) != "月" || value.charAt(8) != "日"){
				return false;
			}
			else{
				var dateValue = value.substring(0,4) + "/0" + value.charAt(5) + "/0" + value.charAt(7);
				return isDate(dateValue);
			}
		}
		else if(value.length == 10){
			if(value.charAt(4) != "-" && value.charAt(4) != "－" && value.charAt(4) != "年"){
				return false;
			}
			else if(value.charAt(7) != "-" && value.charAt(7) != "－" && value.charAt(6) != "月" && value.charAt(7) != "月"){
				return false;
			}
			else{

				var dateValue = value.substring(0,4) + "/" + value.substring(5,7) + "/" + value.substring(8,10);
				
				if(value.charAt(4) == "年" && (value.charAt(6) != "月" && value.charAt(7) != "月" || value.charAt(9) != "日")){
					return false;
				}
				else if(value.charAt(4) == "年"){
					dateValue = value.substring(0,4) + "/" ;
					if(value.charAt(6) == "月"){
						dateValue += "0" + value.charAt(5) + "/" + value.substring(7,9);
					}
					else if(value.charAt(7) == "月"){
						dateValue += value.substring(5,7) + "/0" + value.charAt(8);
					}
				}
				return isDate(dateValue);
			}
		}
		else if(value.length == 11){
			if(value.charAt(4) != "年" || value.charAt(7) != "月" || value.charAt(10) != "日"){
				return false;
			}
			else{
				var dateValue = value.substring(0,4) + "/" + value.substring(5,7) + "/" + value.substring(8,10);
				return isDate(dateValue);
			}
		}
	}
	return false;
}

function valiMaxLength(){
	var elm = event.srcElement;
	if(elm.maxLength && !isNaN(elm.maxLength)){
		var maxLength = parseInt(elm.maxLength);
		
		var factLength = elm.value.length;
		for(i = 0; i < elm.value.length; i++){
			var tmpCode = elm.value.charCodeAt(i);
			if(tmpCode > 255){
				factLength++;
				if(factLength > maxLength){
					alert("输入长度大于最大长度");
					focusElm(elm);
					break;
				}
			}
		}
	}
}

function formatValueMaxLength(){
	var elm = event.srcElement;
	if(elm.maxLength && !isNaN(elm.maxLength)){
		var maxLength = parseInt(elm.maxLength);
		
		var factLength = elm.value.length;
		for(i = 0; i < elm.value.length; i++){
			var tmpCode = elm.value.charCodeAt(i);
			if(tmpCode > 255){
				factLength++;
				if(factLength > maxLength){
					alert("输入长度大于最大长度");
					var newValue = elm.value;
					newValue = newValue.substring(0,i-1);
					elm.value = newValue;
					focusElm(elm);
					return;
				}
			}
		}
	}
}

function valiMinLength(){
	var elm = event.srcElement;
	
	if(elm.minlength && !isNaN(elm.minlength) && elm.value != ""){
		if(isNaN(elm.fractionLength)){
			invokeMinLength();
		}
		else if(parseFloat(elm.value) != 0){
			invokeMinLength();
		}
	}
}

function invokeMinLength(){
	var elm = event.srcElement;
	var minLength = parseInt(elm.minlength);

	var factLength = elm.value.length;
	for(i = 0; i < elm.value.length; i++){
		var tmpCode = elm.value.charCodeAt(i);
		if(tmpCode > 255){
			factLength++;
		}
	}
	
	if(factLength < minLength){
		alert("输入长度小于最小长度");
		if(elm.oldValue && elm.value != elm.oldValue){
			elm.value = elm.oldValue
		}
		else{
			elm.value = "";
		}
		focusElm(elm);
	}
	else{
		elm.oldValue = elm.value;
	}
}

function validateReportData(){
	
	var reportTable = document.getElementById("grid");
	
	if(reportTable && reportTable.nodeName == "TABLE"){
		var array = reportTable.getElementsByTagName("INPUT");
		for(var i=0;i<array.length;i++){
			var fractionFlag = 0; // 0: express it is Integer eg 89 ;else express it is have fraction ,eg: 89.34;
			var elm = array[i];
			if(elm && elm.fractionLength && elm.type!="hidden" && elm.style.display!="none"){
				var intLength = 99;
				if(!isNaN(elm.maxLength)){
					if( elm.fractionLength != 0 ){
						fractionFlag = 1;	
					}
					intLength = parseInt(elm.maxLength) - parseInt(elm.fractionLength) - fractionFlag;					
				}
				var valiFlag = valiDecimalValue(elm.value,intLength,elm.fractionLength);
				if(!valiFlag){
					var msg = "该项存在精度输入错误，请确认输入值的";
					if(intLength!=99){
						msg += "整数长度小于" + intLength + "以及";
					}
					msg += "小数长度小于" + elm.fractionLength;
					alert(msg);
					focusElm(elm);
					return false;
				}
			}
		}
	}
	return true;
}
