package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.*;
import com.zd.csms.zxbank.dao.*;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 移库通知DAO实现层
 * @author duyong
 *
 */
public class MoveNoticeDAO  extends DAOSupport implements IMoveNoticeDAO{

	public MoveNoticeDAO(String dataSourceName) {
		super(dataSourceName);
	}

	// 资源查询语句
	private static String select_movenotice = "SELECT MNID,MNNO,MNOPERORG,MNSUPERVISENAME,MNMOVEDATE,MNLONCPNAME,MNNOTICEDATE,MNTOTNUM,MNCREATEDATE,MNUPDATEDATE FROM ZX_MOVENOTICE WHERE 1=1";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MoveNotice> findByQuery(MoveNotice query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(MoveNoticeDAO.select_movenotice);
		List<Object> params = new ArrayList<Object>();
		List<MoveNotice> list = null;
		formatSQL(sql, params,query);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(MoveNotice.class));
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
	private void formatSQL(StringBuffer sql,List<Object> params,MoveNotice query){
		if(query.getMnNo()!=null&&!query.getMnNo().equals("")){
			params.add("%"+query.getMnNo()+"%");
			sql.append(" AND MNNO LIKE ?");
		}	
		if(query.getMnLoncpname()!=null&&!query.getMnLoncpname().equals("")){
			params.add("%"+query.getMnLoncpname()+"%");
			sql.append(" AND MNLONCPNAME LIKE ?");
		}
	}

	@Override
	public MoveNotice fingByNo(String no) {
		MoveNotice mn = null;
		StringBuffer sql = new StringBuffer();
		sql.append(MoveNoticeDAO.select_movenotice);
		sql.append("AND MNNO ="+no);
		mn = (MoveNotice) getJdbcTemplate().query(sql.toString(),new BeanPropertyRowMapper(MoveNotice.class)).get(0);
		return mn;
	}
}
