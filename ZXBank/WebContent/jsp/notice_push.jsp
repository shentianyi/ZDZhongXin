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
<%-- <%@ taglib uri="fmt.tld" prefix="fmt"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>
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
<script src="js/thumbpage/thumbpage.js"></script>
<!-- 全选 -->
<script type="text/javascript" language="javascript">
function doQuery(){
	document.forms[0].submit();
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
    $( "#noticeid" ).autocomplete({
      source: availableTags
    });
  });


/* 
function selectAllDels() 
{ 
var allCheckBoxs = document.getElementsByName("preDelCheck"); 
var desc = document.getElementById("allChecked"); 
var selectOrUnselect=false; 
for(var i = 0; i < allCheckBoxs.length; i ++ ) 
{ 
if(allCheckBoxs[i].checked){ 
selectOrUnselect=true; 
break; 
} 
} 
if (selectOrUnselect) 
{ 
_allUnchecked(allCheckBoxs); 
}else 
{ 
_allchecked(allCheckBoxs); 
} 
} 
function _allchecked(allCheckBoxs){ 
for(var i = 0; i < allCheckBoxs.length; i ++ ) 
{ 
allCheckBoxs[i].checked = true; 
} 
} 
function _allUnchecked(allCheckBoxs){ 
for(var i = 0; i < allCheckBoxs.length; i ++ ) 
{ 
allCheckBoxs[i].checked = false; 
} 
}  */
</script>

</head>
<body class="h-100 public">
	<div class="public-bar hidden">
		<div class="ly-contai clearfix">
			<div class="public-bar-crumbs fl hidden">
				<a class="crumbs-link" href="/ZXBank">中信银行接口</a>
				>
				<a class="crumbs-link" href="#">通知推送</a>
			</div>
		</div>
	</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="ZXnotice.do?method=findnotice" styleId="nForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="findnotice" />
		<div class="public-main-input ly-col-1 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">通知类型：</div>
	                    <div class="input block fl hidden">
	                    	<select class="ly-bor-none" id="notice" name="notice.ntType" style="min-width:150px;width:80%;">
	                    		<option>请选择</option>
	                    		<c:forEach items="${list1}" var="row">
	                    			<option value="<c:out value='${row.ntType}'/>">
	                    				 <c:if test="${row.ntType=='1'}">收货通知书</c:if>
	                    				 <c:if test="${row.ntType=='2'}">移库通知书</c:if>
	                    				 <c:if test="${row.ntType=='3'}">解除质押通知书</c:if>
	                    			</option>
	                    		</c:forEach>
	                    	</select>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">通知编号：</div>
	                    <div class="input block fl hidden">
	                    	<html:text styleClass="ly-bor-none" property="notice.ntNo"  styleId="notice.ntno" />
	                    	<!-- <input class="ly-bor-none" id="noticeid" type="text" name="pledgeName" value=""/> -->
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
							<!-- <th class="t-th"><input type="checkbox" name="allChecked" onClick="selectAllDels()"/>全选</th> -->
							<th class="t-th">序号</th>
							<th class="t-th">通知书类型</th>
							<th class="t-th">通知书编号</th>
							<th class="t-th">通知书状态</th>
							<th class="t-th">创建时间</th>
							<th class="t-th">操作</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<logic:iterate name="list" id="row" indexId="index">
							<tr class="t-tr">
								<td class="t-td"><c:out value="${index+1}"/></td>
								<td class="t-td"><%-- <c:out value="${row.ntType}"/> --%>
									 <c:if test="${row.ntType=='1'}">收货通知书</c:if>
	                    				<c:if test="${row.ntType=='2'}">移库通知书</c:if>
	                    				 <c:if test="${row.ntType=='3'}">解除质押通知书</c:if>
								</td>
								<td class="t-td"><c:out value="${row.ntNo}"/></td>
								<td class="t-td"><c:out value="${row.ntFailflag}"/></td>
								<td class="t-td"><c:out value="${row.ntStdate}"/></td>
								<td class="t-td"><c:out value="${row.ntFailflag}"/></td>
							</tr>
							
						</logic:iterate>
					</tbody>
				</table>
				<div style="overflow-x: auto; overflow-y: auto; height: 100%; width:100%">
			</div>
		</div>
			</div>
			</div>
		<div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="Notice" action="ZXnotice.do?method=findnotice" />
			</div>
		</div>
	</html:form>
	</div>
	
</div>
</body>
</html>