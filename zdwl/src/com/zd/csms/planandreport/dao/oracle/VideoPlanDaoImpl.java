package com.zd.csms.planandreport.dao.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.planandreport.contants.ApproveStateContants;
import com.zd.csms.planandreport.dao.IVideoPlanDAO;
import com.zd.csms.planandreport.model.VideoPlanQueryVO;
import com.zd.csms.planandreport.querybean.VideoPlanInfoQueryBean;
import com.zd.csms.planandreport.querybean.VideoPlanQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.DateUtil;
import com.zd.csms.windcontrol.constants.EditStatusConstants;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class VideoPlanDaoImpl extends DAOSupport implements IVideoPlanDAO {

	IBankDAO bankDAO = BankDAOFactory.getBankDAO();

	public VideoPlanDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	public List<VideoPlanQueryBean> loadDealerList(VideoPlanQueryVO query,
			IThumbPageTools tools) {
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
				+ "(select max(tvpl.submitReportTime) from t_video_plan tvpl "
				+ "	inner join t_video_planinfo tvplo on tvplo.plancode = tvpl.plancode  "
				+ "	where tvpl.dealerid = md.id) as recentCheckTime "
				+ " from market_dealer md"
				+ " inner join market_dealer_supervisor mds "
				+ "   on md.id = mds.dealerid " + " inner join t_bank tb "
				+ "    on tb.id = mds.bankid " + " inner join t_brand tba "
				+ "   on tba.id = md.brandid ");

		sql.append(" where 1=1  ");
		String dealerIds = null;
		if (StringUtil.isNotEmpty(query.getPlanCode())) {
			dealerIds = "select md.id"
					+ " from t_video_plan tvp  " + " inner join market_dealer md  "
					+ " on md.id = tvp.dealerid  "
					+ " inner join t_video_planinfo tvpo on tvp.plancode = tvpo.plancode "
					+ " inner join market_dealer_supervisor mds  "
					+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
					+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
					+ " on tba.id = md.brandid and (tvp.reportstatus != 2 or tvp.reportstatus is null) "
//					+ " and tvp.plancode = " + query.getPlanCode()
//					+ " and tvpo.status > 0 "
					;
			sql.append(" and md.id not in ("+dealerIds+")");
		}else{//待选经销商   已生成报告且状态不为2(已完成)的  不在查询范围内
			dealerIds = "select md.id"
					+ " from t_video_plan tvp  " + " inner join market_dealer md  "
					+ " on md.id = tvp.dealerid  "
					+ " inner join t_video_planinfo tvpo on tvp.plancode = tvpo.plancode "
					+ " inner join market_dealer_supervisor mds  "
					+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
					+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
					+ " on tba.id = md.brandid and tvpo.status > 0 and (tvp.reportstatus != 2 or tvp.reportstatus is null) ";
					sql.append(" and md.id not in ("+dealerIds+")");
		}
		
		List<Object> params = new ArrayList<Object>();
		List<VideoPlanQueryBean> list = null;
		if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and md.dealername like ?");
			params.add("%" + query.getDealerName() + "%");
		}
		if (StringUtil.isNotEmpty(query.getBrandName())) {
			sql.append(" and tba.name like ?");
			params.add("%" + query.getBrandName() + "%");
		}
		formatModeChangeSQL(sql, params, query);

		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(VideoPlanQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 视频检查计划 经销商列表 查询条件
	 * 
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatModeChangeSQL(StringBuffer sql, List<Object> params,
			VideoPlanQueryVO query) {

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
				params.add("%"
						+ sb.append(oneBankName).append("/")
								.append(twoBankName).append("/")
								.append(threeBankName).toString() + "%");
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

	/**
	 * 分页加载-计划列表所有信息-未审核
	 */
	public List<VideoPlanInfoQueryBean> findList(VideoPlanQueryVO query,
			IThumbPageTools tools) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select tvpo.* from t_video_planinfo tvpo "
				+ "inner join t_rbac_user tru on tru.id = tvpo.videouserid "
				+ " where tvpo.plancode in ( "
				+ "select distinct(vplan.plancode) from ( select tvp.plancode, "
				+ " tvp.dealerid, "
				+ " md.dealername, "
				+ " md.province as provinceId, "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename, "
				+ " md.city as cityId, "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname, "
				+ " tb.id as bankId, " + " tb.bankfullname, "
				+ " tba.id as brandId, " + " tba.name as brandname "
				+ " from t_video_plan tvp " + " inner join market_dealer md "
				+ " on md.id = tvp.dealerid "
				+ " inner join market_dealer_supervisor mds "
				+ " on md.id = mds.dealerid " + " inner join t_bank tb "
				+ " on tb.id = mds.bankid " + " inner join t_brand tba "
				+ " on tba.id = md.brandid ");
		sql.append(" where 1=1 ");
		// if (currRole == RoleConstants.VIDEO_AMALDAR.getCode()) {
		// sql.append(" and tvpo.status != ");
		// }
		List<Object> params = new ArrayList<Object>();
		List<VideoPlanInfoQueryBean> list = null;
		formatModeChangeSQL(sql, params, query);

		sql.append(") vplan ) ");
		if (currRole == RoleConstants.VIDEO_AMALDAR.getCode()) {// 当前角色为视频经理
			if (StringUtil.isNotEmpty(query.getVideoUserName())) {
				sql.append(" and tru.username like ?");
				params.add("%" + query.getVideoUserName() + "%");
			}
//			sql.append(" and tvpo.status > "
//					+ ApproveStateContants.UNCOMMIT.getCode());
			if (null != query.getFlag() && query.getFlag() == 1) {
				sql.append(" and tvpo.status = "+ ApproveStateContants.UNCHECK.getCode());
				sql.append(" or tvpo.status = "+ ApproveStateContants.CHECKING.getCode());
				sql.append(" and tvpo.nextApproval = "+ RoleConstants.RISKMANAGEMENT_MINISTER.getCode());
			}
		}else if (currRole == RoleConstants.RISKMANAGEMENT_MINISTER.getCode()
				&&null != query.getFlag() && query.getFlag() == 1) {// 当前角色为风险管理部长
			sql.append(" and tvpo.status = "+ ApproveStateContants.CHECKING.getCode());
			sql.append(" and tvpo.nextApproval = "+ RoleConstants.RISKMANAGEMENT_MINISTER.getCode());
			
		}/*else if (currRole == RoleConstants.RISKMANAGEMENT_MINISTER.getCode()
				&&null != query.getFlag() && query.getFlag() == 1) {// 当前角色为视频专员 
			sql.append(" and tvpo.status = "+ ApproveStateContants.UNCHECK.getCode());
			
		}*/else{
			sql.append(" and tvpo.status != "
					+ ApproveStateContants.CHECKPASS.getCode());
			sql.append(" and tvpo.status > 0 ");
		}
		
		sql.append(" order by tvpo.planexecutetime desc nulls last ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(VideoPlanInfoQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分页加载-计划列表所有信息-已审核
	 */
	public List<VideoPlanInfoQueryBean> findListAlreadyCheck(
			VideoPlanQueryVO query, IThumbPageTools tools) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select tvpo.* from t_video_planinfo tvpo "
				+ "inner join t_rbac_user tru on tru.id = tvpo.videouserid "
				+ "where tvpo.plancode in ( "
				+ "select distinct(vplan.plancode) from ( select tvp.plancode, "
				+ " tvp.dealerid, "
				+ " md.dealername, "
				+ " md.province as provinceId, "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename, "
				+ " md.city as cityId, "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname, "
				+ " tb.id as bankId, "
				+ " tb.bankfullname, "
				+ " tba.id as brandId, "
				+ " tba.name as brandname "
				+ " from t_video_plan tvp "
				+ " inner join market_dealer md "
				+ " on md.id = tvp.dealerid "
				+ " inner join market_dealer_supervisor mds "
				+ " on md.id = mds.dealerid "
				+ " inner join t_bank tb "
				+ " on tb.id = mds.bankid "
				+ " inner join t_brand tba "
				+ " on tba.id = md.brandid ");
		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<VideoPlanInfoQueryBean> list = null;
		formatModeChangeSQL(sql, params, query);

		sql.append(") vplan ) ");
		if (StringUtil.isNotEmpty(query.getVideoUserName())) {
			sql.append(" and tru.username like ?");
			params.add("%" + query.getVideoUserName() + "%");
		}
//		if (currRole == RoleConstants.VIDEO_AMALDAR.getCode()) {// 当前角色为视频经理
																// 只能查看已提交的数据
//			sql.append(" and tvpo.status != "
//					+ ApproveStateContants.UNCOMMIT.getCode());
			/*sql.append(" and tvpo.status > "
					+ ApproveStateContants.UNCHECK.getCode());*/
//		}
		if (currRole == RoleConstants.VIDEO_AMALDAR.getCode()) {
			sql.append(" and (tvpo.status = ? or tvpo.status = ?)");// 只能查看审核通过的数据
			params.add(ApproveStateContants.CHECKPASS.getCode());
			params.add(ApproveStateContants.UNCHECKPASS.getCode());
		}
		sql.append(" order by tvpo.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(VideoPlanInfoQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据经销商Id-加载符合条件的经销商对象
	 */
	@Override
	public VideoPlanQueryBean loadDealerInfo(int id) {
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
				+ " (select max(tvpl.submitReportTime) from  t_video_plan tvpl "
				+ "	inner join t_video_planinfo tvplo on tvplo.plancode = tvpl.plancode  "
				+ "	where tvpl.dealerid = md.id) as recentCheckTime "
				+ " from market_dealer md "
				+ " inner join market_dealer_supervisor mds "
				+ "   on md.id = mds.dealerid " + " inner join t_bank tb "
				+ "    on tb.id = mds.bankid " + " inner join t_brand tba "
				+ "   on tba.id = md.brandid ");

		sql.append(" where md.id = ? ");

		return (VideoPlanQueryBean) getJdbcTemplate().queryForObject(
				sql.toString(), new Object[] { id },
				new BeanPropertyRowMapper(VideoPlanQueryBean.class));
	}

	/**
	 * 分页加载-计划列表所有信息-未审核
	 */
	public List<VideoPlanQueryBean> findListAlready(VideoPlanQueryVO query,
			IThumbPageTools tools) {
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
				+ " tvp.plancode, tvp.videouserid,tvp.checkminute,tvp.predictcheckdate,tvp.recentCheckTime, "
				+ " tvp.dealerid, tvp.id , "
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname "
				+ " from t_video_plan tvp  " + " inner join market_dealer md  "
				+ " on md.id = tvp.dealerid  "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid ");

		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<VideoPlanQueryBean> list = null;
		sql.append(" and tvp.plancode = " + query.getPlanCode());

		sql.append(" order by tvp.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(VideoPlanQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分页加载-经销商所有信息-台账
	 */
	public List<VideoPlanQueryBean> findListDealerLedger(
			VideoPlanQueryVO query, IThumbPageTools tools) {
		int currRole = query.getCurrRole();
		StringBuffer sql = new StringBuffer();

		sql.append("select ROW_NUMBER() OVER (ORDER BY tvp.predictCheckDate ASC) planCount, (select count(1) "
				+ " from t_supervise_import tsi  "
				+ " inner join t_draft td  "
				+ "  on tsi.draft_num = td.draft_num  "
				+ " where td.distribid = md.id   "
				+ " and ((tsi.state = 2 and tsi.apply = 0) or tsi.state = 4 or  "
				+ " tsi.state = 5 or tsi.state = 6 or  "
				+ " (tsi.state = 3 and tsi.apply = 1))) as stock  , "
				+ " tvp.plancode, tvp.videouserid,tvp.checkminute,tvp.predictcheckdate, "
				+ " tvp.dealerid, tvp.id, tvp.submittime, "
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname "
				+ " from t_video_plan tvp  " + " inner join market_dealer md "
				+ " on md.id = tvp.dealerid  "
				+ " inner join t_rbac_user tru on tru.id = tvp.videouserid "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid ");

		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<VideoPlanQueryBean> list = null;
		if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and md.dealername like ?");
			params.add("%" + query.getDealerName() + "%");
		}
		if (StringUtil.isNotEmpty(query.getBrandName())) {
			sql.append(" and tba.name like ?");
			params.add("%" + query.getBrandName() + "%");
		}
		if (StringUtil.isNotEmpty(query.getVideoUserName())) {
			sql.append(" and tru.username like ?");
			params.add("%" + query.getVideoUserName() + "%");
		}
		if (query.getSubmitTimeBegin() != null) {
			sql.append(" and to_char(tvp.submittime,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					query.getSubmitTimeBegin(), "yyyyMMdd"));
		}
		if (query.getSubmitTimeEnd() != null) {
			sql.append(" and to_char(tvp.submittime,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getSubmitTimeEnd(),
					"yyyyMMdd"));
		}
		formatModeChangeSQL(sql, params, query);
		// sql.append(" and tvp.plancode = " + query.getPlanCode());

		sql.append(" order by tvp.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(VideoPlanQueryBean.class));
			System.out.println("VideoFindListDealerLedger sql:"+sql.toString());
			System.out.println("VideoFindListDealerLedger params:"+params);
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
		sql.append(" delete from t_video_plan t where t.plancode = ? ");
		return getJdbcTemplate().update(sql.toString(),
				new Object[] { planCode }) > 0;
	}

	/**
	 * 批量更新提交时间DateUtil.StringToDate(date)
	 */
	public boolean update(String planCode) {
			return getJdbcTemplate().update(" update t_video_plan t set t.submittime = ? where t.plancode in (" + planCode + ")", 
					new Object[]{new Date()})>0;
	}
	
	/**
	 * 删除未进行新增操作的经销商计划信息
	 */
	public boolean deletePlan() {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from t_video_planinfo t where t.status = ?");
		return getJdbcTemplate().update(sql.toString(),
				new Object[] { 0 }) > 0;
	}
	
	/**
	 * 删除未进行新增操作的经销商计划详细信息
	 */
	public boolean deletePlanInfo() {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from t_video_plan t where t.plancode in (select tp.plancode from t_video_planinfo tp where tp.status = 0) ");
		return getJdbcTemplate().update(sql.toString()) > 0;
	}

	
	/**
	 * 更新视频检查报告状态
	 * @param status
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateStatus(int status, int id) {
		if(EditStatusConstants.SUBMIT.getCode()==status){
			String sql=" update t_video_plan set reportStatus=?  , submitReportTime=?  where id = ?  ";
			return getJdbcTemplate().update(sql, new Object[]{status, new Date() ,id})>=0;
		}else{
			String sql=" update t_video_plan set reportStatus=?  where id = ?  ";
			return getJdbcTemplate().update(sql, new Object[]{status ,id})>=0;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VideoPlanQueryBean> ExtVideoFindListDealerLedger(VideoPlanQueryVO query) {
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
				+ " tvp.plancode, tvp.videouserid,tvp.checkminute,tvp.predictcheckdate, "
				+ " tvp.dealerid, tvp.id, tvp.submittime, "
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname ,tru.username as videoUserName "
				+ " from t_video_plan tvp  " + " inner join market_dealer md "
				+ " on md.id = tvp.dealerid  "
				+ " inner join t_rbac_user tru on tru.id = tvp.videouserid "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid ");

		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<VideoPlanQueryBean> list = null;
		if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and md.dealername like ?");
			params.add("%" + query.getDealerName() + "%");
		}
		if (StringUtil.isNotEmpty(query.getBrandName())) {
			sql.append(" and tba.name like ?");
			params.add("%" + query.getBrandName() + "%");
		}
		if (StringUtil.isNotEmpty(query.getVideoUserName())) {
			sql.append(" and tru.username like ?");
			params.add("%" + query.getVideoUserName() + "%");
		}
		if (query.getSubmitTimeBegin() != null) {
			sql.append(" and to_char(tvp.submittime,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					query.getSubmitTimeBegin(), "yyyyMMdd"));
		}
		if (query.getSubmitTimeEnd() != null) {
			sql.append(" and to_char(tvp.submittime,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getSubmitTimeEnd(),
					"yyyyMMdd"));
		}
		formatModeChangeSQL(sql, params, query);

		sql.append(" order by tvp.id desc ");
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(VideoPlanQueryBean.class));
			System.out.println("ExtVideoFindListDealerLedger sql:"+sql.toString());
			System.out.println("ExtVideoFindListDealerLedger params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Map<Integer, Integer> findCountPlan(String dealerIds) {
		StringBuffer sql = new StringBuffer();
		//sql.append(" select tvp.dealerid as dealerId , count(tvp.dealerid) as  planCount  from t_video_plan tvp  where  tvp.dealerid in ( ");
		sql.append("select tvp.dealerid as dealerId ,  row_number() over (partition by tvp.dealerid ORDER BY tvp.dealerid desc) planCount from t_video_plan tvp where  tvp.dealerid  in (");
	    sql.append(dealerIds.replace("[", "").replace("]", ""));
		//sql.append(" )  group by tvp.dealerid ");
		sql.append(" ) ");
		final Map<Integer,Integer> countMap = new HashMap<Integer, Integer>();
		return (Map<Integer, Integer>) getJdbcTemplate().queryForObject(sql.toString(), new RowMapper(){
			public Map<Integer,Integer> mapRow(ResultSet rs, int arg1) throws SQLException {
				countMap.put(rs.getInt("dealerId"), rs.getInt("planCount"));
				System.out.println(countMap.toString());
				return countMap;
			}
		});
	}
	
}
