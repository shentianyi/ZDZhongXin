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
<%@ page import="com.zd.csms.rbac.constants.RbacConstants"%>
<%@ page import="com.zd.tools.validate.constants.PatternConstants"%>
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>

<html class="h-100">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>中都汽车金融监管系统</title>

<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link type="text/css" rel="stylesheet" href="/css/public.css" />
<link href="/easyui/themes/material/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<script src="/easyui/jquery.min.js"></script>
<script src="/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
.title td {
	padding: 0 15px 0 15px;
}
.oneBankId, .twoBankId, .threeBankId, .provinceId, .cityId {
	margin: 3% 10% ;
	width: 50% ;
    height: 64%;
}
.public-main-input .ly-col .input {
    width: 61%;
}
.public-main-input .ly-col .label {
    width: 39%;
}
</style>
<script src="/js/common.js"></script>
<script src="/js/calendar.js"></script>
<script src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="/js/thumbpage/thumbpage.js"></script>
<script src="/js/video/initinfo.js"></script>
<script type="text/javascript" src="js/jquery.divscroll.js"></script>
<script>
	//页面初始化函数
	function doLoad() {
		initBank();
		var onehiId = $("#onehiId").val();
		var twohiId = $("#twohiId").val();
		var threehiId = $("#threehiId").val();
		if (onehiId && onehiId != "" && onehiId > 0) {
			$("#one").val(onehiId);
		}
		if (twohiId && twohiId != "" && twohiId > 0) {
			loadSelect(onehiId, $("#two"));
			$("#two").val(twohiId);
		}
		if (threehiId && threehiId !="" && threehiId > 0) {
			loadSelect(twohiId, $("#three"));
			$("#three").val(threehiId);
		}
		$('#beginDate').datebox({    
			editable:false   
		});
		$('#endDate').datebox({    
			editable:false   
		});
	}



	 function doSubmit(){
			var isReadIds = $("[name='isReadIds']:checked");
			var idsRead = [];
			var ids = [];
			isReadIds.each(function(){
				ids.push(this.value);
				if(this.getAttribute("disabled") == null){
					idsRead.push(this.value);
				}
			});
			idsRead = idsRead.join();
			if(idsRead.length > 0){
				document.getElementById("idsRead").value = idsRead;
				document.getElementById("ids").value = ids;
				$("#method").val("submitYear");
				document.forms[0].submit();
			}else{
				alert("请选择数据后提交");
				return;
			}
			
		}
	 
	 
		function checkAll(name) {
			var el = document.getElementsByTagName('input');
			var len = el.length;
			for (var i = 0; i < len; i++) {
				if ((el[i].type == "checkbox") && (el[i].name == name) && el[i].getAttribute("checked") != "checked") {
					el[i].checked = true;
				}
			}
			$("#fullRead").prop("checked",true)
		}
		function clearAll(name) {
			var el = document.getElementsByTagName('input');
			var len = el.length;
			for (var i = 0; i < len; i++) {
				if ((el[i].type == "checkbox") && (el[i].name == name) && el[i].getAttribute("checked") != "checked") {
					el[i].checked = false;
				}
			}
			$("#fullRead").prop("checked",false)
		}
	 
	 
	
	//执行返回列表操作
	function doReturn() {
		location = "<url:context/>/videoPlan.do?method=findList";
	}
</script>
</head>
<body class="h-100 public" onLoad="doLoad()">
	<div class="public-main abs">
		<div class="ly-contai rel">
			<html:form action="/supervisoryearanday" styleId="/supervisorStorageForm" method="post" onsubmit="return false">
				<input name="method" id="method" type="hidden" value="submitYear" />
				<input type="hidden" id="idsRead" name="idsRead" />
				<input type="hidden" id="ids" name="ids" />
				<div class="public-main-table hidden abs" style="top:20px;">
					<div class="ly-cont">
						<table class="t-table" border="0" cellspacing="0" cellpadding="0">
							<thead class="t-thead">
								<tr class="t-tr">
									<th class="t-th">序号</th>
									<th class="t-th">问候语</th>
									<th class="t-th"><label for="checkAllValue"></label> <input
										name="test" value=""
										onclick="if(this.checked==true) { checkAll('isReadIds'); } else { clearAll('isReadIds'); }"
										type="checkbox">  已读</th>
								</tr>
							</thead>
							<tbody class="t-tbody">
								<logic:iterate name="list" id="row" indexId="index">
									<tr class="t-tr"  <c:if test="${row.isread == 1}">style="color:red;"</c:if> >
										<td class="t-td"><c:out value="${index+1}" /></td>
										<td class="t-td"><c:out value="${row.greetings}" /></td>
										<td class="t-td">
											<input type="checkbox" <c:if test="${row.isread == 2}"> checked="checked" disabled="disabled"</c:if> id="isReadIds"
												name="isReadIds" value="<c:out value='${row.id}'/>"> 已读
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</div>
				<div class="public-main-footer hidden abs">
				<a href="javascript:doSubmit();" class="button btn-add fl">提交</a>
					<div class="public-main-footer-pagin fr">
					<thumbpage:tools className="<%=ThumbPageConstants.CLASSNAME_DEFAULT.getCode()%>" 
	  							tableName="list" action="/supervisoryearanday.do?method=findListYearAndDay" />
					</div>
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