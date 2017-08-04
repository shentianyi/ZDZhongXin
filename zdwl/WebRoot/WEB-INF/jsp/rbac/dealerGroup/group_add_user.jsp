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
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
</style>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>

<script>
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行保存操作
function doSave(){
	if(hasCheckedItem("userIds")){
		getElement("method").value="addUser";
		document.forms[0].submit();
	} else{
		showMessage("请选择要添加的账号!");
	}
}

//已分配账号
function goUser(){
	var id=getElement("dealerGroup.id").value;
	location = "<url:context/>/rbac/dealerGroup.do?method=userEntry&dealerGroup.id="+id;
}

//返回到
function doReturn(){
	location = "<url:context/>/rbac/dealerGroup.do?method=groupList";
}

</script>
</head>
<body class="h-100 public">

<div class="public-bar hidden">
	<div class="ly-contai clearfix">
        <div class="public-bar-tab fr hidden clearfix">
            <div class="ly-button-w fr">
                <a class="button fl" href="javascript:goUser();">已分配账号</a>
                <a class="button btn-sel fl" href="javascript:void(0);">分配新账号</a>
            </div>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/rbac/dealerGroup" styleId="dealerGroupForm" method="post" onsubmit="return false">
			<input type="hidden" name="method" id="method" value="addUserEntry" />
			<input type="hidden" name="dealerGroup.id" id="dealerGroup.id" value="<c:out value="${groupId}"/>"/>
		    <div class="public-main-input ly-col-1 hidden abs">
			 <div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">用户名：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="userName" styleId="userName"/>
                        </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden"></div>
                        <div class="input block fl hidden"></div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"></div>
                        <div class="input block fl hidden"></div>
                    </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query">查询</a>
            </div>
		</div>
			<div class="public-main-table hidden abs">
				<div class="ly-cont">
					<table class="t-table" border="0" cellspacing="0" cellpadding="0">
						<thead class="t-thead">
							<tr class="t-tr">
								<th class="t-th">
									<label for="checkAllValue"></label>
									<input type="checkbox" name="checkAllValue" id="checkAllValue" class="checkbox_title" onClick="checkAll('userIds')">
								</th>
								<th class="t-th">序号</th>
								<th class="t-th">用户名</th>
							</tr>
						</thead>
						<c:if test="${!empty list}">
						  <tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td">
										<input type="checkbox" id="userIds" name="userIds" value="<c:out value='${row.id}'/>">
									</td>
									<td class="t-td"><c:out value="${index+1}"/></td>
									<td class="t-td"><c:out value="${row.userName}"/></td>
									
								</tr>
							</logic:iterate>
						 </tbody>
					  </c:if>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<a href="javascript:doSave();" class="button btn-add fl">保存</a>
				<a href="javascript:doReturn();" class="button btn-add fl">返回</a>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_TAGBAR.getCode()%>" tableName="pageList" action="/rbac/dealerGroup.do?method=addUserEntry"/>
				</div>
			</div>
		</html:form>
	</div>
</div>
<script src="/pagejs/scrollbar.js"></script>
</body>
</html>