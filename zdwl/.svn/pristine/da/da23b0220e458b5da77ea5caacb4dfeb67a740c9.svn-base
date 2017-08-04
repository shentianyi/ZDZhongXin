package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.dao.IAgreementBackDAO;
import com.zd.csms.marketing.dao.mapper.AgreementBackMapper;
import com.zd.csms.marketing.model.AgreementBackQueryVO;
import com.zd.csms.marketing.model.AgreementBackVO;
import com.zd.csms.marketing.querybean.AgreementQueryBean;
import com.zd.csms.util.DateUtil;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class AgreementBackOracleDAO extends DAOSupport implements IAgreementBackDAO {

	public AgreementBackOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_agreement_back = "select * from t_agreement_back";

	private boolean formatAgreementBackWhereSQL(AgreementBackQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getDistribid()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.distribid=?");
			params.add(vo.getDistribid());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getFinancial_institution())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.financial_institution like ?");
			params.add("%"+vo.getFinancial_institution()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getAgreement_num())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("t.agreement_num = ?");
			params.add(vo.getAgreement_num());
			queryFlag = true;
		}
		if(vo.getAgreement_date() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" to_char(t.agreement_date,'yyyymmdd') = ?");
			params.add(DateUtil.getStringFormatByDate(vo.getAgreement_date(), "yyyyMMdd"));
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getFinancial_user())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("t.financial_user = ?");
			params.add(vo.getFinancial_user());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getFinancial_phone())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("t.financial_phone = ?");
			params.add(vo.getFinancial_phone());
			queryFlag = true;
		}
		if(vo.getBack_date() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" to_char(t.back_date,'yyyymmdd') = ?");
			params.add(DateUtil.getStringFormatByDate(vo.getBack_date(), "yyyyMMdd"));
			queryFlag = true;
		}
		if(vo.getIsback()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.isback=?");
			params.add(vo.getIsback());
			queryFlag = true;
		}
		if(vo.getSend_date() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("t.send_date = ?");
			params.add(vo.getSend_date());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getDistribname())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" md.dealerName like ?");
			params.add("%"+vo.getDistribname()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getBankName())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append(" tb.bankfullname like ?");
			params.add("%"+vo.getBankName()+"%");
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<AgreementBackVO> searchAgreementBackList(AgreementBackQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(AgreementBackOracleDAO.select_agreement_back);
		formatAgreementBackWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<AgreementBackVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new AgreementBackMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<AgreementQueryBean> searchAgreementBackListByPage(AgreementBackQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.*,md.dealerName,tb.bankfullname as \"bankName\",tbr.name as \"brandName\" ");
		sql.append(" ,tbm.manager as \"bankPerson\" ,tbm.managerPhone as \"bankPersonPhone\" ");
		sql.append(" ,pr.name as \"province\",ct.name as \"city\" ");
		sql.append(" from t_agreement_back t ");
		sql.append(" inner join market_dealer md on md.id = t.distribid ");
		sql.append(" inner join market_dealer_bank mdb on mdb.dealerid = md.id ");
		sql.append(" inner join t_bank tb on tb.id = mdb.bankid ");
		sql.append(" inner join t_brand tbr on tbr.id = md.brandid ");
		sql.append(" left join t_bank_manager tbm on tbm.id = md.bankManagerId ");
		sql.append(" left join t_region pr on pr.id = md.province ");
		sql.append(" left join t_region ct on ct.id = md.city ");
		
		formatAgreementBackWhereSQL(query,sql,params);
		sql.append(" order by t.id desc ");
		List<AgreementQueryBean> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(AgreementQueryBean.class));
			System.out.println("searchAgreementBackListByPage sql:"+sql.toString());
			System.out.println("searchAgreementBackListByPage params:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	/**
	 * 监管协议到期前30天提醒
	 * @param query
	 * @return
	 */
	public List<AgreementBackVO> findListByWarring(int day) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from T_AGREEMENT_BACK t where t.isback = 1 and to_char(t.send_date+"+day+",'yyyymmdd') >= to_char(sysdate,'yyyymmdd')");
		
		List<AgreementBackVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new AgreementBackMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * 协议到期预警
	 * @return
	 */
	public List<AgreementBackVO> agreementExpires(int day) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from T_AGREEMENT_BACK t where "
				+ " to_char(t.agreementexpiretime-"+day+",'yyyymmdd') <= to_char(sysdate,'yyyymmdd')"
						+ " and to_char(t.agreementexpiretime,'yyyymmdd')> =to_char(sysdate,'yyyymmdd')");
		
		List<AgreementBackVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new AgreementBackMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * 协议到期未续签
	 * @param day
	 * @return
	 */
	public List<AgreementBackVO> daoqi() {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				"select ab.* " +
						"  from T_AGREEMENT_BACK ab " + 
						" where ab.id in " + 
						"       (select max(t.id) " + 
						"          from T_AGREEMENT_BACK t " + 
						"         group by t.distribid " + 
						"        having to_char(max(t.agreementexpiretime), 'yyyyMMdd') >= to_char(sysdate, 'yyyyMMdd'))");
		
		List<AgreementBackVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new AgreementBackMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	/*
	 * 需求38 -- 导出协议管理台账
	 * @time 20170518
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<AgreementQueryBean> ExtAgreementLedger(AgreementBackQueryVO asQuery) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.*,md.dealerName,tb.bankfullname as \"bankName\",tbr.name as \"brandName\" ");
		sql.append(" ,tbm.manager as \"bankPerson\" ,tbm.managerPhone as \"bankPersonPhone\" ");
		sql.append(" ,pr.name as \"province\",ct.name as \"city\" ");
		sql.append(" from t_agreement_back t ");
		sql.append(" inner join market_dealer md on md.id = t.distribid ");
		sql.append(" inner join market_dealer_bank mdb on mdb.dealerid = md.id ");
		sql.append(" inner join t_bank tb on tb.id = mdb.bankid ");
		sql.append(" inner join t_brand tbr on tbr.id = md.brandid ");
		sql.append(" left join t_bank_manager tbm on tbm.id = md.bankManagerId ");
		sql.append(" left join t_region pr on pr.id = md.province ");
		sql.append(" left join t_region ct on ct.id = md.city ");
		
		formatAgreementBackWhereSQL(asQuery,sql,params);
		sql.append(" order by t.id desc ");
		List<AgreementQueryBean> result;
		try{
			result = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(AgreementQueryBean.class));
			System.out.println("ExtAgreementLedger sql:"+sql.toString());
			System.out.println("ExtAgreementLedger params:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public boolean updBackDateAndIsback(AgreementBackVO abvo) {
		String sql = "update t_agreement_back t set t.back_date=?, t.isback=? where t.id=? ";
		return this.getJdbcTemplate().update(sql, new Object[]{abvo.getBack_date(),abvo.getIsback(),abvo.getId()})>=0;
	}
	
}
