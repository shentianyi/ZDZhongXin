//服务名
var webroot = "/";
//图片服务名
var imgroot = "/";

//提示信息
function alertMessage(msg){
	if(msg=="操作成功"){
		return;
	}
	msg = msg.replaceAll("<br>","\n");
	msg = msg.replaceAll("&lt;br&gt;","\n");
	alert(msg);
}
//提示信息
function showMessage(message){
	if(message && message!="" && message!="null"){
		//message = message.replaceAll("<br>","\n");
		//message = message.replaceAll("&lt;br&gt;","\n");
		/*var messageArea = document.getElementById("message");
		messageArea.innerHTML = message;
		messageArea.style.display = "";
		messageArea.scrollIntoView(false);*/
		alert(message);
	}
	
}
//用户注销
function logout(){
	var mainWindow = window.parent;
	mainWindow.location = webroot + "/rbac/login.do?method=logout";
	
}
//用户退出系统
function systemClear(){
	var mainWindow = window.parent;
	mainWindow.location = webroot + "csms/rbac/login.do?method=systemClear";
	mainWindow.close();
}
//返回系统首页
function gotoMianPage(){
	var mainWindow = window.parent;
	mainWindow.location = webroot + "entry.jsp";
}
//返回系统管理功能首页
function gotoManageSystem(){
	var mainWindow = window.parent;
	mainWindow.location = webroot + "system/mian.jsp";
}

//全选复选框
function checkAll(name){
	var elements = document.getElementsByName(name);
	
	for(var i = 0; i < elements.length; i++){
		if(!elements[i].disabled){
			elements[i].checked = event.srcElement.checked;
		}
	}
}
//根据复选框名称，判断复选框集合 中是否存在有被选中项
function hasCheckedItem(name){
	var checks = document.getElementsByName(name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			return true;
		}
	}
	return false;
}
//单选按钮，可以取消单选框的选中状态
function checkRadioButton(){
	var elm = event.srcElement;
	var lastCheckFlag = elm.lastCheckFlag;
	if(lastCheckFlag){
		elm.checked = !lastCheckFlag;
	}
	elm.lastCheckFlag = elm.checked;
}
//将表单内全部数据整理成数组返回
function formValueToArray(oForm){
	var lastValue = new Array();
	for(var i = 0; i < oForm.elements.length; i++){
		var valueElm = new Object();
		valueElm.name = oForm.elements[i].name;
		
		if(oForm.elements[i].nodeName == "INPUT" || oForm.elements[i].nodeName == "TEXTAREA"){
			if((oForm.elements[i].type=="checkbox" || oForm.elements[i].type=="radio") && oForm.elements[i].checked){
				valueElm.value = oForm.elements[i].value;
			}
			else{
				valueElm.value = oForm.elements[i].value;
			}
		}
		else if(oForm.elements[i].nodeName == "SELECT"){
			var indexId = oForm.elements[i].selectedIndex;
			if(indexId == -1){
				indexId = 0;
			}
			valueElm.value = oForm.elements[i].options[indexId].value;
		}
		
		lastValue.push(valueElm);
	}
	return lastValue;
}
//将数组内数据设置到指定的表单中
function ArrayValueToForm(value,oForm){
	
	for(var i = 0; i < value.length; i++){
		var name = value[i].name;
		if(oForm[name]){
			if(oForm[name].nodeName == "INPUT" || oForm[name].nodeName == "TEXTAREA"){
				if((oForm[name].type=="checkbox" || oForm[name].type=="radio")){
					for(var j = 0; j < oForm[name].length; j++){
						if(oForm[name][j].value = value[i].value){
							oForm[name][j].chekced = true;
						}
					}
				}
				else{
					oForm[name].value = value[i].value;
				}
			}
			else if(oForm[name].nodeName == "SELECT"){
				selectOption(oForm[name],value[i].value);
			}
		}
	}
}
//为下拉列表设置值（ie支持直接通过value属性进行设置，不需要使用此方法）
function selectOption(oSelect,value){
	for(var j = 0; j < oSelect.options.length; j++){
		if(oSelect.options[j].value == value){
			oSelect.options[j].selected = "true";
			break;
		}
	}
}
//通过ID获得对象
function getElement(id){
	return document.getElementById(id);	
}
//通过name获得对象集合
function getElements(name){
	return document.getElementsByName(name);
}
//获得页面最大宽度
function getWindowWidth(){
	//return parseInt(screen.availWidth) - 170;
	return parseInt(document.body.offsetWidth);
}
//获得页面最大高度
function getWindowHeight(){
	//return parseInt(screen.availHeight) - 76;
	return parseInt(document.body.offsetHeight);
}
//打开模态对话框
function showModalDialog_common(webroot,url,parameter,state){
	url = webroot + url;
	if(!parameter){
		parameter = "";
	}
	if(!state){
		state = "dialogHeight:600px;dialogWidth:800px;center:yes;";
	}
	return window.showModalDialog(url,parameter,state);
}
//同步模态框与主窗体的防提交参数
function synchroSubmitParams(parentWindow){
	var oPAGE_SUBMIT_UUID1 = parentWindow.document.getElementById("PAGE_SUBMIT_UUID");
	var oPAGE_SUBMIT_UUID2 = document.getElementById("PAGE_SUBMIT_UUID");
	//oPAGE_SUBMIT_UUID1.value = oPAGE_SUBMIT_UUID2.value;

}
//将防提交参数作为url串返回
function appendSubmitParamsByUrl(){
	return "PAGE_SUBMIT_UUID="+document.getElementById("PAGE_SUBMIT_UUID").value;
}
//通过对象父节点寻找TABLE对象
function findTableByParent(obj){
	var runCount = 0;
	while(obj && obj.nodeName != "TABLE" && runCount < 100){
		obj = obj.parentNode;
		runCount++;
	}
	if(obj && obj.nodeName == "TABLE"){
		return obj;
	}
	return undefined;
}

