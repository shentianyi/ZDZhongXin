<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("agreementBackquery.distribname").value="";
	getElement("agreementBackquery.agreement_num").value="";
	getElement("agreementBackquery.agreement_date").value="";
	getElement("agreementBackquery.send_date").value="";
	getElement("agreementBackquery.isback").value=-1;
	getElement("agreementBackquery.back_date").value="";
}

</script>
</head>
<body onLoad="doLoad()">
<div class="title">协议邮寄台账</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
		<html:form action="/agreementLedger" styleId="agreementBackForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="agreementLedgerList"/>
			<!-- 查询条件 -->
			<table class="formTable" cellpadding="0" cellspacing="0">
				<tr>
					<td class="nameCol">经销商：</td>
					<td class="codeCol">
						<html:text property="agreementBackquery.distribname" styleId="agreementBackquery.distribname"/>
					</td>
					<td class="nameCol">协议编号：</td>
				  	<td class="codeCol"><html:text property="agreementBackquery.agreement_num" styleId="agreementBackquery.agreement_num"/></td>
					<td class="nameCol">协议邮寄时间：</td>
					<td class="codeCol">
						<form:calendar property="agreementBackquery.agreement_date" styleId="agreementBackquery.agreement_date" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="nameCol">邮寄时间：</td>
				  	<td class="codeCol">
				  		<form:calendar property="agreementBackquery.send_date" styleId="agreementBackquery.send_date" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
				  	</td>
				  	<td class="nameCol">是否回收</td>
					<td class="codeCol">
						<form:select property="agreementBackquery.isback" styleId="agreementBackquery.isback">
							<html:option value="-1">请选择</html:option>
							<html:option value="2">是</html:option>
							<html:option value="1">否</html:option>
						</form:select>
					</td>
					<td class="nameCol">收回时间</td>
					<td class="codeCol"><form:calendar property="agreementBackquery.back_date" styleId="agreementBackquery.back_date" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" /></td>
				</tr>
				<tr class="formTableFoot" >
					<td colspan="6" align="center" class="tdPadding">
						<button class="formButton" onClick="doQuery()">查&nbsp;询</button>&nbsp;
						<button class="formButton" onClick="doClear()">重&nbsp;置</button>
					</td>
				</tr>
			</table>
			
			<div class="dvScroll">
			<table  class="listTalbe" cellpadding="0" cellspacing="0">
				<thead>
					<tr class="title">
				       <td>序号</td>
				      <td><thumbpage:order cname="经销商" filedName="distribid"/></td>
				      <td><thumbpage:order cname="金融机构" filedName="financial_institution"/></td>
				      <td><thumbpage:order cname="品牌" filedName="financial_institution"/></td>
				      <td><thumbpage:order cname="省" filedName="financial_institution"/></td>
				      <td><thumbpage:order cname="市" filedName="financial_institution"/></td>
				      <td><thumbpage:order cname="金融机构联系人" filedName="financial_user"/></td>
				      <td><thumbpage:order cname="联系方式" filedName="financial_phone"/></td>
				      <td><thumbpage:order cname="协议编号" filedName="agreement_num"/></td>
				      <td><thumbpage:order cname="协议邮寄时间" filedName="agreement_date"/></td>
				      <td><thumbpage:order cname="邮寄地址" filedName="send_address"/></td>
				      <td><thumbpage:order cname="邮寄时间" filedName="send_date"/></td>
				      <td><thumbpage:order cname="是否回收" filedName="isback"/></td>
				      <td><thumbpage:order cname="收回时间" filedName="back_date"/></td>
				      <td><thumbpage:order cname="协议签署日期" filedName="financial_phone"/></td>
				      <td><thumbpage:order cname="协议到期日" filedName="financial_phone"/></td>
				    </tr>
				</thead>
				<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
					<logic:iterate name="list" id="row" indexId="index">
						<tr class="listTr_a">
							<td align="center"><c:out value="${index+1}"/></td>
							<td class="t-td"><c:out value="${row.dealerName }"/></td>
							<td class="t-td"><c:out value="${row.bankName }"/></td>
							<td class="t-td"><c:out value="${row.brandName }"/></td>
							<td class="t-td"><c:out value="${row.province }"/></td>
							<td class="t-td"><c:out value="${row.city }"/></td>
							<td class="t-td"><c:out value="${row.bankPerson }"/></td>
							<td class="t-td"><c:out value="${row.bankPersonPhone }"/></td>
							<td align="center"><c:out value="${row.agreement_num}" /></td>
							<td align="center"><select:timestamp timestamp="${row.agreement_date}" idtype="date"/></td>
							<td align="center"><c:out value="${row.send_address}"/></td>
							<td align="center"><select:timestamp timestamp="${row.send_date}" idtype="date"/></td>
							<td align="center">
								<c:if test="${row.isback == 1}">
									否
								</c:if>
								<c:if test="${row.isback == 2}">
									是
								</c:if>
							</td>
							<td align="center"><select:timestamp timestamp="${row.back_date}" idtype="date"/></td>
							<td align="center"><select:timestamp timestamp="${row.agreementsigntime}" idtype="date"/></td>
							<td align="center"><select:timestamp timestamp="${row.agreementexpiretime}" idtype="date"/></td>
						</tr>
					</logic:iterate>
				</tbody>  
			</table>
			</div>
			<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="agreementLedgerList" action="/agreementLedger.do?method=agreementLedgerList"/>
		</html:form>
	</div>
</div>
</body>
</html>
