//(function(){
//	var ga = document.createElement('script');
//	ga.type = 'text/javascript';
//	ga.async = true;
//	ga.src = 'http://code.jquery.com/jquery-1.7.2.min.js';
//	var s = document.getElementsByTagName('script')[0];
//	s.parentNode.insertBefore(ga, s);	
//})();
//
//jQuery.noConflict();
//
//jQuery(document).ready(function($){
//    //jQuery("div").hide();
//	alert('ok');
//  });

var ga = document.createElement('script');
ga.type = 'text/javascript';
//ga.async = true;
ga.src = '/js/jquery-1.7.2.min.js';
var s = document.getElementsByTagName('script')[0];
s.parentNode.insertBefore(ga, s);

var gai = document.createElement('script');
gai.type = 'text/javascript';
//gai.async = true;
gai.src = '/js/ready.js';
s.parentNode.insertBefore(gai, s);	

//判断是否IE内核浏览器
rmsie = /(msie) ([\w.]+)/;

var uaie = navigator.userAgent;
if(uaie) uaie = uaie.toLowerCase( );
//alert("uu"+uaie);
if(!rmsie.exec(uaie)){
	//alert("请使用单一IE内核浏览器");
	//转换 至登录页面
//	var w = window.parent;
//	if(w){
//		w.location = "/login.jsp";
//	}else{
//		window.location= "/login.jsp";
//	}
}

function $(id){
	return document.getElementById(id);
}

function getElements(name){
	return document.getElementsByName(name);
}
function checkAll(name){
	var elements = getElements(name);
	
	for(var i = 0; i < elements.length; i++){
		if(!elements[i].disabled){
			elements[i].checked = event.srcElement.checked;
		}
	}
}

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
};

/**
 * is null return true;
 */
function isNull(str){
	str = trim(str);
	if( (!str) || str == ''){
		return true;
	}
	
	return false;
}

//将form中的元素处理成一个字符串，用于location时向后台传递全部数据
function getFormParamStr(oForm){
	var elms = oForm.elements;
	var str = "";
	for(var i=0;i<elms.length;i++){
		if(i>0){
			str += "&";
		}
		str += elms[i].name + "=" + elms[i].value;
	}

	return str;
}

function toReadonly(){
	var imgs = document.getElementsByTagName("IMG");
	for(var i=0; i<imgs.length; i++){
		if(imgs[i].src.indexOf("images/place.gif")==-1){
			imgs[i].removeNode(true);
			i--;
		}
	}
	for(var i=0; i<document.forms[0].elements.length; i++){
		var elm = document.forms[0].elements[i];
		if(elm.nodeName=="INPUT"){
			if(elm.type=="text"){
				var oDiv = document.createElement("DIV");
				oDiv.innerText = elm.value;
				var oParent = elm.parentNode;
				elm.removeNode(true);
				oParent.appendChild(oDiv.cloneNode(true));
				i--;
			}
			else if(elm.type=="button"){
				elm.removeNode(true);
				i--;
			}
			else {
				elm.disabled = true;
			}
		}
		else if(elm.nodeName=="SELECT"){
			var value = "";
			for(var j=0; j<elm.options.length; j++){
				var oOption = elm.options[j];
				if(oOption.selected){
					value = oOption.text;
				}
			}
			var oDiv = document.createElement("DIV");
			oDiv.innerText = value;
			var oParent = elm.parentNode;
			elm.removeNode(true);
			oParent.appendChild(oDiv.cloneNode(true));
			i--;
		}
		else if(elm.nodeName=="TEXTAREA"){
			var oDiv = document.createElement("DIV");
			oDiv.innerText = elm.value;
			oDiv.style.height = elm.offsetHeight;
			var oParent = elm.parentNode;
			elm.removeNode(true);
			oParent.appendChild(oDiv.cloneNode(true));
			i--;
		}
		else if(elm.nodeName=="BUTTON" && elm.removeFlag!="false"){
			elm.removeNode(true);
			i--;
		}
	}
}

