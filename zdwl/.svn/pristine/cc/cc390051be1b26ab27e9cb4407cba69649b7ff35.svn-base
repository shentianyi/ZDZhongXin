package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.dao.ISupervisePayDAO;
import com.zd.csms.marketing.dao.mapper.SupervisePayMapper;
import com.zd.csms.marketing.model.SupervisePayQueryVO;
import com.zd.csms.marketing.model.SupervisePayVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SupervisePayOracleDAO extends DAOSupport implements ISupervisePayDAO {

	public SupervisePayOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_supervise_pay = "select * from t_supervise_pay";

	private boolean formatSupervisePayWhereSQL(SupervisePayQueryVO vo, StringBuffer sql, List<Object> params) {
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
		if(vo.getSupervise_expire() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("supervise_expire = ?");
			params.add(vo.getSupervise_expire());
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<SupervisePayVO> searchSupervisePayList(SupervisePayQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(SupervisePayOracleDAO.select_supervise_pay);
		formatSupervisePayWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<SupervisePayVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new SupervisePayMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<SupervisePayVO> searchSupervisePayListByPage(SupervisePayQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(SupervisePayOracleDAO.select_supervise_pay);
		formatSupervisePayWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<SupervisePayVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new SupervisePayMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
