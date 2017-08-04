package com.zd.csms.message.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.core.util.ArrayUtils;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.MarketFactory;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.contant.NoticeTypeContant;
import com.zd.csms.message.dao.INoticeContentDao;
import com.zd.csms.message.dao.INoticeDAO;
import com.zd.csms.message.dao.MessageDAOFactory;
import com.zd.csms.message.model.ContentDepartmentVO;
import com.zd.csms.message.model.MessageQueryVO;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.model.NoticeContentQueryVO;
import com.zd.csms.message.model.NoticeContentVO;
import com.zd.csms.message.model.NoticeVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserDAO;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;


public class NoticeContentService extends ServiceSupport {
	private INoticeContentDao dao = MessageDAOFactory.getNoticeContentDao();
	private IUserDAO userDao = RbacDAOFactory.getUserDAO();
	private INoticeDAO noticeDao = MessageDAOFactory.getNoticeDao();
	private IMarketApprovalDAO marketDao = MarketFactory.getMarketApprovalDAO();
	
	public boolean add(NoticeContentVO vo, HttpServletRequest request) throws Exception {
		vo.setId(Util.getID(NoticeContentVO.class));
		dao.add(vo);
		if(vo.getType()==2){//如果是选择部门
			String[] depts = request.getParameterValues("department");
			for (String str : depts) {
				ContentDepartmentVO cd = new ContentDepartmentVO();
				cd.setId(Util.getID(ContentDepartmentVO.class));
				cd.setContentId(vo.getId());
				cd.setClientTypeId(Integer.parseInt(str));
				noticeDao.add(cd);
			}
		}
		/*sendMessage(vo, request);*/
		
		return true;
	}

	
	/**
	 * 公告发布消息
	 * @param vo
	 * @param request
	 * @throws Exception
	 */
	private void sendMessage(NoticeContentVO vo) throws Exception {
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		if(vo.getContentType()!=null){
			if(vo.getContentType().intValue() == 1){ // 公告
				int sendType = vo.getType();//1：全部 2:选择部门
				if(sendType>0){
					String[] ids=null;
					if(sendType==1){//全部
						 ids= userDao.findAllIdByClientType().split(",");
					}else if(sendType==2){//选择的部门
						ids= userDao.findAllIdByClientType(
								dao.findDeptByContentId(vo.getId()).split(",")
								).split(",");
					}
					for (String id : ids) {
						NoticeVO n = new NoticeVO();
						n.setId(Util.getID(NoticeVO.class));
						n.setObjectId(vo.getId());
						n.setTitle(vo.getTitle());
						n.setType(NoticeTypeContant.CONTENT.getCode());
						n.setUrl("/notice/noticeContent.do?method=detail&noticeContent.id="+vo.getId());
						n.setUserId(Integer.parseInt(id));
						list.add(n);
					}	
				}
			}else if(vo.getContentType().intValue() == 2){//制度
				NoticeVO n = new NoticeVO();
				n.setId(Util.getID(NoticeVO.class));
				n.setObjectId(vo.getId());
				n.setTitle(vo.getTitle());
				n.setType(NoticeTypeContant.SYSTEM.getCode());
				n.setUrl("/notice/noticeContent.do?method=detail&noticeContent.id="+vo.getId());
				n.setUserId(0);
				list.add(n);
			}
		}
		
		noticeDao.addBatch(list);
	}
	
	public boolean update(NoticeContentVO vo,HttpServletRequest request) throws Exception {
		NoticeContentVO nc = get(vo.getId());
		nc.setTitle(vo.getTitle());
		nc.setContent(vo.getContent());
		nc.setType(vo.getType());
		dao.updateContent(nc);
		
		dao.deleteDeptVOBycontentId(nc.getId());
		if(vo.getType()==2){//如果是选择部门
			String[] depts = request.getParameterValues("department");
			for (String str : depts) {
				ContentDepartmentVO cd = new ContentDepartmentVO();
				cd.setId(Util.getID(ContentDepartmentVO.class));
				cd.setContentId(vo.getId());
				cd.setClientTypeId(Integer.parseInt(str));
				noticeDao.add(cd);
			}
		}
		
		/*//删除所有消息，重新添加
		noticeDao.deleteByObj(nc.getId(), NoticeTypeContant.CONTENT.getCode());
		sendMessage(nc, request);*/
		return true;
	}
	
	
	public boolean delete(int id) throws Exception {
		noticeDao.deleteByObj(id, NoticeTypeContant.CONTENT.getCode());
		return dao.delete(NoticeContentVO.class, id);
    }
	
	public NoticeContentVO get(int id) throws Exception{
		return dao.get(NoticeContentVO.class, id, new BeanPropertyRowMapper(NoticeContentVO.class));
	}
	
