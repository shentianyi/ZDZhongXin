<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<style type="text/css">
	img{border:none;}
</style>
<style>   
 .overDiv{
	background-color: #000;
	background-repeat:repeat;
	width: 100%;
	height: 100%;
	left:0;
	top:0;/*FF IE7*/
	filter:alpha(opacity=40);/*IE*/
	opacity:0.4;/*FF*/
	z-index:1;
	position:fixed!important;/*FF IE7*/
	position:absolute;/*IE6*/
	_top: expression(eval(document.compatMode &&
	document.compatMode=='CSS1Compat') ?
	documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
	document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/
}
.dlDiv{
	background-image:url(dlbj.jpg);
	background-repeat:repeat-x;
	border:2px solid #ffffff;
	z-index:2;
	left:38%;/*FF IE7*/
	top:10%;/*FF IE7*/
	margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 */
	margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/
	margin-top:0px;
	position:fixed!important;/*FF IE7*/
	position:absolute;/*IE6*/
	_top:expression(eval(document.compatMode &&
	document.compatMode=='CSS1Compat') ?
	documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
	document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/
}
</style>
<script type="text/javascript">
//页面初始化函数
function doLoad(){
	//显示提示信息
	showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
}

//执行保存操作
function doSave(){
	//对表单内容进行验证，包括对输入类型等限制方式
	if(validateMain("supervisoryForm")){
		//为时间类型输入项补齐时间戳
		setTimeSuffix();
		//提交表单
		document.forms[0].submit();
	}
}

//执行返回列表操作
function doReturn(){
	location = "<url:context/>/supervisory.do?method=supervisoryPageList";
}

//执行表单重置操作
function doReset(){
	document.forms[0].reset();
}
function setImagePreview(fileId) {
	var docObj = document.getElementById(fileId);
	var imgObjPreview = document.getElementById("preview");
	if(docObj.files && docObj.files[0]){
		//火狐下，直接设img属性 
		imgObjPreview.style.display = 'block';
		imgObjPreview.style.width = '300px';
		imgObjPreview.style.height = '120px';
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
function showDiv(id){
	document.getElementById("pd_"+id).style.display = "block" ;
	document.getElementById("overDiv_"+id).style.display = "block" ;
}

function closeDiv(id){
	document.getElementById("overDiv_"+id).style.display = "none" ;
	document.getElementById("pd_"+id).style.display = "none" ;
}
</script>
<style type="text/css">
#f_file{
 	left: 0;
    opacity: 0;
    position: absolute;
    top: -100;
    z-index: 2;
    width: 100%;
    height: 100%;
}
</style>
</head>
<body  onLoad="doLoad()">

<br/>

<div class="pagebodyOuter">
	<div class="pagebodyInner">
	
<html:form action="/supervisory" styleId="supervisoryForm" method="post" onsubmit="return false">
<input type="hidden" name="method" id="method" value="supervisoryDetailEntity"/>

<table class="formTable">
	<tr class="formTitle">
		<td colspan="4">监管员详情</td>
	</tr>
	<tr>
		<td colspan="4" class="message" style="text-align: center;">请提供您的真实信息，我们将对您的信息进行全面核实，由于信息不实所带来的一切后果将由应试人员承担。（注：<font color="#FF0000">*</font>为必填项）：</td>
	</tr>
	<tr>
		<td class="nameCol">应聘岗位：</td>
		<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.job" styleId="supervisor.superVisorBaseInfo.job"/></td>
		<td class="nameCol">入职时间：</td>
		<td class="codeCol">
			<form:calendar disabled="true" property="supervisor.superVisorBaseInfo.entryTime" styleId="supervisor.superVisorBaseInfo.entryTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr>
		<td colspan="4"> 
			<table>
				<tr>
					<td class="nameCol" colspan="7" style="text-align: left;">一、基本资料</td>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>姓名</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.name" styleId="supervisor.superVisorBaseInfo.name"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>出生日期</td>
					<td class="codeCol">
						<form:calendar disabled="true" property="supervisor.superVisorBaseInfo.birthday" styleId="supervisor.superVisorBaseInfo.birthday" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
					</td>
					<td class="nameCol"><font color="#FF0000">*</font>性别</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.gender" styleId="supervisor.superVisorBaseInfo.gender"/></td>
					<td rowspan="6">
						<%-- <a href="javascript:showDiv('<c:out value='${pic}' />');"> --%>
							<%-- <form:img value="${file}" height="80" width="120"></form:img> --%>
							<img alt="照片" src="/showImg.do?method=showImg&filePath=<c:out value='${file}'/>" height="180" width="135"/>
						<!-- </a> -->
						<!-- 上传头像 -->
					</td>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>身份证号</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.idNumber" styleId="supervisor.superVisorBaseInfo.idNumber"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>民族</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.nation" styleId="supervisor.superVisorBaseInfo.nation"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>学历</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.educationBackground" styleId="supervisor.superVisorBaseInfo.educationBackground"/></td>
				</tr>
				<tr>
					<td class="nameCol">籍贯</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.nativePlace" styleId="supervisor.superVisorBaseInfo.nativePlace"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>政治面貌</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.politicsStatus" styleId="supervisor.superVisorBaseInfo.politicsStatus"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>户口性质</td>
					<td class="nameCol"style="text-align: left;" colspan="4" >
						<form:radios disabled="true"  property="supervisor.superVisorBaseInfo.registeredStatus" collection="registeredStatus" styleId="supervisor.superVisorBaseInfo.registeredStatus"/>
					</td>
					<%-- <td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.registeredStatus" styleId="supervisor.superVisorBaseInfo.registeredStatus"/></td> --%>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>本人联系电话</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.selfTelephone" styleId="supervisor.superVisorBaseInfo.selfTelephone"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>家庭电话</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.homeTelephone" styleId="supervisor.superVisorBaseInfo.homeTelephone"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>婚育状况</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.fertilityState" styleId="supervisor.superVisorBaseInfo.fertilityState"/></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table>
				<tr>
					<td class="nameCol">户口所在地</td>
					<td class="codeCol" colspan="6" style="text-align: left;">
						<html:text style="width:700px" disabled="true" property="supervisor.superVisorBaseInfo.registeredAddress" styleId="supervisor.superVisorBaseInfo.registeredAddress"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol" style="width: 300px;"><font color="#FF0000">*</font>现居住地址</td>
					<td class="codeCol" colspan="6" style="text-align: left;">
						<html:text style="width:700px" disabled="true" property="supervisor.superVisorBaseInfo.liveAddress" styleId="supervisor.superVisorBaseInfo.liveAddress"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol"><font color="#FF0000">*</font>紧急状况联系人</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.emergencyContactor" styleId="supervisor.superVisorBaseInfo.emergencyContactor"/></td>
					<td class="nameCol"><font color="#FF0000">*</font>联系电话</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.emergencyContactNumber" styleId="supervisor.superVisorBaseInfo.emergencyContactNumber"/></td>
					<td class="nameCol" style="width: 300px;"><font color="#FF0000">*</font>与紧急联系人的关系</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.emergencyContactRelationship" styleId="supervisor.superVisorBaseInfo.emergencyContactRelationship"/></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table >
				<tr>
					<td class="nameCol" colspan="6" style="text-align: left;">二、<font color="#FF0000">*</font>教育状况（从高到低写）</td>
				</tr>
				<tr>
					<td colspan="6">
						<div class="dvScroll">
							<table  class="listTalbe" cellpadding="0" cellspacing="0">
								<thead>
									<tr class="title">
								      <td>序号</td>
								      <td><thumbpage:order cname="起" filedName="educationStartTime"/></td>
								      <td><thumbpage:order cname="止" filedName="educationEndTime"/></td>
								      <td><thumbpage:order cname="学校名称" filedName="schoolName"/></td>
								      <td><thumbpage:order cname="主修专业" filedName="major"/></td>
								      <td><thumbpage:order cname="获得证书" filedName="certificate"/></td>
								      <td><thumbpage:order cname="学位" filedName="degree"/></td>
								    </tr>
								</thead>
								<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
									<logic:iterate name="educationList" id="row" indexId="index">
										<tr class="listTr_a">
											<td align="center">&nbsp;<c:out value="${index+1}"/></td>
											<td>&nbsp;<select:timestamp timestamp="${row.educationStartTime}" idtype="date"/></td>
											<td>&nbsp;<select:timestamp timestamp="${row.educationEndTime}" idtype="date"/></td>
											<td>&nbsp;<c:out value="${row.schoolName}" /></td>
											<td>&nbsp;<c:out value="${row.major}" /></td>
											<td>&nbsp;<c:out value="${row.certificate}" /></td>
											<td>&nbsp;<c:out value="${row.degree}" /></td>
										</tr>
									</logic:iterate>
								</tbody>  
							</table>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table >
				<tr>
					<td class="nameCol" colspan="8" style="text-align: left;">三、<font color="#FF0000">*</font>工作经历</td>
				</tr>
				<tr>
					<td>
						<div class="dvScroll">
							<table  class="listTalbe" cellpadding="0" cellspacing="0">
								<thead>
									<tr class="title">
								      <td>序号</td>
								      <td><thumbpage:order cname="起" filedName="workStartTime"/></td>
								      <td><thumbpage:order cname="止" filedName="workEndTime"/></td>
								      <td><thumbpage:order cname="服务机构" filedName="serviceOrganization"/></td>
								      <td><thumbpage:order cname="职位" filedName="position"/></td>
								      <td><thumbpage:order cname="薪资" filedName="compensation"/></td>
								      <td><thumbpage:order cname="离职原因" filedName="leaveReason"/></td>
								      <td><thumbpage:order cname="证明人" filedName="certifier"/></td>
								      <td><thumbpage:order cname="证明人联系方式" filedName="contactNumber"/></td>
								    </tr>
								</thead>
								<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
									<logic:iterate name="workExperienceList" id="row" indexId="index">
										<tr class="listTr_a">
											<td align="center">&nbsp;<c:out value="${index+1}"/></td>
											<td>&nbsp;<select:timestamp timestamp="${row.workStartTime}" idtype="date"/></td>
											<td>&nbsp;<select:timestamp timestamp="${row.workEndTime}}" idtype="date"/></td>
											<td>&nbsp;<c:out value="${row.serviceOrganization}" /></td>
											<td>&nbsp;<c:out value="${row.position}" /></td>
											<td>&nbsp;<c:out value="${row.compensation}" /></td>
											<td>&nbsp;<c:out value="${row.leaveReason}" /></td>
											<td>&nbsp;<c:out value="${row.certifier}" /></td>
											<td>&nbsp;<c:out value="${row.contactNumber}" /></td>
										</tr>
									</logic:iterate>
								</tbody>  
							</table>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table >
				<tr>
					<td class="nameCol" colspan="5" style="text-align: left;">四、<font color="#FF0000">*</font>家庭状况</td>
				</tr>
				<tr>
					<td>
						<div class="dvScroll">
							<table  class="listTalbe" cellpadding="0" cellspacing="0">
								<thead>
									<tr class="title">
								      <td>序号</td>
								      <td><thumbpage:order cname="与该家庭成员关系" filedName="relationship"/></td>
								      <td><thumbpage:order cname="姓名" filedName="name"/></td>
								      <td><thumbpage:order cname="职业" filedName="profession"/></td>
								      <td><thumbpage:order cname="单位" filedName="organization"/></td>
								      <td><thumbpage:order cname="联系方式" filedName="telephone"/></td>
								    </tr>
								</thead>
								<tbody onMouseOver="mouseOver()" onMouseOut="mouseOut()" onClick="clickRow()">
									<logic:iterate name="familyList" id="row" indexId="index">
										<tr class="listTr_a">
											<td align="center">&nbsp;<c:out value="${index+1}"/></td>
											<td>&nbsp;<c:out value="${row.relationship}" /></td>
											<td>&nbsp;<c:out value="${row.name}" /></td>
											<td>&nbsp;<c:out value="${row.profession}" /></td>
											<td>&nbsp;<c:out value="${row.organization}" /></td>
											<td>&nbsp;<c:out value="${row.telephone}" /></td>
										</tr>
									</logic:iterate>
								</tbody>  
							</table>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr> 
	<tr>
		<td colspan="4">
			<table >
				<tr>
					<td class="nameCol" colspan="7" style="text-align: left;">五、<font color="#FF0000">*</font>招聘渠道</td>
				</tr>
				<tr>
					<%-- <td class="nameCol" style="width: 200px" >是否内部人员推荐</td>
					<td class="codeCol" ><html:text disabled="true" property="supervisor.supervisoryRecommend.isInsideRecommend" styleId="supervisor.supervisoryRecommend.isInsideRecommend"/></td> --%>
					<td class="nameCol" >招聘渠道</td>
					<td class="nameCol"style="text-align: left;width: 500px" colspan="6">
						<form:checkboxs   disabled="true" property="supervisor.supervisoryRecommend.otherChannel" collection="RecommendChannelOptions" styleId="supervisor.supervisoryRecommend.otherChannel"/>
					</td>
				</tr>
				 <tr>
				 	<td class="nameCol">推荐人姓名</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.supervisoryRecommend.recommenderName" styleId="supervisor.supervisoryRecommend.recommenderName"/></td>
					<td class="nameCol">推荐人职位</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.supervisoryRecommend.recommenderPosition" styleId="supervisor.supervisoryRecommend.recommenderPosition"/></td>
					<td class="nameCol" rowspan="2">推荐人所在部门或4S店名称</td>
					<td class="codeCol" rowspan="2" colspan="2" style="width:300px;height: 50px;"  >
						<html:text style="width:300px;height: 50px;" disabled="true" property="supervisor.supervisoryRecommend.recommenderDepartment" styleId="supervisor.supervisoryRecommend.recommenderDepartment"/>
					</td>
				</tr>
				<tr>
					<td class="nameCol">推荐人联系方式</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.supervisoryRecommend.recommenderPhone" styleId="supervisor.supervisoryRecommend.recommenderPhone"/></td>
					<td class="nameCol">与推荐人关系</td>
					<td class="codeCol"><html:text disabled="true" property="supervisor.supervisoryRecommend.recommenderRelationship" styleId="supervisor.supervisoryRecommend.recommenderRelationship"/></td>
					
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4" class="message" style="text-align: center;">
			本人声明：以上情况均为如实、正确填写，如有任何虚构，愿接受无偿之解雇；本人确认已阅读过公司发布的制度，了解了所有内容，并且公司为我解答了相关疑问。我将遵守公司的管理制度及规定。						
		</td>
	</tr>
	<tr>
		<td  class="nameCol" >本人对以上内容表示同意并签字确认：</td>
		<td class="codeCol"><html:text disabled="true" property="supervisor.superVisorBaseInfo.sign" styleId="supervisor.superVisorBaseInfo.sign"/></td>
		<td class="nameCol">日期：</td>
		<td class="codeCol">
			<form:calendar disabled="true" property="supervisor.superVisorBaseInfo.currentTime" styleId="supervisor.superVisorBaseInfo.currentTime" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
		</td>
	</tr>
	<tr class="formTableFoot">
		<td colspan="4" align="center">
			<button class="formButton" onClick="doReturn()">返&nbsp;回</button>
		</td>
	</tr>
</table>
<br/>
<div class="message" id="message" style="display:none"></div>
</html:form>
	
	</div>
</div>
<div id="pd_<c:out value='${pic}'/>" style="display: none" class="dlDiv"> 
 	<form:img value="${file}" height="960px" width="1280px"/>
</div> 
<div id="overDiv_<c:out value='${pic}'/>" style="display:none;" class="overDiv" onclick="closeDiv('<c:out value='${pic}'/>')"></div>
</body>
</html>
