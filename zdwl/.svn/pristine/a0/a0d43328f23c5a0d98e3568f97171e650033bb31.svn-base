<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="file.tld" prefix="file"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script src="/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	
	//初始化省份  param1：当前的省份ID（用于修改页面初始化选项）  param2：省级对象,例：$('#province')
	function initProvince(currentId,currSelect){
		var url = "../json/findRegionByParentId.do?callback=?&parentId=0";
		$.getJSON(url, function(result) {
			var data = result.data;
			setProvince(data,currentId,currSelect);
		});
	}
	//设置省份下拉框值
	function setProvince(province,currentId,currSelect){
		for(var i=0;i<province.length;i++){
			var option ;
			if(province[i].id == currentId){
				option = "<option selected='selected' value="+province[i].id+">" + province[i].name
				+ "</option>";
			}else{ 
				option = "<option value="+province[i].id+">" + province[i].name
				+ "</option>";
			 } 
			currSelect.append(option);
		}
	}
	//省或市的change事件，  param1：当前选中省或市的ID，用于查询下级地区列表，  param2：当前的省份ID（用于修改页面初始化选项）
	//param3：下级地区对象，param4：当前省或市的隐藏域对象，修改该对象值为当前选中的地区ID，为了解决往服务器传值问题
	function changeProvinceOrCity(currentId,currSelect,id,nextSelect){
		if(id==-1){
			document.forms[0].reset();
			return;
		}
		//修改省或市的隐藏域对象值为当前选中的地区ID
		$(currSelect).val(id);
		var url = "../json/findRegionByParentId.do?callback=?&parentId="+id;
		$.getJSON(url, function(result) {
			var data = result.data;
			setCityOrCounty(data,currentId,nextSelect);
		});
	}
	//设置市或县的值
	function setCityOrCounty(cityOrCounty,currentId,nextSelect){
		nextSelect.html("");
		var option = "<option value='-1' >请选择</option>";
		nextSelect.append(option);
		for(var i=0;i<cityOrCounty.length;i++){
			var option ;
			if(currentId==cityOrCounty[i].id){
				option = "<option selected='selected' value="+cityOrCounty[i].id+">" + cityOrCounty[i].name
				+ "</option>";
			}else{
				option = "<option value="+cityOrCounty[i].id+">" + cityOrCounty[i].name
				+ "</option>";
			}
			nextSelect.append(option);
		}
	} 
	//最后一级  区/县的change事件    param1：当前省或市的ID， param2：当前 区/县的隐藏域对象，修改该对象值为当前选中的地区ID，为了解决往服务器传值问题 ,例：$('#county')
	function changeLastSelect(id,lastSelect){
		$(lastSelect).val(id);
	}
</script>
</head>
<body>
</body>
</html>
