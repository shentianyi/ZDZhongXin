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
//执行返回列表操作
function doReturn(){
	location = "<url:context/>/duedate.do?method=findList";
}
function two(){
	location = "<url:context/>/twodate.do?method=twoDownLoad";
}
</script>
</head>
<body>

<div class="title">二网日查库</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/twodate" styleId="duedateForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="twoList"/>
<div class="dvScroll">
<table  class="listTalbe" cellpadding="0" cellspacing="0">
	<thead>
		<tr class="title">
	      <td>序号</td>
	      <td><thumbpage:order cname="经销商" filedName="certificate_date"/></td>
	      <td><thumbpage:order cname="金融机构" filedName="certificate_date"/></td>
	      <td><thumbpage:order cname="型号" filedName="certificate_date"/></td>
	      <td><thumbpage:order cname="车架号" filedName="certificate_num"/></td>
	      <td><thumbpage:order cname="二网地址" filedName="certificate_num"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><select:dealer sid="${row.id}" idtype="jxs" dn="${row.draft_num}"/></td>
				<td align="center"><select:dealer sid="${row.id}" idtype="jrjg" dn="${row.draft_num}"/></td>
				<td align="center"><c:out value="${row.car_model}" /></td>
				<td align="center"><c:out value="${row.vin}" /></td>
				<td align="center"><c:out value="${row.two_name}" /></td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<table class="bottomTable">
	<tr>
		<td>
			<button class="formButton" onClick="two()">导出二网日盘点表</button>&nbsp;
			<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>
</table>
</html:form>

	</div>
</div>

</body>
</html>
