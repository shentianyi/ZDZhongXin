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
<script type="text/javascript" src="js/jquery.divscroll.js"></script>
<script>
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//进入新增页面
function goAdd(){
	location="<url:context/>/refundInvoice.do?method=addRefundInvoiceEntry";
}

//进入修改页面
function goUpd(id){
	location = "<url:context/>/refundInvoice.do?method=updRefundInvoiceEntry&refundInvoice.id="+id;
}

//执行删除操作
function doDel(id){
	if(confirm("删除后数据不可恢复\n是否继续？")){
		location="<url:context/>/refundInvoice.do?method=delRefundInvoice&refundInvoice.id="+id;
	}
}

//执行查询操作
function doQuery(){
	document.forms[0].submit();
}

//执行表单清空操作
function doClear(){
	//清空资源名输入框
	getElement("refundInvoicequery.distribid").value="";
	getElement("refundInvoicequery.financial_institution").value="";
	getElement("refundInvoicequery.bank").value="";
	getElement("refundInvoicequery.brand").value="";
	getElement("refundInvoicequery.intime").value="";
	getElement("refundInvoicequery.supervisionfee_standard").value="";
	getElement("refundInvoicequery.payment").value="";
	getElement("refundInvoicequery.pay_standard").value="";
	getElement("refundInvoicequery.pay_time").value="";
	getElement("refundInvoicequery.refund_time").value="";
	getElement("refundInvoicequery.refund_money").value="";
	getElement("refundInvoicequery.isinvoice").value="";
	getElement("refundInvoicequery.invoice_time").value="";
	getElement("refundInvoicequery.invoice_company").value="";
	getElement("refundInvoicequery.invoice_type").value="";
}
</script>
</head>
<body class="h-100 public" onLoad="doLoad()">
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
		<div class="public-bar-crumbs fl hidden">
            <a class="crumbs-link" href="#">财务管理</a>
            > 
            <a class="crumbs-link" href="#">退款发票管理</a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/refundInvoice" styleId="refundInvoiceForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="refundInvoiceList"/>
		<div class="public-main-input ly-col-3 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">经销商：</div>
                        <div class="input block fl hidden">
                        	<form:select styleClass="ly-bor-none" property="refundInvoicequery.distribid" styleId="refundInvoicequery.distribid">
								<html:option value="-1">请选择</html:option>
								<html:optionsCollection name="dealersOptions" label="label" value="value" />
							</form:select>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">金融机构：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="refundInvoicequery.financial_institution" styleId="refundInvoicequery.financial_institution"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">合作银行：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="refundInvoicequery.bank" styleId="refundInvoicequery.bank"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">品牌：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="refundInvoicequery.brand" styleId="refundInvoicequery.brand"/>
                        </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">进店时间：</div>
                        <div class="input block fl hidden">
                            <form:calendar property="refundInvoicequery.intime" styleId="refundInvoicequery.intime" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
                        </div>
                    </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">监管费标准：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="refundInvoicequery.supervisionfee_standard" styleId="refundInvoicequery.supervisionfee_standard"/>
                        </div>
                    </div>
                   <div class="ly-col fl">
                        <div class="label block fl hidden">付费方式：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="refundInvoicequery.payment" styleId="refundInvoicequery.payment"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">缴费标准：</div>
                        <div class="input block fl hidden">
                            <html:text styleClass="ly-bor-none" property="refundInvoicequery.pay_standard" styleId="refundInvoicequery.pay_standard"/>
                        </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">缴费时间：</div>
                        <div class="input block fl hidden">
							<form:calendar property="refundInvoicequery.pay_time" styleId="refundInvoicequery.pay_time" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
                        </div>
                    </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">退费时间：</div>
                        <div class="input block fl hidden">
                            <form:calendar property="refundInvoicequery.refund_time" styleId="refundInvoicequery.refund_time" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">退费金额：</div>
                        <div class="input block fl hidden">
                        	<html:text styleClass="ly-bor-none" property="refundInvoicequery.refund_money" styleId="refundInvoicequery.refund_money"/>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">收费时间：</div>
                        <div class="input block fl hidden">
                        	<form:calendar property="refundInvoicequery.invoice_time" styleId="refundInvoicequery.invoice_time" pattern="<%=PatternConstants.TIMESTAMP_SS.getCode()%>" readonly="true" />
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
							  <th class="t-th">经销商</th>
						      <th class="t-th">金融机构</th>
						      <th class="t-th">合作银行</th>
						      <th class="t-th">品牌</th>
						      <th class="t-th">进店时间</th>
						      <th class="t-th">监管费标准</th>
						      <th class="t-th">付费方式</th>
						      <th class="t-th">缴费标准</th>
						      <th class="t-th">缴费时间</th>
						      <th class="t-th">缴费金额</th>
						      <th class="t-th">退费时间</th>
						      <th class="t-th">退费金额</th>
						      <th class="t-th">退费原因</th>
						      <th class="t-th">是否收到退款发票</th>
						      <th class="t-th">收票时间</th>
						      <th class="t-th">开票单位</th>
						      <th class="t-th">发票类型</th>
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
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><select:dealerName dealerid="${row.distribid}"/></td>
								<td class="t-td"><c:out value="${row.financial_institution}" /></td>
								<td class="t-td"><c:out value="${row.bank}" /></td>
								<td class="t-td"><c:out value="${row.brand}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.intime}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.supervisionfee_standard}"/></td>
								<td class="t-td"><c:out value="${row.payment}"/></td>
								<td class="t-td"><c:out value="${row.pay_standard}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.pay_time}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.pay_money}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.refund_time}" idtype="date"/></td>
								<td class="t-td"><c:out value="${row.refund_money}"/></td>
								<td class="t-td"><c:out value="${row.refund_des}"/></td>
								<td class="t-td"><c:out value="${row.isinvoice}"/></td>
								<td class="t-td"><c:out value="${row.invoice_time}"/></td>
								<td class="t-td"><c:out value="${row.invoice_company}"/></td>
								<td class="t-td"><c:out value="${row.invoice_type}"/></td>
								<td class="t-td"><select:user userid="${row.createuserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.createdate}" idtype="date"/></td>
								<td class="t-td"><select:user userid="${row.upduserid}"/></td>
								<td class="t-td"><select:timestamp timestamp="${row.upddate}" idtype="date"/></td>
								<td class="t-td">
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
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="refundInvoiceList" action="/refundInvoice.do?method=refundInvoiceList"/>
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