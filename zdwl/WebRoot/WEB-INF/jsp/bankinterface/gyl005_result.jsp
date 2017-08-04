<%@page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@page import="com.zd.csms.bank.contants.BankContants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="form.tld" prefix="form"%>
<%@ taglib uri="thumbpage.tld" prefix="thumbpage"%>
<%@ taglib uri="url.tld" prefix="url"%>
<%@ taglib uri="select.tld" prefix="select"%>
<%@ taglib uri="struts-html.tld" prefix="html"%>
<%@ taglib uri="struts-logic.tld" prefix="logic"%>
<%@ taglib uri="struts-bean.tld" prefix="bean"%>
<%@ taglib uri="fmt.tld" prefix="fmt"%>
<%@ taglib uri="c.tld" prefix="c"%>

<%@ page import="com.zd.csms.constants.Constants"%>
<%@ page import="com.zd.csms.constants.StateConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script type="text/javascript" src="/js/jquery.divscroll.js"></script>
<script src="/pagejs/bankdock.js"></script>
<script type="text/javascript">

	//页面初始化函数
	function doLoad(){
		var msg ="<%=request.getAttribute(Constants.OPERATE_MESSAGE.getCode())%>";
		if(msg && msg!=""&&msg!="null"){
			alert(msg);
		}
	}
	function doQuery(method){
		
		getElement("gyl005Synchronize").value="";
		if(method=='gyl005Synchronize'){
			getElement("gyl005Synchronize").value="notnull";
		}
		
		var currRole = $("#currRole").val();
		if(currRole == 10){
			var custNo = $("#custNo").combobox('getValue');
			if(!custNo){
				alert("请选择客户号");
				return false;
			}
		}
		document.forms[0].submit();
	}
	
	function doClear(){
		$("#custNo").combobox('setValue','');
		getElement("pledgeContractId").value="";
		getElement("protocolNo").value="";
		getElement("pledgeContractCode").value="";
	}
	
	function doSynchronize(){
		location = "<url:context/>/bank/interface.do?method=gyl005Synchronize";
	}
</script>
<style type="text/css">
	.title td{
		padding:0 15px 0 15px;
	}
	.textbox{
		margin-top:5px;
		margin-left:10%;
	}
</style>
</head>
<body  onLoad="doLoad()">
<body class="h-100 public" >

<div class="add_nav">
	<span class="add_nav_acolor">
            <a class="crumbs-link" style="color:#929291;" href="#">浙商银行对接</a>
            &gt;
            <a class="crumbs-link" style="color:#929291;" href="#">质押合同信息查询</a>
     </span>
