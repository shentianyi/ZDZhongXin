package com.zd.csms.supervisorymanagement.dao;

import java.util.Date;
import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.roster.model.PostChangeVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.supervisorymanagement.model.ElectronicTextVO;
import com.zd.csms.supervisorymanagement.model.ExtHandoverLogVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogPicVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogVO;
import com.zd.csms.supervisorymanagement.model.OfficeEquipmentVO;
import com.zd.csms.supervisorymanagement.model.OtherDataVO;
import com.zd.csms.supervisorymanagement.model.PaperTextVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IHandoverLogDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<HandoverLogVO> searchHandoverLogList(HandoverLogQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<HandoverLogVO> searchHandoverLogListByPage(HandoverLogQueryVO query,IThumbPageTools tools);
	public HandoverLogPicVO searchPicsByHandoverLogId(int handoverLogId);
	public RosterVO loadRosterBySupervisorId(int supervisorId);
	public PostChangeVO loadLastPostChangeByRosterId(int rosterid);
	public TransferRepositoryVO loadLastTransferRepositoryByIds(int dealerid, int repositoryid);
	public boolean updatePostChange(PostChangeVO pc, int flag);
	public boolean updateTransferRepository(TransferRepositoryVO pc, int flag);
	/**
	 * 提交图片，进入审批流程
	 * @param hpic
	 * @return
	 */
	public boolean updPicEditStatus(HandoverLogPicVO hpic);
	public boolean updHandoverLogEditStatus(HandoverLogVO handoverLog);
	public List<HandoverLogVO> getHandoverLogByDealerId(int dealerId);
	public ElectronicTextVO getETextBySupervisorId(int id);
	public OtherDataVO getODataBySupervisorId(int id);
	public OfficeEquipmentVO getOfficeEquipmentBySupervisorId(int id);
	public PaperTextVO getpTextBySupervisorId(int id);
	
	
	/**
	 * 修改文件监管员
	 * @param id
	 * @param supervisorId
	 * @return
	 */
	public boolean updateETextSupervisorId(int id,int supervisorId);
	public boolean updateODataSupervisorId(int id,int supervisorId);
	public boolean updateOfficeEquipmentSupervisorId(int id,int supervisorId);
	public boolean updatepTextSupervisorId(int id,int supervisorId);
	
	public boolean updateFinishTime(int id, Date date);
	 /*
	  * 导出交接记录管理台账 -- 需求59
	  * @time 20170517
	 */
	public List<ExtHandoverLogVO> ExtHandoverLog(HandoverLogQueryVO handoverLogQuery);
	
	public boolean deleteHandoverPicPicture(int fileId);
}
