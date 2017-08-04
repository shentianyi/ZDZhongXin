<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/jquery-1.7.1.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
function goDetail(id){
	location = "<url:context/>/note.do?method=detail&note.id="+id;
}

</script>
</head>
<body>

<div class="title">便签</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/note" styleId="noteForm" method="post" onsubmit="return false">
	<input name="method" id="method" type="hidden" value="notesList"/>
	<div class="dvScroll">
		<table  class="listTalbe" cellpadding="0" cellspacing="0">
			<thead>
				<tr class="title">
			      <td>序号</td>
			      <td><thumbpage:order cname="标题" filedName="title"/></td>
			    </tr>
			</thead>
			<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
				<logic:iterate name="list" id="row" indexId="index">
					<tr class="listTr_a">
						<td align="center"><c:out value="${index+1}"/></td>
						<td align="center">
							<a href="javascript:goDetail('<c:out value='${row.id}'/>');">
								<c:out value="${row.title}" />
							</a>
						</td>
					</tr>
				</logic:iterate>
			</tbody>  
		</table>
	</div>
</html:form>

	</div>
</div>

</body>
</html>
