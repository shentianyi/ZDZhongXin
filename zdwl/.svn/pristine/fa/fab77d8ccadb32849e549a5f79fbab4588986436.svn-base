<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.rbac.util.UserSession"%>
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
    <link type="text/css" rel="stylesheet" href="/css/userman.css" />
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都物流 - 账户管理</title>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<!-- <link type="text/css" rel="stylesheet" href="/css/userman.css" /> -->

<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/common.js"></script>
<style>
body{ font-size:12px;}
*li,p,ul{ padding:0; margin:0;}
.table_list{ width:920px; margin:0 auto;}
.table_list h2{ height:52px; line-height:52px; border-bottom:solid 1px #c7c7c7; text-align:center;}
.table_list01{ margin-top:20px;}
.fl{ float:left;}
.fr{ float:right;}
.clear{ clear:both;}
.title{ height:38px; line-height:38px; text-align:center;background-color:#f1f1f1;}
.list-cont{ width:348px;border:solid 1px #c7c7c7;}
li{ list-style:none;}
.list-cont ul{ padding:0; margin:0; overflow:hidden;}
.list-cont li { width:100%; height:42px; line-height:42px;border-top:solid 1px #c7c7c7;}
.list-cont li label{ width:100px; display:inline-block; text-align:center;}
.table_list02{ margin-top:35px;}
</style>
<script>
	$(function() {
		showMessage("<c:out value='${message}'/>");
		restrict();
	});
	function doSave() {
		document.forms[0].submit();
	}
	function doReset() {
		document.forms[0].reset();
	}
	function restrict(){
	   var crole = $("#crole").val();
	   if(crole ==  30){
	       //超级角色
	       $('input[type="text"]').attr("readonly","readonly");
	       $("button").hide();
	   }
	}
</script>
<style type="text/css">
.parent {
	width: 100%;
}

.children {
	width: 42%;
	height: 200px;
	padding: 50px;
	float: right;
}

.childrentable {
	width: 100%;
	background-color: lightblue;
}

.childrentable td {
	border: 1px solid black;
}
</style>
</head>
<body class="h-100 userman" style="width:100%;">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
	<div class="title" style="width:100%;"><h2>薪酬参数设置</h2></div>
	<html:form action="/salary" styleId="salaryForm" method="post"
		onsubmit="return false">
		<input name="method" type="hidden" value="updateSalary" />
		<html:hidden property="basepay.id" styleId="id" />
		<div  class="table_list">
        <div class="table_list01">
        	<div class="fl list-cont">
            	<div class="title">基本工资（固定）</div>
                <ul>
                	<li>
                		<label>转正特殊</label>
                		<input type="text" class="easyui-numberbox" name="basepay.zhuanzheng_teshu" value="<c:out value='${basepay.zhuanzheng_teshu }'/>" data-options="min:0,precision:2"/>
					</li>
                    <li>
	                    <label>转正一类</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.zhuanzheng_yilei" value="<c:out value='${basepay.zhuanzheng_yilei }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>转正二类</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.zhuanzheng_erlei" value="<c:out value='${basepay.zhuanzheng_erlei }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>试用期特殊</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.shiyong_teshu" value="<c:out value='${basepay.shiyong_teshu }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>试用期一类</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.shiyong_yilei" value="<c:out value='${basepay.shiyong_yilei }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>试用期二类</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.shiyong_erlei" value="<c:out value='${basepay.shiyong_erlei }'/>" data-options="min:0,precision:2"/>
                    </li>
                </ul>
            </div>
            <div class="fr list-cont">
            	<div class="title">饭补</div>
                <ul>
                	<li>
	                	<label>特殊属地</label>
	                	<input type="text" class="easyui-numberbox" name="basepay.fanbu_teshu_shudi" value="<c:out value='${basepay.fanbu_teshu_shudi }'/>" data-options="min:0,precision:2"/>
                	</li>
                    <li>
	                    <label>特殊外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.fanbu_teshu_waipai" value="<c:out value='${basepay.fanbu_teshu_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>一类属地</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.fanbu_yilei_shudi" value="<c:out value='${basepay.fanbu_yilei_shudi }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>一类外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.fanbu_yilei_waipai" value="<c:out value='${basepay.fanbu_yilei_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>二类属地</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.fanbu_erlei_shudi" value="<c:out value='${basepay.fanbu_erlei_shudi }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>二类外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.fanbu_erlei_waipai" value="<c:out value='${basepay.fanbu_erlei_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
        
        <div class="table_list02">
        	<div class="fl list-cont">
            	<div class="title">交通补助</div>
                <ul>
                	<li>
	                	<label>特殊属地</label>
	                	<input type="text" class="easyui-numberbox" name="basepay.jiaotong_teshu_shudi" value="<c:out value='${basepay.jiaotong_teshu_shudi }'/>" data-options="min:0,precision:2"/>
                	</li>
                    <li>
	                    <label>特殊外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.jiaotong_teshu_waipai" value="<c:out value='${basepay.jiaotong_teshu_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>一类属地</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.jiaotong_yilei_shudi" value="<c:out value='${basepay.jiaotong_yilei_shudi }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>一类外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.jiaotong_yilei_waipai" value="<c:out value='${basepay.jiaotong_yilei_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>二类属地</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.jiaotong_erlei_shudi" value="<c:out value='${basepay.jiaotong_erlei_shudi }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>二类外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.jiaotong_erlei_waipai" value="<c:out value='${basepay.jiaotong_erlei_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                </ul>
            </div>
            <div class="fr list-cont">
            	<div class="title">话补</div>
                <ul>
                	<li>
	                	<label>特殊属地</label>
	                	<input type="text" class="easyui-numberbox" name="basepay.huabu_teshu_shudi" value="<c:out value='${basepay.huabu_teshu_shudi }'/>" data-options="min:0,precision:2"/>
                	</li>
                    <li>
	                    <label>特殊外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.huabu_teshu_waipai" value="<c:out value='${basepay.huabu_teshu_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>一类属地</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.huabu_yilei_shudi" value="<c:out value='${basepay.huabu_yilei_shudi }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>一类外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.huabu_yilei_waipai" value="<c:out value='${basepay.huabu_yilei_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>二类属地</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.huabu_erlei_shudi" value="<c:out value='${basepay.huabu_erlei_shudi }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>二类外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.huabu_erlei_waipai" value="<c:out value='${basepay.huabu_erlei_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
         <div class="table_list02">
        	<div class="fl list-cont">
            	<div class="title">房补</div>
                <ul>
                	<li>
	                	<label>特殊属地</label>
	                	<input type="text" class="easyui-numberbox" name="basepay.fangbu_teshu_shudi" value="<c:out value='${basepay.fangbu_teshu_shudi }'/>" data-options="min:0,precision:2"/>
                	</li>
                    <li>
	                    <label>特殊外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.fangbu_teshu_waipai" value="<c:out value='${basepay.fangbu_teshu_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>一类属地</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.fangbu_yilei_shudi" value="<c:out value='${basepay.fangbu_yilei_shudi }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>一类外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.fangbu_yilei_waipai" value="<c:out value='${basepay.fangbu_yilei_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>二类属地</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.fangbu_erlei_shudi" value="<c:out value='${basepay.fangbu_erlei_shudi }'/>" data-options="min:0,precision:2"/>
                    </li>
                    <li>
	                    <label>二类外派</label>
	                    <input type="text" class="easyui-numberbox" name="basepay.fangbu_erlei_waipai" value="<c:out value='${basepay.fangbu_erlei_waipai }'/>" data-options="min:0,precision:2"/>
                    </li>
                </ul>
            </div>
            <div class="clear"></div>
            <div class="formTableFoot">
				<div colspan="4" align="center">
					<button class="formButton" onClick="doSave()">保&nbsp;存</button>
				</div>
			</div>
        </div>
    </div>
	</html:form>
</div>
</body>
</html>
