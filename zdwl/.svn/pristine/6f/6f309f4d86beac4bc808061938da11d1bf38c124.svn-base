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
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行查询操作
function doQuery() {
	document.forms[0].submit();
}

//执行表单清空操作
function doClear() {
	//清空资源名输入框
	getElement("bankQuery.bankName").value="";
}

function childrenManager(bankId){
	location.href="/bank/bankChildrenManager.do?method=bankChildrenIn&bankQuery.parentId="+bankId;
}
$(function(){
//限制超级角色操作
    restrict();
});

function restrict(){
    var crole = $("#crole").val();
    if(crole == 30){
        $(".yc").hide();
    }
}
</script>
</head>
<body onLoad="doLoad()">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
	<div class="title">分行下支行管理</div>
	<div class="pagebodyOuter">
		<div class="pagebodyInner">

			<html:form action="/bank/bankChildrenManager" styleId="bankForm" method="post"
				onsubmit="return false">
				<input name="method" id="method" type="hidden" value="bankChildrenManagerList" />
				<!-- 查询条件 -->
				<table class="formTable" cellpadding="0" cellspacing="0">
					<colgroup>
						<col width="24%" />
						<col width="24%" />
						<col width="24%" />
						<col width="24%" />
					</colgroup>
					<tr>
						<td class="nameCol" style="border-left: solid 1px #eee;">银行名称：</td>
						<td class="codeCol"><html:text property="bankQuery.bankName"
								styleId="bankQuery.bankName" /></td>
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
								<td><thumbpage:order cname="总行" filedName="zhu" /></td>
								<td><thumbpage:order cname="分行" filedName="fen" /></td>
								<td><thumbpage:order cname="银行类型" filedName="bankType" /></td>
								<td align="center">操作</td>
							</tr>
						</thead>
						<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()"
							onClick="clickRow()">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="listTr_a">
									<td align="center">&nbsp;<c:out value="${index+1}" /></td>
									<td><c:out value="${row.zhu}" /></td>
									<td><c:out value="${row.fen}" /></td>
									<td><select:bankType type="${row.bankType }"></select:bankType>
									</td>
									<td align="center" nowrap="true" class="right-border-none">
										<a href="javascript:childrenManager('<c:out value="${row.id }"/>')">下级支行管理</a>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
				<thumbpage:tools
					className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>"
					tableName="bankList" action="/bank/bankChildrenManager.do?method=bankChildrenManagerList" />
				<table class="bottomTable yc">
					<tr>
						<td><button class="formButton" onClick="goAdd()">新&nbsp;增</button></td>
					</tr>
				</table>
				<div id="message" class="message" style="display: none"></div>
			</html:form>

		</div>
	</div>

</body>
</html>
