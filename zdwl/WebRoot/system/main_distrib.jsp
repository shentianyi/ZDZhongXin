<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>

<%@ page import="com.zd.csms.rbac.login.common.UserSessionUtil"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<url:static/>/css/base.css"/>
<link rel="stylesheet" type="text/css" href="<url:static/>/css/index_distrib.css"/>
<link rel="stylesheet" type="text/css" href="<url:static/>/css/intention.css" />
<script language="javascript" type="text/javascript" src="<url:static/>/js/jquery-1.7.1.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/common2.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/common.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/index3.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/thumbpage/thumbpage.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/intention.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/system/js/dyniframesize.js"></script>
<script language="javascript" type="text/javascript" src="<url:static/>/js/dbsy.js"></script>
<script language="javascript" type="text/javascript">
flesh();
function flesh(){
	dbsyNum('<url:context/>','dbsy_num')
	window.setInterval("dbsyNum('<url:context/>','dbsy_num')",10000);//10秒刷一次
	
}
</script>
<title>车银通 - 首页</title>
<style type="text/css">
.main_cont {
	width: 100%;
	margin: 0;
}
.main_cont .y-bar {
	position: fixed;
	left:180px;

	float: left;
	width: 13px;
	background: #ffd990;
	display:table-cell;
	vertical-align: middle;
	cursor:pointer;
}
.main_cont .y-bar font {
	position:absolute;
	top:50%;
	left:0;
	width:13px;
}
.main_cont .right-bar {
	float: left;	
}
#chechewang_main {
	float:left;
	margin-left:15px;
	min-height: 800px;
}
</style>
<script src="/csms/WebContent/js/jquery-1.7.1.js"></script>
<script type="text/javascript">
	$(function(){
		
		var oNenu = $(".main_menu");
		var oRightBar = $(".right-bar");
		var oYeBar = $(".y-bar");
		var nCw = $("#chechewang_main");
		
		function  reckon(){
		
			var oBodyW = $(".main").outerWidth();
			var oBodyH = $("body").outerHeight();
			
			//var oRightW = oRightBar.width();
			
			
			//console.log("窗体宽度"+oBodyW);
			
			oRightBar.width(oBodyW-220);
			nCw.width(oBodyW-220);
			//var oYeBarH = oBodyH;
			oYeBar.css({height:$(window).height()});
			//alert($(window).height());
			
				
			$(document).scroll(function(){ 
				//alert($(document).scrollTop());
				if($(document).scrollTop() < 80){
					oYeBar.css({top:80 - $(document).scrollTop()});
				}else{
					oYeBar.css({top:0});
				}
				
			})
		};
		reckon();
		$(window).resize(function() { 
			var oBo = $(window).outerWidth();
			reckon();
			
			//console.log("活动窗体宽度"+$(".main").outerWidth())
		});
		
		oYeBar.bind('click',function(){
			oNenu.toggle(); //左侧部分进行显示隐藏
			//var oB= $(".main_menu").is(":hidden");//是否隐藏 

			if(oNenu.is(":hidden")){//如果隐藏，则总宽度减去黄条宽度
				nCw.width($(".main").outerWidth()-20);
				oRightBar.width($(".main").outerWidth()-20);
				//alert("yincangle");
				oYeBar.css({left:0});
			}else{//如果显示，则总宽度减去左侧部分宽度
				nCw.width($(".main").outerWidth()-220);
			    oRightBar.width($(".main").outerWidth()-220);
			    oYeBar.css({left:"180px"});
			    //alert(oYeBar.style.left);
			}	
		});
	});
</script>
</head>

<body class='box'>
<div class="head">
	<div class="center_h">
		<a class="logo" href="<url:context/>" target="_self"><img src="<url:static/>/images/logo.jpg" /></a>
		<p>
	    	当前用户：<%=UserSessionUtil.getUserSession(request).getUser().getUserName()%> <br />
	    	<a href="<url:context/>/dbsy.do?method=dbsyListEntry" target="chechewang_main">待办事宜 <span id="dbsy_num" style="color: red;font-size: 11px;"></span></a>
	        <a href="<url:context/>/rbac/login.do?method=changePasswordEntry" target="chechewang_main">修改密码</a>
	        <a href="javascript:logout();">用户注销</a>
	    </p>
	    <div class="clear"></div>
	</div>
</div>
<div class="main">
	<div class="main_cont" style="width:100%; overflow:hidden;padding: 0;">
	    <c:import url="/system/menutree_distrib.jsp"></c:import>
	    <div class="y-bar"><font>屏幕切换</font></div>
	    <div class="right-bar" style="margin-left: 14px;">
	        <iframe id="chechewang_main" style="width:100%;" name="chechewang_main" allowtransparency="true" frameborder="0" scrolling="no" src="<url:context/>/account/credit.do?method=userAccountCreditList" onload="this.height=100;javascript:dyniframesize('chechewang_main');" ></iframe>
	    </div>
	</div>
</div>
</body>
</html>