package com.zd.csms.ledger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.supervisorymanagement.model.FixedAssetListQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.csms.supervisorymanagement.service.FixedAssetListService;
import com.zd.csms.supervisorymanagement.web.form.FixedAssetListForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class FixedAssetListAction extends ActionSupport {

	
	public ActionForward fixedAssetList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		FixedAssetListForm falform = (FixedAssetListForm)form;
		FixedAssetListService service = new FixedAssetListService();
		
		FixedAssetListQueryVO falQuery = falform.getFixedAssetListquery();
		
		String fixedAssetid = request.getParameter("fid");
		int fid = 0;
		if(!StringUtil.isEmpty(fixedAssetid)){
			fid = Integer.parseInt(fixedAssetid);
		}else{
			fid = falform.getFixdeAssetid();
		}
		
		falform.setFixdeAssetid(fid);
		request.setAttribute("fid", fid);
		
		falQuery.setFid(fid);
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("fixedAssetList",request);
		thumbPageTools.saveQueryVO(falQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<FixedAssetListVO> list = service.searchFixedAssetListByPage(falQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("fixed_asset_list");
	}
	
	public ActionForward detailFixedAssetList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FixedAssetListForm falform = (FixedAssetListForm)form;
		FixedAssetListService service = new FixedAssetListService();
		String fixedAssetid = request.getParameter("fid");
		int fid = 0;
		if(!StringUtil.isEmpty(fixedAssetid)){
			fid = Integer.parseInt(fixedAssetid);
		}else{
			fid = falform.getFixdeAssetid();
		}
		
		falform.setFixdeAssetid(fid);
		request.setAttribute("fid", fid);
		//根据id获取修改对象
		FixedAssetListVO vo = service.loadFixedAssetListById(falform.getFixedAssetList().getId());
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return fixedAssetList(mapping, form, request, response);
		}
		RegionService regionService=new RegionService();
		if (vo !=null) {
			String province = regionService.getNameById(vo.getTrade_province());
			String city = regionService.getNameById(vo.getTrade_city());
			request.setAttribute("province", province);
			request.setAttribute("city", city);
		}
		falform.setFixedAssetList(vo);
		
		UploadfileService ufservice = new UploadfileService();
		UploadfileVO ufvo = ufservice.loadUploadfileById(vo.getReceive_pic());
		if(ufvo != null){
			request.setAttribute("pic", ufvo.getId());
			request.setAttribute("file", ufvo.getFile_path());
		}
		
		//返回修改页面
		return mapping.findForward("detail_fixed_asset_list");
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("supervisorsOptions", OptionUtil.getAllSupervise());
		
	}
}
