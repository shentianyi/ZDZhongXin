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
	private static String select_movedetail = "SELECT MD_ID,MD_NO,MD_REMOVEOUTNO,MD_REMOVEINNO,MD_WARENO,MD_MOVENUMBER,MD_CHASSISNO,MD_CERTIFICATIONNO,MD_CARPRICE FROM ZX_MOVEDETAIL WHERE 1=1";
	
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
			
			String sqls = "insert into ZX_MOVEDETAIL (MD_ID,MD_NO,MD_REMOVEOUTNO,MD_REMOVEINNO,MD_WARENO,MD_MOVENUMBER,MD_CHASSISNO,MD_CERTIFICATIONNO,MD_CARPRICE) values(?,?,?,?,?,?,?,?,?)";
			System.out.println(getJdbcTemplate().add(sqls, new Object[]{"16","10","10","10","10","10","10","10","10"}));
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
			sql.append(" AND MD_NO = ?");
		}	
	}

	@Override
	public List<MoveDetail> findAll(String no) {
		StringBuffer sql = new StringBuffer();
		sql.append(MoveDetailDAO.select_movedetail);
		sql.append(" AND MD_NO = "+no);
		List<MoveDetail> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(MoveDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
