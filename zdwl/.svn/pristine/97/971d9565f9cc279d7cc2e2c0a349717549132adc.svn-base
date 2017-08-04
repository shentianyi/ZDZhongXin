package com.zd.csms.bank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.bank.dao.IBankDAO;
import com.zd.csms.bank.model.BankQueryVO;
import com.zd.csms.bank.model.BankVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * @author licheng
 *
 */

public class BankDao extends DAOSupport implements IBankDAO{

	public BankDao(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 根据parentId获取子集合
	 * @author licheng at 2016年7月14日 上午11:51:41
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BankVO> findChildListById(int id) {
		String sql="select * from t_bank t where t.parentId = ?";
		return getJdbcTemplate().query(sql, new Object[]{id}, new BeanPropertyRowMapper(BankVO.class));
	}

	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BankVO> findBankList(BankQueryVO query,IThumbPageTools tools){
		List<BankVO> list=null;
		StringBuffer sql = new StringBuffer("select * from t_bank t");
		List<Object> params = new ArrayList<Object>();
		formatSQL(query,sql, params);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BankVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<BankVO> findChildrenBankIn(BankQueryVO query,IThumbPageTools tools){
		List<BankVO> list=null;
		StringBuffer sql = new StringBuffer("select t.* from t_bank t ");
		sql.append(" inner join T_BANK_CHILDREN_MANAGER bc on bc.CHILDRENID = t.id ");
		List<Object> params = new ArrayList<Object>();
		formatSQL(query,sql, params);
		if(query.getParentId()>0){
			sql.append(" and t.parentid = ? ");
			params.add(query.getParentId());
		}
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BankVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<BankVO> findChildrenBankOut(BankQueryVO query,IThumbPageTools tools){
		List<BankVO> list=null;
		StringBuffer sql = new StringBuffer("select t.* from t_bank t ");
		List<Object> params = new ArrayList<Object>();
		formatSQL(query,sql, params);
		if(query.getParentId()>0){
			sql.append(" and t.parentid = ? ");
			params.add(query.getParentId());
			sql.append(" and t.id not in ( select bc.childrenId from T_BANK_CHILDREN_MANAGER bc where bc.parentId = ?) ");
			params.add(query.getParentId());
		}
		
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BankVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询银行和其子银行的名称，用逗号隔开
	 * @param query
	 * @return
	 */
	public String findBankNameById(BankQueryVO query){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		int id = query.getId();
		sql.append("select b.bankfullname from t_bank b where b.id=?");
		params.add(id);
		Object obj =  getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), String.class);
		String result = "";
		if(obj!=null)
			result = obj.toString();
		return result;
	}
	
	
	/**
	 * 拼接查询条件
	 * @param query
	 * @param sql
	 * @param params
	 */
	public void formatSQL(BankQueryVO query,StringBuffer sql,List<Object> params){
		Integer[] bankTypes = query.getBankType();
		sql.append(" where 1=1 ");
		if(!StringUtil.isEmpty(query.getCustomerManager())){
			sql.append(" and t.customerManager=? ");
			params.add(query.getCustomerManager());
		}
		
		if(!StringUtil.isEmpty(query.getBankName())){
			sql.append(" and t.bankfullname like ? ");
			params.add("%"+query.getBankName().trim()+"%");
		}
		if(!StringUtil.isEmpty(query.getManagerPhone())){
			sql.append(" and t.ManagerPhone=? ");
			params.add(query.getManagerPhone());
		}
		if(bankTypes!=null && bankTypes.length>0){
			sql.append(" and t.bankType in (");
			for (Integer i : bankTypes) {
				sql.append("?,");
				params.add(i);
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(")");
		}
	}
	
	/**
	 * 根据经销商名称查询银行数量
	 * @param bankName
	 * @return
	 */
	public int findCountByBankName(String bankName,int parentId){
		String sql="select count(1) from t_bank t where t.bankName = ? and t.parentid=?";
		return getJdbcTemplate().queryForInt(sql, new Object[]{bankName,parentId});
	}
	
	
	/**
	 * 级联删除
	 * @param id
	 * @return
	 */
	public boolean deleteBank (int id){
		String sql="delete from t_bank tb where tb.id in "
				+ "( select t.id from t_bank t start with t.id="+id+" connect by prior t.id = t.parentid)";
		return getJdbcTemplate().update(sql)>0;
	}
	
	/**
	 * 删除分行银行管理支行
	 * @param parentId
	 * @param childId
	 * @return
	 */
	public boolean deleteBankChildren(int parentId,int childId){
		String sql="delete from T_BANK_CHILDREN_MANAGER t where t.PARENTID = ? and t.CHILDRENID = ? ";
		return getJdbcTemplate().update(sql,new Object[]{parentId,childId})>0;
	}

	@Override
	public String getBankNameById(int bankId) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select b.bankName from t_bank b where b.id=? ");
		params.add(bankId);
		Object obj =  getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), String.class);
		String result = "";
		if(obj!=null)
			result = obj.toString();
		return result;
	}

	@Override
	public BankVO getTopBank(int bankId) {
		String sql = "select b.* from t_bank a join t_bank b on a.id = ? and a.path like '%' || b.id || '%' and b.parentid  = -1";
		return (BankVO) getJdbcTemplate().queryForObject(sql, new Object[]{bankId}, new BeanPropertyRowMapper(BankVO.class));
	}

}
