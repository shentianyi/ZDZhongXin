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
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
</script>
</head>
<body>

<div class="title">最后一笔业务办理</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/message/LastBusiness" styleId="messageForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="lastBusiness"/>
<div class="dvScroll">
<table  class="listTalbe" cellpadding="0" cellspacing="0">
	<thead>
		<tr class="title">
	      <td>序号</td>
	      <td><thumbpage:order cname="经销商" filedName="msgtype"/></td>
	      <td><thumbpage:order cname="金融机构" filedName="name"/></td>
	      <td><thumbpage:order cname="品牌" filedName="name"/></td>
	      <td><thumbpage:order cname="车架号" filedName="name"/></td>
	      <td><thumbpage:order cname="金额" filedName="name"/></td>
	      <td><thumbpage:order cname="释放时间" filedName="name"/></td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><select:dealer dn="${row.draft_num}" idtype="jxs"/></td>
				<td align="center"><select:dealer dn="${row.draft_num}" idtype="jrjg"/></td>
				<td align="center"><select:dealer dn="${row.draft_num}" idtype="brand"/></td>
				<td align="center"><c:out value="${row.vin}"/></td>
				<td align="center"><c:out value="${row.money}"/></td>
				<td align="center"><select:timestamp timestamp="${row.outtime}" idtype="date"/></td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
</html:form>

	</div>
</div>

</body>
</html>
