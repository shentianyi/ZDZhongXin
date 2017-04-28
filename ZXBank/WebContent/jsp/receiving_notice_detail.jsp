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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<script src="js/thumbpage/thumbpage.js"></script>
<script src="js/jquery-1.8.3.min.js"></script>
<!-- <script src="js/jquery-ui.min.js"></script> -->
<script type="text/javascript">
	function saveCode() {
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
				<a class="crumbs-link" href="ZXinterface.do?method=receivingnotice">收货通知</a>
				&gt;
				<a class="crumbs-link" href="#">通知详细查询</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" method="post" onsubmit="return false">
				<input type="hidden" name="method" value="receivingdetailOut" />
				<input type="hidden" name="nyno" value='${receiving.nyNo}' />
				<div class="public-main-input ly-col-4 abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">收货通知书编号:</div>
								<div class="input block fl hidden">
									<c:out value="${receiving.nyNo}" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">核心企业名称:</div>
								<div class="input block fl hidden">
									<c:out value='${receiving.nyCorentnm}' />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">(在库)监管企业名称:</div>
								<div class="input block fl hidden">
									<c:out value='${receiving.nySpventnm }' />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">(在途)监管企业名称:</div>
								<div class="input block fl hidden">
									<c:out value='${receiving.nyOnwspvenm }' />
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">运输企业名称:</div>
								<div class="input block fl hidden">
									<c:out value='${receiving.nyTrsptentnm }' />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">借款企业ID:</div>
								<div class="input block fl hidden">
									<c:out value='${receiving.nyLonentno}' />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">借款企业名称:</div>
								<div class="input block fl hidden">
									<c:out value='${receiving.nyLonentname }' />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">发货日期:</div>
								<div class="input block fl hidden">
									<select:timestamp timestamp="${receiving.nyCsndate}" idtype="ss" />
								</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">预计到港(库)日期:</div>
								<div class="input block fl hidden">
									<select:timestamp timestamp="${receiving.nyEta}" idtype="ss" />
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">预计到港(库):</div>
								<div class="input block fl hidden">
									<c:out value='${receiving.nyEpa }' />
								</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">纸质监管协议编号:</div>
								<div class="input block fl hidden">
									<c:out value='${receiving.nyOfflnsatno}' />
								</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">通知书日期:</div>
								<div class="input block fl hidden">
									<select:timestamp timestamp="${receiving.nyNtcdate}" idtype="ss" />
								</div>
							</div>
						</div>
					</div>
					<div class="ly-row clearfix">
						<div class="ly-col fl">
							<div class="label block fl hidden">货物价值合计:</div>
							<div class="input block fl hidden">
								<c:out value='${receiving.nyTtlcmdval }' />
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">交货地点:</div>
							<div class="input block fl hidden">
								<c:out value='${receiving.nyExcplace }' />
							</div>
						</div>

						<div class="ly-col fl">
							<div class="label block fl hidden">总记录:</div>
							<div class="input block fl hidden">
								<c:out value='${receiving.nyTotnum }' />
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">备注:</div>
							<div class="input block fl hidden">
								<c:out value='${receiving.nyRemark }' />
							</div>
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:saveCode();" class="button btn-query">导出</a>
						<a href="javascript:history.go(-1);" class="button btn-query">返回</a>
					</div>
				</div>
				<div class="public-main-table hidden abs" style="margin-top: 25px;">
					<div class="ly-cont">
						<div style="overflow-x: auto; overflow-y: auto; height: 100%; width: 100%">
							<table class="t-table" border="0" cellspacing="0" cellpadding="0">
								<thead class="t-thead">
									<tr class="t-tr">
										<th class="t-th">序号</th>
										<th class="t-th">实际订单纸质编号</th>
										<th class="t-th">实际订单名称</th>
										<th class="t-th">商品代码</th>
										<th class="t-th">商品名称</th>
										<th class="t-th">发货数量</th>
										<th class="t-th">计量单位</th>
										<th class="t-th">发货单价</th>
										<th class="t-th">发货价值</th>
										<th class="t-th">SCF放款批次号</th>
										<th class="t-th">质押合同编号</th>
										<th class="t-th">融资编号</th>
										<th class="t-th">合格证编号</th>
										<th class="t-th">车架号</th>
										<th class="t-th">车价</th>
										<th class="t-th">备注</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value="${index+1}" /></td>
												<td class="t-td"><c:out value='${row.ndIdtplanno }' /></td>
												<td class="t-td"><c:out value='${row.ndIdtplannm }' /></td>
												<td class="t-td"><c:out value='${row.ndCmdcode }' /></td>
												<td class="t-td"><c:out value='${row.ndCmdname }' /></td>
												<td class="t-td"><c:out value='${row.ndCsnnum }' /></td>
												<td class="t-td"><c:out value='${row.ndUnit }' /></td>
												<td class="t-td"><c:out value='${row.ndCsnprc }' /></td>
												<td class="t-td"><c:out value='${row.ndReqcsnval }' /></td>
												<td class="t-td"><c:out value='${row.ndScflonno }' /></td>
												<td class="t-td"><c:out value='${row.ndMtgcntno }' /></td>
												<td class="t-td"><c:out value='${row.ndLoancode }' /></td>
												<td class="t-td"><c:out value='${row.ndHgzno }' /></td>
												<td class="t-td"><c:out value='${row.ndVin }' /></td>
												<td class="t-td"><c:out value='${row.ndCarprice }' /></td>
												<td class="t-td"><c:out value='${row.ndRemark }' /></td>
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
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="ReceivingDetail"
							action="ZXinterface.do?method=receivingdetail&nyNo=${receiving.nyNo}" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>