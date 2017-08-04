<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>


<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script src="/easyui/jquery.min.js"></script>
<script>
//页面初始化函数
function doLoad(){
	var mailingitems = document.getElementById("mailcost.mailingitems").value;
	var bb = mailingitems.split(",");
	var aa = document.getElementsByName("mailingitemss");
	for(var i=0; i<bb.length; i++) {
		if(aa[i].value==bb[i]) {
			aa[i].checked = true;
		}
	}
}
$(function(){
	changeYj($('#mailperson').val());
	changeJs($('#receiveid').val());
});

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/mailcost.do?method=findList";
}
function changeYj(id) {
		if(id==-1){
			document.forms[0].reset();
			return;
		}
		var url = "../json/getMailCost.do?callback=?&id="+id;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setYj(data[0]);
		});
	}
	
function setYj(yj){
	$("#yjgh").val(yj.gh);
	$("#yjjxs").val(yj.jxs);
	$("#yjjrjg").val(yj.jrjg);
}
function changeJs(id) {
		if(id==-1){
			document.forms[0].reset();
			return;
		}
		var url = "../json/getMailCost.do?callback=?&id="+id;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setJs(data[0]);
		});
	}
	
function setJs(js){
	$("#jsgh").val(js.gh);
	$("#jsjxs").val(js.jxs);
	$("#jsjrjg").val(js.jrjg);
}
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">邮寄费用申请</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/mailcost" styleId="mailcostForm" method="post" onsubmit="return false">
				<html:hidden property="mailcost.id" styleId="mailcost.id"/>
				<html:hidden property="mailcost.mailingitems" styleId="mailcost.mailingitems"/>
				<input type="hidden" name="method" id="method" value="updMailcost">
				<table class="formTable">
					<tr>
						<td class="nameCol">发起日期：</td>
						<td class="codeCol">
							<form:calendar property="mailcost.fqdate" styleId="mailcost.fqdate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" disabled="true" />
						</td>
						<td class="nameCol">邮寄项目：</td>
						<td class="codeCol">
							<form:checkboxs property="mailingitemss" styleId="mailingitemss" collection="mailcostStateOptions" disabled="true"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">配件：</td>
						<td class="codeCol">
							<html:text property="mailcost.parts" styleId="mailcost.parts"disabled="true"></html:text>
						</td>
						<td class="nameCol">其它：</td>
						<td class="codeCol">
							<html:text property="mailcost.other" styleId="mailcost.other" disabled="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">邮寄人：</td>
						<td class="codeCol">
							<form:select property="mailcost.mailperson" styleId="mailperson" onchange="changeYj(this.value)" disabled="true">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="superviseOptions" label="label" value="value" />
							</form:select>
						</td>
						<td class="nameCol">工号：</td>
						<td class="codeCol">
							<input type="text" id="yjgh" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">经销商：</td>
						<td class="codeCol">
							<input type="text" id="yjjxs" readonly="readonly"/>
						</td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text" id="yjjrjg" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">快递公司：</td>
						<td class="codeCol">
							<html:text property="mailcost.express" styleId="mailcost.express" disabled="true"></html:text>
						</td>
						<td class="nameCol">预计金额：</td>
						<td class="codeCol">
							<html:text property="mailcost.money" styleId="mailcost.money" disabled="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">运输城市起：</td>
						<td class="codeCol">
							<html:text property="mailcost.transportbegin" styleId="mailcost.transportbegin" disabled="true"></html:text>
						</td>
						<td class="nameCol">运输城市止：</td>
						<td class="codeCol">
							<html:text property="mailcost.transportend" styleId="mailcost.transportend" disabled="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class="nameCol">接收人：</td>
						<td class="codeCol">
							<form:select property="mailcost.receiveid" styleId="receiveid" onchange="changeJs(this.value)" disabled="true">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="superviseOptions" label="label" value="value" />
							</form:select>
						</td>
						<td class="nameCol">工号：</td>
						<td class="codeCol">
							<input type="text" id="jsgh" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">经销商：</td>
						<td class="codeCol">
							<input type="text" id="jsjxs" readonly="readonly"/>
						</td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text" id="jsjrjg" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">事件描述：</td>
						<td class="codeCol">
							<html:text property="mailcost.des" styleId="mailcost.des" disabled="true"></html:text>
						</td>
						<td class="nameCol"></td>
						<td class="codeCol"></td>
					</tr>
					<c:if test="${approvals!=null }">
						<tr class="formTitle">
							<td colspan="4">部门意见</td>
						</tr>
						
						<logic:iterate name="approvals" id="row" indexId="index">
							<tr>
							<td class="nameCol">第<c:out value='${index+1 }'/>步</td>
							<td class="nameCol" style="text-align: left;">
								<c:out value="${row.roleName }"/>：
								<c:out value="${row.userName }"/>于<fmt:formatDate value="${row.remarkDate }" pattern="yyyy-MM-dd"/>
							</td>
							<td class="codeCol" colspan="2">
								<c:if test="${row.approvalResult==1}">同意&nbsp;</c:if>
								<c:if test="${row.approvalResult==2}">不同意&nbsp;</c:if>
								<c:out value="${row.remark }"/>
							</td>
						</tr>
						</logic:iterate>
					</c:if>
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
						</td>
					</tr>
				</table>
				<br />
				<div class="message" id="message" style="display: none"></div>
			</html:form>

		</div>
	</div>
</body>
</html>
