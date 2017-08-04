<%@ page contentType="text/html; charset=UTF-8"%>
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
		if(getElement("manager").value==""){
			alert("客户经理名称不能为空");
			return false;
		}
		if(getElement("managerPhone").value==""){
			alert("客户经理电话不能为空");
			return false;
		}
		
		document.forms[0].submit();
	}
	

	//执行返回列表操作
	function doReturn(id) {
		location = "<url:context/>/bank/bankManager.do?method=findList&query.bankId="+id;
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}

</script>
</head>
<body onLoad="doLoad()">

	<div class="title">银行客户经理管理</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/bank/bankManager.do" styleId="bankManagerForm"
				method="post" onsubmit="return false" >
				<input type="hidden" name="method" value="add"/>
				<html:hidden property="bankManager.bankId"/>
				<input type="hidden" name="query.bankId" value="<c:out value='${bankManagerForm.bankManager.bankId }'/>"/> 
				<table class="formTable">
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>客户经理名称：</td>
						<td class="codeCol">
							<html:text property="bankManager.manager" styleId="manager"></html:text>
						</td>
						<td class="nameCol">客户经理电话：</td>
						<td class="codeCol">
							<html:text property="bankManager.managerPhone" styleId="managerPhone"/>
						</td>
					</tr>
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
							<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
							<button class="formButton" onClick="doReturn('<c:out value='${bankManagerForm.bankManager.bankId }'/>')">返&nbsp;回</button>
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
