package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisory.dao.IBankApproveDAO;
import com.zd.csms.supervisory.dao.mapper.BankApproveMapper;
import com.zd.csms.supervisory.model.BankApproveQueryVO;
import com.zd.csms.supervisory.model.BankApproveVO;
import com.zd.csms.supervisory.querybean.BankApproveQueryBean;
import com.zd.csms.supervisory.querybean.CarSummary;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class BankApproveOracleDAO extends DAOSupport implements IBankApproveDAO {

	public BankApproveOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	private boolean formatBankApproveWhereSQL(BankApproveQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getSid()>0){
			sql.append(" and ");
			sql.append("tba.sid=?");
			params.add(vo.getSid());
			queryFlag = true;
		}
		if(vo.getState()>0){
			sql.append(" and ");
			sql.append("tba.state=?");
			params.add(vo.getState());
			queryFlag = true;
		}
		if(vo.getType()>0){
			sql.append(" and ");
			sql.append("tba.type=?");
			params.add(vo.getType());
			queryFlag = true;
		}
		if(StringUtil.isNotEmpty(vo.getDealerName())){
			sql.append(" and ");
			sql.append("md.dealerName like ?");
			params.add("%"+vo.getDealerName()+"%");
			queryFlag = true;
		}
		if(StringUtil.isNotEmpty(vo.getBankName())){
			sql.append(" and ");
			sql.append("tb.bankfullname like ?");
			params.add("%"+vo.getBankName()+"%");
			queryFlag = true;
		}
		if(StringUtil.isNotEmpty(vo.getBrand())){
			sql.append(" and ");
			sql.append("tbra.name like ?");
			params.add("%"+vo.getBrand()+"%");
			queryFlag = true;
		}
		if(StringUtil.isNotEmpty(vo.getVin())){
			sql.append(" and ");
			sql.append("ts.vin like ?");
			params.add("%"+vo.getVin()+"%");
			queryFlag = true;
		}
		
		
		return !queryFlag;
	}
	
	@Override
	public List<BankApproveVO> searchBankApproveList(BankApproveQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(query.getClientid() > 0){
			sql.append("select tba.* from t_bank_approve tba "+
					" left join t_supervise_import ts on tba.sid = ts.id "+
					" left join t_draft td on ts.draft_num = td.draft_num "+
					" left join market_dealer_supervisor mds on td.distribid = mds.dealerid "+
					" where mds.repositoryid =" + query.getClientid());
		}else{
			sql.append(" select * from t_bank_approve tba where 1=1 ");
		}
		
		formatBankApproveWhereSQL(query,sql,params);
		
		List<BankApproveVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BankApproveMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<BankApproveQueryBean> searchBankApproveListByPage(BankApproveQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(
				"select distinct tba.ID, " +
						"tba.SID, " + 
						"tba.STATE, " + 
						"tba.CREATETIME, " + 
						"tba.APPROVETIME, " + 
						"tba.TYPE, " + 
						"ts.CERTIFICATE_DATE, " + 
						"ts.CERTIFICATE_NUM, " + 
						"ts.CAR_MODEL, " + 
						"ts.CAR_STRUCTURE, " + 
						"ts.DISPLACEMENT, " + 
						"ts.COLOR, " + 
						"ts.ENGINE_NUM, " + 
						"ts.VIN, " + 
						"ts.KEY_NUM, " + 
						"ts.MONEY, " + 
						"ts.DES, " + 
						"ts.DRAFT_NUM, " + 
						"ts.KEY_AMOUNT, " + 
						"ts.TWO_NAME");
		sql.append(",md.dealername,tb.bankfullname,tbra.name as \"brandName\" from t_bank_approve tba ");
		sql.append(" inner join t_supervise_import ts on tba.sid = ts.id ");
		sql.append(" inner join t_draft td on ts.draft_num = td.draft_num ");
		sql.append(" inner join market_dealer_supervisor mds on td.distribid = mds.dealerid ");
		sql.append(" inner join market_dealer md on mds.dealerid = md.id ");
		sql.append(" inner join t_bank tb on tb.id = mds.bankid ");
		sql.append(" inner join t_brand tbra on tbra.id = md.brandid ");
		
		
		if(query.getClientid() == 1){//监管员
			sql.append("where mds.repositoryid = ?" );
			params.add(query.getUserid());
		}else if(query.getClientid() == 2){//银行分配
			sql.append("where mds.bankid=?");
			params.add(query.getUserid());
		}else if(query.getClientid() == 3){
			sql.append(" inner join t_yw_bank ty on mds.bankid = ty.bankid ");
			sql.append("where ty.userid=?");
			params.add(query.getUserid());
		}else{
			sql.append(" where 1=1 ");
		}
		formatBankApproveWhereSQL(query,sql,params);
		sql.append(" order by tba.approvetime  desc nulls last ");
		List<BankApproveQueryBean> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BankApproveQueryBean.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public CarSummary getSummaryByBank(BankApproveQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select count(ts.id) as count,sum(to_number(nvl(ts.money, 0))) as carMoney ");
		//统计回款金额
		sql.append(" ,sum(to_number(nvl(ts.payment_amount,0))) as carPaymentAmount ");
		sql.append(" from t_bank_approve tba ");
		sql.append(" inner join t_supervise_import ts on tba.sid = ts.id ");
		sql.append(" inner join t_draft td on ts.draft_num = td.draft_num ");
		sql.append(" inner join market_dealer_supervisor mds on td.distribid = mds.dealerid ");
		sql.append(" inner join market_dealer md on mds.dealerid = md.id ");
		sql.append(" inner join t_bank tb on tb.id = mds.bankid ");
		sql.append(" inner join t_brand tbra on tbra.id = md.brandid ");
		
		if(query.getClientid() == 1){//监管员
			sql.append("where mds.repositoryid = ?" );
			params.add(query.getUserid());
		}else if(query.getClientid() == 2){//银行分配
			sql.append("where mds.bankid=?");
			params.add(query.getUserid());
		}else if(query.getClientid() == 3){
			sql.append(" inner join t_yw_bank ty on mds.bankid = ty.bankid ");
			sql.append("where ty.userid=?");
			params.add(query.getUserid());
		}else{
			sql.append(" where 1=1 ");
		}
		formatBankApproveWhereSQL(query,sql,params);
		sql.append(" order by tba.approvetime  desc nulls last ");
		CarSummary result;
		try{
			result = (CarSummary) getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CarSummary.class));
			System.out.println("getSummaryByBank sql:"+sql.toString());
			System.out.println("getSummaryByBank params:"+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	@Override
	public BankApproveVO searchBankApproveBySid(int vin,int type) {
		return (BankApproveVO)getJdbcTemplate().queryForObject(" select * from t_bank_approve t where t.sid = ? and t.type=? "
				,new Object[]{vin,type},new BeanPropertyRowMapper(BankApproveVO.class));
	}
	
}
