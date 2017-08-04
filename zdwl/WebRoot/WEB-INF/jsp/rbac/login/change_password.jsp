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
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1[1].2.6.js"></script>
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
function doLoad(){
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

function doSave(){
	var lastPassword = getElement("lastPassword");
	var password = getElement("password");
	var confirmPassword = getElement("confirmPassword");
	var passwordTest=/^(?![^a-zA-Z]+$)(?!\D+$).{8,16}$/;
	if(lastPassword.value==""){
		showMessage("请输入登录密码");
		eFocus(lastPassword);
		return false;
	} 
	if(password.value==""){
		showMessage("请输入新密码");
		eFocus(password);
		return false;
	} 
	if(!passwordTest.test(password.value)){
		showMessage("新密码必须包含字母和数字,且长度为8-16位！");
		return false;
	} 
	if(confirmPassword.value==""){
		showMessage("请输入确认新密码");
		eFocus(confirmPassword);
		return false;
	} 
	if(password.value!=confirmPassword.value){
		showMessage("新密码与确认新密码不一致");
		eFocus(password);
		return false;
	}
	document.forms[0].submit(); 
}
function doReset(){
	document.forms[0].reset();
}
$(function(){
	var msg ="<c:out value='${message}'/>";
	if(msg){
		alert(msg);
	}
});
</script>
</head>
<body onLoad="doLoad()">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">系统管理与配置</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">资源管理</a>
         	 &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">新增资源</a>
         </span>
</div>
<div class="title">新增资源</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
		<html:form action="/rbac/login" styleId="loginForm" method="post" onsubmit="return false" >
			<input type="hidden" name="method" id="method" value="changePassword"/>
			
			<table class="formTable">
				<tr> 
					<td class="nameCol">登录密码：</td>
					<td class="codeCol"><input type="password" name="lastPassword" id="lastPassword"></td>
				</tr>
				<tr> 
					<td class="nameCol">新密码：</td>
					<td class="codeCol"><input type="password" name="password" id="password"></td>
				</tr>
				<tr> 
					<td class="nameCol">确认新密码：</td>
					<td class="codeCol"><input type="password" name="confirmPassword" id="confirmPassword"></td>
				</tr>
				<tr class="formTableFoot">
					<td align="center" colspan="2">
						<button class="formButton" onClick="doSave()">保&nbsp;存</button>
						&nbsp;&nbsp;
						<button class="formButton" onClick="doReset()">重&nbsp;置</button>
					</td>
				</tr>
			</table>
			<br />
			<div class="message" id="message" style="display: none"></div>
		</html:form>

	</div>
</div>
</body>