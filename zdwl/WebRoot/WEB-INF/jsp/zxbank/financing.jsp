<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="c-1_0-rt.tld" prefix="c"%>
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

<%@page isELIgnored="false"%>

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<link type="text/css" rel="stylesheet" href="../easyui/themes/bootstrap/easyui.css">
<link type="text/css" rel="stylesheet" href="../css/zxcss.css" />

<link href="../easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="../easyui/themes/icon.css" rel="stylesheet" type="text/css" />

<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../easyui/jquery.min.js"></script>
<script src="../easyui/jquery.easyui.min.js"></script>
<script src="../easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="../js/thumbpage/thumbpage.js"></script>
<script src="../js/calendar.js"></script>
<script src="../js/zxjs.js"></script>

<script src="../js/common.js"></script>
<script type="text/javascript" src="../js/jquery.divscroll.js"></script>

<style type="text/css">
.textbox {
	margin-top: 5px;
	margin-left: 10%;
}
</style>
<script type="text/javascript">
	$(function(){
		var msg = '${message}';
		if (msg != null && msg != "" && msg != "null") {
			alert(msg);
		}
		var choose=$("#queryType").val();
		var fgLonentNo=$("#financingVO_fgLonentNo").val();
		$("#fgLonentNo option[value='"+fgLonentNo+"']").attr("selected","selected");
		$("#choose option[value='"+choose+"']").attr("selected","selected");
		comboboxSetId("#fgLonentNo");
			//加载插件
		$('#fgStDateStart').datebox({
			editable : false
		});
		$('#fgStDateEnd').datebox({
			editable : false
		});
		var startDate=$("#financingVO_fgStDateStart").val();
		var endDate=$("#financingVO_fgStDateEnd").val()
		
		startDate!=""?$('#fgStDateStart').datebox('setValue',parseDate(startDate)):"";
		endDate!=""?$('#fgStDateEnd').datebox('setValue',parseDate(endDate)):"";
		
	});
	
	function doUpdate(fgLoanCode){
		location="<url:context/>/ZXinterface.do?method=financingUpdateShow&fgLoanCode="+fgLoanCode;
	}
	function doQuery() {
		if ($("#choose").val() == "2") {
			if ($("#fgLonentNo").combobox('getValue')=="") {
				alert("-借款企业ID不能为空-");
				return false;
			}
			if (!$("#fgStDateStart").datebox('getValue')) {
				alert("开始时间不能为空");
				return false;
			}
			if (!$("#fgStDateEnd").datebox('getValue')) {
				alert("结束时间不能为空");
				return false;
			}
		} 

		var startDate = new Date($("#fgStDateStart").datebox('getValue'));
		var endDate = new Date($("#fgStDateEnd").datebox('getValue'));
		var nextYear = startDate.getTime() + (1000 * 60 * 60 * 24 * 365);
		if (new Date(nextYear) < new Date()) {
			alert("开始日期+365天小于等于当前日期");
			return;
		}

		if (startDate > endDate) {
			alert("开始时间不能大于结束时间");
			return;
		}
		var day = Math.abs((startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000));
		if (day > 90) {
			alert("开始时间和结束时间不能相差超过90天");
			return;
		}

		$("[name='financingVO.fgStDateStart']").val($("[name='financingVO.fgStDateStart']").val().replace(/-/g, ""));
		$("[name='financingVO.fgStDateEnd']").val($("[name='financingVO.fgStDateEnd']").val().replace(/-/g, ""));
		document.forms[0].submit();
	}

	function doClear() {
		$('#fgStDateStart').datebox({editable:false});
		$('#fgStDateEnd').datebox({editable:false}); 
		$("#fgLonentNo").combobox("setValue","");
		$("#choose").val("");
	}

	function doChoose(val) {
		
		if (val == "2") {
			$(".req").css("visibility", "visible");
		} else {
			$(".req").css("visibility", "hidden");
		}
	}
	
	function parseDate(newDate){
		return newDate.replace(/^(\d{4})(\d{2})(\d{2})$/, '$1-$2-$3');
	}
	
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="#">中信银行接口</a> &gt; <a class="crumbs-link" href="#">借款企业融资信息查询</a>
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
									<div class="ly-sel-w">
										<select id="fgLonentNo" name="financingVO.fgLonentNo" style="width: 85%;">
											<option value="">请选择...</option>
											<c:forEach items="${draftOptions}" var="row">
												<option <c:if test="${row.label==financingVO.fgLonentNo}"></c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }" /></option>
											</c:forEach>
										</select> <input type="hidden" id="financingVO_fgLonentNo" value="${financingVO.fgLonentNo}" />
									</div>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font class="req" color="#FF0000" style="visibility: hidden;">*</font>融资起始日-开始：
								</div>
								<div class="input block fl hidden">
									<input id="fgStDateStart" name="financingVO.fgStDateStart" type="text" />
								</div>
							</div>
							<input type="hidden" id="financingVO_fgStDateStart" value="${financingVO.fgStDateStart}" />
							<div class="ly-col fl">
								<div class="label block fl hidden">
									<font class="req" color="#FF0000" style="visibility: hidden;">*</font>融资起始日-结束：
								</div>
								<div class="input block fl hidden">
									<input id="fgStDateEnd" name="financingVO.fgStDateEnd" type="text" />
								</div>
							</div>
							<input type="hidden" id="financingVO_fgStDateEnd" value="${financingVO.fgStDateEnd}" />
							<div class="ly-col fl">
								<div class="label block fl hidden">查询方式：</div>
								<div class="input block fl hidden">
									<select class="ly-bor-none" id="choose" name="queryType" onchange="doChoose(this.value)" style="width: 85%;">
										<option value="">请选择</option>
										<option value="1">本地查询</option>
										<option value="2">直连查询</option>
									</select> <input type="hidden" id="queryType" value="${queryType}" />
								</div>
							</div>
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:doQuery();" class="button btn-query">查询</a> <a href="javascript:doClear();" class="button btn-reset">重置</a> <a href="ZXinterface.do?method=financingOut" class="button btn-reset">导出</a>
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
										<th class="t-th">保证金比例(%)</th>
										<th class="t-th">自有资金比例(%)</th>
										<th class="t-th">首付保证金可提货比例(%)</th>
										<th class="t-th">融资起始日</th>
										<th class="t-th">融资到期日</th>
										<th class="t-th">授信产品</th>
										<th class="t-th">经办行</th>
										<th class="t-th">业务模式</th>
										<th class="t-th">协议编号</th>
										<th class="t-th">创建时间</th>
										<th class="t-th">更新时间</th>
										<th class="t-th">编辑</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value="${index+1}" /></td>
												<td class="t-td"><c:out value="${row.fgLonentNo}" /></td>
												<td class="t-td"><c:out value="${row.fgLoncpName}" /></td>
												<td class="t-td"><c:out value="${row.fgLoanCode}" /></td>
												<td class="t-td"><c:out value="${row.fgScftxNo}" /></td>
												<td class="t-td"><c:out value="${row.fgLoanAmt}" /></td>
												<td class="t-td"><c:out value="${row.fgBailRat}" /></td>
												<td class="t-td"><c:out value="${row.fgSlfcap}" /></td>
												<td class="t-td"><c:out value="${row.fgFstblRat}" /></td>
												<td class="t-td"><c:out value="${row.fgStDate}" /></td>
												<td class="t-td"><c:out value="${row.fgEndDate}" /></td>
												<td class="t-td"><c:if test="${row.fgProcrt=='1'}">银行承兑汇票</c:if> <c:if test="${row.fgProcrt=='2'}">流动资金贷款</c:if> <c:if test="${row.fgProcrt=='3'}">法人账户透支</c:if> <c:if test="${row.fgProcrt=='4'}">商票贴现</c:if> <c:if
														test="${row.fgProcrt=='5'}">国内信用证</c:if> <c:if test="${row.fgProcrt=='6'}">进口信用证</c:if> <c:if test="${row.fgProcrt=='7'}">非信用证项下进口押汇</c:if></td>
												<%-- <td class="t-td"><c:out value="${row.fgProcrt}" /></td> --%>
												<td class="t-td"><c:out value="${row.fgOperOrg}" /></td>
												<td class="t-td"><c:if test="${row.fgBizMod=='1'}">先票后货三方</c:if> <c:if test="${row.fgBizMod=='2'}">先票后货两方</c:if> <c:if test="${row.fgBizMod=='3'}">保兑仓</c:if> <c:if test="${row.fgBizMod=='4'}">动产质押（静态）</c:if> <c:if
														test="${row.fgBizMod=='5'}">动产质押（总量）</c:if> <c:if test="${row.fgBizMod=='6'}">国内设备买方信贷</c:if> <c:if test="${row.fgBizMod=='7'}">标准仓单</c:if> <c:if test="${row.fgBizMod=='8'}">B2B先款后货</c:if> <c:if test="${row.fgBizMod=='11'}">汽车金融先票后货三方</c:if>
													<c:if test="${row.fgBizMod=='12'}">汽车金融先票后货两方</c:if> <c:if test="${row.fgBizMod=='13'}">汽车金融保兑仓</c:if></td>
												<%-- <td class="t-td"><c:out value="${row.fgBizMod}" /></td> --%>
												<td class="t-td"><c:out value="${row.fgagtid}" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.fgCreateDate}" idtype="ss" /></td>
												<td class="t-td"><select:timestamp timestamp="${row.fgUpdateDate}" idtype="ss" /></td>
												<td class="t-td"><a onclick="doUpdate(${row.fgLoanCode})">编辑</a></td>
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
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Financing" action="ZXinterface.do?method=financing" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>