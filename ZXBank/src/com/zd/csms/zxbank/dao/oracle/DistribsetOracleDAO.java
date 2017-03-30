package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.DistribsetZX;
import com.zd.csms.zxbank.dao.IDistribsetDAO;
import com.zd.tools.SqlUtil;

public class DistribsetOracleDAO extends DAOSupport implements IDistribsetDAO {

	public DistribsetOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	// 资源查询语句
	private static String select_distribset = "SELECT ZX_DID,ZX_MOVEPERC,ZX_BANKDOCKTYPE,CONTRACTNO,ORGANIZATIONCODE,ZX_CREATEDATE,ZX_UPDATEDATE,ZX_CREATEUSER FROM ZX_DISTRIBSET WHERE 1=1";

	private void formatDistribsetWhereSQL(DistribsetZX zx, StringBuffer sql,List<Object> params) {
		// 当zx属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if (zx.getZx_did() > 0) {
			sql.append(" and ZX_DID=? ");
			params.add(zx.getZx_did());
		}
		if (zx.getOrganizationcode() != null) {
			sql.append(" and ORGANIZATIONCODE=? ");
			params.add(zx.getOrganizationcode());
		}
	}

	/**
	 * 测试查询所有
	 */
	@Override
	public List<DistribsetZX> findAll() {
		String sql = "select * from ZX_DISTRIBSET";
		@SuppressWarnings("unchecked")
		List<DistribsetZX> list = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(DistribsetZX.class));
		return null;
	}

	/**
	 * 按条件查询经销商
	 */
	@Override
	public List<DistribsetZX> searchDistribsetList(DistribsetZX query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(DistribsetOracleDAO.select_distribset);
		formatDistribsetWhereSQL(query, sql, params);

		List<DistribsetZX> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(),params.toArray(),new BeanPropertyRowMapper(DistribsetZX.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * 自定义更新数据
	 * @param zx
	 * @return
	 */
	@Override
	public boolean update(Object obj) {
		DistribsetZX zx = (DistribsetZX) obj;
		String sql = "UPDATE ZX_DISTRIBSET SET ZX_MOVEPERC=?,ZX_BANKDOCKTYPE=?,CONTRACTNO=?,ORGANIZATIONCODE=?,ZX_UPDATEDATE=? WHERE ZX_DID=?";
		Boolean flag = false;
		flag = this.update(sql, zx.getZx_moveperc(),zx.getZx_bankdocktype(),zx.getContractno(),zx.getOrganizationcode(),zx.getZx_updatedate(),zx.getZx_did());
		return flag;
	}

	@Override
	public List<DistribsetZX> findorg() {
		String sql = "select ORGANIZATIONCODE from ZX_DISTRIBSET";
		@SuppressWarnings("unchecked")
		List<DistribsetZX> list = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(DistribsetZX.class));
		return list;
	}
}
