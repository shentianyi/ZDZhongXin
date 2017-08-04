package com.zd.csms.marketing.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.sql.TIMESTAMP;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IModeChangeDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.ExpenseChangeVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.model.ModeChangeLogVO;
import com.zd.csms.marketing.model.ModeChangeQueryVO;
import com.zd.csms.marketing.model.ModeChangeVO;
import com.zd.csms.marketing.querybean.ModeChangeQueryBean;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.contant.NoticeTypeContant;
import com.zd.csms.message.dao.INoticeDAO;
import com.zd.csms.message.dao.MessageDAOFactory;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.NoticeVO;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserDAO;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.DBUtil;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.time.TimeToolsFactory;

/**
 * 操作模式变更流转单
 * @author licheng
 *
 */

public class ModeChangeService extends ServiceSupport {
	private IModeChangeDAO dao = MarketFactory.getModeChangeDAO();
	//private DealerService dealerService = new DealerService();
	//private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	private INoticeDAO noticeDao = MessageDAOFactory.getNoticeDao();
	private IUserDAO userDao = RbacDAOFactory.getUserDAO();
	
	public boolean add(ModeChangeVO bean) throws Exception{
		bean.setId(Util.getID(ModeChangeVO.class));
		return dao.add(bean);
	}
	
	public boolean update(ModeChangeVO bean) throws Exception{
		ModeChangeVO vo = dao.get(ModeChangeVO.class, bean.getId(), new BeanPropertyRowMapper(ModeChangeVO.class));
		vo.setChangeDate(bean.getChangeDate());
		vo.setChangeOperationMode(bean.getChangeOperationMode());
		vo.setMoneyIsChange(bean.getMoneyIsChange());
		vo.setRemark(bean.getRemark());
		vo.setDealerId(bean.getDealerId());
		vo.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		vo.setLastModifyDate(bean.getLastModifyDate());
		vo.setLastModifyUserId(bean.getLastModifyUserId());
		return dao.update(vo);
		
	}
	
	public boolean delete(ModeChangeVO bean){
		return dao.delete(ModeChangeVO.class, bean.getId());
	}
	
	public ModeChangeVO get(int id){
		ModeChangeVO bean = dao.get(ModeChangeVO.class, id, new BeanPropertyRowMapper(ModeChangeVO.class));
		return bean;
	}
	
	/**
	 *  分页查询
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception 
	 */
	public List<ModeChangeQueryBean> findList(ModeChangeQueryVO query, IThumbPageTools tools) throws Exception {
		List<ModeChangeQueryBean> list = dao.findList(query, tools);
		return list;
	}
	
