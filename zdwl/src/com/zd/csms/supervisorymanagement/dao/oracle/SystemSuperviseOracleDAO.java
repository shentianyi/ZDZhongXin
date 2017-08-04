package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.supervisorymanagement.dao.ISystemSuperviseDAO;
import com.zd.csms.supervisorymanagement.dao.mapper.SystemSuperviseMapper;
import com.zd.csms.supervisorymanagement.model.SystemSuperviseQueryVO;
import com.zd.csms.supervisorymanagement.model.SystemSuperviseVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class SystemSuperviseOracleDAO extends DAOSupport implements ISystemSuperviseDAO {

	public SystemSuperviseOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_system_supervise = "select * from t_system_supervise";

	private boolean formatSystemSuperviseWhereSQL(SystemSuperviseQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(!StringUtil.isEmpty(vo.getTrade_name())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("trade_name like ?");
			params.add("%"+vo.getTrade_name()+"%");
			queryFlag = true;
		}
		if(vo.getBankid()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("bankid=?");
			params.add(vo.getBankid());
			queryFlag = true;
		}
		if(vo.getBank_fen()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("bank_fen=?");
			params.add(vo.getBank_fen());
			queryFlag = true;
		}
		if(vo.getBank_zhi()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("bank_zhi=?");
			params.add(vo.getBank_zhi());
			queryFlag = true;
		}
		if(vo.getProvince()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("province=?");
			params.add(vo.getProvince());
			queryFlag = true;
		}
		if(vo.getCity()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("city=?");
			params.add(vo.getCity());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getSupervise_name())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("supervise_name like ?");
			params.add("%"+vo.getSupervise_name()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getJob_num())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("job_num = ?");
			params.add(vo.getJob_num());
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
	public List<SystemSuperviseVO> searchSystemSuperviseList(SystemSuperviseQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(SystemSuperviseOracleDAO.select_system_supervise);
		formatSystemSuperviseWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<SystemSuperviseVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new SystemSuperviseMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<SystemSuperviseVO> searchSystemSuperviseListByPage(SystemSuperviseQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(SystemSuperviseOracleDAO.select_system_supervise);
		formatSystemSuperviseWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<SystemSuperviseVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new SystemSuperviseMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
