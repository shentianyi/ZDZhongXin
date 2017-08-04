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
	location="<url:context/>/superviseArrears.do?method=addSuperviseArrearsEntry";
}

//进入修改页面
function goUpd(id){
	location = "<url:context/>/superviseArrears.do?method=updSuperviseArrearsEntry&superviseArrears.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/superviseArrears.do?method=delSuperviseArrears&superviseArrears.id="+id;
	}
}

//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("superviseArrearsForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("superviseArrearsquery.distribid").value=-1;
	getElement("superviseArrearsquery.financial_institution").value="";
	getElement("superviseArrearsquery.arrears_user").value="";
	getElement("superviseArrearsquery.arrears_begin").value="";
	getElement("superviseArrearsquery.arrears_end").value="";
	getElement("superviseArrearsquery.follow_user").value="";
}
</script>
</head>
<body onLoad="doLoad()">

<div class="title">欠费台账</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/superviseArrears" styleId="superviseArrearsForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="superviseArrearsList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">经销商：</td>
		<td class="codeCol">
			<form:select property="superviseArrearsquery.distribid" styleId="superviseArrearsquery.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
		</td>
	  	<td class="nameCol">金融机构：</td>
		<td class="codeCol"><html:text property="superviseArrearsquery.financial_institution" styleId="superviseArrearsquery.financial_institution"/></td>
		<td class="nameCol">待付款联系人：</td>
	  	<td class="codeCol">
	  		<html:text property="superviseArrearsquery.arrears_user" styleId="superviseArrearsquery.arrears_user"/>
	  	</td>
	</tr>
	<tr>
		<td class="nameCol">欠费起始时间：</td>
		<td class="codeCol">
			<form:calendar property="superviseArrearsquery.arrears_begin" styleId="superviseArrearsquery.arrears_begin" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	  	<td class="nameCol">欠费停止时间：</td>
		<td class="codeCol">
			<form:calendar property="superviseArrearsquery.arrears_end" styleId="superviseArrearsquery.arrears_end" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
		<td class="nameCol">跟进人员：</td>
	  	<td class="codeCol">
	  		<html:text property="superviseArrearsquery.follow_user" styleId="superviseArrearsquery.follow_user"/>
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
	      <td><thumbpage:order cname="经销商" filedName="distribid"/></td>
	      <td><thumbpage:order cname="金融机构" filedName="financial_institution"/></td>
	      <td><thumbpage:order cname="欠费起始时间" filedName="arrears_begin"/></td>
	      <td><thumbpage:order cname="欠费停止时间" filedName="arrears_end"/></td>
	      <td><thumbpage:order cname="欠费金额" filedName="arrears_money"/></td>
	      <td><thumbpage:order cname="待付款联系人" filedName="arrears_user"/></td>
	      <td><thumbpage:order cname="联系方式" filedName="arrears_phone"/></td>
	      <td><thumbpage:order cname="跟进时间" filedName="follow_date"/></td>
	      <td><thumbpage:order cname="跟进人员" filedName="follow_user"/></td>
	      <td><thumbpage:order cname="跟进结果" filedName="follow_result"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><select:dealerName dealerid="${row.distribid}"/></td>
				<td align="center"><c:out value="${row.financial_institution}" /></td>
				<td align="center"><select:timestamp timestamp="${row.arrears_begin}" idtype="date"/></td>
				<td align="center"><select:timestamp timestamp="${row.arrears_end}" idtype="date"/></td>
				<td align="center"><c:out value="${row.arrears_money}"/></td>
				<td align="center"><c:out value="${row.arrears_user}"/></td>
				<td align="center"><c:out value="${row.arrears_phone}"/></td>
				<td align="center"><select:timestamp timestamp="${row.follow_date}" idtype="date"/></td>
				<td align="center"><c:out value="${row.follow_user}"/></td>
				<td align="center"><c:out value="${row.follow_result}"/></td>
				<td align="center" nowrap="true" class="right-border-none">
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="superviseArrearsList" action="/superviseArrears.do?method=superviseArrearsList"/>
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
