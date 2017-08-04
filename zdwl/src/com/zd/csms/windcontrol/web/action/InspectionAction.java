package com.zd.csms.windcontrol.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zd.core.ActionSupport;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.util.CreatePDFUtil;
import com.zd.csms.windcontrol.constants.CompleteStatusConstants;
import com.zd.csms.windcontrol.constants.EditStatusConstants;
import com.zd.csms.windcontrol.model.Image;
import com.zd.csms.windcontrol.model.InspectionCommunionVO;
import com.zd.csms.windcontrol.model.InspectionInfoVO;
import com.zd.csms.windcontrol.model.InspectionItemVO;
import com.zd.csms.windcontrol.model.InspectionPictureVO;
import com.zd.csms.windcontrol.model.InspectionResultVO;
import com.zd.csms.windcontrol.model.InspectionSupervisorVO;
import com.zd.csms.windcontrol.model.ProblemRecordVO;
import com.zd.csms.windcontrol.model.SupervisorRecordVO;
import com.zd.csms.windcontrol.querybean.InspectionListRowMapper;
import com.zd.csms.windcontrol.querybean.InspectionReportQueryBean;
import com.zd.csms.windcontrol.service.InspectionService;
import com.zd.csms.windcontrol.web.form.InspectionForm;
import com.zd.tools.StringUtil;

public class InspectionAction extends ActionSupport {
	private UploadfileService ufService = new UploadfileService();
	private InspectionService inspecService = new InspectionService();

	/**
	 * 巡检报告页面(新增/修改)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward inspectionEntry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String idStr = request.getParameter("inspectionId");
		//需求79 - 显示经销商名称“****（经销商名称）巡检报告”
		String inspectionName = request.getParameter("inspectionName");
		if (StringUtil.isNotEmpty(inspectionName)) {
			inspectionName = new String(request.getParameter("inspectionName").getBytes("ISO-8859-1"),"UTF-8"); 
		}
		
		if (StringUtil.isBlank(idStr)) {
			return null;
		}
		int id = Integer.parseInt(idStr);
		InspectionInfoVO vo = inspecService.getInspectionInfoVO(id);
		request.setAttribute("inspectionId", id);
		request.setAttribute("dealerName", inspectionName);
		if (vo == null) {
			return mapping.findForward("add_inspection");// 新增页面
		} else {
			InspectionReportQueryBean inspectionReport=inspecService.getInspectionReport(id);
			request.setAttribute("inspectionReport", inspectionReport);
            return mapping.findForward("upd_inspection");// 修改页面
		}
	}

	/**
	 * 巡检报告详情页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward detailsEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String idStr = request.getParameter("id");
		if (StringUtil.isBlank(idStr)) {
			return null;
		}
		int id=Integer.parseInt(idStr);
		InspectionReportQueryBean inspectionReport=inspecService.getInspectionReport(id);
		inspectionReport.setPictures(inspecService.findPictureById(id));
		request.setAttribute("inspectionId", id);
		request.setAttribute("inspectionReport", inspectionReport);
		return mapping.findForward("view_inspection");
	}

	// 巡检报告:基本信息
	public ActionForward addAndUpdateInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = request.getParameter("jsonData");
		InspectionInfoVO vo = JSON.parseObject(json, InspectionInfoVO.class);
		vo.setModify_time(new Date());
		boolean bool = false;
		if (inspecService.getInspectionInfoVO(vo.getId()) == null) {
			bool = inspecService.addInfo(vo);
			if (bool) {
				bool = inspecService.updateReportStatus(
						EditStatusConstants.EDIT.getCode(), vo.getId());
			}
		} else {
			bool = inspecService.updateInfo(vo);
		}

		result(response, bool);
		return null;
	}

	// 巡检报告：检查内容
	public ActionForward addAndUpdateItem(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = request.getParameter("jsonData");
		InspectionItemVO vo = JSON.parseObject(json, InspectionItemVO.class);
		boolean bool = false;
		if (inspecService.findItembyId(vo.getId()) == null) {
			bool = inspecService.addInfo(vo);
		} else {
			bool = inspecService.updateItem(vo);
		}
		result(response, bool);
		return null;
	}

	// 巡检报告:检查过程中发现的问题及监管员的优缺点
	public ActionForward addAndUpdateRecord(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int inspectionId = Integer.parseInt(request
				.getParameter("inspectionId"));
		String problemJson = request.getParameter("problems");
		String supervisorJson = request.getParameter("supervisors");
		List<ProblemRecordVO> problemRecords = JSON.parseArray(problemJson,
				ProblemRecordVO.class);
		List<SupervisorRecordVO> supervisorRecords = JSON.parseArray(
				supervisorJson, SupervisorRecordVO.class);
		boolean bool = false;
		if (inspecService.findRecordbyInfoId(inspectionId) == null) {
			bool = inspecService.addRecord(problemRecords, supervisorRecords,
					inspectionId);
		} else {

			bool = inspecService.updateRecord(problemRecords,
					supervisorRecords, inspectionId);
		}

		result(response, bool);
		return null;
	}

	// 巡检报告：与店方沟通情况
	public ActionForward addAndUpdateCommunication(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = request.getParameter("jsonData");
		InspectionCommunionVO vo = JSON.parseObject(json,
				InspectionCommunionVO.class);
		boolean bool = false;
		if (inspecService.findCommunionbyId(vo.getId()) == null) {
			bool = inspecService.addInfo(vo);
		} else {
			bool = inspecService.updateCommunion(vo);
		}

		result(response, bool);
		return null;
	}

	// 监管员基本信息
	public ActionForward addAndUpdateSupervisor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = request.getParameter("jsonData");
		InspectionSupervisorVO vo = JSON.parseObject(json,
				InspectionSupervisorVO.class);
		boolean bool = false;
		if (inspecService.findSupervisorbyId(vo.getId()) == null) {
			bool = inspecService.addInfo(vo);

		} else {
			bool = inspecService.updateSupervisor(vo);
		}

		result(response, bool);
		return null;
	}

	// 巡检报告：巡检总结
	public ActionForward addAndUpdateResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = request.getParameter("jsonData");
		InspectionResultVO vo = JSON
				.parseObject(json, InspectionResultVO.class);
		boolean bool = false;
		if (inspecService.findResultbyId(vo.getId()) == null) {
			bool = inspecService.addInfo(vo);
		} else {

			bool = inspecService.updateResult(vo);
		}

		result(response, bool);
		return null;
	}

	// 巡检报告-店面照片:展示图片（报告修改页和新增页）
	public ActionForward showPicture(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String inspection = request.getParameter("inspectionId");
		if (StringUtil.isEmpty(inspection)) {
			return null;
		}

		int inspectionId = Integer.parseInt(request
				.getParameter("inspectionId"));
		List<InspectionPictureVO> picList = inspecService
				.findPictureIdByInfoId(inspectionId);
		if (picList == null) {
			return null;
		}
		/**
		 * 此处取出对应的图片信息拼接成Image对象返回去
		 */

