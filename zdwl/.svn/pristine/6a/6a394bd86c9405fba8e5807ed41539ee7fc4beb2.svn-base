package com.zd.csms.planandreport.dao.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import com.zd.core.DAOSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.planandreport.dao.IVideoReportDAO;
import com.zd.csms.planandreport.model.ReportBaseInfoBean;
import com.zd.csms.planandreport.model.VideoPlanQueryVO;
import com.zd.csms.planandreport.model.VideoReportQuestionVO;
import com.zd.csms.planandreport.model.VideoReportVO;
import com.zd.csms.planandreport.querybean.VideoPlanQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

//视频检查报告
public class VideoReportDaoImpl extends DAOSupport implements IVideoReportDAO {
	IBankDAO bankDAO = BankDAOFactory.getBankDAO();

	public VideoReportDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 根据经销商查询具体的报告表基本信息
	 * 
	 * @param dealer
	 * @return ReportBaseInfoBean
	 */
	public ReportBaseInfoBean findBaseInfoByDealerId(int dealerId) {
		String sql = "select md.dealername, md.supervisionMode,"
				+ "       tb.bankfullname as \"bankName\", "
				+ "       bra.name as \"brand\", "
				+ "       sbi.name as \"supervisorName\", "
				+ "       rost.staffno as \"staffNo\", "
				+ "       (select count(1) "
				+ "          from t_supervise_import tsi "
				+ "         inner join t_draft td "
				+ "            on td.draft_num = tsi.draft_num "
				+ "         where td.distribid = md.id "
				+ "           and ((tsi.state = 2 and tsi.apply = 0) or tsi.state = 4 or "
				+ "               tsi.state = 5 or tsi.state = 6 or "
				+ "               (tsi.state = 3 and tsi.apply = 1))) as \"stockNum\", "
				+ "		  (select count(1) from t_supervise_import tsi " 
				+ "         inner join t_draft td "
				+ "				on td.draft_num = tsi.draft_num " 
				+ "         where td.distribid = md.id "
				+ "           and tsi.state = 1 and tsi.apply = 0)  as \"wayNum\","
				+ "       (select count(tsi.key_amount) "
				+ "          from t_supervise_import tsi "
				+ "         inner join t_draft td "
				+ "            on td.draft_num = tsi.draft_num "
				+ "         where td.distribid = md.id "
				+ "           and ((tsi.state = 2 and tsi.apply = 0) or tsi.state = 4 or "
				+ "               tsi.state = 5 or tsi.state = 6 or "
				+ "               (tsi.state = 3 and tsi.apply = 1))) as \"keyNum\" "
				+ "  from market_dealer md "
				+ " inner join market_dealer_supervisor mds "
				+ "    on mds.dealerid = md.id " + " inner join t_bank tb "
				+ "    on tb.id = mds.bankid " + " inner join t_brand bra "
				+ "    on bra.id = md.brandid "
				+ " inner join t_repository rep "
				+ "    on rep.id = mds.repositoryid "
				+ " inner join t_supervisor_basic_information sbi "
				+ "    on sbi.id = rep.supervisorid "
//				+ " inner join t_roster rost "
				+ " left join t_roster rost "	
				+ "    on rost.repositoryid = rep.id " + " where md.id = ?";
		System.out.println("findBaseInfoByDealerId sql:"+sql.toString());
		System.out.println("findBaseInfoByDealerId params:"+dealerId);
		return (ReportBaseInfoBean) getJdbcTemplate().queryForObject(sql,
				new Object[] { dealerId },
				new BeanPropertyRowMapper(ReportBaseInfoBean.class));

	}

	@Override
	public boolean updateReport(VideoReportVO report) {
		String sql = "UPDATE T_VIDEO_REPORT SET SKIP_FAG=?, REASON=?,CHECK_NAME=?, CHECK_TIME=?,CHECK_MINUTE = ?,ACTUAL_STOCK = ?,ACTUAL_WAY = ?,EVALUATE = ? WHERE ID=?";
		return getJdbcTemplate().update(
				sql,
				new Object[] { report.getSkip_fag(), report.getReason(),
						report.getCheck_name(), report.getCheck_time(),report.getCheck_minute(),
						report.getActual_stock(),report.getActual_way(),report.getEvaluate(),
						report.getId() }) > 0;
	}

