<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=IUTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="/zdwl_test/css/base.css" />
<link type="text/css" rel="stylesheet" href="/zdwl_test/css/public.css" />
<link href="/zdwl_test/css/easyui.css" rel="stylesheet" type="text/css" />
<link href="/zdwl_test/css/icon.css" rel="stylesheet" type="text/css" />
<script src="/zdwl_test/js/jquery.min.js"></script>
<script src="/zdwl_test/js/jquery.easyui.min.js"></script>
<script src="/zdwl_test/js/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
	.textbox{
		margin-top:5px;
		margin-left:10%;
	}
</style>
<script src="/zdwl_test/js/calendar.js"></script>
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
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/zdwl_test">中信银行接口</a>
				>
				<a class="crumbs-link" href="#">借款企业融资信息查询</a>
			</div>
		</div>
	</div>
<div class="public-main abs">
	<div class="ly-contai rel">
	
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				
				<div class="ly-row clearfix">
                    <div class="ly-col fl">
                        <div class="label block fl hidden">借款企业ID：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="loncpid1" type="text" name="custNo" value=""/>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">业务模式：</div>
                        <div class="input block fl hidden">
                        	<select class="ly-bor-none" id="" name="" style="min-width:150px;width:80%;">
	                    		<option>请输入</option>
	                    		<option>先票后货两方</option>
	                    		<option>先票后货三方</option>
	                    	</select>
                        </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>开始日期：</div>
	                    <div class="input block fl hidden">
	                    	<input id="startDate" name="startDate" type="text" value=""></input>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>结束日期：</div>
	                    <div class="input block fl hidden">
	                    	<input id="endDate" name="endDate" type="text" value=""></input>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">查询方式：</div>
	                    <div class="input block fl hidden">
	                    	<select class="ly-bor-none" id="" name="" style="min-width:150px;width:80%;">
	                    		<option>请选择</option>
	                    		<option>本地查询</option>
	                    		<option>远程查询</option>
	                    	</select>
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
			<div style="overflow-x: auto; overflow-y: auto; height: 100%; width:100%">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">借款企业ID</th>
							<th class="t-th">融资编号</th>
							<th class="t-th">放款批次号</th>
							<th class="t-th">融资金额</th>
							<th class="t-th">保证金比例</th>
							<th class="t-th">自有资金比例</th>
							<th class="t-th">首付保证金可提货比例</th>
							<th class="t-th">融资起始日</th>
							<th class="t-th">融资到期日</th>
							<th class="t-th">授信产品</th>
							<th class="t-th">经办行</th>
							<th class="t-th">业务模式</th>
							<th class="t-th">状态</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">更新时间</th>
							
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value=""/>1</td>
								<td class="t-td"><c:out value=""/>968461</td>
								<td class="t-td"><c:out value=""/>R64681</td>
								<td class="t-td"><c:out value=""/>P18618</td>
								<td class="t-td"><c:out value=""/>5500000</td>
								<td class="t-td"><c:out value=""/>15%</td>
								<td class="t-td"><c:out value=""/>20%</td>
								<td class="t-td"><c:out value=""/>50%</td>
								<td class="t-td"><c:out value=""/>2017/03/06</td>
								<td class="t-td"><c:out value=""/>2017/12/06</td>
								<td class="t-td"><c:out value=""/>国内信用证</td>
								<td class="t-td"><c:out value=""/>中信上海支行</td>
								<td class="t-td"><c:out value=""/>先票后货两方</td>
								<td class="t-td"><c:out value=""/>正常</td>
								<td class="t-td"><c:out value=""/>2017/3/12	10:30</td>
								<td class="t-td"><c:out value=""/>2017/3/12	10:30</td>
							</tr>
							<tr class="t-tr">
								<td class="t-td"><c:out value=""/>2</td>
								<td class="t-td"><c:out value=""/>135485</td>
								<td class="t-td"><c:out value=""/>R64381</td>
								<td class="t-td"><c:out value=""/>P18418</td>
								<td class="t-td"><c:out value=""/>5505000</td>
								<td class="t-td"><c:out value=""/>15%</td>
								<td class="t-td"><c:out value=""/>20%</td>
								<td class="t-td"><c:out value=""/>50%</td>
								<td class="t-td"><c:out value=""/>2017/03/06</td>
								<td class="t-td"><c:out value=""/>2017/03/07</td>
								<td class="t-td"><c:out value=""/>进口信用证</td>
								<td class="t-td"><c:out value=""/>中信上海支行</td>
								<td class="t-td"><c:out value=""/>先票后货三方</td>
								<td class="t-td"><c:out value=""/>过期</td>
								<td class="t-td"><c:out value=""/>2017/3/12	10:30</td>
								<td class="t-td"><c:out value=""/>2017/3/12	10:30</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				</div>
			</div>
		</div>
		
		<div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				共&nbsp;3&nbsp;条记录&nbsp;第&nbsp;1&nbsp;页  跳转至<input type="text" value="1" style="width:30px;height:28px;" />
				<button >第一页</button>&nbsp;<button >上一页</button>&nbsp;<button >下一页</button>&nbsp;<button >最末页</button>
			</div>
		</div>
		
	</div>
</div>
</body>
</html>