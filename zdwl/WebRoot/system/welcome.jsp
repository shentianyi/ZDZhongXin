<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>展示代办信息</title>
        <style>
          html,body{
          	width:100%;
          	height:100%;
          }
      
        </style>
        <link href="/css/base.css" rel="stylesheet" type="text/css" />
		<link href="/css/userman.css" rel="stylesheet" type="text/css" />
		<script src="/js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="/js/jquery.divscroll.js"></script>
		<script type="text/javascript">
		    $('.userman-table-tbody').perfectScrollbar();
		</script>
    </head>
    <body>
    	<div style="height:100%;overflow: hidden;" >
	   		<iframe frameborder="0" src="/message.do?method=messageList" style="width:100%; height:100%;
	   		overflow-y:scroll;">
	        	
	        </iframe>
	        <!-- <iframe  frameborder="0" src="/notice.do?method=noticesList" style="border:1px solid black; float:left; width:49%;text-align:right; height:49%;overflow-y:scroll;">

	        </iframe>
	        <iframe frameborder="0" src="/message.do?method=messageList" style="border:1px solid black; float:left; width:49%;text-align:left; height:49%;overflow-y:scroll;">
	        	
	        </iframe>
	        <iframe frameborder="0" src="/note.do?method=notesList" style="border:1px solid black; float:left; width:49%;text-align:right; height:49%;overflow-y:scroll;">
	        	
	        </iframe>
	         -->
	   	</div>
	   	
    </body>
</html>
