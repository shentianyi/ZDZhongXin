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
	location="<url:context/>/systemManage.do?method=addSystemManageEntry";
}

//进入修改页面
function goUpd(id){
	location = "<url:context/>/systemManage.do?method=updSystemManageEntry&systemManage.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/systemManage.do?method=delSystemManage&systemManage.id="+id;
	}
}

//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("systemManageForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("systemManagequery.username").value="";
	getElement("systemManagequery.station").value="";
	getElement("systemManagequery.loginid").value="";
	
}
</script>
</head>
<body onLoad="doLoad()">

<div class="title">系统用户名管理</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/systemManage" styleId="systemManageForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="systemManageList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">姓名：</td>
		<td class="codeCol"><html:text property="systemManagequery.username" styleId="systemManagequery.username"/></td>
	  	<td class="nameCol">岗位：</td>
		<td class="codeCol"><html:text property="systemManagequery.station" styleId="systemManagequery.station"/></td>
		<td class="nameCol">系统账号名称：</td>
	  	<td class="codeCol">
	  		<html:text property="systemManagequery.loginid" styleId="systemManagequery.loginid"/>
	  	</td>
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
	      <td><thumbpage:order cname="姓名" filedName="username"/></td>
	      <td><thumbpage:order cname="岗位" filedName="station"/></td>
	      <td><thumbpage:order cname="系统账号名称" filedName="loginid"/></td>
	      <td><thumbpage:order cname="密码" filedName="password"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><c:out value="${row.username}" /></td>
				<td align="center"><c:out value="${row.station}" /></td>
				<td align="center"><c:out value="${row.loginid}" /></td>
				<td align="center"><c:out value="${row.password}"/></td>
				<td align="center" nowrap="true" class="right-border-none">
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="systemManageList" action="/systemManage.do?method=systemManageList"/>
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
