package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.DealerBankVO;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.ExtDealerVO;
import com.zd.csms.marketing.model.ModeChangeLogVO;
import com.zd.csms.marketing.querybean.DealerBankQueryBean;
import com.zd.csms.marketing.querybean.DealerBusinessQueryBean;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.querybean.ExpenseTotalQueryBean;
import com.zd.csms.marketing.querybean.SelectDealerBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IDealerDAO extends IDAO{
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<DealerVO> findList(DealerQueryVO query,IThumbPageTools tools);
	
	public List<DealerVO> findList(DealerQueryVO query) throws Exception;
	
	/**
	 * 根据主键查询银行列表
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<DealerBankVO> findBankListByIds(DealerQueryVO query) throws Exception;
	
	/**
	 * 监管费用统计分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<ExpenseTotalQueryBean> expenseTotalList(DealerQueryVO query,IThumbPageTools tools);
	
	public List<DealerVO> findDealerList(DealerQueryVO query) throws Exception;
	
	public List<DealerVO> findDealerqcList() throws Exception;
	
	/**
	 * 进驻7天未交监管费提醒
	 * @return
	 * @throws Exception
	 */
	public List<DealerVO> findDealerListByWarring() throws Exception;
	
	/**
	 * 监管费到期前30天进行提醒
	 * @return
	 * @throws Exception
	 */
	public List<DealerVO> payDateBefore(int day) throws Exception;
	
	/**
	 * 
	 * @param dealerId
	 * @return
	 */
	public DealerBankVO getDealerBankByDealerId(int dealerId);
	
	/**
	 * 弹窗选择
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<SelectDealerBean> selectDealerList(DealerQueryVO query,IThumbPageTools tools);
	
	public List<DealerVO> findDealersList(DealerQueryVO query);
	
	/**
	 * 查询绑定店名称
	 * @param ids
	 * @return
	 */
	public String findBindNameByIds(String ids);
	
	/**
	 * 查询该交监管费的经销商
	 * @return
	 */
	public List<DealerVO> findListByPaymentDate();
	
	/**
	 * 提前一个月预警余额不足的的经销商
	 * @return
	 */
	public List<DealerVO> findListByArrearsRemind();
	
	public List<DealerVO> findListByYW(int userid);
	
	/**
	 * 根据监管员储备库Id查询经销商
	 * @param id
	 * @return
	 */
	public List<DealerBankQueryBean> findDealerListByRepId(int id);
	
	public List<DealerBankVO> getDealerListByBankName(String bankName);
	public List<DealerVO> getDealerListByBrandId(int brandId);

	/**
	 * 经销商名录表：业务部
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<DealerBusinessQueryBean> findBusinessList(DealerQueryVO query,
			IThumbPageTools tools);
    
	/**
	 * 经销商名录表：业务部 -导出
	 * @param query
	 * @return
	 */
	public List<DealerBusinessQueryBean> findBusinessForExcel(
			DealerQueryVO query);


	/**
	 * 经销商名录表：市场部 -导出
	 * @param query
	 * @return
	 */
	public List<DealerVO> findListForExcel(DealerQueryVO query);
	
	/*
	 * 经销商名录表（业务）修改入口
	 * 需求53 -- 20170511 -- cym
	 * */
	public DealerBusinessQueryBean dealerModify(DealerQueryVO query);
	
	/*
	 * 经销商名录表（业务）修改
	 * @time 20170512
	*/
	public int updateDealer(DealerQueryBean query);
	
	/*
	 * T_BANK_MANAGER 修改金融机构联系人及电话
	 * @time 20170513
	*/
	public int updateBankManagerInfo(DealerQueryBean query);
	
	/*
	 * 修改合作状态
	 * @time 20170513
	*/
	public boolean updateStopCooperationState(DealerQueryBean query);

	/*
	 * 导出监管费台账 
	 * com.zd.csms.ledger.DealerAction
	 * @time 20170518
	*/
	public List<ExpenseTotalQueryBean> ExtexpenseTotalLedger(DealerQueryVO query);

	/*
	 * 需求38 -- 导出监管费管理台账
	 * com.zd.csms.marketing.web.action.DealerAction
	 * @time 20170519
	*/
	public List<ExpenseTotalQueryBean> ExtExpenseTotalList(DealerQueryVO query);

	/*
	 * 需求38 
	 * 导出经销商考勤时间台账 
	*/
	public List<ExtDealerVO> ExtFindDealerList(DealerQueryVO query);

	public List<ModeChangeLogVO> findModeChangeLogVOById(int id);

}
