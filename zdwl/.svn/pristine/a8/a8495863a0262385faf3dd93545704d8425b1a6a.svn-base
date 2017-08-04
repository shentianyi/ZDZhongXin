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
	.textbox{
		margin-top:5px;
		margin-left:10%;
	}
</style>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script src="/pagejs/bankdock.js"></script>
<script type="text/javascript">
	$(function(){
		var msg = "<%=request.getAttribute("message")%>";
		if(msg!=null&&msg!=""&&msg!="null"){
			alert(msg);
		}
	});

	function doQuery(method){
		getElement("method").value=method;
		
		if(method=='pregyl009'){
			if(!$("#importFile").val()){
				alert("请选择导入文件");
				return false;
			}
		}
		
		if(method=='gyl009'){
			if(!confirm("确定发送？")){
				return false;
			}
			if(!$("#custNo").combobox('getValue')){
				alert("客户号不能为空");
				return false;
			}
			if(!$("#lnciId").val()){
				alert("借据ID不能为空");
				return false;
			}
		}
		document.forms[0].submit();
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
            <a class="crumbs-link" style="color:#929291;" href="#">发货清单录入</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="biForm" method="post" onsubmit="return false" enctype="multipart/form-data">
		<input name="method" id="method" type="hidden" value="" />
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>客户号：</div>
	                    <div class="input block fl hidden">
	                    	<select id="custNo" name="custNo" style="min-width:150px;width:80%;" >
	                    		<c:forEach items="${custList }" var="row">
	                    			<option <c:if test="${row.value==custNo}">selected='selected'</c:if> value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
	                    		</c:forEach>
	                    	</select>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>借据ID：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="lnciId" type="text" name="lnciId" value="<c:out value='${lnciId }'/>"/>
	                    </div>
                    </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">导入文件：</div>
	                    <div class="input block fl hidden">
	                    	<input style="padding: 3px 0 3px 0;margin-left: 10%;margin-top: 5px;" id="importFile" type="file" name="importFile" accept=".xls"/>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">文件模板：</div>
	                    <div class="input block fl hidden">
	                    	<a style="color: blue;" href="/system/importTemplate/fahuoqingdan.xls">下载</a>
	                    </div>
                    </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery('pregyl009');" class="button btn-query">导入</a>
                <c:if test="${not empty list }">
                	<a href="javascript:doQuery('gyl009');" class="button btn-query">发送</a>
                </c:if>
            </div>
		</div>
		<div class="public-main-table hidden abs">
			<div class="ly-cont">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">押品名称</th>
							<th class="t-th">押品规格型号</th>
							<th class="t-th">押品生产厂家</th>
							<th class="t-th">数量/重量</th>
							<th class="t-th">数量/重量 单位</th>
							<th class="t-th">买入单价</th>
							<th class="t-th">发动机号</th>
							<th class="t-th">车架号</th>
							<th class="t-th">合格证号</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value='${index+1 }'/></td>
									<td class="t-td"><c:out value='${row.name }'/></td>
									<td class="t-td"><c:out value='${row.model }'/></td>
									<td class="t-td"><c:out value='${row.manufacturer }'/></td>
									<td class="t-td"><c:out value='${row.quantity }'/></td>
									<td class="t-td"><c:out value='${row.mortgageUnits }'/></td>
									<td class="t-td"><c:out value='${row.price }'/></td>
									<td class="t-td"><c:out value='${row.engineNo }'/></td>
									<td class="t-td"><c:out value='${row.chassisNo }'/></td>
									<td class="t-td"><c:out value='${row.certificationNo }'/></td>
								</tr>
							</logic:iterate>
						</c:if>
					</tbody>
				</table>
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
