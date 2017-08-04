package com.zd.csms.business.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.DAOSupport;
import com.zd.csms.business.dao.IAddresslistDAO;
import com.zd.csms.business.dao.mapper.AddresslistMapper;
import com.zd.csms.business.model.AddresslistQueryVO;
import com.zd.csms.business.model.AddresslistVO;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class AddresslistOracleDAO extends DAOSupport implements IAddresslistDAO {

	public AddresslistOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_addresslist = "select * from t_addresslist";

	private boolean formatAddresslistWhereSQL(AddresslistQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(!StringUtil.isEmpty(vo.getDepartment())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("department = ?");
			params.add(vo.getDepartment());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getName())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("name = ?");
			params.add(vo.getName());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getQuarters())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("quarters = ?");
			params.add(vo.getQuarters());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getPhone())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("phone = ?");
			params.add(vo.getPhone());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getLandline())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("landline = ?");
			params.add(vo.getLandline());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getEmail())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("email = ?");
			params.add(vo.getEmail());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getFax())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("fax = ?");
			params.add(vo.getFax());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getQq())){
			if(queryFlag){
				sql.append(" and ");
			}else{
				sql.append(" where ");
			}
			sql.append("qq = ?");
			params.add(vo.getQq());
			queryFlag = true;
		}
		if(vo.getGenre()>0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("genre=?");
			params.add(vo.getGenre());
			queryFlag = true;
		}
		
		return !queryFlag;
	}

	@Override
	public List<AddresslistVO> searchAddressList(AddresslistQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(AddresslistOracleDAO.select_addresslist);
		formatAddresslistWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<AddresslistVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new AddresslistMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<AddresslistVO> searchAddressListByPage(AddresslistQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(AddresslistOracleDAO.select_addresslist);
		formatAddresslistWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<AddresslistVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new AddresslistMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
