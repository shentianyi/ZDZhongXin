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
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script type="text/javascript">
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	document.forms[0].submit();
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/superviseOut.do?method=superviseOutList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改监管物出库</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/superviseOut" styleId="superviseImportForm" method="post" onsubmit="return false">
<html:hidden property="superviseImport.id" styleId="superviseImport.id"/>
<input type="hidden" name="method" id="method" value="updSuperviseOut">
<input type="hidden" name="superviseImport.state" id="superviseImport.state" value="2">
<table class="formTable">
	<tr>
		<td class="nameCol">钥匙数：</td>
		<td class="codeCol">
			<html:text property="superviseImport.key_amount" styleId="superviseImport.key_amount" disabled="true"></html:text>
		</td>
		<td class="nameCol">合格证发证日期：</td>
		<td class="codeCol">
			<form:calendar property="superviseImport.certificate_date" styleId="superviseImport.certificate_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" disabled="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol">合格证号：</td>
		<td class="codeCol">
			<html:text property="superviseImport.certificate_num" styleId="superviseImport.certificate_num" disabled="true"></html:text>
		</td>
		<td class="nameCol">车辆型号：</td>
		<td class="codeCol">
			<html:text property="superviseImport.car_model" styleId="superviseImport.car_model" disabled="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">车身结构：</td>
		<td class="codeCol">
			<html:text property="superviseImport.car_structure" styleId="superviseImport.car_structure" disabled="true"></html:text>
		</td>
		<td class="nameCol">排量：</td>
		<td class="codeCol">
			<html:text property="superviseImport.displacement" styleId="superviseImport.displacement" disabled="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">颜色：</td>
		<td class="codeCol">
			<html:text property="superviseImport.color" styleId="superviseImport.color" disabled="true"></html:text>
		</td>
		<td class="nameCol">发动机号：</td>
		<td class="codeCol">
			<html:text property="superviseImport.engine_num" styleId="superviseImport.engine_num" disabled="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">车架号：</td>
		<td class="codeCol">
			<html:text property="superviseImport.vin" styleId="superviseImport.vin" disabled="true"></html:text>
		</td>
		<td class="nameCol">钥匙号：</td>
		<td class="codeCol">
			<html:text property="superviseImport.key_num" styleId="superviseImport.key_num" disabled="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">金额：</td>
		<td class="codeCol">
			<html:text property="superviseImport.money" styleId="superviseImport.money" disabled="true"></html:text>
		</td>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:text property="superviseImport.des" styleId="superviseImport.des" disabled="true"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">回款金额：</td>
		<td class="codeCol">
			<input type="text" name="superviseImport.payment_amount" id="payment_amount" 
								class="easyui-numberbox" value="<c:out value='${superviseImportForm.superviseImport.payment_amount}'/>"
								data-options="min:0,precision:2"></input>
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
