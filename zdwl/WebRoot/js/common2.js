$(function(){
    //首页左侧头部头像右侧展开效果
//  $(".mt_img i").toggle(
//      function(){
//          $(this).parent().addClass("topline").siblings(".mt_con").hide();    
//      },
//      function(){
//          $(this).parent().removeClass("topline").siblings(".mt_con").show();
//      }
//  );  
//  
    //首页左侧菜单效果
    $(".box_line").each(function(){
        $(this).click(function(){
//           alert(jQuery(window.parent.parent.frames["chechewang_menu_tree"].document).width());
            // alert(jQuery(window.parent.parent.frames["chechewang_menu_tree"].document);
        	var font = $(this).children('font').html();
        	if(font == "+"){
        		$(this).children('font').html('-');
        		$(this).next(".box_area").slideDown("fast");
        		//$(this).parents(".mc_box").addClass("curbox");
        	}else{
        		$(this).children('font').html('+');
        		$(this).next(".box_area").slideUp('fast');
        		//$(this).parents(".mc_box").removeClass("curbox");
        		
        	}
            /*$(this).children('font').html('-');
            $(this).parents(".mc_box").addClass("curbox").siblings(".mc_box").removeClass("curbox"); 
            $(this).parents(".mc_box").addClass("curbox").siblings(".mc_box").find('font').html('+'); */
        }); 
    });
    
    $(".box_area").find("a").each(function(){
        $(this).click(function(){
            $("a").removeClass("cur_a").children('img').attr('src','../images/leftmenu-arrow.png');
           
            $(this).addClass("cur_a").children('img').attr('src','../images/leftmenu-arrow-select.png');
        });
    });
    
        
    //首页tab切换效果
    $(".mrcont_box").first().show();
    $(".main_tab li").click(function(){
        $(this).addClass("curli").siblings("li").removeClass("curli");
        $(".mrcont_box").eq($(this).index()).show().siblings(".mrcont_box").hide();
    });
    
    //页面tab滚动效果
    $(".mr_tabs").each(function(){
        var li_size = $(this).find("li").size();    //数量
        var li_width = $(this).find("li").width()+1;   //宽度
        $(this).find(".main_tab").width(li_size*li_width);    //ul宽度
        var sroll_len = 0;    //移动的总距离
        var contimg7_len = li_width*7;    //需要移动的整体7个div的长度
        var int_len = parseInt(li_size/7);    
        if(li_size>7){
            $(this).find(".noclick_prev").show().siblings(".next").show();  
        }else {
            $(this).find(".noclick_prev").show().siblings(".noclick_next").show();      
        }
        $(".next").click(function(){
            sroll_len += contimg7_len;
            $(this).siblings(".sroll_content").find(".main_tab").animate({right:sroll_len},500);
            $(this).siblings(".noclick_prev").hide().siblings(".prev").show();
            if(li_size%7==0){
                if(sroll_len==(int_len-1)*contimg7_len){
                    $(this).hide().siblings(".noclick_next").show();    
                }   
            }else {
                if(sroll_len==int_len*contimg7_len){
                    $(this).hide().siblings(".noclick_next").show();    
                }
            }
        });
        $(".prev").click(function(){
            sroll_len -= contimg7_len;
            $(this).siblings(".sroll_content").find(".main_tab").animate({right:sroll_len},500);
            $(this).siblings(".noclick_next").hide();
            $(this).siblings(".next").show();
            if(sroll_len==0){
                $(this).hide().siblings(".noclick_prev").show();    
            }
        });
    });
})