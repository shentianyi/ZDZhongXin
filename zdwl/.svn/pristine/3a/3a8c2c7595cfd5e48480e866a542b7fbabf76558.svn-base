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
		if(!$("#startDate").datebox('getValue')){
			alert("开始时间不能为空");
			return false;
		}
		if(!$("#endDate").datebox('getValue')){
			alert("结束时间不能为空");
			return false;
		}
		var startDate = new Date($("#startDate").datebox('getValue'));
		var endDate = new Date($("#endDate").datebox('getValue'));
		var nextYear = startDate.getTime()+(1000*60*60*24*365);
		if(new Date(nextYear)<new Date()){
			alert("开始日期+365天小于等于当前日期");
			return false;
		}	
		
		if(startDate>endDate){
			alert("开始时间不能大于结束时间");
			return false;
		}
		var day = Math.abs((startDate.getTime()-endDate.getTime())/(24*60*60*1000));
		if(day>90){
			alert("开始时间和结束时间不能相差超过90天");
			return false;
		}
		
		$("[name='startDate']").val($("[name='startDate']").val().replace(/-/g,""));
		$("[name='endDate']").val($("[name='endDate']").val().replace(/-/g,""));
		
		var currRole = $("#currRole").val();
		if(currRole == 10){
			var custNo = $("#custNo").combobox('getValue');
			if(!custNo){
				alert("请选择客户号");
				return false;
			}
		}
		document.forms[0].submit();
	}
	
	function doClear(){
		getElement("custNo").value="";
		$("#noticeType").val("");
		$("#status").val("");
		$('#startDate').datebox('clear');
		$('#endDate').datebox('clear');
	}
	$(function(){
		$("#noticeType").val($("#noticeTypeQuery").val());
		$("#status").val($("#statusQuery").val());
		
		//加载插件
		$('#startDate').datebox({
			editable:false
		});
		$('#endDate').datebox({
			editable:false
		});  
		 

	});
	
	function huizhi(id){
		if(confirm("确定回执？")){
			$.ajax({
				type:"post",
				url:"/bank/interface.do?method=gyl003&noticeAckId="+id,
				success:function(result){
					if(result=="success"){
						alert("回执成功");
					}else{
						alert(result);
					}
					doQuery();
				}
			});
		}
	}
