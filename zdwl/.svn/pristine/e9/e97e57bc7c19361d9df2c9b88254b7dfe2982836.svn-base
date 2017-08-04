package com.zd.csms.planandreport.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.planandreport.contants.ApproveStateContants;
import com.zd.csms.planandreport.dao.IInspectionPlanDAO;
import com.zd.csms.planandreport.model.InspectionPlanQueryVO;
import com.zd.csms.planandreport.querybean.InspectionPlanInfoQueryBean;
import com.zd.csms.planandreport.querybean.InspectionPlanQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class InspectionPlanDaoImpl extends DAOSupport implements
		IInspectionPlanDAO {

	IBankDAO bankDAO = BankDAOFactory.getBankDAO();

	public InspectionPlanDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 分页加载- 巡检计划列表所有信息-未审核
	 */
	public List<InspectionPlanInfoQueryBean> findList(
			InspectionPlanQueryVO query, IThumbPageTools tools) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select tipo.* from t_inspection_planinfo tipo "
				+ "inner join t_rbac_user tru on tru.id = tipo.incontroluserid "
				+ " where tipo.plancode in ( "
				+ "select distinct(vplan.plancode) from ( select tip.plancode, "
				+ " tip.dealerid, "
				+ " md.dealername, "
				+ " md.province as provinceId, "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename, "
				+ " md.city as cityId, "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname, "
				+ " tb.id as bankId, tb.bankfullname, "
				+ " tba.id as brandId, tba.name as brandname "
				+ " from t_inspection_plan tip "
				+ " inner join market_dealer md on md.id = tip.dealerid "
				+ " inner join market_dealer_supervisor mds "
				+ " on md.id = mds.dealerid " + " inner join t_bank tb "
				+ " on tb.id = mds.bankid " + " inner join t_brand tba "
				+ " on tba.id = md.brandid ");
		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<InspectionPlanInfoQueryBean> list = null;

		if (currRole == RoleConstants.WINDCONRTOL_AMALDAR.getCode()) {// 当前角色为风控经理
			if (StringUtil.isNotEmpty(query.getDealerName())) {
				sql.append(" and md.dealername like ? ");
				params.add("%" + query.getDealerName() + "%");
			}
		}

		formatModeChangeSQL(sql, params, query);
		if (currRole==19) {
			sql.append(" and tip.outcontroluserid is not null ");
		}
		sql.append(") vplan ) ");
		if (StringUtil.isNotEmpty(query.getInControlUserName())) {// 内控专员名称
			sql.append(" and tru.username like ? ");
			params.add("%" + query.getInControlUserName() + "%");
		}
		formatSQL(sql, params, query);

		if (StringUtil.isNotEmpty(query.getPlanCode())) {// 巡检编号
			sql.append(" and tipo.plancode = '" + query.getPlanCode() + "'");
		}
