<%@page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
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
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script>
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}
	//执行保存操作
	function doSave() {
		
		var value = document.getElementById("dealerId").value;
		if(value == "-1"){
			alert("请选择经销商");
			return false;
		}
		//对表单内容进行验证，包括对输入类型等限制方式
		if (validateMain("checkStockForm")) {
			//为时间类型输入项补齐时间戳
			setTimeSuffix();
			//提交表单
			document.forms[0].submit();
		}
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/supervisor/checkStock.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}

	function changeDealer(id) {
		if (id == -1) {
			document.forms[0].reset();
			return;
		}
		var url = "../json/getDealerByIdJsonAction.do?callback=?&id=" + id;
		$.getJSON(url, function(result) {
			var data = result.data;
			console.info(data);
			setDealer(data[0]);
		});
	}

	function setDealer(dealer) {
		var tDate;
		$("#dealerName").val(dealer.dealerName);
		$("#brand").val(dealer.brand);
		$("#bankName").val(dealer.bankName);
	}
	$(function(){
	$("#dealerId").combobox({
		onHidePanel : function() {// 验证输入的值，如果不存在下拉列表 则置空
			var newValue = $(this).combobox('getValue');
			var data = $(this).combobox('getData');
			var flag = false;
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (newValue == data[i].value) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				$(this).combobox('clear');
			} 
		}
	});
});
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">查库频次申请</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/supervisor/checkStock.do"
				styleId="checkStockForm" method="post" onsubmit="return false">
				<input type="hidden" name="method" value="addDealer" />
				<html:hidden property="query.id" />

				<table class="formTable">
					<tr>
						<td class="nameCol"><font color="#FF0000">*</font>经销商：</td>
						<td class="codeCol">
						<!-- <form:select property="csd.dealerId"
								styleId="dealerId" onchange="changeDealer(this.value)">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="dealers" label="label"
									value="value" />
						</form:select> -->
						<select id="dealerId" name="csd.dealerId" style="width:150px"  onchange="changeDealer(this.value)" >
								<option value="-1">请选择</option>
								<c:forEach items="${dealers }" var="row">
								<option <c:if test="${checkStockForm.csd.dealerId==row.value}">selected='selected'</c:if>  value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option> 
								</c:forEach>
					</select>
						</td>
					</tr>
					<tr>
						<td class="nameCol">经销店名称：</td>
						<td class="codeCol"><input type="text" id="dealerName" readonly="readonly"/></td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text"id="bankName" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" id="brand" readonly="readonly"/></td>
					</tr>
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
							<button class="formButton" onClick="doReset()">重&nbsp;置</button>&nbsp;
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
