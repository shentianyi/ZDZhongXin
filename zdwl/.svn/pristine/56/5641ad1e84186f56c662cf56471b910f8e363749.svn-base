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
	
	//第一步
	showShade();
	$(".menu_top").addClass("hlcont");
	$(".point1").show();
	$(".step1").show();
	
	//第二步
	$(".step1").click(function(){
		$(this).hide().siblings(".step2").show();
		$(".point1").hide().siblings(".point2").show();
		$(".menu_top").removeClass("hlcont");
		$("#step2_show").show();
	});
	
	//第三步
	$(".step2").click(function(){
		$(this).hide().siblings(".step3").show();
		$(".point2").hide().siblings(".point3").show();
		$("#step2_show").hide().siblings("#step3_show").show();
	});
	
	//关闭提示步骤
	$(".closetips").click(function(){
		closeShade();
		$(this).parents(".steparea").remove();
		$(".stepmenu").remove();
		$(".pointarea").remove();
	});
})

//设置背景遮罩层
function showShade(){
	var documentHeight = document.documentElement.clientHeight;
	$("#layoutbg").css("height",documentHeight);
	$(window).resize(function(){
		var documentHeight = document.documentElement.clientHeight;
		$("#layoutbg").css("height",documentHeight);	
	})
};

//关闭背景遮罩层
function closeShade(){
	if($("#layoutbg").length > 0){$("#layoutbg").remove();}
};