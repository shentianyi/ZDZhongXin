<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>质物入库申请</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<link type="text/css" rel="stylesheet" href="../easyui/themes/bootstrap/easyui.css">
<link type="text/css" rel="stylesheet" href="../css/zxcss.css" />
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../easyui/jquery.easyui.min.js"></script>
<script src="../js/thumbpage/thumbpage.js"></script>
<script src="../js/zxjs.js"></script>
<script>
	$(function(){
		var msg = '${message}';
		if (msg != null && msg != "" && msg != "null") {
			alert(msg);
		}
		var gaLonentno=$("#gager_gaLonentno").val();
		$("#gaLonentno option[value='"+gaLonentno+"']").attr("selected","selected");
		comboboxSetId("#gaLonentno");
	})
	function doQuery() {
		document.forms[0].submit();
	}

	function doClear() {
		$("#gaLonentno").combobox("setValue","");
	}
</script>

</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="#">中信银行接口</a> &gt; <a class="crumbs-link" href="#">质物入库申请信息查询</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="gForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="gager" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font class="req" color="#FF0000" style="visibility: hidden;">*</font>借款企业ID：
								</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="gaLonentno" name="gager.gaLonentno" style="width: 85%;">
											<option value="">请选择...</option>
											<c:forEach items="${draftOptions}" var="row">
												<option <c:if test="${row.label==gager.gaLonentno}"></c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }" /></option>
											</c:forEach>
										</select> <input type="hidden" id="gager_gaLonentno" value="${gager.gaLonentno}" />
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="ly-button-w">
						<a href="javascript:doQuery();" class="button btn-query">查询</a>
						<!-- <a href="javascript:doClear();" class="button btn-reset">重置</a> -->
					</div>
				</div>
				<div class="public-main-table hidden abs">
					<div class="ly-cont">
						<div style="overflow-x: auto; overflow-y: auto; height: 100%; width: 100%">
							<table class="t-table" border="0" cellspacing="0" cellpadding="0">
								<thead class="t-thead">
									<tr class="t-tr">
									<tr class="t-tr">
										<th class="t-th">序号</th>
										<th class="t-th">借款企业ID</th>
										<th class="t-th">借款企业名称</th>
										<th class="t-th">操作人名称</th>
										<th class="t-th">交易流水号</th>
										<th class="t-th">纸质担保合同编号</th>
										<th class="t-th">动产质押合同编号</th>
										<th class="t-th">质物监管确认书编号</th>
										<th class="t-th">仓库代码</th>
										<th class="t-th">状态</th>
										<th class="t-th">总记录数</th>
										<th class="t-th">备注</th>
										<th class="t-th">申请时间</th>
										<th class="t-th">操作</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value="${index+1}" /></td>
												<td class="t-td"><c:out value='${row.gaLonentno }' /></td>
												<td class="t-td"><c:out value='${row.gaLonentname }' /></td>
												<td class="t-td"><c:out value='${row.gaOprtname }' /></td>
												<td class="t-td"><c:out value='${row.gaOrderno }' /></td>
												<td class="t-td"><c:out value='${row.gaPcgrtntno }' /></td>
												<td class="t-td"><c:out value='${row.gaCmgrtcntno }' /></td>
												<td class="t-td"><c:out value='${row.gaConfirmno }' /></td>
												<td class="t-td"><c:out value='${row.gawhCode }' /></td>
												<td class="t-td">${row.gaState==1?'申请成功':'申请失败'}</td>
												<td class="t-td"><c:out value='${row.gaCount }' /></td>
												<td class="t-td"><c:out value='${row.gaRemark }' /></td>
												<td class="t-td"><select:timestamp timestamp="${row.gaCreatedate}" idtype="ss" /></td>
												<td class="t-td"><a href="ZXinterface.do?method=commodity&gaId=${row.gaId}">详情</a></td>
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
						<c:if test="${not empty list }">
							<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Gager" action="ZXinterface.do?method=gager" />
						</c:if>
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>