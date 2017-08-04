<%@ page contentType="text/html; charset=gb2312" %> 
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html><head><title>htmlArea Example</title>


<script language="Javascript1.2"><!-- // load htmlarea
_editor_url = "editor/";                     // URL to htmlarea files
var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
if (navigator.userAgent.indexOf('Mac')        >= 0) { win_ie_ver = 0; }
if (navigator.userAgent.indexOf('Windows CE') >= 0) { win_ie_ver = 0; }
if (navigator.userAgent.indexOf('Opera')      >= 0) { win_ie_ver = 0; }
if (win_ie_ver >= 5.5) {
  document.write('<scr' + 'ipt src="' +_editor_url+ 'editor.js"');
  document.write(' language="Javascript1.2"></scr' + 'ipt>');  
} else { document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); }
// --></script>



</head>
<body>
<a name="top"></a>

<form method=POST action="">

<p>Sample textarea follows:</p>

<textarea name="yourFieldNameHere" style="width:800; height:150">

</textarea><br>

<b>Example "InsertHTML" links: </b> &nbsp;
<a href="javascript:editor_insertHTML('yourFieldNameHere','<font style=\'background-color: yellow\'>','</font>',1);">Highlight selected text</a> -
<a href="javascript:editor_insertHTML('yourFieldNameHere',':)');">Insert Smiley</a>
<a href="javascript:alert(editor_getHTML('yourFieldNameHere'));">getHTML</a>
<a href="javascript:editor_setHTML('yourFieldNameHere','<b>Hello World</b>!!');">setHTML</a>

<script language="javascript1.2">
editor_generate('yourFieldNameHere');
</script>

</form>
</body></html>