</div>
<div class="public-main abs">
	<div class="ly-contai rel">
		<html:form action="/bank/interface.do" styleId="iForm" method="post" onsubmit="return false">
		<input name="method" id="method" type="hidden" value="gyl005" />
		<input type="hidden" name="gyl005Synchronize" id="gyl005Synchronize" value="<c:out value='${gyl005Synchronize }'/>"/>
		<input id="currRole" type="hidden" value="<c:out value='${currRole}'/>"/>
		<div class="public-main-input ly-col-2 hidden abs">
			<div class="ly-input-w">
				<div class="ly-row clearfix">
					<div class="ly-col fl">
                        <div class="label block fl hidden">质押合同ID：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="pledgeContractId" type="text" name="pledgeContractId" value="<c:out value='${pledgeContractId }'/>"/>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">质押合同编号：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="pledgeContractCode" type="text" name="pledgeContractCode" value="<c:out value='${pledgeContractCode }'/>"/>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">岀质人客户名称：</div>
	                    <div class="input block fl hidden">
	                    	<select id="custNo" name="custNo" style="min-width:150px;width:80%;" >
	                    		<option value="">请选择…</option>
	                    		<c:forEach items="${custList }" var="row">
	                    			<option <c:if test="${row.value==custNo}">selected='selected'</c:if> value="<c:out value='${row.value}'/>"><c:out value="${row.label }"/></option>
	                    		</c:forEach>
	                    	</select>
	                    </div>
                    </div>
                    
                    <div class="ly-col fl">
                        <div class="label block fl hidden">监管协议ID：</div>
	                    <div class="input block fl hidden">
	                    	<input class="ly-bor-none" id="protocolNo" type="text" name="protocolNo" value="<c:out value='${protocolNo }'/>"/>
	                    </div>
                    </div>
				</div>
			</div>
			<div class="ly-button-w">
                <a href="javascript:doQuery('gyl005');" class="button btn-query">查询</a>
                <a href="javascript:doClear();" class="button btn-reset">重置</a>
                <a href="javascript:doQuery('gyl005Synchronize');" class="button btn-reset">同步</a>
            </div>
		</div>
		<div class="public-main-table hidden abs">
			<div class="ly-cont">
				<table class="t-table" border="0" cellspacing="0" cellpadding="0">
					<thead class="t-thead">
						<tr class="t-tr">
							<th class="t-th">序号</th>
							<th class="t-th">质押合同代码</th>
							<th class="t-th">质押合同编号</th>
							<th class="t-th">岀质人客户号</th>
							<th class="t-th">出质人名称</th>
							<th class="t-th">出质人证件类型</th>
							<th class="t-th">出质人证件号码</th>
							<th class="t-th">监管协议号</th>
							<th class="t-th">生效日期</th>
							<th class="t-th">到期日期</th>
							<th class="t-th">质押率</th>
						</tr>
					</thead>
					<tbody class="t-tbody hidden">
						<c:if test="${not empty list }">
							<logic:iterate name="list" id="row" indexId="index">
								<tr class="t-tr">
									<td class="t-td"><c:out value="${index+1}" /></td>
									<td class="t-td"><c:out value='${row.pledgeContractId }' /></td>
									<td class="t-td"><c:out value='${row.pledgeContractCode }' /></td>
									<td class="t-td"><c:out value='${row.custNo }' /></td>
									<td class="t-td"><c:out value='${row.pledgeName }' /></td>
									<td class="t-td">
										<c:if test="${row.pledgeCertType=='1' }">组织机构代码证</c:if>
										<c:if test="${row.pledgeCertType=='2' }">营业执照</c:if> 
										<c:if test="${row.pledgeCertType=='3' }">其他组织代码（附属单位）</c:if>
										<c:if test="${row.pledgeCertType=='4' }">其他企业证件类型</c:if>
									</td>
									<td class="t-td"><c:out value='${row.pledgeCertCode }' /></td>
									<td class="t-td"><c:out value='${row.protocolCode }' /></td>
									<td class="t-td"><c:out value='${row.startDate }' /></td>
									<td class="t-td"><c:out value='${row.endDate }' /></td>
									<td class="t-td"><c:out value='${row.ration }' /></td>
								</tr>
							</logic:iterate>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="public-main-footer hidden abs">
			<div class="public-main-footer-pagin fr">
				<c:if test="${not empty list }">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" tableName="gyl005" action="/bank/interface.do?method=gyl005" />
				</c:if>
			</div>
			<div class="public-main-footer-pagin fr" id="message" style="display:none;color: red" align="center"></div>
		</div>
		</html:form>
	</div>
</div>
<script type="text/javascript">
    var ele = $('#ly-table-thead-w'),
        template;

    $('.public-main-table .ly-cont').perfectScrollbar({
        cursorwidth: 10,
        cursorborder: "none",
        cursorcolor: "#999",
        hidecursordelay: 0,
        zindex: 10001,
        horizrailenabled: true,
        callbackFn: function(){
            if (parseInt($('#ascrail2000').find('div').css('top')) > 0) {
                if (ele.length === 0) {
                    var tr = $('.public-main-table .t-tbody tr'),
                        width = 0;

                    // 生成thead区块
                    template = '<div id="ly-table-thead-w"><div class="ly-table-scroll">';
                    $('.public-main-table .t-thead th').each(function(key){
                        template += '<div class="block fl">'+ $(this).text() +'</div>';
                    });
                    template += '</div></div>';

                    ele = $(template).css({
                        position: 'absolute',
                        top: 0,
                        left: 0,
                        width: '100%',
                        height: '36px',
                        overflow: 'hidden'
                    });

                    // 复制操作
                    tr.eq(0).find('td').each(function(key){
                        var _width = $(this).width() + 1;

                        ele.find('.block').eq(key).css({
                            width: _width,
                            padding: '0 5px',
                            height: '36px',
                            lineHeight: '36px',
                            fontSize: '14px',
                            fontFamily: 'Microsoft Yahei',
                            textAlign: 'center',
                        });
                        width += _width + 10;
                    });
                    ele.find('.ly-table-scroll').css({
                        position: 'relative',
                        width: width,
                        height: '100%',
                        background: '#eee'
                    });

                    $('.public-main-table .ly-cont').append(ele);
                } else {
                    ele.show();
                };
            } else {
                ele.hide();
            };
        },
        _callbackFn: function(left){
            ele.find('.ly-table-scroll').css('left', -left);
        }
    });

</script>
</body>
</html>

