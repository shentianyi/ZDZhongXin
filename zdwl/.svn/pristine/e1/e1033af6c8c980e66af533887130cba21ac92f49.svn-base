package com.zd.csms.rbac.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.rbac.dao.IRoleDAO;
import com.zd.csms.rbac.dao.IUserRoleDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.dao.mapper.RoleMapper;
import com.zd.csms.rbac.model.RoleQueryVO;
import com.zd.csms.rbac.model.RoleVO;
import com.zd.csms.rbac.model.UserRoleQueryVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class RoleService extends ServiceSupport {

	private IUserRoleDAO userroleDao = RbacDAOFactory.getUserRoleDAO();
	
	private IRoleDAO dao = RbacDAOFactory.getRoleDAO();
	
	public boolean addUserRole(UserRoleVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(UserVO.class));
		
		userroleDao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean addRole(RoleVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(RoleVO.class));
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updRole(RoleVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteRole(int id) throws Exception {
        return dao.delete(RoleVO.class, id);
    }
	
	public RoleVO loadRoleById(int id) throws Exception{
		RoleVO role = dao.get(RoleVO.class, id,new RoleMapper());
		return role;
	}
	
	/**
	 * 按条件查询角色集合
	 * @param query 查询条件对象
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleList(RoleQueryVO vo){
		return dao.searchRoleList(vo);
	}
	
	/**
	 * 按条件查询角色集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleListByPage(RoleQueryVO vo,IThumbPageTools tools){
		return dao.searchRoleListByPage(vo, tools);
	}
	
	/**
	 * 查询与资源id相关的角色数据（翻页）
	 * @param query 角色查询条件
	 * @param resourceId 资源id
	 * @param tools 翻页工具
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleListWithResource(RoleQueryVO query, int resourceId, IThumbPageTools tools){
		return dao.searchRoleListWithResource(query, resourceId, tools);
	}
	
	/**
	 * 查询与资源id无关的角色数据（翻页）
	 * @param query 角色查询条件
	 * @param resourceId 资源id
	 * @param tools 翻页工具
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleListWithoutResource(RoleQueryVO query, int resourceId, IThumbPageTools tools){
		query.setStates(new Integer[]{StateConstants.USING.getCode()});
		return dao.searchRoleListWithoutResource(query, resourceId, tools);
	}
	
	/**
	 * 查询与账户id相关的角色数据（翻页）
	 * @param query 角色查询条件
	 * @param userId 账户id
	 * @param tools 翻页工具
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleListWithUser(RoleQueryVO query, int userId, IThumbPageTools tools){
		UserService us = new UserService();
		UserVO user;
		try {
			user = us.loadUserById(userId);
			if(user==null){
				return null;
			}
			query.setClientType(user.getClient_type());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return dao.searchRoleListWithUser(query, userId, tools);
	}
	
	public List<RoleVO> searchRoleListWithUser(RoleQueryVO query, int userId){
		return dao.searchRoleListWithUser(query, userId);
	}
	
	public List<RoleVO> searchRoleListWithObject(RoleQueryVO query, int objectId){
		return dao.searchRoleListWithObject(query, objectId);
	}
	
	public List<RoleVO> searchRoleListWithoutUser(RoleQueryVO query, int userId, IThumbPageTools tools){
		UserService us = new UserService();
		UserVO user;
		try {
			user = us.loadUserById(userId);
			if(user==null){
				return null;
			}
			query.setClientType(user.getClient_type());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dao.searchRoleListWithoutUser(query, userId, tools);
	}
	
	public boolean delUserRole(int userId, int roleId) throws Exception {
		
		int id = 0;
		
		if(userId > 0 && roleId > 0){
			UserRoleQueryVO urquery = new UserRoleQueryVO();
			urquery.setUser_id(userId);
			urquery.setRole_id(roleId);
			List<UserRoleVO> urList = this.searchUserRoleList(urquery);
			
			if(urList != null && urList.size()>0){
				UserRoleVO urvo = urList.get(0);
				id = urvo.getId();
			}
		}
		userroleDao.delete(UserRoleVO.class, id);
		
		return true;
    }
	
	public void deleteUserRole(int id) throws Exception {
		userroleDao.delete(UserRoleVO.class, id);
    }
	
	public List<UserRoleVO> searchUserRoleList(UserRoleQueryVO queryVO) throws Exception{
		return userroleDao.searchUserRoleList(queryVO);
	}
	
	public List<UserRoleVO> searchUserRoleByUserid(int userid){
		
		List<UserRoleVO> vo = userroleDao.searchUserRoleByUserid(userid);
		
		return vo;
	}
	
	public void delUserRoleByUserId(int userId,UserSession loginuser){
		
		UserRoleQueryVO urquery = new UserRoleQueryVO();
		urquery.setUser_id(userId);
		
		List<UserRoleVO> urList;
		try {
			urList = this.searchUserRoleList(urquery);
			if(urList != null && urList.size()>0){
				for(UserRoleVO urvo : urList){
					this.deleteUserRole(urvo.getId());
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean delUserRole(int[] userIds, int[] roleIds){
		//多表操作时开启事务
		boolean flag = false;
		try{
			//遍历账户和角色id数组，删除对应关系（外层循环角色因角色数据相对较少）
			a:for(int roleId : roleIds){
				for(int userId : userIds){
					//执行数据库操作并获取操作结果
					flag = this.delUserRole(userId, roleId);
					//如操作失败跳出循环取消后续操作
					if(!flag){
						break a;
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean addUserRole(int[] userIds, int[] roleIds){
		//多表操作时开启事务
		boolean flag = false;
		//this.transactionBegin(userroleDao.getDataSourceName());
		try{
			//遍历账户和角色id数组，添加对应关系（外层循环角色因角色数据相对较少）
			a:for(int roleId : roleIds){
				for(int userId : userIds){
					
					UserRoleVO vo = new UserRoleVO();
					vo.setRole_id(roleId);
					vo.setUser_id(userId);
					//执行数据库操作并获取操作结果
					flag = this.addUserRole(vo);
					//如操作失败跳出循环取消后续操作
					if(!flag){
						break a;
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		//返回操作结果
		return flag;
	}
	
	public boolean isContentUserid(int userId,String roleIds){
		
		boolean flag = userroleDao.isContentUser(userId,roleIds);
		return flag;
	}
}
