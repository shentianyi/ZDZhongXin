<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//执行保存操作
$(function(){
	initBank();
});

function doSave(){
	if($("#bankId").val()==""||$("#bankId").val()=="-1"){
		alert("请选择银行");
		return false;
	}
	
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/business/ywBank.do?method=findList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

function initBank() {
	$("#one option:gt(0)").remove();
	loadSelect(-1, $("#one"));

	$("#one").change(function() {
		$("#two option:gt(0)").remove();
		$("#three option:gt(0)").remove();
		var id = this.value;
		if (id>0) {
			loadSelect(id, $("#two"));
			setBank(id,$(this).find("option:selected").text());
		}else{
			setBank($("#one").val(),$("#one").find("option:selected").text());
		}
	});
	$("#two").change(function() {
		var id = this.value;
		if (id>0) {
			$("#three option:gt(0)").remove();
			loadSelect(id, $("#three"));
			setBank(id,$(this).find("option:selected").text());
		}else{
			setBank($("#one").val(),$("#one").find("option:selected").text());
			$("#three").val("-1");
		}
	});
	$("#three").change(function(){
		var id = this.value;
		if (id>0) {
			setBank(id,$(this).find("option:selected").text());
		}else{
			setBank($("#two").val(),$("#two").find("option:selected").text());
		}
	});
	
}


function loadSelect(id, nextSelect) {
	var url = "../json/getBankChildById.do?method=findChildListById&callback=?&bankQuery.id="
			+ id;
	$.getJSON(url, function(result) {
		var data = result.data;
		console.info(data);
		$.each(data, function(i, item) {
			var option = "<option value="+item.id+">" + item.bankName
					+ "</option>";
			nextSelect.append(option);
		});
	});
}

function setBank(id,name){
	if(id>0){
		$("#bankId").val(id);
	}else{
		$("#bankId").val("");
	}
}
	

</script>
</head>
  
<body>
<div class="title">新增业务员绑定银行</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/business/ywBank.do" styleId="ywBankForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="add">
<input type="hidden" name="ywBank.bankId" id="bankId" value="<c:out value='${ywBankForm.query.bankId }'/>" />
<input type="hidden" name="ywBank.userId" value="<c:out value='${ywBankForm.query.userId }'/>" />
<html:hidden property="query.userId"/>
<table class="formTable">
	
	<tr>
		<td class="nameCol">选择绑定银行：</td>
		<td class="codeCol" colspan="3">
			<select id="one">
					<option value="-1">请选择...</option>
			</select> <select id="two">
					<option value="-1">请选择...</option>
			</select> <select id="three">
					<option value="-1">请选择...</option>
			</select>
		</td>
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
