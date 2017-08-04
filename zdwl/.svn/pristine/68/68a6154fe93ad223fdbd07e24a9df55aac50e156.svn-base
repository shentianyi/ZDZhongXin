$(function(){

	//搜索效果
	$(".sc_img").toggle(
		function(){
			$(this).addClass("curimg").siblings(".sc_cont").show();	
		},
		function(){
			$(this).removeClass("curimg").siblings(".sc_cont").hide();
		}
	);
	
	//融资记录显示效果
	$(".list_line").each(function(){
		if($(this).index()%2 == 0){
			$(this).addClass("grayline");	
		}
	});
	
	//日历效果
	$(".date_c1 i").toggle(
		function(){
			$(this).next().show();	
		},
		function(){
			$(this).next().hide();	
		}
	);
	
	//提醒还款期限
	$(".brownfont").hover(
		function(){
			$(this).find("span").show().parents(".list_line").css("zIndex","10");
		},
		function(){
			$(this).find("span").hide().parents(".list_line").css("zIndex","1");
		}
	);
	


})