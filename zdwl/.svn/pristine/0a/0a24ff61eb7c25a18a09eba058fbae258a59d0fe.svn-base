package com.zd.csms.supervisory.service;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
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
import com.zd.csms.supervisory.dao.IDuedateDAO;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.supervisory.dao.mapper.DuedateMapper;
import com.zd.csms.supervisory.model.DuedateQueryVO;
import com.zd.csms.supervisory.model.DuedateVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class DuedateService extends ServiceSupport {

	private IDuedateDAO dao = SupervisorDAOFactory.getDuedateDAO();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	
	public boolean addDuedate(DuedateVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(DuedateVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
			if (flag) {
				if(vo.getType()==1){
					MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+"",
							RoleConstants.WINDCONRTOL_INTERNAL.getCode()+""}, "本库日查库",
							"/duedate.do?method=findList", 1,MessageTypeContant.BENDUEDATE.getCode(),vo.getCreateuserid());
				}else if(vo.getType()==2){
					MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+"",
							RoleConstants.WINDCONRTOL_INTERNAL.getCode()+""}, "二网日查库", 
							"/duedate.do?method=findList", 1,MessageTypeContant.TWODUEDATE.getCode(),vo.getCreateuserid());
				}
			}
		}
		return flag;
	}
	
	public boolean updDuedate(DuedateVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteDuedate(int id) throws Exception {
		return dao.delete(DuedateVO.class, id);
    }
	
	public DuedateVO loadDuedateById(int id) throws Exception{
		DuedateVO duedate = dao.get(DuedateVO.class, id,new BeanPropertyRowMapper(DuedateVO.class));
		return duedate;
	}
	
	public List<DuedateVO> searchDuedateList(DuedateQueryVO query){
		return dao.searchDuedateList(query);
	}
	
	public List<DuedateVO> searchDuedateListByPage(DuedateQueryVO vo, IThumbPageTools tools){
		return dao.searchDuedateListByPage(vo, tools);
	}
	
	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	public boolean approval(DuedateQueryVO query,UserSession user) throws Exception{
		DuedateVO bean = dao.get(DuedateVO.class, query.getId(),new BeanPropertyRowMapper(DuedateVO.class));
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrRole();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole) ){
				//业务部业务专员
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
			}
			dao.update(bean);
		}
		
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.BENDUEDATE.getCode());
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
	public boolean submit(int id) throws Exception{
		DuedateVO bean =  loadDuedateById(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.BENDUEDATE.getCode());
		bean.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		
		if(bean.getType()==1){
			MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+""}, "本库日查库",
					"/duedate.do?method=findList", 1,MessageTypeContant.BENDUEDATE.getCode(),bean.getCreateuserid());
		}else if(bean.getType()==2){
			MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.BUSINESS_COMMISSIONER.getCode()+""}, "二网日查库", 
					"/duedate.do?method=findList", 1,MessageTypeContant.TWODUEDATE.getCode(),bean.getCreateuserid());
		}
		return dao.update(bean);
	}
	
	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	/*public boolean twoapproval(DuedateQueryVO query,UserSession user) throws Exception{
		DuedateVO bean = dao.get(DuedateVO.class, query.getId(),new BeanPropertyRowMapper(DuedateVO.class));
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrRole();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.BUSINESS_COMMISSIONER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.BUSINESS_COMMISSIONER.getCode(), currRole) ){
				//业务部业务专员
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完
			}
			dao.update(bean);
		}
		
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.TWODUEDATE.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);
		
		return false;
	}*/
	
	/**
	 * 提交
	 * @param id
	 * @return
	 * @throws Exception
	 */
	/*public boolean twosubmit(int id) throws Exception{
		DuedateVO bean =  loadDuedateById(id);
		if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.TWODUEDATE.getCode());
		bean.setNextApproval(RoleConstants.BUSINESS_COMMISSIONER.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		
		RoleService rs = new RoleService();
		UserRoleQueryVO urquery = new UserRoleQueryVO();
		urquery.setRole_id(RoleConstants.BUSINESS_COMMISSIONER.getCode());
		List<UserRoleVO> urList = rs.searchUserRoleList(urquery);
		if(urList != null && urList.size()>0){
			for(UserRoleVO ur : urList){
				MessageUtil.addMsg(ur.getUser_id(), "二网日查库", "/twodate.do?method=findList", 1,1);
			}
		}
		
		return dao.update(bean);
	}*/
}
