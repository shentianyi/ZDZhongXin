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
import com.zd.csms.business.dao.IFlowDAO;
import com.zd.csms.business.model.FlowQueryVO;
import com.zd.csms.business.model.FlowVO;
import com.zd.csms.util.DateUtil;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class FlowOracleDAO extends DAOSupport implements IFlowDAO {

	public FlowOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@Override
	public boolean add(Object obj) {
		final FlowVO flow = (FlowVO) obj;

		return (Boolean) getJdbcTemplate().execute(new ConnectionCallback() {

			@Override
			public Boolean doInConnection(Connection con) throws SQLException, DataAccessException {
				int i = 0;
				boolean commit = con.getAutoCommit();
				con.setAutoCommit(false);
				try {
					String sql = "insert into t_flow(id,flowName,uploadid,createTime,content) values (?,?,?,?,EMPTY_CLOB())";
					getJdbcTemplate().update(sql, new Object[] { flow.getId(), flow.getFlowname(), flow.getUploadid(),
							flow.getCreatetime() });
					ResultSet rs = con.createStatement()
							.executeQuery("select content from t_flow t where t.id=" + flow.getId() + " for update");
					if (rs.next()) {
						CLOB c = (CLOB) rs.getClob("content");
						c.putChars(1, flow.getContent().toCharArray());
						sql = "update t_flow set content = ? where id=" + flow.getId();
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setClob(1, c);
						i = pstmt.executeUpdate();
						pstmt.close();
					}
					con.commit();
					con.setAutoCommit(commit);
				} catch (Exception e) {
					con.rollback();
					e.printStackTrace();
				}
				return i > 0;
			}
		});
	}

	@Override
	public boolean update(Object obj) {
		final FlowVO flow = (FlowVO) obj;

		return (Boolean) getJdbcTemplate().execute(new ConnectionCallback() {

			@Override
			public Boolean doInConnection(Connection con) throws SQLException, DataAccessException {
				int i = 0;
				boolean commit = con.getAutoCommit();
				con.setAutoCommit(false);
				try {
					ResultSet rs = con.createStatement()
							.executeQuery("select content from t_flow t where t.id=" + flow.getId() + " for update");
					if (rs.next()) {
						CLOB c = (CLOB) rs.getClob("content");
						c.putChars(1, flow.getContent().toCharArray());
						String sql = "update t_flow set flowname=?,content = ? where id=" + flow.getId();
						PreparedStatement pstmt = con.prepareStatement(sql);
						pstmt.setString(1, flow.getFlowname());
						pstmt.setClob(2, c);
						i = pstmt.executeUpdate();
						pstmt.close();
					}
					con.commit();
					con.setAutoCommit(commit);
				} catch (Exception e) {
					con.rollback();
				}
				return i > 0;
			}
		});
	}

	// 资源查询语句
	private static String select_flow = "select id,flowname,uploadid,createTime from t_flow";

	private boolean formatFlowWhereSQL(FlowQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		// 当参数不为空时说明sql中已拼入查询条件
		if (!params.isEmpty()) {
			queryFlag = true;
		}
		// 当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if (!StringUtil.isEmpty(vo.getFlowname())) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append("flowname like ?");
			params.add("%" + vo.getFlowname() + "%");
			queryFlag = true;
		}
		if (vo.getCreateDateStart()!=null) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append("to_char(createtime,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(vo.getCreateDateStart(), "yyyyMMdd"));
			queryFlag = true;
		}
		if (vo.getCreateDateEnd()!=null) {
			if (queryFlag) {
				sql.append(" and ");
			} else {
				sql.append(" where ");
			}
			sql.append("to_char(createtime,'yyyymmdd') <=?");
			params.add(DateUtil.getStringFormatByDate(vo.getCreateDateEnd(), "yyyyMMdd"));
			queryFlag = true;
		}

		return !queryFlag;
	}

	@Override
	public List<FlowVO> searchFlowList(FlowQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(FlowOracleDAO.select_flow);
		formatFlowWhereSQL(query, sql, params);

		List<FlowVO> result;
		try {
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(),  new BeanPropertyRowMapper(FlowVO.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<FlowVO> searchFlowListByPage(FlowQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(FlowOracleDAO.select_flow);
		formatFlowWhereSQL(query, sql, params);
		sql.append(" order by createtime desc ");
		List<FlowVO> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(FlowVO.class));
		} catch (Exception e) {
			// 失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
