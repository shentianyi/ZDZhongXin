﻿
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="file.tld" prefix="file"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>


<%@page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
		var bankDockType = $("#bankDockType").val();
		changeBankDockType(bankDockType);
		$("#contractNo").val("${distribset.contractNo}");
	}
	//执行保存操作
	function doSave() {
		if ($("#bankDockType").val() == "1") {
			if ($("#zsCustNo").val() == "") {
				alert("请填写浙商银行客户号!");
				return false;
			}
		}
		if ($("#bankDockType").val() == "2") {
			if ($("#zxOrgCode").val() == "") {
				alert("组织结构代码不能为空");
				return false;
			}
		}

		document.forms[0].submit();
	}

	function changeBankDockType(bankDockType) {
		if (bankDockType == "1") {
			$("#custNoContractNo").show();
			$("#custContractNo").show();

			$("#dealerno").hide();
			$("#zxOrgCode").val("");
		} else if (bankDockType == "2") {
			$("#dealerno").show();
			$("#custContractNo").show();

			$("#custNoContractNo").hide();
			$("#zsCustNo").val("");
		} else {
			$("#custNoContractNo").hide();
			$("#zsCustNo").val("");

			$("#dealerno").hide();
			$("#zxOrgCode").val("");
			
			$("#custContractNo").hide();
		}
		$("#contractNo").val("");
	}

	//进入列表页面
	function goList() {
		location = "/ledger/dealer.do?method=findBusinessList";
	}
</script>
</head>
<body onLoad="doLoad()">
<body>
	<div class="title">经销商参数设置</div>
	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/distribset" styleId="distribsetForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" id="method"
					value="addOrUpddistribset">
				<html:hidden property="distribset.distribid"
					styleId="distribset.distribid" />
				<html:hidden property="distribset.id" styleId="distribset.id" />
				<table class="formTable">
					<tr>
						<td class="nameCol">监管物移动百分比:</td>
						<td class="codeCol"><html:text property="distribset.movePerc"
								styleId="movePerc" />%</td>
						<td class="nameCol">对接银行:</td>
						<td class="codeCol"><form:select
								property="distribset.bankDockType" styleId="bankDockType"
								onchange="changeBankDockType(this.value)">
								<html:option value="0">不对接</html:option>
								<html:optionsCollection name="bankDockTypes" label="label"
									value="value" />
							</form:select></td>
					</tr>
					<tr>
						<td colspan="2" id="custNoContractNo" hidden="true">
							<div id="custNoContractN">
								<table class="formTable">
									<tr>
										<td class="nameCol"><font color="#FF0000">*</font>浙商银行客户号:</td>
										<td class="codeCol"><html:text
												property="distribset.zsCustNo" styleId="zsCustNo" /></td>
									</tr>
								</table>
							</div>
						</td>
						<td colspan="2" id="dealerno" hidden="true">
							<div id="custNoContractM">
								<table class="formTable">
									<tr>
										<td class="nameCol"><font color="#FF0000">*</font>组织机构代码:</td>
										<td class="codeCol"><html:text
												property="distribset.zxOrgCode" styleId="zxOrgCode" /></td>
									</tr>
								</table>
							</div>
						</td>
						<td colspan="2" id="custContractNo" hidden="true">
							<div id="ContractN">
								<table class="formTable">
									<tr>
										<td class="nameCol">质押合同编号：</td>
										<td class="codeCol"><html:text
												property="distribset.contractNo" styleId="contractNo" /></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr class="formTableFoot">
						<td colspan="4" align="center">
							<button class="formButton" onClick="doSave()">保&nbsp;存</button>&nbsp;
							<button class="formButton" onClick="goList()">返&nbsp;回</button>
						</td>
					</tr>
				</table>
			</html:form>
		</div>
	</div>
	<div class="message" id="message" style="display: none" align="center"></div>
</body>
</html>

