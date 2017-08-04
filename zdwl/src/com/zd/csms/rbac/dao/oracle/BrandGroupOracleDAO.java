package com.zd.csms.rbac.dao.oracle;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.marketing.model.BrandVO;
import com.zd.csms.rbac.dao.IBrandGroupDAO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.model.brand.BrandGroupVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class BrandGroupOracleDAO extends DAOSupport implements IBrandGroupDAO {

	public BrandGroupOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BrandGroupVO> findList(String name,IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from t_rbac_BrandGroup");
		
		if (StringUtil.isNotEmpty(name)) {
			 sql.append("  where name LIKE ?  ");
			 params.add("%" + name + "%");
			}
		sql.append(" order by modifyTime desc ");
		List<BrandGroupVO> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(BrandGroupVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<BrandVO> brandList(String brandName,int groupId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select brand.id, brand.name ");
		sql.append(" from t_Brand brand ");
		sql.append(" inner join  t_rbac_BrandGroup_Brand  brandgroup ");
		sql.append(" on brandgroup.brandid = brand.id ");
		sql.append(" where brandgroup.groupid = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
			
		 if (StringUtil.isNotEmpty(brandName)) {
			 sql.append(" and brand.name LIKE ? ");
			 params.add("%" + brandName + "%");
			}
		
		List<BrandVO> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(BrandVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<BrandVO> newBrandList(String brandName,int groupId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select brand.id, brand.name from t_Brand  brand ");
		sql.append(" left join  ( select BrandId  from  t_rbac_BrandGroup_Brand ");
		sql.append(" where groupId= ? ) BrandGroup ");
		sql.append(" on  BrandGroup.BrandId=Brand.id ");
		sql.append(" where  BrandGroup.BrandId is null ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		
		if (StringUtil.isNotEmpty(brandName)) {
			  sql.append(" and brand.name LIKE ? ");
			  params.add("%" + brandName + "%");
		 }
		
		List<BrandVO> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(BrandVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> userList(String userName ,int groupId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("  select  us.id, us.userName , us.loginid , ");
		sql.append("  us.state  , us.client_type  ");
		sql.append("  from  t_rbac_user  us  ");
		sql.append("  inner join  t_rbac_BrandGroup_user  BrandGroup");
		sql.append("  on  BrandGroup.userId=us.id ");
		sql.append("  where  BrandGroup.groupId= ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		
		if (StringUtil.isNotEmpty(userName)) {
			 sql.append("  and us.userName  LIKE ?  ");
			 params.add("%" + userName + "%");
		 }
		
		List<UserVO> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(UserVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> newUserList(String userName ,int groupId,int roleId ,IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("  select us.id, us.userName , us.loginid , ");
		sql.append("  us.state  , us.client_type  ");
		sql.append("  from  t_rbac_user  us  ");
		sql.append("  inner join  t_rbac_user_role  userRole ");
		sql.append("  on  userRole.user_id=us.id ");
		sql.append("  left join  ( select  userId  from t_rbac_BrandGroup_user ");
		sql.append("   where  groupId= ?  ");
		sql.append("   )  BrandGroup ");
		sql.append("  on  BrandGroup.userId=us.id ");
		sql.append("  where  userRole.role_id= ? ");
		sql.append("  and  BrandGroup.userId is null ");
		sql.append("  and  us.state= ?  ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		params.add(roleId);
		
		params.add(StateConstants.USING.getCode());
		
		if (StringUtil.isNotEmpty(userName)) {
			  sql.append("  and us.userName  LIKE ?  ");
			  params.add("%" + userName + "%");
		}
		
		List<UserVO> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(UserVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public int RoleIdByClientType(int code) {
		String sql=" select id from t_rbac_role where client_type=?  and state=? " ;
		List<Object> params = new ArrayList<Object>();
		params.add(code);
		params.add(StateConstants.USING.getCode());
		return getJdbcTemplate().queryForInt(sql, params.toArray());
	}

	@Override
	public boolean delBrand(int groupId, int BrandId) {
		String sql=" delete from t_rbac_BrandGroup_Brand where groupId=?  and BrandId=? " ;
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		params.add(BrandId);
		return getJdbcTemplate().update(sql, params.toArray()) > 0;
	}

	@Override
	public boolean delUser(int groupId, int userId) {
		String sql=" delete from t_rbac_BrandGroup_user where groupId=?  and userId=? " ;
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		params.add(userId);
		return getJdbcTemplate().update(sql, params.toArray()) > 0;
	}

	@Override
	public boolean delUserWithGroup(int groupId) {
		String sql=" delete from t_rbac_BrandGroup_user where groupId=? " ;
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		return getJdbcTemplate().update(sql, params.toArray()) > 0;
	}
	
	@Override
	public boolean delBrandWithGroup(int groupId) {
		String sql=" delete from t_rbac_BrandGroup_Brand  where groupId=? " ;
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		return getJdbcTemplate().update(sql, params.toArray()) > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findBrandIdsByUserId(int userId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" ( select BrandGroup.BrandId ");
		sql.append("  from t_rbac_BrandGroup_user  groupUser  ");
		sql.append("  inner join  t_rbac_BrandGroup_Brand  BrandGroup");
		sql.append("  on  BrandGroup.groupId=groupUser.groupId ");
		sql.append("  where  groupUser.userId= ?  )");
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {userId},
				new BeanPropertyRowMapper(Integer.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Number> findDealerIdsByUserId(int userId) {
		String sql = "select md.id from market_dealer md join t_rbac_brandgroup_brand bb on bb.brandid=md.brandId join t_rbac_brandgroup_user bu on bu.groupid=bb.groupid where bu.userid= ?";
		List<Number> result = getJdbcTemplate().queryForList(sql, new Object[] {userId}, Number.class);
		return result;
	}

}
