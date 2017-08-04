package com.zd.csms.roster.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.zd.core.util.CommonUtil;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.model.RepositoryDispatchCityVO;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.PostChangeVO;
import com.zd.csms.roster.model.RosterEntity;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.roster.web.excel.RosterExportRowMapper;
import com.zd.csms.roster.web.form.RosterForm;
import com.zd.csms.supervisory.model.SupervisorBaseInfoJsonVO;
import com.zd.csms.supervisory.web.jsonaction.SupervisorJsonAction;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.service.TransferService;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class RosterAction extends ActionSupport{
	
	RosterService rosterService=new RosterService();
	
	public ActionForward updRosterEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		RosterForm rosterForm =(RosterForm) actionform;
		RosterVO roster=rosterService.load(rosterForm.getRoster().getId());
		UserSession user = UserSessionUtil.getUserSession(request);
		/*List<PostChangeVO> postChangeList;
		int months = 0;
		int years = 0;*/
		
		if(roster!=null){
			//自动更新账户列表
			roster.setSystemAccount(rosterService.getLoginidList(roster.getRepositoryId()));
			
			//更新在职时长
			/*postChangeList = rosterService.searchPostChangeListByRosterId(roster.getId());
			if(postChangeList!=null&&postChangeList.size()>0){
				for(PostChangeVO pcvo:postChangeList){
					if(pcvo.getMissionDate()!=null&&pcvo.getDimissionDate()!=null){
						try {
							months = months + CommonUtil.countMonths(pcvo.getMissionDate().toString(),pcvo.getDimissionDate().toString(),"yyyy-MM-dd HH:mm:ss");
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			years = months / 12;
			months = months % 12;
			roster.setDriveMonth(months);
			roster.setDriveYear(years);*/
			//显示司龄年/月
			if (roster.getRepositoryId() >0) {
				TransferService transferService = new TransferService();
				List<TransferRepositoryVO> tsList = transferService.searchLeaveTimeAndEntryTimeByRepositoryId(roster.getRepositoryId());
				if (tsList!=null) {
					 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					 String leavetime ;
					 String entrytime ;
					 int months = 0;
					 for (int i = 0; i < tsList.size(); i++) {
						if (tsList.get(i).getLeaveTime() !=null && tsList.get(i).getEntryTime() !=null) {
							leavetime = sdf.format(tsList.get(i).getLeaveTime());
							entrytime = sdf.format(tsList.get(i).getEntryTime());
							try {
								months =months + CommonUtil.countMonths(leavetime,entrytime,"yyyy-MM-dd");
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}else {
							leavetime = "0";
							entrytime = "0";
						}
					 }
					// months += months;
					// int year = (int) Math.floor(months/12);
					int year = months / 12;
					 months = months % 12;
					 roster.setDriveYear(year);
					 roster.setDriveMonth(months);
					 System.out.println(year+"/"+months);
				}
			}
			
			//更新服役状态
			RepositoryVO rvo = rosterService.loadRepositoryById(roster.getRepositoryId());
			if(rvo.getStatus()==1){
				roster.setEnlistStatus("在职");
			}else if(rvo.getStatus()==2){
				roster.setEnlistStatus("有效");
			}else{
				roster.setEnlistStatus("离职");
			}
			
			//更新驻派属性
			BusinessTransferVO btvo = rosterService.loadBusinessTransferByRId(roster.getRepositoryId());
			//监管员来源 1：属地 2：外派
			if(btvo !=null && btvo.getSupervisorSource()==1){
				roster.setDispatchAttribute("属地");
			}else{
				roster.setDispatchAttribute("外派");
			}
			
			//派驻地址属性
			DealerVO mdvo = rosterService.loadDealerByRId(roster.getRepositoryId());
			if (mdvo != null) {
				roster.setDispatchCity(mdvo.getAddress());
			}
			
			
			//添加用户状态
			rosterForm.setClient_type(user.getUser().getClient_type());
		}
		
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		//对象不存在时返回列表页
		if(roster==null){
			return rosterPageList(mapping,rosterForm,request,response);
		}
		rosterForm.setRoster(roster);
		request.setAttribute("client_type", user.getUser().getClient_type());
		//返回新增页面
		return mapping.findForward("upd_roster");
	}
	
	public ActionForward updRoster(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RosterForm rosterForm =(RosterForm) form;
		RosterVO roster=rosterForm.getRoster();
		UserSession user = UserSessionUtil.getUserSession(request);
		roster.setLastModifyTime(new Date());
		roster.setLastModifyUser(user.getUser().getId());
		boolean flag= rosterService.update(roster);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), rosterService.getExecuteMessage());
		if(flag){
			//新增成功返回列表页
			return rosterPageList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return updRosterEntity(mapping,form,request,response);
		}
	}
	public ActionForward delRoster(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RosterForm rosterForm =(RosterForm) form;
		boolean flag=rosterService.delete(rosterForm.getRoster().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), rosterService.getExecuteMessage());
		//返回列表页
		return rosterPageList(mapping,rosterForm,request,response);
	}
	/**
	 * 返回分页列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rosterPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RosterForm rosterForm =(RosterForm) form;
		RosterQueryVO rosterQuery=rosterForm.getRosterQuery();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("rosterList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(rosterQuery);
		
		//按条件查询分页数据
		List<RosterEntity> list = rosterService.searchPageList(rosterQuery,tools);
		
		for(RosterEntity rosterList: list){
			if(rosterList.getPostChangeList().size()>0){
				if(rosterList.getPostChangeList().get(0).getMissionDate()!=null){
					rosterList.getRoster().setMissionDateNTT(rosterList.getPostChangeList().get(0).getMissionDate());
				}
				if(rosterList.getPostChangeList().get(0).getMissionNature()!=null){
					rosterList.getRoster().setMissionNatureNTT(rosterList.getPostChangeList().get(0).getMissionNature());
				}
				if(rosterList.getPostChangeList().get(0).getDimissionDate()!=null){
					rosterList.getRoster().setDimissionDateNTT(rosterList.getPostChangeList().get(0).getDimissionDate());
					if(rosterList.getPostChangeList().get(0).getDimissionNature()!=null){
						rosterList.getRoster().setDimissionNatureNTT(rosterList.getPostChangeList().get(0).getDimissionNature());
					}
				}else{
					if(rosterList.getPostChangeList().size()>1){
						if(rosterList.getPostChangeList().get(1).getDimissionDate()!=null){
							rosterList.getRoster().setDimissionDateNTT(rosterList.getPostChangeList().get(1).getDimissionDate());
							if(rosterList.getPostChangeList().get(1).getDimissionNature()!=null){
								rosterList.getRoster().setDimissionNatureNTT(rosterList.getPostChangeList().get(1).getDimissionNature());
							}
						}
					}
				}
				
			}
		}
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		//返回列表页面
		return mapping.findForward("roster_list");
	}
	
	/**
	 * 返回列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public List<RosterEntity> RosterList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RosterForm supervisorForm =(RosterForm) form;
		RosterQueryVO supervisorQuery=supervisorForm.getRosterQuery();
		
		//按条件查询分页数据
		List<RosterEntity> list = rosterService.searchList(supervisorQuery);
		
		return list;
	}
	public ActionForward addPostChangeEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){

		//返回新增页面
		return mapping.findForward("add_postChange");
	}
	
	public ActionForward addPostChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RosterForm rosterForm =(RosterForm) form;
		PostChangeVO postChangep=rosterForm.getPostChange();
		postChangep.setRosterId(rosterForm.getRoster().getId());
		boolean flag=rosterService.addPostChange(postChangep);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), rosterService.getExecuteMessage());
		if(flag){
			//修改最后更新人，最后更新时间
			updateLastModify(rosterForm.getRoster().getId(),request);
			//新增成功返回列表页
			return postChangeList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return addPostChangeEntity(mapping,form,request,response);
		}
	}
	
	public ActionForward updPostChangeEntity(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		RosterForm rosterForm =(RosterForm) actionform;
		PostChangeVO postChange=rosterService.loadPostChange(rosterForm.getPostChange().getId());
		
		//对象不存在时返回列表页
		if(postChange==null){
			return postChangeList(mapping,rosterForm,request,response);
		}
		rosterForm.setPostChange(postChange);
		rosterForm.getRoster().setId(postChange.getRosterId());
		//返回新增页面
		return mapping.findForward("upd_postChange");
	}
	
	public ActionForward updPostChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		
		RosterForm rosterForm =(RosterForm) form;
		PostChangeVO postChange=rosterForm.getPostChange();
		boolean flag= rosterService.updatePostChange(postChange);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), rosterService.getExecuteMessage());
		rosterForm.getRoster().setId(postChange.getRosterId());
		if(flag){
			//修改最后更新人，最后更新时间
			updateLastModify(rosterForm.getRoster().getId(),request);
			//新增成功返回列表页
			return postChangeList(mapping,form,request,response);
		}else{
			//新增失败返回新增页面
			return updPostChangeEntity(mapping,form,request,response);
		}
	}
	public ActionForward delPostChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RosterForm rosterForm =(RosterForm) form;
		boolean flag=rosterService.deletePostChange(rosterForm.getPostChange().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), rosterService.getExecuteMessage());
		//返回列表页
		return postChangeList(mapping,rosterForm,request,response);
	}
	
	/**
	 * 返回列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward postChangeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RosterForm supervisorForm =(RosterForm) form;
		int rosterId=supervisorForm.getRoster().getId();
		//按条件查询分页数据
		List<PostChangeVO> list = rosterService.searchPostChangeListByRosterId(rosterId);
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		supervisorForm.getRoster().setId(rosterId);;
		//返回列表页面
		return mapping.findForward("postChange_list");	}
	
	public ActionForward getRosterDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RosterForm rosterForm =(RosterForm) form;
		int rosterId=rosterForm.getRoster().getId();
		RosterVO roster=rosterService.load(rosterId);
		//显示司龄年/月
		if (roster.getRepositoryId() >0) {
			TransferService transferService = new TransferService();
			List<TransferRepositoryVO> tsList = transferService.searchLeaveTimeAndEntryTimeByRepositoryId(roster.getRepositoryId());
			if (tsList!=null) {
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 String leavetime ;
				 String entrytime ;
				 int months = 0;
				 for (int i = 0; i < tsList.size(); i++) {
					if (tsList.get(i).getLeaveTime() !=null && tsList.get(i).getEntryTime() !=null) {
						leavetime = sdf.format(tsList.get(i).getLeaveTime());
						entrytime = sdf.format(tsList.get(i).getEntryTime());
						try {
							months = months + CommonUtil.countMonths(leavetime,entrytime, "yyyy-MM-dd");
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else {
						leavetime = "0";
						entrytime = "0";
					}
				 }
				 int year = months / 12;
				 months = months % 12;
				 roster.setDriveYear(year);
				 roster.setDriveMonth(months);
				 request.setAttribute("list", tsList);
			}
		}
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		//按条件查询分页数据
		//List<PostChangeVO> list = rosterService.searchPostChangeListByRosterId(rosterId);
		//将查询结果设置request中，用于页面显示
		//request.setAttribute("list", list);
		rosterForm.setRoster(roster);
		//返回列表页面
		return mapping.findForward("detail_roster");	
	}
	
	
	public ActionForward extExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RosterForm rosterForm =(RosterForm) form;
		RosterQueryVO rosterQuery = rosterForm.getRosterQuery();
		//按条件查询分页数据
		List<RosterEntity> list = rosterService.searchList(rosterQuery);
		Map<Integer,String[]> row=new HashMap<Integer,String[]>();
		String[] title=new String[]{};
		String[] title1=new String[]{"员工工号","监管员姓名","身份证号","性别","出生日期","婚姻状况","民族","学历","毕业院校","专业",
				"户口所在地","现住址","工资卡号","开户行","联系方式","编制类型","司龄（年）","司龄（月数）","入职日期","驻派属性",
				"驻派地区","服役状态","招聘渠道","推荐人姓名","推荐人背景","面试经手人","系统账号","系统密码"};
		String[] title2=new String[]{"上岗日期","上岗性质","离岗日期","离岗性质"};
		if(list!=null && list.size()>0){
			for(RosterEntity entity:list){
				String[] currTitle=new String[]{};
				String[] title3=new String[]{};
				RosterVO roster=entity.getRoster();
				if(roster!=null){
					//set驻派地区
					String dispatchCity="";
					RepositoryService repositoryService=new RepositoryService();
					RepositoryDispatchCityVO dispatchCityVO=repositoryService.loadDispatchCity(StringUtil.intValue(roster.getDispatchCity(),0));
					if(dispatchCityVO!=null){
						dispatchCity=dispatchCityVO.getProvince()+" "+dispatchCityVO.getCity()+" "+dispatchCityVO.getCounty();
					}
					roster.setDispatchCity(dispatchCity);
					//司龄（年）/司龄（月数）
					if (roster.getRepositoryId() >0) {
						TransferService transferService = new TransferService();
						List<TransferRepositoryVO> tsList = transferService.searchLeaveTimeAndEntryTimeByRepositoryId(roster.getRepositoryId());
						if (tsList!=null) {
							 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							 String leavetime ;
							 String entrytime ;
							 int months = 0;
							 for (int i = 0; i < tsList.size(); i++) {
								if (tsList.get(i).getLeaveTime() !=null && tsList.get(i).getEntryTime() !=null) {
									leavetime = sdf.format(tsList.get(i).getLeaveTime());
									entrytime = sdf.format(tsList.get(i).getEntryTime());
									try {
										months = months + CommonUtil.countMonths(leavetime,entrytime, "yyyy-MM-dd");
									} catch (Exception e) {
										e.printStackTrace();
									}
									
								}else {
									leavetime = "0";
									entrytime = "0";
								}
							 }
							 int year = months / 12;
							 months = months % 12;
							 roster.setDriveYear(year);
							 roster.setDriveMonth(months);
						}
					}
					//储备库招聘信息
					RepositoryVO repository=repositoryService.load(roster.getRepositoryId());
					if(repository==null){
						repository=new RepositoryVO();
					}
					//监管员基本信息
					SupervisorJsonAction supervisorJsonAction=new SupervisorJsonAction();
					SupervisorBaseInfoJsonVO supervisor=supervisorJsonAction.getSupervisorBaseInfoJsonVOById(roster.getSupervisorId());
					String[] result=new String[]{roster.getStaffNo(),
							supervisor.getName(),supervisor.getIdNumber(),supervisor.getGender(),supervisor.getBirthdayStr(),
							supervisor.getFertilityState(),supervisor.getNation(),supervisor.getEducationBackground(),supervisor.getGraduateSchool(),
							supervisor.getMajor(),supervisor.getRegisteredAddress(),supervisor.getLiveAddress(),
							roster.getPaycardNo(),roster.getOpenBank(),supervisor.getSelfTelephone(),roster.getOrganizeType(),String.valueOf(roster.getDriveYear()),
							String.valueOf(roster.getDriveMonth()),supervisor.getEntryTimeStr(),roster.getDispatchAttribute(),roster.getDispatchCity(),
							roster.getEnlistStatus(),supervisor.getRecommendChannel(),supervisor.getRecommenderName(),supervisor.getRecommenderPosition(),
							repository.getInterviewee(),roster.getSystemAccount(),roster.getSystemPassword()
					};
					//岗位轮换表记录
					List<PostChangeVO> postChangeList=entity.getPostChangeList();
					String[] postChangeStr=new String[]{};
					if(postChangeList !=null && postChangeList.size()>0){
						for(PostChangeVO postChange:postChangeList){
							String[] d=new String[]{DateUtil.getStringFormatByDate(postChange.getMissionDate(),"yyyy-MM-dd")
									,postChange.getMissionNature(),DateUtil.getStringFormatByDate(postChange.getDimissionDate(),"yyyy-MM-dd"),
									postChange.getDimissionNature()};
							postChangeStr = (String[]) ArrayUtils.concat(postChangeStr, d);
							title3= (String[]) ArrayUtils.concat(title3, title2);
						}
					}
					currTitle=(String[]) ArrayUtils.concat(title1, title3);
					if(currTitle.length>title.length){
						title=currTitle;
					}
					row.put(roster.getId(), (String[]) ArrayUtils.concat(result,postChangeStr)); 
				}
			}
		}
		
		IExportFile export = new ExportFileExcelImpl();
		try {
			export.export(list, new RosterExportRowMapper(row,title), export.createDefaultFileName("花名册"),"花名册", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回列表页面
		return null;
	}
	//修改最后修改人，最后修改时间
	public void updateLastModify(int rosterId,HttpServletRequest request){
		RosterVO roster=rosterService.load(rosterId);
		UserSession user = UserSessionUtil.getUserSession(request);
		roster.setLastModifyTime(new Date());
		roster.setLastModifyUser(user.getUser().getId());
		rosterService.update(roster);
	}
}
