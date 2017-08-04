// JavaScript Document
function initializeShareholderTable(tableId,delFunction){
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
	dTable.rowNumber = oTable.tBodies[0].rows.length;
	dTable.indexId = 0;
	dTable.delFunction = delFunction;
	dTable.delFunctionName="";
	if(delFunction){
		dTable.delFunctionName = delFunction.toString().substring(delFunction.toString().indexOf("function")+8,delFunction.toString().indexOf("("))+"()";
	}
	for(var i = 0; i < oTable.tBodies[0].rows.length; i++){
		var oTr = oTable.tBodies[0].rows[i];
		var stateFlag = oTr.childNodes[0].childNodes[0];
		if(stateFlag && stateFlag.value == templetFlag){
			dTable.listName = stateFlag.name.substring(0,stateFlag.name.indexOf("["));
			stateFlag.value = addFlag;
			dTable.templetTr = oTr;
			dTable.templetName = oTr.name;
			oTr.removeNode(true);
			i--;
			break;
		}
	}

	dTableMap.put(tableId,dTable);
}

function addShareholderRow(tableId){
	var dTable = dTableMap.get(tableId);
	var oTable = dTable.table;
	if(!oTable || oTable.nodeName != "TABLE"){
		alert("新增行：根据\"" + tableId + "\"未找到TABLE对象");
		return false;
	}
	if(!dTable.templetTr || dTable.templetTr.nodeName != "TR"){
		alert("未初始化列表，或初始化列表时未设定模板行");
		return false;
	}
	var newTr = document.createElement("<tr/>");
	newTr = dTable.templetTr.cloneNode(true);
	newTr.childNodes[0].innerHTML = "<input type=\"hidden\" name=\"" + dTable.listName + "[" + dTable.indexId +"].stateFlag\" value=\"" + addFlag + "\">" + dTable.rowNumber;
	newTr.childNodes[0].align = "center";
	newTr.childNodes[newTr.childNodes.length-1].innerHTML = "<button class=\"formButton\" onClick=\"delShareholderRow('" + tableId + "',this.parentNode.parentNode);" + dTable.delFunctionName + "\">删除</button>";
	newTr.childNodes[newTr.childNodes.length-1].align = "center";
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
	oTable.tBodies[0].insertBefore(newTr);
	dTable.rowNumber++;
	dTable.indexId++;
}

function delShareholderRow(tableId,oRow){
	if(!oRow || oRow.nodeName != "TR"){
		alert("被删除对象不是行对象");
		return false;
	}
	oRow.removeNode(true);
	var dTable = dTableMap.get(tableId);
	var oTable = dTable.table;
	dTable.rowNumber = oTable.tBodies[0].rows.length;
	dTable.indexId = 0;
	for(var i = 0; i < oTable.tBodies[0].rows.length; i++){
		var oTr = oTable.tBodies[0].rows[i];
		var stateFlag = oTr.childNodes[0].childNodes[0];
		if(stateFlag && stateFlag.nodeName == "INPUT"){
			oTr.childNodes[0].innerHTML = "<input type=\"hidden\" name=\"" + getElmName(dTable.listName,stateFlag.name,dTable.indexId) + "\" value=\"" + stateFlag.value + "\">" + dTable.rowNumber;
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
		}
	}
}

function delShareholder(id){
	if(confirm("删除附件操作立即生效，是否确认删除？")){
		location = webroot + "/common/uploadfile.do?method=delete&id=" + fileId + "&url=" + url;
	}
}