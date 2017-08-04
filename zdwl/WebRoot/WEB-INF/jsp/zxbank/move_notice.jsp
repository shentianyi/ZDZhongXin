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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<link type="text/css" rel="stylesheet" href="../easyui/themes/bootstrap/easyui.css">
<link type="text/css" rel="stylesheet" href="../css/zxcss.css" />
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../js/jquery.divscroll.js"></script>
<script src="../easyui/jquery.easyui.min.js"></script>
<script src="../js/thumbpage/thumbpage.js"></script>
<script src="../js/zxjs.js"></script>
<script>
	$(function(){
		var msg = '${message}';
		if (msg != null && msg != "" && msg != "null") {
			alert(msg);
		}
		var mnNo=$("#movenotice_mnNo").val();
		$("#mnNo option[value='"+mnNo+"']").attr("selected","selected");
		comboboxSetId("#mnNo");
	})
	function doQuery() {
		document.forms[0].submit();
	}

	function doClear() {
		$(":text").val("");
		$("#mnNo").combobox("setValue","");
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a> &gt; <a class="crumbs-link" href="#">移库通知</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="mnForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="movenotice" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">

							<div class="ly-col fl">
								<div class="label block fl hidden">移库通知书编号：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="mnNo" name="movenotice.mnNo" style="width: 85%;">
											<option value="">请选择...</option>
											<c:forEach items="${draftOptions}" var="row">
												<option <c:if test="${row.label==movenotice.mnNo}"></c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }" /></option>
											</c:forEach>
										</select> <input type="hidden" id="movenotice_mnNo" value="${movenotice.mnNo}" />
									</div>
								</div>
							</div>





							<div class="ly-col fl">
								<div class="label block fl hidden">借款企业名称：</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="mnLoncpname" name="movenotice.mnLoncpname" value="${movenotice.mnLoncpname }" maxlength="122" />
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
										<th class="t-th">移库通知书编号</th>
										<th class="t-th">借款企业名称</th>
										<th class="t-th">监管企业名称</th>
										<th class="t-th">移库申请日期</th>
										<th class="t-th">通知书日期</th>
										<th class="t-th">经办行</th>
										<th class="t-th">创建时间</th>
										<th class="t-th">更新时间</th>
										<th class="t-th">操作</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value="${index+1}" /></td>
												<td class="t-td"><c:out value="${row.mnNo}" /></td>
												<td class="t-td"><c:out value="${row.mnLoncpname}" /></td>
												<td class="t-td"><c:out value="${row.mnSupervisename}" /></td>
												<td class="t-td"><c:out value="${row.mnMovedate}" /></td>
												<td class="t-td"><c:out value="${row.mnNoticedate}" /></td>
												<td class="t-td"><c:out value="${row.mnOperorg}" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.mnCreatedate}" idtype="ss" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.mnUpdatedate}" idtype="ss" /></td>
												<td class="t-td"><a href="ZXinterface.do?method=movedetail&mdno=<c:out value='${row.mnNo }'/>">详情</a></td>
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
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="MoveNotice" action="ZXinterface.do?method=movenotice" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>