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
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
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
	function doQuery(){
		if(compare()){
			$("[name='billEndDate1']").val($("[name='billEndDate1']").val().replace(/-/g,""));
			$("[name='billEndDate2']").val($("[name='billEndDate2']").val().replace(/-/g,""));
			$("[name='billDate1']").val($("[name='billDate1']").val().replace(/-/g,""));
			$("[name='billDate2']").val($("[name='billDate2']").val().replace(/-/g,""));
			document.forms[0].submit();
		}
	}
	
	function compare(){
		if(!$("#custNo").combobox('getValue')){
			alert("授信客户名称不能为空");
			return false;
		}
		if(!$("#billDate1").datebox('getValue')){
			alert("票据开始日开始时间不能为空");
			return false;
		}
		if(!$("#billDate2").datebox('getValue')){
			alert("票据开始日结束时间不能为空");
			return false;
		}
		var billDate1 = new Date($("#billDate1").datebox('getValue'));
		var billDate2 = new Date($("#billDate2").datebox('getValue'));
		var billDateNextYear = billDate1.getTime()+(1000*60*60*24*365);
		if(new Date(billDateNextYear)<new Date()){
			alert("票据开始日开始日期+365天小于等于当前日期");
			return false;
		}	
		
		if(billDate1>billDate2){
			alert("票据开始日开始时间不能大于结束时间");
			return false;
		}
		var day = Math.abs((billDate1.getTime()-billDate2.getTime())/(24*60*60*1000));
		if(day>90){
			alert("票据开始日开始时间和结束时间不能相差超过90天");
			return false;
		}
		return true;
	}
	
	function doClear(){
		getElement("custNo").value="";
		getElement("billNo").value="";
		getElement("lnciNo").value="";
		getElement("lnciId").value="";
		$('#billEndDate1').datebox('clear');
		$('#billEndDate2').datebox('clear');
		$('#billDate1').datebox('clear');
		$('#billDate2').datebox('clear');
	}
	//执行票据信息同步操作
	function doSync() {
		var flag = compare();
		if(flag){
			var custNo = $("#custNo").combobox('getValue');
			var billNo = $('#billNo').val();
			var lnciNo = $('#lnciNo').val();
			var lnciId = $('#lnciId').val();
			var billDate1 = $("#billDate1").datebox('getValue');
			var billDate2 = $("#billDate2").datebox('getValue');
			var billEndDate1 = $("#billEndDate1").datebox('getValue');
			var billEndDate2 = $("#billEndDate2").datebox('getValue');
			var businessType = $('#businessType').val();
			$.ajax({
			   url: "/bank/interface.do?method=syncGyl025toLocal",
			   type: "POST",
			   dataType: "text",
			   data:{
				   "custNo":custNo,"billNo":billNo,"lnciNo":lnciNo,"lnciId":lnciId,"businessType":businessType,
			   	   "billDate1":billDate1,"billDate2":billDate2,"billEndDate1":billEndDate1,"billEndDate2":billEndDate2
			   },
			   success: function(msg){
				    if(msg=="nosync"){
				    	alert("无数据可同步");
				    }else{
				     	if(msg=="success"){
				     		alert("同步成功");
				     	}else{
				     		alert("同步失败");
				     	}
				    }
			   }
			});
		}
	}
	$(function(){
		//加载插件
		$('#billEndDate1').datebox({editable:false});
		$('#billEndDate2').datebox({editable:false}); 
		$('#billDate1').datebox({editable:false});
		$('#billDate2').datebox({editable:false}); 
	});
</script>
</head>
<body class="h-100 public" >
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">浙商银行对接</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">票据信息查询</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gyl025" />
		<div class="public-main-input ly-col-2 hidden abs" style="height:175px;">
			<div class="ly-input-w" style="height:108px;">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>授信客户名称：</div>
	                    <div class="input block fl hidden">
	                    	<select id="custNo" name="custNo" style="min-width:150px;width:80%;" >
	                    		<c:forEach items="${custList }" var="row">
	                    			<option <c:if test="${row.value==custNo}">selected='selected'</c:if> value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
	                    		</c:forEach>
	                    	</select>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">票号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="billNo" type="text" name="billNo" value="<c:out value='${billNo }'/>"/>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">借据号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="lnciNo" type="text" name="lnciNo" value="<c:out value='${lnciNo }'/>"/>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">借据ID：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="lnciId" type="text" name="lnciId" value="<c:out value='${lnciId }'/>"/>
	                    </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>票据开始日从：</div>
	                    <div class="input block fl hidden">
	                    	<input id="billDate1" name="billDate1" type="text" value="<c:out value='${billDate1 }'/>"> </input>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>票据开始日到：</div>
	                    <div class="input block fl hidden">
	                    	<input id="billDate2" name="billDate2" type="text" value="<c:out value='${billDate2 }'/>"> </input>
	                    </div>
                    </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">票据到期日从：</div>
	                    <div class="input block fl hidden">
	                    	<input id="billEndDate1" name="billEndDate1" type="text" value="<c:out value='${billEndDate1 }'/>"> </input>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">票据到期日到：</div>
	                    <div class="input block fl hidden">
	                    	<input id="billEndDate2" name="billEndDate2" type="text" value="<c:out value='${billEndDate2 }'/>"> </input>
	                    </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
	                 <div class="ly-col fl">
                      	<div class="label block fl hidden"><font color="#FF0000">*</font>业务模式：</div>
                    	<div class="input block fl hidden">
	                    	<select class="ly-bor-none" name="businessType" id="businessType">
	                    		<option <c:if test="${businessType=='1210020' }">selected='selected'</c:if> value="1210020">先票/款后货</option>
	                    		<option <c:if test="${businessType=='1220010' }">selected='selected'</c:if> value="1220010">动产静态质押</option>
	                    		<option <c:if test="${businessType=='1220020' }">selected='selected'</c:if> value="1220020">动产动态质押</option>
	                    	</select>
	                   	</div>
	                 </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
                <a href="javascript:doSync();" class="button btn-reset">同步</a>
            </div>
		</div>
		<div class="public-main-table hidden abs" style="top:160px;">
			<div class="ly-cont" style="margin-top:30px;">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">借据号</th>
							<th class="t-th">票号</th>
							<th class="t-th">票据金额</th>
							<th class="t-th">票据开始日期</th>
							<th class="t-th">票据结束日期</th>
							<th class="t-th">收款人名称</th>
							<th class="t-th">出票人名称</th>
							<th class="t-th">出票人开户行</th>
							<th class="t-th">承兑人开户行名称</th>
							<th class="t-th">授信客户名称</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value='${row.lnciNo }'/></td>
									<td class="t-td"><c:out value='${row.billNo }'/></td>
									<td class="t-td"><c:out value='${row.billAmount }'/></td>
									<td class="t-td"><c:out value='${row.billDate }'/></td>
									<td class="t-td"><c:out value='${row.billEndDate }'/></td>
									<td class="t-td"><c:out value='${row.payee }'/></td>
									<td class="t-td"><c:out value='${row.remitterCustName }'/></td>
									<td class="t-td"><c:out value='${row.remitterCustBank }'/></td>
									<td class="t-td"><c:out value='${row.acceptorBank }'/></td>
									<td class="t-td"><c:out value='${row.pledgeName }'/></td>
							</logic:iterate>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				<c:if test="${not empty list }">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="gyl025" action="/bank/interface.do?method=gyl025" />
				</c:if>
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
