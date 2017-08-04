package com.zd.csms.windcontrol.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.file.util.UploadFileUtil;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.messagequartz.contants.MsgUrlContant;
import com.zd.csms.messagequartz.model.InspectRemindInfoVO;
import com.zd.csms.messagequartz.model.InspectRemindVO;
import com.zd.csms.messagequartz.service.InspectRemindService;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.windcontrol.constants.EditStatusConstants;
import com.zd.csms.windcontrol.constants.RecordTypeConstants;
import com.zd.csms.windcontrol.dao.IInspectionDAO;
import com.zd.csms.windcontrol.dao.WindControlDAOFactory;
import com.zd.csms.windcontrol.model.Image;
import com.zd.csms.windcontrol.model.InspectionCommunionVO;
import com.zd.csms.windcontrol.model.InspectionInfoVO;
import com.zd.csms.windcontrol.model.InspectionItemVO;
import com.zd.csms.windcontrol.model.InspectionPictureVO;
import com.zd.csms.windcontrol.model.InspectionRecordVO;
import com.zd.csms.windcontrol.model.InspectionResultVO;
import com.zd.csms.windcontrol.model.InspectionSupervisorVO;
import com.zd.csms.windcontrol.model.ProblemRecordVO;
import com.zd.csms.windcontrol.model.SupervisorRecordVO;
import com.zd.csms.windcontrol.querybean.InspectionLedgerQueryBean;
import com.zd.csms.windcontrol.querybean.InspectionListRowMapper;
import com.zd.csms.windcontrol.querybean.InspectionReportQueryBean;
import com.zd.csms.windcontrol.querybean.InspectionReportRowMapper;
import com.zd.csms.windcontrol.querybean.ReportQuery;
import com.zd.csms.windcontrol.web.form.InspectionForm;
import com.zd.csms.windcontrol.web.form.InspectionLedgerForm;
import com.zd.tools.file.FileUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;


public class InspectionService extends ServiceSupport {
	private IInspectionDAO dao = WindControlDAOFactory.getInspectionDAO();
	private UploadfileService ufService = new UploadfileService();

	private static int[] role = new int[] { 
		RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
		RoleConstants.BUSINESS_AMALDAR.getCode(),
		RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
		RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
		RoleConstants.MARKET_AMALDAR.getCode(),
		RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
		RoleConstants.WINDCONRTOL_INTERNAL.getCode(),
		RoleConstants.WINDCONRTOL_EXTERNAL.getCode(),
		RoleConstants.WINDCONRTOL_DATA.getCode(),
		RoleConstants.VIDEO_AMALDAR.getCode()};
	/**
	 * 根据基本信息Id查询对应的巡检报告-基本信息
	 * 
	 * @param id
	 * @return
	 */
	public InspectionInfoVO getInspectionInfoVO(int id) {
		return dao.get(InspectionInfoVO.class, id, new BeanPropertyRowMapper(
				InspectionInfoVO.class));
	}

	/**
	 * 根据基本信息Id查询对应的巡检报告-检查内容记录
	 * 
	 * @param infoId
	 * @return
	 */
	public InspectionItemVO findItembyId(int id) {
		return dao.get(InspectionItemVO.class, id, new BeanPropertyRowMapper(
				InspectionItemVO.class));
	}

	/**
	 * 检查过程中发现的问题及监管员的优缺点
	 * 
	 * @param infoId
	 * @return
	 */
	public Map<String, List<InspectionRecordVO>> findRecordbyInfoId(int infoId) {
		Map<String, List<InspectionRecordVO>> mapRecord = null;
		List<InspectionRecordVO> records = dao.findRecordbyInfoId(infoId);
		if (records != null && records.size() > 0) {
			mapRecord = new HashMap<String, List<InspectionRecordVO>>(2);
			mapRecord.put("problems", null);
            mapRecord.put("supervisors", null);
			int type1 = RecordTypeConstants.PROBLEM.getCode();
			List<InspectionRecordVO> problemRecords = new ArrayList<InspectionRecordVO>();
			List<InspectionRecordVO> meritsanddemeritss = new ArrayList<InspectionRecordVO>();
			for (InspectionRecordVO rec : records) {
				if (rec.getRecord_type() == type1) {
					problemRecords.add(rec);
				} else {
					meritsanddemeritss.add(rec);
				}
			}
			if (problemRecords.size() > 0) {
				mapRecord.put("problems", problemRecords);
			}

			if (meritsanddemeritss.size() > 0) {
				mapRecord.put("supervisors", meritsanddemeritss);
			}

		}

		return mapRecord;
	}

