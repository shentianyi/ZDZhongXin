$(function(){
	//详情表格
	$(".detail_line").each(function(){
		if($(this).index()%2 != 0){
			$(this).addClass("oddline");	
		}
	});
	$(".detail_cont").each(function(){
		if($(this).index()<($(this).parent().find(".detail_cont").size()-1)){
			$(this).find(".detail_line:last").addClass("lastline");
		}
	});
	//内容收起
	$(".detail_cont").each(function(){
		$(this).find(".upi").toggle(
			function(){
				$(this).addClass("downi").parent().siblings(".detail_c").hide();	
			},
			function(){
				$(this).removeClass("downi").parent().siblings(".detail_c").show();	
			}
		);
	});	
	//选项
	$(".list_select").each(function(){
		$(this).find(".select_a").click(function(){
			$(this).siblings(".select_cont").show();	
			$(this).siblings(".select_cont").find("li").each(function(){
				$(this).click(function(){
					$(this).parents(".list_select").find(".ls_cont").addClass("selectcont").text($(this).text());	
					$(this).parents(".select_cont").hide().parent().siblings(".list_area").show();
				});	
			});
			return false;//阻止事件冒泡
		});
		$(document).click(function(){
			$(this).find(".select_cont").hide();
		});
		$(".select_cont li").hover(
			function(){
				$(this).addClass("hoverli");	
			},
			function(){
				$(this).removeClass("hoverli");
			}
		);	
	});
	//列表效果
	$(".list_main").each(function(){
		$(this).find(".lm_cont").hover(
			function(){
				$(".lm_cont").removeClass("curcont").find(".list_area").hide().siblings(".list_select").find(".select_cont").hide();
				$(".list_select option").hide();
				$(".list_select option").show();
				$(this).addClass("curcont");
				if($(this).find(".ls_cont").text() != "请选择审核结果"){
					$(this).find(".list_area").show();
				}
			},
			function(){
				$(this).removeClass("curcont").find(".list_area").hide().siblings(".list_select").find(".select_cont").hide();
			}
		);	
	});
	//上传图片操作
	$(".uploadlist .lm_cont").each(function(){
		//删除上传的图片
		$(this).find("i").click(function(){
			var pluspath;	
			var newpath="";
			$(this).hide().siblings(".fileinput").show();
			var imgpath = new Array();
			imgpath = String($(this).siblings("img").attr("src")).split("/");
			for(var i = 0; i<imgpath.length-1;i++){
				pluspath = imgpath[i]+"/";
				newpath += pluspath;
			}
			$(this).siblings("img").attr("src",(newpath+"noimage.jpg"));
		});
		//编辑备注内容
		$(this).find(".list_tips_load p").click(function(){
			var p_cont = $(this).text();
			$(this).before("<textarea>"+p_cont+"</textarea>");
			$(this).remove();
		});
	});
	//列表小图预览
	$(".lm_cont").each(function(){
		$(".box_close").hide().not($(".box_close").first().show());
		$(".box_img").hide().not($(".box_img").first().show());
		$(this).find("img").mouseover(function(){
			//var img_name = $(this).attr("src");
			//var bgimg_name = String(img_name).split(".")[0] + "_big.jpg";
			//$("#imgbox").find("img").attr("src",img_name);
			//$(".box_img").find(".boximg").attr("src",img_name);
		});	
		$(this).find("img").click(function(){
			showShade();
			$("#imgbox").show();
			var img_name = $(this).attr("src");
			var bgimg_name = String(img_name).split(".")[0] + "_big.jpg";
			//$(".box_img").find(".boximg").attr("src",img_name);
		});	
	});
	$("#imgbox a").click(function(){
		closeShade();
		$("#imgbox").hide().find(".box_left p").removeClass("curli_list").parents("#imgbox").find(".box_right").remove();
		$("#pic").attr("width","780").attr("height","420").css("top","0").css("left","0");
		$("#picsmall").attr("width","200").attr("height","167").css("top","0").css("left","0");
	});
	
	//自定义滚动条
	/*$(".box_left").jscroll({ W:"15px"
					,BgUrl:"url(../images/check/scroll_bg.png)"
					,Bg:"-16px 0 repeat-y"
					,Bar:{Bg:{Out:"-32px 0px repeat-y",Hover:"-32px 0px repeat-y",Focus:"-32px 0px repeat-y"}}
					,Btn:{btn:true
						 ,uBg:{Out:"0 0",Hover:"0 0",Focus:"0 0"}
						 ,dBg:{Out:"0 -15px",Hover:"0 -15px",Focus:"0 -15px"}}
					,Fn:function(){}
	});*/
})

//设置背景遮罩层
function showShade(){
	$("#layoutbg").show();
	var documentHeight = document.documentElement.clientHeight;
	$("#layoutbg").css("height",documentHeight);
	$(window).resize(function(){
		var documentHeight = document.documentElement.clientHeight;
		$("#layoutbg").css("height",documentHeight);	
	})
};
//关闭背景遮罩层
function closeShade(){
	if($("#layoutbg").length > 0){$("#layoutbg").hide();}
};
//展示小图片
function showMinImg(img_p,imgPath,mortgageVehicleParamsId){
	var evParams=document.getElementsByName("auditCauseIdOpt_"+mortgageVehicleParamsId);
	var evParamsId;
	evParamsId = $("input[name=auditCauseIdOpt_"+mortgageVehicleParamsId+"]:checked").val();
	$("input[name=auditCauseIdOpt_"+mortgageVehicleParamsId+"][value="+evParamsId+"]").attr("checked",true); 
	
	$(img_p).addClass("curli_list").siblings().removeClass("curli_list");
	$(".box_right").remove();
	
	
	$("#picDiv_" + mortgageVehicleParamsId).css("display", "block").siblings().css("display", "none");
	$("#picDiv_" + mortgageVehicleParamsId).next(".box_close").css("display", "block");
	$(".box_left").css("display", "block");
};

