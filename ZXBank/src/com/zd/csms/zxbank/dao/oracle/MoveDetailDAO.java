package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.*;
import com.zd.csms.zxbank.dao.*;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 移库通知详情DAO实现层
 * @author duyong
 *
 */
public class MoveDetailDAO  extends DAOSupport implements IMoveDetailDAO{

	public MoveDetailDAO(String dataSourceName) {
		super(dataSourceName);
	}

	// 资源查询语句
	private static String select_movedetail = "SELECT MDID,MDNO,MDREMOVEOUTNO,MDREMOVEINNO,MDWARENO,MDMOVENUMBER,MDCHASSISNO,MDCERTIFICATIONNO,MDCARPRICE FROM ZX_MOVEDETAIL WHERE 1=1";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MoveDetail> findByQuery(MoveDetail query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(MoveDetailDAO.select_movedetail);
		List<Object> params = new ArrayList<Object>();
		List<MoveDetail> list = null;
		formatSQL(sql, params,query);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(MoveDetail.class));
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
	private void formatSQL(StringBuffer sql,List<Object> params,MoveDetail query){
		if(query.getMdNo()!=null&&!query.getMdNo().equals("")){
			params.add(query.getMdNo());
			sql.append(" AND MDNO = ?");
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MoveDetail> findAll(String no) {
		StringBuffer sql = new StringBuffer();
		sql.append(MoveDetailDAO.select_movedetail);
		sql.append(" AND MDNO = "+no);
		List<MoveDetail> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(MoveDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
