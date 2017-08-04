package com.zd.csms.rbac.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.rbac.model.RoleQueryVO;
import com.zd.csms.rbac.model.RoleVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IRoleDAO extends IDAO {

	public String getDataSourceName();
	
	/**
	 * 按条件查询角色集合
	 * @param query 查询条件对象
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleList(RoleQueryVO query);
	
	/**
	 * 按条件查询角色集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleListByPage(RoleQueryVO query, IThumbPageTools tools);
	
	/**
	 * 查询与资源id相关的角色数据（翻页）
	 * @param query 资源查询条件
	 * @param resourceId 资源id
	 * @param tools 翻页工具
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleListWithResource(RoleQueryVO query, int resourceId, IThumbPageTools tools);
	
	/**
	 * 查询与资源id无关的角色数据（翻页）
	 * @param query 资源查询条件
	 * @param resourceId 资源id
	 * @param tools 翻页工具
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleListWithoutResource(RoleQueryVO query, int resourceId, IThumbPageTools tools);
	
	/**
	 * 查询与账户id相关的角色数据（翻页）
	 * @param query 资源查询条件
	 * @param userId 账户id
	 * @param tools 翻页工具
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleListWithUser(RoleQueryVO query, int userId, IThumbPageTools tools);
	
	/**
	 * 查询与账户id相关的角色数据（不翻页）
	 * @param query 资源查询条件
	 * @param userId 账户id
	 * @param tools 翻页工具
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleListWithUser(RoleQueryVO query, int userId);
	
	public List<RoleVO> searchRoleListWithObject(RoleQueryVO query, int objectId);
	/**
	 * 查询与账户id无关的角色数据（翻页）
	 * @param query 资源查询条件
	 * @param userId 账户id
	 * @param tools 翻页工具
	 * @return List<RoleVO>
	 * */
	public List<RoleVO> searchRoleListWithoutUser(RoleQueryVO query, int userId, IThumbPageTools tools);
}
