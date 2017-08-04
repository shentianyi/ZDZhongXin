<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
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
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/expenseChange.js"></script>t>
<script>
	$(function(){
		init();
	});
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
<body>

	<div class="title">监管费用变更流转单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/expenseChange.do" styleId="ecForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="update" />
				<html:hidden property="ec.id"/>

				<table class="formTable">
					<tr class="formTitle">
						<td colspan="4">市   场   部</td>
					</tr>
					<tr>
						<td class="nameCol2">经销商：</td>
						<td class="codeCol2">
							<select style="width:50%" id="jxs" name="ec.dealerId" >
								<c:forEach items="${dealers }" var="row">
									<option <c:if test="${ecForm.ec.dealerId == row.value }">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="nameCol2">品牌：</td>
						<td class="codeCol2"><input type="text" id="brand" readonly="readonly"/></td>
						<td class="nameCol2">金融机构：</td>
						<td class="codeCol2">
							<input type="text"id="bankName" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol2">原监管费（元/年）：</td>
						<td class="codeCol2">
							<input type="text"id="superviseMoney" readonly="readonly"/>
						</td>
						<td class="nameCol2">变更后监管费（元/年）：</td>
						<td class="codeCol2">
							<%-- <html:text property="ec.changemoney" styleId="changemoney"></html:text> --%>
							<input type="text" name="ec.changemoney" id="changemoney" 
								class="easyui-numberbox"
								data-options="min:0,precision:2,value:<c:out value='${ecForm.ec.changemoney }'/>" ></input>
						</td>
					</tr>
					<tr>
						<td class="nameCol2"><font color="#FF0000">*</font>进店时间：</td>
						<td class="codeCol2"><input type="text" id="inStoreDate" readonly="readonly"/></td>
						<td class="nameCol2"><font color="#FF0000">*</font>费用变更日期：</td>
						<td class="codeCol2">
							<form:calendar
								property="ec.changeDate" styleId="changeDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								 />
						</td>
					</tr>
					
					<tr>
						<td class="nameCol2">制单时间：</td>
						<td class="codeCol2">
							<form:calendar
								property="ec.createDate" styleId="createDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<td class="nameCol2">备注：</td>
						<td class="codeCol2" colspan="3"><html:textarea property="ec.remark"></html:textarea></td>
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
