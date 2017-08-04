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

<link href="<url:static/>/css/css.css" rel="stylesheet" type="text/css" />
<script src="<url:static/>/js/common.js"></script>
<script src="<url:static/>/js/thumbpage/thumbpage.js"></script>
<script src="<url:static/>/js/validate/validate_main.js"></script>
<script src="<url:static/>/js/validate/validate_base.js"></script>

<script>

//执行返回列表操作
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("resourceForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("resourceQuery.resourceName").value="";
	//清空资源状态复选框
	var elements = document.getElementsByName("resourceQuery.states");
	for(var i = 0; i < elements.length; i++){
		elements[i].checked = false;
	}
	//清空节点类型选择按钮
	var nodeTypes = document.getElementsByName("resourceQuery.nodeTypes");
	for(var i = 0; i < nodeTypes.length; i++){
		nodeTypes[i].checked = false;
	}
}

//执行关闭窗口操作
function doClose(){
	window.returnValue=null;
	window.close();
}
</script>
<base target="_self"><!-- 防止点查询按钮的时候再次弹出页面 -->
</head>
<body onLoad="doLoad()">

<c:set var="using"><%=StateConstants.USING.getCode()%></c:set>
<c:set var="unused"><%=StateConstants.UNUSED.getCode()%></c:set>

<c:set var="menu"><%=RbacConstants.RESOURCE_NODE_TYPE_MENU.getCode()%></c:set>
<c:set var="node"><%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%></c:set>

<html:form action="/rbac/resource" styleId="resourceForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="resourceListWithRoleUser"/>
<input type="hidden" name="roleId" id="roleId" value="<c:out value="${roleId}"/>"/>

<!-- 查询条件 -->
<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">权限下分配功能</td>
	</tr>
	<tr>
		<td class="nameCol">权限名称：</td>
		<td class="codeCol">&nbsp;<c:out value="${role.roleName}"/></td>
		<td class="nameCol">资源名称：</td>
		<td class="codeCol"><html:text property="resourceQuery.resourceName" styleId="resourceQuery.resourceName"/></td>
	</tr>
	<tr>
		<td class="nameCol">资源状态：</td>
		<td class="codeCol">
			<form:checkboxs property="resourceQuery.states" collection="stateOptions" styleId="resourceQuery.states"/>
		</td>
		<td class="nameCol">资源类型：</td>
	  	<td class="codeCol">
	  		<form:checkboxs property="resourceQuery.nodeTypes" collection="nodeTypeOptions" styleId="resourceQuery.nodeTypes"/>
	  	</td>
	</tr>
	<!-- 查询按钮 -->
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="doQuery()">查&nbsp;询</button>
			&nbsp;
			<button class="formButton" onClick="doClear()">重&nbsp;置</button>
		</td>
	</tr>
</table>
<br/>

<table class="listTalbe">
<thead>
	 <tr class="listHead">
      <td width="5%">序号</td>
      <td><thumbpage:order cname="名称" filedName="resourceName"/></td>
      <td><thumbpage:order cname="节点类型" filedName="nodeType"/></td>
      <td><thumbpage:order cname="级别" filedName="resourceLevel"/></td>
      <td><thumbpage:order cname="顺序" filedName="resourceIndex"/></td>
      <td><thumbpage:order cname="状态" filedName="state"/></td>
    </tr>
</thead>
<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
	<logic:iterate name="list" id="row" indexId="index">
		 <tr class="listTr_a">
			<td align="center"><c:out value="${index+1}"/></td>
			<td>&nbsp;<c:out value="${row.resourceName}"/></td>
			<td align="center">
				<c:if test="${row.nodeType==menu}">目录</c:if>
				<c:if test="${row.nodeType==node}">节点</c:if>
			</td>
			<td align="center">&nbsp;<c:out value="${row.resourceLevel}" /></td>
			<td align="center">&nbsp;<c:out value="${row.resourceIndex}" />&nbsp;</td>
			<td align="center" nowrap="true">
				<select:stateName state="${row.state}"/>
			</td>
		</tr>
	</logic:iterate>
</tbody>  
</table>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="resourceListWithRoleUser" action="/rbac/resource.do?method=resourceListWithRoleUser"/>
<table class="bottomTable">
	<tr>
		<td><button class="formButton" onClick="doClose()">关&nbsp;闭</button></td>
	</tr>
</table>
<div id="message" class="message" style="display:none"></div>
</html:form>

</body>
</html>
