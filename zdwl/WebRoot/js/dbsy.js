function dbsyNum(systemUrl, dbsy_num) {
	if (window.ActiveXObject) {
		// 创建IE内核的请求
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		// 创建火狐内核的请求
		xmlHttp = new XMLHttpRequest();
	} else {
		alert("咱不支持您的浏览器!");
	}
	method : "GET/POST";
	xmlHttp.open("GET", systemUrl + "/dbsy.do?method=dbsyListNum");
	xmlHttp.onreadystatechange = function dowork() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var text = xmlHttp.responseText;// 获得servlet中传递过来的数据放到text中
				var lab2 = document.getElementById(dbsy_num);// 取得本页面（jsp）中的叫“lab2”的label中
				lab2.innerHTML = text;// 将text中的内容放到lab2中
			}
		}
	}
	xmlHttp.send(null);
	if(window.parent.frames['chechewang_main'].document.getElementById("dbsylistds")!=undefined){
		window.parent.frames['chechewang_main'].location = systemUrl+"/dbsy.do?method=dbsyList";
	}
	
}