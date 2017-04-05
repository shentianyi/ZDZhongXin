<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>质物入库详情</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<script src="js/thumbpage/thumbpage.js"></script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a> > <a
					class="crumbs-link" href="#">质物入库详情</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<input name="method" id="method" type="hidden" value="commodity" />
			<div class="public-main-input ly-col-2 hidden abs">
				<div class="ly-input-w">
					<div class="ly-row clearfix">
						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>借款企业ID：
							</div>
							<div class="input block fl hidden">
								${Gager.gaLonentno }
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>操作人名称：
							</div>
							<div class="input block fl hidden">
								${Gager.gaOprtname }
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">
								<font color="#FF0000">*</font>交易流水号：
							</div>
							<div class="input block fl hidden">
								${Gager.gaOrderno }
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">纸质担保编号</div>
							<div class="input block fl hidden">
								${Gager.gaPcgrtntno }
							</div>
						</div>
					</div>
					<div class="ly-row clearfix">
						<div class="ly-col fl">
							<div class="label block fl hidden">动产质押合同编号</div>
							<div class="input block fl hidden">
								${Gager.gaCmgrtcntno }
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">总记录数：</div>
							<div class="input block fl hidden">
								${Gager.gaCount }
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">状态：</div>
							<div class="input block fl hidden">
								${Gager.gaState }
							</div>
						</div>
						<div class="ly-col fl">
							<div class="label block fl hidden">申请时间：</div>
							<div class="input block fl hidden">
								${Gager.gaCreatedate }
							</div>
						</div>
					</div>

				</div>
				<div class="ly-button-w">
					<a href="history.go(-1)" onclick="history.go(-1)"
						class="button btn-query">返回</a>
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
									<th class="t-th">商品代码</th>
									<th class="t-th">入库数量</th>
									<th class="t-th">入库单价</th>
									<th class="t-th">仓库代码</th>
									<th class="t-th">车架号</th>
									<th class="t-th">合格证编号</th>
									<th class="t-th">车价</th>
									<th class="t-th">融资编号</th>
								</tr>
							</thead>
							<tbody class="t-tbody hidden">
								<c:if test="${not empty list }">
									<logic:iterate name="list" id="row" indexId="index">
										<tr class="t-tr">
											<td class="t-td"><c:out value='${index+1 }' /></td>
											<td class="t-td"><c:out value='${row.cmCmdcode}' /></td>
											<td class="t-td"><c:out value='${row.cmStknum }' /></td>
											<td class="t-td"><c:out value='${row.cmIstkprc }' /></td>
											<td class="t-td"><c:out value='${row.cmWhcode }' /></td>
											<td class="t-td"><c:out value='${row.cmVin }' /></td>
											<td class="t-td"><c:out value='${row.cmHgzno }' /></td>
											<td class="t-td"><c:out value='${row.cmCarprice }' /></td>
											<td class="t-td"><c:out value='${row.cmLoancode }' /></td>
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
						<thumbpage:tools
							className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>"
							tableName="Commodity" action="commodity.do?method=commodity" />
					</c:if>
				</div>
			</div>
		</div>
	</div>

</body>
</html>