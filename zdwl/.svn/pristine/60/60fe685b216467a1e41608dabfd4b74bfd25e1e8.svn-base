// JavaScript Document
var normalFlag = 0;//未改变
var addFlag = 1;//新增
var updFlag = 2;//修改
var delFlag = 3;//删除
var templetFlag = 4;//模板

var DynamicTable = function(tableId){
	this.tableId = tableId;
}

var dTableMap={};
function initializeTable(tableId,delFunction,addCallBack){
	//创建不定长TABLE对象
	var dTable = new DynamicTable(tableId);
	//获得被处理的页面TABLE对象
	var oTable = document.getElementById(tableId);
	if(!oTable || oTable.nodeName != "TABLE"){
		alert("初始化列表：根据\"" + tableId + "\"未找到TABLE对象");
		return;
	}
	//设置不定长对象属性
	dTable.table = oTable;
	dTable.rowNumber = 1;
	dTable.indexId = 0;
	dTable.delFunction = delFunction;
	dTable.addCallBack = addCallBack;
	dTable.delFunctionName="";
	if(delFunction){
		dTable.delFunctionName = delFunction.toString().substring(delFunction.toString().indexOf("function")+8,delFunction.toString().indexOf("("))+"()";
	}
	for(var i = 0; i < oTable.tBodies[0].rows.length; i++){
		var oTr = oTable.tBodies[0].rows[i];
		var stateFlag;
		var oTrInput = oTr.getElementsByTagName("INPUT");
		if(oTrInput[0]){
			stateFlag = oTrInput[0];
		}
		if(stateFlag && stateFlag.value == templetFlag){
			dTable.listName = stateFlag.name.substring(0,stateFlag.name.indexOf("["));
			stateFlag.value = addFlag;
			dTable.templetTr = oTr;
			dTable.templetName = oTr.name;
			oTr.parentNode.removeChild(oTr);
			i--;
			break;
		}
	}
	for(var i = 0; i < oTable.tBodies[0].rows.length; i++){
		var oTr = oTable.tBodies[0].rows[i];
		var stateFlag;
		if(oTr.childNodes[0].childNodes[0]){
			stateFlag = oTr.childNodes[0].childNodes[0];
		}
		dTable.rowNumber++;
		dTable.indexId++;
		oTr.getElementsByTagName("TD")[0].innerHTML = "<input type=\"hidden\" name=\"" + getElmName(dTable.listName,stateFlag.name,i) + "\" value=\"0\">" + (i +1);
		oTr.getElementsByTagName("TD")[0].align = "center";
		oTr.getElementsByTagName("TD")[1].innerHTML = "<button class=\"formButton\" onClick=\"delRow('" + tableId + "',this.parentNode.parentNode);" + dTable.delFunctionName + "\">删除</button>";
		oTr.getElementsByTagName("TD")[1].align = "center";
		var elms = oTr.getElementsByTagName("INPUT");
		for(var j = 0; j < elms.length; j++){
			elms[j].name = getElmName(dTable.listName,elms[j].name,i);
			if(elms[j].addEventListener){
				elms[j].addEventListener("change",changeStateFlag,true);
			}else if(elms[j].attachEvent){
				elms[j].attachEvent("onchange",changeStateFlag);
			}
		}
		elms = oTr.getElementsByTagName("SELECT");
		for(var j = 0; j < elms.length; j++){
			elms[j].name = getElmName(dTable.listName,elms[j].name,i);
			if(elms[j].addEventListener){
				elms[j].addEventListener("change",changeStateFlag,true);
			}else if(elms[j].attachEvent){
				elms[j].attachEvent("onchange",changeStateFlag);
			}
		}
	}

	dTableMap[tableId] = dTable;
}

