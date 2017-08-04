package com.zd.csms.marketing.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.marketing.model.ExpenseChangeQueryVO;
import com.zd.csms.marketing.model.ExpenseChangeVO;
import com.zd.csms.marketing.model.ModeChangeLogVO;
import com.zd.csms.marketing.querybean.ExpenseChangeQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IExpenseChangeDAO extends IDAO{
	/**
	 * 监管费变更分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<ExpenseChangeQueryBean> findList(ExpenseChangeQueryVO query,IThumbPageTools tools);
	public List<ExpenseChangeQueryBean> managementLedgerList(ExpenseChangeQueryVO query,IThumbPageTools tools);
	public List<ExpenseChangeVO> selectLastTwoExpenseChangeByDearlerId(
			int dearlerId);
	public ModeChangeLogVO selectLastModeChangeByDearlerId(int dealerId);
	

}
