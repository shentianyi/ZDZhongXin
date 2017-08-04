package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisory.dao.ICheckStockDAO;
import com.zd.csms.supervisory.model.CheckStockQueryVO;
import com.zd.csms.supervisory.querybean.CheckStockDealerQueryBean;
import com.zd.csms.supervisory.querybean.CheckStockQueryBean;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class CheckStockDaoImpl extends DAOSupport implements ICheckStockDAO{

	public CheckStockDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockQueryBean> findList(CheckStockQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(
						"select t.*, " +
						"       ru.username, " + 
						"       (select wm_concat(md.dealername) " + 
						"          from t_check_stock_dealer csd " + 
						"          left join market_dealer md " + 
						"            on md.id = csd.dealerid " + 
						"         where csd.checkstockid = t.id) as \"dealerName\" " + 
						"  from t_check_stock t " + 
						"  left join t_rbac_user ru " + 
						"    on t.createuserid = ru.id ");
		List<Object> params = new ArrayList<Object>();
		List<CheckStockQueryBean> list = null;
		formatSQL(sql, params, query);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CheckStockQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void formatSQL(StringBuffer sql, List<Object> params, CheckStockQueryVO query) {
		int currRole = query.getCurrRole();
		int pageType = query.getPageType();
		int userId = query.getUser().getId();
		sql.append(" where 1=1 ");
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.SUPERVISORY.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) and t.createUserid = ? ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(userId);
			}else{
				sql.append(" and t.nextApproval = ?  and t.approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
		}else if(pageType==2){//已审批
			if(currRole==RoleConstants.SUPERVISORY.getCode()){//如果角色是发起者
				sql.append(" and (t.approvalState=? or t.approvalState=?) and t.createUserid = ? ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				params.add(userId);
			}else {
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ "from market_approval ma"
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.CHECKSTOCK.getCode());
				params.add(currRole);
			}

		}
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and t.id in ("+
						"select distinct t.checkstockid " +
						"  from t_check_stock_dealer t " + 
						"  left join market_dealer md " + 
						"    on md.id = t.dealerid " + 
						" where md.dealername like ? )");
			params.add("%"+query.getDealerName()+"%");
		}
	}
	
	/**
	 * 查询查库频次申请列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockDealerQueryBean> findDealerList(CheckStockQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				"select t.*, " +
						"       md.dealername, " + 
						"       md.brand, " + 
						"       (select wm_concat(tb.bankfullname) " + 
						"          from market_dealer_bank mdb " + 
						"          left join t_bank tb " + 
						"            on mdb.bankid = tb.id " + 
						"         where mdb.dealerid = md.id) as \"bankName\" " + 
						"  from t_check_stock_dealer t " + 
						"  left join market_dealer md " + 
						"    on t.dealerid = md.id " + 
						" where t.checkstockid = ?");
		params.add(query.getId());
		List<CheckStockDealerQueryBean> list = null;
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CheckStockDealerQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 查询查库频次申请列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<CheckStockDealerQueryBean> findDealerList(CheckStockQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				"select t.*, " +
						"       md.dealername, " + 
						"       md.brand, " + 
						"       (select wm_concat(tb.bankfullname) " + 
						"          from market_dealer_bank mdb " + 
						"          left join t_bank tb " + 
						"            on mdb.bankid = tb.id " + 
						"         where mdb.dealerid = md.id) as \"bankName\" " + 
						"  from t_check_stock_dealer t " + 
						"  left join market_dealer md " + 
						"    on t.dealerid = md.id " + 
						" where t.checkstockid = ?");
		params.add(query.getId());
		List<CheckStockDealerQueryBean> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CheckStockDealerQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
