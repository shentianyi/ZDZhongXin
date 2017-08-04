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
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script type="text/javascript">
//页面初始化函数
function doLoad(){
	var superviseid = document.getElementById("mailing.superviseid").value;
	changeYj(superviseid);
}

//执行保存操作
function doSave(){
	var superviseid = document.getElementById("mailing.superviseid").value;
	if(superviseid == ""){
		alert("请选择监管员");
		return false;
	}
	var mailnature = document.getElementById("mailing.mailnature").value;
	if(mailnature == ""){
		alert("请填写快递性质");
		return false;
	}
	var mailtime = document.getElementById("mailing.mailtime").value;
	if(mailtime == ""){
		alert("请填写邮寄时间");
		return false;
	}
	var express = document.getElementById("mailing.express").value;
	if(express == ""){
		alert("请填写快递公司");
		return false;
	}
	var express_num = document.getElementById("mailing.express_num").value;
	if(express_num == ""){
		alert("请填写单号");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("mailingForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/mailing.do?method=mailingList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

function changeYj(id) {
		if(id==-1){
			document.forms[0].reset();
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
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改监管员邮寄明细</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/mailing" styleId="mailingForm" method="post" onsubmit="return false">
<html:hidden property="mailing.id" styleId="mailing.id"/>
<input type="hidden" name="method" id="method" value="updMailing">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>监管员：</td>
		<td class="codeCol">
			<form:select property="mailing.superviseid" styleId="mailing.superviseid" onchange="changeYj(this.value)">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="superviseOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol">经销商：</td>
		<td class="codeCol">
			<input type="text" id="yjjxs" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol">合作金融机构：</td>
		<td class="codeCol">
			<input type="text" id="yjjrjg" readonly="readonly"/>
		</td>
		<td class="nameCol">工号：</td>
		<td class="codeCol">
			<input type="text" id="yjgh" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>快递性质：</td>
		<td class="codeCol">
			<html:text property="mailing.mailnature" styleId="mailing.mailnature"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>邮寄时间：</td>
		<td class="codeCol">
			<form:calendar property="mailing.mailtime" styleId="mailing.mailtime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>快递公司：</td>
		<td class="codeCol">
			<html:text property="mailing.express" styleId="mailing.express"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>单号：</td>
		<td class="codeCol">
			<html:text property="mailing.express_num" styleId="mailing.express_num"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">内容：</td>
		<td class="codeCol">
			<html:text property="mailing.des" styleId="mailing.des"></html:text>
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
<br/>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>
</body>
</html>
