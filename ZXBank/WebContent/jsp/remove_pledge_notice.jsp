<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>解除质押通知</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" language="javascript"> 
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
    $( "#noticeid" ).autocomplete({
      source: availableTags
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
            <a class="crumbs-link" href="#">解除质押通知</a>
        </div>
	</div>
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
					<div class="ly-col fl" style="width:400px">
                        <div class="label block fl hidden" >解除质押通知编号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="noticeid" type="text" name="pledgeName" value=""/>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">借款企业名称：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="noticeid" type="text" name="pledgeName" value=""/>
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
							<th class="t-th">解除质押通知书编号</th>
							<th class="t-th">经办行</th>
							<th class="t-th">出质人名称</th>
							<th class="t-th">借款企业id</th>
							<th class="t-th">借款企业名称</th>
							<th class="t-th">核心企业名称</th>
							<th class="t-th">解除质押日期</th>
							<th class="t-th">出库原因</th>
							<th class="t-th">通知书日期</th>
							<th class="t-th">通知书状态</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">更新时间</th>
							<th class="t-th">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<tr class="t-tr">
							<td class="t-td">1</td>
							<td class="t-td">265555</td>
							<td class="t-td">银行一</td>
							<td class="t-td">出质人A</td>
							<td class="t-td">555556</td>
							<td class="t-td">借款A</td>
							<td class="t-td">核心A</td>
							<td class="t-td">2013-05-06</td>
							<td class="t-td">出库</td>
							<td class="t-td">2015-06-09</td>
							<td class="t-td">已接收</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td"><a href="remove_pledge_notice_detail.jsp">详情</a></td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">2</td>
							<td class="t-td">464665</td>
							<td class="t-td">银行二</td>
							<td class="t-td">出质人B</td>
							<td class="t-td">555556</td>
							<td class="t-td">借款A</td>
							<td class="t-td">核心A</td>
							<td class="t-td">2013-05-06</td>
							<td class="t-td">出库</td>
							<td class="t-td">2015-06-09</td>
							<td class="t-td">已接收</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td"><a href="remove_pledge_notice_detail.jsp">详情</a></td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">3</td>
							<td class="t-td">888455</td>
							<td class="t-td">银行三</td>
							<td class="t-td">出质人C</td>
							<td class="t-td">555556</td>
							<td class="t-td">借款A</td>
							<td class="t-td">核心A</td>
							<td class="t-td">2013-05-06</td>
							<td class="t-td">出库</td>
							<td class="t-td">2015-06-09</td>
							<td class="t-td">已接收</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">详情</td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">4</td>
							<td class="t-td">333225</td>
							<td class="t-td">银行四</td>
							<td class="t-td">出质人D</td>
							<td class="t-td">555556</td>
							<td class="t-td">借款A</td>
							<td class="t-td">核心A</td>
							<td class="t-td">2013-05-06</td>
							<td class="t-td">出库</td>
							<td class="t-td">2015-06-09</td>
							<td class="t-td">已接收</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">详情</td>
						</tr>
						
						<%-- <c:if test="${not empty list }">
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
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="gyl002" action="/bank/interface.do?method=gyl002" />
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