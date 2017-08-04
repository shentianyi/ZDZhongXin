package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.supervisorymanagement.dao.IFixedAssetListDAO;
import com.zd.csms.supervisorymanagement.dao.mapper.FixedAssetListMapper;
import com.zd.csms.supervisorymanagement.model.FixedAssetListQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class FixedAssetListOracleDAO extends DAOSupport implements IFixedAssetListDAO {

	public FixedAssetListOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_fixed_asset_list = "select * from t_fixed_asset_list";

	private boolean formatFixedAssetListWhereSQL(FixedAssetListQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getFid()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("fid=?");
			params.add(vo.getFid());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getDepartment())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("department like ?");
			params.add("%"+vo.getDepartment()+"%");
			queryFlag = true;
		}
		if(vo.getUsername() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("username like ?");
			params.add("%"+vo.getUsername()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getTrade())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("trade like ?");
			params.add("%"+vo.getTrade()+"%");
			queryFlag = true;
		}
		if(vo.getTrade_province()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("trade_province=?");
			params.add(vo.getTrade_province());
			queryFlag = true;
		}
		if(vo.getTrade_city()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("trade_city=?");
			params.add(vo.getTrade_city());
			queryFlag = true;
		}
		if(vo.getDeposit_time() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("deposit_time = ?");
			params.add(vo.getDeposit_time());
			queryFlag = true;
		}
		if(vo.getOut_time() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("out_time = ?");
			params.add(vo.getOut_time());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getExpress())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("express like ?");
			params.add("%"+vo.getExpress()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getExpress_num())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("express_num like ?");
			params.add("%"+vo.getExpress_num()+"%");
			queryFlag = true;
		}
		if(vo.getRepair_time() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("repair_time = ?");
			params.add(vo.getRepair_time());
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<FixedAssetListVO> searchFixedAssetList(FixedAssetListQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(FixedAssetListOracleDAO.select_fixed_asset_list);
		formatFixedAssetListWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<FixedAssetListVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new FixedAssetListMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<FixedAssetListVO> searchFixedAssetListByPage(FixedAssetListQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(FixedAssetListOracleDAO.select_fixed_asset_list);
		formatFixedAssetListWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<FixedAssetListVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new FixedAssetListMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<FixedAssetListVO> searchfixedAssetListByClientId(int client_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from t_fixed_asset_list t1 join t_supervisor_basic_information t2 on t1.username = t2.id ");
		sql.append(" join t_repository t3 on t2.id = t3.supervisorid and t3.id = ?");
		
		
		List<FixedAssetListVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), new Object[]{client_id}, new FixedAssetListMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{client_id});
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<FixedAssetListVO> searchfixedAssetListByUserName(int username) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from t_fixed_asset_list where username = ?");
		List<FixedAssetListVO> result;
		
		try{
			result = this.getJdbcTemplate().query(sql.toString(), new Object[]{username},new FixedAssetListMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), new Object[]{username});
			e.printStackTrace();
			result = null;
		}
		
		return result;
	}

	
}
