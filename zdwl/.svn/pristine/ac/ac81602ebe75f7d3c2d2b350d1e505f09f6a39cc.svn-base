package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.dao.ISuperviseArrearsDAO;
import com.zd.csms.marketing.dao.mapper.SuperviseArrearsMapper;
import com.zd.csms.marketing.model.SuperviseArrearsQueryVO;
import com.zd.csms.marketing.model.SuperviseArrearsVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SuperviseArrearsOracleDAO extends DAOSupport implements ISuperviseArrearsDAO {

	public SuperviseArrearsOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_supervise_arrears = "select * from t_supervise_arrears";

	private boolean formatSuperviseArrearsWhereSQL(SuperviseArrearsQueryVO vo, StringBuffer sql, List<Object> params) {
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
		if(vo.getArrears_begin() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("arrears_begin = ?");
			params.add(vo.getArrears_begin());
			queryFlag = true;
		}
		if(vo.getArrears_end() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("arrears_end = ?");
			params.add(vo.getArrears_end());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getArrears_user())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("arrears_user like ?");
			params.add("%"+vo.getArrears_user()+"%");
			queryFlag = true;
		}
		if(vo.getFollow_date() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("follow_date = ?");
			params.add(vo.getFollow_date());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getFollow_user())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("follow_user like ?");
			params.add("%"+vo.getFollow_user()+"%");
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<SuperviseArrearsVO> searchSuperviseArrearsList(SuperviseArrearsQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(SuperviseArrearsOracleDAO.select_supervise_arrears);
		formatSuperviseArrearsWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<SuperviseArrearsVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new SuperviseArrearsMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<SuperviseArrearsVO> searchSuperviseArrearsListByPage(SuperviseArrearsQueryVO query,
			IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(SuperviseArrearsOracleDAO.select_supervise_arrears);
		formatSuperviseArrearsWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<SuperviseArrearsVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new SuperviseArrearsMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
