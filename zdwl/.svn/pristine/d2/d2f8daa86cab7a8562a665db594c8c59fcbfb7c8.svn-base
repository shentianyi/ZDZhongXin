<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
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
<script type="text/javascript">
	function doQuery(){
		document.forms[0].submit();
	}
	
	function doClear(){
		getElement("custNo").value="";
		getElement("pledgeName").value="";
	}
	
	function updateCust(){
		$.ajax({
			   type: "POST",
			   url: "/bank/interface.do?method=updateGyl001toLocal",
			   success: function(msg){
			     	if(msg=="success"){
			     		alert("同步成功");
			     	}else{
			     		alert("同步失败");
			     	}
			   }
			});
	}
</script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
</style>
</head>
<body class="h-100 public" >
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">浙商银行对接</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">客户信息查询</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gyl001" />
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">客户号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="custNo" type="text" name="custNo" value="<c:out value='${custNo }'/>"/>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">客户名称：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="pledgeName" type="text" name="pledgeName" value="<c:out value='${pledgeName }'/>"/>
	                    </div>
                    </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
                <a href="javascript:updateCust();" class="button">手动同步</a>
            </div>
		</div>
		<div class="public-main-table hidden abs">
			<div class="ly-cont">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">客户号</th>
							<th class="t-th">客户名称</th>
							<th class="t-th">行业分类</th>
							<th class="t-th">证件类型</th>
							<th class="t-th">证件号码</th>
							<th class="t-th">地址</th>
							<th class="t-th">是否监管客户</th>
							<th class="t-th">是否授信客户</th>
							<th class="t-th">是否核心厂商</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value='${row.custNo }'/></td>
									<td class="t-td"><c:out value='${row.pledgeName }'/></td>
									<td class="t-td"><c:out value='${row.tradeName }'/></td>
									<td class="t-td">
										<c:if test="${row.idtype=='1' }">组织机构代码证</c:if>
										<c:if test="${row.idtype=='2' }">营业执照</c:if>
										<c:if test="${row.idtype=='3' }">其他组织代码（附属单位）</c:if>
										<c:if test="${row.idtype=='4' }">其它企业证件</c:if>
									</td>
									<td class="t-td"><c:out value='${row.idno }'/></td>
									<td class="t-td"><c:out value='${row.address }'/></td>
									<td class="t-td">
										<c:if test="${row.moniFlag=='1'}">是</c:if>
										<c:if test="${row.moniFlag=='0'}">否</c:if>
									</td>
									<td class="t-td">
										<c:if test="${row.creditFlag=='1'}">是</c:if>
										<c:if test="${row.creditFlag=='0'}">否</c:if>
									</td>
									<td class="t-td">
										<c:if test="${row.coreFlag=='1'}">是</c:if>
										<c:if test="${row.coreFlag=='0'}">否</c:if>
									</td>
									
								</tr>
							</logic:iterate>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="gyl001" action="/bank/interface.do?method=gyl001" />
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