//通过对象父节点寻找TR对象
function findRowByParent(obj){
	var runCount = 0;
	while(obj && obj.nodeName != "TR" && runCount < 100){
		obj = obj.parentNode;
		runCount++;
	}
	if(obj && obj.nodeName == "TR"){
		return obj;
	}
	return undefined;
}

//通过对象父节点寻找TD对象
function findCellByParent(obj){
	var runCount = 0;
	while(obj && obj.nodeName != "TD" && runCount < 100){
		obj = obj.parentNode;
		runCount++;
	}
	if(obj && obj.nodeName == "TD"){
		return obj;
	}
	return undefined;
}

//通过对象子节点获得所有表单元素集合
function getElementsByObject(obj){
	var elements = new Array();
	var elms = obj.getElementsByTagName("INPUT");
	pushAll(elements,elms);
	elms = obj.getElementsByTagName("SELECT");
	pushAll(elements,elms);
	elms = obj.getElementsByTagName("TEXTAREA");
	pushAll(elements,elms);
	return elements;
}
//为数组添加一个加入另一数组内所有元素的方法.
function pushAll(array1,array2){
	for(var i = 0; i < array2.length; i++){
		array1.push(array2[i]);
	}
}
//去字符串前后空格
function trim(/*String*/ str){
	if(!str) return '';
	str = str.toString();
	str = str.replace(/^\s+/, '');
	for(var i = str.length - 1; i > 0; i--){
		if(/\S/.test(str.charAt(i))){
			str = str.substring(0, i + 1);
			break;
		}
	}
	return str;	// String
}
//字符串为空校验
function isNull(str){
	str = trim(str);
	if( (!str) || str == ''){
		return true;
	}
	
	return false;
}

//为字符串加入一个替换所有字符的方法.
String.prototype.replaceAll = function (oldValue,newValue){
	if(oldValue == newValue){
		return this;
	}
	
	var returnStr = this;
	
	while(returnStr.indexOf(oldValue)!=-1){
		returnStr = returnStr.replace(oldValue,newValue);
	}
	return returnStr;
}

//为字符串加入一个去前后空格的方法
String.prototype.trim = function (){
	
	var returnStr = this;
	
	while(returnStr.indexOf(" ") == 0){
		returnStr = returnStr.substring(1);
	}

	while(returnStr.length > 0 && returnStr.lastIndexOf(" ") == returnStr.length - 1){
		returnStr = returnStr.substring(0,returnStr.length - 1);
	}
	
	while(returnStr.indexOf("	") == 0){
		returnStr = returnStr.substring(1);
	}

	while(returnStr.length > 0 && returnStr.lastIndexOf("	") == returnStr.length - 1){
		returnStr = returnStr.substring(0,returnStr.length - 1);
	}
	return returnStr;
}

//为字符串加入一个自动加入换行符换行的方法
String.prototype.autoNewline = function (charLength){
	var oldStr = this;
	var doStr = this;
	var returnStr = "";
	
	if(isNaN(charLength)){
		throw new Error(charLength+"不为数字");
	}
	
	charLength = parseInt(charLength);
	
	while(doStr.length > charLength){
		
		if(doStr.indexOf("<br>") > 0 && doStr.indexOf("<br>") <= charLength){
			returnStr += doStr.substring(0,doStr.indexOf("<br>")) + "<br>";
			doStr = doStr.substring(doStr.indexOf("<br>")+4);
		}
		else if(doStr.indexOf("<br>") == 0){
			doStr = doStr.substring(4);
		}
		else{
			returnStr += doStr.substring(0,charLength) + "<br>";
			doStr = doStr.substring(charLength);
		}
	}
	
	returnStr += doStr;
	
	return returnStr;
}

