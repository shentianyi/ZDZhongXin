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

<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>
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
	window.location="<url:context/>/rbac/user.do?method=addjgyUserEntry";
}

//进入修改页面
function goUpd(id){
	window.location="<url:context/>/rbac/user.do?method=updjgyUserEntry&user.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("确认删除？")){
		window.location="<url:context/>/rbac/user.do?method=deljgyUser&user.id="+id;
	}
}

//执行启用操作
function doUsing(id){
	if(confirm("确认启用？")){
		window.location="<url:context/>/rbac/user.do?method=updjgyUserState&user.id="+id+"&user.state=<%=StateConstants.USING.getCode()%>";
	}
}

//执行停用操作
function doUnused(id){
	if(confirm("确认停用？")){
		window.location="<url:context/>/rbac/user.do?method=updjgyUserState&user.id="+id+"&user.state=<%=StateConstants.UNUSED.getCode()%>";
	}
}

//执行注销操作
function doStateDel(id){
	if(confirm("确认注销？")){
		window.location="<url:context/>/rbac/user.do?method=updjgyUserState&user.id="+id+"&user.state=<%=StateConstants.DELETE.getCode()%>";
	}
}

//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("userForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行表单清空操作
function doClear(){
	//清空登录名输入框
	getElement("userQuery.loginid").value="";
	//清空姓名输入框
	getElement("userQuery.userName").value="";
	//清空状态复选框
	var states = document.getElementsByName("userQuery.states");
	for(var i = 0; i<states.length; i++){
		states[i].checked = false;
	}
}

function changeClientType(){

	var clientType = getElement("userQuery.clientType");
	if(clientType.value==5){
		document.getElementById("ri").style.display = "block";
	} else{
		document.getElementById("ri").style.display = "none";
	}
	
}
</script>

</head>
<body onLoad="doLoad()">

<c:set var="undone"><%=StateConstants.UNDONE.getCode()%></c:set>
<c:set var="using"><%=StateConstants.USING.getCode()%></c:set>
<c:set var="unused"><%=StateConstants.UNUSED.getCode()%></c:set>
<div class="title">账户管理</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">

<html:form action="/rbac/user" styleId="userForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="jgyuserList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr> 
		<td class="nameCol">登录名：</td>
		<td class="codeCol"><html:text property="userQuery.loginid" styleId="userQuery.loginid"/></td>
		<td class="nameCol">用户名：</td>
		<td class="codeCol"><html:text property="userQuery.userName" styleId="userQuery.userName"/></td>
		<td class="nameCol">账户状态：</td>
		<td class="codeCol">
			<form:checkboxs property="userQuery.states" styleId="userQuery.states" collection="userStateOptions"/>
		</td>
	</tr>
</table>
<table class="formTable">
	<!-- 查询按钮 -->
	<tr class="formTableFoot">
		<td align="center" colspan="4">
			<button class="formButton" onClick="doQuery()">查&nbsp;询</button>
			&nbsp;&nbsp;
			<button class="formButton" onClick="doClear()">重&nbsp;置</button>
		</td>
	</tr>
</table>
<div class="dvScroll">
	<table  class="listTalbe" cellpadding="0" cellspacing="0">
		<thead>
			<tr class="title">
				<td width="5%">序号</td>
				<td><thumbpage:order cname="登录名" filedName="loginid"/></td>
				<td><thumbpage:order cname="用户名" filedName="user_name"/></td>
				<td><thumbpage:order cname="账户类型" filedName="client_type"/></td>
				<td><thumbpage:order cname="角色类型" filedName="client_type"/></td>
				<td><thumbpage:order cname="账户状态" filedName="state"/></td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a"> 
				<td align="center"><c:out value="${index+1}"/></td>
				<td>&nbsp;<c:out value="${row.loginid}"/></td>
				<td>&nbsp;<c:out value="${row.userName}"/></td>
				<td>&nbsp;<select:clientTypeName clientType="${row.client_type}"/></td>
				<td>&nbsp;<select:roleName clientType="${row.id}" roleCode="-1"/></td>
				<td align="center">
					<select:stateName state="${row.state}"/>
				</td>
				<td align="center" nowrap="true">
					<c:if test="${row.state==using}">
					<a href="javascript:doUnused('<c:out value='${row.id}'/>');">停用</a>&nbsp;
					</c:if>
					<c:if test="${row.state!=using}">
					<a href="javascript:doUsing('<c:out value='${row.id}'/>');">启用</a>&nbsp;
					</c:if>
		
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
		
					<c:if test="${row.state==undone}">
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
					</c:if>
					<c:if test="${row.state==using}">
					<a href="javascript:void(0)" disabled="true">注销</a>
					</c:if>
					<c:if test="${row.state==unused}">
					<a href="javascript:doStateDel('<c:out value='${row.id}'/>')">注销</a>
					</c:if>
				</td>
			</tr>
		</logic:iterate>
		</tbody>
	</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="jgyuserList" action="/rbac/user.do?method=jgyuserList"/>
<table class="bottomTable">
	<tr>
		<td><button class="formButton" onClick="goAdd()">新&nbsp;增</button></td>
	</tr>
</table>
<br/>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>
</body>
</html>