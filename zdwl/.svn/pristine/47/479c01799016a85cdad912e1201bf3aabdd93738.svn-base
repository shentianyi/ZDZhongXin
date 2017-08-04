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
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

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
<script type="text/javascript">
function doSelect(obj){
	var userId = $(obj).attr("userId");
	var userName = $(obj).parent().prev().prev().html();
	window.parent.changeName(userId,userName);
	window.parent.closeDialog("selectOutControlUserList");
}
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("selectUserQuery.userName").value="";
	getElement("selectUserQuery.mobilePhone").value="";
}
function selectRow(obj){
	doSelect($(obj).find("a"));
}
</script>
</head>
<body>

<div class="title">用户选择</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/selectUserList" styleId="selectUserForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="findList"/>
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
	  	<td class="nameCol">姓名：</td>
		<td class="codeCol"><html:text property="selectUserQuery.userName" styleId="selectUserQuery.userName"/></td>
		<td class="nameCol">手机号：</td>
		<td class="codeCol"><html:text property="selectUserQuery.mobilePhone" styleId="selectUserQuery.mobilePhone"/></td>
	</tr>
	<!-- 查询按钮 -->
	<tr class="formTableFoot" >
		<td colspan="6" align="center" class="tdPadding">
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
	      <td><thumbpage:order cname="姓名" filedName="name"/></td>
	      <td><thumbpage:order cname="手机号" filedName="name"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a" ondblclick="selectRow(this)">
				<td align="center"><c:out value="${index+1}"/></td>
				<td class="userName" align="center"><c:out value="${row.userName}" /></td>
				<td align="center"><c:out value="${row.mobilephone}" /></td>
				<td align="center" nowrap="true" class="right-border-none">
					<a userId="<c:out value='${row.id}'/>" href="javascript:void(0)" onclick="doSelect(this)">选择</a>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="userList" action="/selectUserList.do?method=findList"/>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>

</body>
</html>
