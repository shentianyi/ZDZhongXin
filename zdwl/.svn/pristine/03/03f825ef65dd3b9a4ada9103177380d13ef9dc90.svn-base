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
		//检查是否选择了要新增的角色
		if (hasCheckedItem("brandIds")) {

			document.forms[0].submit();
		} else {
			alert("请选择要绑定的品牌!");
		}
	}

	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/business/ywBank.do?method=findList&query.userId="+$("#userId").val();
	}

	//执行查询操作
	function doQuery() {
			
			document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		//清空角色名输入框
		getElement("query.brandName").value = "";
	}
	
	function goBrand(id){
		location.href="/business/ywBank.do?method=brandList&query.brandType="+id+"&query.id="+$("#id").val()+"&query.userId="+$("#userId").val();
	}
</script>
</head>

<body onLoad="doLoad()">

	<div class="pagebodyOuter">
		<div class="pagebodyInner">

			<!--页签-->
			<div class="tagbarMenu">
				<label class="blurLable"> <a
					href="javascript:goBrand('1');">已分配品牌</a>
				</label> 
				<label class="focusLable"> <a href="javascript:void(0);">未分配品牌</a>
				</label>
			</div>

			<div class="tagbarBody">

				<html:form action="/business/ywBank.do" styleId="ywBankForm"
					method="post" onsubmit="return false">
					<input type="hidden" name="method" value="addBrands"/>
					<html:hidden property="query.id" styleId="id" />
					<html:hidden property="query.userId" styleId="userId"/>

					<table class="formTable">
						<tr>
							<td class="nameCol">品牌名称：</td>
							<td class="codeCol"><html:text property="query.brandName" styleId="query.brandName"></html:text></td>
						</tr>
						<tr class="formTableFoot">
							<td colspan="6" align="center">
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
									class="checkbox_title" onClick="checkAll('brandIds')" /></td>
								<td width="5%">序号</td>
								<td><thumbpage:order cname="品牌" filedName="roleName" /></td>
							</tr>
						</thead>

						<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()"
							onClick="clickRow()">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="listTr_a">
									<td align="center"><input type="checkbox" id="brandIds"
										name="brandIds" value="<c:out value='${row.id}'/>"></td>
									<td align="center"><c:out value="${index+1}" /></td>
									<td><c:out value="${row.name }"></c:out></td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<thumbpage:tools
						className="<%=ThumbPageConstants.CLASSNAME_TAGBAR.getCode()%>"
						tableName="ywbrandList"
						action="/business/ywBank.do?method=brandList" />
					<table class="bottomTable">
						<tr>
							<td>
								<button class="formButton" onClick="doSave()">新&nbsp;增</button>
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
