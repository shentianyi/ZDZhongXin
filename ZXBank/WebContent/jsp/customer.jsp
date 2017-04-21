<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link type="text/css" rel="stylesheet" href="css/easyui.css">
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery.easyui.min.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
<style type="text/css">
.textbox {
	margin-top: 5px;
	margin-left: 10%;
}
</style>
<script>
	/* $(function() {
		$("#custOrganizationcode").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "ZXinterface.do?method=disajax",
					dataType : "json",
					data : {
						"custinput" : request.term
					},
					success : function(data) {
						response($.map(data, function(item) {
							return {
								label : item.organizationcode
							};
						}));
					}
				});
			},
			minLength : 3
		});
	}); */

	function doChoose(val) {
		if (val == "2") {
			$(".req").css("visibility", "visible");
			$("#custName").val("");
			$("#custName").attr("disabled","disabled");
		} else {
			$(".req").css("visibility", "hidden");
			$("#custName").removeAttr("disabled");
		}
	}

	function doQuery() {
		
		if ($('#choose').val()=="2" && $("#custOrganizationcode").val()=="") {
			alert("组织机构代码不能为空");
			return;
		}
		
		document.forms[0].submit();
	}

	function doClear() {
		$(":text").val("");
	}
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a>
				&gt;
				<a class="crumbs-link" href="#">客户信息查询</a>
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
									<font class="req" color="#FF0000" style="visibility: hidden;">*</font>组织机构代码：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="custOrganizationcode" name="customer.custOrganizationcode" value="${customer.custOrganizationcode}" maxlength="10" />
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
									<select class="ly-bor-none" id="choose" name="queryType" onchange="doChoose(this.value)">
										<option value="0">请选择</option>
										<option value="1">本地查询</option>
										<option value="2">远程查询</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:doQuery();" class="button btn-query">查询</a>
						<a href="javascript:doClear();" class="button btn-reset">重置</a>
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