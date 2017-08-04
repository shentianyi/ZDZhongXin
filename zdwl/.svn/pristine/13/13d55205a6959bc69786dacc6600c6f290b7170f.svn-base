package com.zd.csms.bank.dao.oracle;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.bank.bean.Gyl001;
import com.zd.csms.bank.bean.Gyl022;
import com.zd.csms.bank.dao.IBankDockDAO;
import com.zd.csms.business.model.DraftToLnciVO;

public class BankDockDao extends DAOSupport implements IBankDockDAO{

	public BankDockDao(String dataSourceName) {
		super(dataSourceName);
	}

	public List<Gyl001> findAllList(){
		String sql="select * from t_zs_cust";
		@SuppressWarnings("unchecked")
		List<Gyl001> list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Gyl001.class));
		return list;
	}
	
	public boolean update(Gyl001 gyl) {
		getJdbcTemplate().update("update t_zs_cust t set t.PLEDGENAME = ?, t.TRADENAME = ?, "
				+ "t.IDTYPE = ?, t.IDNO = ?, t.ADDRESS = ?, t.MONIFLAG = ?, t.CREDITFLAG = ?, t.COREFLAG = ? " +
					" where t.custno = ?", new Object[]{gyl.getPledgeName(),gyl.getTradeName(),gyl.getIdtype(),
							gyl.getIdno(),gyl.getAddress(),gyl.getMoniFlag(),gyl.getCreditFlag(),gyl.getCoreFlag(),
							gyl.getCustNo()});
		return true;
	}
	
	public String getcustName(String custNo){
		Object name= getJdbcTemplate().queryForObject("select t.pledgename from t_zs_cust t where t.custno=?",new Object[]{custNo},String.class);
		if(name!=null)
			return name.toString();
		else
			return "";
	}

	@Override
	public boolean updateDraftToLnci(Gyl022 gyl) {
		getJdbcTemplate().update(" update t_draft_lnci td set td.lnciId = ? "+
					" where td.lnciNo = ?", new Object[]{gyl.getLnciId(),gyl.getLnciNo()});
		return true;
	}
	
	public DraftToLnciVO getDraftToLnciByDraftNum(String draftNum){
		List<DraftToLnciVO> list = getJdbcTemplate().query("select * from t_draft_lnci t where t.draft_num=? "
				, new Object[]{draftNum}, new BeanPropertyRowMapper(DraftToLnciVO.class));
		if(list!=null&&list.size()>0)
			return list.get(0);
		else
			return null;
		
	}
}
