package com.zd.csms.messagequartz.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.messagequartz.model.MsgBillNoCarVO;
import com.zd.csms.messagequartz.model.SupervisorVO;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 监管员定时任务监管员生日提醒
 *
 */
public interface DealerSupervisorDao extends IDAO{

	/**
	 * 所有监管员的生日提醒
	 * @return
	 */
	public List<SupervisorVO> findList();
	
	
	/**
	 * 生日提醒列表
	 * @param query
	 * @param tools
	 * @param userId
	 * @return
	 */
	public List<SupervisorVO> findListSupervisors(SupervisorVO query,
			IThumbPageTools tools,int userId);
	
	
	/**
	 * 更改生日提醒 状态
	 * @param userId
	 * @param id
	 * @return
	 */
	public boolean updateReadStatus(int userId,int id);
	
	/**
	 * 判断是不是此类消息存在未读状态
	 * @param messageType
	 * @param userId
	 * @return
	 */
    public boolean getReadStatus(int messageType,int userId);
	
	/**
	 * 定时任务统计所有监管员的入职满一年提醒
	 * @return
	 */
	public List<SupervisorVO> findListinYear();


	/**
	 * 入职满一年提醒列表
	 */
	List<SupervisorVO> findListSupervisorsYear(SupervisorVO query,
			IThumbPageTools tools, int userId);

    /**
	 * 更改满一年提醒列表
	 * @param userId
	 * @param id
	 * @return
	 */
	boolean updateYearReadStatus(int userId, int id);
	
	/**
	 * 根据汇票号查询汇票信息
	 * @param draftNum
	 * @return
	 */
	MsgBillNoCarVO loadDraftByNum(String draftNum);
	
	/**
	 * 根据汇票号查询对应的业务专员id
	 * @param draftNum
	 * @return
	 */
	String findUserIdByDraftNum(String draftNum);
	String findUserIdByDealerId(int dealerId);
	/**
	 * 根据汇票号查询对应的监管员id
	 * @param draftNum
	 * @return
	 */
	String findSuperviseIdByDraftNum(String draftNum);
	String findSuperviseIdByDealerId(int dealerId);
	String findMarketIdByDealerId(int dealerId);
	String findMarketIdByDraftNum(String draft_num);
	
}
