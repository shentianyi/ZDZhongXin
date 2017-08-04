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
<title>质物入库申请</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<link type="text/css" rel="stylesheet"
	href="../easyui/themes/bootstrap/easyui.css">
<link type="text/css" rel="stylesheet" href="../css/zxcss.css" />
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../easyui/jquery.easyui.min.js"></script>
<script src="../js/thumbpage/thumbpage.js"></script>
<script>
	$(function() {
		var msg = '${message}';
		if (msg != null && msg != "" && msg != "null") {
			alert(msg);
		}
	});

	function doCommit(method) {
		$("#method").val(method);
		
		if(method=='gagerApp'){
			if($('#gaLonentno').val()==""){
				alert("借款企业ID不能为空");
				return;
			}
			if($('#gaOprtname').val()==""){
				alert("操作人名称不能为空");
				return;
			}
			if($('#gaOrderno').val()==""){
				alert("交易流水号不能为空");
				return;
			}
			if($('#gaCount').val()==""){
				alert("总记录数不能为空");
				return;
			}
		}
		document.forms[0].submit();
	}

	function doFile() {
		$("#importFile").click();
	}
	
	/*重置*/
	function doClear() {
		$(":text").val("");
	}
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="#">中信银行接口</a> &gt; <a
					class="crumbs-link" href="#">质物入库申请</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="gForm" method="post"
				onsubmit="return false" enctype="multipart/form-data">
				<input name="method" id="method" type="hidden" value="" />
				<div class="public-main-input ly-col-3 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>借款企业ID：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" id="gaLonentno"
										name="gager.gaLonentno" type="text"
										value="${gager.gaLonentno}" maxlength="10" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>借款企业名称：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" id="gaLonentname"
										name="gager.gaLonentname" type="text"
										value="${gager.gaLonentname}" maxlength="20" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>操作人名称：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" id="gaOprtname"
										name="gager.gaOprtname" type="text"
										value="${gager.gaOprtname}" maxlength="30" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>仓库代码：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" id="gawhCode" name="gager.gawhCode"
										type="text" value="${gager.gawhCode}" maxlength="20" />
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>交易流水号：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" id="gaOrderno"
										name="gager.gaOrderno" type="text" value="${gager.gaOrderno}"
										maxlength="20" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>质物监管确认书编号：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" id="gaConfirmno"
										name="gager.gaConfirmno" type="text"
										value="${gager.gaConfirmno }" maxlength="20" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">纸质担保合同编号：</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" id="gaPcgrtntno"
										name="gager.gaPcgrtntno" type="text"
										value="${gager.gaPcgrtntno}" maxlength="100" />
								</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">动产质押担保合同编号：</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" id="gaCmgrtcntno"
										name="gager.gaCmgrtcntno" type="text"
										value="${gager.gaCmgrtcntno}" maxlength="30" />
								</div>
							</div>

						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font color="#FF0000">*</font>总记录数：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" id="gaCount" name="gager.gaCount"
										type="text" value="${gager.gaCount==0?'':gager.gaCount }" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">导入文件：</div>
								<div class="input block fl hidden">
									<button style="margin: 6px 0 0 10%;" onclick="doFile()">请选择文件</button>
									<span>${empty fileName?'未选择文件':fileName }</span> <input
										id="fileName" name="fileName" type="hidden"
										value="${fileName}"> <input type="file"
										id="importFile" name="importFile" accept=".xls"
										onchange="doCommit('pergager')" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">文件模板：</div>
								<div class="input block fl hidden">
									<a style="color: blue;"
										href="../system/importTemplate/zhiwuruku.xls">下载</a>
								</div>
							</div>
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:doCommit('gagerApp');"
							class="button btn-query">导入</a> <a href="javascript:doClear();"
							class="button btn-query">重置</a>
					</div>
				</div>
				<div class="public-main-table hidden abs">
					<div class="ly-cont">
						<div
							style="overflow-x: auto; overflow-y: auto; height: 100%; width: 100%">
							<table class="t-table" border="0" cellspacing="0" cellpadding="0">
								<thead class="t-thead">
									<tr class="t-tr">
										<th class="t-th">序号</th>
										<th class="t-th">商品代码</th>
										<th class="t-th">入库数量</th>
										<th class="t-th">入库单价</th>
										<th class="t-th">车架号</th>
										<th class="t-th">合格证编号</th>
										<th class="t-th">车价</th>
										<th class="t-th">融资编号</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value='${index+1 }' /></td>
												<td class="t-td"><c:out value='${row.cmCmdcode }' /></td>
												<td class="t-td"><c:out value='${row.cmStknum }' /></td>
												<td class="t-td"><c:out value='${row.cmIstkprc }' /></td>
												<td class="t-td"><c:out value='${row.cmVin }' /></td>
												<td class="t-td"><c:out value='${row.cmHgzno }' /></td>
												<td class="t-td"><c:out value='${row.cmCarprice }' /></td>
												<td class="t-td"><c:out value='${row.cmLoancode }' /></td>
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