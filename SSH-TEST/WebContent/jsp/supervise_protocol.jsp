<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>监管协议查询页面</title>
<link href="../css/base.css"  type="text/css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
<link href="../css/easyui.css" rel="stylesheet" type="text/css" />
<link href="../css/icon.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<script src="../js/jquery-1.8.3.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
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
  
  $(function() {
	    var availableTags = [
			"ActionScript",
			"AppleScript",
			"Asp",
			"BASIC",
			"C",
			"C++",
			"Clojure",
			"COBOL",
			"ColdFusion",
			"Erlang",
			"Fortran",
			"Groovy",
			"Haskell",
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
	    $( "#loncpid_name" ).autocomplete({
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
            <a class="crumbs-link" href="#">监管协议查询</a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gyl004" />
		<%-- <input id="currRole" type="hidden" value="<c:out value='${currRole}'/>"/> --%>
		
		<div class="public-input">
			
		
		</div>
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">ECIF客户号：</div>
	                    <div class="input block fl hidden">
	                    	<input id="loncpid" type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
                     <div class="ly-col fl">
                        <div class="label block fl hidden">客户名称：</div>
	                    <div class="input block fl hidden">
	                    	<input id="loncpid_name" type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
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
							<th class="t-th">总记录数</th>
							<th class="t-th">ECIF客户号</th>
							<th class="t-th">借款企业ID</th>
							<th class="t-th">借款企业名称</th>
							<th class="t-th">系统监管协议编号</th>
							<th class="t-th">纸质监管协议编号</th>
							<th class="t-th">协议状态</th>
							<th class="t-th">协议起始日</th>
							<th class="t-th">协议到期日</th>
							<th class="t-th">是否开通线上业务</th>
							<th class="t-th">是否允许移库</th>
							<th class="t-th">经办行</th>
							<th class="t-th">状态</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">更新时间</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<tr class="t-tr">
							<td class="t-td">1</td>
							<td class="t-td">40</td>
							<td class="t-td">501656</td>
							<td class="t-td">124123</td>
							<td class="t-td">东风</td>
							<td class="t-td">16302121</td>
							<td class="t-td">16225555</td>
							<td class="t-td">生效</td>
							<td class="t-td">2016-05-06</td>
							<td class="t-td">2016-08-09</td>
							<td class="t-td">是</td>
							<td class="t-td">是</td>
							<td class="t-td">浙商</td>
							<td class="t-td">正常</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">2</td>
							<td class="t-td">34</td>
							<td class="t-td">501656</td>
							<td class="t-td">124123</td>
							<td class="t-td">东风</td>
							<td class="t-td">16302121</td>
							<td class="t-td">16225555</td>
							<td class="t-td">生效</td>
							<td class="t-td">2016-05-06</td>
							<td class="t-td">2016-08-09</td>
							<td class="t-td">是</td>
							<td class="t-td">是</td>
							<td class="t-td">中信</td>
							<td class="t-td">正常</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">3</td>
							<td class="t-td">65</td>
							<td class="t-td">501656</td>
							<td class="t-td">124123</td>
							<td class="t-td">测试二</td>
							<td class="t-td">16302121</td>
							<td class="t-td">16225555</td>
							<td class="t-td">生效</td>
							<td class="t-td">2016-05-06</td>
							<td class="t-td">2016-08-09</td>
							<td class="t-td">否</td>
							<td class="t-td">是</td>
							<td class="t-td">上海</td>
							<td class="t-td">正常</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
						</tr>
						<tr class="t-tr">
							<td class="t-td">4</td>
							<td class="t-td">435</td>
							<td class="t-td">501656</td>
							<td class="t-td">124123</td>
							<td class="t-td">测试一</td>
							<td class="t-td">16302121</td>
							<td class="t-td">16225555</td>
							<td class="t-td">失效</td>
							<td class="t-td">2016-05-06</td>
							<td class="t-td">2016-08-09</td>
							<td class="t-td">是</td>
							<td class="t-td">否</td>
							<td class="t-td">测试</td>
							<td class="t-td">过期</td>
							<td class="t-td">2017/3/12	10:30</td>
							<td class="t-td">2017/3/12	10:30</td>
						</tr>
						<%-- <c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value='${row.protocolNo }'/></td>
									<td class="t-td"><c:out value='${row.protocolCode }'/></td>
									<td class="t-td"><c:out value='${row.custNo }'/></td>
									<td class="t-td">
										<c:out value='${row.pledgeName }'/>
									</td>
									<td class="t-td">
										<c:if test="${row.pledgeCertType=='1'}">组织机构代码证</c:if>
										<c:if test="${row.pledgeCertType=='2'}">营业执照</c:if>
										<c:if test="${row.pledgeCertType=='3'}">其他组织代码</c:if>
										<c:if test="${row.pledgeCertType=='4'}">其他企业证件类型</c:if>
									</td>
									<td class="t-td"><c:out value='${row.pledgeCertCode }'/></td>
									<td class="t-td">
										<c:if test="${row.pledgeeCertType=='1'}">组织机构代码证</c:if>
										<c:if test="${row.pledgeeCertType=='2'}">营业执照</c:if>
										<c:if test="${row.pledgeeCertType=='3'}">其他组织代码</c:if>
										<c:if test="${row.pledgeeCertType=='4'}">其他企业证件类型</c:if>
									</td>
									<td class="t-td"><c:out value='${row.pledgeeCertcode }'/></td>
									<td class="t-td"><c:out value='${row.pledgeeCertName }'/></td>
									<td class="t-td">
										<c:out value='${row.startDate }'/>
									</td>
									<td class="t-td">
										<c:out value='${row.endDate }'/>
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
				<a href="#"></a>
				<%-- <c:if test="${not empty list }">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="gyl004" action="/bank/interface.do?method=gyl004" />
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