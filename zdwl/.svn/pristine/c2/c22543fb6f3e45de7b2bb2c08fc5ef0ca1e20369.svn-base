package com.zd.csms.windcontrol.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.zd.core.DAOSupport;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.DateUtil;
import com.zd.csms.windcontrol.dao.IInspectionDAO;
import com.zd.csms.windcontrol.model.InspectionPictureVO;
import com.zd.csms.windcontrol.model.InspectionRecordVO;
import com.zd.csms.windcontrol.querybean.InspectionLedgerQueryBean;
import com.zd.csms.windcontrol.querybean.InspectionListRowMapper;
import com.zd.csms.windcontrol.querybean.InspectionReportRowMapper;
import com.zd.csms.windcontrol.querybean.ReportQuery;
import com.zd.csms.windcontrol.web.form.InspectionLedgerForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 
 *
 * @author zhangzhicheng
 * 
 *         &copy;北京科码先锋软件技术有限公司 2016
 */
public class InspectionOracleDAO extends DAOSupport implements IInspectionDAO {
	IBankDAO bankDAO = BankDAOFactory.getBankDAO();
	public InspectionOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 根据基本信息Id查询对应的图片列表
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InspectionPictureVO> findPictureByInfoId(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.* FROM WINDCONTROL_INSPEC_PICTURE T ");
		sql.append("  WHERE T.INSPECTIONID = ? ");
		sql.append("  ORDER BY T.PICTUREID ASC ");
		return getJdbcTemplate().query(sql.toString(), new Object[] { id },
				new BeanPropertyRowMapper(InspectionPictureVO.class));
	}
	
	/**
	 * 根据用户Id和角色ID判断是否存在关系
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean judgeUserRole(int user_id, int role_id) {
		String sql = " SELECT count(id) FROM t_rbac_user_role where role_id = ? and user_id = ? ";
		List<Object> params = new ArrayList<Object>();
		params.add(role_id);
		params.add(user_id);
		int count =  getJdbcTemplate().queryForInt(sql, params.toArray());
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 根据图片Id删除对应的巡检报告-店面图片记录
	 * 
	 * @param fileId
	 * @return
	 */
	@Override
	public boolean deletePicture(int fileId) {
		String sql = " DELETE FROM WINDCONTROL_INSPEC_PICTURE T WHERE T.PICTUREID=?";
		return getJdbcTemplate().update(sql, new Object[] { fileId }) > 0;

	}
	
	
	
	/**
	 * 查询优缺点
	 * @param infoId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionRecordVO> findRecordbyInfoId(int infoId) {
		String sql = " SELECT T.* FROM WINDCONTROL_INSPEC_RECORD T WHERE T.inspectionId = ? ";
		return (List<InspectionRecordVO>) getJdbcTemplate().query(sql, new Object[] {infoId}, new BeanPropertyRowMapper(InspectionRecordVO.class));
	}
	
	
	/**
	 * 删除检查过程中发现的问题及监管员的优缺点
	 */
	@Override
	public boolean deleteRecord(int inspectionId) {
		String sql = " DELETE FROM WINDCONTROL_INSPEC_RECORD T WHERE T.INSPECTIONID=?";
		return getJdbcTemplate().update(sql, new Object[] { inspectionId }) > 0;

	}
	
	
	

