<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="css/jquery-ui.min.css">
<link rel="stylesheet" href="css/tablecs.css">
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
<script>
	function doQuery() {
		document.forms[0].submit();
	}
	function doClear() {

		$("custNo").value = "";
		getElement("pledgeName").value = "";
	}
	$(function() {
		var availableTags = [ "41234", "12312", "12341", "5123412", "234125",
				"51234", "234534" ];
		$("#loncpid").autocomplete({
			source : availableTags
		});
	});

	$(function() {
		var availableTags = [ "ActionScript", "AppleScript", "Asp", "BASIC",
				"C", "C++" ];
		$("#loncp_name").autocomplete({
			source : availableTags
		});
	});
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a> > <a
					class="crumbs-link" href="#">盘库信息查询</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/checkstock.do" method="post" styleId="gform"
				onsubmit="retuen false">
				<input name="method" id="method" type="hidden" value="checkstock" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">借款企业ID：</div>
								<div class="input block fl hidden">
									<input id="checkstock.csLoncpid" type="text" name="checkstock.csLoncpid"
										style="display: block; width: 80%; margin-left: 10%; margin-top: 5px; border: 1px solid #eee; border-radius: 4px; outline: none; height: 24px;" value="${csLoncpid}" />
								</div>
							</div>
							<!-- <div class="ly-col fl">
								<div class="label block fl hidden">借款企业名称：</div>
								<div class="input block fl hidden">
									<input id="loncp_name" type="text"
										style="display: block; width: 80%; margin-left: 10%; margin-top: 5px; border: 1px solid #eee; border-radius: 4px; outline: none; height: 24px;" />
								</div>
							</div> -->
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:doQuery();" class="button btn-query">查询</a>
						<a href="javascript:doClear();" class="button btn-reset">重置</a>
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
										<th class="t-th">借款企业id</th>
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
											<td class="t-td"><c:out value="${row.csProtocolno }" /></td>
											<td class="t-td"><c:out value="${row.csProtocolcode }" /></td>
											<td class="t-td"><c:out value="${row.csUserno }" /></td>
											<td class="t-td"><c:out value="${row.csUsername }" /></td>
											<td class="t-td"><c:out value="${row.csTradeid }" /></td>
											<td class="t-td"><c:out value="${row.csPlandate }" /></td>
											<td class="t-td"><c:out value="${row.csFactdate }" /></td>
											<td class="t-td"><c:out value="${row.csErrorreport }" /></td>
											<td class="t-td"><c:out value="${row.csRemark }" /></td>
											<td class="t-td">
												<select:timestamp timestamp="${row.csCreatedate}" idtype="ss"/>
											</td>
											<td class="t-td"><a href="checkstockDetail.do?method=checkstockDetail&csid=${row.csId}&loncpid=${row.csLoncpid}">详情</a></td>
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
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Checkstock" action="checkstock.do?method=checkstock" />
						</c:if>
					</div>
				</div>
				
			</html:form>
		</div>
	</div>
</body>
</html>