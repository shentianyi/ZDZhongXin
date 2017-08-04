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
	location="<url:context/>/supervisePay.do?method=addSupervisePayEntry";
}

//进入修改页面
function goUpd(id){
	location = "<url:context/>/supervisePay.do?method=updSupervisePayEntry&supervisePay.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/supervisePay.do?method=delSupervisePay&supervisePay.id="+id;
	}
}

//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("supervisePayForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("supervisePayquery.distribid").value=-1;
	getElement("supervisePayquery.financial_institution").value="";
	getElement("supervisePayquery.supervise_expire").value="";
	getElement("supervisePayquery.payment_period").value="";
	getElement("supervisePayquery.pay_money").value="";
}
</script>
</head>
<body onLoad="doLoad()">

<div class="title">缴费台账</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/supervisePay" styleId="supervisePayForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="supervisePayList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">经销商：</td>
		<td class="codeCol">
			<form:select property="supervisePayquery.distribid" styleId="supervisePayquery.distribid">
				<html:option value="-1">请选择</html:option>
				<html:optionsCollection name="dealersOptions" label="label" value="value" />
			</form:select>
		</td>
	  	<td class="nameCol">金融机构：</td>
		<td class="codeCol"><html:text property="supervisePayquery.financial_institution" styleId="supervisePayquery.financial_institution"/></td>
		<td class="nameCol">监管费到期日：</td>
	  	<td class="codeCol">
	  		<form:calendar property="supervisePayquery.supervise_expire" styleId="supervisePayquery.supervise_expire" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
	  	</td>
	</tr>
	<tr>
		<td class="nameCol">缴费账期：</td>
		<td class="codeCol">
			<html:text property="supervisePayquery.payment_period" styleId="supervisePayquery.payment_period"/>
		</td>
	  	<td class="nameCol">缴费金额：</td>
		<td class="codeCol">
			<html:text property="supervisePayquery.pay_money" styleId="supervisePayquery.pay_money"/>
		</td>
		<td class="nameCol"></td>
	  	<td class="codeCol"></td>
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
	      <td><thumbpage:order cname="监管费到期日" filedName="supervise_expire"/></td>
	      <td><thumbpage:order cname="缴费账期" filedName="payment_period"/></td>
	      <td><thumbpage:order cname="缴费金额" filedName="pay_money"/></td>
	      <td><thumbpage:order cname="已缴" filedName="already_pay"/></td>
	      <td><thumbpage:order cname="未缴" filedName="not_pay"/></td>
	      <td><thumbpage:order cname="变费补差" filedName="change_payment"/></td>
	      <td><thumbpage:order cname="变费余额" filedName="balance_payment"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><select:dealerName dealerid="${row.distribid}"/></td>
				<td align="center"><c:out value="${row.financial_institution}" /></td>
				<td align="center"><select:timestamp timestamp="${row.supervise_expire}" idtype="date"/></td>
				<td align="center"><c:out value="${row.payment_period}"/></td>
				<td align="center"><c:out value="${row.pay_money}"/></td>
				<td align="center"><c:out value="${row.already_pay}"/></td>
				<td align="center"><c:out value="${row.not_pay}"/></td>
				<td align="center"><c:out value="${row.change_payment}"/></td>
				<td align="center"><c:out value="${row.balance_payment}"/></td>
				<td align="center" nowrap="true" class="right-border-none">
					<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
					<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
				</td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="supervisePayList" action="/supervisePay.do?method=supervisePayList"/>
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
