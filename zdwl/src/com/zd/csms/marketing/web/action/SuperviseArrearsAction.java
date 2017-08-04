package com.zd.csms.marketing.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.SuperviseArrearsQueryVO;
import com.zd.csms.marketing.model.SuperviseArrearsVO;
import com.zd.csms.marketing.service.SuperviseArrearsService;
import com.zd.csms.marketing.web.form.SuperviseArrearsForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SuperviseArrearsAction extends ActionSupport {

	public ActionForward superviseArrearsListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return superviseArrearsList(mapping, form,request, response);
	}
	
	
	public ActionForward superviseArrearsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SuperviseArrearsForm saform = (SuperviseArrearsForm)form;
		SuperviseArrearsService service = new SuperviseArrearsService();
		
		SuperviseArrearsQueryVO saQuery = saform.getSuperviseArrearsquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("superviseArrearsList",request);
		thumbPageTools.saveQueryVO(saQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<SuperviseArrearsVO> list = service.searchSuperviseArrearsListByPage(saQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("supervise_arrears_list");
	}
	
	public ActionForward addSuperviseArrearsEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_supervise_arrears");
	}
	
	public ActionForward addAgreementBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseArrearsForm saform = (SuperviseArrearsForm)form;
		SuperviseArrearsService service = new SuperviseArrearsService();
		
		//执行新增操作并获取操作结果
		boolean flag = service.addSuperviseArrears(saform.getSuperviseArrears());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return superviseArrearsList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_supervise_arrears");
	}
	
	public ActionForward updSuperviseArrearsEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseArrearsForm saform = (SuperviseArrearsForm)form;
		SuperviseArrearsService service = new SuperviseArrearsService();
		
		//根据id获取修改对象
		SuperviseArrearsVO vo = service.loadSuperviseArrearsById(saform.getSuperviseArrears().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return superviseArrearsList(mapping, form, request, response);
		}
		
		saform.setSuperviseArrears(vo);
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_supervise_arrears");
	}
	
	public ActionForward updSuperviseArrears(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseArrearsForm saform = (SuperviseArrearsForm)form;
		SuperviseArrearsService service = new SuperviseArrearsService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updSuperviseArrears(saform.getSuperviseArrears());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return superviseArrearsList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_supervise_arrears");
	}
	
	public ActionForward delSuperviseArrears(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SuperviseArrearsForm saform = (SuperviseArrearsForm)form;
		SuperviseArrearsService service = new SuperviseArrearsService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteSuperviseArrears(saform.getSuperviseArrears().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return superviseArrearsList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("yesornoOptions", OptionUtil.yesorno());
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
	}
}
