package com.zd.csms.supervisorymanagement.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.constants.Constants;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.file.util.UploadFileUtil;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.supervisorymanagement.model.FixedAssetListQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.csms.supervisorymanagement.service.FixedAssetListService;
import com.zd.csms.supervisorymanagement.web.form.FixedAssetListForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class FixedAssetListAction extends ActionSupport {

	public ActionForward fixedAssetListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return fixedAssetList(mapping, form,request, response);
	}
	
	
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
	
	public ActionForward addFixedAssetListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		FixedAssetListForm falform = (FixedAssetListForm)form;
		String fixedAssetid = request.getParameter("fid");
		int fid = 0;
		if(!StringUtil.isEmpty(fixedAssetid)){
			fid = Integer.parseInt(fixedAssetid);
		}else{
			fid = falform.getFixdeAssetid();
		}
		
		falform.setFixdeAssetid(fid);
		request.setAttribute("fid", fid);
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_fixed_asset_list");
	}
	
	public ActionForward addFixedAssetList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FixedAssetListForm falform = (FixedAssetListForm)form;
		FixedAssetListService service = new FixedAssetListService();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		String fixedAssetid = request.getParameter("fid");
		int fid = 0;
		if(!StringUtil.isEmpty(fixedAssetid)){
			fid = Integer.parseInt(fixedAssetid);
		}else{
			fid = falform.getFixdeAssetid();
		}
		
		falform.getFixedAssetList().setFid(fid);
		
		falform.setFixdeAssetid(fid);
		request.setAttribute("fid", fid);
		
		int faid = 0;
		
		FormFile file = falform.getFixed();
		if(file != null){
			int ufid = UploadFileUtil.savefile(file, UserSessionUtil.getUserSession(request), request);
			
			FixedAssetListVO falvo = new FixedAssetListVO();
			falvo = falform.getFixedAssetList();
			falvo.setReceive_pic(ufid);
			falvo.setCreateuserid(user.getId());
			falvo.setCreatedate(new Date());
			//执行新增操作并获取操作结果
			faid = service.addFixedAssetList(falvo);
			
			
		}else{
			FixedAssetListVO falvo = new FixedAssetListVO();
			falvo = falform.getFixedAssetList();
			falvo.setCreateuserid(user.getId());
			falvo.setCreatedate(new Date());
			//执行新增操作并获取操作结果
			faid = service.addFixedAssetList(falvo);
		}
		
		
		if(faid > 0){
			//新增成功时返回列表页面
			return fixedAssetList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_fixed_asset_list");
	}
	
	public ActionForward updFixedAssetListEntry(ActionMapping mapping, ActionForm form,
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
		UploadfileService ufService = new UploadfileService();
		UploadfileVO ufvo = ufService.loadUploadfileById(vo.getReceive_pic());
		if (ufvo != null) {
			falform.setFixedpath(ufvo.getFile_path());
			request.setAttribute("picname", ufvo.getFile_name());
		}
		
		request.setAttribute("fixedpath", falform.getFixedpath());
		
		
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return fixedAssetList(mapping, form, request, response);
		}
		
		falform.setFixedAssetList(vo);
		setOptions(request);
		request.setAttribute("provincesOptions", OptionUtil.getRegionProvince());
		request.setAttribute("cid", vo.getTrade_city());
		//返回修改页面
		return mapping.findForward("upd_fixed_asset_list");
	}
	
	/*
	 * 根据省id获取市
	 * @return json
	 * @time 20170512
	*/
	public ActionForward searchCity(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JSONArray ja = new JSONArray();
		FixedAssetListForm falform = (FixedAssetListForm)form;
		FixedAssetListVO query = falform.getFixedAssetList();
		List<OptionObject> brandList= new ArrayList<OptionObject>();
		brandList = OptionUtil.getCitys(String.valueOf(query.getTrade_province()));
		ja.add(brandList);
		 response.setCharacterEncoding("UTF-8"); 
		 PrintWriter writer = null;
		 System.out.println(ja.toString());
		try {
			writer = response.getWriter();
			writer.print(ja.toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			writer.flush();
			writer.close();
		}
		return null;
		
	}
	
	public ActionForward updFixedAssetList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FixedAssetListForm falform = (FixedAssetListForm)form;
		FixedAssetListService service = new FixedAssetListService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		String fixedAssetid = request.getParameter("fid");
		int fid = 0;
		if(!StringUtil.isEmpty(fixedAssetid)){
			fid = Integer.parseInt(fixedAssetid);
		}else{
			fid = falform.getFixdeAssetid();
		}
		falform.getFixedAssetList().setFid(fid);
		falform.setFixdeAssetid(fid);
		request.setAttribute("fid", fid);
		
		FormFile file = falform.getFixed();
		boolean flag = false;
		if(file != null){
			int ufid = UploadFileUtil.savefile(file, UserSessionUtil.getUserSession(request), request);
			
			FixedAssetListVO falvo = new FixedAssetListVO();
			falvo = falform.getFixedAssetList();
			falvo.setReceive_pic(ufid);
			falvo.setUpduserid(user.getId());
			falvo.setUpddate(new Date());
			//执行新增操作并获取操作结果
			flag = service.updFixedAssetList(falvo);
			
			
		}else{
			FixedAssetListVO falvo = falform.getFixedAssetList();
			falvo.setUpduserid(user.getId());
			falvo.setUpddate(new Date());
			flag = service.updFixedAssetList(falvo);
		}
		
		//执行修改操作并获取操作结果
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return fixedAssetList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_fixed_asset_list");
	}
	
	public ActionForward delFixedAssetList(ActionMapping mapping, ActionForm form,
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
		//执行删除操作并获取操作结果
		boolean flag = service.deleteFixedAssetList(falform.getFixedAssetList().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return fixedAssetList(mapping, form, request, response);
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
