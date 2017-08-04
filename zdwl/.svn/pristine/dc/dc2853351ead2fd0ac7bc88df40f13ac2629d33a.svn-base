package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.supervisorymanagement.dao.IUsernameManageDAO;
import com.zd.csms.supervisorymanagement.dao.mapper.UsernameManageMapper;
import com.zd.csms.supervisorymanagement.model.UsernameManageQueryVO;
import com.zd.csms.supervisorymanagement.model.UsernameManageVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class UsernameManageOracleDAO extends DAOSupport implements IUsernameManageDAO {

	public UsernameManageOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_username_manage = "select * from t_username_manage";

	private boolean formatUsernameManageWhereSQL(UsernameManageQueryVO vo, StringBuffer sql, List<Object> params) {
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
		if(!StringUtil.isEmpty(vo.getBankname())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("bankname like ?");
			params.add("%"+vo.getBankname()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getSupervisory_name())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("supervisory_name like ?");
			params.add("%"+vo.getSupervisory_name()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getLoginid())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("loginid like ?");
			params.add("%"+vo.getLoginid()+"%");
			queryFlag = true;
		}
		
		return !queryFlag;
	}
	
	@Override
	public List<UsernameManageVO> searchUsernameManageList(UsernameManageQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(UsernameManageOracleDAO.select_username_manage);
		formatUsernameManageWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<UsernameManageVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new UsernameManageMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<UsernameManageVO> searchUsernameManageListByPage(UsernameManageQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(UsernameManageOracleDAO.select_username_manage);
		formatUsernameManageWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<UsernameManageVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new UsernameManageMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
