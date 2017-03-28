<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<link href="../css/easyui.css" rel="stylesheet" type="text/css" />
<link href="../css/icon.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery.easyui.min.js"></script>
<script src="../js/easyui-lang-zh_CN.js"></script>
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
<script type="text/javascript">
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
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
		<div class="public-bar-crumbs fl hidden">
            <a class="crumbs-link" href="/zdwl_test">中信银行接口</a>
            > 
            <a class="crumbs-link" href="stock_taking_select.jsp">盘库信息查询</a>
            >
            <a class="crumbs-link" href="#">盘库详情</a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post">
		<input name="method" id="method" type="hidden" value="gyl022" />
		<div class="public-main-input ly-col-2 hidden abs" style="height:165px;">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">借款企业ID：</div>
	                    <div class="input block fl hidden">544654
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">监管协议编号：</div>
	                    <div class="input block fl hidden">498684
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">操作人员编号：</div>
	                    <div class="input block fl hidden">王五
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">操作人名称：</div>
	                    <div class="input block fl hidden">654651
	                    </div>
                    </div>
				</div>
				
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">交易流水号：</div>
	                    <div class="input block fl hidden">6541
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">计划盘库日期：</div>
	                    <div class="input block fl hidden">2017/3/2
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">实际盘库日期：</div>
	                    <div class="input block fl hidden">2017/3/3
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">差错报告：</div>
	                    <div class="input block fl hidden">无
	                    </div>
                    </div>
				</div>
			</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">备注：</div>
	                    <div class="input block fl hidden">无
	                    </div>
                    </div>
				</div>
		</div>
		<div class="public-main-table hidden abs" style="top:175px;">
			<div class="ly-cont">
			<div style="overflow-x: auto; overflow-y: auto; height: 100%; width:100%">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">监管仓库代码</th>
							<th class="t-th">商品代码</th>
							<th class="t-th">盘库商品数量</th>
							<th class="t-th">动产质押担保合同编号</th>
							<th class="t-th">车架号</th>
							<th class="t-th">仓库级别</th>
							<th class="t-th">仓库代码</th>
							<th class="t-th">仓库地址</th>
							<th class="t-th">车辆数量</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<tr class="t-tr">
							<td class="t-td">1</td>
							<td class="t-td">45645</td>
							<td class="t-td">788</td>
							<td class="t-td">1</td>
							<td class="t-td">7872</td>
							<td class="t-td">8756</td>
							<td class="t-td">一级仓库</td>
							<td class="t-td">A1</td>
							<td class="t-td">上海</td>
							<td class="t-td">1</td>
						</tr>
						
						<tr class="t-tr">
							<td class="t-td">2</td>
							<td class="t-td">45345</td>
							<td class="t-td">568</td>
							<td class="t-td">1</td>
							<td class="t-td">7892</td>
							<td class="t-td">0556</td>
							<td class="t-td">一级仓库</td>
							<td class="t-td">A3</td>
							<td class="t-td">上海</td>
							<td class="t-td">1</td>
						</tr>
						
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
		
		</html:form>
	</div>
</div>
</body>
</html>
