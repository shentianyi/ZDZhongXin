package com.zd.csms.marketing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.IMarketProjectCirculationDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.DealerBankVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.model.MarketProjectCirculationQueryVO;
import com.zd.csms.marketing.model.MarketProjectCirculationVO;
import com.zd.csms.marketing.querybean.PCListQueryBean;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.contant.NoticeTypeContant;
import com.zd.csms.message.dao.INoticeDAO;
import com.zd.csms.message.dao.MessageDAOFactory;
import com.zd.csms.message.model.NoticeVO;
import com.zd.csms.messageAndWaring.message.service.market.MarketMessageTypeService;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 项目进驻流转单
 * @author licheng
 *
 */

public class MarketProjectCirculationService extends ServiceSupport{
	IMarketProjectCirculationDAO dao = MarketFactory.getMarketProjectCirculationDAO();
	IMarketApprovalDAO approvalDao = MarketFactory.getMarketApprovalDAO();
	//private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	private DealerService ds = new DealerService();
	//private IUserDAO userDao = RbacDAOFactory.getUserDAO();
	private INoticeDAO noticeDao = MessageDAOFactory.getNoticeDao();
	
	private MarketMessageTypeService typeService = new MarketMessageTypeService();
	
	public boolean add(MarketProjectCirculationVO bean){
		try {
			bean.setId(Util.getID(MarketProjectCirculationVO.class));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(bean);
	}
	
	public boolean update(MarketProjectCirculationVO bean){
		MarketProjectCirculationVO vo = get(bean.getId());
		vo.setDealerName(bean.getDealerName());
		vo.setDealerPerson(bean.getDealerPerson());
		vo.setDealerPhone(bean.getDealerPhone());
		vo.setBrandId(bean.getBrandId());
		vo.setSupervisionMode(bean.getSupervisionMode());
		vo.setCooperationModel(bean.getCooperationModel());
		vo.setDealerNature(bean.getDealerNature());
		vo.setSuperviseAddress(bean.getSuperviseAddress());
		vo.setProvince(bean.getProvince());
		vo.setCity(bean.getCity());
		vo.setProvideLunch(bean.getProvideLunch());
		vo.setIsBindShop(bean.getIsBindShop());
		vo.setBindShop(bean.getBindShop());
		vo.setBindShop2(bean.getBindShop2());
		vo.setIsNeedHandover(bean.getIsNeedHandover());
		vo.setHandoverCompany(bean.getHandoverCompany());
		vo.setHandoverDate(bean.getHandoverDate());
		vo.setInStoreDate(bean.getInStoreDate());
		vo.setInStoreRemark(bean.getInStoreRemark());
		vo.setBankId(bean.getBankId());
		vo.setSuperviseMoneyPerson(bean.getSuperviseMoneyPerson());
		vo.setPayMode(bean.getPayMode());
		vo.setSuperviseMoney(bean.getSuperviseMoney());
		vo.setFirstPayDate(bean.getFirstPayDate());
		vo.setInvoiceMode(bean.getInvoiceMode());
		vo.setIsSignAnAgreement(bean.getIsSignAnAgreement());
		vo.setAgreementIsRecovery(bean.getAgreementIsRecovery());
		vo.setPaymentObject(bean.getPaymentObject());
		vo.setMarketRemark(bean.getMarketRemark());
		vo.setBankManagerId(bean.getBankManagerId());
		vo.setApprovalState(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
		vo.setLastModifyDate(bean.getLastModifyDate());
		vo.setLastModifyUserId(bean.getLastModifyUserId());
		vo.setDistrict(bean.getDistrict());
		vo.setCredit(bean.getCredit());
		return dao.update(vo);
	}
	
	public boolean delete(int id){
		return dao.delete(MarketProjectCirculationVO.class, id);
	}
	
	public MarketProjectCirculationVO get(int id){
		return dao.get(MarketProjectCirculationVO.class, id, new BeanPropertyRowMapper(MarketProjectCirculationVO.class));
	}
	
	public MarketProjectCirculationVO getByDealerId(int dealerId){
		return dao.getByDealerId(dealerId);
	}
	/**
	 * 查询项目进驻流转单发出五天的信息
	 * @param createDate
	 * @param msgType 消息类型 1：信息提醒，2：信息预警
	 * @return
	 */
	public List<MarketProjectCirculationVO> findListByCreateDate(Date createDate,int msgType){
		return dao.findListByCreateDate(createDate,msgType);
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<PCListQueryBean> findList(MarketProjectCirculationQueryVO query,IThumbPageTools tools){
		return dao.findList(query, tools);
	}
	
	/**
	 * 审批
	 * @param query
	 * @param user
	 * @return
	 */
	public boolean approval(MarketProjectCirculationQueryVO query,UserSession user) throws Exception{
		MarketProjectCirculationVO bean = dao.get(MarketProjectCirculationVO.class, query.getId(), new BeanPropertyRowMapper(MarketProjectCirculationVO.class));
		UserVO userVO =  user.getUser();
		int currRole = query.getCurrRole();
		if(currRole == RoleConstants.SR.getCode()){
			currRole = bean.getNextApproval();
		}
		
		//判断权限 如果是最高权限审批通过则 增加经销商名录
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			bean.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			
			if(bean.getNextApproval()==RoleConstants.MARKET_AMALDAR.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKET_AMALDAR.getCode(), currRole) ){
				//市场部经理
				bean.setNextApproval(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode());
			}else if(bean.getNextApproval()==RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()
					&&RoleUtil.roleValidate(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(), currRole)){
				//市场部部长
				bean.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());//最后一级审批 ，如果成功则审批完成
				//此处应有增加经销商名录的逻辑
				if(bean.getDealerId()<=0){
					bean.setDealerId(addDealer(bean,user.getUser().getId()));
				}
				if(bean.getDealerId()>0&&bean.getBankId()>0){
					//增加银行关联表
					DealerBankVO dealerBank = new DealerBankVO();
					dealerBank.setId(Util.getID(DealerBankVO.class));
					dealerBank.setBankId(bean.getBankId());
					dealerBank.setDealerId(bean.getDealerId());
					dao.add(dealerBank);
				}
				
				//发送消息
				try {
					MessageUtil.sendMsg(
							new String[]{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()
									+"",RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+"",RoleConstants.WINDCONRTOL_INTERNAL.getCode()+""}, 
							"项目进驻:"+bean.getDealerName(), "/market/projectCirculationForm.do?method=deatilInfo&&query.id="+bean.getId(), 1, 
							MessageTypeContant.PROJECTCIRCULATION.getCode(), bean.getCreateUserId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//发送通知公告,招聘专员，监管员管理部经理，运营管理部部长,风控内控专员
				try {
					
					String ids = userRoleDao.findUsingUserIdByRole(RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()
							+"",RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()
							+"",RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+"",RoleConstants.WINDCONRTOL_INTERNAL.getCode()+"");
					
					if(ids!=null){
						String[] id = ids.split(",");
						
						List<NoticeVO> list = new ArrayList<NoticeVO>();
						for (String string : id) {
							NoticeVO notice = new NoticeVO();
							notice.setId(Util.getID(NoticeVO.class));
							notice.setObjectId(bean.getId());
							notice.setType(NoticeTypeContant.URL.getCode());
							notice.setUrl("/market/projectCirculationForm.do?method=deatilInfo&query.id="+bean.getId());
							notice.setUserId(Integer.parseInt(string));
							notice.setTitle("项目进驻："+bean.getDealerName());
							list.add(notice);
						}
						noticeDao.addBatch(list);
					}	
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		dao.update(bean);
		
		//审批记录表
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalObjectId(query.getId());
		approval.setApprovalPerson(userVO.getUserName());
		approval.setApprovalType(ApprovalTypeContant.MARKETPROJECTCIRCULATION.getCode());
		approval.setCreateDate(new Date());
		approval.setRemark(query.getApprovalRemark());
		approval.setApprovalResult(query.getApprovalState());
		approval.setApprovalUserId(userVO.getId());
		approval.setApprovalUserRole(currRole);
		
		dao.add(approval);
		
		//发送消息
		
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "进驻流转单:"+bean.getDealerName()+"，请审批", "/market/projectCirculationForm.do?method=findList", 1, 
						MessageTypeContant.PROJECTCIRCULATION.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//审批消息提醒
		try {
			if(bean.getApprovalState()==ApprovalContant.APPROVAL_NOPASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "进驻流转单："+bean.getDealerName()+"审批未通过，请查看", "/market/projectCirculationForm.do?method=findList&query.pageType=2",1, MessageTypeContant.PROJECTCIRCULATION.getCode(),bean.getCreateUserId());
			}else if(bean.getApprovalState()==ApprovalContant.APPROVAL_PASS.getCode()){
				MessageUtil.addMsg(bean.getCreateUserId(), "进驻流转单："+bean.getDealerName()+"审批已通过，请查看", "/market/projectCirculationForm.do?method=findList&query.pageType=2",1, MessageTypeContant.PROJECTCIRCULATION.getCode(),bean.getCreateUserId());
				//项目进驻流转单提醒(新改造的)
				typeService.projectIn(bean.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	/**
	 * 记录经销商名录
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	private int addDealer(MarketProjectCirculationVO bean,int userid) throws Exception{
		DealerVO dealer = new DealerVO();
		dealer.setId(Util.getID(DealerVO.class));
		dealer.setDealerName(bean.getDealerName());
		dealer.setBrandId(bean.getBrandId());
		dealer.setProvince(bean.getProvince());
		dealer.setCity(bean.getCity());
		dealer.setDistrict(bean.getDistrict());
		dealer.setAddress(bean.getSuperviseAddress());
		dealer.setInDate(bean.getInStoreDate());
		dealer.setContact(bean.getDealerPerson());
		dealer.setContactPhone(bean.getDealerPhone());
		dealer.setDealerNature(bean.getDealerNature());
		dealer.setSupervisionMode(bean.getSupervisionMode());
		dealer.setCooperationModel(bean.getCooperationModel());
		dealer.setDdorbd(bean.getIsBindShop());
		dealer.setSuperviseMoney(bean.getSuperviseMoney());
		dealer.setPayMode(bean.getPayMode());
		dealer.setHandoverCompany(bean.getHandoverCompany());
		dealer.setHandoverDate(bean.getHandoverDate());
		dealer.setCooperationState(DealerContant.COOPERATIONSTATE_OUT.getCode());
		dealer.setProvideLunch(bean.getProvideLunch());
		dealer.setIsNeedHandover(bean.getIsNeedHandover());
		dealer.setCreateUserid(bean.getCreateUserId());
		dealer.setBankManagerId(bean.getBankManagerId());
		dealer.setCredit(bean.getCredit());
		DealerVO bs1=null;
		DealerVO bs2=null;
		if(bean.getBindShop()>0&&bean.getBindShop2()>0){
			bs1 = ds.get(bean.getBindShop());
			bs1.setBindCount(3);
			bs1.setDdorbd(1);
			bs1.setBindInfo(dealer.getId()+","+bean.getBindShop2());
			
			bs2 = ds.get(bean.getBindShop2());
			bs2.setBindCount(3);
			bs2.setDdorbd(1);
			bs2.setBindInfo(dealer.getId()+","+bean.getBindShop());
			
			dealer.setBindCount(3);
			dealer.setDdorbd(1);
			dealer.setBindInfo(bean.getBindShop()+","+bean.getBindShop2());
		}else if(bean.getBindShop()>0){
			bs1 = ds.get(bean.getBindShop());
			bs1.setBindCount(2);
			bs1.setDdorbd(1);
			bs1.setBindInfo(dealer.getId()+"");
			
			dealer.setBindCount(2);
			dealer.setDdorbd(1);
			dealer.setBindInfo(bean.getBindShop()+"");
		}else if(bean.getBindShop2()>0){
			bs2 = ds.get(bean.getBindShop2());
			bs2.setBindCount(2);
			bs2.setDdorbd(1);
			bs2.setBindInfo(dealer.getId()+"");
			
			dealer.setBindCount(2);
			dealer.setDdorbd(1);
			dealer.setBindInfo(bean.getBindShop2()+"");
		}else{
			dealer.setBindCount(1);
			dealer.setDdorbd(2);
		}
		dao.add(dealer);
		if(bs1!=null){
			ds.update(bs1);
		}
		if(bs2!=null){
			ds.update(bs2);
		}
		return dealer.getId();
	}
	
	
	/**
	 * 更新审批状态
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean submit(int id) throws Exception{
		MarketProjectCirculationVO bean = 
				dao.get(MarketProjectCirculationVO.class, id, new BeanPropertyRowMapper(MarketProjectCirculationVO.class));
		/*if(ApprovalContant.APPROVAL_NOPASS.getCode()==bean.getApprovalState())
			marketDao.deleteApprovalByDealerId(bean.getId(), ApprovalTypeContant.MARKETPROJECTCIRCULATION.getCode());*/
		bean.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
		bean.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		boolean  flag = dao.update(bean);
		//发送消息
		try {
			if(bean.getApprovalState() == ApprovalContant.APPROVAL_WAIT.getCode()){
				MessageUtil.sendMsg(new String[]{bean.getNextApproval()+""}, "进驻流转单:"+bean.getDealerName()+"，请查阅审核", "/market/projectCirculationForm.do?method=findList", 1, 
						MessageTypeContant.PROJECTCIRCULATION.getCode(), bean.getCreateUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return flag;
	}
	
	/**
	 * 验证表单是否重复
	 * @param dealerName
	 * @param bankid
	 * @return
	 */
	public int validateRepeat(String dealerName,int bankid){
		return dao.validateRepeat(dealerName, bankid);
	}

	public List<PCListQueryBean> findmarketProjectListLedger(
			MarketProjectCirculationQueryVO query, IThumbPageTools tools) {
		return dao.findmarketProjectListLedger(query,tools);
	}
	
}
