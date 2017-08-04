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
		<link rel="StyleSheet" href="<url:static/>//js/dtree/dtree.css" type="text/css" />
		<script type="text/javascript" src="<url:static/>//js/dtree/dtree.js"></script>
		
		<style type="text/css">
		body {
			SCROLLBAR-FACE-COLOR: #FDC04D;
			SCROLLBAR-HIGHLIGHT-COLOR: #FFFFFF;
			SCROLLBAR-SHADOW-COLOR: #FFFFFF;
			SCROLLBAR-3DLIGHT-COLOR: #FFFFFF;
			SCROLLBAR-ARROW-COLOR: #FFFFFF;
			SCROLLBAR-TRACK-COLOR: #FFFFFF;
			SCROLLBAR-DARKSHADOW-COLOR: #FFFFFF;
		}
		</style>
	</head>
	<body style="margin: 0px; background-color: #fff;">
		<div class="dtree">
			<p>
				<div onClick="javascript:d.openAll();this.style.display='none';closeNode.style.display='inline';" id="openNode" style="cursor: hand">
					<img src="<url:static/>/js/dtree/images/folder.gif" border="0" alt="全部展开">
					&nbsp;全部展开
				</div>
				<div onClick="javascript:d.closeAll();this.style.display='none';openNode.style.display='inline';" id="closeNode" style="display: none; cursor: hand">
					<img src="<url:static/>/js/dtree/images/folderopen.gif" border="0" alt="全部关闭">
					&nbsp;全部关闭
				</div>
				<script type="text/javascript">
					d = new dTree('d');
					d.add(0,-1,'功能清单',null,'',null);

					<c:forEach items="${resourceList}" var="var">
					<c:if test="${var.nodeType == menu}">
					d.add(<c:out value="${var.id}"/>,<c:out value="${var.parentId}"/>,'<c:out value="${var.resourceName}"/>');
					</c:if>
					<c:if test="${var.nodeType == node}">
					d.add(<c:out value="${var.id}"/>,<c:out value="${var.parentId}"/>,'<c:out value="${var.resourceName}"/>','<url:context/><c:out value="${var.resourceUrl}"/>&fromMenuTree=true&resourceId=<c:out value="${var.id}"/>','<c:out value="${var.resourceName}"/>','chechewang_main');
					</c:if>
					</c:forEach>
					
					document.write(d);
				</script>
		</div>
	</body>
</html>
