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

//执行查询操作
function doQuery(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("carManualForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}
function doClear(){
	//清空资源名输入框
	getElement("carmanualquery.draft_num").value="";
}

</script>
</head>
<body onLoad="doLoad()">

<div class="title">质押监管手工台账</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/carManual" styleId="carManualForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="carManualList"/>
<!-- 查询条件 -->
<table class="formTable" cellpadding="0" cellspacing="0">
	<tr>
		<td class="nameCol">票号：</td>
		<td class="codeCol"><html:text property="carmanualquery.draft_num" styleId="carmanualquery.draft_num"/></td>
		<td class="nameCol"></td>
	  	<td class="codeCol"></td>
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
	      <td><thumbpage:order cname="票号" filedName="distribid"/></td>
	      <td><thumbpage:order cname="合格证发证日期" filedName="actual_address"/></td>
	      <td><thumbpage:order cname="合格证号" filedName="relationship"/></td>
	      <td><thumbpage:order cname="车辆型号" filedName="isreport"/></td>
	      <td><thumbpage:order cname="排量" filedName="distance"/></td>
	      <td><thumbpage:order cname="颜色" filedName="distance"/></td>
	      <td><thumbpage:order cname="发动机号" filedName="distance"/></td>
	      <td><thumbpage:order cname="车架号" filedName="distance"/></td>
	      <td><thumbpage:order cname="钥匙号" filedName="distance"/></td>
	      <td><thumbpage:order cname="入库时间" filedName="distance"/></td>
	      <td><thumbpage:order cname="出库时间" filedName="distance"/></td>
	      <td><thumbpage:order cname="交付人" filedName="distance"/></td>
	      <td><thumbpage:order cname="接收人" filedName="distance"/></td>
	      <td align="center">操作</td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="draft_num"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="certificate_date"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="certificate_num"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="car_model"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="displacement"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="color"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="engine_num"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="vin"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="key_num"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="storagetime"/></td>
				<td align="center"><select:superviseImport sid="${row.sid}" idtype="outtime"/></td>
				<td align="center"><select:roster sid="${row.userid}" idtype="jgy"/></td>
				<td align="center"><select:user userid="${row.receiveid}"/></td>
			</tr>
		</logic:iterate>
	</tbody>  
</table>
</div>
<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="carManualList" action="/carManual.do?method=carManualList"/>
<div id="message" class="message" style="display:none"></div>
</html:form>

	</div>
</div>

</body>
</html>
