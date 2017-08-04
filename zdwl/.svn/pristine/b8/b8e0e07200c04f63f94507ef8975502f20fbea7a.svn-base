package com.zd.csms.rbac.dao.oracle;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.messageAndWaring.message.queryBean.MessageReceiveQueryBean;
import com.zd.csms.rbac.dao.IDealerGroupDAO;
import com.zd.csms.rbac.model.DealerGroupVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class DealerGroupOracleDAO extends DAOSupport implements IDealerGroupDAO {

	public DealerGroupOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DealerGroupVO> findList(String name,IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from t_rbac_dealerGroup");
		
		if (StringUtil.isNotEmpty(name)) {
			 sql.append("  where name LIKE ?  ");
			 params.add("%" + name + "%");
			}
		sql.append(" order by modifyTime desc ");
		List<DealerGroupVO> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(DealerGroupVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<DealerVO> dealerList(String dealerName,int groupId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("  select dealer.id, dealer.dealerName ,dealer.province ,  ");
		sql.append("  dealer.city , dealer.address from market_dealer  dealer  ");
		sql.append("  inner join  t_rbac_dealerGroup_dealer  dealerGroup");
		sql.append("  on  dealerGroup.dealerId=dealer.id ");
		sql.append("  where  dealerGroup.groupId= ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
			
		 if (StringUtil.isNotEmpty(dealerName)) {
			 sql.append("  and dealer.dealerName  LIKE ?  ");
			 params.add("%" + dealerName + "%");
			}
		
		List<DealerVO> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(DealerVO.class));
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<DealerVO> newDealerList(String dealerName,int groupId, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("  select dealer.id, dealer.dealerName ,dealer.province ,  ");
		sql.append("  dealer.city , dealer.address from market_dealer  dealer  ");
		sql.append("  left join  ( select  dealerId  from  t_rbac_dealerGroup_dealer ");
		sql.append("  where groupId= ? ");
		sql.append("   ) dealerGroup ");
		sql.append("  on  dealerGroup.dealerId=dealer.id ");
		sql.append("  where  dealerGroup.dealerId is null");
		sql.append("  and  dealer.cooperationState= ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		params.add(DealerContant.COOPERATIONSTATE_IN.getCode());
		
		if (StringUtil.isNotEmpty(dealerName)) {
			  sql.append("  and dealer.dealerName  LIKE ?  ");
			  params.add("%" + dealerName + "%");
		 }
		
		List<DealerVO> result;
		try {
			result = tools.goPage(sql.toString(), params.toArray(),
					new BeanPropertyRowMapper(DealerVO.class));
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
		sql.append("  inner join  t_rbac_dealerGroup_user  dealerGroup");
		sql.append("  on  dealerGroup.userId=us.id ");
		sql.append("  where  dealerGroup.groupId= ? ");
		
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
		sql.append("  left join  ( select  userId  from t_rbac_dealerGroup_user ");
		sql.append("   where  groupId= ?  ");
		sql.append("   )  dealerGroup ");
		sql.append("  on  dealerGroup.userId=us.id ");
		sql.append("  where  userRole.role_id= ? ");
		sql.append("  and  dealerGroup.userId is null ");
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
	public boolean delDealer(int groupId, int dealerId) {
		String sql=" delete from t_rbac_dealerGroup_dealer where groupId=?  and dealerId=? " ;
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		params.add(dealerId);
		return getJdbcTemplate().update(sql, params.toArray()) > 0;
	}

	@Override
	public boolean delUser(int groupId, int userId) {
		String sql=" delete from t_rbac_dealerGroup_user where groupId=?  and userId=? " ;
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		params.add(userId);
		return getJdbcTemplate().update(sql, params.toArray()) > 0;
	}

	@Override
	public boolean delUserWithGroup(int groupId) {
		String sql=" delete from t_rbac_dealerGroup_user where groupId=? " ;
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		return getJdbcTemplate().update(sql, params.toArray()) > 0;
	}
	
	@Override
	public boolean delDealerWithGroup(int groupId) {
		String sql=" delete from t_rbac_dealerGroup_dealer  where groupId=? " ;
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		return getJdbcTemplate().update(sql, params.toArray()) > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findDealerIdsByUserId(int userId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" ( select dealerGroup.dealerId ");
		sql.append("  from t_rbac_dealerGroup_user  groupUser  ");
		sql.append("  inner join  t_rbac_dealerGroup_dealer  dealerGroup");
		sql.append("  on  dealerGroup.groupId=groupUser.groupId ");
		sql.append("  where  groupUser.userId= ?  )");
		return  getJdbcTemplate().query(sql.toString(),
				new Object[] {userId},
				new BeanPropertyRowMapper(Integer.class));
	}

}