//使脚本中可以创建Map对象
var Map = function(){
	this.list = new Array();
}
var MapTable = function(key,obj){
	this.key = key;
	this.obj = obj;
}
//为脚本段的Map开辟put方法
Map.prototype.put = function(key,obj){
	var isNewKey = true;
	for(var i=0;i<this.list.length;i++){
		if(this.list[i].key==key){
			this.list[i].obj=obj;
			isNewKey = false;
		}
	}
	if(isNewKey){
		this.list.push(new MapTable(key,obj));
	}
}
//为脚本段的Map开辟get方法
Map.prototype.get = function(key){
	for(var i = 0; i < this.list.length; i++){
		if(this.list[i].key == key){
			return this.list[i].obj;
		}
	}
	return null;
}
Map.prototype.getValueByIndex = function(index){
	if(this.list.length){
		return this.list[index].obj;
	}
	return null;
}
Map.prototype.getKeyByIndex = function(index){
	if(this.list.length){
		return this.list[index].key;
	}
	return null;
}

Map.prototype.length = function(){
	return this.list.length;
}
//为数值设置千分位分隔符
function setThousandPoint(number){
	number  = number.toString();
	var pointIndex = number.lastIndexOf('.');
	var intNumber,pointNumber;
	if(pointIndex != -1){
		//获得整数部分
		intNumber = number.substring(0,pointIndex);
		pointNumber = number.substring(pointIndex);
	}
	else{
		intNumber = number;
		pointNumber =".00";
	}
	var clone_number = "";
	
	var flag = "";
	if(intNumber.indexOf("-")==0){
		intNumber = intNumber.substring(1);
		flag = "-";
	}
	//把整数部分以三位进行分隔
	var intLength = intNumber.length;
	var residue = intLength%3;
	if(residue != 0){
		 clone_number = intNumber.substring(0,residue);
	}
	while(residue < intLength){
		if(residue != 0){
			clone_number += ",";
		}	
		//取三位，
		clone_number +=intNumber.substring(residue,residue+3);
		residue += 3;
	}
	return  flag + clone_number + pointNumber;
}				
//截取小数点后两位
function formatPoint(num){
	if(!num) return "0.00";
	num  = num.toString();
	var k = num.lastIndexOf('.');
	if(k != -1){
		return num.substring(0,k+3);
	}
	return num
}

////////////////////////////////////////////////////////////
//行变色使用搅拌

var tbodyColor = "#FFFFFF";       //表体颜色

var mouseOverColor = "#FFF5DC";   //当前行颜色
var checkColor = "#EAEDF0";       //选中行颜色

var buttonClassNames = ",realButton,realTitButton,realBdButton,realBGButton,";

//为行对象设置行变色方法
function setRowChangeColor(row){
   	row.attachEvent("onclick", clickRow);
    row.attachEvent("onmouseover",mouseOver);
    row.attachEvent("onmouseout",mouseOut);
}

function mouseOut(){
	var obj = findRowByParent(event.srcElement);
	
	if(obj){
		if(obj.mouseOutBackground){
			obj.style.backgroundColor=obj.mouseOutBackground;
		}
		else{
			obj.style.backgroundColor = tbodyColor;
			obj.mouseOutBackground = tbodyColor;
		}
		
		/*
		var buttons = obj.getElementsByTagName("input");
		
		for(i = 0; i < buttons.length; i++){
			if(buttonClassNames.indexOf(","+buttons[i].className+",")!=-1){
				if(buttons[i].mouseOutBackground){
					buttons[i].style.backgroundColor=buttons[i].mouseOutBackground;
				}
				else{
					buttons[i].style.backgroundColor = tbodyColor;
					buttons[i].mouseOutBackground = tbodyColor;
				}
			}
		}
		*/
	}
}

function mouseOver(){

	var obj = findRowByParent(event.srcElement);

	if(obj){
		obj.style.backgroundColor=mouseOverColor;
	
		/*
		var buttons = obj.getElementsByTagName("input");
		
		for(i = 0; i < buttons.length; i++){
			if(buttonClassNames.indexOf(","+buttons[i].className+",")!=-1){
				buttons[i].style.backgroundColor = mouseOverColor;
			}
		}
		*/
	}
	
}

