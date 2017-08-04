$(function(){
	$(".search_left").each(function(){
		//通用模拟select
		$(this).find(".select_a").click(function(){
			$(this).siblings(".select_brand").show();	
			$(this).siblings(".select_brand").find("li").each(function(){
				var liindex = $(this).index();
				if(liindex<3){     
					$(this).click(function(){
						$(this).parents("dl").find(".line4").hide().siblings(".line3").show();	
						$(this).parents(".search_left").find("span").addClass("selectcont").attr("title",$(this).text()).text($(this).text());	
						$(this).parents(".select_brand").hide();
						$("#bankId").attr("value",$(this).attr("id"));
						$("#transactionType").attr("value",$(this).attr("id"));
					});	
				}else {       //其他费用
					$(this).click(function(){
						$(this).parents("dl").find(".line3").hide().siblings(".line4").show();
						$(this).parents(".search_left").find("span").addClass("selectcont").attr("title",$(this).text()).text($(this).text());	
						$(this).parents(".select_brand").hide();
						$("#bankId").attr("value",$(this).attr("id"));
						$("#transactionType").attr("value",$(this).attr("id"));
					});	
				}
			});
			return false;//阻止事件冒泡
		});
		$(document).click(function(){
			$(this).find(".select_brand").hide();
		});
		$(".select_brand li").hover(
			function(){
				$(this).addClass("curli");	
			},
			function(){
				$(this).removeClass("curli");
			}
		);
		
		//判断可选择的数量
		if($(this).find(".select_brand").find("li").size() > 6){
			$(this).find(".select_brand").css({height:"132px",overflow:"hidden",overflowY:"scroll"});	
		}
	});
	
	//意向管理列表
	$(".line_cont").each(function(){
		if($(this).index()%2 == 0){
			$(this).find(".list_line").addClass("grayline");	
		}
	});
//	$(".detail_c").each(function(){
//		$(this).find(".detail_line").each(function(){
//			if($(this).index()%2 != 0){
//				$(this).addClass("oddline");
//			}
//		});
//		$(this).find(".detail_line:last").addClass("noborder");
//	});	
	$(".line_detail").each(function(){
		$(this).find(".detail_cont").each(function(){
			$(this).find(".upi").toggle(
				function(){
					$(this).addClass("downi").parent().siblings(".detail_c").hide();
				},
				function(){
					$(this).removeClass("downi").parent().siblings(".detail_c").show();
				}
			);	
		});
		$(this).find(".detail_cont:last").find(".detail_c").addClass("lastc");
	});	
//	$(".line_cont").each(function(){
//		$(this).find(".detaila").click(function(){
//			$(this).parents(".line_cont").find(".line_detail").show().parent(".line_cont").siblings(".line_cont").find(".line_detail").hide();	
//			if($(this).parents(".line_cont").find(".line_detail").is(":visible")){
//				$(this).parents(".line_cont").addClass("curdetail").siblings(".line_cont").removeClass("curdetail");	
//			}
//		});
//	});
	$(".line_cont").last().addClass("lastline");
	$(".ddline").each(function(){
		var val_before = $(this).find("input").val();
		$(this).find("input").focus(function(){
			var val_now = $(this).val();
			if($.trim(val_now)==$.trim(val_before)){
				$(this).val("");
			}
			$(this).parent().addClass("focusebg");	
		}).blur(function(){
			var val_now = $(this).val();
			if($.trim(val_now)=="" || $.trim(val_now)==$.trim(val_before)){
				$(this).val(val_before).parent().removeClass("focusebg").removeClass("fontline");	
			}else {
				$(this).val(val_now).parent().removeClass("focusebg").addClass("fontline");	
			}
		});
	});
	$(".detail_t").each(function(){
		$(this).find(".count_a").click(function(){
			$(this).siblings(".layoutbg").show().siblings(".count_area").show();
			$(this).siblings(".layoutbg").height($(this).siblings(".count_area").outerHeight()+5);
		});	
		$(this).find(".close_i").click(function(){
			$(this).parent().hide().siblings(".layoutbg").hide();	
		});
	});
	
	//日历效果
	$(".lc1 i").toggle(
		function(){
			$(this).next().show();	
		},
		function(){
			$(this).next().hide();	
		}
	);
	
	//添加测算内容行
	$(".add_indd").click(function(){
		var selectval = $(this).parents("dl").find(".search_left").children("span").text();
		if(selectval == $(this).parents("dl").find(".select_brand li").eq(3).text()){       //其他费用效果
			var inputval0 = $(this).siblings(".line4").eq(0).find("input").val();
			var inputval1 = $(this).siblings(".line4").eq(1).find("input").val();
			if(inputval0 != "费用描述" && inputval1 != ""){
				$(this).parents("dl").before("<dl><dt>"+selectval+"</dt><dd><div class='ddline line5 fontline'><input value="+inputval0+" /></div><div class='ddline line6 fontline'><input name='priceAdd' value="+inputval1+" /></div></dd><dd class='deldd' onclick='deldl(this);'></dd></dl>"); 	
				$(this).parents("dl").find(".search_left").children("span").removeClass("selectcont").attr("title","").text("请选择")
				$(this).siblings(".line4").eq(0).removeClass("fontline").find("input").val("费用描述").parent().hide().next().removeClass("fontline").find("input").val("").parent().hide();
				$(this).siblings(".line3").show().removeClass("fontline").find("input").val("请输入您的自定义名称");
				$(this).parents(".count_area").siblings(".layoutbg").height($(this).parents(".count_area").outerHeight()+4);
			}
		}else {
			var inputval = $(this).siblings(".line3").find("input").val();
			if(selectval != "请选择" && inputval != "请输入您的自定义名称"){
				$(this).parents("dl").before("<dl><dt>"+selectval+"</dt><dd><div class='ddline line1 fontline'><input name='priceAdd' value="+inputval+" /></div></dd><dd class='deldd' onclick='deldl(this);'></dd></dl>"); 	
				$(this).parents("dl").find(".search_left").children("span").removeClass("selectcont").attr("title","").text("请选择")
				$(this).siblings(".line3").removeClass("fontline").find("input").val("请输入您的自定义名称");
				$(this).parents(".count_area").siblings(".layoutbg").height($(this).parents(".count_area").outerHeight()+4);
			}
		}
	});
})

//删除测算新增行
function deldl(ddobj){
	$(ddobj).parent().remove();
	$(".layoutbg:visible").height($(".count_area:visible").outerHeight()+4);	
}