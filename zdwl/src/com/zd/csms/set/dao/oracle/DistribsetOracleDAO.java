package com.zd.csms.set.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.set.dao.IDistribsetDAO;
import com.zd.csms.set.model.DistribsetQueryVO;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.set.model.ExtendDistribsetVO;
import com.zd.tools.SqlUtil;

public class DistribsetOracleDAO extends DAOSupport implements IDistribsetDAO {

	public DistribsetOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	// 资源查询语句
	private static String select_distribset = "select * from t_distribset where 1=1 ";

	private void formatDistribsetWhereSQL(DistribsetQueryVO vo,
			StringBuffer sql, List<Object> params) {
		// 当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if (vo.getDistribid() > 0) {
			sql.append(" and distribid=? ");
			params.add(vo.getDistribid());
		}
		if (vo.getZsCustNo() != null) {
			sql.append(" and zsCustNo=? ");
			params.add(vo.getZsCustNo());
		}
	}

	@Override
	public List<DistribsetVO> searchDistribsetList(DistribsetQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(DistribsetOracleDAO.select_distribset);
		formatDistribsetWhereSQL(query, sql, params);

		List<DistribsetVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),
					params.toArray(),
					new BeanPropertyRowMapper(DistribsetVO.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public int findDistidByCustNo(String custNo) {
		String sql = "select t.distribid from t_distribset t where t.zsCustNo = ?";
		return getJdbcTemplate().queryForInt(sql, new Object[] { custNo });
	}

	@Override
	public DistribsetVO getDistribsetVOByDistribid(int id) {
		return (DistribsetVO) getJdbcTemplate().queryForObject(
				"select * from t_distribset t where t.distribid=?",
				new Object[] { id },
				new BeanPropertyRowMapper(DistribsetVO.class));
	}

	@Override
	public boolean updateContractNoByZsCustNo(String contractNo, String zsCustNo) {
		String sql = " update t_distribset set contractNo = ? where zsCustNo = ? ";
		List<Object> params = new ArrayList<Object>();
		params.add(contractNo);
		params.add(zsCustNo);
		return getJdbcTemplate().update(sql,
				params.toArray(new Object[params.size()])) > 0;
	}

	/**
	 * 验证此经销商是否是对接银行
	 * 
	 * @return
	 */
	public boolean validateDealer(int dealerId) {
		String sql = "select count(1) from t_distribset t where t.distribid=? and t.bankdocktype!=0";
		return getJdbcTemplate().queryForInt(sql, new Object[] { dealerId }) > 0;
	}

	@Override
	public DealerVO getDealerEquipmentProvideAndCredit(int dealerId) {
		String sql = "select * from market_dealer where id=" + dealerId;
		return (DealerVO) getJdbcTemplate().queryForObject(sql,
				new BeanPropertyRowMapper(DealerVO.class));
	}

	@Override
	public boolean updDealerEquipmentProvideAndCredit(DealerVO query) {
		String sql = "update market_dealer set equipmentprovide=?,credit=? where id=?";
		return getJdbcTemplate().update(
				sql,
				new Object[] { query.getEquipmentProvide(), query.getCredit(),
						query.getId() }) > 0;
	}

	@Override
	public List<String> getListZxOrgCode() {
		String sql = "SELECT ZXORGCODE FROM T_DISTRIBSET";
		List<String> list = null;
		list = getJdbcTemplate().queryForList(sql, String.class);
		return list;
	}

	@Override
	public int findDistidByOrg(String orgcode) {
		String sql = "select t.distribid from t_distribset t where t.ZXORGCODE = ?";
		return getJdbcTemplate().queryForInt(sql, new Object[] { orgcode });
	}

}
