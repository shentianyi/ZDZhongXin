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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>解除质押通知</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link type="text/css" rel="stylesheet" href="css/jquery-ui.min.css" />
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" language="javascript">
	function doQuery() {
		document.forms[0].submit();
	}

	function doClear() {
		$("#rpNo").val("");
		$("#rpLoncpname").val("");
		document.forms[0].submit();
	}
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a>
				&gt;
				<a class="crumbs-link" href="#">解除质押通知</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="removeForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="removepledge" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">解除质押通知编号：</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="rpNo" name="removepledge.rpNo" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">借款企业名称：</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="rpLoncpname" name="removepledge.rpLoncpname" value="" />
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
										<th class="t-th">解除质押通知书编号</th>
										<th class="t-th">经办行</th>
										<th class="t-th">出质人名称</th>
										<th class="t-th">借款企业id</th>
										<th class="t-th">借款企业名称</th>
										<th class="t-th">核心企业名称</th>
										<th class="t-th">解除质押日期</th>
										<th class="t-th">出库原因</th>
										<th class="t-th">通知书日期</th>
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
												<td class="t-td"><c:out value="${row.rpNo}" /></td>
												<td class="t-td"><c:out value="${row.rpOperorg}" /></td>
												<td class="t-td"><c:out value="${row.rpPldegeename}" /></td>
												<td class="t-td"><c:out value="${row.rpLoncpid}" /></td>
												<td class="t-td"><c:out value="${row.rpLoncpname}" /></td>
												<td class="t-td"><c:out value="${row.rpCorename}" /></td>
												<td class="t-td"><c:out value="${row.rpRelievepddate}" /></td>
												<td class="t-td"><c:out value="${row.rpContent}" /></td>
												<td class="t-td"><c:out value="${row.rpNoticedate}" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.rpCreatedate}" idtype="ss" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.rpUpdatedate}" idtype="ss" /></td>
												<td class="t-td"><a href="ZXinterface.do?method=removepledgedetail&rdno=<c:out value='${row.rpNo }'/>">详情</a>
												</td>
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
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="RemovePledge"
							action="ZXinterface.do?method=removepledge" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>