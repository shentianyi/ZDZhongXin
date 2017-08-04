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
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>

<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	
	var value = document.getElementById("role.role_name").value;
	if(value == ""){
		alert("请填写角色名称");
		return false;
	}
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("roleForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/rbac/role.do?method=roleList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

$(function(){
	$("#role\\.client_type").combobox({
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
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">系统管理与配置</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">角色管理</a>
     		 &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">新增角色</a>
     </span>
</div>
<div class="title">新增角色</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/rbac/role" styleId="roleForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addRole">

<table class="formTable">
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>角色名称：</td>
		<td class="codeCol">
		<html:text property="role.role_name" styleId="role.role_name"></html:text>
		</td>
		<td class="nameCol">角色类型</td>
		<td class="codeCol">	<!-- <form:select property="role.client_type" styleId="role.client_type" onchange="changeClientType()">
			<html:option value="">请选择</html:option>
			<html:optionsCollection name="clientTypeOptions" label="label" value="value"/>
		</form:select> -->
		<select id="role.client_type" name="role.client_type"  >
	                  <c:forEach items="${clientTypeOptions }" var="row">
	                    	<option <c:if test="${row.value==roleForm.role.client_type}">selected='selected'</c:if> value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
	                  </c:forEach>
	        </select>
		</td>
	</tr>
	<tr>
		<td class="nameCol">角色描述：</td>
		<td class="codeCol" colspan="3">
			<html:textarea property="role.des" style="border:1px solid #eee" styleId="role.des" rows="4" cols="70"></html:textarea>
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