	/**
	 * 内控专员:巡检报告列表
	 * @param query
	 * @param tools
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionListRowMapper> findReportList(ReportQuery query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();

		sql.append("select "
				+ " tip.plancode, tip.outcontroluserid,tip.predictcost,tip.predictBeginDate,tip.predictCheckdays, "
				+ " tip.remark,tip.dealerid, tip.id, tus.userName as outControlUserName , "
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname "
				+ " from t_inspection_plan tip  "
				+ " left join t_rbac_user tus  on tus.id=tip.outcontroluserid "
				+ " inner join market_dealer md  "
				+ " on md.id = tip.dealerid  "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid ");

		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<InspectionListRowMapper> list = null;

		if (null != query.getProvinceId() && query.getProvinceId() > 0) {
			sql.append(" and md.province = ? ");
			params.add(query.getProvinceId());
		}

		if (null != query.getCityId() && query.getCityId() > 0) {
			sql.append(" and md.city = ? ");
			params.add(query.getCityId());
		}

		if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and md.dealername LIKE ? ");
			params.add("%" + query.getDealerName() + "%");
		}

		if (StringUtil.isNotEmpty(query.getPlanCode())) {
			sql.append(" and tip.plancode = ?");
			params.add(query.getPlanCode());
		}
		
		sql.append(" and tip.reportStatus=2 ");
		sql.append(" order by tip.plancode desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(InspectionListRowMapper.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 巡视专员:巡检报告列表
	 * @param query
	 * @param tools
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionListRowMapper> findInspectionList(ReportQuery query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();

		sql.append("select "
				+ "  tip.plancode, tip.outcontroluserid,tip.predictcost,tip.predictBeginDate,tip.predictCheckdays, "
				+ "  tip.remark,tip.dealerid, tip.id, tip.reportStatus as completeProcess , "
				+ "  tus.userName as outControlUserName , " 
				+ "  md.dealername,  "
				+ "  md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname "
				+ " from t_inspection_plan tip  "
				+ " inner join  t_inspection_planinfo tipInfo  "
				+ " on tipInfo.planCode = tip.plancode  "
				+ " left join  t_rbac_user tus  on tus.id=tip.outcontroluserid "
				+ " inner join market_dealer md  "
				+ " on md.id = tip.dealerid  "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid "
			  );

		sql.append(" where 1=1  and tipInfo.status=3 ");

		List<Object> params = new ArrayList<Object>();
		List<InspectionListRowMapper> list = null;
		
		if (query.getClient_id() > 0){
			sql.append(" and tus.id = ? ");
			params.add(query.getClient_id());
		}

		if (null != query.getProvinceId() && query.getProvinceId() > 0) {
			sql.append(" and md.province = ? ");
			params.add(query.getProvinceId());
		}

		if (null != query.getCityId() && query.getCityId() > 0) {
			sql.append(" and md.city = ? ");
			params.add(query.getCityId());
		}

		if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and md.dealername LIKE ? ");
			params.add("%" + query.getDealerName() + "%");
		}

		if (StringUtil.isNotEmpty(query.getPlanCode())) {
			sql.append(" and tip.plancode = ?");
			params.add(query.getPlanCode());
		}
		
		if (query.getFlag() >0) {
			sql.append(" and tip.reportStatus=2");
		 }else{
			sql.append(" and tip.reportStatus <2");
		}
		sql.append(" order by tip.plancode desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(InspectionListRowMapper.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
    /**
     * 巡检报告台账列表
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionLedgerQueryBean> findReportLedgerList(
			InspectionLedgerForm query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select "
				+ " tip.plancode, tip.outcontroluserid , "
				+ " tip.remark,tip.dealerid, tip.id,"
				+"  tus.userName as outControlUserName , "
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname , "
				+ " tinfo.check_type  as  check_type ,  " + "  tinfo.check_time  as  check_time , "
				+ " tinfo.check_period  as  check_period ,  " 
				+ " tresult.danger_level  as  danger_level ,  " + " tresult.dealer_level  as  dealer_level , "
				+"  tresult.supervisor_level  as  supervisor_level "
				+ " from t_inspection_plan tip  "
				+"  left join  t_rbac_user tus  on tus.id=tip.outcontroluserid  "
				+ " inner join  windcontrol_inspec_info   tinfo   on  tinfo.id = tip.id  "
				+ " inner join  windcontrol_inspec_result tresult   on  tresult.id = tip.id  "
				+ " inner join market_dealer md  "
				+ " on md.id = tip.dealerid  "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid "
				
				);
		if(query.getCurrRole()==RoleConstants.BRANDMASTER.getCode()){
			sql.append(" left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
						+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
						+ " where bu.userid= ? ");
			params.add(query.getUserId());
		}else if(query.getCurrRole()==RoleConstants.BANK_APPROVE.getCode()){
			//需求67 -- 银行审批人
			sql.append(" and tb.id=(select client_id from T_RBAC_USER t where id = "+query.getUserId()+") ");
		}else if (query.getCurrRole() == RoleConstants.DEALERMASTER.getCode()||
				query.getCurrRole() == RoleConstants.DEALERMASTERA.getCode()) {
			//经销商集团
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
		
		List<InspectionLedgerQueryBean> list = null;
		formatModeChangeSQL(sql, params, query);
		
		if (query.getDealer_level()>0) {
			sql.append(" and tresult.dealer_level = ? ");
			params.add(query.getDealer_level());
		}
		
		if (query.getSupervisor_level()>0) {
			sql.append(" and tresult.supervisor_level = ? ");
			params.add(query.getSupervisor_level());
		}
		
       if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and md.dealername LIKE ? ");
			params.add("%" + query.getDealerName() + "%");
		}
       
       if (StringUtil.isNotEmpty(query.getBrandName())) {
			sql.append(" and tba.name LIKE ?");
			params.add("%" + query.getBrandName() + "%");
		}

		if (StringUtil.isNotEmpty(query.getPlanCode())) {
			sql.append(" and tip.plancode = ?");
			params.add(query.getPlanCode());
		}
		
		if(query.getCheckTimeBegin()!=null){
			sql.append(" and  to_char(tinfo.check_time,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCheckTimeBegin(), "yyyyMMdd"));
		}
		
		if(query.getCheckTimeEnd()!=null){
			sql.append(" and to_char(tinfo.check_time,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCheckTimeEnd(), "yyyyMMdd"));
		}
		sql.append(" order by tip.plancode desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(InspectionLedgerQueryBean.class));
			System.out.println("findReportLedgerList sql:"+sql.toString());
			System.out.println("findReportLedgerList params"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 巡检报告列表,巡检报告台账列表 查询条件
	 * 
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatModeChangeSQL(StringBuffer sql, List<Object> params,
			InspectionLedgerForm query) {

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

	/**
	    * 更新巡检报告完成状态
	    * @param reportStatus
	    * @param id
	    * @return
	    */
	@Override
	public boolean updateReportStatus(int reportStatus, int id) {
		String sql=" update t_inspection_plan set reportStatus=?  where id = ?  ";
		return getJdbcTemplate().update(sql, new Object[]{reportStatus,id})>0;
	}
	/**
	 * 更新巡检报告完成时间
	 * @param vo
	 * @return
	 */
	@Override
	public boolean updateModifyTime(Date modifyTime, int id) {
		String sql=" update windcontrol_inspec_info set modify_time=?  where id = ?  ";
		return getJdbcTemplate().update(sql, new Object[]{modifyTime,id}) >0;
	}

