<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Enumeration"%>
<%
Enumeration names = request.getParameterNames();
String urlParameter = "";
while(names.hasMoreElements()){
	String key = (String)names.nextElement();
	String[] value = request.getParameterValues(key);
	if(!key.equals("url")){
		for(int i = 0; i < value.length; i++){
			urlParameter += "&"+key+"="+value[i];
		}
	}
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<base target="_self">
</head>

<body>
<form>
</form>
</body>
</html>
<script>
alert(311);
var url = "<%=request.getParameter("url")+urlParameter%>";
var parameters = url.split("&");
alert(312);
if(parameters[0].indexOf("?")!=-1){
	var oHidden = document.createElement("INPUT");
	oHidden.type = "hidden";
	oHidden.name = parameters[0].substring(parameters[0].indexOf("?")+1,parameters[0].indexOf("="));
	oHidden.value = parameters[0].substring(parameters[0].indexOf("=")+1);
	document.forms[0].appendChild(oHidden);
}

alert(314);
for(var i = 1; i < parameters.length; i++){
	var oHidden = document.createElement("INPUT");
	oHidden.type = "hidden";
	oHidden.name = parameters[i].substring(0,parameters[i].indexOf("="));
	oHidden.value = parameters[i].substring(parameters[i].indexOf("=")+1);
	document.forms[0].appendChild(oHidden);
}
alert(315);
document.forms[0].action = url;
document.forms[0].submit();
alert(316);
</script>
