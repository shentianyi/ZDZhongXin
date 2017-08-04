<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.zd.csms.rbac.constants.ClientTypeConstants"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>

<c:set value="${pageContext.request.contextPath}" var="frontPath" />
<%
String url = "/system/main.jsp";
if(UserSessionUtil.getUserSession(request)==null){
	url = "/rbac/login.do?method=loginEntry";
}
%>
<c:redirect url="<%=url%>" />
