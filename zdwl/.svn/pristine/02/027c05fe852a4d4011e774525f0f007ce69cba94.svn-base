package com.zd.csms.windcontrol.dao;

import java.util.Date;
import java.util.List;
import com.zd.core.IDAO;
import com.zd.csms.windcontrol.model.InspectionPictureVO;
import com.zd.csms.windcontrol.model.InspectionRecordVO;
import com.zd.csms.windcontrol.querybean.InspectionLedgerQueryBean;
import com.zd.csms.windcontrol.querybean.InspectionListRowMapper;
import com.zd.csms.windcontrol.querybean.InspectionReportRowMapper;
import com.zd.csms.windcontrol.querybean.ReportQuery;
import com.zd.csms.windcontrol.web.form.InspectionLedgerForm;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IInspectionDAO extends IDAO {

	/**
	 * 根据基本信息Id查询对应的图片列表
	 * 
	 * @param id
	 * @return
	 */
	public List<InspectionPictureVO> findPictureByInfoId(int id);

	/**
	 * 根据图片Id删除对应的巡检报告-店面图片记录
	 * 
	 * @param fileId
	 * @return
	 */
	public boolean deletePicture(int fileId);

	
	/**
	 * 查询检查过程中发现的问题及监管员的优缺点
	 * 
	 * @param infoId
	 * @return
	 */
	public List<InspectionRecordVO> findRecordbyInfoId(int infoId);

	

	/**
	 * 删除检查过程中发现的问题及监管员的优缺点
	 */
	boolean deleteRecord(int inspectionId);

	

	/**
	 * 内控专员:巡检报告列表
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionListRowMapper> findReportList(ReportQuery query,
			IThumbPageTools tools);

	/**
	 * 巡视专员:巡检报告列表
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionListRowMapper> findInspectionList(ReportQuery query,
			IThumbPageTools tools);

	/**
	 * 巡检报告台账列表
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionLedgerQueryBean> findReportLedgerList(
			InspectionLedgerForm query, IThumbPageTools tools);
	
	/**
	 * 判断是否包含某角色
	 * 
	 * @param user_id
	 * @param role_id
	 * @return
	 */
	public boolean judgeUserRole(int user_id, int role_id);

	/**
	 * 更新巡检报告完成状态
	 * 
	 * @param reportStatus
	 * @param id
	 * @return
	 */
	public boolean updateReportStatus(int reportStatus, int id);

	/**
	 * 更新巡检报告完成时间
	 * @param vo
	 * @return
	 */
	public boolean updateModifyTime(Date modifyTime, int id);

	public InspectionReportRowMapper getInspectionReport(int id);

	/*
	 * 需求38 -- 导出巡检报告台账
	 * @time 20170518
	 */
	public List<InspectionLedgerQueryBean> ExtInspecReportLenger(
			InspectionLedgerForm query);

}