		// 拼接Image对象信息
		List<Image> list = new ArrayList<Image>();
		for (InspectionPictureVO pic : picList) {
			Image image = new Image();
			UploadfileVO uplFile = ufService.loadUploadfileById(pic
					.getPictureId());
			image.setUrl("/showImg.do?method=downLoadFile&filePath="
					+ uplFile.getFile_path() + "&fileName="
					+ uplFile.getFile_name());// 下载地址
			image.setThumbnailUrl("/showImg.do?method=showThumbnailImg&filePath="
					+ uplFile.getFile_path());
			// 上传完成后的缩略图地址
			image.setDeleteUrl("/windcontrol/inspection.do?method=deletePicture&fileId="
					+ uplFile.getId());

			image.setName(uplFile.getFile_name());

			// 删除图片的地址
			image.setDeleteType("GET");// 提交方式
			list.add(image);
		}
		JSONObject json = new JSONObject();
		PrintWriter out = getWrite(response);
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
	 * 巡检报告-店面照片：保存图片
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addPicture(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = request.getMethod();
		/**
		 * 加载图片列表和新增图片走的请求一致，只不是显示的时候用的是GET而新增是POST
		 */
		if (method.equalsIgnoreCase("GET")) {
			return showPicture(mapping, form, request, response);
		}

		InspectionForm iform = (InspectionForm) form;
		FormFile file = iform.getFile();
		if (file == null) {
			return null;
		}
		PrintWriter out = getWrite(response);
		JSONObject json = new JSONObject();
		Image image = inspecService.addPicture(file, request);
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
	public ActionForward deletePicture(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileId = request.getParameter("fileId");// 获取图片Id
		boolean bool = false;
		if (StringUtil.isNotEmpty(fileId)) {
			int id = Integer.parseInt(fileId);
			bool = inspecService.deletePicture(id);
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

	/**
	 * 下载巡检报告
	 * 
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printInspection(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String idStr = request.getParameter("id");
		if (StringUtil.isBlank(idStr)) {
			return null;
		}
		String path = CreatePDFUtil.workInspection(Integer.parseInt(idStr), request);
		request.setAttribute("path", path);
		request.setAttribute("fileName", "巡检报告");
		return mapping.findForward("down_upfile");
	}

	// 巡检报告列表-内控专员
	public ActionForward findViewList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		InspectionForm inspecForm = (InspectionForm) form;
		List<InspectionListRowMapper> list = inspecService.findReportList(
				inspecForm, request);
        request.setAttribute("list", list);
		return mapping.findForward("viewList");

	}

	// 巡检报告列表-巡视专员
	public ActionForward findProgressList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InspectionForm inspecForm = (InspectionForm) form;
		boolean fkFlag;
		//添加查看用户角色
		UserSession userSession = UserSessionUtil.getUserSession(request);
		if(userSession!=null){
			//先判断这个用户是否带有风控外控专员角色，如果是，带上指定信息
			fkFlag = inspecService.judgeUserRole(userSession.getUser().getId(), 19);
			if(fkFlag){
				inspecForm.getQuery().setClient_id(userSession.getUser().getId());
			}else{
				inspecForm.getQuery().setClient_id(-1);
			}
		}
		List<InspectionListRowMapper> list = inspecService
				.findInspectionList(inspecForm, request);
		request.setAttribute("list", list);
		return mapping.findForward("progressList");// 未完成列表页

	}

	// 巡检报告列表-巡视专员:已完成列表
	public ActionForward findCompletedList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InspectionForm inspecForm = (InspectionForm) form;
		boolean fkFlag;
		inspecForm.getQuery().setFlag(
				CompleteStatusConstants.COMPLETE.getCode());
		//添加查看用户角色
		UserSession userSession = UserSessionUtil.getUserSession(request);
		if(userSession!=null){
			//先判断这个用户是否带有风控外控专员角色，如果是，带上指定信息
			fkFlag = inspecService.judgeUserRole(userSession.getUser().getId(), 19);
			if(fkFlag){
				inspecForm.getQuery().setClient_id(userSession.getUser().getId());
			}else{
				inspecForm.getQuery().setClient_id(-1);
			}
		}
		List<InspectionListRowMapper> list = inspecService
				.findInspectionList(inspecForm, request);
		request.setAttribute("list", list);
		return mapping.findForward("completedList");
	}

	// 提交:提交后巡检报告不可修改,报告状态变为已提交
	public ActionForward submitReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		if (StringUtil.isBlank(id)) {
			return null;
		}
		boolean fag = false;
		fag = inspecService.submitReport(Integer.parseInt(id));
		// 巡检报告列表-巡视专员:已完成列表
		if (fag) {
			/*巡检报告提交后给 监管员管理部经理、市场部经理、业务部经理、风控部经理、视频部经理、财务部经理、
			 * 风险管理部部长、市场管理部部长、运营管理部部长、物流金融中心总监 发送信息提醒
			*/
			InspectionForm iform = (InspectionForm) form;
			/*try {
				MessageUtil.sendMsg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
						RoleConstants.MARKET_AMALDAR.getCode()+"",
						RoleConstants.BUSINESS_AMALDAR.getCode()+"",
						RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
						RoleConstants.VIDEO_AMALDAR.getCode()+"",
						RoleConstants.FINANCE_AMALDAR.getCode()+"",
						RoleConstants.RISKMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""}, "巡检报告",
						"/windcontrol/inspection.do?method=findProgressList", 1,MessageTypeContant.EXCEPTIONDATE.getCode(),iform.getQuery().getClient_id());
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			try {
				String[] roleids = new String[]{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
						RoleConstants.MARKET_AMALDAR.getCode()+"",
						RoleConstants.BUSINESS_AMALDAR.getCode()+"",
						RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
						RoleConstants.VIDEO_AMALDAR.getCode()+"",
						RoleConstants.FINANCE_AMALDAR.getCode()+"",
						RoleConstants.RISKMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""};
						for (String rid : roleids) {
							int uid = MessageUtil.getUserId(rid);
							MessageService msgService = new MessageService();
							MessageVO msg = msgService.loadMsgByUserAndType(Integer.valueOf(uid), MessageTypeContant.DEALEREXIT.getCode());
							if(msg != null){
								int name = 1;
								if(StringUtil.isNumber(msg.getName())){
									name = Integer.parseInt(msg.getName())+1;
								}
								msg.setName(name+"");
								msgService.updMessage(msg);
							}else{
								MessageUtil.sendOrUpdateMeg(roleids,  1+"", 
										"/windcontrol/inspection.do?method=findProgressList", 1,MessageTypeContant.EXCEPTIONDATE.getCode(),iform.getQuery().getClient_id());
							}
						}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return findCompletedList(mapping, form, request, response);
		} else {
			return inspectionEntry(mapping, form, request, response);

		}

	}

	private void result(HttpServletResponse response, boolean result)
			throws IOException {
		PrintWriter out = getWrite(response);
		JSONObject jsonObject = new JSONObject();
		try {
			if (result) {
				jsonObject.put("success", true);
				jsonObject.put("message", "成功");
				out.write(jsonObject.toJSONString());

			} else {
				jsonObject.put("success", false);
				jsonObject.put("message", "失败");
				out.write(jsonObject.toJSONString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

	}
}