	public List<NoticeContentVO> searchNoticeContentListByPage(NoticeContentQueryVO query, IThumbPageTools tools){
		return dao.searchNoticeContentListByPage(query, tools);
	}
	
	/**
	 * 根据公告id查询对应的部门
	 * @param id
	 * @return
	 */
	public String findDeptByContentId(int id){
		return dao.findDeptByContentId(id);
	}


	//TODO
	public void submit(int id, int currRole) throws Exception{
		NoticeContentVO nc = get(id);
		if(ArrayUtils.contains(new int[]{
				RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
				RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
				RoleConstants.RISKMANAGEMENT_MINISTER.getCode(),
				RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
				RoleConstants.ADMINISTRATOR.getCode(),
				RoleConstants.SR.getCode()
		}, currRole)){
			nc.setNextApproval(currRole);
			nc.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());
			//不用审批，直接发布
			sendMessage(nc);
			
		}else{
			if(ArrayUtils.contains(new int[]{
					RoleConstants.SUPERVISORYMANAGEMENT_DATA.getCode(),
					RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
					RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(),
					RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode(),
					RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),
					RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode(),
					RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()
			}, currRole)){
				nc.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){
				nc.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
			}
			
			if(ArrayUtils.contains(new int[]{
					RoleConstants.MARKET_COMMISSIONER.getCode(),
					RoleConstants.MARKET_DATA.getCode()
			}, currRole)){
				nc.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
			}else if(currRole == RoleConstants.MARKET_AMALDAR.getCode()){
				nc.setNextApproval(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode());
			}
			
