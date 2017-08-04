function regCertificate(regCertificate,regCertificate2,regCertificate3,regCertificate4,img,img2,img3,img4){
	var formTable=document.getElementById('formTable');
	var rows=formTable.rows;
	if(regCertificate != -1 || regCertificate2 != -1 || regCertificate3 != -1 || regCertificate4 != -1){
		for(var i=1;i<=7;i++){
			var tr=rows[i];
			var td=tr.cells[0];
			tr.removeChild(td);
		}
		var td=formTable.rows[0].cells[1];
		td.rowSpan=8;
		td.innerHTML = "";
		if(regCertificate != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+regCertificate+"');";
			ahref.innerHTML=img;
			td.appendChild(ahref);
		}
		
		if(regCertificate2 != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv("+regCertificate2+");";
			ahref.innerHTML=img2;
			td.appendChild(ahref);
		}
		
		if(regCertificate3 != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv("+regCertificate3+");";
			ahref.innerHTML=img3;
			td.appendChild(ahref);
		}
		
		if(regCertificate4 != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv("+regCertificate4+");";
			ahref.innerHTML=img4;
			td.appendChild(ahref);
		}
	}
}

function businessContract(businessContract,businessContract2,businessContract3,businessContract4,img,img2,img3,img4){
	var formTable=document.getElementById('formTable');
	var rows=formTable.rows;
	if(businessContract != -1 || businessContract2 != -1 || businessContract3 != -1 || businessContract4 != -1){
		for(var i=1;i<=7;i++){
			var tr=rows[i];
			if(tr.cells.length == 1){
				var td=tr.cells[0];
				tr.removeChild(td);
			}else{
				var td=tr.cells[1];
				tr.removeChild(td);
			}
		}
		var td=formTable.rows[0].cells[3];
		td.rowSpan=8;
		td.innerHTML = "";
		if(businessContract != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+businessContract+"');";
			ahref.innerHTML=img;
			td.appendChild(ahref);
		}
		
		if(businessContract2 != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+businessContract2+"');";
			ahref.innerHTML=img2;
			td.appendChild(ahref);
		}
		
		if(businessContract3 != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+businessContract3+"');";
			ahref.innerHTML=img3;
			td.appendChild(ahref);
		}
		
		if(businessContract4 != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+businessContract4+"');";
			ahref.innerHTML=img4;
			td.appendChild(ahref);
		}
		
	}
}

function carKey(carKey,img){
	var formTable=document.getElementById('formTable');
	var rows=formTable.rows;
	
	if(carKey != -1){
		for(var i=9;i<=9;i++){
			var tr=rows[i];
			var td=tr.cells[0];
			tr.removeChild(td);
		}
		var td=formTable.rows[8].cells[1];
		td.rowSpan=2;
		td.innerHTML = "";
		if(carKey != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+carKey+"');";
			ahref.innerHTML=img;
			td.appendChild(ahref);
		}
		
	}
}


function vehicleLicense(vehicleLicense,img){
	var formTable=document.getElementById('formTable');
	var rows=formTable.rows;
	
	if(vehicleLicense != -1){
		for(var i=9;i<=9;i++){
			var tr=rows[i];
			
			if(tr.cells.length == 1){
				var td=tr.cells[0];
				tr.removeChild(td);
			}else{
				var td=tr.cells[1];
				tr.removeChild(td);
			}
		}
		var td=formTable.rows[8].cells[3];
		td.rowSpan=2;
		td.innerHTML = "";
		if(carKey != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+vehicleLicense+"');";
			ahref.innerHTML=img;
			td.appendChild(ahref);
		}
		
	}
}

