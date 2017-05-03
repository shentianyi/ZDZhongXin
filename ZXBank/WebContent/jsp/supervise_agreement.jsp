<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>监管协议查询页面</title>
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/easyui.css" />
<!-- <link type="text/css" rel="stylesheet" href="css/jquery-ui.min.css" /> -->
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
<script>
	$(function(){
		if("${msg}"!=""){
			alert("${msg}");
		}
	});
	function doQuery() {
		if ($('#choose').val() == "2" && $("#loncpid").val() == "") {
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
			$("#loncpid_name").val("");
			$("#loncpid_name").attr("disabled","disabled");
		} else {
			$(".req").css("visibility", "hidden");
			$("#loncpid_name").removeAttr("disabled");
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
				<a class="crumbs-link" href="#">监管协议查询</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="agrForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="agreement" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden"><font class="req" color="#FF0000" style="visibility: hidden;">*</font>ECIF客户号：</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="loncpid" name="agreement.hostno" value="${agreement.hostno}" maxlength="20" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">借款企业名称：</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" type="text" id="loncpid_name" name="agreement.lonnm" value="${agreement.lonnm}" maxlength="120" />
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
										<th class="t-th">借款企业ID</th>
										<th class="t-th">借款企业名称</th>
										<th class="t-th">经办行</th>
										<th class="t-th">系统监管协议编号</th>
										<th class="t-th">纸质监管协议编号</th>
										<th class="t-th">是否开通线上业务</th>
										<th class="t-th">是否允许移库</th>
										<th class="t-th">协议状态</th>
										<th class="t-th">协议起始日</th>
										<th class="t-th">协议到期日</th>
										<th class="t-th">总记录数</th>
										<th class="t-th">创建时间</th>
										<th class="t-th">更新时间</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value="${index+1}" /></td>
												<td class="t-td"><c:out value="${row.hostno}" /></td>
												<td class="t-td"><c:out value="${row.agloncpid}" /></td>
												<td class="t-td"><c:out value="${row.lonnm}" /></td>
												<td class="t-td"><c:out value="${row.operorg}" /></td>
												<td class="t-td"><c:out value="${row.spvagtid}" /></td>
												<td class="t-td"><c:out value="${row.spvagtno}" /></td>
												<td class="t-td"><c:if test="${row.isauth=='00'}">未开通</c:if> <c:if test="${row.isauth=='01'}">开通</c:if>
												</td>
												<td class="t-td"><c:if test="${row.ismv=='00'}">不允许</c:if> <c:if test="${row.ismv=='01'}">允许</c:if>
												</td>
												<td class="t-td"><c:if test="${row.agtstt=='01'}">生效</c:if> <c:if test="${row.agtstt=='02' }">失效</c:if>
												</td>
												<td class="t-td"><c:out value="${row.startdate}" /></td>
												<td class="t-td"><c:out value="${row.enddate}" /></td>
												<td class="t-td"><c:out value="${row.totnum}" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.agcreatedate}" idtype="ss" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.agupdatedate}" idtype="ss" /></td>
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
							<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Agreement"
								action="ZXinterface.do?method=agreement" />
						</c:if>
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>