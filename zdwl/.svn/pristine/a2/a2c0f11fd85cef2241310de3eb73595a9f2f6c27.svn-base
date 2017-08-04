<%@page import="com.zd.csms.rbac.constants.RoleConstants"%>
<%@page import="com.zd.csms.rbac.util.UserSession"%>
<%@page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
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
function goWaitApproval(){
	location.href="<url:context/>/repaircost.do?method=findList";
}

function detail(id){
	location.href="<url:context/>/repaircost.do?method=detailPage&repaircost.id="+id;
}

function doDel(id) {
	if (confirm("删除后数据不可恢复\n是否继续？")) {
		location = "<url:context/>/repaircost.do?method=delRepairCost&repaircost.id=" + id;
	}
}
//进入修改页面
function doUpd(id){
	location = "<url:context/>/repaircost.do?method=updRepairCostEntry&repaircost.id="+id;
}
function doSubmit(id){
	if(confirm("确定要提交吗？")){
		location.href="<url:context/>/repaircost.do?method=submit&repaircost.id="+id;
	}
}
</script>
</head>
<body class="h-100 public">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">资产管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">设备维修申请</a>
         </span>
</div>
<c:set var="sc"><%=RoleConstants.SUPERVISORY.getCode() %></c:set>
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
        <div class="public-bar-tab fr hidden clearfix">
            <div class="ly-button-w fr">
                <a class="button fl" href="javascript:goWaitApproval();">待审批</a>
                <a class="button btn-sel fl" href="javascript:void(0);">已审批</a>
            </div>
        </div>
	</div>
</div>
<div class="public-main abs" style="margin-top:30px;">
	<div class="ly-contai public-main-input-false rel">
		<html:form action="/repaircost" styleId="repairCostForm" method="post" onsubmit="return false">
			<input name="method" id="method" type="hidden" value="findList" />
			<div class="public-main-table hidden abs">
				<div class="ly-cont">
					<table class="t-table" border="0" cellspacing="0" cellpadding="0">
						<thead class="t-thead">
							<tr class="t-tr">
						      	<th class="t-th">序号</th>
								<th class="t-th">申报人</th>
								<th class="t-th">维修项目</th>
								<th class="t-th">维修费用</th>
								<th class="t-th">具体问题</th>
								<th class="t-th">审批状态</th>
								<th class="t-th">下一审批人</th>
								<th class="t-th">创建人</th>
								<th class="t-th">创建时间</th>
								<th class="t-th">修改人</th>
								<th class="t-th">修改时间</th>
								<th class="t-th">操作</th>
							</tr>
						</thead>
						<tbody class="t-tbody hidden">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><select:user userid="${row.promoter}"/></td>
									<td class="t-td"><c:out value="${row.repair_project}" /></td>
									<td class="t-td"><c:out value="${row.money}" /></td>
									<td class="t-td"><c:out value="${row.problem}" /></td>
									<td class="t-td"><select:approvalState type="${row.approvalState }"></select:approvalState></td>
									<td class="t-td"><select:nextApprovalName roleId="${row.nextApproval }"></select:nextApprovalName></td>
									<td class="t-td"><select:user userid="${row.createuserid}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
									<td class="t-td"><select:user userid="${row.upduserid}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
									<td class="t-td">
										<a href="javascript:detail('<c:out value="${row.id }"/>')">详情</a>
										<c:if test="${row.approvalState==2 && repairCostForm.repaircostquery.currRole == sc }">
											<a href="javascript:doDel('<c:out value="${row.id }"/>')">删除</a>
											<a href="javascript:doSubmit('<c:out value="${row.id }"/>')">提交</a>
											<a href="javascript:doUpd('<c:out value="${row.id }"/>')">更新</a>
										</c:if>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<div class="public-main-footer hidden abs">
				<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="findList" action="/repaircost.do?method=findList" />
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