function identity(identity,identity2,img,img2){
	var formTable=document.getElementById('formTable');
	var rows=formTable.rows;
	
	if(identity != -1 || identity2 != -1){
		for(var i=11;i<=13;i++){
			var tr=rows[i];
			var td=tr.cells[0];
			tr.removeChild(td);
		}
		var td=formTable.rows[10].cells[1];
		td.rowSpan=5;
		td.innerHTML = "";
		if(identity != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+identity+"');";
			ahref.innerHTML=img;
			td.appendChild(ahref);
		}
		if(identity2 != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+identity2+"');";
			ahref.innerHTML=img2;
			td.appendChild(ahref);
		}
		
		
	}
}

function invoice(invoice,invoice2,img,img2){
	var formTable=document.getElementById('formTable');
	var rows=formTable.rows;
	
	if(invoice != -1){
		for(var i=11;i<=14;i++){
			var tr=rows[i];
			if(tr.cells.length == 1){
				var td=tr.cells[0];
				tr.removeChild(td);
			}else{
				var td=tr.cells[1];
				tr.removeChild(td);
			}
		}
		var td=formTable.rows[10].cells[3];
		td.rowSpan=5;
		td.innerHTML = "";
		if(invoice != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+invoice+"');";
			ahref.innerHTML=img;
			td.appendChild(ahref);
		}
		
		if(invoice2 != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+invoice2+"');";
			ahref.innerHTML=img2;
			td.appendChild(ahref);
		}
		
	}
}


function taxPaidProof(taxPaidProof,img){
	var formTable=document.getElementById('formTable');
	var rows=formTable.rows;
	
	if(taxPaidProof != -1){
		for(var i=16;i<=16;i++){
			var tr=rows[i];
			var td=tr.cells[0];
			tr.removeChild(td);
		}
		var td=formTable.rows[15].cells[1];
		td.rowSpan=2;
		td.innerHTML = "";
		if(taxPaidProof != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+taxPaidProof+"');";
			ahref.innerHTML=img;
			td.appendChild(ahref);
		}
		
	}
}

function vehicleLock(vehicleLock,img){
	var formTable=document.getElementById('formTable');
	var rows=formTable.rows;
	
	if(vehicleLock != -1){
		for(var i=16;i<=16;i++){
			var tr=rows[i];
			if(tr.cells.length == 1){
				var td=tr.cells[0];
				tr.removeChild(td);
			}else{
				var td=tr.cells[1];
				tr.removeChild(td);
			}
		}
		var td=formTable.rows[15].cells[3];
		td.rowSpan=2;
		td.innerHTML = "";
		if(vehicleLock != -1){
			var ahref=document.createElement('a');
			ahref.href="javascript:showDiv('"+vehicleLock+"');";
			ahref.innerHTML=img;
			td.appendChild(ahref);
		}
		
	}
}

function setImagePreview(fileId) {
	var docObj = document.getElementById(fileId);
	var imgObjPreview = document.getElementById("preview");
	if(docObj.files && docObj.files[0]){
		//火狐下，直接设img属性 
		imgObjPreview.style.width = '500px';
		imgObjPreview.style.height = '300px';
		//imgObjPreview.src = docObj.files[0].getAsDataURL();
		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
	}else{
		//IE下，使用滤镜
		docObj.select();
		//获取图片路径
		var imgSrc = document.selection.createRange().text;
		var localImagId = document.getElementById("localImag");
		//必须设置初始大小
		localImagId.style.width = "500px";
		localImagId.style.height = "300px";
		//图片异常的捕捉，防止用户修改后缀来伪造图片
		try{
			localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
		}catch(e){
			return false;
		}
		
		imgObjPreview.style.display = 'none';
		document.selection.empty();
	}
	return true;
}

