package com.zd.csms.business.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.CLOB;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ConnectionCallback;

import com.zd.core.DAOSupport;
import com.zd.csms.business.dao.INoteDAO;
import com.zd.csms.business.model.NoteQueryVO;
import com.zd.csms.business.model.NoteVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class NoteOracleDAO extends DAOSupport implements INoteDAO {

	public NoteOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	@Override
	public boolean add(Object obj) {
		final NoteVO note = (NoteVO) obj;
		
		return (Boolean)getJdbcTemplate().execute(new ConnectionCallback() {
			
			@Override
			public Boolean doInConnection(Connection con) throws SQLException, DataAccessException {
				int i=0;
				boolean commit = con.getAutoCommit();
				con.setAutoCommit(false);
				try {
					String sql = "insert into t_note(id,userid,title,content) values (?,?,?,EMPTY_CLOB())";
					getJdbcTemplate().update(sql, new Object[]{note.getId(),note.getUserid(),note.getTitle()});
					ResultSet rs = con.createStatement()
							.executeQuery("select content from t_note t where t.id="+note.getId()+" for update");
					if(rs.next()){
					    CLOB c = (CLOB) rs.getClob("content");
					    c.putChars(1, note.getContent().toCharArray());
					    sql =
						    "update t_note set content = ? where id="+note.getId();
					    PreparedStatement pstmt = con.prepareStatement(sql);
					    pstmt.setClob(1, c);
					    i = pstmt.executeUpdate();
					    pstmt.close();
					}
					con.commit();
					con.setAutoCommit(commit);
				} catch (Exception e) {
					con.rollback();
				}
				return i>0;
			}
		});
		
	}
	
	@Override
	public boolean update(Object obj) {
		final NoteVO note = (NoteVO) obj;
		return (Boolean) getJdbcTemplate().execute(new ConnectionCallback() {
			
			@Override
			public Object doInConnection(Connection con) throws SQLException, DataAccessException {
				int i =0;
				boolean commit = con.getAutoCommit();
				con.setAutoCommit(false);
				try {
					ResultSet rs = con.createStatement()
							.executeQuery("select content from t_note t where t.id="+note.getId()+" for update");
					if(rs.next()){
					    CLOB c = (CLOB) rs.getClob("content");
					    c.putChars(1, note.getContent().toCharArray());
					    String sql =
						    "update t_note set title=?,content = ? where id="+note.getId();
					    PreparedStatement pstmt = con.prepareStatement(sql);
					    pstmt.setString(1,note.getTitle());
					    pstmt.setClob(2, c);
					    i = pstmt.executeUpdate();
					    pstmt.close();
					}
					con.commit();
					con.setAutoCommit(commit);
				} catch (Exception e) {
					con.rollback();
				}
				return i>0;
			}
		});
	}
	
	
	//资源查询语句
	private static String select_note = "select t.id,t.userid,t.title from t_note t";

	private boolean formatNoteWhereSQL(NoteQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getUserid()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("userid=?");
			params.add(vo.getUserid());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getTitle())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("title like ?");
			params.add("%"+vo.getTitle()+"%");
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<NoteVO> searchNoteList(NoteQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(NoteOracleDAO.select_note);
		formatNoteWhereSQL(query,sql,params);
		
		List<NoteVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(NoteVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<NoteVO> searchNoteListByPage(NoteQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(NoteOracleDAO.select_note);
		formatNoteWhereSQL(query,sql,params);
		
		List<NoteVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(NoteVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}


}
