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
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>

//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("draftForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("draftquery.distribid").value=-1;
	getElement("draftquery.draft_num").value="";
	getElement("draftquery.state").value=-1;
}
</script>
</head>
<body>

<div class="title">汇票</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/draft" styleId="draftForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="draftSuperviseList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">经销商：</td>
	  	<td class="codeCol">
	  		<form:select property="draftquery.distribid" styleId="draftquery.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
	  	</td>
	  	<td class="nameCol">汇票号码：</td>
	  	<td class="codeCol"><html:text property="draftquery.draft_num" styleId="draftquery.draft_num"/></td>
	  	<td class="nameCol">状态：</td>
	  	<td class="codeCol">
	  		<form:select property="draftquery.state" styleId="draftquery.state">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="isClearTicket" label="label" value="value" />
			</form:select>
	  	</td>
	</tr>
	<!-- 查询按钮 -->
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
	      <td><thumbpage:order cname="经销商" filedName="department"/></td>
	      <td><thumbpage:order cname="金融机构" filedName="department"/></td>
	      <td><thumbpage:order cname="质押协议号" filedName="department"/></td>
	      <td><thumbpage:order cname="保证金账号" filedName="name"/></td>
	      <td><thumbpage:order cname="汇票号码" filedName="quarters"/></td>
	      <td><thumbpage:order cname="开票日" filedName="phone"/></td>
	      <td><thumbpage:order cname="到期日" filedName="landline"/></td>
	      <td><thumbpage:order cname="开票金额(元)" filedName="email"/></td>
	      <td><thumbpage:order cname="已押车金额" filedName="fax"/></td>
	      <td><thumbpage:order cname="库存台数" filedName="qq"/></td>
	      <td><thumbpage:order cname="库存金额" filedName="duty"/></td>
	      <td><thumbpage:order cname="敞口金额" filedName="duty"/></td>
	      <td><thumbpage:order cname="回款金额" filedName="duty"/></td>
	      <td><thumbpage:order cname="未押车金额" filedName="duty"/></td>
	      <td><thumbpage:order cname="状态" filedName="duty"/></td>
	      <td><thumbpage:order cname="创建人" filedName="password"/></td>
		  <td><thumbpage:order cname="创建时间" filedName="password"/></td>
		  <td><thumbpage:order cname="修改人" filedName="password"/></td>
		  <td><thumbpage:order cname="修改时间" filedName="password"/></td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><select:draft draftid="${row.id}" idtype="jxs"/></td>
				<td align="center"><select:draft draftid="${row.id}" idtype="jrjg"/></td>
				<td align="center"><c:out value="${row.agreement}" /></td>
				<td align="center"><c:out value="${row.bail_num}" /></td>
				<td align="center"><c:out value="${row.draft_num}" /></td>
				<td align="center"><select:timestamp timestamp="${row.billing_date}" idtype="date"/></td>
				<td align="center"><select:timestamp timestamp="${row.due_date}" idtype="date"/></td>
				<td align="center"><c:out value="${row.billing_money}"/></td>
				<td align="center"><select:draft draftid="${row.id}" idtype="yycje"/></td>
				<td align="center"><select:draft draftid="${row.id}" idtype="kcts"/></td>
				<td align="center"><select:draft draftid="${row.id}" idtype="kcje"/></td>
				<td align="center"><select:draft draftid="${row.id}" idtype="ckje"/></td>
				<td align="center"><c:out value="${row.payment_money}"/></td>
				<td align="center"><select:draft draftid="${row.id}" idtype="wycje"/></td>
				<td align="center">
					<c:if test="${row.state == 1}">
						清票
					</c:if>
					<c:if test="${row.state == 2}">
						未清票
					</c:if>
				</td>
				<td align="center"><select:user userid="${row.createuserid}"/></td>
				<td align="center"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
				<td align="center"><select:user userid="${row.upduserid}"/></td>
				<td align="center"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="draftSuperviseList" action="/draft.do?method=draftSuperviseList"/>

<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>

</body>
</html>
