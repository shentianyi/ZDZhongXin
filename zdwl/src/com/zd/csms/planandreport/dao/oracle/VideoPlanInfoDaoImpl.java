package com.zd.csms.planandreport.dao.oracle;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.planandreport.contants.ApproveStateContants;
import com.zd.csms.planandreport.dao.IVideoPlanInfoDAO;
import com.zd.csms.planandreport.querybean.VideoPlanInfoQueryBean;
import com.zd.csms.planandreport.model.VideoPlanInfoVO;

public class VideoPlanInfoDaoImpl extends DAOSupport implements IVideoPlanInfoDAO {

	IBankDAO bankDAO = BankDAOFactory.getBankDAO();

	public VideoPlanInfoDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 *  根据计划编号  获取对象
	 */
	public VideoPlanInfoVO get(String planCode){
		return (VideoPlanInfoVO) getJdbcTemplate().queryForObject(" select * from t_video_planinfo t where t.plancode = ? "
				,new Object[]{planCode},new BeanPropertyRowMapper(VideoPlanInfoVO.class));
	}

	@Override
	public VideoPlanInfoQueryBean getDealerInfoByPlanCode(String planCode) {
		return (VideoPlanInfoQueryBean) getJdbcTemplate().queryForObject(
					"select count(1) as stores,sum(t.checkminute) as checkHoursAmount,"
					+ "sum(t.stock) as stockAmount, min(t.predictcheckdate) as planExecuteTime from t_video_plan t where t.plancode = ? "
				,new Object[]{planCode},new BeanPropertyRowMapper(VideoPlanInfoQueryBean.class));
	}
	
	/**
	 * 批量更新审批状态
	 */
	public boolean update(String planCode){
		return getJdbcTemplate().update(
				" update t_video_planinfo t set t.uncheckpasscause = '',t.status = " + 
						ApproveStateContants.CHECKPASS.getCode() + 
						" where t.plancode in ("+ planCode +")") > 0;
	}
}
