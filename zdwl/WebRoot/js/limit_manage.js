$(function(){
	//额度管理选择银行
	$(".selectarea .selecta").click(function(){
		$(this).siblings(".select_cont").show();	
		$(this).siblings(".select_cont").find("li").each(function(){
			$(this).click(function(){
				$(this).parents(".selectarea").find("span").text($(this).text());
				$(this).parents(".selectarea").find("input").val($(this).attr("id"));
				$(this).parents(".select_cont").hide();
			});	
		});
		return false;//阻止事件冒泡
	});
	$(document).click(function(){
		$(".select_cont").hide();
	});
	$(".select_cont li").hover(
		function(){
			$(this).addClass("hoverli");	
		},
		function(){
			$(this).removeClass("hoverli");
		}
	);
	
	//判断可申请银行的数量
	if($(".select_cont").find("li").size() > 6){
		$(".select_cont").css({height:"132px",overflow:"hidden",overflowY:"scroll"});	
	}
})