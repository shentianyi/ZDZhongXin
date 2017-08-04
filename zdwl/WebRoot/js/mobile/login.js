$(function(){
	$(".cont_line input").focus(function(){
		$(this).addClass("focus_input");
		var input_value = $(this).val();
		if(input_value == $(this).attr("data-value")){
			$(this).val("");
		}	
	});
	$(".cont_line input").blur(function(){
		var input_value=$(this).val();
		if($.trim(input_value)==""){
			$(this).removeClass("focus_input");
			$(this).val($(this).attr("data-value"));	
		}else{
			$(this).val($.trim(input_value));
		}
	 });
	$("input[name=pwdPrompt]").focus(function () {
		$("input[name=pwdPrompt]").hide(); 
		$("input[name=password]").show().focus(); 
	});
	$("input[name=password]").blur(function () { 
		if ($(this).val() == "") { 
			$("input[name=pwdPrompt]").show(); 
			$("input[name=password]").hide(); 
		} 
	});
	$("input[name=loginPrompt]").focus(function () {
		$("input[name=loginPrompt]").hide(); 
		$("input[name=loginid]").show().focus(); 
	});
	$("input[name=loginid]").blur(function () { 
		if ($(this).val() == "") { 
			$("input[name=loginPrompt]").show(); 
			$("input[name=loginid]").hide(); 
		} 
	});
})