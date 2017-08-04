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
public class NoticeDAO extends DAOSupport implements INoticeDAO {

	public NoticeDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> findNotice(Notice query, IThumbPageTools tools) {
		List<Notice> list = null;
		StringBuffer sql = new StringBuffer("SELECT NTCNO,NTCTP,NTBRANCHID,NTCDATE,NTTOTNUM,NTFAILFLAG FROM ZX_NOTICE");
		List<Object> params = new ArrayList<Object>();
		formatSQL(query, sql, params);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Notice.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Notice> findnoticetype() {
		StringBuffer sql = new StringBuffer("SELECT DISTINCT NTCTP FROM ZX_NOTICE");
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(Notice.class));
	}

	public void formatSQL(Notice query, StringBuffer sql, List<Object> params) {

		sql.append(" WHERE 1=1 ");
		if (!StringUtil.isEmpty(query.getNtcno())) {
			sql.append(" AND NTCNO LIKE ? ");
			params.add("%" + query.getNtcno().trim() + "%");
		}
		if (query.getNtctp() != 0) {
			sql.append(" AND NTCTP = ? ");
			params.add(query.getNtctp());
		}
		if(query.getNtfailflag()!=0){
			sql.append(" AND NTFAILFLAG = ?");
			params.add(query.getNtfailflag());
		}
		sql.append(" order by nid desc");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Notice getNotice(Notice notice) {
		String sql = "SELECT NID,NTCNO,NTCTP,NTBRANCHID,NTCDATE,NTTOTNUM,NTFAILFLAG FROM ZX_NOTICE WHERE NTCNO='" + notice.getNtcno() + "'";
		List<Notice> list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Notice.class));
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean update(Notice notice) {
		String sql = "UPDATE ZX_NOTICE SET NTCTP=?,NTBRANCHID=?,NTCDATE=TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'),NTTOTNUM=?,NTFAILFLAG=? WHERE NTCNO=?";
		PreparedStatement stmt = null;
		try {
			stmt = BeanManager.getDataSource(null).getConnection().prepareStatement(sql);
			stmt.setInt(1, notice.getNtctp());
			stmt.setString(2, notice.getNtbranchid());
			stmt.setString(3, DateUtil.getStringFormatByDate(notice.getNtcdate(), "yyyy-MM-dd HH:mm:ss"));
			stmt.setInt(4, notice.getNttotnum());
			stmt.setInt(5, notice.getNtfailflag());
			stmt.setString(6, notice.getNtcno());
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

	@Override
	public List<String> getListNtcno() {
		String sql = "SELECT NTCNO FROM ZX_NOTICE";
		List<String> list=null;
		list=this.getJdbcTemplate().queryForList(sql, String.class);
		return list;
	}

}
