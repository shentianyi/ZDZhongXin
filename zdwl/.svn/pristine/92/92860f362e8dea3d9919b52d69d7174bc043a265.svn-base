<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script>
	//执行保存操作
	function doSave() {
		var value = document.getElementById("brandGroup.name").value;
		if (value == "") {
			alert("请填写名称");
			return false;
		}

		document.forms[0].submit();
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/rbac/brandGroup.do?method=groupList";
	}
</script>
</head>
<body>
	<div class="title">新增/修改品牌集团</div>
	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/rbac/brandGroup" styleId="brandGroupForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="addAndUpdGroup" />
				<html:hidden property="brandGroup.id" styleId="brandGroup.id" />
				<table class="formTable">
					<tr>
						<td class="nameCol">名称：</td>
						<td class="codeCol"><html:text property="brandGroup.name"
								styleId="brandGroup.name" /></td>
					</tr>

					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
						</td>
					</tr>
				</table>
				<br />
			</html:form>
		</div>
	</div>
</body>
</html>
