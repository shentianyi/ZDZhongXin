package com.zd.csms.supervisory.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.ExtRepairCostVO;
import com.zd.csms.supervisory.model.RepairCostQueryVO;
import com.zd.csms.supervisory.model.RepairCostVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IRepairCostDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<RepairCostVO> searchRepairCostList(RepairCostQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具1
	 * @return List<ResourceVO>
	 * */
	public List<RepairCostVO> searchRepairCostListByPage(RepairCostQueryVO query,IThumbPageTools tools,HttpServletRequest request);
	public List<RepairCostVO> searchRepairCostListByPage(RepairCostQueryVO query,IThumbPageTools tools);
	
	/**
	 * 
	 * number:		63
	 * author:		sxs
	 * describe:	按条件查询资源集合（翻页） 不分审批状态
	 * params:		tools 翻页工具
	 * return:		List<ResourceVO>
	 */
	public List<RepairCostVO> searchRepairCostAllListByPage(RepairCostQueryVO query,
			IThumbPageTools tools);
	/*
	 * 需求38
	 * 导出设备维修费用申请
	*/
	public List<ExtRepairCostVO> ExtRepairCostLedger(RepairCostQueryVO query);
}
