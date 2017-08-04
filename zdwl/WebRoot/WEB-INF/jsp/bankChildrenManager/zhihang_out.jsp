<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

	//执行保存操作
	function doSave() {
		$("#method").val("addBankChildren");
		//检查是否选择了要新增的角色
		if (hasCheckedItem("bankQuery.bankChildrenIds")) {
			document.forms[0].submit();
		} else {
			alert("请选择要添加的支行!");
		}
	}


	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/bank/bankChildrenManager.do?method=bankChildrenManagerList";
	}

	//执行查询操作
	function doQuery() {
		$("#method").val("bankChildrenOut");
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		//清空资源名输入框
		getElement("bankQuery.bankName").value="";
	}
	function goBankChildren(){
		location.href="/bank/bankChildrenManager.do?method=bankChildrenIn&bankQuery.parentId="+getElement("parentId").value;
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
	<div class="pagebodyOuter">
		<div class="pagebodyInner">

			<!--页签-->
			<div class="tagbarMenu">
				<label class="blurLable"> <a
					href="javascript:goBankChildren();">已分配支行</a>
				</label> 
				<label class="focusLable"> <a href="javascript:void(0);">未分配支行</a>
				</label>
			</div>

			<div class="tagbarBody">
				<html:form action="/bank/bankChildrenManager.do" styleId="bankForm"
					method="post" onsubmit="return false">
					<input type="hidden" id="method" name="method" value="addBankChildren"/>
					<html:hidden property="bankQuery.parentId" styleId="parentId"/>
					
					<table class="formTable">
						<tr>
							<td class="nameCol" style="border-left: solid 1px #eee;">银行名称：</td>
							<td class="codeCol"><html:text property="bankQuery.bankName"
									styleId="bankQuery.bankName" /></td>
						</tr>
						<tr class="formTableFoot">
							<td colspan="8" align="center">
								<button class="formButton" onClick="doQuery()">查&nbsp;询</button>
								&nbsp;
								<button class="formButton" onClick="doClear()">重&nbsp;置</button>
							</td>
						</tr>
					</table>
					<br />
					<table class="listTalbe">
						<thead>
							<tr class="title">
								<td width="5%"><label for="checkAllValue"></label> <input
									type="checkbox" name="checkAllValue" id="checkAllValue"
									class="checkbox_title" onClick="checkAll('bankQuery.bankChildrenIds')" /></td>
								<td width="5%">序号</td>
								<td><thumbpage:order cname="总行" filedName="zhu" /></td>
								<td><thumbpage:order cname="分行" filedName="fen" /></td>
								<td><thumbpage:order cname="支行" filedName="zhi" /></td>
								<td><thumbpage:order cname="银行类型" filedName="bankType" /></td>
							</tr>
						</thead>

						<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()"
							onClick="clickRow()">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="listTr_a">
									<td align="center"><input type="checkbox" id="bankQuery.bankChildrenIds"
										name="bankQuery.bankChildrenIds" value="<c:out value='${row.id}'/>"></td>
									<td align="center"><c:out value="${index+1}" /></td>
									<td><c:out value="${row.zhu}" /></td>
									<td><c:out value="${row.fen}" /></td>
									<td><c:out value="${row.zhi}" /></td>
									<td><select:bankType type="${row.bankType }"></select:bankType>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<thumbpage:tools
						className="<%=ThumbPageConstants.CLASSNAME_TAGBAR.getCode()%>"
						tableName="bankChildrenOut"
						action="/bank/bankChildrenManager.do" />
					<table class="bottomTable">
						<tr>
							<td>
								<button class="formButton yc" onClick="doSave()">添&nbsp;加</button>
								&nbsp;
								<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
							</td>
						</tr>
					</table>
					<br />
					<div id="message" class="message" style="display: none"></div>
				</html:form>

			</div>
		</div>
	</div>

</body>
</html>
