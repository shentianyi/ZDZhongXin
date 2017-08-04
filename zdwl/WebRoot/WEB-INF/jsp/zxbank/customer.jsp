<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="c-1_0-rt.tld" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<%@page isELIgnored="false"%>

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link type="text/css" rel="stylesheet" href="/easyui/themes/bootstrap/easyui.css">
<link type="text/css" rel="stylesheet" href="/css/zxcss.css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/zxjs.js"></script>
<style type="text/css">
.textbox {
	margin-top: 5px;
	margin-left: 10%;
}
</style>
<script>
	$(function() {
		var msg = '${message}';
		if (msg != null && msg != "" && msg != "null") {
			alert(msg);
		}
		var qype = $("#queryType").val();
		$("#choose option[value='" + qype + "']").attr("selected", "selected");
		
		if (qype == "2") {
			$(".req").css("visibility", "visible");
		} else {
			$(".req").css("visibility", "hidden");
		}

		var code = $("#customer_custOrganizationcode").val();
		$("#custOrganizationcode option[value='" + code + "']").attr(
				"selected", "selected");

		comboboxSetId("#custOrganizationcode");
	});
	function doQuery() {
		/* if ($('#choose').val() == "2" && $("#custOrganizationcode").find("option:selected").text() == "请选择...") {
			alert("组织机构代码不能为空");
			return;
		} */
		document.forms[0].submit();
	}
	function doChoose(val) {
		if (val == "2") {
			$(".req").css("visibility", "visible");
		} else {
			$(".req").css("visibility", "hidden");
		}
	}

	function doClear() {
		$(":text").val("");
		$("#custOrganizationcode").combobox("setValue", "");
		$("#choose").val("");
	}
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="#">中信银行接口</a> &gt; <a class="crumbs-link" href="#">客户信息查询</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="cusForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="customer" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font class="req" color="#FF0000" style="visibility: hidden;">*</font>组织机构代码:
								</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="custOrganizationcode" name="customer.custOrganizationcode" style="width: 85%;">
											<option value="">请选择...</option>
											<c:forEach items="${draftOptions}" var="row">
												<option <c:if test="${row.label==customer.custOrganizationcode}"></c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }" /></option>
											</c:forEach>
										</select> <input type="hidden" id="customer_custOrganizationcode" value="${customer.custOrganizationcode}" />
									</div>
								</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">客户名称：</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="custName" name="customer.custName" value="${customer.custName}" maxlength="122" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">查询方式：</div>
								<div class="input block fl hidden">
									<select Class="ly-bor-none" id="choose" name="queryType" onchange="doChoose(this.value)" style="width: 85%;">
										<option value="-1">请选择</option>
										<option value="1">本地查询</option>
										<option value="2">直连查询</option>
									</select> <input type="hidden" id="queryType" value="${queryType}" />
								</div>
							</div>
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:doQuery();" class="button btn-query">查询</a> <a href="javascript:doClear();" class="button btn-reset">重置</a>
					</div>
				</div>

				<div class="public-main-table hidden abs">
					<div class="ly-cont">
						<div style="overflow-x: auto; overflow-y: auto; height: 100%; width: 100%">
							<table class="t-table" border="0" cellspacing="0" cellpadding="0">
								<thead class="t-thead">
									<tr class="t-tr">
										<th class="t-th">序号</th>
										<th class="t-th">组织机构代码</th>
										<th class="t-th">ECIF客户号</th>
										<th class="t-th">客户名称</th>
										<th class="t-th">创建时间</th>
										<th class="t-th">更新时间</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value="${index+1}" /></td>
												<td class="t-td"><c:out value="${row.custOrganizationcode}" /></td>
												<td class="t-td"><c:out value="${row.custNo}" /></td>
												<td class="t-td"><c:out value="${row.custName}" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.custCreateDate}" idtype="ss" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.custUpdateDate}" idtype="ss" /></td>
											</tr>
										</logic:iterate>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="public-main-footer hidden abs">
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Customer" action="ZXinterface.do?method=customer" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>