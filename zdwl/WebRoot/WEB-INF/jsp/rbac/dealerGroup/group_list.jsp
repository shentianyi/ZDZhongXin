<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ page import="com.zd.csms.constants.Constants"%>
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
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script>
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//新增
function goAdd(){
	location="<url:context/>/rbac/dealerGroup.do?method=groupEntry";
}

//修改
function goUpd(id){
	location = "<url:context/>/rbac/dealerGroup.do?method=groupEntry&dealerGroup.id="+id;
}



//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/rbac/dealerGroup.do?method=delGroup&dealerGroup.id="+id;
	}
}


//为经销商集团分配经销商
function setDealer(groupId){
	location = "<url:context/>/rbac/dealerGroup.do?method=dealerEntry&dealerGroup.id="+groupId ;
}

//为经销商集团分配账号
function setUser(groupId){
	location = "<url:context/>/rbac/dealerGroup.do?method=userEntry&dealerGroup.id="+groupId ;
}

</script>
</head>
<body class="h-100 public" >
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">系统管理与配置</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">经销商集团管理</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/rbac/dealerGroup" styleId="dealerGroupForm" method="post" onsubmit="return false">
		 <input name="method"  type="hidden" value="groupList"/>
	      <div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">名称：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="name" styleId="name"/>
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
							  <th class="t-th">序号</th>
							  <th class="t-th">名称</th>
						      <th class="t-th">操作</th>
						</tr>
					</thead>
				     <tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.name}" /></td>
							
								<td class="t-td">
								    <a href="javascript:setDealer('<c:out value='${row.id}'/>');">分配经销商</a>&nbsp;
									<a href="javascript:setUser('<c:out value='${row.id}'/>');">分配账号</a>&nbsp;
									<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
									<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>
									
								</td>
							</tr>
						</logic:iterate>
					</tbody> 
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<a href="javascript:goAdd();" class="button btn-add fl">新增</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="pageList" action="/rbac/dealerGroup.do?method=groupList"/>
			</div>
		</div>
		</html:form>
	</div>
</div>
<script src="/pagejs/scrollbar.js"></script>
</body>
</html>