function checketransferTypeRadio(type){
	if(type==1){
		document.getElementById("starchar3").innerHTML="身份证";
		
		$('#formTable').find('.first input').prop('disabled', true);
		$('#formTable').find('.two img, .two .starchar').show();
		$('#formTable').find('.first .starchar').hide();
		$('#starchar2').css('color','#b3a8a8');
		$('#starchar3').css('color','#000000');
		$('#formTable').find('.three .starchar').show();
		$('#formTable').find('.three input').prop('disabled', false);
		$('#formTable').find('.five').show();
		
	}else if(type==2){
		document.getElementById("starchar3").innerHTML="身份证";
		
		$('#formTable').find('.first input').prop('disabled', false);
		$('#formTable').find('.two img, .two .starchar').hide();
		$('#formTable').find('.first .starchar').show();
		$('#starchar2').css('color','#000000');
		$('#starchar3').css('color','#000000');
		$('#formTable').find('.three .starchar').show();
		$('#formTable').find('.three input').prop('disabled', false);
		$('#formTable').find('.five').hide();
		
	}else if(type==3){
		
		document.getElementById("starchar3").innerHTML="组织机构代码证（营业执照）";
		
		$('#formTable').find('.two img, .two .starchar').show();
		$('#starchar2').css('color','#b3a8a8');
		$('#formTable').find('.first .starchar').hide();
		$('#formTable').find('.three .starchar').show();
		$('#formTable').find('.three input').prop('disabled', false);
		$('#formTable').find('.first input').prop('disabled', true);
	
	}else if(type==4){
		document.getElementById("starchar3").innerHTML="组织机构代码证（营业执照）";
		
		$('#formTable').find('.two img, .two .starchar').hide();
		$('#formTable').find('.three .starchar').show();
		$('#starchar2').css('color','#000000');
		$('#formTable').find('.first .starchar').show();
		$('#formTable').find('.three input').prop('disabled', false);
		$('#formTable').find('.first input').prop('disabled', false);
	
	}
}

function doSave(){
  	//过户类型
  	var transfertype = document.getElementById("transfer.transferType").value;

  	var regCertificate = document.getElementById("regCertificate");
	var vehicleLicense = document.getElementById("vehicleLicense");
	var businessContract = document.getElementById("businessContract");
	var identity = document.getElementById("identity");
	var carKey = document.getElementById("carKey");
	
	
	if(regCertificate != null ){
		if(regCertificate.value == null || regCertificate.value == ""){
			alert("请至少上传第一张机动车登记证图片");
			return false;
		}
	}
	
	if(vehicleLicense !=null){
		if(vehicleLicense.value == null || vehicleLicense.value == ""){
			alert("请上传行驶证图片");
			return false;
		}
	}
	
	if(carKey != null){
		if(carKey.value == null || carKey.value == ""){
			alert("请上传车钥匙图片");
			return false;
		}	
	}
	
	if(transfertype == 1){
		if(identity != null){
			if(identity.value == null || identity.value == ""){
				alert("请上传车主(正面)身份证图片");
				return false;
			}
		}
		
		//提交表单
		document.forms[0].submit();
	}else if(transfertype == 2){
		
		if(businessContract != null){
			if(businessContract.value == null || businessContract.value == ""){
				alert("请至少上传第一张二手车交易合同图片");
				return false;
			}
		}
		
		if(identity != null){
			if(identity.value == null || identity.value == ""){
				alert("请上传车主(正面)身份证图片");
				return false;
			}
		}
		//提交表单
		document.forms[0].submit();
	}else if(transfertype == 3){
		if(identity != null){
			if(identity.value == null || identity.value == ""){
				alert("请上传组织机构代码证（营业执照图片）");
				return false;
			}
		}	
		
		//提交表单
		document.forms[0].submit();
	}else if(transfertype == 4){
		if(identity != null){
			if(identity.value == null || identity.value == ""){
				alert("请上传组织机构代码证（营业执照图片）");
				return false;
			}
		}
		if(businessContract != null){
			if(businessContract.value == null || businessContract.value == ""){
				alert("请至少上传第一张二手车交易合同图片");
				return false;
			}
		}
		//提交表单
		document.forms[0].submit();
	}else{
		alert("过户类型错误，请重新选择!");
		return false;
	}

	
}
