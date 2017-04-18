<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link type="text/css" rel="stylesheet" href="css/jquery-ui.min.css" />
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
<script>
	function doQuery() {
		if ($('#choose').val() == "2" && $("#custNo").val() == "") {
			alert("ECIF客户号不能为空");
			return;
		}
		document.forms[0].submit();
	}
	function doClear() {
		$(":text").val("");
	}

	function doChoose(val) {
		if (val == "2") {
			$(".req").css("visibility", "visible");
		} else {
			$(".req").css("visibility", "hidden");
		}
	}
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a>
				&gt;
				<a class="crumbs-link" href="#">仓库信息查询</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="whForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="warehouse" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font class="req" color="#FF0000" style="visibility: hidden;">*</font>ECIF客户号：
								</div>
								<div class="input block fl hidden">
<<<<<<< Updated upstream
									<input class="ly-bor-none" type="text" id="custNo" name="warehouse.custNo" value="${warehouse.custNo}" />
=======
									<input class="ly-bor-none" type="text" id="custNo" name="warehouse.custNo" value="${cusNo}" />
>>>>>>> Stashed changes
								</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">仓库名称：</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="whName" name="warehouse.whName" value="${warehouse.whName}" />
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
										<th class="t-th">ECIF客户号</th>
										<th class="t-th">借款企业名称</th>
										<th class="t-th">仓库名称</th>
										<th class="t-th">仓库代码</th>
										<th class="t-th">仓库级别</th>
										<th class="t-th">经办行</th>
										<th class="t-th">地址</th>
										<th class="t-th">电话</th>
										<th class="t-th">创建时间</th>
										<th class="t-th">更新时间</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value="${index+1}" /></td>
												<td class="t-td"><c:out value="${row.custNo}" /></td>
												<td class="t-td"><c:out value="${row.loncpname}" /></td>
												<td class="t-td"><c:out value="${row.whName}" /></td>
												<td class="t-td"><c:out value="${row.whCode}" /></td>
												<td class="t-td"><c:out value="${row.whLevel==1?'一级仓库':'二级仓库'}" /></td>
												<td class="t-td"><c:out value="${row.whOperorg}" /></td>
												<td class="t-td"><c:out value="${row.whAddress}" /></td>
												<td class="t-td"><c:out value="${row.phone}" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.createDate}" idtype="ss" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.updateDate}" idtype="ss" /></td>
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
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Warehouse" action="ZXinterface.do?method=warehouse" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>