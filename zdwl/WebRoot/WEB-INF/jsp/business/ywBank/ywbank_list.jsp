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
<script src="/js/jquery-1.8.3.min.js"></script>
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
	location="<url:context/>/business/ywBank.do?method=preAdd&query.userId="+$("#userId").val();
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/business/ywBank.do?method=delete&ywBank.id="+id+"&query.userId="+$("#userId").val();
	}
}

//执行查询操作
function doQuery(){
	
	
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("bankName").value="";
}

function doReturn(){
	location.href="/business/ywBank.do?method=userList";
}

function goBrandManager(id){
	location.href="/business/ywBank.do?method=brandList&query.id="+id+"&query.brandType=1&query.userId="+$("#userId").val();
}
</script>
</head>
<body onLoad="doLoad()">

<div class="title">银行管理</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/business/ywBank.do" styleId="ywBankForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="findList"/>
<html:hidden property="query.userId" styleId="userId"/>

<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">银行名称：</td>
		<td class="codeCol">
			<html:text property="query.bankName" styleId="bankName"></html:text>
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
	      <td><thumbpage:order cname="银行名称" filedName="bankName"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><c:out value="${row.bankName }"/></td>
				<td align="center" nowrap="true" class="right-border-none">
					<%-- <a href="javascript:goBrandManager('<c:out value='${row.id}'/>');">分配品牌</a>&nbsp; --%>
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>&nbsp;
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="ywBankList" action="/business/ywBank.do?method=findList"/>
		<table class="bottomTable">
			<tr>
				<td><button class="formButton" onClick="goAdd()">新&nbsp;增</button>
				&nbsp;
								<button class="formButton" onClick="doReturn()">返&nbsp;回</button></td>
				
			</tr>
		</table>
<div id="message" class="message" style="display:none"></div>
		
</html:form>

	</div>
</div>

</body>
</html>