function clickRow(){
	/*
	var srcObj = event.srcElement;
	var tableObj;
	var rowObj = findRowByParent(srcObj);
	
	if(!rowObj){
		return false;
	}
	
	tableObj = rowObj.parentNode;
	var rows = tableObj.rows;
	for(i = 0; i < rows.length; i++){
		
		rows[i].style.backgroundColor = tbodyColor;
		rows[i].mouseOutBackground = tbodyColor;
	
		var buttons = rows[i].getElementsByTagName("input");
		for(j = 0; j < buttons.length; j++){
			if(buttonClassNames.indexOf(","+buttons[j].className+",")!=-1){
				buttons[j].style.backgroundColor = tbodyColor;
				buttons[j].mouseOutBackground = tbodyColor;
			}
		}
	
	}
	
	rowObj.style.backgroundColor = checkColor;
	rowObj.mouseOutBackground = checkColor;
	
	var buttons = rowObj.getElementsByTagName("input");
	
	for(i = 0; i < buttons.length; i++){
		if(buttonClassNames.indexOf(","+buttons[i].className+",")!=-1){
			buttons[i].style.backgroundColor = checkColor;
			buttons[i].mouseOutBackground = checkColor;
		}
	}
	*/
}

function createRandomNumber(maxNumber){
	if(!maxNumber){
		maxNumber = 1;
	}
	return Math.floor(Math.random() * (maxNumber+1));
}

function notnull(ids){
	for(var i = 0;i<ids.length;i++){
		var obj = $("#"+ids[i]);
		var value = obj.val();
		var name = obj.parent().prev().html();
		if(!value){
			alert(name+"不能为空");
			return false;
		}
	}
	return true;
}

function changeDealer(id) {
	if(id<=0){
		setDealer(new Object());
		return;
	}
	var url = "../json/getDealerByIdJsonAction.do?callback=?&id="+id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		setDealer(data[0]);
	});
}

function getDealer(id){
	var dealer = null;
	$.ajax({
		url:"../json/getDealerByIdJsonAction.do?callback=?&id="+id,
		dataType:"jsonp",
		async:false,
		success:function(result){
			var data = result.data;
			dealer = data[0];
		}
	});
	dealer.payMoney =  isNaN(dealer.superviseMoney)
	?dealer.payMoney = dealer.superviseMoney:dealer.payMoney = dealer.superviseMoney/1;
	var payMode = dealer.payMode;
	var payMoney =null;
	var payModeStr="";
	if(payMode==1){
		payModeStr="月"; 
		payMoney = dealer.superviseMoney/12;
	}
	else if(payMode==2){
		payModeStr="季度";
		payMoney = dealer.superviseMoney/4;
	}
	else if(payMode==3){
		payModeStr="半年";
		payMoney = dealer.superviseMoney/2;
	}
	else if(payMode==4){
		payModeStr="年";
		payMoney = dealer.superviseMoney;
	}
	dealer.payMode = payModeStr;
	dealer.superviseMoney = payMoney;
	
	if(dealer.inDate){
		var tDate = new Date(dealer.inDate.time);
		dealer.inDate = tDate.getFullYear()+"-"+(tDate.getMonth()+1)+"-"+tDate.getDate();
	}
	
	return dealer;
}

