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
<script src="/pagejs/bankdock.js"></script>
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
<script type="text/javascript">
	function doQuery(){
		console.info($("#startDate").datebox('getValue'));
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
				alert("请选择客户名称");
				return false;
			}
		}
		
		document.forms[0].submit();
	}
	
	function doClear(){
		getElement("serialNo").value="";
		$("#applyType").val("2116");
		$("#status").val("01");
		$('#startDate').datebox('clear');
		$('#endDate').datebox('clear');
		$("#custNo").combobox('setValue','');
	}
	$(function(){
		//加载插件
		$('#startDate').datebox({
			editable:false
		});
		$('#endDate').datebox({
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
            <a class="crumbs-link" style="color:#929291;" href="#"> 申请信息查询</a>
         </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gyl006" />
		<input id="applyTypeQuery" type="hidden" value="<c:out value='${applyType }'/>"/>
		<input id="statusQuery" type="hidden" value="<c:out value='${status }'/>"/>
		<input id="currRole" type="hidden" value="<c:out value='${currRole}'/>"/>
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">客户名称：</div>
	                    <div class="input block fl hidden">
	                    	<select id="custNo" name="custNo" style="min-width:150px;width:80%;" >
	                    		<option value="">请选择…</option>
	                    		<c:forEach items="${custList }" var="row">
	                    			<option <c:if test="${row.value==custNo}">selected='selected'</c:if> value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
	                    		</c:forEach>
	                    	</select>
	                    	<input class="ly-bor-none" id="custNo" type="text" name="custNo" value="<c:out value='${custNo }'/>"/>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">交易流水号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="serialNo" type="text" name="serialNo" value="<c:out value='${serialNo }'/>"/>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">申请类型：</div>
	                    <div class="input block fl hidden">
	                    	<select class="ly-bor-none" name="applyType" style="width:170px;margin-left:-1px;" id="applyType">
	                    		<option <c:if test="${applyType=='2116' }">selected='selected'</c:if> value="2116">先票后货出质入库申请</option>
	                    		<option <c:if test="${applyType=='2122' }">selected='selected'</c:if> value="2122">先票后货提货申请</option>
	                    	</select>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">状态：</div>
	                    <div class="input block fl hidden">
	                    	<select class="ly-bor-none" name="status" id="status">
	                    		<option <c:if test="${status=='01' }">selected='selected'</c:if> value="01">审批中</option>
	                    		<option <c:if test="${status=='02' }">selected='selected'</c:if> value="02">审批通过</option>
	                    		<option <c:if test="${status=='03' }">selected='selected'</c:if> value="03">审批未通过</option>
	                    	</select>
	                    </div>
                    </div>                  
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>开始日期：</div>
	                    <div class="input block fl hidden">
	                    	<input id="startDate" name="startDate" type="text" value="<c:out value='${startDate }'/>"> </input>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>结束日期：</div>
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
							<th class="t-th">交易流水号</th>
							<th class="t-th">申请时间</th>
							<th class="t-th">客户号</th>
							<th class="t-th">客户名称</th>
							<th class="t-th">申请类型</th>
							<th class="t-th">审批状态</th>
							<th class="t-th">同步状态</th>
							<th class="t-th">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value='${row.serialNo }'/></td>
									<td class="t-td"><c:out value='${row.applyDate }'/></td>
									<td class="t-td"> 
										<c:out value="${row.custNo }"></c:out>
									</td>
									<td class="t-td"><c:out value='${row.pledgeName }'/></td>
									<td class="t-td">
										<c:if test="${row.applyType=='2116'}">先票后货出质入库申请</c:if>
										<c:if test="${row.applyType=='2122'}">先票后货提货申请</c:if>
									</td>
									<td class="t-td">
										<c:if test="${status=='01'}">审批中</c:if>
										<c:if test="${status=='02'}">审批通过</c:if>
										<c:if test="${status=='03'}">审批未通过</c:if>
									</td>
									<td class="t-td">
										<select:syncstat serialNo="${row.serialNo}"/>
									</td>
									<td class="t-td">
										<a href="javascript:window.open('/bank/interface.do?method=gyl007&serialNo=<c:out value='${row.serialNo }'/>');">详情</a>
										<c:if test="${status=='02' || status=='03'}">
											<a href="javascript:sync(<c:out value='${row.serialNo }'/>,<c:out value='${row.applyType}'/>,<c:out value='${status}'/>);">同步</a>
										</c:if>
									</td>
							</logic:iterate>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				<c:if test="${not empty list }">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="gyl006" action="/bank/interface.do?method=gyl006" />
				</c:if>
			</div>
		</div>
		</html:form>
	</div>
</div>
<script type="text/javascript">
function sync(serialNo,applyType,status){
	if(serialNo){
		$.ajax({
		   url: "/bank/interface.do?method=syncGyl007toLocal",
		   type: "POST",
		   dataType: "text",
		   data:{
			   "serialNo":serialNo,
			   "status":status,
			   "applyType":applyType
		   },
		   success: function(msg){
			    if(msg=="sync"){
			    	alert("数据已同步");
			    }else if(msg=="nosync"){
			    	alert("无数据可同步");
			    }else{
			     	if(msg=="success"){
			     		alert("同步成功");
			     		doQuery();
			     	}else{
			     		alert("同步失败");
			     	}
			    }
		   }
		});
	}
}

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
