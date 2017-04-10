<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>解除质押通知书详情</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/tablecs.css">
<script src="js/thumbpage/thumbpage.js"></script>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script type="text/javascript">
function saveCode() {
	document.forms[0].submit();
}
</script>

<style type="text/css">
.title td {
	padding: 0 15px 0 15px;
}
</style>

<head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a>
				>
				<a class="crumbs-link" href="removepledge.do?method=removepledge">解除质押通知</a>
				>
				<a class="crumbs-link" href="#">通知书详情查询</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" method="post" onsubmit="return false">
				<input type="hidden" name="method" value="removepledgedetailOut" />
				<input type="hidden" name="rdno" value='${rp.rpNo}' />
				<div class="public-main-input ly-col-3 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">解除质押通知书编号：</div>
								<div class="input block fl hidden">${rp.rpNo}</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">经办行：</div>
								<div class="input block fl hidden">${rp.rpOperorg}</div>
							</div>

							<div class="ly-col fl">
								<div class="label block fl hidden">出质人名称：</div>
								<div class="input block fl hidden">${rp.rpPldegeename}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">核心企业名称：</div>
								<div class="input block fl hidden">${rp.rpCorename}</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">借款企业ID：</div>
								<div class="input block fl hidden">${rp.rpLoncpid}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">借款企业名称：</div>
								<div class="input block fl hidden">${rp.rpLoncpname}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">监管企业名称：</div>
								<div class="input block fl hidden">${rp.rpSupervisename}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">解除质押日期：</div>
								<div class="input block fl hidden">${rp.rpRelievepddate}</div>
							</div>
						</div>

						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">通知书日期：</div>
								<div class="input block fl hidden">${rp.rpNoticedate}</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">出库原因：</div>
								<div class="input block fl hidden">${rp.rpContent}</div>
							</div>
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:saveCode();" class="button btn-query">导出</a>
						<a href="javascript:history.go(-1);" class="button btn-query">返回</a>
					</div>
				</div>
				<div class="public-main-table hidden abs">
					<div class="ly-cont">
						<div style="overflow-x: auto; overflow-y: auto; height: 100%; width: 100%">
							<table class="t-table" border="0" cellspacing="0" cellpadding="0">
								<thead class="t-thead">
									<tr class="t-tr">
										<th class="t-th">序号</th>
										<th class="t-th">商品代码</th>
										<th class="t-th">商品名称</th>
										<th class="t-th">计量单位</th>
										<th class="t-th">库存数量</th>
										<th class="t-th">解除质押数量</th>
										<th class="t-th">所在仓库编号</th>
										<th class="t-th">SCF放款批次号</th>
										<th class="t-th">动产质押担保合同编号</th>
										<th class="t-th">移库数量</th>
										<th class="t-th">车架号</th>
										<th class="t-th">合格证编号</th>
										<th class="t-th">车价</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<c:if test="${not empty list }">
										<logic:iterate name="list" id="row" indexId="index">
											<tr class="t-tr">
												<td class="t-td"><c:out value="${index+1}" /></td>
												<td class="t-td"><c:out value='${row.rdCmdcode }' /></td>
												<td class="t-td"><c:out value='${row.rdCmdname }' /></td>
												<td class="t-td"><c:out value='${row.rdUnit }' /></td>
												<td class="t-td"><c:out value='${row.rdStknum }' /></td>
												<td class="t-td"><c:out value='${row.rdRlsmgnum }' /></td>
												<td class="t-td"><c:out value='${row.rdWhcode }' /></td>
												<td class="t-td"><c:out value='${row.rdScflonno }' /></td>
												<td class="t-td"><c:out value='${row.rdChattelpdno }' /></td>
												<td class="t-td"><c:out value='${row.rdNumber }' /></td>
												<td class="t-td"><c:out value='${row.rdChassisno }' /></td>
												<td class="t-td"><c:out value='${row.rdCertificationno }' /></td>
												<td class="t-td"><c:out value='${row.rdCarprice }' /></td>
										</logic:iterate>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="public-main-footer hidden abs">
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="RemovePledgeDetail"
							action="removepledgedetail.do?method=removepledgedetail&rdno=${rp.rpNo}" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>