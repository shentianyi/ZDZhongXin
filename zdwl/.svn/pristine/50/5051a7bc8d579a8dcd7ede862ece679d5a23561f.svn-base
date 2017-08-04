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
		<link rel="StyleSheet" href="<url:static/>/js/dtree/dtree.css" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<url:static/>/css/base.css"/>
		<link rel="stylesheet" type="text/css" href="<url:static/>/css/index_distrib.css"/>
		<script type="text/javascript" src="<url:static/>/js/dtree/dtree2.js"></script>
		<script language="javascript" type="text/javascript" src="<url:static/>/js/jquery-1.7.1.js"></script>
		<script language="javascript" type="text/javascript" src="<url:static/>/js/common2.js"></script>
		<script>
			window.top.menuFocus = (function() {
				function getCurBox() {
					return $('.mc_box.curbox');
				}
				
				function getCurItem() {
					return $('.mc_box .box_area a.cur_a');
				}
				
				function getItem(menuName) {
					return $('a[title="' + menuName + '"]');
				}
				
				function getBoxByItem(item) {
					return item.closest('.mc_box');
				}
				
				function setCurBox(box) {
					getCurBox().removeClass('curbox');
					box.addClass('curbox');
				}
				
				function setCurItem(item) {
					getCurItem().removeClass('cur_a');
					item.addClass('cur_a');
				}
				
				return function(menuName) {
				    var menuItem = getItem(menuName);
				    if (menuItem.length) {
				    	setCurBox(getBoxByItem(menuItem));
					    setCurItem(menuItem);
				    }
				}
			})();
		</script>
		</head>
	<body style="margin: 0px; background-color: #fff;padding-left: 0px;">
			<div class="main_menu" style="margin-left: 20px;">
				<div class="menu_top">
					<div class="mt_img">
						<div class="imgbg_big"><s class="png"></s></div>
						<div class="imgbg_small"><s class="png"></s></div>
						<img src="<url:static/>/images/index/img.png" /><i></i>
						<ul>
							<li><a href="#">用户信息</a></li>
							<li><a href="#">账户安全</a></li>
						</ul>
					</div>
					<div class="mt_con"><a class="png" href="#"></a></div>
					<div class="clear"></div>
				</div><!-- menu_top end -->
				<div class="menu_cont">
					<script type="text/javascript">
						d = new dTree('d');
						
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
			</div>
	</body>
</html>
