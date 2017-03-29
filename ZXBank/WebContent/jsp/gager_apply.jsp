<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>质物入库申请</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
	.textbox{
		margin-top:5px;
		margin-left:10%;
	}
</style>
<script>
  $(function() {
    var availableTags = [
      "41234",
      "12312",
      "12341",
      "5123412",
      "234125",
      "51234",
      "234534",
      "623452",
      "6346345",
      "62346",
      "62346",
      "1412341y",
      "1234234",
      "Java",
      "JavaScript",
      "Lisp",
      "Perl",
      "PHP",
      "Python",
      "Ruby",
      "Scala",
      "Scheme"
    ];
    $( "#loncpid" ).autocomplete({
      source: availableTags
    });
  });
  </script>
</head>
<body class="h-100 public" >
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
		<div class="public-bar-crumbs fl hidden">
            <a class="crumbs-link" href="../index.jsp">中信银行接口</a>
            > 
            <a class="crumbs-link" href="#">质物入库申请</a>
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
	                    	<input id="loncpid" type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>操作人名称：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    	<%-- <input class="ly-bor-none" id="pledgeContractCode" type="text" name="pledgeContractCode" value="<c:out value='${pledgeContractCode }'/>"/> --%>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>交易流水号：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    	<%-- <input class="ly-bor-none" id="warehouseAddress" type="text" name="warehouseAddress" value="<c:out value='${warehouseAddress }'/>"/> --%>
	                    </div>
                    </div>
					<div class="ly-col fl">
                        <div class="label block fl hidden">导入文件：</div>
	                    <div class="input block fl hidden">
	                    	<input style="padding: 3px 0 3px 0;margin-left: 10%;margin-top: 5px;" id="importFile" type="file" name="importFile" accept=".xls"/>
	                    	
	                    </div>
                    </div>
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">文件模板：</div>
	                    <div class="input block fl hidden">
	                    	<a style="color: blue;" href="#">下载</a>
	                    	<!-- <a style="color: blue;" href="/zdwl/system/importTemplate/chuzhiruku.xls">下载</a> -->
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">纸质担保合同编号：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">动产质押担保合同编号：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">总记录数：</div>
	                    <div class="input block fl hidden">
	                    	<input type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
				</div>
				
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery('pregyl016');" class="button btn-query">导入</a>
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
<!-- <script type="text/javascript">
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

</script> -->
</body>
</html>