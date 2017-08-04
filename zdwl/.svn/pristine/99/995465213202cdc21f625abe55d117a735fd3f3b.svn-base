package com.zd.csms.repository.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ArrayUtils;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.region.service.RegionService;
import com.zd.csms.repository.constants.RepositoryReason;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.model.RepositoryDispatchCityVO;
import com.zd.csms.repository.model.RepositoryEntity;
import com.zd.csms.repository.model.RepositoryQueryVO;
import com.zd.csms.repository.model.RepositorySelectBean;
import com.zd.csms.repository.model.RepositoryTrainVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.repository.web.excel.RepositoryExportRowMapper;
import com.zd.csms.repository.web.excel.RepositoryRowMapper;
import com.zd.csms.repository.web.form.RepositoryForm;
import com.zd.csms.supervisory.model.SupervisorBaseInfoJsonVO;
import com.zd.csms.supervisory.web.jsonaction.SupervisorJsonAction;
import com.zd.csms.util.DateUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class RepositoryAction extends ActionSupport{
	
	RepositoryService repositoryService=new RepositoryService();
	RegionService regionService=new RegionService();
	/**
	 * 流程角色:监管员管理部招聘专员->监管员管理部培训专员
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode()};
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	public ActionForward addRepositoryEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm repositoryForm =(RepositoryForm) actionform;
		repositoryForm.getRepository().setStatus(RepositoryStatus.VALID.getCode());;
		initOption(request);
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getNoRepositorySupervise(repositoryForm.getRepository().getSupervisorId()));
		request.setAttribute("role", getCurrRole(request));
		//request.setAttribute("province",regionService.findAllProvince());
		//返回新增页面
		return mapping.findForward("add_repository");
	}
	
	public ActionForward addRepository(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RepositoryForm repositoryForm =(RepositoryForm) form;
		RepositoryVO repository=repositoryForm.getRepository();
		UserSession user = UserSessionUtil.getUserSession(request);
		repository.setCreateTime(new Date());
		repository.setCreateUser(user.getUser().getId());
		boolean flag=repositoryService.add(repository);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), repositoryService.getExecuteMessage());
		if(flag){
			//新增成功返回列表页
			return repositoryPageList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addRepositoryEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updRepositoryEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm repositoryForm =(RepositoryForm) actionform;
		RepositoryVO repository=repositoryService.get(repositoryForm.getRepository().getId());
		
		//对象不存在时返回列表页
		if(repository==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),"当前储备库信息不存在");
			
			return repositoryPageList(mapping,repositoryForm,request,response);
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), true);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), repositoryService.getExecuteMessage());
				
		repositoryForm.setRepository(repository);
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getNoRepositorySupervise(repository.getSupervisorId()));
		request.setAttribute("role", getCurrRole(request));
		initOption(request);
		//返回新增页面
		return mapping.findForward("upd_repository");
	}
	
	public ActionForward updRepository(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RepositoryForm repositoryForm =(RepositoryForm) form;
		
		RepositoryVO repository=repositoryForm.getRepository();
		UserSession user = UserSessionUtil.getUserSession(request);
		repository.setLastModifyTime(new Date());
		repository.setLastModifyUser(user.getUser().getId());
		boolean flag= repositoryService.update(repository);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), repositoryService.getExecuteMessage());
		if(flag){
			//新增成功返回列表页
			return repositoryPageList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return updRepositoryEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updRepositoryTrainEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm repositoryForm =(RepositoryForm) actionform;
		RepositoryTrainVO train=repositoryService.loadRepositoryTrainByRepositoryId(repositoryForm.getRepository().getId());
		if(train==null){
			train=new RepositoryTrainVO();
		}
		repositoryForm.setRepositoryTrain(train);
		repositoryForm.getRepository().setId(repositoryForm.getRepository().getId());
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		request.setAttribute("role", getCurrRole(request));
		initOption(request);
		//返回新增页面
		return mapping.findForward("upd_repositoryTrain");
	}
	
	public ActionForward updRepositoryTrain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RepositoryForm repositoryForm =(RepositoryForm) form;
		RepositoryTrainVO train=repositoryForm.getRepositoryTrain();
		train.setRepositoryId(repositoryForm.getRepository().getId());
		boolean flag= repositoryService.updateTrain(train);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), repositoryService.getExecuteMessage());
		repositoryForm.setRepositoryTrain(train);
		if(flag){
			//修改最后修改时间，最后修改人
			updateLastModify(train.getRepositoryId(),request);
			//修改成功返回列表页
			return repositoryPageList(mapping,form,request,response);
		}else{
			//修改失败返回新增页面
			return updRepositoryTrainEntity(mapping,form,request,response);
		}
	}
	public ActionForward delRepository(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm repositoryForm =(RepositoryForm) form;
		boolean flag=repositoryService.delete(repositoryForm.getRepository().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), repositoryService.getExecuteMessage());
		//返回列表页
		return repositoryPageList(mapping,repositoryForm,request,response);
	}
	/**
	 * 返回分页列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward repositoryPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm repositoryForm =(RepositoryForm) form;
		RepositoryQueryVO repositoryQuery=repositoryForm.getRepositoryQuery();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("repositoryList", request);
		
		
		if(repositoryQuery.getStatus()==null){
			repositoryQuery.setStatus(new Integer[]{RepositoryStatus.VALID.getCode()});
		}
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(repositoryQuery);
		//按条件查询分页数据
		List<RepositoryEntity> list = repositoryService.searchPageList(repositoryQuery,tools);
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		request.setAttribute("role", getCurrRole(request));
		initOption(request);
		//返回列表页面
		return mapping.findForward("repository_list");
	}
	
	public ActionForward getRepositoryDispatchCityByRepositoryId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm repositoryForm =(RepositoryForm) form;
		int repositoryId=repositoryForm.getRepository().getId();
		//按条件查询分页数据
		List<RepositoryDispatchCityVO> list=repositoryService.getRepositoryDispatchCityByRepositoryId(repositoryId);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		repositoryForm.getRepository().setId(repositoryId);
		//返回列表页面
		return mapping.findForward("repositoryDispatchCity_list");
	}
	/**
	 * 初始化Option
	 * @param request
	 * @return void
	 */
	private void initOption(HttpServletRequest request){
		
		//初始化储备库状态复选框
		List<OptionObject> statusOptions = OptionUtil.repositoryStatusOptions();
		request.setAttribute("statusOptions", statusOptions);
		//初始化储备库状态复选框
		List<OptionObject> reasonOptions = OptionUtil.repositoryReasonOptions();
		request.setAttribute("reasonOptions", reasonOptions);
		
	}
	
	public ActionForward addRepositoryDispatchCityEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm repositoryForm =(RepositoryForm) actionform;
		//返回新增页面
		return mapping.findForward("add_repository_dispatchCity");
	}
	
	public ActionForward addRepositoryDispatchCity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RepositoryForm repositoryForm =(RepositoryForm) form;
		RepositoryDispatchCityVO repositoryDispatchCityVO=repositoryForm.getRepositoryDispatchCity();
		repositoryDispatchCityVO.setRepositoryId(repositoryForm.getRepository().getId());
		boolean flag=repositoryService.addRepositoryDispatchCity(repositoryDispatchCityVO);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), repositoryService.getExecuteMessage());
		if(flag){
			//修改最后修改时间，最后修改人
			updateLastModify(repositoryForm.getRepository().getId(),request);
			//新增成功返回列表页
			return getRepositoryDispatchCityByRepositoryId(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addRepositoryDispatchCityEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updRepositoryDispatchCityEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm repositoryForm =(RepositoryForm) actionform;
		RepositoryDispatchCityVO repositoryDispatchCityVO=repositoryService.loadRepositoryDispatchCity(repositoryForm.getRepositoryDispatchCity().getId());
		//对象不存在时返回列表页
		if(repositoryDispatchCityVO==null){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), false);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),"该对象不存在");
			return getRepositoryDispatchCityByRepositoryId(mapping,repositoryForm,request,response);
		}
		repositoryForm.setRepositoryDispatchCity(repositoryDispatchCityVO);
		repositoryForm.getRepository().setId(repositoryDispatchCityVO.getRepositoryId());
		//返回新增页面
		return mapping.findForward("upd_repository_dispatchCity");
	}
	
	public ActionForward updRepositoryDispatchCity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RepositoryForm repositoryForm =(RepositoryForm) form;
		RepositoryDispatchCityVO repositoryDispatchCityVO=repositoryForm.getRepositoryDispatchCity();
		boolean flag= repositoryService.updRepositoryDispatchCity(repositoryDispatchCityVO);
		repositoryForm.getRepository().setId(repositoryDispatchCityVO.getRepositoryId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), repositoryService.getExecuteMessage());
		if(flag){
			//修改最后修改时间，最后修改人
			updateLastModify(repositoryDispatchCityVO.getRepositoryId(),request);
			//新增成功返回列表页
			return getRepositoryDispatchCityByRepositoryId(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return updRepositoryDispatchCityEntity(mapping,form,request,response);
		}
	}
	public ActionForward delRepositoryDispatchCity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm repositoryForm =(RepositoryForm) form;
		boolean flag=repositoryService.delRepositoryDispatchCity(repositoryForm.getRepositoryDispatchCity().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), repositoryService.getExecuteMessage());
		//返回列表页
		return getRepositoryDispatchCityByRepositoryId(mapping,repositoryForm,request,response);
	}
	
	
	public ActionForward repositoryDetailEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm repositoryForm =(RepositoryForm) actionform;
		RepositoryEntity repositoryEntity=repositoryService.loadRepositoryEntity(repositoryForm.getRepository().getId());
		repositoryForm.setRepositoryEntity(repositoryEntity);
		initOption(request);
		request.setAttribute("list", repositoryEntity.getRepositoryDispatchCity());
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		//返回新增页面
		return mapping.findForward("repository_detail");
	}
	
	public ActionForward selectRepositoryList(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		RepositoryForm rf =(RepositoryForm) actionform;
		RepositoryQueryVO query = rf.getRepositoryQuery();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("selectRepository", request);
		List<RepositorySelectBean> list = repositoryService.selectRepositoryList(query, tools);
		request.setAttribute("list", list);
		return mapping.findForward("select");
	}
	
	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RepositoryForm repositoryForm =(RepositoryForm) form;
		RepositoryQueryVO repositoryQuery = repositoryForm.getRepositoryQuery();
		Map<Integer,String[]> row=new HashMap<Integer,String[]>();
		String[] title=new String[]{};
		String[] title1=new String[]{"状态","原因","监管员姓名","身份证号","性别","出生日期",
				"婚姻状况","民族","学历","毕业院校","专业","户口所在地","现住址"
				,"招聘渠道","推荐人","推荐人岗位","推荐人联系方式","面试人","面试评分","面试点评","属性","培训专员","培训时间","结束时间"
				,"经销商","合作银行（总）","合作银行（分）","合作银行（支）","品牌","地址","培训人","联系方式","员工工号","培训内容（显务）"
				,"培训内容（入职基础知识）","考核得分","考核总评（电脑水平）","考核总评（理论知识）","考核总评（沟通及其它）","备注"};
		String[] title2=new String[]{"可派驻城市（省）","可派驻城市（市）","可派驻城市（区/县）"};
		//按条件查询分页数据
		List<RepositoryEntity> list = repositoryService.searchExportList(repositoryQuery);
		if(list!=null && list.size()>0){
			for(RepositoryEntity repositoryEntity:list){
				String[] currTitle=new String[]{};
				String[] title3=new String[]{};
				//储备库招聘信息
				RepositoryVO repository=repositoryEntity.getRepository();
				//储备库培训信息
				RepositoryTrainVO repositoryTrain=repositoryEntity.getRepositoryTrain();
				DealerQueryBean dealer=null;
				String headBank="";
				String subBank="";
				String branchBank="";
				if(repositoryTrain!=null){
					//经销商
					DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
					try {
						dealer=dealerByIdJsonAction.getDealer(repositoryTrain.getDealer());
						if(dealer!=null){
							String bank=dealer.getBankName();
							String[] banks=bank.split("/");
							if(banks!=null && banks.length==3){
								headBank=banks[0];
								subBank=banks[1];
								branchBank=banks[2];
							}else if(banks!=null && banks.length==2){
								headBank=banks[0];
								subBank=banks[1];
							}else if(banks!=null && banks.length==1){
								headBank=banks[0];
							}
						}else{
							dealer=new DealerQueryBean();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					repositoryTrain=new RepositoryTrainVO();
					dealer=new DealerQueryBean();
				}
				//储备库可派驻城市列表
				List<RepositoryDispatchCityVO> repositoryDispatchCity=repositoryEntity.getRepositoryDispatchCity();
				//监管员基本信息
				SupervisorJsonAction supervisorJsonAction=new SupervisorJsonAction();
				SupervisorBaseInfoJsonVO supervisor=supervisorJsonAction.getSupervisorBaseInfoJsonVOById(repository.getSupervisorId());
				
				String[] dispatchCityStr=new String[]{};
				if(repositoryDispatchCity !=null && repositoryDispatchCity.size()>0){
					for(RepositoryDispatchCityVO dispatchCity:repositoryDispatchCity){
						String[] d=new String[]{dispatchCity.getProvince(),dispatchCity.getCity(),dispatchCity.getCounty()};
						dispatchCityStr = (String[]) ArrayUtils.concat(dispatchCityStr, d);
						title3= (String[]) ArrayUtils.concat(title3, title2);
					}
				}
				String[] result=new String[]{RepositoryStatus.getNameByCode(repository.getStatus()),RepositoryReason.getNameByCode(repository.getReason()),
						supervisor.getName(),supervisor.getIdNumber(),supervisor.getGender(),supervisor.getBirthdayStr(),
						supervisor.getFertilityState(),supervisor.getNation(),supervisor.getEducationBackground(),supervisor.getGraduateSchool(),
						supervisor.getMajor(),supervisor.getRegisteredAddress(),supervisor.getLiveAddress(),
						supervisor.getRecommendChannel(),supervisor.getRecommenderName(),supervisor.getRecommenderPosition(),supervisor.getRecommenderPhone(),
						repository.getInterviewee(),String.valueOf(repository.getInterviewScore()),repository.getInterviewComment(),
						repository.getAttribute(),repositoryTrain.getTrainSpecialist(),repositoryTrain.getStartTime()==null?"":DateUtil.getStringFormatByDate(repositoryTrain.getStartTime(),"yyyy-MM-dd"),
						repositoryTrain.getEndTime()==null?"":DateUtil.getStringFormatByDate(repositoryTrain.getEndTime(),"yyyy-MM-dd"),dealer.getDealerName(),
						headBank,subBank,branchBank,dealer.getBrand(),dealer.getAddress(),repositoryTrain.getTrainer(),
						repositoryTrain.getContactNumber(),repositoryTrain.getStaffNo(),repositoryTrain.getTrainingContent(),
						repositoryTrain.getTrainingContentBasic(),String.valueOf(repositoryTrain.getAssessmentScore()),repositoryTrain.getAssessmentComputerScore(),
						repositoryTrain.getAssessmentTheoryScore(),repositoryTrain.getAssessmentCommunicateScore(),repositoryTrain.getRemark()
						};
				
				row.put(repository.getId(),(String[]) ArrayUtils.concat(result,dispatchCityStr));
				currTitle=(String[]) ArrayUtils.concat(title1, title3);
				if(currTitle.length>title.length){
					title=currTitle;
				}
			}
		}
		IExportFile export = new ExportFileExcelImpl();
		try {
			export.export(list, new RepositoryExportRowMapper(row,title), export.createDefaultFileName("储备库"),"储备库", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回列表页面
		return null;
	}

	//修改最后修改人，最后修改时间
	public void updateLastModify(int repositoryId,HttpServletRequest request){
		RepositoryVO repository=repositoryService.get(repositoryId);
		UserSession user = UserSessionUtil.getUserSession(request);
		repository.setLastModifyTime(new Date());
		repository.setLastModifyUser(user.getUser().getId());
		repositoryService.update(repository);
	}
}
