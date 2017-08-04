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
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");}

	//执行查询操作
	function doQuery(pageIndex) {
		$("#pageIndex").val(pageIndex);
		document.forms[0].submit();
	}
</script>
<style type="text/css">
.title td {
	padding: 0 15px 0 15px;
}
</style>
</head>
<body onLoad="doLoad()">

	<div class="title">提货申请</div>
	<div class="pagebodyOuter">
		<div class="pagebodyInner">

			<html:form action="/bank/interface.do" styleId="iForm" method="post"
				onsubmit="return false">
				<input type="hidden" name="method" value="gyl020" />
				<input name="pageIndex" id="pageIndex" type="hidden" />

				<table class="formTable" cellpadding="0" cellspacing="0">
					<tr>
						<td class="nameCol" style="border-left: solid 1px #eee;">交易流水号：</td>
						<td class="codeCol"><c:out value="${serialNo }"/></td>
					</tr>
				</table>
				<div id="message" class="message" style="display: none"></div>
			</html:form>

		</div>
	</div>

</body>
</html>
