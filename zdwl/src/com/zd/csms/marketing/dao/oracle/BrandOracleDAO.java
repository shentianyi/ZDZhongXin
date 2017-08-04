package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.dao.IBrandDAO;
import com.zd.csms.marketing.dao.mapper.BrandMapper;
import com.zd.csms.marketing.model.BrandQueryVO;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class BrandOracleDAO extends DAOSupport implements IBrandDAO {

	public BrandOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_brand = "select * from t_brand";

	private boolean formatBrandWhereSQL(BrandQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(!StringUtil.isEmpty(vo.getName())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("name like ?");
			params.add("%"+vo.getName()+"%");
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<BrandVO> searchBrandList(BrandQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(BrandOracleDAO.select_brand);
		formatBrandWhereSQL(query,sql,params);
		sql.append(" order by nlssort(name,'NLS_SORT=SCHINESE_PINYIN_M') ");
		List<BrandVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BrandMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<BrandVO> searchBrandListByPage(BrandQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(BrandOracleDAO.select_brand);
		formatBrandWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<BrandVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new BrandMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	/**
	 * 验证品牌名是否存在，返回true为不存在，返回false为存在
	 * @param name
	 * @return
	 */
	public boolean validateBrandName(String name){
		int count = getJdbcTemplate().queryForInt("select count(1) from t_brand t where t.name = ?",new Object[]{name});
		if(count>0)
			return true;
		else
			return false;
	}

}
