<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=IUTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/public.css" />
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
    $( "#tags" ).autocomplete({
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
	    $( "#c_name" ).autocomplete({
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
				<a class="crumbs-link" href="#">客户信息查询</a>
			</div>
		</div>
	</div>
<div class="public-main abs">
	<div class="ly-contai rel">
	
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">组织机构代码：</div>
	                    <div class="input block fl hidden">
	                    	<input id="tags" type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">客户名称：</div>
	                    <div class="input block fl hidden">
	                    	<input id="c_name" type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" />
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
							<th class="t-th">组织机构代码</th>
							<th class="t-th">ECIF客户号</th>
							<th class="t-th">客户名称</th>
							<th class="t-th">状态</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">更新时间</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td">1</td>
								<td class="t-td">Z9984161</td>
								<td class="t-td">K489051651</td>
								<td class="t-td">张三</td>
								<td class="t-td">正常</td>
								<td class="t-td">2017/3/12	10:30</td>
								<td class="t-td">2017/3/12	10:30</td>
							</tr>
							<tr class="t-tr">
								<td class="t-td">2</td>
								<td class="t-td">Z9984161</td>
								<td class="t-td">k745651</td>
								<td class="t-td">李四</td>
								<td class="t-td">正常</td>
								<td class="t-td">2017/3/12	10:40</td>
								<td class="t-td">2017/3/12	10:40</td>
							</tr>
							<tr class="t-tr">
								<td class="t-td">3</td>
								<td class="t-td">Z9984161</td>
								<td class="t-td">K4664355651</td>
								<td class="t-td">王五</td>
								<td class="t-td">正常</td>
								<td class="t-td">2017/3/12	10:40</td>
								<td class="t-td">2017/3/12	10:40</td>
							</tr>
							<tr class="t-tr">
								<td class="t-td">4</td>
								<td class="t-td">Z9984161</td>
								<td class="t-td">K56785651</td>
								<td class="t-td">周琦</td>
								<td class="t-td">未更新</td>
								<td class="t-td">2017/3/12	10:40</td>
								<td class="t-td"></td>
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