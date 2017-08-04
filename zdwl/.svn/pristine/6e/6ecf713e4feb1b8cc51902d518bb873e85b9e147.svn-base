//结算弹出层
   $(function(){
		$(".bl_c_icon1 a,.bl_c_icon6 a").click(function(){
			  var wid=$(document).width();
			  var hei=$(document).height();
			  var wid2=$("#loanslayer").width();
			  var le=(wid-wid2)/2;
			  $("#black").css("width",wid);
			  $("#black").css("height",hei);
			  $("#loanslayer").css("left",le);
			  $("#black,#loanslayer").show();
	});
	//关闭结算弹出层
	$(".layer_title i,.layer_close").click(function(){
	  $("#black,#loanslayer").hide();
	});
})