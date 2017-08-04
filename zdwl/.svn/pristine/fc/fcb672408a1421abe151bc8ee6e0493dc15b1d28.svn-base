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
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
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

//已审批列表
function goAlreadyApproval(){
	location="<url:context/>/notice/noticeContent.do?method=noticeContentList&query.pageType=2&query.contentType="+$("#contentType").val();
}

//进入新增页面
function goAdd(){
	location="<url:context/>/notice/noticeContent.do?method=addNoticeContentEntry&noticeContent.contentType="+$("#contentType").val()+"&query.contentType="+$("#contentType").val();
}
//进入修改页面
function goUpd(id){
	location = "<url:context/>/notice/noticeContent.do?method=updNoticeContentEntry&noticeContent.id="+id+"&query.contentType="+$("#contentType").val();
}
function goDetail(id){
	location = "<url:context/>/notice/noticeContent.do?method=detail&noticeContent.id="+id+"&query.contentType="+$("#contentType").val();
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/notice/noticeContent.do?method=delNoticeContent&noticeContent.id="+id+"&query.contentType="+$("#contentType").val();
	}
}

//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("query.title").value="";
}

function doSubmit(id){
	if(confirm("确定要提交吗？")){
		location.href="<url:context/>/notice/noticeContent.do?method=submit&noticeContent.id="+id+"&query.contentType="+$("#contentType").val();;
	}
}

function approval(id){
	location.href="<url:context/>/notice/noticeContent.do?method=preApproval&noticeContent.id="+id+"&query.contentType="+$("#contentType").val();
}

//导航栏标签 序号19
		function add_nav(){ 
		var messagequery_list = document.getElementsByClassName("crumbs-link");
		for(var i=0;i<messagequery_list.length;i++){
		if(getElement("contentType").value == 1){
			messagequery_list[0].innerText="公告＞公告管理";
		}
		if(getElement("contentType").value != 1){
			messagequery_list[0].innerText="监管员信息管理>制度文件管理";
		}
		if(getElement("contentType").value == 2){
			messagequery_list[0].innerText="制度文件管理>制度文件管理";
		}
			}
	} 
</script>
</head>
<body class="h-100 public" onload="add_nav()">
	<div class="add_nav">
		<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#"></a>
         </span>
	</div>
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
        <div class="public-bar-tab fr hidden clearfix">
            <div class="ly-button-w fr">
                <a class="button btn-sel fl" href="javascript:void(0);">待审批</a>
                <a class="button fl" href="javascript:goAlreadyApproval();">全部公告</a>
            </div>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/notice/noticeContent.do" styleId="noticeContentForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="noticeContentList"/>
			<html:hidden property="query.contentType"  styleId="contentType"/>
			<div class="public-main-input ly-col-1 hidden abs">
				<div class="ly-input-w" style="margin-top:30px;margin-bottom:-10px">
					<div class="ly-row clearfix">
						<div class="ly-col fl">
	                        <div class="label block fl hidden">标题：</div>
	                        <div class="input block fl hidden">
	                        	<html:text styleClass="ly-bor-none" property="query.title" styleId="query.title"/>
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
								<th class="t-th">标题</th>
								<th class="t-th">审批状态</th>
								<th class="t-th">下一审批人	</th>
								<th class="t-th">创建人</th>
								<th class="t-th">创建时间</th>
						      	<th class="t-th">操作</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}"/></td>
									<td class="t-td"><c:out value="${row.title}" /></td>
									<td class="t-td"><select:approvalState type="${row.approvalState }"></select:approvalState></td>
									<td class="t-td"><select:nextApprovalName roleId="${row.nextApproval }"></select:nextApprovalName></td>
									<td class="t-td"><select:user userid="${row.createUserId }"></select:user></td>
									<td class="t-td"><select:timestamp timestamp="${row.createDate }" idtype="date"/></td>
									<td class="t-td">
										<a href="javascript:goDetail('<c:out value='${row.id}'/>');">查看</a>&nbsp;
										<c:if test="${row.approvalState==0||row.approvalState==2 && noticeContentForm.query.currRole !=80}">
											<a href="javascript:goUpd('<c:out value='${row.id}'/>');">修改</a>&nbsp;
											<a href="javascript:doDel('<c:out value='${row.id}'/>');">删除</a>&nbsp;
											<a href="javascript:doSubmit('<c:out value="${row.id }"/>')">提交</a>
										</c:if>
										<%-- <c:if test="${row.createUserId != noticeContentForm.query.currUserId && noticeContentForm.query.currRole !=80}">
											<a href="javascript:approval('<c:out value="${row.id }"/>')">审批</a>&nbsp;
										</c:if> --%>
										<c:if test="${row.approvalState==3 && row.createUserId != noticeContentForm.query.currUserId && noticeContentForm.query.currRole !=80 }">
											<a href="javascript:approval('<c:out value="${row.id }"/>')">审批</a>&nbsp;
										</c:if>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
			
			     <c:if test="${noticeContentForm.query.currRole !=80 && noticeContentForm.query.currRole !=-1}">
				    <a href="javascript:goAdd();" class="button btn-add fl">新增</a>
				</c:if>
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="NoticeContentList" action="/notice/noticeContent.do?method=NoticeContentList"/>
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