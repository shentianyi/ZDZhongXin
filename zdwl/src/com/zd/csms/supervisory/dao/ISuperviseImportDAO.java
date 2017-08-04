package com.zd.csms.supervisory.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zd.core.IDAO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.csms.supervisory.querybean.CarSummary;
import com.zd.csms.supervisory.querybean.ExportCarInfoBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ISuperviseImportDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<SuperviseImportVO> searchSuperviseImportList(SuperviseImportQueryVO query);
	
	/**
	 * 根据车架号获取资源对象
	 * @param vin
	 * @return
	 */
	public SuperviseImportVO searchSupervise(String vin);
	
	public List<String> findVinsByVins(List<String> vins);
	
	public List<UserVO> searchUserById(int id);
	
	public List<UserVO> searchUserBySId(int sid);
	
	public List<UserVO> searchUserByYWId(int id);
	
	public List<String> findUserIds(int id);
	
	public List<SuperviseImportVO> findListByIds(String...ids);
	public int carCountByDraft(String draftNum, int state);
	
	/**
	 * 检查交易流水号是否已同步
	 * @param serialNo
	 * @return
	 */
	public boolean serialNoExist(String serialNo);
	/**
	 * 根据经销商ID获取
	 * @param dealerId
	 * @return
	 */
	public int carCountByDealerId(int dealerId,int[] status);
	
	public List<CarInfoQueryBean> searchSuperviseImportList(SuperviseImportQueryVO query, IThumbPageTools tools);
	
	/**
	 * 根据经销商Id查询经销商下所有的车辆信息
	 * @param dealer
	 * @param state
	 * @return
	 */
	public List<SuperviseImportVO> findListByDealerIdAndState(int dealer,int state);
	
	/**
	 * 根据汇票号和状态查询经销商下所有的车辆信息
	 * @param dealer
	 * @param state
	 * @return
	 */
	public List<SuperviseImportVO> findListByDraftNumAndState(String draftNum,int state);
	
	public CarSummary getSummaryByBank(SuperviseImportQueryVO query) ;
	
	public List<ExportCarInfoBean> exportCarInfo(SuperviseImportQueryVO query);
	public SuperviseImportVO findByVin(String vin);
	/**
	 * 根据ID获取总金额
	 * @param ids
	 * @return
	 */
	public Long findTotalPricesByIds(String...ids);
	
	/**
	 * 批量删除
	 */
	public boolean batchDeleteSuperviseImport(String ids);
	
	//删除车辆之前做记录
	public boolean addSuperviseReport(String ids,HttpServletRequest request);
}
