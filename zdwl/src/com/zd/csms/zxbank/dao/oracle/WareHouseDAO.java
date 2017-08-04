package com.zd.csms.zxbank.dao.oracle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.BeanManager;
import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.dao.IWareHouseDAO;
import com.zd.csms.zxbank.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 仓库信息DAO实现
 */
public class WareHouseDAO extends DAOSupport implements IWareHouseDAO {

	public WareHouseDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> findBusinessList(Warehouse query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT CUSTNO,WHLONENTNM,WHNAME,WHCODE,WHLEVEL,WHOPERORG,WHADDRESS,PHONE,LONENTID,WHDISTANCE,WHCONTACTS,CREATEDATE,UPDATEDATE FROM ZX_WAREHOUSE");
		List<Object> params = new ArrayList<Object>();
		List<Warehouse> list = null;
		formatSQL(sql, params, query);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Warehouse.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * sql语句拼接
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatSQL(StringBuffer sql, List<Object> params, Warehouse query) {
		sql.append(" WHERE 1=1");
		if (!StringUtil.isEmpty(query.getCustNo())) {
			params.add("%" + query.getCustNo() + "%");
			sql.append(" AND CUSTNO LIKE ?");
		}
		if (!StringUtil.isEmpty(query.getWhName())) {
			params.add("%" + query.getWhName() + "%");
			sql.append(" AND WHNAME LIKE ?");
		}
		sql.append(" order by updatedate desc nulls last");
	}

	@Override
	public boolean add(Warehouse ws) {
		return super.add(ws);
	}

	@Override
	public boolean update(Warehouse ware) {
		String sql = "UPDATE ZX_WAREHOUSE SET CUSTNO=?,WHLONENTNM=?,WHNAME=?,WHLEVEL=?,WHOPERORG=?,WHADDRESS=?,PHONE=?,LONENTID=?,WHDISTANCE=?,WHCONTACTS=?,UPDATEDATE=TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') WHERE WHCODE=?";
		PreparedStatement stmt = null;
		try {
			stmt = BeanManager.getDataSource(null).getConnection().prepareStatement(sql);
			stmt.setString(1, ware.getCustNo());
			stmt.setString(2, ware.getWhlonentnm());
			stmt.setString(3, ware.getWhName());
			stmt.setInt(4, ware.getWhLevel());
			stmt.setString(5, ware.getWhOperorg());
			stmt.setString(6, ware.getWhAddress());
			stmt.setString(7, ware.getPhone());
			stmt.setString(8, ware.getLonentid());
			stmt.setString(9, ware.getWhdistance());
			stmt.setString(10, ware.getWhContacts());
			stmt.setString(11, DateUtil.getStringFormatByDate(ware.getUpdateDate(), "yyyy-MM-dd HH:mm:ss"));
			stmt.setString(12, ware.getWhCode());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> query(String custno) {
		String sql = "SELECT WHCODE FROM ZX_WAREHOUSE WHERE CUSTNO='" + custno + "'";
		List<Warehouse> list = null;
		list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Warehouse.class));
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Warehouse getWarehouse(String custNo, String whCode) {

		String sql = "SELECT * FROM ZX_WAREHOUSE WHERE CUSTNO='" + custNo + "' and WHCODE='" + whCode + "'";
		List<Warehouse> list = null;
		list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Warehouse.class));
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Warehouse getWarehouse(String whCode) {
		String sql = "SELECT * FROM ZX_WAREHOUSE WHERE WHCODE='" + whCode + "'";
		List<Warehouse> list = null;
		list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Warehouse.class));
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> findByCustNo(String custNo) {
		String sql = "SELECT * FROM ZX_WAREHOUSE WHERE CUSTNO='" + custNo + "'";
		List<Warehouse> list = null;
		list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Warehouse.class));
		return list;
	}
}