function setDealer(dealer){
	var tDate;
	var date = new Date();
	if(getElement("invoice.applicantDate")){
		getElement("invoice.applicantDate").value = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	}
	$("#dealerName").val(dealer.dealerName);
	$("#contact").val(dealer.contact);
	$("#contactPhone").val(dealer.contactPhone);
	$("#brand").val(dealer.brand);
	$("#dealerNature").val(dealer.dealerNature);
	$("#superviseAddress").val(dealer.address);
	$("#province").val(dealer.province);
	$("#city").val(dealer.city);
	$("#bankName").val(dealer.bankName);
	$("#bankContact").val(dealer.bankContact);
	$("#bankPhone").val(dealer.bankPhone);
	
	$("#bindShopName").val(dealer.bindInfo);
	$("#distance").val(dealer.distance);
	
	$("#distance").val(dealer.distance);
	$("#handoverCompany").val(dealer.handoverCompany);
	$("#superviseMoney").val(dealer.superviseMoney);
	if(dealer.handoverDate){
		tDate = new Date(dealer.handoverDate.time);
		$("#handoverDate").val(tDate.getFullYear()+"-"+(tDate.getMonth()+1)+"-"+tDate.getDate());
	}
	if(dealer.inDate){
		tDate = new Date(dealer.inDate.time);
		$("#inStoreDate").val(tDate.getFullYear()+"-"+(tDate.getMonth()+1)+"-"+tDate.getDate());
	}
	$("#inStoreRemark").val(dealer.inStoreRemark);
	
	if(dealer.supervisionMode){
		$("[id^=supervisionMode]").prop("checked",false);
		
		if(dealer.supervisionMode.indexOf("1")>=0){
			$("#supervisionMode_1").prop("checked",true);
		}
		if(dealer.supervisionMode.indexOf("2")>=0){
			$("#supervisionMode_2").prop("checked",true);
		}
		if(dealer.supervisionMode.indexOf("3")>=0){
			$("#supervisionMode_3").prop("checked",true);
		}
		if(dealer.supervisionMode.indexOf("4")>=0){
			$("#supervisionMode_4").prop("checked",true);
		}
	}
	
	$("[id^=cooperationModel]").prop("checked",false);
	$("#cooperationModel_"+dealer.cooperationModel).prop("checked",true);
	
	$("[id^=provideLunch]").prop("checked",false);
	$("#provideLunch_"+dealer.provideLunch).prop("checked",true);
	
	$("[id^=isBindShop]").prop("checked",false);
	$("#isBindShop_"+dealer.ddorbd).prop("checked",true);
	
	$("#ddorbd").val(dealer.ddorbd==1?"是":"否");
	
	$("[id^=isNeedHandover]").prop("checked",false);
	$("#isNeedHandover_"+dealer.isNeedHandover).prop("checked",true);
	
	var payMoney= null;
	var payMode = dealer.payMode;
	var payModeStr;
	if(payMode==1){
		payModeStr="月"; 
		payMoney = dealer.superviseMoney/12;
	}
	else if(payMode==2){
		payModeStr="季度";
		payMoney = dealer.superviseMoney/4;
	}
	else if(payMode==3){
		payModeStr="半年";
		payMoney = dealer.superviseMoney/2;
	}
	else if(payMode==4){
		payModeStr="年";
		payMoney = dealer.superviseMoney;
	}
	$("#payMode").val(payModeStr);
	
	$("#paymentMoney").val(payMoney);
	
	if(dealer.payDate){
		tDate = new Date(dealer.payDate.time);
		if($("#payDate")!=null)
			$("#payDate").val(tDate.getFullYear()+"-"+(tDate.getMonth()+1)+"-"+tDate.getDate());
	}
	if(dealer.exitDate){
		tDate = new Date(dealer.exitDate.time);
		if($("#exitDate")!=null)
			$("#exitDate").val(tDate.getFullYear()+"-"+(tDate.getMonth()+1)+"-"+tDate.getDate());
	}
	if($("#bindShop")){
		if(dealer.ddorbd==2){
			$("#bindShop").hide();
		}else{
			$("#bindShop").show();
		}
	}
	if($("#jjgs")){
		if(dealer.isNeedHandover==2){
			$("#jjgs").hide();
		}else{
			$("#jjgs").show();
		}
	}
	
}


function changeSupervisor(id,dealerId){
	if (id <=0) {
		setSupervisor(new Object());
		$("#repositoryId").val(-1);
		return;
	}
	var url = "../json/getSupervisorByRepositoryId.do?callback=?&id=" + id;
	if(dealerId){
		url += "&dealerId="+dealerId;
	}
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data[0]);
		setSupervisor(data[0]);
	});
	
}

function setSupervisor(supervisor){
	$("#idNumber").val(supervisor.idNumber);
	$("#name").val(supervisor.name);
	$("#selfTelephone").val(supervisor.selfTelephone);
	$("#gender").val(supervisor.gender);
	$("#liveAddress").val(supervisor.liveAddress);
	$("#educationBackground").val(supervisor.educationBackground);
	$("#interviewee").val(supervisor.interviewee);
	$("#recommenderName").val(supervisor.recommenderName);
	$("#staffNo").val(supervisor.staffNo);
}

function selectDealer(maxSize,dealerIds,dealerName){
	this.maxSize = maxSize;
	this.dealerIds = dealerIds;
	this.addDealer = function(id,name){
		for(var i = 0;i<dealerIds.length;i++){
			if(dealerIds[i]==id){
				return;
			}
		}
		this.dealerIds.push(id);
		this.dealerName.push(name);
	}
	this.removeDealer = function(id){
		for(var i = 0;i<this.dealerIds.length;i++){
			if(this.dealerIds[i]==id){
				this.dealerIds.splice(i,1);
				this.dealerName.splice(i,1);
				break;
			}
		}
	}
	this.dealerName=dealerName;
	return this;
}

function selectRepo(maxSize,ids,names){
	this.maxSize = maxSize;
	this.ids = ids;
	this.addId = function(id,name){
		this.ids.push(id);
		this.names.push(name);
	}
	this.remove = function(id){
		for(var i = 0;i<this.ids.length;i++){
			if(this.ids[i]==id){
				this.ids.splice(i,1);
				this.names.splice(i,1);
				break;
			}
		}
	}
	this.names=names;
	return this;
}

