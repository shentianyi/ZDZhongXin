<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
<%@ page import="com.zd.csms.rbac.constants.ClientTypeConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车银通</title>

<link href="<url:static/>/css/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<url:static/>/js/jquery-1.7.1.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/distrib.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/common.js"></script>
<script>

</script>

</head>
<body>
<div class="title">待办事宜</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
		<html:form action="/dbsy" styleId="dbsyForm" method="post" onsubmit="return false">
		<input name="method" type="hidden" value="dbsyList"/>

		<table class="listTalbe" id="dbsylistds">
			<thead>
				<tr class="listHead">
					<td width="10%">序号</td>
					<td width="15%">工作类型</td>
					<td width="50%">工作描述</td>
					<td width="10%">待办数量</td>
					<td width="15%">紧急程度</td>
				</tr>
			</thead>
			<c:if test="${list!=null}">
			<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
			<logic:iterate name="list" id="row" indexId="index">
				<tr class="listTr_a"> 
					<td align="center"><c:out value="${index+1}"/></td>
					<td align="center"><select:workType workType="${row.workType}"/></td>
					<td align="center">&nbsp;<a href="<url:context/><c:out value="${row.url}"/>"><c:out value="${row.des}"/></a></td>
					<td align="center">&nbsp;<a href="<url:context/><c:out value="${row.url}"/>"><c:out value="${row.num}"/></a></td>
					<td align="center"><select:level level="${row.level}"/></td>
				</tr>
			</logic:iterate>
			</tbody>
			</c:if>
		</table>
		<br/>
		<div id="message" class="message" style="display:none"></div>
		</html:form>
	</div>
</div>
</body>
</html>