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
	location="<url:context/>/mailing.do?method=addMailingEntry";
}

//进入修改页面
function goUpd(id){
	location = "<url:context/>/mailing.do?method=updDraftEntry&mailing.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/mailing.do?method=delMailing&mailing.id="+id;
	}
}

//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("mailingForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("mailingquery.superviseid").value=-1;
	getElement("mailingquery.mailnature").value="";
	getElement("mailingquery.mailtimebegin").value="";
	getElement("mailingquery.mailtimeend").value="";
}
</script>
</head>
<body onLoad="doLoad()">

<div class="title">监管员邮寄明细台账</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/mailing" styleId="mailingForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="mailingList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">监管员：</td>
		<td class="codeCol">
			<form:select property="mailingquery.superviseid" styleId="mailingquery.superviseid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="superviseOptions" label="label" value="value" />
			</form:select>
		</td>
	  	<td class="nameCol">快递性质：</td>
		<td class="codeCol"><html:text property="mailingquery.mailnature" styleId="mailingquery.mailnature"/></td>
		<td class="nameCol">邮寄时间：</td>
	  	<td class="codeCol">
	  		<form:calendar property="mailingquery.mailtimebegin" styleId="mailingquery.mailtimebegin" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
	  		-<form:calendar property="mailingquery.mailtimeend" styleId="mailingquery.mailtimeend" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
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
	      <td><thumbpage:order cname="经销商" filedName="department"/></td>
	      <td><thumbpage:order cname="合作金融机构" filedName="name"/></td>
	      <td><thumbpage:order cname="监管员" filedName="quarters"/></td>
	      <td><thumbpage:order cname="工号" filedName="phone"/></td>
	      <td><thumbpage:order cname="快递性质" filedName="mailnature"/></td>
	      <td><thumbpage:order cname="邮寄时间" filedName="mailtime"/></td>
	      <td><thumbpage:order cname="快递公司" filedName="express"/></td>
	      <td><thumbpage:order cname="单号" filedName="express_num"/></td>
	      <td><thumbpage:order cname="内容" filedName="des"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><select:roster sid="${row.superviseid}" idtype="jxs"/></td>
				<td align="center"><select:roster sid="${row.superviseid}" idtype="jrjg"/></td>
				<td align="center"><select:roster sid="${row.superviseid}" idtype="jgy"/></td>
				<td align="center"><select:roster sid="${row.superviseid}" idtype="gh"/></td>
				<td align="center"><c:out value="${row.mailnature}" /></td>
				<td align="center"><select:timestamp timestamp="${row.mailtime}" idtype="date"/></td>
				<td align="center"><c:out value="${row.express}" /></td>
				<td align="center"><c:out value="${row.express_num}" /></td>
				<td align="center"><c:out value="${row.des}" /></td>
				<td align="center" nowrap="true" class="right-border-none">
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="mailingList" action="/mailing.do?method=mailingList"/>
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