	/**
	 * 查询与店方沟通
	 * 
	 * @param infoId
	 * @return
	 */
	public InspectionCommunionVO findCommunionbyId(int id) {
		return dao.get(InspectionCommunionVO.class, id,
				new BeanPropertyRowMapper(InspectionCommunionVO.class));
	}

	/**
	 * 巡店报告监管员基本情况
	 * 
	 * @param infoId
	 * @return
	 */
	public InspectionSupervisorVO findSupervisorbyId(int id) {
		return dao.get(InspectionSupervisorVO.class, id,
				new BeanPropertyRowMapper(InspectionSupervisorVO.class));
	}

	/**
	 * 查询巡店总结
	 */
	public InspectionResultVO findResultbyId(int id) {
		return dao.get(InspectionResultVO.class, id, new BeanPropertyRowMapper(
				InspectionResultVO.class));
	}

	/**
	 * 新增巡检报告
	 * 
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public boolean addInfo(Object object) throws Exception {
		return dao.add(object);

	}

	/**
	 * 更新巡检报告-基本信息
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean updateInfo(InspectionInfoVO vo) throws Exception {
		return dao.update(vo);

	}

	/**
	 * 更新巡检报告-检查内容
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean updateItem(InspectionItemVO vo) throws Exception {
		return dao.update(vo);
	}

	/**
	 * 更新巡检报告-检查过程中发现的问题及监管员的优缺点
	 * 
	 * @param problemRecords
	 * @param supervisorRecords
	 * @param inspectionId
	 * @return
	 * @throws Exception
	 */
	public boolean updateRecord(List<ProblemRecordVO> problemRecords,
			List<SupervisorRecordVO> supervisorRecords, int inspectionId)
			throws Exception {
		boolean fag = false;
		if (dao.deleteRecord(inspectionId)) {
			fag = addRecord(problemRecords, supervisorRecords, inspectionId);
		}
		return fag;

	}

