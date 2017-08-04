<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>

<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
<%@ page import="com.zd.csms.message.MessageUtil" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="/css/base.css"/>
<link rel="stylesheet" type="text/css" href="/css/header.css"/>
<script src="/js/jquery-1.7.1.js" type="text/javascript" language="javascript"></script>
<script>
function logout(){
	location="<url:context/>/rbac/login.do?method=logout";
}
$(function(){
 	setInterval(dorefresh,3000);	
	function dorefresh(){
		$.ajax({
			type:"POST",
			url:"/json/dorefresh.do?callback=?",
			dataType:"jsonp",
			success:function(data){
				if(data){
					$("#info").text(data.data[0]);	
					$("#warn").text(data.data[1]);	
				}
			}
		});
	}
});

</script>
<title>后台头部</title>
</head>
<body>
	<div class="header clearfix">
        <div class="fl logo">
            	<img class="logo-img" src="images/logo.png" />
            <div class="logo-title title-color">汽车金融质押监管业务操作系统</div>
        </div>
        <div class="fr right-content clearfix">
            <div class="fl icon-content title-color">
                <a href="/message.do?method=messagePageList" target="chechewang_main">信息提醒
                    <div class="rel fr icon-messages">
                        <div id="info" class="num">
							<%=MessageUtil.num(UserSessionUtil.getUserSession(request).getUser().getId(),1) %>
                        </div>
                    </div>
                </a>
            </div>
            <div class="fl icon-content title-color">
                <a href="/message.do?method=warningPageList" target="chechewang_main"> 预警提醒
                    <div class="rel fr icon-remind">
                        <div id="warn" class="num">
							<%=MessageUtil.num(UserSessionUtil.getUserSession(request).getUser().getId(),2) %>
                        </div>
                    </div>
                </a>
            </div>
            <div class="fl icon-content title-color title-margin">
                <a href="<url:context/>/rbac/login.do?method=changePasswordEntry" target="chechewang_main">修改密码</a>
            </div>
            <div class="fl icon-content title-color">
                <a href="javascript:logout();">注销</a>
            </div>

        </div>
    </div>
</body>
</html>
