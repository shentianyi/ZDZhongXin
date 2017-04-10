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
<meta http-equiv="Content-Type" content="text/html; charset=IUTF-8">
<title>中都汽车金融监管系统</title>
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
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a>
				>
				<a class="crumbs-link" href="movenotice.do?method=movenotice">移库通知</a>
				>
				<a class="crumbs-link" href="#">通知书详细查询</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" method="post" onsubmit="return false">
				<input type="hidden" name="method" value="movedetailOut" />
				<input type="hidden" name="mdno" value='${mn.mnNo}' />
				<div class="public-main-input ly-col-2 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">移库通知书编号:</div>
								<div class="input block fl hidden">${mn.mnNo }</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">监管企业名称:</div>
								<div class="input block fl hidden">${mn.mnSupervisename }</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">借款企业名称:</div>
								<div class="input block fl hidden">${mn.mnLoncpname }</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">经办行:</div>
								<div class="input block fl hidden">${mn.mnOperorg }</div>
							</div>
						</div>
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">移库申请日期:</div>
								<div class="input block fl hidden">${mn.mnMovedate }</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">通知书日期:</div>
								<div class="input block fl hidden">${mn.mnNoticedate }</div>
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
										<th class="t-th">移出仓库号</th>
										<th class="t-th">移入仓库号</th>
										<th class="t-th">商品代码</th>
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
												<td class="t-td"><c:out value="${row.mdRemoveoutno}" /></td>
												<td class="t-td"><c:out value="${row.mdRemoveinno}" /></td>
												<td class="t-td"><c:out value="${row.mdWareno}" /></td>
												<td class="t-td"><c:out value="${row.mdMovenumber}" /></td>
												<td class="t-td"><c:out value="${row.mdChassisno}" /></td>
												<td class="t-td"><c:out value="${row.mdCertificationno}" /></td>
												<td class="t-td"><c:out value="${row.mdCarprice}" /></td>
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
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="MoveDetail"
							action="movedetail.do?method=movedetail&mdno=${mn.mnNo}" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>