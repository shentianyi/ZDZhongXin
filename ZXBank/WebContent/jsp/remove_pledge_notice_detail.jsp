<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>解除质押通知书详情</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<!-- <link href="../easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="../easyui/themes/icon.css" rel="stylesheet" type="text/css" /> -->
<!-- <script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
</style>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script> -->
<script type="text/javascript">
	function doQuery(){
		document.forms[0].submit();
	}

</script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
</style>
</head>
<body class="h-100 public" >
<div class="public-bar hidden">
	<div class="ly-contai clearfix">
		<div class="public-bar-crumbs fl hidden">
            <a class="crumbs-link" href="/zdwl_test">中信银行接口</a>
            >
            <a class="crumbs-link" href="../jsp/remove_pledge_notice.jsp">解除质押通知</a>
            >
            <a class="crumbs-link" href="#">通知书详情查询</a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<%-- <input name="method" id="method" type="hidden" value="gyl021" />
		<input id="noticeId" name="noticeId" type="hidden" value="<c:out value='${noticeId }'/>"/> --%>
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">解除质押通知书编号：</div>
	                    <div class="input block fl hidden">
	                    	555664445
	                    	<%-- <c:out value='${noticeId }'/> --%>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">经办行：</div>
	                    <div class="input block fl hidden">
	                    	测试一<%-- <c:out value="${custNo }"/> --%>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">出质人名称：</div>
	                    <div class="input block fl hidden">
	                    	出质人一<%-- <c:out value='${protocolCode}'/> --%>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">核心企业名称：</div>
	                    <div class="input block fl hidden">
	                    	核心 企业一<%-- <c:out value="${pledgeContractCode }"/> --%>
	                    </div>
                    </div>                  
				</div>
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">借款企业ID：</div>
	                    <div class="input block fl hidden">
	                    	5455545<%-- <c:out value="${signDate }"/> --%>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">借款企业名称：</div>
	                    <div class="input block fl hidden">
	                    	借款企业一<%-- <c:out value="${moniName }"/> --%>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">监管企业名称：</div>
	                    <div class="input block fl hidden">
	                    	中都<%-- <c:out value="${pledgeeCertName }"/> --%>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">解除质押日期：</div>
	                    <div class="input block fl hidden">
	                    	2016-06-09<%-- <c:out value="${pledgeName }"/> --%>
	                    </div>
                    </div>
                    
                 </div>
                
			</div>
			<!-- <div class="ly-input-w"> -->
                 <div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">通知书日期：</div>
	                    <div class="input block fl hidden">
	                    	2016-05-06<%-- <c:out value="${pledgeName }"/> --%>
	                    </div>
                    </div>
                    <div class="ly-col fl">
                        <div class="label block fl hidden">出库原因：</div>
	                    <div class="input block fl hidden">
	                    	但是规范哈的撒空间吧房间看电视吧<%-- <c:out value="${pledgeName }"/> --%>
	                    </div>
                    </div>     
				</div>
			</div>
		<!-- </div> -->
		<div class="public-main-table hidden abs">
			<div class="ly-cont">
			<div style="overflow-x: auto; overflow-y: auto; height: 100%; width:100%">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">商品代码</th>
							<th class="t-th">商品名称</th>
							<th class="t-th">计量单位</th>
							<th class="t-th">库存数量</th>
							<th class="t-th">解除质押数量</th>
							<th class="t-th">所在仓库编号</th>
							<th class="t-th">SCF放款批次号</th>
							<th class="t-th">动产质押担保合同编号</th>
							<th class="t-th">移库数量</th>
							<th class="t-th">车架号</th>
							<th class="t-th">合格证编号</th>
							<th class="t-th">车价</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<tr class="t-tr">
							<td class="t-td">1</td>
							<td class="t-td">4565612</td>
							<td class="t-td">劳斯莱斯</td>
							<td class="t-td">辆</td>
							<td class="t-td">15</td>
							<td class="t-td">10</td>
							<td class="t-td">4551212</td>
							<td class="t-td">7478865464</td>
							<td class="t-td">4541214514</td>
							<td class="t-td">3</td>
							<td class="t-td">45645456</td>
							<td class="t-td">56456465</td>
							<td class="t-td">300000000.00</td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">2</td>
							<td class="t-td">4565612</td>
							<td class="t-td">奥迪</td>
							<td class="t-td">辆</td>
							<td class="t-td">35</td>
							<td class="t-td">20</td>
							<td class="t-td">4551212</td>
							<td class="t-td">7478865464</td>
							<td class="t-td">4541214514</td>
							<td class="t-td">3</td>
							<td class="t-td">45645456</td>
							<td class="t-td">56456465</td>
							<td class="t-td">2320000.00</td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">3</td>
							<td class="t-td">4565612</td>
							<td class="t-td">奔驰</td>
							<td class="t-td">辆</td>
							<td class="t-td">95</td>
							<td class="t-td">20</td>
							<td class="t-td">4551212</td>
							<td class="t-td">7478865464</td>
							<td class="t-td">4541214514</td>
							<td class="t-td">3</td>
							<td class="t-td">45645456</td>
							<td class="t-td">56456465</td>
							<td class="t-td">2000000.00</td>
						</tr>
						<%-- <c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value='${row.morgageNo }'/></td>
									<td class="t-td"><c:out value='${row.name }'/></td>
									<td class="t-td"><c:out value='${row.model }'/></td>
									<td class="t-td"><c:out value='${row.manufacturer }'/></td>
									<td class="t-td"><c:out value='${row.quantity }'/></td>
									<td class="t-td"><c:out value='${row.mortgageUnits }'/></td>
									<td class="t-td"><c:out value='${row.price }'/></td>
									<td class="t-td"><c:out value='${row.warehouse }'/></td>
									<td class="t-td"><c:out value='${row.engineNo }'/></td>
									<td class="t-td"><c:out value='${row.chassisno }'/></td>
									<td class="t-td"><c:out value='${row.certificationNo }'/></td>
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
				<%-- <c:if test="${not empty list }">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="gyl021" action="/bank/interface.do?method=gyl021" />
				</c:if> --%>
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