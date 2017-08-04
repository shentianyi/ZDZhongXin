package com.zd.csms.rbac.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.rbac.constants.RbacConstants;
import com.zd.csms.rbac.dao.IResourceDAO;
import com.zd.csms.rbac.dao.IRoleResourceDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.dao.mapper.ResourceMapper;
import com.zd.csms.rbac.model.ResourceQueryVO;
import com.zd.csms.rbac.model.ResourceVO;
import com.zd.csms.rbac.model.RoleResourceQueryVO;
import com.zd.csms.rbac.model.RoleResourceVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class ResourceService extends ServiceSupport {

	private IResourceDAO dao = RbacDAOFactory.getResourceDAO();
	
	private IRoleResourceDAO roleresourceDao = RbacDAOFactory.getRoleResourceDAO();
	
	public boolean addResource(ResourceVO vo) throws Exception {
		
		boolean flag = false;
		
		vo.setId(Util.getID(ResourceVO.class));
		
		if(vo.getState()==-1){
			vo.setState(StateConstants.USING.getCode());
		}
		
		//设置默认上级id为0
		if(vo.getParent_id()==-1){
			vo.setParent_id(0);
		}
		
		dao.add(vo);
		
		if(vo.getId()>0){
			flag = true;
		}
		return flag;
	}
	
	public boolean updResource(ResourceVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteResource(int id) throws Exception {
		
		List<ResourceVO> list = dao.searchResourceListByParent(id);
		if(list != null && list.size() > 0){
			this.setExecuteMessage("删除资源存在下级不可删除");
			return false;
		}
		
        boolean flag = dao.delete(ResourceVO.class, id);
        if(flag){
        	RoleResourceQueryVO rrquery = new RoleResourceQueryVO();
        	rrquery.setResource_id(id);
        	List<RoleResourceVO> rrList = this.searchRoleResourceList(rrquery);
        	if(rrList != null && rrList.size()>0){
        		for(RoleResourceVO rrvo : rrList){
        			roleresourceDao.delete(RoleResourceVO.class, rrvo.getId());
        		}
        	}
        }
        return flag;
    }
	
	public boolean addRoleResource(int[] roleIds, int[] resourceIds){
		boolean flag = false;
		try{
			//遍历角色和资源id数组，添加对应关系（外层循环角色因角色数据相对较少）
			a:for(int roleId : roleIds){
				for(int resourceId : resourceIds){
					
					RoleResourceVO rrvo = new RoleResourceVO();
					rrvo.setId(Util.getID(RoleResourceVO.class));
					rrvo.setRole_id(roleId);
					rrvo.setResource_id(resourceId);
					
					//执行数据库操作并获取操作结果
					flag = roleresourceDao.add(rrvo);
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
	
	public boolean delRoleResource(int[] roleIds, int[] resourceIds) {
		
		boolean flag = false;
		try{
			//遍历角色和资源id数组，删除对应关系（外层循环角色因角色数据相对较少）
			a:for(int roleId : roleIds){
				for(int resourceId : resourceIds){
					RoleResourceQueryVO rrquery = new RoleResourceQueryVO();
		        	rrquery.setResource_id(resourceId);
		        	rrquery.setRole_id(roleId);
		        	List<RoleResourceVO> rrList = this.searchRoleResourceList(rrquery);
		        	if(rrList != null && rrList.size()>0){
		        		flag = roleresourceDao.delete(RoleResourceVO.class, rrList.get(0).getId());
		        	}
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
	
	
	public ResourceVO loadResourceById(int id) throws Exception{
		ResourceVO resource = dao.get(ResourceVO.class, id,new ResourceMapper());
		return resource;
	}
	
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceList(ResourceQueryVO query){
		return dao.searchResourceList(query);
	}
	
	public List<ResourceVO> searchResourceListByParent(int id){
		return dao.searchResourceListByParent(id);
	}
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceListByPage(ResourceQueryVO vo, IThumbPageTools tools){
		return dao.searchResourceListByPage(vo, tools);
	}
	
	/**
	 * 查询与角色id相关的资源数据（翻页）
	 * @param query 资源查询条件
	 * @param roleId 角色id
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceWithRole(ResourceQueryVO query, int roleId, IThumbPageTools tools){
		return dao.searchResourceWithRole(query, roleId, tools);
	}
	
	/**
	 * 查询与角色id无关的资源数据（翻页）
	 * @param query 资源查询条件
	 * @param roleId 角色id
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceWithoutRole(ResourceQueryVO query, int roleId, IThumbPageTools tools){
		//设置默认查询条件为节点类型和在用资源
		query.setNodeTypes(new Integer[]{RbacConstants.RESOURCE_NODE_TYPE_NODE.getCode()});
		query.setStates(new Integer[]{StateConstants.USING.getCode()});
		return dao.searchResourceWithoutRole(query, roleId, tools);
	}
	
	/**
	 * 查询与账户id相关的资源数据（通过账户与角色关系和角色与资源关系）
	 * @param userId 账户id
	 * @return List<ResourceVO>
	 * */
	public List<ResourceVO> searchResourceListByUser(int userId, int flag){
		return dao.searchResourceListByUser(userId,flag);
	}
	
	public List<RoleResourceVO> searchRoleResourceList(RoleResourceQueryVO query){
		return roleresourceDao.searchRoleResourceList(query);
	}
	
	public boolean confirmDj(int repositoryid){
		return dao.confirmDj(repositoryid);
	}
}
