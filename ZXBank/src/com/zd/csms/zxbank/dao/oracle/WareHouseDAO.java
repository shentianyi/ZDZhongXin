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
import com.zd.csms.zxbank.web.bean.WarehouseFar;
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
		sql.append("SELECT LONCPNAME,LONENTID,WHID,CUSTNO,WHNAME,WHCODE,WHLEVEL,WHOPERORG,WHADDRESS,PHONE,CREATEDATE,UPDATEDATE FROM ZX_WAREHOUSE");
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
	}

	@Override
	public boolean add(Warehouse ws) {
		//		String sql="insert into ZX_WAREHOUSE(zxWhid,zxCustno,ZXLONCPNAME,zxWhname,zxWhcode,zxWhlevel,zxWhoperorg,zxWhaddress,zxPhone,zxCreatedate,zxUpdatedate) values(zx_warehouseId.nextval,?,?,?,?,?,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI:SS'),to_date(?,'YYYY-MM-DD HH24:MI:SS'))";
		//		System.out.println(ws);
		//		getJdbcTemplate().add(sql,new Object[]{ws.getCustNo(),ws.getLoncpname(),ws.getWhName(),ws.getWhCode(),ws.getWhLevel(),ws.getWhOperorg(),ws.getWhAddress(),ws.getPhone(),ws.getCreateDate(),ws.getUpdateDate()});
		return super.add(ws);
	}

	@Override
	public boolean upadat(Warehouse ware) {

		String sql = "UPDATE ZX_WAREHOUSE SET CUSTNO=?,WHNAME=?,WHLEVEL=?,WHOPERORG=?,LONCPNAME=?,WHADDRESS=?,PHONE=?,UPDATEDATE=TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),LONENTID=? WHERE WHCODE=?";
		PreparedStatement stmt = null;
		try {
			stmt = BeanManager.getDataSource(null).getConnection().prepareStatement(sql);
			stmt.setString(1, ware.getCustNo());
			stmt.setString(2, ware.getWhName());
			stmt.setString(3, ware.getWhLevel());
			stmt.setString(4, ware.getWhOperorg());
			stmt.setString(5, ware.getLoncpname());
			stmt.setString(6, ware.getWhAddress());
			stmt.setString(7, ware.getPhone());
			stmt.setString(8, DateUtil.getStringFormatByDate(ware.getUpdateDate(), "yyyy-MM-dd HH:mm:ss"));
			stmt.setString(9, ware.getWhCode());
			stmt.setString(10, ware.getLonentid());
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
}
