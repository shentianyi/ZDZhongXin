package com.zd.csms.bank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.bank.dao.IBankManagerDao;
import com.zd.csms.bank.model.BankManagerQueryVO;
import com.zd.csms.bank.model.BankManagerVO;
import com.zd.tools.StringUtil;

public class BankManagerDao extends DAOSupport implements IBankManagerDao{

	public BankManagerDao(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 列表查询
	 * @param query
	 * @return
	 */
	public List<BankManagerVO> findList(BankManagerQueryVO query){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select * from t_bank_manager ");
		formatSql(sql, query, params);
		return getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BankManagerVO.class));
	}
	
	public void formatSql(StringBuffer sql,BankManagerQueryVO query,List<Object> params){
		sql.append(" where 1=1 ");
		if(query.getBankId()>0){
			sql.append(" and bankId = ? ");
			params.add(query.getBankId());
		}
		
		if(!StringUtil.isEmpty(query.getManager())){
			sql.append(" and manager like ? ");
			params.add("%"+query.getManager()+"%");
		}
		
		if(!StringUtil.isEmpty(query.getManagerPhone())){
			sql.append(" and managerPhone like ? ");
			params.add("%"+query.getManagerPhone()+"%");
		}
	}
	
}
