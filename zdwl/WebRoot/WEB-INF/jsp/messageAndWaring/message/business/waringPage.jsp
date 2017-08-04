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
<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
.title td {
	padding: 0 15px 0 15px;
}

.oneBankId, .twoBankId, .threeBankId {
	margin: 3% 10%;
	width: 50%;
	height: 64%;
}

.public-main-input .ly-col .input {
	width: 61%;
}

.public-main-input .ly-col .label {
	width: 39%;
}
</style>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/video/initinfo.js"></script>
<script type="text/javascript" src="/pagejs/messageAndWaring/businList.js"></script>  
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>

</head>
<body class="h-100 public" onLoad="doLoad()">
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/businessRemindAndWaring.do" styleId="businessMessageForm"
				method="post" onsubmit="return false">
                 <%@include file="./waringQuery.jsp"%> 
                 
				<div class="public-main-table hidden abs" style="top: 170px;">
					<div class="ly-cont">
					 <%@include file="./waringList.jsp"%>
					</div>
				</div>
				
				<div class="public-main-footer hidden abs">
					<a href="javascript:doWaringSubmit();" class="button btn-add fl">提交</a>
					<div class="public-main-footer-pagin fr">
						<thumbpage:tools
							className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>"
							tableName="list" action="/businessRemindAndWaring.do?method=findWaringList" />
					</div>
				</div>
				
			</html:form>
		</div>
	</div>
	
   <script type="text/javascript">
     var queryIdStr=<c:out value="${queryIds}"  escapeXml="false" /> ;
     var columnClasssStr=<c:out value="${columnClasss}"  escapeXml="false" /> ;
     var queryIds=queryIdStr.split(',');
     var columnClasss=columnClasssStr.split(',');
     
     $.each(queryIds,function(index,value){
         $("#"+value).show();
      }); 
     
     $.each(columnClasss,function(index,value){
         $("."+value).show();
    });
   </script>
   <script src="/pagejs/scrollbar.js"></script>
</body>
</html>