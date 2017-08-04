﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>
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
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
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
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("agreementBackquery.distribname").value="";
	getElement("agreementBackquery.agreement_num").value="";
	$('#agreement_date').datebox('clear'); 
	getElement("agreementBackquery.isback").value=-1;
	$('#back_date').datebox('clear'); 
	$("#bankName").val("");
}
$(function(){
	var dateboxOptions={"editable":false}
	$('#agreement_date').datebox(dateboxOptions);
	$('#back_date').datebox(dateboxOptions); 
});
//导出execl
function goExt(){
    $("#method").val("ExtAgreementLedger");
    document.forms[0].submit();
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
<body class="h-100 public">
<input type="hidden" value="<%= UserSessionUtil.getUserSession(request).getUser().getClient_type()%>" id="crole" />
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">台帐管理</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">储备库</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/ledger/agreement" styleId="agreementBackForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="agreementLedger"/>
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">经销商：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="agreementBackquery.distribname" styleId="agreementBackquery.distribname"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">协议编号：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="agreementBackquery.agreement_num" styleId="agreementBackquery.agreement_num"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">协议邮寄时间：</div>
                        <div class="input block fl hidden" style="padding:5px 0 0 10px;">
                           <input id="agreement_date" name="agreementBackquery.agreement_date" type="text" 
                        	value='<fmt:formatDate value="${agreementBackForm.agreementBackquery.agreement_date }" pattern="yyyy-MM-dd" />' ></input>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">是否回收：</div>
                        <div class="input block fl hidden">
                            <form:select styleClass="ly-bor-none" property="agreementBackquery.isback" styleId="agreementBackquery.isback">
								<html:option value="-1">请选择</html:option>
								<html:option value="2">是</html:option>
								<html:option value="1">否</html:option>
							</form:select>
                        </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					
					<div class="ly-col fl">
                        <div class="label block fl hidden">收回时间：</div>
                        <div class="input block fl hidden" style="padding:5px 0 0 10px;">
                        	<input id="back_date" name="agreementBackquery.back_date" type="text" 
                        	value='<fmt:formatDate value="${agreementBackForm.agreementBackquery.back_date }" pattern="yyyy-MM-dd" />' ></input>
                        </div>
                    </div>
                   <div class="ly-col fl">
                        <div class="label block fl hidden">金融机构：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="agreementBackquery.bankName" styleId="bankName"/>
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
							  <th class="t-th">经销商</th>
						      <th class="t-th">金融机构</th>
						      <th class="t-th">品牌</th>
						      <th class="t-th">省</th>
						      <th class="t-th">市</th>
						      <th class="t-th">金融机构联系人</th>
						      <th class="t-th">联系方式</th>
						      <th class="t-th">协议编号</th>
						      <th class="t-th">协议邮寄时间</th>
						      <th class="t-th">邮寄地址</th>
						      <!-- <th class="t-th">邮寄时间</th> -->
						      <th class="t-th">是否回收</th>
						      <th class="t-th">收回时间</th>
						      <th class="t-th">协议签署日期</th>
						      <th class="t-th">协议到期日</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.dealerName }"/></td>
								<td class="t-td"><c:out value="${row.bankName }"/></td>
								<td class="t-td"><c:out value="${row.brandName }"/></td>
								<td class="t-td"><c:out value="${row.province }"/></td>
								<td class="t-td"><c:out value="${row.city }"/></td>
								<td class="t-td"><c:out value="${row.bankPerson }"/></td>
								<td class="t-td"><c:out value="${row.bankPersonPhone }"/></td>
								<td class="t-td"><c:out value="${row.agreement_num}" /></td>
								<td class="t-td"><select:timestamp timestamp="${row.agreement_date}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.send_address}"/></td>
								<%-- <td class="t-td"><select:timestamp timestamp="${row.send_date}" idtype="date"/></td> --%>
								<td class="t-td">
									<c:if test="${row.isback == 1}">
										否
									</c:if>
									<c:if test="${row.isback == 2}">
										是
									</c:if>
								</td>
								<td class="t-td"><select:timestamp timestamp="${row.back_date}" idtype="date"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.agreementsigntime}" idtype="date"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.agreementexpiretime}" idtype="date"/></td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
		    <a href="javascript:goExt();" class="button btn-add fl yc">导出协议管理台账</a>
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="agreementLedger" action="/ledger/agreement.do?method=agreementLedger"/>
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