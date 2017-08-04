package com.zd.csms.supervisory.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.CheckStockCarBean;
import com.zd.csms.supervisory.model.CheckStockManageBean;
import com.zd.csms.supervisory.model.CheckStockManageQueryVO;
import com.zd.csms.supervisory.model.CheckStockManageVO;
import com.zd.csms.supervisory.model.CheckStockPicVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.querybean.CheckStockManageQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ICheckStockManageDAO extends IDAO{
	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockManageQueryBean> findList(CheckStockManageQueryVO query, IThumbPageTools tools);

	public List<CheckStockManageBean> findCheckStockManage(CheckStockManageQueryVO query, IThumbPageTools tools, HttpServletRequest request);
	public List<CheckStockManageBean> findCheckStockManageList(CheckStockManageQueryVO query, IThumbPageTools tools,HttpServletRequest request);

	public List<CheckStockCarBean> findCheckStockCarList(int checkstockId, IThumbPageTools tools);

	public List<CheckStockCarBean> findCheckStockCarList(int checkstockId);

	public List<SuperviseImportVO> findSuperviseImportList(int dealerid, int[] carStatus, IThumbPageTools tools);
	
	public List<SuperviseImportVO> findSuperviseImportList(int dealerid, int carStatus);

	public CheckStockManageBean getCheckStockManageBranById(int id);

	public boolean updateCheckStockManage(CheckStockManageBean bean);

	public boolean updateCheckStockCarActualstate(int id, int actualstate,String remark,boolean faTimeflag);

	public boolean submit(int checkstockId);

	public int findCheckStockCarById(int id);

	public boolean addPic(int id, int uplId);

	public List<CheckStockPicVO> findCheckStockPicById(int checkstockId);

	public int findPicIndex(int id);

	
}
