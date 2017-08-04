//页面初始化函数
	function doLoad() {
		initBank();
	    var onehiId = $("#onehiId").val();
		var twohiId = $("#twohiId").val();
		var threehiId = $("#threehiId").val();
		if (onehiId && onehiId != "" && onehiId > 0) {
			$("#one").val(onehiId);
		}
		if (twohiId && twohiId != "" && twohiId > 0) {
			loadSelect(onehiId, $("#two"));
			$("#two").val(twohiId);
		}
		if (threehiId && threehiId != "" && threehiId > 0) {
			loadSelect(twohiId, $("#three"));
			$("#three").val(threehiId);
		}
		
		
		$('.allDate').datebox({
			editable : false
		});
	
	}

	//执行查询操作(信息提醒)
	function doMessageQuery(){
		$("#method").val("findMessageList");
		document.forms[0].submit();
	}
	
	//执行查询操作(信息预警)
	function doWaringQuery(){
		$("#method").val("findWaringList");
		document.forms[0].submit();
	}

	
	//执行表单清空操作(信息提醒)
	function doMessageClear(){
		$(".allText").val("");
		$("#one").val(-1);
		$("#two").val(-1);
		$("#three").val(-1);
		$('.allDate').datebox('clear');
	}
	
	
	//执行表单清空操作(信息预警)
	function doWaringClear(){
		$(".allText").val("");
		$("#one").val(-1);
		$("#two").val(-1);
		$("#three").val(-1);
		$('.allDate').datebox('clear');
	}
	
	
	function doMessageSubmit() {
		var isReadIds = $("[name='isReadIds']:checked");
		if(isReadIds.length<=0){
				alert("请选择已读的消息");
				return false ;
		}
		
		var idsRead = [];
		isReadIds.each(function() {
			idsRead.push(this.value);
		});
		idsRead = idsRead.join();
		$("#idsRead").val(idsRead);
		$("#method").val("submitMessage");
		document.forms[0].submit();

	}
	

	function doWaringSubmit() {
		    var isReadIds = $("[name='isReadIds']:checked");
			var idsShields = $("[name='isShieldIds']:checked");
			if((!isReadIds)&&(!idsShields)){
				alert("请选择已读或要屏蔽的消息");
				return false ;
			}
			
			if(!isReadIds){
				var idsRead = [];
				isReadIds.each(function() {
					idsRead.push(this.value);
				});
				
				idsRead = idsRead.join();
				$("#idsRead").val(idsRead);
			}
			
			
			if(!idsShields){
				var idsShield = [];
				idsShields.each(function() {
					idsShield.push(this.value);
				});
				
				idsShield=idsShield.join();
				$("#idsShield").val(idsShield);
			}
			
			$("#method").val("submitWaring");
		    document.forms[0].submit();
   
	}
	
	function checkAll(obj) {
		if(obj.checked){ 
			   $("[name='isReadIds']").each(function(){
			     $(this).prop("checked",true);
			   });
		}else{
			   $("[name='isReadIds']").each(function(){
			      $(this).prop("checked",false);
			   });
	    }
	}
	
	function checkAllShield() {
		if($(this).attr("checked")){ 
			   $("[name='isShieldIds']").each(function(){
			     $(this).attr("checked",true);
			   });
		}else{
			   $("[name='isShieldIds']").each(function(){
			     $(this).attr("checked",false);
			   });
	     }
	}
	