function addRow(tableId){
	var dTable = dTableMap[tableId];
	var oTable = dTable.table;
	if(!oTable || oTable.nodeName != "TABLE"){
		alert("新增行：根据\"" + tableId + "\"未找到TABLE对象");
		return false;
	}
	if(!dTable.templetTr || dTable.templetTr.nodeName != "TR"){
		alert("未初始化列表，或初始化列表时未设定模板行");
		return false;
	}
	var newTr = document.createElement("tr");
	newTr = dTable.templetTr.cloneNode(true);
	newTr.getElementsByTagName("TD")[0].innerHTML = "<input type=\"hidden\" name=\"" + dTable.listName + "[" + dTable.indexId +"].stateFlag\" value=\"" + addFlag + "\">" + dTable.rowNumber;
	newTr.getElementsByTagName("TD")[0].align = "center";
	newTr.getElementsByTagName("TD")[1].innerHTML = "<button class=\"formButton\" onClick=\"delRow('" + tableId + "',this.parentNode.parentNode);" + dTable.delFunctionName + "\">删除</button>";
	newTr.getElementsByTagName("TD")[1].align = "center";
	var elms = newTr.getElementsByTagName("INPUT");
	for(var i = 0; i < elms.length; i++){
		elms[i].id = getElmName(dTable.listName,elms[i].name,dTable.indexId);
		elms[i].name = getElmName(dTable.listName,elms[i].name,dTable.indexId);
	}
	elms = newTr.getElementsByTagName("SELECT");
	for(var i = 0; i < elms.length; i++){
		elms[i].id = getElmName(dTable.listName,elms[i].name,dTable.indexId);
		elms[i].name = getElmName(dTable.listName,elms[i].name,dTable.indexId);
	}
	elms = newTr.getElementsByTagName("IMG");
	for(var i = 0; i < elms.length; i++){
		if(elms[i].target){
			elms[i].target = getElmName(dTable.listName,elms[i].target,dTable.indexId);
		}
	}
	oTable.tBodies[0].appendChild(newTr);
	dTable.rowNumber++;
	dTable.indexId++;
	dTable.addCallBack(dTable.rowNumber-2);
}

function delRow(tableId,oRow){
	if(!oRow || oRow.nodeName != "TR"){
		alert("被删除对象不是行对象");
		return false;
	}
	var oRowInputs = oRow.getElementsByTagName("INPUT"); 
	var stateFlag = oRowInputs[0];
	if(!stateFlag || stateFlag.nodeName != "INPUT"){
		alert("该行没有状态位");
		return false;
	}
	var name = stateFlag.name;
	var value = stateFlag.value
	if(stateFlag.value == addFlag){
		oRow.parentNode.removeChild(oRow);
		oRow.removeNode(true);
	}
	else{
		stateFlag.value = delFlag;
		oRow.style.display = "none";
	}
	var dTable = dTableMap.get(tableId);
	var oTable = dTable.table;
	dTable.rowNumber = 1;
	dTable.indexId = 0;
	for(var i = 0; i < oTable.tBodies[0].rows.length; i++){
		var oTr = oTable.tBodies[0].rows[i];
		var stateFlag = oTr.childNodes[0].childNodes[0];
		oTr.getElementsByTagName("TD")[0].innerHTML = "<input type=\"hidden\" name=\"" + getElmName(dTable.listName,stateFlag.name,i) + "\" value=\"" + stateFlag.value + "\">" + dTable.rowNumber;
		
		var elms = oTr.getElementsByTagName("INPUT");
		for(var j = 0; j < elms.length; j++){
			elms[j].id = getElmName(dTable.listName,elms[j].name,i);
			elms[j].name = getElmName(dTable.listName,elms[j].name,i);
		}
		elms = oTr.getElementsByTagName("SELECT");
		for(var j = 0; j < elms.length; j++){
			elms[j].id = getElmName(dTable.listName,elms[j].name,i);
			elms[j].name = getElmName(dTable.listName,elms[j].name,i);
		}
		elms = newTr.getElementsByTagName("IMG");
		for(var j = 0; j < elms.length; j++){
			if(elms[j].target){
				elms[j].target = getElmName(dTable.listName,elms[j].target,i);
			}
		}
		
		dTable.indexId++;
		if(oTr.style.display != "none"){
			dTable.rowNumber++;
		}
	}
}

function changeStateFlag(){
	var elm = event.srcElement ? event.srcElement : event.target;
	var oTr = getTr(elm);
	var oTrwInputs = oTr.getElementsByTagName("INPUT"); 
	var stateFlag = oTrwInputs[0];
	if(!stateFlag || stateFlag.nodeName != "INPUT"){
		alert("该行没有状态位");
		return false;
	}
	stateFlag.value = updFlag;
}

function getTr(elm){
	var oTr = elm;
	while(oTr && oTr.nodeName != "TR"){
		oTr = oTr.parentNode;
	}
	return oTr;
}

function getElmName(listName,elmName,indexId){
	var nameBegin = listName+"[";
	var nameEnd = elmName.substring(elmName.indexOf("]"));
	return nameBegin + indexId + nameEnd;
}
