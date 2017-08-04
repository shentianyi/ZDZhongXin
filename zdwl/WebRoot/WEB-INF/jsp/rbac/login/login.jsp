﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="/css/base.css" rel="stylesheet" type="text/css" />
<link href="/css/login.css" rel="stylesheet" type="text/css" />

<script src="/js/jquery-1.7.1.js" type="text/javascript" language="javascript"></script>
<script src="/js/common.js"></script>
<script src="/js/login.js" type="text/javascript"></script>
<title>系统登录</title>
<script>
if(window.parent!=window){
	window.parent.location = "<url:context/>/rbac/login.do?method=loginEntry";
}

function doLoad(){
	var msg ="<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>";
	if(msg!=null && msg!="null"){
		alert(msg);
	}
}

function login(){
	var evt = window.event || arguments.callee.caller.arguments[0];
	var charCode = (evt.which)? evt.which : evt.keyCode;
	if(charCode == 13 || charCode == 0 || charCode == 1){	
		if(document.getElementsByName("loginid")[0].value==""){
			alert("请输入用户名");
			document.getElementsByName("loginid")[0].focus();
			return false;
		}
		if(document.getElementsByName("password")[0].value==""){
			alert("请输入密码");
			document.getElementsByName("password")[0].focus();
			return false;
		}
		
		document.forms[0].submit();
	}
}

//执行表单重置操作
function reset(){
	document.forms[0].reset();
}
//关于我们
function about() {
	location = "<url:context/>/about.do?method=about";
}
//业务介绍
function business() {
	location = "<url:context/>/about.do?method=business";
}
//产品介绍
function product() {
	location = "<url:context/>/about.do?method=product";
}
//联系我们
function contact() {
	location = "<url:context/>/about.do?method=contact";
}
</script>
</head>
	<body onLoad="doLoad()">
		<html:form  action="/rbac/login">
			<input name="method" id="method" type="hidden" value="login">
			<div class="container-top">
            	<div class="content">
	                <div class="login-logo"></div>
                	<div class="login-title">中都物流汽车金融质押监管业务操作系统</div>
            	</div>
        	</div>
        	<div class="container-bottom">
	            <div class="content login-box-pdding">
                	<div class="rel login-box">
	                    <input type="text" placeholder="请输入用户名" id="loginid" name="loginid" class="abs login-username-input" />
                    	<input type="password" placeholder="请输入密码" id="password" name="password" class="abs login-password-input" onkeydown="login()" />
                    	<a href="javascript:;" name="" class="abs login-btn"  onClick="login()"></a>
                	</div>
            	</div>
        	</div>
		</html:form>
	</body>
</html>
