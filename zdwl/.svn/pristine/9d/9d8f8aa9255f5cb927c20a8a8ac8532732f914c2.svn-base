package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.supervisory.dao.ICarManualDAO;
import com.zd.csms.supervisory.dao.mapper.CarManualMapper;
import com.zd.csms.supervisory.model.CarManualQueryVO;
import com.zd.csms.supervisory.model.CarManualVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class CarManualOracleDAO extends DAOSupport implements ICarManualDAO {

	public CarManualOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_car_manual = "select * from t_car_manual";

	private boolean formatCarManualWhereSQL(CarManualQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getUserid() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("userid = ?");
			params.add(vo.getUserid());
			queryFlag = true;
		}
		if(vo.getSid() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("sid = ?");
			params.add(vo.getSid());
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<CarManualVO> searchCarManualList(CarManualQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(CarManualOracleDAO.select_car_manual);
		formatCarManualWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<CarManualVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new CarManualMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<CarManualVO> searchCarManualListByPage(CarManualQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(CarManualOracleDAO.select_car_manual);
		formatCarManualWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<CarManualVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new CarManualMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
