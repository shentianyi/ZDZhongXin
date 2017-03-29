<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=IUTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="/zdwl_test/css/base.css" />
<link type="text/css" rel="stylesheet" href="/zdwl_test/css/public.css" />
</head>
<body class="h-100 public">
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
		<div class="public-bar-crumbs fl hidden">
			<a class="crumbs-link" href="/zdwl_test">中信银行接口</a>
			>
			<a class="crumbs-link" href="../jsp/delivery_notice.jsp">收货通知</a>
			>
			<a class="crumbs-link" href="#">通知详细查询</a>
		</div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gyl007" />
		<input id="custNo" name="custNo" type="hidden" value="<c:out value='${custNo }'/>"/>
		<input id="noticeId" name="noticeId" type="hidden" value="<c:out value='${noticeId }'/>"/>
		<input id="serialNo" name="serialNo" type="hidden" value="<c:out value='${serialNo }'/>"/>
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w" style="height:110px">
				<div class="ly-row clearfix">
                    <div class="ly-col fl">
                        <div class="label block fl hidden">收货通知书编号:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value="${custNo }"/>S4651651
	                    </div>
                    </div>                  
                    <div class="ly-col fl">
                        <div class="label block fl hidden">核心企业名称:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>奥迪生产厂
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">监管企业名称:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>中都通信
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">运输企业名称:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>申通物流
	                    </div>
                    </div>
                </div>
                <div class="ly-row clearfix">
                    <div class="ly-col fl">
                        <div class="label block fl hidden">借款企业ID:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>J167
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">借款企业名称:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>4s店
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">发货日期:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>2016/15/15
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">预计到港(库)日期:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>2016/15/15
	                    </div>
                    </div>
                </div>
                <div class="ly-row clearfix">
                   
                    <div class="ly-col fl">
                        <div class="label block fl hidden">预计到港(库):</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>本地A3仓库
	                    </div>
                    </div>
                    
                     <div class="ly-col fl">
                        <div class="label block fl hidden">纸质监管协议编号:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>Z4196841
	                    </div>
                    </div>
                    
                     <div class="ly-col fl">
                        <div class="label block fl hidden">通知书日期:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>2016/15/15
	                    </div>
                    </div>
                    
                     <div class="ly-col fl">
                        <div class="label block fl hidden">货物价值合计:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>1000000
	                    </div>
                    </div>
				</div>
			</div>
			<div class="ly-row clearfix">
                   
                    <div class="ly-col fl">
                        <div class="label block fl hidden">交货地点:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>上海
	                    </div>
                    </div>
                    
                     <div class="ly-col fl">
                        <div class="label block fl hidden">备注:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>质量保证
	                    </div>
                    </div>
			</div>
		</div>
		<div class="public-main-table hidden abs">
			<div class="ly-cont">
				<div style="overflow-x: auto; overflow-y: auto; height: 100%; width:100%">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">实际订单纸质编号</th>
							<th class="t-th">实际订单名称</th>
							<th class="t-th">商品代码</th>
							<th class="t-th">商品名称</th>
							<th class="t-th">发货数量</th>
							<th class="t-th">计量单位</th>
							<th class="t-th">发货单价</th>
							<th class="t-th">发货价值</th>
							<th class="t-th">SCF放款批次号</th>
							<th class="t-th">质押合同编号</th>
							<th class="t-th">融资编号</th>
							<th class="t-th">合格证编号</th>
							<th class="t-th">车架号</th>
							<th class="t-th">车价</th>
							<th class="t-th">备注</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" />1</td>
									<td class="t-td"><c:out value=''/>D165464</td>
									<td class="t-td"><c:out value=''/>D收货通知单</td>
									<td class="t-td"><c:out value=''/>S68618</td>
									<td class="t-td"><c:out value=''/>奥迪</td>
									<td class="t-td"><c:out value=''/>1</td>
									<td class="t-td"><c:out value=''/>辆</td>
									<td class="t-td"><c:out value=''/>1000000</td>
									<td class="t-td"><c:out value=''/></td>
									<td class="t-td"><c:out value=''/>P1651</td>
									<td class="t-td"><c:out value=''/>zh46861</td>
									<td class="t-td"><c:out value=''/>R4546</td>
									<td class="t-td"><c:out value=''/>H646161</td>
									<td class="t-td"><c:out value=''/>c897614</td>
									<td class="t-td"><c:out value=''/>1000000</td>
									<td class="t-td"><c:out value=''/>质量优先</td>
									</tr>
									
									<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" />2</td>
									<td class="t-td"><c:out value=''/>D165464</td>
									<td class="t-td"><c:out value=''/>D收货通知单</td>
									<td class="t-td"><c:out value=''/>S68618</td>
									<td class="t-td"><c:out value=''/>奥迪</td>
									<td class="t-td"><c:out value=''/>1</td>
									<td class="t-td"><c:out value=''/>辆</td>
									<td class="t-td"><c:out value=''/>1000000</td>
									<td class="t-td"><c:out value=''/></td>
									<td class="t-td"><c:out value=''/>P1651</td>
									<td class="t-td"><c:out value=''/>zh46861</td>
									<td class="t-td"><c:out value=''/>R4546</td>
									<td class="t-td"><c:out value=''/>H646161</td>
									<td class="t-td"><c:out value=''/>c897614</td>
									<td class="t-td"><c:out value=''/>1000000</td>
									<td class="t-td"><c:out value=''/>质量优先</td>
									</tr>
							</logic:iterate>
						</c:if>
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