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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<link type="text/css" rel="stylesheet"
	href="../easyui/themes/bootstrap/easyui.css">
<link type="text/css" rel="stylesheet" href="../css/zxcss.css" />
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../easyui/jquery.easyui.min.js"></script>
<script src="../js/thumbpage/thumbpage.js"></script>
<script src="../js/zxjs.js"></script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="#">中信银行接口</a> &gt; <a
					class="crumbs-link" href="#">详情</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="npdForm" method="post"
				onsubmit="return false">
				<input name="method" id="method" type="hidden"
					value="noticePushDetail" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">通知编号:</div>
								<div class="input block fl hidden">
									<c:out value="${ntcno}" />
								</div>
							</div>
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:history.go(-1);" class="button btn-query">返回</a>
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
										<th class="t-th">中信银行ECIF客户号</th>
										<th class="t-th">经办行</th>
										<th class="t-th">车架号</th>
										<th class="t-th">融资编号</th>
										<th class="t-th">变更状态</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<logic:iterate name="list" id="row" indexId="index">
										<tr class="t-tr">
											<td class="t-td"><c:out value="${index+1}" /></td>
											<td class="t-td"><c:out value="${row.pndecifcode}" /></td>
											<td class="t-td"><c:out value="${row.pndoperorg}" /></td>
											<td class="t-td"><c:out value="${row.pndvin}" /></td>
											<td class="t-td"><c:out value="${row.pndloancode}" /></td>
											<td class="t-td"><c:if test="${row.state=='1'}">变更成功</c:if>
												<c:if test="${row.state=='2'}">变更失败</c:if></td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="public-main-footer hidden abs">
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools
							className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>"
							tableName="PushNoticeDetail"
							action="ZXinterface.do?method=noticePushDetail" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>