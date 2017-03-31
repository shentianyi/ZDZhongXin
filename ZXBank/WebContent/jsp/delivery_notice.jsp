<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=IUTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
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
    $( "#R_No" ).autocomplete({
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
	    $( "#d_name" ).autocomplete({
	      source: availableTags
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
				<a class="crumbs-link" href="#">收货通知</a>
			</div>
		</div>
	</div>
<div class="public-main abs">
	<div class="ly-contai rel">
	
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">收货通知书编号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="R_No" type="text" name="custNo" value=""/>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">借款企业名称：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="d_name" type="text" name="custNo" value=""/>
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
				<table width="100%" class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">收货通知书编号</th>
							<th class="t-th">核心企业名称</th>
							<th class="t-th">(在库)监管企业名称</th>
							<th class="t-th">(在途)监管企业名称</th>
							<th class="t-th">运输企业名称</th>
							<th class="t-th">借款企业ID</th>
							<th class="t-th">借款企业名称</th>
							<th class="t-th">发货日期</th>
							<th class="t-th">预计到港（库）日期</th>
							<th class="t-th">预计到港（库）</th>
							<th class="t-th">纸质监管协议编号</th>
							<th class="t-th">通知书日期</th>
							<th class="t-th">货物价值合计</th>
							<th class="t-th">交货地点</th>
							<th class="t-th">备注</th>
							<th class="t-th">通知书状态</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">更新时间</th>
							<th class="t-th">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value=""/>2</td>
								<td class="t-td"><c:out value=""/>S463651</td>
								<td class="t-td"><c:out value=""/>奥迪生产厂</td>
								<td class="t-td"><c:out value=""/>中都通信</td>
								<td class="t-td"><c:out value=""/>中都通信</td>
								<td class="t-td"><c:out value=""/>申通物流</td>
								<td class="t-td"><c:out value=""/>J9841</td>
								<td class="t-td"><c:out value=""/>4s</td>
								<td class="t-td"><c:out value=""/>2016/5/21</td>
								<td class="t-td"><c:out value=""/>2016/5/23</td>
								<td class="t-td"><c:out value=""/>A1</td>
								<td class="t-td"><c:out value=""/>z198415</td>
								<td class="t-td"><c:out value=""/>2017/3/12	11:20</td>
								<td class="t-td"><c:out value=""/>1000000</td>
								<td class="t-td"><c:out value=""/>上海</td>
								<td class="t-td"><c:out value=""/>无</td>
								<td class="t-td"><c:out value=""/>已接收</td>
								<td class="t-td"><c:out value=""/>2017/3/12	11:20</td>
								<td class="t-td"><c:out value=""/>2017/3/12	11:20</td>
								<td class="t-td"><c:out value=""/><a href="delivery_notice_detail.jsp">详情</a></td>
							</tr>
							<tr class="t-tr">
								<td class="t-td"><c:out value=""/>2</td>
								<td class="t-td"><c:out value=""/>S463651</td>
								<td class="t-td"><c:out value=""/>奥迪生产厂</td>
								<td class="t-td"><c:out value=""/>中都通信</td>
								<td class="t-td"><c:out value=""/>中都通信</td>
								<td class="t-td"><c:out value=""/>申通物流</td>
								<td class="t-td"><c:out value=""/>J9841</td>
								<td class="t-td"><c:out value=""/>4s</td>
								<td class="t-td"><c:out value=""/>2016/5/21</td>
								<td class="t-td"><c:out value=""/>2016/5/23</td>
								<td class="t-td"><c:out value=""/>A1</td>
								<td class="t-td"><c:out value=""/>z198415</td>
								<td class="t-td"><c:out value=""/>2017/3/12	11:20</td>
								<td class="t-td"><c:out value=""/>1000000</td>
								<td class="t-td"><c:out value=""/>上海</td>
								<td class="t-td"><c:out value=""/>无</td>
								<td class="t-td"><c:out value=""/>已接收</td>
								<td class="t-td"><c:out value=""/>2017/3/12	11:20</td>
								<td class="t-td"><c:out value=""/>2017/3/12	11:20</td>
								<td class="t-td"><c:out value=""/><a href="delivery_notice_detail.jsp">详情</a></td>
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