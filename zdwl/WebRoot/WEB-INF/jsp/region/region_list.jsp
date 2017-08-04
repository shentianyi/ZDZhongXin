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
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>

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
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
	restrict();
}

//进入新增页面
function goAdd(){
	var parentId=document.getElementById("parentId").value;
	window.location="<url:context/>/region/region.do?method=addRegionEntity&region.parentId="+parentId;
}

//进入修改页面
function goUpd(id){
	window.location="<url:context/>/region/region.do?method=updRegionEntity&region.id="+id;
}

//进入新增 下级资源页面
function goAddChild(id){
	location = "<url:context/>/region/region.do?method=addRegionEntity&region.parentId="+id;
}
function goFindChild(id){
	location = "<url:context/>/region/region.do?method=findChildList&query.parentId="+id;
}
//执行删除操作
function doDel(id){
	if(confirm("确认删除？")){
		window.location="<url:context/>/region/region.do?method=delRegion&region.id="+id;
	}
}

//执行启用操作
function doUsing(id){
	if(confirm("确认启用？")){
		location="<url:context/>/region/region.do?method=updRegionState&region.id="+id+"&region.status=<%=StateConstants.USING.getCode()%>";
	}
}

//执行停用操作
function doUnused(id){
	if(confirm("确认停用？")){
		location="<url:context/>/region/region.do?method=updRegionState&region.id="+id+"&region.status=<%=StateConstants.UNUSED.getCode()%>";
	}
}
//执行返回列表操作
function doReturn(){
	var grandparentId=document.getElementById("grandparentId").value;
	var grandparentLevel=document.getElementById("grandparentLevel").value;
	location = "<url:context/>/region/region.do?method=regionPageList&query.parentId="+grandparentId+"&query.regionLevel="+grandparentLevel;
}

//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空地区名称输入框
	getElement("query.name").value="";
	getElement("query.parentName").value="";
	//清空状态复选框
	var elements = document.getElementsByName("query.status");
	for(var i = 0; i < elements.length; i++){
		elements[i].checked = false;
	}
	//清空状态复选框
	var elements = document.getElementsByName("query.regionLevel");
	for(var i = 0; i < elements.length; i++){
		elements[i].checked = false;
	}
	
}
function restrict(){
    var crole = $("#crole").val();
    if(crole == 30){
        $(".yc").hide();
    }
}
</script>

</head>
<body class="h-100 public" onLoad="doLoad()">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
<c:set var="using"><%=StateConstants.USING.getCode()%></c:set>
<c:set var="unused"><%=StateConstants.UNUSED.getCode()%></c:set>
<c:set var="menu"><%=RbacConstants.RESOURCE_NODE_TYPE_MENU.getCode()%></c:set>
<c:set var="node"><%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%></c:set>
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">系统管理与配置</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">地区管理</a>
         </span>
</div>

<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/region/region" styleId="regionForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="regionPageList"/>
		<input type="text" hidden="hidden" id="parentId"  value="<c:out value='${regionForm.query.parentId}'/>" />
		<input type="text" hidden="hidden" id="grandparentId"  value="<c:out value='${grandparentId}'/>" />
		<input type="text" hidden="hidden" id="grandparentLevel"  value="<c:out value='${grandparentLevel}'/>" />
		<div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">地区名称：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="query.name" styleId="query.name"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">上级地区名称：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="query.parentName" styleId="query.parentName"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">地区状态：</div>
                        <div class="input block fl hidden">
                            <form:checkboxs property="query.status" collection="stateOptions" styleId="query.status"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"></div>
                        <div class="input block fl hidden"></div>
                    </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
            </div>
		</div>
		<div class="public-main-table hidden abs">
			<div class="ly-cont">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							  <th class="t-th">序号</th>
							  <th class="t-th">名称</th>
						      <th class="t-th">级别</th>
						      <th class="t-th">状态</th>
						      <th class="t-th">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.name}" /></td>
								<td class="t-td"><c:out value="${row.regionLevel}" /></td>
								<td class="t-td"><select:stateName state="${row.status}"/></td>
								<td class="t-td">
									<c:if test="${row.regionLevel==1}">
										<a href="javascript:goFindChild('<c:out value='${row.id}'/>');">查看下级地区</a>&nbsp;
										<a href="javascript:goAddChild('<c:out value='${row.id}'/>');" class="yc">新增下级地区</a>&nbsp;
									</c:if>
									<c:if test="${row.regionLevel==2}">
										<a href="javascript:goFindChild('<c:out value='${row.id}'/>');">查看下级地区</a>&nbsp;
										<a href="javascript:goAddChild('<c:out value='${row.id}'/>');" class="yc">新增下级地区</a>&nbsp;
									</c:if>
									<c:if test="${row.status==using}">
										<a href="javascript:doUnused('<c:out value='${row.id}'/>');" class="yc">停用</a>&nbsp;
									</c:if>
									<c:if test="${row.status!=using}">
										<a href="javascript:doUsing('<c:out value='${row.id}'/>');" class="yc">启用</a>&nbsp;
									</c:if>
									
									<a href="javascript:goUpd('<c:out value='${row.id}'/>');" class="yc">修改</a>&nbsp;
									
									<c:if test="${row.status==unused}">
										<a href="javascript:doDel('<c:out value='${row.id}'/>');" class="yc">删除</a>
									</c:if>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<a href="javascript:goAdd();" class="button btn-add fl yc">新增</a>
			<c:if test="${grandparentLevel!=0}">
				<a href="javascript:doReturn();" class="button btn-add fl">返回</a>
			</c:if>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="regionList" action="/region/region.do?method=regionPageList"/>
			</div>
		</div>
		</html:form>
	</div>
</div>
<script type="text/javascript">
    var ele = $('#ly-table-thead-w'),
        template;

    $('.public-main-table .ly-cont').perfectScrollbar({
        cursorwidth: 10,
        cursorborder: "none",
        cursorcolor: "#999",
        hidecursordelay: 0,
        zindex: 10001,
        horizrailenabled: true,
        callbackFn: function(){
            if (parseInt($('#ascrail2000').find('div').css('top')) > 0) {
                if (ele.length === 0) {
                    var tr = $('.public-main-table .t-tbody tr'),
                        width = 0;

                    // 生成thead区块
                    template = '<div id="ly-table-thead-w"><div class="ly-table-scroll">';
                    $('.public-main-table .t-thead th').each(function(key){
                        template += '<div class="block fl">'+ $(this).text() +'</div>';
                    });
                    template += '</div></div>';

                    ele = $(template).css({
                        position: 'absolute',
                        top: 0,
                        left: 0,
                        width: '100%',
                        height: '36px',
                        overflow: 'hidden'
                    });

                    // 复制操作
                    tr.eq(0).find('td').each(function(key){
                        var _width = $(this).width() + 1;

                        ele.find('.block').eq(key).css({
                            width: _width,
                            padding: '0 5px',
                            height: '36px',
                            lineHeight: '36px',
                            fontSize: '14px',
                            fontFamily: 'Microsoft Yahei',
                            textAlign: 'center',
                        });
                        width += _width + 10;
                    });
                    ele.find('.ly-table-scroll').css({
                        position: 'relative',
                        width: width,
                        height: '100%',
                        background: '#eee'
                    });

                    $('.public-main-table .ly-cont').append(ele);
                } else {
                    ele.show();
                };
            } else {
                ele.hide();
            };
        },
        _callbackFn: function(left){
            ele.find('.ly-table-scroll').css('left', -left);
        }
    });

</script>
</body>
</html>