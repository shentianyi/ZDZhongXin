package com.zd.csms.business.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.business.dao.ITwoAddressDAO;
import com.zd.csms.business.dao.mapper.TwoAddressMapper;
import com.zd.csms.business.model.ExtTwoAddressVO;
import com.zd.csms.business.model.TwoAddressEVO;
import com.zd.csms.business.model.TwoAddressQueryVO;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class TwoAddressOracleDAO extends DAOSupport implements ITwoAddressDAO {

	public TwoAddressOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	// 资源查询语句
	private static String select_two_address = "select * from t_two_address ta ";

	private boolean formatTwoAddressWhereSQL(TwoAddressQueryVO vo,
			StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		// 当参数不为空时说明sql中已拼入查询条件
		if (!params.isEmpty()) {
			queryFlag = true;
		}
		// 当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if (vo.getDistribid() > 0) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append("ta.distribid=?");
			params.add(vo.getDistribid());
			queryFlag = true;
		}
		if (vo.getType() > 0) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append("ta.type=?");
			params.add(vo.getType());
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getTwo_name())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append("ta.two_name like ?");
			params.add("%" + vo.getTwo_name() + "%");
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getTwo_username())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append("ta.two_username like ?");
			params.add("%" + vo.getTwo_username() + "%");
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getDistribids())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" ta.distribid in( ");
			sql.append(vo.getDistribids());
			sql.append(" ) ");
			queryFlag = true;
		}
		if (!StringUtil.isEmpty(vo.getIds())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append(" ta.id in( ");
			sql.append(vo.getIds());
			sql.append(" ) ");
			queryFlag = true;
		}

		return !queryFlag;
	}

	@Override
	public List<TwoAddressVO> searchTwoAddressList(TwoAddressQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		int currRole = query.getCurrentRole();
		// 监管员
		if (query.getTypes() == 1) {
			sql.append(" select ta.* from t_two_address ta "
					+ " left join market_dealer_supervisor mds on mds.dealerid = ta.distribid "
					+ " where mds.repositoryid=?");
			params.add(query.getUserid());
		} else if (query.getTypes() == 2) {
			sql.append("select ta.* from t_two_address ta "
					+ " left join market_dealer_supervisor mds on mds.dealerid = ta.distribid "
					+ " left join t_yw_bank ty on ty.bankid = mds.bankid "
					+ " where ty.userid=?");
			params.add(query.getUserid());
		} else if (query.getTypes() == 3) {
			sql.append("select ta.* from t_two_address ta "
					+ " left join market_dealer md on md.id = ta.distribid "
					+ " left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
					+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
					+ "  where bu.userid= ? ");
			params.add(query.getUserid());
		} else if (currRole == RoleConstants.BANK_APPROVE.getCode()) {
			// sql.append(TwoAddressOracleDAO.select_two_address);
			// sql.append(" where 1=1 ");
			sql.append("select * from t_two_address ta ");
			sql.append(" left join market_dealer_supervisor mds on ta.distribid = mds.dealerid ");
			sql.append(" left join t_bank tb on tb.id = mds.bankid ");
			sql.append(" where 1=1 and tb.id = ? ");
			params.add(query.getClient_id());
		} else {
			sql.append(TwoAddressOracleDAO.select_two_address);
		}
		formatTwoAddressWhereSQL(query, sql, params);
		sql.append(" order by ta.id desc ");
		List<TwoAddressVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					params.toArray(), new TwoAddressMapper());
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TwoAddressVO> searchTwoAddressListByPage(
			TwoAddressQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		int currRole = query.getCurrentRole();
		if (query.getTypes() == 1) {
			sql.append(" select ta.* from t_two_address ta "
					+ " left join market_dealer_supervisor mds on mds.dealerid = ta.distribid "
					+ " where mds.repositoryid=?");
			params.add(query.getUserid());
		} else if (query.getTypes() == 2) {
			sql.append("select ta.* from t_two_address ta "
					+ " left join market_dealer_supervisor mds on mds.dealerid = ta.distribid "
					+ " left join t_yw_bank ty on ty.bankid = mds.bankid "
					+ " where ty.userid=?");
			params.add(query.getUserid());
		} else if (query.getTypes() == 3) {
			sql.append("select ta.* from t_two_address ta "
					+ " left join market_dealer md on md.id = ta.distribid "
					+ " left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
					+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid"
					+ "  where bu.userid= ? ");
			params.add(query.getUserid());
		} else if (currRole == RoleConstants.BANK_APPROVE.getCode()) {
			// sql.append(TwoAddressOracleDAO.select_two_address);
			// sql.append(" where 1=1 ");
			sql.append("select * from t_two_address ta ");
			sql.append(" left join market_dealer_supervisor mds on ta.distribid = mds.dealerid ");
			sql.append(" left join t_bank tb on tb.id = mds.bankid ");
			sql.append(" where 1=1 and tb.id = ? ");
			params.add(query.getClient_id());
		} else if (currRole == RoleConstants.DEALERMASTER.getCode()
				|| currRole == RoleConstants.DEALERMASTERA.getCode()) {
			sql.append("select * from t_two_address ta ");
			sql.append(" left join market_dealer md on md.id = ta.distribid ");
			sql.append(" left join t_rbac_dealergroup_dealer tdd on tdd.dealerid = ta.distribid ");
			sql.append(" left join t_rbac_dealergroup trd on trd.id = tdd.groupid ");
			sql.append(" left join t_rbac_dealerGroup_user tbu  on tdd.groupid = tbu.groupid ");
			sql.append("  where tbu.userid = ? ");
			params.add(query.getUserid());
		} else {
			sql.append(TwoAddressOracleDAO.select_two_address);
		}
		formatTwoAddressWhereSQL(query, sql, params);
		sql.append(" order by ta.upddate desc ");
		List<TwoAddressVO> result;
		try {
			// result = tools.goPage(sql.toString(), params.toArray(), new
			// TwoAddressMapper());
			result = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(TwoAddressVO.class));
			System.out.println("searchTwoAddressListByPage sql:"
					+ sql.toString());
			System.out.println("searchTwoAddressListByPage params" + params);
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<ExtTwoAddressVO> ExtSupervisionSite(TwoAddressQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		// int currRole = query.getCurrentRole();
		// 监管员
		sql.append("select ta.* ,md.dealername as distribNTT ,tb.name as brandNTT,ba.bankfullname as bankfullnameNTT ");
		sql.append(" from t_two_address ta ");
		if (query.getTypes() == 1) {
			sql.append(" left join market_dealer_supervisor mds on mds.dealerid = ta.distribid ");
			sql.append(" left join market_dealer md on md.id = mds.dealerid ");
			sql.append(" left join t_brand tb on md.brandid = tb.id ");
			sql.append(" left join t_bank ba on ba.id = mds.bankId ");
			sql.append(" where mds.repositoryid=? ");
			params.add(query.getUserid());
		} else if (query.getTypes() == 2) {
			sql.append(" left join market_dealer_supervisor mds on mds.dealerid = ta.distribid "
					+ " left join t_yw_bank ty on ty.bankid = mds.bankid "
					+ " left join market_dealer md on md.id = ta.distribid "
					+ " left join t_brand tb on md.brandid = tb.id "
					+ " left join t_bank ba on ba.id = ty.bankid "
					+ " where ty.userid=?");
			params.add(query.getUserid());
		} else if (query.getTypes() == 3) {
			sql.append(" left join market_dealer md on md.id = ta.distribid "
					+ " left join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId "
					+ " left join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid ");
			sql.append(" left join market_dealer_supervisor mds on mds.dealerid = ta.distribid ");
			sql.append(" left join t_brand tb on md.brandid = tb.id ");
			sql.append(" left join t_bank ba on ba.id = mds.bankId ");
			sql.append(" where bu.userid= ?  ");
			params.add(query.getUserid());
		}
		formatTwoAddressWhereSQL(query, sql, params);
		sql.append(" order by ta.upddate desc ");
		List<ExtTwoAddressVO> result;
		try {
			result = getJdbcTemplate().query(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(ExtTwoAddressVO.class));
			System.out.println("ExtSupervisionSite sql:" + sql.toString());
			System.out.println("ExtSupervisionSite params" + params);
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public TwoAddressVO searchByCode(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_two_address ta where ta.bkwhcode = '"
				+ code + "'");
		List<TwoAddressVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					new TwoAddressMapper());
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			e.printStackTrace();
			result = null;
		}
		if (result.size() == 0) {
			return null;
		}
		return result.get(0);
	}

	@Override
	public TwoAddressEVO searchBydid(int did) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT MD.DEALERNAME AS DEALERNAME,B.NAME AS BRAND,TB.BANKFULLNAME AS BANK FROM MARKET_DEALER MD LEFT JOIN MARKET_DEALER_SUPERVISOR MDS ON MD.ID = MDS.DEALERID LEFT JOIN T_BANK TB ON MDS.BANKID = TB.ID LEFT JOIN T_BRAND B ON B.ID = MD.BRANDID WHERE MD.ID = "
				+ did);
		
		TwoAddressEVO addressE = null;
		try {
			addressE =  (TwoAddressEVO) this.getJdbcTemplate().queryForObject(sql.toString(), new BeanPropertyRowMapper(TwoAddressEVO.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			e.printStackTrace();
			return null;
		}
		return addressE;
	}

}
