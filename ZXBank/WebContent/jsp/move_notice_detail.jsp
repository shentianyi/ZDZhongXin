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
			<a class="crumbs-link" href="../jsp/move_notice.jsp">移库通知</a>
			>
			<a class="crumbs-link" href="#">通知书详细查询</a>
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
                        <div class="label block fl hidden">移库通知书编号:</div>
	                    <div class="input block fl hidden">
	                    
	                    	<c:out value=""/>Y4651651
	                    </div>
                    </div>                  
                    <div class="ly-col fl">
                        <div class="label block fl hidden">监管企业名称:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>通信
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">借款企业名称:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>4s店
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">经办行:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>上海支行
	                    </div>
                    </div>
                </div>
                <div class="ly-row clearfix">
                    <div class="ly-col fl">
                        <div class="label block fl hidden">移库申请日期:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>2017/2/23
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">通知书日期:</div>
	                    <div class="input block fl hidden">
	                    	<c:out value=''/>2017/2/23
	                    </div>
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
							<th class="t-th">移出仓库号</th>
							<th class="t-th">移入仓库号</th>
							<th class="t-th">商品代码</th>
							<th class="t-th">移库数量</th>
							<th class="t-th">车架号</th>
							<th class="t-th">合格证编号</th>
							<th class="t-th">车价</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="" />1</td>
									<td class="t-td"><c:out value=''/>A1</td>
									<td class="t-td"><c:out value=''/>A2</td>
									<td class="t-td"><c:out value=''/>S548</td>
									<td class="t-td"><c:out value=''/>1</td>
									<td class="t-td"><c:out value=''/>C6461</td>
									<td class="t-td"><c:out value=''/>H984</td>
									<td class="t-td"><c:out value=''/>500000</td>
								</tr>
								<tr class="t-tr">
									<td class="t-td"><c:out value="" />2</td>
									<td class="t-td"><c:out value=''/>A1</td>
									<td class="t-td"><c:out value=''/>A2</td>
									<td class="t-td"><c:out value=''/>S5558</td>
									<td class="t-td"><c:out value=''/>1</td>
									<td class="t-td"><c:out value=''/>C5661</td>
									<td class="t-td"><c:out value=''/>H9784</td>
									<td class="t-td"><c:out value=''/>500000</td>
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