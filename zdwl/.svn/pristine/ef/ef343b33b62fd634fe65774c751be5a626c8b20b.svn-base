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
	var repositoryId = $(obj).attr("repositoryId");
	var repositoryName = $(obj).parent().parent("tr").find(".userName").html();
	window.parent.changeName(repositoryId,repositoryName);
	window.parent.closeDialog("selectJgy");
}
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("repositoryQuery.supervisorName").value="";
	getElement("repositoryQuery.idNumber").value="";
}
</script>
</head>
<body>

<div class="title">监管员</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/select" styleId="selectsForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="repositoryList"/>
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
	  	<td class="nameCol">姓名：</td>
		<td class="codeCol"><html:text property="repositoryQuery.supervisorName" styleId="repositoryQuery.supervisorName"/></td>
		<td class="nameCol">身份证号：</td>
		<td class="codeCol"><html:text property="repositoryQuery.idNumber" styleId="repositoryQuery.idNumber"/></td>
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
	      <td><thumbpage:order cname="监管员姓名" filedName="name"/></td>
	      <td><thumbpage:order cname="身份证号" filedName="name"/></td>
	      <td><thumbpage:order cname="手机号" filedName="name"/></td>
	      <td><thumbpage:order cname="现居地址" filedName="name"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td class="userName" align="center"><select:supervisorName repositoryId="-1" supervisorId="${row.repository.supervisorId}" idtype="name"/></td>
				<td align="center"><select:supervisorName repositoryId="-1" supervisorId="${row.repository.supervisorId}" idtype="idNumber"/></td>
				<td align="center"><select:supervisorName repositoryId="-1" supervisorId="${row.repository.supervisorId}" idtype="selfTelephone"/></td>
				<td align="center"><select:supervisorName repositoryId="-1" supervisorId="${row.repository.supervisorId}" idtype="address"/></td>
				<td align="center" nowrap="true" class="right-border-none">
					<a repositoryId="<c:out value='${row.repository.id}'/>" href="javascript:void(0)" onclick="doSelect(this)">选择</a>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="repositoryList" action="/select.do?method=repositoryList"/>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>

</body>
</html>
