package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zd.core.DAOSupport;
import com.zd.csms.supervisorymanagement.dao.IFixedAssetsDAO;
import com.zd.csms.supervisorymanagement.dao.mapper.FixedAssetsMapper;
import com.zd.csms.supervisorymanagement.model.FixedAssetsQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class FixedAssetsOracleDAO extends DAOSupport implements IFixedAssetsDAO {

	public FixedAssetsOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	//资源查询语句
	private static String select_fixed_assets = "select * from t_fixed_assets";

	private boolean formatFixedAssetsWhereSQL(FixedAssetsQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(!StringUtil.isEmpty(vo.getAsset_num())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("asset_num = ?");
			params.add(vo.getAsset_num());
			queryFlag = true;
		}
		if(vo.getAsset_type()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("asset_type=?");
			params.add(vo.getAsset_type());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getAsset_name())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("asset_name like ?");
			params.add("%"+vo.getAsset_name()+"%");
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
		if(!StringUtil.isEmpty(vo.getModel())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("model like ?");
			params.add("%"+vo.getModel()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getFactory_code())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("factory_code like ?");
			params.add("%"+vo.getFactory_code()+"%");
			queryFlag = true;
		}
		if(vo.getPurchase_date() != null){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("purchase_date = ?");
			params.add(vo.getPurchase_date());
			queryFlag = true;
		}
		if(vo.getAsset_state()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("asset_state=?");
			params.add(vo.getAsset_state());
			queryFlag = true;
		}
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
		if(vo.getSendee() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("sendee = ?");
			params.add(vo.getSendee());
			queryFlag = true;
		}
		
		return !queryFlag;
	}
	
	@Override
	public List<FixedAssetsVO> searchFixedAssetsList(FixedAssetsQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(FixedAssetsOracleDAO.select_fixed_assets);
		formatFixedAssetsWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<FixedAssetsVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new FixedAssetsMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<FixedAssetsVO> searchFixedAssetsListByPage(FixedAssetsQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(FixedAssetsOracleDAO.select_fixed_assets);
		formatFixedAssetsWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<FixedAssetsVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new FixedAssetsMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public boolean updateFixedAssetsSendee(int fixedAssetsId, int sendee) {
		String sql=" update t_fixed_assets set  sendee = ? where id = ? ";
		return this.getJdbcTemplate().update(sql, new Object[]{sendee,fixedAssetsId})>1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FixedAssetsVO> searchFixedAssetsSendeeByUserId(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t3.* from  t_supervisor_basic_information t1 ");
		sql.append(" join t_fixed_asset_list t2 on t1.id = t2.username ");
		sql.append(" join t_fixed_assets t3 on t3.id = t2.fid ");
		sql.append(" join t_repository t4 on t1.id = t4.supervisorid ");
		sql.append(" join t_rbac_user t5 on t5.client_id = t4.id ");
		sql.append(" where t5.id = ?");
		List<FixedAssetsVO> result;
		
		
		try {
			result = this.getJdbcTemplate().query(sql.toString(), new Object[]{id},new FixedAssetsMapper());
		} catch (DataAccessException e) {
			SqlUtil.debug(getDataSourceName(), sql.toString(),new Object[]{id});
			e.printStackTrace();
			result = null;
		}
		
		return result;
	}

}