	/**
	 * 更新巡检报告-与店方沟通
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean updateCommunion(InspectionCommunionVO vo) throws Exception {
		return dao.update(vo);
	}

	/**
	 * 更新巡检报告-监管员信息
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean updateSupervisor(InspectionSupervisorVO vo) throws Exception {
		return dao.update(vo);
	}

	/**
	 * 更新巡检报告-巡店总结
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean updateResult(InspectionResultVO vo) throws Exception {
		return dao.update(vo);
	}

	/**
	 * 新增巡检报告-检查过程中出现的问题及监管员的优缺点
	 * 
	 * @param problemRecords
	 * @param supervisorRecords
	 * @param inspectionId
	 */
	public boolean addRecord(List<ProblemRecordVO> problemRecords,
			List<SupervisorRecordVO> supervisorRecords, int inspectionId) {
		if (problemRecords == null && supervisorRecords == null)
			return false;

		InspectionRecordVO inspectionrecordvo = null;
		try {
			if (problemRecords != null && problemRecords.size() > 0) {
				int type1 = RecordTypeConstants.PROBLEM.getCode();
				for (ProblemRecordVO problemrecord : problemRecords) {
					inspectionrecordvo = new InspectionRecordVO();
					inspectionrecordvo.setInspectionId(inspectionId);
					inspectionrecordvo.setRecord_type(type1);
					inspectionrecordvo.setContent(problemrecord
							.getProblemContent());
					inspectionrecordvo.setResult(problemrecord
							.getProblemResult());

					dao.add(inspectionrecordvo);
				}
			}

			if (supervisorRecords != null && supervisorRecords.size() > 0) {
				int type2 = RecordTypeConstants.MERITSANDDEMERITS.getCode();
				for (SupervisorRecordVO supervisorrecord : supervisorRecords) {
					inspectionrecordvo = new InspectionRecordVO();
					inspectionrecordvo.setInspectionId(inspectionId);
					inspectionrecordvo.setRecord_type(type2);
					inspectionrecordvo.setContent(supervisorrecord
							.getSupervisorContent());
					inspectionrecordvo.setResult(supervisorrecord
							.getSupervisorResult());
					dao.add(inspectionrecordvo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	// 巡检报告-新增店面图片
	public Image addPicture(FormFile file, HttpServletRequest request)
			throws Exception {
		UserSession userSession = UserSessionUtil.getUserSession(request);
		// 保存文件到数据库中并获取对应的id
		int uplId = UploadFileUtil.savefile(file, userSession, request);
		// 拼接Image对象信息
		Image image = null;
		if (uplId > 0) {
			String filePath = (String) request.getAttribute("filePath");
			// 保存巡检报告与店面图片信息到数据库中
			int inspectionId = Integer.parseInt(request
					.getParameter("inspectionId"));
			InspectionPictureVO pic = new InspectionPictureVO();
			pic.setPictureId(uplId);
			pic.setInspectionId(inspectionId);

			boolean fag = addInfo(pic);
			if (fag) {
				image = new Image();
				image.setUrl("/showImg.do?method=downLoadFile&filePath="
						+ filePath + "&fileName=" + file.getFileName());// 下载地址
				image.setThumbnailUrl("/showImg.do?method=showThumbnailImg&filePath="
						+ filePath);// 上传完成后的缩略图地址
				image.setDeleteUrl("/windcontrol/inspection.do?method=deletePicture&fileId="
						+ uplId);// 删除图片的地址
				image.setName(file.getFileName());

				image.setDeleteType("GET");// 提交方式
			}

		}

		return image;
	}

	/**
	 * 根据基本信息Id查询对应的图片列表
	 * 
	 * @param id
	 * @return
	 */
	public List<InspectionPictureVO> findPictureIdByInfoId(int id) {
		return dao.findPictureByInfoId(id);
	}

	/**
	 * 根据图片Id删除对应的图片
	 * 
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	public boolean deletePicture(int fileId) throws Exception {
		UploadfileVO file = ufService.loadUploadfileById(fileId);
		String filePath = file.getFile_path();
		filePath = FileUtil.getFileFolder() + File.separator + filePath;
		boolean fag = false;
		// 根据图片Id删除对应的图片
		if (FileUtil.delFile(filePath)) {
			try {
				// 根据图片Id删除对应的图片信息
				if (dao.delete(UploadfileVO.class, fileId)){
					// 根据图片Id删除对应的巡检报告-店面图片记录
					fag = dao.deletePicture(fileId);
				}
			} catch (Exception e) {
				fag=false ;
				e.printStackTrace();
			}

		}
		return fag;
	}

	//TODO
	@SuppressWarnings("unchecked")
	public List<UploadfileVO> findPictureById(int id)  {
		List<InspectionPictureVO> picList = findPictureIdByInfoId(id);
		List<UploadfileVO> list = null;
		if (picList != null && picList.size() > 0) {
			list = new ArrayList<UploadfileVO>();
			for (InspectionPictureVO pic : picList) {
				UploadfileVO uplFile = null;
				try {
					uplFile = ufService.loadUploadfileById(pic
							.getPictureId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (uplFile != null) {
					String name = uplFile.getFile_name();
					int index = name.lastIndexOf(".");
					if (index > 0) {
						uplFile.setFile_name(name.substring(0, index));
					}

					list.add(uplFile);
				}

			}
		}
		
		
		if(list!= null && list.size() > 0){
			Collections.sort(list, new Comparator<UploadfileVO>(){
			    public int compare(UploadfileVO vo1, UploadfileVO vo2) {// 实现排序的方法  
			    	if(vo1.getId() < vo2.getId() ){
			    		return -1;
			    	}else if(vo1.getId() > vo2.getId()){
			    		return 1;
			    	}else{
			    		return 0;
			    	}
		        }  
				
			});
		}else{
			list = new ArrayList<UploadfileVO>();
		}
		

		return list;
	}

	/**
	 * 内控专员:巡检报告列表
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionListRowMapper> findReportList(InspectionForm inspecForm,
			HttpServletRequest request) {
		ReportQuery query = inspecForm.getQuery();
        IThumbPageTools tools = ToolsFactory.getThumbPageTools("pagelist",
				request);

		tools.saveQueryVO(query);
		return dao.findReportList(query, tools);
	}

	/**
	 * 巡视专员:巡检报告列表
	 * 
	 * @param inspecForm
	 * @param request
	 * @return
	 */
	public List<InspectionListRowMapper> findInspectionList(
			InspectionForm inspecForm, HttpServletRequest request) {
		ReportQuery query = inspecForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pagelist",
				request);

		tools.saveQueryVO(query);
		return dao.findInspectionList(query, tools);
	}
	
	/*
	  * 判断某用户是否拥有某个角色的权限
	  *
	  * @param user_id
	  * @param role_id
	  * @return true or false
	*/
	public boolean judgeUserRole(int user_id, int role_id){
		return dao.judgeUserRole(user_id, role_id);
	}

	/**
	 * 巡检报告台账列表
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionLedgerQueryBean> findReportLedgerList(
			InspectionLedgerForm query, IThumbPageTools tools) {
		return dao.findReportLedgerList(query, tools);
	}

	/**
	 * 提交巡检报告
	 * 
	 * @param reportStatus
	 * @param vo
	 */
	public boolean submitReport(int id) {
		boolean fag = false;
		if (id > 0) {
			InspectionInfoVO vo =getInspectionInfoVO(id);
			if (vo != null) {
				try {
				InspectRemindService insrserService = new InspectRemindService();
				InspectRemindVO iRemindVO = new InspectRemindVO();
				InspectRemindInfoVO infoVO = new InspectRemindInfoVO();
				infoVO = insrserService.getInfoVO(id);
				if (infoVO != null) {
					iRemindVO.setDirector(infoVO.getOutControlUserName());
					iRemindVO.setMerchantname(infoVO.getDealername());
					iRemindVO.setUserId(infoVO.getOutcontroluserid());
				}
				iRemindVO.setBankname(vo.getBank());
				iRemindVO.setBrandname(vo.getBrand());
				iRemindVO.setCreateDate(new Date());
				iRemindVO.setIsread(MsgIsContants.NOREAD.getCode());
				iRemindVO.setMerchantname(vo.getDealer_name());
				iRemindVO.setMessagetype(MessageTypeContant.COINSPECTI.getCode());
				iRemindVO.setMODIFY_TIME(vo.getModify_time());
				
				
				RoleService rs = new RoleService();
				UserRoleQueryVO urquery = new UserRoleQueryVO();
				
				for (int i : role) {
					urquery.setRole_id(i);
					List<UserRoleVO> urList = rs.searchUserRoleList(urquery);
					fag = addSRCMmessage(urList, iRemindVO);
				}
				
				if (dao.updateModifyTime(new Date(), id)) {
					fag = dao.updateReportStatus(EditStatusConstants.SUBMIT.getCode(), id);
				}
				} catch (Exception e) {
					fag = false;
					e.printStackTrace();
				}
			}

		}
		
		return fag;

	}
	
	
	private boolean addSRCMmessage(List<UserRoleVO> urList,InspectRemindVO iRemindVO) throws Exception{
		MessageService ms = new MessageService();
		InspectRemindService irService = new InspectRemindService();
		UserService userService = new UserService();
		UserVO userVO = null;
		boolean flag = false;
		if(urList != null && urList.size()>0){
			for(UserRoleVO ur : urList){
				if (ur.getUser_id() == iRemindVO.getUserId()) {
					continue;//风控专员（除自己以外的其他人）
				}
				userVO = userService.get(ur.getUser_id());
				if (userVO != null && userVO.getState() == 1) {
					MessageVO msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.COINSPECTI.getCode());
					if (msVo != null) {
						//该用户该类型提醒信息数量增加1
						msVo.setName(String.valueOf(Integer.parseInt(msVo.getName())+1));//数量增加1
						if (msVo.getIsread()==2) {
							msVo.setIsread(1);
						}
						flag = ms.updMessage(msVo);
						if (flag) {
							iRemindVO.setUserId(ur.getUser_id());
							iRemindVO.setMessageId(msVo.getId());
							flag = irService.addInspectRemindMessage(iRemindVO);
							if (!flag) {
								return false;
							}
						}else {
							this.setExecuteMessage("提交失败");
						}
					}else {
						MessageUtil.addMsg(ur.getUser_id(),String.valueOf(1),MsgUrlContant.UNINSPECTINSPECTION.getName(),1,MsgIsContants.NOREAD.getCode(),MessageTypeContant.COINSPECTI.getCode(),ClientTypeConstants.WINDCONRTOL.getName());
						msVo = ms.loadMsgByUserAndType(ur.getUser_id(), MessageTypeContant.COINSPECTI.getCode());
						iRemindVO.setUserId(ur.getUser_id());
						iRemindVO.setMessageId(msVo.getId());
						flag = irService.addInspectRemindMessage(iRemindVO);
					}
				}
			}
		}
		return flag;
	}
	

	/**
	 * 新增，修改，提交，巡检报告更新巡检报告状态
	 * 
	 * @param reportStatus
	 * @param id
	 * @return
	 */
	public boolean updateReportStatus(int reportStatus, int id) {
		return dao.updateReportStatus(reportStatus, id);

	}
	

	public InspectionReportQueryBean getInspectionReport(int id) throws Exception {
		InspectionReportRowMapper report=dao.getInspectionReport(id);
		InspectionReportQueryBean reportBean=null;
		if(report!=null){
			 reportBean=new InspectionReportQueryBean();
			 InspectionInfoVO info=new InspectionInfoVO();
			// 巡检报告-检查内容
			 InspectionItemVO item= new InspectionItemVO();

			// 巡检报告与店方沟通情况
			InspectionCommunionVO communion=new InspectionCommunionVO();

			// 巡检报告监管员信息
			 InspectionSupervisorVO supervisor=new InspectionSupervisorVO();
			// 巡检总结
		    InspectionResultVO inspecResult=new InspectionResultVO();
			BeanUtils.copyProperties(report, info);
			BeanUtils.copyProperties(report, item);
			BeanUtils.copyProperties(report, communion);
			BeanUtils.copyProperties(report, supervisor);
			supervisor.setName(report.getSupervisor_name());
			supervisor.setAddress(report.getSupervisor_address());
			BeanUtils.copyProperties(report, inspecResult);
			reportBean.setInfo(info);
			reportBean.setItem(item);
			reportBean.setCommunion(communion);
			reportBean.setSupervisor(supervisor);
			reportBean.setInspecResult(inspecResult);
			reportBean.setRecords(findRecordbyInfoId(id));	
		}
	
		return reportBean;
	}

	/*
	 * 需求38 -- 导出巡检报告台账
	 * @time 20170518
	 */
	public List<InspectionLedgerQueryBean> ExtInspecReportLenger(
			InspectionLedgerForm query) {
		return dao.ExtInspecReportLenger(query);
	}

}
