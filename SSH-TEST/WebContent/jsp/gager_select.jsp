<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
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
            <a class="crumbs-link" href="#">质物入库申请信息查询</a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gyl013" />
		<input type="hidden" name="gyl013ExtExcel" id="gyl013ExtExcel" value="<c:out value='${gyl013ExtExcel }'/>"/>
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>借款企业Id：</div>
	                    <div class="input block fl hidden">
	                    	<input id="loncpid" type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery('gyl013');" class="button btn-query">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
                <%-- <c:if test="${not empty list }">
                	<a href="javascript:doQuery('gyl013ExtExcel');" class="button btn-query">导出</a>
                </c:if> --%>
            </div>
		</div>
		<div class="public-main-table hidden abs">
			<div class="ly-cont">
			<div style="overflow-x: auto; overflow-y: auto; height: 100%; width:100%">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">借款企业ID</th>
							<th class="t-th">操作人名称</th>
							<th class="t-th">交易流水号</th>
							<th class="t-th">纸质担保合同编号</th>
							<th class="t-th">动产质押合同编号</th>
							<th class="t-th">总记录数</th>
							<th class="t-th">状态</th>
							<th class="t-th">质物入库申请时间</th>
							<th class="t-th">操作</th>
							
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<tr class="t-tr">
							<td class="t-td">1</td>
							<td class="t-td">1234545</td>
							<td class="t-td">aaa</td>
							<td class="t-td">sd1413212</td>
							<td class="t-td">456453125</td>
							<td class="t-td">412331</td>
							<td class="t-td">45</td>
							<td class="t-td">申请中</td>
							<td class="t-td">2015-06-05</td>
							<td class="t-td"><a href="gager_select_detail.jsp">详情</a></td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">2</td>
							<td class="t-td">454654541</td>
							<td class="t-td">bbbb</td>
							<td class="t-td">dd54544525</td>
							<td class="t-td">456453125</td>
							<td class="t-td">412331</td>
							<td class="t-td">45</td>
							<td class="t-td">申请中</td>
							<td class="t-td">2015-06-05</td>
							<td class="t-td"><a href="gager_select_detail.jsp">详情</a></td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">3</td>
							<td class="t-td">4542312</td>
							<td class="t-td">cccc</td>
							<td class="t-td">sd455345</td>
							<td class="t-td">456453125</td>
							<td class="t-td">412331</td>
							<td class="t-td">45</td>
							<td class="t-td">申请中</td>
							<td class="t-td">2015-06-05</td>
							<td class="t-td"><a href="gager_select_detail.jsp">详情</a></td>
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
									<td class="t-td"><c:out value='${row.engineNo }'/></td>
									<td class="t-td"><c:out value='${row.chassisNo }'/></td>
									<td class="t-td"><c:out value='${row.certificationNo }'/></td>
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
				<%-- <c:if test="${not empty list }">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="gyl013" action="/bank/interface.do?method=gyl013" />
				</c:if> --%>
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