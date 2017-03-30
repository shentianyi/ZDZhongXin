package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.model.WarHouseQueryVO;
import com.zd.csms.zxbank.bean.Warehouse;
import com.zd.csms.zxbank.dao.IWareHouseDAO;
import com.zd.tools.thumbPage.IThumbPageTools;

public class WareHouseDao extends DAOSupport implements IWareHouseDAO{

	public WareHouseDao(String dataSourceName) {
		super(dataSourceName);
	}

	/*@Override
	public List<Warehouse> findAllList() {
		System.out.println("warehouse___sql");
		String sql="select zx_whid as Whid,zx_custno as custNo,zx_whname as whName,zx_whcode as whCode,zx_whlevel as whLevel,zx_whoperorg as whOperorg,zx_whaddress as whAddress,zx_phone as phone,zx_createdate as createDate,zx_updatedate as updateDate from ZX_WAREHOUSE";
		@SuppressWarnings("unchecked")
		List<Warehouse> list=getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Warehouse.class));
		System.out.println("warehouse___sql成功");
		for (Warehouse warehouse : list) {
			System.out.println(warehouse.toString());
		}
		return list;
	}
	@Override
	public List<Warehouse> getWareHouse(String custNo) {
		// TODO Auto-generated method stub
		System.out.println("warehouse___sql");
		String sql=null;
		List<Warehouse> list=null;
		if(custNo!=null&&!custNo.equals("")){
			 sql="select zx_whid as Whid,zx_custno as custNo,zx_whname as whName,zx_whcode as whCode,zx_whlevel as whLevel,zx_whoperorg as whOperorg,zx_whaddress as whAddress,zx_phone as phone,zx_createdate as createDate,zx_updatedate as updateDate from ZX_WAREHOUSE where zx_custno=?";
			 list=getJdbcTemplate().query(sql, new Object[]{custNo},new BeanPropertyRowMapper(Warehouse.class));
		}else{
			 sql="select zx_whid as Whid,zx_custno as custNo,zx_whname as whName,zx_whcode as whCode,zx_whlevel as whLevel,zx_whoperorg as whOperorg,zx_whaddress as whAddress,zx_phone as phone,zx_createdate as createDate,zx_updatedate as updateDate from ZX_WAREHOUSE";
			 list=getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Warehouse.class));
		}
		System.out.println("warehouse___sql");
		for (Warehouse warehouse : list) {
			System.out.println(warehouse.toString());
		}
		return list;
	}*/
	
	@Override
	public List<Warehouse> findBusinessList(WarHouseQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select zx_whid as Whid,zx_custno as custNo,zx_whname as whName,zx_whcode as whCode,zx_whlevel as whLevel,zx_whoperorg as whOperorg,zx_whaddress as whAddress,zx_phone as phone,zx_createdate as createDate,zx_updatedate as updateDate from ZX_WAREHOUSE");
		List<Object> params = new ArrayList<Object>();
		List<Warehouse> list = null;
		formatSQL(sql, params,query);
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
	private void formatSQL(StringBuffer sql,List<Object> params,WarHouseQueryVO query){
		sql.append(" where 1=1");
		if(query.getCustNo()!=null&&!query.getCustNo().equals("")){
			params.add(query.getCustNo());
			sql.append(" and zx_custno=?");
		}
	}
}
