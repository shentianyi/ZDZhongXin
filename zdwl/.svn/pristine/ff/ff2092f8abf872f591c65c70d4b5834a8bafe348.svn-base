package com.zd.csms.rbac.service;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.ServiceSupport;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.rbac.dao.IDealerGroupDAO;
import com.zd.csms.rbac.dao.RbacDAOFactory;
import com.zd.csms.rbac.model.DealerGroupDealerVO;
import com.zd.csms.rbac.model.DealerGroupUserVO;
import com.zd.csms.rbac.model.DealerGroupVO;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;

public class DealerGroupService extends ServiceSupport {
	private IDealerGroupDAO dao = RbacDAOFactory.getDealerGroupDAO();

	public List<DealerGroupVO> findList(String name, IThumbPageTools tools) {
		return dao.findList(name,tools);
	}

	public DealerGroupVO getGroupById(int id) {
		return dao.get(DealerGroupVO.class, id, new BeanPropertyRowMapper(
				DealerGroupVO.class));
	}

	public boolean updateGroup(DealerGroupVO dealerGroup) {
		dealerGroup.setModifyTime(new Date());
		return dao.update(dealerGroup);
	}

	public boolean addGroup(DealerGroupVO dealerGroup) throws Exception {
		dealerGroup.setCreateTime(new Date());
		dealerGroup.setModifyTime(dealerGroup.getCreateTime());
		dealerGroup.setId(Util.getID(DealerGroupVO.class));
		return dao.add(dealerGroup);
	}

	public List<DealerVO> dealerList(String dealerName, int groupId, IThumbPageTools tools) {
		return dao.dealerList(dealerName,groupId, tools);
	}

	public List<DealerVO> newDealerList(String dealerName, int groupId, IThumbPageTools tools) {
		return dao.newDealerList(dealerName,groupId, tools);
	}

	public List<UserVO> userList(String userName, int groupId, IThumbPageTools tools) {
		return dao.userList(userName,groupId, tools);
	}

	public List<UserVO> newUserList(String userName,int groupId, int roleId, IThumbPageTools tools) {
		return dao.newUserList(userName,groupId, roleId, tools);
	}

	public int getRoleIdByClientType(int code) {
		return dao.RoleIdByClientType(code);
	}

	public boolean addDealer(int groupId, int[] dealerIds) {
		boolean flag = false;
		DealerGroupDealerVO vo = null;
		try {
			for (int dealerId : dealerIds) {
				vo = new DealerGroupDealerVO();
				vo.setGroupId(groupId);
				vo.setDealerId(dealerId);
				flag = dao.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean addUser(int groupId, int[] userIds) {
		boolean flag = false;
		try {
			for (int userId : userIds) {
				DealerGroupUserVO vo = new DealerGroupUserVO();
				vo.setGroupId(groupId);
				vo.setUserId(userId);
				flag = dao.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delDealer(int groupId, int[] dealerIds) {
		boolean flag = false;
		try {
			for (int dealerId : dealerIds) {
				flag = dao.delDealer(groupId, dealerId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delUser(int groupId, int[] userIds) {
		boolean flag = false;
		try {
			for (int userId : userIds) {
				flag = dao.delUser(groupId, userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delGroup(int id) {
		boolean flag = dao.delete(DealerGroupVO.class, id);
		flag = dao.delDealerWithGroup(id);
		flag = dao.delUserWithGroup(id);
		return flag;
	}
	
	public List<Integer> findDealerIdsByUserId(int userId) {
		return dao.findDealerIdsByUserId(userId);
	}

}
