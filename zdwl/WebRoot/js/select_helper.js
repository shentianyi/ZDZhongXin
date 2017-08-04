//键盘key对象
var SelectKeyCodeManager = function(){
	this.keyCodes = new Array(96,97,98,99,100,101,102,103,104,105,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90);
	this.values = new Array(0,1,2,3,4,5,6,7,8,9,"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
}
//通过键盘key获得对应值
SelectKeyCodeManager.prototype.getValueByKeyCode = function(keyCode){
	for(var i=0;i<this.keyCodes.length;i++){
		if(this.keyCodes[i]==keyCode){
			return this.values[i];
		}
	}
	return -1;
}
var selectKeyCodeManager = new SelectKeyCodeManager();
//下拉列表帮助对象
var SelectHelperManager = function(oSelect){
	this.oSelect = oSelect;
	this.lastKeyDownDate = null;
	this.lastKeyDownLog = "";
}
//变量-用于记录下拉列表与帮助类实例关系
var selectHelperManagerMap = new Map();
//根据用户输入key检索符合条件的第一位选项
function selectKeydownHelper(){
	
	if(valiAllBaseFunctionKey()){
		return true;
	}
	
	var oSelect = event.srcElement;
	var selectHelperManager = selectHelperManagerMap.get(oSelect);
	if(!selectHelperManager){
		selectHelperManager = new SelectHelperManager(oSelect);
		selectHelperManagerMap.put(oSelect,selectHelperManager);
	}
	
	var thisValue = selectKeyCodeManager.getValueByKeyCode(event.keyCode);
	if(thisValue!=-1){
		selectHelperManager.lastKeyDownLog += thisValue;
		selectHelperManager.lastKeyDownDate = new Date();
		for(var i=0;i<oSelect.options.length;i++){
			if(oSelect.options[i].text.indexOf(selectHelperManager.lastKeyDownLog)==0){
				oSelect.options[i].selected = true;
				break;
			}
		}
	}
	setTimeout(clearSelectLastKeyDownLog,clearTime);
	return false;
}

//延时清理时长：0.7秒
var clearTime = 700;
//清理用户输入key记录
function clearSelectLastKeyDownLog(){
	for(var i=0;i<selectHelperManagerMap.list.length;i++){
	    var selectHelperManager = selectHelperManagerMap.list[i].obj;
		var lastKeyDownDate = selectHelperManager.lastKeyDownDate;
		if(lastKeyDownDate){
			var now = new Date();
			if(parseInt(lastKeyDownDate.getTime()+clearTime)<=parseInt(now.getTime())){
				selectHelperManager.lastKeyDownDate = null;
				selectHelperManager.lastKeyDownLog = "";
			}
		}
	}
	setTimeout(clearSelectLastKeyDownLog,clearTime/2);
}

//为select添加option
function addOption(oSelect,value,text){
	if(!oSelect.nodeName || oSelect.nodeName != "SELECT"){
		alert("addOptionByOption method is must be used by SELECT OBJECT");
		return false;
	}
	else{
		var oOption = document.createElement("OPTION");
		oOption.text = text;
		oOption.value = value;
		oSelect.add(oOption);
	}
	
	return true;
}
