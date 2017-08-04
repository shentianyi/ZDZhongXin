<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
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
	//根据资源类型切换显示内容
	doCheck("<c:out value="${resourceForm.resource.node_type}"/>");
}

//执行保存操作
function doSave(){
	var value = document.getElementById("resource.resource_name").value;
	if(value == ""){
		alert("请填写资源名称");
		return false;
	}
	/* var value = document.getElementById("resource.resource_shortname").value;
	if(value == ""){
		alert("请填写资源简称");
		return false;
	} */
	var value=""; 
	var arr = document.getElementsByName("resource.node_type");
	for(var i=0;i<arr.length;i++){
	        if(arr[i].checked){    
	          value=arr[i].value;
	        }
	    }
	if(value == ""){
		alert("请选择资源类型");
		return false;
	}else if(value==<%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%>){
		var value = document.getElementById("resource.resource_url").value;
		if(value == ""){
			alert("请填写资源地址");
			return false;
		}
	}
	/* var value = document.getElementById("resource.resource_index").value;
	if(value == ""){
		alert("请填写显示顺序");
		return false;
	}
	var value = document.getElementById("resource.des").value;
	if(value == ""){
		alert("请填写资源描述");
		return false;
	} */
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("resourceForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/rbac/resource.do?method=resourceList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}

//根据资源类型切换显示内容
function doCheck(nodeType){
	//控制只有节点类型资源才显示资源地址 
	var url = getElement("resource.resource_url");
	var urlRow = getElement("resource_url");
	if(nodeType==<%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%>){
		urlRow.style.display="";
		url.disabled = false;
	} else{
		urlRow.style.display="none";
		url.disabled = true;
	}
}
</script>
</head>
<body  onLoad="doLoad()">
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
	
<html:form action="/rbac/resource" styleId="resourceForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="addResource"/>
<html:hidden property="resource.parent_id" styleId="resource.parent_id"/>
<html:hidden property="resource.resource_level" styleId="resource.resource_level"/>

<table class="formTable">
	<tr>
		<td class="nameCol">上级目录名称：</td>
		<td class="codeCol">&nbsp;<c:out value="${parent.resource_name}"/></td>
		<td class="nameCol">级别：</td>
		<td class="codeCol">&nbsp;<c:out value="${resourceForm.resource.resource_level}"/></td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>资源名称：</td>
		<td class="codeCol"><html:text property="resource.resource_name" styleId="resource.resource_name"/></td>
		<td class="nameCol">资源简称：</td>
	  	<td class="codeCol"><html:text property="resource.resource_shortname" styleId="resource.resource_shortname"/></td>
	</tr>
	<tr>
		<td class="nameCol"><font color="#FF0000">*</font>资源类型：</td>
		<td class="codeCol">
			<form:radios property="resource.node_type" styleId="resource.node_type" collection="nodeTypeOptions" onclick="doCheck(this.value)"/>
		</td>
		<td class="nameCol">显示顺序：</td>
		<td class="codeCol"><html:text property="resource.resource_index" styleId="resource.resource_index"/></td>
	</tr>
	<tr style="display:none" id="resource_url">
		<td class="nameCol"><font color="#FF0000">*</font>资源地址：</td>
		<td class="codeCol" colspan="3"><html:text style="width:650px" property="resource.resource_url" styleId="resource.resource_url" size="98" disabled="true"/></td>
	</tr>
	<tr >
		<td class="nameCol">资源描述：</td>
		<td class="codeCol" colspan="3">
			<html:textarea property="resource.des" style="border:1px solid #eee" styleId="resource.des" rows="3" cols="70"></html:textarea>
		</td>
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
