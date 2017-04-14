<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>监管协议查询页面</title>
<link href="css/base.css"  type="text/css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link href="css/easyui.css" rel="stylesheet" type="text/css" />
<link href="css/icon.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.min.css">
<link rel="stylesheet" href="css/tablecs.css">
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
      "234125"
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
            <a class="crumbs-link" href="/ZXBank">中信银行接口</a>
            > 
            <a class="crumbs-link" href="#">监管协议查询</a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/agreement.do" styleId="agrForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="agreement" />
		<div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">ECIF客户号：</div>
	                    <div class="input block fl hidden">
	              			<input class="ly-bor-none" type="text" id="loncpid" name="agreement.ag_custno" />      		
	                    </div>
                    </div>
                     <div class="ly-col fl">
                        <div class="label block fl hidden">借款企业名称：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" type="text" id="loncpid_name" name="agreement.ag_loncpname" />
	                    </div>
					</div> 
					
					<div class="ly-col fl">
                        <div class="label block fl hidden">查询方式：</div>
	                    <div class="input block fl hidden">
	                    	<select class="ly-bor-none" id="" name="" >
	                    		<option>请选择</option>
	                    		<option value="1">本地查询</option>
	                    		<option value="2">远程查询</option>
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
							<th class="t-th">借款企业ID</th>
							<th class="t-th">借款企业名称</th>
							<th class="t-th">经办行</th>
							<th class="t-th">系统监管协议编号</th>
							<th class="t-th">纸质监管协议编号</th>
							<th class="t-th">是否开通线上业务</th>
							<th class="t-th">是否允许移库</th>
							<th class="t-th">协议状态</th>
							<th class="t-th">协议起始日</th>
							<th class="t-th">协议到期日</th>
							<th class="t-th">总记录数</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">更新时间</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
					<c:if test="${not empty list }">
						<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}"/></td>
									<td class="t-td"><c:out value="${row.ag_custno }"/></td>
									<td class="t-td"><c:out value="${row.ag_loncpid }"/></td>
									<td class="t-td"><c:out value="${row.ag_loncpname }"/></td>
									<td class="t-td"><c:out value="${row.ag_operorg }"/></td>
									<td class="t-td"><c:out value="${row.ag_protocolno }"/></td>
									<td class="t-td"><c:out value="${row.ag_protocolcode }"/></td>
									<td class="t-td">
										<c:if test="${row.ag_isonline=='0'}">否</c:if>
										<c:if test="${row.ag_isonline=='1'}">是</c:if>
									</td>
									<td class="t-td">
										<c:if test="${row.ag_ismove=='0'}">否</c:if>
										<c:if test="${row.ag_ismove=='1'}">是</c:if>
									</td>
									<td class="t-td">
										<c:if test="${row.ag_state=='0' }" >失效</c:if>
										<c:if test="${row.ag_state=='1' }" >生效</c:if>
									</td>
									<td class="t-td"><c:out value="${row.ag_stdate }"/></td>
									<td class="t-td"><c:out value="${row.ag_enddate }"/></td>
									<td class="t-td"><c:out value="${row.ag_totnum }"/></td>
									<td class="t-td">
										<select:timestamp timestamp="${row.ag_createdate}" idtype="ss"/>
									</td>
									<td class="t-td">
										<select:timestamp timestamp="${row.ag_updatedate}" idtype="ss"/>
									</td>
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
				<c:if test="${not empty list }">
						<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Agreement" action="agreement.do?method=agreement" />
				</c:if>
			</div>
		</div>
		</html:form>
	</div>
</div>
</body>
</html>