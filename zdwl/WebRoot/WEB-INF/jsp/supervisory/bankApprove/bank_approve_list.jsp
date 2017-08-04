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
<script type="text/javascript" src="js/jquery.divscroll.js"></script>
<script>
//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	$("#bankApprovequery\\.state").val("-1");
	 getElement("bankApprovequery.dealerName").value="";
	getElement("bankApprovequery.bankName").value="";
	getElement("bankApprovequery.brand").value="";
	getElement("bankApprovequery.vin").value=""; 
	
}
</script>
</head>
<body class="h-100 public">
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
		<div class="public-bar-crumbs fl hidden">
            <a class="crumbs-link" href="#">审批状态</a>
            > 
            <a class="crumbs-link" href="#"><c:out value="${type}" /></a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bankApprove" styleId="bankApproveForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="bankApproveList"/>
		<input name="bankApproveType" id="bankApproveType" type="hidden" value="<c:out value="${bankApproveType}"/>"/>
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
                    <div class="ly-col fl">
                        <div class="label block fl hidden">状态：</div>
                        <div class="input block fl hidden">
							<form:select styleClass="ly-bor-none" property="bankApprovequery.state" styleId="bankApprovequery.state">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="bankStateOptions" label="label" value="value" />
							</form:select>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">经销商：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" styleId="bankApprovequery.dealerName" property="bankApprovequery.dealerName"></html:text>
                        </div>
                    </div>
                    <div class="ly-col fl">
                         <div class="label block fl hidden">金融机构：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" styleId="bankApprovequery.bankName" property="bankApprovequery.bankName"></html:text>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">车架号：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" styleId="bankApprovequery.vin" property="bankApprovequery.vin"></html:text>
                        </div>
                    </div>
            	</div>
            	<div class="ly-row clearfix">
                    <div class="ly-col fl">
                        <div class="label block fl hidden">品牌：</div>
                        <div class="input block fl hidden">
							<html:text styleClass="ly-bor-none" styleId="bankApprovequery.brand" property="bankApprovequery.brand"></html:text>
                        </div>
                    </div>
                 </div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
                <c:if test="${list!=null&&summary!=null&&summary.count!=0 }">
						<span style="position: absolute;top:55%;left:50%; font-size:14px;">
						台数:<c:out value="${summary.count }"/>
						&nbsp;&nbsp;&nbsp;车价总金额：<fmt:formatNumber value="${summary.carMoney }" pattern="#.##" minFractionDigits="2" /> 
						<c:if test="${type == '出库'}">
						&nbsp;&nbsp;&nbsp;回款总金额：<fmt:formatNumber value="${summary.carPaymentAmount }" pattern="#.##" minFractionDigits="2" />
						</c:if>
						</span>
					</c:if>
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
						      <th class="t-th">票号</th>
						      <th class="t-th">车辆型号</th>
						      <th class="t-th">颜色</th>
						      <th class="t-th">车架号</th>
						      <th class="t-th">发动机号</th>
						      <th class="t-th">合格证号</th>
						      <th class="t-th">钥匙(数)</th>
						      <th class="t-th">单价(元)</th>
						      <th class="t-th">二网地址</th>
						      <th class="t-th">提交时间</th>
						      <th class="t-th">审批时间</th>
						      <th class="t-th">状态</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.dealerName}"/></td>
								<td class="t-td"><c:out value="${row.bankFullName}"/></td>
								<td class="t-td"><c:out value="${row.brandName}"/></td>
								<td class="t-td"><c:out value="${row.draft_num}"/></td>
								<td class="t-td"><c:out value="${row.car_model}"/></td>
								<td class="t-td"><c:out value="${row.color}"/></td>
								<td class="t-td"><c:out value="${row.vin}"/></td>
								<td class="t-td"><c:out value="${row.engine_num}"/></td>
								<td class="t-td"><c:out value="${row.certificate_num}"/></td>
								<td class="t-td"><c:out value="${row.key_amount}"/></td>
								<td class="t-td"><c:out value="${row.money}"/></td>
								<td class="t-td"><c:out value="${row.two_name}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.createtime}" idtype="ss"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.approvetime}" idtype="ss"/></td>
								<%-- <td class="t-td"><select:superviseImport sid="${row.sid}" idtype="jxs"/></td>
								<td class="t-td"><select:superviseImport sid="${row.sid}" idtype="draft_num"/></td>
								<td class="t-td"><select:superviseImport sid="${row.sid}" idtype="certificate_num"/></td>
								<td class="t-td"><select:superviseImport sid="${row.sid}" idtype="color"/></td>
								<td class="t-td"><select:superviseImport sid="${row.sid}" idtype="vin"/></td>
								<td class="t-td"><select:superviseImport sid="${row.sid}" idtype="engine_num"/></td>
								<td class="t-td"><select:superviseImport sid="${row.sid}" idtype="certificate_num"/></td>
								<td class="t-td"><select:superviseImport sid="${row.sid}" idtype="key_amount"/></td>
								<td class="t-td"><select:superviseImport sid="${row.sid}" idtype="money"/></td>
								<td class="t-td"><select:superviseImport sid="${row.sid}" idtype="two_name"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.createtime}" idtype="ss"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.approvetime}" idtype="ss"/></td> --%>
								<td class="t-td">
									<c:if test="${row.state == 1}">待审批</c:if>
									<c:if test="${row.state == 2}">审核通过</c:if>
									<c:if test="${row.state == 3}">审核不通过</c:if>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="bankApproveList" action="/bankApprove.do?method=bankApproveList"/>
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