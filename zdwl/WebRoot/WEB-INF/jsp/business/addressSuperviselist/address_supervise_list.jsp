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
	location="<url:context/>/addressSuperviselist.do?method=addAddressSuperviselistEntry";
}

//进入修改页面
function goUpd(id){
	location = "<url:context/>/addressSuperviselist.do?method=updAddressSuperviselistEntry&addresslist.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/addressSuperviselist.do?method=delAddressSuperviselist&addresslist.id="+id;
	}
}

//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("addresslistquery.department").value="";
	getElement("addresslistquery.name").value="";
	getElement("addresslistquery.quarters").value="";
	getElement("addresslistquery.landline").value="";
	getElement("addresslistquery.fax").value="";
	getElement("addresslistquery.qq").value="";
}
</script>
</head>
<body onLoad="doLoad()">

<div class="title">通讯录</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/addressSuperviselist" styleId="addresslistForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="addressSuperviseList"/>
<input type="hidden" id="addresslistquery.genre" name="addresslistquery.genre" value="2" />
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">部门：</td>
		<td class="codeCol"><html:text property="addresslistquery.department" styleId="addresslistquery.department"/></td>
	  	<td class="nameCol">姓名：</td>
		<td class="codeCol"><html:text property="addresslistquery.name" styleId="addresslistquery.name"/></td>
		<td class="nameCol">岗位：</td>
	  	<td class="codeCol"><html:text property="addresslistquery.quarters" styleId="addresslistquery.quarters"/></td>
	</tr>
	<tr>
	  	<td class="nameCol">座机：</td>
		<td class="codeCol">
			<html:text property="addresslistquery.landline" styleId="addresslistquery.landline"/>
		</td>
		<td class="nameCol">传真：</td>
		<td class="codeCol"><html:text property="addresslistquery.fax" styleId="addresslistquery.fax"/></td>
	  	<td class="nameCol">QQ号码：</td>
		<td class="codeCol">
			<html:text property="addresslistquery.qq" styleId="addresslistquery.qq"/>
		</td>
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
	      <td><thumbpage:order cname="部门" filedName="department"/></td>
	      <td><thumbpage:order cname="姓名" filedName="name"/></td>
	      <td><thumbpage:order cname="岗位" filedName="quarters"/></td>
	      <td><thumbpage:order cname="座机" filedName="landline"/></td>
	      <td><thumbpage:order cname="传真" filedName="fax"/></td>
	      <td><thumbpage:order cname="QQ号码" filedName="qq"/></td>
	      <td><thumbpage:order cname="职责" filedName="duty"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><c:out value="${row.department}" /></td>
				<td align="center"><c:out value="${row.name}" /></td>
				<td align="center"><c:out value="${row.quarters}" /></td>
				<td align="center"><c:out value="${row.landline}"/></td>
				<td align="center"><c:out value="${row.fax}"/></td>
				<td align="center"><c:out value="${row.qq}"/></td>
				<td align="center"><c:out value="${row.duty}"/></td>
				
				<td align="center" nowrap="true" class="right-border-none">
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="addressSuperviseList" action="/addressSuperviselist.do?method=addressSuperviseList"/>
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