	/**
	 * 市场专员--市场部经理--（风控部--视频部--业务部）
	 * 监管模式变更单 分页查询
	 * @param query
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean approval(ModeChangeQueryVO query,UserSession user,int currRole) throws Exception{
		ModeChangeVO bean = dao.get(ModeChangeVO.class, query.getId(), new BeanPropertyRowMapper(ModeChangeVO.class));
		UserVO userVO =  user.getUser();
		if(currRole == RoleConstants.SR.getCode())
			currRole = bean.getNextApproval();
		
		DealerVO dealer = dao.get(DealerVO.class, bean.getDealerId(),new BeanPropertyRowMapper(DealerVO.class));
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
			//TODO 此处应该更新审批表的状态
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.MARKET_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKET_AMALDAR.getCode(), currRole) ){
				
				String beforeSuperviseMoneyDate = dealer.getChangeBeforeInfo();	//变更前标准费用
				String lastSuperviseMoneyDate = dealer.getSuperviseMoney();	//变更后标准费用
				Date changeSuperviseMoneyDate = dealer.getChangeSuperviseMoneyDate();	//监管费变更时间
				if(beforeSuperviseMoneyDate == null || lastSuperviseMoneyDate == null || changeSuperviseMoneyDate == null){
					
					ExpenseChangeService ecService = new ExpenseChangeService();
					List<ExpenseChangeVO> ecList = ecService.selectLastTwoExpenseChangeByDearlerId(bean.getDealerId());
					if(ecList.size() >= 1 && (changeSuperviseMoneyDate == null || lastSuperviseMoneyDate == null)){
						lastSuperviseMoneyDate = ecList.get(0).getChangemoney();
						changeSuperviseMoneyDate = new Date( ecList.get(0).getChangeDate().getTime());
					}
					
					if(ecList.size() > 1 && beforeSuperviseMoneyDate == null){
						beforeSuperviseMoneyDate = ecList.get(1).getChangemoney();
					}
					
					
				}
				
				//TODO 记录变更日志
				ModeChangeLogVO mcl = new ModeChangeLogVO(
												Util.getID(ModeChangeLogVO.class)
												,dealer.getId()
												,dealer.getSupervisionMode()
												,bean.getChangeOperationMode()
												//变更前监管费用
												,beforeSuperviseMoneyDate
												//变更后监管费用 
												,lastSuperviseMoneyDate
												,bean.getChangeDate()
												//,new Date()
												,changeSuperviseMoneyDate
												,bean.getCreateUserId()
												,user.getUser().getId()
												,bean.getCreateDate()
												,new Date());
				dao.addModeChangeLog(mcl);
				
				
				
				//市场部部长
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完成
				dealer.setSupervisionMode(bean.getChangeOperationMode());
				dao.update(dealer);
				
				
				//TODO 发送通知
				//发送通知公告,除了监管员其他部门都发
				try {
					
					String ids = userDao.findAllIdByClientType(ClientTypeConstants.getIds(ClientTypeConstants.SUPERVISORY.getCode()));
					if(ids!=null){
						String[] id = ids.split(",");
						
						List<NoticeVO> list = new ArrayList<NoticeVO>();
						for (String string : id) {
							NoticeVO notice = new NoticeVO();
							notice.setId(Util.getID(NoticeVO.class));
							notice.setObjectId(bean.getId());
							notice.setType(NoticeTypeContant.URL.getCode());
							notice.setUrl("/market/modeChange.do?method=detailPage&&mc.id="+bean.getId());
							notice.setUserId(Integer.parseInt(string));
							notice.setTitle("操作模式变更："+dealer.getDealerName());
							list.add(notice);
						}
						noticeDao.addBatch(list);
					}	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		bean.setLastModifyDate(new Date());
		bean.setLastModifyUserId(user.getUser().getId());
		dao.update(bean);
		
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.MODECHANGE.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		dao.add(approval);
		
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "操作模式变更流转单："+dealer.getDealerName(), "/market/modeChange.do?method=findList", 1, 
						MessageTypeContant.MODECHANGE.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "操作模式变更流转单："+dealer.getDealerName()+"审批未通过，请查看", "/market/modeChange.do?method=findList&query.pageType=2",1, MessageTypeContant.MODECHANGE.getCode(),bean.getCreateUserId());
			}else if(bean.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "操作模式变更流转单："+dealer.getDealerName()+"审批已通过，请查看", "/market/modeChange.do?method=findList",1, MessageTypeContant.MODECHANGE.getCode(),bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean submit(int id) throws Exception{
		ModeChangeVO bean =  get(id);
		/*if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.MODECHANGE.getCode());*/
		
		bean.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		DealerVO dealer = dao.get(DealerVO.class, bean.getDealerId(),new BeanPropertyRowMapper(DealerVO.class));
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "操作模式变更流转单："+dealer.getDealerName(), "/market/modeChange.do?method=findList", 1, 
						MessageTypeContant.MODECHANGE.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.update(bean);
	}
	
	public List<ModeChangeVO> selectLastTwoModeChangeByDearlerId(int dearlerId){
		return dao.selectLastTwoModeChangeByDearlerId(dearlerId);
	}
	
	public int addModeChangeLog(ModeChangeLogVO mcl){
		return dao.addModeChangeLog(mcl);
	}
	
}
