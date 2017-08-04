<%@page language="java" contentType="application/x-msdownload" pageEncoding="UTF-8"%>
<%@page import="com.zd.csms.file.util.UploadFileUtil"%>
<%@page import="java.lang.String"%>
<%
String path = (String)request.getAttribute("path");
String absolutePath = (String)request.getSession().getServletContext().getRealPath("/");

path = path.substring(1, path.length());
path = absolutePath + path;

String fileName = (String)request.getAttribute("fileName");
new UploadFileUtil().download(path, fileName, response);
//在使用完输出流以后调用以下两行代码即可 、不加会报错
out.clear();
out = pageContext.pushBody();
%>


