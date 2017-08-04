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
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
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
	var id=document.getElementsByName("baseInfo.id")[0].value;
	window.location="<url:context/>/supervisory.do?method=addSupervisoryEducationEntity&baseInfo.id="+id;
}

//进入修改页面
function goUpd(id){
	window.location="<url:context/>/supervisory.do?method=updSupervisoryEducationEntity&education.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("确认删除？")){
		var baseInfoId=document.getElementsByName("baseInfo.id")[0].value;
		window.location="<url:context/>/supervisory.do?method=delSupervisoryEducation&education.id="+id+"&baseInfo.id="+baseInfoId;
	}
}

//执行启用操作
function doUsing(id){
	if(confirm("确认启用？")){
		location="<url:context/>/supervisory.do?method=updSupervisoryState&supervisory.id="+id+"&supervisory.status=<%=StateConstants.USING.getCode()%>";
	}
}

//执行停用操作
function doUnused(id){
	if(confirm("确认停用？")){
		location="<url:context/>/supervisory.do?method=updSupervisoryState&supervisory.id="+id+"&supervisory.status=<%=StateConstants.UNUSED.getCode()%>";
	}
}
//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("supervisoryForm")){
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

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/supervisory.do?method=supervisoryPageList";
}
$(function(){
//限制超级角色操作
    restrict();
});

function restrict(){
    var crole = $("#crole").val();
    if(crole == 30){
        $(".yc").hide();
    }
}
</script>

</head>
<body onLoad="doLoad()">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
<%-- 
<%=new UserQueryValidate().getValidataHTML("userQuery")%> --%>
<c:set var="using"><%=StateConstants.USING.getCode()%></c:set>
<c:set var="unused"><%=StateConstants.UNUSED.getCode()%></c:set>
<c:set var="menu"><%=RbacConstants.RESOURCE_NODE_TYPE_MENU.getCode()%></c:set>
<c:set var="node"><%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%></c:set>
<div class="title">监管员教育状况管理</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/supervisory" styleId="supervisoryForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="getEducationListBySupervisoryId"/>
<html:hidden property="baseInfo.id" styleId="baseInfo.id" />

<!-- 查询条件 -->
<%-- <table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">监管员名称：</td>
		<td class="codeCol"><html:text property="query.name" styleId="query.name"/></td>
		<td class="nameCol">父级名称：</td>
		<td class="codeCol"><html:text property="query.parentName" styleId="query.parentName"/></td>
	  	<td class="nameCol">监管员状态：</td>
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
</table> --%>

<div class="dvScroll">
<table  class="listTalbe" cellpadding="0" cellspacing="0">
	<thead>
		<tr class="title">
	      <td>序号</td>
	      <td><thumbpage:order cname="起" filedName="educationStartTime"/></td>
	      <td><thumbpage:order cname="止" filedName="educationEndTime"/></td>
	      <td><thumbpage:order cname="学校名称" filedName="schoolName"/></td>
	      <td><thumbpage:order cname="主修专业" filedName="major"/></td>
	      <td><thumbpage:order cname="获得证书" filedName="certificate"/></td>
	      <td><thumbpage:order cname="学位" filedName="degree"/></td>
	      <td align="center" class="yc">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center">&nbsp;<c:out value="${index+1}"/></td>
				<td>&nbsp;<select:timestamp timestamp="${row.educationStartTime}" idtype="date"/></td>
				<td>&nbsp;<select:timestamp timestamp="${row.educationEndTime}" idtype="date"/></td>
				<td>&nbsp;<c:out value="${row.schoolName}" /></td>
				<td>&nbsp;<c:out value="${row.major}" /></td>
				<td>&nbsp;<c:out value="${row.certificate}" /></td>
				<td>&nbsp;<c:out value="${row.degree}" /></td>
				
				<td align="center" nowrap="true" class="right-border-none" class="yc">
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<table  class="formTableFoot">
	<tr>
		<td><button class="formButton yc" onClick="goAdd()">新&nbsp;增</button></td>
		<td><button class="formButton" onClick="doReturn()">返&nbsp;回</button></td>
	</tr>
</table>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>

</body>
</html>