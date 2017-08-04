package com.zd.csms.marketing.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.BrandQueryVO;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.marketing.service.BrandService;
import com.zd.csms.marketing.web.form.BrandForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class BrandAction extends ActionSupport {

	public ActionForward brandListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return brandList(mapping, form,request, response);
	}
	
	
	public ActionForward brandList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		BrandForm bform = (BrandForm)form;
		BrandService service = new BrandService();
		
		BrandQueryVO bQuery = bform.getBrandquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("brandList",request);
		thumbPageTools.saveQueryVO(bQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<BrandVO> list = service.searchBrandListByPage(bQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		//返回列表页面
		return mapping.findForward("brand_list");
	}
	
	public ActionForward addBrandEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		return mapping.findForward("add_brand");
	}
	
	public ActionForward addBrand(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BrandForm bform = (BrandForm)form;
		BrandService service = new BrandService();
	
		//执行新增操作并获取操作结果
		boolean flag = service.addBrand(bform.getBrand());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return brandList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_brand");
	}
	
	public ActionForward updBrandEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BrandForm bform = (BrandForm)form;
		BrandService service = new BrandService();
		
		//根据id获取修改对象
		BrandVO vo = service.loadBrandById(bform.getBrand().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return brandList(mapping, form, request, response);
		}
		
		bform.setBrand(vo);
		//返回修改页面
		return mapping.findForward("upd_brand");
	}
	
	public ActionForward updBrand(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BrandForm bform = (BrandForm)form;
		BrandService service = new BrandService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updBrand(bform.getBrand());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return brandList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_brand");
	}
	
	public ActionForward delBrand(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BrandForm bform = (BrandForm)form;
		BrandService service = new BrandService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteBrand(bform.getBrand().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return brandList(mapping, form, request, response);
	}
}
