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
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link type="text/css" rel="stylesheet" href="css/easyui.css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery.easyui.min.js"></script>
<script src="js/easyui-lang-zh_CN.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a>
				&gt;
				<a class="crumbs-link" href="checkstock.do?method=checkstock">盘库信息查询</a>
				&gt;
				<a class="crumbs-link" href="#">盘库详情</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" method="post" onsubmit="return false">
				<div class="public-main-input ly-col-3 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">借款企业ID：</div>
								<div class="input block fl hidden">${checkstock.csLoncpid}</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">系统监管协议编号：</div>
								<div class="input block fl hidden">${checkstock.csProtocolno}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">纸质监管协议编号：</div>
								<div class="input block fl hidden">${checkstock.csProtocolcode}</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">操作人员编号：</div>
								<div class="input block fl hidden">${checkstock.csUserno}</div>
							</div>
						</div>

						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">操作人名称：</div>
								<div class="input block fl hidden">${checkstock.csUsername}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">交易流水号：</div>
								<div class="input block fl hidden">${checkstock.csTradeid}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">计划盘库日期：</div>
								<div class="input block fl hidden">${checkstock.csPlandate}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">实际盘库日期：</div>
								<div class="input block fl hidden">${checkstock.csFactdate}</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">差错报告：</div>
								<div class="input block fl hidden">${checkstock.csErrorreport}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">备注：</div>
								<div class="input block fl hidden">${checkstock.csRemark}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">创建时间：</div>
								<div class="input block fl hidden">
									<select:timestamp timestamp="${checkstock.csCreatedate}" idtype="ss" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="public-main-table hidden abs" style="top: 175px;">
					<div class="ly-cont">
						<div style="overflow-x: auto; overflow-y: auto; height: 100%; width: 100%">
							<table class="t-table" border="0" cellspacing="0" cellpadding="0">
								<thead class="t-thead">
									<tr class="t-tr">
										<th class="t-th">序号</th>
										<th class="t-th">仓库级别</th>
										<th class="t-th">仓库代码</th>
										<th class="t-th">仓库地址</th>
										<th class="t-th">商品代码</th>
										<th class="t-th">盘库商品数量</th>
										<th class="t-th">动产质押担保合同编号</th>
										<th class="t-th">车架号</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value='${index+1 }' /></td>
												<td class="t-td"><c:out value="${row.whlevel==1?'一级仓库':'二级仓库'}" /></td>
												<td class="t-td"><c:out value='${row.whcode }' /></td>
												<td class="t-td"><c:out value='${row.whaddr }' /></td>
												<td class="t-td"><c:out value='${row.cmcode }' /></td>
												<td class="t-td"><c:out value='${row.num }' /></td>
												<td class="t-td"><c:out value='${row.cmgrtcntno }' /></td>
												<td class="t-td"><c:out value='${row.vin }' /></td>
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
							<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="CheckstockVO"
								action="ZXinterface.do?method=checkstockDetail&csid=${list[0].scid}&loncpid=${checkstock.csLoncpid}" />
						</c:if>
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>
