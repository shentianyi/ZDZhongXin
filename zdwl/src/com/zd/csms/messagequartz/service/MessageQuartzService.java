package com.zd.csms.messagequartz.service;

import java.util.List;

import com.zd.core.ServiceSupport;
import com.zd.csms.messagequartz.dao.DealerSupervisorDao;
import com.zd.csms.messagequartz.model.MsgBillNoCarVO;
import com.zd.csms.messagequartz.model.MsgNoProcessCarVO;
import com.zd.csms.messagequartz.model.MsgZeroSkuDraftVO;
import com.zd.csms.messagequartz.model.SupervisorVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.supervisory.dao.SupervisorDAOFactory;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


/**
 * 监管员定时任务监管员生日提醒
 *
 */
public class MessageQuartzService extends ServiceSupport{

	private DealerSupervisorDao dao = SupervisorDAOFactory.getDealerSupervisorDao();
	private IUserRoleDAO userRoleDao = RbacDAOFactory.getUserRoleDAO();
	public List<SupervisorVO> findList() {
		return dao.findList();
	}
	
	public boolean add(MsgBillNoCarVO obj){
		int id;
		try {
			id = Util.getID(MsgBillNoCarVO.class);
			obj.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(obj);
	}
	public boolean add(MsgNoProcessCarVO obj){
		int id;
		try {
			id = Util.getID(MsgNoProcessCarVO.class);
			obj.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(obj);
	}
	public boolean add(MsgZeroSkuDraftVO obj){
		int id;
		try {
			id = Util.getID(MsgZeroSkuDraftVO.class);
			obj.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(obj);
	}
	public boolean add(SupervisorVO obj){
        int id;
		try {
			id = Util.getID(SupervisorVO.class);
			obj.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.add(obj);
	}
	/**
	 * 列表页
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<SupervisorVO> findList(
		SupervisorVO query, IThumbPageTools tools,int userId) throws Exception {
		List<SupervisorVO> list = dao.findListSupervisors(query, tools,userId);
		return list;
	}
	
	/**
	 * 更改生日提醒
	 * @param userId
	 * @param id
	 * @return
	 */
	public boolean updateReadStatus(int userId,int id) {
		return dao.updateReadStatus(userId,id);
	}
	
	/**
	 * 定时统计入职满一年提醒列表
	 * @return
	 */
	public List<SupervisorVO> findListinYear(){
		return dao.findListinYear();
	}
	
	/**
	 * 入职满一年提醒列表页
	 * @param query
	 * @param tools
	 * @return
	 * @throws Exception
	 */
	public List<SupervisorVO> findListSupervisorsYear(
		SupervisorVO query, IThumbPageTools tools,int userId) throws Exception {
		List<SupervisorVO> list = dao.findListSupervisorsYear(query, tools,userId);
		return list;
	}
	
	
	/**
	 * 更改入职满一年提醒
	 * @param userId
	 * @param id
	 * @return
	 */
	public boolean updateYearReadStatus(int userId,int id) {
		return dao.updateYearReadStatus(userId,id);
	}
	
	/**
	 * 根据汇票号查询汇票信息
	 * @param draftNum
	 * @return
	 */
	public MsgBillNoCarVO loadDraftByNum(String draftNum) {
		return dao.loadDraftByNum(draftNum);
	}
	
	public String findUserIdByDraftNum(String draftNum){
		return dao.findUserIdByDraftNum(draftNum);
	}
	public String findUserIdByDealerId(int dealerId){
		return dao.findUserIdByDealerId(dealerId);
	}
	public String findSuperviseIdByDraftNum(String draftNum){
		return dao.findSuperviseIdByDraftNum(draftNum);
	}
	public String findSuperviseIdByDealerId(int dealerId){
		return dao.findSuperviseIdByDealerId(dealerId);
	}
	public String findMarketIdByDealerId(int dealerId){
		return dao.findMarketIdByDealerId(dealerId);
	}
	public String findMarketIdByDraftNum(String draft_num){
		return dao.findMarketIdByDraftNum(draft_num);
	}
	
	public String findUserIdByRole(String...roleIds){
		return userRoleDao.findUsingUserIdByRole(roleIds);
	}
	
	
}
