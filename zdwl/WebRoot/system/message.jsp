<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<link href="<url:static/>/system/css/css.css" type=text/css rel=stylesheet>
<script src="<url:static/>/js/common.js"></script>
<script src="<url:static/>/js/jquery-1[1].2.6.js"></script>
<input type="hidden" name="sendsys_loader_user_id" value="<c:out value='${userdata.user.user_id}'/>"/>
<script>

function todoListCount_loadBegin(){
	var user_id = getElement("sendsys_loader_user_id").value;

	var url = '<url:static/>/workflow/run.do?method=personalTodoListCount'+'&login_user_id='+user_id;
	url += "&parameter_noCahe="+createRandomNumber(10000);

	$.get(url,function(toduCount){
		todoListCount_loadEnd(toduCount);
    });
}

function todoListCount_loadEnd(toduCount){
	document.all.todoHref.innerText = toduCount;
}

todoListCount_loadBegin();
var loadTodoTimer = setInterval("todoListCount_loadBegin()",10000);


function messageListCount_loadBegin(){
	var user_id = getElement("sendsys_loader_user_id").value;

	var url = '<url:static/>/msg/message.do?method=messagelistCount'+'&login_user_id='+user_id;
	url += "&parameter_noCahe="+createRandomNumber(10000);

	$.get(url,function(messageCount){
		messageListCount_loadEnd(messageCount);
    });
}

function messageListCount_loadEnd(messageCount){
	document.all.messageHref.innerText = messageCount;
}

messageListCount_loadBegin();
var loadMessageTimer = setInterval("messageListCount_loadBegin()",10000);

</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车银通</title>
<link href="<url:static/>/system/css/homecss.css" type=text/css rel=stylesheet>

</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#000000">
		<tr>
			<td width="16" height="34" background="images/6.jpg"><div align="center"><img src="images/Speaker.gif" width="11" height="11" /></div></td>
			<td width="116" background="images/6.jpg" class="Matters">
				<div align="left">
					您有
					<font color="#FF0000"><strong>
						<a href="<url:static/>/workflow/run.do?method=personalTodoListEntry"  target="mineplatform_oa_main" style="font-size:12px;color:#FF0000;" id="todoHref"></a>
					</strong></font>
					条待办事项<span class="Matters">！</span>
				</div>
			</td>
			<td width="19" background="images/6.jpg" class="Matters"><img src="images/massage.gif" width="16" height="13" /></td>
			<td width="707" background="images/6.jpg" class="Matters">
				<div align="left">
					您有
					<font color="#FF0000"><strong>
						<a href="<url:static/>/msg/message.do?method=searchMessagelist"  target="mineplatform_oa_main" style="font-size:12px;color:#FF0000;" id="messageHref"></a>
					</strong></font>
					条新消息<span class="Matters">！</span>
				</div>
			</td>
			<td width="28" background="images/6.jpg" class="new">&nbsp;</td>
			<td width="90" background="images/6.jpg" class="new">&nbsp;</td>
			<td width="6" background="images/right.jpg" class="new"></td>
		</tr>
	</table>
</body>
</html>