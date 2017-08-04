package com.zd.csms.planandreport.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.planandreport.contants.ApproveStateContants;
import com.zd.csms.planandreport.dao.IInspectionPlanInfoDAO;
import com.zd.csms.planandreport.querybean.InspectionPlanInfoQueryBean;
import com.zd.csms.planandreport.querybean.InspectionPlanQueryBean;
import com.zd.csms.planandreport.model.InspectionPlanInfoVO;
import com.zd.csms.planandreport.model.InspectionPlanQueryVO;
import com.zd.tools.StringUtil;

public class InspectionPlanInfoDaoImpl extends DAOSupport implements
		IInspectionPlanInfoDAO {

	IBankDAO bankDAO = BankDAOFactory.getBankDAO();

	public InspectionPlanInfoDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 根据计划编号 获取对象
	 */
	public InspectionPlanInfoVO get(String planCode) {
		return (InspectionPlanInfoVO) getJdbcTemplate().queryForObject(
				" select * from t_inspection_planinfo t where t.plancode = ? ",
				new Object[] { planCode },
				new BeanPropertyRowMapper(InspectionPlanInfoVO.class));
	}

	/**
	 * 根据巡检编号查询所需信息(填充巡检计划对象)
	 */
	@Override
	public InspectionPlanInfoQueryBean getDealerInfoByPlanCode(String planCode) {
		return (InspectionPlanInfoQueryBean) getJdbcTemplate()
				.queryForObject(
						"select count(distinct md.province) as provinceAmount,count(distinct md.city) as cityAmount, "
								+ "    count(distinct md.brandid) as brandAmount,count(distinct tb.id) as bankAmount "
								+ " from t_inspection_plan tip  "
								+ " inner join market_dealer md on tip.dealerid = md.id "
								+ " inner join market_dealer_supervisor mds  on md.id = mds.dealerid   "
								+ " inner join t_bank tb  on tb.id = mds.bankid "
								+ " where tip.plancode = ? and md.city > 0 and md.province > 0 and md.brandid > 0 and tb.id > 0 ",
						new Object[] { planCode },
						new BeanPropertyRowMapper(
								InspectionPlanInfoQueryBean.class));
	}
	
	/**
	 * 巡检计划台账(填充巡检计划对象)
	 */
	@Override
	public InspectionPlanInfoQueryBean getLedgerDealerInfo(InspectionPlanQueryVO query) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select tip.id from t_inspection_plan tip "
				+ " inner join market_dealer md  "
				+ " on md.id = tip.dealerid  "
				+ " inner join market_dealer_supervisor mds "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb "
				+ " on tb.id = mds.bankid inner join t_brand tba "
				+ " on tba.id = md.brandid ");

		sql.append(" where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		formatModeChangeSQL(sql, params, query);
		
		return (InspectionPlanInfoQueryBean) getJdbcTemplate()
				.queryForObject(
						"select count(distinct md.province) as provinceAmount,count(distinct md.city) as cityAmount "
								+ " from t_inspection_plan tip  "
								+ " inner join market_dealer md on tip.dealerid = md.id "
								+ " inner join market_dealer_supervisor mds  on md.id = mds.dealerid   "
								+ " inner join t_bank tb  on tb.id = mds.bankid "
								+ " where md.city > 0 and md.province > 0 and tip.id in ( "+ sql +" )",params.toArray(),
								new BeanPropertyRowMapper(InspectionPlanInfoQueryBean.class));
	}
	
	/**
	 * 根据巡检编号查询所需信息(台账填充巡检计划对象)
	 */
	@Override
	public InspectionPlanInfoQueryBean getLedgerInfo(InspectionPlanQueryVO query) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select tip.id from t_inspection_plan tip "
				+ " inner join market_dealer md  "
				+ " on md.id = tip.dealerid  "
				+ " inner join market_dealer_supervisor mds "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb "
				+ " on tb.id = mds.bankid inner join t_brand tba "
				+ " on tba.id = md.brandid ");

		sql.append(" where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();
		formatModeChangeSQL(sql, params, query);
		return (InspectionPlanInfoQueryBean) getJdbcTemplate()
				.queryForObject(
						"select count(1) as stores, count(distinct tip.dealerid) as storesAmount, "
						+ "count(distinct tip.outcontroluserid) as outcontroluserAmount, sum(tip.predictCost) as predictCostAmount, "
						+ "min(tip.predictbegindate) as planBeginTime, max(tip.predictbegindate) as planBeginTimeMax "
								+ " from t_inspection_plan tip where tip.id in ("+ sql +")",params.toArray(),
						new BeanPropertyRowMapper(
								InspectionPlanInfoQueryBean.class));
	}

	/**
	 * 根据巡检编号查询所需信息(填充巡检计划对象)
	 */
	@Override
	public InspectionPlanInfoQueryBean getDealerInfo(String planCode) {
		return (InspectionPlanInfoQueryBean) getJdbcTemplate()
				.queryForObject(
						"select count(1) as stores, count(distinct tip.dealerid) as storesAmount, sum(tip.predictCheckdays) as predictCheckdays, "
						+ "count(distinct tip.outcontroluserid) as outcontroluserAmount,sum(tip.stock) as stockAmount, sum(tip.predictCost) as predictCostAmount, "
						+ "min(tip.predictbegindate) as planBeginTime, max(tip.predictbegindate) as planBeginTimeMax "
								+ " from t_inspection_plan tip where tip.plancode = ?",
						new Object[] { planCode },
						new BeanPropertyRowMapper(
								InspectionPlanInfoQueryBean.class));
	}

	/**
	 * 批量更新审批状态
	 */
	public boolean update(String planCode) {
		return getJdbcTemplate().update(
				" update t_inspection_planinfo t set t.uncheckpasscause = '',t.status = "
						+ ApproveStateContants.CHECKPASS.getCode() + ",t.lastupdatedate = '" + (new Date())
						+ "' where t.plancode in (" + planCode + ")") > 0;
	}
	
	/**
	 * 查询条件
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatModeChangeSQL(StringBuffer sql, List<Object> params,
			InspectionPlanQueryVO query) {

		StringBuffer sb = new StringBuffer();
		boolean flagInfo = false;
		if (null != query.getThreeBankId() && null != bankDAO
				&& query.getThreeBankId() > 0) {
			String oneBankName = bankDAO.getBankNameById(query.getOneBankId());
			String twoBankName = bankDAO.getBankNameById(query.getTwoBankId());
			String threeBankName = bankDAO.getBankNameById(query
					.getThreeBankId());
			if (StringUtil.isNotEmpty(oneBankName)
					&& StringUtil.isNotEmpty(twoBankName)
					&& StringUtil.isNotEmpty(threeBankName)) {
				sql.append(" and tb.bankfullname like ? ");
				String infoString = sb.append(oneBankName).append("/")
						.append(twoBankName).append("/").append(threeBankName)
						.toString();
				params.add("%" + infoString + "%");
			}
			flagInfo = true;
		}
		if (!flagInfo && null != bankDAO && null != query.getTwoBankId()
				&& query.getTwoBankId() > 0) {
			String oneBankName = bankDAO.getBankNameById(query.getOneBankId());
			String twoBankName = bankDAO.getBankNameById(query.getTwoBankId());
			if (StringUtil.isNotEmpty(oneBankName)
					&& StringUtil.isNotEmpty(twoBankName)) {
				sql.append(" and tb.bankfullname like ? ");
				params.add("%"
						+ sb.append(oneBankName).append("/")
								.append(twoBankName).toString() + "%");
			}
		}
		// TODO 省份和城市的查询条件也要拼 角色控制也要拼
		if (null != bankDAO && null != query.getProvinceId()
				&& query.getProvinceId() > 0) {
			sql.append(" and md.province = ? ");
			params.add(query.getProvinceId());
		}
		if (null != bankDAO && null != query.getCityId()
				&& query.getCityId() > 0) {
			sql.append(" and md.city = ? ");
			params.add(query.getCityId());
		}

	}
}
