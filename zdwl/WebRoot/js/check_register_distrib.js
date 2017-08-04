function check_distribName(distribName){
	if(distribName.value==""||distribName.value=="请输入公司名称"){
		$("#distribName_correc").text("请输入公司名称");
		$("#distribName_cont").removeClass("noimg");
		$("#distribName_cont").addClass("false_i");
	}else{
		$("#distribName_correc").text("");
		$("#distribName_cont").removeClass("noimg");
		$("#distribName_cont").removeClass("false_i");
		$("#distribName_cont").addClass("correct_i");
	}
}
function check_distribKindId(distribKindId){
	if(distribKindId.value==""){
		$("#distribKindId_correc").text("请选择公司类型");
		$("#distribKindId_cont").removeClass("noimg");
		$("#distribKindId_cont").addClass("false_i");
	}else{
		$("#distribKindId_correc").text("");
		$("#distribKindId_cont").removeClass("noimg");
		$("#distribKindId_cont").removeClass("false_i");
		$("#distribKindId_cont").addClass("correct_i");
	}
}
function check_organizationsCardNum(organizationsCardNum){
	if(organizationsCardNum.value==""||organizationsCardNum.value=="组织机构代码证号"){
		$("#organizationsCardNum_correc").text("请输入组织机构代码证号");
		$("#organizationsCardNum_cont").removeClass("noimg");
		$("#organizationsCardNum_cont").addClass("false_i");
	}else{
		$("#organizationsCardNum_correc").text("");
		$("#organizationsCardNum_cont").removeClass("noimg");
		$("#organizationsCardNum_cont").removeClass("false_i");
		$("#organizationsCardNum_cont").addClass("correct_i");
	}
}
function check_distribTypeId(distribTypeId){
	if(distribTypeId.value==""){
		$("#distribTypeId_correc").text("请选客户类型");
		$("#distribTypeId_cont").removeClass("noimg");
		$("#distribTypeId_cont").addClass("false_i");
	}else{
		$("#distribTypeId_correc").text("");
		$("#distribTypeId_cont").removeClass("noimg");
		$("#distribTypeId_cont").removeClass("false_i");
		$("#distribTypeId_cont").addClass("correct_i");
	}
}
function check_brandId(brandId){
	if(brandId.value==""){
		$("#brandId_correc").text("请选主营品牌");
		$("#brandId_cont").removeClass("noimg");
		$("#brandId_cont").addClass("false_i");
	}else{
		$("#brandId_correc").text("");
		$("#brandId_cont").removeClass("noimg");
		$("#brandId_cont").removeClass("false_i");
		$("#brandId_cont").addClass("correct_i");
	}
}
function check_registeredAddress(registeredAddress){
	if(registeredAddress.value==""||registeredAddress.value=="公司注册地址"){
		$("#registeredAddress_correc").text("请输入公司注册地址");
		$("#registeredAddress_cont").removeClass("noimg");
		$("#registeredAddress_cont").addClass("false_i");
	}else{
		$("#registeredAddress_correc").text("");
		$("#registeredAddress_cont").removeClass("noimg");
		$("#registeredAddress_cont").removeClass("false_i");
		$("#registeredAddress_cont").addClass("correct_i");
	}
}
function check_registeredCapital(registeredCapital){
	if(registeredCapital.value==""||registeredCapital.value=="注册资本(万元)"){
		$("#registeredCapital_correc").text("请输入注册资本");
		$("#registeredCapital_cont").removeClass("noimg");
		$("#registeredCapital_cont").addClass("false_i");
	}else if(!moneryGS(registeredCapital.value)){
		$("#registeredCapital_correc").text("不符合金额格式!");
		$("#registeredCapital_cont").removeClass("noimg");
		$("#registeredCapital_cont").addClass("false_i");
	}else{
		$("#registeredCapital_correc").text("");
		$("#registeredCapital_cont").removeClass("noimg");
		$("#registeredCapital_cont").removeClass("false_i");
		$("#registeredCapital_cont").addClass("correct_i");
	}
}
function check_paiclupCapital(paiclupCapital){
	if(paiclupCapital.value==""||paiclupCapital.value=="实收资本(万元)"){
		$("#paiclupCapital_correc").text("请输入实收资本");
		$("#paiclupCapital_cont").removeClass("noimg");
		$("#paiclupCapital_cont").addClass("false_i");
	}else if(!moneryGS(paiclupCapital.value)){
		$("#paiclupCapital_correc").text("不符合金额格式!");
		$("#paiclupCapital_cont").removeClass("noimg");
		$("#paiclupCapital_cont").addClass("false_i");
	}else{
		$("#paiclupCapital_correc").text("");
		$("#paiclupCapital_cont").removeClass("noimg");
		$("#paiclupCapital_cont").removeClass("false_i");
		$("#paiclupCapital_cont").addClass("correct_i");
	}
}
function check_establishmentDate(){
	var establishmentDate =  getElement("show_establishmentDate");
	if(establishmentDate.innerHTML=="请选择公司成立日期"){
		$("#establishmentDate_correc").text("请选择公司成立日期");
		$("#establishmentDate_cont").removeClass("noimg");
		$("#establishmentDate_cont").addClass("false_i");
	}else{
		$("#establishmentDate_correc").text("");
		$("#establishmentDate_cont").removeClass("noimg");
		$("#establishmentDate_cont").removeClass("false_i");
		$("#establishmentDate_cont").addClass("correct_i");
	}
}
function check_corporationName(corporationName){
	if(corporationName.value==""||corporationName.value=="法人代表姓名"){
		$("#corporationName_correc").text("请输入法人代表姓名");
		$("#corporationName_cont").removeClass("noimg");
		$("#corporationName_cont").addClass("false_i");
	}else{
		$("#corporationName_correc").text("");
		$("#corporationName_cont").removeClass("noimg");
		$("#corporationName_cont").removeClass("false_i");
		$("#corporationName_cont").addClass("correct_i");
	}
}
function check_landUsewayId(landUsewayId){
	if(landUsewayId.value==""){
		$("#landUsewayId_correc").text("请选土地使用方式");
		$("#landUsewayId_cont").removeClass("noimg");
		$("#landUsewayId_cont").addClass("false_i");
	}else{
		$("#landUsewayId_correc").text("");
		$("#landUsewayId_cont").removeClass("noimg");
		$("#landUsewayId_cont").removeClass("false_i");
		$("#landUsewayId_cont").addClass("correct_i");
	}
}
function check_landTypeId(landTypeId){
	if(landTypeId.value==""){
		$("#landTypeId_correc").text("请选土地类型");
		$("#landTypeId_cont").removeClass("noimg");
		$("#landTypeId_cont").addClass("false_i");
	}else{
		$("#landTypeId_correc").text("");
		$("#landTypeId_cont").removeClass("noimg");
		$("#landTypeId_cont").removeClass("false_i");
		$("#landTypeId_cont").addClass("correct_i");
	}
}
function check_generalManagerName(generalManagerName){
	if(generalManagerName.value==""||generalManagerName.value=="总经理姓名"){
		$("#generalManagerName_correc").text("请输入总经理姓名");
		$("#generalManagerName_cont").removeClass("noimg");
		$("#generalManagerName_cont").addClass("false_i");
		return false;
	}else{
		$("#generalManagerName_correc").text("");
		$("#generalManagerName_cont").removeClass("noimg");
		$("#generalManagerName_cont").removeClass("false_i");
		$("#generalManagerName_cont").addClass("correct_i");
	}
}
function check_generalManagerTel(generalManagerTel){
	if(generalManagerTel.value==""||generalManagerTel.value=="总经理电话"){
		$("#generalManagerTel_correc").text("请输入总经理电话");
		$("#generalManagerTel_cont").removeClass("noimg");
		$("#generalManagerTel_cont").addClass("false_i");
		return false;
	}else{
		if(!chekphone(generalManagerTel.value)){
			$("#generalManagerTel_correc").text("请输入正确的手机号码");
			$("#generalManagerTel_cont").removeClass("noimg");
			$("#generalManagerTel_cont").addClass("false_i");
			return false;
		}else{
			$("#generalManagerTel_correc").text("");
			$("#generalManagerTel_cont").removeClass("noimg");
			$("#generalManagerTel_cont").removeClass("false_i");
			$("#generalManagerTel_cont").addClass("correct_i");
		}
	}
}
function chekphone(phone) {
	var pattern = /^[1][3-8]+\d{9}$/;
	if (pattern.test(phone)) {
		return true;
	} else {
		return false;
	}
}