//执行返回列表操作
function doReturn() {
	//location = "<url:context/>/market/projectCirculationForm.do?method=findList";
	if(history.length>1)
		history.go(-1);
	else
		window.close();
}

$(document).keypress(function(evt) {  
	var evt = window.event || arguments.callee.caller.arguments[0];
	var charCode = (evt.which)? evt.which : evt.keyCode;
	if(charCode == 13 || charCode == 0 || charCode == 1){	
		doQuery();
	} 
});


//导航栏标签
function mynav(){
var messagequery_list = document.getElementsByClassName("crumbs-link");
var content=document.getElementById("messagequery.type").value;
for(var i=0;i<messagequery_list.length;i++){ 
		if(content ==110){
			messagequery_list[0].innerText="信息提醒＞项目进驻流转单";
		}
		if(content ==48){
			messagequery_list[0].innerText="信息提醒＞监管员面试完成一日未安排培训提醒";
		}
		if(content ==49){
			messagequery_list[0].innerText="信息提醒＞监管员进入储备库15天未安排上岗提醒";
		}
		if(content ==58){
			messagequery_list[0].innerText="信息提醒＞监管员培训考核不通过提醒";
		}
		if(content ==50){
			messagequery_list[0].innerText="信息提醒＞监管员辞职离岗时间前十天未提交人员信息";
		}
		if(content ==101){
			messagequery_list[0].innerText="信息提醒＞进驻7天未交监管费提醒";
		}
		if(content ==102){
			messagequery_list[0].innerText="信息提醒＞监管费到期前30天进行提醒";
		}
		if(content ==103){
			messagequery_list[0].innerText="信息提醒＞监管费收取3天后未开票提醒";
		}
		if(content ==104){
			messagequery_list[0].innerText="信息提醒＞项目进驻当天协议未收回提醒";
		}
		if(content ==105){
			messagequery_list[0].innerText="信息提醒＞监管协议到期前30天提醒";
		}
		if(content ==106){
			messagequery_list[0].innerText="信息提醒＞监管协议签署10天未收回提醒";
		}
		if(content ==107){
			messagequery_list[0].innerText="信息提醒＞协议签署超15天未发进店流转单提醒";
		}
		if(content ==111){
			messagequery_list[0].innerText="信息提醒＞业务进驻流转单";
		}
		if(content ==112){
			messagequery_list[0].innerText="信息提醒＞项目撤出流转单";
		}
		if(content ==113){
			messagequery_list[0].innerText="信息提醒＞项目绑定流转单";
		}
		if(content ==114){
			messagequery_list[0].innerText="信息提醒＞项目解绑流转单";
		}
		if(content ==61){
			messagequery_list[0].innerText="信息提醒＞监管员生日提醒";
		}
		if(content ==62){
			messagequery_list[0].innerText="信息提醒＞监管员入职满一年提醒";
		}
		if(content ==66){
			messagequery_list[0].innerText="信息提醒＞释放申请书提醒";
		}
		if(content ==67){
			messagequery_list[0].innerText="信息提醒＞移动申请书提醒";
		}
		if(content ==63){
			messagequery_list[0].innerText="信息提醒＞设备维修申请单提醒";
		}
		if(content ==64){
			messagequery_list[0].innerText="信息提醒＞邮寄费用申请提醒";
		}
		if(content ==65){
			messagequery_list[0].innerText="信息提醒＞加班申请提醒";
		}
		if(content ==68){
			messagequery_list[0].innerText="信息提醒＞每月20日提交巡店检查计划提醒";
		}
		if(content ==69){
			messagequery_list[0].innerText="信息提醒＞每月20日提交视频检查计划提醒";
		}
		if(content ==70){
			messagequery_list[0].innerText="信息提醒＞未按风控巡检计划执行提醒";
		}
		if(content ==71){
			messagequery_list[0].innerText="信息提醒＞巡店报告上传3日未上传新报告提醒";
		}
		if(content ==72){
			messagequery_list[0].innerText="信息提醒＞巡店报告提醒";
		}
		if(content ==41){
			messagequery_list[0].innerText="信息提醒＞提交轮岗计划";
		}
		if(content ==42){
			messagequery_list[0].innerText="信息提醒＞管理费及社保费用";
		}
		if(content ==81){
			messagequery_list[0].innerText="信息提醒＞银行移动审批提醒";
		}
		if(content ==82){
			messagequery_list[0].innerText="信息提醒＞开票10个工作日未到车提醒";
		}
		if(content ==83){
			messagequery_list[0].innerText="信息提醒＞开票30天汇票未押满";
		}
		if(content ==84){
			messagequery_list[0].innerText="信息提醒＞汇票到期前7天未清票提醒";
		}
		if(content ==85){
			messagequery_list[0].innerText="信息提醒＞银行释放审批提醒";
		}
		if(content ==86){
			messagequery_list[0].innerText="信息提醒＞零库存零汇票提醒";
		}
		if(content ==87){
			messagequery_list[0].innerText="信息提醒＞最后一笔业务提醒";
		}
		if(content ==88){
			messagequery_list[0].innerText="信息提醒＞连续三天无业务发生提醒";
		}
		if(content ==89){
			messagequery_list[0].innerText="信息提醒＞移动车辆超过25天未处理提醒";
		}
		if(content ==90){
			messagequery_list[0].innerText="信息提醒＞移动车辆超过总库存20%提醒";
		}
		if(content ==91){
			messagequery_list[0].innerText="信息提醒＞异常车辆超过总库存15%提醒";
		}
		if(content ==43){
			messagequery_list[0].innerText="信息提醒＞提交监管员考勤";
		}
		if(content ==44){
			messagequery_list[0].innerText="信息提醒＞未按轮岗计划执行";
		}
		if(content ==45){
			messagequery_list[0].innerText="信息提醒＞监管员签到异常";
		}
		if(content ==46){
			messagequery_list[0].innerText="信息提醒＞监管员签退异常";
		}
			if(content ==47){
			messagequery_list[0].innerText="信息提醒＞项目进驻流转单发出后三天未录入人员信息";
		}
		if(content ==48){
			messagequery_list[0].innerText="信息提醒＞监管员面试完成一日未安排培训提醒";
		}
		if(content ==49){
			messagequery_list[0].innerText="信息提醒＞监管员进入储备库15天未安排上岗";
		}
		if(content ==58){
			messagequery_list[0].innerText="信息提醒＞监管员培训考核不通过提醒";
		}
		if(content ==50){
			messagequery_list[0].innerText="信息提醒＞监管员辞职离岗时间前十天未提交人员信息";
		}
		if(content ==51){
			messagequery_list[0].innerText="信息提醒＞监管员连续在一家店工作五个月";
		}
		if(content ==1){
			messagequery_list[0].innerText="信息提醒＞开票7个工作日未到车";
		}
		if(content ==2){
			messagequery_list[0].innerText="信息提醒＞开票15个工作日未到车";
		}
		if(content ==3){
			messagequery_list[0].innerText="信息提醒＞开票30天汇票未押满";
		}
		if(content ==4){
			messagequery_list[0].innerText="信息提醒＞汇票到期前7天提醒";
		}
		if(content ==5){
			messagequery_list[0].innerText="信息提醒＞汇票到期当天提醒";
		}
		if(content ==6){
			messagequery_list[0].innerText="信息提醒＞汇票到期未清票";
		}
		if(content ==7){
			messagequery_list[0].innerText="信息提醒＞0库存0汇票";
		}
		if(content ==8){
			messagequery_list[0].innerText="信息提醒＞连续三天出库5台以上";
		}
		if(content ==9){
			messagequery_list[0].innerText="信息提醒＞移动车辆超过30天未赎车或移回";
		}
		if(content ==10){
			messagequery_list[0].innerText="信息提醒＞连续3天无业务";
		}
		if(content ==11){
			messagequery_list[0].innerText="信息提醒＞监管物入库";
		}
		if(content ==12){
			messagequery_list[0].innerText="信息提醒＞监管物出库";
		}
		if(content ==13){
			messagequery_list[0].innerText="信息提醒＞监管物移动";
		}
		if(content ==14){
			messagequery_list[0].innerText="信息提醒＞本库日查库";
		}
		if(content ==15){
			messagequery_list[0].innerText="信息提醒＞二网日查库";
		}
		if(content ==16){
			messagequery_list[0].innerText="信息提醒＞设备维修申请";
		}
		if(content ==17){
			messagequery_list[0].innerText="信息提醒＞设备邮寄申请";
		}
		if(content ==18){
			messagequery_list[0].innerText="信息提醒＞最后一笔业务";
		}
		if(content ==19){
			messagequery_list[0].innerText="信息提醒＞经销商/金融机构绑定";
		}
		if(content ==20){
			messagequery_list[0].innerText="信息提醒＞经销商/金融机构拆分";
		}
		if(content ==21){
			messagequery_list[0].innerText="信息提醒＞业务流转单";
		}
		if(content ==22){
			messagequery_list[0].innerText="信息提醒＞经销商撤店";
		}
		if(content ==23){
			messagequery_list[0].innerText="信息提醒＞经销商退费";
		}
		if(content ==24){
			messagequery_list[0].innerText="信息提醒＞监管费变更";
		}
		if(content ==25){
			messagequery_list[0].innerText="信息提醒＞开票申请";
		}
		if(content ==26){
			messagequery_list[0].innerText="信息提醒＞项目进驻";
		}
		if(content ==27){
			messagequery_list[0].innerText="信息提醒＞模式变更";
		}
		if(content ==28){
			messagequery_list[0].innerText="信息提醒＞监管费标准单";
		}
		if(content ==29){
			messagequery_list[0].innerText="信息提醒＞监管员交接申请";
		}
		if(content ==30){
			messagequery_list[0].innerText="信息提醒＞储备库培训信息";
		}
		if(content ==31){
			messagequery_list[0].innerText="信息提醒＞协议到期30天";
		}
		if(content ==108){
			messagequery_list[0].innerText="信息提醒＞业务进驻流转单进驻前3天未匹配人员提醒";
		}

			}
}


