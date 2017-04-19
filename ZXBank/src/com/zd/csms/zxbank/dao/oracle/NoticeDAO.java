package com.zd.csms.zxbank.dao.oracle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;


import com.zd.core.BeanManager;
import com.zd.core.DAOSupport;

import com.zd.csms.zxbank.bean.Notice;
import com.zd.csms.zxbank.dao.INoticeDAO;
import com.zd.csms.zxbank.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 通知推送DAO实现
 */
public class NoticeDAO extends DAOSupport implements INoticeDAO{

	public NoticeDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> findNotice(Notice query, IThumbPageTools tools) {
		List<Notice> list = null;
		StringBuffer sql = new StringBuffer("select n.ntid,n.nttype,n.ntno,n.ntLonentid,n.ntBranchid,n.ntstdate,n.ntenddate,n.ntfailflag from zx_notice n");
		List<Object> params = new ArrayList<Object>();
		formatSQL(query,sql, params);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Notice.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Notice> findnoticetype() {
		StringBuffer sql = new StringBuffer("select distinct nttype from zx_notice");
		return getJdbcTemplate().query(sql.toString(),new BeanPropertyRowMapper(Notice.class));
	}
	
	public void formatSQL(Notice query,StringBuffer sql,List<Object> params){
		
		sql.append(" where 1=1 ");
		if(!StringUtil.isEmpty(query.getNtNo())){
			sql.append(" and n.ntno like ? ");
			params.add("%"+query.getNtNo().trim()+"%");
		}
		if(query.getNtType()!=0 ){
			sql.append(" and n.nttype = ? ");
			params.add(query.getNtType());
		}
		
	}

	@Override
	public Notice getNotice(Notice notice) {
		String sql="select * from zx_notice where ntno='"+notice.getNtNo()+"'";
		List<Notice> list=getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Notice.class));
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean update(Notice notice) {
		String sql="update ZX_NOTICE set ntId=?,ntType=?,ntLonentid=?,ntBranchid=?,ntFailflag=?,ntStdate=to_date(?,'YYYY-MM-DD HH24:MI:SS'),ntEnddate=to_date(?,'YYYY-MM-DD HH24:MI:SS') where ntNo=?";
		PreparedStatement stmt = null;
		try {
			stmt = BeanManager.getDataSource(null).getConnection().prepareStatement(sql);
			stmt.setString(1, notice.getNtId()+"");
			stmt.setString(2, notice.getNtType()+"");
			stmt.setString(3, notice.getNtLonentid());
			stmt.setString(4, notice.getNtBranchid());
			stmt.setString(5, notice.getNtFailflag()+"");
			stmt.setString(6, DateUtil.getStringFormatByDate(notice.getNtStdate(),"yyyy-MM-dd HH:mm:ss"));
			stmt.setString(7, DateUtil.getStringFormatByDate(notice.getNtEnddate(),"yyyy-MM-dd HH:mm:ss"));
			stmt.setString(8, notice.getNtNo());
			stmt.executeUpdate();
			return true;	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
}
