<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=IUTF-8">
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/jquery-ui.min.css">
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
<script>
function doQuery(){
	document.forms[0].submit();
}
function doClear(){
	
	$("custNo").value="";
	getElement("pledgeName").value="";
}
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
    $( "#custNo" ).autocomplete({
      source: availableTags
    });
  });
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
			"Scheme"];
	    $( "#loncpid_name" ).autocomplete({
	      source: availableTags
	    });
	  });
  
  
  	/* $.ajax({
  		url:"ZXinterface.do?method=warehouse",
  		type:"POST",
  		date:{
  			
  		},
  		dateType:'json',
  		success:function(date){
  			alert(date);
  		}
  	}); */
  </script>
</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/zdwl_test">中信银行接口</a>
				>
				<a class="crumbs-link" href="#">仓库信息查询</a>
			</div>
		</div>
	</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/Warehouse.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="warehouse" />
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">ECIF客户号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="custNo" type="text" name="custNo" value="<c:out value='${custNo }'/>"/>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">借款企业名称：</div>
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
							<th class="t-th">ECIF客户号</th>
							<!-- <th class="t-th">借款企业名称</th> -->
							<th class="t-th">仓库名称</th>
							<th class="t-th">仓库代码</th>
							<th class="t-th">仓库级别</th>
							<th class="t-th">经办行</th>
							<th class="t-th">地址</th>
							<th class="t-th">电话</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">更新时间</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
					<c:if test="${not empty list }">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><c:out value="${row.custNo}"/></td>
								<td class="t-td"><c:out value="${row.whName}"/></td>
								<td class="t-td"><c:out value="${row.whCode}"/></td>
								<td class="t-td"><c:out value="${row.whLevel}"/></td>
								<td class="t-td"><c:out value="${row.whOperorg}"/></td>
								<td class="t-td"><c:out value="${row.whAddress}"/></td>
								<td class="t-td"><c:out value="${row.phone}"/></td>
								<td class="t-td"><c:out value="${row.createDate}"/></td>
								<td class="t-td"><c:out value="${row.updateDate}"/></td>
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
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Warehouse" action="Warehouse.do?method=warehouse" />
			</div>
		</div>
		</html:form>
	</div>
</div>
</body>
</html>