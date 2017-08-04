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
//进入修改页面
function goUpd(id){
	location = "<url:context/>/twoAddress.do?method=updTwoAddressEntry&twoAddress.id="+id;
}

//执行查询操作
function doQuery(){
		document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("dealerquery.dealerName").value="";
}
</script>
</head>
<body>

<div class="title">经销商列表</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/twoAddress" styleId="twoAddressForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="twoAddressList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">经销商：</td>
		<td class="codeCol">
			<form:select property="twoAddressquery.distribid" styleId="twoAddressquery.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
		</td>
	  	<td class="nameCol">二网名称：</td>
		<td class="codeCol"><html:text property="twoAddressquery.two_name" styleId="twoAddressquery.two_name"/></td>
		<td class="nameCol">二网联系人：</td>
	  	<td class="codeCol"><html:text property="twoAddressquery.two_username" styleId="twoAddressquery.two_username"/></td>
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
	      <td><thumbpage:order cname="经销商" filedName="distribid"/></td>
	      <td><thumbpage:order cname="二网名称" filedName="two_name"/></td>
	      <td><thumbpage:order cname="二网地址" filedName="two_address"/></td>
	      <td><thumbpage:order cname="二网联系人" filedName="two_username"/></td>
	      <td><thumbpage:order cname="联系方式" filedName="phone"/></td>
	      <td><thumbpage:order cname="距离(公里)" filedName="distance"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><select:dealerName dealerid="${row.distribid}"/></td>
				<td align="center"><c:out value="${row.two_name}" /></td>
				<td align="center"><c:out value="${row.two_address}" /></td>
				<td align="center"><c:out value="${row.two_username}" /></td>
				<td align="center"><c:out value="${row.phone}" /></td>
				<td align="center"><c:out value="${row.distance}" /></td>
				<td align="center" nowrap="true" class="right-border-none">
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="twoAddressList" action="/twoAddress.do?method=twoAddressList"/>
<table class="bottomTable">
	<tr>
		<td><button class="formButton" onClick="goAdd()">新&nbsp;增</button></td>
	</tr>
</table>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>

</body>
</html>
