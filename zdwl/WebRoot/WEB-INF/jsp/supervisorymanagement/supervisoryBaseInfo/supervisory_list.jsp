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

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
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
	.public-main-table .t-td {
		max-width: 500px;
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
}

//进入新增页面
function goAdd(){
	window.location="<url:context/>/supervisory.do?method=addSupervisoryBaseInfoEntity";
}

//进入修改页面
function goUpd(id){
	window.location="<url:context/>/supervisory.do?method=updSupervisoryBaseInfoEntity&baseInfo.id="+id;
}

//进入详情页面
function goDetail(id){
	window.location="<url:context/>/supervisory.do?method=supervisoryDetailEntity&baseInfo.id="+id;
}

//进入详情页面
function goDownLoad(id){
	window.location="<url:context/>/supervisory.do?method=downLoad&baseInfo.id="+id;
}
//进入教育状况列表页
function goEducationList(id){
	window.location="<url:context/>/supervisory.do?method=getEducationListBySupervisoryId&baseInfo.id="+id;
}
//进入工作经历列表页
function goWorkExperienceList(id){
	window.location="<url:context/>/supervisory.do?method=getWorkExperienceListBySupervisoryId&baseInfo.id="+id;
}
//进入家庭状况列表页
function goFamilyList(id){
	window.location="<url:context/>/supervisory.do?method=getFamilyListBySupervisoryId&baseInfo.id="+id;
}
//进入招聘渠道列表页
function goRecommendList(id){
	window.location="<url:context/>/supervisory.do?method=getRecommendBySupervisoryId&baseInfo.id="+id;
}


//执行删除操作
function doDel(id){
	if(confirm("确认删除？")){
		window.location="<url:context/>/supervisory.do?method=delSupervisory&baseInfo.id="+id;
	}
}

//执行启用操作
function doUsing(id){
	if(confirm("确认启用？")){
		location="<url:context/>/supervisory.do?method=updSupervisoryState&baseInfo.id="+id+"&baseInfo.status=<%=StateConstants.USING.getCode()%>";
	}
}

//执行停用操作
function doUnused(id){
	if(confirm("确认停用？")){
		location="<url:context/>/supervisory.do?method=updSupervisoryState&baseInfo.id="+id+"&baseInfo.status=<%=StateConstants.UNUSED.getCode()%>";
	}
}
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空地区名称输入框
	getElement("query.name").value="";
	getElement("query.job").value="";
	getElement("query.entryTime").value="";
	getElement("query.educationBackground").value="";
}
function goImport(){
		location="<url:context/>/supervisory.do?method=importExcelEntry";
	}
	$(function(){
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
<body class="h-100 public" onLoad="doLoad()">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
<c:set var="using"><%=StateConstants.USING.getCode()%></c:set>
<c:set var="unused"><%=StateConstants.UNUSED.getCode()%></c:set>
<c:set var="menu"><%=RbacConstants.RESOURCE_NODE_TYPE_MENU.getCode()%></c:set>
<c:set var="node"><%=RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()%></c:set>
<div class="public-bar hidden">
	<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">监管员信息管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">监管员招聘管理</a>
         </span>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/supervisory" styleId="supervisoryForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="supervisoryPageList"/>
		<div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">姓名：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="query.name" styleId="query.name"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">应聘岗位：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="query.job" styleId="query.job"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">入职时间：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="query.entryTime" styleId="query.entryTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">学历：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="query.educationBackground" styleId="query.educationBackground"/>
                        </div>
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
							  <th class="t-th">姓名</th>
						      <th class="t-th">应聘岗位</th>
						      <th class="t-th">入职时间</th>
						      <th class="t-th">学历</th>
						      <th class="t-th">联系电话</th>
						      <th class="t-th">现住址（省）</th>
						      <th class="t-th">现住址（市）</th>
						      <th class="t-th">现住址（区/县）</th>
						      <th class="t-th">籍贯</th>
						      <th class="t-th">政治面貌</th>
						      <th class="t-th">创建人</th>
						      <th class="t-th">创建时间</th>
						      <th class="t-th">最后更新人</th>
						      <th class="t-th">最后更新时间</th>
						      <th class="t-th">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.superVisorBaseInfo.name}" /></td>
								<td class="t-td"><c:out value="${row.superVisorBaseInfo.job}" /></td>
								<td class="t-td"><select:timestamp timestamp="${row.superVisorBaseInfo.entryTime}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.superVisorBaseInfo.educationBackground}" /></td>
								<td class="t-td"><c:out value="${row.superVisorBaseInfo.selfTelephone}" /></td>
								<td class="t-td"><c:out value="${row.superVisorBaseInfo.liveAddressProvince}" /></td>
								<td class="t-td"><c:out value="${row.superVisorBaseInfo.liveAddressCity}" /></td>
								<td class="t-td"><c:out value="${row.superVisorBaseInfo.liveAddressCounty}" /></td>
								<td class="t-td"><c:out value="${row.superVisorBaseInfo.nativePlace}" /></td>
								<td class="t-td"><c:out value="${row.superVisorBaseInfo.politicsStatus}" /></td>
								<td class="t-td"><select:user userid="${row.superVisorBaseInfo.createUser }"></select:user></td>
								<td class="t-td"><select:timestamp timestamp="${row.superVisorBaseInfo.createTime}" idtype="date"/></td>
								<td class="t-td"><select:user userid="${row.superVisorBaseInfo.lastModifyUser }"></select:user></td>
								<td class="t-td"><select:timestamp timestamp="${row.superVisorBaseInfo.lastModifyTime}" idtype="date"/></td>
								<td class="t-td">
									<a href="javascript:goEducationList('<c:out value='${row.superVisorBaseInfo.id}'/>');">教育状况</a>&nbsp;
									<a href="javascript:goWorkExperienceList('<c:out value='${row.superVisorBaseInfo.id}'/>');">工作经历</a>&nbsp;
									<a href="javascript:goFamilyList('<c:out value='${row.superVisorBaseInfo.id}'/>');">家庭状况</a>&nbsp;
									<a href="javascript:goRecommendList('<c:out value='${row.superVisorBaseInfo.id}'/>');">招聘渠道</a>&nbsp;
									<a href="javascript:goDetail('<c:out value='${row.superVisorBaseInfo.id}'/>');">详情</a>&nbsp;
									<a href="javascript:goDownLoad('<c:out value='${row.superVisorBaseInfo.id}'/>');" class="yc">下载</a>&nbsp;
									<a href="javascript:goUpd('<c:out value='${row.superVisorBaseInfo.id}'/>');" class="yc">修改</a>&nbsp;
									<a href="javascript:doDel('<c:out value='${row.superVisorBaseInfo.id}'/>');" class="yc">删除</a>&nbsp;
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<a href="javascript:goAdd();" class="button btn-add fl yc">新增</a>
			<a href="javascript:goImport();" class="button btn-add fl yc">导入监管员基本信息</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="supervisoryList" action="/supervisory.do?method=supervisoryPageList"/>
			</div>
			<div id="message"  style="display:none;color: red"></div>
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