function mynav2(){
	var messagequery_list = document.getElementsByClassName("crumbs-link");
	var content=document.getElementById("messagequery.type").value;
	for(var i=0;i<messagequery_list.length;i++){ 
		if(content ==53){
			messagequery_list[0].innerText="信息预警＞项目进驻流转单发出后五天未匹配人员信息预警";
		}
		if(content ==54){
			messagequery_list[0].innerText="信息预警＞监管员上岗未培训考核预警";
		}
		if(content ==55){
			messagequery_list[0].innerText="信息预警＞监管员面试完成三日未安排培训预警";
		}
		if(content ==56){
			messagequery_list[0].innerText="信息预警＞监管员辞职离岗时间前五天未提交人员信息预警";
		}
		if(content ==57){
			messagequery_list[0].innerText="信息预警＞监管员在一家店工作六个月";
		}
		if(content ==99){
			messagequery_list[0].innerText="信息预警＞银行释放审批一天未出库预警";
		}
		if(content ==100){
			messagequery_list[0].innerText="信息预警＞银行移动审批一天未出库预警";
		}
		if(content ==92){
			messagequery_list[0].innerText="信息预警＞开票15个工作日未到车预警";
		}
		if(content ==93){
			messagequery_list[0].innerText="信息预警＞开票45天汇票未押满预警";
		}
		if(content ==94){
			messagequery_list[0].innerText="信息预警＞汇票到期当天未清票预警";
		}
		if(content ==95){
			messagequery_list[0].innerText="信息预警＞零库存零汇票延续7天预警";
		}
		if(content ==96){
			messagequery_list[0].innerText="信息预警＞连续五天无业务发生预警";
		}
		if(content ==97){
			messagequery_list[0].innerText="信息预警＞移动车辆超过30天未处理预警";
		}
		if(content ==98){
			messagequery_list[0].innerText="信息预警＞异常车辆超过总库存20%预警";
		}
		if(content ==52){
			messagequery_list[0].innerText="信息预警＞监管员连续三天未正常出勤";
		}
		if(content ==115){
			messagequery_list[0].innerText="信息预警＞进驻15天未交监管费预警";
		}
		if(content ==116){
			messagequery_list[0].innerText="信息预警＞监管费到期前10天进行预警";
		}
		if(content ==117){
			messagequery_list[0].innerText="信息预警＞监管费收取后7天未开票预警";
		}
		if(content ==118){
			messagequery_list[0].innerText="信息预警＞监管费到期日未收费预警";
		}
		if(content ==119){
			messagequery_list[0].innerText="信息预警＞项目进驻15天协议未收回预警";
		}
		if(content ==120){
			messagequery_list[0].innerText="信息预警＞监管协议到期前15天预警";
		}
		if(content ==121){
			messagequery_list[0].innerText="信息预警＞协议到期未续签预警";
		}
		if(content ==2){
			messagequery_list[0].innerText="信息预警＞协议到期30天";
		}
		if(content ==3){
			messagequery_list[0].innerText="信息预警＞协议逾期未回收提醒";
		}
		if(content ==4){
			messagequery_list[0].innerText="信息预警＞监管费到期前30天提醒";
		}
		if(content ==5){
			messagequery_list[0].innerText="信息预警＞发送进店流转单未按时进驻提醒";
		}
		if(content ==6){
			messagequery_list[0].innerText="信息预警＞协议到期预警";
		}
		if(content ==7){
			messagequery_list[0].innerText="信息预警＞监管费到期前15天";
		}
		if(content ==8){
			messagequery_list[0].innerText="信息预警＞监管费到期前一周预警";
		}
		if(content ==9){
			messagequery_list[0].innerText="信息预警＞监管费当天到期提醒";
		}
		if(content ==10){
			messagequery_list[0].innerText="信息预警＞监管费余额不足";
		}
		if(content ==11){
			messagequery_list[0].innerText="信息预警＞协议到期未续签";
		}
		}
	}