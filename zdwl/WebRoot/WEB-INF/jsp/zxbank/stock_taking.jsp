<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="c-1_0-rt.tld" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<%@page isELIgnored="false"%>

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>盘点信息提交</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<link type="text/css" rel="stylesheet"
	href="../easyui/themes/bootstrap/easyui.css">
<link type="text/css" rel="stylesheet" href="../css/zxcss.css" />
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../easyui/jquery.easyui.min.js"></script>
<script src="../js/thumbpage/thumbpage.js"></script>
<style type="text/css">
.textbox {
	margin-top: 5px;
	margin-left: 10%;
}
</style>
<script type="text/javascript">
	$(function() {
		//加载插件
		$('#csPlandate').datebox({
			editable : false
		});
		$('#csFactdate').datebox({
			editable : false
		});

		if ('${message}' != "")
			alert('${message}');

	});

	/*重置*/
	function doClear() {
		$(":text").val("");
		$('#csPlandate').datebox('clear');
		$('#csFactdate').datebox('clear');
	}

	function doFile() {
		$("#importFile").click();
	}

	/*提交*/
	function doQuery(method) {
		$("#method").val(method);
		document.forms[0].submit();
	}
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="#">中信银行接口</a> &gt; <a
					class="crumbs-link" href="#">盘点信息提交</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="stockForm" method="post"
				onsubmit="return false" enctype="multipart/form-data">
				<input name="method" id="method" type="hidden" value="" />
				<div class="public-main-input ly-col-4 hidden abs"
					style="height: 200px;">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>借款企业ID：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="csLoncpid"
										name="checkstock.csLoncpid" value="${checkstock.csLoncpid}"
										maxlength="10" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>借款企业名称：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="" name="checkstock."
										value="${checkstock.csLoncpid}" maxlength="10" />
								</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>操作人员编号：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="csUserno"
										name="checkstock.csUserno" value="${checkstock.csUserno}"
										maxlength="20" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>操作人名称：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="csUsername"
										name="checkstock.csUsername" value="${checkstock.csUsername}"
										maxlength="30" />
								</div>
							</div>
						</div>

						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>系统监管协议编号：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="csProtocolno"
										name="checkstock.csProtocolno"
										value="${checkstock.csProtocolno}" maxlength="20" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>纸质监管协议编号：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="csProtocolcode"
										name="checkstock.csProtocolcode"
										value="${checkstock.csProtocolcode}" maxlength="20" />
								</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>计划盘库日期：
								</div>
								<div class="input block fl hidden">
									<input type="text" id="csPlandate" name="checkstock.csPlandate"
										value="${checkstock.csPlandate}" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>实际盘库日期：
								</div>
								<div class="input block fl hidden">
									<input type="text" id="csFactdate" name="checkstock.csFactdate"
										value="${checkstock.csFactdate}" />
								</div>
							</div>
						</div>

						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>交易流水号：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="csTradeid"
										name="checkstock.csTradeid" value="${checkstock.csTradeid}"
										maxlength="20" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>差错报告：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="csErrorreport"
										name="checkstock.csErrorreport"
										value="${checkstock.csErrorreport}" maxlength="1000" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">备注：</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="csRemark"
										name="checkstock.csRemark" value="${checkstock.csRemark}"
										maxlength="200" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">选择文件：</div>
								<div class="input block fl hidden">
									<button style="margin: 6px 0 0 10%;" onclick="doFile()">请选择文件</button>
									<span>${empty fileName?'未选择文件':fileName }</span> <input
										id="fileName" name="fileName" type="hidden"
										value="${fileName}"> <input type="file"
										id="importFile" name="importFile" accept=".xls" />
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">文件模板：</div>
								<div class="input block fl hidden">
									<a style="color: blue;" href="importTemplate/panku.xls">下载</a>
								</div>
							</div>
						</div>
					</div>
					<div style="margin-top: 10px" class="ly-button-w">
						<a href="javascript:doQuery('stockApp');" class="button btn">提交</a>
						<a href="javascript:doClear();" class="button btn-reset">重置</a> <a
							href="javascript:doQuery('perstock');" class="button btn">导入</a>
					</div>
				</div>
				<div class="public-main-table hidden abs" style="margin-top: 15px;">
					<div class="ly-cont">
						<div
							style="overflow-x: auto; overflow-y: auto; height: 100%; width: 100%">
							<table class="t-table" border="0" cellspacing="0" cellpadding="0">
								<thead class="t-thead">
									<tr class="t-tr">
										<th class="t-th">序号</th>
										<th class="t-th">仓库级别</th>
										<th class="t-th">仓库代码</th>
										<th class="t-th">仓库名称</th>
										<th class="t-th">仓库地址</th>
										<th class="t-th">盘点数量</th>
										<th class="t-th">车架号</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value='${index+1 }' /></td>
												<td class="t-td"><c:out value='${row.whlevel }' /></td>
												<td class="t-td"><c:out value='${row.whcode }' /></td>
												<td class="t-td"><c:out value='${row.whname }' /></td>
												<td class="t-td"><c:out value='${row.whaddr }' /></td>
												<td class="t-td"><c:out value='${row.num }' /></td>
												<td class="t-td"><c:out value='${row.vin }' /></td>
											</tr>
										</logic:iterate>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>

			</html:form>
		</div>
	</div>
</body>
</html>
