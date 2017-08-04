 <%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="file.tld" prefix="file"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script>

//执行保存操作
function doSave(){
	document.forms[0].submit();
}

//进入列表页面
function goList() {
	location = "/ledger/dealer.do?method=findBusinessList";
}

</script>
</head>

<body>
<div class="title">经销商业务参数设置</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
		<html:form action="/distribset" styleId="distribsetForm" method="post" onsubmit="return false">
			<input type="hidden" name="method" id="method" value="addOrUpExtendDistrib">
			<html:hidden property="extendDistribset.id" styleId="extendDistribset.id" />
			<table class="formTable">
				<tr>
					<td class="nameCol" >汇票下发方式:</td>
					<td class="codeCol" >
						<html:text property="extendDistribset.draft_way" styleId="draft_way" />
					</td>
					<td class="nameCol">押车方式:</td>
					<td class="codeCol" >
						<html:text property="extendDistribset.guard_way" styleId="guard_way" />
					</td>
				</tr>
				<tr>
					<td class="nameCol" >合格证送达方式:</td>
					<td class="codeCol" >
						<html:text property="extendDistribset.certificate_delivery" styleId="certificate_delivery" />
					</td>
					<td class="nameCol">实际监管模式:</td>
					<td class="codeCol" >
						<html:text property="extendDistribset.actual_supervision" styleId="actual_supervision" />
					</td>
				</tr>
				<tr>
					<td class="nameCol" >钥匙监管:</td>
					<td class="codeCol" >
						<html:text property="extendDistribset.key_supervise" styleId="key_supervise" />
					</td>
					<td class="nameCol">二网:</td>
					<td class="codeCol" >
						<html:text property="extendDistribset.website" styleId="website" />
					</td>
				</tr>
				
				<tr>
					<td class="nameCol" >二库:</td>
					<td class="codeCol" >
						<html:text property="extendDistribset.old_car" styleId="old_car" />
					</td>
					<td class="nameCol">特殊操作:</td>
					<td class="codeCol" >
						<%-- <html:text property="extendDistribset.special_oper" styleId="special_oper" size="40" /> --%>
						<html:textarea property="extendDistribset.special_oper" styleId="special_oper" 
						 style="width: 278px;" rows="1" cols="20"  styleClass="textarea" onkeyup="if(value.length>40) value=value.substr(0,40)"></html:textarea>
					</td>
				</tr>
				
				<tr>
				    <td class="nameCol">授信额度：</td>
                    <td class="codeCol">
                        <html:text property="dealer.credit" styleId="dealer.credit" ></html:text>
                    </td>
                    <td class="nameCol">设备提供方：</td>
                    <td class="codeCol">
                        <html:text property="dealer.equipmentProvide" styleId="dealer.equipmentProvide"></html:text>
                    </td>
				</tr>
				
				<tr class="formTableFoot">
					<td colspan="4" align="center">
						<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
						<button class="formButton" onClick="goList()">返&nbsp;回</button>
					</td>
				</tr>
			</table>
		</html:form>
	</div>
</div>
</body>
</html>

