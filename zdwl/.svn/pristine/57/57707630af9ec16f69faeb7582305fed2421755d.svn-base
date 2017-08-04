package com.zd.csms.rbac.login.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zd.core.BeanManager;
import com.zd.core.SystemProperty;
import com.zd.core.cache.CacheConstants;
import com.zd.core.cache.CacheStorage;
import com.zd.core.cache.CacheStoreFactory;
import com.zd.csms.constants.CacheStorageNameConstant;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RbacConstants;
import com.zd.csms.rbac.login.model.MenuNodeVO;
import com.zd.csms.rbac.model.ResourceQueryVO;
import com.zd.csms.rbac.model.ResourceVO;
import com.zd.csms.rbac.model.RoleVO;
import com.zd.csms.rbac.model.UserRoleVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.ResourceService;
import com.zd.csms.rbac.service.RoleService;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.DigestUtil;
import com.zd.tools.IPUtil;

/**
 * 账户会话信息管理工具
 */
public class UserSessionUtil {

	/**
	 * 菜单相关缓存
	 * @return CacheStorage
	 */
	private static CacheStorage getMenuCache(){
		CacheStoreFactory factory = (CacheStoreFactory)BeanManager.getBean(CacheConstants.LOCALCACHE.getCode());
		return factory.get(CacheStorageNameConstant.MENU.getCode());
	}
	
	public static void clearMenuCache(){
		getMenuCache().clear();
	}
	
	public static UserSession getUserSession(HttpServletRequest request){
		UserSession userData = (UserSession)request.getSession().getAttribute(LoginConstants.USERSESSION);
		return userData;
	}
	
	public static UserSession login(String loginid,String password,HttpServletRequest request) throws Exception{
		
		UserSession u = createUesrSession(loginid, request);
		
		if(u.getUser()==null){
			//账户名错误
			u.setLoginState(LoginConstants.LOGIN_STATE_LOGINIDERROR);
		} else if(u.getUser().getState()!=StateConstants.USING.getCode()){
			//账户状态错误
			u.setLoginState(LoginConstants.LOGIN_STATE_STATEERROR);
		} else {
			//比对账户密码是否正确
			String ps = DigestUtil.MD5(password + u.getUser().getPassword_random());
			//密码错误
			if(!ps.equals(u.getUser().getPassword())){
				u.setLoginState(LoginConstants.LOGIN_STATE_PASSWORDERROR);
			}
		}
		
		//用户登录状态成功时处理会话
		if(u.getLoginState()==LoginConstants.LOGIN_STATE_SUCCESS){
			//将用户信息设置到上下文中
			request.getSession().setAttribute(LoginConstants.USERSESSION, u);
		}
		
		return u;
	}
	
	public static UserSession createUesrSession(String loginid,HttpServletRequest request) throws Exception{
		
		UserService service = new UserService();
		
		UserVO user = service.searchUserByLoginid(loginid);
		
		//创建账户会话对象
		UserSession userData = createUesrSession(user,request);
		
		return userData;
	}
	
