<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>错误页面</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimun-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="no">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="application-name" content="telephone=no">

<link href="<url:static/>/css/mobilebase.css" rel="stylesheet" charset="utf-8">
<link href="<url:static/>/css/mobilemyaccount.css" rel="stylesheet" charset="utf-8">
<link href="<url:static/>/css/todo.css" rel="stylesheet" charset="utf-8">

<script src="<url:static/>/js/mobile/jquery-1.9.1.min.js"></script>
<script src="<url:static/>/js/mobile/base.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/jquery-1.7.1.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/distrib.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/common.js"></script>
<script type="text/javascript" >
function loginout(){
	location = "<url:context/>/mobile/rbac/login.do?method=logout";		
}
function doReturn() {
	history.back();
}
	
</script>
	</head>

<body>
<header>
	<div class="header header-main">
		<div class="header-line">
			<a class="logo" href="#"><img src="<url:static/>/images/mobile/logo.png" /></a>	
		</div>
	</div>
</header>
<div class="main main-top main-bottom">
	<div class="main-area">
		<dl class="dl-line">
			<dt>系统异常：</dt>
		</dl>
		<dl class="dl-line">
			<dt>系统提示：</dt>
			<dd>
				系统出现异常,请与管理员联系或重新登录尝试。
			</dd>
		</dl>
		<dl class="dl-line">
			<dd>
				<a href="javascript:doReturn();">返回</a>
			</dd>
		</dl>
		<dl class="dl-line">
			<dd>
				<a href="javascript:loginout();">重新登录</a>
			</dd>
		</dl>
	</div>
</div>
</body>
</html>
