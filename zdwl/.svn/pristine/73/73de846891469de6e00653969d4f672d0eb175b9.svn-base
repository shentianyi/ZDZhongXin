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
<%@ page import="com.zd.tools.thumbPage.constants.ThumbPageConstants"%>
<html class="h-100">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <style>
		.scope a:hover {
		    text-decoration:none;
	</style>
    <title>中都物流 - 账户管理</title>
    <link type="text/css" rel="stylesheet" href="/css/base.css" />
    <link type="text/css" rel="stylesheet" href="/css/userman.css" />
    <link type="text/css" rel="stylesheet" href="/css/public.css" />
    <link href="/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
	<link href="/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    <script src="/js/jquery-1.7.1.js" type="text/javascript" language="javascript"></script>
    <script src="/js/common.js"></script>
    <script type="text/javascript">
		function messageRead(id){
			$.getJSON("/json/messageRead.do?callback=?&id="+id, function(json){
				  console.info(json);
			});
		}
	</script>
</head>
<body class="h-100 userman" onload="mynav()">
	<div class="add_nav" style="position: absolute;">
      <span class="add_nav_acolor">
      <a class="crumbs-link" style="color:#929291;" href="#">首页</a>
      <a class="crumbs-link" style="color:#929291;" href="#"></a>
      </span>
	</div>
	<html:form action="/message" styleId="messageForm" method="post" onsubmit="return false">
	<html:text styleClass="ly-bor-none" property="messagequery.type" styleId="messagequery.type"/>
    <div class="userman-main abs" style="margin-top:10px;">
        <div class="ly-left fl">
            <div class="ly-contai ly-top rel">
                <div class="ly-cont abs">
                    <!-- 3列表格 -->
                    <!-- start -->
                    <div class="userman-table userman-table-type-three rel">
                        <!-- 表格标题 -->
                        <!-- start -->
                        <div class="userman-table-caption hidden abs">
                            <span class="ico ico-bell fl"></span>
                            <span class="label block fl hidden">预警</span>
                        </div>
                        <!-- end -->
                        
                        <!-- 表格头部 -->
                        <!-- start -->
                        <div class="userman-table-thead abs">
                            <div class="tr h-100 hidden">
                                <div class="th t-first fl">序号</div>
                                <div class="th t-two fl">预警类别</div>
                                <div class="th t-last">预警名称</div>
                            </div>
                        </div>
                        <!-- end -->
                        
                        <!-- 表格主体 -->
                        <!-- start -->
                        <div class="userman-table-tbody abs">
                            <!-- 表格主体 repeat 部分 -->
                            <!-- start -->
                            <logic:iterate name="ylist" id="row" indexId="index">
                            	<div class="tr clearfix">
	                                <div class="td t-first fl"><c:out value="${index+1}"/></div>
	                                <div class="td t-two fl scope" >
	                                	<a href="javascript:void(0)" 
	                                	target="chechewang_main" 
	                                	title="<select:warntype mid="${row.type}"></select:warntype>">
	                                	<select:warntype mid="${row.type}"></select:warntype>
	                                	</a>
	                                </div>
	                                <div class="td t-last">
	                                	<a href="<c:out value="${row.url}"/>&parentId=<c:out value='${row.id}'/>" 
	                                	target="chechewang_main" 
	                                	onclick="messageRead('<c:out value="${row.id }"/>')" 
	                                	title="<c:out value="${row.name}" />">
											<c:out value="${row.name}" />
										</a>
	                                </div>
	                            </div>
							</logic:iterate>
                            <!-- end -->
                        </div>
                        <!-- end -->
                    </div>
                    <!-- end -->
                </div>
            </div>
            <div class="ly-contai ly-bottom rel">
                <div class="ly-cont abs">
                    <!-- 3列表格 -->
                    <!-- start -->
                    <div class="userman-table userman-table-type-three rel">
                        <!-- 表格标题 -->
                        <!-- start -->
                        <div class="userman-table-caption hidden abs">
                            <span class="ico ico-email fl"></span>
                            <span class="label block fl hidden">消息提示</span>
                        </div>
                        <!-- end -->
                        
                        <!-- 表格头部 -->
                        <!-- start -->
                        <div class="userman-table-thead abs">
                            <div class="tr h-100 hidden">
                                <div class="th t-first fl">序号</div>
                                <div class="th t-two fl">消息类别</div>
                                <div class="th t-last">消息名称</div>
                            </div>
                        </div>
                        <!-- end -->
                        <!-- 表格主体 -->
                        <!-- start -->
                        <div class="userman-table-tbody abs">
                            <!-- 表格主体 repeat 部分 -->
                            <!-- start -->
                            <logic:iterate name="mlist" id="row" indexId="index">
                            	<div class="tr clearfix">
	                                <div class="td t-first fl"><c:out value="${index+1}"/></div>
	                                <div class="td t-two fl scope">
	                                	<a href="javascript:void(0)" 
	                                	target="chechewang_main" 
	                                	title="<select:msgtype mid="${row.type}"></select:msgtype>">
	                                	<select:msgtype mid="${row.type}"></select:msgtype>
	                                	</a>
	                             	</div>
	                                <div class="td t-last">
	                                	<a href="<c:out value='${row.url}'/>&parentId=<c:out value='${row.id}'/>" 
	                                	target="chechewang_main" 
	                                	onclick="messageRead('<c:out value="${row.id }"/>')"
	                                	title="<c:out value="${row.name}" />">
											<c:out value="${row.name}" />
										</a>
	                                </div>
	                            </div>
							</logic:iterate>
                        </div>
                        <!-- end -->
                    </div>
                    <!-- end -->
                </div>
            </div>
        </div>
        <div class="ly-right fl rel">
            <div class="ly-contai ly-top rel">
                <div class="ly-cont abs">
                    <!-- 2列表格 -->
                    <!-- start -->
                    <div class="userman-table userman-table-type-two rel">
                        <!-- 表格标题 -->
                        <!-- start -->
                        <div class="userman-table-caption hidden abs">
                            <span class="ico ico-text fl"></span>
                            <span class="label block fl hidden">公告</span>
                        </div>
                        <!-- end -->
                        
                        <!-- 表格头部 -->
                        <!-- start -->
                        <div class="userman-table-thead abs">
                            <div class="tr h-100 hidden">
                                <div class="th t-first fl">序号</div>
                                <div class="th t-last">标题</div>
                            </div>
                        </div>
                        <!-- end -->
                        
                        <!-- 表格主体 -->
                        <!-- start -->
                        <div class="userman-table-tbody abs">
                            <!-- 表格主体 repeat 部分 -->
                            <!-- start -->
                            <logic:iterate name="nlist" id="row" indexId="index">
                            	<div class="tr clearfix">
	                                <div class="td t-first fl"><c:out value="${index+1}"/></div>
	                                <div class="td t-last">
	                                	<a href="javascript:window.open('<c:out value='${row.url }'/>','_self');"
	                                	title="<c:out value="${row.title}" />">
											<c:out value="${row.title}" />
										</a>
	                                </div>
	                            </div>
							</logic:iterate>
                            <!-- end -->
                        </div>
                        <!-- end -->
                    </div>
                    <!-- end -->
                </div>
            </div>
            <div class="ly-contai ly-bottom rel">
                <div class="ly-cont abs">
                    <!-- 2列表格 -->
                    <!-- start -->
                    <div class="userman-table userman-table-type-two rel">
                        <!-- 表格标题 -->
                        <!-- start -->
                        <div class="userman-table-caption hidden abs">
                            <span class="ico ico-calendar fl"></span>
                            <span class="label block fl hidden">制度文件</span>
                        </div>
                        <!-- end -->
                        
                        <!-- 表格头部 -->
                        <!-- start -->
                        <div class="userman-table-thead abs">
                            <div class="tr h-100 hidden">
                                <div class="th t-first fl">序号</div>
                                <div class="th t-last">标题</div>
                            </div>
                        </div>
                        <!-- end -->
                        
                        <!-- 表格主体 -->
                        <!-- start -->
                        <div class="userman-table-tbody abs">
                            <!-- 表格主体 repeat 部分 -->
                            <!-- start -->
                            <logic:iterate name="systemList" id="row" indexId="index">
                            	<div class="tr clearfix">
	                                <div class="td t-first fl"><c:out value="${index+1}"/></div>
	                                <div class="td t-last">
	                                	<a href="javascript:window.open('<c:out value='${row.url }'/>','_self');"
	                                	title="<c:out value="${row.title}" />">
											<c:out value="${row.title}" />
										</a>
	                                </div>
	                            </div>
							</logic:iterate>
                            <!-- end -->
                        </div>
                        <!-- end -->
                    </div>
                    <!-- end -->
                </div>
            </div>
        </div>
    </div>
    </html:form>
    <script type="text/javascript" src="/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="/js/jquery.divscroll1.js"></script>
    <script type="text/javascript">
        $('.userman-table-tbody').perfectScrollbar();
    </script>
</body>
</html>