	public static UserSession createUesrSession(UserVO user,HttpServletRequest request) throws Exception{
		UserSession u = new UserSession();
		ResourceService service = new ResourceService();
		//根据request设置登录ip
		u.setIp(IPUtil.getRemoteAddr(request));
		//根据request获取sessionid
		u.setSessionId(getSessionId(request));
		if(user!=null){
			
			RoleService rs = new RoleService();
			
			List<UserRoleVO> urList = rs.searchUserRoleByUserid(user.getId());
			List<RoleVO> rList = new ArrayList<RoleVO>();
			if(urList != null && urList.size()>0){
				for(UserRoleVO ur : urList){
					RoleVO rvo = rs.loadRoleById(ur.getRole_id());
					if(rvo != null){
						rList.add(rvo);
					}
				}
				u.setRole(rList);
			}
			
			//账户存在说明登录状态
			u.setLoginState(LoginConstants.LOGIN_STATE_SUCCESS);
			//设置账户信息
			u.setUser(user);
			
			//如果用户是监管员，需要检测是否浙商银行对接
			if(user.getClient_type()==3){
				boolean djflag = service.confirmDj(user.getClient_id());
				boolean jgyFlag = false;
				List<RoleVO> roles = u.getRole();
				for(RoleVO rvo:roles){
					if(rvo.getId()==10){
						jgyFlag = true;
						break;
					}
				}
				if(!djflag&&jgyFlag){
					//根据用户id获取菜单树
					u.setMeunTree(searchMenuListByUser(user.getId(), 1));
					//根据用户id获取资源集合
					u.setResourceList(searchResourceListByUser(user.getId(), request, 1));
				}else{
					//根据用户id获取菜单树
					u.setMeunTree(searchMenuListByUser(user.getId(), 0));
					//根据用户id获取资源集合
					u.setResourceList(searchResourceListByUser(user.getId(), request, 0));
				}
			}else{
				//根据用户id获取菜单树
				u.setMeunTree(searchMenuListByUser(user.getId(), 0));
				//根据用户id获取资源集合
				u.setResourceList(searchResourceListByUser(user.getId(), request, 0));
			}
			
		} else{
			//账户不存在，设置默认错误状态
			u.setLoginState(LoginConstants.LOGIN_STATE_ERROR);
		}
		
		return u;
	}
	
	@SuppressWarnings("unchecked")
	public
	static List<MenuNodeVO> searchMenuListByUser(int userId, int flag){
		
		//查找用户下是否有缓存的菜单集合
		String key = "menu_user_"+userId;
		List<MenuNodeVO> fullList = (List<MenuNodeVO>)getMenuCache().get(key);
		if(fullList!=null){
			return fullList;
		}
		
		ResourceService service = new ResourceService();
		//返回结果
		fullList = new ArrayList<MenuNodeVO>();
		
		//菜单项Map（用于遍历资源上级节点检查重复等逻辑用）
		Map<Integer, MenuNodeVO> menuMap = new HashMap<Integer, MenuNodeVO>();

		//用户已分配的资源集合（不含目录类资源）
		List<ResourceVO> resourceList = service.searchResourceListByUser(userId,flag);

		//查找缓存中是否有全部菜单项集合（有缓存）
		List<ResourceVO> allMenuList = searchAllMenuResourceList();
		
		//迭代用户下资源，将资源自身及所属所有上级目录转为MenuNodeVO并添加至fullList
		MenuNodeVO node;
		MenuNodeVO menu;
		for(ResourceVO vo : resourceList){
			
			//创建菜单对象
			node = new MenuNodeVO();
			node.setNode(vo);
			
			//如当前节点未加入fullList则添加
			if(!hasThisMenu(fullList,node)){
				fullList.add(node);
			}
			if(node.getNode() != null){
				if(null == node.getNode()){
					System.out.println();
				}
				//资源上级资源id大于0时继续寻找上级资源
				while(node.getNode().getParent_id()>0){
					
					//从map中查找是否有遍历过的目录对象，为保证目录添加不重复及目录下级资源完整，不同资源的相同上级目录需为一个实例
					menu = menuMap.get(node.getNode().getParent_id());
					
					//上级目录不存在时初始化上级目录对象，并添加至menuMap和fullList中
					if(menu == null){
						menu = new MenuNodeVO();
						menu.setNode(getResourceById(node.getNode().getParent_id(),allMenuList));
						menu.setChildNode(new ArrayList<MenuNodeVO>());
						menuMap.put(node.getNode().getParent_id(), menu);
						fullList.add(menu);
					}
					
					//如上级目录下子节点不包含当前节点，则添加
					if(!hasThisMenu(menu.getChildNode(),node)){
						menu.getChildNode().add(node);
					}
					
					node = menu;
					
					
				}
			}
			
			
		}
		
		//按显示顺序排序。
		sortMenu(fullList);
		
		//缓存计算后的菜单集合
		getMenuCache().put(key, fullList);
		
		return fullList;
		
	}
	
