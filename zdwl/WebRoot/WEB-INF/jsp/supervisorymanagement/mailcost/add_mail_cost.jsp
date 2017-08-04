<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//执行保存操作
function doSave(){
	var value = document.getElementById("mailcost.fqdate").value;
	if(value == ""){
		alert("请填写发起日期");
		return false;
	}
	var value=""; 
	var arr = document.getElementsByName("mailingitemss");
	for(var i=0;i<arr.length;i++){
	        if(arr[i].checked){    
	          value=value+","+arr[i].value;
	        }
	    }
	if(value == ""){
		alert("请选择邮寄项目");
		return false;
	}else{
		if(value.indexOf("3") > 0 ){
			var part=$("#part").val();
			if(part==""){
				alert("请填写配件");
				return false;
			}
		}
		if(value.indexOf("5") > 0){
			var other=$("#other").val();
			if(other==""){
				alert("请填写其他");
				return false;
			}
		}
	}
	
	var value =$("#mailperson").combobox('getValue'); 
	if(value == "-1"){
		alert("请选择邮寄人");
		return false;
	}
	var value = document.getElementById("mailcost.express").value;
	if(value == ""){
		alert("请填写快递公司");
		return false;
	}
	
	var value = document.getElementById("mailcost.money").value;
	if(value == ""){
		alert("请填写预计金额");
		return false;
	}
	var value = document.getElementById("mailcost.transportbegin").value;
	if(value == ""){
		alert("请填写运输城市（起）");
		return false;
	}
	var value = document.getElementById("mailcost.transportend").value;
	if(value == ""){
		alert("请填写运输城市（止）");
		return false;
	}
	var value = $("#receiveid").combobox('getValue');;
	if(value == "-1"){
		alert("请选择接收人");
		return false;
	}
	//提交表单
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/mailcost.do?method=findList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
function changeYj(id) {
		if(id==-1){
			return;
		}
		var url = "../json/getMailCost.do?callback=?&id="+id;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setYj(data[0]);
		});
	}
	
function setYj(yj){
	$("#yjgh").val(yj.gh);
	$("#yjjxs").val(yj.jxs);
	$("#yjjrjg").val(yj.jrjg);
}

function changeJs(id) {
		if(id==-1){
			return;
		}
		var url = "../json/getMailCost.do?callback=?&id="+id;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setJs(data[0]);
		});
	}
	
function setJs(js){
	$("#jsgh").val(js.gh);
	$("#jsjxs").val(js.jxs);
	$("#jsjrjg").val(js.jrjg);
}


function changeMailItems(){
	var value=""; 
	var arr = document.getElementsByName("mailingitemss"); 
	for(var i=0;i<arr.length;i++){
        if(arr[i].checked){    
          value=value+","+arr[i].value;
        }
    }
	if(value.indexOf("3") > 0 ){
		$("#mailcostParts").show();
	}
	if(value.indexOf("3") <= 0 ){
		$("#mailcostParts").hide();
		$("#part").val("");
	}
	if(value.indexOf("5") > 0){
		$("#mailcostOther").show();
	}
	if(value.indexOf("5") <= 0){
		$("#mailcostOther").hide();
		$("#other").val("");
	}
}

function init() {
	$("#mailperson").combobox({
		onHidePanel : function() {// 验证输入的值，如果不存在下拉列表 则置空
			var newValue = $(this).combobox('getValue');
			var data = $(this).combobox('getData');
			var flag = false;
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (newValue == data[i].value) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				$(this).combobox('clear');
			} else {
				changeYj(newValue);
			}

		}
	});
	var mailperson = $("#mailperson").combobox('getValue');
	if (mailperson) {
		changeYj(mailperson);
	}
	$("#receiveid").combobox({
		onHidePanel : function() {// 验证输入的值，如果不存在下拉列表 则置空
			var newValue = $(this).combobox('getValue');
			var data = $(this).combobox('getData');
			var flag = false;
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (newValue == data[i].value) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				$(this).combobox('clear');
			} else {
				changeJs(newValue);
			}

		}
	});
	var receiveid = $("#receiveid").combobox('getValue');
	if (receiveid) {
		changeJs(receiveid);
	}
}

$(function(){
	init();
});
</script>
</head>
  
<body >
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管员信息管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#"> 邮寄费用申请</a>
             &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">新增邮寄费用申请</a>
         </span>
</div>
<div class="title">新增邮寄费用申请</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/mailcost" styleId="mailcostForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addMailcost">
<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>发起日期：</td>
		<td class="codeCol">
			<form:calendar property="mailcost.fqdate" styleId="mailcost.fqdate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>邮寄项目：</td>
		<td class="codeCol">
			<form:checkboxs property="mailingitemss" styleId="mailingitemss" collection="mailcostStateOptions" onchange="changeMailItems()"/>
		</td>
	</tr>
	<tr id="mailcostParts" hidden="hidden">
		<td class="nameCol"><font color="#FF0000">*</font>配件：</td>
		<td class="codeCol" colspan="3">
			<input style="width:60%;" type="text"  name="mailcost.parts" id="part"/>
		</td>
	</tr>
	<tr id="mailcostOther" hidden="hidden">
		<td class="nameCol"><font color="#FF0000">*</font>其他：</td>
		<td class="codeCol" colspan="3">
			<input style="width:60%;" type="text"  name="mailcost.other" id="other"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>邮寄人：</td>
		<td class="codeCol">
			<select style="width:250px" id="mailperson" name="mailcost.mailperson" onchange="changeYj(this.value)" >
			<option value="-1">请选择</option>
			<c:forEach items="${superviseOptions }" var="row">
				<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
			</c:forEach>
		</td>
		<td class="nameCol">工号：</td>
		<td class="codeCol">
			<input type="text" id="yjgh" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol">经销商：</td>
		<td class="codeCol">
			<input type="text" id="yjjxs" readonly="readonly"/>
		</td>
		<td class="nameCol">金融机构：</td>
		<td class="codeCol">
			<input type="text" id="yjjrjg" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>快递公司：</td>
		<td class="codeCol">
			<html:text property="mailcost.express" styleId="mailcost.express"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>预计金额：</td>
		<td class="codeCol">
			<html:text property="mailcost.money" styleId="mailcost.money"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>运输城市起：</td>
		<td class="codeCol">
			<html:text property="mailcost.transportbegin" styleId="mailcost.transportbegin"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>运输城市止：</td>
		<td class="codeCol">
			<html:text property="mailcost.transportend" styleId="mailcost.transportend"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>接收人：</td>
		<td class="codeCol">
			<select style="width:250px" id="receiveid" name="mailcost.receiveid" onchange="changeJs(this.value)" >
			<option value="-1">请选择</option>
			<c:forEach items="${superviseOptions }" var="row">
				<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
			</c:forEach>
		</td>
		<td class="nameCol">工号：</td>
		<td class="codeCol">
			<input type="text" id="jsgh" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol">经销商：</td>
		<td class="codeCol">
			<input type="text" id="jsjxs" readonly="readonly"/>
		</td>
		<td class="nameCol">金融机构：</td>
		<td class="codeCol">
			<input width="100%" type="text" id="jsjrjg" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol">事件描述：</td>
		<td class="codeCol">
			<html:text property="mailcost.des" styleId="mailcost.des"></html:text>
		</td>
		<td class="nameCol"></td>
		<td class="codeCol"></td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
			<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
			<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>
</table>

</html:form>
<br/>
<div id="message" class="message" style="display:none"></div>

	</div>
</div>
</body>
</html>
