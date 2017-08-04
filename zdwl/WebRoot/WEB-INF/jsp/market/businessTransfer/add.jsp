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
<!-- easyui -->
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>

<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/pagejs/businessTransfer.js"></script>

<script>

	$(function(){
		init();
	});
	//页面初始化函数
	function doLoad(){
		//显示提示信息
		showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}


	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/market/businessTransfer.do?method=findList";
	}

	//执行表单重置操作
	function doReset() {
		document.forms[0].reset();
	}
	
</script>
</head>
<body onLoad="doLoad()">

	<div class="title">新增业务流转单</div>
	<hr size="1">
	<br />

	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<html:form action="/market/businessTransfer" styleId="btForm"
				method="post" onsubmit="return false">
				<input type="hidden" name="method" value="add" />

				<table class="formTable">
					<tr class="formTitle">
						<td colspan="4">一、市场部信息</td>
					</tr>
					<tr>
						<td class="nameCol">经销商：</td>
						<td class="codeCol">
							<select style="width:100%" id="jxs" name="business.dealerId" >
								<c:forEach items="${dealers }" var="row">
									<option value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
								</c:forEach>
							</select>
						</td>
						<td class="nameCol">店方联系人：</td>
						<td class="codeCol"><input type="text" id="contact" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">联系人电话：</td>
						<td class="codeCol"><input type="text" id="contactPhone" readonly="readonly"/></td>
						<td class="nameCol">金融机构：</td>
						<td class="codeCol">
							<input type="text"id="bankName"readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td class="nameCol">银行客户经理：</td>
						<td class="codeCol" >
							<input type="text"id="bankContact"readonly="readonly" />
						</td>
						<td class="nameCol">客户经理电话：</td>
						<td class="codeCol" >
							<input type="text"id="bankPhone"readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td class="nameCol">品牌：</td>
						<td class="codeCol"><input type="text" id="brand" readonly="readonly"/></td>
						<td class="nameCol">协议监管模式：</td>
						<td class="codeCol">
							<input type="checkbox" value="车证" id="supervisionMode_1" disabled="disabled"/>车证
							<input type="checkbox" value="合格证" id="supervisionMode_2" disabled="disabled"/>合格证
							<input type="checkbox" value="贷后巡库" id="supervisionMode_3" disabled="disabled"/>贷后巡库
							<input type="checkbox" value="金融监管库" id="supervisionMode_4" disabled="disabled"/>金融监管库
						</td>
					</tr>
					<tr>
						<td class="nameCol">合作模式：</td>
						<td class="codeCol">
							两方：<input type="radio" id="cooperationModel_1" disabled="disabled"/>
							三方：<input type="radio" id="cooperationModel_2" disabled="disabled"/>
						</td>
						<td class="nameCol">经销商性质：</td>
						<td class="codeCol"><input type="text" id="dealerNature" readonly="readonly"/></td>
					</tr>
					<tr>
						<td class="nameCol">监管详细地址：</td>
						<td class="codeCol"><input type="text" id="superviseAddress" readonly="readonly" style="width:100%;"/></td>
						<td class="nameCol">是否提供午餐：</td>
						<td class="codeCol">
							是：<input type="radio" id="provideLunch_1" disabled="disabled"/>
							否：<input type="radio" id="provideLunch_2" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td class="nameCol">是否绑定店：</td>
						<td class="codeCol">
							是：<input type="radio" id="isBindShop_1" disabled="disabled"/>
							否：<input type="radio" id="isBindShop_2" disabled="disabled"/>
						</td>
					</tr>
					<tr id="bindShop">
						<td class="nameCol">绑定店名称：</td>
						<td class="codeCol"><input type="text" id="bindShopName" readonly="readonly" style="width:100%"/></td>
					</tr>
					<tr>
						<td class="nameCol">是否需要交接：</td>
						<td class="codeCol">
							是：<input type="radio" id="isNeedHandover_1" disabled="disabled"/>
							否：<input type="radio" id="isNeedHandover_2" disabled="disabled"/>
						</td>
						<td class="nameCol"><font color="#FF0000">*</font>进店时间：</td>
						<td class="codeCol">
							<form:calendar property="business.inStoreDate" styleId="yjjd"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" />
						</td>
					</tr>
					<tr id="jjgs">
						<td class="nameCol">交接公司：</td>
						<td class="codeCol"><input type="text" id="handoverCompany" readonly="readonly"/></td>
						<td class="nameCol">交接时间：</td>
						<td class="codeCol"><form:calendar property="business.jiaojieDate"
								pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" /></td>
					</tr>
					<tr>
						<td class="nameCol">备注：</td>
						<td class="codeCol" colspan="3">
							<html:text property="business.dealerRemark"></html:text>
						</td>
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
