<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
<%@page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@page import="com.zd.csms.rbac.util.LoginUserUtil" %>
<%
request.setAttribute("resourceList",UserSessionUtil.getUserSession(request).getResourceList());
%>
<c:set var="menu"><%=RbacConstants.RESOURCE_NODE_TYPE_MENU.getCode()%></c:set>
<c:set var="node"><%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>无标题文档</title>
		<link rel="StyleSheet" href="/css/leftmenu.css" type="text/css" />
		<link rel="stylesheet" type="text/css" href="/css/base.css"/>
		<script type="text/javascript" src="/js/dtree/dtree2.js"></script>
		<script language="javascript" type="text/javascript" src="/js/jquery-1.7.1.js"></script>
		<script language="javascript" type="text/javascript" src="/js/common2.js"></script>
		<script>
			$(function(){
				$("#menu_cont a").each(function(){
						$(this).css("white-space","nowrap");
						$(this).css("text-overflow","ellipsis");
						$(this).css("overflow","hidden");
				});
			});
			console.info(window.top);
		</script>
	</head>
	<body>
			<div class="main_menu">
				<div class="menu_top">
					<table style="width: 100%;">
						<tr>
							<td style="text-align: center;">
								<%=LoginUserUtil.loginUser(UserSessionUtil.getUserSession(request).getUser().getId()).getYiji()%>-
								<%=LoginUserUtil.loginUser(UserSessionUtil.getUserSession(request).getUser().getId()).getErji()%>-
								<%=LoginUserUtil.loginUser(UserSessionUtil.getUserSession(request).getUser().getId()).getSanji()%>
							</td>
						</tr>
						<tr>
							<%-- <td style="text-align: center;">
								<%=LoginUserUtil.loginUser(UserSessionUtil.getUserSession(request).getUser().getId()).getName()%>
							</td> --%>
						</tr>
					</table>
				</div>
				<div class="menu_cont" id="menu_cont">
					<div class="mc_box">
						<div class="box_line" style="text-align: center;"><a href="<url:context/>/system/welcome.jsp" target="chechewang_main">首页</a></div>
					</div>
					<script type="text/javascript">
						d = new dTree('d');
						
						<c:forEach items="${resourceList}" var="var">
							<c:if test="${var.node_type == menu}">
								d.add(<c:out value="${var.id}"/>,<c:out value="${var.parent_id}"/>,'<c:out value="${var.resource_name}"/>');
							</c:if>
							<c:if test="${var.node_type == node}">
								d.add(<c:out value="${var.id}"/>,<c:out value="${var.parent_id}"/>,'<c:out value="${var.resource_name}"/>','<url:context/><c:out value="${var.resource_url}"/>&fromMenuTree=true&resourceId=<c:out value="${var.id}"/>','<c:out value="${var.resource_name}"/>','chechewang_main');
							</c:if>
						</c:forEach>
						
						document.write(d);
					</script>
				</div>
			</div>
	</body>
</html>
