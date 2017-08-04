package com.zd.csms.supervisory.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.constants.Constants;
import com.zd.csms.supervisory.model.CarManualQueryVO;
import com.zd.csms.supervisory.model.CarManualVO;
import com.zd.csms.supervisory.service.CarManualService;
import com.zd.csms.supervisory.web.form.CarManualForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class CarManualAction extends ActionSupport {

	public ActionForward carManualListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return carManualList(mapping, form,request, response);
	}
	
	
	public ActionForward carManualList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		CarManualForm cform = (CarManualForm)form;
		CarManualService service = new CarManualService();
		
		CarManualQueryVO cQuery = cform.getCarmanualquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("carManualList",request);
		thumbPageTools.saveQueryVO(cQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<CarManualVO> list = service.searchCarManualListByPage(cQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		//返回列表页面
		return mapping.findForward("car_manual_list");
	}
	
	public ActionForward updCarManualEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CarManualForm cform = (CarManualForm)form;
		CarManualService service = new CarManualService();
		
		//根据id获取修改对象
		CarManualVO vo = service.loadCarManualById(cform.getCarmanual().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return carManualList(mapping, form, request, response);
		}
		
		cform.setCarmanual(vo);
		//返回修改页面
		return mapping.findForward("upd_car_manual");
	}
	
	public ActionForward updCarManual(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CarManualForm cform = (CarManualForm)form;
		CarManualService service = new CarManualService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updCarManual(cform.getCarmanual());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return carManualList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_car_manual");
	}
}
