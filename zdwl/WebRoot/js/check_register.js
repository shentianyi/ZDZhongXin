var xmlhttp = false;
try {
	xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e) {
	try {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	} catch (e2) {
		xmlhttp = false;
	}
}
if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
	xmlhttp = new XMLHttpRequest();
}
function Ajax(systemUrl,data) {
	xmlhttp.open("GET", systemUrl + "/register/check_user.do?method=checkRegisterUser&userName=" + data, true);
	xmlhttp.send(null);
	xmlhttp.onreadystatechange = function() {
		if (4 == xmlhttp.readyState) {
			if (200 == xmlhttp.status) {
				var responseText = xmlhttp.responseText;
				if (responseText == 1) {
					ck_user("true");
					data="";
				} else {
					ck_user("false");
				}
			} else {
				alert("发生错误!");
			}
		}
	}
}
function chkUserName(systemUrl,obj) {
	if (checks(obj.value) == false) {
		showInfo("loginid_notice", msg_un_format);
		change_submit("true");
		$("#Login_cont").removeClass("noimg");
		$("#Login_cont").addClass("bidd_judge false_cont");
		obj.value="";
		return false;
	} else if (obj.value.length == 0) {
		showInfo("loginid_notice", msg_un_blank);
		change_submit("true");
		$("#Login_cont").removeClass("noimg");
		$("#Login_cont").addClass("bidd_judge false_cont");
		obj.value="";
		return false;
	} else if (obj.value.length < 6) {
		showInfo("loginid_notice", username_shorter_s);
		change_submit("true");
		$("#Login_cont").removeClass("noimg");
		$("#Login_cont").addClass("bidd_judge false_cont");
		obj.value="";
		return false;
	} else if (obj.value.length > 12) {
		showInfo("loginid_notice", username_shorter_m);
		change_submit("true");
		$("#Login_cont").removeClass("noimg");
		$("#Login_cont").addClass("bidd_judge false_cont");
		obj.value="";
		return false;
	} else {
		// 调用Ajax函数,向服务器端发送查询
		Ajax(systemUrl,obj.value);
	}
}
// --------------用户名检测---------------------//
function ck_user(result) {
	if (result == "true") {
		showInfo("loginid_notice", msg_un_registered);
		change_submit("true");// 禁用提交按钮
		$("#Login_cont").removeClass("noimg");
		$("#Login_cont").addClass("bidd_judge false_cont");
		return false;
	} else {
		showInfo("loginid_notice", info_right);
		change_submit("false");// 可用提交按钮
		$("#Login_cont").removeClass("noimg");
		$("#Login_cont").removeClass("false_cont");
		$("#Login_cont").addClass("bidd_judge correct_cont");
		return true;
	}
}

function checks(t) {
	szMsg = "[#%&'\",;:=!^@]";
	// alertStr="";
	for (i = 1; i < szMsg.length + 1; i++) {
		if (t.indexOf(szMsg.substring(i - 1, i)) > -1) {
			// alertStr="请勿包含非法字符如[#_%&'\",;:=!^]";
			return false;
		}
	}
	return true;
}

function check_userName(userName) {
	if (userName.value.length < 1) {
		showInfo("userName_notice", username_empty);
		change_submit("true");// 禁用提交按
		$("#userName_cont").removeClass("noimg");
		$("#userName_cont").addClass("bidd_judge false_cont");
		userName.value="";
		return false;
	} else {
		showInfo("userName_notice", info_right);
		change_submit("false");// 允许提交按钮
		$("#userName_cont").removeClass("noimg");
		$("#userName_cont").removeClass("false_cont");
		$("#userName_cont").addClass("bidd_judge correct_cont");
		return true;
	}
}

function check_password(password) {
	if (password.value.length < 6) {
		showInfo("password_notice", password_shorter_s);
		change_submit("true");// 禁用提交按
		$("#password_cont").removeClass("noimg");
		$("#password_cont").addClass("bidd_judge false_cont");
		password.value="";
		return false;
	} else if (password.value.length > 12) {
		showInfo("password_notice", password_shorter_m);
		change_submit("true");// 禁用提交按
		$("#password_cont").removeClass("noimg");
		$("#password_cont").addClass("bidd_judge false_cont");
		password.value="";
		return false;
	} else {
		showInfo("password_notice", info_right);
		change_submit("false");// 允许提交按钮
		$("#password_cont").removeClass("noimg");
		$("#password_cont").removeClass("false_cont");
		$("#password_cont").addClass("bidd_judge correct_cont");
		return true;
	}
}

