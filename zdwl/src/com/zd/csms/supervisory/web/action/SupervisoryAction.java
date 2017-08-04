package com.zd.csms.supervisory.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.supervisory.dao.ISupervisoryDao;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.model.SuperviseVO;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.SupervisorEducationVO;
import com.zd.csms.supervisory.model.SupervisorEntity;
import com.zd.csms.supervisory.model.SupervisorFamilyVO;
import com.zd.csms.supervisory.model.SupervisorImportEntity;
import com.zd.csms.supervisory.model.SupervisorQueryVO;
import com.zd.csms.supervisory.model.SupervisorRecommendVO;
import com.zd.csms.supervisory.model.SupervisorWorkExperienceVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisory.util.CreatePDFUtil;
import com.zd.csms.supervisory.web.excel.SuperviseRowMapper;
import com.zd.csms.supervisory.web.form.SupervisorForm;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.impl.ImportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SupervisoryAction extends ActionSupport{
	
	SupervisoryService supervisoryService=new SupervisoryService();
	UploadfileService uploadfileService = new UploadfileService();
	RegionService regionService=new RegionService();
	private ISupervisoryDao dao = SupervisorDAOFactory.getSupervisoryDAO();
	public ActionForward addSupervisoryBaseInfoEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		initOption(request);
		//返回新增页面
		return mapping.findForward("add_supervisory_baseInfo");
	}
	
	public ActionForward addSupervisoryBaseInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorBaseInfoVO baseInfo=supervisoryForm.getBaseInfo();
		baseInfo.setCurrentTime(new Date());
		FormFile file =supervisoryForm.getHeadPicture();
		if(file != null && file.getFileName()!=""){
			int ufid = UploadFileUtil.savefile(file, UserSessionUtil.getUserSession(request), request);
			baseInfo.setPictureId(ufid);
		}
		UserSession user = UserSessionUtil.getUserSession(request);
		baseInfo.setCreateTime(new Date());
		baseInfo.setCreateUser(user.getUser().getId());
		boolean flag=supervisoryService.addBaseInfo(baseInfo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		if(flag){
			//新增成功返回列表页
			return supervisoryPageList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addSupervisoryBaseInfoEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updSupervisoryBaseInfoEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) actionform;
		SupervisorBaseInfoVO baseInfo=supervisoryService.getBaseInfo(supervisoryForm.getBaseInfo().getId());
		//对象不存在时返回列表页
		if(baseInfo==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),"该对象不存在");
			return supervisoryPageList(mapping,supervisoryForm,request,response);
		}
		supervisoryForm.setBaseInfo(baseInfo);
		request.setAttribute("registeredAddres",regionService.getNameById(StringUtil.intValue(baseInfo.getNativePlaceProvince(),0))
				+" "+regionService.getNameById(StringUtil.intValue(baseInfo.getNativePlaceCity(),0))
				+" "+regionService.getNameById(StringUtil.intValue(baseInfo.getNativePlaceCounty(),0)));
		request.setAttribute("liveAddres", regionService.getNameById(StringUtil.intValue(baseInfo.getLiveAddressProvince(),0))
				+" "+regionService.getNameById(StringUtil.intValue(baseInfo.getLiveAddressCity(),0))
				+" "+regionService.getNameById(StringUtil.intValue(baseInfo.getLiveAddressCounty(),0)));
		initOption(request);
		try {
			UploadfileVO ufvo = uploadfileService.loadUploadfileById(baseInfo.getPictureId());
			if(ufvo!=null){
				request.setAttribute("filepath", ufvo.getFile_path());
				request.setAttribute("picname", ufvo.getFile_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回新增页面
		return mapping.findForward("upd_supervisory_baseInfo");
	}
	
	public ActionForward updSupervisoryBaseInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorBaseInfoVO baseInfo=supervisoryForm.getBaseInfo();
		FormFile file =supervisoryForm.getHeadPicture();
		if(file != null && file.getFileName()!=""){
			int ufid = UploadFileUtil.savefile(file, UserSessionUtil.getUserSession(request), request);
			baseInfo.setPictureId(ufid);
		}
		UserSession user = UserSessionUtil.getUserSession(request);
		baseInfo.setLastModifyTime(new Date());
		baseInfo.setLastModifyUser(user.getUser().getId());
		boolean flag= supervisoryService.updBaseInfo(baseInfo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		if(flag){
			//新增成功返回列表页
			return supervisoryPageList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return updSupervisoryBaseInfoEntity(mapping,form,request,response);
		}
	}
	public ActionForward delSupervisory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		boolean flag=supervisoryService.delete(supervisoryForm.getBaseInfo().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		//返回列表页
		return supervisoryPageList(mapping,supervisoryForm,request,response);
	}
	public ActionForward addSupervisoryEducationEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		//返回新增页面
		return mapping.findForward("add_supervisory_education");
	}
	
	public ActionForward addSupervisoryEducation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorEducationVO education=supervisoryForm.getEducation();
		education.setSupervisorId(supervisoryForm.getBaseInfo().getId());
		boolean flag=supervisoryService.addEducation(education);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		if(flag){
			//修改最后更新人和最后更新时间
			updateLastModify(supervisoryForm.getBaseInfo().getId(),request);
			//新增成功返回列表页
			return getEducationListBySupervisoryId(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addSupervisoryEducationEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updSupervisoryEducationEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) actionform;
		SupervisorEducationVO education=supervisoryService.getEducation(supervisoryForm.getEducation().getId());
		//对象不存在时返回列表页
		if(education==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),"该对象不存在");
			return getEducationListBySupervisoryId(mapping,supervisoryForm,request,response);
		}
		supervisoryForm.setEducation(education);
		//返回新增页面
		return mapping.findForward("upd_supervisory_education");
	}
	
	public ActionForward updSupervisoryEducation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorEducationVO education=supervisoryForm.getEducation();
		boolean flag= supervisoryService.updEducation(education);
		supervisoryForm.getBaseInfo().setId(education.getSupervisorId());;
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		if(flag){
			//修改最后更新人和最后更新时间
			updateLastModify(supervisoryForm.getBaseInfo().getId(),request);
			//新增成功返回列表页
			return getEducationListBySupervisoryId(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addSupervisoryEducationEntity(mapping,form,request,response);
		}
	}
	public ActionForward delSupervisoryEducation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		boolean flag=supervisoryService.delEducation(supervisoryForm.getEducation().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		//返回列表页
		return getEducationListBySupervisoryId(mapping,supervisoryForm,request,response);
	}
	
	public ActionForward addSupervisoryFamilyEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		//返回新增页面
		return mapping.findForward("add_supervisory_family");
	}
	
	public ActionForward addSupervisoryFamily(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorFamilyVO family=supervisoryForm.getFamily();
		family.setSupervisorId(supervisoryForm.getBaseInfo().getId());
		boolean flag=supervisoryService.addFamily(family);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		if(flag){
			//修改最后更新人和最后更新时间
			updateLastModify(supervisoryForm.getBaseInfo().getId(),request);
			//新增成功返回列表页
			return getFamilyListBySupervisoryId(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addSupervisoryFamilyEntity(mapping,form,request,response);
		}
	}
	
	@SuppressWarnings("unused")
	public ActionForward updSupervisoryFamilyEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) actionform;
		SupervisorFamilyVO family=supervisoryService.getFamily(supervisoryForm.getFamily().getId());
		supervisoryForm.getBaseInfo().setId(family.getSupervisorId());;
		//对象不存在时返回列表页
		if(family==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "该对象不存在");
			return getFamilyListBySupervisoryId(mapping,supervisoryForm,request,response);
		}
		supervisoryForm.setFamily(family);
		//返回新增页面
		return mapping.findForward("upd_supervisory_family");
	}
	
	public ActionForward updSupervisoryFamily(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorFamilyVO family=supervisoryForm.getFamily();
		boolean flag= supervisoryService.updFamily(family);
		supervisoryForm.getBaseInfo().setId(family.getSupervisorId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		if(flag){
			//修改最后更新人和最后更新时间
			updateLastModify(supervisoryForm.getBaseInfo().getId(),request);
			//新增成功返回列表页
			return getFamilyListBySupervisoryId(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addSupervisoryFamilyEntity(mapping,form,request,response);
		}
	}
	public ActionForward delSupervisoryFamily(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		boolean flag=supervisoryService.delFamily(supervisoryForm.getFamily().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		//返回列表页
		return getFamilyListBySupervisoryId(mapping,supervisoryForm,request,response);
	}
	

	public ActionForward addSupervisoryRecommendEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		initOption(request);
		//返回新增页面
		return mapping.findForward("add_supervisory_recommend");
	}
	
	public ActionForward addSupervisoryRecommend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorRecommendVO recommend=supervisoryForm.getRecommend();
		recommend.setSupervisorId(supervisoryForm.getBaseInfo().getId());
		boolean flag=supervisoryService.addRecommend(recommend);
		supervisoryForm.getBaseInfo().setId(recommend.getSupervisorId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		if(flag){
			//修改最后更新人和最后更新时间
			updateLastModify(supervisoryForm.getBaseInfo().getId(),request);
			//新增成功返回列表页
			return getRecommendBySupervisoryId(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addSupervisoryRecommendEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updSupervisoryRecommendEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		
		SupervisorForm supervisoryForm =(SupervisorForm) actionform;
		SupervisorRecommendVO recommend=supervisoryService.getRecommend(supervisoryForm.getRecommend().getId());
		//对象不存在时返回列表页
		if(recommend==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),"该对象不存在");
			return getRecommendBySupervisoryId(mapping,supervisoryForm,request,response);
		}
		supervisoryForm.setRecommend(recommend);
		initOption(request);
		//返回新增页面
		return mapping.findForward("upd_supervisory_recommend");
	}
	
	public ActionForward updSupervisoryRecommend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorRecommendVO recommend=supervisoryForm.getRecommend();
		boolean flag= supervisoryService.updRecommend(recommend);
		supervisoryForm.getBaseInfo().setId(recommend.getSupervisorId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		if(flag){
			//修改最后更新人和最后更新时间
			updateLastModify(supervisoryForm.getBaseInfo().getId(),request);
			//新增成功返回列表页
			return getRecommendBySupervisoryId(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addSupervisoryRecommendEntity(mapping,form,request,response);
		}
	}
	public ActionForward delSupervisoryRecommend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		boolean flag=supervisoryService.delRecommend(supervisoryForm.getRecommend().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		//返回列表页
		return getRecommendBySupervisoryId(mapping,supervisoryForm,request,response);
	}
	

	public ActionForward addSupervisoryWorkExperienceEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		//返回新增页面
		return mapping.findForward("add_supervisory_workExperience");
	}
	
	public ActionForward addSupervisoryWorkExperience(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorWorkExperienceVO workExperience=supervisoryForm.getWorkExperience();
		workExperience.setSupervisorId(supervisoryForm.getBaseInfo().getId());
		boolean flag=supervisoryService.addWorkExperience(workExperience);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		if(flag){
			//修改最后更新人和最后更新时间
			updateLastModify(supervisoryForm.getBaseInfo().getId(),request);
			//新增成功返回列表页
			return getWorkExperienceListBySupervisoryId(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addSupervisoryWorkExperienceEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updSupervisoryWorkExperienceEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) actionform;
		SupervisorWorkExperienceVO workExperience=supervisoryService.getWorkExperience(supervisoryForm.getWorkExperience().getId());
		
		//对象不存在时返回列表页
		if(workExperience==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "该对象不存在");
			return getWorkExperienceListBySupervisoryId(mapping,supervisoryForm,request,response);
		}
		supervisoryForm.setWorkExperience(workExperience);
		//返回新增页面
		return mapping.findForward("upd_supervisory_workExperience");
	}
	
	public ActionForward updSupervisoryWorkExperience(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorWorkExperienceVO workExperience=supervisoryForm.getWorkExperience();
		boolean flag= supervisoryService.updWorkExperience(workExperience);
		supervisoryForm.getBaseInfo().setId(workExperience.getSupervisorId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		if(flag){
			//修改最后更新人和最后更新时间
			updateLastModify(supervisoryForm.getBaseInfo().getId(),request);
			//新增成功返回列表页
			return getWorkExperienceListBySupervisoryId(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addSupervisoryWorkExperienceEntity(mapping,form,request,response);
		}
	}
	public ActionForward delSupervisoryWorkExperience(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		boolean flag=supervisoryService.delWorkExperience(supervisoryForm.getWorkExperience().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), supervisoryService.getExecuteMessage());
		//返回列表页
		return getWorkExperienceListBySupervisoryId(mapping,supervisoryForm,request,response);
	}
	public ActionForward supervisoryPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisoryForm =(SupervisorForm) form;
		SupervisorQueryVO supervisoryQuery=supervisoryForm.getQuery();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("supervisoryList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(supervisoryQuery);
		
		//按条件查询分页数据
		List<SupervisorEntity> list = supervisoryService.searchPageList(supervisoryQuery,tools);
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//返回列表页面
		return mapping.findForward("supervisory_list");
	}
	
	public List<SupervisorEntity> SupervisoryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisorForm =(SupervisorForm) form;
		SupervisorQueryVO supervisorQuery=supervisorForm.getQuery();
		
		//按条件查询分页数据
		List<SupervisorEntity> list = supervisoryService.searchList(supervisorQuery);
		
		return list;
	}
	public ActionForward getEducationListBySupervisoryId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisorForm =(SupervisorForm) form;
		
		List<SupervisorEducationVO> list = supervisoryService.getEducationBySupervisorId(supervisorForm.getBaseInfo().getId());
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
								
		//返回列表页面
		return mapping.findForward("supervisoryEducation_list");
	}
	public ActionForward getFamilyListBySupervisoryId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisorForm =(SupervisorForm) form;
		
		List<SupervisorFamilyVO> list = supervisoryService.getFamilyBySupervisorId(supervisorForm.getBaseInfo().getId());
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
						
		//返回列表页面
		return mapping.findForward("supervisoryFamily_list");
	}
	public ActionForward getWorkExperienceListBySupervisoryId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisorForm =(SupervisorForm) form;
		
		//按条件查询分页数据
		List<SupervisorWorkExperienceVO> list = supervisoryService.getWorkExperienceBySupervisorId(supervisorForm.getBaseInfo().getId());
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
				
		//返回列表页面
		return mapping.findForward("supervisoryWorkExperience_list");
	}
	public ActionForward getRecommendBySupervisoryId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisorForm =(SupervisorForm) form;
		
		//按条件查询分页数据
		SupervisorRecommendVO recommend = supervisoryService.getRecommendBySupervisorId(supervisorForm.getBaseInfo().getId());
		List<SupervisorRecommendVO> list=new ArrayList<SupervisorRecommendVO>();
		if(recommend!=null){
			list.add(recommend);
		}
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		if(list != null && list.size()>0){
			request.setAttribute("flag", 1);
		}
		//返回列表页面
		return mapping.findForward("supervisoryRecommend_list");
	}
	
	public ActionForward supervisoryDetailEntity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SupervisorForm supervisorForm =(SupervisorForm) form;
		SupervisorEntity supervisor=supervisoryService.load(supervisorForm.getBaseInfo().getId());
		request.setAttribute("familyList", supervisor.getSupervisoryFamily());
		request.setAttribute("workExperienceList", supervisor.getSupervisoryWorkExperience());
		request.setAttribute("educationList", supervisor.getSupervisoryEducation());
		supervisorForm.setSupervisor(supervisor);
		try {
			UploadfileVO ufvo = uploadfileService.loadUploadfileById(supervisor.getSuperVisorBaseInfo().getPictureId());
			if(ufvo != null){
				request.setAttribute("pic", ufvo.getId());
				request.setAttribute("file", ufvo.getFile_path());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		initOption(request);
		//返回列表页面
		return mapping.findForward("supervisory_detail");
	}
	/**
	 * 初始化Option
	 * @param request
	 * @return void
	 */
	private void initOption(HttpServletRequest request){
		
		//初始化新增页面其他推荐渠道复选框
		List<OptionObject> RecommendChannelOptions = OptionUtil.superVisorRecommendChannelOptions();
		request.setAttribute("RecommendChannelOptions", RecommendChannelOptions);
		request.setAttribute("registeredStatus", OptionUtil.superVisorRegisteredStatusOptions());
		
	}
	
	public ActionForward importExcelEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("import_supervise");
	}
	
	public ActionForward importExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SupervisorForm sForm =(SupervisorForm) form;
		
		FormFile file =  sForm.getExcelfile();
		
		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);
		List<SupervisorImportEntity> supervisorImportEntityList=new ArrayList<SupervisorImportEntity>();
		List<SuperviseVO> list = (List<SuperviseVO>) importFile.readAll(1,new SuperviseRowMapper());
		boolean valid=true;
		String message="";
		for (int i=0;i<list.size();i++) {
			SuperviseVO o =list.get(i);
			if(!StringUtil.isEmpty(o.getName())){
				SupervisorImportEntity supervisorImportEntity=new SupervisorImportEntity();
				List<SupervisorFamilyVO> supervisorFamilyList=new ArrayList<SupervisorFamilyVO>();
				SupervisorBaseInfoVO sbvo = new SupervisorBaseInfoVO();
				
				if(o.getJob()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员应聘岗位！";
					break;
				}
				sbvo.setJob(o.getJob());
				
				sbvo.setEntryTime(o.getEntryTime());
				
				sbvo.setName(o.getName());
				
				if(o.getGender()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员性别！";
					break;
				}
				sbvo.setGender(o.getGender());
				
				if(o.getBirthday()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员出生日期！";
					break;
				}
				sbvo.setBirthday(o.getBirthday());
				
				if(o.getIdNumber()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员身份证号！";
					break;
				}else{
					boolean f=dao.validateIdNumberUnique(o.getId(), o.getIdNumber());
					if(!f){
						valid=false;
						message="导入失败，第"+(i+1)+"条记录中的身份证号已存在，请核实后重新输入！";
						break;
					}
				}
				sbvo.setIdNumber(o.getIdNumber());
				
				if(o.getNation()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员民族！";
					break;
				}
				sbvo.setNation(o.getNation());
				
				if(o.getEducationBackground()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员学历！";
					break;
				}
				sbvo.setEducationBackground(o.getEducationBackground());
				sbvo.setNativePlace(o.getNativePlace());
				
				if(o.getPoliticsStatus()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员政治面貌！";
					break;
				}
				sbvo.setPoliticsStatus(o.getPoliticsStatus());
				
				if(o.getRegisteredStatus()==0){
					valid=false;
					message="导入失败，第"+(i+1)+"条记录中的监管员户口性质填写有误！";
					break;
				}
				sbvo.setRegisteredStatus(o.getRegisteredStatus());
				
				if(o.getSelfTelephone()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员本人联系电话！";
					break;
				}
				sbvo.setSelfTelephone(o.getSelfTelephone());
				sbvo.setHomeTelephone(o.getHomeTelephone());
				
				if(o.getFertilityState()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员婚育状况！";
					break;
				}
				sbvo.setFertilityState(o.getFertilityState());
				sbvo.setRegisteredAddress(o.getRegisteredAddress());
				sbvo.setNativePlaceProvince(o.getNativePlaceProvince());
				sbvo.setNativePlaceCity(o.getNativePlaceCity());
				sbvo.setNativePlaceCounty(o.getNativePlaceCounty());
				
				if(o.getLiveAddressProvince()==null || o.getLiveAddressProvince().equals("0")){
					valid=false;
					message="导入失败，第"+(i+1)+"条记录监管员现住址（省）填写有误，需填入地区管理已配置好的省的名称！";
					break;
				}
				sbvo.setLiveAddressProvince(o.getLiveAddressProvince());
				if(o.getLiveAddressCity()==null || o.getLiveAddressCity().equals("0")){
					valid=false;
					message="导入失败，第"+(i+1)+"条记录监管员现住址（市）填写有误，需填入地区管理已配置好的市的名称！";
					break;
				}
				sbvo.setLiveAddressCity(o.getLiveAddressCity());
				if(o.getLiveAddressCounty()==null || o.getLiveAddressCounty().equals("0")){
					valid=false;
					message="导入失败，第"+(i+1)+"条记录监管员现住址（区/县）填写有误，需填入地区管理已配置好的区/县的名称！";
					break;
				}
				sbvo.setLiveAddressCounty(o.getLiveAddressCounty());
				if(o.getLiveAddress()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员现住址详细地址！";
					break;
				}
				sbvo.setLiveAddress(o.getLiveAddress());
				
				if(o.getEmergencyContactor()==null){
					valid=false;
					message="导入失败，请填写第"+(i+1)+"条记录中的监管员紧急状况联系人！";
					break;
				}
				sbvo.setEmergencyContactor(o.getEmergencyContactor());
				sbvo.setEmergencyContactNumber(o.getEmergencyContactNumber());
				sbvo.setEmergencyContactRelationship(o.getEmergencyContactRelationship());
				UserSession user = UserSessionUtil.getUserSession(request);
				sbvo.setCreateTime(new Date());
				sbvo.setCreateUser(user.getUser().getId());
				int sid =Util.getID(SupervisorBaseInfoVO.class);
				sbvo.setId(sid);
				supervisorImportEntity.setSuperVisorBaseInfo(sbvo);
				
				SupervisorEducationVO sevo = new SupervisorEducationVO();
				sevo.setEducationStartTime(o.getEducationStartTime());
				sevo.setEducationEndTime(o.getEducationEndTime());
				sevo.setSchoolName(o.getSchoolName());
				sevo.setMajor(o.getMajor());
				sevo.setCertificate(o.getCertificate());
				sevo.setDegree(o.getDegree());
				sevo.setSupervisorId(sid);
				supervisorImportEntity.setSupervisoryEducation(sevo);
				
				SupervisorWorkExperienceVO swvo = new SupervisorWorkExperienceVO();
				swvo.setWorkStartTime(o.getWorkStartTime());
				swvo.setWorkEndTime(o.getWorkEndTime());
				swvo.setServiceOrganization(o.getServiceOrganization());
				swvo.setPosition(o.getPosition());
				swvo.setCompensation(o.getCompensation());
				swvo.setLeaveReason(o.getLeaveReason());
				swvo.setCertifier(o.getCertifier());
				swvo.setContactNumber(o.getContactNumber());
				swvo.setSupervisorId(sid);
				supervisorImportEntity.setSupervisoryWorkExperience(swvo);
				
				SupervisorFamilyVO sfvo = new SupervisorFamilyVO();
				sfvo.setName(o.getFname());
				sfvo.setProfession(o.getFprofession());
				sfvo.setOrganization(o.getForganization());
				sfvo.setTelephone(o.getFtelephone());
				sfvo.setRelationship(o.getFrelationship());
				sfvo.setSupervisorId(sid);
				supervisorFamilyList.add(sfvo);
				
				SupervisorFamilyVO smvo = new SupervisorFamilyVO();
				smvo.setName(o.getMname());
				smvo.setProfession(o.getMprofession());
				smvo.setOrganization(o.getMorganization());
				smvo.setTelephone(o.getMtelephone());
				smvo.setRelationship(o.getMrelationship());
				smvo.setSupervisorId(sid);
				supervisorFamilyList.add(smvo);
				
				SupervisorFamilyVO sxvo = new SupervisorFamilyVO();
				sxvo.setName(o.getXname());
				sxvo.setProfession(o.getXprofession());
				sxvo.setOrganization(o.getXorganization());
				sxvo.setTelephone(o.getXtelephone());
				sxvo.setRelationship(o.getXrelationship());
				sxvo.setSupervisorId(sid);
				supervisorFamilyList.add(sxvo);
				
				SupervisorFamilyVO szvo = new SupervisorFamilyVO();
				szvo.setName(o.getZname());
				szvo.setProfession(o.getZprofession());
				szvo.setOrganization(o.getZorganization());
				szvo.setTelephone(o.getZtelephone());
				szvo.setRelationship(o.getZrelationship());
				szvo.setSupervisorId(sid);
				supervisorFamilyList.add(szvo);
				supervisorImportEntity.setSupervisoryFamily(supervisorFamilyList);
				
				SupervisorRecommendVO srvo = new SupervisorRecommendVO();
				srvo.setIsInsideRecommend(o.getIsInsideRecommend());
				srvo.setOtherChannel(o.getOtherChannel());
				srvo.setRecommenderName(o.getRecommenderName());
				srvo.setRecommenderPosition(o.getRecommenderPosition());
				srvo.setRecommenderPhone(o.getRecommenderPhone());
				srvo.setRecommenderRelationship(o.getRecommenderRelationship());
				srvo.setRecommenderDepartment(o.getRecommenderDepartment());
				srvo.setSupervisorId(sid);
				supervisorImportEntity.setSupervisoryRecommend(srvo);
				supervisorImportEntityList.add(supervisorImportEntity);
			}else{
				valid=false;
				message="导入失败，请填写第"+(i+1)+"条记录中的监管员姓名！";
				break;
			}
		}
		//如果全部验证通过，则执行新增方法
		if(valid){
			if(supervisorImportEntityList!=null && supervisorImportEntityList.size()>0){
				for(SupervisorImportEntity entity:supervisorImportEntityList){
					valid=valid && dao.add(entity.getSuperVisorBaseInfo());
					if(valid){
						valid=valid && supervisoryService.addEducation(entity.getSupervisoryEducation());
						if(valid){
							valid=valid && supervisoryService.addWorkExperience(entity.getSupervisoryWorkExperience());
							if(valid){
								valid=valid && supervisoryService.addRecommend(entity.getSupervisoryRecommend());
								if(valid){
									if(entity.getSupervisoryFamily()!=null && entity.getSupervisoryFamily().size()>0){
										for(SupervisorFamilyVO family:entity.getSupervisoryFamily()){
											valid=valid && supervisoryService.addFamily(family);
											if(!valid){
												message="监管员家庭状况导入失败！";
												break;
											}
										}
									}
								}else{
									message="监管员招聘渠道导入失败！";
									break;
								}
							}else{
								message="监管员工作经历导入失败！";
								break;
							}
						}else{
							message="监管员教育状况导入失败！";
							break;
						}
					}else{
						message="监管员基本信息导入失败！";
						break;
					}
					
				}
			}
		}
		//将执行结果及消息设置到request为页面处理使用
		if(valid){
			message="导入成功";
		}
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), valid);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), message);
		
		return supervisoryPageList(mapping,form,request,response);
	}
	
	
	//下载
	public ActionForward downLoad(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		SupervisorForm supervisorForm =(SupervisorForm) actionform;
		int id = supervisorForm.getBaseInfo().getId();
		String path = CreatePDFUtil.supervisorInfo(id, request);
		request.setAttribute("path", path);
		request.setAttribute("fileName", "监管员基本信息");
		return mapping.findForward("down_upfile");
	}
	
	public void updateLastModify(int supervisorBaseInfoId,HttpServletRequest request){
		//修改最后修改人，最后修改时间
		SupervisorBaseInfoVO baseInfo=supervisoryService.getBaseInfo(supervisorBaseInfoId);
		UserSession user = UserSessionUtil.getUserSession(request);
		baseInfo.setLastModifyTime(new Date());
		baseInfo.setLastModifyUser(user.getUser().getId());
		supervisoryService.updBaseInfo(baseInfo);
	}
}
