<%@page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>
<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
	var selectRepo = null;
	$(function(){
		selectRepo = parent.selectRepo;
		var ids = selectRepo.ids;
		if(ids!=null&&ids.length>0){//如果经销商id不为空，则循环赋值
			for(var i = 0;i<ids.length;i++){
				$("[name='ids']").each(function(){
					if(ids[i]==this.value){
						$(this).prop("checked",true);
					}
				});
			}
		}
	});
	$(function(){
		$("[name='ids']").change(function(){
			if(this.checked){
				selectRepo.addId(this.value,$(this).attr("repoName"));
			}else{
				selectRepo.remove(this.value);
			}
			console.info(selectRepo);
		});
	});
	//执行查询操作
	function doQuery() {
		document.forms[0].submit();
	}

	//执行表单清空操作
	function doClear() {
		//清空角色名输入框
		getElement("supervisorName").value = "";
		getElement("staffNo").value = "";
	}
	
	function doSave(){
		
		window.parent.closeSelect();
	}
</script>
</head>

<body>

	<div class="pagebodyOuter">
		<div class="pagebodyInner">

			<div class="tagbarBody">

				<html:form action="/repository.do" styleId="repositoryForm" method="post"
					onsubmit="return false">
					<input type="hidden" name="method" value="selectRepositoryList">
					<table class="formTable">
						<tr>
							<td class="nameCol">姓名：</td>
							<td class="codeCol"><html:text property="repositoryQuery.supervisorName"
									styleId="supervisorName" /></td>
							<td class="nameCol">工号：</td>
							<td class="codeCol"><html:text property="repositoryQuery.staffNo"
									styleId="staffNo" /></td>
						</tr>
						<tr class="formTableFoot">
							<td colspan="6" align="center">
								<button class="formButton" onClick="doQuery()">查&nbsp;询</button>
								&nbsp;
								<button class="formButton" onClick="doClear()">重&nbsp;置</button>
							</td>
						</tr>
					</table>
					<br />
					<table class="listTalbe">
						<thead>
							<tr class="title">
								<td width="5%"><!-- <label for="checkAllValue"></label> <input
									type="checkbox" name="checkAllValue" id="checkAllValue"
									class="checkbox_title" onClick="checkAll('ids')" /> --></td>
								<td width="5%">序号</td>
								<td><thumbpage:order cname="姓名" filedName="name" /></td>
								<td><thumbpage:order cname="工号" filedName="staffNo" /></td>
							</tr>
						</thead>

						<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()"
							onClick="clickRow()">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="listTr_a">
									<td align="center"><input type="checkbox" id="ids"
										name="ids" value="<c:out value='${row.id}'/>"
										repoName="<c:out value='${row.name}' />" /></td>
									<td align="center"><c:out value="${index+1}" /></td>
									<td><c:out value="${row.name}" /></td>
									<td><c:out value="${row.staffNo}" /></td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<thumbpage:tools
						className="<%=ThumbPageConstants.CLASSNAME_TAGBAR.getCode()%>"
						tableName="selectRepository"
						action="/repository.do" />
					<table class="bottomTable">
						<tr>
							<td style="text-align: center;">
								<button class="formButton" onClick="doSave()">保&nbsp;存</button>
							</td>
						</tr>
					</table>
					<br />
					<div id="message" class="message" style="display: none"></div>
				</html:form>
			</div>
		</div>
	</div>
</body>
</html>
