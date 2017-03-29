<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>质物入库详情</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
</head>
<body class="h-100 public" >
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
		<div class="public-bar-crumbs fl hidden">
            <a class="crumbs-link" href="../index.jsp">中信银行接口</a>
            > 
            <a class="crumbs-link" href="#">质物入库详情</a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="biForm" method="post" onsubmit="return false" enctype="multipart/form-data">
		<input name="method" id="method" type="hidden" value="" />
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>借款企业ID：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" value="45544512" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    	<%-- <select id="custNo" name="custNo" style="min-width:150px;width:80%;" >
	                    		<c:forEach items="${custList }" var="row">
	                    			<option <c:if test="${row.value==custNo}">selected='selected'</c:if> value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
	                    		</c:forEach>
	                    	</select> --%>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>操作人名称：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" value="AAAA" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    	<%-- <input class="ly-bor-none" id="pledgeContractCode" type="text" name="pledgeContractCode" value="<c:out value='${pledgeContractCode }'/>"/> --%>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>交易流水号：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" value="sdfs" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    	<%-- <input class="ly-bor-none" id="warehouseAddress" type="text" name="warehouseAddress" value="<c:out value='${warehouseAddress }'/>"/> --%>
	                    </div>
                    </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">纸质担保编号</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" value="sd4541255" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    	
	                    </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">动产质押合同编号</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" value="sd54555" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    	
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">总记录数：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" value="50" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">状态：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" value="申请通过" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">质物入库时间：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" value="2017-05-06" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
				</div>
				
			</div>
			<div class="ly-button-w">
                <a href="history.go(-1)" onclick="history.go(-1)" class="button btn-query">返回</a>
                <%-- <c:if test="${not empty list }">
                	<a href="javascript:doQuery('gyl016');" class="button btn-query">发送</a>
                </c:if> --%>
            </div>
		</div>
		<div class="public-main-table hidden abs">
			<div class="ly-cont">
			<div style="overflow-x: auto; overflow-y: auto; height: 100%; width:100%">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">商品代码</th>
							<th class="t-th">入库数量</th>
							<th class="t-th">入库单价</th>
							<th class="t-th">仓库代码</th>
							<th class="t-th">车架号</th>
							<th class="t-th">合格证编号</th>
							<th class="t-th">车价</th>
							<th class="t-th">融资编号</th>
							<!-- <th class="t-th">状态</th> -->
							<th class="t-th">创建时间</th>
							<th class="t-th">更新时间</th>
							
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<tr class="t-tr">
							<td class="t-td">1</td>
							<td class="t-td">45121212</td>
							<td class="t-td">50</td>
							<td class="t-td">3111111.00</td>
							<td class="t-td">46545465452</td>
							<td class="t-td">45645454555555</td>
							<td class="t-td">784758453455545555</td>
							<td class="t-td">5454554.00</td>
							<td class="t-td">456453412</td>
							<!-- <td class="t-td">申请中</td> -->
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">2</td>
							<td class="t-td">45121212</td>
							<td class="t-td">50</td>
							<td class="t-td">3111111.00</td>
							<td class="t-td">46545465452</td>
							<td class="t-td">45645454555555</td>
							<td class="t-td">784758453455545555</td>
							<td class="t-td">5454554.00</td>
							<td class="t-td">456453412</td>
							<!-- <td class="t-td">申请中</td> -->
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">3</td>
							<td class="t-td">45121212</td>
							<td class="t-td">50</td>
							<td class="t-td">3111111.00</td>
							<td class="t-td">46545465452</td>
							<td class="t-td">454535454555555</td>
							<td class="t-td">45528453455545555</td>
							<td class="t-td">5454554.00</td>
							<td class="t-td">456453412</td>
							<!-- <td class="t-td">申请中</td> -->
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">4</td>
							<td class="t-td">45121212</td>
							<td class="t-td">40</td>
							<td class="t-td">314511111.00</td>
							<td class="t-td">465454652</td>
							<td class="t-td">456454555555</td>
							<td class="t-td">78478453455545555</td>
							<td class="t-td">5454554.00</td>
							<td class="t-td">456453412</td>
							<!-- <td class="t-td">申请失败</td> -->
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
						</tr>
						<%-- <c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value='${index+1 }'/></td>
									<td class="t-td"><c:out value='${row.morgageNo }'/></td>
									<td class="t-td"><c:out value='${row.name }'/></td>
									<td class="t-td"><c:out value='${row.model }'/></td>
									<td class="t-td"><c:out value='${row.manufacturer }'/></td>
									<td class="t-td"><c:out value='${row.quantity }'/></td>
									<td class="t-td"><c:out value='${row.mortgageUnits }'/></td>
									<td class="t-td"><c:out value='${row.price }'/></td>
									<td class="t-td"><c:out value='${row.engineNo }'/></td>
									<td class="t-td"><c:out value='${row.chassisNo }'/></td>
									<td class="t-td"><c:out value='${row.certificationNo }'/></td>
									<td class="t-td"><c:out value='${row.memo }'/></td>
								</tr>
							</logic:iterate>
						</c:if> --%>
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