package com.zd.csms.supervisorymanagement.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.constants.Constants;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.file.util.UploadFileUtil;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.contants.SuperVisoryStatusContants;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.csms.supervisory.util.CreatePDFUtil;
import com.zd.csms.supervisorymanagement.contants.AssetsTypeContant;
import com.zd.csms.supervisorymanagement.model.ElectronicTextVO;
import com.zd.csms.supervisorymanagement.model.ExtHandoverLogVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.csms.supervisorymanagement.model.HandoverBookVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogListVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogPicVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogVO;
import com.zd.csms.supervisorymanagement.model.OfficeEquipmentVO;
import com.zd.csms.supervisorymanagement.model.OtherDataVO;
import com.zd.csms.supervisorymanagement.model.PaperTextVO;
import com.zd.csms.supervisorymanagement.service.FixedAssetsService;
import com.zd.csms.supervisorymanagement.service.HandoverLogService;
import com.zd.csms.supervisorymanagement.web.excel.HandoverLogRowMapper;
import com.zd.csms.supervisorymanagement.web.form.HandoverLogForm;
import com.zd.csms.windcontrol.model.Image;
import com.zd.tools.StringUtil;
import com.zd.tools.file.FileUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class HandoverLogAction extends ActionSupport {
	HandoverLogService service=new HandoverLogService();
	UploadfileService ufService = new UploadfileService();
	SuperviseImportService superviseImportService = new SuperviseImportService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	public ActionForward addHandoverLogEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getRepositorySupervise());
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_handoverLog");
	}
	
	public ActionForward addHandoverLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogVO handoverLog=handoverLogForm.getHandoverLog();
		UserSession user = UserSessionUtil.getUserSession(request);
		handoverLog.setDeployId(user.getUser().getId());
		handoverLog.setIsEdit(0);
		handoverLog.setCreateUserId(user.getUser().getId());
		handoverLog.setCreateTime(new Date());
		handoverLog.setApproveStatus(-1);
		boolean flag=false;
		try {
			flag = service.add(handoverLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//新增成功返回列表页
			return handoverLogPageList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addHandoverLogEntity(mapping,form,request,response);
		}
	}
	
	
	public ActionForward saveSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogVO handoverLog = handoverLogForm.getHandoverLog();
		
		
		int num = 1;
		if(handoverLog.getId() > 0){
			handoverLog.setApproveStatus(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			boolean flag = service.updHandoverLogEditStatus(handoverLog);
			if(flag){
				num = 0;
			}
		}
		
//		JSONObject json = new JSONObject();
		String message = "通知提交成功";
		if(num==1){
			message = "通知提交失败";
		}
		PrintWriter out = null;
		try {
			out = getWrite(response);
//			json.put("message", num);
//			out.write(json.toJSONString());
			out.write(message);
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
			
		}
		return null;
	}
	
	public ActionForward updHandoverLogEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		HandoverLogVO handoverLog=null;
		try {
			handoverLog = service.load(handoverLogForm.getHandoverLog().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//对象不存在时返回列表页
		if(handoverLog==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改内容不存在");
			return handoverLogPageList(mapping,handoverLogForm,request,response);
		}
		handoverLogForm.setHandoverLog(handoverLog);
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getRepositorySupervise());
		request.setAttribute("isEdit", handoverLog.getIsEdit());
		setOptions(request);
		//返回新增页面
		return mapping.findForward("upd_handoverLog");
	}
	
	public ActionForward showHandoverPic(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		
		String hpicId = request.getParameter("hpicId");
		if(hpicId==null || hpicId.equals("") || hpicId.equals("undefined")){
			return null;
		}
		
		int hId;
		try {
			hId = Integer.parseInt(hpicId);
		} catch (NumberFormatException e2) {
			e2.printStackTrace();
			return null;
		}
		
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogPicVO pic;
		pic=service.loadHandoverLogPic(hId);
		
		
		if(pic == null){
			return null;
		}
		
		handoverLogForm.setHpic(pic);
		
		/**
		 * 此处取出对应的图片信息拼接成Image对象返回去
		 */

		// 拼接Image对象信息
		List<Image> list = new ArrayList<Image>();
		Image image;
		
		if(pic.getPic1()>0){
			image = service.createHandoverPicImageList(pic.getPic1(),hId);
			list.add(image);
		}
		
		if(pic.getPic2()>0){
			image = service.createHandoverPicImageList(pic.getPic2(),hId);
			list.add(image);
		}
		
		if(pic.getPic3()>0){
			image = service.createHandoverPicImageList(pic.getPic3(),hId);
			list.add(image);
		}
		
		if(pic.getPic4()>0){
			image = service.createHandoverPicImageList(pic.getPic4(),hId);
			list.add(image);
		}
		
		if(pic.getPic5()>0){
			image = service.createHandoverPicImageList(pic.getPic5(),hId);
			list.add(image);
		}
		
		if(pic.getPic6()>0){
			image = service.createHandoverPicImageList(pic.getPic6(),hId);
			list.add(image);
		}
		
		if(pic.getPic7()>0){
			image = service.createHandoverPicImageList(pic.getPic7(),hId);
			list.add(image);
		}
		
		if(pic.getPic8()>0){
			image = service.createHandoverPicImageList(pic.getPic8(),hId);
			list.add(image);
		}
		
		if(pic.getPic9()>0){
			image = service.createHandoverPicImageList(pic.getPic9(),hId);
			list.add(image);
		}
		
		if(pic.getPic10()>0){
			image = service.createHandoverPicImageList(pic.getPic10(),hId);
			list.add(image);
		}
		
		
		
		
		
		
		
		
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		try {
			out = getWrite(response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		json.put("files", list);
		try {
			out.write(json.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
		
	}
	
	/**
	 * 删除图片的方法
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteHandoverPicPicture(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileId = request.getParameter("fileId");// 获取图片Id
		String hpic = request.getParameter("hpicId");
		
		boolean bool = false;
		if (StringUtil.isNotEmpty(fileId) && StringUtil.isNotEmpty(hpic)) {
			int fid = Integer.parseInt(fileId);
			
			int hId = Integer.parseInt(hpic);
			
			
			try {
				HandoverLogPicVO pic = service.loadHandoverLogPic(hId);

				if(pic.getIsEdit() == 1){
					return null;
				}
				
				//删除图片文件
				bool = service.deleteHandoverPicPicture(fid);

				//删除图片关联ID
				if(pic.getPic1() == fid){
					pic.setPic1(0);
				}else if(pic.getPic2() == fid){
					pic.setPic2(0);
				}else if(pic.getPic3() == fid){
					pic.setPic3(0);
				}else if(pic.getPic4() == fid){
					pic.setPic4(0);
				}else if(pic.getPic5() == fid){
					pic.setPic5(0);
				}else if(pic.getPic6() == fid){
					pic.setPic6(0);
				}else if(pic.getPic7() == fid){
					pic.setPic7(0);
				}else if(pic.getPic8() == fid){
					pic.setPic8(0);
				}else if(pic.getPic9() == fid){
					pic.setPic9(0);
				}else if(pic.getPic10() == fid){
					pic.setPic10(0);
				}
				
				service.updateHandoverLogPic(pic);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		PrintWriter out = getWrite(response);
		JSONObject json = new JSONObject();
		json.put("success", bool);
		try {
			out.write(json.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

		return null;
	}
	
	public ActionForward downLoadPathFile(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String distribName = request.getParameter("distribName");
		//文件名乱码
		if (StringUtil.isNotEmpty(distribName)) {
			distribName = new String(request.getParameter("distribName").getBytes("ISO8859_1"),"utf-8");
		}
		
		HandoverLogPicVO pic = service.searchPicsByHandoverLogId(handoverLogForm.getHpic().getId());
		if(pic==null){
			return null;
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		UploadfileService ufService = new UploadfileService();
		Field[] fields = pic.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			 String name = field. getName();
			 if(name.startsWith("pic")){
				 try {
					UploadfileVO file = ufService.loadUploadfileById(field.getInt(pic));
					map.put(file.getFile_name(), (FileUtil.getFileFolder()+file.getFile_path()));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
			
		}
		UserService us = new UserService();
		
		FileUtil.downloadZIP(map,(distribName), response);
		
		return null;
	}
	
	
	public ActionForward addHandoverPic(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		
		
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		UserSession userSession = UserSessionUtil.getUserSession(request);
		
		String method = request.getMethod();
		/**
		 * 加载图片列表和新增图片走的请求一致，只不是显示的时候用的是GET而新增是POST
		 */
		if (method.equalsIgnoreCase("GET")) {
			return showHandoverPic(mapping, form, request, response);
		}
		
		String hpicId = request.getParameter("hpicId");
		if(hpicId==null || hpicId.equals("") || hpicId.equals("undefined")){
			return null;
		}
		
		int Id;
		try {
			Id = Integer.parseInt(hpicId);
		} catch (NumberFormatException e2) {
			e2.printStackTrace();
			return null;
		}
		
		HandoverLogPicVO pic=service.loadHandoverLogPic(Id);
		
		if(pic.getIsEdit() == 1){
			return null;
		}
		
		FormFile file = handoverLogForm.getFileNNT();
		if (file == null) {
			return null;
		}
		PrintWriter out = null;
		try {
			out = getWrite(response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JSONObject json = new JSONObject();
		
		handoverLogForm.setHpic(pic);
		
		// 保存文件到数据库中并获取对应的id
		int uplId = UploadFileUtil.savefile(file, userSession, request);
		
		if(pic.getPic1()<=0){
			pic.setPic1(uplId);
		}else if(pic.getPic2()<=0){
			pic.setPic2(uplId);
		}else if(pic.getPic3()<=0){
			pic.setPic3(uplId);
		}else if(pic.getPic4()<=0){
			pic.setPic4(uplId);
		}else if(pic.getPic5()<=0){
			pic.setPic5(uplId);
		}else if(pic.getPic6()<=0){
			pic.setPic6(uplId);
		}else if(pic.getPic7()<=0){
			pic.setPic7(uplId);
		}else if(pic.getPic8()<=0){
			pic.setPic8(uplId);
		}else if(pic.getPic9()<=0){
			pic.setPic9(uplId);
		}else{
			pic.setPic10(uplId);
		}
		
		service.updateHandoverLogPic(pic);
		
		// 拼接Image对象信息
				Image image = null;
				if (uplId > 0) {
				String filePath = (String) request.getAttribute("filePath");
					// 保存巡检报告与店面图片信息到数据库中

				//	boolean fag = addInfo(pic);
						image = new Image();
						image.setUrl("/showImg.do?method=downLoadFile&filePath="
								+ filePath + "&fileName=" + file.getFileName());// 下载地址
						image.setThumbnailUrl("/showImg.do?method=showThumbnailImg&filePath="
								+ filePath);// 上传完成后的缩略图地址
						image.setDeleteUrl("/handoverLog.do?method=deleteHandoverPicPicture&fileId="
								+ uplId+"&hpicId="+pic.getId());// 删除图片的地址
						image.setName(file.getFileName());

						image.setDeleteType("GET");// 提交方式
						
				}
		
		
		if (image != null) {
			List<Image> list = new ArrayList<Image>(1);
			list.add(image);
			json.put("files", list);
			try {
				out.write(json.toJSONString());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.flush();
				out.close();
			}
		}

		return null;
	}
	
	
	public ActionForward updHandoverLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogVO handoverLog=handoverLogForm.getHandoverLog();
		UserSession user = UserSessionUtil.getUserSession(request);
		handoverLog.setDeployId(user.getUser().getId());
		handoverLog.setLastModifyUser(user.getUser().getId());
		handoverLog.setLastModifyTime(new Date());
		//交接记录管理-列表页-点击修改-保存-审批状态变成“未提交”，应该是未通知 
		handoverLog.setApproveStatus(-1);
		boolean flag=false;
		try {
			flag = service.update(handoverLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//修改成功返回列表页
			return handoverLogPageList(mapping,form,request,response);
		}else{
			//修改失败返回修改页面
			return updHandoverLogEntity(mapping,form,request,response);
		}
	}
	public ActionForward delHandoverLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		boolean flag=false;
		try {
			flag = service.delete(handoverLogForm.getHandoverLog().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		//返回列表页
		return handoverLogPageList(mapping,handoverLogForm,request,response);
	}
	/**
	 * 返回分页列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward handoverLogPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogQueryVO handoverLogQuery=handoverLogForm.getQuery();
		int currRole = getCurrRole(request);
		if (handoverLogQuery.getPageType() == 0) {
			handoverLogQuery.setPageType(1);
		}
		int pageType=handoverLogQuery.getPageType();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("handoverLogList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(handoverLogQuery);
		handoverLogQuery.setPageType(pageType);
		handoverLogQuery.setCurrentRole(currRole);
		if(currRole==RoleConstants.SUPERVISORY.getCode()){
			handoverLogQuery.setCurrentRepositoryId(UserSessionUtil.getUserSession(request).getUser().getClient_id());
		}
		//按条件查询分页数据
		List<HandoverLogVO> handoverLogs = service.searchHandoverLogListByPage(handoverLogQuery, tools);
		List<HandoverLogListVO> list=new ArrayList<HandoverLogListVO>();
		if(handoverLogs!=null&&handoverLogs.size()>0){
			for(HandoverLogVO handoverLog:handoverLogs){
				HandoverLogListVO h=new HandoverLogListVO();
				BeanUtils.copyProperties(handoverLog, h);
				h.setApproveStatusStr(ApprovalContant.getByCode(handoverLog.getApproveStatus()).getName());
				h.setNextApprovalStr(RoleConstants.getName(handoverLog.getNextApproval()));
				HandoverLogPicVO pic=service.searchPicsByHandoverLogId(handoverLog.getId());
				if(pic!=null){
					h.setPicIsEdit(pic.getIsEdit());
					h.setPicId(pic.getId());
				}
				list.add(h);
			}
		}
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		setOptions(request);
		request.setAttribute("role", getCurrRole(request));
		if (handoverLogQuery.getPageType() == 1){
			return mapping.findForward("list_wait_approval");
		}else{
			return mapping.findForward("list_already_approval");
		}
	}
	
	
	
	
	
	/**
	 * 列表页需要使用的角色
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode(),
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.SR.getCode()};
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	/**
	 * 返回列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * 
	 */
	public List<HandoverLogVO> HandoverLogList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogQueryVO handoverLogQuery=handoverLogForm.getQuery();
		
		//按条件查询分页数据
		List<HandoverLogVO> list = service.searchHandoverLogList(handoverLogQuery);
		
		return list;
	}
	public void setOptions(HttpServletRequest request){
		request.setAttribute("handoverNatures", OptionUtil.handoverNatureOptions());
		request.setAttribute("resignReasons", OptionUtil.resignReasonOptions());
		request.setAttribute("receiveNatures", OptionUtil.receiveNatureOptions());
		request.setAttribute("afterHandoverNature", OptionUtil.afterHandoverNatureOptions());
		request.setAttribute("superviseStateOptions", OptionUtil.superviseState());
		request.setAttribute("brandOptions", OptionUtil.getBrand());
		request.setAttribute("yesorno", OptionUtil.yesorno());
		request.setAttribute("matchorno", OptionUtil.matchorno());
		request.setAttribute("informorno", OptionUtil.informorno());
		request.setAttribute("haveorno", OptionUtil.haveorno());
		request.setAttribute("nomalorno", OptionUtil.nomalorno());
		request.setAttribute("keyAmountorno", OptionUtil.keyAmountorno());
		request.setAttribute("breakorno", OptionUtil.breakorno());
		request.setAttribute("haveornoDebt", OptionUtil.haveornoDebt());
		request.setAttribute("handoverTypes", OptionUtil.getHandoverType());
		
	}
	public ActionForward handoverLogDetail(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		int id = handoverLogForm.getHandoverLog().getId();
		HandoverLogVO handoverLog=null;
		HandoverBookVO book=null;
		try {
			handoverLog = service.load(id);
			if(handoverLog!=null){
				getHandoverBook(handoverLog,request);
				book=service.getHandoverBookBySupervisorId(handoverLog.getDeliverer());
				request.setAttribute("draftOptions", OptionUtil.getDraftOptionsByDealerId(handoverLog.getDealerId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		handoverLogForm.setHandoverLog(handoverLog);
		handoverLogForm.setHandoverBook(book);
		handoverLogForm.seteText(book.geteText());
		handoverLogForm.setpText(book.getpText());
		handoverLogForm.setOData(book.getOData());
		handoverLogForm.setOfficeEquipment(book.getOfficeEquipment());
		setOptions(request);
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(id);
		approvalQuery.setObjectType(ApprovalTypeContant.HANDOVERLOG.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		HandoverLogPicVO pic=service.searchPicsByHandoverLogId(id);
		if(pic==null){
			pic=service.loadHandoverLogPic(handoverLogForm.getHpic().getId());
		}
		handoverLogForm.setHpic(pic);
		if(pic!=null){
			UploadfileVO ufvo1;
			try {
				ufvo1 = ufService.loadUploadfileById(pic.getPic1());
				if(ufvo1!=null){
					request.setAttribute("fixedpath1", ufvo1.getFile_path());
					request.setAttribute("picId1", ufvo1.getId());
				}else{
					request.setAttribute("fixedpath1", null);
					request.setAttribute("picId1", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo2;
			try {
				ufvo2 = ufService.loadUploadfileById(pic.getPic2());
				if(ufvo2!=null){
					request.setAttribute("fixedpath2", ufvo2.getFile_path());
					request.setAttribute("picId2", ufvo2.getFile_name());
				}else{
					request.setAttribute("fixedpath2", null);
					request.setAttribute("picId2", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo3;
			try {
				ufvo3 = ufService.loadUploadfileById(pic.getPic3());
				if(ufvo3!=null){
					request.setAttribute("fixedpath3", ufvo3.getFile_path());
					request.setAttribute("picId3", ufvo3.getFile_name());
				}else{
					request.setAttribute("fixedpath3", null);
					request.setAttribute("picId3", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo4;
			try {
				ufvo4 = ufService.loadUploadfileById(pic.getPic4());
				if(ufvo4!=null){
					request.setAttribute("fixedpath4", ufvo4.getFile_path());
					request.setAttribute("picId4", ufvo4.getFile_name());
				}else{
					request.setAttribute("fixedpath4",null);
					request.setAttribute("picId4",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo5;
			try {
				ufvo5 = ufService.loadUploadfileById(pic.getPic5());
				if(ufvo5!=null){
					request.setAttribute("fixedpath5", ufvo5.getFile_path());
					request.setAttribute("picId5", ufvo5.getFile_name());
				}else{
					request.setAttribute("fixedpath5", null);
					request.setAttribute("picId5", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo6;
			try {
				ufvo6 = ufService.loadUploadfileById(pic.getPic6());
				if(ufvo6!=null){
					request.setAttribute("fixedpath6", ufvo6.getFile_path());
					request.setAttribute("picId6", ufvo6.getFile_name());
				}else{
					request.setAttribute("fixedpath6",null);
					request.setAttribute("picId6", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo7;
			try {
				ufvo7 = ufService.loadUploadfileById(pic.getPic7());
				if(ufvo7!=null){
					request.setAttribute("fixedpath7", ufvo7.getFile_path());
					request.setAttribute("picId7", ufvo7.getFile_name());
				}else{
					request.setAttribute("fixedpath7", null);
					request.setAttribute("picId7",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo8;
			try {
				ufvo8 = ufService.loadUploadfileById(pic.getPic8());
				if(ufvo8!=null){
					request.setAttribute("fixedpath8", ufvo8.getFile_path());
					request.setAttribute("picId8", ufvo8.getFile_name());
				}else{
					request.setAttribute("fixedpath8", null);
					request.setAttribute("picId8",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo9;
			try {
				ufvo9 = ufService.loadUploadfileById(pic.getPic9());
				if(ufvo9!=null){
					request.setAttribute("fixedpath9", ufvo9.getFile_path());
					request.setAttribute("picId9", ufvo9.getFile_name());
				}else{
					request.setAttribute("fixedpath9", null);
					request.setAttribute("picId9", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo10;
			try {
				ufvo10 = ufService.loadUploadfileById(pic.getPic10());
				if(ufvo10!=null){
					request.setAttribute("fixedpath10", ufvo10.getFile_path());
					request.setAttribute("picId10", ufvo10.getFile_name());
				}else{
					request.setAttribute("fixedpath10", null);
					request.setAttribute("picId10",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			handoverLogForm.setHpic(pic);
		}else{
			for(int i=1;i<=10;i++){
				request.setAttribute("fixedpath"+i, null);
				request.setAttribute("picId"+i, null);
			}
		}
		return mapping.findForward("handoverLogDetail");
	}
	public ActionForward jjsDownLoad(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		int id = handoverLogForm.getHandoverLog().getId();
		String path = CreatePDFUtil.workHandover(id, request);
		if(path==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),"没有交接书可下载");
			return this.handoverLogPageList(mapping, actionform, request, response);
		}
		request.setAttribute("path", path);
		request.setAttribute("fileName", "交接书");
		return mapping.findForward("down_upfile");
	}
	
	public ActionForward checkHandoverBookPicExists(ActionMapping mapping,ActionForm actionform,
			HttpServletRequest request,HttpServletResponse reponse){
		String id = request.getParameter("id");		
		int count = 0;
		if(StringUtil.isNumber(id)){
			HandoverLogPicVO pic = service.searchPicsByHandoverLogId(Integer.parseInt(id));
			if(pic.getPic1() > 0){
				count++;
			}
			if(pic.getPic2() > 0){
				count++;
			}
			if(pic.getPic3() > 0){
				count++;
			}
			if(pic.getPic4() > 0){
				count++;
			}
			if(pic.getPic5() > 0){
				count++;
			}
			if(pic.getPic6() > 0){
				count++;
			}
			if(pic.getPic7() > 0){
				count++;
			}
			if(pic.getPic8() > 0){
				count++;
			}
			if(pic.getPic9() > 0){
				count++;
			}
			if(pic.getPic10() > 0){
				count++;
			}
		}
		PrintWriter pw = null;
		try {
			 pw = getWrite(reponse);
			JSONObject jo = new JSONObject();
			jo.put("num", count);
			pw.write(jo.toJSONString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.flush();
				try {
					pw.close();
				} catch (Exception e) {
					pw.close();
				}
			}
		}
		
		
		
		return null;
	}
	
	public ActionForward upLoadHandoverBookPicEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		HandoverLogPicVO pic=service.searchPicsByHandoverLogId(handoverLogForm.getHandoverLog().getId());
		handoverLogForm.setHpic(pic);
		/*if(pic!=null){
			UploadfileVO ufvo1;
			try {
				ufvo1 = ufService.loadUploadfileById(pic.getPic1());
				if(ufvo1!=null){
					request.setAttribute("fixedpath1", ufvo1.getFile_path());
					request.setAttribute("picname1", ufvo1.getFile_name());
				}else{
					request.setAttribute("fixedpath1", null);
					request.setAttribute("picname1", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo2;
			try {
				ufvo2 = ufService.loadUploadfileById(pic.getPic2());
				if(ufvo2!=null){
					request.setAttribute("fixedpath2", ufvo2.getFile_path());
					request.setAttribute("picname2", ufvo2.getFile_name());
				}else{
					request.setAttribute("fixedpath2", null);
					request.setAttribute("picname2", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo3;
			try {
				ufvo3 = ufService.loadUploadfileById(pic.getPic3());
				if(ufvo3!=null){
					request.setAttribute("fixedpath3", ufvo3.getFile_path());
					request.setAttribute("picname3", ufvo3.getFile_name());
				}else{
					request.setAttribute("fixedpath3", null);
					request.setAttribute("picname3", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo4;
			try {
				ufvo4 = ufService.loadUploadfileById(pic.getPic4());
				if(ufvo4!=null){
					request.setAttribute("fixedpath4", ufvo4.getFile_path());
					request.setAttribute("picname4", ufvo4.getFile_name());
				}else{
					request.setAttribute("fixedpath4",null);
					request.setAttribute("picname4",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo5;
			try {
				ufvo5 = ufService.loadUploadfileById(pic.getPic5());
				if(ufvo5!=null){
					request.setAttribute("fixedpath5", ufvo5.getFile_path());
					request.setAttribute("picname5", ufvo5.getFile_name());
				}else{
					request.setAttribute("fixedpath5", null);
					request.setAttribute("picname5", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo6;
			try {
				ufvo6 = ufService.loadUploadfileById(pic.getPic6());
				if(ufvo6!=null){
					request.setAttribute("fixedpath6", ufvo6.getFile_path());
					request.setAttribute("picname6", ufvo6.getFile_name());
				}else{
					request.setAttribute("fixedpath6",null);
					request.setAttribute("picname6", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo7;
			try {
				ufvo7 = ufService.loadUploadfileById(pic.getPic7());
				if(ufvo7!=null){
					request.setAttribute("fixedpath7", ufvo7.getFile_path());
					request.setAttribute("picname7", ufvo7.getFile_name());
				}else{
					request.setAttribute("fixedpath7", null);
					request.setAttribute("picname7",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo8;
			try {
				ufvo8 = ufService.loadUploadfileById(pic.getPic8());
				if(ufvo8!=null){
					request.setAttribute("fixedpath8", ufvo8.getFile_path());
					request.setAttribute("picname8", ufvo8.getFile_name());
				}else{
					request.setAttribute("fixedpath8", null);
					request.setAttribute("picname8",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo9;
			try {
				ufvo9 = ufService.loadUploadfileById(pic.getPic9());
				if(ufvo9!=null){
					request.setAttribute("fixedpath9", ufvo9.getFile_path());
					request.setAttribute("picname9", ufvo9.getFile_name());
				}else{
					request.setAttribute("fixedpath9", null);
					request.setAttribute("picname9", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo10;
			try {
				ufvo10 = ufService.loadUploadfileById(pic.getPic10());
				if(ufvo10!=null){
					request.setAttribute("fixedpath10", ufvo10.getFile_path());
					request.setAttribute("picname10", ufvo10.getFile_name());
				}else{
					request.setAttribute("fixedpath10", null);
					request.setAttribute("picname10",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			handoverLogForm.setHpic(pic);
		}else{
			for(int i=1;i<=10;i++){
				request.setAttribute("fixedpath"+i, null);
				request.setAttribute("picname"+i, null);
			}
		}*/
		request.setAttribute("hpic",pic);
		request.setAttribute("isEdit", pic.getIsEdit());
		
		//返回新增页面
		return mapping.findForward("upload_handoverLogPic");
	}
	
	public ActionForward upLoadHandoverBookPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		//HandoverLogVO handoverLog=handoverLogForm.getHandoverLog();
		HandoverLogPicVO handoverLogPic = handoverLogForm.getHpic();
		HandoverLogPicVO hpic = service.loadHandoverLogPic(handoverLogPic.getId());
		hpic.setRemark(handoverLogPic.getRemark());
		UserSession user = UserSessionUtil.getUserSession(request);
		hpic.setCreateUser(user.getUser().getId());
		hpic.setCreateTime(new Date());
		//hpic.setHandoverLogId(handoverLog.getId());
//		hpic.setIsEdit(0);
		/*FormFile file1 = handoverLogForm.getPic1();
		if(file1 != null && StringUtils.isNotEmpty(file1.getFileName())){
			int ufid = UploadFileUtil.savefile(file1, UserSessionUtil.getUserSession(request), request);
			handoverLogPic.setPic1(ufid);
		}
		FormFile file2 = handoverLogForm.getPic2();
		if(file2 != null  && StringUtils.isNotEmpty(file2.getFileName())){
			int ufid = UploadFileUtil.savefile(file2, UserSessionUtil.getUserSession(request), request);
			handoverLogPic.setPic2(ufid);
		}
		FormFile file3 = handoverLogForm.getPic3();
		if(file3!= null  && StringUtils.isNotEmpty(file3.getFileName())){
			int ufid = UploadFileUtil.savefile(file3, UserSessionUtil.getUserSession(request), request);
			handoverLogPic.setPic3(ufid);
		}
		FormFile file4 = handoverLogForm.getPic4();
		if(file4 != null && StringUtils.isNotEmpty(file4.getFileName())){
			int ufid = UploadFileUtil.savefile(file4, UserSessionUtil.getUserSession(request), request);
			handoverLogPic.setPic4(ufid);
		}
		FormFile file5 = handoverLogForm.getPic5();
		if(file5 != null && StringUtils.isNotEmpty(file5.getFileName())){
			int ufid = UploadFileUtil.savefile(file5, UserSessionUtil.getUserSession(request), request);
			handoverLogPic.setPic5(ufid);
		}
		FormFile file6 = handoverLogForm.getPic6();
		if(file6 != null && StringUtils.isNotEmpty(file6.getFileName())){
			int ufid = UploadFileUtil.savefile(file6, UserSessionUtil.getUserSession(request), request);
			handoverLogPic.setPic6(ufid);
		}
		FormFile file7 = handoverLogForm.getPic7();
		if(file7 != null && StringUtils.isNotEmpty(file7.getFileName())){
			int ufid = UploadFileUtil.savefile(file7, UserSessionUtil.getUserSession(request), request);
			handoverLogPic.setPic7(ufid);
		}
		FormFile file8 = handoverLogForm.getPic8();
		if(file8 != null && StringUtils.isNotEmpty(file8.getFileName())){
			int ufid = UploadFileUtil.savefile(file8, UserSessionUtil.getUserSession(request), request);
			handoverLogPic.setPic8(ufid);
		}
		FormFile file9 = handoverLogForm.getPic9();
		if(file9 != null && StringUtils.isNotEmpty(file9.getFileName())){
			int ufid = UploadFileUtil.savefile(file9, UserSessionUtil.getUserSession(request), request);
			handoverLogPic.setPic9(ufid);
		}
		FormFile file10 = handoverLogForm.getPic10();
		if(file10 != null && StringUtils.isNotEmpty(file10.getFileName())){
			int ufid = UploadFileUtil.savefile(file10, UserSessionUtil.getUserSession(request), request);
			handoverLogPic.setPic10(ufid);
		}*/
		boolean flag=false;
		try {
			flag = service.updateHandoverLogPic(hpic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//修改成功返回列表页
//			return handoverLogPageList(mapping,form,request,response);
			return upLoadHandoverBookPicEntity(mapping,form,request,response);
		}else{
			//修改失败返回修改页面
//			return updHandoverLogEntity(mapping,form,request,response);
			return upLoadHandoverBookPicEntity(mapping,form,request,response);
		}
	}

	/**
	 * 提交图片，进入审批流程
	 * @param hpic
	 * @return
	 */
	public ActionForward updPicEditStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogPicVO hpic=handoverLogForm.getHpic();
		hpic.setApproveStatus(ApprovalContant.APPROVAL_WAIT.getCode());
		hpic.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
		//不可编辑
		hpic.setIsEdit(1);
		boolean flag=false;
		try {
			flag = service.updPicEditStatus(hpic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		//返回列表页
		return handoverLogPageList(mapping,handoverLogForm,request,response);
	}
	/**
	 * 提交交接记录，进入制作交接书流程
	 * @param hpic
	 * @return
	 */
	public ActionForward updHandoverLogEditStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogVO handoverLog=null;
		try {
			handoverLog = service.load(handoverLogForm.getHandoverLog().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		handoverLog.setApproveStatus(ApprovalContant.APPROVAL_WAIT.getCode());
		handoverLog.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
		//不可编辑
		handoverLog.setIsEdit(1);
		boolean flag=false;
		try {
			flag = service.updHandoverLogEditStatus(handoverLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		//返回列表页
		return handoverLogPageList(mapping,handoverLogForm,request,response);
	}
	

	/**
	 * 跳转到审批页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward picpreApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogPicVO pic=service.searchPicsByHandoverLogId(handoverLogForm.getHandoverLog().getId());
		if(pic==null){
			pic=service.loadHandoverLogPic(handoverLogForm.getHpic().getId());
		}
		handoverLogForm.setHpic(pic);
		if(pic!=null){
			UploadfileVO ufvo1;
			try {
				ufvo1 = ufService.loadUploadfileById(pic.getPic1());
				if(ufvo1!=null){
					request.setAttribute("fixedpath1", ufvo1.getFile_path());
					request.setAttribute("picId1", ufvo1.getId());
				}else{
					request.setAttribute("fixedpath1", null);
					request.setAttribute("picId1", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo2;
			try {
				ufvo2 = ufService.loadUploadfileById(pic.getPic2());
				if(ufvo2!=null){
					request.setAttribute("fixedpath2", ufvo2.getFile_path());
					request.setAttribute("picId2", ufvo2.getFile_name());
				}else{
					request.setAttribute("fixedpath2", null);
					request.setAttribute("picId2", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo3;
			try {
				ufvo3 = ufService.loadUploadfileById(pic.getPic3());
				if(ufvo3!=null){
					request.setAttribute("fixedpath3", ufvo3.getFile_path());
					request.setAttribute("picId3", ufvo3.getFile_name());
				}else{
					request.setAttribute("fixedpath3", null);
					request.setAttribute("picId3", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo4;
			try {
				ufvo4 = ufService.loadUploadfileById(pic.getPic4());
				if(ufvo4!=null){
					request.setAttribute("fixedpath4", ufvo4.getFile_path());
					request.setAttribute("picId4", ufvo4.getFile_name());
				}else{
					request.setAttribute("fixedpath4",null);
					request.setAttribute("picId4",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo5;
			try {
				ufvo5 = ufService.loadUploadfileById(pic.getPic5());
				if(ufvo5!=null){
					request.setAttribute("fixedpath5", ufvo5.getFile_path());
					request.setAttribute("picId5", ufvo5.getFile_name());
				}else{
					request.setAttribute("fixedpath5", null);
					request.setAttribute("picId5", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo6;
			try {
				ufvo6 = ufService.loadUploadfileById(pic.getPic6());
				if(ufvo6!=null){
					request.setAttribute("fixedpath6", ufvo6.getFile_path());
					request.setAttribute("picId6", ufvo6.getFile_name());
				}else{
					request.setAttribute("fixedpath6",null);
					request.setAttribute("picId6", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo7;
			try {
				ufvo7 = ufService.loadUploadfileById(pic.getPic7());
				if(ufvo7!=null){
					request.setAttribute("fixedpath7", ufvo7.getFile_path());
					request.setAttribute("picId7", ufvo7.getFile_name());
				}else{
					request.setAttribute("fixedpath7", null);
					request.setAttribute("picId7",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo8;
			try {
				ufvo8 = ufService.loadUploadfileById(pic.getPic8());
				if(ufvo8!=null){
					request.setAttribute("fixedpath8", ufvo8.getFile_path());
					request.setAttribute("picId8", ufvo8.getFile_name());
				}else{
					request.setAttribute("fixedpath8", null);
					request.setAttribute("picId8",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo9;
			try {
				ufvo9 = ufService.loadUploadfileById(pic.getPic9());
				if(ufvo9!=null){
					request.setAttribute("fixedpath9", ufvo9.getFile_path());
					request.setAttribute("picId9", ufvo9.getFile_name());
				}else{
					request.setAttribute("fixedpath9", null);
					request.setAttribute("picId9", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo10;
			try {
				ufvo10 = ufService.loadUploadfileById(pic.getPic10());
				if(ufvo10!=null){
					request.setAttribute("fixedpath10", ufvo10.getFile_path());
					request.setAttribute("picId10", ufvo10.getFile_name());
				}else{
					request.setAttribute("fixedpath10", null);
					request.setAttribute("picId10",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			handoverLogForm.setHpic(pic);
		}else{
			for(int i=1;i<=10;i++){
				request.setAttribute("fixedpath"+i, null);
				request.setAttribute("picId"+i, null);
			}
		}
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(pic.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.HANDOVERLOG.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		setOptions(request);
		return mapping.findForward("handoverBook_approval");
	}
	/**
	 *	交接书审批页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward preApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		int id = handoverLogForm.getHandoverLog().getId();
		HandoverLogVO handoverLog=null;
		HandoverBookVO book=null;
		try {
			handoverLog = service.load(id);
			if(handoverLog!=null){
				getHandoverBook(handoverLog,request);
				book=service.getHandoverBookBySupervisorId(handoverLog.getDeliverer());
				request.setAttribute("draftOptions", OptionUtil.getDraftOptionsByDealerId(handoverLog.getDealerId()));
			}else{
				book=new HandoverBookVO();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		handoverLogForm.setHandoverLog(handoverLog);
		handoverLogForm.setHandoverBook(book);
		handoverLogForm.seteText(book.geteText());
		handoverLogForm.setpText(book.getpText());
		handoverLogForm.setOData(book.getOData());
		handoverLogForm.setOfficeEquipment(book.getOfficeEquipment());
		setOptions(request);
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(id);
		approvalQuery.setObjectType(ApprovalTypeContant.HANDOVERLOG.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		
		HandoverLogPicVO pic=service.searchPicsByHandoverLogId(id);
		if(pic==null){
			pic=service.loadHandoverLogPic(handoverLogForm.getHpic().getId());
		}
		handoverLogForm.setHpic(pic);
		if(pic!=null){
			UploadfileVO ufvo1;
			try {
				ufvo1 = ufService.loadUploadfileById(pic.getPic1());
				if(ufvo1!=null){
					request.setAttribute("fixedpath1", ufvo1.getFile_path());
					request.setAttribute("picId1", ufvo1.getId());
				}else{
					request.setAttribute("fixedpath1", null);
					request.setAttribute("picId1", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo2;
			try {
				ufvo2 = ufService.loadUploadfileById(pic.getPic2());
				if(ufvo2!=null){
					request.setAttribute("fixedpath2", ufvo2.getFile_path());
					request.setAttribute("picId2", ufvo2.getFile_name());
				}else{
					request.setAttribute("fixedpath2", null);
					request.setAttribute("picId2", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo3;
			try {
				ufvo3 = ufService.loadUploadfileById(pic.getPic3());
				if(ufvo3!=null){
					request.setAttribute("fixedpath3", ufvo3.getFile_path());
					request.setAttribute("picId3", ufvo3.getFile_name());
				}else{
					request.setAttribute("fixedpath3", null);
					request.setAttribute("picId3", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo4;
			try {
				ufvo4 = ufService.loadUploadfileById(pic.getPic4());
				if(ufvo4!=null){
					request.setAttribute("fixedpath4", ufvo4.getFile_path());
					request.setAttribute("picId4", ufvo4.getFile_name());
				}else{
					request.setAttribute("fixedpath4",null);
					request.setAttribute("picId4",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo5;
			try {
				ufvo5 = ufService.loadUploadfileById(pic.getPic5());
				if(ufvo5!=null){
					request.setAttribute("fixedpath5", ufvo5.getFile_path());
					request.setAttribute("picId5", ufvo5.getFile_name());
				}else{
					request.setAttribute("fixedpath5", null);
					request.setAttribute("picId5", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo6;
			try {
				ufvo6 = ufService.loadUploadfileById(pic.getPic6());
				if(ufvo6!=null){
					request.setAttribute("fixedpath6", ufvo6.getFile_path());
					request.setAttribute("picId6", ufvo6.getFile_name());
				}else{
					request.setAttribute("fixedpath6",null);
					request.setAttribute("picId6", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo7;
			try {
				ufvo7 = ufService.loadUploadfileById(pic.getPic7());
				if(ufvo7!=null){
					request.setAttribute("fixedpath7", ufvo7.getFile_path());
					request.setAttribute("picId7", ufvo7.getFile_name());
				}else{
					request.setAttribute("fixedpath7", null);
					request.setAttribute("picId7",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			UploadfileVO ufvo8;
			try {
				ufvo8 = ufService.loadUploadfileById(pic.getPic8());
				if(ufvo8!=null){
					request.setAttribute("fixedpath8", ufvo8.getFile_path());
					request.setAttribute("picId8", ufvo8.getFile_name());
				}else{
					request.setAttribute("fixedpath8", null);
					request.setAttribute("picId8",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo9;
			try {
				ufvo9 = ufService.loadUploadfileById(pic.getPic9());
				if(ufvo9!=null){
					request.setAttribute("fixedpath9", ufvo9.getFile_path());
					request.setAttribute("picId9", ufvo9.getFile_name());
				}else{
					request.setAttribute("fixedpath9", null);
					request.setAttribute("picId9", null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			UploadfileVO ufvo10;
			try {
				ufvo10 = ufService.loadUploadfileById(pic.getPic10());
				if(ufvo10!=null){
					request.setAttribute("fixedpath10", ufvo10.getFile_path());
					request.setAttribute("picId10", ufvo10.getFile_name());
				}else{
					request.setAttribute("fixedpath10", null);
					request.setAttribute("picId10",null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			handoverLogForm.setHpic(pic);
		}else{
			for(int i=1;i<=10;i++){
				request.setAttribute("fixedpath"+i, null);
				request.setAttribute("picId"+i, null);
			}
		}
		
		return mapping.findForward("handoverBook_approval");
	}
	/**
	 * 流程控制
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward approval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogQueryVO query = handoverLogForm.getQuery();
		query.setCurrentRole(currRole);
	
		UserSession user = UserSessionUtil.getUserSession(request);
		boolean flag=false;
		flag=service.approval(query, user);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			return handoverLogPageList(mapping,handoverLogForm,request,response);
		}else{
			handoverLogForm.getHpic().setId(query.getId());;
			return preApproval(mapping,handoverLogForm,request,response);
		}
	}
	
	public ActionForward updHandoverBook(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		int id = handoverLogForm.getHandoverLog().getId();
		HandoverLogVO handoverLog=null;
		HandoverBookVO book=null;
		try {
			handoverLog = service.load(id);
			if(handoverLog!=null){
				getHandoverBook(handoverLog,request);
				book=service.getHandoverBookBySupervisorId(handoverLog.getDeliverer());
				request.setAttribute("draftOptions", OptionUtil.getDraftOptionsByDealerId(handoverLog.getDealerId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		handoverLogForm.setHandoverLog(handoverLog);
		handoverLogForm.setHandoverBook(book);
		handoverLogForm.seteText(book.geteText());
		handoverLogForm.setpText(book.getpText());
		handoverLogForm.setOData(book.getOData());
		handoverLogForm.setOfficeEquipment(book.getOfficeEquipment());
		setOptions(request);
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		
		return mapping.findForward("upd_handoverBook");
	}
	
	public HttpServletRequest  getHandoverBook(HandoverLogVO handoverLog,HttpServletRequest request){
		//经销商
		DealerService dealerService = new DealerService();
		/*DealerVO dealer = dealerService.get(handoverLog.getDealerId());
		if(dealer==null){
			dealer=new DealerVO();
		}
		*/
		//合作银行
		BankVO bank=null;
		try {
			bank = dealerService.getBankByDealerId(handoverLog.getDealerId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//汇票
		DraftService dservice = new DraftService();
		DraftQueryVO dquery = new DraftQueryVO();
		dquery.setDistribid(handoverLog.getDealerId());
		List<DraftVO> dList = dservice.searchDraftList(dquery);
		if(dList==null){
			dList=new ArrayList<DraftVO>();
		}
		
		SuperviseImportQueryVO siQuery=new SuperviseImportQueryVO();
		
		//在库车辆
		double zkMoney=0;
		List<SuperviseImportVO> zkcls=superviseImportService.findListByDealerIdAndState(handoverLog.getDealerId(),SuperVisoryStatusContants.IN_STORE.getCode());//入库
		if(zkcls!=null){
			for(SuperviseImportVO zkcl : zkcls){
				if(zkcl!=null){
					zkMoney=sum(zkMoney,Double.parseDouble(zkcl.getMoney()));
				}
			}
			BigDecimal db = new BigDecimal(Double.toString(zkMoney));
			String zkMoneyStr = db.toPlainString();
			request.setAttribute("zkMoney", zkMoneyStr);//在库车辆合计金额
			request.setAttribute("zkCount", zkcls.size());//在库车辆合计数量
			request.setAttribute("inStockCar", zkcls);//在库车辆信息
		}
		
		//在途车辆
		double ztMoney=0;

		List<SuperviseImportVO> ztcls=superviseImportService.findListByDealerIdAndState(handoverLog.getDealerId(), SuperVisoryStatusContants.ON_WAY.getCode());//在途
		if(ztcls!=null){
			for(SuperviseImportVO ztcl : ztcls){
				if(ztcl!=null){
					ztMoney=sum(ztMoney,Double.parseDouble(ztcl.getMoney()));
				}
			}
			BigDecimal bd = new BigDecimal(Double.toString(ztMoney));
			String ztMoneyStr = bd.toPlainString();
			request.setAttribute("ztMoney", ztMoneyStr);
			request.setAttribute("ztCount", ztcls.size());
			request.setAttribute("onWayCar", ztcls);
		}
		
		//销售未还款车辆
		List<SuperviseImportVO> xswhkcars=new ArrayList<SuperviseImportVO>();
		List<SuperviseImportVO> whkcars=superviseImportService.findListByDealerIdAndState(handoverLog.getDealerId(), SuperVisoryStatusContants.SALE_NO_PAY.getCode());
		if(whkcars!=null)
			for(SuperviseImportVO svo : whkcars){
				if(StringUtil.isEmpty(svo.getPayment_amount())){
					xswhkcars.add(svo);
				}
			}
		request.setAttribute("sellNoPayCar", xswhkcars);
		
		//私自移动车辆
		List<SuperviseImportVO> privateMoveCar=superviseImportService.findListByDealerIdAndState(handoverLog.getDealerId(), SuperVisoryStatusContants.PRIVATE_MOVE.getCode());
		request.setAttribute("privateMoveCar", privateMoveCar);
		//私自售卖车辆
		List<SuperviseImportVO> privateSellCar=superviseImportService.findListByDealerIdAndState(handoverLog.getDealerId(), SuperVisoryStatusContants.PRIVATE_SALE.getCode());
		request.setAttribute("privateSellCar", privateSellCar);
		//未结清汇票
		List<DraftVO> wjqdrafts = new ArrayList<DraftVO>();
		if(dList!=null && dList.size()>0){
			int i=0;
			for(DraftVO dvo : dList){
				double billing_money = 0;
				//回款金额
				double payment_money = 0;
				//已押车金额
				double mortgagecar_money = 0;
				if(dvo.getBilling_money()!=null){
					billing_money = Double.parseDouble(dvo.getBilling_money());
				}
				//合计已押车金额和回款金额
				siQuery.setDraft_num(dvo.getDraft_num());
				List<SuperviseImportVO> wjqdraftscars=superviseImportService.searchSuperviseImportList(siQuery);
				if(wjqdraftscars!=null && wjqdraftscars.size()>0){
					for(SuperviseImportVO svo : wjqdraftscars){
						if(svo!=null){
							if(StringUtils.isNotEmpty(svo.getPayment_amount())){
								payment_money+=Double.parseDouble(svo.getPayment_amount());
							}
							if(svo.getMoney()!=null){
								mortgagecar_money+=Double.parseDouble(svo.getMoney());
							}
						}
					}
					dvo.setPayment_money(String.valueOf(payment_money));
					dvo.setMortgagecar_money(String.valueOf(mortgagecar_money));
				}
				if(dvo.getPayment_money()==null){
					dvo.setPayment_money("0");
				}
				if(dvo.getMortgagecar_money()==null){
					dvo.setMortgagecar_money("0");
				}
				if((billing_money - payment_money) > 0){
					wjqdrafts.add(dvo);
				}
			}
		}
		request.setAttribute("wjqdrafts", wjqdrafts);
		
		FixedAssetsService fixedAssetsService=new FixedAssetsService();
		FixedAssetsQueryVO query=new FixedAssetsQueryVO();
		query.setSendee(handoverLog.getDeliverer());
		query.setAsset_type(AssetsTypeContant.ELECTRONICS.getCode());
		//电脑
		List<FixedAssetsVO> computers=fixedAssetsService.searchFixedAssets(query);
		FixedAssetsVO dn=null;
		if(computers!=null && computers.size()>0){
			 dn= computers.get(0);
		}
		request.setAttribute("dn", dn);
		//保险柜
		query.setAsset_type(AssetsTypeContant.WORK.getCode());
		List<FixedAssetsVO> safetyBox=fixedAssetsService.searchFixedAssets(query);
		FixedAssetsVO bxg =null;
		if(safetyBox!=null && safetyBox.size()>0){
			bxg= safetyBox.get(0);
		}
		request.setAttribute("bxg", bxg);
		return request;
	}
	public ActionForward addSuperviseImportEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		setOptions(request);
		int id = handoverLogForm.getHandoverLog().getId();
		HandoverLogVO handoverLog;
		try {
			handoverLog = service.load(id);
			if(handoverLog!=null){
				request.setAttribute("draftOptions", OptionUtil.getDraftOptionsByDealerId(handoverLog.getDealerId()));
			}else{
				request.setAttribute("draftOptions", new ArrayList<OptionObject>());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("superviseImportStatus",handoverLogForm.getSuperviseImport().getState());
		return mapping.findForward("add_supervise_import");
	}
	public ActionForward addSuperviseImport(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		SuperviseImportService service = new SuperviseImportService();
		DraftService ds = new DraftService();
		int draftid = handoverLogForm.getDraftid();
		DraftVO draft;
		String dn = "";
		try {
			draft = ds.loadDraftById(draftid);
			if(draft != null){
				dn = draft.getDraft_num();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		SuperviseImportVO superviseImport=handoverLogForm.getSuperviseImport();
		superviseImport.setDraft_num(dn);
		//执行新增操作并获取操作结果
		boolean flag=false;
		try {
			flag = superviseImportService.addSuperviseImport(superviseImport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		handoverLogForm.setHandoverLog(handoverLogForm.getHandoverLog());
		if(flag){
			//新增成功时返回列表页面
			return updHandoverBook(mapping, actionform, request, response);
		}
		return addSuperviseImportEntity(mapping, actionform, request, response);
	}
	public ActionForward updSuperviseImportEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		int id = handoverLogForm.getHandoverLog().getId();
		HandoverLogVO handoverLog;
		try {
			handoverLog = service.load(id);
			if(handoverLog!=null){
				request.setAttribute("draftOptions", OptionUtil.getDraftOptionsByDealerId(handoverLog.getDealerId()));
				handoverLogForm.setHandoverLog(handoverLog);
			}else{
				request.setAttribute("draftOptions", new ArrayList<OptionObject>());
				handoverLogForm.setHandoverLog(new HandoverLogVO());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("superviseImportStatus",handoverLogForm.getSuperviseImport().getState());
		try {
			SuperviseImportVO superviseImport=superviseImportService.loadSuperviseImportById(handoverLogForm.getSuperviseImport().getId());
			//对象不存在时返回列表页
			if(superviseImport==null){
				request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
				return updHandoverBook(mapping, actionform, request, response);
			}
			DraftService ds = new DraftService();
			DraftQueryVO dq = new DraftQueryVO();
			dq.setDraft_num(superviseImport.getDraft_num());
			List<DraftVO> dList = ds.searchDraftList(dq);
			if(dList != null && dList.size()>0){
				DraftVO draft = dList.get(0);
				superviseImport.setDraft_num(draft.getId()+"");
			}
			handoverLogForm.setSuperviseImport(superviseImport);
			handoverLogForm.setDraftid(StringUtil.intValue(superviseImport.getDraft_num(), -1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setOptions(request);
		return mapping.findForward("upd_supervise_import");
	}
	public ActionForward updSuperviseImport(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		int draftid=handoverLogForm.getDraftid();
		SuperviseImportVO superviseImport=handoverLogForm.getSuperviseImport();
		DraftService ds = new DraftService();
		DraftVO draft;
		String dn = "";
		try {
			draft = ds.loadDraftById(draftid);
			if(draft != null){
				dn = draft.getDraft_num();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		superviseImport.setDraft_num(dn);
		//执行修改操作并获取操作结果
		boolean flag=false;
		try {
			flag = superviseImportService.updSuperviseImport(superviseImport, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//修改成功时返回列表页面
			return updHandoverBook(mapping, actionform, request, response);
		}
		return updSuperviseImportEntity(mapping, actionform, request, response);
	}
	public ActionForward delSuperviseImport(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		//执行修改操作并获取操作结果
		boolean flag=false;
		try {
			flag = superviseImportService.deleteSuperviseImport(handoverLogForm.getSuperviseImport().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return updHandoverBook(mapping, actionform, request, response);
	}
	public ActionForward updElectronicText(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		ElectronicTextVO eText=handoverLogForm.geteText();
		eText.setRemark(java.net.URLDecoder.decode(java.net.URLDecoder.decode(eText.getRemark(),"UTF-8"),"UTF-8"));
		eText.setElectronBill(java.net.URLDecoder.decode(java.net.URLDecoder.decode(eText.getElectronBill(),"UTF-8"),"UTF-8"));
		//执行修改操作并获取操作结果
		boolean flag=false;
		try {
			flag = service.updElectronicText(eText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return null;
//		return updHandoverBook(mapping, actionform, request, response);
	}
	public ActionForward updOfficeEquipment(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		OfficeEquipmentVO officeEquipment=handoverLogForm.getOfficeEquipment();
		officeEquipment.setComputerPropertyReason(java.net.URLDecoder.decode(java.net.URLDecoder.decode(officeEquipment.getComputerPropertyReason(),"UTF-8"),"UTF-8"));
		officeEquipment.setComputerPassword(java.net.URLDecoder.decode(java.net.URLDecoder.decode(officeEquipment.getComputerPassword(),"UTF-8"),"UTF-8"));
		officeEquipment.setSafetyBoxPropertyReason(java.net.URLDecoder.decode(java.net.URLDecoder.decode(officeEquipment.getSafetyBoxPropertyReason(),"UTF-8"),"UTF-8"));
		officeEquipment.setSafetyBoxPassword(java.net.URLDecoder.decode(java.net.URLDecoder.decode(officeEquipment.getSafetyBoxPassword(),"UTF-8"),"UTF-8"));
		officeEquipment.setSituationExplain(java.net.URLDecoder.decode(java.net.URLDecoder.decode(officeEquipment.getSituationExplain(),"UTF-8"),"UTF-8"));
		officeEquipment.setQqNumber(java.net.URLDecoder.decode(java.net.URLDecoder.decode(officeEquipment.getQqNumber(),"UTF-8"),"UTF-8"));
		officeEquipment.setQqPassword(java.net.URLDecoder.decode(java.net.URLDecoder.decode(officeEquipment.getQqPassword(),"UTF-8"),"UTF-8"));
		officeEquipment.setMoneyRemark(java.net.URLDecoder.decode(java.net.URLDecoder.decode(officeEquipment.getMoneyRemark(),"UTF-8"),"UTF-8"));
		officeEquipment.setMerchantRemark(java.net.URLDecoder.decode(java.net.URLDecoder.decode(officeEquipment.getMerchantRemark(),"UTF-8"),"UTF-8"));
		//执行修改操作并获取操作结果
		boolean flag=false;
		try {
			flag = service.updOfficeEquipment(officeEquipment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return null;
//		return updHandoverBook(mapping, actionform, request, response);
	}
	public ActionForward updOtherData(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		OtherDataVO OData=handoverLogForm.getOData();
		OData.setSituationExplain(java.net.URLDecoder.decode(java.net.URLDecoder.decode(OData.getSituationExplain(),"UTF-8"),"UTF-8"));
		OData.setSpecialOperation(java.net.URLDecoder.decode(java.net.URLDecoder.decode(OData.getSpecialOperation(),"UTF-8"),"UTF-8"));
		//执行修改操作并获取操作结果
		boolean flag=false;
		try {
			flag = service.updOtherData(OData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return null;
//		return updHandoverBook(mapping, actionform, request, response);
	}
	public ActionForward updPaperText(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
		PaperTextVO pText=handoverLogForm.getpText();
		//执行修改操作并获取操作结果
		boolean flag=false;
		try {
			flag = service.updPaperText(pText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return null;
//		return updHandoverBook(mapping, actionform, request, response);
	}
	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		HandoverLogQueryVO query = handoverLogForm.getQuery();
		//按条件查询分页数据
		List<HandoverLogVO> list = service.searchHandoverLogList(query);
		IExportFile export = new ExportFileExcelImpl();
		try {
			export.export(list, new HandoverLogRowMapper(), export.createDefaultFileName("交接记录表"),"交接记录表", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回列表页面
		return null;
	}
	
	 public static double sum(double d1,double d2){ 
	        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
	        BigDecimal bd2 = new BigDecimal(Double.toString(d2)); 
	        return bd1.add(bd2).doubleValue(); 
	    } 
	 
	 
	 /*
	  * 交接记录管理台账 -- 需求59
	  * @time 20170516
	  * 
	 */
	 public ActionForward HandoverRecordManagementLedgerList(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
		 	HandoverLogForm handoverLogForm =(HandoverLogForm) form;
			HandoverLogQueryVO handoverLogQuery=handoverLogForm.getQuery();
			int currRole = getCurrRole(request);
			handoverLogQuery.setPageType(2);
			int pageType=handoverLogQuery.getPageType();
			//初始化分页查询工具
			IThumbPageTools tools = ToolsFactory.getThumbPageTools("HandoverRecordManagementLedgerList", request);
			//记录查询条件,用于查询条件变更时返回第一页
			tools.saveQueryVO(handoverLogQuery);
			handoverLogQuery.setPageType(pageType);
			handoverLogQuery.setCurrentRole(currRole);
			if(currRole==RoleConstants.SUPERVISORY.getCode()){
				handoverLogQuery.setCurrentRepositoryId(UserSessionUtil.getUserSession(request).getUser().getClient_id());
			}
			//按条件查询分页数据
			List<HandoverLogVO> handoverLogs = service.searchHandoverLogListByPage(handoverLogQuery, tools);
			List<HandoverLogListVO> list=null;
			if(handoverLogs!=null&&handoverLogs.size()>0){
				list=new ArrayList<HandoverLogListVO>();
				for(HandoverLogVO handoverLog:handoverLogs){
					HandoverLogListVO h=new HandoverLogListVO();
					BeanUtils.copyProperties(handoverLog, h);
					h.setApproveStatusStr(ApprovalContant.getByCode(handoverLog.getApproveStatus()).getName());
					h.setNextApprovalStr(RoleConstants.getName(handoverLog.getNextApproval()));
					HandoverLogPicVO pic=service.searchPicsByHandoverLogId(handoverLog.getId());
					if(pic!=null){
						h.setPicIsEdit(pic.getIsEdit());
						h.setPicId(pic.getId());
					}
					list.add(h);
				}
			}
			
			//将查询结果设置request中，用于页面显示
			request.setAttribute("list", list);
			request.setAttribute("dealers", OptionUtil.getDealers());
			request.setAttribute("supervisors", OptionUtil.getAllSupervise());
			setOptions(request);
			request.setAttribute("role", getCurrRole(request));
			return mapping.findForward("HandoverRecordManagementLedgerList");
	 }
	 
	 /*
	  * 导出交接记录管理台账 -- 需求59
	  * @time 20170517
	 */
	 public ActionForward extHandoverRecordManagementLedgerExcel(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
		 HandoverLogForm handoverLogForm =(HandoverLogForm) form;
		 HandoverLogQueryVO handoverLogQuery=handoverLogForm.getQuery();
		 int currRole = getCurrRole(request);
		 handoverLogQuery.setCurrentRole(currRole);
		 handoverLogQuery.setPageType(2);
		 if(currRole==RoleConstants.SUPERVISORY.getCode()){
			handoverLogQuery.setCurrentRepositoryId(UserSessionUtil.getUserSession(request).getUser().getClient_id());
		 }
		 
		 String[] titles = {"序号","经销商名称","交付人姓名","交付人交付性质","交付人离岗时间",
				 "接收人姓名","接收人接收性质","接收人交接后属性","接收人上岗时间","交接时间","完成时间",
				 "创建人","创建时间","最后更新人","最后更新时间","审批状态","下一审批人"};
		 String filename = "交接记录管理台账";
		 
		 List<ExtHandoverLogVO> list = service.ExtHandoverLog(handoverLogQuery);
		 SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		 ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		 //合并第一行
		 tool.setTitle(titles, 0);
		 tool.setRowHeight(0, 25);
		 for (int i = 0; list != null && i < list.size(); i++){
			ExtHandoverLogVO vo = list.get(i);
			tool.setCellValue(i+1, 0, i+1);
			
			tool.setCellValue(i+1, 1, vo.getDealerNameNTT());
			
	    	tool.setCellValue(i+1, 2, vo.getDelivererNTT());
	    	
	    	if (vo.getHandoverNature() == 1) {
	    		tool.setCellValue(i+1, 3,"辞职");
			}else if(vo.getHandoverNature() == 2){
				tool.setCellValue(i+1, 3,"辞退");
			}else if(vo.getHandoverNature() == 3){
				tool.setCellValue(i+1, 3,"正常轮岗");
			}else if(vo.getHandoverNature() == 4){
				tool.setCellValue(i+1, 3,"投诉轮岗");
			}
	    	
	    	if (vo.getDimissionDate() !=null) {
				String dimissionDate = format.format(vo.getDimissionDate());
				tool.setCellValue(i+1, 4, dimissionDate);
			}else{
				tool.setCellValue(i+1, 4, "");
			}
	    	
	    	tool.setCellValue(i+1, 5, vo.getReceiverNTT());
	    	
	    	if (vo.getReceiveNature() == 1) {
	    		tool.setCellValue(i+1, 6, "轮岗");
			}else if (vo.getReceiveNature() == 2) {
	    		tool.setCellValue(i+1, 6, "新入职");
			}else if (vo.getReceiveNature() == 3) {
	    		tool.setCellValue(i+1, 6, "二次上岗");
			}
	    	
	    	if (vo.getAfterHandoverNature() == 1) {
	    		tool.setCellValue(i+1, 7, "属地");
			}else if (vo.getAfterHandoverNature() == 2) {
	    		tool.setCellValue(i+1, 7, "外派");
			}
	    	
	    	if (vo.getMissionDate() !=null) {
				String missionDate = format.format(vo.getMissionDate());
				tool.setCellValue(i+1, 8, missionDate);
			}else{
				tool.setCellValue(i+1, 8, "");
			}
	    	
	    	if (vo.getHandoverDate() !=null) {
	    		String handoverDate = format.format(vo.getHandoverDate());
				tool.setCellValue(i+1, 9, handoverDate);
			}else{
				tool.setCellValue(i+1, 9, "");
			}
	    	
	    	if (vo.getFinishTime() !=null) {
	    		String finishTime = format.format(vo.getFinishTime());
				tool.setCellValue(i+1, 10, finishTime);
			}else{
				tool.setCellValue(i+1, 10, "");
			}
	    	
	    	tool.setCellValue(i+1, 11, vo.getCreateUserNTT());
	    	
	    	if (vo.getCreateTime() !=null) {
	    		String createTime = format.format(vo.getCreateTime());
				tool.setCellValue(i+1, 12, createTime);
			}else{
				tool.setCellValue(i+1, 12, "");
			}
	    	
	    	tool.setCellValue(i+1, 13, vo.getLastModifyUserNTT());
	    	
	    	if (vo.getLastModifyTime() !=null) {
	    		String lastModifyTime = format.format(vo.getLastModifyTime());
				tool.setCellValue(i+1, 14, lastModifyTime);
			}else{
				tool.setCellValue(i+1, 14, "");
			}
	    	
	    	if (vo.getApproveStatus() == 1) {
	    		tool.setCellValue(i+1, 15, "审批不通过");
			}else if (vo.getApproveStatus() == 2) {
	    		tool.setCellValue(i+1, 15, "审批通过");
			}
	    	
	    	tool.setCellValue(i+1, 16, vo.getNextApprovalNTT());
	    	
	    	
			tool.allAutoColumnWidth(i);
		 }
		 tool.writeStream(response, filename);
		 return null;
		 
	 }
	 /*
	  * 需求59 交接记录管理台账详情
	 */
	 public ActionForward HandoverRecordManagementLedgerDetail(ActionMapping mapping, ActionForm actionform,
				HttpServletRequest request, HttpServletResponse response) throws Exception{
			HandoverLogForm handoverLogForm =(HandoverLogForm) actionform;
			int id = handoverLogForm.getHandoverLog().getId();
			HandoverLogVO handoverLog=null;
			HandoverBookVO book=null;
			try {
				handoverLog = service.load(id);
				if(handoverLog!=null){
					getHandoverBook(handoverLog,request);
					book=service.getHandoverBookBySupervisorId(handoverLog.getDeliverer());
					request.setAttribute("draftOptions", OptionUtil.getDraftOptionsByDealerId(handoverLog.getDealerId()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			handoverLogForm.setHandoverLog(handoverLog);
			handoverLogForm.setHandoverBook(book);
			handoverLogForm.seteText(book.geteText());
			handoverLogForm.setpText(book.getpText());
			handoverLogForm.setOData(book.getOData());
			handoverLogForm.setOfficeEquipment(book.getOfficeEquipment());
			setOptions(request);
			request.setAttribute("dealers", OptionUtil.getDealers());
			request.setAttribute("supervisors", OptionUtil.getAllSupervise());
			MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
			approvalQuery.setObjectId(id);
			approvalQuery.setObjectType(ApprovalTypeContant.HANDOVERLOG.getCode());
			request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
			HandoverLogPicVO pic=service.searchPicsByHandoverLogId(id);
			if(pic==null){
				pic=service.loadHandoverLogPic(handoverLogForm.getHpic().getId());
			}
			handoverLogForm.setHpic(pic);
			if(pic!=null){
				UploadfileVO ufvo1;
				try {
					ufvo1 = ufService.loadUploadfileById(pic.getPic1());
					if(ufvo1!=null){
						request.setAttribute("fixedpath1", ufvo1.getFile_path());
						request.setAttribute("picId1", ufvo1.getId());
					}else{
						request.setAttribute("fixedpath1", null);
						request.setAttribute("picId1", null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				UploadfileVO ufvo2;
				try {
					ufvo2 = ufService.loadUploadfileById(pic.getPic2());
					if(ufvo2!=null){
						request.setAttribute("fixedpath2", ufvo2.getFile_path());
						request.setAttribute("picId2", ufvo2.getFile_name());
					}else{
						request.setAttribute("fixedpath2", null);
						request.setAttribute("picId2", null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				UploadfileVO ufvo3;
				try {
					ufvo3 = ufService.loadUploadfileById(pic.getPic3());
					if(ufvo3!=null){
						request.setAttribute("fixedpath3", ufvo3.getFile_path());
						request.setAttribute("picId3", ufvo3.getFile_name());
					}else{
						request.setAttribute("fixedpath3", null);
						request.setAttribute("picId3", null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				UploadfileVO ufvo4;
				try {
					ufvo4 = ufService.loadUploadfileById(pic.getPic4());
					if(ufvo4!=null){
						request.setAttribute("fixedpath4", ufvo4.getFile_path());
						request.setAttribute("picId4", ufvo4.getFile_name());
					}else{
						request.setAttribute("fixedpath4",null);
						request.setAttribute("picId4",null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				UploadfileVO ufvo5;
				try {
					ufvo5 = ufService.loadUploadfileById(pic.getPic5());
					if(ufvo5!=null){
						request.setAttribute("fixedpath5", ufvo5.getFile_path());
						request.setAttribute("picId5", ufvo5.getFile_name());
					}else{
						request.setAttribute("fixedpath5", null);
						request.setAttribute("picId5", null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				UploadfileVO ufvo6;
				try {
					ufvo6 = ufService.loadUploadfileById(pic.getPic6());
					if(ufvo6!=null){
						request.setAttribute("fixedpath6", ufvo6.getFile_path());
						request.setAttribute("picId6", ufvo6.getFile_name());
					}else{
						request.setAttribute("fixedpath6",null);
						request.setAttribute("picId6", null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				UploadfileVO ufvo7;
				try {
					ufvo7 = ufService.loadUploadfileById(pic.getPic7());
					if(ufvo7!=null){
						request.setAttribute("fixedpath7", ufvo7.getFile_path());
						request.setAttribute("picId7", ufvo7.getFile_name());
					}else{
						request.setAttribute("fixedpath7", null);
						request.setAttribute("picId7",null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				UploadfileVO ufvo8;
				try {
					ufvo8 = ufService.loadUploadfileById(pic.getPic8());
					if(ufvo8!=null){
						request.setAttribute("fixedpath8", ufvo8.getFile_path());
						request.setAttribute("picId8", ufvo8.getFile_name());
					}else{
						request.setAttribute("fixedpath8", null);
						request.setAttribute("picId8",null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				UploadfileVO ufvo9;
				try {
					ufvo9 = ufService.loadUploadfileById(pic.getPic9());
					if(ufvo9!=null){
						request.setAttribute("fixedpath9", ufvo9.getFile_path());
						request.setAttribute("picId9", ufvo9.getFile_name());
					}else{
						request.setAttribute("fixedpath9", null);
						request.setAttribute("picId9", null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				UploadfileVO ufvo10;
				try {
					ufvo10 = ufService.loadUploadfileById(pic.getPic10());
					if(ufvo10!=null){
						request.setAttribute("fixedpath10", ufvo10.getFile_path());
						request.setAttribute("picId10", ufvo10.getFile_name());
					}else{
						request.setAttribute("fixedpath10", null);
						request.setAttribute("picId10",null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				handoverLogForm.setHpic(pic);
			}else{
				for(int i=1;i<=10;i++){
					request.setAttribute("fixedpath"+i, null);
					request.setAttribute("picId"+i, null);
				}
			}
			return mapping.findForward("handover_record_management_ledger_detail");
		}
	 
	 

}
