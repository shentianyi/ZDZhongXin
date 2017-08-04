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
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	var vin = document.getElementById("superviseImport.vin").value;
	var vinType = /^[a-zA-Z0-9]/;
	if(vin != ""){
		if (!(vinType.test(vin))) {
			alert("车架号格式错误！");
			return false;
		} else if (vin.length != 17) {
			alert("车架号位数不正确！");
			return false;
		}
	}
	var url = "../json/checkVin.do?callback=?&vin="+vin;
	$.getJSON(url, function(result) {
		var data = result.data;
		if(data > 0){
			alert("车架号不能重复");
			return false;
		}else{
			document.forms[0].submit();
		}
	});
		
}
//执行返回列表操作
function doReturn(){
	var id=document.getElementById("handoverLog.id").value;
	window.location="<url:context/>/handoverLog.do?method=updHandoverBook&handoverLog.id="+id;
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

</script>
<style type="text/css">
.nameCol2{
width:25%;
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee; 
text-align: right;
}
.codeCol2{
width:25%;
border-bottom:1px solid #eee;padding-top:13px;padding-bottom:13px;
border-right:1px solid #eee; 
} 
</style>
</head>
   
<body onLoad="doLoad()">
<div class="title">
	<c:choose>  
	   <c:when test="${superviseImportStatus==5}">新增私自售卖车辆</c:when>  
	   <c:when test="${superviseImportStatus==6}">新增私自移动车辆</c:when>    
	</c:choose> 
</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/handoverLog" styleId="handoverLogForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addSuperviseImport">
<html:hidden property="superviseImport.state" styleId="superviseImport.state"/>
<html:hidden property="handoverLog.id" styleId="handoverLog.id"/>
<table class="formTable">
	<tr>
		<td class="nameCol2">票号：</td>
		<td class="codeCol2">
			<form:select property="draftid" styleId="draftid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="draftOptions" label="label" value="value" />
			</form:select>
		</td>
		<td class="nameCol2"></td>
		<td class="codeCol2"></td>
	</tr>
	<tr>
		<td class="nameCol2">合格证发证日期：</td>
		<td class="codeCol2">
			<form:calendar property="superviseImport.certificate_date" styleId="superviseImport.certificate_date" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol2">合格证号：</td>
		<td class="codeCol2">
			<html:text property="superviseImport.certificate_num" styleId="superviseImport.certificate_num"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol2">车辆型号：</td>
		<td class="codeCol2">
			<html:text property="superviseImport.car_model" styleId="superviseImport.car_model"></html:text>
		</td>
		<td class="nameCol2">车身结构：</td>
		<td class="codeCol2">
			<html:text property="superviseImport.car_structure" styleId="superviseImport.car_structure"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol2">排量：</td>
		<td class="codeCol2">
			<html:text property="superviseImport.displacement" styleId="superviseImport.displacement"></html:text>
		</td>
		<td class="nameCol2">颜色：</td>
		<td class="codeCol2">
			<html:text property="superviseImport.color" styleId="superviseImport.color"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol2">发动机号：</td>
		<td class="codeCol2">
			<html:text property="superviseImport.engine_num" styleId="superviseImport.engine_num"></html:text>
		</td>
		<td class="nameCol2"><font color="#FF0000">*</font>车架号：</td>
		<td class="codeCol2">
			<html:text property="superviseImport.vin" styleId="superviseImport.vin"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol2">钥匙号：</td>
		<td class="codeCol2">
			<html:text property="superviseImport.key_num" styleId="superviseImport.key_num"></html:text>
		</td>
		<td class="nameCol2">钥匙数量：</td>
		<td class="codeCol2">
			<html:text property="superviseImport.key_amount" styleId="superviseImport.key_amount"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol2">金额：</td>
		<td class="codeCol2">
			<input type="text" name="superviseImport.money" id="superviseImport.money" 
								class="easyui-numberbox" 
								data-options="min:0,precision:2"></input>
		</td>
		<td class="nameCol2">合格证接收时间：</td>
		<td class="codeCol2">
			<form:calendar property="superviseImport.certificate_intime" styleId="superviseImport.certificate_intime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="nameCol2">备注：</td>
		<td class="codeCol2">
			<html:text property="superviseImport.des" styleId="superviseImport.des"></html:text>
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
