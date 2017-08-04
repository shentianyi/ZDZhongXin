<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
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
<script src="/js/jquery-1.8.3.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/validate/validate_main.js"></script>
<script src="/js/validate/validate_base.js"></script>
<script>
//页面初始化函数
function doLoad(){
    //显示提示信息
    showMessage("<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>");
    cleanDeliver();
    cleanReceiver();
    //交付人
     var delivererDealerID=document.getElementById("delivererDealerID").value;
     delivererDealerID.replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');
    if(delivererDealerID!="0"){
         var arr=delivererDealerID.split(",");
         var delivererDealers=new Array();
         for(var i=0;i<arr.length;i++){
             var delivererDealer=new Object();
             delivererDealer.id=arr[i];
             delivererDealers[delivererDealers.length]=delivererDealer;
         }
         changeDealer(delivererDealers,1); 
    }
     //接收人
     var receiverDealerID=document.getElementById("receiverDealerID").value;
     receiverDealerID.replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');
     if(receiverDealerID!="0"){
         var arr=receiverDealerID.split(",");
         var receiverDealers=new Array();
         for(var i=0;i<arr.length;i++){
             var receiverDealer=new Object();
             receiverDealer.id=arr[i];
             receiverDealers[receiverDealers.length]=receiverDealer;
         }
        changeDealer(receiverDealers,2); 
     }
    var deliverer=document.getElementById("handoverPlan.deliverer").value;
    changeDeliverer(deliverer);
    var receiver=document.getElementById("handoverPlan.receiver").value;
    changeReceiver(receiver);
}



//执行返回列表操作
function doReturn(){
    location = "<url:context/>/handoverPlan.do?method=HandoverPlanLedgerList";
}


function changeDealer(dealers,type) {
    if(dealers==null){
        return;
    }
    for(var i=0;i<dealers.length;i++){
        var url = "../json/getDealerByIdJsonAction.do?callback=?&id="+dealers[i].id;
        $.getJSON(url, function(result) {
            var data = result.data;
            console.info(data);
            setDealer(data[0],type);
        });
    }
}
function setDealer(dealer,type){
    if(type==1){
        $("#ddealer").val($("#ddealer").val()+"  "+dealer.dealerName);
        $("#dbrand").val($("#dbrand").val()+"  "+dealer.brand);
        $("#daddress").val($("#daddress").val()+"  "+dealer.address);
        var brand=dealer.bankName;
        var brands=brand.split("/");
        if(brands!=null){
            if(brands.length==1){
                $("#dheadBank").val($("#dheadBank").val()+"  "+brands[0]);
            }else if(brands.length==2){
                $("#dheadBank").val($("#dheadBank").val()+"  "+brands[0]);
                $("#dbranch").val( $("#dbranch").val()+"  "+brands[1]);
            }else if(brands.length==3){
                $("#dheadBank").val($("#dheadBank").val()+"  "+brands[0]);
                $("#dbranch").val( $("#dbranch").val()+"  "+brands[1]);
                $("#dsubbranch").val($("#dsubbranch").val()+"  "+brands[2]);
            }
        }
        $("#dprovince").val($("#dprovince").val()+"  "+dealer.province);
        $("#dcity").val($("#dcity").val()+"  "+dealer.city);
        $('#dequipmentProvide').val($('#dequipmentProvide').val()+"  "+dealer.equipmentProvide);
    }else if(type==2){
        $("#cdealer").val($("#cdealer").val()+"  "+dealer.dealerName);
        $("#cbrand").val($("#cbrand").val()+"  "+dealer.brand);
        $("#caddress").val($("#caddress").val()+"  "+dealer.address);
        var brand=dealer.bankName;
        var brands=brand.split("/");
        if(brands!=null){
            if(brands.length==1){
                $("#cheadBank").val($("#cheadBank").val()+"  "+brands[0]);
            }else if(brands.length==2){
                $("#cheadBank").val($("#cheadBank").val()+"  "+brands[0]);
                $("#cbranch").val( $("#cbranch").val()+"  "+brands[1]);
            }else if(brands.length==3){
                $("#cheadBank").val($("#cheadBank").val()+"  "+brands[0]);
                $("#cbranch").val( $("#cbranch").val()+"  "+brands[1]);
                $("#csubbranch").val($("#csubbranch").val()+"  "+brands[2]);
            }
        }
        $("#cprovince").val($("#cprovince").val()+"  "+dealer.province);
        $("#ccity").val($("#ccity").val()+"  "+dealer.city);
        $('#cequipmentProvide').val($('#cequipmentProvide').val()+"  "+dealer.equipmentProvide);
    }
}
function changeDeliverer(id) {
    if(id==-1){
        return;
    }
    var url = "../json/getSupervisorByRepositoryId.do?callback=?&id="+id;
    $.getJSON(url, function(result) {
        var data = result.data;
        console.info(data);
        setDeliverer(data[0]);
    });
}
function setDeliverer(supervisor){
    $("#idNumber").val(supervisor.idNumber);
    $("#gender").val(supervisor.gender);
    $("#staffNo").val(supervisor.staffNo);
    $("#entryDate").val(supervisor.entryTimeStr);
    $("#contactNumber").val(supervisor.selfTelephone);
    
}

