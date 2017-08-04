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
	.gylztt{
		 width:48%;
		 margin-top:5px;
		 margin-left:11%;
		 text-indent:5px;
		 padding:1px 0 2px 0;
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
			$("[name='startDate1']").val($("[name='startDate1']").val().replace(/-/g,""));
			$("[name='startDate2']").val($("[name='startDate2']").val().replace(/-/g,""));
			$("[name='endDate1']").val($("[name='endDate1']").val().replace(/-/g,""));
			$("[name='endDate2']").val($("[name='endDate2']").val().replace(/-/g,""));
			document.forms[0].submit();
		}
	}
	
	function compare(){
		if(!$("#custNo").combobox('getValue')){
			alert("授信客户名称不能为空");
			return false;
		}
		if(!$("#startDate1").datebox('getValue')){
			alert("开始时间不能为空");
			return false;
		}
		if(!$("#startDate2").datebox('getValue')){
			alert("结束时间不能为空");
			return false;
		}
		var startDate1 = new Date($("#startDate1").datebox('getValue'));
		var startDate2 = new Date($("#startDate2").datebox('getValue'));
		var nextYear = startDate1.getTime()+(1000*60*60*24*365);
		if(new Date(nextYear)<new Date()){
			alert("开始日期+365天小于等于当前日期");
			return false;
		}	
		if(startDate1>startDate2){
			alert("开始时间不能大于结束时间");
			return false;
		}
		var day = Math.abs((startDate1.getTime()-startDate2.getTime())/(24*60*60*1000));
		if(day>90){
			alert("开始时间和结束时间不能相差超过90天");
			return false;
		}
		return true;
	}
	
	function doClear(){
		$('#startDate1').datebox('clear');
		$('#startDate2').datebox('clear');
		$('#endDate1').datebox('clear');
		$('#endDate2').datebox('clear');
		getElement("lnciNo").value="";
		getElement("lnciId").value="";
		getElement("businessCode").value="";
		getElement("contractId").value="";
		$("#status").val("");
		
	}
	
	//执行票据信息同步操作
	function doSync() {
		var flag = compare();
		if(flag){
			var custNo = $("#custNo").combobox('getValue');
			var businessType = $('#businessType').val();
			var businessCode = $('#businessCode').val();
			var contractId = $('#contractId').val();
			var startDate1 = $("#startDate1").datebox('getValue');
			var startDate2 = $("#startDate2").datebox('getValue');
			var endDate1 = $("#endDate1").datebox('getValue');
			var endDate2 = $("#endDate2").datebox('getValue');
			var status = $('#status').val();     
			var lnciNo = $('#lnciNo').val();
			var lnciId = $('#lnciId').val();
			$.ajax({
			   url: "/bank/interface.do?method=syncGyl022toLocal",
			   type: "POST",
			   data:{
				   "custNo":custNo,"businessType":businessType,"businessCode":businessCode,"contractId":contractId,
			   	   "startDate1":startDate1,"startDate2":startDate2,"endDate1":endDate1,"endDate2":endDate2,
				   "status":status,"lnciNo":lnciNo,"lnciId":lnciId
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
		$('#startDate1').datebox({
			editable:false
		});
		$('#startDate2').datebox({
			editable:false
		});  
		$('#endDate1').datebox({
			editable:false
		});
		$('#endDate2').datebox({
			editable:false
		});  
		 

	});
</script>
</head>
<body class="h-100 public" >

<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">浙商银行对接</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">借据信息查询</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gyl022" />
		<div class="public-main-input ly-col-2 hidden abs" style="height:175px;">
			<div class="ly-input-w">
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
                        <div class="label block fl hidden"><font color="#FF0000">*</font>业务模式：</div>
	                    <div class="input block fl hidden">
	                    	<select class="ly-bor-none" name="businessType" id="businessType">
	                    		<option <c:if test="${businessType=='1210020' }">selected='selected'</c:if> value="1210020">先票/款后货</option>
	                    		<option <c:if test="${businessType=='1220010' }">selected='selected'</c:if> value="1220010">动产静态质押</option>
	                    		<option <c:if test="${businessType=='1220020' }">selected='selected'</c:if> value="1220020">动产动态质押</option>
	                    	</select>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">业务编号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="businessCode" type="text" name="businessCode" value="<c:out value='${businessCode }'/>"/>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">我行合同编号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="contractId" type="text" name="contractId" value="<c:out value='${contractId }'/>"/>
	                    </div>
                    </div>                  
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>出账日 从：</div>
	                    <div class="input block fl hidden">
	                    	<input id="startDate1" name="startDate1" type="text" value="<c:out value='${startDate1 }'/>"> </input>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>出账日 到：</div>
	                    <div class="input block fl hidden">
	                    	<input id="startDate2" name="startDate2" type="text" value="<c:out value='${startDate2 }'/>"> </input>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">到期日 从：</div>
	                    <div class="input block fl hidden">
	                    	<input id="endDate1" name="endDate1" type="text" value="<c:out value='${endDate1 }'/>"> </input>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">到期日 到：</div>
	                    <div class="input block fl hidden">
	                    	<input id="endDate2" name="endDate2" type="text" value="<c:out value='${endDate2 }'/>"> </input>
	                    </div>
                    </div>
				</div>
			</div>
			<div class="ly-row clearfix">
				<div class="ly-col fl">
                    <div class="label block fl hidden">收款确认状态：</div>
                    <div class="input block fl hidden">
                    	<select class="gylztt" style="width:120px;" name="status" id="status">
                    		<option value="">全部</option>
                    		<option <c:if test="1">selected='selected'</c:if> value="1">收款确认成功</option>
                    		<option <c:if test="0">selected='selected'</c:if> value="0">未收款确认</option>
                   		</select>
                    </div>
                </div>
                <div class="ly-col fl">
                    <div class="label block fl hidden">借据号：</div>
                    <div class="input block fl hidden">
                    	<input class="ly-bor-none" id="lnciNo" name="lnciNo" type="text" value="<c:out value='${lnciNo }'/>"> </input>
                    </div>
                </div>
                <div class="ly-col fl">
	                 <div class="label block fl hidden">借据ID：</div>
	                 <div class="input block fl hidden">
	                 	<input class="ly-bor-none" id="lnciId" name="lnciId" type="text" value="<c:out value='${lnciId }'/>"> </input>
	                 </div>
                </div>
            </div>
			<div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
                <a href="javascript:doSync();" class="button btn-reset">同步</a>
            </div>
		</div>
		<div class="public-main-table hidden abs" style="top:175px;">
			<div class="ly-cont">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">授信客户号</th>
							<th class="t-th">借据ID</th>
							<th class="t-th">借据号</th>
							<th class="t-th">业务编号</th>
							<th class="t-th">我行合同编号</th>
							<th class="t-th">授信客户名称</th>
							<th class="t-th">业务模式</th>
							<th class="t-th">业务品种</th>
							<th class="t-th">借据金额</th>
							<th class="t-th">借据余额</th>
							<th class="t-th">出账日期</th>
							<th class="t-th">到期日</th>
							<th class="t-th">收款确认状态</th>
							<th class="t-th">收款确认确认日期</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value='${row.custNo }'/></td>
									<td class="t-td" title="<c:out value='${row.lnciId }'/>"><c:out value='${row.lnciId }'/></td>
									<td class="t-td"><c:out value='${row.lnciNo }'/></td>
									<td class="t-td"><c:out value='${row.businessCode }'/></td>
									<td class="t-td"><c:out value='${row.contractId }'/></td>
									<td class="t-td"><c:out value='${row.pledgeName }'/></td>
									<td class="t-td">
										<c:if test="${row.businessType=='1210020' }">先票/款后货</c:if>
			                    		<c:if test="${row.businessType=='1240010' }">商票保贴</c:if>
			                    		<c:if test="${row.businessType=='1210010' }">厂商一票通</c:if>
			                    		<c:if test="${row.businessType=='1230010' }">国内保理</c:if>
			                    		<c:if test="${row.businessType=='1230020' }">应收账款质押</c:if>
			                    		<c:if test="${row.businessType=='1220010' }">动产静态质押</c:if>
			                    		<c:if test="${row.businessType=='1220020' }">动产动态质押</c:if>
									</td>
									<td class="t-td">
										<c:if test="${row.loanWay=='1010010' }">短期流动资金贷款</c:if>
			                    		<c:if test="${row.loanWay=='1010020' }">中期流动资金贷款</c:if>
			                    		<c:if test="${row.loanWay=='1020020' }">商业承兑汇票贴现</c:if>
			                    		<c:if test="${row.loanWay=='1020022' }">商业承兑汇票贴现(电子)</c:if>
			                    		<c:if test="${row.loanWay=='1020040' }">商业承兑汇票保贴</c:if>
			                    		<c:if test="${row.loanWay=='1020042' }">商业承兑汇票保贴(电子)</c:if>
			                    		<c:if test="${row.loanWay=='1085010' }">国内信用证议付</c:if>
			                    		<c:if test="${row.loanWay=='1085020' }">国内信用证代付</c:if>
			                    		<c:if test="${row.loanWay=='1085030' }">国内信用证偿付</c:if>
			                    		<c:if test="${row.loanWay=='1085040' }">国内保理代付</c:if>
			                    		<c:if test="${row.loanWay=='1090015' }">国内保理</c:if>
			                    		<c:if test="${row.loanWay=='2010' }">银行承兑汇票</c:if>
			                    		<c:if test="${row.loanWay=='2015' }">银行承兑汇票(电子)</c:if>
			                    		<c:if test="${row.loanWay=='2030' }">融资性保函</c:if>
			                    		<c:if test="${row.loanWay=='2040' }">非融资性保函</c:if>
			                    		<c:if test="${row.loanWay=='2050020' }">备用信用证</c:if>
			                    		<c:if test="${row.loanWay=='2050023' }">备用信用证(OTS)</c:if>
			                    		<c:if test="${row.loanWay=='2050040' }">对外保函</c:if>
			                    		<c:if test="${row.loanWay=='2050043' }">对外保函(OTS)</c:if>
			                    		<c:if test="${row.loanWay=='2055010' }">国内信用证开立</c:if>
									</td>
									<td class="t-td"><c:out value='${row.lnciAmt }'/></td>
									<td class="t-td"><c:out value='${row.lnciBal }'/></td>
									<td class="t-td"><c:out value='${row.startDate }'/></td>
									<td class="t-td"><c:out value='${row.endDate }'/></td>
									<td class="t-td">
										<c:if test="${row.status=='1' }">收款确认成功</c:if>
										<c:if test="${row.status=='0' }">未收款确认</c:if>
									<td class="t-td"><c:out value='${row.confirmDate }'/></td>
									
							</logic:iterate>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				<c:if test="${not empty list }">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="gyl022" action="/bank/interface.do?method=gyl022" />
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