	@Override
	public InspectionReportRowMapper getInspectionReport(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select " +
				"t.name, " +
				"t.position, " +
				"t.month_sales, " +
				"t.car_count, " +
				"t.content, " +
				"t.info, " +
				"t.company_info, " +
				"t4.name as supervisor_name ,  " +
				"t4.contact_phone, " +
				"t4.emergency_telephone, " +
				"t4.sex , " +
				"t4.education, " +
				"t4.age, " +
				"t4.company_attr, " +
				"t4.registered_address, " +
				"t4.people_attr, " +
				"t4.address as supervisor_address , " +
				"t4.meal, " +
				"t4.computer_skills, " +
				"t4.job_channel, " +
				"t4.train_mode, " +
				"t4.train_man, " +
				"t4.weekend, " +
				"t4.posts_time, " +
				"t4.takeover_time, " +
				"t4.work_experience, " +
				"t4.evaluate, " +
				"t3.danger_level, " +
				"t3.dealer_level, " +
				"t3.supervisor_level, " +
				"t3.remak, " +
				"t2.car_num, " +
				"t2.certificate_num, " +
				"t2.key_num, " +
				"t2.hall_num, " +
				"t2.hall_remark, " +
				"t2.store_num, " +
				"t2.store_remark, " +
				"t2.network_num, " +
				"t2.network_remark, " +
				"t2.arrears, " +
				"t2.arrears_remark, " +
				"t2.sale_num, " +
				"t2.sale_remark, " +
				"t2.priv_sale, " +
				"t2.privsale_remark, " +
				"t2.move_car, " +
				"t2.movecar_remark, " +
				"t2.normal_certificate, " +
				"t2.normal_remark, " +
				"t2.abnormal_certificate, " +
				"t2.abnormal_remark, " +
				"t2.normal_key, " +
				"t2.key_remark, " +
				"t2.abnormal_key, " +
				"t2.abnormalkey_remark, " +
				"t2.manual_accounting, " +
				"t2.manual_remark, " +
				"t2.manual_standard, " +
				"t2.standard_remark, " +
				"t2.check_list, " +
				"t2.check_remark, " +
				"t2.check_continuity, " +
				"t2.continuity_remark, " +
				"t2.authorization, " +
				"t2.auth_remark, " +
				"t2.key_handover, " +
				"t2.handover_remark, " +
				"t2.billing, " +
				"t2.billing_remark, " +
				"t2.guard, " +
				"t2.guard_remark, " +
				"t2.clear_ticket, " +
				"t2.clearticket_remark, " +
				"t2.other, " +
				"t2.other_remark, " +
				"t2.system_state, " +
				"t2.state_remark, " +
				"t2.identification, " +
				"t2.identification_remark, " +
				"t2.office_machine, " +
				"t2.machine_remark, " +
				"t2.office_conditions, " +
				"t2.conditions_remark, " +
				"t1.id, " +
				"t1.dealer_name, " +
				"t1.bank, " +
				"t1.customer_manager, " +
				"t1.brand, " +
				"t1.company_type, " +
				"t1.address, " +
				"t1.cooperate_time, " +
				"t1.oper_mode, " +
				"t1.check_type, " +
				"t1.business_name, " +
				"t1.exist_storage, " +
				"t1.exist_network, " +
				"t1.check_name, " +
				"t1.check_time, " +
				"t1.check_period, " +
				"t1.entourage, " +
				"t1.description, " +
				"t1.other_description, " +
				"t1.modify_time " +
				"from " +
				" windcontrol_inspec_info t1 " +
				"left join  windcontrol_inspec_communion t on t.id = t1.id " +
				"left join  windcontrol_inspec_item t2 on t2.id = t1.id " +
				"left join  windcontrol_inspec_result t3 on t3.id = t1.id " +
				"left join  windcontrol_inspec_supervisor t4 on t4.id = t1.id");
		
