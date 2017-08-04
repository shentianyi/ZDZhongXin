$(function(){
	//填写基础信息时效果
	$(".bidd_input").each(function(){
		$(this).find("input:not(:button)").focus(function(){
			$(this).addClass("focus_in");	
		}).blur(function(){
			$(this).removeClass("focus_in");	
		});	
	});
	
	//填写详情信息时效果
	$(".input_line, .cont_line").find("input:not(:button)").each(function(){
		var val_before = $(this).val();
		$(this).focus(function(){
			var val_now = $(this).val();
			if($(this).val()==$.trim(val_before)){
				$(this).val("");
			}
			$(this).addClass("focus_in");
		}).blur(function(){
			var val_now = $(this).val();
			if($.trim(val_now)=="" || $.trim(val_now)==$.trim(val_before)){
				$(this).val(val_before).removeClass("focus_in").removeClass("input_c");	
			}else {
				$(this).removeClass("focus_in").addClass("input_c");
			}
		});
	});
	
	//模拟select选择框效果
	$(".select_div").each(function(){
		$(this).find(".select_a").click(function(){
			$(this).addClass("selected")
					.parents(".select_div").addClass("click_div")
					.parents(".cont_line").addClass("select_contline")
					.siblings(".cont_line").removeClass("select_contline")
					.find(".select_div ").removeClass("click_div")
					.find(".select_cont").hide()
					.siblings(".select_a").removeClass("selected");
			$(this).siblings(".select_cont").show().find("li").each(function(){
				$(this).click(function(){
					$(this).parents(".select_div").find("span").addClass("spe_span").text($(this).text()).siblings(".select_a").removeClass("selected");
					$(this).parents(".select_div").find("input").val($(this).attr("id"));
					$(this).parents(".select_cont").hide();
					$(this).parents(".select_div").removeClass("click_div");
					$(this).parents(".select_div").parents(".cont_line").find("p").text("");
					$(this).parents(".select_div").parents(".cont_line").find("i").removeClass("noimg").removeClass("false_i").addClass("correct_i");
				});	
			});
			return false;//阻止事件冒泡
		});
	});
	$(document).click(function(){
		$(".select_cont").hide();
		$(".select_div").removeClass("click_div");
		$(".cont_line").removeClass("select_contline");
		$(".select_a").removeClass("selected");
	});
	$(".select_cont li").hover(
		function(){
			$(this).addClass("hover_li");	
		},
		function(){
			$(this).removeClass("hover_li");
		}
	);	
	
	//添加公司股东效果
	var flag0,flag1;
	var $inputline0 = $(".input_line").eq(0);
	var $inputline1 = $(".input_line").eq(1);
	var addinput0_before = $inputline0.find(".add_input").val();
	var addinput1_before = $inputline1.find(".add_input").val();
	$inputline0.find(".add_input").blur(function(){
		var addinput0_now = $inputline0.find(".add_input").val();
		if(addinput0_now != "" && addinput0_now != addinput0_before){
			flag0 = true;
		}else {
			flag0 = false;	
		}
		inputBlur(flag0,flag1);
	});
	$inputline1.find(".add_input").blur(function(){
		var addinput1_now = $inputline1.find(".add_input").val();
		if(addinput1_now != "" && addinput1_now != addinput1_before){
			flag1 = true;
		}else {
			flag0 = false;	
		}
		inputBlur(flag0,flag1);
	});
})

//添加公司股东信息
function inputBlur(flag0,flag1){
	if(flag0 && flag1){
		$(".add_btn").removeClass("noclick_btn");
	}else {
		$(".add_btn").addClass("noclick_btn");
	}
}