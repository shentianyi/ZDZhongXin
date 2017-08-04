package com.zd.csms.supervisory.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.bank.bean.Gyl007;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.contants.CarOperationContants;
import com.zd.csms.supervisory.dao.ICarOperationDAO;
import com.zd.csms.supervisory.dao.ISuperviseImportDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.dao.mapper.CarOperationMapper;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.model.CarManualVO;
import com.zd.csms.supervisory.model.CarOperationQueryVO;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.model.SyncApplyInfoVO;
import com.zd.csms.supervisory.querybean.CarOperationBean;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


public class CarOperationService extends ServiceSupport {

	private ICarOperationDAO dao = SupervisorDAOFactory.getCarOperationDAO();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private ISuperviseImportDAO supervisorDao = SupervisorDAOFactory.getSuperviseImportDAO();
	private SuperviseImportService siservice = new SuperviseImportService();
	private CarManualService cservice = new CarManualService();
	private BankApproveService baservice = new BankApproveService();
	private static final CarOperationService CAR_OPERATION_SERVICE = new CarOperationService();
	public static CarOperationService getInstance(){
		return CAR_OPERATION_SERVICE;
	}
	public int addCarOperation(CarOperationVO vo) throws Exception {
		
		
		int id = Util.getID(CarOperationVO.class);
		
		vo.setId(id);
		
		dao.add(vo);
		return id;
	}
	
	public CarOperationVO loadCarOperationById(int id) throws Exception{
		CarOperationVO carOperation = dao.get(CarOperationVO.class, id,new CarOperationMapper());
		return carOperation;
	}
	
	public List<CarOperationVO> searchCarOperationList(CarOperationQueryVO query){
		return dao.searchCarOperationList(query);
	}
	
	public List<CarOperationVO> searchCarOperationListByPage(CarOperationQueryVO vo, IThumbPageTools tools){
		return dao.searchCarOperationListByPage(vo, tools);
	}
	
	public List<CarOperationVO> searchCarOperationByBankId(int bankid){
		return dao.searchCarOperationByBankId(bankid);
	}
	
	/**
	 * 根据车架号获取资源对象
	 * @param vin
	 * @return
	 */
	public SuperviseImportVO searchSupervise(String vin){
		return supervisorDao.searchSupervise(vin);
	}
	
	/**
	 * 根据交易流水号判断是否已同步
	 * @param serialNo
	 * @return
	 */
	public boolean serialNoExist(String serialNo){
		return supervisorDao.serialNoExist(serialNo);
	}
	