	private static boolean hasThisMenu(List<MenuNodeVO> list,MenuNodeVO vo){

		for(MenuNodeVO menu : list){
			//通过比对资源id判断是否同一菜单项
			if(menu.getNode().getId()==vo.getNode().getId()){
				return true;
			}
		}
		
		return false;
	}
	
	private static void sortMenu(List<MenuNodeVO> list){
		if(list==null)return;
		
		Comparator<MenuNodeVO> comp = new MenuComparator();
		//对菜单项集合排序
		Collections.sort(list, comp);
		for(MenuNodeVO vo : list){
			//为菜单项下级菜单集合排序
			sortMenu(vo.getChildNode());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<ResourceVO> searchResourceListByUser(int userId,HttpServletRequest request,int flag) throws Exception{
		
		//查找用户下是否有缓存的资源集合
		String key = "resource_user_"+userId;
		List<ResourceVO> fullList = (List<ResourceVO>)getMenuCache().get(key);
		if(fullList!=null){
			return fullList;
		}
		
		ResourceService service = new ResourceService();
		
		fullList = new ArrayList<ResourceVO>();
		
		//资源Map（用于遍历资源上级节点检查重复等逻辑用）
		Map<Integer, ResourceVO> nodeMap = new HashMap<Integer, ResourceVO>();

		//资源集合
		List<ResourceVO> resourceList = service.searchResourceListByUser(userId,flag);
		UserService us = new UserService();
		UserVO user = us.loadUserById(userId);
		
//		String port = SystemProperty.getPropertyValue("system.properties", "port");
//		List<ResourceVO> rn = new ArrayList<ResourceVO>();
//		if(user.getClient_type() != ClientTypeConstants.ADMINISTRATOR.getCode()){
//			if(port.equals("15000")){
//				for(int i = 0; i<resourceList.size();i++){
//					ResourceVO rvo = resourceList.get(i);
//					if(rvo.getResource_url().indexOf("/ledger/")!=-1){
//						rn.add(rvo);
//					}
//				}
//			}else{
//				for(int i = 0; i<resourceList.size();i++){
//					ResourceVO rvo = resourceList.get(i);
//					if(rvo.getResource_url().indexOf("/ledger/")==-1){
//						rn.add(rvo);
//					}
//				}
//			}
//		}else{
//			rn = resourceList;
//		}
		
		
		//查询全部目录资源（有缓存）
		List<ResourceVO> allResourceList = searchAllMenuResourceList();
		
		//迭代用户下资源，将资源及上级资源加入fullList集合
		ResourceVO parentResource;
		for(ResourceVO vo : resourceList){
			
			//将资源加入到完整资源集合中
			if(!hasThisResource(fullList,vo)){
				fullList.add(vo);
			}
			
			//资源上级资源id大于0时继续寻找上级资源
			while(vo.getParent_id()>0){
				
				//上级资源没有在nodeMap中记录过
				if(!nodeMap.containsKey(vo.getParent_id())){
					
					//查找上级资源
					parentResource = getResourceById(vo.getParent_id(),allResourceList);
					
					//上级资源存在
					if(parentResource!=null){
						vo = parentResource;
						
						//将上级资源设置到nodeMap中
						nodeMap.put(vo.getId(), vo);
						//将上级资源添加到完整资源集合中
						if(!hasThisResource(fullList,vo)){
							fullList.add(vo);
						}
						
					} else{
						break;
					}
					
				} else{
					break;
				}
				
			}
		}

		//按显示顺序排序。
		sortResource(fullList);

		//缓存计算后的菜单集合
		getMenuCache().put(key,fullList);
		
		return fullList;
		
	}
	
	public static List<ResourceVO> searchResourceListByUserId(int userId, int flag){
		
		ResourceService service = new ResourceService();
		
		List<ResourceVO> fullList = new ArrayList<ResourceVO>();
		
		//资源Map（用于遍历资源上级节点检查重复等逻辑用）
		Map<Integer, ResourceVO> nodeMap = new HashMap<Integer, ResourceVO>();

		//资源集合
		List<ResourceVO> resourceList = service.searchResourceListByUser(userId,flag);
		
		//查询全部目录资源（有缓存）
		List<ResourceVO> allResourceList = searchAllMenuResourceList();
		
		
		//迭代用户下资源，将资源及上级资源加入fullList集合
		ResourceVO parentResource;
		for(ResourceVO vo : resourceList){
			
			//将资源加入到完整资源集合中
			if(!hasThisResource(fullList,vo)){
				fullList.add(vo);
			}
			
			//资源上级资源id大于0时继续寻找上级资源
			while(vo.getParent_id()>0){
				
				//上级资源没有在nodeMap中记录过
				if(!nodeMap.containsKey(vo.getParent_id())){
					
					//查找上级资源
					parentResource = getResourceById(vo.getParent_id(),allResourceList);
					
					//上级资源存在
					if(parentResource!=null){
						vo = parentResource;
						
						//将上级资源设置到nodeMap中
						nodeMap.put(vo.getId(), vo);
						//将上级资源添加到完整资源集合中
						if(!hasThisResource(fullList,vo)){
							fullList.add(vo);
						}
						
					} else{
						break;
					}
					
				} else{
					break;
				}
				
			}
		}

		//按显示顺序排序。
		sortResource(fullList);
		
		return fullList;
		
	}

	/**
	 * 检查集合中是否已包含某资源
	 * @param list 资源集合
	 * @param vo 资源对象
	 * @return List<ResourceVO>	资源集合
	 */
	private static boolean hasThisResource(List<ResourceVO> list,ResourceVO vo){

		for(ResourceVO res : list){
			//通过比对资源id判断是否同一资源
			if(res.getId()==vo.getId()){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 对资源项进行排序（按显示顺序排序）
	 * @param list 资源集合
	 * @return void
	 */
	private static void sortResource(List<ResourceVO> list){
		if(list==null)return;
		Comparator<ResourceVO> comp = new ResourceComparator();
		Collections.sort(list, comp);
	}

	/**
	 * 通过资源id及全部目录集合查找目录
	 * @param id 资源id
	 * @param list 资源集合
	 * @return ResourceVO
	 */
	private static ResourceVO getResourceById(int id,List<ResourceVO> list){
		for(ResourceVO vo : list){
			if(vo.getId()==id){
				return vo;
			}
		}
		return null;
	}

	/**
	 * 查询系统内全部目录类资源集合
	 * @return List<ResourceVO>
	 */
	@SuppressWarnings("unchecked")
	private static List<ResourceVO> searchAllMenuResourceList(){
		//查找缓存中是否有全部菜单项集合
		String key = "resource_menu_all";
		List<ResourceVO> list = (List<ResourceVO>)getMenuCache().get(key);
		if(list==null){
			ResourceService service = new ResourceService();
			//查询系统内全部目录资源（节点类型：目录、资源状态：在用）
			ResourceQueryVO queryVO = new ResourceQueryVO();
			queryVO.setNodeTypes(new Integer[]{RbacConstants.RESOURCE_NODE_TYPE_MENU.getCode()});
			queryVO.setStates(new Integer[]{StateConstants.USING.getCode()});
			list = service.searchResourceList(queryVO);
			getMenuCache().put(key, list);
		}
		return list;
	}
	
	public static String getSessionId(HttpServletRequest request){
		return request.getSession().getId();
	}
	
	public static void logout(HttpServletRequest request){
		
		UserSession u = getUserSession(request);
		//从session中移除用户信息
		request.getSession().removeAttribute(LoginConstants.USERSESSION);
		request.getSession().invalidate();
	}
}