	// 视频检查报告列表
	@SuppressWarnings("unchecked")
	@Override
	public List<VideoPlanQueryBean> findReportList(VideoPlanQueryVO query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select "
				+ " tvp.plancode, tvp.videouserid,"
				+ " tvp.dealerid, tvp.id , tvp.submitReportTime ,"
				+ "  tvp.reportStatus  as editStatus,"
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname "
			    + " from t_video_plan tvp  " 
			    + " inner join t_video_planinfo  tvpinfo  "
			    + "  on tvpinfo.planCode = tvp.plancode  "
				+ " inner join market_dealer md  "
				+ " on md.id = tvp.dealerid  "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid ");
		
		sql.append(" where 1=1 and tvpinfo.status=3");
		List<Object> params = new ArrayList<Object>();
		List<VideoPlanQueryBean> list = null;
		formatModeChangeSQL(sql, params, query);
		String dealername = query.getDealerName();
		if (StringUtil.isNotEmpty(dealername)) {
			sql.append(" and md.dealername LIKE ? ");
			params.add("%" + dealername + "%");
		}

		if (StringUtil.isNotEmpty(query.getPlanCode())) {
			sql.append(" and tvp.plancode= ");
			params.add(query.getPlanCode());
		}

		if (query.getSubmitTimeBegin() != null) {
			sql.append(" and  to_char(tvp.submitReportTime ,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					query.getSubmitTimeBegin(), "yyyyMMdd"));
		}

		if (query.getSubmitTimeEnd() != null) {
			sql.append(" and to_char(tvp.submitReportTime ,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getSubmitTimeEnd(),
					"yyyyMMdd"));
		}

		if (query.getFlag() != null&&query.getFlag() >0) {
			sql.append(" and tvp.reportStatus=2");
		}else{
			sql.append(" and tvp.reportStatus <2");
		}
		sql.append(" order by tvp.plancode desc");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(VideoPlanQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	// 视频检查报告台账
	@SuppressWarnings("unchecked")
	@Override
	public List<VideoPlanQueryBean> findReportLedger(VideoPlanQueryVO query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select "
				+ " tvp.plancode, tvp.videouserid,"
				+ " tvp.dealerid, tvp.id , "
				+ " tru.username  as videoUserName,"
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname "
				+ " from t_video_plan tvp  "
				+ "  left join t_rbac_user tru on tru.id = tvp.videouserid" 
				+ "  inner join t_video_report vr on tvp.id = vr.id"
				+ "  inner join market_dealer md  "
				+ "  on md.id = tvp.dealerid  "
				+ "  inner join market_dealer_supervisor mds  "
				+ "  on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ "  on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ "  on tba.id = md.brandid ");
		
		if(query.getCurrRole()==RoleConstants.BRANDMASTER.getCode()){
			sql.append(" left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
						+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
						+ " where bu.userid= ? ");
			params.add(query.getUserId());
		}else if (query.getCurrRole() == RoleConstants.DEALERMASTER.getCode()||
				query.getCurrRole() == RoleConstants.DEALERMASTERA.getCode()) {
			sql.append(" where md.id in (");
			sql.append(" select  md1.id from  market_dealer md1 ");
			sql.append(" left join t_rbac_dealergroup_dealer tdd on tdd.dealerid = md1.id ");
			sql.append(" left join t_rbac_dealerGroup trd on tdd.groupid = trd.id ");
			sql.append(" left join t_rbac_dealerGroup_user tbu on tdd.groupid = tbu.groupid ");
			sql.append(" where tbu.userid = ? ");
			params.add(query.getUserId());
			sql.append(")");
		}else {
			sql.append(" where 1=1 ");
		}
		List<VideoPlanQueryBean> list = null;
		formatModeChangeSQL(sql, params, query);

		if (StringUtil.isNotEmpty(query.getVideoUserName())) {
			sql.append(" and tru.username like ?");
			params.add("%" + query.getVideoUserName() + "%");
		}
		
		String dealername = query.getDealerName();
		if (StringUtil.isNotEmpty(dealername)) {
			sql.append(" and md.dealername LIKE ? ");
			params.add("%" + dealername + "%");
		}

		if (StringUtil.isNotEmpty(query.getBrandName())) {
			sql.append(" and tba.name LIKE ?");
			params.add("%" + query.getBrandName() + "%");
		}

		if (StringUtil.isNotEmpty(query.getPlanCode())) {
			sql.append(" and tvp.plancode = ?");
			params.add(query.getPlanCode());
		}

		if (query.getSubmitTimeBegin() != null) {
			sql.append(" and  to_char(tvp.submitTime,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					query.getSubmitTimeBegin(), "yyyyMMdd"));
		}

		if (query.getSubmitTimeEnd() != null) {
			sql.append(" and to_char(tvp.submitTime,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getSubmitTimeEnd(),
					"yyyyMMdd"));
		}
		
		sql.append(" and tvp.reportStatus=2");
		if(query.getCurrRole()==RoleConstants.BANK_APPROVE.getCode()){
			sql.append(" and tru.client_id =  "+query.getUserVO().getClient_id());
		}
		sql.append(" and tvp.plancode is not null");
		sql.append(" order by tvp.plancode desc");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(VideoPlanQueryBean.class));
			System.out.println(" 视频检查报告台账findReportLedger sql:"+sql.toString());
			System.out.println(" 视频检查报告台账findReportLedger params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 视频检查报告及台账列表 查询条件
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

	@SuppressWarnings("unchecked")
	@Override
	public  Map<Integer,Integer>  findPlanCount(String dealerIds) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select tvp.dealerid as dealerId , count(tvp.dealerid) as  planCount  from t_video_plan tvp  where  tvp.dealerid in ( ");
	    sql.append(dealerIds.replace("[", "").replace("]", ""));
		sql.append(" )  group by tvp.dealerid ");
		final Map<Integer,Integer> countMap = new HashMap<Integer, Integer>();
		return (Map<Integer, Integer>) getJdbcTemplate().queryForObject(sql.toString(), new RowMapper(){
			public Map<Integer,Integer> mapRow(ResultSet rs, int arg1) throws SQLException {
				countMap.put(rs.getInt("dealerId"), rs.getInt("planCount"));
				return countMap;
			}
		});
		
		
	   /* return (List<PlanCountBean>) getJdbcTemplate()
				.query(sql.toString(), new Object[] {},
						new BeanPropertyRowMapper(PlanCountBean.class));*/
	}
	
	@SuppressWarnings("unchecked")
	public List<VideoPlanQueryBean> ExtReportLedger(VideoPlanQueryVO query){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select "
				+ " tvp.plancode, tvp.videouserid,"
				+ " tvp.dealerid, tvp.id , "
				+ " tru.username  as videoUserName,"
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname, "
				+ " (select count(tvp1.dealerid) as  planCount  from t_video_plan tvp1  where  tvp1.dealerid in (select tvp.dealerid from t_video_plan tvp1)) as planCount"
				+ " from t_video_plan tvp  "
				+ "  left join t_rbac_user tru on tru.id = tvp.videouserid"
				+ "  inner join market_dealer md  "
				+ "  on md.id = tvp.dealerid  "
				+ "  inner join market_dealer_supervisor mds  "
				+ "  on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ "  on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ "  on tba.id = md.brandid ");
		
		if(query.getCurrRole()==RoleConstants.BRANDMASTER.getCode()){
			sql.append(" left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
						+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
						+ " where bu.userid= ? ");
			params.add(query.getUserId());
		}else {
			sql.append(" where 1=1 ");
		}
		List<VideoPlanQueryBean> list = null;
		formatModeChangeSQL(sql, params, query);

		if (StringUtil.isNotEmpty(query.getVideoUserName())) {
			sql.append(" and tru.username like ?");
			params.add("%" + query.getVideoUserName() + "%");
		}
		
		String dealername = query.getDealerName();
		if (StringUtil.isNotEmpty(dealername)) {
			sql.append(" and md.dealername LIKE ? ");
			params.add("%" + dealername + "%");
		}

		if (StringUtil.isNotEmpty(query.getBrandName())) {
			sql.append(" and tba.name LIKE ?");
			params.add("%" + query.getBrandName() + "%");
		}

		if (StringUtil.isNotEmpty(query.getPlanCode())) {
			sql.append(" and tvp.plancode = ?");
			params.add(query.getPlanCode());
		}

		if (query.getSubmitTimeBegin() != null) {
			sql.append(" and  to_char(tvp.submitTime,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(
					query.getSubmitTimeBegin(), "yyyyMMdd"));
		}

		if (query.getSubmitTimeEnd() != null) {
			sql.append(" and to_char(tvp.submitTime,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getSubmitTimeEnd(),
					"yyyyMMdd"));
		}
		