</script>
</head>
<body class="h-100 public" >
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">浙商银行对接</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">通知书列表查询</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gyl002" />
		<input id="noticeTypeQuery" type="hidden" value="<c:out value='${noticeType }'/>"/>
		<input id="statusQuery" type="hidden" value="<c:out value='${status }'/>"/>
		<input id="currRole" type="hidden" value="<c:out value='${currRole}'/>"/>
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">客户号：</div>
	                    <div class="input block fl hidden">
	                    	<select id="custNo" name="custNo" style="min-width:150px;width:80%;" >
	                    		<option value="">请选择…</option>
	                    		<c:forEach items="${custList }" var="row">
	                    			<option <c:if test="${row.value==custNo}">selected='selected'</c:if> value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
	                    		</c:forEach>
	                    	</select>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">通知书类型：</div>
	                    <div class="input block fl hidden">
	                    	<select class="ly-bor-none" name="noticeType" id="noticeType">
	                    		<option value="">全部</option>
								<option value="79">先票/款后货-汽车金融收款确认通知书</option>
								<option value="81">先票/款后货-汽车金融出质入库通知书</option>
								<option value="73">先票/款后货-汽车金融核库报告</option>
								<option value="0">先票/款后货-汽车金融质物价格确定、调整通知书</option>
								<option value="83">先票/款后货-汽车金融提货通知书</option>
								<option value="23">先票/款后货-汽车金融质物跌价补偿通知书</option>
								<option value="99">先票/款后货-汽车金融卖方发货情况对账单</option>
								<option value="124">先票/款后货-汽车金融反出质通知书</option>
								<option value="85">先票/款后货-汽车金融差额退款通知书</option>
								<option value="78">厂商一票通-银承收款确认通知书</option>
								<option value="80">厂商一票通-提货通知书</option>
								<option value="88">厂商一票通-发货对账通知书</option>
								<option value="71">厂商一票通-差额退款通知书</option>
								<option value="13">国内保理-应收账款债权转让通知书</option>
								<option value="33">国内保理-应收账款到期催收通知书</option>
								<option value="116">国内保理-应收账款到期提示通知书</option>
								<option value="123">国内保理-保理专户资金划付通知书</option>
								<option value="36">国内保理-费用收取借记通知书</option>
								<option value="69">国内保理-还款通知书</option>
								<option value="114">应收账款质押-应收账款出质通知书</option>
								<option value="90">动产质押-委托质押监管通知书</option>
								<option value="91">动产质押-押品价格确定调整通知书</option>
								<option value="1">动产质押-押品监管下限通知书</option>
								<option value="93">动产质押-核库报告通知书</option>
							</select>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">通知书状态：</div>
	                    <div class="input block fl hidden">
	                    	<select class="ly-bor-none" name="status" id="status">
	                    		<option value="">全部</option>
	                    		<option value="01">已签发已确认</option>
	                    		<option value="02">签发未确认</option>
	                    	</select>
	                    </div>
                    </div>                   
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">开始日期：</div>
	                    <div class="input block fl hidden">
	                    	<input id="startDate" name="startDate" type="text" value="<c:out value='${startDate }'/>"> </input>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">结束日期：</div>
	                    <div class="input block fl hidden">
	                    	<input id="endDate" name="endDate" type="text" value="<c:out value='${endDate }'/>"> </input>
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
							<th class="t-th">监管协议号</th>
							<th class="t-th">质押合同号</th>
							<th class="t-th">通知书编号</th>
							<th class="t-th">通知书类型</th>
							<th class="t-th">客户号</th>
							<th class="t-th">客户名称</th>
							<th class="t-th">通知书状态</th>
							<th class="t-th">是否可回执</th>
							<th class="t-th">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value='${row.protocolCode }'/></td>
									<td class="t-td"><c:out value='${row.pledgeContractCode }'/></td>
									<td class="t-td"><c:out value='${row.noticeId }'/></td>
									<td class="t-td">
										<c:if test="${row.noticeType=='79' }">先票/款后货-汽车金融收款确认通知书</c:if>
										<c:if test="${row.noticeType=='81' }">先票/款后货-汽车金融出质入库通知书</c:if>
										<c:if test="${row.noticeType=='73' }">先票/款后货-汽车金融核库报告</c:if>
										<c:if test="${row.noticeType=='0' }">先票/款后货-汽车金融质物价格确定、调整通知书</c:if>
										<c:if test="${row.noticeType=='83' }">先票/款后货-汽车金融提货通知书</c:if>
										<c:if test="${row.noticeType=='23' }">先票/款后货-汽车金融质物跌价补偿通知书</c:if>
										<c:if test="${row.noticeType=='99' }">先票/款后货-汽车金融卖方发货情况对账单</c:if>
										<c:if test="${row.noticeType=='124' }">先票/款后货-汽车金融反出质通知书</c:if>
										<c:if test="${row.noticeType=='85' }">先票/款后货-汽车金融差额退款通知书</c:if>
										<c:if test="${row.noticeType=='78' }">厂商一票通-银承收款确认通知书</c:if>
										<c:if test="${row.noticeType=='80' }">厂商一票通-提货通知书</c:if>
										<c:if test="${row.noticeType=='88' }">厂商一票通-发货对账通知书</c:if>
										<c:if test="${row.noticeType=='71' }">厂商一票通-差额退款通知书</c:if>
										<c:if test="${row.noticeType=='13' }">国内保理-应收账款债权转让通知书</c:if>
										<c:if test="${row.noticeType=='33' }">国内保理-应收账款到期催收通知书</c:if>
										<c:if test="${row.noticeType=='116' }">国内保理-应收账款到期提示通知书</c:if>
										<c:if test="${row.noticeType=='123' }">国内保理-保理专户资金划付通知书</c:if>
										<c:if test="${row.noticeType=='36' }">国内保理-费用收取借记通知书</c:if>
										<c:if test="${row.noticeType=='69' }">国内保理-还款通知书</c:if>
										<c:if test="${row.noticeType=='114' }">应收账款质押-应收账款出质通知书</c:if>
										<c:if test="${row.noticeType=='90' }">动产质押-委托质押监管通知书</c:if>
										<c:if test="${row.noticeType=='91' }">动产质押-押品价格确定调整通知书</c:if>
										<c:if test="${row.noticeType=='1' }">动产质押-押品监管下限通知书</c:if>
										<c:if test="${row.noticeType=='93' }">动产质押-核库报告通知书</c:if>
									</td>
									<td class="t-td"><c:out value='${row.custNo }'/></td>
									<td class="t-td"><c:out value='${row.pledgeName }'/></td>
									<td class="t-td">
										<c:if test="${row.status=='00'}">未阅</c:if>
										<c:if test="${row.status=='01'}">已签发已确认</c:if>
										<c:if test="${row.status=='02'}">签发未确认</c:if>
									</td>
									<td class="t-td">
										<c:if test="${row.isReceipt=='1'}">是</c:if>
										<c:if test="${row.isReceipt=='0'}">否</c:if>
									</td>
									<td class="t-td">
										<c:if test="${row.isReceipt=='1'}">
											<a href="javascript:huizhi('<c:out value='${row.noticeId }'/>');">回执</a>
										</c:if>
										<c:if test="${row.noticeType=='81'}">
											<a href="javascript:window.open('/bank/interface.do?method=gyl017&noticeId=<c:out value='${row.noticeId }'/>&custNo=<c:out value='${row.custNo}'/>');">详情</a>
										</c:if>
										<c:if test="${row.noticeType=='83' }">
											<a href="javascript:window.open('/bank/interface.do?method=gyl021&noticeId=<c:out value='${row.noticeId }'/>');">详情</a>
										</c:if>
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
				<c:if test="${not empty list }">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="gyl002" action="/bank/interface.do?method=gyl002" />
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
