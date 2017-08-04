package com.zd.csms.business.dao.oracle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.business.dao.IDraftDAO;
import com.zd.csms.business.dao.mapper.DraftMapper;
import com.zd.csms.business.model.DraftQueryVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.queryBean.DraftQueryBean;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.supervisory.querybean.CarInfoQueryBean;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class DraftOracleDAO extends DAOSupport implements IDraftDAO {

	private static Log log = LogFactory.getLog(DraftOracleDAO.class);

	public DraftOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	// 资源查询语句
	private static String select_draft = "select * from t_draft td ";

	private boolean formatDraftWhereSQL(DraftQueryVO vo, StringBuffer sql,
			List<Object> params) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		boolean queryFlag = false;
		// 当参数不为空时说明sql中已拼入查询条件
		if (!params.isEmpty()) {
			queryFlag = true;
		}
		// 当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if (!StringUtil.isEmpty(vo.getAgreement())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.agreement like ?");
			params.add("%" + vo.getAgreement() + "%");
			queryFlag = true;
		}

		//开票日
		if (vo.getBilling_datebeginDate() != null) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.billing_date >= to_date('"+sdf.format(vo.getBilling_datebeginDate())+"','yyyy-mm-dd')");
			queryFlag = true;
		}
		if (vo.getBilling_dateendDate() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(vo.getBilling_dateendDate());
			cal.add(Calendar.DATE, 1);
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.billing_date <= to_date('"+sdf.format(cal.getTime())+"','yyyy-mm-dd')");
			queryFlag = true;
		}
		//到期日
		if (vo.getDue_datebegin() != null) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.due_date >= to_date('"+sdf.format(vo.getDue_datebegin())+"','yyyy-mm-dd')");
			queryFlag = true;
		}
		if (vo.getDue_dateend() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(vo.getDue_dateend());
			cal.add(Calendar.DATE, 1);
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.due_date <= to_date('"+sdf.format(cal.getTime())+"','yyyy-mm-dd')");
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getBail_num())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.bail_num like ?");
			params.add("%" + vo.getBail_num() + "%");
			queryFlag = true;
		}
		if (vo.getDistribid() > 0) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.distribid=?");
			params.add(vo.getDistribid());
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getFinancial_institution())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.financial_institution like ?");
			params.add("%" + vo.getFinancial_institution() + "%");
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getDraft_num())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.draft_num like ?");
			params.add("%" + vo.getDraft_num() + "%");
			queryFlag = true;
		}
		if (vo.getBilling_date() != null) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.billing_date = ?");
			params.add(vo.getBilling_date());
			queryFlag = true;
		}
		if (vo.getDue_date() != null) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.due_date = ?");
			params.add(vo.getDue_date());
			queryFlag = true;
		}
		if (vo.getState() > 0) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.state=?");
			params.add(vo.getState());
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getDistribids())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.distribid in ( ");
			sql.append(vo.getDistribids());
			sql.append(")");
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getIds())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" td.id in ( ");
			sql.append(vo.getIds());
			sql.append(")");
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getDistribname())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" md.dealername like ? ");
			params.add("%" + vo.getDistribname() + "%");
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getBankName())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" tb.bankfullname like ? ");
			params.add("%" + vo.getBankName() + "%");
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getBrand())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" tbr.name like ? ");
			params.add("%" + vo.getBrand() + "%");
			queryFlag = true;
		}

		return !queryFlag;
	}

	@Override
	public List<DraftVO> searchDraftList(DraftQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(DraftOracleDAO.select_draft);
		formatDraftWhereSQL(query, sql, params);
		sql.append(" order by td.id desc ");
		List<DraftVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					params.toArray(), new DraftMapper());
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<DraftQueryBean> findListByNowDate(int type) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select td.*,nvl((select sum(nvl(tsi.money, 0)) "
				+ " from t_supervise_import tsi "
				+ " where tsi.draft_num = td.draft_num"
				+ " and tsi.state != 1"
				+ " and (tsi.state != 2 or (tsi.state = 2 and tsi.apply != 1))),"
				+ " 0) as  yycMoney  from t_draft td where 1=1 ");
		
		if (type == MessageTypeContant.MSGBILLNOCARREMIND.getCode()) {
			sql.append(" and to_char(td.billing_date + 10,'yyyy-MM-dd') = to_char(sysdate,'yyyy-MM-dd') ");
		} else if (type == MessageTypeContant.MSGBILLNODRAFTREMIND.getCode()) {
			sql.append(" and to_char(td.due_date,'yyyy-MM-dd') = to_char(sysdate+7,'yyyy-MM-dd') and td.state = 2 ");
		} else if (type == MessageTypeContant.MSGEXCEPTIONCAREXCEEDSKUFIFTEENREMIND.getCode()) {//开票15个工作日未到车预警
			sql.append(" and to_char(td.billing_date + 15,'yyyy-MM-dd') < to_char(sysdate,'yyyy-MM-dd') ");
		} else if (type == MessageTypeContant.BILLNODRAFTNOWDATEWARING.getCode()) {
			sql.append(" and to_char(td.due_date,'yyyy-MM-dd') >= to_char(sysdate,'yyyy-MM-dd') and td.state = 2 ");
		} 

		sql.append(" order by td.id desc ");
		List<DraftQueryBean> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					params.toArray(),
					new BeanPropertyRowMapper(DraftQueryBean.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<DealerQueryBean> findListDealer(int type) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select md.id as id,a.upddate as upddate from market_dealer md "
				+ " left join t_draft td on td.distribid = md.id "
				+ " left join  "
				+ " (select tsi.draft_num as draft_num,max(tsi.upddate) as upddate from t_supervise_import tsi group by tsi.draft_num having tsi.draft_num is not null) a "
				+ " on a.draft_num = td.draft_num " + " where 1=1 ");
		if (type == MessageTypeContant.MSGTHREEDAYNOBUSINESSREMIND.getCode()) {// 连续三天无业务发生
																				// 查询条件
			sql.append(" and (select count(1) from t_draft td where td.distribid = md.id and td.state=2)!=0 and to_char(a.upddate,'yyyy-MM-dd') > to_char(sysdate + 3,'yyyy-MM-dd') ");
		} else if (type == MessageTypeContant.MSGZEROSKUZERODRAFTREMIND
				.getCode()) {
			sql.append(" and (select count(1) from t_draft td where td.distribid = md.id and td.state=2)=0 ");
		}
		sql.append(" order by md.id desc ");
		List<DealerQueryBean> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					params.toArray(),
					new BeanPropertyRowMapper(DealerQueryBean.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<CarInfoQueryBean> findListCar(int type) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();

		if (type == MessageTypeContant.MSGMOVEEXCEEDTWENTYFIVENOPROCESSREMIND
				.getCode()) {// 移动车辆超过25天未处理提醒
			sql.append(" select t.* " + "from t_supervise_import t "
					+ "where 1=1 ");
			sql.append(" and to_char(t.movetime + 25,'yyyy-MM-dd') = to_char(sysdate,'yyyy-MM-dd') "
					+ "and t.state = 4 " + "and t.apply = 0 ");
		} else if (type == MessageTypeContant.MSGMOVECARTEXCEEDKURTWENTYEMIND
				.getCode()) {// 移动车辆超过总库存20%提醒
			sql.append("select * "
					+ "  from (select md.*, "
					+ "               (select count(1) "
					+ "                  from t_supervise_import tsi "
					+ "                 inner join t_draft td "
					+ "                    on td.draft_num = tsi.draft_num "
					+ "                 where td.distribid = md.id "
					+ "                   and tsi.state = 4 "
					+ "                   and tsi.apply = 0) as yd, "
					+ "               (select count(1) "
					+ "                  from t_supervise_import tsi "
					+ "                 inner join t_draft td "
					+ "                    on td.draft_num = tsi.draft_num "
					+ "                 where td.distribid = md.id "
					+ "                   and tsi.state != 1 "
					+ "                   and (tsi.state != 2 or (tsi.state = 2 and tsi.apply = 1))) as allcar "
					+ "          from market_dealer md) t "
					+ " where t.allcar > 0 " + "   and (t.yd / t.allcar) > 0.2");
		} else if (type == MessageTypeContant.MSGEXCEPTIONCAREXCEEDSKUFIFTEENREMIND
				.getCode()) {// 异常车辆超过总库存15%提醒
			sql.append("select * "
					+ "  from (select md.*, "
					+ "               (select count(1) "
					+ "                  from t_supervise_import tsi "
					+ "                 inner join t_draft td "
					+ "                    on td.draft_num = tsi.draft_num "
					+ "                 where td.distribid = md.id "
					+ "                   and (tsi.state = 5 or tsi.state=6) "
					+ "                   and tsi.apply = 0) as yc, "
					+ "               (select count(1) "
					+ "                  from t_supervise_import tsi "
					+ "                 inner join t_draft td "
					+ "                    on td.draft_num = tsi.draft_num "
					+ "                 where td.distribid = md.id "
					+ "                   and tsi.state != 1 "
					+ "                   and (tsi.state != 2 or (tsi.state = 2 and tsi.apply = 1))) as allcar "
					+ "          from market_dealer md) t "
					+ " where t.allcar > 0 "
					+ "   and (t.yc / t.allcar) > 0.15");
		}
		sql.append(" order by t.id desc ");
		List<CarInfoQueryBean> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					params.toArray(),
					new BeanPropertyRowMapper(CarInfoQueryBean.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<DraftQueryBean> findListByNowDateNoFull() {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select td.* from t_draft td "
				+ " where nvl((select sum(nvl(tsi.money, 0)) from t_supervise_import tsi where tsi.draft_num = td.draft_num "
				+ " and tsi.state != 1 "
				+ " and (tsi.state != 2 or (tsi.state = 2 and tsi.apply != 1))),0) > td.billing_money - 50000 "
				+ " and  "
				+ " nvl((select sum(nvl(tsi.money, 0)) from t_supervise_import tsi where tsi.draft_num = td.draft_num"
				+ " and tsi.state != 1 "
				+ " and (tsi.state != 2 or (tsi.state = 2 and tsi.apply != 1))),0) < td.billing_money "
				+ " and to_char(td.billing_date + 30,'yyyy-MM-dd') = to_char(sysdate,'yyyy-MM-dd') ");
		sql.append(" order by td.id desc ");
		List<DraftQueryBean> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					params.toArray(),
					new BeanPropertyRowMapper(DraftQueryBean.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<DraftQueryBean> searchDraftListByPage(DraftQueryVO query,
			IThumbPageTools tools) {
		int currRole = query.getCurrRole();
		int clientId = query.getCurrUser().getClient_id();
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select td.*, "
				+ "       md.dealerName, "
				+ "       tb.bankFullName as \"bankName\", tbr.name as \"brandName\", "
				+ "       nvl((select sum(nvl(tsi.money, 0)) "
				+ // 已押车金额
				"             from t_supervise_import tsi "
				+ "            where tsi.draft_num = td.draft_num "
				+ "              and tsi.state != 1 "
				+ "              and (tsi.state != 2 or (tsi.state = 2 and tsi.apply != 1))), "
				+ "           0) as \"yycMoney\", "
				+ "       (select count(1) "
				+ // 库存台数
				"          from t_supervise_import tsi "
				+ "         where tsi.draft_num = td.draft_num "
				+ "           and ((tsi.state = 2 and tsi.apply = 0) or tsi.state = 4 or "
				+ "               tsi.state = 5 or tsi.state = 6 or "
				+ "               (tsi.state = 3 and tsi.apply = 1))) as \"kcts\", "
				+ "       nvl((select sum(nvl(tsi.money, 0)) "
				+ // 库存金额
				"             from t_supervise_import tsi "
				+ "            where tsi.draft_num = td.draft_num "
				+ "              and ((tsi.state = 2 and tsi.apply = 0) or tsi.state = 4 or "
				+ "                  tsi.state = 5 or tsi.state = 6 or "
				+ "                  (tsi.state = 3 and tsi.apply = 1))), "
				+ "           0) as\"kcMoney\", "
				+ "       nvl((select sum(nvl(tsi.payment_amount, 0)) "
				+ // 回款金额
				"             from t_supervise_import tsi "
				+ "            where tsi.draft_num = td.draft_num "
				+ "              and tsi.state = 3 "
				+ "              and tsi.apply = 0), "
				+ "           0) as \"hkMoney\" " + "  from t_draft td "
				+ " inner join market_dealer_supervisor mds "
				+ "    on mds.dealerid = td.distribid "
				+ " inner join market_dealer md "
				+ "    on md.id = mds.dealerid " + " inner join t_bank tb "
				+ "    on tb.id = mds.bankid ");
		sql.append(" left join t_brand tbr on tbr.id = md.brandid ");
		if (currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()) {
			sql.append(" inner join t_yw_bank ty on ty.bankid = mds.bankid ");
		}

		if (currRole == RoleConstants.SUPERVISORY.getCode()) {
			sql.append(" where mds.repositoryid=? ");
			params.add(clientId);
		} else if (currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()) {
			sql.append(" where ty.userid = ? ");
			params.add(query.getCurrUser().getId());
		} else if (currRole == RoleConstants.BANK_APPROVE.getCode()) {
			sql.append(" where (mds.bankid=?");
			params.add(clientId);
			sql.append(" or mds.bankid in (select bcm.childrenid from t_bank_children_manager bcm where bcm.parentid = ?))");
			params.add(clientId);
		}else if (currRole == RoleConstants.DEALERMASTER.getCode() || currRole == RoleConstants.DEALERMASTERA.getCode()) {
			sql.append(" where md.id in (");
			sql.append(" select md1.id from market_dealer md1 ");
			sql.append(" left join t_rbac_dealergroup_dealer tdd on tdd.dealerid = md1.id ");
			sql.append(" left join t_rbac_dealerGroup trd on tdd.groupid = trd.id ");
			sql.append(" left join t_rbac_dealerGroup_user tbu on tdd.groupid = tbu.groupid ");
			sql.append(" where tbu.userid = ? ");
			params.add(query.getCurrUser().getId());
			sql.append(")");
		} else {
			// 其他角色可以查看所有的信息
		}
		formatDraftWhereSQL(query, sql, params);
		sql.append(" order by td.id desc ");
		List<DraftQueryBean> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(DraftQueryBean.class));
			System.out.println("searchDraftListByPage sql:"+sql.toString());
			System.out.println("searchDraftListByPage params:"+params);
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	public List<DraftVO> searchDraftListById(int userid, int type) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (type == 1) {// 业务专员
			sql.append(" select td.id from t_draft td "
					+ " inner join market_dealer_supervisor mds on mds.dealerid = td.distribid "
					+ " inner join t_yw_bank ty on ty.bankid = mds.bankid "
					+ " where ty.userid=" + userid);
		} else if (type == 2) {// 监管员
			sql.append(" select td.id from t_draft td "
					+ " inner join market_dealer_supervisor mds on mds.dealerid = td.distribid "
					+ " inner join t_rbac_user tr on tr.client_id = mds.repositoryid "
					+ " where tr.id=" + userid);
		}

		List<DraftVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					params.toArray(), new BeanPropertyRowMapper(DraftVO.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	public List<String> findDistribIds(int userid) {
		return getJdbcTemplate()
				.queryForList(
						"select mds.dealerid from market_dealer_supervisor mds left join t_yw_bank ty on ty.bankid = mds.bankid where ty.userid="
								+ userid, String.class);
	}

	public List<String> findDraftIds() {
		return getJdbcTemplate().queryForList(
				"select t.draft_num from t_draft t ", String.class);
	}

	@Override
	public List<String> findDraftToLncis() {
		String sql = "select td.lnciNo from t_draft_lnci td";
		List<String> result;
		try {
			result = this.getJdbcTemplate().queryForList(sql, String.class);
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	public List<String> searchDraftList() {
		String sql = "select td.draft_num from t_draft td order by td.id desc";
		List<String> result;
		try {
			result = this.getJdbcTemplate().queryForList(sql, String.class);
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	/**
	 * 验证此汇票是重复
	 * 
	 * @param draftNum
	 * @return true代表重复，flase代表不重复
	 */
	public boolean validateDraftIsRepeat(String draftNum) {
		String sql = "select count(1) from t_draft td where td.draft_num=?";
		return getJdbcTemplate().queryForInt(sql, new Object[] { draftNum }) > 0;
	}

	/**
	 * 根据不同的角色查询不同的汇票信息
	 */
	@Override
	public List<String> findDraftNumByRole(UserVO user, int role) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		// 只查询未清票的数据
		sql.append("select t.draft_num from t_draft t ");
		// 角色等于业务专员
		if (role == RoleConstants.BUSINESS_COMMISSIONER.getCode()) {
			sql.append(" inner join market_dealer_supervisor mds on mds.dealerid = t.distribid ");
			sql.append(" inner join t_yw_bank yb on mds.bankid = yb.bankid ");
			sql.append(" where t.state = 2 and yb.userid = ? ");
			params.add(user.getId());
		} else if (role == RoleConstants.BANK_APPROVE.getCode()) {// 角色为银行审批人
			sql.append(" inner join market_dealer_supervisor mds on mds.dealerid = t.distribid ");
			sql.append(" where t.state = 2 and mds.bankid = ? ");
			params.add(user.getClient_id());// 此处传入的是当前用户关联的银行的id
		} else if (role == RoleConstants.SUPERVISORY.getCode()) {// 角色为监管员
			sql.append(" inner join market_dealer_supervisor mds on mds.dealerid = t.distribid ");
			sql.append(" where t.state = 2 and mds.repositoryid = ? ");
			params.add(user.getClient_id());
		} else {// 其他角色直接查询全部汇票信息
			sql.append(" where t.state = 2 ");
		}

		sql.append(" order by t.id desc ");
		List<String> result;
		try {
			result = this.getJdbcTemplate().queryForList(sql.toString(),
					params.toArray(), String.class);
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public DraftVO findDraftByDraftNum(String draftNum) {
		DraftVO vo = null;
		String sql = " select * from t_draft t where t.draft_num = ? ";
		try {
			vo = (DraftVO) getJdbcTemplate().queryForObject(sql,
					new Object[] { draftNum },
					new BeanPropertyRowMapper(DraftVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			vo = null;
		}
		return vo;

	}

}
