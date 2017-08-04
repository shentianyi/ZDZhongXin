<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404错误页面</title>
<link href="<url:static/>/css/css.css" rel="stylesheet" type="text/css" />
<script src="<url:static/>/js/common.js"></script>
</head>

<body>

<table class="formTable">
	<tr class="formTitle" align="left">
		<td colspan="2">系统异常</td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF" width="15%">系统提示：</td>
		<td bgcolor="#FFFFFF" width="85%">系统出现异常,请与管理员联系或重新登录尝试。</td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">详细信息：</td>
		<td bgcolor="#FFFFFF">您所访问的路径不存在，HTTP状态代码  <%=pageContext.getErrorData().getStatusCode()%>。</td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="window.history.back();">返&nbsp;&nbsp;&nbsp;&nbsp;回</button>
			&nbsp;
			<button class="formButton" onClick="logout();">重新登录</button>
		</td>
	</tr>
</table>
</body>
</html>
