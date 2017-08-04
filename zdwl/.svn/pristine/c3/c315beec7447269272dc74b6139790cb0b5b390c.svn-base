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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
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
	var id=document.getElementsByName("roster.id")[0].value;
	window.location="<url:context/>/roster.do?method=addPostChangeEntity&roster.id="+id;
}

//进入修改页面
function goUpd(id){
	window.location="<url:context/>/roster.do?method=updPostChangeEntity&postChange.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("确认删除？")){
		var rosterId=document.getElementsByName("roster.id")[0].value;
		window.location="<url:context/>/roster.do?method=delPostChange&postChange.id="+id+"&roster.id="+rosterId;
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/roster.do?method=rosterPageList";
}

//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("rosterForm")){
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
<div class="title">花名册岗位轮换管理</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/roster" styleId="rosterForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="postChangeList"/>
<html:hidden property="roster.id" styleId="roster.id" />

<div class="dvScroll">
<table  class="listTalbe" cellpadding="0" cellspacing="0">
	<thead>
		<tr class="title">
	      <td>序号</td>
	      <td><thumbpage:order cname="上岗日期" filedName="missionDate"/></td>
	       <td><thumbpage:order cname="上岗性质" filedName="missionNature"/></td>
	      <td><thumbpage:order cname="离岗日期" filedName="dimissionDate"/></td>
	      <td><thumbpage:order cname="离岗性质" filedName="dimissionNature"/></td>
	      
	      <!-- td align="center">操作</td -->
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center">&nbsp;<c:out value="${index+1}"/></td>
				<td>&nbsp;<select:timestamp timestamp="${row.missionDate}" idtype="date"/></td>
				<td>&nbsp;<c:out value="${row.missionNature}" /></td>
				<td>&nbsp;<select:timestamp timestamp="${row.dimissionDate}" idtype="date"/></td>
				<td>&nbsp;<c:out value="${row.dimissionNature}" /></td>
				
				<!-- td align="center" nowrap="true" class="right-border-none">
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
				</td -->
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<table class="bottomTable">
	<tr>
		<!-- td><button class="formButton" onClick="goAdd()">新&nbsp;增</button></td -->
		<td><button class="formButton" onClick="doReturn()">返&nbsp;回</button></td>
	</tr>
</table>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>

</body>
</html>