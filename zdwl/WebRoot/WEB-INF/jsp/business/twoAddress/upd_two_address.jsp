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
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script type="text/javascript">

//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	//对表单内容进行验证，包括对输入类型等限制方式
	var distribid = document.getElementById("twoAddress.distribid").value;
	if(distribid == -1 ){
		alert("请填写经销商");
		return false;
	}
	var two_name = document.getElementById("twoAddress.two_name").value;
	if(two_name == ""){
		alert("请填写名称");
		return false;
	}
	/* var type = document.getElementById("twoAddress.type").value;
	if(type == -1){
		alert("请选择类别");
		return false;
	} */
	var type = $("#twoAddress\\.type").combobox('getValue');
	if(type == "请选择..."){
        alert("请选择类别");
        return false;
    }
	var two_address = document.getElementById("twoAddress.two_address").value;
	if(two_address == ""){
		alert("请填写详细地址");
		return false;
	}
	var two_username = document.getElementById("twoAddress.two_username").value;
	if(two_username == ""){
		alert("请填写联系人");
		return false;
	}
	var phone = document.getElementById("twoAddress.phone").value;
	if(phone == ""){
		alert("请填写联系电话");
		return false;
	}
	var distance = document.getElementById("twoAddress.distance").value;
	if(distance == ""){
		alert("请填写距离");
		return false;
	}
	
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("twoAddressForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/twoAddress.do?method=twoAddressList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

/* 需求32修改下拉框为easyUI下拉筛选控件 */
$(function(){
	$("#twoAddress\\.distribid").combobox({
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
});
$(function(){
	$("#twoAddress\\.type").combobox({
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
});
</script>
</head>
  
<body onLoad="doLoad()">
<div class="title">修改监管场地</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/twoAddress" styleId="twoAddressForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="updTwoAddress">
<html:hidden property="twoAddress.id" styleId="twoAddress.id"/>
<html:hidden property="twoAddress.createuserid" styleId="twoAddress.createuserid"/>
<html:hidden property="twoAddress.createdate" styleId="twoAddress.createdate"/>
<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
		<td class="codeCol">
			<!-- <form:select property="twoAddress.distribid" styleId="twoAddress.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select> -->
			<select id="twoAddress.distribid" name="twoAddress.distribid" style="width:85%;">
					<option value="-1">请选择...</option>
							<c:forEach items="${dealersOptions}" var="row">
							<option <c:if test="${row.value==twoAddressForm.twoAddress.distribid }">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
					</c:forEach>
			</select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>名称：</td>
		<td class="codeCol">
			<html:text property="twoAddress.two_name" styleId="twoAddress.two_name"></html:text>
		</td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>类别：</td>
		<td class="codeCol">
			<!-- <form:select property="twoAddress.type" styleId="twoAddress.type">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="twoAddressTypeOptions" label="label" value="value" />
			</form:select> -->
			<select id="twoAddress.type" name="twoAddress.type" style="width:85%;">
					<option value="-1">请选择...</option>
							<c:forEach items="${twoAddressTypeOptions}" var="row">
							<option <c:if test="${row.value==twoAddressForm.twoAddress.distribid }">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
					</c:forEach>
			</select>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>详细地址：</td>
		<td class="codeCol">
			<html:text property="twoAddress.two_address" styleId="twoAddress.two_address"></html:text>
		</td>
		
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>联系人：</td>
		<td class="codeCol">
			<html:text property="twoAddress.two_username" styleId="twoAddress.two_username"></html:text>
		</td>
		<td class="nameCol"><font color="#FF0000">*</font>联系方式：</td>
		<td class="codeCol">
			<html:text property="twoAddress.phone" styleId="twoAddress.phone"></html:text>
		</td>
		
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>距离(公里)：</td>
		<td class="codeCol">
			<html:text property="twoAddress.distance" styleId="twoAddress.distance"></html:text>
		</td>
		<td class="nameCol">备注：</td>
		<td class="codeCol">
			<html:text property="twoAddress.des" styleId="twoAddress.des"></html:text>
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
<br/>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>
</body>
</html>
