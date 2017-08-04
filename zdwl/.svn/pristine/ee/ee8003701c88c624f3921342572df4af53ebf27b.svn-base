<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	$(function() {
		loadSelect(-1, $("#one"));
		
		$("#one").change(function() {
			$("#two option:gt(0)").remove();
			$("#three option:gt(0)").remove();
			var id = this.value;
			if(id){
				loadSelect(id, $("#two"));
			}
		});
		$("#two").change(function() {
			var id = this.value;
			if(id){
				$("#three option:gt(0)").remove();
				loadSelect(id, $("#three"));
			}
				
		});
	});

	function loadSelect(id, nextSelect) {
		var url = "../json/getBankChildById.do?method=findChildListById&callback=?&bankQuery.id="
				+ id;
		$.getJSON(url, function(result) {
			var data = result.data;
			$.each(data, function(i, item) {
				var option = "<option value="+item.id+">" + item.bankName
						+ "</option>";
				nextSelect.append(option);
			});
		});
	}
</script>
</head>
<body>
	<select id="one">
		<option>请选择...</option>
	</select>
	<select id="two">
		<option>请选择...</option>
	</select>
	<select id="three">
		<option>请选择...</option>
	</select>
</body>
</html>
