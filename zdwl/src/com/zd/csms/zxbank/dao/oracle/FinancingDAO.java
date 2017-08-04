package com.zd.csms.zxbank.dao.oracle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.BeanManager;
import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Financing;
import com.zd.csms.zxbank.bean.FinancingQueryVO;
import com.zd.csms.zxbank.dao.IFinancingDAO;
import com.zd.csms.zxbank.util.DateUtil;
import com.zd.csms.zxbank.util.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 融资信息DAO实现
 */
public class FinancingDAO extends DAOSupport implements IFinancingDAO {

	public FinancingDAO(String dataSourceName) {
		super(dataSourceName);
	}

	// 资源查询语句
	private static String select_financing = "SELECT FIID,FGLONENTNO,FGLONCPNAME,FGSTDATE,FGENDDATE,FGLOANCODE,FGSCFTXNO,FGLOANAMT,FGBAILRAT,FGSLFCAP,FGFSTBLRAT,FGPROCRT,FGBIZMOD,FGOPERORG,FGAGTID,FGCREATEDATE,FGUPDATEDATE FROM ZX_FINANCING WHERE 1=1";

	@SuppressWarnings("unchecked")
	@Override
	public List<Financing> findByQuery(FinancingQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(FinancingDAO.select_financing);
		List<Object> params = new ArrayList<Object>();
		List<Financing> list = null;
		formatSQL(sql, params, query);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(Financing.class));
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
	private void formatSQL(StringBuffer sql, List<Object> params, FinancingQueryVO query) {
		if (query.getFgLonentNo() != null && !query.getFgLonentNo().equals("")) {
			params.add("%" + query.getFgLonentNo().trim() + "%");
			sql.append(" AND FGLONENTNO like ?");
		}
		if (query.getFgStDateStart() != null && !query.getFgStDateStart().equals("")) {
			params.add(query.getFgStDateStart());
			sql.append(" AND FGSTDATE>=?");
		}
		if (query.getFgStDateEnd() != null && !query.getFgStDateEnd().equals("")) {
			params.add(query.getFgStDateEnd());
			sql.append(" AND FGSTDATE<=?");
		}
		sql.append(" order by fgupdatedate desc nulls last");
	}

	@Override
	public int getCountByOther(String code) {
		String sql = "SELECT COUNT(*) FROM ZX_FINANCING WHERE FGLOANCODE = '" + code + "'";
		return getJdbcTemplate().queryForInt(sql);
	}

	@Override
	public boolean update(Financing fing) {
		String sql = "update ZX_FINANCING set fgLonentNo=?,fgLoncpName=?,fgStDate=?,fgEndDate=?,fgScftxNo=?,fgLoanAmt=?,fgBailRat=?,fgSlfcap=?,fgFstblRat=?,fgProcrt=?,fgBizMod=?,fgOperOrg=?,fgagtid=?,fgUpdateDate=to_date(?,'YYYY-MM-DD HH24:MI:SS') where fgLoanCode=?";
		PreparedStatement stmt = null;
		try {
			stmt = BeanManager.getDataSource(null).getConnection().prepareStatement(sql);
			stmt.setString(1, fing.getFgLonentNo());
			stmt.setString(2, fing.getFgLoncpName());
			stmt.setString(3, fing.getFgStDate());
			stmt.setString(4, fing.getFgEndDate());
			stmt.setString(5, fing.getFgScftxNo());
			stmt.setString(6, fing.getFgLoanAmt());
			stmt.setString(7, fing.getFgBailRat());
			stmt.setString(8, fing.getFgSlfcap());
			stmt.setString(9, fing.getFgFstblRat());
			stmt.setString(10, fing.getFgProcrt());
			stmt.setString(11, fing.getFgBizMod());
			stmt.setString(12, fing.getFgOperOrg());
			stmt.setString(13, fing.getFgagtid());
			stmt.setString(14, DateUtil.getStringFormatByDate(fing.getFgUpdateDate(), "yyyy-MM-dd HH:mm:ss"));
			stmt.setString(15, fing.getFgLoanCode());
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
	public boolean add(Financing financing) {
		financing.setFiId(SqlUtil.getID(Financing.class));
		return super.add(financing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Financing getFinancing(String code) {
		String sql = "SELECT * FROM ZX_FINANCING WHERE FGLOANCODE = '" + code + "'";
		List<Financing> list = null;
		list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Financing.class));
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<Financing> getFinancingList() {
		StringBuffer sql = new StringBuffer();
		sql.append(FinancingDAO.select_financing);
		sql.append(" order by fgupdatedate desc nulls last");
		List<Financing> list=this.getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(Financing.class));
		return list;
	}

}