		sql.append(" and tvp.reportStatus=2");
		if(query.getCurrRole()==RoleConstants.BANK_APPROVE.getCode()){
			sql.append(" and tru.client_id =  "+query.getUserVO().getClient_id());
		}
		sql.append(" and tvp.plancode is not null");
		sql.append(" order by tvp.plancode desc");
		try {
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(VideoPlanQueryBean.class));
			System.out.println(" ExtReportLedger sql:"+sql.toString());
			System.out.println(" ExtReportLedger params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean addVideoReportQuestionVO(List<VideoReportQuestionVO> vrqList) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_video_report_question(id,type,question_classify,question_desc,depart)");
		sql.append(" values(?,?,?,?,?)");
		List<Object> params = new ArrayList<Object>();
		boolean flag = true;
		for (VideoReportQuestionVO vo : vrqList) {
			params.clear();
			params.add(vo.getId());
			params.add(vo.getType());
			params.add(vo.getQuestion_classify());
			params.add(vo.getQuestion_desc());
			params.add(vo.getDepart());
			System.out.println("addVideoReportQuestionVO sql:" +sql.toString());
			System.out.println("addVideoReportQuestionVO params:" +params);
			
			int count = this.getJdbcTemplate().update(sql.toString(), params.toArray());
			
			if(count <= 0){
				flag = false;
			}
		
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VideoReportQuestionVO> findVideoReportQuestionVOByVrid(int vr_id) {
		String sql = "select * from t_video_report_question where id = ? order by type";
		return this.getJdbcTemplate().query(sql, new Object[]{vr_id}, new BeanPropertyRowMapper(VideoReportQuestionVO.class));
	}

	@Override
	public int findReportQyestuibCountById(int id) {
		String sql = "select count(1) from t_video_report_question where id = ?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[]{id});
	}
	
	
	

}