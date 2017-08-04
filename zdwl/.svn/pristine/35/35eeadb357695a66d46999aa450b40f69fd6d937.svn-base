package com.zd.csms.rbac.dao;


import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.base.web.model.SelectUserQueryVO;
import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IUserDAO extends IDAO{

	public String getDataSourceName();
	
	/**
	 * 按登录名查询账户信息
	 * @param loginid 登录名
	 * @return UserVO
	 * */
	public UserVO searchUserByLoginid(String loginid);
	
	/**
	 * 按条件查询账户集合
	 * @param query 查询条件对象
	 * @return List<UserVO>
	 * */
	public List<UserVO> searchUserList(UserQueryVO query);
	
	/**
	 * 按条件查询角色集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<UserVO>
	 * */
	public List<UserVO> searchUserListByPage(UserQueryVO vo, IThumbPageTools tools);
	
	/**
	 * 按条件查询用户集合（翻页）
	 * @param vo
	 * @param tools
	 * @return
	 */
	public List<UserVO> searchUserListByPage(SelectUserQueryVO vo, IThumbPageTools tools);
	/**
	 * 根据客户类型和角色ID查询用户，暂用于根据客户类型和储备库ID查询用户
	 * @param clientType
	 * @param clientId
	 * @return
	 */
	public List<UserVO>  findUserListByClientTypeAndClientId(int clientType,int clientId);
	
	/**
	 * 查询与角色id相关的账户数据（翻页）
	 * @param query 资源查询条件
	 * @param roleId 角色id
	 * @param tools 翻页工具
	 * @return List<UserVO>
	 * */
	public List<UserVO> searchUserListWithRole(UserQueryVO query, int roleId, IThumbPageTools tools);
	
	/**
	 * 查询与角色id无关的账户数据（翻页）
	 * @param query 资源查询条件
	 * @param roleId 角色id
	 * @param tools 翻页工具
	 * @return List<UserVO>
	 * */
	public List<UserVO> searchUserListWithoutRole(UserQueryVO query, int roleId, IThumbPageTools tools);
	
	/**
	 * 根据部门查询 用户id 不传参数查询所有部门
	 * @param clientTypeIds
	 * @return
	 */
	public String findAllIdByClientType(String...clientTypeIds);
	
	public List<String> findAllIds();
}