//		if (currRole == RoleConstants.WINDCONRTOL_AMALDAR.getCode()) {// 当前角色为风控经理
//			sql.append(" and tipo.status > "
//					+ ApproveStateContants.UNCOMMIT.getCode());
//		}
		// 当前角色为风控经理
		if (currRole == RoleConstants.WINDCONRTOL_AMALDAR.getCode()
				&& null != query.getFlag() && query.getFlag() == 1) {
			sql.append(" and tipo.status = "+ ApproveStateContants.UNCHECK.getCode());
			sql.append(" or tipo.status = "+ ApproveStateContants.CHECKING.getCode());
			sql.append(" or tipo.nextapproval ="+RoleConstants.SR.getCode());
		}else if (currRole == RoleConstants.RISKMANAGEMENT_MINISTER.getCode()//风险管理部部长
				&& null != query.getFlag() && query.getFlag() == 1) {
			sql.append(" and tipo.status = "+ ApproveStateContants.CHECKING.getCode());
			sql.append(" or tipo.nextapproval ="+RoleConstants.RISKMANAGEMENT_MINISTER.getCode());
		}else {
			sql.append(" and tipo.status != "
					+ ApproveStateContants.CHECKPASS.getCode());//除了通过其它状态在未审核都能查看
			sql.append(" and tipo.status > 0 ");
		}
		sql.append(" order by tipo.id desc ");
		try {
			list = tools
					.goPage(sql.toString(), params.toArray(),
							new BeanPropertyRowMapper(
									InspectionPlanInfoQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分页加载- 巡检计划台账列表所有信息-未审核
	 */
	public List<InspectionPlanInfoQueryBean> findListLedger(
			InspectionPlanQueryVO query, IThumbPageTools tools) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select tipo.* from t_inspection_planinfo tipo "
				+ "inner join t_rbac_user tru on tru.id = tipo.incontroluserid "
				+ " where tipo.plancode in ( "
				+ "select distinct(vplan.plancode) from ( select tip.plancode, "
				+ " tip.dealerid, "
				+ " md.dealername, "
				+ " md.province as provinceId, "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename, "
				+ " md.city as cityId, "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname, "
				+ " tb.id as bankId, tb.bankfullname, "
				+ " tba.id as brandId, tba.name as brandname "
				+ " from t_inspection_plan tip "
				+ " inner join market_dealer md on md.id = tip.dealerid "
				+ " inner join market_dealer_supervisor mds "
				+ " on md.id = mds.dealerid " + " inner join t_bank tb "
				+ " on tb.id = mds.bankid " + " inner join t_brand tba "
				+ " on tba.id = md.brandid ");
		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<InspectionPlanInfoQueryBean> list = null;

		formatModeChangeSQL(sql, params, query);
		sql.append(" ) vplan ) ");
		if (StringUtil.isNotEmpty(query.getInControlUserName())) {// 内控专员名称
			sql.append(" and tru.username like ? ");
			params.add("%" + query.getInControlUserName() + "%");
		}
		formatSQL(sql, params, query);

		if (StringUtil.isNotEmpty(query.getPlanCode())) {// 巡检编号
			sql.append(" and tipo.plancode = '" + query.getPlanCode() + "'");
		}
		if (null != query.getFlag() && query.getFlag() == 1) {
			sql.append(" and tipo.status = "
					+ ApproveStateContants.UNCHECK.getCode());
			sql.append(" or tipo.status = "
					+ ApproveStateContants.CHECKPASS.getCode());
		} else {
			sql.append(" and tipo.status > "
					+ ApproveStateContants.UNCHECK.getCode());
		}
		if(query.getCurrRole()==RoleConstants.BANK_APPROVE.getCode()){
			sql.append(" and tru.client_id = "+query.getUserVO().getClient_id());
		}
		sql.append(" order by tipo.id desc ");
		try {
			list = tools
					.goPage(sql.toString(), params.toArray(),
							new BeanPropertyRowMapper(
									InspectionPlanInfoQueryBean.class));
			System.out.println("findListLedger sql:"+sql.toString());
			System.out.println("findListLedger params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 风控经理查询条件
	 * 
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatSQL(StringBuffer sql, List<Object> params,
			InspectionPlanQueryVO query) {
		if (query.getCurrRole() == RoleConstants.WINDCONRTOL_AMALDAR.getCode()) {// 当前角色为风控经理
			if (query.getCheckBeginDate() != null) {
				sql.append(" and to_char(to_date(tipo.plansubmittime,'yyyy-mm-dd'),'yyyymmdd') >=? ");
				params.add(DateUtil.getStringFormatByDate(
						query.getCheckBeginDate(), "yyyyMMdd"));
			}
			if (query.getCheckEndDate() != null) {
				sql.append(" and to_char(to_date(tipo.plansubmittime,'yyyy-mm-dd'),'yyyymmdd') <=? ");
				params.add(DateUtil.getStringFormatByDate(
						query.getCheckEndDate(), "yyyyMMdd"));
			}
		}
	}

	public List<InspectionPlanQueryBean> loadDealerList(
			InspectionPlanQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();

		sql.append(" select md.id as dealerId, "
				+ " md.dealername, "
				+ " md.province as provinceId, "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename, "
				+ " md.city as cityId, "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname, "
				+ " tb.id as bankId, "
				+ " tb.bankfullname, "
				+ " tba.id as brandId, "
				+ " tba.name as brandname, "
				+ " (select count(1) "
				+ "   from t_supervise_import tsi "
				+ "  inner join t_draft td "
				+ "     on tsi.draft_num = td.draft_num "
				+ "   where td.distribid = md.id  "
				+ "     and ((tsi.state = 2 and tsi.apply = 0) or tsi.state = 4 or "
				+ "        tsi.state = 5 or tsi.state = 6 or "
				+ "        (tsi.state = 3 and tsi.apply = 1))) as stock, "
				+ "(select max(tipo.createdate) from t_inspection_planinfo tipo "
				+ "	inner join t_inspection_plan tip on tipo.plancode = tip.plancode  "
				+ "	where tip.dealerid = md.id) as recentCheckTime "
				+ " from market_dealer md "
				+ " inner join market_dealer_supervisor mds on md.id = mds.dealerid"
				+ " inner join t_bank tb on tb.id = mds.bankid"
				+ " inner join t_brand tba on tba.id = md.brandid ");

		sql.append(" where 1=1 ");
		String dealerIds = null;
		if (StringUtil.isNotEmpty(query.getPlanCode())) {
			dealerIds = "select md.id"
					+ " from t_inspection_plan tip  " + " inner join market_dealer md  "
					+ " on md.id = tip.dealerid  "
					+ " inner join t_inspection_planinfo tipo on tip.plancode = tipo.plancode "
					+ " inner join market_dealer_supervisor mds  "
					+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
					+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
					+ " on tba.id = md.brandid and (tip.reportstatus != 2 or tip.reportstatus is null) "
//					+ " and tipo.status > 0 and tip.plancode = " + query.getPlanCode()
					;
			sql.append(" and md.id not in ("+dealerIds+")");
		}else{//待选经销商   已生成报告且状态不为2(已完成)的  不在查询范围内
			dealerIds = "select md.id"
					+ " from t_inspection_plan tip  " + " inner join market_dealer md  "
					+ " on md.id = tip.dealerid  "
					+ " inner join t_inspection_planinfo tipo on tip.plancode = tipo.plancode "
					+ " inner join market_dealer_supervisor mds  "
					+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
					+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
					+ " on tba.id = md.brandid and tipo.status > 0 and (tip.reportstatus != 2 or tip.reportstatus is null) ";
					sql.append(" and md.id not in ("+dealerIds+")");
		}
		
		String recentCheckTime = "(select max(tipo.createdate) from t_inspection_planinfo tipo "
				+ "inner join t_inspection_plan tip on tipo.plancode = tip.plancode "
				+ "where tip.dealerid = md.id)";
		// and md.id not in (select tp.dealerid from t_inspection_plan tp )
		List<Object> params = new ArrayList<Object>();
		List<InspectionPlanQueryBean> list = null;
		if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and md.dealername like ?");
			params.add("%" + query.getDealerName() + "%");
		}
		if (StringUtil.isNotEmpty(query.getBrandName())) {
			sql.append(" and tba.name like ?");
			params.add("%" + query.getBrandName() + "%");
		}
		if (query.getCheckBeginDate() != null) {
			sql.append(" and to_char(" + recentCheckTime + ",'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					query.getCheckBeginDate(), "yyyyMMdd"));
		}
		if (query.getCheckEndDate() != null) {
			sql.append(" and to_char(" + recentCheckTime + ",'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCheckEndDate(),
					"yyyyMMdd"));
		}
		formatModeChangeSQL(sql, params, query);

		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(InspectionPlanQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据经销商Id-加载符合条件的经销商对象
	 */
	@Override
	public InspectionPlanQueryBean loadDealerInfo(int id) {
		StringBuffer sql = new StringBuffer();

		sql.append(" select md.id as dealerId, "
				+ " md.dealername, "
				+ " md.province as provinceId, "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename, "
				+ " md.city as cityId, "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname, "
				+ " tb.id as bankId, "
				+ " tb.bankfullname, "
				+ " tba.id as brandId, "
				+ " tba.name as brandname, "
				+ " (select count(1) "
				+ "   from t_supervise_import tsi "
				+ "  inner join t_draft td "
				+ "     on tsi.draft_num = td.draft_num "
				+ "   where td.distribid = md.id  "
				+ "     and ((tsi.state = 2 and tsi.apply = 0) or tsi.state = 4 or "
				+ "        tsi.state = 5 or tsi.state = 6 or "
				+ "        (tsi.state = 3 and tsi.apply = 1))) as stock, "
				+ "(select max(tipo.lastupdatedate) from t_inspection_planinfo tipo "
				+ "	inner join t_inspection_plan tip on tipo.plancode = tip.plancode  "
				+ "	where tip.dealerid = md.id) as recentCheckTime "
				+ " from market_dealer md "
				+ " inner join market_dealer_supervisor mds "
				+ "   on md.id = mds.dealerid " + " inner join t_bank tb "
				+ "    on tb.id = mds.bankid " + " inner join t_brand tba "
				+ "   on tba.id = md.brandid ");

		sql.append(" where md.id = ? ");

		return (InspectionPlanQueryBean) getJdbcTemplate().queryForObject(
				sql.toString(), new Object[] { id },
				new BeanPropertyRowMapper(InspectionPlanQueryBean.class));
	}

	public List<InspectionPlanQueryBean> findListAlready(
			InspectionPlanQueryVO query, IThumbPageTools tools) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select (select count(1) "
				+ " from t_supervise_import tsi  "
				+ " inner join t_draft td  "
				+ "  on tsi.draft_num = td.draft_num  "
				+ " where td.distribid = md.id   "
				+ " and ((tsi.state = 2 and tsi.apply = 0) or tsi.state = 4 or  "
				+ " tsi.state = 5 or tsi.state = 6 or  "
				+ " (tsi.state = 3 and tsi.apply = 1))) as stock  , "
				+ " tip.plancode, tip.outcontroluserid,tip.predictcost,tip.recentchecktime,tip.predictCheckdays, "
				+ " tip.remark,tip.dealerid, tip.id, tip.escortUserInfo, tip.predictbegindate, "
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname "
				+ " from t_inspection_plan tip  "
				+ " inner join market_dealer md  "
				+ " on md.id = tip.dealerid  "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid ");

		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<InspectionPlanQueryBean> list = null;
		sql.append(" and tip.plancode = " + query.getPlanCode());
		
		if(currRole == 19){
			//sql.append(" and tip.outcontroluserid = 19 ");
			sql.append(" and tip.outcontroluserid = ? ");
			params.add(query.getOutControlUserId());
		}

		sql.append(" order by tip.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(InspectionPlanQueryBean.class));
			System.out.println("findListAlready sql:"+sql.toString());
			System.out.println("findListAlready params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据巡检编号查询经销商记录
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<InspectionPlanQueryBean> findListByPlanCode(
			InspectionPlanQueryVO query) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select tip.plancode, tip.id "
				+ " from t_inspection_plan tip  "
				+ " inner join market_dealer md  "
				+ " on md.id = tip.dealerid  "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid ");

		sql.append(" where 1=1 ");

		List<InspectionPlanQueryBean> list = null;
		sql.append(" and tip.plancode = " + query.getPlanCode());

		sql.append(" order by tip.id desc ");
		try {
			list = getJdbcTemplate().query(sql.toString(),
					new BeanPropertyRowMapper(InspectionPlanQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 巡检计划台账
	 */
	public List<InspectionPlanQueryBean> findListDealerLedger(
			InspectionPlanQueryVO query, IThumbPageTools tools) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select (select count(1) "
				+ " from t_supervise_import tsi  "
				+ " inner join t_draft td  "
				+ "  on tsi.draft_num = td.draft_num  "
				+ " where td.distribid = md.id   "
				+ " and ((tsi.state = 2 and tsi.apply = 0) or tsi.state = 4 or  "
				+ " tsi.state = 5 or tsi.state = 6 or  "
				+ " (tsi.state = 3 and tsi.apply = 1))) as stock  , "
				+ " tip.plancode, tip.outcontroluserid,tip.predictcost,tip.recentchecktime, "
				+ " tip.remark,tip.dealerid, tip.id, tip.escortUserInfo, tip.predictbegindate, "
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId, tb.bankfullname, tip.predictCheckdays, "
				+ " tba.id as brandId, tba.name as brandname "
				+ " from t_inspection_plan tip  "
				+ " inner join market_dealer md on md.id = tip.dealerid "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid ");

		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<InspectionPlanQueryBean> list = null;

		formatModeChangeSQL(sql, params, query);
		sql.append(" order by tip.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(InspectionPlanQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 巡检计划台账(获取巡检编号)
	 */
	public List<InspectionPlanQueryBean> getDealerPlanCodes(
			InspectionPlanQueryVO query) {
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
		List<InspectionPlanQueryBean> list = null;

		formatModeChangeSQL(sql, params, query);
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(InspectionPlanQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分页加载-计划列表所有信息-已审核
	 */
	public List<InspectionPlanInfoQueryBean> findListAlreadyCheck(
			InspectionPlanQueryVO query, IThumbPageTools tools) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select tipo.* from t_inspection_planinfo tipo "
				+ "inner join t_rbac_user tru on tru.id = tipo.incontroluserid "
				+ "where tipo.plancode in ( "
				+ "select distinct(vplan.plancode) from ( select tip.plancode, "
				+ " tip.dealerid, "
				+ " md.dealername, "
				+ " md.province as provinceId, "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename, "
				+ " md.city as cityId, "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname, "
				+ " tb.id as bankId, " + " tb.bankfullname, "
				+ " tba.id as brandId, " + " tba.name as brandname "
				+ " from t_inspection_plan tip "
				+ " inner join market_dealer md " + " on md.id = tip.dealerid "
				+ " inner join market_dealer_supervisor mds "
				+ " on md.id = mds.dealerid " + " inner join t_bank tb "
				+ " on tb.id = mds.bankid " + " inner join t_brand tba "
				+ " on tba.id = md.brandid ");
		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<InspectionPlanInfoQueryBean> list = null;
		formatModeChangeSQL(sql, params, query);

		sql.append(") vplan ) ");
		formatSQL(sql, params, query);
		if (StringUtil.isNotEmpty(query.getInControlUserName())) {
			sql.append(" and tru.username like ?");
			params.add("%" + query.getInControlUserName() + "%");
		}

//		if (currRole == RoleConstants.WINDCONRTOL_AMALDAR.getCode()) {// 当前角色为风控经理
//			// 只能查看已提交的数据
//			sql.append(" and tipo.status != "
//					+ ApproveStateContants.UNCOMMIT.getCode());
//		} else {
			/*sql.append(" and tipo.status > "
					+ ApproveStateContants.UNCHECK.getCode());*///除了未审核其他状态可以看
//		}
		sql.append(" and tipo.status = "
				+ ApproveStateContants.CHECKPASS.getCode());//查看已通过的数据
		if (currRole == RoleConstants.WINDCONRTOL_AMALDAR.getCode()) {
			//风控经理-审核未通过
			sql.append(" or tipo.status = "
					+ ApproveStateContants.UNCHECKPASS.getCode());
		}
		sql.append(" order by tipo.id desc ");
		try {
			list = tools
					.goPage(sql.toString(), params.toArray(),
							new BeanPropertyRowMapper(
									InspectionPlanInfoQueryBean.class));
			System.out.println("findListAlreadyCheck sql:"+sql.toString());
			System.out.println("findListAlreadyCheck params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 关联删除经销商记录表的记录
	 */
	public boolean deleteByPlanCode(String planCode) {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from t_inspection_plan t where t.plancode = ? ");
		return getJdbcTemplate().update(sql.toString(),
				new Object[] { planCode }) > 0;
	}
	
	/**
	 * 删除未进行新增操作的经销商计划信息
	 */
	public boolean deletePlan() {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from t_inspection_planinfo t where t.status = ?");
		return getJdbcTemplate().update(sql.toString(),
				new Object[] { 0 }) > 0;
	}
	/**
	 * 删除未进行新增操作的经销商计划详细信息
	 */
	public boolean deletePlanInfo() {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from t_inspection_plan t where t.plancode in (select tp.plancode from t_inspection_planinfo tp where tp.status = 0) ");
		return getJdbcTemplate().update(sql.toString()) > 0;
	}

	/**
	 * 视频检查计划 经销商列表 查询条件
	 * 
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
	
	@SuppressWarnings("unchecked")
	public List<InspectionPlanInfoQueryBean> ExtFindListLedgerNotAudited(
			InspectionPlanQueryVO query){
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select tipo.*, (select tru.username from t_rbac_user tru where tru.id = tipo.incontroluserid) as incontroluserNTT from t_inspection_planinfo tipo "
				+ "inner join t_rbac_user tru on tru.id = tipo.incontroluserid "
				+ " where tipo.plancode in ( "
				+ "select distinct(vplan.plancode) from ( select tip.plancode, "
				+ " tip.dealerid, "
				+ " md.dealername, "
				+ " md.province as provinceId, "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename, "
				+ " md.city as cityId, "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname, "
				+ " tb.id as bankId, tb.bankfullname, "
				+ " tba.id as brandId, tba.name as brandname "
				+ " from t_inspection_plan tip "
				+ " inner join market_dealer md on md.id = tip.dealerid "
				+ " inner join market_dealer_supervisor mds "
				+ " on md.id = mds.dealerid " + " inner join t_bank tb "
				+ " on tb.id = mds.bankid " + " inner join t_brand tba "
				+ " on tba.id = md.brandid ");
		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<InspectionPlanInfoQueryBean> list = null;

		formatModeChangeSQL(sql, params, query);
		sql.append(" ) vplan ) ");
		if (StringUtil.isNotEmpty(query.getInControlUserName())) {// 内控专员名称
			sql.append(" and tru.username like ? ");
			params.add("%" + query.getInControlUserName() + "%");
		}
		formatSQL(sql, params, query);

		if (StringUtil.isNotEmpty(query.getPlanCode())) {// 巡检编号
			sql.append(" and tipo.plancode = '" + query.getPlanCode() + "'");
		}
		if (null != query.getFlag() && query.getFlag() == 1) {
			sql.append(" and tipo.status = "
					+ ApproveStateContants.UNCHECK.getCode());
		} else {
			sql.append(" and tipo.status > "
					+ ApproveStateContants.UNCHECK.getCode());
		}
		if(query.getCurrRole()==RoleConstants.BANK_APPROVE.getCode()){
			sql.append(" and tru.client_id = "+query.getUserVO().getClient_id());
		}
		sql.append(" order by tipo.id desc ");
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(),
							new BeanPropertyRowMapper(InspectionPlanInfoQueryBean.class));
			System.out.println("ExtFindListLedgerNotAudited sql:"+sql.toString());
			System.out.println("ExtFindListLedgerNotAudited params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
