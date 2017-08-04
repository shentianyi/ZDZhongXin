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

//执行查询操作
function doQuery(){
	
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空姓名输入框
	getElement("query.userName").value="";
}

function goBank(id){
	window.location="/business/ywBank.do?method=findList&query.userId="+id;
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

<html:form action="/business/ywBank.do" styleId="ywBankForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="userList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr> 
		<td class="nameCol">用户名：</td>
		<td class="codeCol"><html:text property="query.userName" styleId="query.userName"/></td>
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
				<td>操作</td>
			</tr>
		</thead>
		<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a"> 
				<td align="center"><c:out value="${index+1}"/></td>
				<td>&nbsp;<c:out value="${row.loginid}"/></td>
				<td>&nbsp;<c:out value="${row.userName}"/></td>
				<td align="center" nowrap="true">
					<a href="javascript:goBank('<c:out value='${row.id}'/>')">银行管理</a>
				</td>
			</tr>
		</logic:iterate>
		</tbody>
	</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="ywUserList" action="/business/ywBank.do?method=userList"/>
<br/>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>
</body>
</html>