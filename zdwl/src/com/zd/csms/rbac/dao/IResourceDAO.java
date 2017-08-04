package com.zd.csms.rbac.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.rbac.model.ResourceQueryVO;
import com.zd.csms.rbac.model.ResourceVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IResourceDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceList(ResourceQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceListByPage(ResourceQueryVO query,IThumbPageTools tools);
	
	/**
	 * 查询与账户id相关的资源数据（通过账户与角色关系和角色与资源关系）
	 * @param userId 账户id
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceListByUser(int userId, int flag);
	
	/* 确认是否有对接 */
	public boolean confirmDj(int repositoryid);
	
	/**
	 * 查询与角色id相关的资源数据（翻页）
	 * @param query 资源查询条件
	 * @param roleId 角色id
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceWithRole(ResourceQueryVO query,int roleId,IThumbPageTools tools);
	
	/**
	 * 查询与角色id无关的资源数据（翻页）
	 * @param query 资源查询条件
	 * @param roleId 角色id
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceWithoutRole(ResourceQueryVO query,int roleId,IThumbPageTools tools);
	
	/**
	 * 通过上级资源id查询资源集合
	 * @param id 上级资源id
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceListByParent(int id);
}