//为数组添加一个加入另一数组内所有元素的方法.
Array.prototype.pushAll = function (array){
	for(var i = 0; i < array.length; i++){
		this.push(array[i]);
	}
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


var Map = function(){
	this.list = new Array();
}
var MapTable = function(key,obj){
	this.key = key;
	this.obj = obj;
}
Map.prototype.put = function(key,obj){
	var hasKey = false;
	for(var i = 0; i < this.list.length; i++){
		if(this.list[i].key == key){
			this.list[i].obj = obj;
			hasKey=true;
		}
	}
	if(!hasKey){
		this.list.push(new MapTable(key,obj));
	}
}
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

//求中英文混合字符的长度
function strlength(str){
	if(str == ""){
	 return 0;
	}
	var n = str.length;
	for (var i = 0; i < str.length; i++)
	{
		if (str.charCodeAt(i) < 0 || str.charCodeAt(i) > 255){
		  n++;
		}
	}
	return n;
 }
 
function formatZs(zs){
	var rs = "";
	//把整数部分以三位进行分隔
	var zslength = zs.length;
	var zsMod = zslength%3;
	if(zsMod != 0){
		 rs = zs.substring(0,zsMod);
	}	
	
	while(zsMod <zslength){
		if(zsMod != 0){
			rs += ",";
		}	
		//取三位，
		rs +=zs.substring(zsMod,zsMod+3);
		zsMod += 3;
	}
	return  rs;	
}				
// JS格式化数字为金钱格式 
function formatMoney(num){
	if(!num) return "0.00";
	num  = num.toString();
	var i = num.lastIndexOf('.');

	
	if(i == -1){
		return formatZs(num)+'.00';
	}		
	
	//整数部分
	var rs = "";
	var zs = num.substring(0,i);

  rs = formatZs(zs);
	
	var xs = num.substring(i+1);
	switch (xs.length){
		case 0:
			rs +=".00";
			break;
		case 1:
			rs +="."+xs+"0";
			break;
		case 2:
			rs +="."+xs;
			break;
		default:
			var pre2 = xs.substring(0,2); //前两位
			var pre3 = xs.substring(2,3); //第三位			
			
			if(pre2 == '00'){
				if(parseInt(pre3)>0){
					pre2 = '01';
				}else{
					pre2 = '00';
				}		
			}else{
				if(parseInt(pre3)>0){
					pre2 = (parseInt(pre2)+1).toString();
				}
			}		
			
			if(pre2 == 100){
				//alert(rs);
				rs = formatInt((parseNumber(rs)+1))+".00";
			}else if(pre2 < 10){
				rs +=".0"+pre2;	
			}else{
				rs +="."+pre2;
			}
			break;			
	}
	return rs;	
}

/**
 *解析数字num,
 * lx默认为int 如果为1，则为float
 */
function parseNumber(num,lx){
	if(isNull(num)) return 0;
	var n = num.toString();
	n = n.replace(/,/g,'')
	if(lx && 1==lx){
		return parseInt(n);
	}
	return parseFloat(n);
	
}

/**
 *格式化整数
 */
function formatInt(num){
	if(isNull(num)) return 0;
	
	var n = num.toString();
	var idx = n.indexOf('.');
	if(idx != -1){
		n = n.substring(0,idx);
	}
	return formatZs(n);
} 

/**
 * 
 * @param obj
 * @param tag
 * @returns
 */
function nextSibling(obj,tag){
	if(obj.nextSibling ){
		//alert(obj.nextSibling.tagName);
		if(obj.nextSibling.tagName == tag){
			return obj.nextSibling;
		}else{
			return nextSibling(obj.nextSibling,tag);
		}
	}else{
		return ;
	}
}

function getRadioValue(rv){
	var rs = document.getElementsByName(rv);
	if(rs ){
		for(var i = 0; i < rs.length; i++){
			if(rs[i].checked){
				return rs[i].value;
			}
		}
	}
}