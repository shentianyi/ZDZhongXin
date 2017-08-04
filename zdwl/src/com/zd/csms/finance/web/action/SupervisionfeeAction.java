package com.zd.csms.finance.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.finance.model.SupervisionfeeQueryVO;
import com.zd.csms.finance.model.SupervisionfeeVO;
import com.zd.csms.finance.service.SupervisionfeeService;
import com.zd.csms.finance.web.form.SupervisionfeeForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SupervisionfeeAction extends ActionSupport {

	public ActionForward supervisionfeeListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return supervisionfeeList(mapping, form,request, response);
	}
	
	
	public ActionForward supervisionfeeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SupervisionfeeForm srform = (SupervisionfeeForm)form;
		SupervisionfeeService service = new SupervisionfeeService();
		
		SupervisionfeeQueryVO srQuery = srform.getSupervisionfeequery();
		srQuery.setType(1);
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("supervisionfeeList",request);
		thumbPageTools.saveQueryVO(srQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<SupervisionfeeVO> list = service.searchSupervisionfeeListByPage(srQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("supervisionfee_list");
	}
	
	public ActionForward addSupervisionfeeEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_supervisionfee");
	}
	
	public ActionForward addSupervisionfee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SupervisionfeeForm srform = (SupervisionfeeForm)form;
		SupervisionfeeService service = new SupervisionfeeService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		SupervisionfeeVO svo = srform.getSupervisionfee();
		svo.setCreateuserid(user.getId());
		svo.setCreatedate(new Date());
		//执行新增操作并获取操作结果
		boolean flag = service.addSupervisionfee(svo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return supervisionfeeList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_supervisionfee");
	}
	
	public ActionForward updSupervisionfeeEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SupervisionfeeForm srform = (SupervisionfeeForm)form;
		SupervisionfeeService service = new SupervisionfeeService();
		
		//根据id获取修改对象
		SupervisionfeeVO vo = service.loadSupervisionfeeById(srform.getSupervisionfee().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return supervisionfeeList(mapping, form, request, response);
		}
		
		srform.setSupervisionfee(vo);
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_supervisionfee");
	}
	
	public ActionForward updSupervisionfee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SupervisionfeeForm srform = (SupervisionfeeForm)form;
		SupervisionfeeService service = new SupervisionfeeService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		SupervisionfeeVO svo = srform.getSupervisionfee();
		svo.setUpduserid(user.getId());
		svo.setUpddate(new Date());
		//执行修改操作并获取操作结果
		boolean flag = service.updSupervisionfee(svo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return supervisionfeeList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_supervisionfee");
	}
	
	public ActionForward delSupervisionfee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SupervisionfeeForm srform = (SupervisionfeeForm)form;
		SupervisionfeeService service = new SupervisionfeeService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteSupervisionfee(srform.getSupervisionfee().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return supervisionfeeList(mapping, form, request, response);
	}
	
	public ActionForward supervisionfeeSummary(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SupervisionfeeForm srform = (SupervisionfeeForm)form;
		SupervisionfeeService service = new SupervisionfeeService();
		
		SupervisionfeeQueryVO srQuery = srform.getSupervisionfeequery();
		srQuery.setType(1);
		srQuery.setOthersql(" group by distribid");
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("supervisionfeeSummary",request);
		thumbPageTools.saveQueryVO(srQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<SupervisionfeeVO> list = service.searchSupervisionfeeListByPage(srQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("supervisionfee_summary");
	}
	
	public ActionForward supervisionfeeSummaryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SupervisionfeeForm srform = (SupervisionfeeForm)form;
		SupervisionfeeService service = new SupervisionfeeService();
		
		SupervisionfeeQueryVO srQuery = srform.getSupervisionfeequery();
		srQuery.setType(1);
		
		String distribid = request.getParameter("distribid");
		
		if(!StringUtil.isEmpty(distribid)){
			srQuery.setDistribid(Integer.parseInt(distribid));
		}
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("supervisionfeeSummaryList",request);
		thumbPageTools.saveQueryVO(srQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<SupervisionfeeVO> list = service.searchSupervisionfeeListByPage(srQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//返回列表页面
		return mapping.findForward("supervisionfee_summary_list");
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		
	}
}