			if(ArrayUtils.contains(new int[]{
					RoleConstants.BUSINESS_COMMISSIONER.getCode(),
					RoleConstants.BUSINESS_DATA.getCode()
			}, currRole)){
				nc.setNextApproval(RoleConstants.BUSINESS_AMALDAR.getCode());
			}else if(currRole == RoleConstants.BUSINESS_AMALDAR.getCode()){
				nc.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());//运营部部长
				
			}else if (currRole==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()) {
				nc.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());//总监

			}
			
			if(ArrayUtils.contains(new int[]{
					RoleConstants.WINDCONRTOL_DATA.getCode(),
					RoleConstants.WINDCONRTOL_INTERNAL.getCode(),
					RoleConstants.WINDCONRTOL_EXTERNAL.getCode()
			}, currRole)){
				nc.setNextApproval(RoleConstants.WINDCONRTOL_AMALDAR.getCode());
			}else if(RoleConstants.WINDCONRTOL_AMALDAR.getCode()==currRole){
				nc.setNextApproval(RoleConstants.RISKMANAGEMENT_MINISTER.getCode());
			}
			
			if(ArrayUtils.contains(new int[]{
					RoleConstants.VIDEO_COMMISSIONER.getCode(),
					RoleConstants.VIDEO_DATA.getCode()
			}, currRole)){
				nc.setNextApproval(RoleConstants.VIDEO_AMALDAR.getCode());
			}else if(RoleConstants.VIDEO_AMALDAR.getCode()==currRole){
				nc.setNextApproval(RoleConstants.RISKMANAGEMENT_MINISTER.getCode());
			}
			
			nc.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		}
		boolean flag = dao.update(nc);
		if(flag && nc.getNextApproval() != 0){
			
			String[] strArr = new String[]{nc.getNextApproval()+""};
			MessageService ms = new MessageService();
			
			IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
			
			String userid = userRoleDao.findUsingUserIdByRole(strArr);
			if(StringUtil.isNotEmpty(userid)){
				String[] userids = userid.split(",");
				for (String uid : userids) {
					MessageVO msg = ms.loadMsgByUserAndType(Integer.parseInt(uid), MessageTypeContant.APPROVALNOTICE.getCode());
					
					if(msg==null){
						MessageUtil.addMsg(Integer.parseInt(uid), "0", "/notice/noticeContent.do?method=noticeContentList&query.pageType=1&query.contentType=1", 1,
								MessageTypeContant.APPROVALNOTICE.getCode(),nc.getCreateUserId());
					}else{
						int name = 1;
						if(StringUtil.isNumber(msg.getName())){
							name = Integer.parseInt(msg.getName()) + 1;
						}
						msg.setName(name+"");
						msg.setUrl("/notice/noticeContent.do?method=noticeContentList&query.pageType=1&query.contentType=1");
						msg.setIsread(1);
						msg.setMsgtype(1);
						ms.updMessage(msg);
					}
				}
				
			}
			
			
			//发布消息
		}
	}
	
	public void approval(UserSession user,NoticeContentQueryVO query,NoticeContentVO bean) throws Exception{
		int currRole = query.getCurrRole();
		NoticeContentVO nc =  get(bean.getId());
		query.setContentType(nc.getContentType().intValue());
		
		if(query.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode()){
			nc.setApprovalState(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else if(query.getApprovalState() == ApprovalContant.APPROVAL_PASS.getCode()){
			/*if (currRole==RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()) {
				nc.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
				nc.setNextApproval(RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode());
			}*/ 
			if (currRole==RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()) {
				nc.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());
				nc.setNextApproval(0);
				
				//发布消息
				sendMessage(nc);
			}else if(currRole == RoleConstants.BUSINESS_AMALDAR.getCode()){
				nc.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
				nc.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
			}
			
			
			
			if(ArrayUtils.contains(new int[]{
					RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
					RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
					RoleConstants.RISKMANAGEMENT_MINISTER.getCode(),
					RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
					RoleConstants.MARKET_AMALDAR.getCode(),
					RoleConstants.BUSINESS_AMALDAR.getCode(),
					RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
					RoleConstants.VIDEO_AMALDAR.getCode()
			}, currRole)){
				nc.setNextApproval(currRole);
				nc.setApprovalState(ApprovalContant.APPROVAL_PASS.getCode());
				//发布消息
				sendMessage(nc);
				
			}/*else{
				if(ArrayUtils.contains(new int[]{
						RoleConstants.SUPERVISORYMANAGEMENT_DATA.getCode(),
						RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
						RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(),
						RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode(),
						RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),
						RoleConstants.SUPERVISORYMANAGEMENT_TRAIN.getCode(),
						RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode()
				}, currRole)){
					nc.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
				}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){
					nc.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
				}
				
				if(ArrayUtils.contains(new int[]{
						RoleConstants.MARKET_COMMISSIONER.getCode(),
						RoleConstants.MARKET_DATA.getCode()
				}, currRole)){
					nc.setNextApproval(RoleConstants.MARKET_AMALDAR.getCode());
				}else if(currRole == RoleConstants.MARKET_AMALDAR.getCode()){
					nc.setNextApproval(RoleConstants.MARKETMANAGEMENT_MINISTER.getCode());
				}
				
				if(ArrayUtils.contains(new int[]{
						RoleConstants.BUSINESS_COMMISSIONER.getCode(),
						RoleConstants.BUSINESS_DATA.getCode()
				}, currRole)){
					nc.setNextApproval(RoleConstants.BUSINESS_AMALDAR.getCode());
				}else if(currRole == RoleConstants.BUSINESS_AMALDAR.getCode()){
					nc.setNextApproval(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
				}
				
				if(ArrayUtils.contains(new int[]{
						RoleConstants.WINDCONRTOL_DATA.getCode(),
						RoleConstants.WINDCONRTOL_INTERNAL.getCode(),
						RoleConstants.WINDCONRTOL_EXTERNAL.getCode()
				}, currRole)){
					nc.setNextApproval(RoleConstants.WINDCONRTOL_AMALDAR.getCode());
				}else if(RoleConstants.WINDCONRTOL_AMALDAR.getCode()==currRole){
					nc.setNextApproval(RoleConstants.RISKMANAGEMENT_MINISTER.getCode());
				}
				
				if(ArrayUtils.contains(new int[]{
						RoleConstants.VIDEO_COMMISSIONER.getCode(),
						RoleConstants.VIDEO_DATA.getCode()
				}, currRole)){
					nc.setNextApproval(RoleConstants.VIDEO_AMALDAR.getCode());
				}else if(RoleConstants.VIDEO_AMALDAR.getCode()==currRole){
					nc.setNextApproval(RoleConstants.RISKMANAGEMENT_MINISTER.getCode());
				}
				
				nc.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
			}*/
		}
		
		dao.update(nc);
		//记录流程
		MarketApprovalVO approval = new MarketApprovalVO();
		approval.setId(Util.getID(MarketApprovalVO.class));
		approval.setApprovalPerson(user.getUser().getUserName());
		approval.setApprovalObjectId(nc.getId());
		approval.setApprovalResult(query.getApprovalState());
		if(nc.getContentType()==1){
			approval.setApprovalType(ApprovalTypeContant.GONGGAO.getCode());
		}else if(nc.getContentType()==2){
			approval.setApprovalType(ApprovalTypeContant.ZHIDU.getCode());
		}
		
		approval.setApprovalUserId(user.getUser().getId());
		approval.setApprovalUserRole(currRole);
		approval.setCreateDate(new Date());
		approval.setRemark(query.getRemark());
		marketDao.add(approval);
		
	}


	public int selectMessageCountByUserAndType(int roleId, int type,int status) {
		return dao.selectMessageCountByUserAndType(roleId,type,status);
	}
	
}
