<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link href="css/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/icon.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/tablecs.css">
<script src="js/jquery.min.js"></script>
<script src="js/jquery.easyui.min.js"></script>
<script src="js/easyui-lang-zh_CN.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
<script src="js/calendar.js"></script>
<style type="text/css">
.title td {
	padding: 0 15px 0 15px;
}

.textbox {
	margin-top: 5px;
	margin-left: 10%;
}

.gylztt {
	width: 48%;
	margin-top: 5px;
	margin-left: 11%;
	text-indent: 5px;
	padding: 1px 0 2px 0;
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

	});

	/*重置*/
	function doClear() {

	}
	
	/*重置*/
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
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a>
				>
				<a class="crumbs-link" href="#">盘库信息提交</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/stock.do" styleId="gForm" method="post" onsubmit="return false" enctype="multipart/form-data">
			<input name="method" id="method" type="hidden" value="" />
			<div class="public-main-input ly-col-3 hidden abs" style="height: 165px;">
				<div class="ly-input-w">
					<div class="ly-row clearfix">
						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>借款企业ID：
							</div>
							<div class="input block fl hidden">
								<input class="ly-bor-none" type="text" id="csLoncpid" name="checkstock.csLoncpid" value="${checkstock.csLoncpid}" />
							</div>
						</div>

						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>系统监管协议编号：
							</div>
							<div class="input block fl hidden">
								<input class="ly-bor-none" type="text" id="csProtocolno" name="checkstock.csProtocolno" value="${checkstock.csProtocolno}" />
							</div>
						</div>

						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>操作人员编号：
							</div>
							<div class="input block fl hidden">
								<input class="ly-bor-none" type="text" id="csUserno" name="checkstock.csUserno" value="${checkstock.csUserno}" />
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>操作人名称：
							</div>
							<div class="input block fl hidden">
								<input class="ly-bor-none" type="text" id="csUsername" name="checkstock.csUsername" value="${checkstock.csUsername}" />
							</div>
						</div>
					</div>

					<div class="ly-row clearfix">
						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>交易流水号：
							</div>
							<div class="input block fl hidden">
								<input class="ly-bor-none" type="text" id="csTradeid" name="checkstock.csTradeid" value="${checkstock.csTradeid}" />
							</div>
						</div>

						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>纸质监管协议编号：
							</div>
							<div class="input block fl hidden">
								<input class="ly-bor-none" type="text" id="csProtocolcode" name="checkstock.csProtocolcode" value="${checkstock.csProtocolcode}" />
							</div>
						</div>

						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>计划盘库日期：
							</div>
							<div class="input block fl hidden">
								<input type="text" id="csPlandate" name="checkstock.csPlandate" value="${checkstock.csPlandate}" />
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>实际盘库日期：
							</div>
							<div class="input block fl hidden">
								<input type="text" id="csFactdate" name="checkstock.csFactdate" value="${checkstock.csFactdate}" />
							</div>
						</div>
					</div>
					<div class="ly-row clearfix">
						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>差错报告：
							</div>
							<div class="input block fl hidden">
								<input class="ly-bor-none" type="text" id="csErrorreport" name="checkstock.csErrorreport" value="${checkstock.csErrorreport}" />
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">备注：</div>
							<div class="input block fl hidden">
								<input class="ly-bor-none" type="text" id="csRemark" name="checkstock.csRemark" value="${checkstock.csRemark}" />
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">选择文件：</div>
							<div class="input block fl hidden">
								<button style="margin:6px 0 0 10%;" onclick="doFile()" >请选择文件</button>
	                    		<span>${empty fileName?'未选择文件':fileName }</span>
	                    		<input id="fileName" name="fileName" type="hidden" value="${fileName}">
	                    		<input type="file" id="importFile" name="importFile" accept=".xls" />
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">文件模板：</div>
							<div class="input block fl hidden">
								<a style="color: blue;" href="importTemplate/panku.xls">下载</a>
							</div>
						</div>
					</div>
				</div>

				<div style="margin-top: 25px" class="ly-button-w">
					<a href="javascript:doQuery('perstock');" class="button btn">提交</a>
					<a href="javascript:doClear();" class="button btn-reset">重置</a>
					<a href="javascript:doQuery('perstock');" class="button btn">导入</a>
				</div>
			</div>
			<div class="public-main-table hidden abs" style="top: 175px;">
				<div class="ly-cont">
					<div style="overflow-x: auto; overflow-y: auto; height: 100%; width: 100%">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<th class="t-th">序号</th>
									<!-- <th class="t-th">监管仓库代码</th>
									<th class="t-th">商品代码</th>
									<th class="t-th">盘库商品数量</th>
									<th class="t-th">动产质押担保合同编号</th>
									<th class="t-th">车架号</th>
									<th class="t-th">仓库级别</th>
									<th class="t-th">仓库代码</th>
									<th class="t-th">仓库地址</th>
									<th class="t-th">车辆数量</th> -->
									<th class="t-th">仓库级别</th>
									<th class="t-th">仓库代码</th>
									<th class="t-th">仓库地址</th>
									<th class="t-th">商品代码</th>
									<th class="t-th">盘库商品数量</th>
									<th class="t-th">动产质押担保合同编号</th>
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
											<td class="t-td"><c:out value='${row.whaddr }' /></td>
											<td class="t-td"><c:out value='${row.cmcode }' /></td>
											<td class="t-td"><c:out value='${row.num }' /></td>
											<td class="t-td"><c:out value='${row.cmgrtcntno }' /></td>
											<td class="t-td"><c:out value='${row.vin }' /></td>
										</tr>
									</logic:iterate>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<!-- <div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				共&nbsp;3&nbsp;条记录&nbsp;第&nbsp;1&nbsp;页  跳转至<input type="text" value="1" style="width:30px;height:28px;" />
				<button >第一页</button>&nbsp;<button >上一页</button>&nbsp;<button >下一页</button>&nbsp;<button >最末页</button>
			</div>
		</div> -->

		</html:form>
		</div>
	</div>
</body>
</html>