		sql.append(" where t1.id=? ");
		return (InspectionReportRowMapper) getJdbcTemplate().queryForObject(sql.toString(),
				new Object[] { id },
				new BeanPropertyRowMapper(InspectionReportRowMapper.class));
	}

	/*
	 * 需求38 -- 导出巡检报告台账
	 * @time 20170518
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InspectionLedgerQueryBean> ExtInspecReportLenger(InspectionLedgerForm query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select "
				+ " tip.plancode, tip.outcontroluserid , "
				+ " tip.remark,tip.dealerid, tip.id,"
				+"  tus.userName as outControlUserName , "
				+ " md.dealername,  "
				+ " md.province as provinceId,  "
				+ " (select tr.name from t_region tr where tr.id = md.province) as provincename,  "
				+ " md.city as cityId,  "
				+ " (select tr.name from t_region tr where tr.id = md.city) as cityname,  "
				+ " tb.id as bankId,  " + " tb.bankfullname,  "
				+ " tba.id as brandId,  " + " tba.name as brandname , "
				+ " tinfo.check_type  as  check_type ,  " + "  tinfo.check_time  as  check_time , "
				+ " tinfo.check_period  as  check_period ,  " 
				+ " tresult.danger_level  as  danger_level ,  " + " tresult.dealer_level  as  dealer_level , "
				+"  tresult.supervisor_level  as  supervisor_level "
				+ " from t_inspection_plan tip  "
				+"  left join  t_rbac_user tus  on tus.id=tip.outcontroluserid  "
				+ " inner join  windcontrol_inspec_info   tinfo   on  tinfo.id = tip.id  "
				+ " inner join  windcontrol_inspec_result tresult   on  tresult.id = tip.id  "
				+ " inner join market_dealer md  "
				+ " on md.id = tip.dealerid  "
				+ " inner join market_dealer_supervisor mds  "
				+ " on md.id = mds.dealerid  " + " inner join t_bank tb  "
				+ " on tb.id = mds.bankid  " + " inner join t_brand tba  "
				+ " on tba.id = md.brandid "
				
				);
		if(query.getCurrRole()==RoleConstants.BRANDMASTER.getCode()){
			sql.append(" left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
						+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
						+ " where bu.userid= ? ");
			params.add(query.getUserId());
		}else if(query.getCurrRole()==RoleConstants.BANK_APPROVE.getCode()){
			sql.append(" and tb.id=(select client_id from T_RBAC_USER t where id = "+query.getUserId()+") ");
		}else {
			sql.append(" where 1=1 ");
		}
		
		List<InspectionLedgerQueryBean> list = null;
		formatModeChangeSQL(sql, params, query);
		if (query.getDanger_level()>0) {
			sql.append(" and tresult.danger_level = ? ");
			params.add(query.getDanger_level());
		}
		
		if (query.getDealer_level()>0) {
			sql.append(" and tresult.dealer_level = ? ");
			params.add(query.getDealer_level());
		}
		
		if (query.getSupervisor_level()>0) {
			sql.append(" and tresult.supervisor_level = ? ");
			params.add(query.getSupervisor_level());
		}
		
       if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and md.dealername LIKE ? ");
			params.add("%" + query.getDealerName() + "%");
		}
       
       if (StringUtil.isNotEmpty(query.getBrandName())) {
			sql.append(" and tba.name LIKE ?");
			params.add("%" + query.getBrandName() + "%");
		}

		if (StringUtil.isNotEmpty(query.getPlanCode())) {
			sql.append(" and tip.plancode = ?");
			params.add(query.getPlanCode());
		}
		
		if(query.getCheckTimeBegin()!=null){
			sql.append(" and  to_char(tinfo.check_time,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCheckTimeBegin(), "yyyyMMdd"));
		}
		
		if(query.getCheckTimeEnd()!=null){
			sql.append(" and to_char(tinfo.check_time,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCheckTimeEnd(), "yyyyMMdd"));
		}
		sql.append(" order by tip.plancode desc ");
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(InspectionLedgerQueryBean.class));
			System.out.println("ExtInspecReportLenger sql:"+sql.toString());
			System.out.println("ExtInspecReportLenger params"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

}
