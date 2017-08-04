<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>
<%@ taglib uri="select.tld" prefix="select"%>


<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.csms.constants.Constants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中都汽车金融监管系统</title>

<link href="/css/css.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/common.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
function doLoad(){
    //合作模式
    if(document.getElementsByName("dealerQ.cooperationModel")[0].value == 1){
        document.getElementById("dealerQ_cooperationModel").value = "两方";
    }else if(document.getElementsByName("dealerQ.cooperationModel")[0].value == 2){
        document.getElementById("dealerQ_cooperationModel").value = "三方";
    }
    //单店/绑定
    if(document.getElementsByName("dealerQ.ddorbd")[0].value == 2){
        document.getElementById("dealerQ_ddorbd").value = "单店";
    }else if(document.getElementsByName("dealerQ.ddorbd")[0].value == 1){
        document.getElementById("dealerQ_ddorbd").value = "绑定";
    }
    //是否变更监管费
    if(document.getElementsByName("dealerQ.isChangeSuperviseMoney")[0].value == 1){
        document.getElementById("dealerQ_isChangeSuperviseMoney").value = "是";
    }else if(document.getElementsByName("dealerQ.isChangeSuperviseMoney")[0].value == 0){
        document.getElementById("dealerQ_isChangeSuperviseMoney").value = "否";
    }
    //合作状态
    if(document.getElementsByName("dealerQ.cooperationState")[0].value == 1){
        document.getElementById("dealerQ_cooperationState").value = "合作中";
    }else if(document.getElementsByName("dealerQ.cooperationState")[0].value == 2){
        document.getElementById("dealerQ_cooperationState").value = "未进店";
    }else if(document.getElementsByName("dealerQ.cooperationState")[0].value == 3){
        document.getElementById("dealerQ_cooperationState").value = "撤店";
        //document.getElementById("dealerQ_cooperationState").value = "停用";
    }
    document.getElementById("dealerQ.brands").style.display = "none";
    document.getElementById("dealerQ.provinces").style.display = "none";
    document.getElementById("dealerQ.citys").style.display = "none";
    
}
function doReturn(){
    history.go(-1);
}
function choiseBrand(){
   document.getElementById("dealerQ.brand").style.display = "none";
   document.getElementById("dealerQ.brands").style.display = "block";
}
function choiseProvince(){
    document.getElementById("dealerQ.province").style.display = "none";
   document.getElementById("dealerQ.provinces").style.display = "block";
}
function searchCity(){
   $.ajax({
       type : "POST",
       url : "/ledger/dealer.do?method=searchCity",
       dataType : "json",
       data : "dealerQ.province="+ document.getElementById("dealerQ.provinces").options[document.getElementById("dealerQ.provinces").selectedIndex].value,
       success : function(json) {
            console.log(json);
            document.getElementById("dealerQ.city").style.display = "none";
            document.getElementById("dealerQ.citys").style.display = "block";
            document.getElementById("dealerQ.citys").options.length=0;///清除所有options
            document.getElementById("dealerQ.citys").options.add(new Option("请选择",-1,true,false));
            for ( var i = 0; i < json[0].length; i++) {
                document.getElementById("dealerQ.citys").options.add(new Option(json[0][i].label,json[0][i].value));
            } 
       }
           
   }); 
}
//执行保存操作
function doSave(){
   //提交表单
   document.forms[0].submit();
}
</script>
<style type="text/css">
.red{color: red;}
.tips{color: red;font-size: 14px;}
.codeCol select{
    height: 32px;
    line-height: 32px;
    width: 225px;
    border: solid 1px #eee;
}
</style>
</head>
<body onLoad="doLoad()">

    <div class="title">经销商名录表（业务） -- 修改<br/>
        <span class="tips">*为可编辑项</span>
    </div>
    <hr size="1">
    <br />

    <div class="pagebodyOuter">
        <div class="pagebodyInner">
            <html:form action="/ledger/dealer" styleId="dealerForm" method="post" onsubmit="return false">
                <input type="hidden" name="method" id="method" value="UpdateDealer">
                <html:hidden property="dealerQ.id" styleId="dealerQ.id" />
                <html:hidden property="dealerQ.bankManagerId" styleId="dealerQ.bankManagerId"/>
                <html:hidden property="dealerQ.bankId" styleId="dealerQ.bankId" />
                <table class="formTable">
                    <tr>
                        <td class="nameCol">经销商全称：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.dealerName" styleId="dealerQ.dealerName" disabled="true"></html:text>
                        </td>
                        
                        <td class="nameCol">金融机构：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.bankName" styleId="dealerQ.bankName" disabled="true"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol"><span class="red">* </span>经销商所在省：</td>
                        <td class="codeCol">
                            <input type="text" value='<c:out value="${dealer.province }" />' id="dealerQ.province" onfocus="choiseProvince();" />
                            <form:select property="dealerQ.province" styleId="dealerQ.provinces" onchange="searchCity();">
                                <html:optionsCollection name="provinceList" label="label" value="value"/>
                            </form:select>
                        </td>
                        
                        <td class="nameCol"><span class="red">* </span>经销商所在市：</td>
                        <td class="codeCol">
                            <input type="text" value='<c:out value="${dealer.city }" />' id="dealerQ.city" />
                            <form:select property="dealerQ.city" styleId="dealerQ.citys">
                                <html:optionsCollection name="cityList" label="label" value="value"/>
                            </form:select>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">进驻时间：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.inDate" styleId="dealerQ.inDate" disabled="true"></html:text>
                        </td>
                        
                        <td class="nameCol"><span class="red">* </span>品牌：</td>
                        <td class="codeCol">
                            <input type="text" value='<c:out value="${dealer.brand }" />'  id="dealerQ.brand" onfocus="choiseBrand();" />
                            <form:select property="dealerQ.brand" styleId="dealerQ.brands">
		                        <html:optionsCollection name="brandList" label="label" value="value"/>
		                    </form:select>
                        </td>
                    </tr>
                    
                     <tr>
                        <td class="nameCol"><span class="red">* </span>经销商性质：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.dealerNature" styleId="dealerQ.dealerNature"></html:text>
                        </td>
                        
                        <td class="nameCol"><span class="red">* </span>经销商具体地址：</td>
                        <td class="codeCol" >
                            <html:text property="dealerQ.address" styleId="dealerQ.address"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol"><span class="red">* </span>经销商联系人：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.contact" styleId="dealerQ.contact"></html:text>
                        </td>
                        <td class="nameCol"><span class="red">* </span>电话：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.contactPhone" styleId="dealerQ.contactPhone"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">监管员：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.superviseName" styleId="dealerQ.superviseName" disabled="true"></html:text>
                        </td>
                        <td class="nameCol">电话：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.supervisePhone" styleId="dealerQ.supervisePhone" disabled="true"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol"><span class="red">* </span>金融机构联系人：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.bankContact" styleId="dealerQ.bankContact"></html:text>
                        </td>
                        <td class="nameCol"><span class="red">* </span>电话：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.bankPhone" styleId="dealerQ.bankPhone"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        
                        <%-- <td class="nameCol"><span class="red">* </span>设备提供方：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.equipmentProvide" styleId="dealerQ.equipmentProvide"></html:text>
                        </td> --%>
                        <td class="nameCol">协议监管模式：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.supervisionMode" styleId="dealerQ.supervisionMode" disabled="true"></html:text>
                        </td>
                        <td class="nameCol">汇票下发方式：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.draft_way" styleId="dealerQ.draft_way" disabled="true"></html:text>
                        </td>
                    </tr>
                    <tr>
                        <td class="nameCol">合作模式：</td>
                        <td class="codeCol">
                            <html:hidden property="dealerQ.cooperationModel" styleId="dealerQ.cooperationModel"/>
                            <input type="text" id="dealerQ_cooperationModel" name="dealerQ_cooperationModel" disabled="true"/>
                        </td>
                        <td class="nameCol">单店/绑定：</td>
                        <td class="codeCol">
                            <html:hidden property="dealerQ.ddorbd" styleId="dealerQ.ddorbd"/>
                            <input type="text" id="dealerQ_ddorbd" name="dealerQ_ddorbd" disabled="true"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">绑定信息：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.bindInfo" styleId="dealerQ.bindInfo" disabled="true"></html:text>
                        </td>
                        <td class="nameCol">监管费标准/年：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.superviseMoney" styleId="dealerQ.superviseMoney" disabled="true"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">付费方式：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.payMode" styleId="dealerQ.payMode" disabled="true"></html:text>
                        </td>
                        <td class="nameCol">是否变更监管费：</td>
                        <td class="codeCol">
                            <html:hidden property="dealerQ.isChangeSuperviseMoney" styleId="dealerQ.isChangeSuperviseMoney"/>
                            <input type="text" id="dealerQ_isChangeSuperviseMoney" name="dealerQ_isChangeSuperviseMoney" disabled="true"/>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">变更监管费时间：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.changeSuperviseMoneyDate" styleId="dealerQ.changeSuperviseMoneyDate" disabled="true"></html:text>
                        </td>
                        <td class="nameCol">变更前监管费标准：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.changeBeforeInfo" styleId="dealerQ.changeBeforeInfo" disabled="true"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">交接公司：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.handoverCompany" styleId="dealerQ.handoverCompany" disabled="true"></html:text>
                        </td>
                        <td class="nameCol">交接时间：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.handoverDate" styleId="dealerQ.handoverDate" disabled="true"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">合作状态：</td>
                        <td class="codeCol">
                            <html:hidden property="dealerQ.cooperationState" styleId="dealerQ.cooperationState"/>
                            <input type="text" id="dealerQ_cooperationState" name="dealerQ_cooperationState" disabled="true"/>
                        </td>
                        <td class="nameCol">撤店时间：</td>
                        <td class="codeCol"><c:out value="${dealer.exitDate }"></c:out>
                            <html:text property="dealerQ.exitDate" styleId="dealerQ.exitDate" disabled="true"></html:text>
                        </td>
                    </tr>
                    
                    <!-- 需求167 将修改里面的授信额度、设备提供方移动到业务参数设置里面 -->
                    <%-- <tr>
                        <td class="nameCol"><span class="red">* </span>授信额度：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.credit" styleId="dealerQ.credit" ></html:text>
                        </td>
                        <td class="nameCol">汇票下发方式：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.draft_way" styleId="dealerQ.draft_way" disabled="true"></html:text>
                        </td>
                    </tr> --%>
                    
                    <tr>
                        <td class="nameCol">押车方式：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.guard_way" styleId="dealerQ.guard_way" disabled="true"></html:text>
                        </td>
                        <td class="nameCol">合格证送达方式：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.certificate_delivery" styleId="dealerQ.certificate_delivery" disabled="true"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">实际监管模式：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.actual_supervision" styleId="dealerQ.actual_supervision" disabled="true"></html:text>
                        </td>
                        <td class="nameCol">钥匙监管：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.key_supervise" styleId="dealerQ.key_supervise" disabled="true"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">二网：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.website" styleId="dealerQ.website" disabled="true"></html:text>
                        </td>
                        <td class="nameCol">二库：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.old_car" styleId="dealerQ.old_car" disabled="true"></html:text>
                        </td>
                    </tr>
                    
                    <tr>
                        <td class="nameCol">监管物移动百分比：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.ydbl" styleId="dealerQ.ydbl" disabled="true"></html:text>
                        </td>
                        <td class="nameCol">特殊操作：</td>
                        <td class="codeCol">
                            <html:text property="dealerQ.special_oper" styleId="dealerQ.special_oper" disabled="true"></html:text>
                        </td>
                    </tr>
                    
                    <tr class="formTableFoot">
                        <td colspan="4" align="center">
                            <button class="formButton" onClick="doSave()">提&nbsp;交</button>
                            <button class="formButton" onClick="doReturn()">返&nbsp;回</button>
                        </td>
                    </tr>
                </table>
                </html:form>
                <br />
                <div class="message" id="message" style="display: none"></div>

        </div>
    </div>
</body>
</html>
