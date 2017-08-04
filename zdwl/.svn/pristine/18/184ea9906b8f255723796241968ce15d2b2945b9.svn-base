<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(!getElement("region.name").value){
		alert("地区名称不能为空");
		return false;
	}
	
	document.forms[0].submit();

}

//执行返回列表操作
function doReturn(){
	var parentId=document.getElementById("parentId").value;
	var parentLevel=document.getElementById("parentLevel").value;
	location = "<url:context/>/region/region.do?method=regionPageList&query.parentId="+parentId+"&query.regionLevel="+parentLevel;
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

//根据资源类型切换显示内容
<%-- function doCheck(nodeType){
	//控制只有节点类型资源才显示资源地址 
	var url = getElement("region.resourceUrl");
	var urlRow = getElement("resourceUrl");
	if(nodeType==<%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%>){
		urlRow.style.display="";
		url.disabled = false;
	} else{
		urlRow.style.display="none";
		url.disabled = true;
	}
} --%>
</script>
</head>
<body  onLoad="doLoad()">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">系统管理与配置</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">地区管理</a>
         	&gt;
            <a class="crumbs-link" style="color:#929291;" href="#">新增地区</a>
         </span>
</div>
<br/>

<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/region/region" styleId="regionForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addRegion"/>
<input type="text" hidden="hidden" id="parentId" name="region.parentId" value="<c:out value='${parentId}'/>" />
<input type="text" hidden="hidden" id="parentLevel"  value="<c:out value='${parentLevel}'/>" />
<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">新增地区</td>
	</tr>
	<tr>
		<td class="nameCol">上级地区名称：</td>
		<td class="codeCol">&nbsp;<c:out value="${parent.name}"/></td>
	</tr>
	<tr>
		<td class="nameCol">上级地区级别：</td>
		<td class="codeCol">&nbsp;<c:out value="${parent.regionLevel}"/></td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>地区名称：</td>
		<td class="codeCol"><html:text property="region.name" styleId="region.name"/></td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
			<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
			<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>
</table>
<br/>
<div class="message" id="message" style="display:none"></div>
</html:form>
	
	</div>
</div>
</body>
</html>
