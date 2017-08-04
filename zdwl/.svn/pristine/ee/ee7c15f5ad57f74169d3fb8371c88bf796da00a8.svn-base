package com.zd.csms.payment.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.zd.core.DAOSupport;
import com.zd.csms.attendance.model.AttendanceInfo;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.payment.contants.PaymentContants;
import com.zd.csms.payment.dao.IPaymentInfoDao;
import com.zd.csms.payment.model.PaymentInfoQueryVO;
import com.zd.csms.payment.model.PaymentInfoVO;
import com.zd.csms.payment.model.PaymentVO;
import com.zd.csms.payment.querybean.PaymentInfoQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

@Repository
public class PaymentInfoDaoImpl extends DAOSupport implements IPaymentInfoDao{
	private IBankDAO bankDAO = BankDAOFactory.getBankDAO();
	
	private static String select_paymentInfo = "select distinct tpi.*,tp.state as payState from t_payment_info tpi "
			+ "inner join t_payment tp on tp.id = tpi.paymentId ";
	public PaymentInfoDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public List<PaymentInfoQueryBean> findList(PaymentInfoQueryVO query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(select_paymentInfo);
		sql.append(" where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		List<PaymentInfoQueryBean> list = null;
		formatModeChangeSQL(sql, params,query);
		sql.append(" order by tp.state asc,tpi.id desc ");
		
		System.out.println("findList sql:"+sql.toString());
		System.out.println("findList params:"+params);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(PaymentInfoQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean updatePaymentState(int id){
		String sql=" update t_payment set state = "+PaymentContants.UNCHECK.getCode()+" where id = ?  ";
		return getJdbcTemplate().update(sql, new Object[]{id})>=0;
	}
	
	public boolean updatePaymentInfoState(int id) {
		String sql=" update t_payment_info set status = " + ApprovalContant.APPROVAL_WAIT.getCode() + " where paymentId = ?  ";
		return getJdbcTemplate().update(sql, new Object[]{id})>=0;
	}

	public boolean updateApprovalState(int id, int state){
		String sql=" update t_payment set state = " + state + " where id = ?  ";
		return getJdbcTemplate().update(sql, new Object[]{id})>=0;
	}
	
	public List<PaymentInfoVO> getPaymentInfoByState(int id, int status){
		String sql="select tpi.* from t_payment_info tpi inner join t_payment tp on tp.id = tpi.paymentId "
				+ " where tpi.paymentId =  ? and tpi.status = ?  ";
		return getJdbcTemplate().query(sql, new Object[]{id, status}, new BeanPropertyRowMapper(PaymentInfoVO.class));
	}
	
 	/**
	 * 视频检查计划 经销商列表 查询条件、、
	 * 
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatModeChangeSQL(StringBuffer sql, List<Object> params,
			PaymentInfoQueryVO query) {
		int currRole = query.getCurrRole();
		
		if (StringUtil.isNotEmpty(query.getStaffName())) {//员工姓名
			sql.append(" and tpi.staffName like ? ");
			params.add("%" + query.getStaffName() + "%");
		}
		if (StringUtil.isNotEmpty(query.getStaffNo())) {//员工工号
			sql.append(" and tpi.staffNo like ? ");
			params.add("%" + query.getStaffNo() + "%");
		}
		if (StringUtil.isNotEmpty(query.getCardNo())) {//身份证号
			sql.append(" and tpi.cardNo like ? ");
			params.add("%" + query.getCardNo() + "%");
		}
		if (StringUtil.isNotEmpty(query.getDealerNames())) {//店名(经销商名称)
			sql.append(" and tpi.dealerNames like ? ");
			params.add("%" + query.getDealerNames() + "%");
		}
		if (null != query.getCityType() && query.getCityType() > 0) {//城市类型
			sql.append(" and tpi.cityType = ?");
			params.add(query.getCityType());
		}
		if (null != query.getStatus() && query.getStatus() > 0) {//审批状态
			sql.append(" and tpi.status = ?");
			params.add(query.getStatus());
		}
		if (null != bankDAO && null != query.getProvinceId()
				&& query.getProvinceId() > 0) {//省份
			sql.append(" and tpi.provinceId = ? ");
			params.add(query.getProvinceId());
		}
		if (null != bankDAO && null != query.getCityId()
				&& query.getCityId() > 0) {//城市
			sql.append(" and tpi.cityId = ? ");
			params.add(query.getCityId());
		}
		if(query.getYear() > 0){//年份
			sql.append(" and tp.year = ? ");
			params.add(query.getYear());
		}
		if(query.getMonth() > 0){//月份
			sql.append(" and tp.month = ? ");
			params.add(query.getMonth());
		}
		
	/*	if(currRole != RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode() && query.getPayState() > 0){
			sql.append(" and tp.state <> 1");
		}*/
		
	}

	@Override
	public List<PaymentVO> findListByYearAndMonth(int year, int month) {
		String sql = "select * from t_payment where year = ? and month = ?";
		return this.getJdbcTemplate().query(sql, new Object[]{year,month},new BeanPropertyRowMapper(PaymentVO.class));
	}
}