	/**
	 * 同步接口数据到数据库
	 * @param syncGyl025
	 * @param distribsetid
	 * @return
	 * @throws Exception   
	 */
	public boolean syncGyl007(UserVO user, List<Gyl007> syncGyl007, String applyType, String status ,String serialNo) throws Exception{
		//取车所有架号
		ArrayList<String> chassisNos = new ArrayList<String>();
		if (null != syncGyl007 && syncGyl007.size() > 0) {
			for (Gyl007 gyl007 : syncGyl007) {
				if (null != gyl007) {
					chassisNos.add(gyl007.getChassisNo());
				}
			}
		}
		int sid = 0;
		boolean flag = false;
		List<UserVO> uList = null;
		SyncApplyInfoVO syncApplyInfo = null;
		SuperviseImportVO vo = null;
		BankApproveVO bavo = null;
		CarManualVO cvo = null;
		if (StringUtil.isNotEmpty(applyType) && StringUtil.isNotEmpty(status) 
				&& applyType.equals(CarOperationContants.APPLY_TYPE_IN.getCode()) && status.equals(CarOperationContants.APPROVE_STATUS_YES.getCode())) {//入库 审批通过
			if (null != chassisNos && chassisNos.size() > 0) {
				for (int i = 0; i < chassisNos.size(); i++) {
					vo = supervisorDao.searchSupervise(chassisNos.get(i));
					if (null != vo) {
						vo.setApply(0);
						vo.setStoragetime(new Date());
						siservice.updSuperviseImport(vo,0);
						cvo = new CarManualVO();
						cvo.setSid(vo.getId());
						cvo.setUserid(user.getId());
						cvo.setReceiveid(0);
						cservice.addCarManual(cvo);
						
						
						bavo = baservice.searchBankApproveBySid(vo.getId(),2);
						if (null != bavo) {
							bavo.setState(2);
							bavo.setType(2);
							bavo.setApprovetime(new Date());
							baservice.updBankApprove(bavo);
						}
						sid = vo.getId();
					}
				}
				if(sid!=0){
					uList = siservice.searchUserBySId(sid);
					if(uList != null && uList.size()>0){
						for(UserVO ur : uList){
							MessageUtil.addMsg(ur.getId(), "监管物入库", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CARSTORAGE.getCode(),vo.getCreateuserid());
						}
					}
				}
				syncApplyInfo = new SyncApplyInfoVO();
				syncApplyInfo.setSerialNo(serialNo);
				supervisorDao.add(syncApplyInfo);
				flag = true;
			}
		}else if (StringUtil.isNotEmpty(applyType) && StringUtil.isNotEmpty(status)
				&& applyType.equals(CarOperationContants.APPLY_TYPE_IN.getCode()) && status.equals(CarOperationContants.APPROVE_STATUS_NOT.getCode())) {//入库  审批未通过
			if (null != chassisNos && chassisNos.size() > 0) {
				for (int i = 0; i < chassisNos.size(); i++) {
					vo = supervisorDao.searchSupervise(chassisNos.get(i));
					vo.setApply(0);
					vo.setState(1);
					siservice.updSuperviseImport(vo,0);
					
					bavo = baservice.searchBankApproveBySid(vo.getId(),2);
					if(bavo!=null){
						bavo.setState(3);
						bavo.setType(2);
						bavo.setApprovetime(new Date());
						baservice.updBankApprove(bavo);
					}
					
					sid = vo.getId();
				}
				if(sid!=0){
					uList = siservice.searchUserBySId(sid);
					if(uList != null && uList.size()>0){
						for(UserVO ur : uList){
							MessageUtil.addMsg(ur.getId(), "监管物入库失败", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CARSTORAGE.getCode(),vo.getCreateuserid());
						}
					}
				}
				syncApplyInfo = new SyncApplyInfoVO();
				syncApplyInfo.setSerialNo(serialNo);
				supervisorDao.add(syncApplyInfo);
				flag = true;
			}
			
		}else if (StringUtil.isNotEmpty(applyType) && StringUtil.isNotEmpty(status)
				&& applyType.equals(CarOperationContants.APPLY_TYPE_OUT.getCode()) && status.equals(CarOperationContants.APPROVE_STATUS_YES.getCode())) {//出库  未审
			if (null != chassisNos && chassisNos.size() > 0) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < chassisNos.size(); i++) {
					vo = supervisorDao.searchSupervise(chassisNos.get(i));
					if (null != vo) {
						vo.setApply(0);
						vo.setOuttime(new Date());
						siservice.updSuperviseImport(vo,0);
						
						bavo = baservice.searchBankApproveBySid(vo.getId(),3);
						if(bavo!=null){
							bavo.setState(2);
							bavo.setType(3);
							bavo.setApprovetime(new Date());
							baservice.updBankApprove(bavo);
						}

						if (i != chassisNos.size()-1) {
							sb.append(vo.getId() + ",");
						}else{
							sb.append(vo.getId());
						}
						sid = vo.getId();
					}
				}
				if(sid!=0){
					uList = siservice.searchUserBySId(sid);
					if(uList != null && uList.size()>0){
						for(UserVO ur : uList){
							MessageUtil.addMsg(ur.getId(), "监管物出库", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CAROUT.getCode(),vo.getCreateuserid());
						}
					}
				}
				syncApplyInfo = new SyncApplyInfoVO();
				syncApplyInfo.setSerialNo(serialNo);
				supervisorDao.add(syncApplyInfo);
				flag = true;
			}
		}else if (StringUtil.isNotEmpty(applyType) && StringUtil.isNotEmpty(status)
				&& applyType.equals(CarOperationContants.APPLY_TYPE_OUT.getCode()) && status.equals(CarOperationContants.APPROVE_STATUS_NOT.getCode())) {//出库  未审
			if (null != chassisNos && chassisNos.size() > 0) {
				for (int i = 0; i < chassisNos.size(); i++) {
					vo = supervisorDao.searchSupervise(chassisNos.get(i));
					if (null != vo) {
						vo.setApply(0);
						vo.setState(2);
						siservice.updSuperviseImport(vo,0);
						
						bavo = baservice.searchBankApproveBySid(vo.getId(),3);
						if(bavo!=null){
							bavo.setState(3);
							bavo.setType(3);
							bavo.setApprovetime(new Date());
							baservice.updBankApprove(bavo);
						}
						
						sid = vo.getId();
					}
				}
				if(sid!=0){
					uList = siservice.searchUserBySId(sid);
					if(uList != null && uList.size()>0){
						for(UserVO ur : uList){
							MessageUtil.addMsg(ur.getId(), "监管物出库失败", "/bankApprove.do?method=bankApproveList", 1,MessageTypeContant.CAROUT.getCode(),vo.getCreateuserid());
						}
					}
				}
				syncApplyInfo = new SyncApplyInfoVO();
				syncApplyInfo.setSerialNo(serialNo);
				supervisorDao.add(syncApplyInfo);
				flag = true;
			}
		}
		return flag;
	}
	
	
	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	public boolean storageapproval(CarOperationQueryVO query,UserSession user) throws Exception{
		CarOperationVO bean = dao.get(CarOperationVO.class, query.getId(),new CarOperationMapper());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrRole();
		
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole) ){
				//业务专员
				bean.setNextApproval(RoleConstants.BANK_APPROVE.getCode());
				MessageUtil.sendMsg(new String[]{RoleConstants.BANK_APPROVE.getCode()+""}, "监管物入库", "/carOperation.do?method=storagefindList", 1,MessageTypeContant.CARSTORAGE.getCode(),bean.getUserid());
				
			}else if(bean.getNextApproval()==RoleConstants.BANK_APPROVE.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BANK_APPROVE.getCode(), currRole)){
				//业务部经理
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				
				SuperviseImportService service = new SuperviseImportService();
				String[] idArray = bean.getCars().split(",");
				try {
					for (int i = 0; i < idArray.length; i++) {
						SuperviseImportVO vo = service.loadSuperviseImportById(Integer.parseInt(idArray[i]));
						vo.setState(2);
						vo.setApply(0);
						service.updSuperviseImport(vo,0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			dao.update(bean);
		}
		
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.CARSTORAGE.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);
		
		return false;
	}
	
	/**
	 * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean storagesubmit(int id) throws Exception{
		CarOperationVO bean =  loadCarOperationById(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.CARSTORAGE.getCode());
		bean.setNextApproval(RoleConstants.BANK_APPROVE.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		
		return dao.update(bean);
	}
	
	
	
	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	public boolean outapproval(CarOperationQueryVO query,UserSession user) throws Exception{
		CarOperationVO bean = dao.get(CarOperationVO.class, query.getId(),new CarOperationMapper());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrRole();
		
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole) ){
				//业务专员
				bean.setNextApproval(RoleConstants.BANK_APPROVE.getCode());
				MessageUtil.sendMsg(new String[]{RoleConstants.BANK_APPROVE.getCode()+""}, "监管物出库", "/carOperation.do?method=outfindList", 1,MessageTypeContant.CAROUT.getCode(),bean.getUserid());

			}else if(bean.getNextApproval()==RoleConstants.BANK_APPROVE.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BANK_APPROVE.getCode(), currRole)){
				//业务部经理
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				
				SuperviseImportService service = new SuperviseImportService();
				String[] idArray = bean.getCars().split(",");
				try {
					for (int i = 0; i < idArray.length; i++) {
						SuperviseImportVO vo = service.loadSuperviseImportById(Integer.parseInt(idArray[i]));
						vo.setState(3);
						vo.setApply(0);
						service.updSuperviseImport(vo,0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			dao.update(bean);
		}
		
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.CAROUT.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);
		
		return false;
	}
	
	/**
	 * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean outsubmit(int id) throws Exception{
		CarOperationVO bean =  loadCarOperationById(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.CAROUT.getCode());
		bean.setNextApproval(RoleConstants.BANK_APPROVE.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		
		return dao.update(bean);
	}
	
	
	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	public boolean moveapproval(CarOperationQueryVO query,UserSession user) throws Exception{
		CarOperationVO bean = dao.get(CarOperationVO.class, query.getId(),new CarOperationMapper());
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrRole();
		
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole) ){
				//业务专员
				bean.setNextApproval(RoleConstants.BANK_APPROVE.getCode());
				MessageUtil.sendMsg(new String[]{RoleConstants.BANK_APPROVE.getCode()+""}, "监管物移动", "/carOperation.do?method=movefindList", 1,MessageTypeContant.CARMOVE.getCode(),bean.getUserid());
				
			}else if(bean.getNextApproval()==RoleConstants.BANK_APPROVE.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BANK_APPROVE.getCode(), currRole)){
				//业务部经理
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
				
				SuperviseImportService service = new SuperviseImportService();
				String[] idArray = bean.getCars().split(",");
				try {
					for (int i = 0; i < idArray.length; i++) {
						SuperviseImportVO vo = service.loadSuperviseImportById(Integer.parseInt(idArray[i]));
						vo.setState(4);
						vo.setApply(0);
						service.updSuperviseImport(vo,0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			dao.update(bean);
		}
		
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.CARMOVE.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);
		
		return false;
	}
	
	/**
	 * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean movesubmit(int id) throws Exception{
		CarOperationVO bean =  loadCarOperationById(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.CARMOVE.getCode());
		bean.setNextApproval(RoleConstants.BANK_APPROVE.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		
		return dao.update(bean);
	}
	
	public List<CarOperationBean> searchtheday(int type){
		return dao.searchtheday(type);
	}
	
	public List<CarOperationBean> searchday(int userid,int type){
		return dao.searchday(userid,type);
	}
	
	public List<CarOperationBean> searchzuoday(int userid,int type){
		return dao.searchzuoday(userid,type);
	}
	
	public List<CarOperationBean> searchqianday(int userid,int type){
		return dao.searchqianday(userid,type);
	}
	
	public List<CarOperationBean> searchshangyue(int userid,int type){
		return dao.searchshangyue(userid,type);
	}
	
	public List<CarOperationVO> todaycount(int type, int userid){
		return dao.todaycount(type,userid);
	}
	
}
