package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.Financing;
import com.zd.csms.zxbank.bean.FinancingQueryVO;
import com.zd.csms.zxbank.dao.IFinancingDAO;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 融资信息DAO实现
 */
public class FinancingDAO extends DAOSupport implements IFinancingDAO{

	public FinancingDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	// 资源查询语句
	private static String select_financing = "SELECT FIID,FGLONENTNO,LONCPNAME,FGSTDATE,FGENDDATE,FGLOANCODE,FGSCFTXNO,FGLOANAMT,FGBAILRAT,FGSLFCAP,FGFSTBLRAT,FGPROCRT,FGBIZMOD,FGOPERORG,FGCREATEDATE,FGUPDATEDATE FROM ZX_FINANCING WHERE 1=1";

	@SuppressWarnings("unchecked")
	@Override
	public List<Financing> findByQuery(FinancingQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(FinancingDAO.select_financing);
		List<Object> params = new ArrayList<Object>();
		List<Financing> list = null;
		formatSQL(sql, params,query);
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
	private void formatSQL(StringBuffer sql,List<Object> params,FinancingQueryVO query){
		if(query.getFgLonentNo()!=null&&!query.getFgLonentNo().equals("")){
			params.add("%"+query.getFgLonentNo().trim()+"%");
			sql.append(" AND FGLONENTNO like ?");
		}
		if(query.getFgStDateStart()!=null&&!query.getFgStDateStart().equals("")){
			params.add(query.getFgStDateStart());
			sql.append(" AND FGSTDATE>=?");
		}
		if(query.getFgStDateEnd()!=null&&!query.getFgStDateEnd().equals("")){
			params.add(query.getFgStDateEnd());
			sql.append(" AND FGSTDATE<=?");
		}
	}

	@Override
	public int getCountByOther(String code) {
		String sql = "SELCET COUNT(*) FROM ZX_FINANCING WHERE FGLOANCODE = "+code;
		return getJdbcTemplate().queryForInt(sql);
	}

	@Override
	public boolean update(Financing financing) {
		/*StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ZX_FINANCING SET ");
		super.update(financing);*/
		System.out.println("融资修改");
		return true;
	}

	@Override
	public boolean add(Financing financing) {
		System.out.println("融资添加");
		return super.add(financing);
	}
}
