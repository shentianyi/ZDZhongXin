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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link type="text/css" rel="stylesheet" href="css/easyui.css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery.easyui.min.js"></script>
<script src="js/easyui-lang-zh_CN.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
<script src="js/calendar.js"></script>

<script type="text/javascript">
	function doQuery() {
		if ($("#choose").val() == "2") {
			if ($("#fgLonentNo").val() == "") {
				alert("借款企业ID不能为空");
				return false;
			}
			if (!$("#fgStDateStart").datebox('getValue')) {
				alert("开始时间不能为空");
				return false;
			}
		}

		var startDate = new Date($("#fgStDateStart").datebox('getValue'));
		var endDate = new Date($("#fgStDateEnd").datebox('getValue'));
		var nextYear = startDate.getTime() + (1000 * 60 * 60 * 24 * 365);
		if (new Date(nextYear) < new Date()) {
			alert("开始日期+365天小于等于当前日期");
			return false;
		}

		if (startDate > endDate) {
			alert("开始时间不能大于结束时间");
			return false;
		}
		var day = Math.abs((startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000));
		if (day > 90) {
			alert("开始时间和结束时间不能相差超过90天");
			return false;
		}

		$("[name='financingVO.fgStDateStart']").val($("[name='financingVO.fgStDateStart']").val().replace(/-/g, ""));
		$("[name='financingVO.fgStDateEnd']").val($("[name='financingVO.fgStDateEnd']").val().replace(/-/g, ""));

		document.forms[0].submit();
	}

	function doClear() {
		$("#fgLonentNo").val("");
		$('#fgStDateStart').datebox('clear');
		$('#fgStDateEnd').datebox('clear');
	}

	function doChoose(val) {
		if (val == "2") {
			$(".req").css("visibility", "visible");
		} else {
			$(".req").css("visibility", "hidden");
		}
	}

	$(function() {
		//加载插件
		$('#fgStDateStart').datebox({
			editable : false
		});
		$('#fgStDateEnd').datebox({
			editable : false
		});

	});
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a>
				&gt;
				<a class="crumbs-link" href="#">借款企业融资信息查询</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="fiForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="financing" />
				<div class="public-main-input ly-col-1 abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font class="req" color="#FF0000" style="visibility: hidden;">*</font>借款企业ID：
								</div>
								<div class="input block fl hidden">
									<input class="ly-bor-none" id="fgLonentNo" name="financingVO.fgLonentNo" type="text" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font class="req" color="#FF0000" style="visibility: hidden;">*</font>融资起始日-开始：
								</div>
								<div class="input block fl hidden">
									<input id="fgStDateStart" name="financingVO.fgStDateStart" type="text" value="" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">融资起始日-结束：</div>
								<div class="input block fl hidden">
									<input id="fgStDateEnd" name="financingVO.fgStDateEnd" type="text" value="" />
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
										<th class="t-th">借款企业ID</th>
										<th class="t-th">借款企业名称</th>
										<th class="t-th">融资编号</th>
										<th class="t-th">放款批次号</th>
										<th class="t-th">融资金额</th>
										<th class="t-th">保证金比例</th>
										<th class="t-th">自有资金比例</th>
										<th class="t-th">首付保证金可提货比例</th>
										<th class="t-th">融资起始日</th>
										<th class="t-th">融资到期日</th>
										<th class="t-th">授信产品</th>
										<th class="t-th">经办行</th>
										<th class="t-th">业务模式</th>
										<th class="t-th">创建时间</th>
										<th class="t-th">更新时间</th>

									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<logic:iterate name="list" id="row" indexId="index">
										<tr class="t-tr">
											<td class="t-td"><c:out value="${index+1}" /></td>
											<td class="t-td"><c:out value="${row.fgLonentNo}" /></td>
											<td class="t-td"><c:out value="${row.loncpname}" /></td>
											<td class="t-td"><c:out value="${row.fgLoanCode}" /></td>
											<td class="t-td"><c:out value="${row.fgScftxNo}" /></td>
											<td class="t-td"><c:out value="${row.fgLoanAmt}" /></td>
											<td class="t-td"><c:out value="${row.fgBailRat}%" /></td>
											<td class="t-td"><c:out value="${row.fgSlfcap}%" /></td>
											<td class="t-td"><c:out value="${row.fgFstblRat}%" /></td>
											<td class="t-td"><c:out value="${row.fgStDate}" /></td>
											<td class="t-td"><c:out value="${row.fgEndDate}" /></td>
											<td class="t-td"><c:if test="${row.fgProcrt==1}">
										银行承兑汇票
									</c:if></td>
											<td class="t-td"><c:out value="${row.fgOperOrg}" /></td>
											<td class="t-td"><c:if test="${row.fgBizMod==11}">
										汽车金融先票后货三方
									</c:if></td>
											<td class="t-td"><select:timestamp timestamp="${row.fgCreateDate}" idtype="ss" /></td>
											<td class="t-td"><select:timestamp timestamp="${row.fgUpdateDate}" idtype="ss" /></td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="public-main-footer hidden abs">
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Financing"
							action="ZXinterface.do?method=financing" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>