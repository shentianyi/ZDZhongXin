﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>


<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}

	//执行保存操作
	function doSave() {
		//对表单内容进行验证，包括对输入类型等限制方式
		if (validateMain("bankForm")) {
			//为时间类型输入项补齐时间戳
			setTimeSuffix();
			//提交表单
			document.forms[0].submit();
		}
	}
	

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/bank.do?method=bankList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}

</script>
</head>
<body onLoad="doLoad()">

	<div class="title">导入测试</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/test/importFile" styleId="bankForm"
				method="post" onsubmit="return false" enctype="multipart/form-data">
				<input type="hidden" name="method" value="importExcel"/>
				<table class="formTable">

					<tr class="formTitle">
						<td colspan="4">导入测试</td>
					</tr>
					<tr>
						<td class="nameCol">上传：</td>
						<td class="codeCol" colspan="3"><html:file property="myFile"></html:file></td>
					</tr>
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
							<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
						</td>
					</tr>
				</table>
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>

		</div>
	</div>
</body>
</html>
