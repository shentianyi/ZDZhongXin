<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="url.tld" prefix="url"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>My JSP 'hidden.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		      #shleft{
			margin:0px;
			padding:0px;
			width: 100%;
			height: 100%;
			border:0px;
			top: 0px;
			left:0px;
			right:0px;
			background-image: url(/system/images/bg_2.jpg);
			background-repeat: repeat-y;
          }  
          body{
          	margin:0px;
			padding:0px;
			top: 0px;
			left:0px;
			overflow-x:hidden;
           }  
	</style>
<script type="text/javascript">
	/*    解决序号18的左侧菜单栏可以隐藏和展开 */
		function shleft(){
			var parent_window_ele=window.parent.document.getElementById('chechewang_body');
			var  left_frame_eles=parent_window_ele.getElementsByTagName('frame');
 
		if(left_frame_eles.length==3){
		 	parent_window_ele.removeChild(left_frame_eles[0]);
	 		parent_window_ele.setAttribute('cols','25,*');
	 		document.getElementById("shleft").style.backgroundImage="url(/system/images/bg_1.jpg)";
		}else{
		 	var ele=document.createElement('frame');
		    ele.name="chechewang_menu_tree";
		   	ele.target="main";
		    ele.src="<url:context/>/system/menutree2.jsp";	  
		   parent_window_ele.insertBefore(ele,left_frame_eles[0]); 
		   parent_window_ele.setAttribute('cols','297,25,*');
		    document.getElementById("shleft").style.backgroundImage="url(/system/images/bg_2.jpg)";
		}
}
</script>
  </head>
  
  <body>
    <input type="button" name="shleft" id="shleft" onclick="shleft();" value="">
    
  </body>
</html>
