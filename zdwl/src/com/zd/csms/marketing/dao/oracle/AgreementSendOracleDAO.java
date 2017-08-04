package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.dao.IAgreementSendDAO;
import com.zd.csms.marketing.dao.mapper.AgreementBackMapper;
import com.zd.csms.marketing.dao.mapper.AgreementSendMapper;
import com.zd.csms.marketing.model.AgreementBackVO;
import com.zd.csms.marketing.model.AgreementSendQueryVO;
import com.zd.csms.marketing.model.AgreementSendVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class AgreementSendOracleDAO extends DAOSupport implements IAgreementSendDAO {

	public AgreementSendOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_agreement_send = "select * from t_agreement_send";

	private boolean formatAgreementSendWhereSQL(AgreementSendQueryVO vo, StringBuffer sql, List<Object> params) {
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
		if(!StringUtil.isEmpty(vo.getAgreement_num())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("agreement_num = ?");
			params.add(vo.getAgreement_num());
			queryFlag = true;
		}
		if(vo.getAgreement_date() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("agreement_date = ?");
			params.add(vo.getAgreement_date());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getFinancial_user())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("financial_user = ?");
			params.add(vo.getFinancial_user());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getFinancial_phone())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("financial_phone = ?");
			params.add(vo.getFinancial_phone());
			queryFlag = true;
		}
		if(vo.getBack_date() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("back_date = ?");
			params.add(vo.getBack_date());
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<AgreementSendVO> searchAgreementSendList(AgreementSendQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(AgreementSendOracleDAO.select_agreement_send);
		formatAgreementSendWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<AgreementSendVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new AgreementSendMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<AgreementSendVO> searchAgreementSendListByPage(AgreementSendQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(AgreementSendOracleDAO.select_agreement_send);
		formatAgreementSendWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<AgreementSendVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new AgreementSendMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	
	
}
