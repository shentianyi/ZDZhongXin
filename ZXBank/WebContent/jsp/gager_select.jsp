<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>质物入库申请</title>
<link type="text/css" rel="stylesheet" href="css/base.css" />
<link type="text/css" rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/jquery-ui.min.css">
<link rel="stylesheet" href="css/tablecs.css">
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/thumbpage/thumbpage.js"></script>
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
function doQuery(){
	document.forms[0].submit();
}
$(function() {
    var availableTags = [
      "41234",
      "12312",
      "12341"
    ];
    $( "#custNo" ).autocomplete({
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
            <a class="crumbs-link" href="#">质物入库申请信息查询</a>
        </div>
	</div>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="gager.do" styleId="gForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gager" />
		<div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden"><font color="#FF0000">*</font>借款企业Id：</div>
	                    <div class="input block fl hidden">
	                    	<input name="gager.gaLonentno" id="gager.gaLonentno" type="text" style="display: block;width:80%;margin-left:10%;margin-top:5px;border: 1px solid #eee;border-radius: 4px;outline: none;height:24px;" value="${gaLonentno}" />
	                    </div>
                    </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery();" class="button btn-query">查询</a>
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
							<th class="t-th">借款企业名称</th>
							<th class="t-th">操作人名称</th>
							<th class="t-th">交易流水号</th>
							<th class="t-th">纸质担保合同编号</th>
							<th class="t-th">动产质押合同编号</th>
							<th class="t-th">质物监管确认书编号</th>
							<th class="t-th">业务模式</th>
							<th class="t-th">状态</th>
							<th class="t-th">总记录数</th>
							<th class="t-th">备注</th>
							<th class="t-th">申请时间</th>
							<th class="t-th">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value='${row.gaLonentno }'/></td>
									<td class="t-td"><c:out value='${row.gaLonentname }'/></td>
									<td class="t-td"><c:out value='${row.gaOprtname }'/></td>
									<td class="t-td"><c:out value='${row.gaOrderno }'/></td>
									<td class="t-td"><c:out value='${row.gaPcgrtntno }'/></td>
									<td class="t-td"><c:out value='${row.gaCmgrtcntno }'/></td>
									<td class="t-td"><c:out value='${row.gaConfirmno }'/></td>
									<td class="t-td"><c:out value='${row.gaBizmod }'/></td>
									<td class="t-td"><c:out value='${row.gaState }'/></td>
									<td class="t-td"><c:out value='${row.gaCount }'/></td>
									<td class="t-td"><c:out value='${row.gaRemark }'/></td>
									<td class="t-td">
										<select:timestamp timestamp="${row.gaCreatedate}" idtype="ss"/>
									</td>
									<td class="t-td"><a href="commodity.do?method=commodity&gaId=${row.gaId}">详情</a></td>
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
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Gager" action="gager.do?method=gager" />
				</c:if>
			</div>
		</div>
		</html:form>
	</div>
</div>
</body>
</html>