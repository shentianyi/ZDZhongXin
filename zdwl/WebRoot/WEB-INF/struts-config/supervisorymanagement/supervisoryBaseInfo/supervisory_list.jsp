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
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//进入新增页面
function goAdd(){
	window.location="<url:context/>/region.do?method=addRegionEntity";
}

//进入修改页面
function goUpd(id){
	window.location="<url:context/>/region.do?method=updRegionEntity&region.id="+id;
}

//进入新增 下级资源页面
function goAddChild(id){
	location = "<url:context/>/region.do?method=addRegionEntity&region.parentId="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("确认删除？")){
		window.location="<url:context/>/region.do?method=delRegion&region.id="+id;
	}
}

//执行启用操作
function doUsing(id){
	if(confirm("确认启用？")){
		location="<url:context/>/region.do?method=updRegionState&region.id="+id+"&region.status=<%=StateConstants.USING.getCode()%>";
	}
}

//执行停用操作
function doUnused(id){
	if(confirm("确认停用？")){
		location="<url:context/>/region.do?method=updRegionState&region.id="+id+"&region.status=<%=StateConstants.UNUSED.getCode()%>";
	}
}
//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("regionForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行表单清空操作
function doClear(){
	//清空地区名称输入框
	getElement("query.name").value="";
	getElement("query.parentName").value="";
	//清空状态复选框
	var elements = document.getElementsByName("query.status");
	for(var i = 0; i < elements.length; i++){
		elements[i].checked = false;
	}
}
</script>

</head>
<body onLoad="doLoad()">
<%-- 
<%=new UserQueryValidate().getValidataHTML("userQuery")%> --%>
<c:set var="using"><%=StateConstants.USING.getCode()%></c:set>
<c:set var="unused"><%=StateConstants.UNUSED.getCode()%></c:set>
<c:set var="menu"><%=RbacConstants.RESOURCE_NODE_TYPE_MENU.getCode()%></c:set>
<c:set var="node"><%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%></c:set>

<div class="title">监管员基本信息管理</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/region" styleId="regionForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="regionPageList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">监管员名称：</td>
		<td class="codeCol"><html:text property="query.name" styleId="query.name"/></td>
		<td class="nameCol">父级名称：</td>
		<td class="codeCol"><html:text property="query.parentName" styleId="query.parentName"/></td>
	  	<td class="nameCol">地区状态：</td>
		<td class="codeCol">
			<form:checkboxs property="query.status" collection="stateOptions" styleId="query.status"/>
		</td>
	  	<td class="nameCol"></td>
		<td class="codeCol"></td>
	</tr>
	<!-- 查询按钮 -->
	<tr class="formTableFoot" >
		<td colspan="8" align="center" class="tdPadding">
			<button class="formButton" onClick="doQuery()">查&nbsp;询</button>&nbsp;
			<button class="formButton" onClick="doClear()">重&nbsp;置</button>
		</td>
	</tr>
</table>

<div class="dvScroll">
<table  class="listTalbe" cellpadding="0" cellspacing="0">
	<thead>
		<tr class="title">
	      <td>序号</td>
	      <td><thumbpage:order cname="名称" filedName="name"/></td>
	      <td><thumbpage:order cname="级别" filedName="regionLevel"/></td>
	      <td><thumbpage:order cname="状态" filedName="status"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center">&nbsp;<c:out value="${index+1}"/></td>
				<td>&nbsp;<c:out value="${row.name}" /></td>
				<td>&nbsp;<c:out value="${row.regionLevel}" /></td>
				<td align="center">
					<select:stateName state="${row.status}"/>
				</td>
				<td align="center" nowrap="true" class="right-border-none">
					<c:if test="${row.parentId==0}">
						<a href="javascript:goAddChild('<c:out value='${row.id}'/>');">新增下级地区</a>&nbsp;
					</c:if>
<%-- 					<c:if test="${row.node_type==node}">
						<a href="javascript:void(0)" disabled="true">新增下级资源</a>&nbsp;
						<a href="javascript:setRole('<c:out value='${row.id}'/>');">分配角色</a>&nbsp;
					</c:if>
					
 --%>					<c:if test="${row.status==using}">
						<a href="javascript:doUnused('<c:out value='${row.id}'/>');">停用</a>&nbsp;
					</c:if>
					<c:if test="${row.status!=using}">
						<a href="javascript:doUsing('<c:out value='${row.id}'/>');">启用</a>&nbsp;
					</c:if>
					
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
					
					<c:if test="${row.status==unused}">
						<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
					</c:if>
					<c:if test="${row.status!=unused}">
						<a href="javascript:void(0)" disabled="true">删除</a>
					</c:if>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="regionList" action="/region/region.do?method=regionPageList"/>
<table class="bottomTable">
	<tr>
		<td><button class="formButton" onClick="goAdd()">新&nbsp;增</button></td>
	</tr>
</table>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>

</body>
</html>