function check_conform_password(conform_password) {
	password = document.getElementById('user.password').value;
	if (conform_password.value.length < 6) {
		showInfo("conform_password_notice", password_shorter_s);
		change_submit("true");// 禁用提交按
		$("#confirmPassword_cont").removeClass("noimg");
		$("#confirmPassword_cont").addClass("bidd_judge false_cont");
		conform_password.value="";
		return false;
	}
	if (conform_password.value.length > 12) {
		showInfo("conform_password_notice", password_shorter_m);
		change_submit("true");// 禁用提交按
		$("#confirmPassword_cont").removeClass("noimg");
		$("#confirmPassword_cont").addClass("bidd_judge false_cont");
		conform_password.value="";
		return false;
	}
	if (conform_password.value != password) {
		showInfo("conform_password_notice", confirm_password_invalid);
		conform_password.value="";
		change_submit("true");// 禁用提交按
		$("#confirmPassword_cont").removeClass("noimg");
		$("#confirmPassword_cont").addClass("bidd_judge false_cont");
		return false;
	} else {
		showInfo("conform_password_notice", info_right);
		change_submit("false");// 允许提交按钮
		$("#confirmPassword_cont").removeClass("noimg");
		$("#confirmPassword_cont").removeClass("false_cont");
		$("#confirmPassword_cont").addClass("bidd_judge correct_cont");
		return true;
	}
}
// -----------EMAIL检测--------------------------------//
function checkEmail(email) {
	if(email.value.length<1){
		showInfo("email_notice", msg_email_empty);
		change_submit("true");
		$("#email_cont").removeClass("noimg");
		$("#email_cont").addClass("bidd_judge false_cont");
		email.value="";
		return false;
	}
	if (chekemail(email.value) == false){
		showInfo("email_notice", msg_email_format);
		change_submit("true");
		$("#email_cont").removeClass("noimg");
		$("#email_cont").addClass("bidd_judge false_cont");
		email.value="";
		return false;
	} else {
		showInfo("email_notice", info_right);
		change_submit("false");
		$("#email_cont").removeClass("noimg");
		$("#email_cont").removeClass("false_cont");
		$("#email_cont").addClass("bidd_judge correct_cont");
		return true;
	}
}

function chekemail(temail) {
	var pattern = /^[\w]{1}[\w\.\-_]*@[\w]{1}[\w\-_\.]*\.[\w]{2,4}$/i;
	if (pattern.test(temail)) {
		return true;
	} else {
		return false;
	}
}

function checkMoblephone(moblephone){
	if(moblephone.value.length<1){
		showInfo("phone_notice", msg_phone_empty);
		change_submit("true");
		$("#moblephone_cont").removeClass("noimg");
		$("#moblephone_cont").addClass("bidd_judge false_cont");
		moblephone.value="";
		return false;
	}
	if (chekphone(moblephone.value) == false||moblephone.value.length!=11){
		showInfo("phone_notice", msg_phone_format);
		change_submit("true");
		$("#moblephone_cont").removeClass("noimg");
		$("#moblephone_cont").addClass("bidd_judge false_cont");
		moblephone.value="";
		return false;
	} else {
		showInfo("phone_notice", info_right);
		change_submit("false");
		$("#moblephone_cont").removeClass("noimg");
		$("#moblephone_cont").removeClass("false_cont");
		$("#moblephone_cont").addClass("bidd_judge correct_cont");
		return true;
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

// ------------ 按钮状态设置-----------------------------//
function change_submit(zt) {
	if (zt == "true") {
		return true;
	} else {
		return false;
	}
}
// ------公用程序------------------------------------//
function showInfo(target, Infos) {
	document.getElementById(target).innerHTML = Infos;
}
var username_empty = "<span style='COLOR:#ff0000'>用户名不能为空!</span>";
var username_shorter_s = "<span style='COLOR:#ff0000'>用户名长度不能少于 6 个字符。</span>";
var username_shorter_m = "<span style='COLOR:#ff0000'>用户名长度不能大于 12 个字符。</span>";
var password_empty = "<span style='COLOR:#ff0000'>登录密码不能为空。</span>";
var password_shorter_s = "<span style='COLOR:#ff0000'>登录密码不能少于 6 个字符。</span>";
var password_shorter_m = "<span style='COLOR:#ff0000'>登录密码不能大于 12 个字符。</span>";
var confirm_password_invalid = "<span style='COLOR:#ff0000'>两次输入密码不一致!</span>";
var agreement = "<span style='COLOR:#ff0000'>您没有接受协议</span>";
var msg_un_blank = "<span style='COLOR:#ff0000'>用户名不能为空!</span>";
var msg_un_format = "<span style='COLOR:#ff0000'>用户名含有非法字符!</span>";
var msg_un_registered = "<span style='COLOR:#ff0000'>用户名已经存在,请重新输入!</span>";
var msg_email_empty = "<span style='COLOR:#ff0000'>邮件地址不能为空!</span>";
var msg_email_format = "<span style='COLOR:#ff0000'>邮件地址不合法!</span>";
var msg_phone_empty = "<span style='COLOR:#ff0000'>手机号码不能为空!</span>";
var msg_phone_format = "<span style='COLOR:#ff0000'>手机号码不正确!</span>";
var info_right = "<span style='COLOR:#006600'> 填写正确!</span>";
