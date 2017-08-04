<%@page import="com.zd.csms.rbac.constants.RoleConstants"%>
<%@page import="com.zd.csms.rbac.util.UserSession"%>
<%@page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
<%@page import="com.zd.csms.bank.contants.BankContants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	}

	//进入新增页面
	function goAdd() {
		location = "<url:context/>/supervisor/checkStock.do?method=preAddDealer&query.id="+$("#checkStockId").val();
	}

	//进入修改页面
	function goUpd(id) {
		location = "<url:context/>/supervisor/checkStock.do?method=preUpdate&checkStock.id=" + id;
	}

	//执行删除操作
	function doDel(id,checkStockId) {
		if (confirm("删除后数据不可恢复\n是否继续？")) {
			location = "<url:context/>/supervisor/checkStock.do?method=deleteDealer&csd.id=" + id+"&query.id="+checkStockId;
		}
	}

	//执行查询操作
	function doQuery() {
		//对表单内容进行验证，包括对输入类型等限制方式
		if (validateMain("checkStockForm")) {
			//为时间类型输入项补齐时间戳
			setTimeSuffix();
			//提交表单
			document.forms[0].submit();
		}
	}

	//执行表单清空操作
	function doClear() {
		//清空资源名输入框
		getElement("query.dealerName").value = "";
	}
</script>
</head>
<body onLoad="doLoad()">
	<c:set var="supervisory"><%=RoleConstants.SUPERVISORY.getCode() %></c:set>

	<div class="title">查库频次申请</div>
	<div class="pagebodyOuter">
		<div class="pagebodyInner">
			<div class="tagbarBody">
				<html:form action="/supervisor/checkStock.do" styleId="checkStockForm" method="post"
					onsubmit="return false">
					<input name="method" id="method" type="hidden" value="dealerList" />
					<html:hidden property="query.id" styleId="checkStockId"/>
					<!-- 查询条件 -->
					<table class="formTable" cellpadding="0" cellspacing="0">
						<colgroup>
							<col width="24%" />
							<col width="24%" />
						</colgroup>
						<tr>
							<td class="nameCol" style="border-left: solid 1px #eee;">经销商名称：</td>
							<td class="codeCol"><html:text property="query.dealerName"
									styleId="query.dealerName" /></td>


						</tr>
						<!-- 查询按钮 -->
						<tr class="formTableFoot">
							<td colspan="8" align="center" class="tdPadding">
								<button class="formButton" onClick="doQuery()">查&nbsp;询</button>&nbsp;
								<button class="formButton" onClick="doClear()">重&nbsp;置</button>
							</td>
						</tr>
					</table>

					<div class="dvScroll">
						<table class="listTalbe" cellpadding="0" cellspacing="0">
							<thead>
								<tr class="title">
									<td>序号</td>
									<td><thumbpage:order cname="经销商名称" filedName="dealerName" /></td>
									<td><thumbpage:order cname="金融机构" filedName="bankName" /></td>
									<td><thumbpage:order cname="品牌" filedName="brand" /></td>
									<td align="center">操作</td>
								</tr>
							</thead>
							<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()"
								onClick="clickRow()">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="listTr_a">
										<td align="center">&nbsp;<c:out value="${index+1}" /></td>
										<td><c:out value="${row.dealerName }" /></td>
										<td><c:out value="${row.bankName }"></c:out></td>
										<td><c:out value="${row.brand }"></c:out></td>
										<td align="center" nowrap="true" class="right-border-none">
											<a href="javascript:doDel('<c:out value="${row.id }"/>','<c:out value="${row.checkStockId }"/>')">删除</a>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<thumbpage:tools
						className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>"
						tableName="list" action="/supervisor/checkStock.do?method=dealerList" />
					<table class="bottomTable">
						<c:if test="${checkStockForm.query.currRole == supervisory }">
							<tr>
								<td><button class="formButton" onClick="goAdd()">新&nbsp;增</button></td>
							</tr>
						</c:if>
					</table>
					<div id="message" class="message" style="display: none"></div>
				</html:form>
			</div>
		</div>
	</div>

</body>
</html>
