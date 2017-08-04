package com.zd.csms.supervisory.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisory.model.CarOperationQueryVO;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.querybean.CarOperationBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface ICarOperationDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<CarOperationVO> searchCarOperationList(CarOperationQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<CarOperationVO> searchCarOperationListByPage(CarOperationQueryVO query,IThumbPageTools tools);
	
	/**
	 * 查询当日出库数
	 * @return
	 */
	public List<CarOperationBean> searchtheday(int type);
	public List<CarOperationBean> searchday(int userid,int type);
	/**
	 * 查询昨日出库数
	 * @param userid
	 * @return
	 */
	public List<CarOperationBean> searchzuoday(int userid,int type);
	/**
	 * 查询前日出库数
	 * @param userid
	 * @return
	 */
	public List<CarOperationBean> searchqianday(int userid,int type);
	/**
	 * 查询上月出库数
	 * @param userid
	 * @return
	 */
	public List<CarOperationBean> searchshangyue(int userid,int type);
	
	public List<CarOperationVO> searchCarOperationByBankId(int bankid);
	
	public List<CarOperationVO> todaycount(int type, int userid);
}