function getDealerBySupervisor(id,type) {
    if(id==-1){
        return;
    }
    var url = "../json/getDealerByRepositoryId.do?callback=?&id="+id;
    $.getJSON(url, function(result) {
        var data = result.data;
        console.info(data);
        changeDealer(data,type);
    });
}

function changeReceiver(id) {
    if(id==-1){
        return;
    }
    var url = "../json/getSupervisorByRepositoryId.do?callback=?&id="+id;
    $.getJSON(url, function(result) {
        var data = result.data;
        console.info(data);
        setReceiver(data[0]);
    });
}
function setReceiver(supervisor){
    $("#ridNumber").val(supervisor.idNumber);
    $("#rgender").val(supervisor.gender);
    $("#rstaffNo").val(supervisor.staffNo);
    $("#rentryDate").val(supervisor.entryTimeStr);
    $("#rcontactNumber").val(supervisor.selfTelephone);
}
function cleanDeliver(){
    $("#idNumber").val("");
    $("#gender").val("");
    $("#staffNo").val("");
    $("#entryDate").val("");
    $("#contactNumber").val("");
    $("#ddealer").val("");
    $("#dbrand").val("");
    $("#daddress").val("");
    $("#dheadBank").val("");
    $("#dbranch").val("");
    $("#dsubbranch").val("");
    $("#dprovince").val("");
    $("#dcity").val("");
    $('#dequipmentProvide').val("");
}
function cleanReceiver(){
    $("#ridNumber").val("");
    $("#rgender").val("");
    $("#rstaffNo").val("");
    $("#rentryDate").val("");
    $("#rcontactNumber").val("");
    $("#cdealer").val("");
    $("#cbrand").val("");
    $("#caddress").val("");
    $("#cheadBank").val("");
    $("#cbranch").val("");
    $("#csubbranch").val("");
    $("#cprovince").val("");
    $("#ccity").val("");
    $('#cequipmentProvide').val("");
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
    
<html:form action="/handoverPlan" styleId="handoverPlanForm" method="post" onsubmit="return false" >
<input type="hidden" name="method" id="method" value="handoverPlanDetail"/>
<html:hidden property="handoverPlan.delivererDealerID" styleId="delivererDealerID" />
<html:hidden property="handoverPlan.receiverDealerID" styleId="receiverDealerID" />
<table class="formTable" style="width:100%;">
    <tr class="formTitle">
        <td colspan="4">轮岗计划详情</td>
    </tr>
    <tr>
        <td colspan="4"> 
            <table style="width:100%;">
                <tr>
                    <td class="nameCol">交付人：</td>
                    <td class="codeCol">
                         <form:select property="handoverPlan.deliverer" disabled="true"
                                styleId="handoverPlan.deliverer" onchange="changeDeliverer(this.value)">
                                <html:option value="-1">请选择</html:option>
                                <html:optionsCollection name="dealerSupervisors" label="label"
                                    value="value" />
                        </form:select>
                    </td> 
                    <td class="nameCol">性别：</td>
                    <td class="codeCol"><input type="text"  id="gender" disabled="disabled"/></td>
                    <td class="nameCol">身份证号：</td>
                    <td class="codeCol"><input type="text"  id="idNumber" disabled="disabled"/></td>
                </tr>
                 <tr>
                    <td class="nameCol">员工工号：</td>
                    <td class="codeCol"><input type="text"  id="staffNo" disabled="disabled"/></td>
                    <td class="nameCol">调入时间：</td>
                    <td class="codeCol">
                        <input type="text"  id="entryDate" disabled="disabled"/>
                    </td> 
                    <td class="nameCol">联系方式：</td>
                    <td class="codeCol"><input type="text"  id="contactNumber" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td class="nameCol">经销商：</td>
                    <td class="codeCol">
                        <html:text  property="handoverPlan.delivererDealer" styleId="ddealer" disabled="true"/>
                    </td>
                    <td class="nameCol">经销商品牌：</td>
                    <td class="codeCol"><input type="text"  id="dbrand" disabled="true" /></td>
                    <td class="nameCol">经销商地址：</td>
                    <td class="codeCol"><input type="text"  id="daddress" disabled="true" /></td>
                </tr>
                <tr>
                    <td class="nameCol">经销商所在省：</td>
                    <td class="codeCol"><input type="text"  id="dprovince"disabled="true" /></td>
                    <td class="nameCol">经销商所在市：</td>
                    <td class="codeCol"><input type="text" id="dcity"disabled="true" /></td>
                    <td class="nameCol">设备提供方：</td>
                    <td class="codeCol"><input type="text" id="dequipmentProvide"disabled="true" /></td>
                </tr>
                <tr>
                    <td class="nameCol">合作银行（总）：</td>
                    <td class="codeCol"><input type="text"  id="dheadBank"disabled="true" /></td>
                    <td class="nameCol">合作银行（分）：</td>
                    <td class="codeCol"><input type="text" id="dbranch"disabled="true" /></td>
                    <td class="nameCol">合作银行（支）：</td>
                    <td class="codeCol"><input type="text" id="dsubbranch"disabled="true" /></td>
                </tr>
                <%-- <tr>
                    <td class="nameCol">绑定店：</td>
                    <td class="codeCol"><html:text property="handoverPlan.bindMerchant" styleId="handoverPlan.bindMerchant"/></td>
                    <td class="nameCol">绑定行：</td>
                    <td class="codeCol"><html:text property="handoverPlan.bindBank" styleId="handoverPlan.bindBank"/></td>
                </tr>
                <tr>
                    <td class="nameCol">店方要求：</td>
                    <td class="codeCol" colspan="5"><html:text style="width:700px" property="handoverPlan.merchantDemand" styleId="handoverPlan.merchantDemand"/></td>
                </tr> --%>
                <tr>
                    <td class="nameCol">交接性质：</td>
                    <td class="nameCol"style="text-align: left;" colspan="3" >
                        <form:radios  property="handoverPlan.handoverNature" collection="handoverNatures" styleId="handoverPlan.handoverNature" disabled="true" />
                    </td>
                </tr>
                
                <tr>
                    <td class="nameCol">接收人：</td>
                    <td class="codeCol">
                         <form:select property="handoverPlan.receiver"  disabled="true"
                                styleId="handoverPlan.receiver" onchange="changeReceiver(this.value)">
                                <html:option value="-1">请选择</html:option>
                                <html:optionsCollection name="supervisors" label="label"
                                    value="value" />
                            </form:select>
                    </td>
                    <td class="nameCol">性别：</td>
                    <td class="codeCol"><input type="text"  id="rgender" disabled="disabled"/></td>
                    <td class="nameCol">身份证号：</td>
                    <td class="codeCol"><input type="text"  id="ridNumber" disabled="disabled"/></td>
                </tr>
                 <tr>
                    <td class="nameCol">员工工号：</td>
                    <td class="codeCol"><input type="text"  id="rstaffNo" disabled="disabled"/></td>
                    <td class="nameCol">调入时间：</td>
                    <td class="codeCol">
                        <input type="text"  id="rentryDate" disabled="disabled"/>
                    </td> 
                    <td class="nameCol">联系方式：</td>
                    <td class="codeCol"><input type="text"  id="rcontactNumber" disabled="disabled"/></td>
                </tr>
                <tr>
                    <td class="nameCol">经销商：</td>
                    <td class="codeCol">
                        <html:text  property="handoverPlan.receiverDealer" styleId="cdealer" disabled="true"/>
                    </td>
                    <td class="nameCol">经销商品牌：</td>
                    <td class="codeCol"><input type="text"  id="cbrand" disabled="true" /></td>
                    <td class="nameCol">经销商地址：</td>
                    <td class="codeCol"><input type="text"  id="caddress" disabled="true" /></td>
                </tr>
                <tr>
                    <td class="nameCol">经销商所在省：</td>
                    <td class="codeCol"><input type="text"  id="cprovince"disabled="true" /></td>
                    <td class="nameCol">经销商所在市：</td>
                    <td class="codeCol"><input type="text" id="ccity"disabled="true" /></td>
                    <td class="nameCol">设备提供方：</td>
                    <td class="codeCol"><input type="text" id="cequipmentProvide"disabled="true" /></td>
                </tr>
                <tr>
                    <td class="nameCol">合作银行（总）：</td>
                    <td class="codeCol"><input type="text"  id="cheadBank"disabled="true" /></td>
                    <td class="nameCol">合作银行（分）：</td>
                    <td class="codeCol"><input type="text" id="cbranch"disabled="true" /></td>
                    <td class="nameCol">合作银行（支）：</td>
                    <td class="codeCol"><input type="text" id="csubbranch"disabled="true" /></td>
                </tr>
                <%-- <tr>
                    <td class="nameCol">绑定店：</td>
                    <td class="codeCol"><html:text property="handoverPlan.bindMerchant" styleId="handoverPlan.bindMerchant"/></td>
                    <td class="nameCol">绑定行：</td>
                    <td class="codeCol"><html:text property="handoverPlan.bindBank" styleId="handoverPlan.bindBank"/></td>
                </tr>
                <tr>
                    <td class="nameCol">店方要求：</td>
                    <td class="codeCol" colspan="5"><html:text style="width:700px" property="handoverPlan.merchantDemand" styleId="handoverPlan.merchantDemand"/></td>
                </tr> --%>
                <tr>
                    <td class="nameCol">接收性质：</td>
                    <td class="nameCol"style="text-align: left;" colspan="3">
                        <form:radios  property="handoverPlan.receiveNature" disabled="true" collection="receiveNatures" styleId="handoverPlan.receiveNature"/>
                    </td>
                    <td class="nameCol">调入时间：</td>
                    <td class="codeCol">
                        <form:calendar property="handoverPlan.missionDate" disabled="true" styleId="handoverPlan.missionDate" pattern="<%=PatternConstants.TIMESTAMP.getCode()%>" readonly="true" />
                    </td>
                </tr>
                <tr>
                    <td class="nameCol">备注：</td>
                    <td class="codeCol"colspan="5" style="text-align: left;">
                        <html:text style="width:700px" property="handoverPlan.remark" disabled="true" styleId="handoverPlan.remark"/>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td  colspan="4">
                        <table style="width:100%">
                            <c:if test="${not empty approvals}">
                                <tr class="formTitle">
                                    <td colspan="4">部门意见</td>
                                </tr>
                                <logic:iterate name="approvals" id="row" indexId="index">
                                    <tr>
                                    <td class="nameCol">第<c:out value='${index+1 }'/>步</td>
                                    <td class="nameCol" style="text-align: left;">
                                        <c:out value="${row.roleName }"/>：
                                        <c:out value="${row.userName }"/>于<fmt:formatDate value="${row.remarkDate }" pattern="yyyy-MM-dd"/>
                                    </td>
                                    <td class="codeCol" colspan="2">
                                        <c:if test="${row.approvalResult==1}">同意&nbsp;</c:if>
                                        <c:if test="${row.approvalResult==2}">不同意&nbsp;</c:if>
                                        <c:out value="${row.remark }"/>
                                    </td>
                                </tr>
                                </logic:iterate>
                            </c:if>
                        </table>
                    </td>
                </tr>
                <tr>
    </tr>
            </table>
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
</body>
</html>
