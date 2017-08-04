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
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	var value = document.getElementById("supervisionfee.distribid").value;
	if(value == "-1"){
		alert("请选择经销商");
		return false;
	}
	var value = document.getElementById("supervisionfee.financial_institution").value;
	if(value == ""){
		alert("请填写金融机构");
		return false;
	}
	var value = document.getElementById("supervisionfee.bank").value;
	if(value == ""){
		alert("请填写合作银行");
		return false;
	}
	var value = document.getElementById("supervisionfee.brand").value;
	if(value == ""){
		alert("请填写品牌");
		return false;
	}
	var value = document.getElementById("supervisionfee.intime").value;
	if(value == ""){
		alert("请选择进店时间");
		return false;
	}
	var value = document.getElementById("supervisionfee.supervisionfee_standard").value;
	if(value == ""){
		alert("请填写监管费标准");
		return false;
	}
	var value = document.getElementById("supervisionfee.payment").value;
	if(value == ""){
		alert("请填写付费方式");
		return false;
	}
	var value = document.getElementById("supervisionfee.pay_standard").value;
	if(value == ""){
		alert("请填写缴费标准");
		return false;
	}
	var value = document.getElementById("supervisionfee.pay_time").value;
	if(value == ""){
		alert("请选择缴费时间");
		return false;
	}
	var value = document.getElementById("supervisionfee.pay_money").value;
	if(value == ""){
		alert("请填写缴费金额");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("supervisionfeeForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/supervisionfee.do?method=supervisionfeeList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">新增监管费/仓储费缴费</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/supervisionfee" styleId="supervisionfeeForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addSupervisionfee">
<input type="hidden" name="supervisionfee.type" id="supervisionfee.type" value="1">
<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
		<td class="codeCol">
			<form:select property="supervisionfee.distribid" styleId="supervisionfee.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>金融机构</td>
		<td class="codeCol">
			<html:text property="supervisionfee.financial_institution" styleId="supervisionfee.financial_institution"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>合作银行：</td>
		<td class="codeCol">
			<html:text property="supervisionfee.bank" styleId="supervisionfee.bank"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>品牌：</td>
		<td class="codeCol">
			<html:text property="supervisionfee.brand" styleId="supervisionfee.brand"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>进店时间：</td>
		<td class="codeCol">
			<form:calendar property="supervisionfee.intime" styleId="supervisionfee.intime" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>监管费标准：</td>
		<td class="codeCol">
			<html:text property="supervisionfee.supervisionfee_standard" styleId="supervisionfee.supervisionfee_standard"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>付费方式：</td>
		<td class="codeCol">
			<html:text property="supervisionfee.payment" styleId="supervisionfee.payment"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>缴费标准：</td>
		<td class="codeCol">
			<html:text property="supervisionfee.pay_standard" styleId="supervisionfee.pay_standard"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>缴费时间：</td>
		<td class="codeCol">
			<form:calendar property="supervisionfee.pay_time" styleId="supervisionfee.pay_time" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>缴费金额：</td>
		<td class="codeCol">
			<html:text property="supervisionfee.pay_money" styleId="supervisionfee.pay_money"></html:text>
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
