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
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<link href="/css/base.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script type="text/javascript">
function messageRead(id){
	$.getJSON("/json/messageRead.do?callback=?&id="+id, function(json){
		  console.info(json);
	});
}
</script>
</head>
<body onload="mynav2();">
<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#"></a>
     </span>
     
</div>
<div class="title">预警</div>
<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/message" styleId="messageForm" method="post" onsubmit="return false">
<input name="method" id="method" type="hidden" value="warningList"/>
<html:hidden property="messagequery.type" styleId="messagequery.type" />
<div class="dvScroll">
<table  class="listTalbe" cellpadding="0" cellspacing="0">
	<thead>
		<tr class="title">
	      <td>序号</td>
	      <td><thumbpage:order cname="预警类别" filedName="name"/></td>
	      <td><thumbpage:order cname="预警名称" filedName="name"/></td>
	    </tr>
	</thead>
	<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
		<logic:iterate name="list" id="row" indexId="index">
			<tr class="listTr_a">
				<td align="center"><c:out value="${index+1}"/></td>
				<td align="center"><select:warntype mid="${row.type}"></select:warntype></td>
				<td align="center">
					<a href="<c:out value="${row.url}"/>" target="chechewang_main" onclick="messageRead('<c:out value="${row.id }"/>')">
						<c:out value="${row.name}" />
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
