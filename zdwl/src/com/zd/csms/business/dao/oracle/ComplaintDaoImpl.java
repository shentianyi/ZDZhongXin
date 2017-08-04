package com.zd.csms.business.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.business.dao.IComplaintDAO;
import com.zd.csms.business.model.ComplaintInfoQueryBean;
import com.zd.csms.business.model.ComplaintInfoQueryVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.constants.StatusConstants;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ComplaintDaoImpl extends DAOSupport implements IComplaintDAO {

	public ComplaintDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	public List<ComplaintInfoQueryBean> findList(ComplaintInfoQueryVO query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();

		sql.append("select t.*, " + "       (decode(t.Complaintobjid, "
				+ "               1, " + "               md.dealername, "
				+ "               2, " + "               tb.bankfullname, "
				+ "               3, " + "               tbi.name, "
				+ "               '')) as \"objName\" "
				+ "  from t_complaintinfo t " + "  left join market_dealer md "
				+ "    on md.id = t.dealerid " + "  left join t_bank tb "
				+ "    on tb.id = t.financeid "
				+ "  left join t_repository rep "
				+ "    on rep.id = t.rosterid "
				+ "  left join t_supervisor_basic_information tbi "
				+ "    on tbi.id = rep.supervisorid");
		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<ComplaintInfoQueryBean> list = null;
		formatModeChangeSQL(sql, params, query, 1);

		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(ComplaintInfoQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 台账投诉记录分页查询
	 */
	public List<ComplaintInfoQueryBean> findListLedger(ComplaintInfoQueryVO query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();

		sql.append("select t.*, " + "       (decode(t.Complaintobjid, "
				+ "               1, " + "               md.dealername, "
				+ "               2, " + "               tb.bankfullname, "
				+ "               3, " + "               tbi.name, "
				+ "               '')) as \"objName\" "
				+ "  from t_complaintinfo t " + "  left join market_dealer md "
				+ "    on md.id = t.dealerid " + "  left join t_bank tb "
				+ "    on tb.id = t.financeid "
				+ "  left join t_repository rep "
				+ "    on rep.id = t.rosterid "
				+ "  left join t_supervisor_basic_information tbi "
				+ "    on tbi.id = rep.supervisorid");
		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<ComplaintInfoQueryBean> list = null;
		formatModeChangeSQL(sql, params, query, 2);

		try {
			list = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(ComplaintInfoQueryBean.class));
			System.out.println("投诉记录信息汇总表findListLedger sql:"+sql.toString());
			System.out.println("投诉记录信息汇总表findListLedger params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 监管模式变更单 查询条件
	 * 
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatModeChangeSQL(StringBuffer sql, List<Object> params,
			ComplaintInfoQueryVO query, int flag) {

		int currRole = query.getCurrRole();
		if (flag == 1) {
			if (currRole != RoleConstants.RISKMANAGEMENT_MINISTER.getCode()
					&& currRole != RoleConstants.MARKETMANAGEMENT_MINISTER
					.getCode()
					&& currRole != RoleConstants.OPERATIONMANAGEMENT_MINISTER
					.getCode()
					&& currRole != RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO
					.getCode()) {
				
				sql.append(" and (t.createuserid = ? or (t.processingid = ? and t.status != ? ) ) ");
				params.add(query.getCreateUserId());
				params.add(query.getCreateUserId());
				params.add(StatusConstants.UNCOMMIT.getCode());
			} else {
				sql.append(" and t.status != ? ");
				params.add(StatusConstants.UNCOMMIT.getCode());
			}
		}else{
			//台账投诉记录查询   状态为发送成功的数据  不做角色控制
			sql.append(" and t.status = ? ");
			params.add(StatusConstants.SUCCESS.getCode());
		}

		if (query.getPhoneType() != null && !query.getPhoneType().equals("-1")) {
			sql.append(" and t.phoneType like ? ");
			params.add("%" + query.getPhoneType() + "%");
		}
		if (query.getComplaintObjId() != null && query.getComplaintObjId() > 0) {
			sql.append(" and t.complaintobjid = ?");
			params.add(query.getComplaintObjId());
		}
		if (StringUtil.isNotEmpty(query.getCreateUserName())) {
			sql.append(" and t.createUserName like ?");
			params.add("%" + query.getCreateUserName() + "%");
		}
		if (StringUtil.isNotEmpty(query.getFinanceName())) {
			sql.append(" and tb.bankfullname like ?");
			params.add("%" + query.getFinanceName() + "%");
		}
		if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and md.dealerName like ?");
			params.add("%" + query.getDealerName() + "%");
		}
		if (query.getCreateDate() != null) {
			sql.append(" and to_char(t.createDate,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCreateDate(),
					"yyyyMMdd"));
		}
		if (query.getEndDate() != null) {
			sql.append(" and to_char(t.createDate,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getEndDate(),
					"yyyyMMdd"));
		}
		sql.append(" order by t.id desc ");

	}
	
	@SuppressWarnings("unchecked")
	public List<ComplaintInfoQueryBean> ExtComplaintLedger(ComplaintInfoQueryVO query){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*, " + "       (decode(t.Complaintobjid, "
				+ "               1, " + "               md.dealername, "
				+ "               2, " + "               tb.bankfullname, "
				+ "               3, " + "               tbi.name, "
				+ "               '')) as \"objName\" "
				+ "  from t_complaintinfo t " + "  left join market_dealer md "
				+ "    on md.id = t.dealerid " + "  left join t_bank tb "
				+ "    on tb.id = t.financeid "
				+ "  left join t_repository rep "
				+ "    on rep.id = t.rosterid "
				+ "  left join t_supervisor_basic_information tbi "
				+ "    on tbi.id = rep.supervisorid");
		sql.append(" where 1=1 ");

		List<Object> params = new ArrayList<Object>();
		List<ComplaintInfoQueryBean> list = null;
		formatModeChangeSQL(sql, params, query, 2);

		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(ComplaintInfoQueryBean.class));
			System.out.println("ExtComplaintLedger sql:"+sql.toString());
			System.out.println("ExtComplaintLedger params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
