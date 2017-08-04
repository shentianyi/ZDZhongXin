package com.zd.csms.finance.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.finance.dao.IOpenInvoiceDAO;
import com.zd.csms.finance.dao.mapper.OpenInvoiceMapper;
import com.zd.csms.finance.model.OpenInvoiceQueryVO;
import com.zd.csms.finance.model.OpenInvoiceVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class OpenInvoiceOracleDAO extends DAOSupport implements IOpenInvoiceDAO {

	public OpenInvoiceOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_open_invoice = "select * from t_open_invoice";

	private boolean formatOpenInvoiceWhereSQL(OpenInvoiceQueryVO vo, StringBuffer sql, List<Object> params) {
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
			sql.append("distribid=?");
			params.add(vo.getDistribid());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getFinancial_institution())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("financial_institution like ?");
			params.add("%"+vo.getFinancial_institution()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getBank())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("bank like ?");
			params.add("%"+vo.getBank()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getBrand())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("brand like ?");
			params.add("%"+vo.getBrand()+"%");
			queryFlag = true;
		}
		if(vo.getIntime() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("intime = ?");
			params.add(vo.getIntime());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getSupervisionfee_standard())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("supervisionfee_standard = ?");
			params.add(vo.getSupervisionfee_standard());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getPayment())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("payment = ?");
			params.add(vo.getPayment());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getPay_standard())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("pay_standard = ?");
			params.add(vo.getPay_standard());
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<OpenInvoiceVO> searchOpenInvoiceList(OpenInvoiceQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(OpenInvoiceOracleDAO.select_open_invoice);
		formatOpenInvoiceWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<OpenInvoiceVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new OpenInvoiceMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<OpenInvoiceVO> searchOpenInvoiceListByPage(OpenInvoiceQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(OpenInvoiceOracleDAO.select_open_invoice);
		formatOpenInvoiceWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<OpenInvoiceVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new OpenInvoiceMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
