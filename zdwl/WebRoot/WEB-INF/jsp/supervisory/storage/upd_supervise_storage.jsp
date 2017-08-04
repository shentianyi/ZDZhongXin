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

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script type="text/javascript">
var vin=null; 
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	
}
$(function(){
	vin= getElement("superviseImport.vin").value;
});

//执行保存操作
function doSave(){
	if(!$("#superviseImport\\.draft_num").combobox('getValue')){
		alert("请选择票号");
		return false;
	}
	
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
		if(data > 0&&vin !=getElement("superviseImport.vin").value ){
			alert("车架号不能重复");
			return false;
		}else{
			document.forms[0].submit();
		}
	});
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/superviseStorage.do?method=superviseStorageList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

function init() {
	$("#superviseImport\\.draft_num").combobox({
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
			}
		}
	});

}

$(function(){
	init();
});
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改监管物入库</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/superviseStorage" styleId="superviseImportForm" method="post" onsubmit="return false">
<html:hidden property="superviseImport.id" styleId="superviseImport.id"/>
<input type="hidden" name="method" id="method" value="updSuperviseStorage">
<input type="hidden" name="superviseImport.state" id="superviseImport.state" value="1">
<input type="hidden" name="draftid" id="draftid">
<table class="formTable">
	<tr>
		<td class="nameCol">票号：</td>
		<td class="codeCol">
			<%-- <form:select property="superviseImport.draft_num" styleId="superviseImport.draft_num">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="draftOptions" label="label" value="value" />
			</form:select> --%>
			<select style="width:100%" id="superviseImport.draft_num" name="superviseImport.draft_num" >
				<c:forEach items="${draftOptions }" var="row">
					<option <c:if test="${superviseImportForm.superviseImport.draft_num==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
				</c:forEach>
			</select>
		</td>
		<td class="nameCol">车辆型号：</td>
		<td class="codeCol">
			<html:text property="superviseImport.car_model" styleId="superviseImport.car_model" disabled="disabled"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">颜色：</td>
		<td class="codeCol">
			<html:text property="superviseImport.color" styleId="superviseImport.color" disabled="disabled"></html:text>
		</td>
		<td class="nameCol">车架号：</td>
		<td class="codeCol">
			<html:text property="superviseImport.vin" styleId="superviseImport.vin" disabled="disabled"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">发动机号：</td>
		<td class="codeCol">
			<html:text property="superviseImport.engine_num" styleId="superviseImport.engine_num" disabled="disabled"></html:text>
		</td>
		<td class="nameCol">合格证号：</td>
		<td class="codeCol">
			<html:text property="superviseImport.certificate_num" styleId="superviseImport.certificate_num" disabled="disabled"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">钥匙数：</td>
		<td class="codeCol">
			<html:text property="superviseImport.key_amount" styleId="superviseImport.key_amount" disabled="disabled"></html:text>
		</td>
		<td class="nameCol">单价(元)：</td>
		<td class="codeCol">
			<html:text property="superviseImport.money" styleId="superviseImport.money" disabled="disabled"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol">合格证接收时间：</td>
		<td class="codeCol">
			<form:calendar property="superviseImport.certificate_intime" styleId="superviseImport.certificate_intime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
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
