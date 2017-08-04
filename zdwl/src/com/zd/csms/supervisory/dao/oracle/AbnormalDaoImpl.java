package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisory.dao.IAbnormalDAO;
import com.zd.csms.supervisory.model.AbnormalQueryBean;
import com.zd.csms.supervisory.model.AbnormalQueryVO;
import com.zd.csms.supervisory.model.AbnormalVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 经销商撤店信息流转
 * 
 * @author licheng
 *
 */
public class AbnormalDaoImpl extends DAOSupport implements IAbnormalDAO {

	public AbnormalDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 根据经销商Id查询异常数量
	 * @param id
	 * @return
	 */
	public int findCountByDealerId(int id){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) from T_SUPERVISOR_ABNORMAL t where t.dealerid = ? ");
		return getJdbcTemplate().queryForInt(sql.toString(), new Object[]{id});
	}

	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<AbnormalQueryBean> findList(AbnormalQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select t.*, ru.username as \"businessName\",  md.dealername as \"dealerName\", "
						+ " (select wm_concat(tb.bankfullname)  from market_dealer_bank mdb "
						+ " left join t_bank tb  on mdb.bankid = tb.id "
						+ " where mdb.dealerid = md.id) as \"bankName\",  md.province, "
						+ " md.city  from T_SUPERVISOR_ABNORMAL t  left join t_rbac_user ru "
						+ " on ru.id = t.business  left join market_dealer md  on md.id = t.dealerid" );
		List<Object> params = new ArrayList<Object>();
		List<AbnormalQueryBean> list = null;
		formatSQL(sql, params, query);
		sql.append(" order by t.createDate desc");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(AbnormalQueryBean.class));
			System.out.println("异常数据findList sql："+sql.toString());
			System.out.println("异常数据findList params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 查询条件
	 * 
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatSQL(StringBuffer sql, List<Object> params, AbnormalQueryVO query) {
		int currRole = query.getCurrRole();
		int pageType = query.getPageType();
		if (pageType == 1) {
			if (currRole == RoleConstants.SUPERVISORY.getCode()) {
				sql.append(" where 1=1 ");
				sql.append(" and t.createUser = ? and (t.approvalState=? or t.approvalState = ?  or t.approvalState = ?)");
				params.add(query.getUser().getId());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			} else if (currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()) {
				sql.append(" where 1=1 ");
				sql.append(" and t.business = ? and t.approvalState=?");
				params.add(query.getUser().getId());
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}else if (currRole == RoleConstants.BRANDMASTER.getCode()){
				sql.append(" left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
						+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
						+ " where bu.userid= ? and t.approvalState=? ");
				params.add(query.getUser().getId());
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
		} else if (pageType == 2) {
			if (currRole == RoleConstants.SUPERVISORY.getCode()) {
				sql.append(" where 1=1 ");
				sql.append(" and t.createUser = ? and t.approvalState = ?");
				params.add(query.getUser().getId());
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			} else if (currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode() || currRole == RoleConstants.BUSINESS_AMALDAR.getCode()) {
				sql.append(" where 1=1 ");
				sql.append(" and t.business = ? and t.approvalState=?");
				params.add(query.getUser().getId());
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			}else if (currRole == RoleConstants.BANK_APPROVE.getCode()) {
				sql.append(" where 1=1 ");
				sql.append(" and ru.id = ? and t.approvalState=?");
				params.add(query.getUser().getId());
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			}else if (currRole == RoleConstants.BRANDMASTER.getCode()){
				sql.append(" left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
						+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
						+ " where bu.userid= ? and t.approvalState=? ");
				params.add(query.getUser().getId());
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			}else if (currRole == RoleConstants.RISKMANAGEMENT_MINISTER.getCode() ||
					currRole == RoleConstants.MARKETMANAGEMENT_MINISTER.getCode() ||
					currRole == RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode() ||
					currRole == RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode() ||
					currRole == RoleConstants.VIDEO_AMALDAR.getCode() ||
					currRole == RoleConstants.WINDCONRTOL_AMALDAR.getCode() ||
					currRole == RoleConstants.WINDCONRTOL_INTERNAL.getCode()) {
				sql.append(" where 1=1 ");
				sql.append(" and ru.id = ? and t.approvalState=?");
				params.add(query.getUser().getId());
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			}
		}

		if (StringUtil.isNotEmpty(query.getDealerName())) {
			sql.append(" and md.dealerName like ? ");
			params.add("%" + query.getDealerName().trim() + "%");
		}
		if (StringUtil.isNotEmpty(query.getBusinessName())) {
			sql.append(" and ru.username like ? ");
			params.add("%" + query.getBusinessName().trim() + "%");
		}
		if (StringUtil.isNotEmpty(query.getProvince())) {
			sql.append(" and md.Province in ( select id from t_region where name like ? )");
			params.add("%" + query.getProvince().trim() + "%");
		}
		if (StringUtil.isNotEmpty(query.getCity())) {
			sql.append(" and md.city in ( select id from t_region where name like ? )");
			params.add("%" + query.getCity().trim() + "%");
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AbnormalVO> getAbnormalListByDealerId(int dealerId) {
		String sql=" select * from T_SUPERVISOR_ABNORMAL where dealerId=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(dealerId);
		return getJdbcTemplate().query(sql, params.toArray(), new BeanPropertyRowMapper(AbnormalVO.class));
	}
}
