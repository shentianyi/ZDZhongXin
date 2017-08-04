package com.zd.csms.business.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.business.dao.IYwBankDao;
import com.zd.csms.business.dao.mapper.TwoAddressMapper;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.business.model.YwBankQueryBean;
import com.zd.csms.business.model.YwBankQueryVO;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.dao.mapper.YwBankUserMapper;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class YwBankDaoImpl extends DAOSupport implements IYwBankDao{

	public YwBankDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}

	/**
	 * 查询列表
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<YwBankQueryBean> findList(YwBankQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select t.*,tb.bankfullname as \"bankName\" from t_yw_bank t left join t_bank tb on t.bankId = tb.id"
				+ " where t.userid = ? ");
		params.add(query.getUserId());
		formatSQL(query,sql,params);
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and tb.bankfullname like ? ");
			params.add("%"+query.getBankName()+"%");
		}
		List<YwBankQueryBean> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(YwBankQueryBean.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	public void formatSQL(YwBankQueryVO query,StringBuffer sql,List<Object> params){
		
	}
	
	public List<UserVO> ywUserList(YwBankQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select t.* from t_rbac_user t left join t_rbac_user_role ur on ur.user_id = t.id where ur.role_id = ? ");
		params.add(RoleConstants.BUSINESS_COMMISSIONER.getCode());
		if(StringUtil.isNotEmpty(query.getUserName())){
			sql.append(" and t.username like ? ");
			params.add("%"+query.getUserName()+"%");
		}
		List<UserVO> result;
		try{
			//result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(UserVO.class));
			//需求143
			result = tools.goPage(sql.toString(), params.toArray(), new YwBankUserMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	public List<BrandVO> brandList(YwBankQueryVO query, IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select * from t_brand t where t.id ");
		if(query.getBrandType()==1){
			sql.append("in");
		}else if(query.getBrandType()==2){
			sql.append("not in");
		}
		sql.append(" (select bb.brandid from t_yw_bank_brand bb where bb.ywbankid=?) ");
		params.add(query.getId());
		
		if(StringUtil.isNotEmpty(query.getBrandName())){
			sql.append(" and t.name like ? ");
			params.add("%"+query.getBrandName()+"%");
		}
		
		List<BrandVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BrandVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	public List<TwoAddressVO> searchTwoAddress(int userid){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select tt.* from t_two_address tt "+
				" left join market_dealer_supervisor mds on mds.dealerid = tt.distribid "+
				" left join t_yw_bank ty on ty.bankid = mds.bankid "+
				" where ty.userid=" + userid);
		List<TwoAddressVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new TwoAddressMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	public boolean deleteBrand(int brandId,int id){
		String sql="delete from t_yw_bank_brand t where t.ywbankid=? and t.brandid = ?";
		return getJdbcTemplate().update(sql, new Object[]{id,brandId})>0;
	}
	
	public boolean deleteBrandById(int id){
		String sql="delete from t_yw_bank_brand t where t.ywbankid=?";
		return getJdbcTemplate().update(sql, new Object[]{id})>0;
	}

	/**
	 * 根据银行获取对应的业务专员Id
	 */
	@Override
	public String findUserIdByBank(int bankId) {
		String sql="select wm_concat(t.userid) from t_yw_bank t where t.bankid = ?";
		return (String) getJdbcTemplate().queryForObject(sql,new Object[]{bankId},String.class);
	}
}
