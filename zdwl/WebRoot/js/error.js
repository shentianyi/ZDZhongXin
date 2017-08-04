// 404错误页面 5秒后跳转
   $(function(){    
          var seconds=5;
          jQuery("#seconds").html(seconds);
          setInterval(function(){         
            seconds--;
            jQuery("#seconds").html(seconds);
            if(seconds<2){
              window.top.location.href="http://www.baidu.com";
              return;
            }
          },1000);
      });