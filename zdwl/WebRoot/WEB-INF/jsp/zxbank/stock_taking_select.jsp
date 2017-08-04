<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>盘点信息查询</title>
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
		var csLoncpid=$("#checkstock_csLoncpid").val();
		$("#csLoncpid option[value='"+csLoncpid+"']").attr("selected","selected");
		comboboxSetId("#csLoncpid");
	})
	function doQuery() {
		document.forms[0].submit();
	}
	function doClear() {
		$("#csLoncpid").combobox("setValue","");
	}
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="#">中信银行接口</a> &gt; <a class="crumbs-link" href="#">盘点信息查询</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" method="post" styleId="gform" onsubmit="retuen false">
				<input name="method" id="method" type="hidden" value="checkstock" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font class="req" color="#FF0000" style="visibility: hidden;">*</font>借款企业ID：
								</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="csLoncpid" name="checkstock.csLoncpid" style="width: 85%;">
											<option value="">请选择...</option>
											<c:forEach items="${draftOptions}" var="row">
												<option <c:if test="${row.label==checkstock.csLoncpid}"></c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }" /></option>
											</c:forEach>
										</select> <input type="hidden" id="checkstock_csLoncpid" value="${checkstock.csLoncpid}" />
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
										<th class="t-th">序号</th>
										<th class="t-th">借款企业id</th>
										<th class="t-th">借款企业名称</th>
										<th class="t-th">系统监管协议编号</th>
										<th class="t-th">纸质监管协议编号</th>
										<th class="t-th">操作人编号</th>
										<th class="t-th">操作人名称</th>
										<th class="t-th">交易流水号</th>
										<th class="t-th">计划盘库日期</th>
										<th class="t-th">实际盘库日期</th>
										<th class="t-th">差错报告</th>
										<th class="t-th">备注</th>
										<th class="t-th">创建时间</th>
										<th class="t-th">操作</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<logic:iterate name="list" id="row" indexId="index">
										<tr class="t-tr">
											<td class="t-td"><c:out value="${index+1 }" /></td>
											<td class="t-td"><c:out value="${row.csLoncpid }" /></td>
											<td class="t-td"><c:out value="${row.csLoncpname }" /></td>
											<td class="t-td"><c:out value="${row.csProtocolno }" /></td>
											<td class="t-td"><c:out value="${row.csProtocolcode }" /></td>
											<td class="t-td"><c:out value="${row.csUserno }" /></td>
											<td class="t-td"><c:out value="${row.csUsername }" /></td>
											<td class="t-td"><c:out value="${row.csTradeid }" /></td>
											<td class="t-td"><c:out value="${row.csPlandate }" /></td>
											<td class="t-td"><c:out value="${row.csFactdate }" /></td>
											<td class="t-td"><c:out value="${row.csErrorreport }" /></td>
											<td class="t-td"><c:out value="${row.csRemark }" /></td>
											<td class="t-td"><select:timestamp timestamp="${row.csCreatedate}" idtype="ss" /></td>
											<td class="t-td"><a href="ZXinterface.do?method=checkstockDetail&csid=${row.csId}">详情</a></td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="public-main-footer hidden abs">
					<div class="public-main-footer-pagin fr">
						<c:if test="${not empty list }">
							<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Checkstock" action="ZXinterface.do?method=checkstock" />
						</c:if>
					</div>
				</div>

			</html:form>
		</div>
	</div>
</body>
</html>