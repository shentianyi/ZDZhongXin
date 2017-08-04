package com.zd.csms.rbac.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.base.web.model.SelectUserQueryVO;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.rbac.dao.IUserDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.dao.mapper.UserMapper;
import com.zd.csms.rbac.model.UserQueryVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.util.Util;
import com.zd.tools.DigestUtil;
import com.zd.tools.thumbPage.IThumbPageTools;



public class UserService extends ServiceSupport {

	private IUserDAO dao = RbacDAOFactory.getUserDAO();
	
	public UserVO get(int id){
		return dao.get(UserVO.class, id, new UserMapper());
	}
	
	private void encryptPassword(UserVO vo){
		vo.setPassword_random(Math.random()+"");
		String ps = vo.getPassword() + vo.getPassword_random();
		vo.setPassword(DigestUtil.MD5(ps));
	}
	
	public boolean addUser(UserVO vo) throws Exception {
		
		vo.setId(Util.getID(UserVO.class));
		encryptPassword(vo);
		vo.setCreatetime(new Date());
		 
		return dao.add(vo);
	}
	
	public int addjgyUser(UserVO vo) throws Exception {
		int id = Util.getID(UserVO.class);
		vo.setId(id);
		encryptPassword(vo);
		vo.setCreatetime(new Date());
		dao.add(vo);
		return id;
	}
	
	public boolean updUser(UserVO vo) throws Exception {
		return dao.update(vo);
	}
	
	public boolean deleteUser(int id) throws Exception {
		return dao.delete(UserVO.class, id);
    }
	
	public UserVO loadUserById(int id) throws Exception{
		UserVO user = dao.get(UserVO.class, id,new UserMapper());
		return user;
	}
	
	public List<UserVO> searchUserList(UserQueryVO queryVO) throws Exception{
			return dao.searchUserList(queryVO);
	}
	
	public List<UserVO> searchUserListByPage(UserQueryVO query, IThumbPageTools tools) throws Exception{
		return dao.searchUserListByPage(query, tools);
	}
	
	public List<UserVO> searchUserListByPage(SelectUserQueryVO query, IThumbPageTools tools) throws Exception{
		return dao.searchUserListByPage(query, tools);
	}
	
	public UserVO searchUserByLoginid(String loginid){
		return dao.searchUserByLoginid(loginid);
	}
	
	public List<UserVO> searchUserListWithRole(UserQueryVO query, int roleId, IThumbPageTools tools){
		return dao.searchUserListWithRole(query, roleId, tools);
	}
	
	public List<UserVO> searchUserListWithoutRole(UserQueryVO query, int roleId, IThumbPageTools tools){
		//设置默认查询条件为在用账户
		query.setStates(new Integer[]{StateConstants.USING.getCode()});
		return dao.searchUserListWithoutRole(query, roleId, tools);
	}
	
	public boolean updPassword(int id,String password,String lastPassword) throws Exception{
		//通过id获取账户信息
		UserVO user = this.loadUserById(id);
		//比对用户输入原始密码与数据库保存是否一致
		if(!user.getPassword().equals(DigestUtil.MD5(lastPassword.toUpperCase()+user.getPassword_random()))){
			this.setExecuteMessage("登录密码输入错误!");
			return false;
		}

		//创建临时变量用于修改操作
		UserVO updVO = this.loadUserById(id);
		updVO.setPassword(password.toUpperCase());	//设置密码
		encryptPassword(updVO);			//对密码进行加密

		//执行数据库操作并获取操作结果
		boolean flag = this.updUser(updVO);
		if(flag){
			this.setExecuteMessage("修改密码成功!");
		}else{
			this.setExecuteMessage("修改密码失败!");
		}
		return flag;
	}
	
	public List<String> findAllIds() {
		return dao.findAllIds();
	}
	public List<UserVO> findUserListByClientTypeAndClientId(int clientType, int clientId){
		return dao.findUserListByClientTypeAndClientId(clientType, clientId);
	}
}
