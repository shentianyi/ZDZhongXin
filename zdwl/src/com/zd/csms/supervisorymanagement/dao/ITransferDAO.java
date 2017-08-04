package com.zd.csms.supervisorymanagement.dao;

import java.util.Date;
import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.TransferQueryVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.model.TransferVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ITransferDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<TransferVO> searchTransferList(TransferQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<TransferVO> searchTransferListByPage(TransferQueryVO query,IThumbPageTools tools);
	/**
	 * 根据经销商ID获取监管员列表
	 * @param dealerId
	 * @return
	 */
	public List<TransferRepositoryVO> getSupervisorListByDealerId(int dealerId);
	
	/**
	 * 根据储备库id查询当前监管员当月的调动次数
	 * @param respId
	 * @param date
	 * @return
	 */
	public List<TransferRepositoryVO> findTranRespListByRespId(int respId);
	/**
	 * 根据经销商ID和储备库ID获取监管员列表
	 * @param dealerId
	 * @param repositoryId
	 * @return
	 */
	public List<TransferRepositoryVO> getSupervisorListByDealerIdAndRepId(String[] dealerIds, int repositoryId);
	/**
	 * 根据调入时间查询监管员列表（用于监管员在一家店工作五个月提醒）
	 * @param entryTime
	 * @return
	 */
	public List<TransferRepositoryVO> getSupervisorListByEntryTime( Date entryTime,int msgType);
	/**
	 * 根据调入时间和监管员ID查询监管员列表（用于监管员在一家店工作五个月提醒）
	 * @param entryTime
	 * @return
	 */
	public List<TransferRepositoryVO> getSupervisorListByEntryTimeAndRepId( int repositoryId,Date entryTime);
	//查询监管员的上岗日期和离岗日期
	public List<TransferRepositoryVO> searchLeaveTimeAndEntryTimeByRepositoryId(
			int repositoryId);
}
