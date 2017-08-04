package com.zd.csms.payment.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.payment.model.PaymentInfoQueryVO;
import com.zd.csms.payment.model.PaymentInfoVO;
import com.zd.csms.payment.model.PaymentVO;
import com.zd.csms.payment.querybean.PaymentInfoQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IPaymentInfoDao extends IDAO{
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return List<PaymentInfoQueryBean>
	 */
	public List<PaymentInfoQueryBean> findList(PaymentInfoQueryVO query, IThumbPageTools tools);
	
	/**
	 * 根据薪酬表Id更新薪酬记录
	 * @param currRole
	 * @param id
	 * @return
	 */
	public boolean updatePaymentState(int id);
	public boolean updatePaymentInfoState(int id);
	
	/**
	 * 更新薪酬记录状态
	 * @param id
	 * @return
	 */
	public boolean updateApprovalState(int id, int status);
	
	/**
	 * 查询是否有审批不通过记录
	 * @param id
	 * @param status
	 * @return
	 */
	public List<PaymentInfoVO> getPaymentInfoByState(int id, int status);

	public List<PaymentVO> findListByYearAndMonth(int year, int month);
	
}
