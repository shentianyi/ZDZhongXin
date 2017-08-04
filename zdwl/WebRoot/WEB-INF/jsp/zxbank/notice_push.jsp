<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link type="text/css" rel="stylesheet" href="../easyui/themes/bootstrap/easyui.css">
<link type="text/css" rel="stylesheet" href="../css/zxcss.css" />
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../easyui/jquery.easyui.min.js"></script>
<script src="../js/thumbpage/thumbpage.js"></script>
<script src="../js/zxjs.js"></script>
<!-- 全选 -->
<script type="text/javascript">
	$(function(){
		var msg = '${message}';
		if (msg != null && msg != "" && msg != "null") {
			alert(msg);
		}
		var ntctp= $("#notice_hidden_id").val();
		$("#ntctp option[value='"+ntctp+"']").attr("selected","selected");
	
		var ntcno=$("#notice_ntcno").val();
		$("#ntcno option[value='"+ntcno+"']").attr("selected","selected");
		
		var ntfailflag=$("#notice_ntfailflag").val();
		$("#ntfailflag option[value='"+ntfailflag+"']").attr("selected","selected");
		
		comboboxSetId("#ntcno");
		comboboxSetId("#ntctp");
		comboboxSetId("#ntfailflag");
	});
	
	function doQuery() {
		document.forms[0].submit();
	}
	
	function doClear() {
		$("#ntcno").combobox('setValue',"");
		$("#ntctp").combobox('setValue',"");
		$("#ntfailflag").combobox('setValue',"");
	}
</script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="#">中信银行接口</a> &gt; <a class="crumbs-link" href="#">通知推送</a>
			</div>
		</div>
	</div>
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/ZXinterface.do" styleId="nForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="findnotice" />
				<div class="public-main-input ly-col-1 hidden abs">
					<div class="ly-input-w">
						<div class="ly-row clearfix">
							<div class="ly-col fl">
								<div class="label block fl hidden">通知类型：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="ntctp" name="notice.ntctp" style="width: 85%;">
											<option value="">请选择</option>
											<option value="1">收货通知书</option>
											<option value="2">移库通知书</option>
											<option value="3">解除质押通知书</option>
											<option value="4">质物与融资关系变更通知书</option>
										</select> <input type="hidden" id="notice_hidden_id" value="${notice.ntctp}" />
									</div>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">通知书编号:</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="ntcno" name="notice.ntcno" style="width: 85%; color: #000;">
											<option value="">请选择...</option>
											<c:forEach items="${draftOptions}" var="row">
												<option <c:if test="${row.label==notice.ntcno}"></c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }" /></option>
											</c:forEach>
										</select> <input type="hidden" id="notice_ntcno" value="${notice.ntcno}" />
									</div>
								</div>
							</div>
							<div class="ly-col fl">
								<div class="label block fl hidden">通知书状态：</div>
								<div class="input block fl hidden">
									<div class="ly-sel-w">
										<select id="ntfailflag" name="notice.ntfailflag" style="width: 85%;">
											<option value="">请选择</option>
											<option value="2">读取成功</option>
											<option value="1">读取失败</option>
										</select>
									</div>
									<input type="hidden" id="notice_ntfailflag" value="${notice.ntfailflag}" />
								</div>
							</div>
						</div>
					</div>
					<div class="ly-button-w">
						<a href="javascript:doQuery();" class="button btn-query">查询</a> <a href="javascript:doClear();" class="button btn-reset">重置</a>
					</div>
				</div>
				<div class="public-main-table hidden abs">
					<div class="ly-cont">
						<div style="overflow-x: auto; overflow-y: auto; height: 100%; width: 100%">
							<table class="t-table" border="0" cellspacing="0" cellpadding="0">
								<thead class="t-thead">
									<tr class="t-tr">
										<th class="t-th">序号</th>
										<th class="t-th">通知书类型</th>
										<th class="t-th">通知书编号</th>
										<th class="t-th">分行ID</th>
										<th class="t-th">通知书状态</th>
										<th class="t-th">发送时间</th>
										<th class="t-th">操作</th>
									</tr>
								</thead>
								<tbody class="t-tbody hidden">
									<logic:iterate name="list" id="row" indexId="index">
										<tr class="t-tr">
											<td class="t-td"><c:out value="${index+1}" /></td>
											<td class="t-td"><c:if test="${row.ntctp=='1'}">收货通知书</c:if> <c:if test="${row.ntctp=='2'}">移库通知书</c:if> <c:if test="${row.ntctp=='3'}">解除质押通知书</c:if> <c:if test="${row.ntctp=='4'}">质物与融资关系变更通知书</c:if> <c:if test="${row.ntctp=='5'}">入库通知书</c:if></td>
											<td class="t-td"><c:out value="${row.ntcno}" /></td>
											<td class="t-td"><c:out value="${row.ntbranchid}" /></td>
											<td class="t-td"><c:if test="${row.ntctp=='4'}">
														${row.ntfailflag=='2'?'变更成功':'变更失败' }
												</c:if> <c:if test="${row.ntctp!='4'}">
														${row.ntfailflag=='2'?'读取成功':'读取失败' }
												</c:if></td>
											<%-- <td class="t-td"><c:if test="${row.ntfailflag=='0' }">回执失败</c:if>
												<c:if test="${row.ntfailflag=='1' }">读取失败</c:if> <c:if
													test="${row.ntfailflag=='2' }">读取成功</c:if></td> --%>
											<td class="t-td"><select:timestamp timestamp="${row.ntcdate}" idtype="ss" /></td>
											<td class="t-td"><c:if test="${row.ntfailflag=='0'}">
													<a> <input type="button" value="待推送" />
													</a>
												</c:if> <c:if test="${row.ntfailflag=='1'&&row.ntctp!='4'&&row.ntctp!='5'}">
													<a href="ZXinterface.do?method=noticeReread&ntType=${row.ntctp}&ntcno=${row.ntcno}"> <input type="button" value="重新读取" />
													</a>
												</c:if> <c:if test="${row.ntfailflag=='1'&&(row.ntctp=='4'||row.ntctp=='5')}">
													<a href="ZXinterface.do?method=retPushDetail"> <input type="button" value="重新变更" />
													</a>
													<a href="ZXinterface.do?method=noticePushDetail&ntcno=${row.ntcno}"> <input type="button" value="变更详情" />
													</a>
												</c:if> <c:if test="${row.ntfailflag=='2'&&(row.ntctp!='4' && row.ntctp!='5')}">
													<a href="ZXinterface.do?method=noticepush&ntNo=${row.ntcno}&ntType=${row.ntctp}"> <input type="button" value="查看详情" />
													</a>
												</c:if> <c:if test="${(row.ntctp=='4'||row.ntctp=='5')&&row.ntfailflag=='2'}">
													<a href="ZXinterface.do?method=noticePushDetail&ntcno=${row.ntcno}"> <input type="button" value="变更详情" />
													</a>
												</c:if></td>
										</tr>

									</logic:iterate>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="public-main-footer hidden abs">
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Notice" action="ZXinterface.do?method=findnotice" />
					</div>
				</div>
			</html:form>
		</div>
	</div>
</body>
</html>