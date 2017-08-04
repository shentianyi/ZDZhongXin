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

	function doReturn() {
		history.go(-1);
	}

	//执行查询操作
	function doQuery() {
		//对表单内容进行验证，包括对输入类型等限制方式
		if (validateMain("dealerForm")) {
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
		//清空状态复选框
		/* var elements = document.getElementsByName("bankQuery.bankType");
		for(var i = 0; i < elements.length; i++){
			elements[i].checked = false;
		} */
	}
</script>
<style type="text/css">
.title td {
	padding: 0 15px 0 15px;
}
</style>
</head>
<body onLoad="doLoad()">

	<div class="title">监管费用统计详情</div>
	<div class="pagebodyOuter">
		<div class="pagebodyInner">

			<html:form action="/market/dealer" styleId="dealerForm" method="post"
				onsubmit="return false">
				<input name="method" id="method" type="hidden"
					value="paymentDetailList" />
				<div class="dvScroll">
					<table class="listTalbe" style="white-space: nowrap;"
						cellpadding="0" cellspacing="0">
						<thead>
							<tr class="title">
								<td>序号</td>
								<td><thumbpage:order cname="缴费时间"
										filedName="actualpaymentdate" /></td>
								<td><thumbpage:order cname="缴费金额"
										filedName="actualpaymentmoney" /></td>
								<!-- <td align="center">操作</td> -->
							</tr>
						</thead>
						<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()"
							onClick="clickRow()">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="listTr_a">
									<td align="center">&nbsp;<c:out value="${index+1}" /></td>
									<td>
										<fmt:formatDate value="${row.actualPaymentDate}" pattern="yyyy-MM-dd"/>
									</td>
									<td><c:out value='${row.actualPaymentMoney }'/></td>

									<!-- <td align="center" nowrap="true" class="right-border-none">
										
									</td> -->
								</tr>
							</logic:iterate>
							<tr class="formTableFoot">
								<td colspan="4" align="center">
									<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<thumbpage:tools
					className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>"
					tableName="paymentDetailList"
					action="/market/dealer.do?method=paymentDetailList" />
				<table class="bottomTable">
				</table>
				<div id="message" class="message" style="display: none"></div>
			</html:form>

		</div>
	</div>

</body>
</html>
