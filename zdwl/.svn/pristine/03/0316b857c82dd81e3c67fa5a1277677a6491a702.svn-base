<%@page import="com.zd.csms.rbac.constants.RoleConstants"%>
<%@page import="com.zd.csms.rbac.util.UserSession"%>
<%@page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
<%@page import="com.zd.csms.bank.contants.BankContants"%>
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
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
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
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
function detail(id){
	location.href="<url:context/>/ledger/mailcost.do?method=mailcostDetail&mailcost.id="+id;
}
//执行查询操作
function doQuery(){
	$("#method").val("mailcostLedger");
	document.forms[0].submit();
}
function goExport(){
	$("#method").val("export");
	document.forms[0].submit();
}
//执行表单清空操作
function doClear(){
	$("#fqdate").val("");
	var checkboxs = document.getElementsByName('mailcostquery.mailingitems');
	for(var i=0; i<checkboxs.length;i++){
		checkboxs[i].checked=false;//全不选
	} 
	$("#transportbegin").val("");
	$("#transportend").val("");
	$("#mailperson").combobox('select',-1);
}


function init() {
	$("#mailperson").combobox({
		onHidePanel : function() {// 验证输入的值，如果不存在下拉列表 则置空
			var newValue = $(this).combobox('getValue');
			var data = $(this).combobox('getData');
			var flag = false;
			if (data != null && data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (newValue == data[i].value) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				$(this).combobox('clear');
			}

		}
	});
	var draftValue = $("#mailperson").combobox('getValue');
}

$(function(){
	init();
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
<body class="h-100 public">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">台账管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">邮寄费用申请台账</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/ledger/mailcost" styleId="mailcostForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="mailcostLedger" />
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">发起日期：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="mailcostquery.fqdate" styleId="fqdate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                        </div>
                    </div>
                    <div class="ly-col fl"  style="width: 50%;">
                        <div class="label block fl hidden"  style="width:15%;">邮寄项目：</div>
                        <div class="input block fl hidden" style="width: 85%;" >
                        	<form:checkboxs property="mailcostquery.mailingitems" styleId="mailingitems" collection="mailcostStateOptions"/>
                        </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					 <div class="ly-col fl">
                        <div class="label block fl hidden">运输城市(起)：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="mailcostquery.transportbegin" styleId="transportbegin"/>
                        </div>
                    </div>
                     <div class="ly-col fl">
                        <div class="label block fl hidden">运输城市(止)：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="mailcostquery.transportend" styleId="transportend"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">邮寄人：</div>
                        <div class="input block fl hidden add_easyui">
							<select  style="width:99%" class="ly-sel-w" name="mailcostquery.mailperson" id="mailperson" >
								<option value="-1">请选择</option>
							    <c:forEach items="${superviseOptions}" var="row">
										<option <c:if test="${mailcostForm.mailcostquery.mailperson==row.value}">selected='selected'</c:if> value="<c:out value='${row.value }'/>"><c:out value="${row.label }"/></option>
									</c:forEach>
								
							<select>
                        </div>
                    </div>
                     <div class="ly-col fl">
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
								<th class="t-th">发起时间</th>
								<th class="t-th">邮寄项目</th>
								<th class="t-th">配件</th>
								<th class="t-th">其他</th>
								<th class="t-th">邮寄人</th>
								<th class="t-th">快递公司</th>
								<th class="t-th">预计费用</th>
								<th class="t-th">运输城市(起)</th>
								<th class="t-th">运输城市(止)</th>
								<th class="t-th">接收人</th>
								<th class="t-th">事件描述</th>
								<th class="t-th">审批状态</th>
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
									<td class="t-td">&nbsp;<c:out value="${index+1}" /></td>
									<td class="t-td"><select:timestamp timestamp="${row.fqdate}" idtype="date"/></td>
									<td class="t-td"><c:out value="${row.mailingitems}" /></td>
									<td class="t-td"><c:out value="${row.parts}" /></td>
									<td class="t-td"><c:out value="${row.other}" /></td>
									<td class="t-td"><select:supervisorName repositoryId="${row.mailperson}"idtype="name"supervisorId=""/></td>
									<td class="t-td"><c:out value="${row.express}" /></td>
									<td class="t-td"><c:out value="${row.money}" /></td>
									<td class="t-td"><c:out value="${row.transportbegin}" /></td>
									<td class="t-td"><c:out value="${row.transportend}" /></td>
									<td class="t-td"><select:supervisorName repositoryId="${row.receiveid}"idtype="name"supervisorId=""/></td>
									<td class="t-td"><c:out value="${row.des}" /></td>
									<td class="t-td"><select:approvalState type="${row.approvalState }"></select:approvalState></td>
									<td class="t-td"><select:user userid="${row.createuserid}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
									<td class="t-td"><select:user userid="${row.upduserid}"/></td>
									<td class="t-td"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
									<td class="t-td">
										<a href="javascript:detail('<c:out value="${row.id }"/>')">详情</a>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
		<div class="public-main-footer hidden abs">
			<a href="javascript:goExport();" class="button btn-add fl yc">导出</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="mailcostLedger" action="/ledger/mailcost.do?method=mailcostLedger" />
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